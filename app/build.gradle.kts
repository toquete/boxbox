import com.toquete.boxbox.plugins.BoxBoxBuildType

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
        release {
            applicationIdSuffix = BoxBoxBuildType.RELEASE.applicationIdSuffix
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        create("minified") {
            initWith(getByName("debug"))
            applicationIdSuffix = BoxBoxBuildType.MINIFIED.applicationIdSuffix
            versionNameSuffix = BoxBoxBuildType.MINIFIED.versionNameSuffix
            matchingFallbacks.add("debug")
            isMinifyEnabled = true
            enableUnitTestCoverage = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        debug {
            applicationIdSuffix = BoxBoxBuildType.DEBUG.applicationIdSuffix
            versionNameSuffix = BoxBoxBuildType.DEBUG.versionNameSuffix
        }
    }
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":core:common"))
    implementation(project(":core:model"))
    implementation(project(":core:preferences"))
    implementation(project(":data:driverstandings"))
    implementation(project(":data:constructorstandings"))
    implementation(project(":data:fullconstructorstandings"))
    implementation(project(":feature:standings"))
    implementation(project(":feature:settings"))
    implementation(libs.core.ktx)
    implementation(libs.activity.compose)
    implementation(libs.splashscreen)
    implementation(libs.work.runtime)
    implementation(libs.hilt.work)
    kapt(libs.hilt.work.compiler)

    testImplementation(project(":core:testing"))
}
