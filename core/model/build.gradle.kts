plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

kotlin {
    jvmToolchain(11)
}

val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
dependencies {
    add("implementation", libs.findLibrary("kotlinx.datetime").get())
}
