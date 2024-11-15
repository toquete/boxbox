plugins {
    id("boxbox.android.library")
    id("boxbox.android.hilt")
}

android {
    namespace = "com.toquete.boxbox.domain.sprintresults"
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:common"))
    implementation(project(":data:sprintresults"))
    implementation(project(":domain:common"))

    testImplementation(project(":core:testing"))
}
