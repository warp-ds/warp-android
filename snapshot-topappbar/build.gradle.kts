plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("app.cash.paparazzi")
}

android {
    namespace = "com.schibsted.snapshot.topappbar"
    compileSdk = ConfigData.compileSdkVersion

    defaultConfig {
        minSdk = ConfigData.minSdkVersion

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(17)
    }
    testOptions.unitTests.isIncludeAndroidResources = true

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.kotlinCompiler
    }
}

dependencies {
    implementation(project(":snapshot-test-utils"))
    implementation(Dependencies.composeMaterial3)
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.8.0")
}
