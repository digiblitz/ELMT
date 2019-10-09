/*
 * Program Name     :   DonateAction.java
 * Created Date     :   November 16, 2006, 6:05 PM
 * Author           :   Hari
 * Copy Right       :   digiBlitz Technologies Inc /  digiBlitz Technologies (P) Ltd
 * Version          :   1.3
 * CopyRightInformation:
 *  (c)2006,digiBlitz Technologies Inc/digiBlitz Technologies (P) Ltd. All rights reserved.
 *  916 W. Broad Street Suite 205, FallsChurch, VA 22046.
 *  This document is protected by copyright. No part of this document may be reproduced in any form by any means without
 *  prior written authorization of Sun and its licensors. if any.
 *  The information described in this document may be protected by one or more U.S.patents.foreign patents,or
 *  pending applications.
 */

package com.mrm.action;

import com.hlccommon.util.Debug;
import com.hlckavery.stateless.HLCkaveryStatelessRemote;
import com.hlckavery.stateless.HLCkaveryStatelessRemoteHome;
import com.hlcmember.type.stateless.HLCKaveryMembershipTypeSessionRemote;
import com.hlcmember.type.stateless.HLCKaveryMembershipTypeSessionRemoteHome;
import com.hlcmrm.util.HLCDonationVO;
import java.util.ArrayList;
import java.util.Iterator;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.util.MessageResources;


public class DonateAction extends Action {
    
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

                String membTypename = "ejb/HLCKaveryMembershipTypeJNDI";
                Object obj=jndiContext.lookup(membTypename);
                HLCKaveryMembershipTypeSessionRemoteHome membTypeHome = (HLCKaveryMembershipTypeSessionRemoteHome)
                javax.rmi.PortableRemoteObject.narrow(obj,HLCKaveryMembershipTypeSessionRemoteHome.class);
                HLCKaveryMembershipTypeSessionRemote membTyperemote = membTypeHome.create();               
                
                HLCkaveryStatelessRemoteHome home = (HLCkaveryStatelessRemoteHome)
                javax.rmi.PortableRemoteObject.narrow(objref,HLCkaveryStatelessRemoteHome.class);
                HLCkaveryStatelessRemote remote = home.create(); 
                
                String process= (String) request.getParameter("process");
                Debug.print("Process is "+process);
                Debug.print("Inside Servlet");
        /*  Add 
         *
         *      Process flow directed to add Donate information
         */  
                if(process.equals("add")){
                  Debug.print("\n Inside Donate Action insert...\n");                                          
                    try{
                    request.setAttribute("err","");       
                    ArrayList accDetails = (ArrayList) membTyperemote.getAccTxnTypDetails();
                    request.setAttribute("accDetails",accDetails);
                    return mapping.findForward("frmAddDonationDetails");
                   }
                    catch (Exception e){
                        Debug.print(" Error : "+e.getMessage());
                    }
                }
       /*  Insert 
         *
         *      Process flow directed to list donation details on success
         *      Process flow directed to add page on failure
         */
                if(process.equals("insert")){
                    Debug.print("\n Inside Donation Insert...\n");      
                    String donationId;
                    boolean chk_bol=false;
                    String donationName = request.getParameter("DonationTypeName");
                    String donationPrice = request.getParameter("DonationAmount") ;
                    String pre_check_stat = request.getParameter("preset");
                    String transacType = request.getParameter("transacType");
                    String priorValue=request.getParameter("txtPrior");
                    int priority=0;
                    if(priorValue!=null && priorValue.trim().length()!=0){
                    priority=Integer.parseInt(priorValue);    
                    }
                    if(pre_check_stat.equalsIgnoreCase("True")){
                        chk_bol = true;
                    }                    
                    Debug.print("chk_bol is "+chk_bol);
                    String status = (String)request.getParameter("stat");
                    boolean bol = remote.createDonation(donationName,donationPrice,transacType,chk_bol,priority);  
                    if(bol==true){
                        Debug.print("Result for add in Servlet : "+bol);
                        Debug.print("Donation Name is "+donationName+" Amount is "+donationPrice);
                        Debug.print("Result for add in Servlet : "+bol);
                        ArrayList objlist = (ArrayList) remote.getAllDonationDetails();
                        request.setAttribute("list",objlist);
                        request.setAttribute("err",""); 
                        return mapping.findForward("frmDonationConfir");                          
                      }
                    else{
                        request.setAttribute("err","st");
                        ArrayList accDetails = (ArrayList) membTyperemote.getAccTxnTypDetails();
                        request.setAttribute("accDetails",accDetails);                          
                        return mapping.findForward("frmAddDonationDetails");
                       }
                }
        /*  Edit 
         *
         *      Process flow directed to Editing donation info based on donation ID
         */                 
                if(process.equals("edit")){
                    Debug.print("\n Inside Donation Editing...\n");        
                    String donationId = request.getParameter("donationId");
                    HLCDonationVO Vobj = remote.getDonationDetailsById(donationId);
                     Debug.print("donationId ="+ Vobj.getDonationId());
                     Debug.print("donationName ="+ Vobj.getDonationName());
                     Debug.print("donationPrice ="+ Vobj.getDonationPrice());
                     Debug.print("donationStatus"+ Vobj.isActiveStatus());
                     Debug.print("Pre_check Status "+Vobj.isPrecheckStatus());
                     Debug.print("Transaction Type Id "+Vobj.getTransaction_type_id());
                    ArrayList accDetails = (ArrayList) membTyperemote.getAccTxnTypDetails();
                    request.setAttribute("accDetails",accDetails);                     
                     //Debug.print("Donation Precheck Status "+Vobj)
                    request.setAttribute("Vobj",Vobj);
                    request.setAttribute("err",""); 
                    return mapping.findForward("frmEditDonationDetails");            
                }
       /*  Update 
         *
         *      Process flow directed to Update dontaion info based on donation ID
         *      On success it redirects to List Dontaion Details
         *      On failure it redirects to Edit Dontaion Details
         */                
                if(process.equals("update")){
                    Debug.print("\n Inside Donation Update...\n");                                          
                    String donationId,donationName,donationPrice;

                    donationId = request.getParameter("donationTypeId");
                    donationName = request.getParameter("DonationTypeName");
                    donationPrice = request.getParameter("DonationAmount");
                    String pre_check_stat = request.getParameter("preset");
                    String transacType = request.getParameter("transacType");
                    String priorValue=request.getParameter("txtPrior");
                    int priority=0;
                    if(priorValue!=null && priorValue.trim().length()!=0){
                    priority=Integer.parseInt(priorValue);    
                    }
                    //String status = (String)request.getParameter("status");
                    boolean chk_bol=false;
                     if(pre_check_stat.equalsIgnoreCase("True")){
                        chk_bol = true;
                    }                    
                    Debug.print("chk_bol is "+chk_bol);
                    boolean bol = remote.editDonation(donationId,donationName,donationPrice,transacType,chk_bol,priority);
                    if(bol==true){
                            ArrayList objlist = new ArrayList();//)remote.getAllDonationDetails();
                            Iterator itr = objlist.iterator();
                            request.setAttribute("list",objlist);
                            request.setAttribute("err",""); 
                            String stat = (String)request.getParameter("status");
                            //ArrayList objlist = new ArrayList();
                            if((stat==null))//||(stat.equalsIgnoreCase("Select")))
                            {
                                request.setAttribute("bol_stat","novalue");
                                request.setAttribute("list",null);
                            }
                            if(stat.equalsIgnoreCase("Select")){
                                request.setAttribute("bol_stat","novalue");
                                request.setAttribute("list",null);
                            }
                            if(stat.equalsIgnoreCase("Activate")){
                                 objlist = (ArrayList)remote.getAllDonationDetailsBasedOnStatus(true);
                                 request.setAttribute("bol_stat","Activate");
                                 request.setAttribute("list",objlist);                         
                            }
                            else{
                                 objlist = (ArrayList)remote.getAllDonationDetailsBasedOnStatus(false);                                                
                                 request.setAttribute("bol_stat","Deactivate");                         
                                 request.setAttribute("list",objlist);
                            }
                            return mapping.findForward("frmDonationList");
                    }
                    else{
                        HLCDonationVO Vobj = remote.getDonationDetailsById(donationId);
                        Debug.print("donationId ="+ Vobj.getDonationId());
                        Debug.print("donationName ="+ Vobj.getDonationName());
                        Debug.print("donationPrice ="+ Vobj.getDonationPrice());
                        Debug.print("donationStatus"+ Vobj.isActiveStatus());
                        //Debug.print("Donation Precheck Status "+Vobj)
                        request.setAttribute("Vobj",Vobj);
                        request.setAttribute("err","st");  
                        ArrayList accDetails = (ArrayList) membTyperemote.getAccTxnTypDetails();
                        request.setAttribute("accDetails",accDetails);
                        return mapping.findForward("frmEditDonationDetails");
                    }
                }
        /*  List 
         *
         *      Process flow directed to Listing Donation info along with donation ID
         */            
                if(process.equals("list")){
                     Debug.print("\n Inside Donation Lsting...\n"); 
                      ArrayList objlist = new ArrayList();
                      request.setAttribute("list",null);
                      return mapping.findForward("frmDonationList");
                }
                
                if(process.equals("lst")){
                    Debug.print("\n Inside Donation Listing...\n"); 
                    String stat = (String) request.getParameter("status");
                    Debug.print("Status "+stat);
                    ArrayList objlist = new ArrayList();
                    if((stat==null))//||(stat.equalsIgnoreCase("Select")))
                    {
                        request.setAttribute("bol_stat","novalue");
                        request.setAttribute("list",null);
                    }
                    if(stat.equalsIgnoreCase("Select")){
                        request.setAttribute("bol_stat","novalue");
                        request.setAttribute("list",null);
                    }
                    if(stat.equalsIgnoreCase("Activate")){
                         objlist = (ArrayList)remote.getAllDonationDetailsBasedOnStatus(true);
                         request.setAttribute("bol_stat","Activate");
                         request.setAttribute("list",objlist);                         
                    }
                    else{
                         objlist = (ArrayList)remote.getAllDonationDetailsBasedOnStatus(false);                                                
                         request.setAttribute("bol_stat","Deactivate");                         
                         request.setAttribute("list",objlist);
                    }
                    return mapping.findForward("frmDonationList");            
                }
                
                if(process.equalsIgnoreCase("activate")){
                    Debug.print("\n Inside Activate Donation Listing...\n");                                                              
                    String donationID = (String)request.getParameter("donationId");
                    Debug.print("DonationID "+donationID);
                    boolean res  = remote.activateDonation(donationID);
                    Debug.print("Result of activate is "+res);
                    ArrayList objlist = (ArrayList)remote.getAllDonationDetailsBasedOnStatus(true);
                    request.setAttribute("bol_stat","Deactivate");
                    request.setAttribute("list",objlist);
                    return mapping.findForward("frmDonationList");                          
                }
                
                if(process.equalsIgnoreCase("deactivate")){
                    Debug.print("\n Inside Deactivate Donation Listing...\n");                                                              
                    String donationID = (String)request.getParameter("donationId");
                    Debug.print("DonationID "+donationID);
                    boolean res  = remote.deactivateDonation(donationID);
                    Debug.print("Result of deactivate is "+res);
                    ArrayList objlist = (ArrayList)remote.getAllDonationDetailsBasedOnStatus(false);
                    request.setAttribute("bol_stat","Activate");
                    request.setAttribute("list",objlist);
                    return mapping.findForward("frmDonationList");                          
                }   
            }
            catch(Exception einsert){
                System.out.println("Donate Action Error " + einsert.toString());
            }                   
   return null;
    }
}
