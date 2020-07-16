package br.com.zup.serverdrivenrendering.data.model

enum class LayoutComponentType(val type: String) {
    LAYOUT_VERTICAL(type = "vertical"),
    TEXT(type = "text"),
    BUTTON(type = "button")
}