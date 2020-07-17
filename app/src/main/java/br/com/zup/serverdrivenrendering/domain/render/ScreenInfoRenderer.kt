package br.com.zup.serverdrivenrendering.domain.render

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import br.com.zup.serverdrivenrendering.domain.model.ScreenInfo
import br.com.zup.serverdrivenrendering.presentation.widget.core.Widget
import br.com.zup.serverdrivenrendering.presentation.widget.layout.Vertical
import br.com.zup.serverdrivenrendering.presentation.widget.ui.Text

class ScreenInfoRenderer(private val context: Context) {

    fun renderScreenInfo(screenInfo: ScreenInfo): ViewGroup {
        when (screenInfo.rootComponent) {
            is Vertical -> {
                return renderRootVerticalComponent(screenInfo.rootComponent)
            }
            else -> {
                TODO("Should return nothing")
            }
        }
    }

    private fun renderRootVerticalComponent(rootComponent: Vertical): ViewGroup {
        val linearLayout = LinearLayout(context)
        linearLayout.orientation = LinearLayout.VERTICAL
        linearLayout.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        rootComponent.children.forEach {
            linearLayout.addView(renderVerticalChildComponent(it))
        }
        return linearLayout
    }

    private fun renderVerticalChildComponent(it: Widget): View {
        return when (it) {
            is Text -> {
                renderTextComponent(it)
            }
            is br.com.zup.serverdrivenrendering.presentation.widget.ui.Button -> {
                renderButtonComponent(it)
            }
            else -> {
                TODO("Should return nothing")
            }
        }
    }

    private fun renderButtonComponent(it: br.com.zup.serverdrivenrendering.presentation.widget.ui.Button): Button {
        val button = Button(context)
        button.text = it.text
        return button
    }

    private fun renderTextComponent(it: Text): TextView {
        val textView = TextView(context)
        textView.text = it.text
        return textView
    }
}