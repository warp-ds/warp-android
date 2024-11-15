pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        mavenLocal()
    }
}
dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositoriesMode.set(RepositoriesMode.PREFER_PROJECT)
    @Suppress("UnstableApiUsage")
    repositories {
        google()
        mavenCentral()
        mavenLocal()
    }
}
rootProject.name = "Warp app"
include(":app")
include(":warp")
include(":warp-finn")
include(":warp-tori")
include(":snapshot")
include(":warp-dba")
include(":warp-blocket")
