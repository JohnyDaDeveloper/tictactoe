package cz.johnyapps.tictactoe.convention.plugin

import cz.johnyapps.tictactoe.convention.PluginFactory
import cz.johnyapps.tictactoe.convention.extension.getLibrary
import cz.johnyapps.tictactoe.convention.extension.getPluginId
import cz.johnyapps.tictactoe.convention.extension.kotlinMultiplatformExtension
import cz.johnyapps.tictactoe.convention.extension.versionCatalog
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply

class NetworkingPlugin : Plugin<Project> by PluginFactory build {
    apply(plugin = versionCatalog.getPluginId("kotlinx-serialization"))

    kotlinMultiplatformExtension?.apply {
        sourceSets.commonMain.dependencies {
            implementation(versionCatalog.getLibrary("ktor-client-core"))
            implementation(versionCatalog.getLibrary("kotlinx-serialization-core"))

            implementation(project(":multiplatform:library:networking"))
        }
    } ?: error("Expected Kotlin Multiplatform plugin to be applied")
}