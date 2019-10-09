/*******************************************************************************
 * * * Copyright: 2019 digiBlitz Foundation
 *  * * 
 *  * * License: digiBlitz Public License 1.0 (DPL) 
 *  * * Administered by digiBlitz Foundation. www.digiblitz.org/dpl/
 *  * * 
 *  * * Inventor: Suresh Kannan (Maya Suresh Kannan Balabisegan ) (www.sureshkannan.org)
 *  * * 
 *  * * Authors: Suresh Kannan (Maya Suresh Kannan Balabisegan )& digiBlitz.
 *  * * 
 *  * * "Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software in accordance to the rules & restrictions of the digiBlitz Public License."
 ******************************************************************************/
package com.mrm.action;
import com.hlcform.util.HLCMemberDetails;
import com.hlchorse.form.util.HLCUserRegVO;
import com.hlcHorse.Form.Display.HLCkaverySessionBeanStatlessRemote;
import com.hlcHorse.Form.Display.HLCkaverySessionBeanStatlessRemoteHome;
import com.hlcevent.edu.HLCEducationalSessionRemote;
import com.hlcevent.edu.HLCEducationalSessionRemoteHome;
import com.hlcreg.util.Debug;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.vo.UsrSignUpVO;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import javax.servlet.http.HttpSession;

import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import org.apache.struts.util.MessageResources;
import com.hlcform.stateless.*;
import com.hlchorse.form.display.*;
/**
 *
 * @author karthikeyan
 * @version
 */

public class EditUserRegAction extends Action {
    
 
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
               String fwd="";
               HttpSession session=request.getSession();
               HLCUserRegVO userregvo=new HLCUserRegVO();
               String userId=(String)session.getAttribute("userId");
               
         try {
             
                    String status=request.getParameter("status");
                    
                    System .out.println("status =" +status);
                    
                     MessageResources mr=getResources(request);
                    
                    /*
                     * Service Locator For ejb/HLCKaverySessionStatefulBean JNDI 
                     */ 
                    
                    String namingfactory=mr.getMessage("ejbclient.namingfactory");
                    String contextfactory=mr.getMessage("ejbclient.contextfactory");
                    String urlprovider=mr.getMessage("ejbclient.urlprovider");
                    String lookupip=mr.getMessage("ejbclient.ip");
                    String jndiname=mr.getMessage("jndi.kaverysful");
                    String jndiname2=mr.getMessage("jndi.usrreg");
                    
                    System.setProperty(namingfactory,contextfactory);
                    System.setProperty(urlprovider,lookupip);
                    Context jndiContext = new InitialContext();
                    Object objref = jndiContext.lookup(jndiname);
                    UsrSignUpVO usrVal=new UsrSignUpVO();
                    
                    Object objref2 = jndiContext.lookup(jndiname2);
                    HLCkaverystatelessRemoteHome home1 = (HLCkaverystatelessRemoteHome)
                    javax.rmi.PortableRemoteObject.narrow(objref2,HLCkaverystatelessRemoteHome.class);
                    HLCkaverystatelessRemote remote1 = home1.create();
                                                        
                    HLCKaverySessionStatefulRemoteHome home =
                    (HLCKaverySessionStatefulRemoteHome) PortableRemoteObject.narrow(objref, HLCKaverySessionStatefulRemoteHome.class);
                    
                    HLCKaverySessionStatefulRemote remote = null;
                    remote = home.create();
                    
                    if(status!=null)
                    {
                        
                        /*
                         * Redirecting to User Signup.jsp
                         */
                        
                     if(status.equalsIgnoreCase("signup"))
                    {
                         fwd="NewSignDisp";
                         System .out.println("inside status =" +status);                        
                    }                   
                                            
                        /*
                         * Redirecting to User Registration.jsp
                         */
                        
                    if(status.equalsIgnoreCase("register"))
                    {
                          HttpServlet ser = getServlet();
      
                          ServletContext context=ser.getServletContext();
                          System.out.println("after ser.getServletContext() .....");

                          //String[] usrVal=(String[])context.getAttribute("usrVal");
                       // String[] usrVal=(String[])request.getAttribute("usrVal");
                          
                        usrVal=(UsrSignUpVO)context.getAttribute("usrVal");  
                        request.setAttribute("usrVal",usrVal);

                        if(usrVal!=null)
                        {Debug.print("Inside servlet ctx usrVal inside edit usr :"+usrVal.toString());}
                        else
                        {Debug.print("Inside servlet ctx usrVal inside edit usr is empty");}
                          
                        fwd="UserReg";
                        System.out.println("inside status =" +status);
                    }
                    
                    /*
                     * Redirecting to ViewUserReg.jsp
                     */
                    
                    if(status.equalsIgnoreCase("view"))
                    {
                        Vector EditusrVect=new Vector();
                        String usrId=(String)session.getAttribute("userId");
                        Debug.print("User Id :"+ usrId);
                        HLCMemberDetails objMember = new HLCMemberDetails();
                        
                        userregvo = remote.selectUserRegistrationForm1(usrId);
                        String membId=(String)session.getAttribute("memberId");
                        
                        if(membId!=null)
                        {
                            objMember=remote1.displayMemberDetail(membId);
                            request.setAttribute("objMember",objMember);
                        }
                        
                        Object objref1 = jndiContext.lookup("ejb/HLCkaverySessionBeanStatlessBean");
                        HLCkaverySessionBeanStatlessRemoteHome home3 =(HLCkaverySessionBeanStatlessRemoteHome) PortableRemoteObject.narrow(objref1, HLCkaverySessionBeanStatlessRemoteHome.class);
                        HLCkaverySessionBeanStatlessRemote remote3 = home3.create();
                        
                          String jndinameed=mr.getMessage("jndi.education");
                          Object objNew = jndiContext.lookup(jndinameed);
                          HLCEducationalSessionRemoteHome eduHome = (HLCEducationalSessionRemoteHome)
                          javax.rmi.PortableRemoteObject.narrow(objNew,HLCEducationalSessionRemoteHome.class);
                          HLCEducationalSessionRemote eduRemote = eduHome.create();
                          
                          ArrayList resultNew = (ArrayList)remote1.getUserContactDetailsForAdmin(usrId);
                          String mailStatus = (String)resultNew.get(16);
                          request.setAttribute("mailStatus",mailStatus);
                          
                         ArrayList donSelect=new ArrayList();
                         donSelect=remote3.getMemberDonationDetails(userId);
                         
                        request.setAttribute("EditusrVect",userregvo);
                        request.setAttribute("donSelect",donSelect);
                        
                        fwd="ViewUserReg";
                        System .out.println("inside status =" +status);
                     
                    }
                    
                    /*
                     * Redirecting to EditUserReg.jsp by getting User Details From EJB 
                     */
                    
                    if(status.equalsIgnoreCase("edit")) 
                    {
                        Vector EditusrVect=new Vector();
                        String usrId=(String)session.getAttribute("userId");
                        Debug.print("loginName :"+usrId);
                    
                        userregvo = remote.selectUserRegistrationForm1(usrId);

                        request.setAttribute("EditusrVect",userregvo);
                                                  
                        System .out.println("inside status =" +status);
                        fwd="EditUserReg";
                    }
                     if(status.equalsIgnoreCase("searchSignup")){
                         Debug.print("Sign up at Search");
                         session.setAttribute("DBLeftPrivlegeList" , null);
                         fwd="frmsearchSignup";
                     }
              }
                 
           
         }
         
          catch (Exception ex) {
            System.err.println("Caught an exception.");
            ex.printStackTrace();
         }
               
         /*
          * Forwarding to appropriate request
          */
            return mapping.findForward(fwd);
         }
        
    }
        

