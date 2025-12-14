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

fun <T> DataResult<T>.getOrThrow(): T {
    return when (this) {
        is DataResult.Success -> {
            data
        }

        is DataResult.Error -> {
            throw exception
        }
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

inline fun <T1, T2> DataResult<T1>.combine(
    other: (T1) -> DataResult<T2>,
): DataResult<Pair<T1, T2>> = flatMap { data1 ->
    other(data1).map { data2 ->
        data1 to data2
    }
}

inline fun <T1, T2, T3> DataResult<Pair<T1, T2>>.combine(
    other: (T1, T2) -> DataResult<T3>
): DataResult<Triple<T1, T2, T3>> = flatMap { (data1, data2) ->
    other(data1, data2).map { data3 ->
        Triple(
            data1,
            data2,
            data3,
        )
    }
}
