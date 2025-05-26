
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.example.weatherapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.weatherapp"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        vectorDrawables {
            useSupportLibrary = true
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    // AndroidX
    implementation(libs.bundles.androidX)

    // Compose
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.ui)
    debugImplementation(libs.compose.tooling)

    implementation(libs.androidx.material3)

    // Retrofit & Gson
    implementation (libs.retrofit)
    implementation (libs.converter.gson)

    //Hilt
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.dagger.hilt)
    ksp(libs.dagger.hilt.android.compiler)
    ksp(libs.dagger.hilt.compiler)

    // Room
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)

    // Kotlin Coroutines
    implementation (libs.kotlinx.coroutines.android)

    // Location
    implementation (libs.play.services.location)

    // DataStore
    implementation (libs.androidx.datastore.preferences)

    // ViewModel + LiveData
    implementation (libs.androidx.lifecycle.livedata.ktx)
    implementation (libs.androidx.lifecycle.viewmodel.ktx)
}