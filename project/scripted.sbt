libraryDependencies += { "org.scala-sbt" %% "scripted-plugin" % sbtVersion.value }

/**
scriptedLaunchOpts := { scriptedLaunchOpts.value ++
  Seq("-Xmx1024M", "-Dplugin.version=" + version.value)
}
scriptedBufferLog := false
**/
