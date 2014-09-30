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

package uk.co.xenonique.digitalone.simple

import java.util.{Date, Calendar}

import org.junit.runner.RunWith
import org.scalatest.FlatSpec
import org.scalatest.junit.JUnitRunner
import org.scalatest.Matchers


/**
 * Verifies the operation of the TravellerSpec
 *
 * @author Peter Pilgrim
 */

@RunWith(classOf[JUnitRunner])
class TravellerSpec extends FlatSpec with Matchers {

  def generateDob( year: Int, month: Int, day: Int) : Date = {
    val cal = Calendar.getInstance()
    cal.set(year, month, day)
    cal.getTime()
  }

  "A traveller" should "be accessible as a POJO" in {

    val t1 = new Traveller( "Kenneth", "Wayne", generateDob(1977, Calendar.JULY, 16), "52873915" )
    println( s"t1=$t1")

    t1.getFamilyName() should be === "Wayne"
    t1.getGivenName() should be === "Kenneth"
//    t1.getDateOfBirth() should be === generateDob(1977, Calendar.JULY, 16)
    t1.getDocNo() should be === "52873915"

    t1.setFamilyName("Smith")
    t1.setGivenName("Candice")
    t1.setDocNo("78124053")
    println( s"t1=$t1")

    t1.getFamilyName() should be === "Smith"
    t1.getGivenName() should be === "Candice"
    //    t1.getDateOfBirth() should be === generateDob(1977, Calendar.JULY, 16)
    t1.getDocNo() should be === "78124053"
  }
}
