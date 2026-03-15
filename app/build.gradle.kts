plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    // dagger hilt
    id("org.jetbrains.kotlin.kapt")
    id ("com.google.dagger.hilt.android")

    id("kotlin-parcelize")
}


android {

    namespace = "com.ahmadmaaz1.newsy"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.ahmadmaaz1.newsy"
        minSdk = 23
        targetSdk = 35
        versionCode = 2
        versionName = "2.22030"

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

    // ✅ IMPORTANT: Make Java 17 to match Kotlin 17
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        compose = true
    }
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

kotlin {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
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
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)

    // testing
    testImplementation("org.junit.jupiter:junit-jupiter-api:6.0.3")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:6.0.3")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.10.2")

    // 🔥 REQUIRED for Gradle 9+
    testRuntimeOnly("org.junit.platform:junit-platform-launcher:6.0.3")
    // flow
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
    // turbine
    testImplementation("app.cash.turbine:turbine:1.2.1")

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    val room_version = "2.8.3"
    /// Room Db ///
    implementation("androidx.room:room-runtime:$room_version")
    kapt("androidx.room:room-compiler:$room_version")

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


    //ads
    //noinspection UseTomlInstead
    implementation("com.google.android.gms:play-services-ads:23.4.0")

    // junit 5 jupiter





}
kapt{
    correctErrorTypes = true
}