import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import com.toquete.boxbox.plugins.configureFirebase
import com.toquete.boxbox.plugins.configureFlavors
import com.toquete.boxbox.plugins.configureGradleManagedDevices
import com.toquete.boxbox.plugins.configureKotlinAndroid
import com.toquete.boxbox.plugins.disableUnnecessaryAndroidTests
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("org.jetbrains.kotlinx.kover")
                apply("com.google.gms.google-services")
                apply("com.google.firebase.crashlytics")
                apply("boxbox.android.firebase")
            }

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                configureGradleManagedDevices(this)
                configureFlavors(this)
                configureFirebase(this)
                defaultConfig {
                    targetSdk = 36
                    testInstrumentationRunner = "com.toquete.boxbox.BoxBoxTestRunner"
                }
                packaging {
                    resources {
                        excludes += listOf(
                            "/META-INF/{AL2.0,LGPL2.1}",
                            "META-INF/LICENSE.md",
                            "META-INF/LICENSE-notice.md"
                        )
                    }
                }

                extensions.configure<ApplicationAndroidComponentsExtension> {
                    disableUnnecessaryAndroidTests(target)
                }
            }
        }
    }
}
