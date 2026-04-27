plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    // enable ksp

    id("com.google.devtools.ksp")
    // Add the dependency for the Google services Gradle plugin
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.finance_app"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.finance_app"
        minSdk = 24
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

    // compose material icons extra
    implementation("androidx.compose.material:material-icons-extended")



    // navigation component
    val navVersion = "2.9.8"
    implementation("androidx.navigation:navigation-compose:$navVersion")


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)


    val roomVersion = "2.7.2"
    implementation("androidx.room:room-runtime:$roomVersion")
// use Kotlin Symbol Processing (KSP)
    ksp("androidx.room:room-compiler:$roomVersion")
// optional - Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:$roomVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.10.1")


    // Import the Firebase BoM
    implementation(platform("com.google.firebase:firebase-bom:34.12.0"))


    // When using the BoM, don't specify versions in Firebase dependencies
    implementation("com.google.firebase:firebase-analytics")

    // Add the dependency for the Firebase AI Logic library When using the BoM,
    // you don't specify versions in Firebase library dependencies
    implementation("com.google.firebase:firebase-ai")

    // Add the dependencies for any other desired Firebase products
    // https://firebase.google.com/docs/android/setup#available-libraries


}