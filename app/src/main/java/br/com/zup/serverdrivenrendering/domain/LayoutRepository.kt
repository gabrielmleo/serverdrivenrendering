package br.com.zup.serverdrivenrendering.domain

import br.com.zup.serverdrivenrendering.data.datasource.LayoutDataSource
import br.com.zup.serverdrivenrendering.data.model.ScreenInfo

class LayoutRepository(
    private val layoutDataSource: LayoutDataSource
) {
    suspend fun getMainScreenLayoutData(): ScreenInfo {
        return layoutDataSource.getMainScreenLayoutData()
    }
}