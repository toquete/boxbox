import com.toquete.boxbox.plugins.configureKotlinMultiplatform
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KmpMultiplatformConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.multiplatform")
                apply("com.android.library")
                apply("org.jetbrains.kotlinx.kover")
            }
            extensions.configure<KotlinMultiplatformExtension> {
                configureKotlinMultiplatform(this)
            }
        }
    }
}
