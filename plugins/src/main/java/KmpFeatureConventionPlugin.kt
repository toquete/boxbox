import com.toquete.boxbox.plugins.configureKotlinMultiplatform
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KmpFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.multiplatform")
                apply("com.android.library")
                apply("org.jetbrains.kotlinx.kover")
            }

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

            extensions.configure<KotlinMultiplatformExtension> {
                configureKotlinMultiplatform(this)
                sourceSets.apply {
                    commonMain.dependencies {
                        implementation(libs.findLibrary("coil.compose").get())
                    }
                    androidMain.dependencies {
                        implementation(libs.findLibrary("core.ktx").get())
                    }
                }
            }
        }
    }
}
