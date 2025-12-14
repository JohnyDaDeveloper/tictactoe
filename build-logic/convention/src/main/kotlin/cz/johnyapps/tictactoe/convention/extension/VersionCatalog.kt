package cz.johnyapps.tictactoe.convention.extension

import org.gradle.api.Project
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.findByType

/**
 * There is no built-in support for typesafe version catalogs in custom plugins
 * [Issue](https://github.com/gradle/gradle/issues/15383)
 */
internal val Project.versionCatalog: VersionCatalog
    get() = extensions.findByType<VersionCatalogsExtension>()
        ?.named("libs")
        ?: error("No ${VersionCatalogsExtension::class.simpleName} named 'libs' found.")

internal fun VersionCatalog.getPluginId(alias: String): String {
    val optional = findPlugin(alias)

    if (optional.isPresent) {
        return optional.get().get().pluginId
    } else {
        error("No plugin found by alias $alias in version catalog.")
    }
}

internal fun VersionCatalog.getVersion(alias: String): String {
    val optional = findVersion(alias)

    if (optional.isPresent) {
        return optional.get().displayName
    } else {
        error("No version found by alias $alias in version catalog.")
    }
}

internal fun VersionCatalog.getLibrary(alias: String): Provider<MinimalExternalModuleDependency> {
    val optional = findLibrary(alias)

    if (optional.isPresent) {
        return optional.get()
    } else {
        error("No library found by alias $alias in version catalog.")
    }
}

internal val VersionCatalog.namespace: String
    get() = getVersion("namespace")

internal val VersionCatalog.javaVersion: Int
    get() = getVersion("java").toInt()

internal val VersionCatalog.androidMinSdk: Int
    get() = getVersion("android-minSdk").toInt()

internal val VersionCatalog.androidCompileSdk: Int
    get() = getVersion("android-compileSdk").toInt()
