package br.com.zup.serverdrivenrendering.data.model

enum class LayoutComponentTag(val tagName: String) {
    COMPONENT_NAME(tagName = "_componentName_"),
    CHILDREN(tagName = "children"),
    TEXT(tagName = "text")
}