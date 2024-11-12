plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("maven-publish")
}
apply(plugin = "com.jfrog.artifactory")

android {
    namespace = ConfigData.namespaceWarp
    compileSdk = ConfigData.compileSdkVersion

    defaultConfig {
        minSdk = ConfigData.minSdkVersion

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            @Suppress("UnstableApiUsage")
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        debug {
            isMinifyEnabled = false
        }
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
            artifactId = ConfigData.artifactIdWarp
            version = ConfigData.warpVersion
            artifact("$buildDir/outputs/aar/${project.name}-release.aar")
            artifact(androidSourcesJar.get())
        }
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

    // UI Tests
    androidTestImplementation(Dependencies.composeJunit)
    debugImplementation(Dependencies.composeUiTestManifest)

    implementation(Dependencies.core)
    implementation(Dependencies.appCompat)
    testImplementation(Dependencies.junit)
    androidTestImplementation(Dependencies.extJunit)
    androidTestImplementation(Dependencies.espressoCore)
}