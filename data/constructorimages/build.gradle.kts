plugins {
    id("boxbox.android.library")
    id("boxbox.android.hilt")
    id("boxbox.android.koin")
}

android {
    namespace = "com.toquete.boxbox.data.constructorimages"
}

dependencies {
    implementation(project(":core:database"))
    implementation(project(":core:network"))
    implementation(project(":domain"))
    testImplementation(project(":core:testing"))
}
