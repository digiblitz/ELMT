/*
 * SpecificationMasterAction.java
 *
 * Created on September 21, 2007, 1:58 PM
 */

package com.mee.action;

import com.hlcmeeting.session.HLCVaigaiSessionRemote;
import com.hlcmeeting.session.HLCVaigaiSessionRemoteHome;
import com.hlcmeeting.util.Debug;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import java.util.*;
import javax.naming.*;
import org.apache.struts.util.MessageResources;
import javax.servlet.http.*;
/**
 *
 * @author Dhivya
 * @version
 */

public class SpecificationMasterAction extends Action {
    
    
    
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
        
        
        try {
            /*
             * Service Locator for ejb/HLCVaigaiSessionJNDI
 JNDI
             */
            
            MessageResources mr=getResources(request);
            String namingfactory=mr.getMessage("ejbclient.namingfactory");
            String contextfactory=mr.getMessage("ejbclient.contextfactory");
            String urlprovider=mr.getMessage("ejbclient.urlprovider");
            String lookupip=mr.getMessage("ejbclient.ip");
            String jndiname=mr.getMessage("jndi.icp");
            
            System.setProperty(namingfactory,contextfactory);
            System.setProperty(urlprovider,lookupip);
            Context jndiContext = new InitialContext();
            Object objref = jndiContext.lookup(jndiname);
            
            HLCVaigaiSessionRemoteHome home = (HLCVaigaiSessionRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(objref,HLCVaigaiSessionRemoteHome.class);
            HLCVaigaiSessionRemote remote = home.create();
            
            HttpSession session = request.getSession(true);
            
            System.out.println("\n after InitialContext inside icp user Meeting list action...\n");
            
            String specProcess = request.getParameter("specProcess");
            Debug.print("specProcess In Servlet:" + specProcess);
                                  
            if(specProcess!=null && specProcess.equals("initSpec")){
                
                Vector eventObj = (Vector)remote.displayAnnualActivityTypeMaster();
                Debug.print("Event Details are" + eventObj);
                request.setAttribute("eventObj",eventObj);
                
                return mapping.findForward("frmCreateSpecificationType");
                
            }
            
            
            else if(specProcess!=null && specProcess.equals("insertSpec")){
                
                String selEventType=request.getParameter("selEventType");
                Debug.print("selEventType : "+selEventType);
                String txtSpec = request.getParameter("txtSpec");
                Debug.print("txtSpec : "+txtSpec);
                String txtDesc = request.getParameter("txtDesc");
                Debug.print("txtDesc : "+txtDesc);
                boolean bol = false;
                
                if(selEventType!=null && selEventType.trim().length()!=0 && txtSpec!=null && txtSpec.trim().length()!=0){
                    
                    boolean exists =remote.isSpecNameExist(selEventType,txtSpec);
                    if(exists == false){
                        bol = remote.addAnnualSpecificationDetails(selEventType,txtSpec,txtDesc);
                        Debug.print("result in servlet: "+bol);
                        if(bol == true){
                            return mapping.findForward("frmCreateSuccess");
                        } else{
                            Vector eventObj = (Vector)remote.displayAnnualActivityTypeMaster();
                            Debug.print("Event Details are" + eventObj);
                            request.setAttribute("eventObj",eventObj);
                            request.setAttribute("err","st");
                            return mapping.findForward("frmCreateSpecificationType");
                        }
                    } else{
                        Vector eventObj = (Vector)remote.displayAnnualActivityTypeMaster();
                        Debug.print("Event Details are" + eventObj);
                        request.setAttribute("eventObj",eventObj);
                        request.setAttribute("err","st");
                        return mapping.findForward("frmCreateSpecificationType");
                    }
                    
                }
                
            }
            //=========================================List========================================
            
            else if(specProcess!=null && specProcess.equalsIgnoreCase("toListSpecMaster")){
                Debug.print("Inside SpecificationMasterAction calling specProcess::"+specProcess);
                return mapping.findForward("listSpecMaster");
            } else if(specProcess!=null && specProcess.equalsIgnoreCase("listSpecMaster")){
                Debug.print("Inside SpecificationMasterAction calling specProcess::"+specProcess);
                ArrayList objAllSpecDetails = new ArrayList();
                String status = request.getParameter("status");
                boolean stat = false;
                if(status!=null ){
                    stat = Boolean.parseBoolean(status);
                }
                objAllSpecDetails = remote.displayAllSpecificationDetails(stat);
                request.setAttribute("status",status);
                request.setAttribute("objAllSpecDetails",objAllSpecDetails);
                return mapping.findForward("listSpecMaster");
                
            } else if(specProcess!=null && specProcess.equalsIgnoreCase("selectSpecDet")){
                Debug.print("Inside SpecificationMasterAction calling specProcess::"+specProcess);
                String status = request.getParameter("btnSubmit");
                String specId = request.getParameter("specId");
                boolean activityStatus = false;
                if(status!=null && status.equalsIgnoreCase("Edit")){
                    Debug.print("Inside SpecificationMasterAction calling status::"+status);
                    return mapping.findForward("editSingleSpecDetail");
                }else if(status!=null && status.equalsIgnoreCase("Activate")){
                    Debug.print("Inside SpecificationMasterAction calling status::"+status);
                    if(specId!=null && specId.trim().length()!=0){
                        activityStatus = remote.activateSpecDetail(specId);
                    }
                    if(!activityStatus){
                        request.setAttribute("Error","Error");
                    }
                    return mapping.findForward("listSpecMaster");
                }else if(status!=null && status.equalsIgnoreCase("Deactivate")){
                    Debug.print("Inside SpecificationMasterAction calling status::"+status);
                    if(specId!=null && specId.trim().length()!=0){
                        activityStatus = remote.deleteSpecDetail(specId);
                    }
                    if(!activityStatus){
                        request.setAttribute("Error","Error");
                    }
                    return mapping.findForward("listSpecMaster");
                }
                
            }
            
            //============================================Edit==========================================
            
            if(specProcess!=null && specProcess.equals("initUpdate")){
                
                Vector eventObj = (Vector)remote.displayAnnualActivityTypeMaster();
                Debug.print("Event Details are" + eventObj);
                request.setAttribute("eventObj",eventObj);
                String specId=request.getParameter("specId");
                Debug.print("specId : "+specId);
                ArrayList objSingleSpecDet = remote.displaySingleSpecDetail(specId);
                Debug.print("ArrayList Returned::>"+objSingleSpecDet);
                
                request.setAttribute("objSingleSpecDet",objSingleSpecDet);
                
                return mapping.findForward("frmEditSpecificationType");
                
            }
            
            else if(specProcess!=null && specProcess.equals("update")){
                
                String specId=request.getParameter("specId");
                Debug.print("specId : "+specId);     
                String eventId=request.getParameter("eventTypeId");
                Debug.print("eventName : "+eventId);
                String txtSpec = request.getParameter("txtSpec");
                Debug.print("txtSpec : "+txtSpec);
                String txtDesc = request.getParameter("txtDesc");
                Debug.print("txtDesc : "+txtDesc);
                
                boolean result=false;
                if(specId!=null && specId.trim().length()!=0 && eventId!=null && eventId.trim().length()!=0){
                    
                    
                    if(specId!=null && specId.trim().length()!=0 && txtSpec!=null && txtSpec.trim().length()!=0){
                        
                        boolean exists1 =remote.isEditSpecNameExist(specId,eventId,txtSpec);
                        if(exists1 == false){
                            result = remote.editSpecDetail(specId,eventId,txtSpec,txtDesc);
                            Debug.print("result in servlet: "+result);
                            if(result == true){
                                return mapping.findForward("listSpecMaster");
                            } else{
                                Vector eventObj = (Vector)remote.displayAnnualActivityTypeMaster();
                                Debug.print("Event Details are" + eventObj);
                                request.setAttribute("eventObj",eventObj);
                                request.setAttribute("err","st");
                                ArrayList objSingleSpecDet = remote.displaySingleSpecDetail(specId);
                                Debug.print("ArrayList Returned::>"+objSingleSpecDet);
                                request.setAttribute("objSingleSpecDet",objSingleSpecDet);
                                return mapping.findForward("frmEditSpecificationType");
                            }
                        } else{
                            Vector eventObj = (Vector)remote.displayAnnualActivityTypeMaster();
                            Debug.print("Event Details are" + eventObj);
                            request.setAttribute("eventObj",eventObj);
                            request.setAttribute("err","st");
                            ArrayList objSingleSpecDet = remote.displaySingleSpecDetail(specId);
                            Debug.print("ArrayList Returned::>"+objSingleSpecDet);
                            request.setAttribute("objSingleSpecDet",objSingleSpecDet);
                            return mapping.findForward("frmEditSpecificationType");
                        }
                        
                    }
                    
                }
            }
            
            
        } catch(Exception e){
            System.out.println("Exception"+e);
        }
        return null;
        
    }
}
