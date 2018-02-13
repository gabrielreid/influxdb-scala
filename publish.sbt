releasePublishArtifactsAction := PgpKeys.publishSigned.value

publishMavenStyle := true

pomIncludeRepository := { _ => false }

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

pomExtra := (
  <url>https://github.com/waylayio/influxdb-scala</url>
    <licenses>
      <license>
        <name>MIT License</name>
        <url>http://www.opensource.org/licenses/mit-license.php</url>
        <distribution>repo</distribution>
      </license>
    </licenses>
    <developers>
      <developer>
        <id>francisdb</id>
        <name>Francis De Brabandere</name>
        <url>https://github.com/francisdb</url>
      </developer>
      <developer>
        <id>thomastoye</id>
        <name>Thomas Toye</name>
        <url>https://github.com/thomastoye</url>
      </developer>
    </developers>)