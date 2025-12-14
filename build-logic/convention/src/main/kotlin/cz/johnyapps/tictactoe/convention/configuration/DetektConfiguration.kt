package cz.johnyapps.tictactoe.convention.configuration

import cz.johnyapps.tictactoe.convention.extension.detektExtension
import cz.johnyapps.tictactoe.convention.extension.detektPlugins
import cz.johnyapps.tictactoe.convention.extension.getLibrary
import cz.johnyapps.tictactoe.convention.extension.versionCatalog
import org.gradle.api.Project

internal fun Project.configureDetekt() {
    detektExtension?.apply {
        buildUponDefaultConfig = true
        autoCorrect = true

        config.setFrom("${rootProject.rootDir}/detekt-config.yml")
        source.setFrom("$projectDir/src")
        baseline = file("$projectDir/detekt-baseline.xml")
    } ?: error("Detekt extension not found. Is the plugin applied?")

    dependencies.apply {
        detektPlugins(versionCatalog.getLibrary("detekt-compose"))
        detektPlugins(versionCatalog.getLibrary("detekt-formatting"))
    }
}
