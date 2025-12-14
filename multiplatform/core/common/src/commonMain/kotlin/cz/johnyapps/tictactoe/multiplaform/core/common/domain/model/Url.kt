package cz.johnyapps.tictactoe.multiplaform.core.common.domain.model

data class Url(
    val value: String
) {

    init {
        check(value.isNotEmpty()) {
            "URL can't be empty"
        }
    }
}
