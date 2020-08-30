package app.itsyour.weathercrane.service

import com.google.gson.annotations.Expose

data class WeatherServiceResponse(
    @Expose val windSpeed: Int)