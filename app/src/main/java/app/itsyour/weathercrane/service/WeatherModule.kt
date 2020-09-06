package app.itsyour.weathercrane.service

import app.itsyour.weathercrane.service.openweathermap.OpenWeatherMapService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
abstract class WeatherModule {

    @Binds
    abstract fun bindWeatherService(openWeatherMapService: OpenWeatherMapService): WeatherService
}