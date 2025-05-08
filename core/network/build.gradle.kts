plugins {
    id("boxbox.android.library")
    id("boxbox.android.remote")
    id("boxbox.android.koin")
}

android {
    namespace = "com.toquete.boxbox.core.network"
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:common"))
    implementation(libs.mockwebserver)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.firestore)

    testImplementation(project(":core:testing"))
}
