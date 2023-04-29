plugins {
    id("boxbox.android.library")
    id("boxbox.android.library.compose")
    id("boxbox.android.hilt")
}

android {
    namespace = "com.toquete.boxbox.core.testing"
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:database"))
    implementation(project(":core:network"))
}