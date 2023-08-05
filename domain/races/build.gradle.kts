plugins {
    id("boxbox.android.library")
    id("boxbox.android.hilt")
}

android {
    namespace = "com.toquete.boxbox.domain.races"
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:common"))
    implementation(project(":domain:common"))

    testImplementation(project(":core:testing"))
}
