package cz.johnyapps.tictactoe.multiplatform.shared.domain

import cz.johnyapps.tictactoe.multiplatform.core.logger.util.LogWriter

interface DependencyProvider {

    fun provideLogWriters(): List<LogWriter>
}
