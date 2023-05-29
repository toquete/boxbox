dependencyResolutionManagement {
    repositories {
        google()
        maven(url = "https://repo1.maven.org/maven2")
    }
    versionCatalogs {
        create("libs") {
            from(files("../gradle/libs.versions.toml"))
        }
    }
}

rootProject.name = "plugins"
