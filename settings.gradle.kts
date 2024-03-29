dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
    versionCatalogs {
        create("libs") {
            from(files("libs.toml"))
        }
    }
}

rootProject.name = "RandomizeWear"
include(":mobile")
include(":common")
include(":wear")
