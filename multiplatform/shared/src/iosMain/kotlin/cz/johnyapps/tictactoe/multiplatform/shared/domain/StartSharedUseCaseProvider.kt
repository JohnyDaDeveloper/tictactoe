package cz.johnyapps.tictactoe.multiplatform.shared.domain

@Suppress("unused") // Used in iOS
object StartSharedUseCaseProvider {

    fun provide(): StartSharedUseCase {
        return IosStartSharedUseCase()
    }
}
