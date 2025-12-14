package cz.johnyapps.tictactoe.convention.configuration

import com.android.build.api.dsl.CommonExtension
import cz.johnyapps.tictactoe.convention.extension.androidAppExtension
import cz.johnyapps.tictactoe.convention.extension.androidCompileSdk
import cz.johnyapps.tictactoe.convention.extension.androidLibraryExtension
import cz.johnyapps.tictactoe.convention.extension.androidMinSdk
import cz.johnyapps.tictactoe.convention.extension.getVersion
import cz.johnyapps.tictactoe.convention.extension.versionCatalog
import org.gradle.api.Project

internal fun Project.configureAndroid(
    specificConfiguration: CommonExtension<*, *, *, *, *, *>.() -> Unit = {}
) {
    val moduleExtension = androidAppExtension ?: androidLibraryExtension
    ?: error("No App or Library extension found on ${project.name}, is any of them applied?")

    androidAppExtension?.apply {
        defaultConfig {
            targetSdk = versionCatalog.getVersion("android-compileSdk").toInt()
        }

        buildTypes { 
            release {
                isMinifyEnabled = true
            }

            debug {
                isMinifyEnabled = false
            }
        }
    }

    with(moduleExtension) {
        specificConfiguration()

        compileSdk = versionCatalog.androidCompileSdk

        defaultConfig {
            minSdk = versionCatalog.androidMinSdk
        }
    }
}
