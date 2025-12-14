package cz.johnyapps.mutliplatform.library.game.domain.model

data class MakeMove(
    val gameId: GameId,
    val field: Field,
) {

    data class Field(
        val x: Int,
        val y: Int,
    )
}
