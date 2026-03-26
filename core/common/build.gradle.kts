plugins {
    id("boxbox.kotlin.multiplatform")
    id("dagger.hilt.android.plugin")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.toquete.boxbox.core.common"
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(libs.core.ktx)
            implementation(libs.hilt)
        }
    }
}

dependencies {
    add("kspAndroid", libs.hilt.compiler)
}
