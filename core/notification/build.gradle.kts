plugins {
    id("boxbox.android.library")
    id("boxbox.android.hilt")
}

android {
    namespace = "com.toquete.boxbox.core.notification"
}

dependencies {
    testImplementation(project(":core:testing"))
    androidTestImplementation(project(":core:testing"))
}
