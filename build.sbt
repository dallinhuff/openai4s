ThisBuild / version := "0.1.1-SNAPSHOT"
ThisBuild / scalaVersion := "3.3.1"

githubOwner := "dallinhuff"
githubRepository := "openai4s"
githubTokenSource := TokenSource.Environment("GH_PKG_TOKEN")

val circeVersion = "0.14.5"

lazy val root = (project in file("."))
  .settings(
    organization := "com.dallinhuff",
    name := "openai4s",
    libraryDependencies ++= Seq(
      "io.circe" %% "circe-core"    % circeVersion,
      "io.circe" %% "circe-generic" % circeVersion,
      "io.circe" %% "circe-literal" % circeVersion,
      "io.circe" %% "circe-parser"  % circeVersion
    )
  )
