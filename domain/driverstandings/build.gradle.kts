plugins {
    id("boxbox.android.library")
    id("boxbox.android.hilt")
}

android {
    namespace = "com.toquete.boxbox.domain.driverstandings"
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":data:driverstandings"))
}