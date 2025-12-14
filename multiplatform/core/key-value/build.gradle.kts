plugins {
    alias(libs.plugins.local.multiplatform.library)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.datastore.core)
            implementation(libs.datastore.preferences)
        }
    }
}
