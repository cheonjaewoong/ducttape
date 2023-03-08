import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.android.library") version "7.4.0" apply false
    id("com.android.application") version "7.4.0" apply false
    id("org.jetbrains.kotlin.android") version "1.7.20" apply false
}

allprojects {
    group = "io.woong.ducttape"
    version = "0.1.0-SNAPSHOT"

    repositories {
        google()
        mavenCentral()
    }
}

subprojects {
    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()
        kotlinOptions.freeCompilerArgs += "-Xexplicit-api=strict"
    }
}
