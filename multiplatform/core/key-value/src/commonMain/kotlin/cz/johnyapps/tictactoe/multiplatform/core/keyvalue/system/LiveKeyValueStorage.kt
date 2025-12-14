package cz.johnyapps.tictactoe.multiplatform.core.keyvalue.system

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import cz.johnyapps.tictactoe.multiplatform.core.keyvalue.data.KeyValueStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

internal class LiveKeyValueStorage(
    private val dataStore: DataStore<Preferences>,
) : KeyValueStorage {

    override suspend fun getString(key: String): String? {
        val storeKey = stringPreferencesKey(key)
        return dataStore.data.first()[storeKey]
    }

    override suspend fun setString(key: String, value: String?) {
        val storeKey = stringPreferencesKey(key)
        dataStore.updateData { preferences ->
            val mutablePreferences = preferences.toMutablePreferences()

            if (value == null) {
                mutablePreferences.remove(storeKey)
            } else {
                mutablePreferences[storeKey] = value
            }

            mutablePreferences
        }
    }

    override suspend fun getBoolean(key: String): Boolean? {
        val storeKey = booleanPreferencesKey(key)
        return dataStore.data.first()[storeKey]
    }

    override suspend fun setBoolean(key: String, value: Boolean?) {
        val storeKey = booleanPreferencesKey(key)
        dataStore.updateData { preferences ->
            val mutablePreferences = preferences.toMutablePreferences()

            if (value == null) {
                mutablePreferences.remove(storeKey)
            } else {
                mutablePreferences[storeKey] = value
            }

            mutablePreferences
        }
    }

    override fun getBooleanFlow(key: String): Flow<Boolean?> {
        val storeKey = booleanPreferencesKey(key)
        return dataStore.data.map {
            it[storeKey]
        }
    }
}
