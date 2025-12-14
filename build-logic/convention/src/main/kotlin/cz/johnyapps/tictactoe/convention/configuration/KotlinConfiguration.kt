package cz.johnyapps.tictactoe.convention.configuration

import cz.johnyapps.tictactoe.convention.extension.javaVersion
import cz.johnyapps.tictactoe.convention.extension.kotlinAndroidExtension
import cz.johnyapps.tictactoe.convention.extension.kotlinMultiplatformExtension
import cz.johnyapps.tictactoe.convention.extension.versionCatalog
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

internal fun Project.configureKotlin() {
    val kotlinExtension = kotlinAndroidExtension ?: kotlinMultiplatformExtension

    kotlinMultiplatformExtension?.apply {
        compilerOptions {
            // Opt-in for expect/actual classes
            freeCompilerArgs.add("-Xexpect-actual-classes")
            //See https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-consistent-copy-visibility/
            freeCompilerArgs.add("-Xconsistent-data-class-copy-visibility")
            freeCompilerArgs.add("-opt-in=kotlin.time.ExperimentalTime")
        }

        androidTarget {
            compilerOptions {
                val target = JvmTarget.fromTarget(versionCatalog.javaVersion.toString())
                jvmTarget.set(target)
            }
        }
    }

    kotlinAndroidExtension?.apply {
        compilerOptions {
            //See https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-consistent-copy-visibility/
            freeCompilerArgs.add("-Xconsistent-data-class-copy-visibility")
        }
    }

    kotlinExtension?.apply {
        jvmToolchain(versionCatalog.javaVersion)
    } ?: error("No Kotlin extension found on ${project.name}, is it applied on module?")
}
