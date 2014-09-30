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

package uk.co.xenonique.digitalone.payments

import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

import uk.co.xenonique.digitalone.model.Account

import scala.beans.BeanProperty

/**
 * The type Payment
 *
 * @author Peter Pilgrim
 */
class Payment {

}

@ApplicationScoped
class CashierService
{
  @BeanProperty @Inject() var service: PaymentService = _

  def empty(): Unit = {}
  def process( acc: Account, amount: BigDecimal): Unit = {
    service.debit(amount);
    acc.balance = acc.balance - amount
  }
}

@ApplicationScoped
class PaymentService {

  var message: String = _

  def debit(amount: BigDecimal): Unit = {
    message = s"You paid ${amount}"
  }
}

