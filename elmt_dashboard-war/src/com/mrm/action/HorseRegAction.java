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

/*  Program Name    : HorseRegAction.java
 *  Created Date    : August 22, 2006, 10:42 AM
 *  Author          : Punitha.R & Karthikeyan
 *  Version         : 1.8
 *  CopyrightInformation:
(c)2006,digiBlitz Technologies Inc/digiBlitz Technologies (P) Ltd. All rights reserved.
916 W. Broad Street Suite 205, FallsChurch, VA 22046.
This document is protected by copyright. No part of this document may be reproduced in any form by any means without
prior written authorization of Sun and its licensors. if any.
The information described in this document may be protected by one or more U.S.patents.foreign patents,or
pending applications.
 */
import com.hlcHorse.Form.Display.HLCkaverySessionBeanStatlessRemote;
import com.hlcHorse.Form.Display.HLCkaverySessionBeanStatlessRemoteHome;
import com.hlcevent.edu.HLCEducationalSessionRemote;
import com.hlcevent.edu.HLCEducationalSessionRemoteHome;
import com.hlckavery.stateless.HLCkaveryStatelessRemote;
import com.hlckavery.stateless.HLCkaveryStatelessRemoteHome;
import com.hlckavery.statful.HLCKaverySessionBeanStatfulRemote;
import com.hlckavery.statful.HLCKaverySessionBeanStatfulRemoteHome;
import com.hlcmrm.util.Debug;
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
import javax.naming.*;
import org.apache.struts.util.*;
import com.hlccommon.util.HLCHorseRegisterationVO;
import com.hlcform.stateless.HLCkaverystatelessRemote;
import com.hlcform.stateless.HLCkaverystatelessRemoteHome;

public class HorseRegAction extends Action {

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String namingfactory, contextfactory, urlprovider, lookupip, jndiname, jndiname2;
        Context jndiContext = new InitialContext();

        try {

            /*
             * Service Locator for ejb/HLCkaverySessionBeanStatlessBean JNDI
             */

            MessageResources mr = getResources(request);

            namingfactory = mr.getMessage("ejbclient.namingfactory");
            contextfactory = mr.getMessage("ejbclient.contextfactory");
            urlprovider = mr.getMessage("ejbclient.urlprovider");
            lookupip = mr.getMessage("ejbclient.ip");
            jndiname = mr.getMessage("jndi.kaverysless");

            System.setProperty(namingfactory, contextfactory);
            System.setProperty(urlprovider, lookupip);

            Object objref = jndiContext.lookup(jndiname);

            /*
             * Getting Horse Details From kaverySessionBeanStatlessBean For Display
             */
            String name = "ejb/HLCKaveryMrmJNDI";
            System.out.println("\n after InitialContext Beginning emp Client...\n" + name);
            Object obj = jndiContext.lookup(name);

            String jname = mr.getMessage("jndi.kavery");
            Object oref = jndiContext.lookup(jname);
            HLCKaverySessionBeanStatfulRemoteHome statefulhome = (HLCKaverySessionBeanStatfulRemoteHome) javax.rmi.PortableRemoteObject.narrow(oref, HLCKaverySessionBeanStatfulRemoteHome.class);
            HLCKaverySessionBeanStatfulRemote statefulremote = statefulhome.create();

            HLCkaveryStatelessRemoteHome kaveryhome = (HLCkaveryStatelessRemoteHome) javax.rmi.PortableRemoteObject.narrow(obj, HLCkaveryStatelessRemoteHome.class);
            HLCkaveryStatelessRemote kaveryremote = kaveryhome.create();
            System.out.println("After Create...");
            HLCkaverySessionBeanStatlessRemoteHome home = (HLCkaverySessionBeanStatlessRemoteHome) PortableRemoteObject.narrow(objref, HLCkaverySessionBeanStatlessRemoteHome.class);
            HLCkaverySessionBeanStatlessRemote remote = home.create();

            String edujndiname = mr.getMessage("jndi.education");
            Object eduobj = jndiContext.lookup(edujndiname);
            HLCEducationalSessionRemoteHome eduHome = (HLCEducationalSessionRemoteHome) javax.rmi.PortableRemoteObject.narrow(eduobj, HLCEducationalSessionRemoteHome.class);
            HLCEducationalSessionRemote eduRemote = eduHome.create();

            String jndiname1 = mr.getMessage("jndi.usrreg");
            Object objref1 = jndiContext.lookup(jndiname1);

            HLCkaverystatelessRemoteHome home1 = (HLCkaverystatelessRemoteHome) javax.rmi.PortableRemoteObject.narrow(objref1, HLCkaverystatelessRemoteHome.class);
            HLCkaverystatelessRemote remote1 = home1.create();

            String process = request.getParameter("process");
            System.out.println("process" + process);

            if (process == null || process.trim().length() == 0) {
                return mapping.findForward("login");
            }
            if (process != null && process.equalsIgnoreCase("HorseReg")) {
                Vector horsememtypVect = new Vector();
                String horsesertypVect[] = {};
                HttpSession session = request.getSession();

                Debug.print("process value in servlet:" + process);
                String horseName = (String) request.getAttribute("horseName");
                String regName = (String) request.getAttribute("regName");
                String baRegName = (String) request.getAttribute("baRegName");
                String baPastName = (String) request.getAttribute("baPastName");


                horsememtypVect = remote.displayMembershipType("Horse");
                horsesertypVect = (String[]) statefulremote.getHorseServiceCharge();

                request.setAttribute("horsememberVect", horsememtypVect);
                request.setAttribute("horsesertypVect", horsesertypVect);

                ArrayList colorDetails = kaveryremote.getAllHorseColorDetails();
                Debug.print("colorDetails:" + colorDetails);
                request.setAttribute("DisplayColorDetails", colorDetails);

                ArrayList breedDetails = kaveryremote.getAllHorseBreedDetails();
                Debug.print("breedDetails:" + breedDetails);
                request.setAttribute("DisplayBreedDetails", breedDetails);

                String userTypeId = (String) session.getAttribute("userTypeId");
                String userName = statefulremote.getUserTypeName(userTypeId);

                Debug.print("userTypeId:" + userName);
                Debug.print("horseName:" + horseName);
                Debug.print("regName:" + regName);
                Debug.print("baRegName:" + baRegName);
                Debug.print("baPastName:" + baPastName);
                request.setAttribute("userName", userName);
                request.setAttribute("horseName", horseName);
                request.setAttribute("regName", regName);
                request.setAttribute("baRegName", baRegName);
                request.setAttribute("baPastName", baPastName);
                return mapping.findForward("HorseRegistration");
            } else if (process != null && process.equalsIgnoreCase("newReg")) {
                String horseMemberId = request.getParameter("horseMemberId");
                if (horseMemberId != null && horseMemberId.trim().length() != 0) {
                    HLCHorseRegisterationVO horseDetails = (HLCHorseRegisterationVO) statefulremote.getHorseDetailsByHorseMemberId(horseMemberId);
                    String ownerId = horseDetails.getOwnerId();
                    String riderId = horseDetails.getRiderMemberId();

                    if (ownerId != null) {
                        Debug.print("Inside If of Owner Id" + ownerId);
                        ArrayList ownerDetails = (ArrayList) remote1.getUserContactDetails(ownerId);
                        request.setAttribute("ownerDetails", ownerDetails);
                    } else {
                        Debug.print("Inside Else of Owner Id" + ownerId);
                        request.setAttribute("ownerDetails", null);
                    }
                    if (riderId != null) {
                        Debug.print("Inside If of riderId" + riderId);
                        ArrayList prevriderDet = (ArrayList) remote1.getMemberContactDetails(riderId);
                        request.setAttribute("riderInfoDetails", prevriderDet);
                    } else {
                        Debug.print("Inside Else of riderId " + riderId);
                        request.setAttribute("riderInfoDetails", null);
                    }
                    ArrayList HorseDispDetails = (ArrayList) statefulremote.getHorseRelationshipDetailsDetails(horseMemberId);
                    Vector horseMemType = remote.displayMembershipType("Horse");
                    String[] horseServiceType = (String[]) statefulremote.getHorseServiceCharge();
                    ArrayList horseColorDetails = kaveryremote.getAllHorseColorDetails();
                    Debug.print("colorDetails:" + horseColorDetails);
                    ArrayList horseBreedDetails = kaveryremote.getAllHorseBreedDetails();
                    Debug.print("breedDetails:" + horseBreedDetails);
                    request.setAttribute("DisplayBreedDetails", horseBreedDetails);
                    request.setAttribute("DisplayColorDetails", horseColorDetails);
                    request.setAttribute("ListVODet", HorseDispDetails);
                    request.setAttribute("horsememberVect", horseMemType);
                    request.setAttribute("horsesertypVect", horseServiceType);
                    request.setAttribute("ListVO", horseDetails);
                    return mapping.findForward("NotRegisteredHorse");
                }
            }
        } catch (Exception e) {
            System.out.println("client exception :" + e);
        }
        return null;
    }
}


