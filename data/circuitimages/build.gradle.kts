plugins {
    id("boxbox.android.library")
}

android {
    namespace = "com.toquete.boxbox.data.circuitimages"
}

dependencies {
    implementation(project(":core:database"))
    implementation(project(":core:network"))
    implementation(project(":domain"))
    implementation(libs.koin.android)
    testImplementation(project(":core:testing"))
}
