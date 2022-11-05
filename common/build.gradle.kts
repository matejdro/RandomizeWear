plugins {
    id("com.android.library")
    id("kotlin-android")
    id("com.squareup.wire")
}

android {
    compileSdk = 33

    defaultConfig {
        minSdk = 26
        targetSdk = 31
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
    }
}

wire {
    kotlin {}
}

dependencies {
    api(libs.wire.runtime)

    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.lifecycle)
    implementation(libs.kotlin.coroutines)
}
