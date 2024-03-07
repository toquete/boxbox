pluginManagement {
    includeBuild("plugins")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
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
include(":core:common")
include(":core:testing")
include(":data:countries")
include(":data:drivers")
include(":data:driverstandings")
include(":data:driverimages")
include(":data:constructors")
include(":data:constructorstandings")
include(":data:constructorimages")
include(":data:constructorcolors")
include(":data:races")
include(":data:circuits")
include(":data:circuitimages")
include(":data:raceresults")
include(":domain:common")
include(":domain:races")
include(":domain:raceresults")
include(":feature:standings")
include(":feature:settings")
include(":feature:races")
include(":feature:raceresults")
