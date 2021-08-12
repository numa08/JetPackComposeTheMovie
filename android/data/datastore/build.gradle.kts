import com.google.protobuf.gradle.*

plugins {
    id("com.android.library")
    id("dagger.hilt.android.plugin")
    kotlin("android")
    kotlin("kapt")
    id("com.google.protobuf") version "0.8.17"
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
        Dependencies.Protobuf.javaLite,
        Dependencies.AndroidX.Hilt.hilt,
        Dependencies.AndroidX.DataStore.dataStore,
    ).forEach(::implementation)
    kapt(Dependencies.AndroidX.Hilt.hiltCompiler)
    kapt(Dependencies.AndroidX.Hilt.compiler)
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
