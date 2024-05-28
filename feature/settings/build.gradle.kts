plugins {
    id("boxbox.android.library")
    id("boxbox.android.library.compose")
    id("boxbox.android.feature")
    id("boxbox.android.hilt")
}

android {
    namespace = "com.toquete.boxbox.feature.settings"
    resourcePrefix = "settings_"
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:ui"))
    implementation(project(":core:common"))
    implementation(project(":core:preferences"))

    testImplementation(project(":core:testing"))
    androidTestImplementation(project(":core:testing"))
}
