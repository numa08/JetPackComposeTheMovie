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
    ":data:http",
    ":feature:worker",
    ":feature:ui",
    ":domain:data",
    ":domain:repository"
).forEach(::include)
