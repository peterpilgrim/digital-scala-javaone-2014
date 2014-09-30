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

package uk.co.xenonique.digitalone.servlet

import java.util.Date
import javax.servlet.annotation.WebServlet
import javax.servlet.http.{HttpServlet, HttpServletResponse, HttpServletRequest}

/**
 * The type SimpleServlet
 *
 * @author Peter Pilgrim
 */
@WebServlet(Array("/simple"))
class SimpleServlet extends HttpServlet {
   override def doGet(req: HttpServletRequest,
                      resp: HttpServletResponse): Unit = {
     resp.setContentType("application/json");
     val writer = resp.getWriter();
     writer.println(
       s"""
          |{
          |    "name": "Two Tribes",
          |    "class": "${this.getClass().getName}",
          |    "date": "${new Date()}"
          |}
        """.stripMargin )
   }
}
