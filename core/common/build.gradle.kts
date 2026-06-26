plugins {
    id("boxbox.kotlin.multiplatform")
}

kotlin {
    android {
        namespace = "com.toquete.boxbox.core.common"
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.napier)
        }
        androidMain.dependencies {
            implementation(libs.core.ktx)
            implementation(libs.hilt)
        }
    }
}
