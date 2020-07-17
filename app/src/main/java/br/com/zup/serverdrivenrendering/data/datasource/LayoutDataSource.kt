package br.com.zup.serverdrivenrendering.data.datasource

import br.com.zup.serverdrivenrendering.data.model.ScreenInfo
import br.com.zup.serverdrivenrendering.domain.util.Response

interface LayoutDataSource {
    suspend fun getMainScreenLayoutData() : Response<ScreenInfo>
}