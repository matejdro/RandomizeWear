plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = 33
    namespace = "com.matejdro.randomizewear.wear"

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

    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_17)
        targetCompatibility(JavaVersion.VERSION_17)
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidx.compose.compiler.get()
    }

    kotlinOptions {
        freeCompilerArgs = freeCompilerArgs + "-Xopt-in=androidx.wear.compose.material.ExperimentalWearMaterialApi"
        freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi"
        freeCompilerArgs = freeCompilerArgs + "-Xopt-in=androidx.compose.animation.ExperimentalAnimationApi"
        freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlin.RequiresOptIn"
    }
}

kotlin {
    jvmToolchain(17)
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
    implementation(libs.androidx.datastore)
    implementation(libs.androidx.core)
    implementation(libs.androidx.wear)
    implementation(libs.dagger.hilt)
    implementation(libs.dagger.hilt.navigationCompose)
    implementation(libs.kotlin.coroutines)
    implementation(libs.kotlin.coroutines.playServices)
    implementation(libs.logcat)
    implementation(libs.playServices.wearable)

    kapt(libs.dagger.hilt.compiler)
}
