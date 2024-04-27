import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services") // Ensure this is the latest version
   // id ("kotlin-kapt")
    //id ("com.google.dagger.hilt.android") version "2.44"
}

android {
    namespace = "com.example.cricstat"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.cricstat"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
        languageVersion = "1.9"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")

    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    val nav_version = "2.7.7"
    implementation("androidx.navigation:navigation-compose:$nav_version")

    //firebase
    implementation(platform("com.google.firebase:firebase-bom:32.8.0"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-auth:22.3.1")
    implementation("com.google.android.gms:play-services-auth:21.0.0")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))

    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")


    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
//   implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.0-alpha04")
    implementation ("androidx.lifecycle:lifecycle-runtime-compose:2.7.0")
    implementation ("androidx.compose.runtime:runtime-livedata:1.6.5")

    implementation ("io.coil-kt:coil-compose:2.6.0")

    //retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:okhttp:4.12.0")

    //hilt
    //implementation ("com.google.dagger:hilt-android:2.49")
    //kapt ("com.google.dagger:hilt-android-compiler:2.44")
    //implementation ("androidx.hilt:hilt-navigation-compose:1.2.0")

    //implementation ("com.example:retrofit-retry:1.0.0")
    implementation("com.exyte:animated-navigation-bar:1.0.0")

    implementation ("com.github.bumptech.glide:glide:4.12.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.12.0")
    implementation ("com.github.bumptech.glide:compose:1.0.0-beta01")


    implementation ("org.jsoup:jsoup:1.17.2")
}

/*
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    languageVersion = "1.9"
}*/
