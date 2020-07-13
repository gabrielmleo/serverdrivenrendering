package br.com.zup.serverdrivenrendering.data.manager

import br.com.zup.serverdrivenrendering.data.datasource.JsonProvider
import br.com.zup.serverdrivenrendering.data.datasource.LayoutDataSource
import br.com.zup.serverdrivenrendering.data.model.ScreenInfo

class LayoutDataSourceManager (
    private val jsonProvider: JsonProvider,
    private val jsonManager: JsonManager
) : LayoutDataSource{
    override suspend fun getMainScreenLayoutData(): ScreenInfo {
        TODO("Not yet implemented")
    }
    
}