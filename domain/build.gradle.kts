plugins {
    id("boxbox.android.library")
    id("boxbox.android.hilt")
}

android {
    namespace = "com.toquete.boxbox.domain"
}

dependencies {
    api(project(":core:model"))
    api(project(":core:common"))
    implementation(project(":data:races"))
    implementation(project(":data:raceresults"))
    implementation(project(":data:sprintresults"))

    testImplementation(project(":core:testing"))
}
