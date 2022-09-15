import io.gitlab.arturbosch.detekt.Detekt
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.plugin.SpringBootPlugin
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    val kotlinVersion = "1.7.10"

    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
    kotlin("plugin.jpa") version kotlinVersion
    id("org.springframework.boot") version "2.7.3"
    jacoco
    id("io.gitlab.arturbosch.detekt") version "1.21.0"
    id("com.diffplug.spotless") version "6.11.0"
}

group = "cn.example.ddd"
version = "1.1.0-SNAPSHOT"

allprojects {
    apply(plugin = "java-library")
    apply(plugin = "kotlin")
    apply(plugin = "jacoco")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "io.gitlab.arturbosch.detekt")
    apply(plugin = "com.diffplug.spotless")
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
        implementation(platform("org.jetbrains.kotlinx:kotlinx-coroutines-bom:1.6.4"))
        implementation(platform(SpringBootPlugin.BOM_COORDINATES))
        implementation(platform("org.springframework.cloud:spring-cloud-dependencies:2021.0.3"))
        implementation(platform("io.jsonwebtoken:jjwt-root:0.11.5"))
        implementation(platform("com.github.cloudyrock.mongock:mongock-core-bom:4.3.8"))
        implementation(platform("org.zalando:logbook-bom:2.14.0"))
        implementation(platform("org.testcontainers:testcontainers-bom:1.17.3"))

        detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.21.0")

        // developmentOnly("org.springframework.boot:spring-boot-devtools")
    }

    tasks {
        withType<KotlinCompile> {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_17.toString()
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
            reports { xml.required.set(true) }
            classDirectories.exclude(jacocoExcludeList)
        }

        withType<Detekt> {
            config.from(files("$rootDir/gradle/config/detekt.yml"))
            reports {
                xml.required.set(true)
                xml.outputLocation.set(file("${rootProject.buildDir}/reports/${project.name}.xml"))

                html.required.set(true)
                html.outputLocation.set(file("${rootProject.buildDir}/reports/${project.name}.html"))

                txt.required.set(false)
                txt.outputLocation.set(file("${rootProject.buildDir}/reports/${project.name}.txt"))

                sarif.required.set(true)
                sarif.outputLocation.set(file("${rootProject.buildDir}/reports/detekt.sarif"))
            }
        }
    }

    val compileTestKotlin: KotlinCompile by tasks
    compileTestKotlin.kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    // Config Detekt
    detekt {
        toolVersion = "1.21.0"
        parallel = true
        buildUponDefaultConfig = true
        allRules = false
        ignoreFailures = true
        autoCorrect = true
    }

    // Config Spotless
    spotless {
        format("misc") {
            target("*.gradle.kts", "*.md", ".gitignore")
            trimTrailingWhitespace()
            indentWithSpaces()
            endWithNewline()
        }

        java {
            importOrder()
            removeUnusedImports()
            googleJavaFormat().aosp().reflowLongStrings()
            licenseHeaderFile(rootProject.file(".license-header"))
        }

        kotlin {
            ktlint()

            licenseHeaderFile(rootProject.file(".license-header"))
        }

        kotlinGradle {
            target("*.gradle.kts")
            ktlint()
        }
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
    toolVersion = "0.8.8"
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
        dependsOn(subprojects.map { it.tasks.getByName("jacocoTestReport") })
        reports { xml.required.set(true) }
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
