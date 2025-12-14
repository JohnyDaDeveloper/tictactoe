package cz.johnyapps.tictactoe.convention.extension

import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.findByType
import org.jetbrains.compose.ComposePlugin

internal fun DependencyHandler.implementation(dependencyNotation: Any): Dependency? =
    add("implementation", dependencyNotation)

internal fun DependencyHandler.api(dependencyNotation: Any): Dependency? =
    add("api", dependencyNotation)

internal fun DependencyHandler.debugImplementation(dependencyNotation: Any): Dependency? =
    add("debugImplementation", dependencyNotation)

internal fun DependencyHandler.testImplementation(dependencyNotation: Any): Dependency? =
    add("testImplementation", dependencyNotation)

internal fun DependencyHandler.detektPlugins(dependencyNotation: Any): Dependency? =
    add("detektPlugins", dependencyNotation)

internal val DependencyHandler.compose: ComposePlugin.Dependencies
    get() = this.extensions.findByType<ComposePlugin.Dependencies>() ?: error("Not found")
