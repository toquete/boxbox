plugins {
    id("boxbox.android.library")
    id("boxbox.android.hilt")
}

android {
    namespace = "com.toquete.boxbox.data.countries"
}

dependencies {
    implementation(project(":core:database"))
    implementation(project(":core:network"))
    implementation(project(":domain"))
    testImplementation(project(":core:testing"))
}
