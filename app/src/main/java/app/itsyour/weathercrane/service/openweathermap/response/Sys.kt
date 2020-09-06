package app.itsyour.weathercrane.service.openweathermap.response

data class Sys(
    val country: String,
    val id: Int,
    val sunrise: Int,
    val sunset: Int,
    val type: Int
)