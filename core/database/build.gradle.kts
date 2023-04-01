plugins {
    id("boxbox.android.library")
    id("boxbox.android.hilt")
    id("boxbox.android.room")
}

android {
    namespace = "com.toquete.boxbox.database"
}

dependencies {
    implementation(project(":core:model"))
}