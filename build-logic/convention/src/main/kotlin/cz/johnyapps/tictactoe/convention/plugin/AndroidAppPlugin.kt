package cz.johnyapps.tictactoe.convention.plugin

import cz.johnyapps.tictactoe.convention.PluginFactory
import cz.johnyapps.tictactoe.convention.configuration.configureAndroid
import cz.johnyapps.tictactoe.convention.configuration.configureDetekt
import cz.johnyapps.tictactoe.convention.configuration.configureKotlin
import cz.johnyapps.tictactoe.convention.extension.androidAppExtension
import cz.johnyapps.tictactoe.convention.extension.getLibrary
import cz.johnyapps.tictactoe.convention.extension.getPluginId
import cz.johnyapps.tictactoe.convention.extension.getVersion
import cz.johnyapps.tictactoe.convention.extension.implementation
import cz.johnyapps.tictactoe.convention.extension.namespace
import cz.johnyapps.tictactoe.convention.extension.versionCatalog
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class AndroidAppPlugin : Plugin<Project> by PluginFactory build {
    apply(plugin = versionCatalog.getPluginId("android-application"))
    apply(plugin = versionCatalog.getPluginId("kotlin-android"))
    apply(plugin = versionCatalog.getPluginId("detekt"))

    configureKotlin()
    configureAndroid {
        namespace = versionCatalog.namespace
    }
    configureDetekt()

    androidAppExtension?.apply {
        defaultConfig {
            versionCode = versionCatalog.getVersion("app-version-code").toInt()
            versionName = versionCatalog.getVersion("app-version-name")
        }

        packaging {
            resources {
                excludes += listOf(
                    "/META-INF/{AL2.0,LGPL2.1}",
                    "/META-INF/INDEX.LIST",
                    "/META-INF/groovy-release-info.properties"
                )
            }
        }

        dependencies {
            implementation(versionCatalog.getLibrary("koin-core"))
            implementation(versionCatalog.getLibrary("koin-android"))
            implementation(versionCatalog.getLibrary("koin-compose"))
        }
    } ?: error("Android app extension not found, was the app plugin applied?")
}
