package br.com.zup.serverdrivenrendering.data.model

sealed class ScreenComponent  {
    data class VerticalLayoutComponent(val textValue: String): ScreenComponent()
    data class TextComponent(val textValue: String): ScreenComponent()
    object UndefinedComponent : ScreenComponent()
}