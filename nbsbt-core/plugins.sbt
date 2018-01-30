addSbtPlugin("com.github.gseitz" % "sbt-release" % "1.0.7")

// https://github.com/sbt/sbt-scalariform
addSbtPlugin("org.scalariform" % "sbt-scalariform" % "1.8.2")

//addSbtPlugin("org.scala-sbt" %% "scripted-plugin" % "1.0.4")

resolvers ++= Seq(Resolver.mavenLocal, Resolver.sonatypeRepo("releases"))
