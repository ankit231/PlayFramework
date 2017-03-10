name := """play-java-intro"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file("."))
  .enablePlugins(PlayJava)

scalaVersion := "2.11.7"

libraryDependencies ++= {
  val cassandraVersion = "3.1.4"
  Seq(
    "com.datastax.cassandra" % "cassandra-driver-core" % cassandraVersion,
    javaWs
  )
}

routesGenerator := InjectedRoutesGenerator

maintainer := "FinServ"

dockerRepository := Some("docker.paytmlabs.com")

dockerExposedPorts in Docker := Seq(9000, 9443)
