import java.util.Base64

plugins {
    `java-library`
    `maven-publish`
    signing
}

allprojects {
    group = "sh.polar"
    version = System.getenv("RELEASE_VERSION")?.removePrefix("v") ?: "0.1.3"

    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "java-library")
    apply(plugin = "maven-publish")
    apply(plugin = "signing")

    java {
        toolchain {
            languageVersion = JavaLanguageVersion.of(17)
        }
        withJavadocJar()
        withSourcesJar()
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    tasks.withType<Javadoc> {
        (options as StandardJavadocDocletOptions).addStringOption("Xmaxwarns", "1000")
    }

    publishing {
        repositories {
            maven {
                name = "OSSRH"
                url = uri(
                    if (version.toString().endsWith("-SNAPSHOT"))
                        "https://central.sonatype.com/repository/maven-snapshots/"
                    else
                        "https://ossrh-staging-api.central.sonatype.com/service/local/staging/deploy/maven2/"
                )
                credentials {
                    val token = System.getenv("OSSRH_TOKEN") ?: ""
                    val decoded = if (token.isNotBlank()) String(Base64.getDecoder().decode(token), Charsets.UTF_8) else ":"
                    username = decoded.substringBefore(":")
                    password = decoded.substringAfter(":")
                }
            }
            maven {
                name = "GitHubPackages"
                url = uri("https://maven.pkg.github.com/brkscosta/polar-java")
                credentials {
                    username = System.getenv("GITHUB_ACTOR") ?: ""
                    password = System.getenv("GITHUB_TOKEN") ?: ""
                }
            }
        }
    }

    signing {
        val signingKey = System.getenv("GPG_PRIVATE_KEY")
        val signingPassword = System.getenv("GPG_PASSPHRASE")
        isRequired = signingKey != null && signingKey.isNotBlank()
        if (isRequired) {
            useInMemoryPgpKeys(signingKey, signingPassword)
        }
        sign(publishing.publications)
    }
}
