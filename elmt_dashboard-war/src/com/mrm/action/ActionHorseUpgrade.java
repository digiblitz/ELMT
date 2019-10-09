/*
 * Program Name     :   ActionHorseUpgrade.java
 * Created Date     :   December 4, 2006, 7:34 PM
 * Author           :   Hari
 * Copy Right       :   digiBlitz Technologies Inc /  digiBlitz Technologies (P) Ltd
 * Version          :   1.4
 * CopyRightInformation:
 *  (c)2006,digiBlitz Technologies Inc/digiBlitz Technologies (P) Ltd. All rights reserved.
 *  916 W. Broad Street Suite 205, FallsChurch, VA 22046.
 *  This document is protected by copyright. No part of this document may be reproduced in any form by any means without
 *  prior written authorization of Sun and its licensors. if any.
 *  The information described in this document may be protected by one or more U.S.patents.foreign patents,or
 *  pending applications.
 */

package com.mrm.action;

import com.hlcaccounts.session.HLCMahanadhiSessionRemote;
import com.hlcaccounts.session.HLCMahanadhiSessionRemoteHome;
import com.hlcaccounts.util.HLCAccTransactionVO;
import com.hlcaccounts.util.HLCAccTxnTypeDetailVO;
import com.hlcform.stateless.HLCkaverystatelessRemote;
import com.hlcform.stateless.HLCkaverystatelessRemoteHome;
import com.hlcform.util.HLCMemberHistoryDetail;
import com.hlccommon.util.*;
import com.hlckavery.statful.HLCKaverySessionBeanStatfulRemote;
import com.hlckavery.statful.HLCKaverySessionBeanStatfulRemoteHome;
import com.mrm.actionform.FormHorseUpgrade;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.*;
import javax.servlet.http.HttpSession;
import com.hlcpayment.HLCPaymentSessionRemote;
import com.hlcpayment.HLCPaymentSessionRemoteHome;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import com.hlccommon.util.HLCHorseRegisterationVO;
//import com.hlcform.util.*;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import com.util.email.EmailContent;
import com.util.email.EmailEngine;
import java.util.Date;
import java.text.*;
import org.apache.struts.util.MessageResources;

public class ActionHorseUpgrade extends Action {
    
    private String ccName;
    private String ccType;
    private String ccNumber;
    private String ccExpMonth;
    private String ccExpYear;
    private String ccCvvid;
    private String bankName;
    private String nameCheck;
    private String checkDate;
    private String checkNumber;
    private String totalAmount;
    private String insertProcess;
    private String paymentDate;
    private String paymentStatus;
    private String r11;
    
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try{
            
            HttpSession session=request.getSession();
            MessageResources mr=getResources(request);
            
            String staff_user_id= (String) session.getAttribute("staff_user_id");
            
            String namingfactory=mr.getMessage("ejbclient.namingfactory");
            String contextfactory=mr.getMessage("ejbclient.contextfactory");
            String urlprovider=mr.getMessage("ejbclient.urlprovider");
            String lookupip=mr.getMessage("ejbclient.ip");
            String jndiname=mr.getMessage("jndi.kavery");
            
            System.setProperty(namingfactory,contextfactory);
            System.setProperty(urlprovider,lookupip);
            Context jndiContext = new InitialContext();
            
            Object objPayss=jndiContext.lookup("ejb/HLCPaymentSessionBean");
            
//Payment Bean
            HLCPaymentSessionRemoteHome objPaySessHome = (HLCPaymentSessionRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(objPayss,HLCPaymentSessionRemoteHome.class);
            HLCPaymentSessionRemote objPaySessRemote = objPaySessHome.create();
            
            
            Object objref = jndiContext.lookup(jndiname);
            
//For User Name
            HLCKaverySessionBeanStatfulRemoteHome home = (HLCKaverySessionBeanStatfulRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(objref,HLCKaverySessionBeanStatfulRemoteHome.class);
            HLCKaverySessionBeanStatfulRemote remote = home.create();
            
/// Transaction
            String mahanadhiJndi=mr.getMessage("jndi.acc");
            Debug.print("mahanadhiJndi  :" +mahanadhiJndi);
            Object maha=jndiContext.lookup(mahanadhiJndi);
            HLCMahanadhiSessionRemoteHome mahaHome = (HLCMahanadhiSessionRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(maha,HLCMahanadhiSessionRemoteHome.class);
            HLCMahanadhiSessionRemote mahaRemote = mahaHome.create();
            
// History Table entries
            String jname=mr.getMessage("jndi.usrreg");
            Object oef = jndiContext.lookup(jname);
            
            HLCkaverystatelessRemoteHome userhome = (HLCkaverystatelessRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(oef,HLCkaverystatelessRemoteHome.class);
            HLCkaverystatelessRemote userremote = userhome.create();
            
            //Object objref = jndiContext.lookup(jndiname);
//Payment VO
            
            HLCPaymentDetailVO payDetVO = new HLCPaymentDetailVO();
            FormHorseUpgrade fbean=(FormHorseUpgrade)form;
            System.out.println("FormHorseUpgrade ="+ fbean);
            String paymentId= null;
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            String process = request.getParameter("process");
            Debug.print("Process in Action Upgrade "+process);
            
            String userId = (String)session.getAttribute("userId");
            String reqIp=request.getRemoteAddr();
            Debug.print(" Request IP :"+reqIp);
            
            if(process==null || process.trim().length()==0){
                return mapping.findForward("login");
            }

            if(process!=null && process.equalsIgnoreCase("updatestatus")){
                String serviceType = "";
                String amountSect = request.getParameter("amountSect");
                String regstrBy = request.getParameter("regstrBy");
                String membertype = request.getParameter("membertype");
                
                String ownRelName = request.getParameter("relTypeName");
                String ownFName = request.getParameter("ownFirstName");
                String ownLName = request.getParameter("ownLastName");
                String upType = request.getParameter("feeDisp");
                Pattern pat=Pattern.compile("#");
                String str[]=pat.split(upType);
                String upgradedType=str[1];
                Debug.print("upgradedType : "+upgradedType);
                Debug.print("amountSect is "+amountSect);
                Debug.print("regstrBy is "+regstrBy);
                String membTypeName = "";
                if(amountSect!=null){
                    if(amountSect.trim().equalsIgnoreCase("no")){
                        Debug.print("Inside Yes Section");
                        float feeAmt = 0.0f;
                        float payAmt = 0.0f;
                        float reduceAmt = 0.0f;
                        float totalPay = 0.0f;
                        
                        String fee_txt = request.getParameter("fee_txt");
                        String amtPay = request.getParameter("currMemAmt");
                        Debug.print("fee_txt "+fee_txt);
                        Debug.print("amtPay "+amtPay);
                        String horseMemberId = request.getParameter("horseMemberId");
                        String horseName = request.getParameter("horseName");
                        
                        Debug.print("horseMemberId is "+horseMemberId);
                        Debug.print("horseName is "+horseName);
                        
                        String currMemAmt = request.getParameter("currMemAmt");
                        if(fee_txt!=null && fee_txt.trim().length()!=0){
                            feeAmt = Float.parseFloat(fee_txt);
                        }
                        if(amtPay!=null && amtPay.trim().length()!=0){
                            payAmt = Float.parseFloat(amtPay);
                        }
                        if(currMemAmt!=null && currMemAmt.trim().length()!=0){
                            reduceAmt = Float.parseFloat(currMemAmt);
                        }
                        String PaymentId = request.getParameter("parentPaymentId");
                        Debug.print("PaymentId is "+PaymentId);
                        totalPay = feeAmt - payAmt;
                        
                        Debug.print("totalPay is "+totalPay);
                        
                        if(totalPay == 0 && PaymentId!=null){
                            String prevMemTypeId = request.getParameter("prevMemTypeId");
                            String feeDisp = request.getParameter("feeDisp");
                            String[] splitfeeDisp = feeDisp.split("#");
                            String newTypeId = splitfeeDisp[0];
                            membTypeName = splitfeeDisp[1];
                            
                            Debug.print("feeDisp is "+feeDisp);
                            Debug.print("prevMemTypeId is "+prevMemTypeId);
                            Debug.print("newTypeId is "+newTypeId);
                            
                            String phoneCharge=request.getParameter("phoneCharge");
                            String serviceStatus = request.getParameter("servicePrice");
                            
                            if(phoneCharge!=null && phoneCharge.trim().length()!=0){
                                if(serviceStatus!=null){
                                    serviceType = serviceStatus;
                                    session.setAttribute("phoneCharge",serviceType);
                                } else{
                                    serviceType = null;
                                }
                            }
                            String fee_txt1 = request.getParameter("fee_txt");
                            Debug.print("fee_txt1 is "+fee_txt1);
                            boolean upgradeFEHYEH = remote.upgrade_FEH_YEH_Horse(horseMemberId, newTypeId, PaymentId,serviceType,fee_txt1);
                            Debug.print("upgradeFEHYEH is "+upgradeFEHYEH);
                            
                            if(prevMemTypeId!=null && prevMemTypeId.trim().length()!=0){
                                HLCAccTxnTypeDetailVO PaccTxnDet = mahaRemote.getMemberAccTxnTypeDetails(prevMemTypeId);
                                HLCAccTransactionVO prevhorseMem = new HLCAccTransactionVO();
                                
                                String PaccNo =  PaccTxnDet.getAccount_no();
                                String PclassName = PaccTxnDet.getClass_name();
                                String PitemNo = PaccTxnDet.getItem_no();
                                String PsubAccNo = PaccTxnDet.getSub_account_no();
                                String PtransName = PaccTxnDet.getTransaction_name();
                                
                                String currMemAmt1 = request.getParameter("membAmount");
                                Debug.print("currMemAmt1 is "+currMemAmt1);
                                
                                if(currMemAmt1!=null && currMemAmt1.trim().length()!=0){
                                    currMemAmt1 ="-"+currMemAmt1;
                                }
                                prevhorseMem.setAmount(Float.parseFloat(currMemAmt1));
                                
                                ///prevhorseMem.setPayment_mode("check");
                                prevhorseMem.setActive_status(true);
                                PtransName = PtransName+" Upgrade";
                                prevhorseMem.setDescription(PtransName);
                                prevhorseMem.setClass_Typ(PclassName);
                                prevhorseMem.setAccount_no(PaccNo);
                                prevhorseMem.setAccount_type("Income");
                                prevhorseMem.setItem_no(PitemNo);
                                prevhorseMem.setSub_account_no(PsubAccNo);
                                prevhorseMem.setPayment_id(PaymentId);
                                
                                String logBy="user";
                                // Setting staff user_id and ip_address
                                if(session.getAttribute("loggedBy")!=null){
                                    String loggedBy="";
                                    loggedBy=(String)session.getAttribute("loggedBy");
                                    logBy=loggedBy;
                                    prevhorseMem.setStaff_user_id(staff_user_id);
                                    prevhorseMem.setStaff_ip_address(reqIp);
                                }
                                //End Setting staff_user_id and ip_address
                                
                                boolean Prev_ins_status = mahaRemote.insertAccountTxnDetails(prevhorseMem);
                                Debug.print("Insert Status "+Prev_ins_status);
                            }
                            Debug.print("Inside Transaction New Type Id");
                            if(newTypeId!=null && newTypeId.trim().length()!=0){
                                HLCAccTxnTypeDetailVO PaccTxnDet = mahaRemote.getMemberAccTxnTypeDetails(newTypeId);
                                HLCAccTransactionVO horseMem = new HLCAccTransactionVO();
                                
                                String PaccNo =  PaccTxnDet.getAccount_no();
                                String PclassName = PaccTxnDet.getClass_name();
                                String PitemNo = PaccTxnDet.getItem_no();
                                String PsubAccNo = PaccTxnDet.getSub_account_no();
                                String PtransName = PaccTxnDet.getTransaction_name();
                                
                                horseMem.setAmount(feeAmt);
                                
                                ///horseMem.setPayment_mode("check");
                                horseMem.setActive_status(true);
                                
                                PtransName = PtransName+" Upgrade";
                                
                                horseMem.setDescription(PtransName);
                                horseMem.setClass_Typ(PclassName);
                                horseMem.setAccount_no(PaccNo);
                                horseMem.setAccount_type("Income");
                                horseMem.setItem_no(PitemNo);
                                horseMem.setSub_account_no(PsubAccNo);
                                horseMem.setPayment_id(PaymentId);
                                
                                String logBy="user";
                                // Setting staff user_id and ip_address
                                if(session.getAttribute("loggedBy")!=null){
                                    String loggedBy="";
                                    loggedBy=(String)session.getAttribute("loggedBy");
                                    logBy=loggedBy;
                                    horseMem.setStaff_user_id(staff_user_id);
                                    horseMem.setStaff_ip_address(reqIp);
                                }
                                //End Setting staff_user_id and ip_address
                                
                                //session.setAttribute("PrevMembTyp",prevhorseMem);
                                boolean ins_status = mahaRemote.insertAccountTxnDetails(horseMem);
                                Debug.print("Insert Status "+ins_status);
                            }
                            Debug.print("membTypeName is "+membTypeName);
                            Debug.print("horseMemberId is "+horseMemberId);
                            Debug.print("horseName is "+horseName);
                            
                            request.setAttribute("membTypeName",membTypeName);
                            request.setAttribute("horseMemberId",horseMemberId);
                            request.setAttribute("horseName",horseName);
                            
                            Debug.print("History Table Entries");
                            HLCMemberHistoryDetail detailVO = new HLCMemberHistoryDetail();
                            
                            java.util.Calendar toDay = java.util.Calendar.getInstance();
                            int newyear = toDay.get(Calendar.YEAR);
                            int new_month = toDay.get(Calendar.MONTH);
                            String membershipName = request.getParameter("membershipName");
                            
                            if(newTypeId!=null || newTypeId.trim().length()!=0){
                                String statid ="";
                                statid=userremote.getStatusIBasedOnStatus("Pending");
                                
                                detailVO.setTo_year(newyear);
                                detailVO.setPayment_id(paymentId);
                                detailVO.setMemberId(horseMemberId);
                                detailVO.setMembership_action("Upgrade");
                                detailVO.setMembership_type_id(newTypeId);
                                detailVO.setMembership_type_name(membershipName);
                                //detailVO.setZip_code();
                                detailVO.setStatus_id(statid);
                                detailVO.setUser_id((String)session.getAttribute("userId"));
                                
                                Debug.print("History Updat Status");
                                
                                boolean historyUpdate = userremote.insertHorseMemberHistoryDetails(detailVO);
                                Debug.print("historyUpdate "+historyUpdate);
                            }
                            
                            
                            
                            
                            return mapping.findForward("success");
                        } else{
                            return mapping.findForward("login");
                        }
                    }
                }
                String parentPaymentId =null;
                String phoneCharge=request.getParameter("phoneCharge");
                String serviceStatus = request.getParameter("servicePrice");
                parentPaymentId = request.getParameter("parentPaymentId");
                Debug.print("init parentPaymentId: " + parentPaymentId);
                
                if(parentPaymentId!=null && parentPaymentId.trim().equalsIgnoreCase("null")){
                    parentPaymentId = null;
                }
                
                String servicePrice ="";
                if(phoneCharge!=null && phoneCharge.trim().length()!=0){
                    if(serviceStatus!=null){
                        serviceType = serviceStatus;
                        session.setAttribute("phoneCharge",serviceType);
                    }
                    
                    else{
                        serviceType = null;
                    }
                    
                }
                
                String sesUserId = (String)session.getAttribute("userId");
                String emailId = (String)session.getAttribute("emailId");
                
                // Get client's IP address
                String addr = request.getRemoteAddr(); // 123.123.123.123
                Debug.print("Port Value:" + addr);
                int  CcExpMonth = 0;
                int CcExpYear = 0;
                int CcCvvid =0;
                double totalAmount =0;
                float checkAmount =0;
                String CcNumber ="0";
                String checkNumber="0";
                Date check_date = null;
                Date comp_date = null;
                String ccType=null;
                String ccName=null;
                String nameoncheck = null;
                String bankname = null;
                String  r11 = fbean.getR11();
                System.out.print("Check Status :" + r11);
                
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
                    payDetVO.setPaymentStatus("card");
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
                    payDetVO.setPaymentStatus("Check");
                    
                }
                
                String tempCheckAmount = request.getParameter("chkBalAmt");
                if(tempCheckAmount!=null && tempCheckAmount.trim().length()!=0){
                    checkAmount = Float.parseFloat(tempCheckAmount);
                } else{
                    checkAmount=0;
                }
                if(fbean.getTotalAmount()==null || fbean.getTotalAmount()==""){
                    totalAmount=0;
                } else{
                    totalAmount = Double.parseDouble(fbean.getTotalAmount());
                }
                paymentId = remote.getNextId();
                bankname =fbean.getBankName();
                nameoncheck =fbean.getNameCheck();
                Debug.print("Payment Id Created In Action Horse Upgrade " +paymentId);
                Debug.print("Session User ID: " +sesUserId);
                
                payDetVO.setUserId(sesUserId);
                payDetVO.setCcName(ccName);
                payDetVO.setCcType(ccType);
                payDetVO.setCcNumber(CcNumber);
                payDetVO.setCcExpMonth(CcExpMonth);
                payDetVO.setCcExpYear(CcExpYear);
                payDetVO.setCcCvvid(CcCvvid);
                payDetVO.setBankName(bankname);
                payDetVO.setCheckName(nameoncheck);
                payDetVO.setCheckDate(check_date);
                payDetVO.setCheckNumber(checkNumber);
                payDetVO.setAmount(totalAmount);
                payDetVO.setPaymentDate(new Date());
                payDetVO.setIpAddress(addr);
                
                Debug.print("paymentId: " + paymentId);
                payDetVO.setPaymentId(paymentId);
//Adding the new payment ID to tblHorseMemberDetails if parentPaymentId is null
                Debug.print("ParentPaymentid: " + parentPaymentId);
                
                if(parentPaymentId!=null && parentPaymentId.trim().length()!=0) {
                    payDetVO.setParentPaymentId(parentPaymentId);
                } else{
                    payDetVO.setParentPaymentId(paymentId);
                }
                
                String horseMemberId = request.getParameter("horseMemberId");
                String totalAmount1 = (String) request.getParameter("totalAmount");
                HLCHorseRegisterationVO horseVo = (HLCHorseRegisterationVO)remote.getHorseDetailsByHorseMemberId(horseMemberId);
                String horseName= horseVo.getCompetitionName();
                horseName= (String) request.getParameter("horseName");
                session.setAttribute("horseName",horseName);
                String horseAmt= horseVo.getAmount();
                String membershipAmount = request.getParameter("fee_txt");
                Debug.print("membershipAmount is "+membershipAmount);
                session.setAttribute("membershipAmount",membershipAmount);
                
                String membAmount = request.getParameter("membAmount");
                Debug.print("membAmount is "+membAmount);
                
                if(r11.equals("card")){
                    
                    System.out.println("Sucessfully Redirect to NoVa:");
                    
                    String expMon = String.valueOf(CcExpMonth);
                    String expYear = String.valueOf(CcExpYear);
                    if(expMon.trim().length()==1) {
                        expMon = "0" + expMon;
                    }
                    //expYear = expYear.substring(2);
                    String expDate = expMon + expYear;
                    
                    String feeDisp = request.getParameter("feeDisp");
                    String[] splitfeeDisp = feeDisp.split("#");
                    String membershipTypeId = splitfeeDisp[0];
                    String membName = splitfeeDisp[1];
                    
                    Debug.print("membName is "+membName);
                    
                    session.setAttribute("UpgrdMembName",membName);
                    //Request Attribute Setting
                    request.setAttribute("expDate",expDate);
                    request.setAttribute("email",emailId);
                    request.setAttribute("cardNo",String.valueOf(CcNumber));
                    request.setAttribute("amount",String.valueOf(totalAmount));
                    request.setAttribute("chkDigit",String.valueOf(CcCvvid));
                    //String membershipTypeId = request.getParameter("membershipTypeId");
                    
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
                                        /*if(r11.equalsIgnoreCase("card")){
                                             PhaccTxnVO.setAmount(0);
                                        }   */
                                
                                PhaccTxnVO.setPayment_id(paymentId);
                                PhaccTxnVO.setAccount_type("Income");
                                PhaccTxnVO.setClass_Typ(accClassname);
                                //PhaccTxnVO.setUser_id(userId);
                                //PhaccTxnVO.setIp_address(reqIp);
                                PhaccTxnVO.setAccount_no(accNo);
                                PhaccTxnVO.setItem_no(accItemNo);
                                PhaccTxnVO.setDescription(accTranName);
                                PhaccTxnVO.setSub_account_no(accAccNo);
                            }
                        }
                    }
                    
                    
                    Debug.print("mahaRemote.insertAccountTxnDetails(phoneVO) for Phone Service");
                    session.setAttribute("PhoneVO",PhaccTxnVO);
                    
                    //// Horse Member type Entry
                    String membtype = request.getParameter("membershipTypeId");
                    if(membershipTypeId!=null || membershipTypeId.trim().length()!=0){
                        
                        
                        HLCAccTxnTypeDetailVO accTxnDet = mahaRemote.getMemberAccTxnTypeDetails(membershipTypeId);
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
                        
                        String fee_txt = request.getParameter("fee_txt");
                        if(fee_txt!=null || fee_txt.trim().length()!=0){
                            horseMem.setAmount(Float.parseFloat(fee_txt));
                        } else{
                            horseMem.setAmount(0);
                        }
                        
                        horseMem.setDescription(transName);
                        horseMem.setClass_Typ(className);
                        horseMem.setAccount_no(accNo);
                        horseMem.setAccount_type("Income");
                        //horseMem.setUser_id(userId);
                        //horseMem.setIp_address(reqIp);
                        horseMem.setItem_no(itemNo);
                        horseMem.setSub_account_no(subAccNo);
                        horseMem.setPayment_id(paymentId);
                        
                        Debug.print("mahaRemote.insertAccountTxnDetails(horseMem) for Phone Service");
                        session.setAttribute("horseMem",horseMem);
                    }
                    
                    //////// Previous Membertype
                    String prevmembtype = request.getParameter("prevMemTypeId");
                    if(prevmembtype!=null || prevmembtype.trim().length()!=0){
                        
                        String currMemAmt = request.getParameter("membAmount");
                        if(currMemAmt!=null || currMemAmt.trim().length()!=0){
                            currMemAmt =request.getParameter("membAmount");
                            currMemAmt ="-"+currMemAmt;
                        }
                        
                        HLCAccTxnTypeDetailVO accTxnDet = mahaRemote.getMemberAccTxnTypeDetails(prevmembtype);
                        HLCAccTransactionVO prevhorseMem = new HLCAccTransactionVO();
                        
                        String accNo =  accTxnDet.getAccount_no();
                        String className = accTxnDet.getClass_name();
                        String itemNo = accTxnDet.getItem_no();
                        String subAccNo = accTxnDet.getSub_account_no();
                        String transName = accTxnDet.getTransaction_name();
                        String transType = accTxnDet.getTransaction_type();
                        
                        if(currMemAmt!=null){
                            prevhorseMem.setAmount(Float.parseFloat(currMemAmt));
                        }
                        
                        if(r11.equalsIgnoreCase("check")){
                            prevhorseMem.setPayment_mode("check");
                            prevhorseMem.setActive_status(false);
                        }
                        if(r11.equalsIgnoreCase("card")){
                            String cardselect = request.getParameter("ccType");
                            prevhorseMem.setPayment_mode(cardselect);
                            prevhorseMem.setActive_status(false);
                        }
                        
                        prevhorseMem.setDescription(transName);
                        prevhorseMem.setClass_Typ(className);
                        prevhorseMem.setAccount_no(accNo);
                        prevhorseMem.setAccount_type("Income");
                        //prevhorseMem.setUser_id(userId);
                        //prevhorseMem.setIp_address(reqIp);
                        prevhorseMem.setItem_no(itemNo);
                        prevhorseMem.setSub_account_no(subAccNo);
                        prevhorseMem.setPayment_id(paymentId);
                        
                        session.setAttribute("PrevMembTyp",prevhorseMem);
                        
                    }
                    //End Transaction Entries
                    
                    
                    
                    //Activation Date Start
                    String activationDate = request.getParameter("activeDatVal");
                    if(activationDate!=null && activationDate.trim().length()!=0){
                        session.setAttribute("activationDate",activationDate);
                    } else{
                        session.setAttribute("activationDate",null);
                    }
                    
                    
                    
//End Activation Date
                    session.setAttribute("payVOList",payDetVO);
                    
                    session.setAttribute("horseMemberId",horseMemberId);
                    session.setAttribute("horseName",horseName);
                    request.setAttribute("horseName",horseName);
                    session.setAttribute("totalAmount",totalAmount1);
                    
                    session.setAttribute("membershipTypeId",membershipTypeId);
                    session.setAttribute("serviceType",serviceType);
                    
                    session.setAttribute("paymentId",paymentId);
                    String membershipName = request.getParameter("membershipName");
                    session.setAttribute("membershipName",membershipName);
                    
                    StringBuffer reqURL = request.getRequestURL();
                    int lastIndex = reqURL.lastIndexOf("/") ;
                    String url ="";
                    if(lastIndex!=-1){
                        url = reqURL.substring(0,lastIndex+1);
                    }
                    String tmpNova  = mr.getMessage("upgradehorse.nova");
                    String nova = url + tmpNova;
                    Debug.print("succsssullll from app file nova........::" + nova);
                    Debug.print("succsssullll from app file tmpNova ........::" + tmpNova);
                    
                      String riderFName="";
                            String riderLName="";
                            String OwnerFName="";
                            String OwnerLName="";
                            String OwnerMailId="";
                            String textFull="This horse can enter any level at which it is properly qualified";
                            String textLimit="This horse can enter events up to and including Training level";
                            String membershipName1="";
                             membertype="";
                            ArrayList objOwnerDet1 = new ArrayList();
                        objOwnerDet1 = remote.getUserDetails(horseMemberId);
                        if(objOwnerDet1!=null && objOwnerDet1.size()!=0){
                            Debug.print("objOwnerDet1::: ***** " + objOwnerDet1);
                          Iterator itr1 = objOwnerDet1.iterator();
                          while(itr1.hasNext()) {
                          HLCHorseRegListVO objOwnerInfo1 =(HLCHorseRegListVO)itr1.next();
                            riderFName=objOwnerInfo1.getRiderFirstName();
                            riderLName=objOwnerInfo1.getRiderLastName();                           
          
                          }  
                        }
                        
                         ArrayList objOwnerDet = new ArrayList();
                        objOwnerDet = remote.getOwnerDetails(horseMemberId);
                        if(objOwnerDet!=null && objOwnerDet.size()!=0){
                            Debug.print("objOwnerDet::: ***** " + objOwnerDet);
                          Iterator itr1 = objOwnerDet.iterator();
                          while(itr1.hasNext()) {
                          HLCHorseRegListVO objOwnerInfo1 =(HLCHorseRegListVO)itr1.next();
                           OwnerFName=objOwnerInfo1.getOwnerFirstName();
                           OwnerLName=objOwnerInfo1.getOwnerLastName();
                           OwnerMailId=objOwnerInfo1.getOwnerEmail();
          
                          }  
                        }
                         Debug.print("upgradedType : " + upgradedType);
                         Debug.print("membershipName1 a: " + membershipName1);
                        if(upgradedType!=null && upgradedType.equalsIgnoreCase("Full")){
                        membershipName1 = upgradedType+" "+textFull;   
                         Debug.print("membershipName1 b: "+ membershipName1);
                        }
                        else if(upgradedType!=null && upgradedType.equalsIgnoreCase("Limited")){
                        membershipName1 = upgradedType+" "+textLimit; 
                         Debug.print("membershipName1 c: "+ membershipName1);
                        }
                        else{
                        membershipName1 =upgradedType; 
                        Debug.print("membershipName1 d: "+ membershipName1);
                        }
                    request.setAttribute("nova",nova);
                    session.setAttribute("regstrBy",regstrBy);
                    session.setAttribute("riderFName",riderFName);
                    session.setAttribute("riderLName",riderLName);
                    session.setAttribute("OwnerMailId",OwnerMailId);
                    session.setAttribute("membershipName1",membershipName1);
                    session.setAttribute("membertype",membertype);
                    session.setAttribute("upgradedType",upgradedType);
                    session.setAttribute("ownRelName",ownRelName);
                    session.setAttribute("ownFName",ownFName);
                    session.setAttribute("ownLName",ownLName);
                    String inVoiceId1 = (String) session.getAttribute("inVoiceId1");
                     // activation date is set in session for history record details table and setting it into session.
                  if(request.getParameter("activeDatVal")!=null && request.getParameter("activeDatVal").trim().length()!=0) {
                        session.setAttribute("activation_date",request.getParameter("activeDatVal"));
                     }
                 
                  String inVId = request.getParameter("inVoiceId");
                  int invoiceFromForm= Integer.parseInt(inVId);
                System.out.println("inVId in servlet from request: " + inVId);
                int intVId = 1;
               // if (inVoiceId1.equalsIgnoreCase("0")) {
                //    intVId = 1;
              //  } else {
               //     intVId = 1;
              //  }
                request.setAttribute("AMT", request.getParameter("totalAmount"));
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
                request.setAttribute("EMAIL",emailId);
                session.setAttribute("objPaymentVO", payDetVO);

                request.setAttribute("purpose", "upgradehorse");
                //intVId++; 
                invoiceFromForm++;
                session.setAttribute("incrementalInvoice",inVId);
                request.setAttribute("intVId",inVId);
                //request.setAttribute("intVId", String.valueOf(intVId));
                System.out.println("intVId in servlet InsertNon-HumanRegAction &&&&&&&" + intVId);

                return mapping.findForward("testing");
                    //return mapping.findForward("novaPage");
                    
                    //return mapping.findForward("frmUpgradeHorseSuccess");
                } else{
                    Debug.print("This is from check");
                    session.setAttribute("horseMemberId",horseMemberId);
                    
                    String feeDisp = request.getParameter("feeDisp");
                    String[] splitfeeDisp = feeDisp.split("#");
                    String newTypeId = splitfeeDisp[0];
                    membTypeName = splitfeeDisp[1];
                    
                    Debug.print("%%%%%%%%%%%%%Upgraded Membership Type: "+newTypeId);
                    Debug.print("%%%%%%%%%%%%%Upgraded Membership Type: "+membTypeName);
                    
                    String membershipTypeId = newTypeId;
                    
                    boolean updBal=false;
                    boolean statusChange = false;
                    boolean activeStatus = false;
                    boolean activateDateStatus = false;
                    boolean result1  = objPaySessRemote.createPayment(payDetVO);
                    
                    String tempPaymentId = null;
                   /* if(parentPaymentId!=null && parentPaymentId.trim().length()!=0) {
                       tempPaymentId = parentPaymentId;
                    }
                    else{
                        tempPaymentId = paymentId;
                    }*/
                    //fee_txt
                    String fee_txt = request.getParameter("fee_txt");
                    Debug.print("fee_txt is "+fee_txt);
                    
                    boolean res = remote.upgradeHorse(horseMemberId, membershipTypeId, paymentId, serviceType, fee_txt, totalAmount1);
                    String sessionUser = (String)session.getAttribute("loggedBy");
                    
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
                                    Debug.print("Amount Set as NULL ");
                                    PhaccTxnVO.setAmount(0);
                                }
                                
                                PhaccTxnVO.setSub_account_no(accAccNo);
                                PhaccTxnVO.setPayment_id(paymentId);
                                PhaccTxnVO.setAccount_type("Income");
                                PhaccTxnVO.setClass_Typ(accClassname);
                                //PhaccTxnVO.setUser_id(userId);
                                //PhaccTxnVO.setIp_address(reqIp);
                                PhaccTxnVO.setAccount_no(accNo);
                                PhaccTxnVO.setItem_no(accItemNo);
                                PhaccTxnVO.setDescription(accTranName);
                            }
                        }
                    }
                    
                    if(r11.equals("check")){
                        if(PhaccTxnVO!=null){
                            PhaccTxnVO.setStaff_user_id(staff_user_id);
                            PhaccTxnVO.setStaff_ip_address(reqIp);
                            boolean phone_status = mahaRemote.insertAccountTxnDetails(PhaccTxnVO);
                            Debug.print("mahaRemote.insertAccountTxnDetails(phoneVO) for Phone Service :"+phone_status);
                        }
                    } else{
                        Debug.print("mahaRemote.insertAccountTxnDetails(phoneVO) for Phone Service");
                        session.setAttribute("PhoneVO",PhaccTxnVO);
                    }
                    
//// Horse Member type Entry
                    //String membtype = request.getParameter("membershipTypeId");
                    //if(membtype!=null || membtype.trim().length()!=0){
                    if(membershipTypeId!=null && membershipTypeId.trim().length()!=0){
                        HLCAccTxnTypeDetailVO accTxnDet = mahaRemote.getMemberAccTxnTypeDetails(membershipTypeId);
                        HLCAccTransactionVO horseMem = new HLCAccTransactionVO();
                        //String fee_txt = request.getParameter("fee_txt");
                        if(fee_txt!=null || fee_txt.trim().length()!=0){
                            horseMem.setAmount(Float.parseFloat(fee_txt));
                        } else{
                            horseMem.setAmount(0);
                        }
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
                        
                        horseMem.setClass_Typ(className);
                        horseMem.setAccount_no(accNo);
                        horseMem.setAccount_type("Income");
                        horseMem.setItem_no(itemNo);
                        horseMem.setSub_account_no(subAccNo);
                        horseMem.setPayment_id(paymentId);
                        horseMem.setDescription(transName);
                        
                        // Setting staff user_id and ip_address
                        if(session.getAttribute("loggedBy")!=null) {
                            String loggedBy="";
                            loggedBy=(String)session.getAttribute("loggedBy");
                            logBy=loggedBy;
                            
                            horseMem.setStaff_user_id(staff_user_id);
                            horseMem.setStaff_ip_address(reqIp);
                        }
                        //End Setting staff_user_id and ip_address
                        
                        boolean insert_status = mahaRemote.insertAccountTxnDetails(horseMem);
                        Debug.print("Insert Status "+insert_status);
                        
                    }
                    //////// Previous Membertype
                    String prevmembtype = request.getParameter("prevMemTypeId");
                    if(prevmembtype!=null || prevmembtype.trim().length()!=0){
                        
                        String membAmount1 = request.getParameter("membAmount");
                        Debug.print("membAmount: "+membAmount);
                        Debug.print("membAmount1: "+membAmount1);
                        if(membAmount1!=null || membAmount1.trim().length()!=0){
                            membAmount1 =request.getParameter("membAmount");
                            membAmount1 ="-"+membAmount1;
                        }
                        
                        HLCAccTxnTypeDetailVO PaccTxnDet = mahaRemote.getMemberAccTxnTypeDetails(prevmembtype);
                        HLCAccTransactionVO prevhorseMem = new HLCAccTransactionVO();
                        
                        String PaccNo =  PaccTxnDet.getAccount_no();
                        String PclassName = PaccTxnDet.getClass_name();
                        String PitemNo = PaccTxnDet.getItem_no();
                        String PsubAccNo = PaccTxnDet.getSub_account_no();
                        String PtransName = PaccTxnDet.getTransaction_name();
                        String PtransType = PaccTxnDet.getTransaction_type();
                        
                        PtransType = PtransType+" Upgrade";
                        
                        if(membAmount!=null){
                            prevhorseMem.setAmount(Float.parseFloat(membAmount1));
                        }
                        
                        if(r11.equalsIgnoreCase("check")){
                            prevhorseMem.setPayment_mode("check");
                            prevhorseMem.setActive_status(false);
                        }
                        if(r11.equalsIgnoreCase("card")){
                            String cardselect = request.getParameter("ccType");
                            prevhorseMem.setPayment_mode(cardselect);
                            prevhorseMem.setActive_status(false);
                        }
                        
                        prevhorseMem.setDescription(PtransName);
                        prevhorseMem.setClass_Typ(PclassName);
                        prevhorseMem.setAccount_no(PaccNo);
                        prevhorseMem.setAccount_type("Income");
                        //prevhorseMem.setUser_id(userId);
                        //prevhorseMem.setIp_address(reqIp);
                        prevhorseMem.setItem_no(PitemNo);
                        prevhorseMem.setSub_account_no(PsubAccNo);
                        prevhorseMem.setPayment_id(paymentId);
                        
                        // Setting staff user_id and ip_address
                        if(session.getAttribute("loggedBy")!=null) {
                            String loggedBy="";
                            loggedBy=(String)session.getAttribute("loggedBy");
                            logBy=loggedBy;
                            
                            
                            prevhorseMem.setStaff_user_id(staff_user_id);
                            prevhorseMem.setStaff_ip_address(reqIp);
                            
                        }
                        //End Setting staff_user_id and ip_address
                        
                        //session.setAttribute("PrevMembTyp",prevhorseMem);
                        boolean Prev_ins_status = mahaRemote.insertAccountTxnDetails(prevhorseMem);
                        Debug.print("Insert Status "+Prev_ins_status);
                    }
//End Transaction Entries
                    // Activating Status
                    double glblChkAmt =0.0;
                    double glbltotAmt =0.0;
                    String totAmount1 = request.getParameter("totalAmount");
                    if(totAmount1.trim().length()!=0){
                        double totAmt = Double.parseDouble(totAmount1);
                        glbltotAmt = totAmt;
                    }
                    if(session.getAttribute("loggedBy")!=null) {
                        String loggedBy="";
                        loggedBy=(String)session.getAttribute("loggedBy");
                        logBy=loggedBy;
                        
                        // Making the active Status and reconcile status as true on full payment
                        
                        //Setting the reconcile & active Status TRUE
                        String chckAmount = request.getParameter("chkBalAmt");
                        String totAmount = request.getParameter("totalAmount");
                        Debug.print("Check Amount is in Admin Pay "+chckAmount);
                        Debug.print("Total Amount is in Admin Pay "+totAmount);
                        
                        if(chckAmount!=null && totAmount!=null){
                            if(chckAmount.trim().length()!=0 && totAmount.trim().length()!=0){
                                double chkAmt = Double.parseDouble(chckAmount);
                                double totAmt = Double.parseDouble(totAmount);
                                
                                glblChkAmt = chkAmt;
                                
                                Debug.print("Check Amount is in Admin Pay "+chkAmt);
                                Debug.print("Total Amount is in Admin Pay "+totAmt);
                                double extraAmt = checkAmount - totalAmount;
                                Debug.print("Extra Amount Through Admin Pay "+extraAmt);
                                if(extraAmt > 0){
                                    String StrextraAmt = String.valueOf(extraAmt);
                                    HLCAccTxnTypeDetailVO overpayDet = mahaRemote.getOverPayAccTransactionTypeDetail();
                                    HLCAccTransactionVO overpayfinalDet = new HLCAccTransactionVO();
                                    
                                    String acctNo = overpayDet.getAccount_no();
                                    String accClassname = overpayDet.getClass_name();
                                    String accItemNo = overpayDet.getItem_no();
                                    String accAccNo = overpayDet.getSub_account_no();
                                    String accTranName = overpayDet.getTransaction_name();
                                    String accTyped = overpayDet.getTransaction_type();
                                    String accTypeId = overpayDet.getTransaction_type_id();
                                    String description = overpayDet.getTransaction_name();
                                    Debug.print("While getting"+ overpayDet.toString());
                                    
                                    overpayfinalDet.setAccount_no(acctNo);
                                    overpayfinalDet.setAccount_type("Income");
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
                                    
                                    // Setting staff user_id and ip_address
                                    overpayfinalDet.setStaff_user_id(staff_user_id);
                                    overpayfinalDet.setStaff_ip_address(reqIp);
                                    //End Setting staff_user_id and ip_address
                                    
                                    Debug.print("B4 insert "+ overpayfinalDet.toString());
                                    boolean extraPay = mahaRemote.insertAccountTxnDetails(overpayfinalDet);
                                }
                                
                                if(chkAmt>=totAmt){
                                    if(paymentId!=null || paymentId.trim().length()!=0){
                                        boolean Update_Status = mahaRemote.updateRecouncilActiveStatusAccTxnDetails(paymentId);
                                        Debug.print("Update Status "+Update_Status);
                                    }
                                }
                            }
                        }
                        // End Of Status change
                        
                    }
                    
                    Debug.print("History Table Entries");
                    HLCMemberHistoryDetail detailVO = new HLCMemberHistoryDetail();
                    
                    java.util.Calendar toDay = java.util.Calendar.getInstance();
                    int newyear = toDay.get(Calendar.YEAR);
                    int new_month = toDay.get(Calendar.MONTH);
                    String membershipName = request.getParameter("membershipName");
                    
                    if(membershipTypeId!=null || membershipTypeId.trim().length()!=0){
                        String statid ="";
                        
                        if(glbltotAmt<=glblChkAmt) {
                            statid=userremote.getStatusIBasedOnStatus("Active");
                        } else{
                            statid=userremote.getStatusIBasedOnStatus("Pending");
                        }
                        
                        Debug.print("New Horse Member Id Generated is "+res);
                        Debug.print("New Payment Id Generated is "+paymentId);
                        Debug.print("Member Type Id is "+membershipTypeId);
                        Debug.print("New Member Type Name is "+membershipName);
                        
                        detailVO.setTo_year(newyear);
                        detailVO.setPayment_id(paymentId);
                        detailVO.setMemberId(horseMemberId);
                        detailVO.setMembership_action("Upgrade");
                        detailVO.setMembership_type_id(membershipTypeId);
                        detailVO.setMembership_type_name(membershipName);
                        //detailVO.setZip_code();
                        detailVO.setStatus_id(statid);
                        detailVO.setUser_id(sesUserId);
                        
                        Debug.print("History Updat Status");
                        
                        boolean historyUpdate = userremote.insertHorseMemberHistoryDetails(detailVO);
                        Debug.print("historyUpdate "+historyUpdate);
                    }
                    //// End History Details
                    
                    //Active Date Setting
/*                    String activationDate = request.getParameter("activeDatVal");
                    Date checkActDate =null;
                    if(activationDate!=null && activationDate.trim().length()!=0){
                        checkActDate =  sdf.parse(activationDate);
                    }
                    activateDateStatus = remote.updateRequestStatus(horseMemberId,"","Upgrade Horse Registration",checkActDate);*/
                    //End Active Date Ending
                    
                    if(sessionUser !=null && (sessionUser.equalsIgnoreCase("Admin") || sessionUser.equalsIgnoreCase("Usea Staff"))){
                        if(checkAmount!=0){
                            updBal = remote.updatePendingAmount(sesUserId,paymentId,checkAmount);
                            request.setAttribute("PaidAmt",String.valueOf(checkAmount));
                            if(checkAmount>=totalAmount){
                                statusChange = remote.statusChangeForHorse(horseMemberId,null);
                                activeStatus = remote.updateRealtionshipStatus(horseMemberId);
                                //activateDateStatus = remote.updateRequestStatus(horseMemberId,"","Upgrade Horse Registration",checkActDate);
                                request.setAttribute("activeLevel","Active");
                            } else{
                                request.setAttribute("activeLevel","null");
                            }
                        } else{
                            request.setAttribute("PaidAmt",totalAmount1);
                        }
                    }
                    
                    
                    request.setAttribute("horseMemberId",horseMemberId);
                    request.setAttribute("amount",totalAmount1);
                    request.setAttribute("horseName",horseName);
                    
                    request.setAttribute("registrationLevel","FULL STATUS");
                    
                    session.setAttribute("registrationLevel","FULL STATUS");
                    
                    Debug.print("Upgrade result is "+res);
                    if(res==true){
                        Debug.print("payDetVO is "+payDetVO.toString());
                        
                        if(result1 == true && res==true){ 
                            
                            String riderFName="";
                            String riderLName="";
                            String OwnerFName="";
                            String OwnerLName="";
                            String OwnerMailId="";
                            String textFull="This horse can enter any level at which it is properly qualified";
                            String textLimit="This horse can enter events up to and including Training level";
                            String membershipName1="";
                            
                         ArrayList objOwnerDet1 = new ArrayList();
                        objOwnerDet1 = remote.getUserDetails(horseMemberId);
                        if(objOwnerDet1!=null && objOwnerDet1.size()!=0){
                            Debug.print("objOwnerDet1::: ***** " + objOwnerDet1);
                          Iterator itr1 = objOwnerDet1.iterator();
                          while(itr1.hasNext()) {
                          HLCHorseRegListVO objOwnerInfo1 =(HLCHorseRegListVO)itr1.next();
                            riderFName=objOwnerInfo1.getRiderFirstName();
                            riderLName=objOwnerInfo1.getRiderLastName();                           
          
                          }  
                        }
                        
                         ArrayList objOwnerDet = new ArrayList();
                        objOwnerDet = remote.getOwnerDetails(horseMemberId);
                        if(objOwnerDet!=null && objOwnerDet.size()!=0){
                            Debug.print("objOwnerDet::: ***** " + objOwnerDet);
                          Iterator itr1 = objOwnerDet.iterator();
                          while(itr1.hasNext()) {
                          HLCHorseRegListVO objOwnerInfo1 =(HLCHorseRegListVO)itr1.next();
                           OwnerFName=objOwnerInfo1.getOwnerFirstName();
                           OwnerLName=objOwnerInfo1.getOwnerLastName();
                           OwnerMailId=objOwnerInfo1.getOwnerEmail();
                            Debug.print("OwnerMailId in upgrade horse section "+OwnerMailId);
          
                          }  
                        }
                        
                        if(upgradedType!=null && upgradedType.equalsIgnoreCase("Full")){
                        membershipName1 = upgradedType+" "+textFull;      
                        }
                        else if(upgradedType!=null && upgradedType.equalsIgnoreCase("Limited")){
                        membershipName1 = upgradedType+" "+textLimit;      
                        }
                        else{
                         membershipName1 =upgradedType;  
                        }
                        if(OwnerMailId!=null && OwnerMailId.length()!=0){
                          Debug.print("OwnerMailId in upgrade horse section &&&& "+OwnerMailId);
                            String toMailIds[] = {OwnerMailId};
                            EmailContent email=new EmailContent();
                            email.setTo(toMailIds);
                            email.setFrom("anandv@digiblitz.com");
                            email.setSubject("Horse Member Upgrade");
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
                                "<p>Thank you for upgrading "+horseName+" to "+upgradedType+" status.<p>"+
                                "<p>Please retain the following information for your records:<p>"+
                                "Horse Name: "+ horseName +"<br />"+
                                "Horse Registration ID:"+ horseMemberId+"<br />"+
                                "Horse Registration level:"+membershipName1+"This is a lifetime registration.<br/>"+
                                "Owner(s):"+ regstrBy+"<br/>"+
                                "Rider(s):"+ riderFName+" "+riderLName+"<br/>"+                               
                                "<p>Additional information regarding "+ horseName +" can be viewed by logging in to the HLC Online Services.<p>"+ 
                                "<p>Thank you.<p><br />"+
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
                            request.setAttribute("membTypeName",membTypeName);
                            
                            
                            return mapping.findForward("SuccessFrm");
                        } else{
                            Debug.print("Sorry unable to insert due to some bugs");
                        }
                    } else{
                        return mapping.findForward("error");
                    }
                }
            }
//---------------------------------Payment Section Ends--------------------------------------------
        } catch(Exception  ex) {
            System.err.println("Caught an exception.");
            ex.printStackTrace();
        }
        return null;
    }
}
