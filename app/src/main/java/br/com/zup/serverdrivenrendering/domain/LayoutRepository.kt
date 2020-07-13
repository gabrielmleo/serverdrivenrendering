package br.com.zup.serverdrivenrendering.domain

import br.com.zup.serverdrivenrendering.data.datasource.LayoutDataSource

class LayoutRepository(
    private val screenInfoMapper: ScreenInfoMapper,
    private val layoutDataSource: LayoutDataSource
) {
    suspend fun getMainScreenLayoutData(): ScreenLayout {
        TODO()
    }
}