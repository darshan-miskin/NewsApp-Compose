import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
//    alias(libs.plugins.devtools.ksp)
    alias(libs.plugins.legacy.kapt)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.compose.compiler)
}

val secretPropertiesFile = rootProject.file("secrets.properties")
val secretProperties = Properties()
secretProperties.load(FileInputStream(secretPropertiesFile))

val apiKey = secretProperties["api_key"]
val baseUrl = secretProperties["base_url"]


android {
    namespace = "com.darshanmiskin.newsapp"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.darshanmiskin.newsapp"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        defaultConfig {
            buildConfigField("String", "API_KEY", "\"$apiKey\"")
            buildConfigField("String", "BASE_URL", "\"$baseUrl\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures{
        buildConfig = true
        viewBinding = true
        dataBinding = true
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    implementation(libs.glide)
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.browser)
    implementation(libs.hilt.android)
    implementation(libs.androidx.runtime)
    kapt(libs.hilt.android.compiler)


    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)
    val composeBom = platform(libs.androidx.compose.bom)
    implementation(composeBom)
    androidTestImplementation(composeBom)
    implementation(libs.androidx.constraintlayout.compose)
    // Choose one of the following:
    // Material Design 3
    implementation(libs.androidx.material3)

//    // Optional - Add window size utils
//    implementation(libs.androidx.adaptive)
    // Optional - Integration with activities
    implementation(libs.androidx.activity.compose)
//    // Optional - Integration with ViewModels
//    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.ui.tooling.preview)
    debugImplementation(libs.androidx.ui.tooling)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}