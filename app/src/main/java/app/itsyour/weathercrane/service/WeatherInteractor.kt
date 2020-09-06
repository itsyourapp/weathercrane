package app.itsyour.weathercrane.service

import app.itsyour.weathercrane.service.openweathermap.response.CurrentWeatherResponse
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class WeatherInteractor
    @Inject constructor(private val service: WeatherService) {

    fun fetchCurrentWeather(cityName: String): Single<CurrentWeatherResponse>
            = service.currentWeather(cityName)
}
