plugins {
    `java-library`
}

description = "Spring Boot auto-configuration for Polar Java SDK"

val springBootVersion = "3.4.4"

dependencies {
    api(project(":polar-java-sdk"))
    api("org.springframework.boot:spring-boot-autoconfigure:$springBootVersion")

    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor:$springBootVersion")

    testImplementation("org.springframework.boot:spring-boot-starter-test:$springBootVersion")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            artifactId = "polar-spring"
            pom {
                name = "Polar Spring"
                description = project.description
                url = "https://github.com/brkscosta/polar-java"
                licenses {
                    license {
                        name = "MIT License"
                        url = "https://opensource.org/licenses/MIT"
                    }
                }
                developers {
                    developer {
                        id = "ojsandev"
                        name = "Joanã Costa"
                        email = "joanacosta97@hotmail.com"
                        url = "https://github.com/brkscosta"
                    }
                }
                scm {
                    connection = "scm:git:git://github.com/brkscosta/polar-java.git"
                    developerConnection = "scm:git:ssh://github.com/brkscosta/polar-java.git"
                    url = "https://github.com/brkscosta/polar-java"
                }
            }
        }
    }
}
