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
    implementation(project(":data:countries"))
    implementation(project(":data:driverstandings"))
    implementation(project(":data:constructorstandings"))
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

dependencies {
    kover(project(":core:model"))
    kover(project(":core:ui"))
    kover(project(":core:preferences"))
    kover(project(":core:database"))
    kover(project(":core:network"))
    kover(project(":data:drivers"))
    kover(project(":core:common"))
    kover(project(":core:testing"))
    kover(project(":data:countries"))
    kover(project(":data:driverstandings"))
    kover(project(":data:constructors"))
    kover(project(":data:constructorstandings"))
    kover(project(":feature:standings"))
    kover(project(":feature:settings"))
}

koverReport {
    defaults {
        mergeWith("debug")
    }
    filters {
        excludes {
            classes(
                "*Activity",
                "*Activity\$*",
                "*.BuildConfig",
                "*Hilt*",
                "*Factory*",
                "*Injector",
                "*Database*",
                "*Dao*",
                "*Module*",
                "*Application",
                "*Worker*",
                "*Composable*"
            )
            packages(
                "*.di",
                "com.toquete.boxbox.util",
                "com.toquete.boxbox.core.ui.theme"
            )
            annotatedBy(
                "*Composable",
                "*Preview",
                "*Stable",
                "*Database",
                "*Module",
                "*Generated"
            )
        }
    }
}
