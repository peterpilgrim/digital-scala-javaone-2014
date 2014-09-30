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

package uk.co.xenonique.digitalone.casex

import javax.inject.Inject

import org.jboss.arquillian.container.test.api.Deployment
import org.jboss.arquillian.junit.Arquillian
import org.jboss.shrinkwrap.api.ShrinkWrap
import org.jboss.shrinkwrap.api.asset.EmptyAsset
import org.jboss.shrinkwrap.api.spec.WebArchive
import org.junit.Test
import org.junit.Assert._
import org.junit.runner.RunWith
import uk.co.xenonique.digitalone.GradleDependency

/**
 * The type FruitTest
 *
 * @author Peter Pilgrim
 */
@RunWith(classOf[Arquillian])
class FruitTest {

  @Inject
  var fruitVendor: FruitVendor = _

  @Test
  def shouldPriceFruitWithQuantity(): Unit = {
    val fruits = List((Apple,2), (Orange,4), (Banana,5))

    for ( (fruit, quantity) <- fruits) {
      val prodPrice = fruitVendor.sell(fruit(), quantity)
      println(s"********  $quantity, $fruit")
      assertEquals( prodPrice, fruit().price * quantity, 0.01 )
    }

    val result = fruitVendor.sell( Orange(), 4 )
    println( f"+++++++++ FRUIT +++ ${result}" );
    assertEquals( 2.40, result, 0.001 )
  }

  @Inject
  var fruitFactory: FruitFactory = _

  @Test
  def generateFruitCocktailMaker(): Unit = {
    val result = fruitFactory.generateCocktail().createSample()
    assertEquals( "Strawberry", result.name)
    assertTrue( result.isInstanceOf[Strawberry])
  }

  @Test
  def generateFruitSaladMaker(): Unit = {
    val result = fruitFactory.generateFruitSalad().createSample()
    assertEquals( "Banana", result.name)
    assertTrue( result.isInstanceOf[Banana])
  }
}

object FruitTest {
  @Deployment
  def createDeployment():WebArchive = {
    val war = ShrinkWrap.create(classOf[WebArchive], "test.war")
      .addPackage("uk.co.xenonique.digitalone")
      .addPackage("uk.co.xenonique.digitalone.casex")
      .addAsLibrary( GradleDependency.resolve("org.scala-lang:scala-library:2.11.1") )
      .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    println(war.toString(true));
    return war;
  }

}