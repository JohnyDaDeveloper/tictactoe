package cz.johnyapps.tictactoe.convention.extension

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import cz.johnyapps.tictactoe.convention.Naming
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.findByType
import org.jetbrains.compose.ComposePlugin
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import java.io.FileInputStream
import java.util.Properties

internal val Project.androidAppExtension: ApplicationExtension?
    get() = extensions.findByType()

internal val Project.androidLibraryExtension: LibraryExtension?
    get() = extensions.findByType()

internal val Project.kotlinAndroidExtension: KotlinAndroidProjectExtension?
    get() = extensions.findByType()

internal val Project.kotlinMultiplatformExtension: KotlinMultiplatformExtension?
    get() = extensions.findByType()

internal val Project.detektExtension: DetektExtension?
    get() = extensions.findByType()

internal val KotlinMultiplatformExtension.compose: ComposePlugin.Dependencies?
    get() = extensions.findByType<ComposePlugin.Dependencies>()

val Project.nameNormalized: String
    get() = name.replace("-", "")

val Project.isMultiplatform: Boolean
    get() = path.contains(":${Naming.MULTIPLATFORM}:")

val Project.isAndroid: Boolean
    get() = path.contains(":${Naming.ANDROID}:")

val Project.isCore: Boolean
    get() = path.contains(":${Naming.CORE}:")

val Project.isLibrary: Boolean
    get() = path.contains(":${Naming.LIBRARY}:")

val Project.isFeature: Boolean
    get() = path.contains(":${Naming.FEATURE}:")

/**
 * All multiplatform projects excluding shared
 */
val Project.multiplatformProjects
    get() = rootProject.allprojects.filter { project ->
        val notShared = project.isCore || project.isLibrary || project.isFeature
        project.isMultiplatform && notShared
    }

val Project.keystoreProperties: Properties
    get() {
        val propertiesFile = rootProject.file("keystore.properties")
        val properties = Properties()
        properties.load(FileInputStream(propertiesFile))

        return properties
    }
