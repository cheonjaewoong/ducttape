pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "ducttape"

include(
    ":ducttape-core",
    ":ducttape-viewmodel"
)
