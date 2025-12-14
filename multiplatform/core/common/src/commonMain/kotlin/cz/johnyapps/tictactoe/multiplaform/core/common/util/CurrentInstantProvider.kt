package cz.johnyapps.tictactoe.multiplaform.core.common.util

import kotlin.time.Clock
import kotlin.time.Instant

interface CurrentInstantProvider {

    fun provide(): Instant
}

internal class LiveCurrentInstantProvider : CurrentInstantProvider {

    override fun provide(): Instant {
        return Clock.System.now()
    }
}
