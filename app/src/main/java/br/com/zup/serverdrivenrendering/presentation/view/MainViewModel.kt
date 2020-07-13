package br.com.zup.serverdrivenrendering.presentation.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.zup.serverdrivenrendering.domain.LayoutRepository
import br.com.zup.serverdrivenrendering.domain.ScreenLayout

class MainViewModel (
    private val layoutRepository: LayoutRepository
): ViewModel() {

    private val _mainScreenLayoutLiveData = MutableLiveData<ScreenLayout>()
    val mainScreenLayoutLiveData : LiveData<ScreenLayout> = _mainScreenLayoutLiveData
}