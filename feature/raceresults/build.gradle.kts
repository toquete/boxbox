plugins {
    id("boxbox.android.library")
    id("boxbox.android.library.compose")
    id("boxbox.android.feature")
    id("boxbox.android.hilt")
}

android {
    namespace = "com.toquete.boxbox.feature.raceresults"
    resourcePrefix = "race_results_"
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:ui"))
    implementation(project(":core:common"))
    implementation(project(":domain:raceresults"))
    implementation(project(":domain:sprintresults"))

    testImplementation(project(":core:testing"))
    androidTestImplementation(project(":core:testing"))
}
