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
include(":data:drivers")
include(":core:common")
include(":data:driverstandings")
include(":data:fulldriverstandings")
include(":data:constructors")
include(":data:constructorstandings")
include(":data:fullconstructorstandings")
include(":domain:fulldriverstandings")
include(":domain:fullconstructorstandings")
include(":feature:standings")
