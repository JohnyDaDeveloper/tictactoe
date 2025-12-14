package cz.johnyapps.tictactoe.multiplaform.core.common.presentation.model

sealed class LoadableState<out T> {
    data object Loading : LoadableState<Nothing>()

    data class Ready<T> internal constructor(
        val data: T,
    ) : LoadableState<T>()

    companion object {
        fun <T> loading(): LoadableState<T> = Loading
        fun <T> ready(data: T): LoadableState<T> = Ready(data)
    }
}

fun <T1, T2, R> LoadableState<T1>.combine(
    otherState: LoadableState<T2>,
    transform: (T1, T2) -> R
): LoadableState<R> {
    return when (this) {
        is LoadableState.Loading -> LoadableState.loading()
        is LoadableState.Ready -> {
            when (otherState) {
                is LoadableState.Loading -> LoadableState.loading()
                is LoadableState.Ready -> LoadableState.ready(
                    transform(data, otherState.data)
                )
            }
        }
    }
}

inline fun <T1, T2> LoadableState<T1>.map(
    transform: (T1) -> T2
): LoadableState<T2> {
    return when (this) {
        is LoadableState.Loading -> this
        is LoadableState.Ready -> {
            val newData = transform(data)
            LoadableState.ready(newData)
        }
    }
}
