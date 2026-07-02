plugins {
    id("boxbox.android.library")
}

android {
    namespace = "com.toquete.boxbox.domain"
}

dependencies {
    api(project(":core:model"))
    api(project(":core:common"))

    testImplementation(project(":core:testing"))

    implementation(libs.koin.android)
}
