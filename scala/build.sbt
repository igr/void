name := "voids"

version := "0.1.0"

scalaVersion := "3.3.6"

libraryDependencies ++= Seq(
  "org.typelevel"     %% "cats-core"            % "2.13.0",
  "org.typelevel"     %% "cats-effect"          % "3.6.3",
  "org.scalatest"     %% "scalatest"            % "3.2.19" % Test
)