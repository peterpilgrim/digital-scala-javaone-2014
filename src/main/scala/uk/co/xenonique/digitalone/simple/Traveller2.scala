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

import com.fasterxml.jackson.annotation.{JsonProperty, JsonIgnoreProperties}

/**
 * The type Traveller
 *
 * @author Peter Pilgrim
 */
class Traveller2(
  @BeanProperty
  @JsonProperty("givenName")
  var givenName: String,

  @BeanProperty
  @JsonProperty("familyName")
  var familyName: String,

  @BeanProperty
  @JsonProperty("dateOfBirth")
  var dateOfBirth: Date ,

  @BeanProperty
  @JsonProperty("documentNo")
  var docNo: String ) {

  override def toString(): String = {
    s"Traveller( familyName = $familyName, givenName=$givenName, dateOfBirth=$dateOfBirth, docNo=$docNo )"
  }
}
