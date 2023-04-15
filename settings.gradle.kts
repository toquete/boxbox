pluginManagement {
    includeBuild("plugins")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "BoxBox"
include(":app")
include(":core:model")
include(":core:ui")
include(":core:preferences")
include(":core:database")
include(":core:network")
include(":data:driverstandings")
include(":domain:driverstandings")
include(":feature:driverstandings")
