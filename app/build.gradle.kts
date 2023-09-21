plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.schibsted.nmp.warpapp"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.schibsted.nmp.warpapp"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
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
    val composeBom = platform("androidx.compose:compose-bom:2023.04.01")
    implementation(composeBom)
    androidTestImplementation(composeBom)
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    debugImplementation("androidx.compose.ui:ui-tooling")
    implementation("androidx.compose.foundation:foundation")
    implementation("androidx.activity:activity-compose:1.6.1")
    implementation("androidx.compose.material3:material3")

    implementation("androidx.navigation:navigation-compose:2.6.0")

    implementation("com.schibsted.nmp.warp:warp-android-tori:0.0.1")
    implementation("com.schibsted.nmp.warp:warp-android-finn:0.0.1")
    implementation(project(path = ":warp"))

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("com.google.android.material:material:1.5.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}