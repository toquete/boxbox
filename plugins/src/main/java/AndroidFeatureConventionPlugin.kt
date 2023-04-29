import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.kotlin

class AndroidFeatureConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("boxbox.android.library")
                apply("boxbox.android.hilt")
            }

            extensions.configure<LibraryExtension> {
                defaultConfig {
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }
            }

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            dependencies {
                add("implementation", libs.findLibrary("core.ktx").get())
                add("implementation", libs.findBundle("compose").get())
                add("implementation", libs.findBundle("coil").get())

                add("testImplementation", kotlin("test"))
                add("testImplementation", libs.findLibrary("coroutines.test").get())
                add("testImplementation", libs.findLibrary("junit").get())

                add("androidTestImplementation", kotlin("test"))
                add("androidTestImplementation", libs.findLibrary("junit.ext").get())
                add("androidTestImplementation", libs.findLibrary("junit.compose").get())

                add("debugImplementation", libs.findLibrary("compose.ui.tooling").get())
                add("debugImplementation", libs.findLibrary("compose.ui.manifest").get())
            }
        }
    }
}
