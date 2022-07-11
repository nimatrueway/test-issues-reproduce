val scalaV = "2.12.16"

ThisBuild / version := "1.0.0-SNAPSHOT"
ThisBuild / scalaVersion := scalaV

lazy val AwsRekognition = "software.amazon.awssdk" % "rekognition" % "2.17.190"
lazy val Scalactic = "org.scalactic" %% "scalactic" % "3.2.12" % "test"
lazy val ScalaTest = "org.scalatest" %% "scalatest" % "3.2.12" % "test"
lazy val ScalaMock = "org.scalamock" %% "scalamock" % "5.1.0" % "test"
lazy val Mockito =
  "org.mockito" %% "mockito-scala-scalatest" % "1.16.29" % "test"
lazy val ScalaReflection = "org.scala-lang" % "scala-reflect" % scalaV

lazy val root = (project in file("."))
  .settings(
    name := "reproduce",
    libraryDependencies ++= Seq(
      AwsRekognition,
      ScalaReflection,
      Mockito,
      ScalaMock,
      Scalactic,
      ScalaTest
    )
  )
