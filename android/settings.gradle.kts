@file:Suppress("UnstableApiUsage")

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
).forEach(::include)
