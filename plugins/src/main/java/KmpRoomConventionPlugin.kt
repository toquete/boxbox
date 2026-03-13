import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KmpRoomConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.google.devtools.ksp")

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

            extensions.configure<KotlinMultiplatformExtension> {
                sourceSets.commonMain.dependencies {
                    implementation(libs.findLibrary("room.runtime").get())
                    implementation(libs.findLibrary("room.ktx").get())
                }
                sourceSets.getByName("androidUnitTest").dependencies {
                    implementation(libs.findLibrary("room.testing").get())
                }
            }

            dependencies {
                add("kspAndroid", libs.findLibrary("room.compiler").get())
                add("kspIosArm64", libs.findLibrary("room.compiler").get())
                add("kspIosSimulatorArm64", libs.findLibrary("room.compiler").get())
                add("kspIosX64", libs.findLibrary("room.compiler").get())
            }
        }
    }
}
