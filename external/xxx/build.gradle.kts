plugins {
    `maven-publish`
}

dependencies {
    api("org.springframework.boot:spring-boot-starter-webflux")
    api("io.projectreactor.kotlin:reactor-kotlin-extensions")
}

// tasks {
//     register<Jar>("sourcesJar") {
//         group = "build"
//         archiveClassifier.set("sources")
//         from(sourceSets.main.get().allSource)
//     }
//     named<Jar>("jar") {
//         enabled = true
//     }
//     named<BootJar>("bootJar") {
//         enabled = false
//     }
// }