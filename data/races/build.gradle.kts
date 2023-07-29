@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("boxbox.android.library")
    id("boxbox.android.hilt")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.toquete.boxbox.data.races"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:model"))
    implementation(project(":core:database"))
    implementation(project(":core:network"))
    implementation(project(":data:circuits"))
    demoImplementation(libs.serialization)
    testImplementation(project(":core:testing"))
}
