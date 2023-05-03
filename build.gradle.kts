buildscript {
   repositories {
      google()
      mavenCentral()
      gradlePluginPortal()
   }
   dependencies {
      classpath(libs.androidPluginGradle)
      classpath(libs.kotlin.pluginGradle)
      classpath(libs.wire.pluginGradle)
      classpath(libs.dagger.hilt.pluginGradle)
      classpath(libs.versionsPlugin)
      classpath(libs.kotlinova.gradle)
   }
}

allprojects {
   apply(plugin = "com.github.ben-manes.versions")

   tasks.withType<com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask> {
      rejectVersionIf {
         candidate.version.contains("alpha", ignoreCase = true) ||
                 candidate.version.contains("beta", ignoreCase = true) ||
                 candidate.version.contains("RC", ignoreCase = true) ||
                 candidate.version.contains("M", ignoreCase = true) ||
                 candidate.version.contains("dev", ignoreCase = true) ||
                 candidate.version.contains("eap", ignoreCase = true)
      }

      // Output library version report into json that toml-version-bump will read
      reportfileName = "versions"
      outputFormatter = "json"
   }
}
