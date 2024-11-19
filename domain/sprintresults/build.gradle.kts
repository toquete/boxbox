plugins {
    id("boxbox.android.library")
    id("boxbox.android.hilt")
}

android {
    namespace = "com.toquete.boxbox.domain.sprintresults"
}

dependencies {
    implementation(project(":data:sprintresults"))
    implementation(project(":domainnew"))

    testImplementation(project(":core:testing"))
}
