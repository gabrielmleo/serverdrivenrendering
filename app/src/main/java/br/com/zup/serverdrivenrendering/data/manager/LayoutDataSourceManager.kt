package br.com.zup.serverdrivenrendering.data.manager

import br.com.zup.serverdrivenrendering.data.datasource.JsonProvider
import br.com.zup.serverdrivenrendering.data.datasource.LayoutDataSource
import br.com.zup.serverdrivenrendering.domain.model.ScreenInfo
import br.com.zup.serverdrivenrendering.domain.exception.ScreenReaderException
import br.com.zup.serverdrivenrendering.domain.model.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LayoutDataSourceManager(
    private val jsonProvider: JsonProvider,
    private val jsonManager: JsonManager
) : LayoutDataSource {
    override suspend fun getMainScreenLayoutData(): Response = withContext(Dispatchers.IO) {
        try {
            Response.Success(
                ScreenInfo(
                    rootComponent = jsonManager.extractLayoutComponent(jsonString = jsonProvider.provide())
                )
            )
        } catch (screenReaderException: Exception) {
            Response.Failure(ScreenReaderException())
        }
    }
}