import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project

fun BaseExtension.applyCommon(project: Project, resourcePrefix: String? = null) {
    resourcePrefix?.let {
        resourcePrefix("${it}_")
    }
    compileSdkVersion(Versions.AndroidSdk.compile)
    defaultConfig {
        minSdk = Versions.AndroidSdk.min
        targetSdk = Versions.AndroidSdk.target
        versionCode = Versions.Application.code
        versionName = Versions.Application.name
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        javaCompileOptions {
            annotationProcessorOptions {
                arguments["room.schemaLocation"] = "${project.projectDir}/schemas"
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}