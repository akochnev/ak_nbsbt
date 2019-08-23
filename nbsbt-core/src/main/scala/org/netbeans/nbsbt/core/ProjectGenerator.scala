package org.netbeans.nbsbt.core

import scala.xml.Node

object ProjectGenerator {
  def projectXml(name: String, builderAndNatures: (String, Seq[String])): Node =
    <projectDescription>
      <name>{ name }</name>
      <buildSpec>
        <buildCommand>
          <name>{ builderAndNatures._1 }</name>
        </buildCommand>
      </buildSpec>
      <natures>
        { builderAndNatures._2.map(n => <nature>{ n }</nature>) }
      </natures>
    </projectDescription>
}