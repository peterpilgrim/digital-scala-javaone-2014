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

import com.fasterxml.jackson.annotation.{JsonIgnoreProperties, JsonProperty}

import scala.beans.BeanProperty

/**
 * The type Traveller
 *
 * @author Peter Pilgrim
 */
@JsonIgnoreProperties(Array("id", "computedDeliveryDateTime"))
case class Traveller3(
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
  var docNo: Option[String],

  @BeanProperty
  var computedDeliveryDateTime: Date = new Date()) {

}
