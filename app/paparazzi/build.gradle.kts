
plugins {
    id("com.android.library")
    id("app.cash.paparazzi")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.schibsted.nmp.warpapp.paparazzi"
    compileSdk = ConfigData.compileSdkVersion

    buildFeatures {
        compose = true
    }

    defaultConfig {
        minSdk = ConfigData.minSdkVersion

    }
    testOptions.unitTests {
        all {
            it.enabled = true
        }
        isReturnDefaultValues = true
        isIncludeAndroidResources = true
        tasks.withType<Test> {
            useJUnitPlatform()
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = Versions.jvm
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.kotlinCompiler
    }

}

dependencies {
    val composeBom = platform(Dependencies.composeBom)
    implementation(composeBom)
    implementation(Dependencies.composeUi)
    implementation(Dependencies.composeUiToolingPreview)
    implementation(Dependencies.composeUiTooling)
    implementation(Dependencies.composeFoundation)
    //Material
    implementation(Dependencies.composeMaterial3)

    implementation(project(path = ":warp"))
    implementation(project(path = ":warp-tori"))
    implementation(project(path = ":warp-finn"))

    implementation(Dependencies.material)

    testImplementation(Dependencies.junit)
    testImplementation(Dependencies.composeActivity)
    testImplementation(Dependencies.composeJunit)
    testImplementation(Dependencies.kotestCore)
    testImplementation(Dependencies.kotestRunner)
    testImplementation(Dependencies.testParameterInjector)
    testImplementation(Dependencies.composeUiTest)
    testImplementation(Dependencies.composeUiTestManifest)
}
