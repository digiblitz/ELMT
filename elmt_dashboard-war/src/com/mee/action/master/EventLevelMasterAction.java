/*
 * DivisionEventTypeMasterAction.java
 *
 * Created on December 23, 2006, 2:04 PM
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

/**
 *
 * @author vidhya
 * @version
 */

public class EventLevelMasterAction extends Action {
    
    
    
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
            throws Exception {try{
             
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
                 *      Process flow directed to insert Event Level Master details JSP page
                 */                  
                 if(process.equals("add")){
                 return mapping.findForward("frmMeeEventLevelCreate"); //
                 }
                  if(process.equals("insert")){
                    try{
                        Debug.print("\n Inside Refund Rule Adding...\n"); 
                        String eventlevel=request.getParameter("eventlevel");
                        String eventcode=request.getParameter("eventcode");
                        String jumpinterface=null;
                        boolean bol=false;
                        Debug.print(" eventlevel:"+eventlevel);
                        if(eventlevel!=null && eventlevel.trim().length()!=0 && eventcode!=null && eventcode .trim().length()!=0){
                            eventlevel = eventlevel.trim();
                            eventcode  =eventcode.trim();
			    bol = remote.insertEventLevelMaster(eventlevel,eventcode,jumpinterface);
                        }
                        Debug.print("Boolean bol="+bol);
                        if (bol == true){
                            Debug.print(" Succeffully Inserted : "+bol);
                            return mapping.findForward("frmMeeEventLevelConf");          
                        }
                        else {   
                            request.setAttribute("err","st");
                            return mapping.findForward("frmMeeEventLevelCreate");
                        }
                     }
                    catch (Exception e){
                        Debug.print(" Error : "+e.getMessage());
                    }
                  }
                 /*  Delete 
                 *
                 *      Process flow directed to delete Event Level Master  details JSP page
                 */  
                  
                  else if(process.equals("deactivate")){
                    try{
                        Debug.print("\n Inside Division Event Name Deleting...\n"); 
                        String eventId=request.getParameter("eventId");
                        System.out.println("eventId ="+eventId);
                       	boolean bol = remote.deleteEventLevelMaster(eventId);
                         System.out.println("bol ="+bol);
                        if (bol == true){
                            Debug.print(" Succeffully Deleted : "+bol);
                            return mapping.findForward("frmMeeEventLevelStatusConf");                        
                        }
                        else {
                          request.setAttribute("err","st");
                          return mapping.findForward("frmMeeEventLevelDelete");
                        }
                   }
                    catch(Exception e){
                        Debug.print("Error: "+e.getMessage());                      
                    }
                  }
                 /*  Update 
                 *
                 *      Process flow directed to Update Event Level Master details JSP page
                 */  
                        

            else if(process.equals("update")){
                        try{
                            Debug.print("\n Inside Event Type Updating...\n"); 
                            String eventId=request.getParameter("eventId");
                            String eventlevel=request.getParameter("eventlevel");
                            String eventcode=request.getParameter("eventcode");
                            String jumpinterface=null;
                            Debug.print(" Event Id in Servlet:" + eventId);
                            Debug.print(" eventlevel in Servlet:" + eventlevel);
                            Debug.print(" eventcode in Servlet:" + eventcode);
                            
                            if(eventId!=null && eventId.trim().length()!=0 && eventlevel!=null && eventlevel.trim().length()!=0 && eventcode!=null && eventcode.trim().length()!=0){
                                boolean bol = remote.updateEventLevelMaster(eventlevel,eventcode,jumpinterface,eventId);
                                Debug.print("   Result :" + bol);
                                if (bol == true){
                                    Debug.print(" Succeffully Updated : "+bol);
                                    return mapping.findForward("frmMeeEventLevelEditConf");                        
                                }
                                else {
                                    String[] s=new String[4];
                                    Vector vObja = (Vector)remote.displayEventLevelMaster(eventId);
                                    Enumeration e = vObja.elements();
                                    if(e.hasMoreElements()){
                                         s = (String [])e.nextElement();                                    
                                        System.out.println("Hello Test123");
                                        System.out.println(""+s[0]+":"+s[1]+":"+s[2]+":"+s[3]);                           
                                    }
                                       request.setAttribute("EventDetail",s);
                                       request.setAttribute("eventId",s[0]);
                                        request.setAttribute("eventlevel",s[1]); 
                                        request.setAttribute("eventcode",s[2]); 
                                  request.setAttribute("err","st");
                                  return mapping.findForward("frmMeeEventLevelEdit");
                                }
                            }
                           /* else{
                                Vector vObj = (Vector)remote.displayAllEventLevelMaster();
                                request.setAttribute("EventAllList",vObj);
                                return mapping.findForward("frmMeeEventLevelList");
                            }*/
                        }
                        catch (Exception e){
                            Debug.print(" Error : "+e.getMessage());
                        }
                  }
 //=========================================For View===============================
            
             else if(process.equals("view")){
                        try{ 
                            //return mapping.findForward("frmRefundRuleEdit");
                            /*Debug.print("\n Inside Refund Rule View .....\n");
                            String refundId=request.getParameter("refundId");
                            Vector vObj = (Vector)remote.displayRefundRule(refundId);
                            request.setAttribute("refundView",vObj);
                            return mapping.findForward("ViewRefundRule");*/
                            
                        String eventId=request.getParameter("eventId");
                        String eventStatus = request.getParameter("btnSubmit");
                                String[] s=new String[4];
                                Vector vObja = (Vector)remote.displayEventLevelMaster(eventId);
                                Enumeration e = vObja.elements();
                                if(e.hasMoreElements()){
                                     s = (String [])e.nextElement();                                    
                                    System.out.println("Hello Test123");
                                    System.out.println(""+s[0]+":"+s[1]+":"+s[2]+":"+s[3]);                           
                                }
                             
                                    request.setAttribute("EventDetail",s);
                                    request.setAttribute("eventId",s[0]);
                                    request.setAttribute("eventlevel",s[1]);
                                    request.setAttribute("eventcode",s[2]);
                        if(eventStatus.equals("Activate")){
                           return mapping.findForward("frmMeeEventLevelStatusConf"); 
                        }
                        else if(eventStatus.equals("Edit")){
                           return mapping.findForward("frmMeeEventLevelEdit");
                        }
                        else if(eventStatus.equals("Deactivate")){
                           return mapping.findForward("frmMeeEventLevelStatusConf");
                        }
                        }
                        catch(Exception e){
                            Debug.print("Error: "+e.getMessage());
                        }
                   }
//===========================List============================
                   /*else if(process.equals("list")){
                        try{
						
                            Debug.print("\n Inside Division Event List all ....\n");
                          //  Vector vObj  = (Vector)remote.displayAllEventLevelMaster();
							
                          //  request.setAttribute("EventAllList",vObj);
                            return mapping.findForward("frmMeeEventLevelList");
                           }
                            catch(Exception e){
                            Debug.print("Error: "+e.getMessage());
                        }
                   }*/
                  if(process.equals("list")){
                 return mapping.findForward("frmMeeEventLevelList");
                 }
             else if(process.equals("initList")){
                        try{
                            Debug.print("\n Inside Event Level List all ....\n");
                            boolean result = false;
                            /*
                            boolean result = false;
                            String eventStatus = (String)request.getParameter("status");
                            if(eventStatus.equals("activate")){
                               result =true;
                            }
                            if(eventStatus.equals("deactivate")){
                               result =false;
                            }*/
                             Vector vObj = (Vector)remote.displayAllEventLevelMaster(result);
                             
                                request.setAttribute("EventAllList",vObj);
                                return mapping.findForward("frmMeeEventLevelList");
                           }
                            catch(Exception e){
                            Debug.print("Error: "+e.getMessage());
                        }
                   }
            
   //================================                                                
                         
                       
                   else if(process.equals("activate")){
                        try{
                            Debug.print("\n Inside Division Event Activate  ....\n");
                            String eventId=request.getParameter("eventId");
                            
                	    boolean bol = remote.activateEventLevelMaster(eventId);
                            if (bol == true){
                                Debug.print(" Succeffully Activated : "+bol);
                                return mapping.findForward("frmMeeEventLevelStatusConf");                        
                            }
                            else {
                              request.setAttribute("err","st");
                              return mapping.findForward("frmMeeEventLevelActivate");
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

