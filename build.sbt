//import sbt.ScriptedPlugin
//import sbt.ScriptedPlugin._
//import sbtrelease.ReleasePlugin._
//import com.typesafe.sbt.SbtScalariform
//import com.typesafe.sbt.SbtScalariform.ScalariformKeys
import scalariform.formatter.preferences._


lazy val root = (project in file("."))
  .aggregate(nbsbtCore, nbsbtPlugin)
  .settings(commonSettings:_*)
  .settings(
    name := "nbsbt",
    publishArtifact := false
  )


lazy val nbsbtCore = (project in file("nbsbt-core"))
  .settings(commonSettings:_*)
  .settings (
    name := "nbsbt-core",
    libraryDependencies ++= Seq(
      "org.scalaz" %% "scalaz-core" % "7.2.18",
      "org.scalaz" %% "scalaz-effect" % "7.2.18"
    )
  )
  


lazy val nbsbtPlugin = (project in file("nbsbt-plugin"))
  .dependsOn(nbsbtCore)
  .settings(commonSettings:_*)
  .settings(
    name := "nbsbt-plugin"
)

//Defaults.defaultSettings ++
//formatSettings ++
//scriptedSettings ++
//releaseSettings ++

lazy val commonSettings = Seq(
    organization := "org.netbeans.nbsbt",
    // version is defined in version.sbt in order to support sbt-release
    scalacOptions ++= Seq("-unchecked", "-deprecation"),
    publishTo :=  {         
        val id = if (isSnapshot.value) "snapshots" else "releases"
        val uri = "http://repo.scala-sbt.org/scalasbt/sbt-plugin-" + id
        Some(Resolver.url("sbt-plugin-" + id, url(uri))(Resolver.ivyStylePatterns))
    },
    credentials += Credentials(Path.userHome / ".ivy2" / ".credentials"),
    sbtPlugin := true,
    publishMavenStyle := false,
    sbtVersion in GlobalScope := {  
        System.getProperty("sbt.build.version", (sbtVersion in GlobalScope).value)  
    },
    scalaVersion  := {
      (sbtVersion in GlobalScope).value match {
          case sbt1x if sbt1x.startsWith("1.0") => "2.12.4"
          case sbt013 if sbt013.startsWith("0.13.") => "2.11.12"
          case sbt012 if sbt012.startsWith("0.12.") => "2.9.3"
          case _                                    =>   "2.9.3"
        } 
    },
    sbtDependency  := { 
        (sbtDependency in GlobalScope).value.withRevision((sbtVersion in GlobalScope).value)  
    },
    publishArtifact in (Compile, packageDoc) := false,
    publishArtifact in (Compile, packageSrc) := false
)
/**
lazy val commonSettings =  Seq(        
      /** scriptedLaunchOpts ++= List("-Xmx1024m", "-XX:MaxPermSize=256M") **/
   )
**/

//scalariformSettings.preferences = formattingPreferences

/**
lazy val formatSettings = SbtScalariform.scalariformSettings ++ Seq(
  ScalariformKeys.preferences in Compile := formattingPreferences,
  ScalariformKeys.preferences in Test    := formattingPreferences

)
**/
import scalariform.formatter.preferences._
scalariformPreferences := scalariformPreferences.value
    .setPreference(RewriteArrowSymbols, false)
    .setPreference(AlignParameters, true)
    .setPreference(AlignSingleLineCaseStatements, true)
    .setPreference(DoubleIndentConstructorArguments, true)
    .setPreference(IndentSpaces, 2)

    


