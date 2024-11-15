plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("app.cash.paparazzi")
}

android {
    namespace = "com.schibsted.snapshot"

    namespace = ConfigData.namespaceFinn
    compileSdk = ConfigData.compileSdkVersion

    defaultConfig {
        minSdk = ConfigData.minSdkVersion

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = false
        }
    }
    testOptions.unitTests.isIncludeAndroidResources = true

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(17)
    }
    @Suppress("UnstableApiUsage")
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.kotlinCompiler
    }
}

dependencies {
    val composeBom = platform(Dependencies.composeBom)
    implementation(composeBom)
    androidTestImplementation(composeBom)
    implementation(Dependencies.composeMaterial3)
    implementation(Dependencies.composeUi)
    implementation(Dependencies.composeUiToolingPreview)
    debugImplementation(Dependencies.composeUiTooling)
    implementation(Dependencies.composeFoundation)
    implementation("com.google.testparameterinjector:test-parameter-injector:1.9") {
        exclude(group = "org.hamcrest")
    }
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.8.0")

    implementation(project(":warp"))
    implementation(project(":warp-finn"))
    implementation(project(":warp-tori"))
    implementation(project(":warp-dba"))
    implementation(project(":warp-blocket"))
}