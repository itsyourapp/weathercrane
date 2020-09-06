package app.itsyour.weathercrane.app.di

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.createDataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore
import androidx.preference.PreferenceManager
import app.itsyour.weathercrane.app.settings.Settings
import app.itsyour.weathercrane.app.settings.SettingsSerializer
import com.f2prateek.rx.preferences2.RxSharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@InstallIn(ApplicationComponent::class)
@Module
class AppModule {

    /**
     * Provides the simple key value pair datastore.
     */
    @Provides
    fun dataStore(@ApplicationContext context: Context): DataStore<Preferences> = context.createDataStore(name = "user")

    /**
     * Provides the type safe protobuf-backed datastore.
     */
    @Provides
    fun protoDataStore(@ApplicationContext context: Context): DataStore<Settings> = context.createDataStore(
            fileName = "weathercrane.pb",
            serializer = SettingsSerializer
    )

    /**
     * Provides an Rx wrapped SharedPreferences
     */
    @Provides
    fun rxSharedPreferences(@ApplicationContext context: Context): RxSharedPreferences
            = PreferenceManager.getDefaultSharedPreferences(context)
                 .let { RxSharedPreferences.create(it) }
}
