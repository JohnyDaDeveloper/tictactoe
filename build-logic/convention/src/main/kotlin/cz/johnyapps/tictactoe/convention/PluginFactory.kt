package cz.johnyapps.tictactoe.convention

import org.gradle.api.Plugin
import org.gradle.api.Project

internal object PluginFactory {

    infix fun build(block: Project.() -> Unit): Plugin<Project> {
        return Plugin<Project> { block(it) }
    }
}
