package br.com.zup.serverdrivenrendering.presentation

import android.widget.LinearLayout
import android.widget.TextView
import br.com.zup.serverdrivenrendering.model.ScreenInfo
import br.com.zup.serverdrivenrendering.presentation.render.ScreenInfoRenderer
import br.com.zup.serverdrivenrendering.presentation.render.ViewProvider
import br.com.zup.serverdrivenrendering.presentation.widget.layout.Vertical
import br.com.zup.serverdrivenrendering.presentation.widget.ui.Text
import br.com.zup.serverdrivenrendering.presentation.widget.ui.UndefinedWidget
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class ScreenInfoRendererTest {

    @Nested
    inner class `Given a screen info with a vertical layout and a Text as child` {

        private val textViewMock: TextView = mockk()
        private val textMock: String = "text"
        private val linearLayoutMock: LinearLayout = mockk()
        private val screenInfoMock: ScreenInfo = ScreenInfo(rootComponent = Vertical(children = listOf(Text(textMock))))
        private val viewProviderMock: ViewProvider = mockk()
        private val subject = ScreenInfoRenderer(viewProvider = viewProviderMock)

        @Test
        fun `When call render Should return a LinearLayout with textView inside`() {
            every { viewProviderMock.getLinearLayoutVertical() } returns linearLayoutMock
            every { viewProviderMock.getTextViewWithTextValue(textMock) } returns textViewMock
            every { linearLayoutMock.addView(textViewMock) } answers {}

            val expected = linearLayoutMock
            val result = subject.renderScreenInfo(screenInfo = screenInfoMock)

            Assertions.assertEquals(expected, result)

        }
    }

    @Nested
    inner class `Given a screen info with non vertical layout` {

        private val linearLayoutMock: LinearLayout = mockk()
        private val screenInfoMock: ScreenInfo = ScreenInfo(rootComponent = UndefinedWidget())
        private val viewProviderMock: ViewProvider = mockk()
        private val subject = ScreenInfoRenderer(viewProvider = viewProviderMock)

        @Test
        fun `When call render Should return a empty linear layout`() {
            every { viewProviderMock.getEmptyLinearLayout() } returns linearLayoutMock

            val expected = linearLayoutMock
            val result = subject.renderScreenInfo(screenInfo = screenInfoMock)

            Assertions.assertEquals(expected, result)

        }
    }
}