name := "abstract-monad"
version := "0.1"
scalaVersion := "2.13.1"

addCommandAlias("fm", "all compile:scalafmt test:scalafmt")
addCommandAlias("cc", "all clean compile")
addCommandAlias("c", "compile")
addCommandAlias("r", "run")
addCommandAlias("t", "test")
addCommandAlias("to", "testOnly")
addCommandAlias("ps", "projects")
addCommandAlias("p", "project")

scalacOptions ++= scalacSettings
resolvers ++= resolversSettings
libraryDependencies ++= libsSettings
addCompilerPlugin("com.olegpy" %% "better-monadic-for" % "0.3.1")


lazy val scalacSettings = Seq(
  "-encoding",
  "UTF-8",
  "-deprecation",
  "-unchecked",
  "-feature",
  "-explaintypes",
  "-opt-warnings",
  "-language:higherKinds",
  "-Yrangepos",
  "-Ywarn-numeric-widen",
  "-Ywarn-extra-implicit",
  "-Xlint:_,-unused",
  "-Xfatal-warnings"
)

lazy val resolversSettings = Seq(
  Resolver.sonatypeRepo("public"),
  Resolver.sonatypeRepo("snapshots"),
  Resolver.sonatypeRepo("releases")
)

lazy val libsSettings = Seq(
  "org.typelevel" %% "cats-core"     % "2.1.1",
  "org.typelevel" %% "cats-effect"   % "2.1.2",
  "org.typelevel" %% "cats-mtl-core" % "0.7.1"
)

