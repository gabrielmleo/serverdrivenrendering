package br.com.zup.serverdrivenrendering.data.manager

import br.com.zup.serverdrivenrendering.data.datasource.JsonProvider
import br.com.zup.serverdrivenrendering.data.util.JSONObjectWrapper
import br.com.zup.serverdrivenrendering.factory.createJsonChildOne
import br.com.zup.serverdrivenrendering.factory.createJsonChildThree
import br.com.zup.serverdrivenrendering.factory.createJsonChildTwo
import br.com.zup.serverdrivenrendering.factory.createJsonFile
import br.com.zup.serverdrivenrendering.presentation.widget.layout.Vertical
import br.com.zup.serverdrivenrendering.presentation.widget.ui.Button
import br.com.zup.serverdrivenrendering.presentation.widget.ui.Text
import io.mockk.every
import io.mockk.mockk
import org.json.JSONArray
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@Suppress("ClassName", "TestFunctionName")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class JsonManagerTest {

    @Nested
    inner class `Given a valid Json of main ScreenInfo object to JsonManager` {

        private val jsonArrayMock: JSONArray = mockk()
        private val componentNameTagMock = "_componentName_"
        private val childrenTagMock = "children"
        private val textTagMock = "text"
        private val buttonTagMock = "button"
        private val jsonObjectWrapperMock: JSONObjectWrapper = mockk()
        private val jsonString = createJsonFile()
        private val jsonChildOne = createJsonChildOne()
        private val jsonChildTwo = createJsonChildTwo()
        private val jsonChildThree = createJsonChildThree()
        private val childComponentOneText = "Hello world"
        private val childComponentTwoText = "From Server!"
        private val childComponentThreeText = "Continue"
        private val rootComponentText = "vertical"

        val subject = JsonManager(jsonObjectWrapperMock)

        @Test
        fun `When call extract method for main layout Should return the corresponding ScreenComponent object`() {
            every { jsonObjectWrapperMock.getTagValueFromJson(tag = componentNameTagMock, jsonString = jsonString ) } returns rootComponentText
            every { jsonObjectWrapperMock.getTagValueFromJson(tag = textTagMock, jsonString = jsonChildOne ) } returns childComponentOneText
            every { jsonObjectWrapperMock.getTagValueFromJson(tag = textTagMock, jsonString = jsonChildTwo ) } returns childComponentTwoText
            every { jsonObjectWrapperMock.getTagValueFromJson(tag = textTagMock, jsonString = jsonChildThree ) } returns childComponentThreeText
            every { jsonObjectWrapperMock.getTagValueFromJson(tag = componentNameTagMock, jsonString = jsonChildOne ) } returns textTagMock
            every { jsonObjectWrapperMock.getTagValueFromJson(tag = componentNameTagMock, jsonString = jsonChildTwo ) } returns textTagMock
            every { jsonObjectWrapperMock.getTagValueFromJson(tag = componentNameTagMock, jsonString = jsonChildThree ) } returns buttonTagMock
            every { jsonObjectWrapperMock.getJsonArrayWithTagFromJsonString(jsonString = jsonString, tag = childrenTagMock) } returns jsonArrayMock
            every { jsonArrayMock.length() } returns 3
            every { jsonArrayMock.getString(0) } returns jsonChildOne
            every { jsonArrayMock.getString(1) } returns jsonChildTwo
            every { jsonArrayMock.getString(2) } returns jsonChildThree

            val expected = Vertical(children = listOf(
                Text(text = childComponentOneText),
                Text(text = childComponentTwoText),
                Button(text = childComponentThreeText)
            ))

            val result = subject.extractLayoutComponent(JsonProvider().provide())

            Assertions.assertEquals(expected, result)
        }
    }
}