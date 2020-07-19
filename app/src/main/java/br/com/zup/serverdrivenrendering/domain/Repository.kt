package br.com.zup.serverdrivenrendering.domain

import br.com.zup.serverdrivenrendering.data.datasource.LayoutDataSource
import br.com.zup.serverdrivenrendering.model.Response

class Repository (
    private val layoutDataSource: LayoutDataSource
) {
    suspend fun getMainScreenLayoutData(): Response {
        return layoutDataSource.getMainScreenLayoutData()
    }
}