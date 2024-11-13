plugins {
    id("com.android.library")
    id("kotlin-android")
    id("org.jetbrains.kotlin.plugin.compose")
    id("com.squareup.wire")
}

android {
    compileSdk = 35
    namespace = "com.matejdro.randomizewear.common"

    defaultConfig {
        minSdk = 26
    }

    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_17)
        targetCompatibility(JavaVersion.VERSION_17)
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
