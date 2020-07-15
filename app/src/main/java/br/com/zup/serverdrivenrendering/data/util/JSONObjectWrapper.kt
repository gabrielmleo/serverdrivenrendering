package br.com.zup.serverdrivenrendering.data.util

import org.json.JSONArray
import org.json.JSONObject

class JSONObjectWrapper() {
    fun getTagValueFromJson(tag: String, jsonString: String): String {
        return JSONObject(jsonString).getString(tag)
    }
    fun getJsonArrayWithTagFromJsonString(jsonString: String, tag: String): JSONArray {
        return JSONObject(jsonString).getJSONObject(jsonString).getJSONArray(tag)
    }
}