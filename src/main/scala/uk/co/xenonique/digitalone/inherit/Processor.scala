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

import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject


/**
 * The type Processor
 *
 * @author Peter Pilgrim
 */
trait Processor {
  val interestRate: BigDecimal
  def process( payload: DataValue ) : Unit
}

@ApplicationScoped
@ShortTermCredit
class DukeProcessor extends Processor {
  @Inject var dataStore: DataStore = _
  override val interestRate = BigDecimal(12.5);

  override def process( payload: DataValue): Unit = {
    dataStore.save( payload, interestRate)
  }
}


@ApplicationScoped
@LongTermCredit
class PlatinumProcessor extends Processor {
  @Inject var dataStore: DataStore = _
  override val interestRate = BigDecimal(5.79);

  override def process( payload: DataValue): Unit = {
    dataStore.save( payload, interestRate)
  }
}

case class DataValue( val productName: String, val price: BigDecimal)

@ApplicationScoped
class DataStore() {

  private var value: Option[DataValue] = None
  private var rate: BigDecimal = 0;

  def retrieve(): (DataValue, BigDecimal) = (value.get, rate);

  def save( payload: DataValue, interestRate: BigDecimal): Unit = {
    println(f"saving payload ${payload} at interest rate ${rate}%.2f%%")
    this.value = Some(payload)
    this.rate = interestRate
  }
}