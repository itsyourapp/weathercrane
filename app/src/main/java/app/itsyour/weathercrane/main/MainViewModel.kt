package app.itsyour.weathercrane.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.itsyour.weathercrane.service.WeatherInteractor
import io.reactivex.rxjava3.kotlin.subscribeBy

class MainViewModel
    @ViewModelInject constructor(
        weatherInteractor: WeatherInteractor
    ) : ViewModel() {

    private val _windSpeed = MutableLiveData<MainViewState>()
    val windSpeed: LiveData<MainViewState>
        get() = _windSpeed

    init {
        weatherInteractor.fetchWindSpeed().subscribeBy(onSuccess = {
            _windSpeed.postValue(WindSpeedUpdated(it.wind.speed))
        })
    }
}