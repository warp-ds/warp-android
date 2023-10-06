object Artifactory {
    var userName: String = ""
    var password: String = ""
}

object Dependencies {
    const val core = "androidx.core:core-ktx:${Versions.core}"
    const val composeBom = "androidx.compose:compose-bom:${Versions.compose}"
    const val composeUi = "androidx.compose.ui:ui"
    const val composeUiToolingPreview = "androidx.compose.ui:ui-tooling-preview"
    const val composeUiTooling = "androidx.compose.ui:ui-tooling"
    const val composeFoundation = "androidx.compose.foundation:foundation"
    const val composeMaterial3 = "androidx.compose.material3:material3"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout-compose:${Versions.constraintLayout}"
    const val composeJunit = "androidx.compose.ui:ui-test-junit4"
    const val composeUiTestManifest = "androidx.compose.ui:ui-test-manifest"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val junit = "junit:junit:${Versions.junit}"
    const val extJunit = "androidx.test.ext:junit:${Versions.extJunit}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
    const val koin = "io.insert-koin:koin-androidx-compose:${Versions.koin}"
}