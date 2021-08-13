plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
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
        Dependencies.AndroidX.Navigation.compose,
        Dependencies.AndroidX.Paging.compose,
        Dependencies.AndroidX.Paging.runtime,
        project(":domain:data"),
        project(":domain:repository"),
    ).forEach(::implementation)
    kapt(Dependencies.AndroidX.Hilt.hiltCompiler)
    kapt(Dependencies.AndroidX.Hilt.compiler)
    debugImplementation(Dependencies.AndroidX.Compose.debugTools)
}