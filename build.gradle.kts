import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.plugin.SpringBootPlugin
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    val kotlinVersion = "1.4.10"

    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
    kotlin("plugin.jpa") version kotlinVersion
    id("org.springframework.boot") version "2.4.0"
}

group = "cn.example.ddd"
version = "1.0.0-SNAPSHOT"

allprojects {
    apply(plugin = "java-library")
    apply(plugin = "kotlin")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(from = rootProject.file("gradle/ktlint.gradle.kts"))

    repositories {
        maven(url = "https://maven.aliyun.com/repository/public/")
        maven(url = "https://maven.aliyun.com/repository/spring/")
        maven("http://oss.jfrog.org/artifactory/oss-snapshot-local/")
        maven("https://repo.spring.io/milestone")
        mavenCentral()
        jcenter()
    }

    dependencies {
        implementation(kotlin("reflect"))

        implementation(platform(SpringBootPlugin.BOM_COORDINATES))
        implementation(platform("org.springframework.cloud:spring-cloud-dependencies:Greenwich.SR3"))
        implementation(platform("org.springframework.boot.experimental:spring-boot-bom-r2dbc:0.1.0.M2"))
        implementation(platform("org.springframework.data:spring-data-releasetrain:Moore-RELEASE"))
    }

    tasks {
        withType<KotlinCompile> {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_1_8.toString()
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
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

}

dependencies {
    implementation(project("core"))
    implementation(project("user"))
}

tasks.named<BootJar>("bootJar") {
    launchScript()
}
