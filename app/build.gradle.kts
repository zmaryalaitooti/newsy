plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    // dagger hilt
    id("org.jetbrains.kotlin.kapt")
    id ("com.google.dagger.hilt.android")
}

apply<HelloWordPlugin>()
class HelloWordPlugin : Plugin<Project>{
    override fun apply (target:Project){
        println("Hello word in plugin ")
    }
}

android {
    namespace = "com.ahmadmaaz1.newsy"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.ahmadmaaz1.newsy"
        minSdk = 21
        targetSdk = 35
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
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // dagger hilt setup
    implementation ("com.google.dagger:hilt-android:2.56.2")
    kapt("com.google.dagger:hilt-compiler:2.56.2")
    kapt("com.google.dagger:hilt-android-compiler:2.56.2")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
///network library
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    // coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")

    // splash creen
    implementation("androidx.core:core-splashscreen:1.0.0")
    // coilImage
    implementation("io.coil-kt:coil-compose:2.4.0")
    //dataStore preferences
    implementation("androidx.datastore:datastore-preferences:1.1.6")
    // Accompanist
    implementation ("com.google.accompanist:accompanist-systemuicontroller:0.35.0-alpha")

    /// PAGING ////
    implementation( "androidx.paging:paging-runtime:3.3.6")
    implementation( "androidx.paging:paging-compose:3.2.1")




}
kapt{
    correctErrorTypes = true
}