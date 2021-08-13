@file:Suppress("UnstableApiUsage")

include(":domain:repository")


include(":domain:data")


include(":data:json")


include(":feature:worker")


include(":data:datastore")


dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "JetPackComposeTheMovie"
listOf(
    ":app",
    ":data:database",
    ":data:datastore",
    ":data:json",
    ":feature:worker",
    ":domain:data",
    ":domain:repository"
).forEach(::include)
