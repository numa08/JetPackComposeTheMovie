@file:Suppress("unused", "MemberVisibilityCanBePrivate")

object Dependencies {
    object Android {
        object BuildTools {
            private const val version = "7.0.0"
            const val classPath = "com.android.tools.build:gradle:$version"
        }
    }

    object AndroidX {
        object Core {
            private const val version = "1.6.0"
            const val coreKtx = "androidx.core:core-ktx:$version"
        }
        object AppCompat {
            private const val version = "1.3.1"
            const val appCompat = "androidx.appcompat:appcompat:$version"
        }
        object Material {
            const val material = "com.google.android.material:material:1.4.0"
        }
        object Compose {
            const val version = "1.0.1"
            const val ui = "androidx.compose.ui:ui:$version"
            const val foundation = "androidx.compose.foundation:foundation:$version"
            const val material = "androidx.compose.material:material:$version"
            const val materialIcons = "androidx.compose.material:material-icons-core:$version"
            const val materialIconsExtended = "androidx.compose.material:material-icons-extended:$version"
            const val livedata = "androidx.compose.runtime:runtime-livedata:$version"
            const val debugTools = "androidx.compose.ui:ui-tooling:$version"
        }
        object Hilt {
            const val classPath = "com.google.dagger:hilt-android-gradle-plugin:2.38.1"
            const val hilt = "com.google.dagger:hilt-android:2.38.1"
            const val hiltCompiler = "com.google.dagger:hilt-android-compiler:2.38.1"
            const val compose = "androidx.hilt:hilt-navigation-compose:1.0.0-alpha03"
            const val viewModel = "androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03"
            const val compiler = "androidx.hilt:hilt-compiler:1.0.0"
            const val worker = "androidx.hilt:hilt-work:1.0.0"
        }
        object Navigation {
            const val compose = "androidx.navigation:navigation-compose:2.4.0-alpha04"
        }
        object Paging {
            const val compose = "androidx.paging:paging-compose:1.0.0-alpha12"
            const val runtime = "androidx.paging:paging-runtime:3.0.0"
        }
        object Room {
            private const val version = "2.3.0"
            const val ktx = "androidx.room:room-ktx:$version"
            const val compiler = "androidx.room:room-compiler:$version"
        }
        object WorkManager {
            private const val version = "2.5.0"
            const val ktx = "androidx.work:work-runtime-ktx:$version"
            const val gcm = "androidx.work:work-gcm:$version"
        }
        object DataStore {
            private const val version = "1.0.0"
            const val dataStore = "androidx.datastore:datastore:$version"
        }
    }

    object Kotlin {
        object GradlePlugin {
            private const val version = "1.5.21"
            const val classPath = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        }
        object Serialization {
            const val json = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.2"
        }
    }

    object Protobuf {
        private const val version = "3.17.3"
        const val protobuf = "com.google.protobuf:protoc:$version"
        const val javaLite = "com.google.protobuf:protobuf-javalite:$version"
    }
}