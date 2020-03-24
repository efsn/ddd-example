dependencies {
    implementation(project(":core"))

    api("org.springframework.boot:spring-boot-starter-test") { exclude(module = "junit") }
    api("org.springframework.security:spring-security-test")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.3.2")
    api("org.junit.jupiter:junit-jupiter-api")
    api("org.junit.jupiter:junit-jupiter-params")
    runtimeOnly("org.junit.jupiter:junit-jupiter-engine")
    api("io.projectreactor:reactor-test")

    api("com.github.tomakehurst:wiremock-jre8:2.23.2")
    api("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")

    api("org.testcontainers:testcontainers:1.12.3")
    api("org.testcontainers:junit-jupiter:1.12.3")
    api("org.testcontainers:postgresql:1.12.3")
}
