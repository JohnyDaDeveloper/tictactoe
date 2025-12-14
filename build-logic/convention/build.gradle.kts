plugins {
    `kotlin-dsl`
}

kotlin {
    jvmToolchain(libs.versions.java.get().toInt())
}

repositories {
    gradlePluginPortal()
    google()
    mavenCentral()
}

gradlePlugin {
    val pluginsPackage = "${libs.versions.namespace.get()}.convention.plugin"

    plugins {
        register("localAndroidApplication") {
            id = libs.plugins.local.android.app.get().pluginId
            implementationClass = "$pluginsPackage.AndroidAppPlugin"
        }
        register("localMultiplatformLibrary") {
            id = libs.plugins.local.multiplatform.library.get().pluginId
            implementationClass = "$pluginsPackage.MultiplatformLibraryPlugin"
        }
        register("localMultiplatformUi") {
            id = libs.plugins.local.multiplatform.ui.get().pluginId
            implementationClass = "$pluginsPackage.MultiplatformUiPlugin"
        }
        register("localNetworking") {
            id = libs.plugins.local.networking.get().pluginId
            implementationClass = "$pluginsPackage.NetworkingPlugin"
        }
    }
}

dependencies {
    implementation(libs.gradle.android)
    implementation(libs.gradle.kotlin)
    implementation(libs.gradle.compose)
    implementation(libs.gradle.detekt)
}