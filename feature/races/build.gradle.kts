plugins {
    id("boxbox.android.library")
    id("boxbox.android.library.compose")
    id("boxbox.android.feature")
    id("boxbox.android.hilt")
}

android {
    namespace = "com.toquete.boxbox.feature.races"
    resourcePrefix = "races_"
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:ui"))
    implementation(project(":core:common"))
    implementation(project(":domain:races"))

    testImplementation(project(":core:testing"))
    testImplementation(libs.robolectric)
}
