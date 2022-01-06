plugins {
    id("io.gitlab.arturbosch.detekt") version "1.19.0"
}

detekt {
    toolVersion = "1.19.0"
    parallel = true
    allRules = false
    reports {
        xml {
            required.set(true)
            destination = file("build/reports/mydetekt.xml")
        }
    }
}

dependencies {
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.19.0")
}

tasks.register<io.gitlab.arturbosch.detekt.Detekt>("myDetekt") {
    description = "Runs a custom detekt build."
    setSource(files("src/main/kotlin", "src/test/kotlin"))
    config.setFrom(files("$rootDir/config.yml"))
    debug = true
    reports {
        xml {
            destination = file("build/reports/mydetekt.xml")
        }
        html.destination = file("build/reports/mydetekt.html")
    }
    include("**/*.kt")
    include("**/*.kts")
    exclude("resources/")
    exclude("build/")
}
