plugins {
    id("boxbox.kotlin.multiplatform")
}

kotlin {
    android {
        namespace = "com.toquete.boxbox.core.testing"

        // core:database and core:network still have prod/demo flavors; pick prod.
        // Remove after Tasks 21-22 migrate them to KMP.
        localDependencySelection {
            productFlavorDimension("version") {
                selectFrom.set(listOf("prod"))
            }
        }
    }

    sourceSets {
        androidMain.dependencies {
            api(project(":core:model"))
            api(project(":core:database"))
            api(project(":core:network"))
            api(libs.junit)
            api(libs.coroutines.test)
            api(libs.mockk)
            api(libs.napier)
            api(libs.junit.ext)
            api(kotlin("test"))
            api(libs.test.core)
            api(libs.test.runner)
            api(libs.test.rules)
            api(libs.junit.compose)
            api(libs.espresso)
        }
    }
}

// Compose BOM must be at project level for KMP modules.
// ui-test-manifest included unconditionally (it's a test helper; the KMP library plugin has no debugApi equivalent).
dependencies {
    add("androidMainApi", platform(libs.compose.bom))
    add("androidMainApi", libs.compose.ui.manifest)
}
