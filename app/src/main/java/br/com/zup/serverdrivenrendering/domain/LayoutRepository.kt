package br.com.zup.serverdrivenrendering.domain

import br.com.zup.serverdrivenrendering.data.datasource.LayoutDataSource
import br.com.zup.serverdrivenrendering.domain.model.ScreenInfo
import br.com.zup.serverdrivenrendering.domain.model.Response

class LayoutRepository(
    private val layoutDataSource: LayoutDataSource
) {
    suspend fun getMainScreenLayoutData(): Response<ScreenInfo> {
      return layoutDataSource.getMainScreenLayoutData()
    }
}