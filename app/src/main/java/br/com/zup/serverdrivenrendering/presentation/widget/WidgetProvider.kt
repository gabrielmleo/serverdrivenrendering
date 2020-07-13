package br.com.zup.serverdrivenrendering.presentation.widget

import br.com.zup.serverdrivenrendering.presentation.widget.core.Widget
import br.com.zup.serverdrivenrendering.presentation.widget.layout.Vertical
import br.com.zup.serverdrivenrendering.presentation.widget.ui.Button
import br.com.zup.serverdrivenrendering.presentation.widget.ui.Text

class WidgetProvider {

    fun provide(): Widget {
        return Vertical(
            children = listOf(
                Text("Hello world"),
                Text("From Server!"),
                Button("Continue")
            )
        )
    }
}