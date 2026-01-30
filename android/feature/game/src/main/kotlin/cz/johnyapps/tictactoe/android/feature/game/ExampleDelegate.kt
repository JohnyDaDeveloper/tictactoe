package cz.johnyapps.tictactoe.android.feature.game

import kotlinx.coroutines.flow.MutableStateFlow

interface ExampleDelegate {

    fun handle(state: MutableStateFlow<ViewModelState>)
}
