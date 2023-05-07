plugins {
    id("boxbox.android.library")
    id("boxbox.android.hilt")
    id("boxbox.android.room")
}

android {
    namespace = "com.toquete.boxbox.core.database"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        javaCompileOptions {
            annotationProcessorOptions {
                compilerArgumentProviders(
                    RoomSchemaArgProvider(File(projectDir, "schemas"))
                )
            }
        }
    }
}

dependencies {
    implementation(project(":core:model"))
    androidTestImplementation(project(":core:testing"))
}

class RoomSchemaArgProvider(
    @get:InputDirectory
    @get:PathSensitive(PathSensitivity.RELATIVE)
    val schemaDir: File
) : CommandLineArgumentProvider {

    override fun asArguments(): Iterable<String> {
        return listOf("-Aroom.schemaLocation=${schemaDir.path}")
    }
}