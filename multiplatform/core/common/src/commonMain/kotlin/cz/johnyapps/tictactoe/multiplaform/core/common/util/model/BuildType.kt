package cz.johnyapps.tictactoe.multiplaform.core.common.util.model

enum class BuildType(
    val isPublic: Boolean,
) {
    Debug(
        isPublic = true,
    ),
    Release(
        isPublic = false,
    ),
    ClosedRelease(
        isPublic = true,
    ),
}
