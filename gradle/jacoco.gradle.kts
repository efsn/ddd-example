plugins { jacoco }

subprojects {
    apply(plugin = "jacoco")

    tasks {
        jacocoTestReport {
            dependsOn("test")
            reports { xml.isEnabled = true }
            classDirectories.exclude(jacocoExcludeList)
        }
    }
}

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

    val testCoverage by tasks.registering {
        group = "verification"
        description = "Runs the unit tests with coverage"
        dependsOn(":test", ":jacocoTestReport", ":jacocoTestCoverageVerification")
        tasks["jacocoTestReport"].mustRunAfter(tasks["test"])
        tasks["jacocoTestCoverageVerification"].mustRunAfter(tasks["jacocoTestReport"])
    }
}
