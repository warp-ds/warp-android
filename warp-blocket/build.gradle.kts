import Dependencies.composeBom
import Dependencies.composeFoundation
import Dependencies.composeMaterial3
import Dependencies.composeUi
import Dependencies.composeUiTooling
import Dependencies.composeUiToolingPreview

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("maven-publish")
}
apply(plugin = "com.jfrog.artifactory")

android {
    namespace = ConfigData.namespaceBlocket
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
    testOptions.unitTests {
        isIncludeAndroidResources = true
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
val androidSourcesJar by tasks.registering(Jar::class) {
    archiveClassifier.set("sources")
    from(android.sourceSets.getByName("main").java.srcDirs)
}
configure<PublishingExtension> {
    publications {
        create<MavenPublication>("aar") {
            groupId = ConfigData.groupId
            artifactId = ConfigData.artifactIdBlocket
            version = ConfigData.warpVersion
            artifact("$buildDir/outputs/aar/${project.name}-release.aar")
            artifact(androidSourcesJar.get())
        }
    }
}

dependencies {
    val composeBom = platform(composeBom)
    implementation(composeBom)
    androidTestImplementation(composeBom)
    implementation(composeUi)
    implementation(composeUiToolingPreview)
    debugImplementation(composeUiTooling)
    implementation(composeFoundation)
    implementation(composeMaterial3)


    implementation(project(":warp"))

}