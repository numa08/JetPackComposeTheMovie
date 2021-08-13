plugins {
    id("com.android.library")
    id("dagger.hilt.android.plugin")
    kotlin("android")
    kotlin("kapt")
}

android {
    applyCommon(project)

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
        Dependencies.AndroidX.Hilt.worker,
        Dependencies.AndroidX.WorkManager.ktx,
        Dependencies.AndroidX.WorkManager.gcm,
        Dependencies.AndroidX.DataStore.dataStore,
        Dependencies.Protobuf.javaLite,
        project(":data:datastore"),
        project(":data:database"),
        project(":data:json")
    ).forEach(::implementation)
    kapt(Dependencies.AndroidX.Hilt.hiltCompiler)
    kapt(Dependencies.AndroidX.Hilt.compiler)
}
