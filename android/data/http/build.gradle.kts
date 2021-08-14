import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    id("com.android.library")
    id("dagger.hilt.android.plugin")
    kotlin("android")
    kotlin("kapt")
    kotlin("plugin.serialization") version "1.5.20"
}

val props = Properties().apply {
    val file = project.rootProject.file("local.properties").inputStream()
    load(file)
}

val omdbApiKey = requireNotNull(props.getProperty("omdb_api_key")) {
    "should set 'omdb_api_key' in local.properties"
}

android {
    applyCommon(project)


    defaultConfig {
        buildConfigField("String", "OMDB_API_KEY", "\"$omdbApiKey\"")
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlin.RequiresOptIn"
    }
}

dependencies {
    listOf(
        Dependencies.AndroidX.Core.coreKtx,
        Dependencies.AndroidX.Hilt.hilt,
        Dependencies.Kotlin.Serialization.json,
        Dependencies.Kotlin.coroutine,
        Dependencies.Retrofit.retrofit,
        Dependencies.Retrofit.kotlinxSerialization,
        project(":data:json")
    ).forEach(::implementation)
    kapt(Dependencies.AndroidX.Hilt.hiltCompiler)
    kapt(Dependencies.AndroidX.Hilt.compiler)
}