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
package com.message.action;
/*
 * InsertAddContactAction.java
 *
 * Created on August 26, 2006, 12:37 AM
 */

import com.hlcform.util.Debug;
import com.message.actionform.InsertAddContactForm;

import com.hlcmsg.util.HLCMsgContactListMaster;
import com.hlcmsg.util.HLCMsgPaginationVO;
import javax.jms.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.util.MessageResources;

import com.hlcmsg.session.HLCMessageSessionRemote;
import com.hlcmsg.session.HLCMessageSessionRemoteHome;

/**
 *
 * @author Shiva Kumar Subbiaha
 * @version
 */

public class InsertAddContactAction extends DispatchAction {
    
    
     public ActionForward initAdd(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
            Debug.print("InsertAddContactAction.initAdd() method calling ..........");      
             return mapping.findForward("formView");
     
     }
     
        
     
         public ActionForward search(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception { 
                Debug.print("InsertAddContactAction.search() method calling ..........");   
                InsertAddContactForm contactForm =(InsertAddContactForm)form;              
                int start =(request.getParameter("start")!=null)?new Integer(request.getParameter("start")).intValue():0;
		int range =(request.getParameter("range")!=null)?new Integer(request.getParameter("range")).intValue():10;                
                String sortableName = (request.getParameter("sortName")!=null)?request.getParameter("sortName"):"";
                Debug.print("SortableName="+sortableName+",Start :"+start + ",Range:"+range); 
                HLCMessageSessionRemote msgRemote =  initializeEJB(request);
                HttpSession session = request.getSession();
                String userId = (String) session.getAttribute("userId");
                Debug.print("User Id "+userId);
                HLCMsgPaginationVO msgPaginationVO = msgRemote.getContactDetails(sortableName,start,range,userId);
                Debug.print("TotalSize="+msgPaginationVO.getTotalRecords()+" ,Results:"+msgPaginationVO.getResults().size()); 
                contactForm.setResultSize(msgPaginationVO.getTotalRecords());
                 //MsgContactListMaster vo object        
                contactForm.setResults(msgPaginationVO.getResults());  
                contactForm.setStart(String.valueOf(start));
                 contactForm.setRange(String.valueOf(range));
                contactForm.setSortName(sortableName);                    
                request.setAttribute(mapping.getName(),contactForm);         
           
            return mapping.findForward("addressView");
         }
         
     
      public ActionForward initEdit(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
               Debug.print("InsertAddContactAction.initEdit() method calling ..........");            
               InsertAddContactForm contactForm =(InsertAddContactForm)form;  
               String contactId = request.getParameter("contactId");
               Debug.print("Contact Details :contactId="+contactId);                
               contactForm = getContactView(request,contactForm,contactId);
               Debug.print("Contact Details update values:"+contactForm);
           request.setAttribute(mapping.getName(),contactForm); 
       return mapping.findForward("formEditView");     
     }
     
      public ActionForward initView(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
               Debug.print("InsertAddContactAction.initView() method calling ..........");            
               InsertAddContactForm contactForm =(InsertAddContactForm)form;  
               String contactId = request.getParameter("contactId");
               Debug.print("Contact Details :contactId="+contactId);   
               //Debug.print("Contact Details update values:"+contactForm);
               request.setAttribute(mapping.getName(),getContactView(request,contactForm,contactId));        
          
       return mapping.findForward("displayView");     
     }
      
      
      public ActionForward deleteAll(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
               Debug.print("InsertAddContactAction.deleteAll() method calling ..........");            
               InsertAddContactForm contactForm =(InsertAddContactForm)form;  
              String[] deleteFields = contactForm.getUserCheckBox();
              Debug.print("Contact Details Deleted values:"+getAsString(deleteFields)); 
               HLCMessageSessionRemote msgRemote =  initializeEJB(request);
               boolean[] checkFlag= new boolean[deleteFields.length];
              for (int i = 0; i < deleteFields.length; i++) {
                  checkFlag[i] = msgRemote.deleteContactListMaster(deleteFields[i]);
              }
         Debug.print("Contact Details Deleted Flag:"+getAsBoolean(checkFlag)); 
         
       return mapping.findForward("searchBackView");     
     }
      
      private InsertAddContactForm getContactView(HttpServletRequest request,InsertAddContactForm contactForm,String contactId) throws Exception{   
                HLCMessageSessionRemote msgRemote =  initializeEJB(request);
                HLCMsgContactListMaster contactDetalils = msgRemote.displayContactListBasedOnContactListIDVO(contactId);
               if(contactDetalils.getAlternateEmailId()==null || contactDetalils.getAlternateEmailId().length()==0){
                   contactDetalils.setAlternateEmailId("-N/A-");
               }
               if(contactDetalils.getMiddleName()==null || contactDetalils.getMiddleName().length()==0){
                   contactDetalils.setMiddleName("-N/A-");
               }
               if(contactDetalils.getMobileNo()==null || contactDetalils.getMobileNo().length()==0){
                   contactDetalils.setMobileNo("-N/A-");
               }
               if(contactDetalils.getFaxNo()==null || contactDetalils.getFaxNo().length()==0){
                   contactDetalils.setFaxNo("-N/A-");
               }                 
                contactForm.setContactId(contactDetalils.getContactlistId());
                contactForm.setFirstname(contactDetalils.getFirstName());
                contactForm.setLastname(contactDetalils.getLastName());
                contactForm.setAlteremailid(contactDetalils.getAlternateEmailId());
                contactForm.setCity(contactDetalils.getCity());
                contactForm.setCountry(contactDetalils.getCountry());
                contactForm.setEmailid(contactDetalils.getEmailId());
                contactForm.setFax(contactDetalils.getFaxNo());
                contactForm.setMiddlename(contactDetalils.getMiddleName());
                contactForm.setNickname(contactDetalils.getNickName());
                contactForm.setMobile(contactDetalils.getMobileNo());
                contactForm.setPhone(contactDetalils.getPhoneNo());
                contactForm.setState(contactDetalils.getState());
                contactForm.setStreet(contactDetalils.getStreet());
                contactForm.setZipcode(contactDetalils.getZip());
         return  contactForm;      
      
      }
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward save(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

                Debug.print("InsertAddContactAction.save() method calling ..........");            
                InsertAddContactForm addconbean =(InsertAddContactForm)form;  
                String userId = (String)request.getSession().getAttribute("userId");
                String emailId = (String)request.getSession().getAttribute("emailId");
                Debug.print("Contact Details :"+addconbean+"\n,userId="+userId+",emailId="+emailId); 
                HLCMessageSessionRemote msgRemote =  initializeEJB(request);                
                HLCMsgContactListMaster objContact = new HLCMsgContactListMaster();  
                String Phno=""; 
                String MobNo ="";
                String FaxNo ="";
        // Phone Number Stripping                
               if(addconbean.getPhone()!=null)
                {
                StringTokenizer strTkns = new StringTokenizer(addconbean.getPhone(),"[](),.-{}");
                                               
                        while(strTkns.hasMoreTokens()){
                            try{
                                String phNo = (String)strTkns.nextToken();
                                
                                if(phNo!=null && phNo.trim().length()!=0){
                                    Phno=Phno+phNo;
                                }
                            }
                                 catch(Exception e){
                                    Debug.print("ph no tokanizing exception:" + e);
                                }
                            }
                }
        // Mobile Number Stripping                
               if(addconbean.getMobile()!=null)
                {
                StringTokenizer strTkns = new StringTokenizer(addconbean.getMobile(),"[](),.-{}");
                                               
                        while(strTkns.hasMoreTokens()){
                            try{
                                String phNo = (String)strTkns.nextToken();
                                
                                if(phNo!=null && phNo.trim().length()!=0){
                                    MobNo=MobNo+phNo;
                                }
                            }
                                 catch(Exception e){
                                    Debug.print("ph no tokanizing exception:" + e);
                                }
                            }
                 }         
            // FAX Number Stripping
               if(addconbean.getFax()!=null)
                {
                StringTokenizer strTkns = new StringTokenizer(addconbean.getFax(),"[](),.-{}");
                        while(strTkns.hasMoreTokens()){
                            try{
                                String phNo = (String)strTkns.nextToken();
                                
                                if(phNo!=null && phNo.trim().length()!=0){
                                    FaxNo=FaxNo+phNo;
                                }
                            }
                                 catch(Exception e){
                                    Debug.print("ph no tokanizing exception:" + e);
                                }
                            }
                }                
                objContact.setUserId(userId);
                objContact.setFirstName(addconbean.getFirstname());
                objContact.setMiddleName(addconbean.getMiddlename());
                objContact.setLastName(addconbean.getLastname());
                objContact.setNickName(addconbean.getNickname());
                objContact.setEmailId(addconbean.getEmailid());
                objContact.setAlternateEmailId(addconbean.getAlteremailid());
                objContact.setPhoneNo(Phno);
                objContact.setMobileNo(MobNo);
                objContact.setFaxNo(FaxNo);
                objContact.setStreet(addconbean.getStreet());
                objContact.setCountry(addconbean.getCountry());
                objContact.setState(addconbean.getState());
                objContact.setCity(addconbean.getCity());
                objContact.setZip(addconbean.getZipcode());                
               boolean insertFlag = msgRemote.addContactList(objContact);               
               Debug.print("Inserted Flag :"+insertFlag);
           form.reset(mapping,request);
        
         /*
          * Redirected to Confirmation page
          */
        return mapping.findForward("confirmView");
    }
    
     private String getAsString(String[] arr)  {
           if(arr==null)
               return null;       
         StringBuffer buffer = new StringBuffer();
         int len =  (arr.length-1);
         for (int i = 0; i < len; i++) {
             buffer.append(arr[i]+",");
         }
         if(arr.length>0)
            buffer.append(arr[arr.length-1]); 
         //buffer.append();
      return buffer.toString();
    }
     
      private String getAsBoolean(boolean[] arr)  {
           if(arr==null)
               return null;       
         StringBuffer buffer = new StringBuffer();
         int len =  (arr.length-1);
         for (int i = 0; i < len; i++) {
             buffer.append(arr[i]+",");
         }
         if(arr.length>0)
            buffer.append(arr[arr.length-1]); 
         //buffer.append();
      return buffer.toString();
    }
    
    public ActionForward edit(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

                Debug.print("InsertAddContactAction.edit() method calling ..........");            
                InsertAddContactForm addconbean =(InsertAddContactForm)form;           
                HLCMessageSessionRemote msgRemote =  initializeEJB(request);
                String Phno=""; 
                String MobNo ="";
                String FaxNo ="";                
        // Phone Number Stripping                
               if(addconbean.getPhone()!=null)
                {
                StringTokenizer strTkns = new StringTokenizer(addconbean.getPhone(),"[](),.-{}");
                                               
                        while(strTkns.hasMoreTokens()){
                            try{
                                String phNo = (String)strTkns.nextToken();
                                
                                if(phNo!=null && phNo.trim().length()!=0){
                                    Phno=Phno+phNo;
                                }
                            }
                                 catch(Exception e){
                                    Debug.print("ph no tokanizing exception:" + e);
                                }
                            }
                }
        // Mobile Number Stripping                
               if(addconbean.getMobile()!=null)
                {
                StringTokenizer strTkns = new StringTokenizer(addconbean.getMobile(),"[](),.-{}");
                                               
                        while(strTkns.hasMoreTokens()){
                            try{
                                String phNo = (String)strTkns.nextToken();
                                
                                if(phNo!=null && phNo.trim().length()!=0){
                                    MobNo=MobNo+phNo;
                                }
                            }
                                 catch(Exception e){
                                    Debug.print("ph no tokanizing exception:" + e);
                                }
                            }
                }         
            // FAX Number Stripping
               if(addconbean.getFax()!=null){
                    StringTokenizer strTkns = new StringTokenizer(addconbean.getFax(),"[](),.-{}");

                    while(strTkns.hasMoreTokens()){
                        try{
                            String phNo = (String)strTkns.nextToken();

                            if(phNo!=null && phNo.trim().length()!=0){
                                FaxNo=FaxNo+phNo;
                            }
                        }
                             catch(Exception e){
                                Debug.print("ph no tokanizing exception:" + e);
                            }
                        }
                }                      
                HLCMsgContactListMaster objContact = new HLCMsgContactListMaster();  
                objContact.setContactlistId(addconbean.getContactId());                
                objContact.setFirstName(addconbean.getFirstname());
                objContact.setMiddleName(addconbean.getMiddlename());
                objContact.setLastName(addconbean.getLastname());
                objContact.setNickName(addconbean.getNickname());
                objContact.setEmailId(addconbean.getEmailid());
                objContact.setAlternateEmailId(addconbean.getAlteremailid());
                objContact.setPhoneNo(Phno);
                objContact.setMobileNo(MobNo);
                objContact.setFaxNo(FaxNo);
                objContact.setStreet(addconbean.getStreet());
                objContact.setCountry(addconbean.getCountry());
                objContact.setState(addconbean.getState());
                objContact.setCity(addconbean.getCity());
                objContact.setZip(addconbean.getZipcode());                
               boolean insertFlag = msgRemote.editContactListMaster(objContact);               
               Debug.print("Contact details Updated Flag :"+insertFlag);
        
         /*
          * Redirected to Confirmation page
          */
        return mapping.findForward("searchBackView");
    }
    
    
     private HLCMessageSessionRemote initializeEJB(HttpServletRequest request) throws Exception{              
            
                MessageResources mr = getResources(request);
                String namingfactory=mr.getMessage("ejbclient.namingfactory");
                String contextfactory=mr.getMessage("ejbclient.contextfactory");
                String urlprovider=mr.getMessage("ejbclient.urlprovider");
                String lookupip=mr.getMessage("ejbclient.ip");
                String jndiname=mr.getMessage("jndi.contactDetail");

                System.setProperty(namingfactory,contextfactory);
                System.setProperty(urlprovider,lookupip);
                
                Context jndiContext = new InitialContext();
                Object objref = jndiContext.lookup(jndiname);  
                     HLCMessageSessionRemoteHome home = (HLCMessageSessionRemoteHome)
                     javax.rmi.PortableRemoteObject.narrow(objref,HLCMessageSessionRemoteHome.class);
                     HLCMessageSessionRemote remote = home.create();
                
              return remote;  
          
        }
        
    }

