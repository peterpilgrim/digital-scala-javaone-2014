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

package uk.co.xenonique.digitalone.app

import javax.annotation.{PostConstruct, PreDestroy}
import javax.ejb.{Singleton, Startup}

/**
 * The type User
 *
 * @author Peter Pilgrim
 */
case class User( loginName: String, firstName: String, lastName: String, secretCode: Int )

@Startup
@Singleton
class UserRegistry {

  private var registeredUsers = Map.empty[String,User]

  def addUser( user: User ):Unit = {
    registeredUsers += ( user.loginName -> user )
  }

  def removeUser( user: User): Unit = {
    registeredUsers -= user.loginName
  }

  def findUser( loginName: String ): Option[User] = {
    registeredUsers.get(loginName)
  }

  def getUsers(): List[User] = {
    registeredUsers.toSeq.sortWith ( _._1 < _._1 ) map ( _._2) toList
  }

  @PostConstruct def acquireResource {
    println(this.getClass.getSimpleName + "#acquireResource()")

    addUser( User( "jobs", "Steve", "Jobs", 1955 ))
    addUser( User( "mandela", "Nelson", "Mandela", 1918 ))
    addUser( User( "ordersky", "Martin", "Odersky", 1958 ))
    addUser( User( "giggs", "Ryan", "Giggs", 1973  ))
    addUser( User( "gosling", "James", "Gosling", 1955 ))
  }

  @PreDestroy def releaseResource {
    println(this.getClass.getSimpleName + "#releaseResource()")
  }


}
