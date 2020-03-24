dependencies {
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.2")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.3.2")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-reactive:1.3.2")

    api("org.springframework.boot:spring-boot-starter-webflux")
    api("org.springframework.boot:spring-boot-starter-actuator")
    api("org.springframework.boot:spring-boot-starter-security")
    api("org.springframework.security:spring-security-oauth2-resource-server")
    api("org.springframework.security:spring-security-oauth2-client")
    api("org.springframework.security:spring-security-oauth2-jose")
    api("org.springframework:spring-tx")
    api("com.fasterxml.jackson.module", "jackson-module-kotlin")

    api("io.jsonwebtoken:jjwt-api:0.10.5")
    api("org.javassist:javassist:3.18.2-GA")
    api("com.google.guava:guava:28.1-jre")

    api("org.springframework.boot.experimental:spring-boot-starter-data-r2dbc")
    api("org.springframework.boot:spring-boot-starter-jdbc")
    api("org.flywaydb:flyway-core")
    api("io.r2dbc:r2dbc-spi")
    api("io.r2dbc:r2dbc-pool")
    api("io.r2dbc:r2dbc-postgresql")
    api("org.postgresql:postgresql:42.2.8")

    api("io.projectreactor.addons:reactor-extra:3.2.3.RELEASE")
    api("org.codehaus.groovy:groovy:2.5.7")
    api("io.projectreactor.kotlin:reactor-kotlin-extensions")

    api("io.springfox:springfox-swagger2:3.0.0-SNAPSHOT")
    api("io.springfox:springfox-swagger-ui:3.0.0-SNAPSHOT")
    api("io.springfox:springfox-spring-webflux:3.0.0-SNAPSHOT")

    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
}