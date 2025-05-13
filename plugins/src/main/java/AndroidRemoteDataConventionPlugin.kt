import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.kotlin

class AndroidRemoteDataConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("org.jetbrains.kotlin.plugin.serialization")

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            dependencies {
                add("implementation", libs.findBundle("ktor").get())
                add("testImplementation", libs.findLibrary("junit").get())
                add("testImplementation", kotlin("test"))
                add("testImplementation", libs.findLibrary("coroutines.test").get())
            }
        }
    }
}
