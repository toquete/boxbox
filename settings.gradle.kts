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
gradle.startParameter.excludedTaskNames.addAll(listOf(":plugins:testClasses"))
include(":app")
include(":core:model")
include(":core:ui")
include(":core:preferences")
include(":core:database")
include(":core:network")
include(":core:common")
include(":core:testing")
include(":core:alarm")
include(":core:notification")
include(":data:countries")
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
include(":data:sprintresults")
include(":domain:common")
include(":domain:races")
include(":domain:raceresults")
include(":domain:sprintresults")
include(":feature:standings")
include(":feature:settings")
include(":feature:races")
include(":feature:raceresults")
include(":feature:home")
