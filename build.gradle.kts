plugins {
    `java-library`
    `maven-publish`
    signing
}

allprojects {
    group = "sh.polar"
    version = System.getenv("RELEASE_VERSION")?.removePrefix("v") ?: "0.1.0"

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
                    username = System.getenv("OSSRH_USERNAME") ?: ""
                    password = System.getenv("OSSRH_TOKEN") ?: ""
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
