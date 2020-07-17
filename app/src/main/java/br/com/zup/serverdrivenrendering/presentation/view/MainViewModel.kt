package br.com.zup.serverdrivenrendering.presentation.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.zup.serverdrivenrendering.domain.model.ScreenInfo
import br.com.zup.serverdrivenrendering.domain.LayoutRepository
import br.com.zup.serverdrivenrendering.domain.model.DefaultScreenProvider
import br.com.zup.serverdrivenrendering.domain.model.Response
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel (
    private val layoutRepository: LayoutRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main,
    private val defaultScreenProvider: DefaultScreenProvider
): ViewModel() {

    private val _mainScreenLayoutLiveData = MutableLiveData<ScreenInfo>()
    val mainScreenLayoutLiveData : LiveData<ScreenInfo> = _mainScreenLayoutLiveData

    fun fetchScreenLayout() = viewModelScope.launch(dispatcher) {
        val result = layoutRepository.getMainScreenLayoutData()
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