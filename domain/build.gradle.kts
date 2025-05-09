plugins {
    id("boxbox.android.library")
    id("boxbox.android.koin")
}

android {
    namespace = "com.toquete.boxbox.domain"
}

dependencies {
    api(project(":core:model"))
    api(project(":core:common"))

    testImplementation(project(":core:testing"))
}
