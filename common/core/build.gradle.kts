dependencies {
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-reactive")
    api("com.fasterxml.jackson.module:jackson-module-kotlin")

    api("org.springframework.boot:spring-boot-starter-webflux")
    api("org.springframework.boot:spring-boot-starter-actuator")
    api("org.springframework.boot:spring-boot-starter-security")
    api("org.springframework.security:spring-security-oauth2-resource-server")
    api("org.springframework.security:spring-security-oauth2-client")
    api("org.springframework.security:spring-security-oauth2-jose")
    api("org.springframework:spring-tx")
    api("javax.validation:validation-api:2.0.1.Final")

    api("io.jsonwebtoken:jjwt-api:0.11.2")
    api("org.javassist:javassist:3.28.0-GA")
    api("com.google.guava:guava:31.0.1-jre")

    api("org.springframework.boot:spring-boot-starter-data-r2dbc")
    api("org.springframework.boot:spring-boot-starter-jdbc")
    api("org.flywaydb:flyway-core")
    api("io.r2dbc:r2dbc-spi")
    api("io.r2dbc:r2dbc-pool")
    api("io.r2dbc:r2dbc-postgresql")
    api("org.postgresql:postgresql:42.3.1")

    api("io.projectreactor.addons:reactor-extra:3.4.6")
    api("org.codehaus.groovy:groovy:3.0.8")
    api("io.projectreactor.kotlin:reactor-kotlin-extensions")

    api("io.springfox:springfox-swagger2:3.0.0")
    api("io.springfox:springfox-swagger-ui:3.0.0")
    api("io.springfox:springfox-spring-webflux:3.0.0")

    api("org.zalando:logbook-core")
    api("org.zalando:logbook-netty")
//    api("org.zalando:logbook-logstash")
//    api("net.logstash.logback:logstash-logback-encoder:6.6")

    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
}
