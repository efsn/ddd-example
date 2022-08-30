rootProject.name = "ddd-example"

files("common").forEach { dir ->
    dir.listFiles()?.forEach {
        if (it.isDirectory) {
            include(it.name)
            project(":${it.name}").projectDir = it
        }
    }
}

include("user")
project(":user").projectDir = file("${rootProject.projectDir}/service/user")
