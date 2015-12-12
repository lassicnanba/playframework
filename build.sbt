name := "PlayApp"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache
  "mysql" % "mysql-connector-java" % "5.2.64"
)

play.Project.playJavaSettings
