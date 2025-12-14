import co.touchlab.skie.configuration.DefaultArgumentInterop
import co.touchlab.skie.configuration.EnumInterop
import co.touchlab.skie.configuration.FlowInterop
import co.touchlab.skie.configuration.FunctionInterop
import co.touchlab.skie.configuration.SealedInterop
import co.touchlab.skie.configuration.SuppressSkieWarning
import co.touchlab.skie.configuration.SuspendInterop
import cz.johnyapps.tictactoe.convention.Naming
import cz.johnyapps.tictactoe.convention.extension.iosTargets
import cz.johnyapps.tictactoe.convention.extension.multiplatformProjects
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    alias(libs.plugins.local.multiplatform.library)
    alias(libs.plugins.local.multiplatform.ui)
    alias(libs.plugins.skie)
}

val libName = "Shared"
val iosLibFolder = "XCFrameworks/lib"
val xcFramework = XCFramework(libName)

@OptIn(ExperimentalKotlinGradlePluginApi::class)
kotlin {
    iosTargets().forEach { target ->
        target.binaries.framework(libName) {
            isStatic = true
            transitiveExport = false

            linkerOpts.add("-lsqlite3")

            binaryOption("bundleId", "${libs.versions.namespace.get()}.${Naming.MULTIPLATFORM}.shared")
            xcFramework.add(this)

            multiplatformProjects.forEach { project ->
                // TODO: Limit to only needed modules
                export(project)
            }
        }
    }

    sourceSets {
        androidMain.dependencies {
            implementation(libs.koin.android)
        }

        commonMain.dependencies {
            multiplatformProjects.forEach { project ->
                api(project)
            }
        }
    }
}

skie {
    features {
        coroutinesInterop.set(true)
        group {
            SuppressSkieWarning.NameCollision(false)
            EnumInterop.Enabled(true)
            SealedInterop.Enabled(true)
            DefaultArgumentInterop.Enabled(false)
            SuspendInterop.Enabled(true)
            FlowInterop.Enabled(true)
            FunctionInterop.FileScopeConversion.Enabled(true)
        }
        group(libs.versions.namespace.get()) {
            SuppressSkieWarning.NameCollision(false)
            DefaultArgumentInterop.Enabled(true)
        }
    }
}

val buildAndCopyDebugXCFrameworkToCommonFolder by tasks.creating(Copy::class) {
    group = "build"
    dependsOn("assembleSharedDebugXCFramework")
    from(layout.buildDirectory.dir("XCFrameworks/debug"))
    into(layout.buildDirectory.dir(iosLibFolder))
}

val buildAndCopyReleaseXCFrameworkToCommonFolder by tasks.creating(Copy::class) {
    group = "build"
    dependsOn("assembleSharedReleaseXCFramework")
    from(layout.buildDirectory.dir("XCFrameworks/release"))
    into(layout.buildDirectory.dir(iosLibFolder))
}
