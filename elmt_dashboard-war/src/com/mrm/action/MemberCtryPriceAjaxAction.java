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
package com.mrm.action;
/*
 * MembershipRegistrationAction.java
 *
 * Created on August 24, 2006, 3:10 PM
 */

import com.hlcHorse.Form.Display.HLCkaverySessionBeanStatlessRemote;
import com.hlcHorse.Form.Display.HLCkaverySessionBeanStatlessRemoteHome;
import com.hlccommon.util.Debug;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import java.util.*;
import java.text.*;
import org.apache.struts.util.*;

/**
 *
 * @author karthikeyan
 * @version
 */

public class MemberCtryPriceAjaxAction extends Action {
    
    
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
             //set the content type
            response.setContentType("text/xml");

            response.setHeader("Cache-Control", "no-cache");

            //get the PrintWriter object to write the html page
            PrintWriter writer = response.getWriter();
                
                   
            
        try {
                   
                    MessageResources mr=getResources(request);
                   
                    String namingfactory=mr.getMessage("ejbclient.namingfactory");
                    String contextfactory=mr.getMessage("ejbclient.contextfactory");
                    String urlprovider=mr.getMessage("ejbclient.urlprovider");
                    String lookupip=mr.getMessage("ejbclient.ip");
                    String jndiname=mr.getMessage("jndi.kaverysless");
            
                    Debug.print("MemberCtryPriceAjaxAction servlet called .........");
                                      
                    System.setProperty(namingfactory,contextfactory);
                    System.setProperty(urlprovider,lookupip);
                    Context jndiContext = new InitialContext();
                    Object objref = jndiContext.lookup(jndiname);
                                     
                    HLCkaverySessionBeanStatlessRemoteHome home =(HLCkaverySessionBeanStatlessRemoteHome) PortableRemoteObject.narrow(objref, HLCkaverySessionBeanStatlessRemoteHome.class);
                                      
                    HLCkaverySessionBeanStatlessRemote remote = home.create();
                    
                    String memTypId=request.getParameter("memTypId");
                    String ctryName=request.getParameter("ctryName");
                    
                    Debug.print("memTypId :"+memTypId);
                    Debug.print("ctryName :"+ctryName);
                    String ctryPrice="";           
                    
                    if(ctryName.trim().length()!=0)
                    {                       
                    
                    if(ctryName.equalsIgnoreCase("Canada") || ctryName.equalsIgnoreCase("Mexico"))
                    {
                         Debug.print("Inside Canada or Mexico block :");
                        ctryPrice=remote.getCountryPrice(memTypId,ctryName);
                        
                    }
                    else if(ctryName.equalsIgnoreCase("USA"))
                    {
                         Debug.print("Inside USA block :");
                        ctryPrice="0";
                    }
                    else
                    {
                         Debug.print("Inside Others block :");
                        ctryPrice=remote.getCountryPrice(memTypId,"Others");
                       
                    }
                    }
                    
                Debug.print("ctryPrice :"+ctryPrice);
                
                 writer.println("<ctryPrice><![CDATA[" + ctryPrice + "]]></ctryPrice>");
            
            //close the write
            writer.close();
                
        }
    
        catch(Exception e)
        {
		System.out.println("client exception :" +e);
	}
                
     return null;    
        
    }
}
