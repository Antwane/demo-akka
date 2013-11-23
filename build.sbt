organization := "name.heikoseeberger"

name := "demo-akka"

version := "0.1.0"

scalaVersion := Version.scala

libraryDependencies ++= Dependencies.demoAkka

scalacOptions ++= List(
  "-unchecked",
  "-deprecation",
  "-language:_",
  "-target:jvm-1.7",
  "-encoding", "UTF-8"
)

initialCommands in console := "import name.heikoseeberger.demoakka._"
