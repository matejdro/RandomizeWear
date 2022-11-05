plugins {
   id("com.android.application")
   kotlin("android")
   kotlin("kapt")
   id("dagger.hilt.android.plugin")
   id("com.squareup.wire")
}

android {
   compileSdk = 33

   defaultConfig {
      applicationId = "com.matejdro.randomizewear"
      minSdk = 26
      targetSdk = 31

      versionCode = 1
      versionName = "1.0"
   }

   buildFeatures {
      compose = true
   }

   composeOptions {
      kotlinCompilerExtensionVersion = libs.versions.androidx.compose.asProvider().get()
   }

   compileOptions {
      sourceCompatibility(JavaVersion.VERSION_1_8)
      targetCompatibility(JavaVersion.VERSION_1_8)
   }

   kotlinOptions {
      jvmTarget = "1.8"

      freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlin.ExperimentalStdlibApi"
      freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlin.RequiresOptIn"
   }

   wire {
      kotlin {}
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
