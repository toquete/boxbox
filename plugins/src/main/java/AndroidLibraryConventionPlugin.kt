import com.android.build.api.variant.LibraryAndroidComponentsExtension
import com.android.build.gradle.LibraryExtension
import com.toquete.boxbox.plugins.configureGradleManagedDevices
import com.toquete.boxbox.plugins.configureKotlinAndroid
import com.toquete.boxbox.plugins.disableUnnecessaryAndroidTests
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibraryConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
                apply("boxbox.android.firebase")
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                configureGradleManagedDevices(this)
                defaultConfig.targetSdk = 33
                buildTypes {
                    debug {
                        enableUnitTestCoverage = true
                    }
                }
                packagingOptions {
                    resources {
                        excludes += listOf(
                            "/META-INF/{AL2.0,LGPL2.1}",
                            "META-INF/LICENSE.md",
                            "META-INF/LICENSE-notice.md"
                        )
                    }
                }
            }

            extensions.configure<LibraryAndroidComponentsExtension> {
                disableUnnecessaryAndroidTests(target)
            }
        }
    }
}
