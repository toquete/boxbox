plugins {
    id("boxbox.android.application")
    id("boxbox.android.application.compose")
    id("boxbox.android.hilt")
}

android {
    namespace = "com.toquete.boxbox"

    defaultConfig {
        applicationId = "com.toquete.boxbox"
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":core:common"))
    implementation(project(":core:model"))
    implementation(project(":domain:fulldriverstandings"))
    implementation(project(":domain:fullconstructorstandings"))
    implementation(project(":data:fulldriverstandings"))
    implementation(project(":data:fullconstructorstandings"))
    implementation(project(":feature:fulldriverstandings"))
    implementation(project(":feature:fullconstructorstandings"))
    implementation(project(":feature:standings"))
    implementation(libs.core.ktx)
    implementation(libs.activity.compose)
    implementation(libs.splashscreen)
}