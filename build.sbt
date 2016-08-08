
val playVersion = "2.5.4"
val slf4jVersion = "1.7.12"
val logbackVersion = "1.1.7"
val specs2Version = "3.7.3"

lazy val libraryExclusions = Seq(
  ExclusionRule("commons-logging"),
  ExclusionRule("org.slf4j", "slf4j-log4j12"),
  ExclusionRule("commons-logging", "commons-logging"),
  ExclusionRule("org.apache.logging.log4j", "log4j-core")
)

lazy val repoSettings = Seq(
  publishTo := {
    val nexus = "https://nexus.waylay.io"
    if (isSnapshot.value)
      Some("Waylay snapshot repo" at nexus + "/repository/maven-snapshots")
    else
      Some("Waylay releases repo" at nexus + "/repository/maven-releases")
  }
)

organization in ThisBuild := "io.waylay.influxdb"

lazy val root = (project in file("."))
  .settings(
    name := "influxdb-scala",
    scalaVersion := "2.11.8",

    // Be wary of adding extra dependencies (especially the Waylay common dependencies)
    // They may pull in a newer Netty version, breaking play-ws
    libraryDependencies ++= Seq(
      "com.typesafe.play" %% "play-json" % playVersion,
      "com.typesafe.play" %% "play-ws" % playVersion, // pulls in the whole of play
      //"com.typesafe.scala-logging" %% "scala-logging" % "3.1.0",
      "org.slf4j" % "slf4j-api" % slf4jVersion,
      "org.slf4j" % "jcl-over-slf4j" % slf4jVersion,

      // TEST
      "ch.qos.logback" % "logback-classic" % logbackVersion % Test,
      "org.specs2" %% "specs2-core" % specs2Version % Test,
      "org.specs2" %% "specs2-junit" % specs2Version % Test,
      "com.whisk" %% "docker-testkit-specs2" % "0.9.0-M5" % Test excludeAll ExclusionRule(organization = "io.netty"),

      // INTEGRATION TESTS
      // TODO investigate if we can do this with specs2
      "org.scalatest" %% "scalatest" % "2.2.6" % Test,
      "com.whisk" %% "docker-testkit-scalatest" % "0.9.0-M5" % Test excludeAll ExclusionRule(organization = "io.netty"),
      "com.jsuereth" %% "scala-arm" % "1.4" % Test
    ).map(_.excludeAll(libraryExclusions:_*))
  )
  .settings(
    repoSettings
  )
