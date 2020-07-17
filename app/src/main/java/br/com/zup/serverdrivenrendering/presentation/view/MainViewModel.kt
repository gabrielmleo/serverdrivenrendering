package br.com.zup.serverdrivenrendering.presentation.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.zup.serverdrivenrendering.data.model.ScreenInfo
import br.com.zup.serverdrivenrendering.domain.LayoutRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel (
    private val layoutRepository: LayoutRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
): ViewModel() {

    init {
        fetchScreenLayout()
    }
    private val _mainScreenLayoutLiveData = MutableLiveData<ScreenInfo>()
    val mainScreenLayoutLiveData : LiveData<ScreenInfo> = _mainScreenLayoutLiveData

    private fun fetchScreenLayout() = viewModelScope.launch(dispatcher) {
        layoutRepository.getMainScreenLayoutData()
            .onSuccess { TODO() }
            .onFailure { TODO() }
    }
}