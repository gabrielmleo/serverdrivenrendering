package br.com.zup.serverdrivenrendering.domain.util

import br.com.zup.serverdrivenrendering.model.ScreenInfo
import br.com.zup.serverdrivenrendering.presentation.widget.layout.Vertical
import br.com.zup.serverdrivenrendering.presentation.widget.ui.Text

class DefaultScreenProvider {
    fun getDefaultErrorScreen() =
        ScreenInfo(
            rootComponent = Vertical(
                children = listOf(Text(text = "Não foi possível carregar o layout"))
            )
        )
}