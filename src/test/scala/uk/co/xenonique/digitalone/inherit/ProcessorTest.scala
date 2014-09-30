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

package uk.co.xenonique.digitalone.inherit

import javax.inject.Inject

import org.jboss.arquillian.container.test.api.Deployment
import org.jboss.arquillian.junit.Arquillian
import org.jboss.shrinkwrap.api.ShrinkWrap
import org.jboss.shrinkwrap.api.asset.EmptyAsset
import org.jboss.shrinkwrap.api.spec.WebArchive
import org.junit.Test
import org.junit.runner.RunWith
import org.scalatest.Matchers
import org.scalatest.junit.JUnitSuite
import uk.co.xenonique.digitalone.GradleDependency

/**
 * The type ProcessorTest
 *
 * @author Peter Pilgrim
 */

@RunWith(classOf[Arquillian])
class ProcessorTest extends JUnitSuite with Matchers {

  @Inject @ShortTermCredit var shortTermProcessor: Processor = _

  @Inject @LongTermCredit var longTermProcessor: Processor = _

  @Inject var dataStore : DataStore = _

  @Test
  def shouldProcessShortTermCredit():Unit = {

    val dataValues = List( new DataValue("television", 1199.0), new DataValue("tablet", 319.49) )
    val processors = List( shortTermProcessor, longTermProcessor)

    for ( processor <- processors; dataValue <- dataValues) {
      processor.process( dataValue )

      val result:(DataValue,BigDecimal) = dataStore.retrieve()
      result._1.productName should be (dataValue.productName)
      result._1.price should be (dataValue.price)
      result._2 should be ( processor.interestRate )
    }
  }
}

object ProcessorTest {
  @Deployment
  def createDeployment():WebArchive = {
    val war = ShrinkWrap.create(classOf[WebArchive], "test.war")
      .addPackage("uk.co.xenonique.digitalone")
      .addPackage("uk.co.xenonique.digitalone.inherit")
      .addAsLibrary( GradleDependency.resolve("org.scala-lang:scala-library:2.11.1") )
      .addAsLibrary( GradleDependency.resolve("org.scalatest:scalatest_2.11:2.2.0") )
      .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
    println(war.toString(true))
    war
  }

}
