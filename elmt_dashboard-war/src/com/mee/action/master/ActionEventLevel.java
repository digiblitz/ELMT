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
public class ActionEventLevel extends Action {
   
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
             
            HttpSession session = request.getSession(true);


//=============================Add New Event Type=========================================================               
            
            
            if(process.equals("newEventLevel")){
                return mapping.findForward("frmMeeEventLevelAdd");
            }
           
            else if(process.equals("addLevel")){
                boolean result = false;
                String eventlevel = request.getParameter("eventlevel");
                String eventcode= request.getParameter("eventcode");
                String jumpinterface = request.getParameter("jumping");
                System.out.print("eventlevel:" + eventlevel);
                System.out.print("eventcode:" + eventcode);
                System.out.print("jumpinterface:" + jumpinterface);
                 if(eventlevel!=null){
                      eventlevel = eventlevel.trim();
                 }
                result =remote.insertEventLevelMaster(eventlevel,eventcode,jumpinterface);
                    if(result==true){
                        return mapping.findForward("frmMeeEventLevelConf");
                    }
                    else{
                        request.setAttribute("err","st");
                        return mapping.findForward("frmMeeEventLevelAdd");
                    }
            }
            
            else if(process.equals("lstEventLevel")){
                //Vector vectObj = (Vector)remote.displayAllEventLevelMaster();
                //request.setAttribute("DisplayEventLevel",vectObj);
                return mapping.findForward("frmMeeEventLevelList");
            }
 
//f//=============================Edit & Delete Event level=========================================================
            else if(process.equals("manupLevel")){
                String prolevel = request.getParameter("opType");
                String levelId = request.getParameter("levelId");
                System.out.print("prolevel:" + prolevel);
                System.out.print("levelId:" + levelId);
                    if(prolevel.equals("Edit")){
                        Vector levelObj = (Vector)remote.displayEventLevelMaster(levelId);
                        request.setAttribute("editeventlevel",levelObj);
                        return mapping.findForward("frmMeeEventLevelEdit");
                    }
                    else if(prolevel.equals("Delete")){
                        Vector levelObj = (Vector)remote.displayEventLevelMaster(levelId);
                        request.setAttribute("deleteEventLevel",levelObj);
                        return mapping.findForward("frmMeeEventLevelDelete");
                    }
            }
//=============================Edit Exist Event Level=========================================================
            else if(process.equals("editLevel")){
                 boolean result = false;
                    String eventlevelid= request.getParameter("edLeId");
                    String eventlevel = request.getParameter("eventlevel");
                    String eventcode= request.getParameter("eventcode");
                    String jumpinterface = request.getParameter("jumping");
                    System.out.print("eventlevelid:" + eventlevelid);
                    System.out.print("eventlevel:" + eventlevel);
                    System.out.print("eventcode:" + eventcode);
                    System.out.print("jumpinterface:" + jumpinterface);
                    result = remote.updateEventLevelMaster(eventlevelid,eventlevel,eventcode,jumpinterface);
                    if(result==true){
                       return mapping.findForward("frmMeeEventLevelList"); 
                    }
                    else{
                        request.setAttribute("err","st");
                        return mapping.findForward("frmMeeEventLevelEdit");
                    }
            }
            
             else if(process.equals("confirmDelete")){
                    String eventlevelid= request.getParameter("edLeId");
                    System.out.print("eventlevelid:" + eventlevelid);
                    boolean resultDelete = remote.deleteEventLevelMaster(eventlevelid);
                    if(resultDelete == true){
                        return mapping.findForward("frmMeeEventLevelList"); 
                    }
                    else{
                        request.setAttribute("err","st");
                        return mapping.findForward("frmMeeEventLevelList"); 
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
