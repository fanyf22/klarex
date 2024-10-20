plugins { id("buildlogic.kotlin-library-conventions") }

dependencies {
  implementation(project(":parser"))
  implementation("org.graalvm.truffle:truffle-api:24.1.1")
  implementation("org.graalvm.polyglot:python:24.1.1")
}
