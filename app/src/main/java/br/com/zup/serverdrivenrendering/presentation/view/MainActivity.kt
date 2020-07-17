package br.com.zup.serverdrivenrendering.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import br.com.zup.serverdrivenrendering.domain.render.ScreenInfoRenderer
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mainViewModel by viewModel<MainViewModel>()
    private val screenInfoRenderer: ScreenInfoRenderer by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel.fetchScreenLayout()
        observerFetchScreenLayout()
    }

    private fun observerFetchScreenLayout() {
        mainViewModel.mainScreenLayoutLiveData.observe(this, Observer {
            it?.let {
                setContentView(screenInfoRenderer.renderScreenInfo(it))
            }
        })
    }
}
