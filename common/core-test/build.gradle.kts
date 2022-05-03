dependencies {
    implementation(project(":core"))

    api("org.springframework.boot:spring-boot-starter-test") {
        exclude(module = "junit")
        exclude(module = "mockito-core")
    }

    api("org.springframework.security:spring-security-test")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-test")
    api("org.junit.jupiter:junit-jupiter-api")
    api("org.junit.jupiter:junit-jupiter-params")
    runtimeOnly("org.junit.jupiter:junit-jupiter-engine")
    api("io.projectreactor:reactor-test")

    // api("com.github.tomakehurst:wiremock-jre8:2.32.0")
    // api("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
    // TODO: mockk component instead of mockito
    // TODO: wiremock contracts verify
    // TODO: kotlin not supported
    // api("com.github.hippoom:wiremock-contract-verifier:0.3.0")

    api("io.mockk:mockk:1.12.3")
    api("com.ninja-squad:springmockk:3.1.1")
    // Why kotest instead of jUnit5?

    api("org.testcontainers:testcontainers")
    api("org.testcontainers:junit-jupiter")
    api("org.testcontainers:postgresql")
}
