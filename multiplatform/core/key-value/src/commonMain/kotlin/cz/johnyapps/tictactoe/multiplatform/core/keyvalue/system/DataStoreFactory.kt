package cz.johnyapps.tictactoe.multiplatform.core.keyvalue.system

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import okio.Path.Companion.toPath

internal fun createDataStore(producePath: () -> String): DataStore<Preferences> =
    PreferenceDataStoreFactory.createWithPath(
        produceFile = { producePath().toPath() }
    )

@Suppress("TopLevelPropertyNaming")
internal const val dataStoreFileName = "user.preferences_pb"
