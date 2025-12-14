plugins {
    alias(libs.plugins.local.multiplatform.library)
}

android {
    defaultConfig {
        buildConfigField(
            type = "int",
            name = "versionCode",
            value = libs.versions.app.version.code.get(),
        )
        buildConfigField(
            type = "String",
            name = "versionName",
            value = "\"${libs.versions.app.version.name.get()}\"",
        )
        buildConfigField(
            type = "kotlin.time.Instant",
            name = "buildTime",
            value = "kotlin.time.Instant.Companion.fromEpochMilliseconds(${System.currentTimeMillis()}L)",
        )
    }

    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        debug {
            buildConfigField(
                type = "cz.johnyapps.tictactoe.multiplaform.core.common.util.model.BuildType",
                name = "buildType",
                value = "cz.johnyapps.tictactoe.multiplaform.core.common.util.model.BuildType.Debug",
            )
        }

        release {
            buildConfigField(
                type = "cz.johnyapps.tictactoe.multiplaform.core.common.util.model.BuildType",
                name = "buildType",
                value = "cz.johnyapps.tictactoe.multiplaform.core.common.util.model.BuildType.Release",
            )
        }

        maybeCreate("closedRelease").apply {
            buildConfigField(
                type = "cz.johnyapps.tictactoe.multiplaform.core.common.util.model.BuildType",
                name = "buildType",
                value = "cz.johnyapps.tictactoe.multiplaform.core.common.util.model.BuildType.ClosedRelease",
            )
        }
    }
}

kotlin {
    sourceSets {
        commonMain.dependencies {

            implementation(libs.androidx.viewmodel.compose)
        }
    }
}
