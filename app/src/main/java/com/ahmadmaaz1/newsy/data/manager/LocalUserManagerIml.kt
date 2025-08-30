package com.ahmadmaaz1.newsy.data.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.ahmadmaaz1.newsy.domain.manager.LocalUserManager
import com.ahmadmaaz1.newsy.util.Constant
import com.ahmadmaaz1.newsy.util.Constant.USER_SETTINGS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalUserManagerIml(private val context: Context) : LocalUserManager {
    override suspend fun saveAppEntry() {
        context.dataStore.edit { dataStore ->
            dataStore[PreferencesKey.APP_ENTRY] = true
        }
    }

    override  fun readAppEntry(): Flow<Boolean> {
       return context.dataStore.data.map { preferences ->
           preferences[PreferencesKey.APP_ENTRY] ?:false
        }
    }
}

// first create the dataStore setUp
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_SETTINGS)

private object PreferencesKey {
    val APP_ENTRY = booleanPreferencesKey(Constant.APP_ENTRY)
}