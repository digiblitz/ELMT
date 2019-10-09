/*
 * AddrDistAction.java
 *
 * Created on September 28, 2006, 2:55 PM
 */

package com.msg.action;

import com.hlcmsg.session.HLCMessageSessionRemote;
import com.hlcmsg.session.HLCMessageSessionRemoteHome;
import com.hlcmsg.util.Debug;
import com.hlcmsg.util.HLCMsgContactListMaster;
import com.hlcmsg.util.HLCMsgDistributionListMaster;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import org.apache.struts.util.MessageResources;

import com.hlcmsg.session.HLCMessageSessionRemote;
import com.hlcmsg.session.HLCMessageSessionRemoteHome;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import org.apache.struts.util.MessageResources;

/**
 *
 * @author karthikeyan
 * @version
 */

public class AddrDistAction extends Action {
    
  
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
        
         String fwd="";
        
        try
        {
                    /*
                     * service Locator For MessageSessionBean		
                     */
            
                    HttpSession session=request.getSession() ;
                    MessageResources mr=getResources(request);
                    
                    String namingfactory=mr.getMessage("ejbclient.namingfactory");
                    String contextfactory=mr.getMessage("ejbclient.contextfactory");
                    String urlprovider=mr.getMessage("ejbclient.urlprovider");
                    String lookupip=mr.getMessage("ejbclient.ip");
                    String jndiname=mr.getMessage("jndi.message");
                    
                    System.setProperty(namingfactory,contextfactory);
                    System.setProperty(urlprovider,lookupip);
                    Context jndiContext = new InitialContext();
                    
                    Object obj=jndiContext.lookup(jndiname);
                    HLCMessageSessionRemoteHome home = (HLCMessageSessionRemoteHome)
                    javax.rmi.PortableRemoteObject.narrow(obj,HLCMessageSessionRemoteHome.class);
                    HLCMessageSessionRemote remote = home.create();
        
                    HLCMsgContactListMaster objContList = new HLCMsgContactListMaster();
                    HLCMsgDistributionListMaster objDistList = new HLCMsgDistributionListMaster();
 
                    String process=request.getParameter("process");
                    String userId=(String)session.getAttribute("userId");
                        
                    
                    if(process!=null) {
                    
                        /*
                         * Redirecting to displayDList
                         */
                        
                        if(process.equalsIgnoreCase("Create")) 
                        {
                           
                            Vector addrList=new Vector();
                            addrList=(Vector)remote.displayAddressBook(userId);				
                            request.setAttribute("addrList",addrList);
                            System.out.println("addrList size :"+addrList.size());
                            
                            fwd="displayDList";
                        }   
                                         
                        /*
                         * Redirecting to Confirm after addingdist list
                         */
                        
                        if(process.equalsIgnoreCase("createDList")) 
                        {
                            String[] distList=null;
                            System.out.println("inside insert block....");
                            
                            String listName=request.getParameter("listName");
                            System.out.println("Dist List name :"+listName);
                            
                            distList=request.getParameterValues("distsel");
                            System.out.println("selected contact length :"+distList.length);
                          
                            for(int i=0;i<distList.length;i++)
                            {
                                 System.out.println("selected List :"+distList[i]);
                                
                                 objDistList.setContactlistId(distList[i]);
                                 objDistList.setListName(listName);
                                 boolean status=remote.addDisttList(objDistList);
                                 
                                 System.out.println("Status for insertion is :" +status);
                                 
                            }
                           
                            fwd="viewDistList";
                            
                        }
                        
                        /*
                         * Redirect to ListDistList
                         */
                        
                        if(process.equalsIgnoreCase("listDistList"))
                        {
                            Vector listDistList=new Vector();
                            listDistList=remote.displayDistribitionListNameAndNoOfMembers(userId);
                            System.out.println("listDistList size :" +listDistList.size());
                            
                            request.setAttribute("listDistList",listDistList);
                            
                            fwd="listDistList";
                        }
                         if(process.equalsIgnoreCase("MsgDistListView")){
                            String status = request.getParameter("status");
                            String dlistName = request.getParameter("dlistName");
                            System.out.println("dlistName ::>"+dlistName);
                            if(status != null && status.equalsIgnoreCase("Edit")){
                                 System.out.println("inside edit block .....");
                             
                                Vector addrList=null;
                                addrList=(Vector)remote.displayAddressBook(userId);	
                                //The returned Vector is set in request Attribute.
                                request.setAttribute("addrList",addrList);
                                System.out.println("addrList size :"+addrList.size());

                                // Display Distribution List based on list name

                                //String dlistName=request.getParameter("dlistName");
                                //String listName="hi-fi";
                                Vector dList=null;
                                dList=remote.displayDistList(dlistName.trim());
                                Debug.print("Vector Returned:"+dList);                                
                                //The returned Vector is set in request Attribute.
                                request.setAttribute("dList",dList);

                                fwd="editDistList";
                            }else if(status!= null && status.equalsIgnoreCase("View")){
                                System.out.println("inside view block .....");
                            
                                //String dlistName=request.getParameter("dlistName");
                                Vector viewDistList=new Vector();
                                viewDistList=remote.displayMemberAdded(dlistName);
                                //The returned Vector is set in request Attribute.
                                request.setAttribute("viewDistList",viewDistList);
                                request.setAttribute("dlistName",dlistName);

                                System.out.println("viewDistList size :" +viewDistList.size());
                                fwd="DistListView";
                            }
                        }
                        /*
                         * Redirect to viewDistList
                         */
                         
                         /*if(process.equalsIgnoreCase("View"))
                        {
                            System.out.println("inside view block .....");
                            
                            String dlistName=request.getParameter("dlistName");
                            Vector viewDistList=new Vector();
                            viewDistList=remote.displayMemberAdded(dlistName);
                            request.setAttribute("viewDistList",viewDistList);
                            request.setAttribute("dlistName",dlistName);
                            
                            System.out.println("viewDistList size :" +viewDistList.size());
                            fwd="DistListView";
                            
                        }*/
                        
                        /*
                         * Redirect to confirmation after deleting a contact from a distlist
                         */
                        
                         if(process.equalsIgnoreCase("delDistList"))
                        {
                            
                            //deleteDistMasterBasedOnContactId(String contactId)				

                            String contid=request.getParameter("contid");
                            boolean stat=remote.deleteDistributionListMaster(contid);
                            System.out.println("delete contact name on list status :"+stat );
                            fwd="detViewDistList";
                        }
                        
                        /*
                         * Redirect to edit distlist
                         */
                        
                       /* if(process.equalsIgnoreCase("Edit"))
                        {
                            // Display Address Book based on user id
                            
                            System.out.println("inside edit block .....");
                             
                            Vector addrList=new Vector();
                            addrList=(Vector)remote.displayAddressBook(userId);				
                            request.setAttribute("addrList",addrList);
                            System.out.println("addrList size :"+addrList.size());
                            
                            // Display Distribution List based on list name
                            
                            String dlistName=request.getParameter("dlistName");
                            //String listName="hi-fi";
                            Vector dList=new Vector();
                            dList=remote.displayDistList(dlistName);
                            request.setAttribute("dList",dList);
                           
                            fwd="editDistList";
                            
                        }*/
                        
                        /*
                         * Redirect to confirm distlist
                         */
                        
                         if(process.equalsIgnoreCase("Update"))
                        {
                            // delete dlist block
                            
                            System.out.println("inside Update block .....");
                            String lName=request.getParameter("listname");
                            System.out.println("Dist List name :"+lName);
                            boolean dstat=remote.deleteDistListBasedOnListName(lName);
                            System.out.println("Update delete list status :"+dstat);
                            
                            // add dlist block
                          
                            String[] dListSel=request.getParameterValues("distsel");
                            String lname= request.getParameter("lstname");
                            
                            System.out.println("selected contact length :"+dListSel.length);
                          
                            for(int j=0;j<dListSel.length;j++)
                            {
                                 System.out.println("selected List :"+dListSel[j]);
                                
                                 objDistList.setContactlistId(dListSel[j]);
                                 objDistList.setListName(lname);
                                 boolean status=remote.addDisttList(objDistList);
                                 
                                 System.out.println("Status for Updation"+j+ "is :" +status);
                                 
                                  fwd="viewDistList";
                                 
                            }
                            
                         }
                            
                            if(process.equalsIgnoreCase("Delete")) 
                            {
                                 System.out.println("Inside Delete List Block..........");
                                 
                                String dlistct=request.getParameter("dlistct");
                                String[] dlistname=dlistct.split("#");
                                        
                                for(int y=0;y<dlistname.length;y++)
                                {
                                    boolean dliststatus=remote.deleteDistListBasedOnListName(dlistname[y]);
                                    System.out.println("Distribution List :"+dlistname[y]+"is deleted......"+dliststatus);
                                 
                                }
                                 fwd="viewDistList";
                            }
                        
                         if(process.equalsIgnoreCase("Delete List")) 
                            {
                                 System.out.println("Inside Delete List Block..........");
                                 
                                String dlistName1=request.getParameter("dlistName1");
                                                                       
                                    boolean dliststatus=remote.deleteDistListBasedOnListName(dlistName1);
                                    System.out.println("Distribution List :"+dlistName1+"is deleted......"+dliststatus);
                                 
                                
                                 fwd="viewDistList";
                            }
                            
                    }   
              
        }
                    catch(Exception e)
                    {
                        System.out.println("client side exception :" +e);
                    }
         
         return mapping.findForward(fwd);
         
    }
                    

}
