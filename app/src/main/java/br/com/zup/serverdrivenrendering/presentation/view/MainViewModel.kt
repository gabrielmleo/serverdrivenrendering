package br.com.zup.serverdrivenrendering.presentation.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.zup.serverdrivenrendering.data.model.ScreenInfo
import br.com.zup.serverdrivenrendering.domain.LayoutRepository

class MainViewModel (
    private val layoutRepository: LayoutRepository
): ViewModel() {

    private val _mainScreenLayoutLiveData = MutableLiveData<ScreenInfo>()
    val mainScreenLayoutLiveData : LiveData<ScreenInfo> = _mainScreenLayoutLiveData
}