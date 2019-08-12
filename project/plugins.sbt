
//libraryDependencies <+= (sbtVersion)("org.scala-sbt" % "scripted-plugin" % _)

//addSbtPlugin("com.github.gseitz" % "sbt-release" % "0.8")

// https://github.com/sbt/sbt-scalariform
addSbtPlugin("org.scalariform" % "sbt-scalariform" % "1.8.3")


resolvers ++= Seq(Resolver.mavenLocal, Resolver.sonatypeRepo("releases"))
