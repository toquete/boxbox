import com.android.build.api.dsl.ApplicationExtension
import com.toquete.boxbox.plugins.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.kotlin

class AndroidApplicationConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                defaultConfig {
                    targetSdk = 33
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }
                packagingOptions {
                    resources {
                        excludes += "/META-INF/{AL2.0,LGPL2.1}"
                    }
                }
            }

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            dependencies {
                add("implementation", libs.findLibrary("work.runtime").get())

                add("testImplementation", kotlin("test"))
                add("testImplementation", libs.findLibrary("coroutines.test").get())
                add("testImplementation", libs.findLibrary("junit").get())
                add("androidTestImplementation", kotlin("test"))
                add("androidTestImplementation", libs.findLibrary("junit.ext").get())
            }
        }
    }
}