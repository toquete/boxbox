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

    api(libs.junit)
    api(kotlin("test"))
    api(libs.coroutines.test)
    api(libs.mockk)
    api(libs.test.core)
    api(libs.test.runner)
    api(libs.test.rules)
}