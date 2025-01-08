plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.bintest"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.bintest"
        minSdk = 24
        targetSdk = 34
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
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    // Для ViewModel
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.0")

// Для корутин
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    val retrofit_version = "2.9.0" // Убедитесь, что это последняя версия

    // Основная библиотека Retrofit
    implementation ("com.squareup.retrofit2:retrofit:$retrofit_version")

    // Конвертер JSON (Moshi, Gson или другой)
    implementation ("com.squareup.retrofit2:converter-gson:$retrofit_version")

    // Опционально: поддержка Coroutines
    implementation ("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2") // Для Coroutines

    val room_version="2.6.1"

    // Основная библиотека Room
    implementation ("androidx.room:room-runtime:$room_version")

    // Компилятор Room для KSP
    ksp ("androidx.room:room-compiler:$room_version")

    // Опционально: поддержка Kotlin Coroutines
    implementation ("androidx.room:room-ktx:$room_version")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}