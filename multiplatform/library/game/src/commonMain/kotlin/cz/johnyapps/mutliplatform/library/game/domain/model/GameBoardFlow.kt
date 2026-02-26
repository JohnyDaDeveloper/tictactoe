package cz.johnyapps.mutliplatform.library.game.domain.model

import kotlinx.coroutines.flow.Flow

data class GameBoardFlow(
    val flow: Flow<GameBoard>,
)