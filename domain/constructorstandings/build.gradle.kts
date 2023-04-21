plugins {
    id("boxbox.android.library")
    id("boxbox.android.hilt")
}

android {
    namespace = "com.toquete.boxbox.domain.constructorstandings"
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":data:constructorstandings"))
}