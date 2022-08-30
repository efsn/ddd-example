plugins {
    id("com.diffplug.spotless") version "6.10.0"
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
        googleJavaFormat().aosp().reflowLongStrings()
        // make sure every file has the following copyright header.
        // optionally, Spotless can set copyright years by digging
        // through git history (see "license" section below)
        licenseHeader("/* (C)\$YEAR */")
    }

    kotlin {
        removeUnusedImports()

        ktlint().editorConfigOverride(
            mapOf(
                "max_line_length" to "",
                "ij_kotlin_imports_layout" to "",
                "ij_kotlin_packages_to_use_import_on_demand" to "",
                "ktlint_code_style" to "",
                "indent_style" to "",
                "ij_kotlin_allow_trailing_comma" to "",
                "insert_final_newline" to "",
                "disabled_rules" to "",
                "indent_size" to "",
                "ij_kotlin_allow_trailing_comma_on_call_site" to "",
                "ktlint_ignore_back_ticked_identifier" to "",
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
