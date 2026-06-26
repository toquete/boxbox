plugins {
    id("boxbox.kotlin.multiplatform")
    id("org.jetbrains.kotlin.plugin.serialization")
}

kotlin {
    android {
        namespace = "com.toquete.boxbox.core.navigation"
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.serialization)
        }
    }
}
