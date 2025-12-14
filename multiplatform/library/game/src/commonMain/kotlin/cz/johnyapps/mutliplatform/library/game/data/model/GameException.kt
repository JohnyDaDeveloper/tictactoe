package cz.johnyapps.mutliplatform.library.game.data.model

import cz.johnyapps.mutliplatform.library.game.domain.model.GameId

open class GameException(
    message: String,
) : RuntimeException(message)

class GameNotFoundException(
    gameId: GameId
) : GameException(
    "Game with id '${gameId.gameId}' not found"
)
