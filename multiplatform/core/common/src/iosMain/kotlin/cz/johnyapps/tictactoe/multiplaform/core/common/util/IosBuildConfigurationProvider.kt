package cz.johnyapps.tictactoe.multiplaform.core.common.util

import cz.johnyapps.tictactoe.multiplaform.core.common.util.model.BuildType
import platform.Foundation.NSBundle
import platform.UIKit.UIDevice
import kotlin.experimental.ExperimentalNativeApi
import cz.johnyapps.tictactoe.multiplaform.core.common.util.model.Platform as KmpPlatform

internal class IosBuildConfigurationProvider : BuildConfigurationProvider {

    private val mainBundle = NSBundle.mainBundle

    @OptIn(ExperimentalNativeApi::class)
    override fun provide(): cz.johnyapps.tictactoe.multiplaform.core.common.util.model.BuildConfiguration {
        return cz.johnyapps.tictactoe.multiplaform.core.common.util.model.BuildConfiguration(
            versionName = getBuildVersionName(),
            versionCode = getBuildCode(),
            platform = KmpPlatform.Ios,
            buildType = if (Platform.isDebugBinary) {
                BuildType.Debug
            } else {
                BuildType.Release
            },
            systemVersion = UIDevice.currentDevice.systemVersion,
        )
    }

    private fun getBuildCode(): Int {
        val map = mainBundle.infoDictionary
        val code = map?.get(KEY_BUNDLE_VERSION) as? String
        if (code.isNullOrEmpty()) {
            error("$KEY_BUNDLE_VERSION not defined in main bundle")
        }
        return code.toIntOrNull() ?: error("Could not format $code to int")
    }

    private fun getBuildVersionName(): String {
        val map = mainBundle.infoDictionary
        val code = map?.get(KEY_BUNDLE_VERSION_NAME) as? String
        if (code.isNullOrEmpty()) {
            error("$KEY_BUNDLE_VERSION_NAME not defined in main bundle")
        }
        return code
    }

    companion object {
        private const val KEY_BUNDLE_VERSION = "CFBundleVersion"
        private const val KEY_BUNDLE_VERSION_NAME = "CFBundleShortVersionString"
    }
}
