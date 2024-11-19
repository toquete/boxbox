plugins {
    id("boxbox.android.library")
    id("boxbox.android.hilt")
}

android {
    namespace = "com.toquete.boxbox.domain.races"
}

dependencies {
    implementation(project(":data:races"))
    implementation(project(":domainnew"))

    testImplementation(project(":core:testing"))
}
