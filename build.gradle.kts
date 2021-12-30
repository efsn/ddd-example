import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.plugin.SpringBootPlugin
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    val kotlinVersion = "1.6.10"

    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
    kotlin("plugin.jpa") version kotlinVersion
    id("org.springframework.boot") version "2.6.2"
}

group = "cn.example.ddd"
version = "1.1.0-SNAPSHOT"

allprojects {
    apply(plugin = "java-library")
    apply(plugin = "kotlin")
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
//        implementation("com.github.cloudyrock.mongock:mongock-spring-v5")
//        implementation("com.github.cloudyrock.mongock:mongodb-springdata-v3-driver")
    }

    tasks {
        withType<KotlinCompile> {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_11.toString()
                freeCompilerArgs = listOf("-Xjsr305=strict")
            }
        }
        test {
            failFast = true
            useJUnitPlatform()
            testLogging {
                events("passed", "skipped", "failed")
            }
            dependsOn(project.tasks.named("ktlint"))
        }
    }

    val compileTestKotlin: KotlinCompile by tasks
    compileTestKotlin.kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

}

dependencies {
    implementation(project("core"))
    implementation(project("user"))
}

tasks.named<BootJar>("bootJar") {
    launchScript()
}
