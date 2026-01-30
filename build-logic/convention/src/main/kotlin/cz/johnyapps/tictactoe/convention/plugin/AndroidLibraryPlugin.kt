package cz.johnyapps.tictactoe.convention.plugin

import cz.johnyapps.tictactoe.convention.PluginFactory
import cz.johnyapps.tictactoe.convention.configuration.configureAndroid
import cz.johnyapps.tictactoe.convention.configuration.configureDetekt
import cz.johnyapps.tictactoe.convention.configuration.configureKotlin
import cz.johnyapps.tictactoe.convention.extension.getLibrary
import cz.johnyapps.tictactoe.convention.extension.getPluginId
import cz.johnyapps.tictactoe.convention.extension.implementation
import cz.johnyapps.tictactoe.convention.extension.isCore
import cz.johnyapps.tictactoe.convention.extension.nameNormalized
import cz.johnyapps.tictactoe.convention.extension.namespace
import cz.johnyapps.tictactoe.convention.extension.versionCatalog
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class AndroidLibraryPlugin : Plugin<Project> by PluginFactory build {
    apply(plugin = versionCatalog.getPluginId("android-library"))
    apply(plugin = versionCatalog.getPluginId("kotlin-android"))
    apply(plugin = versionCatalog.getPluginId("detekt"))

    configureKotlin()
    configureAndroid {
        namespace = "${versionCatalog.namespace}.${project.nameNormalized}"
    }
    configureDetekt()

    dependencies {
        implementation(versionCatalog.getLibrary("koin-core"))
        implementation(versionCatalog.getLibrary("kotlinx-coroutines-core"))
        implementation(versionCatalog.getLibrary("kotlinx-datetime"))

        if (!project.isCore) {
            implementation(project(":multiplatform:core:common"))
            implementation(project(":multiplatform:core:logger"))
        }
    }
}