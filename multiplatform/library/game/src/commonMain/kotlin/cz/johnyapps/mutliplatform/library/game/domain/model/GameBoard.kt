package cz.johnyapps.mutliplatform.library.game.domain.model

data class GameBoard(
    val moves: Set<Move>,
    val size: Size,
) {

    data class Move(
        val column: Int,
        val row: Int,
        val playerId: PlayerId,
    )

    data class Size(
        val columns: Int,
        val rows: Int,
    )
}
