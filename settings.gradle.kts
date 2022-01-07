rootProject.name = "ddd-example"

files("common", "external", "service").forEach { dir ->
    dir.listFiles()?.forEach {
        if (it.isDirectory) {
            include(it.name)
            project(":${it.name}").projectDir = it
        }
    }
}
