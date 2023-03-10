plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "${project.group}.viewmodel"
    compileSdk = 32

    defaultConfig {
        minSdk = 21
        targetSdk = 32
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {
    api(project(":ducttape-core"))
    api("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")
    api("androidx.lifecycle:lifecycle-viewmodel-savedstate:2.5.1")

    androidTestImplementation("androidx.test.ext:junit-ktx:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.activity:activity-ktx:1.6.1")
    androidTestImplementation("androidx.fragment:fragment-ktx:1.5.5")
}
