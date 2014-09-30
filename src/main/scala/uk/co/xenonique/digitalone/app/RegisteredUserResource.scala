package uk.co.xenonique.digitalone.app

/** *****************************************************************************
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
  * -- Blog: http://www.xenonique.co.uk/blog/
  * -- Twitter: @peter_pilgrim
  *
  * Contributors:
  *
  * ******************************************************************************/
import javax.ejb.{EJB, Stateless}
import javax.ws.rs._

/**
 * The type RegisteredUserResource
 *
 * @author Peter Pilgrim (peter)
 */
@Path("/users")
@Stateless class RegisteredUserResource {

  @EJB private var userRegistry: UserRegistry = _

  @GET
  @Produces(Array("text/csv")) def listUsers: String = {
    printf("***DEBUG*** listUsers()\n")
    printf("    userRegistry=%s\n", userRegistry)
    val buf: StringBuilder = new StringBuilder
    for (user <- userRegistry.getUsers) {
      buf.append(user.loginName + "," + user.firstName + ",")
      buf.append(user.lastName + "," + user.secretCode + "\n")
    }
    return buf.toString
  }

  @GET
  @Path("{id}")
  @Produces(Array("text/csv")) def getUser(@PathParam("id") loginName: String): String = {
    printf("***DEBUG*** getUser( %s )\n", loginName)
    printf("    userRegistry=%s\n", userRegistry)
    val someUser = userRegistry.findUser(loginName)

    val buf: StringBuilder = new StringBuilder
    someUser match {
      case Some(user) =>
        buf.append(user.loginName + "," + user.firstName + ",")
        buf.append(user.lastName + "," + user.secretCode + "\n")
      case _ =>
    }
    return buf.toString
  }

  @POST
  @Path("{id}") def addUser(@PathParam("id") loginName: String, @FormParam("firstName") fname: String, @FormParam("lastName") lname: String, @FormParam("secretCode") code: Int) {
    printf("***DEBUG*** addUser( %s, %s, %s, %d)\n", loginName, fname, lname, code)
    printf("    userRegistry=%s\n", userRegistry)
    val user: User = new User(loginName, fname, lname, code)
    userRegistry.addUser(user)
  }

  @PUT
  @Path("{id}") def amendUser(@PathParam("id") loginName: String, @FormParam("firstName") fname: String, @FormParam("lastName") lname: String, @FormParam("secretCode") code: Int) {
    printf("***DEBUG*** amendUser( %s, %s, %s, %d)\n", loginName, fname, lname, code)
    printf("    userRegistry=%s\n", userRegistry)
    val someUser = userRegistry.findUser(loginName)
    someUser match {
      case Some(user) =>
        userRegistry.removeUser(user)
        userRegistry.addUser( User(user.loginName, fname, lname, code))
      case _ =>
        throw new Exception("unknown login name: [" + loginName + "]")
    }
  }

  @DELETE
  @Path("{id}") def deleteUser(@PathParam("id") loginName: String) {
    printf("***DEBUG*** deleteUser( %s )\n", loginName)
    printf("    userRegistry=%s\n", userRegistry)
    val someUser = userRegistry.findUser(loginName)
    someUser match {
      case Some(user) =>
        userRegistry.removeUser(user)
      case _ =>
        throw new Exception("unknown login name: [" + loginName + "]")
    }
  }

}


class UnknownUserException extends Exception