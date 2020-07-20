package br.com.zup.serverdrivenrendering.factory

fun createJsonFile() = """
        {
           "_componentName_":"vertical",
           "children":[
              {
                 "_componentName_":"text",
                 "text":"Hello world"
              },
              {
                 "_componentName_":"text",
                 "text":"From Server!"
              },
              {
                 "_componentName_":"button",
                 "text":"Continue"
              }
           ]
        }
    """.trimIndent()

fun createJsonChildOne() = """{
                 "_componentName_":"text",
                 "text":"Hello world"
              }""".trimIndent()

fun createJsonChildTwo() = """{
                 "_componentName_":"text",
                 "text":"From Server!"
              }""".trimIndent()

fun createJsonChildThree() = """{
                 "_componentName_":"button",
                 "text":"Continue"
              }""".trimIndent()