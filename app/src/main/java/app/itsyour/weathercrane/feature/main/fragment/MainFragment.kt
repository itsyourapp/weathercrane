package app.itsyour.weathercrane.feature.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import app.itsyour.weathercrane.R
import app.itsyour.weathercrane.feature.main.MainContract
import app.itsyour.weathercrane.feature.main.MainActivityViewModel
import com.jakewharton.rxbinding4.view.clicks
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_main.*

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel: MainFragmentViewModel by viewModels()
    private val activityViewModel: MainActivityViewModel by activityViewModels()

    private val subscriptions = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
            = inflater.inflate(R.layout.fragment_main, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.fetchCity() // TODO: Verify this pattern

        subscriptions += city_update_button.clicks().subscribe {
            viewModel.updateSettings(city_update_edit_text.text.toString())
        }

        activityViewModel.actions.observe(viewLifecycleOwner, Observer<MainContract.Action> { action ->
            when (action) {
                is MainContract.Action.Refresh -> viewModel.refresh()
                else -> Unit
            }
        })

        viewModel.uiStates.observe(viewLifecycleOwner, Observer<MainContract.UiState> { state ->
            when (state) {
                is MainContract.UiState.CurrentWeather -> updateCurrentWeather(state)
                is MainContract.UiState.CityUpdate -> updateCity(state)
                is MainContract.UiState.Refreshed -> updateSwipeRefreshLayout()
            }
        })
    }

    private fun updateCity(city: MainContract.UiState.CityUpdate) {
        city_value.text = city.city
    }

    private fun updateCurrentWeather(currentWeather: MainContract.UiState.CurrentWeather) {
        updateWindSpeed(currentWeather.windSpeed)
        updateSwipeRefreshLayout()
    }

    private fun updateSwipeRefreshLayout() {
        activityViewModel.refreshed()
    }

    private fun updateWindSpeed(speed: Double) {
        wind_speed_value.text = speed.toString()
    }
}
