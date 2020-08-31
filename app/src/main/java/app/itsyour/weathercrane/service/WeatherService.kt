package app.itsyour.weathercrane.service

import app.itsyour.weathercrane.service.response.CurrentWeatherResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService  {
    @GET("weather")
    fun getWindSpeed(
        @Query("q") cityName: String): Single<CurrentWeatherResponse>
}