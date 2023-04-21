plugins {
    id("boxbox.android.library")
    id("boxbox.android.hilt")
}

android {
    namespace = "com.toquete.boxbox.domain.fullconstructorstandings"
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":data:constructorstandings"))
}