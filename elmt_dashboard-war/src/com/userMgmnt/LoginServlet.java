/*******************************************************************************
 * * * Copyright: 2019 digiBlitz Foundation
 *  * * 
 *  * * License: digiBlitz Public License 1.0 (DPL) 
 *  * * Administered by digiBlitz Foundation. www.digiblitz.org/dpl/
 *  * * 
 *  * * Inventor: Suresh Kannan (Maya Suresh Kannan Balabisegan ) (www.sureshkannan.org)
 *  * * 
 *  * * Authors: Suresh Kannan (Maya Suresh Kannan Balabisegan )& digiBlitz.
 *  * * 
 *  * * "Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software in accordance to the rules & restrictions of the digiBlitz Public License."
 ******************************************************************************/
package com.userMgmnt;

import com.hlcrole.management.HLCBrahmaputraSessionRemote;
import com.hlcrole.management.HLCBrahmaputraSessionRemoteHome;
import com.um.session.BeasRemote;
import com.um.session.BeasRemoteHome;
import java.io.IOException;
import java.util.ArrayList;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet
{
  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    processRequest(request, response);

    String userId = request.getParameter("userId");
    String password = request.getParameter("password");
    String result = null;
    try
    {
      Context context = new InitialContext();
      Object object = context.lookup("BeasBean");

      BeasRemoteHome beasRemoteHome = (BeasRemoteHome)PortableRemoteObject.narrow(object, BeasRemoteHome.class);
      BeasRemote beasRemote = beasRemoteHome.create();

      System.setProperty("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
      System.setProperty("java.naming.provider.url", "jnp://");
      Context jndiContext = new InitialContext();
      Object objref = jndiContext.lookup("ejb/HLCBrahmaputraSessionRemoteHome");

      HttpSession session = request.getSession(true);

      HLCBrahmaputraSessionRemoteHome roleHome = (HLCBrahmaputraSessionRemoteHome)PortableRemoteObject.narrow(objref, HLCBrahmaputraSessionRemoteHome.class);
      HLCBrahmaputraSessionRemote roleRemote = roleHome.create();

      result = beasRemote.validateUser(userId, password);
      String userName = roleRemote.getUserIdBasedOnUserName(userId);

      ArrayList roleId = roleRemote.getAllRolesBasedOnUser(userName);
      String[] arr = (String[])(String[])roleId.get(0);
      String tingTong = arr[2];
      String roleNm = arr[3];

      if ((result.equals("valid")) && (roleNm.equals("user")))
      {
        ArrayList entityList = roleRemote.getMappingDetailsForRoleAndPrivileges(arr[2]);
        session.setAttribute("entityList", entityList);

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/homePage.jsp");
        rd.forward(request, response);
      }
      else if ((result.equals("valid")) && (roleNm.equals("admin")))
      {
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/adminHomePage.jsp");
        rd.forward(request, response);
      }
      else if (result.equals("invalidUserIdPwd")) {
        request.setAttribute("msg", "invalidUserIdPwd");
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/XYZ-IED.jsp");
        rd.forward(request, response);
      }
    }
    catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    processRequest(request, response);
  }

  public String getServletInfo()
  {
    return "Short description";
  }
}
