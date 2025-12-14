package cz.johnyapps.tictactoe.multiplaform.core.common.util

import cz.johnyapps.tictactoe.multiplaform.core.common.util.model.Locale

interface LocaleProvider {

    fun provide(): Locale
}
