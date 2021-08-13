@file:Suppress("UnstableApiUsage")
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
    ":feature:ui",
    ":domain:data",
    ":domain:repository"
).forEach(::include)
