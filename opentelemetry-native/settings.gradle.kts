pluginManagement {
    plugins {
        id("com.diffplug.spotless") version "6.25.0"
        id("com.github.johnrengelman.shadow") version "8.1.1"
        id("com.google.protobuf") version "0.9.4"
        id("com.gradle.develocity") version "3.18.1"
        id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
        id("com.google.cloud.tools.jib") version "3.4.3"
    }
}

plugins {
    id("com.gradle.develocity")
    id("org.gradle.toolchains.foojay-resolver-convention")
}

val gradleEnterpriseServer = "https://ge.opentelemetry.io"
val isCI = System.getenv("CI") != null
val geAccessKey = System.getenv("GRADLE_ENTERPRISE_ACCESS_KEY") ?: ""

// if the GE access key is not given, and we are in CI, then we publish to scans.gradle.com
val useScansGradleCom = isCI && geAccessKey.isEmpty()

develocity {
    if (!useScansGradleCom) {
        server = gradleEnterpriseServer
    }
    buildScan {
        uploadInBackground = !isCI

        if (useScansGradleCom) {
            termsOfUseUrl = "https://gradle.com/terms-of-service"
            termsOfUseAgree = "yes"
            publishing.onlyIf {true }
        } else {
            publishing.onlyIf { geAccessKey.isNotEmpty() }
        }

        capture {
            fileFingerprints.set(true)
        }
    }
}

rootProject.name = "spring-native-opentelemetry"

