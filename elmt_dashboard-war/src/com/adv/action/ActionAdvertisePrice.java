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
/*  Program Name    : ActionAdvertisePrice.java
 *  Created Date    : September 13, 2006, 9:23 PM
 *  Author          : Punitha.R
 *  Version         : 1.14
 *  CopyrightInformation:
    (c)2006,digiBlitz Technologies Inc/digiBlitz Technologies (P) Ltd. All rights reserved.
    916 W. Broad Street Suite 205, FallsChurch, VA 22046.
    
    This document is protected by copyright. No part of this document may be reproduced in any form by any means without
    prior written authorization of Sun and its licensors. if any.
 
    The information described in this document may be protected by one or more U.S.patents.foreign patents,or
    pending applications.
 */
package com.adv.action;
import com.hlcadv.advertise.HLCAdvertiseSessionRemote;
import com.hlcadv.advertise.HLCAdvertiseSessionRemoteHome;
import com.hlccommon.util.Debug;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.MessageResources;
import java.io.*;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import java.util.Properties;
import java.util.*;

public class ActionAdvertisePrice extends Action {
 
      public ActionForward execute(ActionMapping mapping, ActionForm  form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {

            try{
                MessageResources mr=getResources(request);

                String namingfactory=mr.getMessage("ejbclient.namingfactory");
                String contextfactory=mr.getMessage("ejbclient.contextfactory");
                String urlprovider=mr.getMessage("ejbclient.urlprovider");
                String lookupip=mr.getMessage("ejbclient.ip");
                String jndiname=mr.getMessage("jndi.advertise");

                System.setProperty(namingfactory,contextfactory);
                System.setProperty(urlprovider,lookupip);
                Context jndiContext = new InitialContext();
                Object objref = jndiContext.lookup(jndiname);        

                HLCAdvertiseSessionRemoteHome advHome = (HLCAdvertiseSessionRemoteHome)PortableRemoteObject.narrow(objref,HLCAdvertiseSessionRemoteHome.class);
                HLCAdvertiseSessionRemote advRemote = advHome.create();
                HttpSession session = request.getSession(false); 
                Debug.print("ActionAdvertisePrice.initLoad() called ...."); 
                String advPrice = request.getParameter("advPrice");
    /*
     *  new page
     *
     */         if(advPrice.equals("userView")){    
                    Vector dispMediaType = (Vector)advRemote.getAllMediaDetails();
                    session.setAttribute("FrequencyPriceDetails",null);
                    session.setAttribute("DisplayTypeDetails",null);
                    session.setAttribute("DisplayMediaDetails",null);
                    session.setAttribute("DisplayMediaDetails",dispMediaType);
                    return mapping.findForward("frmAdvUsrPricematrixList");  
               }
	   
     /*
      *  based on media Id get display id
      *
      */
             else if(advPrice.equals("mediaPriceLst")){
                    try{
                            String mid = request.getParameter("mid");
                            Debug.print("Servlet mid" + mid);
                                Vector dispMediaType = (Vector)advRemote.getAllMediaDetails();
                                session.setAttribute("DisplayMediaDetails" ,dispMediaType);  
                                session.setAttribute("FrequencyPriceDetails",null);
                                session.setAttribute("DisplayTypeDetails",null);
                                Vector displayType = (Vector)advRemote.getMediaDisplayTypeDetails(mid);
                                session.setAttribute("DisplayTypeDetails" ,displayType);
                                
                                request.setAttribute("mid",mid);
                                return mapping.findForward("frmAdvUsrPricematrixList");
                    } 
                    catch(Exception priceFreqType){
                        Debug.print("While Listing new display sub type page" + priceFreqType);
                    }
            }   
        
/*
* based on display id list all frequency and price..........
*
*/
                
              else if(advPrice.equals("searchFreq")){
                    try{
                        String mid = request.getParameter("mid");
                        Debug.print("Servlet mid" + mid);
                        Vector dispMediaType = (Vector)advRemote.getAllMediaDetails();
                        session.setAttribute("DisplayMediaDetails" ,dispMediaType);    

                        Vector displayType = (Vector)advRemote.getMediaDisplayTypeDetails(mid);
                        session.setAttribute("DisplayTypeDetails" ,displayType);
                        request.setAttribute("mid",mid);

                        String dispId = request.getParameter("dispId");
                        Debug.print("Servlet dispId" + dispId);
                        
                        ArrayList FrequencyPriceDetails  = (ArrayList)advRemote.getPriceDetailsByMediaIdDispId(mid, dispId);
                        session.setAttribute("FrequencyPriceDetails", FrequencyPriceDetails);
                        request.setAttribute("dispId",dispId);
                        return mapping.findForward("frmAdvUsrPricematrixList");
                    }
                    catch(Exception freqPrice){
                        Debug.print("Frequency and price Exception......." + freqPrice);
                   }
              }   
 /*
  * User post status............
  *
  */  
            else if(advPrice.equals("userStatus")){
                    String userId = (String)session.getAttribute("userId");     
                    if(userId!=null){
                        Debug.print("User ID In Sesssion:" + userId);
                        ArrayList UserListDetails = advRemote.viewAllAdsForUser(userId);            
                        Debug.print("ArrayList" +UserListDetails);
                        session.setAttribute("UserListDetails",null);
                        session.setAttribute("UserListDetails" ,UserListDetails); 

                         return mapping.findForward("frmAdvMyRequest");
                    }
                }
                
             
   /*
    * to see posted ad details
    *
    */   
           else if(advPrice.equals("viewMyAds")){
                    try{
                        String manifest = request.getParameter("manifest");
                        String advId = request.getParameter("advId");
                        Debug.print("Servlet viewAds() advId:"+ advId);
                        Debug.print("Servlet viewAds() manifest:"+ manifest);
                        Debug.print("Servlet posted ad details() is calling");
                         if(advId!=null && advId.trim().length()!=0){
                            if(manifest.equals("View")) {
                                    Debug.print("Servlet Inside Loop:"+ advId);
                                    ArrayList listAds = advRemote.getAdvertiserDetails(advId);
                                    session.setAttribute("displayParticularAds" ,null);
                                    session.setAttribute("displayParticularAds" ,listAds);
                                    Debug.print("Servlet Suscessfully leaved" + listAds);
                                    return mapping.findForward("advViewMyAds");
                            }
                            else if(manifest.equals("Manifest")){
                                ArrayList adsDetailList = advRemote.getAllManifestDetailForUser(advId);
                                request.setAttribute("manifestDetailList",adsDetailList);
                                return mapping.findForward("advManifestUpdatedDetails");
                            }     
                            
                        }
                        
                    }
                    catch(Exception eDisp){
                        Debug.print("while redirecting User My Ads  page" + eDisp);
                    }
                }                                      
        
    /*
     *  new page
     *
     */      else if(advPrice.equals("mediaView")){    
                    Vector dispMediaType = (Vector)advRemote.getAllMediaDetails();
                    session.setAttribute("DisplayMediaDetails",null);
                    session.setAttribute("DisplayMediaDetails",dispMediaType);
                    return mapping.findForward("frmAdvAvailableMedia");  
               }                
     /*
      * based on sub type id and media id we r getting freqDet details....................
      * public ArrayList getDimensionDetailBySubTypeAdvertisement(String mediaId, String subTypeId) throws RemoteException;
      * String dimList[] = {mediaName,  dispName, dispSubName, dimName, height, width, units, imagePath };
      */           
     else if (advPrice.equals("freqDet")){
              Debug.print("Action AdvertisePrice.getDimensionDetailBySubTypeAdvertisement() is calling......");      
              String mediaId = request.getParameter("mediaId");
              String subTypeId = request.getParameter("subTypeId");
              System.out.print("mediaId" + mediaId);
              System.out.print("subTypeId" + subTypeId);
              ArrayList list = (ArrayList)advRemote.getDimensionDetailBySubTypeAdvertisement(mediaId,subTypeId);                    
              session.setAttribute("dimensionDetails",list);
              return mapping.findForward("frmAdvDimDetail");
     }   
                
     /*  
      * try block end..............
      */       

      }  
      /*
      *
      * catch block starts..............
      */               
      catch(Exception general){
          Debug.print("Exception occurs while calling price servlet" + general);
      }
           return mapping.findForward("index");
      }
}
