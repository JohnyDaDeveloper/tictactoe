package cz.johnyapps.tictactoe.multiplaform.core.common.util

import cz.johnyapps.tictactoe.multiplaform.core.common.util.model.Locale
import platform.Foundation.NSLocale

internal class IosLocaleProvider : LocaleProvider {
    override fun provide(): Locale {
        return NSLocale("cs-CZ")
    }
}
