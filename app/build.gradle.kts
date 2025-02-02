plugins {
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.stocks"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.stocks"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions {
            freeCompilerArgs += "-opt-in=kotlin.RequiresOptIn"
            freeCompilerArgs += "-opt-in=kotlin.Experimental"
        }
    }

    testOptions.unitTests {
        isIncludeAndroidResources = true
        all { test ->
            with(test) {
                testLogging {
                    events = setOf(
                        org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED,
                        org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED,
                        org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED,
                        org.gradle.api.tasks.testing.logging.TestLogEvent.STANDARD_OUT,
                        org.gradle.api.tasks.testing.logging.TestLogEvent.STANDARD_ERROR,
                    )
                }
            }
        }
    }


}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.junit.ktx)
    testImplementation(libs.junit)
    testImplementation(libs.hilt.android.testing)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
    testImplementation("io.mockk:mockk:1.13.8")

    // Hilt Testing
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.51.1")
    kaptAndroidTest("com.google.dagger:hilt-compiler:2.51.1")


    testImplementation("androidx.arch.core:core-testing:2.2.0")

    kapt("androidx.room:room-compiler:2.6.1")

    implementation("androidx.room:room-runtime:2.6.1")

    kaptTest("com.google.dagger:hilt-android-compiler:2.51.1")



    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")

    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-android-compiler:2.48")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")
    implementation ("androidx.room:room-ktx:2.6.1")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.9.10")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0")
    implementation("com.github.bumptech.glide:glide:4.16.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.11.0")
    implementation("io.coil-kt:coil:2.7.0")
    implementation("androidx.security:security-crypto:1.1.0-alpha06")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.2.0-alpha01")

}