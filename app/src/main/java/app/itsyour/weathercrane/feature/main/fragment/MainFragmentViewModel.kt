package app.itsyour.weathercrane.feature.main.fragment

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.itsyour.weathercrane.app.settings.SettingsInteractor
import app.itsyour.weathercrane.feature.main.MainContract
import app.itsyour.weathercrane.service.WeatherInteractor
import io.reactivex.rxjava3.kotlin.subscribeBy
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainFragmentViewModel
    @ViewModelInject constructor(
        private val settingsInteractor: SettingsInteractor,
        private val weatherInteractor: WeatherInteractor)
            : ViewModel() {

    private val _uiStates = MutableLiveData<MainContract.UiState>()
    val uiStates: LiveData<MainContract.UiState> = _uiStates

    fun refresh() {
        viewModelScope.launch {
            settingsInteractor.readCity().collect { city ->
                weatherInteractor.fetchCurrentWeather(city).subscribeBy(onSuccess = {
                    _uiStates.postValue(MainContract.UiState.CurrentWeather(it.wind.speed))
                })
            }
        }
    }

    fun fetchCity() {
        viewModelScope.launch {
            settingsInteractor.readCity().collect { city ->
                _uiStates.postValue(MainContract.UiState.CityUpdate(city))
            }
        }
    }

    fun updateSettings(city: String) {
        viewModelScope.launch {
            settingsInteractor.updateCity(city)
        }
    }
}
