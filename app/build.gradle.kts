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

val projectVersion: String by rootProject.extra

android {
    namespace = "com.toquete.boxbox"

    defaultConfig {
        applicationId = "com.toquete.boxbox"
        versionCode = 19
        versionName = projectVersion

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

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }

    lint {
        checkDependencies = true
        xmlReport = true
        xmlOutput = file("${rootProject.rootDir}/build/reports/lint-results.xml")
        htmlReport = true
        htmlOutput = file("${rootProject.rootDir}/build/reports/lint-results.html")
    }
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":core:preferences"))
    implementation(project(":data:driverimages"))
    implementation(project(":data:constructorimages"))
    implementation(project(":data:circuitimages"))
    implementation(project(":data:countries"))
    implementation(project(":data:driverstandings"))
    implementation(project(":data:constructorstandings"))
    implementation(project(":data:constructorcolors"))
    implementation(project(":data:races"))
    implementation(project(":data:raceresults"))
    implementation(project(":data:sprintresults"))
    implementation(project(":feature:standings"))
    implementation(project(":feature:settings"))
    implementation(project(":feature:races"))
    implementation(project(":feature:raceresults"))
    implementation(project(":feature:home"))
    implementation(project(":domain"))
    implementation(libs.core.ktx)
    implementation(libs.activity.compose)
    implementation(libs.splashscreen)
    implementation(libs.work.runtime)
    implementation(libs.coil)
    implementation(libs.hilt.work)
    ksp(libs.hilt.work.compiler)
    implementation(libs.play.services.ads)

    testImplementation(project(":core:testing"))
    testImplementation(libs.robolectric)
    testImplementation(libs.work.testing)
    androidTestImplementation(project(":core:testing"))
    androidTestImplementation(libs.compose.navigation.test)
    androidTestImplementation(libs.hilt.android.testing)
    kspAndroidTest(libs.hilt.android.compiler)
}

kover {
    merge {
        projects(
            ":core:model",
            ":core:ui",
            ":core:preferences",
            ":core:database",
            ":core:network",
            ":core:common",
            ":core:testing",
            ":data:countries",
            ":data:driverstandings",
            ":data:driverimages",
            ":data:constructorstandings",
            ":data:constructorimages",
            ":data:constructorcolors",
            ":data:races",
            ":data:circuitimages",
            ":data:raceresults",
            ":data:sprintresults",
            ":feature:standings",
            ":feature:settings",
            ":feature:races",
            ":feature:raceresults",
            ":feature:home",
            ":domain"
        )
    }
    reports {
        variant("prodDebug") {
            filters {
                excludes {
                    androidGeneratedClasses()
                    annotatedBy(
                        "*Composable",
                        "*Preview",
                        "*Stable",
                        "*Module",
                        "*Generated"
                    )
                    classes(
                        "*Hilt*",
                        "*Factory*",
                        "*Injector",
                        "*Module*",
                        "*Application",
                        "*Composable*",
                        "*Navigation*",
                        "*FirebaseDatabase*",
                        "*MainActivity*"
                    )
                    packages(
                        "*.di",
                        "com.toquete.boxbox.util",
                        "com.toquete.boxbox.core.ui.theme"
                    )
                }
            }
            html {
                title = "BoxBox"
                onCheck = false
                htmlDir = layout.buildDirectory.dir("reports/coverage")
            }
            xml {
                onCheck = false
                xmlFile = layout.buildDirectory.file("reports/coverage/coverage.xml")
            }
        }
    }
}
