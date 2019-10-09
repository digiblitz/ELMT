/*
 * AddMeePriceDetailMasterAction.java
 *
 * Created on September 23, 2006, 11:44 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mee.action;

import com.hlcform.stateless.HLCkaverystatelessRemote;
import com.hlcform.stateless.HLCkaverystatelessRemoteHome;
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
 * @author harmohan
 */
public class AddMeePriceDetailMasterAction extends Action {
    
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

        try
        {
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
            
            String jndiname1=mr.getMessage("jndi.usrreg");
            Object objref1 = jndiContext.lookup(jndiname1);          
            HLCkaverystatelessRemoteHome home1 = (HLCkaverystatelessRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(objref1,HLCkaverystatelessRemoteHome.class);
            HLCkaverystatelessRemote remote1 = home1.create();
                        
            HttpSession session = request.getSession(true);

            System.out.println("\n after InitialContext inside icp user Meeting list action...\n");

            String priceMasterProcess = request.getParameter("priceMasterProcess");
            Debug.print("Activity Category Name In Servlet:" + priceMasterProcess);
            
            String specificationId = null;
            String userTypeId = null;
            String eventRegistrationTypeId = null;
            double dueDatePrice = 0;
            double aftDueDatePrice = 0;
             
            if(priceMasterProcess.equals("addPriceMaster")){
                
                eventRegistrationTypeId = request.getParameter("selCatagoryType");
                Debug.print("eventRegistrationTypeId : "+eventRegistrationTypeId);
                specificationId = request.getParameter("selSpecification");
                Debug.print("specificationId : "+specificationId);
                userTypeId = request.getParameter("selUserType");
                Debug.print(" userTypeId : "+userTypeId);
                dueDatePrice = Double.valueOf(request.getParameter("duePrice")).doubleValue();
                Debug.print(" dueDatePrice : "+dueDatePrice);
                aftDueDatePrice = Double.valueOf(request.getParameter("afterPrice")).doubleValue();
                Debug.print("aftDueDatePrice : "+aftDueDatePrice);
                try {
                    boolean bol = remote.addPriceDetail(specificationId,userTypeId,
                            eventRegistrationTypeId,dueDatePrice,aftDueDatePrice);
                    Debug.print("Result for add in Servlet : "+bol);

                    if (bol == true){
                        return mapping.findForward("confirmViw");
                    }else {
                        request.setAttribute("err","st");
                        return mapping.findForward("ajaxPriceDetailMaster");
                    }
                }catch (Exception e){
                    Debug.print(" Error : "+e.getMessage());
                }
            }
            else if(priceMasterProcess.equals("initPriceList")){
                Vector nobj = (Vector)remote.displayEventRegistrationTypeMaster();
                request.setAttribute("eventTypeList",nobj);
               
                //Vector vObj =(Vector)remote.displaySpecification1();
                //session.setAttribute("specList",vObj);
                Debug.print("sucessfully comes from initPriceList");
                return(mapping.findForward("frmMeeAdminAnnConPriceDetailListings"));
            }
            
            else if(priceMasterProcess.equals("selectPriceList")){
                ArrayList priceList = new ArrayList();
                String eventTypeId = request.getParameter("eventTypeId");
                String specId = request.getParameter("specId");
                if(eventTypeId!=null && eventTypeId.trim().length()!=0 && specId!=null && specId.trim().length()!=0){
                     priceList = remote.displayPriceDetails(eventTypeId, specId);
                }
                Vector nobj = (Vector)remote.displayEventRegistrationTypeMaster();
                request.setAttribute("eventTypeList",nobj);
               
                Vector vObj =(Vector)remote.displaySpecification1();
                request.setAttribute("specList",vObj);
                
                request.setAttribute("eventTypeId",eventTypeId);
                request.setAttribute("specId",specId);
                request.setAttribute("priceListView",priceList);
                Debug.print("sucessfully comes from selectPriceList");
                return(mapping.findForward("frmMeeAdminAnnConPriceDetailListings"));
            }
            
            else if(priceMasterProcess.equals("viewEditPriceList")){
                ArrayList priceList = new ArrayList();
                String btnSubmit = request.getParameter("btnSubmit");
                String priceDetId = request.getParameter("priceDetId");
                
                Vector nobj = (Vector)remote.displayEventRegistrationTypeMaster();
                request.setAttribute("eventTypeList",nobj);
               
                Vector vObj =(Vector)remote.displaySpecification1();
                request.setAttribute("specList",vObj);
                
                //Vector userObj =(Vector)remote.displayUserType();
                Vector userObj =(Vector)remote1.displayMemberType();
                request.setAttribute("userTypeVec",userObj);
               
                String[] priceDetail = {};
                if(priceDetId!=null && priceDetId.trim().length()!=0){
                    String [] priceListDet = remote.displayPriceDetailsById(priceDetId);
                    priceDetail = priceListDet;
                }
                request.setAttribute("priceDetails",priceDetail);
                
                if(btnSubmit.equals("Edit")){
                     return(mapping.findForward("frmMeeAdminAnnConPriceDetailEdit"));
                }
                else if(btnSubmit.equals("View")){
                     return(mapping.findForward("frmMeeAdminAnnConPriceDetailView"));
                }
               Debug.print("sucessfully comes from viewEditPriceList");
               return(mapping.findForward("frmMeeAdminAnnConPriceDetailListings"));
            }
            
             else if(priceMasterProcess.equals("editPriceMaster")){
                String priceId = request.getParameter("priceDetId");
                Debug.print("priceId : "+priceId);
                eventRegistrationTypeId = request.getParameter("eventTypeId");
                Debug.print("eventTypeId : "+eventRegistrationTypeId);
                specificationId = request.getParameter("specId");
                Debug.print("specId : "+specificationId);
                userTypeId = request.getParameter("selUserType");
                Debug.print(" userTypeId : "+userTypeId);
                dueDatePrice = Double.valueOf(request.getParameter("duePrice")).doubleValue();
                Debug.print(" dueDatePrice : "+dueDatePrice);
                aftDueDatePrice = Double.valueOf(request.getParameter("afterPrice")).doubleValue();
                Debug.print("aftDueDatePrice : "+aftDueDatePrice);
                ArrayList priceList = new ArrayList();
                
                if(eventRegistrationTypeId!=null && eventRegistrationTypeId.trim().length()!=0 && specificationId!=null && specificationId.trim().length()!=0){
                     priceList = remote.displayPriceDetails(eventRegistrationTypeId, specificationId);
                }
                
             
                
                Vector nobj = (Vector)remote.displayEventRegistrationTypeMaster();
                request.setAttribute("eventTypeList",nobj);

                Vector vObj =(Vector)remote.displaySpecification1();
                request.setAttribute("specList",vObj);

               // Vector userObj =(Vector)remote.displayUserType();
                Vector userObj =(Vector)remote1.displayMemberType();
                request.setAttribute("userTypeVec",userObj);

               
                try {
                    if(priceId!=null && priceId.trim().length() !=0){
                        boolean bol = remote.editPriceDetail(priceId,specificationId,userTypeId,
                                eventRegistrationTypeId,dueDatePrice,aftDueDatePrice);
                        Debug.print("Result for edit in Servlet : "+bol);
                        
                        
                       if (bol == true){
                             if(eventRegistrationTypeId!=null && eventRegistrationTypeId.trim().length()!=0 && specificationId!=null && specificationId.trim().length()!=0){
                             priceList = remote.displayPriceDetails(eventRegistrationTypeId, specificationId);
                            }
                            request.setAttribute("eventTypeId",eventRegistrationTypeId);
                            request.setAttribute("specId",specificationId);
                            request.setAttribute("priceListView",priceList);
                            return mapping.findForward("frmMeeAdminAnnConPriceDetailListings");
                        }else {
                            request.setAttribute("err","st");
                            String[] priceDetail = {};
                            if(priceId!=null && priceId.trim().length()!=0){
                                String [] priceListDet = remote.displayPriceDetailsById(priceId);
                                priceDetail = priceListDet;
                            }
                            request.setAttribute("priceDetails",priceDetail);
                            return mapping.findForward("frmMeeAdminAnnConPriceDetailEdit");
                        }
                    }
                }catch (Exception e){
                    Debug.print(" Error : "+e.getMessage());
                }
            }
            
            
           
    }catch(Exception ex)
            {
                System.err.println("Caught an exception."+ex.getMessage());
                ex.printStackTrace();
            }
         return(mapping.findForward("ajaxPriceDetailMaster"));
    }
    
}
