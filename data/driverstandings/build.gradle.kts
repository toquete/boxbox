plugins {
    id("boxbox.android.library")
    id("boxbox.android.hilt")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.toquete.boxbox.data.driverstandings"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:model"))
    implementation(project(":core:database"))
    implementation(project(":core:network"))
    implementation(project(":data:drivers"))
    implementation(project(":data:constructors"))
    demoImplementation(libs.serialization)
    testImplementation(project(":core:testing"))
}
