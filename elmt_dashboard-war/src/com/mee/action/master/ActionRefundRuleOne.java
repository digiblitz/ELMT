/*
 * ActionRefundRuleOne.java
 *
 * Created on December 23, 2006, 2:04 PM
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
 * @author Ganapathy
 * @version
 */

public class ActionRefundRuleOne extends Action {
    
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
            
            HttpSession session = request.getSession(true);
            String process = request.getParameter("process");              
            System.out.println("Process is "+process);
                    
                /*  Add 
                 *
                 *      Process flow directed to insert Refund Rule Level One details JSP page
                 */                  
                 if(process.equals("add")){
                 return mapping.findForward("frmMeeRefundRuleCreate");
                 }
                  if(process.equals("insert")){
                    try{
                        Debug.print("\n Inside Refund Rule Adding...\n"); 
                        String refundName=request.getParameter("refundName");
                        boolean bol=false;
                        Debug.print("      refundName:"+refundName);
                        if(refundName!=null && refundName.trim().length()!=0){
                            refundName = refundName.trim();
                            bol = remote.insertRefundRuleMaster(refundName);
                        }
                        Debug.print("Boolean bol="+bol);
                        if (bol == true){
                            Debug.print(" Succeffully Inserted : "+bol);
                            return mapping.findForward("addConfirmation");          
                        }
                        else {   
                            request.setAttribute("err","st");
                            return mapping.findForward("frmMeeRefundRuleCreate");
                        }
                     }
                    catch (Exception e){
                        Debug.print(" Error : "+e.getMessage());
                    }
                  }
                 /*  Delete 
                 *
                 *      Process flow directed to delete Refund Rule Level One details JSP page
                 */  
                  
                  else if(process.equals("deactivate")){
                    try{
                        Debug.print("\n Inside Refund Rule deactivating...\n"); 
                        String refundId=request.getParameter("refundId");
                        System.out.println("refundId ="+refundId);
                        boolean bol = remote.deleteRefundRuleMaster(refundId);
                         System.out.println("bol ="+bol);
                        if (bol == true){
                            Debug.print(" Succeffully Deactivated : "+bol);
                            return mapping.findForward("Confirmation");                        
                        }
                        else {
                          request.setAttribute("err","st");
                          return mapping.findForward("frmRefundRuleDelete");
                        }
                   }
                    catch(Exception e){
                        Debug.print("Error: "+e.getMessage());                      
                    }
                  }
                 /*  Update 
                 *
                 *      Process flow directed to delete Refund Rule Level One details JSP page
                 */  
                  
                   else if(process.equals("update")){
                        try{
                            Debug.print("\n Inside Refund Rule Updating...\n"); 
                            String refundId=request.getParameter("refundId");
                            String refundName=request.getParameter("refundName");
                            boolean bol = remote.updateRefundRuleMaster(refundName,refundId);
                            if (bol == true){
                                Debug.print(" Succeffully Updated : "+bol);
                                return mapping.findForward("Confirmation");                        
                            }
                            else {
                              request.setAttribute("err","st");
                              String[] s=new String[4];
                                Vector vObja = (Vector)remote.displayRefundRule(refundId);
                                 Enumeration e = vObja.elements();
                                if(e.hasMoreElements()){
                                     s = (String [])e.nextElement();   }
                               request.setAttribute("refundDetail",s);
                              return mapping.findForward("frmRefundRuleEdit");
                            }
                   }
                            catch (Exception e){
                                Debug.print(" Error : "+e.getMessage());
                            }
                  }
                   else if(process.equals("view")){
                        try{                                                      
                        String refundId=request.getParameter("refundId");
                        String refundStatus = request.getParameter("btnSubmit");
                                String[] s=new String[4];
                                Vector vObja = (Vector)remote.displayRefundRule(refundId);
                                Enumeration e = vObja.elements();
                                if(e.hasMoreElements()){
                                     s = (String [])e.nextElement();                                    
                                    System.out.println("Hello Test123");
                                    System.out.println(""+s[0]+":"+s[1]+":"+s[2]+":"+s[3]);                           
                                }
                                    request.setAttribute("refundDetail",s);
                                   
                        if(refundStatus.equals("Activate")){
                           return mapping.findForward("frmRefundRuleActivate"); 
                        }
                        else if(refundStatus.equals("Edit")){
                           return mapping.findForward("frmRefundRuleEdit");
                        }
                        else if(refundStatus.equals("Deactivate")){
                           return mapping.findForward("frmRefundRuleDelete");
                        }
                        }
                        catch(Exception e){
                            Debug.print("Error: "+e.getMessage());
                        }
                   }
                   else if(process.equals("list")){
                        try{
                            Debug.print("\n Inside Refund Rules List all ....\n");
                            boolean result = false;
                            String refundstatus = (String)request.getParameter("status");
                            if(refundstatus.equals("activate")){
                               result =true;
                               Vector vObj = (Vector)remote.displayAllRefundRule(result);
                               request.setAttribute("status",refundstatus);
                                request.setAttribute("RefundAllList",vObj);
                                return mapping.findForward("frmRefundRuleList");
                            }
                            if(refundstatus.equals("deactivate")){
                               result =false;
                               Vector vObj = (Vector)remote.displayAllRefundRule(result);
                               request.setAttribute("status",refundstatus);
                                request.setAttribute("RefundAllList",vObj);
                                return mapping.findForward("frmRefundRuleList");
                            }
                            else {
                              request.setAttribute("err","st");
                             return mapping.findForward("frmRefundRuleList");
                            }
                           }
                            catch(Exception e){
                            Debug.print("Error: "+e.getMessage());
                        }
                   }
            
                    
                                   
                         
                       
                   else if(process.equals("activate")){
                        try{
                            Debug.print("\n Inside Refund Rules Activate  ....\n");
                            String refundId=request.getParameter("refundId");
                            boolean bol = remote.activateRefundRuleType(refundId);
                            if (bol == true){
                                Debug.print(" Succeffully Activated : "+bol);
                                return mapping.findForward("Confirmation");                        
                            }
                            else {
                              request.setAttribute("err","st");
                              return mapping.findForward("frmRefundRuleActivate");
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
