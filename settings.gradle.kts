pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

rootProject.name = "polar-java"

include("sdk")
include("polar-spring")

project(":sdk").name = "polar-java-sdk"
project(":polar-spring").name = "polar-spring-boot-starter"
