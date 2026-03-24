plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.crashlytics.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "boxbox.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidLibrary") {
            id = "boxbox.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidHilt") {
            id = "boxbox.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }
        register("androidApplicationCompose") {
            id = "boxbox.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "boxbox.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("androidFeature") {
            id = "boxbox.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }
        register("androidRemoteData") {
            id = "boxbox.android.remote"
            implementationClass = "AndroidRemoteDataConventionPlugin"
        }
        register("androidRoom") {
            id = "boxbox.android.room"
            implementationClass = "AndroidRoomConventionPlugin"
        }
        register("androidFirebase") {
            id = "boxbox.android.firebase"
            implementationClass = "AndroidFirebaseConventionPlugin"
        }
        register("kmpMultiplatform") {
            id = "boxbox.kotlin.multiplatform"
            implementationClass = "KmpMultiplatformConventionPlugin"
        }
        register("kmpFeature") {
            id = "boxbox.kmp.feature"
            implementationClass = "KmpFeatureConventionPlugin"
        }
        register("kmpRoom") {
            id = "boxbox.kmp.room"
            implementationClass = "KmpRoomConventionPlugin"
        }
        register("kmpRemote") {
            id = "boxbox.kmp.remote"
            implementationClass = "KmpRemoteConventionPlugin"
        }
    }
}
