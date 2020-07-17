package br.com.zup.serverdrivenrendering.presentation.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.zup.serverdrivenrendering.domain.model.ScreenInfo
import br.com.zup.serverdrivenrendering.domain.LayoutRepository
import br.com.zup.serverdrivenrendering.domain.model.DefaultScreenProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel (
    private val layoutRepository: LayoutRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main,
    private val defaultScreenProvider: DefaultScreenProvider
): ViewModel() {

    init {
        fetchScreenLayout()
    }
    private val _mainScreenLayoutLiveData = MutableLiveData<ScreenInfo>()
    val mainScreenLayoutLiveData : LiveData<ScreenInfo> = _mainScreenLayoutLiveData

    private fun fetchScreenLayout() = viewModelScope.launch(dispatcher) {
        layoutRepository.getMainScreenLayoutData()
            .onSuccess { _mainScreenLayoutLiveData.value = it }
            .onFailure { _mainScreenLayoutLiveData.value = defaultScreenProvider.getDefaultErrorScreen() }
    }
}