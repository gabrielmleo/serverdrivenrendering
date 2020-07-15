package br.com.zup.serverdrivenrendering.data.model

import br.com.zup.serverdrivenrendering.presentation.widget.core.Widget

data class ScreenInfo (
    val layoutComponent: Widget,
    val children: List<Widget>
)
