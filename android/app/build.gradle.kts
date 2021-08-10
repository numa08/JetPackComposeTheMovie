plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    kotlin("plugin.serialization") version "1.5.20"
}

android {
    setCompileSdkVersion(31)

    defaultConfig {
        applicationId = "net.numa08.jetpack_compose_the_movie"
        minSdk = 21
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        javaCompileOptions {
            annotationProcessorOptions {
                arguments["room.schemaLocation"] = "$projectDir/schemas"
            }
        }
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
    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }
    kotlinOptions {
        jvmTarget = "1.8"
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
        Dependencies.AndroidX.Room.ktx,
        Dependencies.AndroidX.WorkManager.ktx,
        Dependencies.AndroidX.WorkManager.gcm,
        Dependencies.Kotlin.Serialization.json
    ).forEach(::implementation)
    kapt(Dependencies.AndroidX.Room.compiler)
    kapt(Dependencies.AndroidX.Hilt.hiltCompiler)
    kapt(Dependencies.AndroidX.Hilt.compiler)
    debugImplementation(Dependencies.AndroidX.Compose.debugTools)
}