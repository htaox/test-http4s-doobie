val Http4sVersion = "0.21.0-M5"
val CirceVersion = "0.12.0-M4"
val Specs2Version = "4.7.0"
val LogbackVersion = "1.2.3"
val CatsVersion = "2.0.0"
val DoobieVersion = "0.8.4"
val H2Version = "1.4.199"

lazy val root = (project in file("."))
  .settings(
    organization := "com.eztier",
    name := "test-http4s-doobie",
    version := "0.0.1-SNAPSHOT",
    scalaVersion := "2.12.8",
    libraryDependencies ++= Seq(
      "org.typelevel" %% "cats-core" % CatsVersion,

      "org.http4s"      %% "http4s-blaze-server" % Http4sVersion,
      "org.http4s"      %% "http4s-blaze-client" % Http4sVersion,
      "org.http4s"      %% "http4s-circe"        % Http4sVersion,
      "org.http4s"      %% "http4s-dsl"          % Http4sVersion,
      
      "io.circe"        %% "circe-generic"       % CirceVersion,

      "org.tpolecat" %% "doobie-core" % DoobieVersion,
      "org.tpolecat" %% "doobie-h2" % DoobieVersion,
      "org.tpolecat" %% "doobie-scalatest" % DoobieVersion,
      "org.tpolecat" %% "doobie-hikari" % DoobieVersion,

      "com.h2database" % "h2" % H2Version,

      "org.specs2"      %% "specs2-core"         % Specs2Version % "test",
      "ch.qos.logback"  %  "logback-classic"     % LogbackVersion
    )
    // ,
    // addCompilerPlugin("org.typelevel" %% "kind-projector"     % "0.10.3"),
    // addCompilerPlugin("com.olegpy"    %% "better-monadic-for" % "0.3.0")
  )

scalacOptions ++= Seq(
  "-Xfatal-warnings",
  "-deprecation",
  "-feature",
  "-unchecked",
  "-language:_"
)

// Filter out compiler flags to make the repl experience functional...
val badConsoleFlags = Seq("-Xfatal-warnings", "-Ywarn-unused:imports")
scalacOptions in (Compile, console) ~= (_.filterNot(badConsoleFlags.contains(_)))
