tasks {
    register<Copy>("copyGitHooks") {
        val old = rootProject.file(".git/hooks/pre-commit")
        if (!old.exists()) {
            from(rootProject.file("gradle/scripts/pre-commit"))
            into(rootProject.file(".git/hooks"))
            fileMode(0755)
        }
    }
    named("ktlint") { dependsOn("copyGitHooks") }
}
