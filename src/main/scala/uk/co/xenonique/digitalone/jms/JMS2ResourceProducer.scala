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
import javax.inject.Inject
import javax.jms._

/**
 * The type JMS2ResourceProducer using the JMS 2.0 API in Java EE 7
 *
 * @author Peter Pilgrim
 */
@ApplicationScoped
class JMS2ResourceProducer {
  @Inject
  @JMSConnectionFactory( "jms/OrderQueueConnectionFactory")
  private var context: JMSContext = _;

  @Produces @Order
  @Resource(mappedName = "jms/OrderQueue")
  var orderQueue: Queue = _

  @Produces @Order
  def createProducer(): JMSProducer = {
    context.createProducer()
  }
}
