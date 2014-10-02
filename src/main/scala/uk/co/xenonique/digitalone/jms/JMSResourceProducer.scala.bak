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

package uk.co.xenonique.digitalone.jms

import javax.annotation.Resource
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.{Disposes, Produces}
import javax.jms._

import scala.beans.BeanProperty

/**
 * The type JMSResourceProducer
 *
 * @author Peter Pilgrim
 */
@ApplicationScoped
class JMSResourceProducer {
  @Resource(name = "jms/OrderQueueConnectionFactory")
  var orderConnectionFactory: QueueConnectionFactory = _

  @Produces @Order
  @Resource(mappedName = "jms/OrderQueue")
  var orderQueue: Queue = _

  @Produces @Order
  def createOrderConnection(): QueueConnection = orderConnectionFactory.createQueueConnection()

  @Produces @Order
  def createOrderSession(@Order conn: QueueConnection): QueueSession = {
    conn.createQueueSession(true, Session.AUTO_ACKNOWLEDGE)
  }

  def closeOrderSession(@Disposes @Order conn: QueueConnection): Unit = {
    conn.close()
  }

  def closeOrderSession(@Disposes @Order session: QueueSession): Unit = {
    session.close()
  }
}
