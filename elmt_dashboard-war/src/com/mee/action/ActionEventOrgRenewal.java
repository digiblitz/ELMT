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
package com.mee.action;
import com.hlcevent.edu.HLCEducationalSessionRemote;
import com.hlcevent.edu.HLCEducationalSessionRemoteHome;
import com.hlcform.stateless.HLCkaverystatelessRemote;
import com.hlcform.stateless.HLCkaverystatelessRemoteHome;
import com.hlcmro.display.*;
import com.util.email.EmailContent;
import com.util.email.EmailEngine;
import com.mee.actionform.FormEventOrgRenewal;
import javax.rmi.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import javax.naming.InitialContext;
import javax.naming.Context;
import com.hlcform.util.HLCPaymentDetails;
import com.hlcmro.util.*;
import com.hlcmro.org.*;
import java.util.Properties;
import java.util.Date;
import java.text.*;
import java.util.*;
import com.hlccommon.util.Debug;
import com.hlcsessionbean.krishna.HLCKrishnaStatelessRemote;
import com.hlcsessionbean.krishna.HLCKrishnaStatelessRemoteHome;
import org.apache.struts.util.MessageResources;
import com.hlcaccounts.util.HLCAccTxnTypeDetailVO;

public class ActionEventOrgRenewal extends Action {
    
     public ActionForward execute(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response)throws Exception {
             try{
                    Properties p =new Properties();
                    p.setProperty( "java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory" );
                    p.setProperty( "java.naming.factory.url.pkgs", "org.jboss.naming:org.jnp.interfaces" );
                    p.setProperty( "java.naming.provider.url", "localhost:11199" );
                    MessageResources mr=getResources(request);                
                    Context jndiContext = new InitialContext(p);
                    
                    Object obj=jndiContext.lookup("ejb/HLCVaigaiSessionBean");
                    HLCVaigaiSessionRemoteHome home = (HLCVaigaiSessionRemoteHome)
                    javax.rmi.PortableRemoteObject.narrow(obj,HLCVaigaiSessionRemoteHome.class);
                    HLCVaigaiSessionRemote remote = home.create(); 
                    HttpSession session = request.getSession(false);

                    Object objEdu=jndiContext.lookup("ejb/HLCEducationalSessionBean");
                     HLCEducationalSessionRemoteHome eduHome = (HLCEducationalSessionRemoteHome)
                    javax.rmi.PortableRemoteObject.narrow(objEdu,HLCEducationalSessionRemoteHome.class);
                    HLCEducationalSessionRemote eduRemote = eduHome.create();


                    Object objHuman=jndiContext.lookup("ejb/HLCMemberRegistrationJNDI");
                    HLCkaverystatelessRemoteHome humanHome = (HLCkaverystatelessRemoteHome)
                    javax.rmi.PortableRemoteObject.narrow(objHuman,HLCkaverystatelessRemoteHome.class);
                    HLCkaverystatelessRemote humanRemote = humanHome.create();

                    FormEventOrgRenewal endForm=(FormEventOrgRenewal)form; 
                    HLCRenewalOrganizerDetails objRenewalDet = new HLCRenewalOrganizerDetails();
                    HLCPaymentDetails objPayDet = new HLCPaymentDetails();
                    HLCPaymentDetailsVO objPayDet1=new HLCPaymentDetailsVO();
                    HLCEndorsedFormVO objEndorseDet = new HLCEndorsedFormVO();
                    String eventProcess = request.getParameter("eventProcess");
                    Debug.print("eventProcess: " + eventProcess);
                    
                     Object krish=jndiContext.lookup("ejb/HLCKrishnaStatelessJNDI");
                    HLCKrishnaStatelessRemoteHome krishHome = (HLCKrishnaStatelessRemoteHome)
                    javax.rmi.PortableRemoteObject.narrow(krish,HLCKrishnaStatelessRemoteHome.class);
                    HLCKrishnaStatelessRemote krishRemote = krishHome.create(); 
                     String reqIp=request.getRemoteAddr();
                     Debug.print(" Request IP :"+reqIp);
/*
 *
 * redirect to Event Organisert Endorsed form......
 *
 */                
            if(eventProcess.equals("newReq")){
                
              
                 String memberId = (String)session.getAttribute("memberId");
                 String eventId = (String)request.getAttribute("eventId");
                 //String eveTitle=request.getParameter("eveTitle");
                 String requestDate=request.getParameter("requestDate");
                 String eventId1=request.getParameter("eventId");
                 Vector allTypesVect = new Vector();
                 Vector eventTypeDetails = new Vector();
                 HashMap hm = new HashMap();
                HashMap eventTypeMap = new HashMap();
                ArrayList subLevel = new ArrayList();
                
          boolean delRes=krishRemote.deleteEveTypeDets(eventId1);            
         Debug.print("existRes in update of delRes" + delRes);            
        ArrayList eveChampLevlDets=remote.getChampionEveLevelDets(eventId1);          
          String mapTypeId=null;
          if(eveChampLevlDets!=null && eveChampLevlDets.size()!=0){          
            Iterator itrSub = eveChampLevlDets.iterator();
            while(itrSub.hasNext()){
            String[] subEntry = (String[])itrSub.next();
            String levelId = subEntry[1];
            String eveTypId = subEntry[3];
            
             mapTypeId=krishRemote.getEveTypLevelMapId(eveTypId,levelId);
           Debug.print("existRes in update of mapTypeId" + mapTypeId);  
 
           boolean mapResult=krishRemote.insertMapDets(eventId1,mapTypeId);           
                                    
            }
          }              
                
                 allTypesVect = remote.getAllMapTyLe();
                  if(allTypesVect!=null ){
                    Enumeration e = allTypesVect.elements();
                    while(e.hasMoreElements()){
                        String[] typeDetails = (String[])e.nextElement();
                        String mapId = typeDetails[0];
                        String typeId = typeDetails[1];
                        String levelId = typeDetails[2];
                        String typeName = typeDetails[3];
                        String levelCode = typeDetails[4];
                        String levelName = typeDetails[5];
                        if(hm.containsKey(typeId)){
                            String[] subEntry = {mapId,levelId,levelCode,levelName};
                            subLevel.add(subEntry);
                            eventTypeMap.put(typeId,subLevel);
                        }else{
                            subLevel = new ArrayList();
                            String[] subEntry = {mapId,levelId,levelCode,levelName};
                            subLevel.add(subEntry);
                            eventTypeMap.put(typeId,subLevel);
                        }
                        hm.put(typeId,typeName);
                    }
                    Set set = hm.keySet();
                    Iterator itr = set.iterator();
                    while(itr.hasNext()){
                        String keyId = (String)itr.next();
                        String valueName = (String)hm.get(keyId);
                        String[] eventTypeDet = {keyId,valueName};
                        eventTypeDetails.add(eventTypeDet);
                    }
                }
               
                 
                 request.setAttribute("eventTypeMap",eventTypeMap);
                 request.setAttribute("eventTypeDetails",eventTypeDetails);
                 
                               
                 if(request.getParameter("eventId")!=null ){
                     eventId = (String)request.getParameter("eventId");
                     String provisionalId=remote.getProvisionalId(eventId);
                     Debug.print("provisionalId........:" + provisionalId);
                     /*if(provisionalId!=null && provisionalId.trim().length()!=0){
                    String chmpLevels = remote.getChampionshipLevel(provisionalId);
                }*/
                 }
                 
                 
                if(request.getParameter("eventId")!=null ){
                    eventId = (String)request.getParameter("eventId");
                    Debug.print("request.getParameter(eventId):" + eventId);
                 
                ArrayList eveTypeList=remote.getAllEveTypeDets();
               
                if(eveTypeList!=null && eveTypeList.size()!=0){                 
                  Iterator itrSub = eveTypeList.iterator();
		  while(itrSub.hasNext()){
	        String[] subEntry = (String[])itrSub.next();  
                String typeId = subEntry[0];
                               
                } 
                } 
   
                }
                request.setAttribute("eventId",eventId);
                Debug.print("request.getAttribute(eventId):" + eventId);
                String organiser_id=eduRemote.getOrganiserByEventId(eventId);
                Debug.print("ActionMeeting Oragnisers memberId:" + organiser_id);
                ArrayList organiserDet = null;
                if(memberId!=null){
                organiserDet = (ArrayList)eduRemote.getMemberContactDetails(organiser_id);
                }
                session.setAttribute("dynamicOrgDetails",organiserDet);
                ArrayList area =(ArrayList)eduRemote.getAreaMaster();
                session.setAttribute("DispAreaDetails",area);
                HLCEventRequestVO eventReqVO = new HLCEventRequestVO();
                ArrayList stateDetails = new ArrayList();
                stateDetails = remote.getAllStates();
                ArrayList areaDetails = new ArrayList();
                if(eventId!= null && eventId.trim().length()!=0){
                    eventReqVO = remote.getEventRequestDetails(eventId);
                   areaDetails = remote.getZipcodeFromToOnStateId(eventReqVO.getCompetition_state(),eventReqVO.getCompetition_zip());
                }
                if(areaDetails!=null && areaDetails.size()!=0){
               Iterator arItr = areaDetails.iterator();
               if(arItr.hasNext()){
                    String[] areaDet = (String[])arItr.next();
                    request.setAttribute("AREA_DETAILS",areaDet);
                }
                }
                request.setAttribute("EVENT_REQUEST_VO",eventReqVO);
                request.setAttribute("stateDetails",stateDetails);
               
                //for total amount based on date.
                Date currentDate= new Date();
                SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
		String detValue = "";
                if(currentDate!=null){
                detValue = sdf1.format(currentDate);
                }
                String issueId="";
                String compYear="";
                int year=0;
                ArrayList seasonDetails = new ArrayList();
                seasonDetails=remote.getIssueIdBasedOnEventId(eventId);
                if(seasonDetails!=null && seasonDetails.size()!=0){
                    Iterator it=seasonDetails.iterator();
                    while(it.hasNext()){
                    String[] tempDet= (String[])it.next();
                    issueId=tempDet[0];
                    compYear=tempDet[1];
                    }
                }
                if(compYear!=null && compYear.trim().length()!=0){
                year=Integer.parseInt(compYear);    
                }
                boolean isValid=false;
                boolean isExists=false;
                float price=0;                
                String transacTyp=null;
                String tempValue="";
                ArrayList evePriceTypId= new ArrayList();
                Debug.print("issueId :"+issueId);
                Debug.print("year :"+year);
                Debug.print("currentDate :"+detValue);
                isExists=remote.isIssueIdExists(issueId,year);
                Debug.print("isExists Result :"+isExists);
                if(isExists==true){
                isValid=remote.isEventRegDateValid(issueId,year,detValue);
                Debug.print("isValid Result :"+isValid);
                if(isValid==true){
                price=remote.getDueDatePrice(issueId);
                evePriceTypId=remote.getEvePriceDets(issueId);                             
                tempValue="Due Date";                            
                }else{
                price=remote.getAfterDueDatePrice(issueId);
                evePriceTypId=remote.getEvePriceDets(issueId);
                tempValue="After Due Date";
                }
                }else{
                price=0;    
                }
                Debug.print("price :"+price);
                request.setAttribute("price",String.valueOf(price));                
                request.setAttribute("tempValue",tempValue);
                request.setAttribute("evePriceTypId",evePriceTypId);
                //for dynaminc content in jsp
                ArrayList priceDetails = new ArrayList();
                priceDetails=remote.getPriceDetsForEndorse(issueId,year);
                request.setAttribute("priceDetails",priceDetails);
                boolean isExist=remote.isOrgEventDetExist(eventId);
                if(isExist==true){
                float TotalPay=remote.getTotalPay(eventId);
                //request.setAttribute("eveTitle",eveTitle);    
                request.setAttribute("eventId",eventId);  
                request.setAttribute("TotalPay",String.valueOf(TotalPay)); 
                request.setAttribute("requestDate",requestDate);              
                return mapping.findForward("alreadyExists");    
                }else{
                return mapping.findForward("frmEventOrgRenewal");
                }
              
                
            }
/*
*  inserts event organiser endorsed details......
*
*/              
            else if(eventProcess.equals("insert")){
                   String paymentId=remote.getPaymtId();
                System.out.println("paymentId in servlet********" + paymentId);
                
                 //String inVoiceId1 = (String) session.getAttribute("inVoiceId1");
                 //System.out.println("inVoiceId1 in servlet********" + inVoiceId1);
        
                Debug.print("insert method is calling......");;   
                SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
                int  CcExpMonth = 0;
                int CcExpYear = 0;
                int CcCvvid =0;
                double amount =0;
                long CcNumber = 0;
                long checkNumber=0;
                Date check_date = null;
                Date comp_date = null;
                String ccType=null;
                String ccName=null;
                
/*********************Payment Details**********************************************************************/
                String  r11 = endForm.getR11();
                System.out.print("Check Status :" + r11);
                String userId=(String)session.getAttribute("userId");
                Debug.print("userId in servlet :"+userId);
                boolean insertResult=false;
                int mCount=0;
               String evePriceSize = request.getParameter("evePriceSize");
               
               if(evePriceSize!=null && evePriceSize.trim().length()!=0) {
                    mCount = Integer.parseInt(evePriceSize);
                }
                Debug.print("evePriceSize in servlet:" + evePriceSize);
                                              
                String cardselect = request.getParameter("ccType");       
                
                 if(endForm.getAmount()==null || endForm.getAmount()=="" ){
                    amount=0; 
                }
                else{
                    amount = Double.parseDouble(endForm.getAmount());
                } 
            if(r11.equals("card")){
                if(endForm.getCcNumber()==null || endForm.getCcNumber().equals("")){
                    CcNumber = 0;
                }
                else {
                    CcNumber = Long.parseLong(endForm.getCcNumber());
                }
                if(endForm.getCcName()==null || endForm.getCcName().equals("")){
                    ccName ="";
                }
                else {
                    ccName=endForm.getCcName();
                }
                if(!(endForm.getCcType().equals("")) && endForm.getCcType()!=null){
                     ccType = endForm.getCcType();  
                }
                if(endForm.getCcExpMonth()==null || endForm.getCcExpMonth().equals("")){
                     CcExpMonth =0;
                }
                else {
                    CcExpMonth=Integer.parseInt(endForm.getCcExpMonth());
                }
                if(endForm.getCcExpYear()==null || endForm.getCcExpYear().equals("")){
                    CcExpYear =0;
                }
                else{
                    CcExpYear = Integer.parseInt(endForm.getCcExpYear());
                }
                if(endForm.getCcCvvid()==null || endForm.getCcCvvid().equals("")){
                    CcCvvid =0;
                }
                else{
                    CcCvvid = Integer.parseInt(endForm.getCcCvvid());
                }
                check_date= null;
                objPayDet.setPaymentStatus("card");
                
                 HLCAccTxnTypeDetailVO transTxnDet = new HLCAccTxnTypeDetailVO();
                 
                if(mCount!=0){
                    for(int j=1; j<=mCount; j++){    
                    String evePriceTypId = request.getParameter("evePriceTypId"+j);
                    String transacTypId = request.getParameter("transacTypId"+j);  
                    String evePrice = request.getParameter("evePrice"+j); 
                if(!(transacTypId.equalsIgnoreCase("null") && evePrice.equalsIgnoreCase("null"))){
                    if(j==1){
                      session.setAttribute("transacTypId1",transacTypId);  
                      session.setAttribute("evePrice1",evePrice); 
                    } 
                    if(j==2){
                      session.setAttribute("transacTypId2",transacTypId);
                      session.setAttribute("evePrice2",evePrice); 
                    }
                    if(j==3){
                      session.setAttribute("transacTypId3",transacTypId); 
                      session.setAttribute("evePrice3",evePrice); 
                    }                                                          
                   }
                    }     
                }
                 
           objPayDet.setUserId(userId);
                objPayDet.setCcName(ccName);
                objPayDet.setCcType(ccType);
                objPayDet.setCcNumber(endForm.getCcNumber());
                objPayDet.setCcExpMonth(CcExpMonth);
                objPayDet.setCcExpYear(CcExpYear);
                objPayDet.setCcCvvid(CcCvvid);
                objPayDet.setBankName(endForm.getBankName());
                objPayDet.setCheckName(endForm.getNameOnchk());
                objPayDet.setCheckDate(check_date);
                objPayDet.setCheckNumber(endForm.getCheckNumber());
                objPayDet.setAmount(amount);
                objPayDet.setPaymentDate(new Date());       
                 
            }
            if(r11.equals("check")){
                if(endForm.getCheckNumber()==null || endForm.getCheckNumber().equals("")){
                    checkNumber=0;
                }
                else{
                    checkNumber= Long.parseLong(endForm.getCheckNumber());
                }
                if(endForm.getCheckDate().equals("")){
                    check_date= null;
                }
                else{
                    check_date =(Date)sdf1.parse(endForm.getCheckDate());
                }
                    objPayDet.setPaymentStatus("Check");
                    objPayDet1.setPaymentStatus("Check");
                 objRenewalDet.setRequestStat("Pending"); 
                 
        objPayDet1.setUserId(userId);
                objPayDet1.setCcName(ccName);
                objPayDet1.setCcType(ccType);
                if(endForm.getCcNumber()==null || endForm.getCcNumber().equals("")){
                    CcNumber = 0;
                }
                else {
                    CcNumber = Long.parseLong(endForm.getCcNumber());
                }
                objPayDet1.setCcNumber(CcNumber);
                objPayDet1.setCcExpMonth(CcExpMonth);
                objPayDet1.setCcExpYear(CcExpYear);
                objPayDet1.setCcCvvid(CcCvvid);
                objPayDet1.setBankName(endForm.getBankName());
                objPayDet1.setCheckName(endForm.getNameOnchk());
                objPayDet1.setCheckDate(check_date);
                objPayDet1.setCheckNumber(Long.parseLong(endForm.getCheckNumber()));
                objPayDet1.setAmount(amount);
                objPayDet1.setPaymentDate(new Date());
                         
            }
               

                System.out.println("checkNumber in servlet1111111" +endForm.getCheckNumber());
                
                                     
                String tempAmt=String.valueOf(amount);
                
                System.out.println("getBankName" +endForm.getBankName());
                System.out.println("check_date" +check_date);
                System.out.println("checkNumber" +checkNumber);
                System.out.println("amount" +amount);
                System.out.println("setPaymentDate" +new Date());
                Debug.print("endForm.getCompetitionDate():"+endForm.getCompetitionDate());     
            if(endForm.getCompetitionDate() != null || !(endForm.getCompetitionDate().equals(""))){
                comp_date =(Date)sdf1.parse(endForm.getCompetitionDate());            
            }
     System.out.print(" comp_date " + comp_date);
            String eventId = request.getParameter("eventId");
            String orgId = (String)session.getAttribute("memberId");
            Debug.print("OrganizerId:" + orgId);
            Debug.print("eventId:" + eventId);
            session.setAttribute("EventID",eventId);
            Debug.print("comp_date:" + comp_date);
            if(eventId!=null && orgId!=null && eventId.trim().length()!=0) {
                objRenewalDet.setEventId(Integer.parseInt(eventId));
                objRenewalDet.setOrganizerId(orgId);
                objRenewalDet.setCompetitionName(endForm.getCompetitionName());
                session.setAttribute("EventTitle",endForm.getCompetitionName());
                //objRenewalDet.setCompetitionLocation(endForm.getCompetitionLocation());
                //objRenewalDet.setCompetitionCity(endForm.getCompetitionCity());
                //objRenewalDet.setCompetitionState(endForm.getCompetitionState());
                //objRenewalDet.setCompetitionCountry(endForm.getCompetitionCountry());
                //objRenewalDet.setCompetitionZip(endForm.getCompetitionZip());
                objRenewalDet.setCompetitionDate(comp_date);
                //objRenewalDet.setChampionshipArea(endForm.getChampionshipArea());        
                objRenewalDet.setComManagementName(endForm.getComManagementName());
                objRenewalDet.setComManagementAddress(endForm.getComManagementAddress());
                objRenewalDet.setComManagementAddress2(endForm.getComManagementAddress2());
                objRenewalDet.setComManagementCity(endForm.getComManagementCity());
                objRenewalDet.setComManagementState(endForm.getComManagementState());
                objRenewalDet.setComManagementCountry(endForm.getComManagementCountry());
                objRenewalDet.setComManagementZip(endForm.getComManagementZip());
                objRenewalDet.setComManagementPhone(endForm.getComManagementPhone());
                objRenewalDet.setComManagementFax(endForm.getComManagementFax());
                objRenewalDet.setComManagementEmail(endForm.getComManagementEmail());
                objRenewalDet.setManagerUsefMemberId(endForm.getManagerUsefMemberId());
                objRenewalDet.setManagerUseaMemberId(endForm.getManagerUseaMemberId());
                objRenewalDet.setManagerName(endForm.getManagerName());
                objRenewalDet.setSecretaryUsefMemberId(endForm.getSecretaryUsefMemberId());
                objRenewalDet.setSecretaryName(endForm.getSecretaryName());

           
            
                System.out.println("setCompetitionName" +endForm.getCompetitionName());
                /*System.out.println("setCompetitionLocation" +endForm.getCompetitionLocation());
                System.out.println("setCompetitionCity" + endForm.getCompetitionCity());
                System.out.println("setCompetitionState" + endForm.getCompetitionState());
                System.out.println("setCompetitionCountry" + endForm.getCompetitionCountry());
                System.out.println("setCompetitionZip" + endForm.getCompetitionZip());*/
                System.out.println("setCompetitionDate" + comp_date);
                //System.out.println("setChampionshipArea" + endForm.getChampionshipArea());        
                System.out.println("setComManagementName" +endForm.getComManagementName());
                System.out.println("getComManagementAddress" +endForm.getComManagementAddress());
                System.out.println("getComManagementAddress2" +endForm.getComManagementAddress2());
                System.out.println("getComManagementCity" +endForm.getComManagementCity());
                System.out.println("getComManagementState" +endForm.getComManagementState());
                System.out.println("getComManagementCountry" +endForm.getComManagementCountry());
                System.out.println("getComManagementZip" +endForm.getComManagementZip());
                System.out.println("getComManagementPhone" +endForm.getComManagementPhone());
                System.out.println("getComManagementFax" +endForm.getComManagementFax());
                System.out.println("getComManagementEmail" +endForm.getComManagementEmail());
                System.out.println("getManagerUsefMemberId" +endForm.getManagerUsefMemberId());
                System.out.println("getManagerUseaMemberId" +endForm.getManagerUseaMemberId());
                System.out.println("getManagerName" +endForm.getManagerName());
                System.out.println("getSecretaryUsefMemberId" + endForm.getSecretaryUsefMemberId());
                System.out.println("getSecretaryName" + endForm.getSecretaryName());
                
         if(paymentId!=null && paymentId.trim().length()!=0){
                 objPayDet1.setPaymentId(paymentId);
             }else{
                 objPayDet1.setPaymentId(null); 
             }
             
                
                
                if(r11.equals("card")){
                    Debug.print("Sucessfully Redirect to Paypal");
                   
                    //ActivityOrganizerVO
                    Debug.print("Sucessfully Payment objRenewalDet:" + objRenewalDet.toString());
                    Debug.print("Sucessfully Payment objPaymentList:" + objPayDet.toString());
                     //=======================================
                    
                    request.setAttribute("email","suryey@yahoo.co.in");
                    
                    request.setAttribute("cardNo",String.valueOf(CcNumber));
                    String expMon = String.valueOf(CcExpMonth);
                    String expYear = String.valueOf(CcExpYear);
                    /*if(expMon.trim().length()==1) {
                         expMon = "0" + expMon;
                    }*/
                   // if(expYear.trim().length()==1) {
                   // Debug.print("expYear:" + expYear);
                    //expYear = expYear.substring(2);
                    //
                    Debug.print("expMon:" + expMon);
                    Debug.print("expYear:" + expYear);

                    String expDate = expMon + expYear;
                    
          // commented for not going to use viaklix instead going to use PayPal
            //Starts Here           
                    
                    /*request.setAttribute("expDate",expDate);
                    request.setAttribute("amount",String.valueOf(amount));
                    request.setAttribute("chkDigit",String.valueOf(CcCvvid));                                    
                    session.setAttribute("cardselect",ccType);*/
         //Ends Here
                String inVoiceId1="1"; 
                System.out.println("Invoice Id value : "+inVoiceId1);
             int intVId = 0;
            if (inVoiceId1.equalsIgnoreCase("0")) {
                intVId = 1;
            } else {
                intVId = 1;
            }       
             if(paymentId!=null && paymentId.trim().length()!=0){
                 objPayDet.setPaymentId(paymentId);
             }else{
                 objPayDet.setPaymentId(null); 
             }
             if(reqIp!=null && reqIp.trim().length()!=0){
                 objPayDet.setIpAddress(reqIp);
             }else{
                 objPayDet.setIpAddress(null); 
             }
             Debug.print("objPayDet.getPaymentId() in ActionEventOrgRenewal&&&&&&"+objPayDet.getPaymentId());
             
            request.setAttribute("AMT", request.getParameter("amount"));
            request.setAttribute("PAYMENTACTION", "Authorization");
            request.setAttribute("CREDITCARDTYPE", request.getParameter("ccType"));
            request.setAttribute("ACCT", request.getParameter("ccNumber"));
            request.setAttribute("EXPDATE", expDate);
            request.setAttribute("IPADDRESS", reqIp);
            request.setAttribute("FIRSTNAME", request.getParameter("ccName"));
            request.setAttribute("CVVNo", request.getParameter("ccCvvid"));
            request.setAttribute("STREET", "1 Main St");
            request.setAttribute("CITY", "San Jose");
            request.setAttribute("STATE", "CA");
            request.setAttribute("ZIP", "95131");
            request.setAttribute("COUNTRYCODE", "US");
            request.setAttribute("EMAIL", (String)session.getAttribute("emailId"));
            session.setAttribute("URLPATH", "EventOrgRenewal.do?eventProcess=newReq");
            session.setAttribute("objRenewalDet",objRenewalDet);
            session.setAttribute("objPayment",objPayDet);
            session.setAttribute("cardselect",request.getParameter("ccType"));
            
            //intVId++; 
            request.setAttribute("intVId", String.valueOf(intVId));
            System.out.println("intVId in servlet&&&&&&&" + intVId);
            request.setAttribute("purpose", "endorsedForm");         
             // commented for not going to use viaklix instead going to use PayPal
            //Starts Here
       
                   /* StringBuffer reqURL = request.getRequestURL();
                    int lastIndex = reqURL.lastIndexOf("/") ;
                    String url ="";
                    if(lastIndex!=-1){
                        url = reqURL.substring(0,lastIndex+1);
                    }
                    String tmpNova  = mr.getMessage("orgEventDetail.nova");
                    String nova = url + tmpNova;
                    
                    request.setAttribute("nova",nova);
                    //===========================================
                    return mapping.findForward("novaPage");*/
                //Ends Here  
               Debug.print("Redirect To Paypal");
               return mapping.findForward("testing");   

                }
                 else{
                    //remote.addRenewal(objRenewalDet,objPayDet);
                    
                    String paymentId_Cheque=remote.createPayment(objPayDet1);
                    Debug.print("paymentId in servlet"+paymentId_Cheque);
                    if(paymentId_Cheque!=null && paymentId_Cheque.trim().length()!=0){
                    insertResult=remote.insertEndorseRenDetails(objRenewalDet,paymentId_Cheque);    
                    }
                    Debug.print("Sucessfully Inserted................"+insertResult);
                    //for updating the paymentid in history table
                    String issueId="";
                    String compYear="";
                    int year=0;
                    ArrayList seasonDetails = new ArrayList();
                    seasonDetails=remote.getIssueIdBasedOnEventId(eventId);
                    if(seasonDetails!=null && seasonDetails.size()!=0){
                        Iterator it=seasonDetails.iterator();
                        while(it.hasNext()){
                        String[] tempDet= (String[])it.next();
                        issueId=tempDet[0];
                        compYear=tempDet[1];
                        }
                    }
                    if(compYear!=null && compYear.trim().length()!=0){
                    year=Integer.parseInt(compYear);    
                    }
                    boolean updatePaymentId=false;
                    if(paymentId!=null && paymentId.trim().length()!=0){
                    updatePaymentId=remote.updatePaymentIdInHist(eventId,year,paymentId);
                    }
                    Debug.print("Updating paymentId in History Table :"+updatePaymentId);
 //======================================================================================================================                    
                        //   if(){
                        String toMailId = (String)session.getAttribute("emailId");
                        String toMailIds[] = {toMailId};
                        //String toMailIds[] = {"rpunithaa@yahoo.co.in"};
                        EmailContent email=new EmailContent();
                        email.setTo(toMailIds);
                        email.setFrom("info@hlc.com");
                        email.setSubject("Event Registration Application confirmation.");

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
                        "<p>Dear Organizer,"+

                        "You have successfully sent an Event Registration application to United States Eventing Association for all  "+
                        "the events and competitions that are to be held! Your application would be verified and processed within"+
                        "24 hours."+
                        "Thank you for using this service.<p>"+
                        "Thank you for using the service provided by <span class=\"boldTxt\">United States Eventing Association</span>.</p>"+
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
                        //email.setAttachments();
                        EmailEngine emailEngine = new EmailEngine();
                        boolean emailFlag = emailEngine.sendMimeEmail(email);
                        Debug.print("Email sent sucessfully :"+emailFlag);
         }
//======================================================================================================================
                        request.setAttribute("eventId",eventId);
                        
                        if(insertResult==true){
                        request.setAttribute("paymentMode","check");
                        request.setAttribute("amount",String.valueOf(objPayDet.getAmount()));
                        return mapping.findForward("forEndorseSuccess");
                        }else{
                       
                        request.setAttribute("paymentId",objPayDet.getPaymentId()); 
                        request.setAttribute("price",String.valueOf(objPayDet.getAmount()));
                        return mapping.findForward("frmMeeRenewalSuccess");
                        }
                     }
       }      
           
/*
 * show endorsed details to admin.....
 * Ejb -  HLCRenewalOrganizerDetails objRenewalDet = new HLCRenewalOrganizerDetails();
 */        
             else if(eventProcess.equals("initShow")){
               //String status = request.getParameter("status");
               //Debug.print("status" + status);
              // ArrayList list =(ArrayList)remote.getRenewalDetailByStatus("status");
               session.setAttribute("DisplayRenewalDetails",null);
               return mapping.findForward("frmEventOrgRenewalListing");
            }
                
            else if(eventProcess.equals("show")){
               String status = request.getParameter("status");
               Debug.print("status" + status);
               ArrayList renewalList = new ArrayList();
               renewalList =(ArrayList)remote.getRenewalDetailByStatus(status);
               session.setAttribute("DisplayRenewalDetails",renewalList);
               request.setAttribute("status",status);
               return mapping.findForward("frmEventOrgRenewalListing");
            }
                
             else if(eventProcess.equals("showOrg")){
               Debug.print("showOrg()");
               String memberId = (String)session.getAttribute("memberId");
               //String memberId = "100001";
               ArrayList renewalList = new ArrayList();
               if(memberId!=null){
                   renewalList =(ArrayList)remote.getRenewalDetailByOraganizer(memberId);
               }
               session.setAttribute("DisplayRenewalDetails",renewalList);
               
               return mapping.findForward("frmEventOrgRenewalListingforOrg");
                    
            }
/*
 * based on event id display organiser form details.........
 *
 */                
       else if(eventProcess.equals("display")){
            String eventId = request.getParameter("eventId");
            String renewalId = request.getParameter("renewalId");
            Debug.print("eventProcess() Renewal Id:" + renewalId);
            //String eventHotId = "02f09a7f-3edd-4443-948b-aeee07d24fa8";
            //String eventHotId = "02f09a7f-3edd-4443-948b-aeee07d24fa8";
            
             String memberId = (String)session.getAttribute("memberId");
                // String memberId = "100001";  
             if(renewalId!=null){
                //objRenewalDet = remote.getRenewalDetails(renewalId);
                 objEndorseDet=remote.getSingleEndorsedFormDetails(eventId);
                memberId = objEndorseDet.getOrgId();
                Debug.print("ActionMeeting memberId:" + memberId);
                ArrayList organiserDet = null;
                if(memberId!=null){
                    organiserDet = (ArrayList)humanRemote.getMemberContactDetails(memberId);
                }

             session.setAttribute("dynamicOrgDetails",organiserDet);
            }
           
              ArrayList area =(ArrayList)eduRemote.getAreaMaster();
                session.setAttribute("DispAreaDetails",area);
             
             
             request.setAttribute("DisplayEndorsedDetails",objEndorseDet);
            Debug.print("objEndorseDet:" + objEndorseDet);
            return mapping.findForward("frmEventOrgRenewalView");
        }  


                //Event Detail View for Admin

 	else if(eventProcess.equals("eventViewAdmin")){
           // ArrayList eventList = new ArrayList();
           // eventList = remote.getAllEventDetails();
          //  Debug.print("ActionEventOrgRenewal.eventViewAdmin() Called:");
          //  session.setAttribute("displayAllEventDet",eventList);
            return mapping.findForward("frmMeeAdminEventListings");
        }
                
         else if(eventProcess.equals("eventViewAdminStaus")){
            String requestStatus = request.getParameter("requestStatus");
            Debug.print("       Request Status in Servlet: " + requestStatus);
            ArrayList eventList = new ArrayList();
            if(requestStatus!=null && requestStatus.trim().length()!=0){
                eventList = remote.getAllEventDetailsByRequestStatus(requestStatus);
                Debug.print("ActionEventOrgRenewal.eventViewAdmin() Called:");
                request.setAttribute("displayAllEventDet",eventList);
            }
            request.setAttribute("requestStatus" , requestStatus);
            return mapping.findForward("frmMeeAdminEventListings");
        }
        
        
        //Event Detail View for Organizer
                
       /* else if(eventProcess.equals("eventViewOrg")){
           // String eventId = request.getParameter("eventId");
           // String eventHotId = "10000";
             String organizerId = (String)session.getAttribute("memberId");
             Debug.print("eventViewOrg():memberId:" + organizerId);
             
             ArrayList eventList = new ArrayList();
             if(organizerId!=null){
                eventList = remote.getAllOrganizerEventDetails(organizerId);
             }
            session.setAttribute("displayOrgAllEventDet",eventList);
            return mapping.findForward("frmMeeOrgEventListings");
        }*/
         else if(eventProcess.equals("eventViewOrg")){
        Debug.print("eventProcess in ActionEventOrgRenewal :"+eventProcess);       
        String organizerId = (String)session.getAttribute("memberId");
        String indieventID = (String)request.getAttribute("indieventID");
        Debug.print("eventViewOrg():memberId:" + organizerId);
        ArrayList eventList = new ArrayList();
            if(organizerId!=null){
            eventList = remote.getAllOrgEventDetails(organizerId);
            }
        
        request.setAttribute("displayOrgAllEventDet",eventList);  
         request.setAttribute("indieventID",indieventID); 
        return mapping.findForward("frmMeeOrgEventListings");
        }
       
       else if(eventProcess.equals("eventViewSecre")){
        Debug.print("eventProcess in ActionEventOrgRenewal :"+eventProcess);       
        String userId = (String)session.getAttribute("userId");
        String indieventID = (String)request.getAttribute("indieventID");
        Debug.print("eventViewOrg():userId:" + userId);
        ArrayList eventList = new ArrayList();
            if(userId!=null){
            eventList = remote.getAllEveSecreEventDetails(userId);
            }
        
        request.setAttribute("displayOrgAllEventDet",eventList);  
         request.setAttribute("indieventID",indieventID); 
        return mapping.findForward("frmMeeSecEventListings");
        }     
                    
                    
        /*else if(eventProcess.equals("listDetails")){
        Debug.print("eventProcess in ActionEventOrgRenewal :"+eventProcess);
        String status=request.getParameter("acStatus");
        String organizerId = (String)session.getAttribute("memberId");
        Debug.print("eventViewOrg():memberId:" + organizerId);
        ArrayList eventList = new ArrayList();
            if(organizerId!=null){
            eventList = remote.getAllOrgEventDetails(organizerId,status);
            }
        request.setAttribute("status",status);
        request.setAttribute("displayOrgAllEventDet",eventList);
        return mapping.findForward("frmMeeOrgEventListings");
        
        }*/
                    
       //Event Status Change  for Admin.
        
        else if(eventProcess.equals("statusChange")){
            String eventId = request.getParameter("eventId");
            String statusId = request.getParameter("statusId");
             String comments = request.getParameter("comments");
             
             Debug.print("statusChange():eventId:" + eventId);
             Debug.print("statusChange():statusId:" + statusId);
              Debug.print("statusChange():comments:" + comments);
             
             if(eventId!=null && statusId !=null){
                boolean result = remote.changeEventStatus(eventId,statusId,comments);
             }
             
            ArrayList eventList = new ArrayList();
            eventList = remote.getAllEventDetails();
            Debug.print("ActionEventOrgRenewal.statusChange() Called:");
            session.setAttribute("displayAllEventDet",eventList);
            return mapping.findForward("frmMeeAdminEventListings");
        }
        
          //==================================================Admin view eo details for approval =====================================================================================           
                
        else if(eventProcess.equals("approve"))
        {
                    String jndiname=mr.getMessage("jndi.vaigaisless");
                    Object objref = jndiContext.lookup(jndiname);
                    
                    HLCVaigaiStatelessRemoteHome home1 =(HLCVaigaiStatelessRemoteHome) PortableRemoteObject.narrow(objref, HLCVaigaiStatelessRemoteHome.class);
                    
                    Object obj2=jndiContext.lookup("ejb/HLCVaigaiSessionBean");
                    HLCVaigaiSessionRemoteHome home2 = (HLCVaigaiSessionRemoteHome)
                    javax.rmi.PortableRemoteObject.narrow(obj2,HLCVaigaiSessionRemoteHome.class);
                    HLCVaigaiSessionRemote remote2 = home2.create();
                    
                    Vector allTypesVect = new Vector();
                    Vector getAllMapRuleSubRule= new Vector();
                    
                    Vector distanceSpeedVect = new Vector();
                    Vector dressageHorseTrialsVect = new Vector();
                    ArrayList arenaSizeVect = new ArrayList();
                    ArrayList jdDet=new ArrayList();
                    
                    HLCVaigaiStatelessRemote remote1 = home1.create();
                    allTypesVect = remote1.getAllMapTyLe();
                    getAllMapRuleSubRule=remote1.getAllMapRuleSubRule();
                    jdDet=remote1.selectJudgesDetails();
                    System.out.println("jdDet size in servlet :"+jdDet.size());
                    
                    dressageHorseTrialsVect = remote1.selectDressageHorseTrials();
                    arenaSizeVect = remote1.getAreaPriceMaster();//selectArenaSize();
                    
                    request.setAttribute("allTypesVect", allTypesVect);
                    request.setAttribute("distanceSpeedVect",distanceSpeedVect);
                    request.setAttribute("getAllMapRuleSubRule",getAllMapRuleSubRule);
                    request.setAttribute("dressageHorseTrialsVect",dressageHorseTrialsVect);
                    request.setAttribute("arenaSizeVect",arenaSizeVect);
                    request.setAttribute("jdDet",jdDet);
                   
                    String eventId=request.getParameter("eventId");
                    Debug.print("eventId :"+eventId);
                    ArrayList eventDetails=new ArrayList();
                   // HLCEventDetailsVO objEventDet=new HLCEventDetailsVO();
                   
                    eventDetails=remote2.getDetailedEvents(eventId);
                    request.setAttribute("eventDetails",eventDetails);
                    
                    return mapping.findForward("viewEventDetails");
        }
    
        //==================================================init Map Area State Zip=====================================================================================
        else if(eventProcess.equals("initMapStateZip")){
            Debug.print("ActionEventOrgRenewal.initMapStateZip()");
            ArrayList stateList = new ArrayList();
            ArrayList areaList = new ArrayList();
            stateList = (ArrayList)remote.getAllStates();
            areaList = (ArrayList)remote.getAllAreaMasters();

            request.setAttribute("allStateList",null);
            request.setAttribute("allStateList",stateList);

            request.setAttribute("allAreaList",null);
            request.setAttribute("allAreaList",areaList);

            Debug.print("ActionEventOrgRenewal.initMapStateZip() sucessfully comes from servlet.");
            return mapping.findForward("frmMeeMapAreaZip");
        }

        //==================================================List Map Area State Zip=====================================================================================
        else if(eventProcess.equals("LstMapStateZip")){
            Debug.print("ActionEventOrgRenewal.LstMapStateZip()");

            ArrayList areaList = null;
            areaList = (ArrayList)remote.getAllAreaMasters();

            request.setAttribute("allAreaList",areaList);
            request.setAttribute("ValueList",null);

            Debug.print("ActionEventOrgRenewal.LstMapStateZip() sucessfully comes from servlet.");
            return mapping.findForward("frmLstMapStateZip");
        }
        //==================================================List Map Area State Zip=====================================================================================
        else if(eventProcess.equals("OnLstMapStateZip")){
            Debug.print("ActionEventOrgRenewal.OnLstMapStateZip()");
            
            String areaID = request.getParameter("areaSelect");
            Debug.print("areaID "+areaID);
            ArrayList areaList = null;
            ArrayList ValLst = null;            
            
            areaList = (ArrayList)remote.getAllAreaMasters();
            
            request.setAttribute("allAreaList",areaList);
            
            ValLst = (ArrayList) remote.getAreaStateMasterOnID(areaID);
            Debug.print("OnareaList Size "+ValLst.size());
           
            request.setAttribute("ValueList",ValLst);
            request.setAttribute("area_id",areaID);
            
            Debug.print("ActionEventOrgRenewal.OnLstMapStateZip() sucessfully comes from servlet.");
            return mapping.findForward("frmLstMapStateZip");
        }                
 //==================================================init Map Area and Area Chair=====================================================================================
        else if(eventProcess.equals("initMapAreaAndAreaChair")){
            Debug.print("ActionEventOrgRenewal.initMapAreaAndAreaChair()");
            ArrayList areaList = new ArrayList();
            areaList = (ArrayList)remote.getAllAreaMasters();

            request.setAttribute("allAreaList",null);
            request.setAttribute("allAreaList",areaList);

            Debug.print("ActionEventOrgRenewal.initMapAreaAndAreaChair() sucessfully comes from servlet.");
            return mapping.findForward("frmMeeMapACAreaMaster");
        }
//==================================================Map Area and State=====================================================================================
                else if(eventProcess.equals("mapAreaChairAreaPerson")){
                    Debug.print("ActionEventOrgRenewal.mapAreaChairAreaPerson()");
                    
                    String areaId = request.getParameter("areaId");
                    String areaChairId = request.getParameter("areaChairId");
                    
                    boolean resultVal = humanRemote.getUserCode(areaChairId);
                    Debug.print("ActionEventOrgRenewal areaChairId Checking:" + resultVal);
                    if(resultVal==true){
                         if(areaId!=null && areaChairId!=null){
                            boolean result = remote.generateMappingAreaChairArea(areaId, areaChairId);
                            Debug.print("ActionEventOrgRenewal.mapAreaChairAreaPerson() result:"  + result);
                        }
                    }
                    else{
                        ArrayList areaList = new ArrayList();
                        areaList = (ArrayList)remote.getAllAreaMasters();

                        request.setAttribute("allAreaList",null);
                        request.setAttribute("allAreaList",areaList);
                        request.setAttribute("err",areaChairId);
                        return mapping.findForward("frmMeeMapACAreaMaster");
                    }
                    
                    Debug.print("ActionRoleMangement.mapAreaChairAreaPerson() areaId:" + areaId);
                    Debug.print("ActionRoleMangement.mapAreaChairAreaPerson() areaChairId:" + areaChairId);
                     
                   
                    Debug.print("ActionEventOrgRenewal.mapAreaChairAreaPerson() sucessfully comes from servlet.");
                     return mapping.findForward("redirFrmMeeMapACAreaMaster");
                }                  
                
//==================================================Map Area and State=====================================================================================
                else if(eventProcess.equals("mapAreaState")){
                    Debug.print("ActionEventOrgRenewal.mapAreaState()");
                    String areaId = request.getParameter("areaId");
                    String stateId = request.getParameter("stateId");
                    String zipCodeFrom = request.getParameter("zipCodeFrom");
                    String zipCodeTo = request.getParameter("zipCodeTo");
                    
                    Debug.print("ActionRoleMangement.mapAreaState() areaId:" + areaId);
                    Debug.print("ActionRoleMangement.mapAreaState() stateId:" + stateId);
                    
                    if(areaId!=null){
                        boolean result = remote.generateMappingAreaStateZip(areaId, stateId, zipCodeFrom, zipCodeTo);
                        Debug.print("ActionEventOrgRenewal.mapAreaState() result:"  + result);
                        if(result==false)
                        request.setAttribute("err", "err");
                    }
                    
                    ArrayList stateList = new ArrayList();
                    ArrayList areaList = new ArrayList();
                    stateList = (ArrayList)remote.getAllStates();
                    areaList = (ArrayList)remote.getAllAreaMasters();

                    request.setAttribute("allStateList",null);
                    request.setAttribute("allStateList",stateList);

                    request.setAttribute("allAreaList",null);
                    request.setAttribute("allAreaList",areaList);
                    
                    Debug.print("ActionEventOrgRenewal.mapAreaState() sucessfully comes from servlet.");
                    return mapping.findForward("frmMeeMapAreaZip");
                }
//==================================================init list for AreaChair And Area=====================================================================================
                else if(eventProcess.equals("mapAreaAndAreaChairListing")){
                    Debug.print("ActionEventOrgRenewal.mapAreaAndAreaChairListing()");
                    ArrayList areaChairList = new ArrayList();
                    areaChairList = (ArrayList)remote.getAllAreaAndAreaChairs();

                    request.setAttribute("allAreaChairList",null);
                    request.setAttribute("allAreaChairList",areaChairList);
                    Debug.print("ActionEventOrgRenewal.mapAreaAndAreaChairListing() sucessfully comes from servlet.");
                    return mapping.findForward("frmMeeACAreaMapListing");
                }
//==================================================init list for AreaChair And Area=====================================================================================
                else if(eventProcess.equals("initEditAreaChairArea")){
                    Debug.print("ActionEventOrgRenewal.initEditAreaChairArea()");
                    String mapAreaId = request.getParameter("mapAreaId");
                    Debug.print("ActionEventOrgRenewal.initEditAreaChairArea() mapAreaId:" + mapAreaId);
                    HLCAreaChairsVO objACVO = new  HLCAreaChairsVO();

                    if(mapAreaId != null){
                         objACVO = (HLCAreaChairsVO)remote.getAreaAndAreaChairsByAreaMapId(mapAreaId);
                    }
                    request.setAttribute("areaChairDetailVO",null);
                    request.setAttribute("areaChairDetailVO",objACVO);
                    
                    ArrayList areaList = new ArrayList();
                    areaList = (ArrayList)remote.getAllAreaMasters();

                    request.setAttribute("allAreaList",null);
                    request.setAttribute("allAreaList",areaList);
                    
                    Debug.print("ActionEventOrgRenewal.initEditAreaChairArea() sucessfully comes from servlet.");
                    return mapping.findForward("frmMeeEditMapACAreaMaster");
                }                    
//==================================================Edit Area and Area Chair=====================================================================================
                else if(eventProcess.equals("editAreaChair")){
                    Debug.print("ActionEventOrgRenewal.editAreaChair()");
                    
                    String mapAreaId = request.getParameter("mapAreaId");
                    String areaId = request.getParameter("areaId");
                    String areaChairId = request.getParameter("areaChairId");
                    
                    
                    Debug.print("ActionRoleMangement.editAreaChair() mapAreaId:" + mapAreaId);
                    Debug.print("ActionRoleMangement.editAreaChair() areaId:" + areaId);
                    Debug.print("ActionRoleMangement.editAreaChair() areaChairId:" + areaChairId);
                    boolean resultVal = humanRemote.getUserCode(areaChairId);
                    Debug.print("ActionEventOrgRenewal areaChairId Checking:" + resultVal);
                     if(resultVal==true){
                         if(mapAreaId!=null && areaId!=null && areaChairId!=null){
                            boolean result = remote.editMappingAreaChairArea(mapAreaId,areaId,areaChairId);
                            Debug.print("ActionEventOrgRenewal.editAreaChair() result:"  + result);
                        }
                    }
                    else{
                        ArrayList areaList = new ArrayList();
                        areaList = (ArrayList)remote.getAllAreaMasters();

                        request.setAttribute("allAreaList",null);
                        request.setAttribute("allAreaList",areaList);
                        HLCAreaChairsVO objACVO = new  HLCAreaChairsVO();
                        if(mapAreaId != null){
                            objACVO = (HLCAreaChairsVO)remote.getAreaAndAreaChairsByAreaMapId(mapAreaId);
                        }
                        request.setAttribute("areaChairDetailVO",null);
                        request.setAttribute("areaChairDetailVO",objACVO);
                    
                        request.setAttribute("err",areaChairId);
                        return mapping.findForward("frmMeeEditMapACAreaMaster");
                    }
                    Debug.print("ActionEventOrgRenewal.editAreaChair() sucessfully comes from servlet.");
                     return mapping.findForward("redirfrmMeeACAreaMapListing");
                }                   
//==================================================show a particular AreaChair And Area=====================================================================================
                else if(eventProcess.equals("showAreaChairDet")){
                    Debug.print("ActionEventOrgRenewal.initEditAreaChairArea()");
                    String mapAreaId = request.getParameter("mapAreaId");
                    Debug.print("ActionEventOrgRenewal.showAreaChairDet() mapAreaId:" + mapAreaId);
                    HLCAreaChairsVO objACVO = new  HLCAreaChairsVO();

                    if(mapAreaId != null){
                         objACVO = (HLCAreaChairsVO)remote.getAreaAndAreaChairsByAreaMapId(mapAreaId);
                    }
                    request.setAttribute("areaChairDetailVO",null);
                    request.setAttribute("areaChairDetailVO",objACVO);
                    int stateCount = 0;
                    if(objACVO.getAreaId()!=null){
                       stateCount  = remote.stateCount(objACVO.getAreaId());
                    }
                    request.setAttribute("stateCount",String.valueOf(stateCount));

                    Debug.print("ActionEventOrgRenewal.showAreaChairDet() sucessfully comes from servlet.");
                    return mapping.findForward("frmMeeAreaChairDetails");
                } 
                else if(eventProcess.equals("initApprove")){
                Debug.print("eventProcess in ActionEventOrgRenewal :"+eventProcess);
                
                        String eventId = request.getParameter("eventId");
                        String renewalId = request.getParameter("renewalId");
                        Debug.print("eventProcess() Renewal Id:" + renewalId);
                        String memberId = (String)session.getAttribute("memberId");
                          
                        if(renewalId!=null){
                        
                        objEndorseDet=remote.getSingleEndorsedFormDetails(eventId);
                        memberId = objEndorseDet.getOrgId();
                        Debug.print("ActionMeeting memberId:" + memberId);
                        ArrayList organiserDet = null;
                        if(memberId!=null){
                        organiserDet = (ArrayList)humanRemote.getMemberContactDetails(memberId);
                        }

                        session.setAttribute("dynamicOrgDetails",organiserDet);
                        }

                        ArrayList area =(ArrayList)eduRemote.getAreaMaster();
                        session.setAttribute("DispAreaDetails",area);
                        request.setAttribute("DisplayEndorsedDetails",objEndorseDet);
                        Debug.print("objEndorseDet:" + objEndorseDet);
                        return mapping.findForward("frmEventOrgRenewalApprove");   
                }
                else if(eventProcess.equals("approveEndorse")){
                Debug.print("eventProcess in ActionEventOrgRenewal :"+eventProcess); 
                String eventId=request.getParameter("eventId");
                String status=request.getParameter("acStatus");
                Debug.print("eventId in approveEndorse : "+eventId);
                Debug.print("status in approveEndorse : "+status);
                if(eventId!=null && eventId.trim().length()!=0 && status!=null && status.trim().length()!=0){
                boolean updateResult=remote.updateEndorseEventByAdmin(eventId,status);    
                }
                return mapping.findForward("frmEventOrgRenewalEditSuccess");    
                }
//===============================                
    }
        catch( Exception e ){
            e.printStackTrace();
        }
        return mapping.findForward("errorPage");
        
    }
     
     

}
