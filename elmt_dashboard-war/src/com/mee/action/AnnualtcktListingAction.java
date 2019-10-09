/*
 * Program Name     :   AnnualtcktListingAction.java
 * Created Date     :   November 2, 2006, 5:57 PM
 * Author           :   Hari
 * Copy Right       :   digiBlitz Technologies Inc /  digiBlitz Technologies (P) Ltd
 * Version          :   1.2
 */

package com.mee.action;

import com.hlccommon.util.Debug;
import com.hlcmeeting.session.HLCVaigaiSessionRemote;
import com.hlcmeeting.session.HLCVaigaiSessionRemoteHome;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import javax.naming.Context;
import javax.naming.InitialContext;
import org.apache.struts.util.MessageResources;
import java.util.*;

public class AnnualtcktListingAction extends Action {
    
    String forward = "";

    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
            try{
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

                    String process = request.getParameter("process");              
                    System.out.println("Process is "+process);

                /*  Add 
                 *
                 *      Process flow directed to insert ticket data JSP page
                 */                  
                  if (process.equals("add")){
                    try{
                        Debug.print("\n Inside Ticket Adding...\n");  
                        ArrayList det =  (ArrayList)remote.getAllTicketDetails();
                        session.setAttribute("details",det);
                        Debug.print("Array List Size is"+det.size());  
                        forward = "AddAnnualTkt";
                        request.setAttribute("err","");                    
                        return mapping.findForward(forward);                        
                   }
                    catch (Exception e){
                        Debug.print(" Error : "+e.getMessage());
                    }
                  }

                /*  Insert 
                 *
                 *      Process directs to inserting ticket data 
                 *      If insertion success flow directed to ticket booked Confirmation
                 *      If insertion fails flow directed to add ticket page
                 */
//====================================================insert Ticket===============================================================  
                  
              else if(process.equalsIgnoreCase("insert")){
                    String act_type =null;
                    String ticket=null;
                    Debug.print("\n Inside Insert adding...\n");                      
                    act_type = request.getParameter("act_type"); 
                    ticket =request.getParameter("ticket"); 
                    boolean bol = remote.createTicketDetails(ticket,act_type);                      
                    Debug.print("Result for add in Servlet : "+bol);
                      if (bol == true){
                          Debug.print("Activity Type is "+act_type+" Ticket value is "+ticket);
                          Debug.print("Result for add in Servlet : "+bol);
                          request.setAttribute("err","");
                          return mapping.findForward("ConfirAnnualTckt");
                       }
                      else{
                        request.setAttribute("err","st");  
                        return mapping.findForward("AddAnnualTkt");
                      }
              }
            /*  Edit 
             *
             *      Process directs to Editing ticket values based on the ticket ID
             */                    
//=============================================Get Ticket Details=================================================================              
              else if (process.equalsIgnoreCase("edit")){
                  Debug.print("\n Inside Ticket Editing...\n");          
                  String tkt_id = request.getParameter("act_typ");
                  Debug.print("Ticket Id is "+tkt_id);
              
                  String[] ticket_details = (String[])remote.getTicketDetails(tkt_id);
                  request.setAttribute("err","");                 	
                  session.setAttribute("value",ticket_details);
                  return mapping.findForward("EditAnnualTkt");
              }
            /*  Update Edit
             *
             *      Process directs to Editing ticket values based on the ticket ID
             *      If update success flow directed to ticket listing page
             *      If update fails flow directed to add ticket page
             */                      
//===============================================Update Ticket================================================================              
              else if(process.equalsIgnoreCase("updateedit")){
                    try{
                        Debug.print("\n Inside Ticket Update Editing...\n");                                  
                        String annualTId = request.getParameter("act_typ");
                        String ticketNumber = request.getParameter("ticket");
                        Debug.print("Ticket Id is"+annualTId);
                        Debug.print("Ticket No is"+ticketNumber);                       
                        boolean result =  remote.updateTicketDetails(annualTId,ticketNumber);
                        Debug.print("result Update:" + result);
                         if(result==true){
                                ArrayList lst = (ArrayList)remote.getAllTicketDetails();
                                request.setAttribute("details",lst);
                                return mapping.findForward("ViewAnnualTkt");
                            }
                         else{
                                return mapping.findForward("EditAnnualTkt"); 
                            }
                    }
                    catch(Exception eEditDim){
                       Debug.print("While editing the Ticket :::;" + eEditDim);
                   }
              }
                    
            /*  Ticket Listing
             *
             *      Process directs to Listing ticket values based on the Ticket ID, Ticket Number
             */ 
              else if (process.equalsIgnoreCase("list")){
                  Debug.print("\n Inside Ticket Listing...\n");                            
                  System.out.println("\n Inside Ticket Listing...\n");
                  ArrayList det =  (ArrayList)remote.getAllTicketDetails();
                  request.setAttribute("details",det);
                  return mapping.findForward("ViewAnnualTkt");
              }
            }
         catch(Exception einsert){
         System.out.println("Annual Ticket Listing Action Error " + einsert);
        }     
        return null; 
        }
}

