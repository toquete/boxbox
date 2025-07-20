plugins {
    id("boxbox.android.library")
}

android {
    namespace = "com.toquete.boxbox.core.testing"
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:database"))
    implementation(project(":core:network"))

    api(libs.junit)
    api(libs.junit.ext)
    api(kotlin("test"))
    api(libs.coroutines.test)
    api(libs.mockk)
    api(libs.test.core)
    api(libs.test.runner)
    api(libs.test.rules)
    api(libs.espresso)
}
