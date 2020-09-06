package app.itsyour.weathercrane.service.openweathermap.response

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)