package cz.johnyapps.tictactoe.convention.extension

import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

fun KotlinMultiplatformExtension.iosTargets() = listOf(
    iosArm64(),
    iosSimulatorArm64()
)
