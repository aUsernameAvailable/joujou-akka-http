import sbt.Keys.libraryDependencies

name := "akka-http"

version := "1.0"

scalaVersion := "2.12.2"

lazy val root = (project in file(".")).settings(
  libraryDependencies ++= Seq(
    "com.typesafe.akka" %% "akka-http" % "10.0.7",
    "com.typesafe.akka" %% "akka-http-testkit" % "10.0.7" % Test,
    "de.heikoseeberger" %% "akka-http-circe" % "1.13.0"
  )
)

val circeVersion = "0.8.0"

libraryDependencies ++= Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic",
  "io.circe" %% "circe-parser"
).map(_ % circeVersion)