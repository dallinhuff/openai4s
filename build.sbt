ThisBuild / organization := "com.dallinhuff"
ThisBuild / name := "openai4s"
ThisBuild / scalaVersion := "3.3.1"
ThisBuild / description := "OpenAI API request serialization library for Scala"
ThisBuild / sonatypeCredentialHost := "s01.oss.sonatype.org"

licenses := Seq("MIT" -> url("https://github.com/dallinhuff/openai4s/blob/main/LICENSE"))

import xerial.sbt.Sonatype.*
sonatypeRepository := "https://s01.oss.sonatype.org/service/local"
sonatypeProjectHosting := Some(GitHubHosting("dallinhuff", "openai4s", "dallinhuff@gmail.com"))
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
