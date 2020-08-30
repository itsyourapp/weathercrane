package app.itsyour.weathercrane

import app.itsyour.weathercrane.service.WeatherService
import app.itsyour.weathercrane.service.WeatherServiceInMemory
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
abstract class AppModule {
    @Binds
    abstract fun provideWeatherServiceInMemory(inMemory: WeatherServiceInMemory): WeatherService
}
