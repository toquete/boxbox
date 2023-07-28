import com.toquete.boxbox.plugins.BoxBoxBuildType
import java.io.FileInputStream
import java.util.Properties

plugins {
    id("boxbox.android.application")
    id("boxbox.android.application.compose")
    id("boxbox.android.hilt")
}

val keystorePropertiesFile = rootProject.file("keystore.properties")
val keystoreProperties = Properties().apply {
    if (keystorePropertiesFile.exists()) {
        load(FileInputStream(keystorePropertiesFile))
    }
}

android {
    namespace = "com.toquete.boxbox"

    defaultConfig {
        applicationId = "com.toquete.boxbox"
        versionCode = 3
        versionName = "1.0.0"
    }

    signingConfigs {
        create("release") {
            storeFile = keystoreProperties.getProperty("storeFile")?.let { file(it) }
            storePassword = keystoreProperties.getProperty("storePassword")
            keyAlias = keystoreProperties.getProperty("keyAlias")
            keyPassword = keystoreProperties.getProperty("keyPassword")
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isDebuggable = false
            signingConfig = signingConfigs.getByName("release")
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
            isMinifyEnabled = false
            isDebuggable = true
        }
    }
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":core:common"))
    implementation(project(":core:model"))
    implementation(project(":core:preferences"))
    implementation(project(":data:driverimages"))
    implementation(project(":data:constructorimages"))
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
    kover(project(":data:driverimages"))
    kover(project(":data:constructors"))
    kover(project(":data:constructorstandings"))
    kover(project(":data:constructorimages"))
    kover(project(":data:races"))
    kover(project(":feature:standings"))
    kover(project(":feature:settings"))
}

koverReport {
    defaults {
        mergeWith("prodDebug")
        html {
            title = "BoxBox"
            onCheck = false
            setReportDir(layout.buildDirectory.dir("reports/coverage"))
        }
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
