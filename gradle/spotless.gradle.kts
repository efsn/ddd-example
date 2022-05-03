plugins {
    id("com.diffplug.spotless") version "6.2.2"
}

spotless {
    // optional: limit format enforcement to just the files changed by this feature branch
    ratchetFrom("origin/master")

    format("misc") {
        // define the files to apply `misc` to
        target("*.gradle.kts", "*.md", ".gitignore")

        // define the steps to apply to those files
        trimTrailingWhitespace()
        indentWithSpaces() // or spaces. Takes an integer argument if you don"t like 4
        endWithNewline()
    }

    java {
        importOrder()
        importOrder("java", "javax", "com.acme", "")
        removeUnusedImports()
        // apply a specific flavor of google-java-format
        googleJavaFormat("11").aosp().reflowLongStrings()
        // make sure every file has the following copyright header.
        // optionally, Spotless can set copyright years by digging
        // through git history (see "license" section below)
        licenseHeader("/* (C)\$YEAR */")
    }

    kotlin {
        removeUnusedImports()
    }

    kotlin {
        ktlint("0.45.2")
            .userData(
                mapOf(
                    "editorconfig" to project.file(".editorconfig").absolutePath
                )
            )

        ktfmt()    // has its own section below
        licenseHeader("/* (C)\$YEAR */") // or licenseHeaderFile
    }

    kotlinGradle {
        target("*.gradle.kts") // default target for kotlinGradle
        ktlint() // or ktfmt() or prettier()
    }
}
