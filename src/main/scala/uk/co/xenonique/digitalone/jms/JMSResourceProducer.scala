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
