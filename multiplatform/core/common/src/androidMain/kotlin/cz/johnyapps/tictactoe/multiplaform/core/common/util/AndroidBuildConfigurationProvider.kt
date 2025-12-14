package cz.johnyapps.tictactoe.multiplaform.core.common.util

import android.os.Build
import cz.johnyapps.tictactoe.common.BuildConfig
import cz.johnyapps.tictactoe.multiplaform.core.common.util.model.Platform

internal class AndroidBuildConfigurationProvider : BuildConfigurationProvider {

    override fun provide(): cz.johnyapps.tictactoe.multiplaform.core.common.util.model.BuildConfiguration {
        return cz.johnyapps.tictactoe.multiplaform.core.common.util.model.BuildConfiguration(
            versionName = BuildConfig.versionName,
            versionCode = BuildConfig.versionCode,
            platform = Platform.Android,
            buildType = BuildConfig.buildType,
            systemVersion = Build.VERSION.RELEASE,
        )
    }
}
