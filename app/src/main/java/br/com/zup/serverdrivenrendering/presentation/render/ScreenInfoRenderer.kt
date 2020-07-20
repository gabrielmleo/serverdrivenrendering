package br.com.zup.serverdrivenrendering.presentation.render

import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import br.com.zup.serverdrivenrendering.model.ScreenInfo
import br.com.zup.serverdrivenrendering.presentation.widget.core.Widget
import br.com.zup.serverdrivenrendering.presentation.widget.layout.Vertical
import br.com.zup.serverdrivenrendering.presentation.widget.ui.Text

class ScreenInfoRenderer(private val viewProvider: ViewProvider) {

    fun renderScreenInfo(screenInfo: ScreenInfo): ViewGroup {
        return when (screenInfo.rootComponent) {
            is Vertical -> {
                renderRootVerticalComponent(screenInfo.rootComponent)
            }
            else -> {
                emptyLinearLayout()
            }
        }
    }

    private fun renderRootVerticalComponent(rootComponent: Vertical): ViewGroup {
        val linearLayout = viewProvider.getLinearLayoutVertical()
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
                emptyView()
            }
        }
    }

    private fun renderButtonComponent(it: br.com.zup.serverdrivenrendering.presentation.widget.ui.Button): Button {
        return viewProvider.getButtonWithTextValue(it.text)
    }

    private fun renderTextComponent(it: Text): TextView {
        return viewProvider.getTextViewWithTextValue(it.text)
    }

    private fun emptyLinearLayout(): LinearLayout {
        return viewProvider.getEmptyLinearLayout()
    }

    private fun emptyView(): View {
        return viewProvider.getEmptyView()
    }
}