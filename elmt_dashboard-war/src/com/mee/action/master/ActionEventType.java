/*
 * ActionEventType.java
 *
 * Created on December 23, 2006, 2:32 PM
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
 * @author dhivya
 * @version
 */

public class ActionEventType extends Action {
    
    /* forward name="success" path="" */
    private final static String SUCCESS = "success";
    
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
            Debug.print("Inital servlet process....");
            String process= (String) request.getParameter("process");
            Debug.print("Process in Servlet "+process);

             
            HttpSession session = request.getSession(true);


//=============================Add New Event Type=========================================================               
      
          
            if(process.equals("newEventType")){
                 return mapping.findForward("frmMeeEventTypeAdd");
                 }
                  if(process.equals("addType")){
                    try{
                        Debug.print("\n Inside Event Type Adding...\n"); 
                        String eventName=request.getParameter("eventName");
                        boolean bol=false;
                        Debug.print("eventName:"+eventName);
                        if(eventName!=null && eventName.trim().length()!=0){
                            eventName = eventName.trim();
                            bol = remote.insertEventTypeMaster(eventName);
                        }
                        Debug.print("Boolean bol="+bol);
                        if (bol == true){
                            Debug.print(" Succeffully Inserted : "+bol);
                            return mapping.findForward("confirmation");          
                        }
                        else {   
                            request.setAttribute("err","st");
                            return mapping.findForward("frmMeeEventTypeAdd");
                        }
                     }
                    catch (Exception e){
                        Debug.print(" Error : "+e.getMessage());
                    }
                  }
//=============================Delete Event Type=========================================================   
                  
                  else if(process.equals("deactivate")){
                    try{
                        Debug.print("\n Inside Event Type deactivating...\n"); 
                        String eventId =request.getParameter("eventId");
                        System.out.println("eventId  ="+eventId );
                        boolean bol = remote.deleteEventTypeMaster(eventId);
                         System.out.println("bol ="+bol);
                        if (bol == true){
                            Debug.print(" Succeffully Deactivated : "+bol);
                            return mapping.findForward("ListCnf");                        
                        }
                        else {
                          request.setAttribute("err","st");
                          return mapping.findForward("frmMeeEventTypeDelete");
                        }
                   }
                    catch(Exception e){
                        Debug.print("Error: "+e.getMessage());                      
                    }
                  }
                 
//==================================Update=================================================      
                  
                   else if(process.equals("update")){
                        try{
                            Debug.print("\n Inside Event Type Updating...\n"); 
                            String eventId=request.getParameter("eventId");
                            String eventName=request.getParameter("eventName");
                            
                            Debug.print(" Event Id in Servlet:" + eventId);
                            Debug.print(" eventName in Servlet:" + eventName);
                            
                            if(eventId!=null && eventId.trim().length()!=0 && eventName!=null && eventName.trim().length()!=0){
                                boolean bol = remote.updateEventTypeMaster(eventName,eventId);
                                Debug.print("   Result :" + bol);
                                if (bol == true){
                                    Debug.print(" Succeffully Updated : "+bol);
                                    return mapping.findForward("UpdateCnf");                        
                                }
                                else {
                                    String[] s=new String[4];
                                    Vector vObja = (Vector)remote.displayEventTypeMaster(eventId);
                                    Enumeration e = vObja.elements();
                                    if(e.hasMoreElements()){
                                         s = (String [])e.nextElement();                                    
                                        System.out.println("Hello Test123");
                                        System.out.println(""+s[0]+":"+s[1]+":"+s[2]+":"+s[3]);                           
                                    }
                                       request.setAttribute("EventTypedet",s);
                                       request.setAttribute("eventId",s[0]);
                                       request.setAttribute("eventName",s[1]); 
                                  request.setAttribute("err","st");
                                  return mapping.findForward("frmMeeEventTypeEdit");
                                }
                            }
                            /*else{
                               Vector vObj = (Vector)remote.displayEventTypeMaster(eventId);
                               request.setAttribute("EventTypedet",vObj);
                                return mapping.findForward("frmMeeEventTypeList");
                            }*/
                        }
                        catch (Exception e){
                            Debug.print(" Error : "+e.getMessage());
                        }
                  }
//============================================View Event Type=====================================
                   else if(process.equals("view")){
                        try{ 
                                
                        String eventId=request.getParameter("eventId");
                        String eventStatus = request.getParameter("btnSubmit");
                                String[] s=new String[4];
                                Vector vObja = (Vector)remote.displayEventTypeMaster(eventId);
                                Enumeration e = vObja.elements();
                                if(e.hasMoreElements()){
                                     s = (String [])e.nextElement();                                    
                                    System.out.println("Hello Test123");
                                    System.out.println(""+s[0]+":"+s[1]+":"+s[2]+":"+s[3]);                           
                                }
                             
                                    request.setAttribute("EventTypedet",s);
                                    request.setAttribute("eventId",s[0]);
                                    request.setAttribute("eventName",s[1]);
                        if(eventStatus.equals("Activate")){
                           return mapping.findForward("ListCnf"); 
                        }
                        else if(eventStatus.equals("Edit")){
                           return mapping.findForward("frmMeeEventTypeEdit");
                        }
                        else if(eventStatus.equals("Deactivate")){
                           return mapping.findForward("ListCnf");
                        }
                        }
                        catch(Exception e){
                            Debug.print("Error: "+e.getMessage());
                        }
                   }
//===========================================List Event Type=============================
                   /*else if(process.equals("list")){
                        try{
                            Debug.print("\n Inside Event Type List all ....\n");
                           Vector vObj = (Vector)remote.displayAllEventTypeMaster();
                           request.setAttribute("EventTypeAllList",vObj);
                            return mapping.findForward("frmMeeEventTypeList");
                           }
                            catch(Exception e){
                            Debug.print("Error: "+e.getMessage());
                        }
                   }*/
            
            
            if(process.equals("list")){
                 return mapping.findForward("frmMeeEventTypeList");
                 }
            
             else if(process.equals("initList")){
             
                        try{
                            Debug.print("\n Inside Event Type List all ....\n");
                            boolean result = false;
                          /*  String eventStatus = (String)request.getParameter("status");
                            if(eventStatus.equals("activate")){
                               result =true;
                            }
                            if(eventStatus.equals("deactivate")){
                               result =false;
                            }*/
                              Vector vObj = (Vector)remote.displayAllEventTypeMaster(result);
                               //request.setAttribute("status",eventStatus);
                                request.setAttribute("EventTypeAllList",vObj);
                                return mapping.findForward("frmMeeEventTypeList");
                           }
                            catch(Exception e){
                            Debug.print("Error: "+e.getMessage());
                        }
                   }
            
            
                    
 //======================================Activate=======================================                                  
                         
                       
                   else if(process.equals("activate")){
                        try{
                            Debug.print("\n Inside Event Type Activate  ....\n");
                            String eventId=request.getParameter("eventId");
                            boolean bol = remote.activateEventTypeMaster(eventId);
                            if (bol == true){
                                Debug.print(" Succeffully Activated : "+bol);
                                return mapping.findForward("ListCnf");                        
                            }
                            else {
                              request.setAttribute("err","st");
                              return mapping.findForward("frmMeeEventTypeActivate");
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
      
            
            