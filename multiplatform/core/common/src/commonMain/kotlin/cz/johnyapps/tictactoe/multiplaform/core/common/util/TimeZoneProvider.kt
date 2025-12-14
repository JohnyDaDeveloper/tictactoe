package cz.johnyapps.tictactoe.multiplaform.core.common.util

import kotlinx.datetime.TimeZone

interface TimeZoneProvider {

    fun provide(): TimeZone
}

internal class LiveTimeZoneProvider : TimeZoneProvider {

    override fun provide(): TimeZone {
        return TimeZone.of("Europe/Prague")
    }
}
