plugins {
    id("boxbox.android.library")
    id("boxbox.android.koin")
}

android {
    namespace = "com.toquete.boxbox.data.sprintresults"
}

dependencies {
    implementation(project(":core:database"))
    implementation(project(":core:network"))
    implementation(project(":domain"))
    testImplementation(project(":core:testing"))
}
