ThisBuild / organization := "com.dallinhuff"
ThisBuild / name := "openai4s"
ThisBuild / scalaVersion := "3.3.1"

// open source licenses that apply to the project
licenses := Seq("MIT" -> url("https://github.com/dallinhuff/openai4s/blob/main/LICENSE"))

description := "OpenAI API request serialization library for Scala"

import xerial.sbt.Sonatype._
sonatypeProjectHosting := Some(GitHubHosting("dallinhuff", "openai4s", "dallinhuff@gmail.com"))

// publish to the sonatype repository
publishTo := sonatypePublishToBundle.value

val circeVersion = "0.14.5"

lazy val root = (project in file("."))
  .settings(
    libraryDependencies ++= Seq(
      "io.circe" %% "circe-core"    % circeVersion,
      "io.circe" %% "circe-generic" % circeVersion,
      "io.circe" %% "circe-literal" % circeVersion,
      "io.circe" %% "circe-parser"  % circeVersion
    )
  )
