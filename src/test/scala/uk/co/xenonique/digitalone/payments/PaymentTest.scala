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

import java.io.{FileNotFoundException, File}
import javax.inject.Inject

import org.junit.{Assert, Test}
import org.junit.runner.RunWith
import org.jboss.arquillian.container.test.api.Deployment
import org.jboss.arquillian.junit.Arquillian
import org.jboss.shrinkwrap.api.ShrinkWrap
import org.jboss.shrinkwrap.api.asset.EmptyAsset
import org.jboss.shrinkwrap.api.spec.JavaArchive
import org.jboss.shrinkwrap.api.spec.WebArchive
import uk.co.xenonique.digitalone.GradleDependency
import uk.co.xenonique.digitalone.model.Account


/**
 * The type PaymentTest
 *
 * @author Peter Pilgrim
 */
@RunWith(classOf[Arquillian])
class PaymentTest {

  @Inject
  var cashierService: CashierService = _
  @Inject
  var paymentService: PaymentService = _

  @Test
  def cashierProcessPaymentService(): Unit = {
    val account = new Account( 10000 )
    cashierService.process( account, 5000 )
    println(paymentService.message)
    Assert.assertEquals("You paid 5000", paymentService.message)
  }

}

object PaymentTest {
  @Deployment
  def createDeployment():WebArchive = {
    val war = ShrinkWrap.create(classOf[WebArchive], "test.war")
     .addPackage("uk.co.xenonique.digitalone")
     .addPackage("uk.co.xenonique.digitalone.model")
     .addPackage("uk.co.xenonique.digitalone.payments")
//      .addAsLibrary( new File("/Users/peterpilgrim/.gradle/caches/modules-2/files-2.1/org.scala-lang/scala-library/2.11.1/e11da23da3eabab9f4777b9220e60d44c1aab6a/scala-library-2.11.1.jar") )
      .addAsLibrary( GradleDependency.resolve("org.scala-lang:scala-library:2.11.1") )
      .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    println(war.toString(true));
    return war;
  }
}

