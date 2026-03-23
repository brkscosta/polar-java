plugins {
    `java-library`
}

description = "Java SDK for the Polar payment platform API — framework-agnostic"

dependencies {
    api("com.fasterxml.jackson.core:jackson-databind:2.18.3")
    api("com.fasterxml.jackson.core:jackson-annotations:2.18.3")
    api("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.18.3")
    api("org.slf4j:slf4j-api:2.0.17")

    implementation("com.cosium.standard_webhooks_consumer:standard-webhooks-consumer:1.2")

    testImplementation("org.junit.jupiter:junit-jupiter:5.11.4")
    testImplementation("org.assertj:assertj-core:3.27.3")
    testImplementation("org.mockito:mockito-core:5.15.2")
    testImplementation("org.mockito:mockito-junit-jupiter:5.15.2")
    testImplementation("org.slf4j:slf4j-simple:2.0.17")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            artifactId = "polar-java-sdk"
            pom {
                name = "Polar Java SDK"
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
