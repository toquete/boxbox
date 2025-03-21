plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    jvmToolchain(17)
}

val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
dependencies {
    add("implementation", libs.findLibrary("kotlinx.datetime").get())
}
