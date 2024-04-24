plugins {
    id("com.android.library")
    id("kotlin-android")
    id("com.squareup.wire")
}

android {
    compileSdk = 34
    namespace = "com.matejdro.randomizewear.common"

    defaultConfig {
        minSdk = 26
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
}

kotlin {
    jvmToolchain(17)
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
