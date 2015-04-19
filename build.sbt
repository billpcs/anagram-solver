name := "EnglishAnagramSolver"

version := "1.0"

scalaVersion := "2.11.6"

libraryDependencies += "org.scala-lang.modules" %% "scala-swing" % "1.0.1"

mainClass := Some("AnagramGUI")

selectMainClass := Some("AnagramGUI")

mainClass in (Compile, run) := Some("AnagramGUI")