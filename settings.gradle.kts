import groovy.json.JsonSlurper

plugins { id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0" }

rootProject.name = "klarex"

include("cli", "parser", "compiler")

fun getVersionFromPackageJson(): String {
  val packageJson = file("package.json")
  val jsonSlurper = JsonSlurper()
  val json = jsonSlurper.parseText(packageJson.readText()) as Map<*, *>
  return json["version"] as String
}

System.setProperty("version", getVersionFromPackageJson())
