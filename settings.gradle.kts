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

//snapshot-dirs
listOf(
    "test-utils",
    "alert",
    "badge",
    "box",
    "button",
    "callout",
    "checkbox",
    "datepicker",
    "divider",
    "expandable",
    "icons",
    "link",
    "logo",
    "navigationbar",
    "pageindicator",
    "pill",
    "popover",
    "radio",
    "rangeslider",
    "select",
    "slider",
    "snackbar",
    "spinner",
    "state",
    "switch",
    "tabs",
    "textfield",
    "toast",
    "tooltip",
    "topappbar"
).forEach { name ->
    include(":snapshot:snapshot-$name")
    project(":snapshot:snapshot-$name").projectDir = file("snapshot/$name")
}

include(":warp")
include(":warp-blocket")
include(":warp-dba")
include(":warp-finn")
include(":warp-tori")
include(":warp-vend")
include(":warp-neutral")
