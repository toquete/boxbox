plugins {
    id("boxbox.android.library")
    id("boxbox.android.hilt")
    id("boxbox.android.room")
}

android {
    namespace = "com.toquete.boxbox.database"

    defaultConfig {
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