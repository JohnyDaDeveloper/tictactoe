package cz.johnyapps.tictactoe.multiplatform.core.keyvalue.data

import kotlinx.coroutines.flow.Flow

interface KeyValueStorage {

    suspend fun getString(key: String): String?

    suspend fun setString(key: String, value: String?)

    suspend fun getBoolean(key: String): Boolean?

    suspend fun setBoolean(key: String, value: Boolean?)

    fun getBooleanFlow(key: String): Flow<Boolean?>
}
