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
/*  Program Name    : ActionSponsorOwnPlanList.java
 *  Created Date    : 
 *  Author          : Punitha.R
 *  Version         : 1.1
 *  CopyrightInformation:
    (c)2006,digiBlitz Technologies Inc/digiBlitz Technologies (P) Ltd. All rights reserved.
    916 W. Broad Street Suite 205, FallsChurch, VA 22046.
    
    This document is protected by copyright. No part of this document may be reproduced in any form by any means without
    prior written authorization of Sun and its licensors. if any.
 
    The information described in this document may be protected by one or more U.S.patents.foreign patents,or
    pending applications.
 */

package com.sponsor.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

import com.hlcspnr.sponsor.HLCSponsorSessionRemoteHome;
import com.hlcspnr.sponsor.HLCSponsorSessionRemote;
import com.hlcspnr.sponsor.*;
import com.hlcspnr.plan.*;
import com.hlcspnr.util.*;
import javax.naming.Context;
import javax.naming.*;
import java.util.Properties;
import java.util.*;

public class ActionSponsorOwnPlanList extends Action {
        
    private final static String success = "success";
    private final static String  LST = "lstAllPlan";
    private final static String  VIEW = "manupPlan";
    private final static String  MUlTIPLEPLAN ="myplan";
    private final static String  DESC ="planDet";
   
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try{
            Properties p =new Properties();
            p.setProperty( "java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory" );
            p.setProperty( "java.naming.factory.url.pkgs", "org.jboss.naming:org.jnp.interfaces" );
            p.setProperty( "java.naming.provider.url", "localhost:11199" );
            Context jndiContext = new InitialContext(p);
            Object obj=jndiContext.lookup("ejb/HLCSponsorSessionBean");
            HLCSponsorSessionRemoteHome home = (HLCSponsorSessionRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(obj,HLCSponsorSessionRemoteHome.class);
            HLCSponsorSessionRemote remote = home.create();
            
            String usrprocess = request.getParameter("usrprocess");
 //=========================================================SPONSOR PLAN LIST=============================================================            
        if(usrprocess.equals(LST)){
            Vector v = (Vector)remote.getAllPlanDetails();
             HttpSession hs = request.getSession(true);
             hs.setAttribute("showAllPlanDet",null);
             hs.setAttribute("showAllPlanDet",v);
             return mapping.findForward("frmSponsorOwnPlanList");
           }
 //=========================================================SPONSOR DETAIL PLAN LIST=============================================================                        
        if(usrprocess.equals(VIEW)){
            String planId = request.getParameter("planId");
            String plan[] = (String[])remote.getPlanDetails(planId);
            HttpSession hs1 = request.getSession(true);
            hs1.setAttribute("showPlanDesc",null);
            hs1.setAttribute("showPlanDesc",plan);
            return mapping.findForward("frmSponsorPlanDetails");
       }
        if(usrprocess.equals(MUlTIPLEPLAN)){
             Vector v2 = (Vector)remote.getAllPlanDetails();
             HttpSession hs = request.getSession(true);
             hs.setAttribute("planDet",null);
             hs.setAttribute("planDet",v2);
             return mapping.findForward("frmSponsorMyplan");
        } 
        if(usrprocess.equals(DESC)){
            return mapping.findForward("frmSponsorStatus");
        }
               
  //=========================================================TRY BLOCK END========================================================================                    
             }
  //=========================================================CATCH BLOCK=============================================================                    
        catch(Exception e){
            System.out.println("Exception while listing all plans to sponsor" + e);
        }
 //=========================================================TRY BLCOK END========================================================================                           
        return mapping.findForward(success);
        
    }
}
