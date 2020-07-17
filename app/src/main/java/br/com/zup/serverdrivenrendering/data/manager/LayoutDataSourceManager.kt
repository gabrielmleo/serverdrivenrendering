package br.com.zup.serverdrivenrendering.data.manager

import br.com.zup.serverdrivenrendering.data.datasource.JsonProvider
import br.com.zup.serverdrivenrendering.data.datasource.LayoutDataSource
import br.com.zup.serverdrivenrendering.data.model.ScreenInfo
import br.com.zup.serverdrivenrendering.domain.exception.ScreenReaderException
import br.com.zup.serverdrivenrendering.domain.util.Response

class LayoutDataSourceManager(
    private val jsonProvider: JsonProvider,
    private val jsonManager: JsonManager
) : LayoutDataSource {
    override suspend fun getMainScreenLayoutData(): Response<ScreenInfo> {
        return try {
            Response.success(
                ScreenInfo(
                    rootComponent = jsonManager.extractLayoutComponent(jsonString = jsonProvider.provide())
                )
            )
        } catch (screenReaderException: Exception) {
            Response.failure(ScreenReaderException())
        }
    }
}