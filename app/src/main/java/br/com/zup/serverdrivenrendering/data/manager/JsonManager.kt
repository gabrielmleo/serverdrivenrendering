package br.com.zup.serverdrivenrendering.data.manager

import br.com.zup.serverdrivenrendering.data.util.JSONObjectWrapper
import br.com.zup.serverdrivenrendering.presentation.widget.core.Widget
import br.com.zup.serverdrivenrendering.presentation.widget.layout.Vertical
import br.com.zup.serverdrivenrendering.presentation.widget.ui.Button
import br.com.zup.serverdrivenrendering.presentation.widget.ui.Text
import br.com.zup.serverdrivenrendering.presentation.widget.ui.UndefinedWidget
import org.json.JSONArray
import org.json.JSONException

class JsonManager(private val jsonObjectWrapper: JSONObjectWrapper = JSONObjectWrapper()) {

    fun extractChildrenComponent(jsonString: String): List<Widget> {
        var childrenComponentArray = mutableListOf<Widget>()
        return try {
            val childrenJsonArray: JSONArray =
                jsonObjectWrapper.getJsonArrayWithTagFromJsonString(jsonString, "children")
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
                jsonObjectWrapper.getTagValueFromJson("_componentName_", jsonString)
            getComponentByTypeWithJson(layoutComponentName, jsonString)
        } catch (jsonException: JSONException) {
            UndefinedWidget()
        }
    }

    private fun getComponentByTypeWithJson(
        layoutComponentName: String,
        jsonString: String
    ): Widget {
        return when (layoutComponentName) {
            "vertical" -> {
                Vertical(children = extractChildrenComponent(jsonString))
            }
            "text" -> {
                Text(text = jsonObjectWrapper.getTagValueFromJson(tag = "text", jsonString = jsonString))
            }
            "button" -> {
                Button(text = jsonObjectWrapper.getTagValueFromJson(tag = "text", jsonString = jsonString))
            }
            else -> {
                TODO()
            }
        }
    }
}
