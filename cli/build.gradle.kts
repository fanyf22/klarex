plugins { id("buildlogic.kotlin-application-conventions") }

dependencies { implementation(project(":parser")) }

application { mainClass = "io.github.fanyf22.klarex.cli.MainKt" }
