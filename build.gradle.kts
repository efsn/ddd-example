import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.plugin.SpringBootPlugin
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    val kotlinVersion = "1.6.10"

    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
    kotlin("plugin.jpa") version kotlinVersion
    id("org.springframework.boot") version "2.6.2"
    jacoco
}

group = "cn.example.ddd"
version = "1.1.0-SNAPSHOT"

allprojects {
    apply(plugin = "java-library")
    apply(plugin = "kotlin")
    apply(plugin = "jacoco")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(from = rootProject.file("gradle/ktlint.gradle.kts"))

    repositories {
        maven(url = "https://maven.aliyun.com/repository/public/")
        maven(url = "https://maven.aliyun.com/repository/spring/")
        maven("https://oss.jfrog.org/artifactory/oss-snapshot-local/")
        maven("https://repo.spring.io/milestone")
        mavenCentral()
    }

    dependencies {
        implementation(kotlin("reflect"))
        implementation(platform("org.jetbrains.kotlinx:kotlinx-coroutines-bom:1.6.0-native-mt"))
        implementation(platform(SpringBootPlugin.BOM_COORDINATES))
        implementation(platform("org.springframework.cloud:spring-cloud-dependencies:2021.0.0"))
        implementation(platform("com.github.cloudyrock.mongock:mongock-core-bom:4.3.8"))
        implementation(platform("org.zalando:logbook-bom:2.14.0"))
    }

    tasks {
        withType<KotlinCompile> {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_11.toString()
                freeCompilerArgs = listOf("-Xjsr305=strict")
            }
        }

        test {
            project.setProperty("testResultsDirName", "$rootDir/build/test-results")
            failFast = true
            useJUnitPlatform()
            testLogging {
                events("passed", "skipped", "failed")
            }
            dependsOn(project.tasks.named("ktlint"))
        }

        jacocoTestReport {
            dependsOn("test")
            reports { xml.isEnabled = true }
            classDirectories.exclude(jacocoExcludeList)
        }
    }

    val compileTestKotlin: KotlinCompile by tasks
    compileTestKotlin.kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

}

apply(from = rootProject.file("gradle/hooks.gradle.kts"))

dependencies {
    implementation(project("core"))
    implementation(project("user"))
}

tasks.named<BootJar>("bootJar") {
    launchScript()
}

// Config Jacoco
jacoco {
    toolVersion = "0.8.7"
}

fun ConfigurableFileCollection.exclude(list: List<String>) = setFrom(asFileTree.matching { exclude(list) }.files)

val sourceSets = subprojects.flatMap { it.sourceSets }
val jacocoExcludeList = listOf(
    "**/configuration/**",
    "**/filter/**",
    "**/converter/**",
    "**/util/**",
    "**/task/**",
    "**/migration/**",
    "**/po/**",
    "**/request/**",
    "**/command/**",
    "**/response/**",
    "**/prop/**",
    "**/exception/**",
    "**/*Configuration*.*",
    "**/*Client*.*",
    "**/*Test*.*"
)

tasks {
    withType<JacocoReport> {
        dependsOn(subprojects.map { it.tasks.getByName("test") })
        reports { xml.isEnabled = true }
        val childTask = subprojects.map { it.tasks.named<JacocoReport>("jacocoTestReport").get() }
        sourceDirectories.setFrom(childTask.flatMap { it.sourceDirectories.files })
        classDirectories.setFrom(childTask.flatMap { it.classDirectories.files })
        executionData.setFrom(childTask.map { it.executionData.asPath }.filter { File(it).exists() })
        afterEvaluate { classDirectories.exclude(jacocoExcludeList) }
    }

    withType<JacocoCoverageVerification> {
        dependsOn("jacocoTestReport")
        val reportTask = named<JacocoReport>("jacocoTestReport").get()
        sourceDirectories.setFrom(reportTask.sourceDirectories)
        classDirectories.setFrom(reportTask.classDirectories)
        executionData.setFrom(reportTask.executionData)
        afterEvaluate { classDirectories.exclude(jacocoExcludeList) }
        violationRules {
            rule {
                limit {
                    counter = "METHOD"
                    minimum = "0.01".toBigDecimal()
                }
                limit {
                    counter = "INSTRUCTION"
                    minimum = "0.01".toBigDecimal()
                }
            }
        }
    }

    named("check") {
        dependsOn("jacocoTestCoverageVerification")
    }
}
