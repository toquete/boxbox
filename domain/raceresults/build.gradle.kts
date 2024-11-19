plugins {
    id("boxbox.android.library")
    id("boxbox.android.hilt")
}

android {
    namespace = "com.toquete.boxbox.domain.raceresults"
}

dependencies {
    implementation(project(":data:raceresults"))
    implementation(project(":domainnew"))

    testImplementation(project(":core:testing"))
}
