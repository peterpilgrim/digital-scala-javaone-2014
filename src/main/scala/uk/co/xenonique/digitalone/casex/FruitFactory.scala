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

import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Produces
import scala.collection.immutable.HashSet
/**
 * The type FruitFactory
 *
 * @author Peter Pilgrim
 */
@ApplicationScoped
class FruitVendor {
  def sell( fruit: Fruit with Product with Serializable, quantity: Int): Double = {
    fruit.price * quantity
  }
}

trait Maker {
  def createSample(): Fruit
}

@ApplicationScoped
class FruitFactory {

  private class CocktailMaker extends Maker {
    override def createSample():Fruit = {
      new Strawberry
    }
  }

  private class FruitSaladMaker extends Maker {
    override def createSample():Fruit = {
      new Banana
    }
  }

  @Produces
  def generateCocktail(): Maker = {
    new CocktailMaker()
  }

  @Produces
  def generateFruitSalad(): Maker = {
    new FruitSaladMaker()
  }
}

trait Fruit {
  val price: Double;
  val name = this.getClass.getSimpleName
}

case class Apple() extends Fruit { override val price = 0.75 }
case class Kiwi() extends Fruit { override val price = 1.59 }
case class Banana() extends Fruit { override val price = 0.95 }
case class Grape() extends Fruit { override val price = 0.79 }
case class Lemon() extends Fruit { override val price = 0.49 }
case class Orange() extends Fruit { override val price = 0.60 }
case class Pear() extends Fruit { override val price = 1.79 }
case class Pineapple() extends Fruit { override val price = 3.79 }
case class Plum() extends Fruit { override val price = 1.49 }
case class Strawberry() extends Fruit { override val price = 2.99 }
case class Tangerine() extends Fruit { override val price = 1.67 }

