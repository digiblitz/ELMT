/*
 * Program Name     :   DivisionEventTypeMasterAction.java
 * Created Date     :   December 23, 2006, 2:04 PM
 * Author           :   Hari
 * Copy Right       :   digiBlitz Technologies Inc /  digiBlitz Technologies (P) Ltd
 * Version          :   1.5
 * CopyRightInformation:
 *  (c)2006,digiBlitz Technologies Inc/digiBlitz Technologies (P) Ltd. All rights reserved.
 *  916 W. Broad Street Suite 205, FallsChurch, VA 22046.
 *  This document is protected by copyright. No part of this document may be reproduced in any form by any means without
 *  prior written authorization of Sun and its licensors. if any.
 *  The information described in this document may be protected by one or more U.S.patents.foreign patents,or
 *  pending applications.
 */


package com.mee.action.master;
        
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.naming.Context;
import javax.servlet.http.HttpSession;
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



public class DivisionEventTypeMasterAction extends Action {

    private final static String SUCCESS = "success";
    
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

                HttpSession session = request.getSession(true);
                String process = request.getParameter("process");              
                System.out.println("Process is "+process);
                    
                /*  Add 
                 *
                 *      Process flow directed to insert DivisionEventTypeMaster details JSP page
                 */                  
                     if(process.equals("add")){
                        return mapping.findForward("frmMeeDivisionEventTypeCreate"); 
                     }
                
                /*  Insert 
                 *
                 *      Process flow directed to insert DivisionEventTypeMaster details JSP page
                 */                                  
                    else if(process.equals("insert")){
                            try{
                                String eventName=request.getParameter("eventName");
                                boolean bol=false;
                                if(eventName!=null && eventName.trim().length()!=0){
                                    eventName = eventName.trim();
                                    bol = remote.CreateEventDivision(eventName);                            
                                    Debug.print(" Create Event Result : "+bol);
                                }
                                if (bol == true){
                                    return mapping.findForward("addConfirmation");          
                                }
                                else {   
                                    request.setAttribute("err","st");
                                    return mapping.findForward("frmMeeDivisionEventTypeCreate");
                                }
                             }
                            catch (Exception e){
                                Debug.print(" Error : "+e.getMessage());
                            }
                      }
                
                 /*  Deactivate 
                 *
                 *      Process flow directed to Deactivate Division Event Type Master  details JSP page
                 */  
                  
                  else if(process.equals("deactivate")){
                        try{
                            Debug.print("\n Inside Division Event Name Deleting...\n"); 
                            String eventId=request.getParameter("eventId");
                            boolean bol = remote.deleteEventDivision(eventId);
                            System.out.println("Delete Event Result ="+bol);
                            if (bol == true){
                                return mapping.findForward("Confirmation");                        
                            }
                            else {
                              request.setAttribute("err","st");
                              return mapping.findForward("frmDivisionEventTypeDelete");
                            }
                        }
                        catch(Exception e){
                            Debug.print("Error: "+e.getMessage());                      
                        }
                  }
                 /*  Update 
                 *
                 *      Process flow directed to Update Division Event Type Master details JSP page
                 */  
                  
                   else if(process.equals("update")){
                        try{
                            Debug.print("\n Inside Division Event Updating...\n"); 
                            String eventId=request.getParameter("eventId");
                            String eventName=request.getParameter("eventName");
			    String evntName = (String) session.getAttribute("eventName");
                            
                            boolean bol = remote.updateEventDivision(eventName,eventId);
                            Debug.print("Reuslt of Update : "+bol);                          
                            if (bol == true){
                                return mapping.findForward("Confirmation");                        
                            }
                            else {
                                if(evntName.equalsIgnoreCase(eventName)){
                                    session.setAttribute("eventName",null);
                                    session.removeAttribute("eventName");
                                    return mapping.findForward("Confirmation");  
                                }
                                Vector vObja = (Vector)remote.listEventDivision(eventId);
                                Enumeration e = vObja.elements();
                                if(e.hasMoreElements()){
                                    String[] s = (String [])e.nextElement();
                                    request.setAttribute("EventDetail",s);
                                }
                                request.setAttribute("err","st");
                                return mapping.findForward("frmDivisionEventTypeEdit");
                            }
                        }
                        catch (Exception e){
                                Debug.print(" Error : "+e.getMessage());
                        }
                  }
                 /*  View
                 *
                 *      Process flow directed to View individual Division Event Master details JSP page
                 */                  
                   else if(process.equals("view")){
                        try{ 
                                String eventId=request.getParameter("eventId");
                                String EventStatus = request.getParameter("btnSubmit");
                                Vector vObja = (Vector)remote.listEventDivision(eventId);
                                Enumeration e = vObja.elements();
                                if(e.hasMoreElements()){
                                    String[] s = (String [])e.nextElement();
                                    session.setAttribute("eventName",s[1]);
                                    request.setAttribute("EventDetail",s);
                                }

                                if(EventStatus.equals("Activate")){
                                   return mapping.findForward("frmDivisionEventTypeActivate"); 
                                }

                                else if(EventStatus.equals("Deactivate")){
                                   return mapping.findForward("frmDivisionEventTypeDelete");
                                }
                            }
                            catch(Exception e){
                                Debug.print("Error: "+e.getMessage());
                            }
                 }

                else if(process.equals("edit")){
                        try{ 
                                String eventId=request.getParameter("eventId");
                                Debug.print("edit eventId "+ eventId);
                                if(eventId!=null || eventId.trim().length()!=0){
                                    Vector vObja = (Vector)remote.listEventDivision(eventId);
                                    Enumeration e = vObja.elements();
                                    if(e.hasMoreElements()){
                                        String[] s = (String [])e.nextElement();
                                        session.setAttribute("eventName",s[1]);
                                        request.setAttribute("EventDetail",s);
                                    }
                                       Debug.print("Inside Edit of EventStatus  "+session.getAttribute("eventName"));
                                       return mapping.findForward("frmDivisionEventTypeEdit");
                                }
                        }
                        catch(Exception e){
                             Debug.print("Error: "+e.getMessage());
                        }
                  }
                 /*  List
                 *
                 *      Process flow directed to List Division Event Master details JSP page
                 */                                
                            
                   else if(process.equals("list")){
                        try{
						
                            Debug.print("\n Inside Division Event List all ....\n");
                            Vector vObj  = (Vector)remote.getAllEventDivision();
							
                            request.setAttribute("EventAllList",vObj);
                            return mapping.findForward("frmDivisionEventList");
                           }
                            catch(Exception e){
                            Debug.print("Error: "+e.getMessage());
                        }
                   }
                 /*  Activate
                 *
                 *      Process flow directed to Activate the Division Event Master details JSP page
                 */    
                   else if(process.equals("activate")){
                        try{
                            String eventId=request.getParameter("eventId");
                            
                            boolean bol = remote.activateEventDivision(eventId);
                            Debug.print("Activation Result : "+bol);                            

                            if (bol == true){
                                return mapping.findForward("Confirmation");                        
                            }
                            else {
                              request.setAttribute("err","st");
                              return mapping.findForward("frmDivisionEventTypeActivate");
                            }
                            
                        }
                        catch(Exception e){
                            Debug.print("Error: "+e.getMessage());
                        }
                   }
              }
            catch(Exception e){
                e.printStackTrace();
            }
			return null;
			}
    }
