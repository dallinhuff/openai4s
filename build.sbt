ThisBuild / organization  := "com.dallinhuff"
ThisBuild / versionScheme := Some("early-semver")
ThisBuild / scalaVersion  := "3.3.1"
ThisBuild / description := "OpenAI API request serialization library for Scala"
ThisBuild / sonatypeCredentialHost := "s01.oss.sonatype.org"

licenses := Seq(
  "MIT" -> url("https://github.com/dallinhuff/openai4s/blob/main/LICENSE")
)

import xerial.sbt.Sonatype.*
sonatypeRepository := "https://s01.oss.sonatype.org/service/local"
sonatypeProjectHosting := Some(
  GitHubHosting("dallinhuff", "openai4s", "dallinhuff@gmail.com")
)
publishTo := sonatypePublishToBundle.value

val circeVersion  = "0.14.5"
val http4sVersion = "0.23.19"

lazy val root = (project in file("."))
  .settings(
    name := "openai4s",
    libraryDependencies ++= Seq(
      "io.circe"      %% "circe-core"          % circeVersion,
      "io.circe"      %% "circe-generic"       % circeVersion,
      "io.circe"      %% "circe-literal"       % circeVersion,
      "io.circe"      %% "circe-parser"        % circeVersion,
      "org.typelevel" %% "cats-effect"         % "3.5.3",
      "org.http4s"    %% "http4s-dsl"          % http4sVersion,
      "org.http4s"    %% "http4s-ember-client" % http4sVersion,
      "org.http4s"    %% "http4s-circe"        % http4sVersion,
      "org.scalatest" %% "scalatest"           % "3.2.15" % "test",
      "ch.qos.logback" % "logback-classic"     % "1.4.12"
    )
  )
