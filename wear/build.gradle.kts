plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "com.matejdro.randomizewear.wear"
        minSdk = 26
        targetSdk = 31

        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        compose = true
    }

    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidx.compose.asProvider().get()
    }

    kotlinOptions {
        jvmTarget = "1.8"

        freeCompilerArgs = freeCompilerArgs + "-Xopt-in=androidx.wear.compose.material.ExperimentalWearMaterialApi"
    }
}

kapt {
    correctErrorTypes = true
    useBuildCache = true
}

dependencies {
    implementation(project(":common"))

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.compiler)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.lifecycle.viewModel.compose)
    implementation(libs.androidx.compose.wear.foundation)
    implementation(libs.androidx.compose.wear.material)
    implementation(libs.androidx.compose.wear.navigation)
    implementation(libs.androidx.core)
    implementation(libs.androidx.wear)
    implementation(libs.dagger.hilt)
    implementation(libs.dagger.hilt.navigationCompose)
    implementation(libs.kotlin.coroutines)
    implementation(libs.logcat)
    implementation(libs.playServices.wearable)

    kapt(libs.dagger.hilt.compiler)
}
