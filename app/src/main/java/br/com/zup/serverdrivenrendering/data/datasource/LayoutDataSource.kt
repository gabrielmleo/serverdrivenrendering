package br.com.zup.serverdrivenrendering.data.datasource

import br.com.zup.serverdrivenrendering.model.Response

interface LayoutDataSource {
    suspend fun getMainScreenLayoutData() : Response
}