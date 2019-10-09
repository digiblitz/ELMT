/*
 * ActionAddRidOwnPay.java
 *
 * Created on February 6, 2007, 1:13 PM
 */

package com.mrm.action;

import com.hlcaccounts.session.HLCMahanadhiSessionRemote;
import com.hlcaccounts.session.HLCMahanadhiSessionRemoteHome;
import com.hlcaccounts.util.HLCAccTransactionVO;
import com.hlcaccounts.util.HLCAccTxnTypeDetailVO;
import com.hlcform.util.HLCMemberHistoryDetail;
import com.hlccommon.util.*;
import com.hlckavery.statful.HLCKaverySessionBeanStatfulRemote;
import com.hlckavery.statful.HLCKaverySessionBeanStatfulRemoteHome;
import com.mrm.actionform.FormAddRidOwnPay;
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
import com.hlcform.stateless.*;
import javax.servlet.http.*;


public class HorseRegEditAction extends Action {
    
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
            
///Transaction Entry
            String mahanadhiJndi=mr.getMessage("jndi.acc");
            Debug.print("mahanadhiJndi  :" +mahanadhiJndi);
            Object maha=jndiContext.lookup(mahanadhiJndi);
            HLCMahanadhiSessionRemoteHome mahaHome = (HLCMahanadhiSessionRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(maha,HLCMahanadhiSessionRemoteHome.class);
            HLCMahanadhiSessionRemote mahaRemote = mahaHome.create();
            
            
            HLCPaymentDetailVO objPayment = new HLCPaymentDetailVO();
            HLCHorseRegistrationVO horseRegVO  = new HLCHorseRegistrationVO();
            HLCHorseDescriptionVO objHorseDesc = new HLCHorseDescriptionVO();
            HLCHorseUserVO objOwnerVO = new HLCHorseUserVO();
            HLCHorseUserVO objTrainerVO = new HLCHorseUserVO();
            HLCHorseUserVO objAddOwnerVO = new HLCHorseUserVO();
            HLCRequestHorseDetVO reqHrDetVO = new HLCRequestHorseDetVO();
            FormAddRidOwnPay fbean=(FormAddRidOwnPay)form;
            System.out.println("FormAddRidOwnPay ="+ fbean);
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
                String regstrBy="";
                String breename="";
                String baPastName="";
                String riderMemberId="";
                String prevRiderMemberId="";
                String addRiderMemberId="";
                String registeredName="";
                String colorselect = "";
                String genderselect = "";
                String height = "";
                String foaled = "";
                String breed = "";
                String breedTwo = "";
                String countryOrigin = "";
                String sireName = "";
                String sireBreed = "";
                String sireBreedTwo = "";
                String damName = "";
                String damBreed = "";
                String damBreedTwo = "";
                String importedFrm = "";
                String importedDate = "";
                String finalPrimaryPh="";
                String finalFax="";
                Date dob=null;
                Date dob1=null;
                boolean status = false;
                boolean ownStatus = false;
                String loginName ="";
                String serviceType = "";
                String feedisp=request.getParameter("feeDisp");
                Pattern pat=Pattern.compile("#");
                String str[]=pat.split(feedisp);
                String id=str[0];
                String mname=str[1];
                String amt=str[2];
                
                //-------------------------------HORSE OWNER INFORMATION-------------------------------------------
                String userId =(String)session.getAttribute("userId");
                ado =request.getParameter("ado");
                horseRegVO.setRegisteredBy(userId);
                String phoneCharge=request.getParameter("phoneCharge");
                String serviceStatus = request.getParameter("servicePrice");
                
                String servicePrice ="";
                if(phoneCharge!=null && phoneCharge.trim().length()!=0){
                    if(serviceStatus!=null){
                        serviceType = serviceStatus;
                    } else{
                        serviceType = null;
                    }
                }
                
                if(request.getParameter("horseMemberId")!=null && request.getParameter("horseMemberId").trim().length()!=0){
                    horseMemberId = request.getParameter("horseMemberId");
                } else{
                    horseMemberId = null;
                }
                if(request.getParameter("competitionName")!=null && request.getParameter("competitionName").trim().length()!=0){
                    competitionName = request.getParameter("competitionName");
                } else{
                    competitionName = null;
                }
                if(request.getParameter("regstrBy")!=null && request.getParameter("regstrBy").trim().length()!=0){
                    regstrBy = request.getParameter("regstrBy");
                } else{
                    regstrBy = null;
                }
                if(request.getParameter("breename")!=null && request.getParameter("breename").trim().length()!=0){
                    breename = request.getParameter("breename");
                } else{
                    breename = null;
                }
                if(request.getParameter("baPastName")!=null && request.getParameter("baPastName").trim().length()!=0){
                    baPastName = request.getParameter("baPastName");
                } else{
                    baPastName = null;
                }
                if(request.getParameter("registeredName")!=null && request.getParameter("registeredName").trim().length()!=0){
                    registereName=request.getParameter("registeredName");
                } else{
                    registereName = null;
                }
                
                if(request.getParameter("hlcNo")!=null && request.getParameter("hlcNo").trim().length()!=0){
                    riderMemberId = request.getParameter("hlcNo");
                } else{
                    riderMemberId = null;
                }
                
                prevRiderMemberId = null;
                addRiderMemberId = null;
                colorselect = request.getParameter("colorselect");
                genderselect = request.getParameter("genderselect");
                height = request.getParameter("height");
                foaled = request.getParameter("foaled");
                breed = request.getParameter("breed");
                breedTwo = request.getParameter("breedTwo");
                countryOrigin = request.getParameter("countryOrigin");
                sireName = request.getParameter("sireName");
                sireBreed = request.getParameter("sireBreed");
                sireBreedTwo = request.getParameter("sireBreedTwo");
                damName = request.getParameter("damName");
                damBreed = request.getParameter("damBreed");
                damBreedTwo = request.getParameter("damBreedTwo");
                importedFrm = request.getParameter("importedFrm");
                importedDate = request.getParameter("importedDate");
                
                if(colorselect!=null && colorselect.trim().length()!=0){
                    objHorseDesc.setColor(colorselect);
                } else{
                    objHorseDesc.setColor(null);
                }
                if(genderselect!=null && genderselect.trim().length()!=0){
                    objHorseDesc.setGender(genderselect);
                } else{
                    objHorseDesc.setGender(null);
                }
                if(height!=null && height.trim().length()!=0){
                    objHorseDesc.setHeight(height);
                } else{
                    objHorseDesc.setHeight(null);
                }
                if(foaled!=null && foaled.trim().length()!=0){
                    objHorseDesc.setYearFoaled(foaled);
                } else{
                    objHorseDesc.setYearFoaled(null);
                }
                if(breed!=null && breed.trim().length()!=0){
                    objHorseDesc.setBreed(breed);
                } else{
                    objHorseDesc.setBreed(null);
                }
                if(breedTwo!=null && breedTwo.trim().length()!=0){
                    objHorseDesc.setBreed2(breedTwo);
                } else{
                    objHorseDesc.setBreed2(null);
                }
                if(countryOrigin!=null && countryOrigin.trim().length()!=0){
                    objHorseDesc.setCountry(countryOrigin);
                } else{
                    objHorseDesc.setCountry(null);
                }
                if(sireName!=null && sireName.trim().length()!=0){
                    objHorseDesc.setSire(sireName);
                } else{
                    objHorseDesc.setSire(null);
                }
                if(sireBreed!=null && sireBreed.trim().length()!=0){
                    objHorseDesc.setSireBreed(sireBreed);
                } else{
                    objHorseDesc.setSireBreed(null);
                }
                if(sireBreedTwo!=null && sireBreedTwo.trim().length()!=0){
                    objHorseDesc.setSireBreed2(sireBreedTwo);
                } else{
                    objHorseDesc.setSireBreed2(null);
                }
                if(damName!=null && damName.trim().length()!=0){
                    objHorseDesc.setDam(damName);
                } else{
                    objHorseDesc.setDam(null);
                }
                if(damBreed!=null && damBreed.trim().length()!=0){
                    objHorseDesc.setDamBreed(damBreed);
                } else{
                    objHorseDesc.setDamBreed(null);
                }
                if(damBreedTwo!=null && damBreedTwo.trim().length()!=0){
                    objHorseDesc.setDamBreed2(damBreedTwo);
                } else{
                    objHorseDesc.setDamBreed2(null);
                }
                if(importedFrm!=null && importedFrm.trim().length()!=0){
                    objHorseDesc.setImportedFrom(importedFrm);
                } else{
                    objHorseDesc.setImportedFrom(null);
                }
                if(importedDate!=null && importedDate.trim().length()!=0){
                    objHorseDesc.setImportDate(sdf.parse(importedDate));
                } else{
                    objHorseDesc.setImportDate(null);
                }
                
              /*
               * if rider id and owner id comes null it redirects to horseregadd.jsp
               */
                try {
                    if(ado!=null && ado.trim().length()!=0) {
                        if(ado.equals("yes")){
                            ownStatus = true;
                            regFor= request.getParameter("regAddFor");
                            ownerUserId = request.getParameter("addOwnerUserId");
                            
                            if(regFor.equals("mem")){
                                userNameOne = request.getParameter("userNameId2");
                                ownerUserId = (String)userremote.getUserIdBasedOnMemberId(userNameOne);
                                Debug.print("ownerUserId from another method: "+ ownerUserId);
                            } else if(regFor.equals("rid")){
                                firstName = request.getParameter("ownerFNameTwo1");
                                lastName = request.getParameter("ownerLNameTwo1");
                            } else if(regFor.equals("acc")){
                                firstName = request.getParameter("firstNameId3");
                                lastName = request.getParameter("lastNameId3");
                            } else if(regFor.equals("cmp")){
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
                                    }
                                    
                                    if(newFaxId!=null){
                                        StringTokenizer strTkns = new StringTokenizer(newFaxId,"[](),.-{}");
                                        while(strTkns.hasMoreTokens()){
                                            try{
                                                String faxNo = (String)strTkns.nextToken();
                                                if(faxNo!=null && faxNo.trim().length()!=0){
                                                    finalFax=finalFax+faxNo;
                                                }
                                            } catch(Exception e){
                                                Debug.print("ph no tokanizing exception:" + e);
                                            }
                                        }
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
                            objOwnerVO.setUserId(ownerUserId);
                            
                            Debug.print("::::::::::::::::::::::::Inside Owner Information:::::::::::::::");
                            Debug.print(objOwnerVO.getLoginName());
                            Debug.print(objOwnerVO.getFirstName());
                            Debug.print(objOwnerVO.getLastName());
                            Debug.print(objOwnerVO.getGender());
                            Debug.print(""+objOwnerVO.getDob());
                            Debug.print(objOwnerVO.getAddress());
                            Debug.print(objOwnerVO.getCity());
                            Debug.print(objOwnerVO.getCountry());
                            Debug.print(objOwnerVO.getState());
                            Debug.print(objOwnerVO.getEmailId());
                            Debug.print(objOwnerVO.getPhoneNo());
                            Debug.print(objOwnerVO.getFaxNo());
                            Debug.print(objOwnerVO.getZip());
                            Debug.print("::::::::::::::::::::::::Inside Owner Information:::::::::::::::");
                            
                            boolean relationStatus1 = false;
                            boolean res1 = remote.editHorseOwner(horseMemberId,ownerUserId,objOwnerVO,ownStatus,relationStatus1);
                        } else if(ado.equals("no")) {
                            Debug.print("inside the no part in no part:");
                        }
                    } else{
                        ownStatus = false;
                    }
                    
                    
                    String regTrainer = request.getParameter("regFor");
                    boolean status1 = false;
                    Date Tdob = null;
                    regFor= request.getParameter("regFor");
                    
                    String horseMemberId1 = request.getParameter("horseMemberId");
                    String trainerUserId = "";
                    if(regTrainer.equals("other")){
                        boolean trainerResult = remote.deleteTrainerInfo(horseMemberId);
                        Debug.print("&&&&&&&&&&&&&&&& Trainer Delete Result: "+trainerResult);
                        loginName = "";
                        firstName = "";
                        lastName = "";
                        sexId = "";
                        dob = null;
                        newAddressId = "";
                        newCityId = "";
                        newCountryId = "";
                        newStateId = "";
                        newEmailId = "";
                        finalPrimaryPh = "";
                        finalFax = "";
                        newZipId = "";
                        trainerUserId = "";
                    }else{
                        if(ado.equals("yes") && regTrainer.equals("own1")){
                            Debug.print("Inside own1 yes");
                            loginName = objOwnerVO.getLoginName();
                            firstName = objOwnerVO.getFirstName();
                            lastName = objOwnerVO.getLastName();
                            sexId = objOwnerVO.getGender();
                            dob = objOwnerVO.getDob();
                            newAddressId = objOwnerVO.getAddress();
                            newCityId = objOwnerVO.getCity();
                            newCountryId = objOwnerVO.getCountry();
                            newStateId = objOwnerVO.getState();
                            newEmailId = objOwnerVO.getEmailId();
                            finalPrimaryPh = objOwnerVO.getPhoneNo();
                            finalFax = objOwnerVO.getFaxNo();
                            newZipId = objOwnerVO.getZip();
                            trainerUserId = ownerUserId;
                            
                            Debug.print("***********************Trainer User Id in Section Yes: "+trainerUserId);
                        }
                        if(ado.equals("no") && regTrainer.equals("own1")){
                            Debug.print("Inside own1 no ");
                            
                            firstName = request.getParameter("ownFirstName");
                            lastName = request.getParameter("ownLastName");
                            newStateId = request.getParameter("state");
                            newEmailId = request.getParameter("email_id");
                            finalPrimaryPh = request.getParameter("phone_no");
                            newZipId = request.getParameter("zip");
                            trainerUserId = request.getParameter("ownUserId");
                            
                            Debug.print("************************Trainer User Id In NO Section: "+ trainerUserId);
                        }
                        
                        if(regTrainer.equals("mem1")){
                            firstName = request.getParameter("ownerFNameTwo");
                            lastName = request.getParameter("ownerLNameTwo");
                        }
                        if(ado.equals("yes") && regTrainer.equals("rid1")){
                            firstName = request.getParameter("firstName");
                            lastName = request.getParameter("lastName");
                            Debug.print("Member trainerUserId:" + trainerUserId);
                        }
                        if(regTrainer.equals("acc1")){
                            firstName = request.getParameter("firstNameId1");
                            lastName = request.getParameter("lastNameId1");
                            Debug.print("Login trainerUserId:" + trainerUserId);
                        }
                        
                        if(regTrainer.equals("cmp1")){
                            status = true;
                            Debug.print("Inside the company:" + status);
                            compAddFor = request.getParameter("ecmp");
                            Debug.print("compAddFor:" + compAddFor);
                            
                            if(compAddFor.equals("yes")){
                                loginName = request.getParameter("existCompNameId1");
                                firstName = request.getParameter("cpfirstNameId1");
                                lastName = request.getParameter("cplastNameId1");
                                Debug.print("loginName: " + loginName);
                                Debug.print("firstName: " + firstName);
                                Debug.print("firstName: " + firstName);
                            } else if(compAddFor.equals("no")){
                                loginName = request.getParameter("newFirstNameIdComp");
                                newAddressId = request.getParameter("newAddressIdComp");
                                newCountryId = request.getParameter("newTCountryIdComp");
                                newStateId = request.getParameter("newTStateIdComp");
                                newCityId = request.getParameter("newCityIdComp");
                                newZipId = request.getParameter("newZipIdComp");
                                newEmailId = request.getParameter("newEmailIdComp");
                                newPhoneId = request.getParameter("newPhoneIdComp");
                                newFaxId = request.getParameter("newFaxIdComp");
                                firstName = request.getParameter("cpFirstNameIdComp");
                                lastName = request.getParameter("cpLastNameIdComp");
                                trainerUserId = null;
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
                                }
                                
                                if(newFaxId!=null){
                                    StringTokenizer strTkns = new StringTokenizer(newFaxId,"[](),.-{}");
                                    while(strTkns.hasMoreTokens()){
                                        try{
                                            String faxNo = (String)strTkns.nextToken();
                                            if(faxNo!=null && faxNo.trim().length()!=0){
                                                finalFax=finalFax+faxNo;
                                            }
                                        } catch(Exception e){
                                            Debug.print("ph no tokanizing exception:" + e);
                                        }
                                    }
                                }
                            }
                        }
                        
                        
                        objTrainerVO.setFirstName(firstName);
                        objTrainerVO.setLastName(lastName);
                        objTrainerVO.setGender(sexId);
                        objTrainerVO.setDob(dob);
                        objTrainerVO.setAddress(newAddressId);
                        objTrainerVO.setCity(newCityId);
                        objTrainerVO.setCountry(newCountryId);
                        objTrainerVO.setState(newStateId);
                        objTrainerVO.setEmailId(newEmailId);
                        objTrainerVO.setPhoneNo(finalPrimaryPh);
                        objTrainerVO.setFaxNo(finalFax);
                        objTrainerVO.setZip(newZipId);
                        String relationStatus = "Pending";
                        Debug.print("befor calling method>>>>>>>>>>>>>>>>>>>>>>>"+trainerUserId);
                        boolean res = remote.insertTrainerInfo(horseMemberId, trainerUserId, objTrainerVO,status,relationStatus);
                    }
                    
                } catch(Exception e){
                    e.printStackTrace();
                }
                
                String sesUserId = (String)session.getAttribute("userId");
                String emailId = (String)session.getAttribute("emailId");
                // Get client's IP address
                String addr = request.getRemoteAddr(); // 123.123.123.123
                int  CcExpMonth = 0;
                int CcExpYear = 0;
                int CcCvvid =0;
                double totalAmount =0;
                String CcNumber ="0";
                String checkNumber="0";
                Date check_date = null;
                Date comp_date = null;
                String ccType=null;
                String ccName=null;
                String nameoncheck = null;
                String bankname = null;
                float checkAmount = 0;
                String  r11 = fbean.getR11();
                
                if(r11.equals("card")){
                    if(fbean.getCcNumber()==null || fbean.getCcNumber().equals("")){
                        CcNumber = "0";
                    } else{
                        CcNumber = fbean.getCcNumber();
                    }
                    if(fbean.getCcName()==null || fbean.getCcName().equals("")){
                        ccName ="";
                    } else {
                        ccName=fbean.getCcName();
                    }
                    if(!(fbean.getCcType().equals("")) && fbean.getCcType()!=null){
                        ccType = fbean.getCcType();
                    }
                    if(fbean.getCcExpMonth()==null || fbean.getCcExpMonth().equals("")){
                        CcExpMonth =0;
                    } else {
                        CcExpMonth=Integer.parseInt(fbean.getCcExpMonth());
                    }
                    if(fbean.getCcExpYear()==null || fbean.getCcExpYear().equals("")){
                        CcExpYear =0;
                    } else{
                        CcExpYear = Integer.parseInt(fbean.getCcExpYear());
                    }
                    if(fbean.getCcCvvid()==null || fbean.getCcCvvid().equals("")){
                        CcCvvid =0;
                    } else{
                        CcCvvid = Integer.parseInt(fbean.getCcCvvid());
                    }
                    check_date= null;
                    objPayment.setPaymentStatus("Card");
                }
                
                if(r11.equals("check")){
                    if(fbean.getCheckNumber()==null || fbean.getCheckNumber().equals("")){
                        checkNumber="0";
                    } else{
                        checkNumber= fbean.getCheckNumber();
                    }
                    if(fbean.getCheckDate().equals("")){
                        check_date= null;
                    } else{
                        check_date =(Date)sdf.parse(fbean.getCheckDate());
                    }
                    objPayment.setPaymentStatus("Check");
                }
                
                String tempAmount = request.getParameter("totalAmount");
                String tempCheckAmount = request.getParameter("chkBalAmt");
                if(tempCheckAmount==null || tempCheckAmount==""){
                    checkAmount=0;
                } else{
                    checkAmount = Float.parseFloat(tempCheckAmount);
                }
                if(tempAmount==null || tempAmount==""){
                    totalAmount=0;
                } else{
                    totalAmount = Double.parseDouble(tempAmount);
                }
                bankname =fbean.getBankName();
                nameoncheck =fbean.getNameCheck();
                objPayment.setUserId(sesUserId);
                objPayment.setCcName(ccName);
                objPayment.setCcType(ccType);
                objPayment.setCcNumber(CcNumber);
                objPayment.setCcExpMonth(CcExpMonth);
                objPayment.setCcExpYear(CcExpYear);
                objPayment.setCcCvvid(CcCvvid);
                objPayment.setBankName(bankname);
                objPayment.setCheckName(nameoncheck);
                objPayment.setCheckDate(check_date);
                objPayment.setCheckNumber(checkNumber);
                objPayment.setAmount(totalAmount);
                objPayment.setPaymentDate(new Date());
                objPayment.setIpAddress(addr);
                String paymentId = remote.getNextId();
                objPayment.setPaymentId(paymentId);
                String fee_txt  =  request.getParameter("fee_txt");
                if(r11.equals("card")){
                    String cvNo = request.getParameter("ccCvvid");
                    session.setAttribute("objPaymentList",objPayment);
                    request.setAttribute("email",emailId);
                    request.setAttribute("cardNo",String.valueOf(CcNumber));
                    String expMon = String.valueOf(CcExpMonth);
                    String expYear = String.valueOf(CcExpYear);
                    if(expMon.trim().length()==1) {
                        expMon = "0" + expMon;
                    }
                    boolean updBal = false;
                    expYear = expYear.substring(2);
                    String expDate = expMon + expYear;
                    request.setAttribute("expDate",expDate);
                    request.setAttribute("amount",String.valueOf(totalAmount));
                    request.setAttribute("chkDigit",String.valueOf(CcCvvid));
                    boolean upres = remote.upgradeNonRegisteredHorse(horseMemberId,id,paymentId,fee_txt,String.valueOf(totalAmount));
                    boolean result = remote.updateHorseUpgradeForNotRegisterHorse(horseMemberId,competitionName,registereName,breename,baPastName,riderMemberId,prevRiderMemberId,addRiderMemberId,objHorseDesc,serviceType);
                    String activationDate = request.getParameter("activeDatVal");
                    if(activationDate!=null && activationDate.trim().length()!=0){
                        session.setAttribute("activationDate",activationDate);
                    } else{
                        session.setAttribute("activationDate",null);
                    }
                    
///Start Transaction Entries
///////  Phone Service Charge
                    
                    HLCAccTransactionVO PhaccTxnVO = null;
                    HLCAccTransactionVO accTxnVO = new HLCAccTransactionVO();
                    int si=0;
                    String logBy="user";
                    
                    if(session.getAttribute("loggedBy")!=null) {
                        String loggedBy="";
                        loggedBy=(String)session.getAttribute("loggedBy");
                        logBy=loggedBy;
                        
                        if(loggedBy.equalsIgnoreCase("Admin")){
                            PhaccTxnVO = new HLCAccTransactionVO();
                            String phoneCharge1 = request.getParameter("phoneCharge");
                            String phoneId = request.getParameter("servicePrice");
                            if(phoneCharge1!=null){
                                if(phoneId!=null || phoneId.trim().length()!=0){
                                    HLCAccTxnTypeDetailVO accTxnDet = mahaRemote.getPhoneServChgAccTxnTypeDetails(phoneId);
                                    String accNo = accTxnDet.getAccount_no();
                                    String accClassname = accTxnDet.getClass_name();
                                    String accItemNo = accTxnDet.getItem_no();
                                    String accAccNo = accTxnDet.getSub_account_no();
                                    String accTranName = accTxnDet.getTransaction_name();
                                    String accTyped = accTxnDet.getTransaction_type();
                                    String accTypeId = accTxnDet.getTransaction_type_id();
                                    
                                    if(r11.equalsIgnoreCase("check")){
                                        PhaccTxnVO.setPayment_mode("check");
                                        PhaccTxnVO.setActive_status(false);
                                    }
                                    if(r11.equalsIgnoreCase("card")){
                                        String cardselect = request.getParameter("ccType");
                                        PhaccTxnVO.setPayment_mode(cardselect);
                                        PhaccTxnVO.setActive_status(false);
                                    }
                                    if(phoneCharge1!=null){
                                        if(phoneCharge1.trim().length()!=0){
                                            PhaccTxnVO.setAmount(Float.parseFloat(phoneCharge1));
                                        }
                                    } else{
                                        PhaccTxnVO.setAmount(0);
                                    }
                                    
                                    PhaccTxnVO.setSub_account_no(accAccNo);
                                    PhaccTxnVO.setPayment_id(paymentId);
                                    PhaccTxnVO.setAccount_type("Income");
                                    PhaccTxnVO.setClass_Typ(accClassname);
                                    PhaccTxnVO.setAccount_no(accNo);
                                    PhaccTxnVO.setItem_no(accItemNo);
                                    PhaccTxnVO.setDescription(accTranName);
                                }
                            }
                        }
                    }
                    
                    if(r11.equals("check")){
                        if(PhaccTxnVO!=null){
                            boolean phone_status = mahaRemote.insertAccountTxnDetails(PhaccTxnVO);
                        }
                    } else{
                        session.setAttribute("PhoneVO",PhaccTxnVO);
                    }
                    
//// Horse Member type Entry
                    String membtype = request.getParameter("feeDisp");
                    if(membtype!=null || membtype.trim().length()!=0){
                        String  sarray[] = membtype.split("#");
                        String memberTypeId = sarray[0];
                        String membershipName = sarray[1];
                        String membershipAmount = sarray[2];
                        String cnct=memberTypeId+"#"+membershipName+"#"+membershipAmount;
                        HLCAccTxnTypeDetailVO accTxnDet = mahaRemote.getMemberAccTxnTypeDetails(memberTypeId);
                        HLCAccTransactionVO horseMem = new HLCAccTransactionVO();
                        
                        String accNo =  accTxnDet.getAccount_no();
                        String className = accTxnDet.getClass_name();
                        String itemNo = accTxnDet.getItem_no();
                        String subAccNo = accTxnDet.getSub_account_no();
                        String transName = accTxnDet.getTransaction_name();
                        String transType = accTxnDet.getTransaction_type();
                        
                        if(r11.equalsIgnoreCase("check")){
                            horseMem.setPayment_mode("check");
                            horseMem.setActive_status(false);
                        }
                        if(r11.equalsIgnoreCase("card")){
                            String cardselect = request.getParameter("ccType");
                            horseMem.setPayment_mode(cardselect);
                            horseMem.setActive_status(false);
                        }
                        if(membershipAmount==null || membershipAmount.trim().length()!=0){
                            horseMem.setAmount(Float.parseFloat(membershipAmount));
                        } else{
                            horseMem.setAmount(0);
                        }
                        horseMem.setClass_Typ(className);
                        horseMem.setAccount_no(accNo);
                        horseMem.setAccount_type("Income");
                        horseMem.setItem_no(itemNo);
                        horseMem.setSub_account_no(subAccNo);
                        horseMem.setPayment_id(paymentId);
                        horseMem.setDescription(transName);
                        if(r11.equals("check")){
                            if(PhaccTxnVO!=null){
                                boolean insert_status = mahaRemote.insertAccountTxnDetails(horseMem);
                            }
                        } else{
                            session.setAttribute("horseMem",horseMem);
                        }
                        
                        // History
                        HLCMemberHistoryDetail detailVO = new HLCMemberHistoryDetail();
                        java.util.Calendar toDay = java.util.Calendar.getInstance();
                        int newyear = toDay.get(Calendar.YEAR);
                        int new_month = toDay.get(Calendar.MONTH);
                        
                        if(memberTypeId!=null || memberTypeId.trim().length()!=0){
                            String statid ="";
                            statid=userremote.getStatusIBasedOnStatus("Active");
                            detailVO.setTo_year(newyear);
                            detailVO.setPayment_id(paymentId);
                            detailVO.setMemberId(horseMemberId);
                            detailVO.setMembership_action("Register");
                            detailVO.setMembership_type_id(memberTypeId);
                            detailVO.setMembership_type_name(membershipName);
                            detailVO.setStatus_id(statid);
                            detailVO.setUser_id(sesUserId);
                            
                            boolean historyUpdate = userremote.insertHorseMemberHistoryDetails(detailVO);
                        }
                    }
                    
//End Transaction Entries
                    session.setAttribute("amount",String.valueOf(totalAmount));
                    session.setAttribute("paymentId",paymentId);
                    session.setAttribute("horseMemberId",horseMemberId);
                    request.setAttribute("registrationLevel",mname);
                    request.setAttribute("horseName",competitionName);
                    session.setAttribute("horseName",competitionName);
                    StringBuffer reqURL = request.getRequestURL();
                    int lastIndex = reqURL.lastIndexOf("/") ;
                    String url ="";
                    if(lastIndex!=-1){
                        url = reqURL.substring(0,lastIndex+1);
                    }
                    String tmpNova  = mr.getMessage("notreghorse.nova");
                    String nova = url + tmpNova;
                    request.setAttribute("nova",nova);
                    return mapping.findForward("novaPage");
                    
                    //return mapping.findForward("frmHorseSuccessRedir");
                } else{
                    boolean updBal = false;
                    boolean result1  = false;
                    boolean upres = false;
                    boolean result = false;
                    
                    try{
                        result1  = objPaySessRemote.createPayment(objPayment);
                        upres = remote.upgradeNonRegisteredHorse(horseMemberId,id,paymentId,fee_txt,String.valueOf(totalAmount));
                        result = remote.updateHorseUpgradeForNotRegisterHorse(horseMemberId,competitionName,registereName,breename,baPastName,riderMemberId,prevRiderMemberId,addRiderMemberId,objHorseDesc,serviceType);
                    } catch(Exception e){
                        e.printStackTrace();
                    }
                    boolean statusChange = false;
                    boolean activeStatus = false;
                    boolean activateDateStatus = false;
                    
///Start Transaction Entries
///////  Phone Service Charge
                    
                    HLCAccTransactionVO PhaccTxnVO = null;
                    HLCAccTransactionVO accTxnVO = new HLCAccTransactionVO();
                    int si=0;
                    String logBy="user";
                    
                    if(session.getAttribute("loggedBy")!=null) {
                        String loggedBy="";
                        loggedBy=(String)session.getAttribute("loggedBy");
                        logBy=loggedBy;
                        
                        if(loggedBy.equalsIgnoreCase("Admin")){
                            PhaccTxnVO = new HLCAccTransactionVO();
                            String phoneCharge1 = request.getParameter("phoneCharge");
                            String phoneId = request.getParameter("servicePrice");
                            if(phoneCharge1!=null){
                                if(phoneId!=null || phoneId.trim().length()!=0){
                                    HLCAccTxnTypeDetailVO accTxnDet = mahaRemote.getPhoneServChgAccTxnTypeDetails(phoneId);
                                    String accNo = accTxnDet.getAccount_no();
                                    String accClassname = accTxnDet.getClass_name();
                                    String accItemNo = accTxnDet.getItem_no();
                                    String accAccNo = accTxnDet.getSub_account_no();
                                    String accTranName = accTxnDet.getTransaction_name();
                                    String accTyped = accTxnDet.getTransaction_type();
                                    String accTypeId = accTxnDet.getTransaction_type_id();
                                    
                                    if(r11.equalsIgnoreCase("check")){
                                        PhaccTxnVO.setPayment_mode("check");
                                        PhaccTxnVO.setActive_status(false);
                                    }
                                    if(r11.equalsIgnoreCase("card")){
                                        String cardselect = request.getParameter("ccType");
                                        PhaccTxnVO.setPayment_mode(cardselect);
                                        PhaccTxnVO.setActive_status(false);
                                    }
                                    
                                    if(phoneCharge1!=null || phoneCharge1.trim().length()!=0){
                                        PhaccTxnVO.setAmount(Float.parseFloat(phoneCharge1));
                                    }
                                    if(r11.equalsIgnoreCase("card")){
                                        PhaccTxnVO.setAmount(0);
                                    }
                                    
                                    PhaccTxnVO.setPayment_id(paymentId);
                                    PhaccTxnVO.setAccount_type("Income");
                                    PhaccTxnVO.setSub_account_no(accAccNo);
                                    PhaccTxnVO.setClass_Typ(accClassname);
                                    PhaccTxnVO.setAccount_no(accNo);
                                    PhaccTxnVO.setItem_no(accItemNo);
                                    PhaccTxnVO.setDescription(accTranName);
                                }
                            }
                        }
                        
                        // Making the active Status and reconcile status as true on full payment
                        String chckAmount = request.getParameter("chkBalAmt");
                        String totAmount = request.getParameter("totalAmount");
                        if(chckAmount!=null && totAmount!=null){
                            double chkAmt = Double.parseDouble(chckAmount);
                            double totAmt = Double.parseDouble(totAmount);
                            double extraAmt = chkAmt - totAmt;
                            if(extraAmt > 0){
                                String StrextraAmt = String.valueOf(extraAmt);
                                HLCAccTxnTypeDetailVO overpayDet = mahaRemote.getOverPayAccTransactionTypeDetail();
                                HLCAccTransactionVO overpayfinalDet = new HLCAccTransactionVO();
                                
                                String accNo = overpayDet.getAccount_no();
                                String accClassname = overpayDet.getClass_name();
                                String accItemNo = overpayDet.getItem_no();
                                String accAccNo = overpayDet.getSub_account_no();
                                String accTranName = overpayDet.getTransaction_name();
                                String accTyped = overpayDet.getTransaction_type();
                                String accTypeId = overpayDet.getTransaction_type_id();
                                String description = overpayDet.getTransaction_name();
                                Debug.print("While getting"+ overpayDet.toString());
                                
                                overpayfinalDet.setAccount_no(accNo);
                                overpayfinalDet.setAccount_type("Income");
                                overpayfinalDet.setAccount_no(accNo);
                                overpayfinalDet.setClass_Typ(accClassname);
                                overpayfinalDet.setPayment_id(paymentId);
                                overpayfinalDet.setSub_account_no(accAccNo);
                                overpayfinalDet.setItem_no(accItemNo);
                                overpayfinalDet.setAmount(Float.parseFloat(StrextraAmt));
                                overpayfinalDet.setTransaction_id(accTypeId);
                                overpayfinalDet.setDescription(description);
                                
                                String pay_mode=request.getParameter("r11");
                                
                                if(pay_mode!=null && pay_mode.trim().length()>0){
                                    if(pay_mode.equalsIgnoreCase("check")){
                                        overpayfinalDet.setPayment_mode("check");
                                        overpayfinalDet.setActive_status(false);
                                    }
                                }
                                boolean extraPay = mahaRemote.insertAccountTxnDetails(overpayfinalDet);
                            }
                            if(chkAmt>=totAmt){
                                if(paymentId!=null || paymentId.trim().length()!=0){
                                    boolean Update_Status = mahaRemote.updateRecouncilActiveStatusAccTxnDetails(paymentId);
                                }
                            }
                        }
                    }
                    
                    if(r11.equals("check")){
                        if(PhaccTxnVO!=null){
                            boolean phone_status = mahaRemote.insertAccountTxnDetails(PhaccTxnVO);
                        }
                    } else{
                        session.setAttribute("PhoneVO",PhaccTxnVO);
                    }
                    
//// Horse Member type Entry
                    String membtype = request.getParameter("feeDisp");
                    if(membtype!=null || membtype.trim().length()!=0){
                        String  sarray[] = membtype.split("#");
                        String memberTypeId = sarray[0];
                        String membershipName = sarray[1];
                        String membershipAmount = sarray[2];
                        String cnct=memberTypeId+"#"+membershipName+"#"+membershipAmount;
                        
                        HLCAccTxnTypeDetailVO accTxnDet = mahaRemote.getMemberAccTxnTypeDetails(memberTypeId);
                        HLCAccTransactionVO horseMem = new HLCAccTransactionVO();
                        String accNo =  accTxnDet.getAccount_no();
                        String className = accTxnDet.getClass_name();
                        String itemNo = accTxnDet.getItem_no();
                        String subAccNo = accTxnDet.getSub_account_no();
                        String transName = accTxnDet.getTransaction_name();
                        String transType = accTxnDet.getTransaction_type();
                        
                        if(membershipAmount==null || membershipAmount.trim().length()!=0){
                            horseMem.setAmount(Float.parseFloat(membershipAmount));
                        } else{
                            horseMem.setAmount(0);
                        }
                        
                        if(r11.equalsIgnoreCase("check")){
                            horseMem.setPayment_mode("check");
                            horseMem.setActive_status(false);
                        }
                        if(r11.equalsIgnoreCase("card")){
                            String cardselect = request.getParameter("ccType");
                            horseMem.setPayment_mode(cardselect);
                            horseMem.setActive_status(false);
                        }
                        
                        horseMem.setAccount_no(accNo);
                        horseMem.setAccount_type("Income");
                        horseMem.setClass_Typ(className);
                        horseMem.setItem_no(itemNo);
                        horseMem.setSub_account_no(subAccNo);
                        horseMem.setPayment_id(paymentId);
                        horseMem.setDescription(transName);
                        
                        boolean insert_status = mahaRemote.insertAccountTxnDetails(horseMem);
                    }
                    
//End Transaction Entries
                    // History
                    HLCMemberHistoryDetail detailVO = new HLCMemberHistoryDetail();
                    
                    java.util.Calendar toDay = java.util.Calendar.getInstance();
                    int newyear = toDay.get(Calendar.YEAR);
                    int new_month = toDay.get(Calendar.MONTH);
                    String membtype1 = request.getParameter("feeDisp");
                    if(membtype1!=null || membtype1.trim().length()!=0){
                        
                        String  sarray[] = membtype1.split("#");
                        String memberTypeId = sarray[0];
                        String membershipName = sarray[1];
                        String membershipAmount = sarray[2];
                        if(memberTypeId!=null || memberTypeId.trim().length()!=0){
                            String statid ="";
                            if(checkAmount>=totalAmount){
                                statid=userremote.getStatusIBasedOnStatus("Active");
                            } else{
                                statid=userremote.getStatusIBasedOnStatus("Pending");
                            }
                            
                            detailVO.setTo_year(newyear);
                            detailVO.setPayment_id(paymentId);
                            detailVO.setMemberId(horseMemberId);
                            detailVO.setMembership_action("Register");
                            detailVO.setMembership_type_id(memberTypeId);
                            detailVO.setMembership_type_name(membershipName);
                            detailVO.setStatus_id(statid);
                            detailVO.setUser_id(sesUserId);
                            
                            boolean historyUpdate = userremote.insertHorseMemberHistoryDetails(detailVO);
                        }
                    }
                    
                    String sessionUser = (String)session.getAttribute("loggedBy");
                    if(sessionUser !=null && (sessionUser.equalsIgnoreCase("Admin") || sessionUser.equalsIgnoreCase("Usea Staff"))){
                        String activationDate = request.getParameter("activeDatVal");
                        Date checkActDate =null;
                        if(activationDate!=null && activationDate.trim().length()!=0){
                            checkActDate =  sdf.parse(activationDate);
                        }
                        if(checkAmount!=0){
                            updBal = remote.updatePendingAmount(sesUserId,paymentId,checkAmount);
                            request.setAttribute("totalAmount",String.valueOf(checkAmount));
                            request.setAttribute("horseName",competitionName);
                            if(checkAmount>=totalAmount){
                                statusChange = remote.statusChangeForHorse(horseMemberId,null);
                                activeStatus = remote.updateRealtionshipStatus(horseMemberId);
                                activateDateStatus = remote.updateRequestStatus(horseMemberId,"","Horse Registration",checkActDate);
                                request.setAttribute("activeLevel","Active");
                            } else{
                                request.setAttribute("activeLevel","null");
                            }
                        } else{
                            request.setAttribute("totalAmount",String.valueOf(totalAmount));
                        }
                    }
                    String horseRelId[] = (String[])remote.getAllEmailIds(horseMemberId);
                    if(result1 == true && result == true && upres == true){
                        String toMailIds[] = {emailId};
                        EmailContent email=new EmailContent();
                        email.setTo(toMailIds);
                        email.setFrom("anandv@digiblitz.com");
                        email.setSubject("Horse Member Registration");
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
                                "<span class=\"boldTxt\">Dear User</span>,<br /><br />"+
                                "<p>Dear User,"+
                                
                                "<p>You have successfully added a Owner/Rider for the horse and horse member id is" +horseMemberId+". It will be reviewed and Approved within 24 hrs.<p>"+
                                "Thank you for using this service.<p>"+
                                
                                "Thank You <br />"+
                                "------------------ <br />"+
                                "<span class=\"boldRedTxt\">HLC Team</span></td>"+
                                "<td valign=\"top\" background=\"images/Rght.jpg\">&nbsp;</td>"+
                                "</tr>"+
                                "<tr><td valign=\"top\" background=\"images/cornerBotLeft.jpg\">&nbsp;</td>"+
                                "<td valign=\"top\" background=\"images/cornerBot.jpg\">&nbsp;</td>"+
                                "<td valign=\"top\" background=\"images/cornerBotRght.jpg\">&nbsp;</td>"+
                                "</tr>"+
                                " </table>"+
                                "</td></tr>"+
                                "+<tr>"+
                                "<td valign=\"top\" style=\"padding:10px;\">"+
                                "<img src=\"images/pic.jpg\" width=\"272\" height=\"76\" style=\"float:right; padding-left:8px; padding-bottom:8px;\" />"+
                                "<p>The easiest way to access your day to day HLC activities online or offline where ever you are and when ever you want."+
                                "</p>If you are a NEW VISITOR, register now and ENJOY the following privileges:"+
                                "<ul>"+
                                "<li>Unlimited shopping online.</li>"+
                                "<li>Place advertisements online and/or on-site.</li>"+
                                "<li>Sponsor competitions held by HLC.</li>"+
                                "</ul>"+
                                "Also, REGISTER NOW! and become a member of HLC to access and 	enjoy the following privileges as per your Membership Type and as "+
                                "per your �Role� assigned:"+
                                "<ul>"+
                                "<li>Compete in Equestrian Events held by HLC.</li>"+
                                "<li>Take part in other events like; Annual Meetings, Educational events,"+
                                "Activity Meetings held by HLC etc.</li>"+
                                "<li>Send Messages to other members.</li>"+
                                "<li>Create your own Distribution Lists.</li>"+
                                "<li>Create/Join a group and share your thoughts and common ideas.</li>"+
                                " <li>Unlimited Shopping online.</li>"+
                                " <li>Place advertisements online and/or on-site.</li>"+
                                " <li>Sponsor competitions held by HLC.</li>"+
                                "</ul>"+
                                "and much more..."+
                                "So go ahead and <a href=\"#\">REGISTER NOW!</a></td>"+
                                "</tr>"+
                                " <tr>"+
                                "<td style=\"border-top:1px solid #CC3333; padding-top:8px;\" align=\"right\"><img src=\"images/logo-eMailFooter.jpg\" width=\"63\" height=\"65\" /></td>"+
                                "</tr>"+
                                "</table>";
                        email.setBody(content);
                        EmailEngine emailEngine = new EmailEngine();
                        boolean emailFlag = emailEngine.sendMimeEmail(email);
                        Debug.print("Email sent sucessfully :"+emailFlag);
                        request.setAttribute("totalAmount",String.valueOf(totalAmount));
                        request.setAttribute("horseMemberId",horseMemberId);
                        request.setAttribute("registrationLevel",mname);
                        request.setAttribute("horseName",competitionName);
                        return mapping.findForward("HorseSuccess");
                    } else{
                        return mapping.findForward("userListing");
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}