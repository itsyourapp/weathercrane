package app.itsyour.weathercrane.service

import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class WeatherServiceInMemory
    @Inject constructor() : WeatherService {

    override fun getWindSpeed(): Single<WeatherServiceResponse> =
        Single.just(WeatherServiceResponse(32))
}