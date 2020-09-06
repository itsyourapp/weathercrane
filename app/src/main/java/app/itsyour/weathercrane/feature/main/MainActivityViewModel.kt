package app.itsyour.weathercrane.feature.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class MainActivityViewModel
    @ViewModelInject constructor() : ViewModel() {

    private val _actions = MutableLiveData<MainContract.Action>()
    val actions: LiveData<MainContract.Action> = _actions

    private val _uiState = MutableLiveData<MainContract.UiState>()
    val uiState: LiveData<MainContract.UiState> = _uiState

    fun action(action: MainContract.Action) {
        when (action) {
            is MainContract.Action.Refresh -> refresh()
            else -> Unit
        }
    }

    private fun refresh() {
        Timber.tag("itsyourapp").d("REFRESHED!")
        _actions.postValue(MainContract.Action.Refresh)
    }

    fun refreshed() {
        _uiState.postValue(MainContract.UiState.Refreshed)
    }
}
