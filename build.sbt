sbtPlugin := true

ThisBuild / version := "1.1.5-SNAPSHOT"
ThisBuild / organization := "org.netbeans.nbsbt"
ThisBuild / scalacOptions ++=  Seq("-unchecked", "-deprecation")

import com.typesafe.sbt.SbtScalariform
import sbt.plugins.SbtPlugin


lazy val root = Project(
    "nbsbt",
    file(".")
)
.settings(
    commonSettings,
    publishArtifact := false
)
.aggregate(
    nbsbtCore,nbsbtPlugin
)
.enablePlugins(SbtPlugin,SbtScalariform)

lazy val nbsbtCore =  project.in(
    file("nbsbt-core")
).settings(
    commonSettings,
    libraryDependencies ++= Seq(
         "org.scalaz" %% "scalaz-core" % "7.2.28",
         "org.scalaz" %% "scalaz-effect" % "7.2.28",
         "org.scala-lang.modules" %% "scala-xml" % "1.2.0",
    )
).enablePlugins(SbtScalariform)

lazy val nbsbtPlugin = project.in(
    file("nbsbt-plugin")
).settings(
    commonSettings
).dependsOn(
    nbsbtCore
).enablePlugins(SbtPlugin)


lazy val commonSettings = Seq(
     organization := "org.netbeans.nbsbt",
     scalacOptions ++= Seq("-unchecked", "-deprecation"),
     //credentials += Credentials(Path.userHome / ".ivy2" / ".credentials"),
     sbtPlugin := true,
     scalaVersion := "2.12.8",
     publishMavenStyle := false,
     scriptedLaunchOpts := { scriptedLaunchOpts.value ++
      Seq("-Xmx1024M", "-XX:MaxPermSize=256M", "-Dplugin.version=" + version.value)
     },
     scriptedBufferLog := false
)


import scalariform.formatter.preferences._

scalariformPreferences := scalariformPreferences.value
    .setPreference(RewriteArrowSymbols, false)
      .setPreference(AlignParameters, true)
      .setPreference(AlignSingleLineCaseStatements, true)
      .setPreference(DoubleIndentConstructorArguments, true)
      .setPreference(IndentSpaces, 2)



      