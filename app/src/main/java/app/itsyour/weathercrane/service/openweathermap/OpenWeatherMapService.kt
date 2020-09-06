package app.itsyour.weathercrane.service.openweathermap

import app.itsyour.weathercrane.service.WeatherService
import app.itsyour.weathercrane.service.openweathermap.response.CurrentWeatherResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Implements a Retrofit specification for the Open Weather Map API.
 */
interface OpenWeatherMapService : WeatherService  {

    @GET("weather")
    override fun currentWeather(
        @Query("q") cityName: String): Single<CurrentWeatherResponse>
}