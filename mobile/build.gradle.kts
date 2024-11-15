plugins {
   id("com.android.application")
   kotlin("android")
   kotlin("kapt")
   id("org.jetbrains.kotlin.plugin.compose")
   id("dagger.hilt.android.plugin")
   id("com.squareup.wire")
   id("kotlinova")
}

android {
   compileSdk = 35
   namespace = "com.matejdro.randomizewear"

   defaultConfig {
      applicationId = "com.matejdro.randomizewear"
      minSdk = 26
      targetSdk = 34

      versionCode = 1
      versionName = "1.0"
   }

   compileOptions {
      sourceCompatibility(JavaVersion.VERSION_17)
      targetCompatibility(JavaVersion.VERSION_17)
   }

   kotlinOptions {
      freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlin.ExperimentalStdlibApi"
      freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlin.RequiresOptIn"
   }

   wire {
      kotlin {}
   }
}

kotlin {
   jvmToolchain(17)
}

kotlinova {
   tomlVersionBump {
      // Search for all instances of versions.json, generated by the gradle versions plugin.
      versionReportFiles.set(
         fileTree(rootDir).apply {
            include("**/build/dependencyUpdates/versions.json")
         }
      )

      // Location of the toml file that we should bump versions for
      tomlFile.set(File(rootDir, "libs.toml"))
   }
}

dependencies {
   implementation(project(":common"))

   implementation(libs.androidx.activity.compose)
   implementation(libs.androidx.appcompat)
   implementation(libs.androidx.core)
   implementation(libs.androidx.compose.material)
   implementation(libs.androidx.compose.foundation)
   implementation(libs.androidx.compose.ui)
   implementation(libs.androidx.compose.ui.tooling)
   implementation(libs.androidx.datastore)
   implementation(libs.kotlin.coroutines.playServices)
   implementation(libs.playServices.wearable)
   implementation(libs.dagger.hilt)
   implementation(libs.logcat)
   implementation(libs.wire.runtime)

   kapt(libs.dagger.hilt.compiler)
}
