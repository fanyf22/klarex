import com.strumenta.antlrkotlin.gradle.AntlrKotlinTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  id("buildlogic.kotlin-library-conventions")
  id("com.strumenta.antlr-kotlin") version "1.0.0"
}

dependencies {
  implementation(kotlin("reflect"))
  implementation("com.strumenta:antlr-kotlin-runtime:1.0.0")
}

val generateKotlinGrammarSource =
    tasks.register<AntlrKotlinTask>("generateKotlinGrammarSource") {
      dependsOn("cleanGenerateKotlinGrammarSource")

      // ANTLR .g4 files are under {project}/src/main/antlr
      // Only include *.g4 files. This allows tools (e.g., IDE plugins)
      // to generate temporary files inside the base path
      source = fileTree(layout.projectDirectory.dir("src/main/antlr")) { include("**/*.g4") }

      // We want the generated source files to have this package name
      val pkgName = "io.github.fanyf22.klarex.parser.antlr"
      packageName = pkgName

      // We want visitors and don't need listeners.
      // The Kotlin target language is implicit, as is the file encoding (UTF-8)
      arguments = listOf("-no-listener", "-visitor")

      // Generated files are outputted inside build/generatedAntlr/{package-name}
      val outDir = "generatedAntlr/${pkgName.replace(".", "/")}"
      outputDirectory = layout.buildDirectory.dir(outDir).get().asFile
    }

tasks.withType<KotlinCompile> { dependsOn(generateKotlinGrammarSource) }

kotlin {
  sourceSets {
    main {
      kotlin {
        // ANTLR generated files
        srcDir(layout.buildDirectory.dir("generatedAntlr"))
      }
    }
  }
}
