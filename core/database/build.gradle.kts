plugins {
    id("boxbox.android.library")
    id("boxbox.android.hilt")
    id("boxbox.android.room")
    id("boxbox.android.koin")
}

android {
    namespace = "com.toquete.boxbox.core.database"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    sourceSets {
        getByName("test").assets.srcDir("$projectDir/schemas")
    }
    ksp {
        arg(RoomSchemaArgProvider(File(projectDir, "schemas")))
    }
}

dependencies {
    implementation(project(":core:model"))
    testImplementation(project(":core:testing"))
    testImplementation(libs.robolectric)
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
