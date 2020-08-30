package app.itsyour.weathercrane.service

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface WeatherService  {
    @GET("weather")
    fun getWindSpeed(): Single<WeatherServiceResponse>
}