package com.imxie.core.ktx.util

import org.json.JSONException
import org.json.JSONObject

fun JSONObject.putSilent(vararg properties: Pair<String, Any?>): JSONObject {
    properties.forEach {
        try {
            put(it.first, it.second)
        } catch (e: JSONException) {
        }
    }
    return this
}

fun JSONObject.putMap(map: Map<String, Any?>): JSONObject {
    map.forEach {
        val value = if (it.value is CharSequence) {
            it.value.toString()
        } else {
            it.value
        }
        putSilent(it.key to value)
    }
    return this
}