package app.itsyour.weathercrane.feature.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import app.itsyour.weathercrane.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.action(MainContract.Action.Refresh) }
        viewModel.uiState.observe(this, Observer {
            when (it) {
                is MainContract.UiState.Refreshed -> swipeRefreshLayout.isRefreshing = false
                else -> Unit
            }
        })
    }
}
