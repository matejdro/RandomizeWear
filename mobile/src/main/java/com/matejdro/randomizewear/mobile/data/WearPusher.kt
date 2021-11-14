package com.matejdro.randomizewear.mobile.data

import android.content.ContentResolver
import android.net.Uri
import android.provider.DocumentsContract
import com.matejdro.randomizewear.models.RandomList
import com.matejdro.randomizewear.models.RandomLists
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WearPusher @Inject constructor(private val settingsRepository: SettingsRepository, private val contentResolver: ContentResolver) {
   suspend fun refresh() {
      val listsOnDisk = loadLists()
   }

   private suspend fun loadLists(): RandomLists = withContext(Dispatchers.IO) {
      val uri = settingsRepository.uri.first() ?: return@withContext RandomLists(emptyList())

      val parsedUri = DocumentsContract.buildChildDocumentsUriUsingTree(uri, DocumentsContract.getTreeDocumentId(uri))

      val lists: List<RandomList> = buildList {
         contentResolver.query(
            parsedUri,
            arrayOf(
               DocumentsContract.Document.COLUMN_DOCUMENT_ID,
               DocumentsContract.Document.COLUMN_DISPLAY_NAME
            ),
            "${DocumentsContract.Document.COLUMN_MIME_TYPE} = ?",
            arrayOf("text/plain"),
            "${DocumentsContract.Document.COLUMN_DISPLAY_NAME} ASC"
         )?.use { cursor ->
            while (cursor.moveToNext()) {
               val id = cursor.getString(0)
               val name = cursor.getString(1).removeSuffix(".txt")

               val content = readFile(uri, id)
               if (content.isEmpty()) {
                  continue
               }

               add(RandomList(name, content))
            }
         }
      }

      RandomLists(lists)
   }

   private fun readFile(parentUri: Uri, id: String): List<String> {
      val documentId = DocumentsContract.buildDocumentUriUsingTree(parentUri, id)
      @Suppress("BlockingMethodInNonBlockingContext")
      return contentResolver.openInputStream(documentId)!!.bufferedReader().readLines()
         .filter { it.isNotBlank() }
   }
}
