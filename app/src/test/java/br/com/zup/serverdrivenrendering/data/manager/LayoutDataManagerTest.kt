package br.com.zup.serverdrivenrendering.data.manager

import br.com.zup.serverdrivenrendering.data.datasource.JsonProvider
import br.com.zup.serverdrivenrendering.domain.exception.ScreenReaderException
import br.com.zup.serverdrivenrendering.domain.model.Response
import br.com.zup.serverdrivenrendering.domain.model.ScreenInfo
import br.com.zup.serverdrivenrendering.factory.createJsonFile
import br.com.zup.serverdrivenrendering.presentation.widget.core.Widget
import br.com.zup.serverdrivenrendering.presentation.widget.ui.UndefinedWidget
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@Suppress("ClassName", "TestFunctionName")
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class LayoutDataManagerTest {

    private val undefinedWidgetMock: Widget = UndefinedWidget()
    private val widgetMock: Widget = mockk()
    private val jsonProviderMock: JsonProvider = mockk()
    private val jsonManagerMock: JsonManager = mockk()
    private val jsonMock = createJsonFile()

    @Nested
    inner class `Given a jsonManager and a jsonProvider` {
        val subject = LayoutDataManager(jsonProviderMock, jsonManagerMock)

        @Test
        fun `When jsonManager return a widget Should return response success with widget inside it`() =
            runBlocking {
                every { jsonProviderMock.provide() } returns jsonMock
                every { jsonManagerMock.extractLayoutComponent(jsonString = jsonMock) } returns widgetMock

                val result = subject.getMainScreenLayoutData()
                val expected = Response.Success(ScreenInfo(rootComponent = widgetMock))
                Assertions.assertEquals(expected, result)
            }

        @Test
        fun `When jsonManager return an error Should return response failure with ScreenReaderException inside it`() =
            runBlocking {
                every { jsonProviderMock.provide() } returns jsonMock
                every { jsonManagerMock.extractLayoutComponent(jsonString = jsonMock) } returns undefinedWidgetMock

                val result = subject.getMainScreenLayoutData()
                val expected = Response.Failure(ScreenReaderException())
                Assertions.assertEquals(expected, result)
            }
    }
}