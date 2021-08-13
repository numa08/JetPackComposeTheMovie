import com.google.protobuf.gradle.*

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("com.google.protobuf") version "0.8.17"
    kotlin("plugin.serialization") version "1.5.20"
}

android {
    applyCommon(project)
    defaultConfig {
        applicationId = "net.numa08.jetpack_compose_the_movie"
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Dependencies.AndroidX.Compose.version
    }
}

dependencies {
    listOf(
        Dependencies.AndroidX.Core.coreKtx,
        Dependencies.AndroidX.Material.material,
        Dependencies.AndroidX.AppCompat.appCompat,
        Dependencies.AndroidX.Compose.ui,
        Dependencies.AndroidX.Compose.foundation,
        Dependencies.AndroidX.Compose.material,
        Dependencies.AndroidX.Compose.materialIcons,
        Dependencies.AndroidX.Compose.materialIconsExtended,
        Dependencies.AndroidX.Compose.livedata,
        Dependencies.AndroidX.Hilt.compose,
        Dependencies.AndroidX.Hilt.hilt,
        Dependencies.AndroidX.Hilt.viewModel,
        Dependencies.AndroidX.Hilt.worker,
        Dependencies.AndroidX.Navigation.compose,
        Dependencies.AndroidX.Paging.compose,
        Dependencies.AndroidX.Paging.runtime,
        Dependencies.AndroidX.Room.ktx,
        Dependencies.AndroidX.WorkManager.ktx,
        Dependencies.AndroidX.WorkManager.gcm,
        Dependencies.AndroidX.DataStore.dataStore,
        Dependencies.Kotlin.Serialization.json,
        Dependencies.Protobuf.javaLite,
        project(":data:database"),
        project(":data:datastore"),
        project(":data:json"),
        project("feature:worker"),
    ).forEach(::implementation)
    kapt(Dependencies.AndroidX.Room.compiler)
    kapt(Dependencies.AndroidX.Hilt.hiltCompiler)
    kapt(Dependencies.AndroidX.Hilt.compiler)
    debugImplementation(Dependencies.AndroidX.Compose.debugTools)
}

protobuf {
    protoc {
        artifact = Dependencies.Protobuf.protobuf
    }
    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                id("java") {
                    options.add("lite")
                }
            }
        }
    }
}