import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidFirebaseConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            dependencies {
                add("implementation", platform(libs.findLibrary("firebase.bom").get()))
                add("implementation", libs.findLibrary("firebase.analytics").get())
                add("implementation", libs.findLibrary("firebase.remote.config").get())
                add("implementation", libs.findLibrary("firebase.appcheck").get())
                add("implementation", libs.findLibrary("firebase.appcheck.ktx").get())
                add("implementation", libs.findLibrary("firebase.appcheck.debug").get())
                add("implementation", libs.findLibrary("firebase.crashlytics").get())
            }
        }
    }
}
