import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kover)
    alias(libs.plugins.ksp)
    id("boxbox.android.room")
    id("boxbox.android.koin")
}

android {
    namespace = "com.toquete.boxbox.core.database"
    compileSdk = 35

    kotlin {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    defaultConfig {
        minSdk = 24
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
