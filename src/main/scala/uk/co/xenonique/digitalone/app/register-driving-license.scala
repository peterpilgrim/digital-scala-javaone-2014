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
import javax.enterprise.context.{Conversation, ConversationScoped}
import javax.inject.{Inject, Named}
import javax.validation.constraints._

import scala.beans.BeanProperty

@Named("wizard")
@ConversationScoped
class RegisterDrivingLicense extends Serializable {
  @Inject
  var conversation: Conversation = _;

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

  def jumpGettingStarted(): String = {
    beginConversation()
    "form1?faces-redirect=true"
  }

  def submitPage1(): String = {
    beginConversation()

    println("submitPage1() ********")
    println("  email="+email)
    println("  title="+title)
    println("  firstName="+firstName)
    println("  lastName="+lastName)

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

    "form3?faces-redirect=true"
  }

  def submitPage3(): String = {
    println("submitPage3() ********")
    println("  street1="+street1)
    println("  street2="+street2)
    println("  cityTown="+cityTown)
    println("  region="+region)
    println("  postalCode="+postalCode)

    endConversation()

    "declaration?faces-redirect=true"
  }

  def submitDeclarationPage(): String = {
    endConversation()
    if ( !declaration ) {
      // The declaration was not signed
      cancelApplication()
    }
    else {
      // Here the form is complete
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
}


case class KeyValue[U,V]( @BeanProperty var key: U, @BeanProperty val value: V ) {

}