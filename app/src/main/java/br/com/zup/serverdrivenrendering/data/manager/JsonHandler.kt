package br.com.zup.serverdrivenrendering.data.manager

import br.com.zup.serverdrivenrendering.data.model.LayoutComponentTag
import br.com.zup.serverdrivenrendering.data.model.LayoutComponentType
import br.com.zup.serverdrivenrendering.data.util.JSONObjectWrapper
import br.com.zup.serverdrivenrendering.presentation.widget.core.Widget
import br.com.zup.serverdrivenrendering.presentation.widget.layout.Vertical
import br.com.zup.serverdrivenrendering.presentation.widget.ui.Button
import br.com.zup.serverdrivenrendering.presentation.widget.ui.Text
import br.com.zup.serverdrivenrendering.presentation.widget.ui.UndefinedWidget
import org.json.JSONArray
import org.json.JSONException
import java.lang.Exception

class JsonHandler(private val jsonObjectWrapper: JSONObjectWrapper = JSONObjectWrapper()) {

    private fun extractChildrenComponent(jsonString: String): List<Widget> {
        var childrenComponentArray = mutableListOf<Widget>()
        return try {
            val childrenJsonArray: JSONArray =
                jsonObjectWrapper.getJsonArrayWithTagFromJsonString(jsonString, LayoutComponentTag.CHILDREN.tagName)
            (0 until childrenJsonArray.length()).forEach { i ->
                childrenComponentArray.add(
                    i,
                    extractLayoutComponent(childrenJsonArray.getString(i))
                )
            }
            childrenComponentArray.toList()
        } catch (jsonException: JSONException) {
            childrenComponentArray.toList()
        }

    }

    fun extractLayoutComponent(jsonString: String): Widget {
        return try {
            val layoutComponentName =
                jsonObjectWrapper.getTagValueFromJson(LayoutComponentTag.COMPONENT_NAME.tagName, jsonString)
            getComponentByTypeWithJson(layoutComponentName, jsonString)
        } catch (jsonException: Exception) {
            UndefinedWidget()
        }
    }

    private fun getComponentByTypeWithJson(
        layoutComponentName: String,
        jsonString: String
    ): Widget {
        return when (layoutComponentName) {
            LayoutComponentType.LAYOUT_VERTICAL.type -> {
                Vertical(children = extractChildrenComponent(jsonString))
            }
            LayoutComponentType.TEXT.type -> {
                Text(text = jsonObjectWrapper.getTagValueFromJson(tag = LayoutComponentTag.TEXT.tagName, jsonString = jsonString))
            }
            LayoutComponentType.BUTTON.type -> {
                Button(text = jsonObjectWrapper.getTagValueFromJson(tag = LayoutComponentTag.TEXT.tagName, jsonString = jsonString))
            }
            else -> {
                UndefinedWidget()
            }
        }
    }
}
