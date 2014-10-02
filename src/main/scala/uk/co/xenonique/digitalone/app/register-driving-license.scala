package uk.co.xenonique.digitalone.app

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

import java.util.Date
import javax.ejb.Stateless
import javax.enterprise.context.{Conversation, ConversationScoped}
import javax.faces.context.FacesContext
import javax.inject.{Inject, Named}
import javax.jms._
import javax.validation.constraints._

import uk.co.xenonique.digitalone.jms.Order

import scala.beans.BeanProperty

@Named("wizard")
@ConversationScoped
class RegisterDrivingLicense extends Serializable {
  @Inject
  var conversation: Conversation = _;

  @BeanProperty var applicationReferenceId: String = "XEN-" + RegisterDrivingLicense.randomAlpha(3) + "-" +
    RegisterDrivingLicense.randomAlphaNumericString(3) + "-" + RegisterDrivingLicense.randomAlphaNumericString(3)

  @BeanProperty
  @NotNull
  @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                  + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
  var email: String = _

  @BeanProperty
  var title: String = _

  @BeanProperty
  var firstName: String = _

  @BeanProperty
  var lastName: String = _

  @BeanProperty
  var drivingLicense: String = _

  /** Date of registration - day */
  @BeanProperty
  @Min(value = 1)
  @Max(value = 31)
  var dotrDay: Int = _

  /** Date of registration - month */
  @BeanProperty
  @Min(value = 1)
  @Max(value = 12)
  var dotrMonth: Int = _

  /** Date of registration - year */
  @BeanProperty
  @NotNull
  var dotrYear: String = _

  @BeanProperty
  @Past
  var registrationDate: Date = _

  // Address
  @BeanProperty var street1: String = _
  @BeanProperty var street2: String = _
  @BeanProperty var cityTown: String = _
  @BeanProperty var region: String = _
  @BeanProperty var postalCode: String = _

  @BeanProperty
  var declaration: Boolean = _


  @Inject var submitter: RegisteredOrderSubmitter = _


  def jumpGettingStarted(): String = {
    beginConversation()
    println(s"(1) +++++++++++++ applicationReference=$applicationReferenceId")
    "form1?faces-redirect=true"
  }

  def submitPage1(): String = {
    beginConversation()

    println("submitPage1() ********")
    println("  email="+email)
    println("  title="+title)
    println("  firstName="+firstName)
    println("  lastName="+lastName)
    println(s"(2) +++++++++++++ applicationReference=$applicationReferenceId")

    "form2?faces-redirect=true"
  }

  def submitPage2(): String = {
    println("submitPage2() ********")
    println("  drivingLicense="+drivingLicense)
    println("  dotrDay="+dotrDay+", dotrMonth="+dotrMonth+", dotrYear="+dotrYear)

    import java.util.Calendar

    val cal = Calendar.getInstance(RegisterDrivingLicense.en)
    cal.set(Calendar.DAY_OF_MONTH, dotrDay)
    cal.set(Calendar.MONTH, dotrMonth)

    val year = Integer.parseInt(dotrYear)
    cal.set(Calendar.YEAR, year)

    registrationDate = cal.getTime
    println(s"(3) +++++++++++++ applicationReference=$applicationReferenceId")

    "form3?faces-redirect=true"
  }

  def submitPage3(): String = {
    println("submitPage3() ********")
    println("  street1="+street1)
    println("  street2="+street2)
    println("  cityTown="+cityTown)
    println("  region="+region)
    println("  postalCode="+postalCode)

    println(s"(4) +++++++++++++ applicationReference=$applicationReferenceId")

    "declaration?faces-redirect=true"
  }

  def submitDeclarationPage(): String = {
    if ( !declaration ) {
      // The declaration was not signed
      cancelApplication()
    }
    else {
      // Here the form is complete
      println(s"(5) +++++++++++++ applicationReference=$applicationReferenceId")
      submitter.sendOrder(
        s"""
         |==== NEW DRIVING LICENSE REGISTRATION ====
         |Application Order: ${applicationReferenceId}
         |Title: ${title}
         |Given name: ${firstName}
         |Family name: ${lastName}
         |Email: ${email}
         |Registration Date: ${dotrDay}-${dotrMonth}-${dotrYear}
         |Street1: ${street1}
         |Street2: ${street2}
         |City/Town: ${cityTown}
         |Region: ${region}
         |Postal Code: ${postalCode}
         |Submission Date: ${new Date()}
       """.stripMargin)

      // Store the submitted id into a flash scope
      val context = FacesContext.getCurrentInstance();
      context.getExternalContext().getFlash().put("lastApplicationReference", applicationReferenceId );

      endConversation()

      "end?faces-redirect=true"
    }
  }

  def cancelApplication(): String = {
    endConversation()

    "cancel?faces-redirect=true"
  }

  def beginConversation(): Unit =
  {
    if (conversation.isTransient()) {
      conversation.begin()
    }
  }

  def endConversation(): Unit =
  {
    if (!conversation.isTransient()) {
      conversation.end()
    }
  }

  def daysOfTheMonth: java.util.List[Int] = {
    import scala.collection.JavaConversions._

    val javaList: java.util.List[Int]  = (1 to 31).toList
    javaList
  }

  def monthsOfTheYear11: java.util.TreeMap[String,Int] = {
    import uk.co.xenonique.digitalone.app.RegisterDrivingLicense._
    val scalaMap = (1 to 12).toList.map{ x => (symbols.getMonths()(x-1), x) }.toMap
    val javaMap = new  java.util.TreeMap[String,Int]()
    for ( ( k,v) <- scalaMap ) {
      javaMap.put( k,v )
    }
    javaMap
  }

  def monthsOfTheYear: java.util.Map[String,Int] = {
    import uk.co.xenonique.digitalone.app.RegisterDrivingLicense._
    val scalaList = (1 to 12).toList.map{ x => (symbols.getMonths()(x-1), x) }
    val javaMap = new java.util.LinkedHashMap[String,Int]()
    for ((k,v) <- scalaList ) {
      javaMap.put(k,v)
    }
    javaMap
  }


}

object RegisterDrivingLicense {
  val hr = new java.util.Locale("hr", "HR")
  val en = new java.util.Locale("en_gb", "EN")
  val symbols = new java.text.DateFormatSymbols( en )

  // See    http://alvinalexander.com/scala/creating-random-strings-in-scala

  // 6 - random alphanumeric
  def randomAlphaNumericString(length: Int): String = {
    val chars = ('a' to 'z') ++ ('A' to 'Z') ++ ('0' to '9')
    randomStringFromCharList(length, chars)
  }

  // 7 - random alpha
  def randomAlpha(length: Int): String = {
    val chars = ('a' to 'z') ++ ('A' to 'Z')
    randomStringFromCharList(length, chars)
  }

  // used by #6 and #7
  def randomStringFromCharList(length: Int, chars: Seq[Char]): String = {
    val sb = new StringBuilder
    for (i <- 1 to length) {
      val randomNum = util.Random.nextInt(chars.length)
      sb.append(chars(randomNum))
    }
    sb.toString
  }
}


case class KeyValue[U,V]( @BeanProperty var key: U, @BeanProperty val value: V ) {

}

/**
 * See the code in JMS2ResourceProducer.scala (or JMSResourceProducer.scala for JMS 1.1)
 */
@Stateless
class RegisteredOrderSubmitter {

  // See the JMSResourceProducer.scala the CDI container will inject these properties
  @Inject @Order var orderQueue: Queue = _

  // Uncomment for old JMS 1.1 style code
  // @Inject @Order var orderQueueSession: QueueSession = _
//
//  def sendOrder_JMS1_1(text: String): Unit = {
//    val sender:QueueSender = orderQueueSession.createSender(orderQueue)
//    val message:TextMessage = orderQueueSession.createTextMessage()
//    message.setText(text);
//    sender.send(message)
//  }

  // Uncomment for the new JMS 2.0 style code
  @Inject @Order var producer: JMSProducer = _

  // JMS 2.0 is easier. CDI will produce the necessary JMS Producer
  def sendOrder(text: String): Unit = {
    producer.send( orderQueue, text )
  }
}