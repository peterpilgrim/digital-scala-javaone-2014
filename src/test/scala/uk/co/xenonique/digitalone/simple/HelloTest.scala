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

import java.io.File
import javax.inject.Inject

import org.jboss.arquillian.container.test.api.Deployment
import org.jboss.arquillian.junit.Arquillian
import org.jboss.shrinkwrap.api.ShrinkWrap
import org.jboss.shrinkwrap.api.asset.EmptyAsset
import org.jboss.shrinkwrap.api.spec.WebArchive
import org.junit.runner.RunWith
import org.junit.{Assert, Test}
import uk.co.xenonique.digitalone.GradleDependency

/**
 * The type HelloTest
 *
 * @author Peter Pilgrim
 */
@RunWith(classOf[Arquillian])
class HelloTest {

  @Inject
  private var hello: Hello = _

  @Test
  def helloPrintsHelloWorld(): Unit = {
    val msg = hello.hello()
    Assert.assertEquals("Hello World", msg)
  }

}

object HelloTest {
  @Deployment
  def createDeployment():WebArchive = {
    val war = ShrinkWrap.create(classOf[WebArchive], "test.war")
      .addPackage("uk.co.xenonique.digitalone")
      .addPackage("uk.co.xenonique.digitalone.simple")
      .addAsLibrary( GradleDependency.resolve("org.scala-lang:scala-library:2.11.1") )
      .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    println(war.toString(true));
    return war;
  }

}