package cz.johnyapps.mutliplatform.library.game.domain

import cz.johnyapps.tictactoe.multiplaform.core.common.util.model.DataResult
import cz.johnyapps.mutliplatform.library.game.data.GameSource
import cz.johnyapps.mutliplatform.library.game.domain.model.MakeMove
import cz.johnyapps.mutliplatform.library.game.domain.model.PlayerId

private val playerId = PlayerId("fake_player")

interface MakeMoveUseCase {
    
    suspend operator fun invoke(makeMove: MakeMove): DataResult<Unit>
}

internal class LiveMakeMoveUseCase(
    private val gameSource: GameSource,
    private val gameCaller: GameCaller,
) : MakeMoveUseCase {

    override suspend fun invoke(makeMove: MakeMove): DataResult<Unit> {
        return gameCaller.callGame { 
            gameSource.makeMove(playerId, makeMove)
        }
    }
}
