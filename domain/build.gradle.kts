plugins {
    id("boxbox.kotlin.multiplatform")
}

kotlin {
    android {
        namespace = "com.toquete.boxbox.domain"

        // core:testing still has prod/demo flavors; select prod variant for host tests.
        // Remove after Task 20 migrates core:testing to KMP (no more flavors).
        localDependencySelection {
            productFlavorDimension("version") {
                selectFrom.set(listOf("prod"))
            }
        }
    }

    sourceSets {
        commonMain.dependencies {
            api(project(":core:model"))
            api(project(":core:common"))
        }
        // core:testing is still Android-only; move to commonTest after Task 20 migrates it to KMP
        named("androidHostTest").dependencies {
            implementation(project(":core:testing"))
        }
    }
}
