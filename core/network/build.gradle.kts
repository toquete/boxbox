plugins {
    alias(libs.plugins.boxbox.kotlin.multiplatform)
    alias(libs.plugins.boxbox.kmp.remote)
}

kotlin {
    android {
        namespace = "com.toquete.boxbox.core.network"
    }

    sourceSets {
        commonMain.dependencies {
            api(project(":core:model"))
            api(project(":core:common"))
            implementation(libs.koin.core)
        }
        androidMain.dependencies {
            implementation(libs.firebase.firestore)
            // TODO Step 12: remove — BoxBoxService (Retrofit) kept for Hilt shim in :app
            implementation(libs.retrofit)
            implementation(libs.serialization.converter)
        }
        commonTest.dependencies {
            implementation(libs.ktor.client.mock)
            implementation(libs.coroutines.test)
            implementation(kotlin("test"))
        }
    }
}

// Firebase BOM must be at project level for KMP modules.
dependencies {
    add("androidMainImplementation", platform(libs.firebase.bom))
}
