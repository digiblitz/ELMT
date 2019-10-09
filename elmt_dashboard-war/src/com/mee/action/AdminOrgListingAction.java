/*
 * ActionAdvertisement.java
 *
 * Created on September 1, 2006, 10:25 AM
 */
package com.mee.action;
//import com.adv.actionform.FormAdvertisement;
import com.adv.actionform.AdvertisementFormVO;
import com.adv.actionform.SearchAdvtForm;
import com.hlccommon.util.Debug;
import com.hlchorse.form.display.*;
import com.hlcmeeting.session.HLCVaigaiSessionRemote;
import com.hlcmeeting.session.HLCVaigaiSessionRemoteHome;

import com.mee.actionform.AdminOrgListingForm;
import com.mee.actionform.MemberContactDetails;
import com.util.AttributeConstant;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.util.LabelValueBean;
import org.apache.struts.util.MessageResources;

import java.io.*;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import java.util.Properties;
import java.util.*;

/**
 *
 * @author Shiva Kumar Subbiaha
 * @version
 */
public class AdminOrgListingAction extends DispatchAction {
 
    public ActionForward initAdmin(ActionMapping mapping, ActionForm  form,HttpServletRequest request, HttpServletResponse response)
		throws Exception {     
		   Debug.print("AdminOrgListingAction.initAdmin() called ...."); 
		
		return mapping.findForward("success");       
	  } 
         
        public ActionForward initOrg(ActionMapping mapping, ActionForm  form,HttpServletRequest request, HttpServletResponse response)
		throws Exception {     
		   Debug.print("AdminOrgListingAction.initOrg() called ...."); 
		
		return mapping.findForward("orgView");       
	  } 
         
  public ActionForward searchAdmin(ActionMapping mapping, ActionForm  form,HttpServletRequest request, HttpServletResponse response)
		throws Exception {     
		   Debug.print("AdminOrgListingAction.searchAdmin() called ...."); 
		  HLCVaigaiSessionRemote listRemote = initializeEJB(request);
                  AdminOrgListingForm adminOrgForm = (AdminOrgListingForm)form;  
                  if(request.getAttribute(AttributeConstant.ADMIN_LISTING_FORM_NAME)!=null)
                      adminOrgForm =(AdminOrgListingForm) request.getAttribute(AttributeConstant.ADMIN_LISTING_FORM_NAME);
                  
                  String status = adminOrgForm.getStatus();                  
                  Debug.print("status="+status);                 
                  Vector resultObj = listRemote.displayICPRegistrationForm(status);
	          adminOrgForm.setResults(getList(resultObj));
                  request.setAttribute(mapping.getName(),adminOrgForm);	               
		return mapping.findForward("AdminList");       
	  } 
         
         public ActionForward viewAdmin(ActionMapping mapping, ActionForm  form,HttpServletRequest request, HttpServletResponse response)
		throws Exception {     
		  Debug.print("AdminOrgListingAction.viewAdmin() called ...."); 
		  HLCVaigaiSessionRemote listRemote = initializeEJB(request);
                  String icpMeetingId = request.getParameter("icpMeetingId");
                  String reqstatus = request.getParameter("usrStat");
                  String comments = request.getParameter("commentarea");
                  
                  Debug.print("IcpMeeting Id is "+icpMeetingId);
                  Debug.print("reqstatus="+reqstatus);
                  Debug.print("comments"+comments);
            //Update for the icp meeting register                  
            //listRemote.update
                  Vector resultObj = listRemote.displayICPRegBasedOnMeetingId(icpMeetingId);
                  AdminOrgVO adminOrg =  getAdminView(resultObj); 
                  Debug.print("HLCContactDetails : "+adminOrg);   
	         request.setAttribute("ViewOrgDetails",resultObj);
		return mapping.findForward("ApprovePage");       
	  }
         
         
          public ActionForward viewOrg(ActionMapping mapping, ActionForm  form,HttpServletRequest request, HttpServletResponse response)
		throws Exception {     
		   Debug.print("AdminOrgListingAction.viewAdmin() called ...."); 
		  HLCVaigaiSessionRemote listRemote = initializeEJB(request);                              
                  String icpMeetingId = request.getParameter("icpMeetingId")                 ;
                  Debug.print("IcpMeetingId="+icpMeetingId);                 
                  Vector resultObj =  listRemote.displayICPRegBasedOnMeetingId(icpMeetingId);
                  AdminOrgVO adminOrg =  getAdminView(resultObj);
                  request.setAttribute("MeeDetails",resultObj);
                  Debug.print("HLCContactDetails : "+adminOrg);   
	          request.setAttribute("ViewOrgDetails",adminOrg);
                  return mapping.findForward("viewOrgStatus");       
	  }
       
          public ActionForward AdminupdateStatus(ActionMapping mapping, ActionForm  form,HttpServletRequest request, HttpServletResponse response)
		throws Exception {
                  HLCVaigaiSessionRemote listRemote = initializeEJB(request);
                  String icpMeetingId = request.getParameter("icpMeetingId");
                  String reqstatus = request.getParameter("usrStat");
                  String comments = request.getParameter("commentarea");
                  Debug.print("IcpMeeting Id is "+icpMeetingId);
                  Debug.print("reqstatus="+reqstatus);
                  Debug.print("comments"+comments);
                  String approvedBy =  (String)request.getSession().getAttribute("userCode");
                  boolean updateStatus =  listRemote.editStatusICPRequest(icpMeetingId,reqstatus,approvedBy,comments);
                  
                  Debug.print("Update status :"+updateStatus); 
                  return mapping.findForward("searchPage");
          }
          
            public ActionForward searchOrg(ActionMapping mapping, ActionForm  form,HttpServletRequest request, HttpServletResponse response)
		throws Exception {     
		   Debug.print("AdminOrgListingAction.searchOrg() called ...."); 
		  HLCVaigaiSessionRemote listRemote = initializeEJB(request);
                  AdminOrgListingForm adminOrgForm = (AdminOrgListingForm)form;                   
                  String instructorId =  (String)request.getSession().getAttribute("memberId");                   
                  Debug.print("Member Id from the session :"+instructorId);
                  Vector resultObj =  listRemote.displayICPRegBasedOnInstructorId(instructorId);
                  List orgDetails = getOrgList(resultObj);
	          Debug.print("Org Member Details:"+orgDetails);
                  adminOrgForm.setResults(orgDetails);                  
                  request.setAttribute(mapping.getName(),adminOrgForm);	               
		return mapping.findForward("orgView");       
	  } 
            
         
          public ActionForward updateStatus(ActionMapping mapping, ActionForm  form,HttpServletRequest request, HttpServletResponse response)
		throws Exception {     
              
		 Debug.print("AdminOrgListingAction.updateStatus() called ...."); 
		 HLCVaigaiSessionRemote listRemote = initializeEJB(request);          
                 AdminOrgListingForm adminOrgForm = (AdminOrgListingForm)form;  
                 String meetingId = adminOrgForm.getMeetingId();  
                 String status  = adminOrgForm.getStatus();
                 String approvedBy =  (String)request.getSession().getAttribute("userCode");
                 String comments = adminOrgForm.getComments();

                 Debug.print("meetingId="+meetingId);                 
                 Debug.print("status="+status);    
                 Debug.print("approvedBy="+approvedBy);    
                 Debug.print("comments="+comments);    
                      
                 boolean updateStatus =  listRemote.editStatusICPRequest(meetingId,status,approvedBy,comments);
                  
                 Debug.print("Update status :"+updateStatus); 
                  
	         request.setAttribute(mapping.getName(),adminOrgForm);	
                  
                String userCode =  (String)request.getSession().getAttribute("userCode");
		return mapping.findForward("searchPage");       
	  } 
       
          
          private HLCVaigaiSessionRemote initializeEJB(HttpServletRequest request) throws Exception{            
                MessageResources mr=getResources(request);                
                String namingfactory=mr.getMessage("ejbclient.namingfactory");
                String contextfactory=mr.getMessage("ejbclient.contextfactory");
                String urlprovider=mr.getMessage("ejbclient.urlprovider");
                String lookupip=mr.getMessage("ejbclient.ip");
                String jndiname=mr.getMessage("jndi.meetingVaigai");

                System.setProperty(namingfactory,contextfactory);
                System.setProperty(urlprovider,lookupip);
                Context jndiContext = new InitialContext();
                Object objref = jndiContext.lookup(jndiname); 
   
                    HLCVaigaiSessionRemoteHome home = (HLCVaigaiSessionRemoteHome)
                javax.rmi.PortableRemoteObject.narrow(objref,HLCVaigaiSessionRemoteHome.class);
                HLCVaigaiSessionRemote remote = home.create();
        return remote;
       }
       
        
        /*
         *  Convert the vector into array list
         */
        private ArrayList getList(Vector mediaLists){
           ArrayList dropDwonLists = new ArrayList();
           AdminOrgVO adminOrgVO = null;
                  for (Iterator it = mediaLists.iterator(); it.hasNext();) {
                      String[] icpDetails = (String[]) it.next();
                      adminOrgVO = new AdminOrgVO();
                      adminOrgVO.setInstructorId(icpDetails[6]);
                      adminOrgVO.setLandOwnerName(icpDetails[11]);
                      adminOrgVO.setAssesmentLevel(icpDetails[1]);
                      adminOrgVO.setHostMemberId(icpDetails[7]);
                      if(icpDetails[2]!=null){
                          adminOrgVO.setAssesmentDate(icpDetails[2]);
                      }
                      else{
                          
                      }
                      if(icpDetails[24]!=null){
                          adminOrgVO.setAddDate(icpDetails[24]);
                      }
                      else{
                          
                      }
                    
                        adminOrgVO.setIcpMeetingId(icpDetails[0]);
                        
                       
                        dropDwonLists.add(adminOrgVO);  
                     
             }
                      
       return dropDwonLists; 
     }   
        
        
        
        private ArrayList getOrgList(Vector mediaLists){
           ArrayList dropDwonLists = new ArrayList();
           AdminOrgVO adminOrgVO = null;
                  for (Iterator it = mediaLists.iterator(); it.hasNext();) {
                      String[] icpDetails = (String[]) it.next();
                      adminOrgVO = new AdminOrgVO();
                      adminOrgVO.setInstructorId(icpDetails[6]);
                      adminOrgVO.setRequestStatus(icpDetails[25]);
                      adminOrgVO.setAssesmentDate(icpDetails[2]);
                      adminOrgVO.setAddDate(icpDetails[24]);
                      adminOrgVO.setIcpMeetingId(icpDetails[0]);
                      adminOrgVO.setHostMemberId(icpDetails[7]);
                      adminOrgVO.setAssesmentLevel(icpDetails[1]);
                      dropDwonLists.add(adminOrgVO);                  
                  }    
       return dropDwonLists; 
     }   

    private AdminOrgVO getAdminView(Vector resultObj) {    
                    AdminOrgVO adminOrgVO = new AdminOrgVO();
             for (Iterator it = resultObj.iterator(); it.hasNext();) {
                     String[] icpDetails = (String[]) it.next();   
                     adminOrgVO.setIcpMeetingId(icpDetails[0]);
                     adminOrgVO.setAssesmentLevel(icpDetails[1]);
                     adminOrgVO.setAssesmentDate(icpDetails[2]);
                     adminOrgVO.setNoOfDays(icpDetails[3]);                      
                     adminOrgVO.setUseaAreaId(icpDetails[4]);
                     adminOrgVO.setLocation(icpDetails[5]);                    
                     adminOrgVO.setInstructorId(icpDetails[6]);                     
                     adminOrgVO.setHostMemberId(icpDetails[7]);
                     adminOrgVO.setShippingTypeId(icpDetails[8]);
                     //adminOrgVO.setAreaName();
                     adminOrgVO.setAssessor(icpDetails[9]);
                     adminOrgVO.setFacilities(icpDetails[10]);
                     adminOrgVO.setLandOwnerName(icpDetails[11]);
                     adminOrgVO.setLandOwnerBusinessName(icpDetails[12]);
                     adminOrgVO.setLandOwnerAddress(icpDetails[13]);
                     adminOrgVO.setLandOwnerCity(icpDetails[14]);
                     adminOrgVO.setLandOwnerState(icpDetails[15]);
                     adminOrgVO.setLandOwnerCountry(icpDetails[16]);
                     adminOrgVO.setLandOwnerZip(icpDetails[17]);
                     adminOrgVO.setLandOwnerPhone(icpDetails[18]);
                     if(icpDetails[19]==null || icpDetails[19].trim().length()==0){
                         icpDetails[19] ="-N.A-";
                      adminOrgVO.setLandOwnerFax(icpDetails[19]);
                     }
                     else{
                     adminOrgVO.setLandOwnerFax(icpDetails[19]);
                     }
                     
                     adminOrgVO.setLandOwnerEmail(icpDetails[20]);                     
                     adminOrgVO.setApprovedBy(icpDetails[21]);
                     adminOrgVO.setApprovedDate(icpDetails[22]);
                     adminOrgVO.setPostingType(icpDetails[23]);
                     adminOrgVO.setAddDate(icpDetails[24]);
                     adminOrgVO.setRequestStatus(icpDetails[25]);   
                     adminOrgVO.setAreaName(icpDetails[26]); 
                     adminOrgVO.setAmount(icpDetails[49]);
                     adminOrgVO.setCcName(icpDetails[39]);
                     adminOrgVO.setCheckDate(icpDetails[46]);
                     adminOrgVO.setCheckName(icpDetails[48]);
                     adminOrgVO.setBankName(icpDetails[45]);
                     adminOrgVO.setCcType(icpDetails[40]);
                     adminOrgVO.setCcNumber(icpDetails[26]);
                     adminOrgVO.setPaymentStatus(icpDetails[51]);
                     adminOrgVO.setPaymentDate(icpDetails[50]);
                     adminOrgVO.setCcCvvid(icpDetails[44]);
                     adminOrgVO.setCheckNumber(icpDetails[47]);
                     
                     if(icpDetails[37]==null || icpDetails[37].trim().length()==0){
                         icpDetails[37] ="-N.A-";
                         adminOrgVO.setComments(icpDetails[37]);
                     }
                     else{
                     adminOrgVO.setComments(icpDetails[37]);
                     }
                     
                    MemberContactDetails  conatctDetails = new MemberContactDetails();
                    conatctDetails.setFirstName(icpDetails[27]);
                    //conatctDetails.setMiddleName(icpDetails[]); 
                    conatctDetails.setLastName(icpDetails[28]);  
 		    conatctDetails.setEmailId(icpDetails[29]);           
                    conatctDetails.setAddress1(icpDetails[30]);
                  //  conatctDetails.setAddress2(icpDetails[]);
                    conatctDetails.setCity(icpDetails[33]);
                    conatctDetails.setCountry(icpDetails[31]);
                    if(icpDetails[36]==null || icpDetails[36].trim().length()==0){
                         icpDetails[36] ="-N.A-";
                         conatctDetails.setFaxNo(icpDetails[36]);
                     }
                     else{
                     conatctDetails.setFaxNo(icpDetails[36]);
                     }
                    
                  //  conatctDetails.setMobileNo(icpDetails[]);
                    conatctDetails.setPhoneNo(icpDetails[35]);
                    conatctDetails.setState(icpDetails[32]);
                   // conatctDetails.setSuite(icpDetails[]);
                  //  conatctDetails.setUserId(icpDetails[]);
                    conatctDetails.setZip(icpDetails[34]);
                    
                    adminOrgVO.setMemberContactDetails(conatctDetails);
                     
             }
                    
                     
                 
         return adminOrgVO;
    }
        
      private HLCKaverySessionStatefulRemote getHorseEJB(HttpServletRequest request) throws Exception{  
            
            
                MessageResources mr=getResources(request);
                String namingfactory=mr.getMessage("ejbclient.namingfactory");
                String contextfactory=mr.getMessage("ejbclient.contextfactory");
                String urlprovider=mr.getMessage("ejbclient.urlprovider");
                String lookupip=mr.getMessage("ejbclient.ip");
                String jndiname=mr.getMessage("jndi.kaverysful");
                System.setProperty(namingfactory,contextfactory);
                System.setProperty(urlprovider,lookupip);                
                Context jndiContext = new InitialContext();
                Object objref = jndiContext.lookup(jndiname);  
                          HLCKaverySessionStatefulRemoteHome home =
                    (HLCKaverySessionStatefulRemoteHome) PortableRemoteObject.narrow(objref, HLCKaverySessionStatefulRemoteHome.class);
                     HLCKaverySessionStatefulRemote remote = home.create();
                
              return remote;  
          
        } 

         
    
  }
//=========================================THE END========================================================================================
       

