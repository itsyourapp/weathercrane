package app.itsyour.weathercrane.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import app.itsyour.weathercrane.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main.*

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
            = inflater.inflate(R.layout.fragment_main, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.windSpeed.observe(viewLifecycleOwner, Observer<MainViewState> { state ->
            when (state) {
                is WindSpeedUpdated -> updateWindSpeed(state.windSpeed)
            }
        })
    }

    private fun updateWindSpeed(speed: Double) {
        windSpeed.text = speed.toString()
    }
}