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
include(":data:fulldriverstandings")
include(":data:constructorstandings")
include(":domain:fulldriverstandings")
include(":domain:constructorstandings")
include(":feature:fulldriverstandings")
include(":feature:constructorstandings")
