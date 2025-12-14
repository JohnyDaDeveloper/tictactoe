plugins {
    alias(libs.plugins.local.android.app)
    alias(libs.plugins.compose.compiler)
}

android {
    defaultConfig {
        applicationId = "${libs.versions.namespace.get()}"
    }

    buildTypes {
        release {
            isMinifyEnabled = true

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        debug {
            isMinifyEnabled = false
            applicationIdSuffix = ".debug"
        }
    }

    androidResources {
        generateLocaleConfig = true
    }
}

dependencies {

}