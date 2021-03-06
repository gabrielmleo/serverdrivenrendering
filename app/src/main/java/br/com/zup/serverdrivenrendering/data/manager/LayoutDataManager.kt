package br.com.zup.serverdrivenrendering.data.manager

import br.com.zup.serverdrivenrendering.data.datasource.JsonProvider
import br.com.zup.serverdrivenrendering.data.datasource.LayoutDataSource
import br.com.zup.serverdrivenrendering.model.ScreenInfo
import br.com.zup.serverdrivenrendering.domain.exception.ScreenReaderException
import br.com.zup.serverdrivenrendering.model.Response
import br.com.zup.serverdrivenrendering.presentation.widget.ui.UndefinedWidget
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LayoutDataManager(
    private val jsonProvider: JsonProvider,
    private val jsonHandler: JsonHandler
) : LayoutDataSource {
    override suspend fun getMainScreenLayoutData(): Response = withContext(Dispatchers.IO) {
        when (val resultWidget =
            jsonHandler.extractLayoutComponent(jsonString = jsonProvider.provide())) {
            is UndefinedWidget -> {
                Response.Failure(ScreenReaderException())
            }
            else -> {
                Response.Success(ScreenInfo(rootComponent = resultWidget))
            }
        }
    }
}