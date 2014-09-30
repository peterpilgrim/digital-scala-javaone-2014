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

import java.io.File

import org.junit.{Assert, Test}

/**
 * The type GradleDependencyTest
 *
 * @author Peter Pilgrim
 */
class GradleDependencyTest {

  @Test
  def findFileRecursively(): Unit =  {
    val sourceFile = this.getClass.getSimpleName + ".scala"
    println(sourceFile)
    val dir = new File( System.getProperty("user.dir") + File.separator + "src")
    println(dir)
    val result = GradleDependency.find(dir, sourceFile)
    println( result )
    result match {
      case Some(f) => Assert.assertTrue( f.getName().endsWith(sourceFile))
      case _ => Assert.fail(s"failed to find $sourceFile")
    }
  }


  @Test
  def resolveScalaLibrary(): Unit =  {
    val gav = "org.scala-lang:scala-library:2.11.1"
    val result = GradleDependency.resolve(gav)
    println( result )
  }
}
