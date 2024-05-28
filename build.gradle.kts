// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.1" apply false
    id("com.android.library") version "8.2.1" apply false
    id("org.jetbrains.kotlin.android") version Versions.kotlin apply false
    id("com.google.gms.google-services") version Versions.googleServices apply false
    id("maven-publish")
    id("app.cash.paparazzi") version Versions.paparazzi apply false
}
apply(plugin = "com.jfrog.artifactory")


buildscript {
    Artifactory.userName = System.getenv("ARTIFACTORY_USER") ?: extra["ARTIFACTORY_USER"].toString()
    Artifactory.password = System.getenv("ARTIFACTORY_PWD") ?: extra["ARTIFACTORY_PWD"].toString()

    repositories {
        mavenCentral()
        maven { url = uri("https://plugins.gradle.org/m2/") }
        google()
        maven {
            url = uri("https://artifacts.schibsted.io/artifactory/plugins-release")
            credentials {
                username = System.getenv("ARTIFACTORY_USER") ?: extra["ARTIFACTORY_USER"].toString()
                password = System.getenv("ARTIFACTORY_PWD") ?: extra["ARTIFACTORY_PWD"].toString()
            }
        }
    }
    dependencies {
        classpath("com.jfrog.bintray.gradle:gradle-bintray-plugin:1.8.5")
        classpath("org.jfrog.buildinfo:build-info-extractor-gradle:5.1.14")
    }

}
configure<org.jfrog.gradle.plugin.artifactory.dsl.ArtifactoryPluginConvention> {
    publish {
        // Define the Artifactory URL for publishing artifacts
        contextUrl = "https://artifacts.schibsted.io/artifactory"
        // Define the project repository to which the artifacts will be published
        repository {
            // Set the Artifactory repository key
            repoKey = "libs-release-local"
            // Specify the publisher username
            username = Artifactory.userName
            // Provide the publisher password
            password = Artifactory.password

        }

        // Include all configured publications for all the modules
        defaults {
            publications("ALL_PUBLICATIONS")
        }
    }
}
