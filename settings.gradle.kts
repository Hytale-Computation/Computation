import dev.scaffoldit.hytale.wire.HytaleManifest

rootProject.name = "Computation"

plugins {
    // See documentation on https://scaffoldit.dev
    id("dev.scaffoldit") version "0.2.+"
}

// Would you like to do a split project?
// Create a folder named "common", then configure details with `common { }`

hytale {
    usePatchline("release")
    useVersion("latest")

    repositories {
        // Any external repositories besides: MavenLocal, MavenCentral, HytaleMaven, and CurseMaven
    }

    dependencies {
        // Any external dependency you also want to include
    }

    manifest {
        Group = "Computation"
        Name = "Computation"
        Main = "io.github.computation.Computation"
        Version = "1.0.0"
        Description = "The base Computation code and API!"
        Authors = listOf(HytaleManifest.Author("TechTastic"))
        Website = "https://github.com/Computation/Computation"
    }
}
