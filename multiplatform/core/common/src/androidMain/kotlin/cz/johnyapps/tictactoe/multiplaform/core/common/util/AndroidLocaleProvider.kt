package cz.johnyapps.tictactoe.multiplaform.core.common.util

import cz.johnyapps.tictactoe.multiplaform.core.common.util.model.Locale

internal class AndroidLocaleProvider : LocaleProvider {
    override fun provide(): Locale {
        return java.util.Locale.forLanguageTag("cs-CZ")
    }
}
