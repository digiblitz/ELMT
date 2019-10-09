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
// Java Document
/*
 * ActionEventLevel.java
 *
 * Created on December 22, 2006, 5:28 PM
 */

package com.mee.action.master;

import java.util.*;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.hlcmro.org.*;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.util.MessageResources;
import com.hlccommon.util.Debug;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.*;
/**
 *
 * @author punitha
 * @version
 */
public class ActionRefundRuleSubType extends Action {
   
    /* forward name="success" path="" */
    private final static String SUCCESS = "EditHorseRegistration";
    
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
  
 
 
    try{
        
        
            MessageResources mr=getResources(request);
            String namingfactory=mr.getMessage("ejbclient.namingfactory");
            String contextfactory=mr.getMessage("ejbclient.contextfactory");
            String urlprovider=mr.getMessage("ejbclient.urlprovider");
            String lookupip=mr.getMessage("ejbclient.ip");

            System.setProperty(namingfactory,contextfactory);
            System.setProperty(urlprovider,lookupip);
            Context jndiContext = new InitialContext();
				
            Object obj=jndiContext.lookup("ejb/HLCVaigaiSessionBean");
            HLCVaigaiSessionRemoteHome home = (HLCVaigaiSessionRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(obj,HLCVaigaiSessionRemoteHome.class);
            HLCVaigaiSessionRemote remote = home.create();
			
          	            
                String process= (String) request.getParameter("process");
                Debug.print("Process in Servlet "+process);

                String statusvalue = (String)request.getParameter("status");
                Debug.print("Process in Saved Status "+statusvalue);
             
                HttpSession session = request.getSession(true);


//=============================Add New Refund SubType Level=========================================================               
            
            
            if(process.equals("newRefundSubType")){
                return mapping.findForward("frmMeeRefundSubTypeAdd");
            }
             if(process.equals("newRefundSubListContent")){
                return mapping.findForward("frmMeeRefundSubTypeList");
            }
           
            else if(process.equals("newSubType")){
                boolean result = false;
                boolean txtstatus = false;
                String refundlevel = request.getParameter("txtRefundSubName");
                String refundStatus = request.getParameter("rdstatus");
                Debug.print("refundlevel:" + refundlevel);
                Debug.print("refundStatus:" + refundStatus);
               
               if(refundStatus.equals("true")){
                    txtstatus = true;
               }
               else if(refundStatus.equals("false")){
               txtstatus = false;
               }
                 if(refundlevel!=null){ 
                      refundlevel = refundlevel.trim();
                 }
                result =remote.insertRefundRuleSubTypeMaster(refundlevel,txtstatus);
                    if(result==true){
                        return mapping.findForward("frmMeeRefundLevelConf");
                    }
                    else{
                        request.setAttribute("err","st");
                        return mapping.findForward("frmMeeRefundSubTypeAdd");
                    }
            }
            
            else if(process.equals("newSubTypeList")){
                String status = null;
                
                if(session.getAttribute("status")!=null){
                    status = (String)session.getAttribute("status");    
                    Debug.print("Inside session.getAttribute(status)");
                }
                                
                
                boolean result = false;
                
                if(request.getParameter("status")!=null){
                    status = (String)request.getParameter("status");
                    Debug.print("Inside request.getParameter(status)");
                }
                
                
                Debug.print("status in list servlet:"+status);
                if(status.equalsIgnoreCase("true")){
                    result = true;
                
                Vector vectObj = (Vector)remote.displayAllRefundRuleSubType(result);
                request.setAttribute("DisplayEventLevel",vectObj);
                request.setAttribute("status",status);
                
                session.setAttribute("status",null);
                
                return mapping.findForward("frmMeeRefundSubTypeList");
                }
                if(status.equalsIgnoreCase("false")){
                    result = false;
                
                Vector vectObj = (Vector)remote.displayAllRefundRuleSubType(result);
                request.setAttribute("DisplayEventLevel",vectObj);
                request.setAttribute("status",status);
                
                session.setAttribute("status",null);
                
                return mapping.findForward("frmMeeRefundSubTypeList");
                }
                else{
                        request.setAttribute("err","st");
                        return mapping.findForward("frmMeeRefundSubTypeList");
                    }
            }
            
                
 
//f//=============================Edit & Delete Refund SubType level=========================================================
            
            else if(process.equals("editRefundLevel")){
                String prolevel = request.getParameter("advDimButton");
                String levelId = request.getParameter("refundListId");
                System.out.print("prolevel:" + prolevel);
                System.out.print("levelId:" + levelId);
                
                    if(prolevel.equals("Edit")){
                        Vector levelObj = (Vector)remote.displayRefundRuleSubType(levelId);
                        Debug.print("levelObj.size() :" +levelObj.size());
                        
                        Enumeration enm = levelObj.elements();
                        while(enm.hasMoreElements()){
                            String[] s = (String[])enm.nextElement();
                            Debug.print("s[0] :" +s[0]);
                            Debug.print("s[1] :" +s[1]);
                            
                            request.setAttribute("editRefundSublevel",s);
                            
                        }
                        return mapping.findForward("frmMeeRefundLevelEdit");
                    }
                    else if(prolevel.equals("Delete")){
                        Vector levelObj = (Vector)remote.displayRefundRuleSubType(levelId);
                        request.setAttribute("deleteEventLevel",levelObj);
                        return mapping.findForward("frmMeeRefundLevelDelete");
                    }
            }
//=============================Edit Exist Refund SubType Level=========================================================
            
            else if(process.equals("Update")){
                 boolean result = false;
                    String refundListId= request.getParameter("refundListId");
                    String refundListName = request.getParameter("refundListName");
                    String refundStatus = request.getParameter("rdstatus");
                    Debug.print("refundListId in servlet:" + refundListId);
                    Debug.print("refundListName in servlet:" + refundListName);
                    Debug.print("refundStatus in servlet:"+refundStatus);
                      boolean txtstatus = false;
               
                       if(refundStatus.equals("true")){
                            txtstatus = true;
                       }
                       else if(refundStatus.equals("false")){
                       txtstatus = false;
                       }
                
                
                    result = remote.updateRefundRuleSubMaster(refundListName,refundListId,txtstatus);
                    if(result==true){
                       return mapping.findForward("frmMeeRefundSubTypeLevelConfUpt"); 
                    }
                    else{

			String levelId = request.getParameter("refundListId");
                        Vector levelObj = (Vector)remote.displayRefundRuleSubType(levelId);
                        Debug.print("levelObj.size() :" +levelObj.size());
                        System.out.print("levelId:" + levelId);
                           
                        Enumeration enm = levelObj.elements();
                        while(enm.hasMoreElements()){
                            String[] s = (String[])enm.nextElement();
                            Debug.print("s[0] :" +s[0]);
                            Debug.print("s[1] :" +s[1]);
                            
                            request.setAttribute("editRefundSublevel",s);
                        }

                        request.setAttribute("err","st");
                        return mapping.findForward("frmMeeRefundLevelEdit");
                    }
            }
//=============================Deactivate Refund SubType Level=========================================================  
            
             else if(process.equals("Deactivate")){
                    String refundListId= request.getParameter("refundListId");
                    System.out.print("refundListId:" + refundListId);
                    boolean resultDelete = remote.deleteRefundRuleSubMaster(refundListId);
                    
                        Debug.print("statusvalue in Deactive:"+statusvalue);
                        session.setAttribute("status",statusvalue);
                    
                    if(resultDelete == true){
                        Debug.print(" Succeffully Deativated : "+resultDelete);
                        return mapping.findForward("frmMeeRefundSubTypeStatusConf"); 
                    }
                    
                    else{
                        request.setAttribute("err","st");
                        return mapping.findForward("frmMeeRefundSubTypeList"); 
                   }
             }
//=============================Activate Refund SubType Level=========================================================  
            
             else if(process.equals("Activate")){
                String refundListId= request.getParameter("refundListId");
                boolean bol = remote.activateRefundRuleSubType(refundListId);
                
                    
                    Debug.print("statusvalue in Active:"+statusvalue);
                    session.setAttribute("status",statusvalue);
                    
                            
            if (bol == true){
                Debug.print(" Succeffully Activated : "+bol);
                return mapping.findForward("frmMeeRefundSubTypeStatusConf");
                 }
                else{
                        request.setAttribute("err","st");
                        return mapping.findForward("frmMeeRefundSubTypeList"); 
                   }
             }
  //=================================Delete Event Level====================================================================
        }
        catch(Exception e){
            System.out.print("Exception :" + e.getMessage());
        }
 
 return null;
        
    }
}

			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
