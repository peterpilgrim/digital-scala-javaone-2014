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
import javax.ws.rs.core._
import javax.ws.rs.core.Response.Status._
import javax.ws.rs._

import uk.co.xenonique.digitalone.simple.{Traveller3, Traveller2}


/**
  * The type TravellerServiceEndpoint
  *
  * @author Peter Pilgrim
  */
@Path("/traveller2")
class TravellerServiceEndpoint2 {

   @GET()
   @Produces(Array(MediaType.APPLICATION_JSON))
   def retrieve(): Response = {
     val cal = Calendar.getInstance()
     cal.set(1975, Calendar.SEPTEMBER, 19 )
     val dob = cal.getTime
     val traveller = Traveller3("Janet", "Henderson", dob, Some("27053649143"))
     Response.ok(traveller).build()
   }

   @POST()
   @Consumes(Array(MediaType.APPLICATION_JSON))
   def store( traveller: Traveller3): Response = {
     println(s"traveller = $traveller")
     if (!traveller.getDocNo().isDefined) {
       Response.status(
         Response.Status.BAD_REQUEST)
         .entity(ErrorMessage("E199", "Missing document No"))
         .build()
     }
     else {
      Response.ok().build()
     }
   }


 }
