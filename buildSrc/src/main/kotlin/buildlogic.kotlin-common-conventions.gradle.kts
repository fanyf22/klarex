plugins { kotlin("jvm") }

group = "io.github.fanyf22.klarex"

version = System.getProperty("version")

repositories { mavenCentral() }

dependencies { testImplementation(kotlin("test")) }

tasks.test { useJUnitPlatform() }

kotlin { jvmToolchain(21) }
