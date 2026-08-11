object Artifactory {
    var userName: String = ""
    var password: String = ""
}

object Dependencies {
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val composeBom = "androidx.compose:compose-bom:${Versions.compose}"
    const val composeFoundation = "androidx.compose.foundation:foundation"
    const val composeJunit = "androidx.compose.ui:ui-test-junit4"
    const val composeMaterial3 = "androidx.compose.material3:material3:1.4.0"
    const val composeUi = "androidx.compose.ui:ui"
    const val composeUiTestManifest = "androidx.compose.ui:ui-test-manifest"
    const val composeUiTooling = "androidx.compose.ui:ui-tooling"
    const val composeUiToolingPreview = "androidx.compose.ui:ui-tooling-preview"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout-compose:${Versions.constraintLayout}"
    const val core = "androidx.core:core-ktx:${Versions.core}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
    const val extJunit = "androidx.test.ext:junit:${Versions.extJunit}"
    const val junit = "junit:junit:${Versions.junit}"
    const val koin = "io.insert-koin:koin-androidx-compose:${Versions.koin}"
    const val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val materialIconsCore = "androidx.compose.material:material-icons-core"
    const val navigationCompose = "androidx.navigation:navigation-compose:${Versions.navigationCompose}"
}
