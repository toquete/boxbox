plugins {
    id("boxbox.android.library")
    id("boxbox.android.koin")
}

android {
    namespace = "com.toquete.boxbox.data.raceresults"
}

dependencies {
    implementation(project(":core:database"))
    implementation(project(":core:network"))
    implementation(project(":domain"))
    testImplementation(project(":core:testing"))
}
