package cz.johnyapps.tictactoe.convention.plugin

import cz.johnyapps.tictactoe.convention.PluginFactory
import cz.johnyapps.tictactoe.convention.configuration.configureAndroid
import cz.johnyapps.tictactoe.convention.configuration.configureDetekt
import cz.johnyapps.tictactoe.convention.configuration.configureKotlin
import cz.johnyapps.tictactoe.convention.extension.getLibrary
import cz.johnyapps.tictactoe.convention.extension.getPluginId
import cz.johnyapps.tictactoe.convention.extension.iosTargets
import cz.johnyapps.tictactoe.convention.extension.isCore
import cz.johnyapps.tictactoe.convention.extension.kotlinMultiplatformExtension
import cz.johnyapps.tictactoe.convention.extension.nameNormalized
import cz.johnyapps.tictactoe.convention.extension.namespace
import cz.johnyapps.tictactoe.convention.extension.versionCatalog
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply

class MultiplatformLibraryPlugin : Plugin<Project> by PluginFactory build {
    apply(plugin = versionCatalog.getPluginId("android-library"))
    apply(plugin = versionCatalog.getPluginId("kotlin-multiplatform"))
    apply(plugin = versionCatalog.getPluginId("detekt"))

    configureKotlin()
    configureAndroid {
        namespace = "${versionCatalog.namespace}.${project.nameNormalized}"
    }
    configureDetekt()

    kotlinMultiplatformExtension?.apply {
        androidTarget()
        iosTargets()

        sourceSets.commonMain.dependencies {
            implementation(versionCatalog.getLibrary("koin-core"))
            implementation(versionCatalog.getLibrary("kotlinx-coroutines-core"))
            implementation(versionCatalog.getLibrary("kotlinx-datetime"))

            if (!project.isCore) {
                implementation(project(":multiplatform:core:common"))
                implementation(project(":multiplatform:core:logger"))
                implementation(project(":multiplatform:core:money"))
            }
        }

        sourceSets.androidUnitTest.dependencies {
            implementation(versionCatalog.getLibrary("kotlin-test"))
            implementation(versionCatalog.getLibrary("mockk"))
            implementation(versionCatalog.getLibrary("kotest-assertions"))
        }
    } ?: error("Expected Kotlin Multiplatform plugin to be applied")
}