package br.com.zup.serverdrivenrendering.presentation.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.zup.serverdrivenrendering.model.ScreenInfo
import br.com.zup.serverdrivenrendering.domain.LayoutService
import br.com.zup.serverdrivenrendering.domain.util.DefaultScreenProvider
import br.com.zup.serverdrivenrendering.model.Response
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel (
    private val layoutService: LayoutService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main,
    private val defaultScreenProvider: DefaultScreenProvider
): ViewModel() {

    private val _mainScreenLayoutLiveData = MutableLiveData<ScreenInfo>()
    val mainScreenLayoutLiveData : LiveData<ScreenInfo> = _mainScreenLayoutLiveData

    fun fetchScreenLayout() = viewModelScope.launch(dispatcher) {
        val result = layoutService.getMainScreenLayoutData()
        when(result) {
            is Response.Success -> {
                _mainScreenLayoutLiveData.value = result.screenInfo
            }
            is Response.Failure -> {
                _mainScreenLayoutLiveData.value = defaultScreenProvider.getDefaultErrorScreen()
            }
        }
    }
}