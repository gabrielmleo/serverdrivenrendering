package br.com.zup.serverdrivenrendering.data.datasource

import br.com.zup.serverdrivenrendering.data.model.ScreenInfo

interface LayoutDataSource {
    suspend fun getMainScreenLayoutData() : ScreenInfo
}