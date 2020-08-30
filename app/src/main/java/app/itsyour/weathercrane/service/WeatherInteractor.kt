package app.itsyour.weathercrane.service

import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class WeatherInteractor
    @Inject constructor(private val service: WeatherService) {

    fun fetchWindSpeed(): Single<WeatherServiceResponse> = service.getWindSpeed()
}
