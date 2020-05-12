name := "abstract-monad"
version := "0.1"
scalaVersion := "2.13.1"

addCommandAlias("fm", "all compile:scalafmt test:scalafmt")
addCommandAlias("cc", "all clean compile")
addCommandAlias("c", "compile")
addCommandAlias("r", "run")
addCommandAlias("t", "test")
addCommandAlias("to", "testOnly")

scalacOptions ++= scalacSettings
resolvers ++= resolversSettings
libraryDependencies ++= libsSettings
testFrameworks += new TestFramework("munit.Framework")

addCompilerPlugin("com.olegpy" %% "better-monadic-for" % "0.3.1")
addCompilerPlugin(("org.typelevel" %% "kind-projector" % "0.11.0").cross(CrossVersion.full))

lazy val scalacSettings = Seq(
  "-encoding",
  "UTF-8",
  "-deprecation",
  "-unchecked",
  "-feature",
  "-explaintypes",
  "-opt-warnings",
  "-language:higherKinds",
  "-language:implicitConversions",
  "-Yrangepos",
  "-Ywarn-numeric-widen",
  "-Ywarn-extra-implicit",
  "-Xlint:_,-unused",
  "-Xfatal-warnings",
  "-Ymacro-annotations"
)

lazy val resolversSettings = Seq(
  Resolver.sonatypeRepo("public"),
  Resolver.sonatypeRepo("snapshots"),
  Resolver.sonatypeRepo("releases")
)

lazy val libsSettings = Seq(
  "org.typelevel" %% "simulacrum"    % "1.0.0",
  "org.typelevel" %% "cats-core"     % "2.1.1",
  "org.typelevel" %% "cats-effect"   % "2.1.2",
  "org.typelevel" %% "cats-mtl-core" % "0.7.1",
  "org.scalameta" %% "munit"         % "0.7.6" % Test
)
