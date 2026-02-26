package cz.johnyapps.tictactoe.multiplaform.core.common.util.model

sealed class DataResult<out T> {

    data class Success<T> internal constructor(
        val data: T,
    ) : DataResult<T>()

    data class Error internal constructor(
        val exception: Exception,
    ) : DataResult<Nothing>()

    companion object {
        fun <T> success(data: T): DataResult<T> = Success(data)
        fun <T> error(exception: Exception): DataResult<T> = Error(exception)
    }
}

inline fun <T, R> DataResult<T>.map(block: (T) -> R): DataResult<R> {
    return when (this) {
        is DataResult.Success -> {
            DataResult.success(block(data))
        }

        is DataResult.Error -> {
            this
        }
    }
}

inline fun <T, R> DataResult<T>.flatMap(block: (T) -> DataResult<R>): DataResult<R> {
    return when (this) {
        is DataResult.Success -> {
            block(data)
        }

        is DataResult.Error -> {
            this
        }
    }
}
