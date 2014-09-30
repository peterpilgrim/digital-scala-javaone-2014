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

package uk.co.xenonique.digitalone

import java.io.{FileNotFoundException, File}

/**
 * The type GradleDependency
 *
 * @author Peter Pilgrim
 */
object GradleDependency {

  def find( dir: File, name: String): Option[File] = {
    val file = new File(dir, name )
    if ( file.exists) {
      Some(file)
    }
    else {
      for ( e <- dir.listFiles() ) {
        if ( e.isDirectory() && !e.getName().equals(".")) {
          val f = find(e, name)
          if ( f.isDefined) {
            return f
          }
        }
      }
      None
    }
  }

  def resolve( gav: String ): File = {
    // ls ~/.gradle/caches/modules-2/files-2.1/

    val gradleCache = new File(
      System.getProperty("user.home") +
      File.separator + ".gradle" + File.separator + "caches" + File.separator + "modules-2" +
      File.separator +"files-2.1")

    if ( !gradleCache.exists()) {
        throw new FileNotFoundException("Gradle cache does not exist ")
    }
    val list = gav.split(":")
    val group = list(0)
    val artifactId = list(1)
    val version = list(2)

    println(gradleCache)
    find(gradleCache, artifactId + "-" + version + ".jar") match {
      case Some(f) => f
      case _ => throw new FileNotFoundException(s"unable to resolve $gav")
    }
  }
}
