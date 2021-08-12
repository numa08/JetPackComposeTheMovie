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
        Dependencies.AndroidX.Room.ktx,
        Dependencies.AndroidX.Paging.compose,
        Dependencies.AndroidX.Paging.runtime,
        Dependencies.AndroidX.Hilt.hilt,
    ).forEach(::implementation)
    kapt(Dependencies.AndroidX.Room.compiler)
    kapt(Dependencies.AndroidX.Hilt.hiltCompiler)
    kapt(Dependencies.AndroidX.Hilt.compiler)
}