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
/*
 * ActionOwnerAndRiderDetail.java
 *
 * Created on March 14, 2007, 1:20 PM
 */

import com.hlcHorse.Form.Display.HLCkaverySessionBeanStatlessRemote;
import com.hlcHorse.Form.Display.HLCkaverySessionBeanStatlessRemoteHome;
import com.hlccommon.util.Debug;
import com.hlcform.util.HLCMemberDetails;
import com.hlchorse.form.util.HLCUserRegVO;
import com.hlckavery.stateless.HLCkaveryStatelessRemote;
import com.hlckavery.stateless.HLCkaveryStatelessRemoteHome;
import com.hlckavery.statful.HLCKaverySessionBeanStatfulRemote;
import com.hlckavery.statful.HLCKaverySessionBeanStatfulRemoteHome;
import com.hlcmember.type.stateless.HLCKaveryMembershipTypeSessionRemote;
import com.hlcmember.type.stateless.HLCKaveryMembershipTypeSessionRemoteHome;
import javax.servlet.http.*;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import com.hlcform.util.HLCPaymentDetails;
import java.util.*;
import java.util.Date;
import java.text.*;
import org.apache.struts.util.*;
import com.hlcform.stateless.*;
import com.hlchorse.form.display.*;

public class ActionOwnerAndRiderDetail extends Action {
    
    String fwd="";
    
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
            
            HttpSession session=request.getSession();
            String usrNam=(String) session.getAttribute("loginName");
            String usrId=(String) session.getAttribute("userId");
            
            
            MessageResources mr=getResources(request);
            String namingfactory=mr.getMessage("ejbclient.namingfactory");
            String contextfactory=mr.getMessage("ejbclient.contextfactory");
            String urlprovider=mr.getMessage("ejbclient.urlprovider");
            
            String lookupip=mr.getMessage("ejbclient.ip");
            String jndiname=mr.getMessage("jndi.kaverysless");
            String jndiname2=mr.getMessage("jndi.usrreg");
            String jndiname3=mr.getMessage("jndi.kaverymrm");
            String jndiname4=mr.getMessage("jndi.kaverysful");
            
            System.setProperty(namingfactory,contextfactory);
            System.setProperty(urlprovider,lookupip);
            Context jndiContext = new InitialContext();
            Object objref = jndiContext.lookup(jndiname);
            
            HLCkaverySessionBeanStatlessRemoteHome home =(HLCkaverySessionBeanStatlessRemoteHome) PortableRemoteObject.narrow(objref, HLCkaverySessionBeanStatlessRemoteHome.class);
            Vector dispMembershipTypeVect = new Vector();
            Vector areUAMemberVect = new Vector();
            Vector membershipToVect = new Vector();
            Vector serviceTypeVect = new Vector();
            
            HLCkaverySessionBeanStatlessRemote remote = home.create();
            dispMembershipTypeVect = remote.displayMembershipType("Human");
            areUAMemberVect = remote.areUAMember();
            membershipToVect = remote.membershipTo();
            String[] addonPrice=remote.getFamilyAddOn("family member");
            String country=remote.getCountryName(usrId);
            Debug.print("User Country :"+country);
            
            Object objref2 = jndiContext.lookup(jndiname2);
            HLCkaverystatelessRemoteHome home1 = (HLCkaverystatelessRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(objref2,HLCkaverystatelessRemoteHome.class);
            HLCkaverystatelessRemote remote1 = home1.create();
            
            Object objref3 = jndiContext.lookup(jndiname3);
            HLCkaveryStatelessRemoteHome home2 = (HLCkaveryStatelessRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(objref3,HLCkaveryStatelessRemoteHome.class);
            HLCkaveryStatelessRemote remote2 = home2.create();
            
            String name="ejb/HLCKaveryJNDI";
            Object horseref = jndiContext.lookup(name);
            HLCKaverySessionBeanStatfulRemoteHome horsehome = (HLCKaverySessionBeanStatfulRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(horseref,HLCKaverySessionBeanStatfulRemoteHome.class);
            HLCKaverySessionBeanStatfulRemote horseremote = horsehome.create();
            
            Object objref4 = jndiContext.lookup(jndiname4);
            HLCKaverySessionStatefulRemoteHome home4 =(HLCKaverySessionStatefulRemoteHome)
            PortableRemoteObject.narrow(objref4, HLCKaverySessionStatefulRemoteHome.class);
            HLCKaverySessionStatefulRemote remote4 = null;
            remote4 = home4.create();
                      
            String name1 = "ejb/HLCKaveryMembershipTypeJNDI";
            Object obj=jndiContext.lookup(name1);
            HLCKaveryMembershipTypeSessionRemoteHome memberHome = (HLCKaveryMembershipTypeSessionRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(obj,HLCKaveryMembershipTypeSessionRemoteHome.class);
            HLCKaveryMembershipTypeSessionRemote memberRemote = memberHome.create();
            
            String jndiname5=mr.getMessage("jndi.usrreg");
            Object objref5 = jndiContext.lookup(jndiname5);

            HLCkaverystatelessRemoteHome home5 = (HLCkaverystatelessRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(objref5,HLCkaverystatelessRemoteHome.class);
            HLCkaverystatelessRemote remote5 = home5.create(); 
                 
            ArrayList donDet=new ArrayList();
            donDet=remote2.getAllDonationDetails();
            
            String tempMemberId = request.getParameter("memberId");
            String memberId ="";
            String userIdforDate = (String)session.getAttribute("userId");
            String process=request.getParameter("process");
            
            
            if(process!=null && process.equalsIgnoreCase("view")) {
                
                if(tempMemberId != null && tempMemberId.trim().length() != 0) {
                    memberId = tempMemberId;
                    userIdforDate = remote1.getUserIdBasedOnMemberId(tempMemberId);
                    Debug.print("request.getParameter(memberId) :"+memberId);
                }
                
                
                if(request.getParameter("userId") != null && request.getParameter("userId").trim().length() != 0) {
                    userIdforDate=request.getParameter("userId");
                    Debug.print("request.getParameter(userId) :"+userIdforDate);
                    request.setAttribute("userId",userIdforDate);
                    
                    if(request.getParameter("ownerInfo") != null && request.getParameter("ownerInfo").trim().length() != 0) {
                        if(request.getParameter("ownerInfo").equalsIgnoreCase("ownerValue")) {
                            Debug.print("request.getParameter(ownerInfo) :"+request.getParameter("ownerInfo"));
                            
                            memberId= remote1.getMemberIdBasedOnUserId(userIdforDate);
                            Debug.print("remote1.getMemberIdBasedOnUserId(userIdforDate) :"+memberId);
                            if(memberId==null || memberId.trim().length()==0){
                                HLCUserRegVO ownerInfo=new HLCUserRegVO();
                                ownerInfo = remote4.selectUserRegistrationForm1(userIdforDate);
                                request.setAttribute("EditusrVect",ownerInfo);
                                return mapping.findForward("DispOwnerDetails");
                            }
                        }
                    }
                }
                
                
                Debug.print("userId from servlet :"+userIdforDate);
                
                Vector userVect3=memberRemote.selectDetailsForMember(memberId);
                request.setAttribute("userTypeVect3",userVect3);
                
                ArrayList objlist = (ArrayList)remote2.getAllPublicationDetails();
                request.setAttribute("DispPubDetails",objlist);
                
                HLCMemberDetails objMember = new HLCMemberDetails();
                objMember=remote1.displayMemberDetail(memberId);
                request.setAttribute("objMember",objMember);
                
                ArrayList nonhlc=new ArrayList();
                ArrayList donSelect=new ArrayList();
                ArrayList mailPreference = new ArrayList();
                ArrayList familyAddOnId = new ArrayList();
                mailPreference = memberRemote.getMailPreference(memberId);
                familyAddOnId = memberRemote.getFamilyAddOnId(memberId);
                
                nonhlc=remote.getMemberNonUseaDetails(memberId);
                
                donSelect=remote.getMemberDonationDetails(userIdforDate);
                
                request.setAttribute("nonhlc",nonhlc);
                request.setAttribute("donSelect",donSelect);
                request.setAttribute("mailPreference",mailPreference);
                request.setAttribute("familyAddOnId",familyAddOnId);
                
                HLCPaymentDetails objPayment = new HLCPaymentDetails();
                objPayment=remote1.getPaymentDetails(objMember.getPaymentId());
                request.setAttribute("objPayment",objPayment);
                
                Debug.print("objMember.getPaymentId() :"+objMember.getPaymentId());
                System.out.println("objPayment :"+objPayment.toString());
                
                ArrayList resultNew = (ArrayList)remote5.getUserContactDetailsForAdmin(userIdforDate);
                String mailStatus = (String)resultNew.get(16);
                String splNotes = (String)resultNew.get(17);
                System.out.println("Click mailStatus:"+mailStatus);
                System.out.println("Click splNotes:"+splNotes);
                request.setAttribute("mailStatus",mailStatus);
                request.setAttribute("splNotes",splNotes);
                Date approvalDate =(Date) remote1.getApprovedDate(memberId);
                request.setAttribute("approvalDate",approvalDate);
                
                HLCUserRegVO userregvo=new HLCUserRegVO();
                userregvo = remote4.selectUserRegistrationForm1(userIdforDate);
                Debug.print("user details :"+userregvo.toString());
                
                request.setAttribute("userregvo",userregvo);
                
            }
        } catch(Exception e) {
            System.out.println("client exception :" +e);
        }
        return mapping.findForward("HorseMemberDetails");
    }
}