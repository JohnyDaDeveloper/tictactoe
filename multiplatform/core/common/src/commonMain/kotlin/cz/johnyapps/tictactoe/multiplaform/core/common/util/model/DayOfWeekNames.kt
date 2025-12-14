package cz.johnyapps.tictactoe.multiplaform.core.common.util.model

import kotlinx.datetime.format.DayOfWeekNames

val DayOfWeekNames.Companion.CZECH_FULL: DayOfWeekNames
    get() = DayOfWeekNames(
        listOf("Pondělí", "Úterý", "Středa", "Čtvrtek", "Pátek", "Sobota", "Neděle")
    )
