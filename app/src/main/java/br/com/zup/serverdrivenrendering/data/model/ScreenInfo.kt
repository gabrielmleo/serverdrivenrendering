package br.com.zup.serverdrivenrendering.data.model

data class ScreenInfo (
    val layoutComponent: ScreenComponent,
    val children: List<ScreenComponent>
)
