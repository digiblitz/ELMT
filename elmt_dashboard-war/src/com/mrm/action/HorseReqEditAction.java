/*
 * ActionAddRidOwnPay.java
 *
 * Created on February 6, 2007, 1:13 PM
 */

package com.mrm.action;


import com.hlccommon.util.*;
import com.hlcform.stateless.HLCkaverystatelessRemote;
import com.hlcform.stateless.HLCkaverystatelessRemoteHome;
import com.hlckavery.statful.HLCKaverySessionBeanStatfulRemote;
import com.hlckavery.statful.HLCKaverySessionBeanStatfulRemoteHome;
import java.util.regex.*;
import javax.servlet.http.HttpSession;
import com.hlcpayment.HLCPaymentSessionRemote;
import com.hlcpayment.HLCPaymentSessionRemoteHome;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import com.util.email.EmailContent;
import com.util.email.EmailEngine;
import java.util.Date;
import java.text.*;
import org.apache.struts.util.MessageResources;
import javax.servlet.http.*;


public class HorseReqEditAction extends Action {
    
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try {
            HttpSession session=request.getSession();
            MessageResources mr=getResources(request);
            
            String namingfactory=mr.getMessage("ejbclient.namingfactory");
            String contextfactory=mr.getMessage("ejbclient.contextfactory");
            String urlprovider=mr.getMessage("ejbclient.urlprovider");
            String lookupip=mr.getMessage("ejbclient.ip");
            String jndiname=mr.getMessage("jndi.kavery");
            String jname=mr.getMessage("jndi.usrreg");
            System.setProperty(namingfactory,contextfactory);
            System.setProperty(urlprovider,lookupip);
            Context jndiContext = new InitialContext();
            
            Object objPayss=jndiContext.lookup("ejb/HLCPaymentSessionBean");
            
            HLCPaymentSessionRemoteHome objPaySessHome = (HLCPaymentSessionRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(objPayss,HLCPaymentSessionRemoteHome.class);
            HLCPaymentSessionRemote objPaySessRemote = objPaySessHome.create();
            Object oef = jndiContext.lookup(jname);
            
            HLCkaverystatelessRemoteHome userhome = (HLCkaverystatelessRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(oef,HLCkaverystatelessRemoteHome.class);
            HLCkaverystatelessRemote userremote = userhome.create();
            
            Object objref = jndiContext.lookup(jndiname);
            
            HLCKaverySessionBeanStatfulRemoteHome home = (HLCKaverySessionBeanStatfulRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(objref,HLCKaverySessionBeanStatfulRemoteHome.class);
            HLCKaverySessionBeanStatfulRemote remote = home.create();
            
            HLCPaymentDetailVO objPayment = new HLCPaymentDetailVO();
            HLCHorseRegistrationVO horseRegVO  = new HLCHorseRegistrationVO();
            HLCHorseDescriptionVO objHorseDesc = new HLCHorseDescriptionVO();
            HLCHorseUserVO objOwnerVO = new HLCHorseUserVO();
            HLCHorseUserVO objAddOwnerVO = new HLCHorseUserVO();
            HLCRequestHorseDetVO reqHrDetVO = new HLCRequestHorseDetVO();
            
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            
            
            String process = request.getParameter("process");
            Debug.print("process function:" + process);
            
            if(process.equals("horseReg")) {
                Debug.print("Inside the add function");
                String newBirthmonthId = "";
                String newBirthdayId = "";
                String newBirthyearId = "";
                String sexId = "";
                String newAddressId = "";
                String newCountryId = "";
                String newStateId = "";
                String newCityId = "";
                String newZipId = "";
                String newEmailId = "";
                String newPhoneId = "";
                String newFaxId = "";
                String regFor = "";
                String addOwnerUserId = "";
                String firstName = "";
                String lastName = "";
                String phone = "";
                String firstNameId1 = "";
                String lastNameId1 = "";
                String phoneId1 = "";
                String ownerUserId = "";
                String userNameId3 = "";
                String firstNameId3 = "";
                String lastNameId3 = "";
                String phoneId3 = "";
                String regAddFor="";
                String ado ="";
                
                String userNameOne ="";
                String comFor ="";
                String compAddFor = "";
                String horseMemberId = "";
                String riderId = "";
                String companyName="";
                
                String horseName ="";
                String horseRegisterName ="";
                String ownerName ="";
                String state="";
                String zip="";
                String phoneNo="";
                String emailIdNew="";
                String relationId ="";
                String competitionName="";
                String registereName ="";
                String ownerId ="";
                Date dob=null;
                Date dob1=null;
                boolean status = false;
                boolean ownStatus = false;
                String loginName ="";
                String userId =(String)session.getAttribute("userId");
                String reqOwnerId = request.getParameter("reqOwnerId");
                horseMemberId = request.getParameter("horseMemberId");
                ado =request.getParameter("ado");
                String finalOwnerId = null;
                String hName=request.getParameter("hName");
               
                String tempExistingRiderEmailId=request.getParameter("rEmailid");
                String tempExistingOwnerEmailId=request.getParameter("existiongOwnerEmail");
                String tempRequestedOwnerEmailId=request.getParameter("requestedOwnerMail");
                String tempRequestedRiderEmailId=request.getParameter("requestedRiderMail");
                String existingRiderEmailId="";
                String existingOwnerEmailId="";
                String requestedOwnerEmailId="";
                String requestedRiderEmailId="";
                
                if(tempExistingRiderEmailId!=null && tempExistingRiderEmailId.trim().length()!=0){
                existingRiderEmailId=tempExistingRiderEmailId;
                }else{
                existingRiderEmailId="";    
                }
                if(tempExistingOwnerEmailId!=null && tempExistingOwnerEmailId.trim().length()!=0){
                existingOwnerEmailId=tempExistingOwnerEmailId;
                }else{
                existingOwnerEmailId="";    
                }
                if(tempRequestedOwnerEmailId!=null && tempRequestedOwnerEmailId.trim().length()!=0){
                requestedOwnerEmailId=tempRequestedOwnerEmailId;
                }else{
                requestedOwnerEmailId="";    
                }
                if(tempRequestedRiderEmailId!=null && tempRequestedRiderEmailId.trim().length()!=0){
                requestedRiderEmailId=tempRequestedRiderEmailId;
                }else{
                requestedRiderEmailId="";    
                }
                Debug.print("existingRiderEmailId :"+existingRiderEmailId);
                Debug.print("existingOwnerEmailId :"+existingOwnerEmailId);
                Debug.print("requestedOwnerEmailId :"+requestedOwnerEmailId);
                Debug.print("requestedRiderEmailId :"+requestedRiderEmailId);
                String membershipName=request.getParameter("membertype");
                             
                //-------------------------------HORSE OWNER INFORMATION-------------------------------------------
                String finalPrimaryPh="";
                String finalFax ="";
                System.out.println("Checking1");
                relationId = request.getParameter("relationId");
                System.out.println("relationId"+relationId);
                Debug.print("request.getParameter(reqHrName):" + request.getParameter("reqHrName"));
                
                if(request.getParameter("reqHrName")!=null && request.getParameter("reqHrName").trim().length()!=0){
                    competitionName=request.getParameter("reqHrName");
                    System.out.println("Checking12");
                } else{
                    competitionName=null;
                }
                Debug.print("request.getParameter(reqHrRegName):" + request.getParameter("reqHrRegName"));
                if(request.getParameter("reqHrRegName")!=null && request.getParameter("reqHrRegName").trim().length()!=0){
                    registereName=request.getParameter("reqHrRegName");
                } else{
                    registereName=null;
                }
                
                Debug.print("horseMemberId:" + horseMemberId);
                riderId = request.getParameter("hlcNo");
                Debug.print("RIDER ID IS" + riderId);
                // Debug.print("riderId1:" + riderId1);
                Debug.print("competitionName:" + competitionName);
                Debug.print("registereName:" + registereName);
                regFor= request.getParameter("regAddFor");
                Debug.print("regFor:" + regFor);
                userId =(String)session.getAttribute("userId");
                horseRegVO.setRegisteredBy(userId);
                Debug.print(" Session userId:" + userId);
                Debug.print(" ado value:" + ado);
              /*
               * if rider id and owner id comes null it redirects to horseregadd.jsp
               */
                try {
                    System.out.println("Checking13");
                    if(ado!=null && ado.trim().length()!=0) {
                        ownStatus = true;
                        if(ado.equals("yes")){
                            Debug.print("inside the yes part:");
                            ownerUserId = request.getParameter("addOwnerUserId");
                            Debug.print("ownerUserId: " + ownerUserId);
                            finalOwnerId = ownerUserId;
                            if(regFor.equals("cmp")){
                                status = true;
                                compAddFor = request.getParameter("ecmp1");
                                if(compAddFor.equals("no")){
                                    loginName = request.getParameter("newAddFirstNameIdComp");
                                    newAddressId = request.getParameter("newAddAddressIdComp");
                                    newCountryId = request.getParameter("newAddCountryIdComp");
                                    newStateId = request.getParameter("newAddStateIdComp");
                                    newCityId = request.getParameter("newAddCityIdComp");
                                    newZipId = request.getParameter("newAddZipIdComp");
                                    newEmailId = request.getParameter("newAddEmailIdComp");
                                    newPhoneId = request.getParameter("newAddPhoneIdComp");
                                    newFaxId = request.getParameter("newAddFaxIdComp");
                                    firstName = request.getParameter("cpAddFirstNameIdComp");
                                    lastName = request.getParameter("cpAddLastNameIdComp");
                                    ownerUserId = null;
                                    if(newPhoneId!=null){
                                        Debug.print("request.getParameter(pphone_txt) value:"+newPhoneId);
                                        StringTokenizer strTkns = new StringTokenizer(newPhoneId,"[](),.-{}");
                                        
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
                                    if(newFaxId!=null){
                                        Debug.print("request.getParameter(pphone_txt) value:"+newFaxId);
                                        StringTokenizer strTkns = new StringTokenizer(newFaxId,"[](),.-{}");
                                        
                                        while(strTkns.hasMoreTokens()){
                                            try{
                                                String faxNo = (String)strTkns.nextToken();
                                                if(faxNo!=null && faxNo.trim().length()!=0){
                                                    Debug.print("ph no Added from Stokenizer:" + faxNo);
                                                    finalFax=finalFax+faxNo;
                                                }
                                            } catch(Exception e){
                                                Debug.print("ph no tokanizing exception:" + e);
                                            }
                                        }
                                        
                                        Debug.print("finally appended primary phNo :"+finalFax);
                                    }
                                }
                            }
                            objOwnerVO.setLoginName(loginName);
                            objOwnerVO.setFirstName(firstName);
                            objOwnerVO.setLastName(lastName);
                            objOwnerVO.setGender(sexId);
                            objOwnerVO.setDob(dob);
                            objOwnerVO.setAddress(newAddressId);
                            objOwnerVO.setCity(newCityId);
                            objOwnerVO.setCountry(newCountryId);
                            objOwnerVO.setState(newStateId);
                            objOwnerVO.setEmailId(newEmailId);
                            objOwnerVO.setPhoneNo(finalPrimaryPh);
                            objOwnerVO.setFaxNo(finalFax);
                            objOwnerVO.setZip(newZipId);
                        } else if(ado.equals("no")) {
                            Debug.print("inside the no part in no part:");
                            finalOwnerId = reqOwnerId;
                            Debug.print("finalOwnerId:" + finalOwnerId);
                            objOwnerVO = null;
                        }
                        
                    } else{
                        ownStatus = false;
                    }
                    Debug.print("*ownStatus*:" +ownStatus);
                } catch(Exception e){
                    System.out.println("Exception occurs in owner insertion part:" + e.getMessage());
                }
                
                boolean result = remote.updateRequestHorseDetailsByAdmin(horseMemberId,relationId,competitionName,registereName,riderId,finalOwnerId, objOwnerVO,status,ownStatus);
                Debug.print("Final Result:" + result);
                //for getting the previous owner, previous riders, riders and owners details
                        String ownerNames="";
                        String riderNames="";
                        String preOwnerNames="";
                        String preRiderNames="";
                        String FName,LName="";
                        ArrayList objPreOwnRidDet = new ArrayList();
                        objPreOwnRidDet = remote.getPreOwnerRiderDetails(horseMemberId);
                        if(objPreOwnRidDet!=null && objPreOwnRidDet.size()!=0){
                          Iterator itr = objPreOwnRidDet.iterator();
                          int i=0;
                          while(itr.hasNext()) {
                          HLCHorseRegListVO objOwnerInfo =(HLCHorseRegListVO)itr.next();                         
                           String relaId=objOwnerInfo.getRelationTypeId();
                           String relaTypeName=objOwnerInfo.getRelationTypeName();
                           if(relaTypeName!=null && (relaTypeName.equalsIgnoreCase("Previous Owner"))){
                                FName=objOwnerInfo.getPreOwnerFName();
                                LName=objOwnerInfo.getPreOwnerLName();
                                String tempPreOwnerNames=FName+" "+LName;
                                if(i==0){
                                preOwnerNames=tempPreOwnerNames;
                                }else{
                                preOwnerNames=preOwnerNames+","+tempPreOwnerNames;    
                                }
                           } 
                           if(relaTypeName!=null && (relaTypeName.equalsIgnoreCase("Previous Rider"))){    
                                FName=objOwnerInfo.getPreOwnerFName();
                                LName=objOwnerInfo.getPreOwnerLName();
                                String tempPreRiderNames=FName+" "+LName;
                                if(i==0){
                                preRiderNames=tempPreRiderNames;
                                }else{
                                preRiderNames=preRiderNames+","+tempPreRiderNames;    
                                }
                           }
                           if(relaTypeName!=null && (relaTypeName.equalsIgnoreCase("Owner"))){  
                                FName=objOwnerInfo.getPreOwnerFName();
                                LName=objOwnerInfo.getPreOwnerLName();
                                String tempOwnerNames=FName+" "+LName;
                                if(i==0){
                                ownerNames=tempOwnerNames;
                                }else{
                                ownerNames=ownerNames+","+tempOwnerNames;    
                                }
                          }
                           if(relaTypeName!=null && (relaTypeName.equalsIgnoreCase("Rider"))){  
                                FName=objOwnerInfo.getPreOwnerFName();
                                LName=objOwnerInfo.getPreOwnerLName();
                                String tempRiderNames=FName+" "+LName;
                                if(i==0){
                                riderNames=tempRiderNames;
                                }else{
                                riderNames=riderNames+","+tempRiderNames;    
                                }
                           }
                           i++; 
                          }  
                        }  
                
                if(result==true){
                      String textFull=" This horse can enter any level at which it is properly qualified";
                      String textLimit="This horse can enter events up to and including Training level";      
                      String membershipName1="";
                      if(membershipName!=null && membershipName.equalsIgnoreCase("Full")){
                      membershipName1 = membershipName+" "+textFull;      
                      }
                      else if(membershipName!=null && membershipName.equalsIgnoreCase("Limited")){
                      membershipName1 = membershipName+" "+textLimit;      
                      }
                     else{
                     membershipName1 =membershipName;  
                     }  
                // for sending mail to previous owner and rider     
                if((requestedRiderEmailId!=null && requestedRiderEmailId.length()!=0) || (requestedOwnerEmailId!=null && requestedOwnerEmailId.length()!=0)){                        
                            Debug.print("riderEmailId::: inside if condition :" + existingRiderEmailId);  
                            Debug.print("OwnerMailId::: inside if condition :" + existingOwnerEmailId);
                            EmailContent email=new EmailContent();
                            
                            if((requestedOwnerEmailId!=null && requestedOwnerEmailId.length()!=0) && (requestedRiderEmailId!=null && requestedRiderEmailId.length()!=0)){
                            String toMailIds[] = {existingOwnerEmailId,existingRiderEmailId};
                            email.setTo(toMailIds);
                            }else if(requestedOwnerEmailId!=null && requestedOwnerEmailId.length()!=0){
                            String toMailIds[] = {existingOwnerEmailId};
                            email.setTo(toMailIds);    
                            }else if(requestedRiderEmailId!=null && requestedRiderEmailId.length()!=0){
                            String toMailIds[] = {existingRiderEmailId};
                            email.setTo(toMailIds);     
                            }
                                                      
                        email.setFrom("anandv@digiblitz.com");
                        email.setSubject("Horse Change Rider/Owner");
                        String content = "<table width=\"526\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"border:1px solid #999;\"> " +
                                " <tr>" +
                                " <td height=\"463\" valign=\"top\" bgcolor=\"#FCFBF0\" style=\"padding-top:10px; padding-bottom:10px; padding-left:10px; padding-right:10px;\">" +
                                " <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> " +
                                "<tr>" +
                                "<td height=\"70\" valign=\"top\"><img src=\"images/emailHeader.jpg\" alt=\"HLC Online Services Dashboard Email Template\" width=\"515\" height=\"55\" /></td> " +
                                " </tr>"+
                                "  <tr>"+
                                "<td valign=\"top\">"+
                                "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">"+
                                "<tr>"+
                                "<td width=\"13\" height=\"12\" valign=\"top\"><img src=\"images/cornerTopLeft.jpg\" width=\"13\" height=\"12\" /></td> " +
                                "<td valign=\"top\" bgcolor=\"#FBF2F2\"></td>" +
                                "<td width=\"13\" height=\"12\" valign=\"top\"><img src=\"images/cornerTopRght.jpg\" width=\"13\" height=\"12\" /></td>" +
                                "</tr>"+
                                "<tr>"+
                                "<td valign=\"top\" background=\"images/left.jpg\">&nbsp;</td>"+
                                "<td valign=\"top\" bgcolor=\"#FBF2F2\">"+
                                "<span class=\"boldTxt\">Dear Member,</span><br />"+                                                      
                                "<p>The rider and/or owner information has been updated for <strong>"+hName+"</strong>.<p>"+
                                "<p>Please retain the following information for your records:<p>"+
                                "Horse Name: "+ hName +"<br />"+
                                "Horse Registration ID:"+ horseMemberId+"<br />"+
                                "Horse Registration level:"+membershipName1+".This is a lifetime registration.<br/>"+
                                "Current Owner(s):"+ ownerNames+"<br/>"+
                                "Current Rider(s):"+ riderNames+"<br/>"+
                                "Previous Owner(s):"+ preOwnerNames+"<br/>"+
                                "Previous Rider(s):"+ preRiderNames+"<br/>"+  
                                "<p>Additional information regarding <strong>"+ hName +"</strong> can be viewed by logging in to the HLC Online Services. Thank you.<p>"+                          
                                 "<p>Sincerely, <br />" +
                                "<p>HLC Staff <p>"+  
                                "</tr>"+                           
                                " </table>"+
                                "</td></tr>"+                      
                                "</table>";
                        email.setBody(content);
                        EmailEngine emailEngine = new EmailEngine();
                        boolean emailFlag = emailEngine.sendMimeEmail(email);
                        Debug.print("Email sent sucessfully :"+emailFlag);
                        
                    }    
                }
            }
            
        } catch (Exception ex) {
            System.out.println("Exception occurs in Adding owner and rider details:" + ex.getMessage());
        }
        return mapping.findForward("Success");
    }
}