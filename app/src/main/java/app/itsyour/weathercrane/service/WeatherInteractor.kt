package app.itsyour.weathercrane.service

import app.itsyour.weathercrane.service.response.CurrentWeatherResponse
import io.reactivex.rxjava3.core.Single
import okhttp3.OkHttpClient
import javax.inject.Inject

class WeatherInteractor
    @Inject constructor(private val service: WeatherService) {

    fun fetchWindSpeed(): Single<CurrentWeatherResponse>
            = service.getWindSpeed("ann arbor")
}
