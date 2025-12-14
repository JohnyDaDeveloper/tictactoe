package cz.johnyapps.tictactoe.multiplaform.core.common.domain

import cz.johnyapps.tictactoe.multiplaform.core.common.domain.model.Url

fun urlBuilder(
    block: MutableUrl.() -> Unit,
): Url {
    val mutableUrl = MutableUrl()
    mutableUrl.block()
    return mutableUrl.build()
}

class MutableUrl {

    private val stringBuilder = StringBuilder()
    private var baseUrl: String? = null

    fun setBaseUrl(baseUrl: String) {
        this.baseUrl = baseUrl
    }

    fun appendParam(key: String, value: String) {
        if (stringBuilder.isEmpty()) {
            stringBuilder.append("?")
        } else {
            stringBuilder.append("&")
        }

        stringBuilder.append(key)
        stringBuilder.append("=")
        stringBuilder.append(value)
    }

    fun build(): Url {
        val baseUrl = this.baseUrl ?: error("Base URL must be set")
        return Url(
            baseUrl + stringBuilder.toString()
        )
    }
}
