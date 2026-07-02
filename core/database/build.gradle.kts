plugins {
    id("boxbox.android.library")
    id("boxbox.android.room")
}

android {
    namespace = "com.toquete.boxbox.core.database"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        ksp {
            arg(RoomSchemaArgProvider(File(projectDir, "schemas")))
        }
    }

    sourceSets {
        getByName("test").assets.srcDir("$projectDir/schemas")
    }
}

dependencies {
    implementation(project(":core:model"))
    testImplementation(project(":core:testing"))
    testImplementation(libs.robolectric)

    implementation(libs.koin.android)
}

class RoomSchemaArgProvider(
    @get:InputDirectory
    @get:PathSensitive(PathSensitivity.RELATIVE)
    val schemaDir: File
) : CommandLineArgumentProvider {

    override fun asArguments(): Iterable<String> {
        return listOf("room.schemaLocation=${schemaDir.path}")
    }
}
