pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Words"
include(":app")
include(":app:pager")
include(":app:ui")
include(":test")
include(":test:manager")
include(":test:constant")
include(":test:ui")
include(":test:linker")
include(":test:pager")
include(":test:util")
include(":feature")
include(":ztest")
include(":ztest:pager")
include(":ztest:ui")
include(":ztest:linker")
