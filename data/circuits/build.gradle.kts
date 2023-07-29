plugins {
    id("boxbox.android.library")
    id("boxbox.android.hilt")
}

android {
    namespace = "com.toquete.boxbox.data.circuits"
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:database"))
    testImplementation(project(":core:testing"))
}
