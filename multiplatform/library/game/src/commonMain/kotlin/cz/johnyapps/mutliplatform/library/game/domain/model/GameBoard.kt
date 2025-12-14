package cz.johnyapps.mutliplatform.library.game.domain.model

data class GameBoard(
    val moves: Set<Move>,
    val size: Size,
) {

    data class Move(
        val x: Int,
        val y: Int,
        val playerId: PlayerId,
    )

    data class Size(
        val x: Int,
        val y: Int,
    )
}
