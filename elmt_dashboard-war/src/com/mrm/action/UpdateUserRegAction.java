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
 * UpdateUserRegAction.java
 *
 * Created on August 28, 2006, 9:42 PM
 */

import com.hlcform.stateless.HLCkaverystatelessRemote;
import com.hlcform.stateless.HLCkaverystatelessRemoteHome;
import com.hlcform.util.HLCContactDetails;
import com.hlcform.util.Debug;
import com.hlcform.util.HLCUserMaster;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.regex.*;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import org.apache.struts.util.MessageResources;
import com.mrm.actionform.*;
import com.mysql.dao.MySQLDAO;

/**
 *
 * @author karthikeyan
 * @version
 */

public class UpdateUserRegAction extends Action {
    
    String fwd="";
    
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session=request.getSession();
        
        try {
            
             /*
              * Service Locator for ejb/HLCMemberRegistrationJNDI JNDI
              */
            
            MessageResources mr=getResources(request);
            
            String namingfactory=mr.getMessage("ejbclient.namingfactory");
            String contextfactory=mr.getMessage("ejbclient.contextfactory");
            String urlprovider=mr.getMessage("ejbclient.urlprovider");
            String lookupip=mr.getMessage("ejbclient.ip");
            String jndiname=mr.getMessage("jndi.usrreg");
             String requestStat=(String)session.getAttribute("requestStat");
              String req_Value=(String)session.getAttribute("req_Value");
              String displayButton=(String)session.getAttribute("viewButton");
              System.out.println("request status..."+requestStat);
              System.out.println("request value..."+req_Value);
              System.out.println("display value..."+displayButton);
            System.setProperty(namingfactory,contextfactory);
            System.setProperty(urlprovider,lookupip);
            Context jndiContext = new InitialContext();
            Object objref = jndiContext.lookup(jndiname);
            String email=(String)session.getAttribute("emailId");
            String userId=(String)session.getAttribute("userId");
            
            System.out.println("After Look up......");
            
            HLCkaverystatelessRemoteHome home = (HLCkaverystatelessRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(objref,HLCkaverystatelessRemoteHome.class);
            HLCkaverystatelessRemote remote = home.create();
            
            HLCUserMaster objUserMaster = new HLCUserMaster();
            HLCContactDetails objContact = new HLCContactDetails();
            
              /*
               * Updating User Details by ejb/HLCMemberRegistrationJNDI JNDI
               */
            
            UpdateUserRegActionForm usrbean=(UpdateUserRegActionForm)form;
            
            String bday=usrbean.getBirthyear()+"-"+usrbean.getBirthmonth()+"-"+usrbean.getBirthday();
            System.out.println(bday);
            
            System.out.println(usrbean.getSelect());
            System.out.println(usrbean.getFname());
            System.out.println(usrbean.getMname());
            System.out.println(usrbean.getLname());
            System.out.println(usrbean.getSname());
            System.out.println(usrbean.getBirthmonth());
            System.out.println(usrbean.getBirthday());
            System.out.println(usrbean.getBirthyear());
            System.out.println(usrbean.getGender());
            System.out.println(usrbean.getPhone());
            System.out.println(usrbean.getMobile());
            System.out.println(usrbean.getFax());
            //System.out.println(email);
            
            System.out.println(usrbean.getPriAdd_cbx());
            System.out.println(usrbean.getSecAdd_cbx());
            System.out.println(usrbean.getPadd_txt());
            System.out.println(usrbean.getPadd_txt2());
            System.out.println(usrbean.getPcountry_sel());
            System.out.println(usrbean.getPstate_sel());
            System.out.println(usrbean.getPcity_txt());
            System.out.println(usrbean.getPzip_txt());
            System.out.println(usrbean.getSadd_txt());
            System.out.println(usrbean.getSadd_txt1());
            System.out.println(usrbean.getScountry_txt());
            System.out.println(usrbean.getSstate_txt());
            System.out.println(usrbean.getScity_txt());
            System.out.println(usrbean.getSzip_txt());
            System.out.println(usrbean.getComAdd_sel());
            
            objUserMaster.setPrefix(usrbean.getSelect());
            objUserMaster.setFirstName(usrbean.getFname());
            objUserMaster.setMiddleName(usrbean.getMname());
            objUserMaster.setLastName(usrbean.getLname());
            objUserMaster.setSufix(usrbean.getSname());
            objUserMaster.setDob(bday);
            objUserMaster.setGender(usrbean.getGender());
            objUserMaster.setCommunicationAddress(usrbean.getComAdd_sel());
            objUserMaster.setEmailId(request.getParameter("email"));
            objUserMaster.setUserId(userId);
            objUserMaster.setLoginName(request.getParameter("usrname"));
            objUserMaster.setUserCode(request.getParameter("userCode"));
            
            String finalPrimaryPh="";
            
            if(request.getParameter("pphone_txt")!=null) {
                Debug.print("request.getParameter(pphone_txt) value:"+request.getParameter("pphone_txt"));
                
                StringTokenizer strTkns = new StringTokenizer(request.getParameter("pphone_txt"),"[](),.-{}");
                
                while(strTkns.hasMoreTokens()){
                    try{
                        String phNo = (String)strTkns.nextToken();
                        
                        if(phNo!=null && phNo.trim().length()!=0){
                            Debug.print("ph no Added from Stokenizer:" + phNo);
                            finalPrimaryPh=finalPrimaryPh+phNo;
                        }
                    } catch(Exception e){
                        Debug.print("ph no tokanizing exception:" + e);
                    }
                }
                
                Debug.print("finally appended primary phNo :"+finalPrimaryPh);
            }
            
            String finalPrimaryMob="";
            
            if(request.getParameter("pmob_txt")!=null) {
                Debug.print("request.getParameter(pmob_txt) value:"+request.getParameter("pmob_txt"));
                
                StringTokenizer strTkns = new StringTokenizer(request.getParameter("pmob_txt"),"[](),.-{}");
                
                while(strTkns.hasMoreTokens()){
                    try{
                        String phNo = (String)strTkns.nextToken();
                        
                        if(phNo!=null && phNo.trim().length()!=0){
                            Debug.print("ph no Added from Stokenizer:" + phNo);
                            finalPrimaryMob=finalPrimaryMob+phNo;
                        }
                    } catch(Exception e){
                        Debug.print("ph no tokanizing exception:" + e);
                    }
                }
                
                Debug.print("finally appended primary finalPrimaryMob :"+finalPrimaryMob);
            }
            
            String finalPrimaryFax="";
            
            if(request.getParameter("pfax_txt")!=null) {
                Debug.print("request.getParameter(pfax_txt) value:"+request.getParameter("pfax_txt"));
                
                StringTokenizer strTkns = new StringTokenizer(request.getParameter("pfax_txt"),"[](),.-{}");
                
                while(strTkns.hasMoreTokens()){
                    try{
                        String phNo = (String)strTkns.nextToken();
                        
                        if(phNo!=null && phNo.trim().length()!=0){
                            Debug.print("ph no Added from Stokenizer:" + phNo);
                            finalPrimaryFax=finalPrimaryFax+phNo;
                        }
                    } catch(Exception e){
                        Debug.print("ph no tokanizing exception:" + e);
                    }
                }
                
                Debug.print("finally appended primary finalPrimaryFax :"+finalPrimaryFax);
            }
             int count=remote.getRolesCountForUser(userId);
            
            session.setAttribute("loginName",request.getParameter("usrname"));
            session.setAttribute("emailId",request.getParameter("email"));
            session.setAttribute("firstName",usrbean.getFname());
            session.setAttribute("lastName",usrbean.getLname());
            session.setAttribute("phoneNo",finalPrimaryPh);
            
            objContact.setContactType(usrbean.getPriAdd_cbx());
            objContact.setAddress1(usrbean.getPadd_txt());
            objContact.setAddress2(usrbean.getPadd_txt2());
            objContact.setCity(usrbean.getPcity_txt());
            objContact.setState(usrbean.getPstate_sel());
            objContact.setCountry(usrbean.getPcountry_sel());
            objContact.setZip(usrbean.getPzip_txt());
            objContact.setPhoneNo(finalPrimaryPh);
            objContact.setMobileNo(finalPrimaryMob);
            objContact.setFaxNo(finalPrimaryFax);
            System.out.println("nonUseaEmail.status:="+request.getParameter("nonUseaEmail"));
            System.out.println("nonUseaMail.status:="+request.getParameter("nonUseaMail"));
            
            if(request.getParameter("nonUseaEmail")!=null ){
                objUserMaster.setNonUseaEmailStatus(true);
                System.out.println("commit nonUseaEMail status--true");
            } else {
                objUserMaster.setNonUseaEmailStatus(false);
                System.out.println("commit nonUseaEMail status--false");
            }
            if(request.getParameter("nonUseaMail")!=null){
                objUserMaster.setNonUseaMailingStatus(true);
                System.out.println("commit nonUseaMail status--true");
            } else {
                objUserMaster.setNonUseaMailingStatus(false);
                System.out.println("commit nonUseaMail status--false");
            }
            remote.editUserDetails(objUserMaster, objContact);
            
            Debug.print("objUserMaster.getLoginName() :"+objUserMaster.getLoginName());
            Debug.print("objUserMaster.getEmailId() :"+objUserMaster.getEmailId());
            
            //boolean result = new MySQLDAO().updateUserDetailToMqSQL(objUserMaster, objContact);
            //Debug.print("                MySql Result :" + result);
            
                /*
                 * If Secondary address is updated or added
                 */
            
            String secstat=request.getParameter("cttyp");
            String userid=request.getParameter("userid");
            
            System.out.println("sec status :"+secstat);
            System.out.println("user id :"+userid);
            
            
            if(usrbean.getSecAdd_cbx()!=null) {
                objContact.setUserId(userid);
                objContact.setContactType(usrbean.getSecAdd_cbx());
                objContact.setAddress1(usrbean.getSadd_txt());
                objContact.setAddress2(usrbean.getSadd_txt1());
                objContact.setCity(usrbean.getScity_txt());
                objContact.setState(usrbean.getSstate_txt());
                objContact.setCountry(usrbean.getScountry_txt());
                objContact.setZip(usrbean.getSzip_txt());
                
                Debug.print("request.getParameter(sphone_txt) value:"+request.getParameter("sphone_txt"));
                
                String finalSecPh="";
                if(request.getParameter("sphone_txt")!=null) {
                    
                    StringTokenizer strTkns1 = new StringTokenizer(request.getParameter("sphone_txt"),"[](),.-{}");
                    
                    
                    while(strTkns1.hasMoreTokens()){
                        try{
                            String phNo = (String)strTkns1.nextToken();
                            
                            if(phNo!=null && phNo.trim().length()!=0){
                                Debug.print("ph no Added from Stokenizer:" + phNo);
                                finalSecPh=finalSecPh+phNo;
                            }
                        } catch(Exception e){
                            Debug.print("Secondary ph no tokanizing exception:" + e);
                        }
                    }
                    
                    Debug.print("finally appended Secondary phNo :"+finalSecPh);
                }
                
                String finalSecMob="";
                if(request.getParameter("smob_txt")!=null) {
                    
                    StringTokenizer strTkns1 = new StringTokenizer(request.getParameter("smob_txt"),"[](),.-{}");
                    
                    
                    while(strTkns1.hasMoreTokens()){
                        try{
                            String phNo = (String)strTkns1.nextToken();
                            
                            if(phNo!=null && phNo.trim().length()!=0){
                                Debug.print("ph no Added from Stokenizer:" + phNo);
                                finalSecMob=finalSecMob+phNo;
                            }
                        } catch(Exception e){
                            Debug.print("Secondary ph no tokanizing exception:" + e);
                        }
                    }
                    
                    Debug.print("finally appended Secondary finalSecMob :"+finalSecMob);
                }
                
                String finalSecFax="";
                if(request.getParameter("sfax_txt")!=null) {
                    
                    StringTokenizer strTkns1 = new StringTokenizer(request.getParameter("sfax_txt"),"[](),.-{}");
                    
                    
                    while(strTkns1.hasMoreTokens()){
                        try{
                            String phNo = (String)strTkns1.nextToken();
                            
                            if(phNo!=null && phNo.trim().length()!=0){
                                Debug.print("ph no Added from Stokenizer:" + phNo);
                                finalSecFax=finalSecFax+phNo;
                            }
                        } catch(Exception e){
                            Debug.print("Secondary ph no tokanizing exception:" + e);
                        }
                    }
                    
                    Debug.print("finally appended Secondary finalSecFax :"+finalSecFax);
                }
                
                objContact.setPhoneNo(finalSecPh);
                objContact.setFaxNo(finalSecFax);
                objContact.setMobileNo(finalSecMob);
                // remote.addContactDetails(objContact);
                
                remote.editUserDetails(objUserMaster, objContact);
                
            }
             if(requestStat.equalsIgnoreCase("true"))
                {    
                    System.out.println("first if ......");
                    request.setAttribute("requestStatus","true");
                    request.setAttribute("requestValue","true");
                    fwd="LoginSuccess";
                }
             else if(displayButton!=null && requestStat.equalsIgnoreCase("false") && displayButton.equalsIgnoreCase("true"))
                {
                    System.out.println("second if else......");
                    
                    request.setAttribute("viewButton","true");
                    request.setAttribute("requestStatus","false");
                   fwd="Confirmation";
                }
                else if(requestStat.equalsIgnoreCase("false") && req_Value.equalsIgnoreCase("true"))
                {
                    System.out.println("Third if else......");
                    request.setAttribute("viewButton","true");
                   
                    request.setAttribute("requestStatus","false");
                    request.setAttribute("requestValue","true");
                    fwd="Confirmation"; 
                }
            else if(requestStat.equalsIgnoreCase("false") && req_Value.equalsIgnoreCase("false"))
                {
                    request.setAttribute("requestStatus","false");
                    request.setAttribute("requestValue","false");
                   // request.setAttribute("viewButton","true");
                    fwd="Confirmation"; 
                }
            
            if(secstat!=null && usrbean.getSecAdd_cbx()==null ) {
                boolean delstat=remote.deleteContactDetail(userId,"Secondary");
                System.out.println("Secondary Contact Delete Status :" +delstat);
            }
            String forEE = (String)session.getAttribute("forEE");
            if(forEE!=null && forEE.equalsIgnoreCase("fromEventEntry")){
                fwd = "fromEventEntry";
            }else{
                fwd="Confirmation";
            }
            
        }
        
        catch (Exception ex) {
            System.err.println("Caught an exception.");
            ex.printStackTrace();
        }
        
        if(request.getParameter("confPwd")!=null && request.getParameter("confPwd").trim().length()!=0) {
            String confPwd=request.getParameter("confPwd");
            Debug.print("confPwd :"+confPwd);
            
            session.setAttribute("confPwd",confPwd);
            
            fwd="changePwd";
        }
        System.out.println("fwd=="+fwd);
     /*
      * Redirecting to confirmation page
      */
        return mapping.findForward(fwd);
        
        
        
    }
    
}




