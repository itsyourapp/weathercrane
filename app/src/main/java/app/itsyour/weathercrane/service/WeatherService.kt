package app.itsyour.weathercrane.service

import app.itsyour.weathercrane.service.openweathermap.response.CurrentWeatherResponse
import io.reactivex.rxjava3.core.Single

/**
 * Aggregate service collection providing weather related data.
 */
interface WeatherService {

    fun currentWeather(cityName: String): Single<CurrentWeatherResponse>
}