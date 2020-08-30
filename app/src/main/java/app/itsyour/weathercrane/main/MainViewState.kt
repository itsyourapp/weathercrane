package app.itsyour.weathercrane.main

sealed class MainViewState
class WindSpeedUpdated(val windSpeed: Int) : MainViewState()
