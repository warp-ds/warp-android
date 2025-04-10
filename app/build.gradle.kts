plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.schibsted.nmp.warpapp"
    compileSdk = ConfigData.compileSdkVersion

    defaultConfig {
        applicationId = "com.schibsted.nmp.warpapp"
        minSdk = ConfigData.minSdkVersion
        targetSdk = ConfigData.compileSdkVersion
        versionCode = ConfigData.sampleAppVersionCode
        versionName = ConfigData.warpVersion

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(17)
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.kotlinCompiler
    }
}
allprojects {
    repositories {
        mavenCentral()
        mavenLocal()
        google()
        maven { url = uri("https://jitpack.io") }
        maven {
            url = uri("https://artifacts.schibsted.io/artifactory/libs-release")
            credentials {
                username = Artifactory.userName
                password = Artifactory.password
            }
        }
        maven { url = uri("https://plugins.gradle.org/m2/") }
    }
}

dependencies {
    val composeBom = platform(Dependencies.composeBom)
    implementation(composeBom)
    androidTestImplementation(composeBom)
    implementation(Dependencies.composeUi)
    implementation(Dependencies.composeUiToolingPreview)
    debugImplementation(Dependencies.composeUiTooling)
    implementation(Dependencies.composeFoundation)
    implementation(Dependencies.koin)
    //Material
    implementation(Dependencies.composeMaterial3)
    implementation(Dependencies.constraintLayout)

    implementation(Dependencies.navigationCompose)
    implementation(Dependencies.koin)

    implementation(project(path = ":warp"))
    implementation(project(path = ":warp-tori"))
    implementation(project(path = ":warp-finn"))
    implementation(project(path = ":warp-dba"))
    implementation(project(path = ":warp-blocket"))
    implementation(project(path = ":warp-vend"))


    implementation(Dependencies.material)
    implementation(Dependencies.core)
    implementation(Dependencies.appCompat)
    testImplementation(Dependencies.junit)
    androidTestImplementation(Dependencies.extJunit)
    androidTestImplementation(Dependencies.espressoCore)
}