package br.com.zup.serverdrivenrendering.domain.model

import br.com.zup.serverdrivenrendering.presentation.widget.layout.Vertical
import br.com.zup.serverdrivenrendering.presentation.widget.ui.Text

class DefaultScreenProvider {
    fun getDefaultErrorScreen() = ScreenInfo(
        rootComponent = Vertical(
            children = listOf(Text(text = "Não foi possível carregar o layout"))
        )
    )
}