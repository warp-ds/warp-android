plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.schibsted.snapshot.testutils"
    compileSdk = ConfigData.compileSdkVersion

    defaultConfig {
        minSdk = ConfigData.minSdkVersion
    }

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
    api(composeBom)
    api(Dependencies.composeUi)
    api(Dependencies.composeFoundation)

    api(project(":warp"))
    api(project(":warp-finn"))
    api(project(":warp-tori"))
    api(project(":warp-dba"))
    api(project(":warp-blocket"))

    api("com.google.testparameterinjector:test-parameter-injector:1.9") {
        exclude(group = "org.hamcrest")
    }
}
