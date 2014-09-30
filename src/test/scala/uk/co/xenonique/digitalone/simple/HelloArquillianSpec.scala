/*******************************************************************************
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2013,2014 by Peter Pilgrim, Addiscombe, Surrey, XeNoNiQUe UK
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU GPL v3.0
 * which accompanies this distribution, and is available at:
 * http://www.gnu.org/licenses/gpl-3.0.txt
 *
 * Developers:
 * Peter Pilgrim -- design, development and implementation
 *               -- Blog: http://www.xenonique.co.uk/blog/
 *               -- Twitter: @peter_pilgrim
 *
 * Contributors:
 *
 *******************************************************************************/

package uk.co.xenonique.digitalone.simple

import javax.inject.Inject

import org.jboss.arquillian.container.test.api.Deployment
import org.jboss.arquillian.junit.Arquillian
import org.jboss.shrinkwrap.api.ShrinkWrap
import org.jboss.shrinkwrap.api.asset.EmptyAsset
import org.jboss.shrinkwrap.api.spec.WebArchive
import org.junit.Test
import org.scalatest.junit.{JUnitSuiteLike, JUnitSuite}

import org.scalatest.{FlatSpecLike, Matchers, FlatSpec}
import uk.co.xenonique.digitalone.GradleDependency
import org.junit.runner.RunWith
/**
 * The type HelloArquillianSpec
 *
 * @author Peter Pilgrim
 */

@RunWith(classOf[Arquillian])
//@RunWith(classOf[JUnitRunner])
class HelloArquillianSpec extends JUnitSuite with Matchers {

  @Inject var hello: Hello = _

  @Test
  def shouldPrintAPoliteGreeting():Unit = {
    val msg = hello.hello("JavaOne 2014")
    println("****** " + msg)
    (msg) should be ("Hello JavaOne 2014")
  }

  // Ideally, we want the following:
  //  "Hello" should "print a polite greeting" in {
  //    val msg = hello.hello("JavaOne 2014")
  //    println("****** " + msg)
  //    (msg) should be ("Hello JavaOne 2014")
  //  }
}

object HelloArquillianSpec {
  @Deployment
  def createDeployment():WebArchive = {
    val war = ShrinkWrap.create(classOf[WebArchive], "test.war")
      .addPackage("uk.co.xenonique.digitalone")
      .addPackage("uk.co.xenonique.digitalone.simple")
      .addAsLibrary( GradleDependency.resolve("org.scala-lang:scala-library:2.11.1") )
      .addAsLibrary( GradleDependency.resolve("org.scalatest:scalatest_2.11:2.2.0") )
      .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
    println(war.toString(true))
    war
  }

}

// See Also
// https://github.com/kazuhira-r/javaee6-scala-examples/blob/master/arquillian-scalatest-integration/src/test/scala/javaee6/web/service/CalcServiceTest.scala
