package br.com.zup.serverdrivenrendering.presentation.render

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class ViewProvider(private val context: Context) {
    fun getLinearLayoutVertical(): LinearLayout {
        val linear =  LinearLayout(context)
        linear.orientation = LinearLayout.VERTICAL
        linear.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        return linear
    }

    fun getTextViewWithTextValue(text: String): TextView {
        val textView = TextView(context)
        textView.text = text
        return textView
    }

    fun getButtonWithTextValue(text: String): Button {
        val button = Button(context)
        button.text = text
        return button
    }

    fun getEmptyLinearLayout(): LinearLayout {
        return LinearLayout(context)
    }

    fun getEmptyView(): View {
        return View(context)
    }
}