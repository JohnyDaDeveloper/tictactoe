package cz.johnyapps.tictactoe.convention.plugin

import cz.johnyapps.tictactoe.convention.PluginFactory
import cz.johnyapps.tictactoe.convention.extension.androidLibraryExtension
import cz.johnyapps.tictactoe.convention.extension.compose
import cz.johnyapps.tictactoe.convention.extension.debugImplementation
import cz.johnyapps.tictactoe.convention.extension.getLibrary
import cz.johnyapps.tictactoe.convention.extension.getPluginId
import cz.johnyapps.tictactoe.convention.extension.kotlinMultiplatformExtension
import cz.johnyapps.tictactoe.convention.extension.versionCatalog
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class MultiplatformUiPlugin :  Plugin<Project> by PluginFactory build {
    apply(plugin = versionCatalog.getPluginId("compose-compiler"))
    apply(plugin = versionCatalog.getPluginId("compose-multiplatform"))

    androidLibraryExtension?.apply {
        dependencies {
            debugImplementation(compose.uiTooling)
        }
    }

    kotlinMultiplatformExtension?.apply {

        sourceSets.commonMain.dependencies {
            implementation(versionCatalog.getLibrary("koin-compose"))
            implementation(versionCatalog.getLibrary("androidx-navigation-compose"))

            val compose = compose ?: error("Compose extension not found")

            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.materialIconsExtended)
            implementation(compose.ui)
            implementation(compose.components.uiToolingPreview)
            implementation(compose.components.resources)
        }
    } ?: error("Expected Kotlin Multiplatform plugin to be applied")
}
