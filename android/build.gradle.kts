buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Dependencies.Android.BuildTools.classPath)
        classpath(Dependencies.Kotlin.GradlePlugin.classPath)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.21")
    }
}

tasks.register("clean") {
    delete(rootProject.buildDir)
}
