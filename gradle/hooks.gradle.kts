tasks {
    register<Copy>("copyGitHooks") {
        val old = rootProject.file(".git/hooks/pre-commit")
        if (!old.exists()) {
            from(rootProject.file("gradle/scripts/pre-commit"))
            into(rootProject.file(".git/hooks"))
            setFileMode(770)
            // Runtime.getRuntime().exec("chmod -R +x .git/hooks")
        }
    }
    named("ktlint") { dependsOn("copyGitHooks") }
}
