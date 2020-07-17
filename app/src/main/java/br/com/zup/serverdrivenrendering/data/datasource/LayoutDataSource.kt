package br.com.zup.serverdrivenrendering.data.datasource

import br.com.zup.serverdrivenrendering.domain.model.ScreenInfo
import br.com.zup.serverdrivenrendering.domain.model.Response

interface LayoutDataSource {
    suspend fun getMainScreenLayoutData() : Response<ScreenInfo>
}