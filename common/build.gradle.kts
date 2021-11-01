plugins {
    id("com.android.library")
    id("kotlin-android")
}

android {
    compileSdk = 31

    defaultConfig {
        minSdk = 26
        targetSdk = 31
    }

    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
}