plugins {
    id("boxbox.android.library")
    id("boxbox.android.hilt")
}

android {
    namespace = "com.toquete.boxbox.data.fulldriverstandings"
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:database"))
    implementation(project(":core:preferences"))
    implementation(project(":core:network"))
    implementation(project(":data:driverstandings"))
    implementation(project(":data:drivers"))
    implementation(project(":data:constructors"))
}