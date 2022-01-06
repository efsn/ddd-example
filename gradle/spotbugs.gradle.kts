plugins {
    id("com.github.spotbugs") version "5.0.3"
}

apply(plugin = "com.github.spotbugs")

// Config SpotBugs
spotbugs {
    toolVersion.set("4.5.2")
    // reportsDir.set(file("${rootProject.buildDir}/spotbugs"))
}

dependencies {
    spotbugsPlugins("com.h3xstream.findsecbugs:findsecbugs-plugin:1.11.0")
    // spotbugs("com.github.spotbugs:spotbugs:4.5.2")
}

// spotbugs / sonar-findbugs

task.spotbugsMain {
    reports.create("html") {
        required.set(true)
        // outputLocation.set(file("$buildDir/reports/spotbugs.html"))
        // outputLocation.set(file("${rootProject.buildDir}/reports/spotbugs.html"))

        setStylesheet("fancy-hist.xsl")
    }
}

// TODO: Only for java
