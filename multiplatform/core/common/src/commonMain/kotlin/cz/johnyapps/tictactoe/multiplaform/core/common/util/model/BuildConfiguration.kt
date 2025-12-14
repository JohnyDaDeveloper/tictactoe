package cz.johnyapps.tictactoe.multiplaform.core.common.util.model

data class BuildConfiguration(
    val versionName: String,
    val versionCode: Int,
    val platform: cz.johnyapps.tictactoe.multiplaform.core.common.util.model.Platform,
    val buildType: cz.johnyapps.tictactoe.multiplaform.core.common.util.model.BuildType,
    val systemVersion: String,
)
