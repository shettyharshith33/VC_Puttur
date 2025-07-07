buildscript {
    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.51.1")
        classpath("com.google.gms:google-services:4.3.10")
    }
}

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.google.gms.google.services)
    id("com.google.dagger.hilt.android") version "2.51.1" apply true
    id("kotlin-kapt")
}

android {
    namespace = "com.shettyharshith33.vcputtur"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.shettyharshith33.vcputtur"
        minSdk = 24
        targetSdk = 35
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
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.firebase.database)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)
    implementation(libs.firebase.firestore)

    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    implementation(libs.google.firebase.auth)
    implementation(libs.google.firebase.firestore)
    implementation(libs.google.firebase.database)
    implementation(libs.firebase.storage)
    implementation(libs.firebase.appcheck.playintegrity)

    implementation(libs.material3)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.androidx.runtime.livedata)
    implementation(libs.lottie.compose)
    implementation(libs.play.services.auth)
    implementation (libs.com.google.firebase.firebase.auth)
    implementation(libs.okhttp)
    implementation(platform(libs.firebase.bom.v3272))
    implementation (libs.firebase.dynamic.links.ktx)
    implementation (libs.accompanist.pager)

    implementation(libs.androidx.material3.v131) // or latest

    implementation(libs.compose.shimmer)
    implementation (libs.coil.compose)

    implementation (libs.androidx.datastore.preferences)
    implementation ("androidx.datastore:datastore-preferences:1.0.0")


}
kapt {
    correctErrorTypes = true
}