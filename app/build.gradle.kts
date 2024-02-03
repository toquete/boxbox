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

val buildPropertiesFile = rootProject.file("build.properties")
val buildProperties = Properties().apply {
    if (buildPropertiesFile.exists()) {
        load(FileInputStream(buildPropertiesFile))
    }
}

android {
    namespace = "com.toquete.boxbox"

    defaultConfig {
        applicationId = "com.toquete.boxbox"
        versionCode = 6
        versionName = "1.1.1"

        buildConfigField(
            "String",
            "APP_CHECK_DEBUG_TOKEN",
            "\"${buildProperties.getProperty("APP_CHECK_DEBUG_TOKEN")}\""
        )
    }

    signingConfigs {
        getByName("debug") {
            storeFile = file("debug.keystore")
        }
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

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":core:common"))
    implementation(project(":core:model"))
    implementation(project(":core:preferences"))
    implementation(project(":data:driverimages"))
    implementation(project(":data:constructorimages"))
    implementation(project(":data:circuitimages"))
    implementation(project(":data:countries"))
    implementation(project(":data:driverstandings"))
    implementation(project(":data:constructorstandings"))
    implementation(project(":data:constructorcolors"))
    implementation(project(":data:races"))
    implementation(project(":feature:standings"))
    implementation(project(":feature:settings"))
    implementation(project(":feature:races"))
    implementation(libs.core.ktx)
    implementation(libs.activity.compose)
    implementation(libs.splashscreen)
    implementation(libs.work.runtime)
    implementation(libs.firebase.appcheck)
    implementation(libs.firebase.appcheck.ktx)
    implementation(libs.firebase.appcheck.debug)
    implementation(libs.hilt.work)
    ksp(libs.hilt.work.compiler)

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
    kover(project(":data:constructorcolors"))
    kover(project(":data:races"))
    kover(project(":data:circuits"))
    kover(project(":data:circuitimages"))
    kover(project(":domain:common"))
    kover(project(":domain:races"))
    kover(project(":feature:standings"))
    kover(project(":feature:settings"))
    kover(project(":feature:races"))
}

koverReport {
    defaults {
        mergeWith("prodDebug")
        html {
            title = "BoxBox"
            onCheck = false
            setReportDir(layout.buildDirectory.dir("reports/coverage"))
        }
        xml {
            onCheck = false
            setReportFile(layout.buildDirectory.file("reports/coverage/coverage.xml"))
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
                "*Composable*",
                "*Destination*"
            )
            packages(
                "*.di",
                "com.toquete.boxbox.util",
                "com.toquete.boxbox.core.ui.theme",
                "com.toquete.boxbox.core.ui.custom",
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
