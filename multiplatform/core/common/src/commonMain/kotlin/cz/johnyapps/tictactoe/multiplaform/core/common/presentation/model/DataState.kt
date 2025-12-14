package cz.johnyapps.tictactoe.multiplaform.core.common.presentation.model

sealed class DataState<out T> {
    data object Loading : DataState<Nothing>()

    data class Ready<T> internal constructor(
        val data: T
    ) : DataState<T>()

    data object Error : DataState<Nothing>()

    companion object {
        fun <T> loading(): DataState<T> = Loading
        fun <T> ready(data: T): DataState<T> = Ready(data)
        fun <T> error(): DataState<T> = Error
    }
}

inline fun <T, R> DataState<T>.map(block: (T) -> R): DataState<R> {
    return when (this) {
        is DataState.Loading -> DataState.Loading

        is DataState.Ready -> {
            DataState.ready(block(data))
        }

        is DataState.Error -> DataState.Error
    }
}
