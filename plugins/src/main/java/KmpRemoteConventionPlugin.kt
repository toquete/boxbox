import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KmpRemoteConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("org.jetbrains.kotlin.plugin.serialization")

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

            extensions.configure<KotlinMultiplatformExtension> {
                sourceSets.apply {
                    commonMain.dependencies {
                        implementation(libs.findLibrary("ktor.client.core").get())
                        implementation(libs.findLibrary("ktor.client.content.negotiation").get())
                        implementation(libs.findLibrary("ktor.serialization.kotlinx.json").get())
                        implementation(libs.findLibrary("ktor.client.logging").get())
                        implementation(libs.findLibrary("serialization").get())
                    }
                    androidMain.dependencies {
                        implementation(libs.findLibrary("ktor.client.okhttp").get())
                    }
                    // iosMain is created automatically by the default hierarchy template
                    iosMain.get().dependencies {
                        implementation(libs.findLibrary("ktor.client.darwin").get())
                    }
                    commonTest.dependencies {
                        implementation(libs.findLibrary("ktor.client.mock").get())
                    }
                }
            }
        }
    }
}
