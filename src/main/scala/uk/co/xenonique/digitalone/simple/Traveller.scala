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

import java.util.Date

import scala.beans.BeanProperty

/**
 * The type Traveller
 *
 * @author Peter Pilgrim
 */
class Traveller(
  @BeanProperty
  var givenName: String,
  @BeanProperty
  var familyName: String,
  @BeanProperty
  var dateOfBirth: Date ,
  @BeanProperty
  var docNo: String ) {

  override def toString(): String = {
    s"Traveller( familyName = $familyName, givenName=$givenName, dateOfBirth=$dateOfBirth, docNo=$docNo )"
  }
}
