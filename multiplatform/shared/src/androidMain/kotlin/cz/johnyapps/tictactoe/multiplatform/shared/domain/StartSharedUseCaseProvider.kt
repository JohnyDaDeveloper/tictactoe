package cz.johnyapps.tictactoe.multiplatform.shared.domain

object StartSharedUseCaseProvider {

    fun provide(): StartSharedUseCase {
        return AndroidStartSharedUseCase()
    }
}
