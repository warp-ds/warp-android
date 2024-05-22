
plugins {
    id("com.android.library")
    id("app.cash.paparazzi")

}

android {
    namespace = "com.schibsted.nmp.warpapp.paparazzi"
    compileSdk = ConfigData.compileSdkVersion

    buildFeatures {
        compose = true
    }

}

dependencies {
    val composeBom = platform(Dependencies.composeBom)
    implementation(composeBom)
    androidTestImplementation(composeBom)
    implementation(Dependencies.composeUi)
    implementation(Dependencies.composeUiToolingPreview)
    implementation(Dependencies.composeUiTooling)
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


    implementation(Dependencies.material)
    implementation(Dependencies.core)
    implementation(Dependencies.appCompat)

    
    testImplementation(Dependencies.junit)
    testImplementation(Dependencies.composeActivity)
    testImplementation(Dependencies.composeJunit)
    testImplementation(Dependencies.kotestCore)
    testImplementation(Dependencies.kotestRunner)
    testImplementation(Dependencies.testParameterInjector)
    androidTestImplementation(Dependencies.extJunit)
}
