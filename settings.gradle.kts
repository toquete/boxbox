pluginManagement {
    includeBuild("plugins")
    repositories {
        gradlePluginPortal()
        google()
        maven(url = "https://repo1.maven.org/maven2")
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        maven(url = "https://repo1.maven.org/maven2")
    }
}
rootProject.name = "BoxBox"
include(":app")
include(":core:model")
include(":core:ui")
include(":core:preferences")
include(":core:database")
include(":core:network")
include(":core:common")
include(":core:testing")
include(":data:countries")
include(":data:drivers")
include(":data:driverstandings")
include(":data:driverimages")
include(":data:constructors")
include(":data:constructorstandings")
include(":data:constructorimages")
include(":feature:standings")
include(":feature:settings")
