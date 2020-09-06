package app.itsyour.weathercrane.feature.main

import app.itsyour.weathercrane.service.openweathermap.response.CurrentWeatherResponse

interface MainContract {

    sealed class Action {
        object Refresh : Action()
    }

    sealed class UiState {
        class CityUpdate(val city: String) : UiState()
        class CurrentWeather(val windSpeed: Double) : UiState()
        object Refreshed : UiState()
    }
}
