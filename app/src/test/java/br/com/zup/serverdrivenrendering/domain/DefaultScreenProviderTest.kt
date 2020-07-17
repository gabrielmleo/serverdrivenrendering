package br.com.zup.serverdrivenrendering.domain

import br.com.zup.serverdrivenrendering.domain.model.DefaultScreenProvider
import br.com.zup.serverdrivenrendering.domain.model.ScreenInfo
import br.com.zup.serverdrivenrendering.presentation.widget.layout.Vertical
import br.com.zup.serverdrivenrendering.presentation.widget.ui.Text
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@Suppress("ClassName", "TestFunctionName")
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class DefaultScreenProviderTest {

    @Nested
    inner class `Given a DefaultScreenProcider` {

        val subject = DefaultScreenProvider()
        @Test
        fun `When call getDefaultErrorScreen Should return default screen info`() {
            val expected = ScreenInfo(
                rootComponent = Vertical(
                    children = listOf(Text(text = "Não foi possível carregar o layout"))
                )
            )
            val result = subject.getDefaultErrorScreen()
            Assertions.assertEquals(expected, result)
        }
    }
}