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

package uk.co.xenonique.digitalone.provider

import javax.ws.rs.ext.Provider
import javax.ws.rs.{Produces, Consumes}
import javax.ws.rs.core.MediaType
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import javax.inject.Singleton
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider
import com.fasterxml.jackson.databind.{DeserializationFeature, ObjectMapper}
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper
import com.fasterxml.jackson.annotation.JsonInclude.Include

/**
 * Inspired shamelessly from http://code.google.com/p/boilermaker/source/browse/src/main/scala/com/daveclay/boilermaker/web/providers/JacksonScalaContextResolver.scala
 *
 * @author Peter Pilgrim
 */
@Singleton
@Provider
@Consumes(Array(MediaType.APPLICATION_JSON, "application/*+json", "text/json"))
@Produces(Array(MediaType.APPLICATION_JSON, "application/*+json", "text/json"))
class JacksonScalaContextResolver extends JacksonJaxbJsonProvider(JacksonScalaContextResolver.getObjectMapper, JacksonJaxbJsonProvider.DEFAULT_ANNOTATIONS)

object JacksonScalaContextResolver {
  def getObjectMapper: ObjectMapper = {
    val mapper = new ObjectMapper with ScalaObjectMapper
    mapper.registerModule(new DefaultScalaModule)
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    mapper.setSerializationInclusion(Include.NON_NULL)
    mapper
  }
}
