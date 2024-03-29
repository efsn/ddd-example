tasks {
    register<Copy>("copyGitHooks") {
        val old = rootProject.file(".git/hooks/pre-commit")
        if (!old.exists()) {
            from(rootProject.file("gradle/scripts/pre-commit"))
            into(rootProject.file(".git/hooks"))
            setFileMode(0b111101101)
        }
    }
    named("ktlint") { dependsOn("copyGitHooks") }
}

/*

tasks.withType<ProcessResources> {
    copy {
        from(file("$projectDir/scripts/pre-commit"))
        into(file("$projectDir/.git/hooks"))
        fileMode = 0b111101101
    }
}

 */
