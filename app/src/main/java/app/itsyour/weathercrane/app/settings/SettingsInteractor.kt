package app.itsyour.weathercrane.app.settings

import androidx.datastore.DataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SettingsInteractor
    @Inject constructor(private val dataStore: DataStore<Settings>) {

    fun readCity(): Flow<String> =
         dataStore.data.map { settings -> settings.city }

    suspend fun updateCity(city: String) {
        dataStore.updateData { currentSettings ->
            currentSettings.toBuilder()
                .setCity(city)
                .build()
        }
    }
}
