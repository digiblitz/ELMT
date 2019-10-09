/*
 * PublicationDetAction.java
 *
 * Created on November 21, 2006, 3:20 PM
 */

package com.mrm.action;

import com.hlccommon.util.Debug;
import com.hlckavery.stateless.HLCkaveryStatelessRemote;
import com.hlckavery.stateless.HLCkaveryStatelessRemoteHome;
import com.hlcmrm.util.HLCPublicationVO;
import java.util.ArrayList;
import java.util.Iterator;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.util.MessageResources;
/**
 *
 * @author hari
 * @version
 */

public class PublicationDetAction extends Action {
    
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
        String name="ejb/HLCKaveryMrmJNDI";
        try {
            MessageResources mr=getResources(request);
            String namingfactory=mr.getMessage("ejbclient.namingfactory");
            String contextfactory=mr.getMessage("ejbclient.contextfactory");
            String urlprovider=mr.getMessage("ejbclient.urlprovider");
            String lookupip=mr.getMessage("ejbclient.ip");
            //String jndiname=mr.getMessage(name);
            System.setProperty(namingfactory,contextfactory);
            System.setProperty(urlprovider,lookupip);
            Context jndiContext = new InitialContext();
            Debug.print("ActionMessage.jndiname:" + name);
            Object objref = jndiContext.lookup(name);
                        
            HttpSession session=request.getSession();
            
            HLCkaveryStatelessRemoteHome home = (HLCkaveryStatelessRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(objref,HLCkaveryStatelessRemoteHome.class);
            HLCkaveryStatelessRemote remote = home.create(); 
            String publicationProcess= (String) request.getParameter("process");
                Debug.print("publicationProcess is "+publicationProcess);
                Debug.print("Inside Servlet");
//====================================================redirects to donation===============================================================                
                if(publicationProcess.equals("initPub")){
                  Debug.print("Publication Detail Servlet is calling.......");                                          
                    try{
                     return mapping.findForward("frmPubliMasterAdd");
                   }
                    catch (Exception e){
                        Debug.print(" Error : "+e.getMessage());
                    }
                }
//====================================================redirects to insert   Donation==================================================                
                else if(publicationProcess.equals("insertPub")){
                    Debug.print("Publication Detail insertPub Servlet is calling.......");      
//                
                    String publicationName = request.getParameter("publicationName");
                   
                    boolean bool = remote.createPublication(publicationName);                      
                      Debug.print("Result for insertPub in Servlet : "+bool);
                      if (bool == true){
                          Debug.print("publicationName  is "+publicationName);
                          Debug.print("Result for add in Servlet : "+bool);
                          return mapping.findForward("frmPubliMasterConf");                          
                      }
                      else{
                          request.setAttribute("err","st");
                          return mapping.findForward("frmPubliMasterAdd");
                       }
                }
//====================================================redirects to Edit Donation=========================================================                
                else if(publicationProcess.equals("editPub")){
                     Debug.print("Publication Detail editPub Servlet is calling.......");    
                    String publicationId = request.getParameter("publicationId");
                    HLCPublicationVO  Vobj = remote.getPublicationDetailsById(publicationId);
                    request.setAttribute("Vobj",Vobj);
                    return mapping.findForward("frmPubliMasterEdit");            
                }
//====================================================redirects to Update Ticket=========================================================                
                else if(publicationProcess.equals("updatePub")){
                    Debug.print("Publication Detail editPub Servlet is calling.......");                                    
                    String publicationId,publicationName;
//                 
                    publicationId = request.getParameter("publicationId");
                    publicationName = request.getParameter("publicationName");
                    Debug.print("publicationId:" + publicationId);
                    Debug.print("publicationName:" + publicationName);
                    boolean bol = remote.editPublication(publicationId,publicationName);
                    if(bol==true){
                            ArrayList objlist = (ArrayList)remote.getAllPublicationDetails();
                            session.setAttribute("DispPubDetails",objlist);
                            return mapping.findForward("frmPubliMasterList");
                    }
                    else
                    {   
                        HLCPublicationVO  Vobj = remote.getPublicationDetailsById(publicationId);
                        request.setAttribute("Vobj",Vobj);
                        request.setAttribute("err","st");
                        return mapping.findForward("frmPubliMasterEdit");
                    }
                }
//====================================================redirects to List Donation=========================================================                                
                else if(publicationProcess.equals("listPub")){
                 Debug.print("Publication Detail Publication Listing Servlet is calling.......");  
                                                         
                ArrayList objlist = (ArrayList)remote.getAllPublicationDetails();                Iterator itr = objlist.iterator();
                session.setAttribute("DispPubDetails",objlist);
                return mapping.findForward("frmPubliMasterList");            
                }
            }
            catch(Exception einsert){
         System.out.println("Donate Action Error " + einsert.toString());
        }                   
   return null;
 
    }

}
