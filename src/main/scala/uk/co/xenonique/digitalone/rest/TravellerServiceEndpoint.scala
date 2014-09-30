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

package uk.co.xenonique.digitalone.rest

import java.util.Calendar
import javax.ws.rs.{Produces, Path, GET}
import javax.ws.rs.core._

import uk.co.xenonique.digitalone.simple.Traveller2


/**
 * The type TravellerServiceEndpoint
 *
 * @author Peter Pilgrim
 */
@Path("/traveller")
class TravellerServiceEndpoint {

  @GET()
  @Produces(Array(MediaType.APPLICATION_JSON))
  def retrieve(): Response = {
    val cal = Calendar.getInstance()
    cal.set(1986, Calendar.MAY, 21)
    val dob = cal.getTime
    val traveller = new Traveller2("Sam", "Smith", dob, "1234567890")
    Response.ok(traveller).build()
  }
}
