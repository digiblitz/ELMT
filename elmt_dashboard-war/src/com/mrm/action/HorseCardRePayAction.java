/*
 * HorseCardRePayAction.java
 *
 * Created on February 13, 2007, 5:01 PM
 */
package com.mrm.action;

import com.hlcaccounts.session.HLCMahanadhiSessionRemote;
import com.hlcaccounts.session.HLCMahanadhiSessionRemoteHome;
import com.hlcaccounts.util.HLCAccTransactionVO;
import com.hlcaccounts.util.HLCAccTxnTypeDetailVO;
import com.hlccommon.util.Debug;
import com.hlccommon.util.HLCPaymentDetailVO;
import com.hlcreg.util.HLCPaymentVO;
import com.hlckavery.statful.HLCKaverySessionBeanStatfulRemote;
import com.hlckavery.statful.HLCKaverySessionBeanStatfulRemoteHome;
import com.mrm.actionform.FormHorseUpgrade;
import com.hlcpayment.HLCPaymentSessionRemote;
import com.hlcpayment.HLCPaymentSessionRemoteHome;
import com.util.email.EmailContent;
import com.util.email.EmailEngine;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.util.MessageResources;

/**
 *
 * @author hari
 * @version
 */
public class HorseCardRePayAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try {
            String act = (String) request.getParameter("act");
            HttpSession session = request.getSession();
            MessageResources mr = getResources(request);
            String jndiname = mr.getMessage("jndi.kavery");
            String jname = mr.getMessage("jndi.usrreg");
            Context jndiContext = new InitialContext();
            Object objref = jndiContext.lookup(jndiname);

            HLCKaverySessionBeanStatfulRemoteHome home = (HLCKaverySessionBeanStatfulRemoteHome) javax.rmi.PortableRemoteObject.narrow(objref, HLCKaverySessionBeanStatfulRemoteHome.class);
            HLCKaverySessionBeanStatfulRemote remote = home.create();


            HLCPaymentVO objPayment = new HLCPaymentVO();
            HLCPaymentDetailVO payDetVO = new HLCPaymentDetailVO();
            HLCAccTransactionVO horseMem = new HLCAccTransactionVO();
               HLCAccTransactionVO prevhorseMem = new HLCAccTransactionVO();

            String jndihorse = mr.getMessage("jndi.kavery");
            Object objPayss = jndiContext.lookup("ejb/HLCPaymentSessionBean");
            Object objhorse = jndiContext.lookup(jndihorse);


///Transaction Entry
            String mahanadhiJndi = mr.getMessage("jndi.acc");
            Debug.print("mahanadhiJndi  :" + mahanadhiJndi);
            Object maha = jndiContext.lookup(mahanadhiJndi);
            HLCMahanadhiSessionRemoteHome mahaHome = (HLCMahanadhiSessionRemoteHome) javax.rmi.PortableRemoteObject.narrow(maha, HLCMahanadhiSessionRemoteHome.class);
            HLCMahanadhiSessionRemote mahaRemote = mahaHome.create();

            HLCPaymentSessionRemoteHome objPaySessHome = (HLCPaymentSessionRemoteHome) javax.rmi.PortableRemoteObject.narrow(objPayss, HLCPaymentSessionRemoteHome.class);
            HLCPaymentSessionRemote objPaySessRemote = objPaySessHome.create();
            Object oef = jndiContext.lookup(jname);
            String sesUserId = (String) session.getAttribute("userId");

            if (act.equalsIgnoreCase("repay")) {
                Debug.print("Inside Repay of HorseCardRePayAction");
                String horseName = (String) request.getParameter("horseName");
                String horseMemberId = (String) request.getParameter("horseMemberId");
                if (horseMemberId == null || horseMemberId.trim().length() == 0) {
                    horseMemberId = (String) request.getAttribute("horseMemberId");
                }
                String registrationLevel = (String) request.getParameter("registrationLevel");
                String totalAmount = (String) request.getParameter("totalAmount");
                String paymentId = (String) request.getParameter("paymentId");

                request.setAttribute("horseName", horseName);
                request.setAttribute("horseMemberId", horseMemberId);
                request.setAttribute("registrationLevel", registrationLevel);
                request.setAttribute("totalAmount", totalAmount);


                session.setAttribute("horseName", horseName);
                session.setAttribute("horseMemberId", horseMemberId);
                session.setAttribute("registrationLevel", registrationLevel);
                session.setAttribute("totalAmount", totalAmount);

                session.setAttribute("paymentId", paymentId);
                request.setAttribute("paymentId", paymentId);

                Debug.print("horseName" + horseName);
                Debug.print("horseMemberId" + horseMemberId);
                Debug.print("registrationLevel" + registrationLevel);
                Debug.print("totalAmount" + totalAmount);
                /*            boolean pri_rider =false;
                boolean pri_owner = false;
                pri_owner = remote.isHorsePriOwnerExist(horseMemberId,sesUserId);
                pri_rider = remote.isHorsePriRiderExist(horseMemberId,sesUserId);
                Debug.print("Primary Owner is Avilable "+pri_owner);
                Debug.print("Primary Rider is Avilable "+pri_rider);
                if(pri_owner ==false && pri_rider==false){
                Debug.print("Sorry u r not able to pay the pending amount:" );
                return mapping.findForward("UserDuePayMessage");
                }
                else{*/
                return mapping.findForward("repay");
            // }
            }

            if (act.equalsIgnoreCase("payment")) {
                Debug.print("Inside the payment option");


                sesUserId = (String) session.getAttribute("userId");
                String emailId = (String) session.getAttribute("emailId");
                FormHorseUpgrade fbean = (FormHorseUpgrade) form;
                System.out.println("FormHorseUpgrade =" + fbean);
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

                String paymentId = request.getParameter("paymentId");
                String registrationLevel = request.getParameter("usrStat");
                String horseName = request.getParameter("horseName");
                String horseMemberId = request.getParameter("horseMemberId");
                String totalAmount1 = request.getParameter("totalAmount");
                String tempAmount = (String) request.getParameter("tempAmount");

                Debug.print("tempAmount" + tempAmount);
                Debug.print("horseName" + horseName);
                Debug.print("horseMemberId" + horseMemberId);
                Debug.print("registrationLevel" + registrationLevel);
                Debug.print("totalAmount" + totalAmount1);

                String memName1 = request.getParameter("membershipName1");
                String OwnFName = request.getParameter("OwnerFName");
                String OwnLName = request.getParameter("OwnerLName");
                String ridFName = request.getParameter("riderFName");
                String ridLName = request.getParameter("riderLName");
                String ridEmailId = request.getParameter("riderEmailId");
                String OwnMailId = request.getParameter("OwnerMailId");
                Debug.print("ridEmailId :" + ridEmailId);
                Debug.print("ridEmailId :" + ridEmailId);

                request.setAttribute("tempAmount", tempAmount);

                // Get client's IP address
                String addr = request.getRemoteAddr(); // 123.123.123.123
                Debug.print("Port Value:" + addr);

                int CcExpMonth = 0;
                int CcExpYear = 0;
                int CcCvvid = 0;
                double totalAmount = 0;
                String CcNumber = "0";
                String checkNumber = "0";
                Date check_date = null;
                Date comp_date = null;
                String ccType = null;
                String ccName = null;
                String nameoncheck = null;
                String bankname = null;
                float checkAmount = 0;
                String r11 = fbean.getR11();
                System.out.print("Check Status :" + r11);

                if (r11.equals("card")) {
                    if (fbean.getCcNumber() == null || fbean.getCcNumber().equals("")) {
                        CcNumber = "0";
                    } else {
                        CcNumber = fbean.getCcNumber();
                    }
                    if (fbean.getCcName() == null || fbean.getCcName().equals("")) {
                        ccName = "";
                    } else {
                        ccName = fbean.getCcName();
                    }
                    if (!(fbean.getCcType().equals("")) && fbean.getCcType() != null) {
                        ccType = fbean.getCcType();
                    }
                    if (fbean.getCcExpMonth() == null || fbean.getCcExpMonth().equals("")) {
                        CcExpMonth = 0;
                    } else {
                        CcExpMonth = Integer.parseInt(fbean.getCcExpMonth());
                    }
                    if (fbean.getCcExpYear() == null || fbean.getCcExpYear().equals("")) {
                        CcExpYear = 0;
                    } else {
                        CcExpYear = Integer.parseInt(fbean.getCcExpYear());
                    }
                    if (fbean.getCcCvvid() == null || fbean.getCcCvvid().equals("")) {
                        CcCvvid = 0;
                    } else {
                        CcCvvid = Integer.parseInt(fbean.getCcCvvid());
                    }
                    check_date = null;
                    payDetVO.setPaymentStatus("Card");
                }

                if (r11.equals("check")) {
                    if (fbean.getCheckNumber() == null || fbean.getCheckNumber().equals("")) {
                        checkNumber = "0";
                    } else {
                        checkNumber = fbean.getCheckNumber();
                    }
                    if (fbean.getCheckDate().equals("")) {
                        check_date = null;
                    } else {
                        check_date = (Date) sdf.parse(fbean.getCheckDate());
                    }
                    tempAmount = fbean.getTotalAmount();
                    Debug.print("Inside check " + tempAmount);
                    payDetVO.setPaymentStatus("Check");

                }
                tempAmount = request.getParameter("tempAmount");
                System.out.println("tempAmount1" + tempAmount);
                if (tempAmount == null || tempAmount == "") {
                    totalAmount = 0;
                } else {
                    totalAmount = Double.parseDouble(tempAmount);
                }

                System.out.println("tempAmount1" + tempAmount);
                bankname = fbean.getBankName();
                nameoncheck = fbean.getNameCheck();
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
                //objPayment.getCheckAmount();
                payDetVO.setAmount(totalAmount);
                payDetVO.setPaymentDate(new Date());
                payDetVO.setPaymentId(paymentId);
                //IP Address
                payDetVO.setIpAddress(addr);
                boolean updBal = false;
                String tempCheckAmount = request.getParameter("chkBalAmt");
                if (tempCheckAmount != null && tempCheckAmount.trim().length() != 0) {
                    checkAmount = Float.parseFloat(tempCheckAmount);
                } else {
                    checkAmount = 0;
                }
                if (r11.equals("card")) {
                    System.out.println("Sucessfully Redirect to NoVa:");
                    session.setAttribute("objPaymentList", payDetVO);
                    //System.out.println("Sucessfully Payment objPaymentList:" + objPayment.toString());
                    request.setAttribute("email", emailId);
                    request.setAttribute("cardNo", String.valueOf(CcNumber));
                    String expMon = String.valueOf(CcExpMonth);
                    String expYear = String.valueOf(CcExpYear);
                    if (expMon.trim().length() == 1) {
                        expMon = "0" + expMon;
                    }
                    System.out.println("expYear:" + expYear);
                    //expYear = expYear.substring(2);
                    System.out.println("expMon:" + expMon);
                    System.out.println("expYear:" + expYear);
                    String expDate = expMon + expYear;
                    request.setAttribute("expDate", expDate);
                    request.setAttribute("amount", String.valueOf(totalAmount));
                    request.setAttribute("chkDigit", String.valueOf(CcCvvid));
                    session.setAttribute("payVOList", payDetVO);
                    String res = "";

                    boolean update_stat = remote.updatePaymentStatus(objPayment);
                    Debug.print("Update Status " + update_stat);

                    session.setAttribute("amount", String.valueOf(totalAmount));
                    session.setAttribute("paymentId", paymentId);
                    session.setAttribute("horseName", horseName);
                    session.setAttribute("horseMemberId", horseMemberId);
                    session.setAttribute("registrationLevel", registrationLevel);

                    session.setAttribute("totalAmount", totalAmount1);
                    //Activation Date                    
                    String activationDate = request.getParameter("activeDatVal");
                    System.out.print("activationDate" + activationDate);
                    if (activationDate != null && activationDate.trim().length() != 0) {
                        session.setAttribute("activationDate", activationDate);
                    } else {
                        session.setAttribute("activationDate", null);
                    }
                    // End Activation Date
                    StringBuffer reqURL = request.getRequestURL();
                    int lastIndex = reqURL.lastIndexOf("/");
                    String url = "";
                    if (lastIndex != -1) {
                        url = reqURL.substring(0, lastIndex + 1);
                    }
                    String tmpNova = mr.getMessage("horserepay.nova");
                    String nova = url + tmpNova;
                    //Debug.print("succsssullll from app file nova........::" + nova);
                    //Debug.print("succsssullll from app file tmpNova ........::" + tmpNova);
                    //request.setAttribute("nova", nova);

                  String inVId = request.getParameter("inVoiceId");
             request.setAttribute("AMT",String.valueOf(totalAmount));
             request.setAttribute("PAYMENTACTION", "Authorization");
             request.setAttribute("CREDITCARDTYPE",request.getParameter("ccType"));
             request.setAttribute("ACCT",String.valueOf(payDetVO.getCcNumber()));
             request.setAttribute("EXPDATE",expDate);
             request.setAttribute("CVVNo",(String)request.getParameter("ccCvvid"));
             request.setAttribute("IPADDRESS", addr);
             request.setAttribute("FIRSTNAME", request.getParameter("ccName"));
             request.setAttribute("STREET", "1 Main St");
             request.setAttribute("CITY", "San Jose");
             request.setAttribute("STATE", "CA");
             request.setAttribute("ZIP", "95131");
             request.setAttribute("COUNTRYCODE", "US");
             request.setAttribute("EMAIL",emailId);
             request.setAttribute("intVId",inVId);
             
             System.out.println("invoiceid in the retry servlet..........."+inVId);
             session.setAttribute("sessionInvoiceId","1");
             String purposeOfPayment=(String)session.getAttribute("purpose");
             if(purposeOfPayment.equalsIgnoreCase("newhorseregistration"))
             {
             request.setAttribute("purpose","newhorseregistration");  
             }
             else if(purposeOfPayment.equalsIgnoreCase("upgradehorse"))
             {
                 request.setAttribute("purpose","upgradehorse"); 
             }
             else
             {
                 request.setAttribute("purpose","notregisteredhorse");
             }
             session.setAttribute("objPaymentVO",payDetVO);  
             prevhorseMem=(HLCAccTransactionVO)session.getAttribute("PrevMembTyp");
             if(prevhorseMem!=null)
             {
             prevhorseMem.setPayment_mode(request.getParameter("ccType"));
             session.setAttribute("PrevMembTyp",prevhorseMem);
             }
              horseMem =(HLCAccTransactionVO)session.getAttribute("horseMem");
               horseMem.setPayment_mode(request.getParameter("ccType"));
                
                session.setAttribute("horseMem",horseMem);
                session.setAttribute("memName1",memName1);
                session.setAttribute("OwnFName",OwnFName);
                session.setAttribute("OwnLName",OwnLName);
                session.setAttribute("ridFName",ridFName);
                session.setAttribute("ridLName",ridLName);
                session.setAttribute("ridEmailId",ridEmailId);
                session.setAttribute("OwnMailId",OwnMailId);
                
               // return mapping.findForward("novaPage");

                return mapping.findForward("testing");
              

                //return mapping.findForward("frmHorseSuccessRedir");
                } else {

                    Debug.print("paymentId in check:" + paymentId);
                    Debug.print("horseRegVO.setRegisteredBy(sesUserId);" + sesUserId);
                    Debug.print("amount:" + totalAmount);
                    Debug.print("horseRegVO.setRegisteredBy(sesUserId)");
                    //  String amt = (String) session.getAttribute("totalAmount");
                    request.setAttribute("amount", totalAmount1);
                    if ((checkAmount == 0) || (String.valueOf(checkAmount).trim().length() == 0)) {
                        request.setAttribute("PaidAmt", totalAmount1);
                    } else {
                        request.setAttribute("PaidAmt", String.valueOf(checkAmount));
                    }
                    request.setAttribute("CheckNo", objPayment.getCheckNumber());
                    request.setAttribute("Chkdte", objPayment.getCheckDate());
                    request.setAttribute("banknme", objPayment.getBankName());
                    request.setAttribute("horseName", horseName);
                    request.setAttribute("horseMemberId", horseMemberId);

                    String sessionUser = (String) session.getAttribute("loggedBy");
                    Debug.print("sessionUser" + sessionUser);
                    if (sessionUser != null && (sessionUser.equalsIgnoreCase("Admin") || sessionUser.equalsIgnoreCase("Usea Staff"))) {
                        String activationDate = request.getParameter("activeDatVal");
                        Date checkActDate = null;
                        if (activationDate != null && activationDate.trim().length() != 0) {
                            checkActDate = sdf.parse(activationDate);
                            Debug.print("checkActDate:" + checkActDate);
                        }
                        boolean statusChange = false;
                        boolean activeStatus = false;
                        boolean activateDateStatus = false;
                        if (checkAmount != 0) {
                            updBal = remote.updatePendingAmount(sesUserId, paymentId, checkAmount);
                            request.setAttribute("PaidAmt", String.valueOf(checkAmount));
                            if (checkAmount >= totalAmount) {
                                Debug.print("inside the condition totAmt > checkAmt:");
                                Debug.print("Check Amount is in Admin Pay " + checkAmount);
                                Debug.print("Total Amount is in Admin Pay " + totalAmount);
                                double extraAmt = checkAmount - totalAmount;
                                Debug.print("Extra Amount Through Admin Pay " + extraAmt);
                                if (extraAmt > 0) {
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
                                    Debug.print("While getting" + overpayDet.toString());

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

                                    String pay_mode = request.getParameter("r11");

                                    if (pay_mode != null && pay_mode.trim().length() > 0) {
                                        if (pay_mode.equalsIgnoreCase("check")) {
                                            overpayfinalDet.setPayment_mode("check");
                                            overpayfinalDet.setActive_status(false);
                                        }
                                    }
                                    Debug.print("B4 insert " + overpayfinalDet.toString());
                                    boolean extraPay = mahaRemote.insertAccountTxnDetails(overpayfinalDet);
                                }

                                statusChange = remote.statusChangeForHorse(horseMemberId, null);
                                activeStatus = remote.updateRealtionshipStatus(horseMemberId);
                                activateDateStatus = remote.updateRequestStatus(horseMemberId, "", "Upgrade Horse Registration", checkActDate);
                                if (paymentId != null || paymentId.trim().length() != 0) {
                                    boolean Update_Status = mahaRemote.updateRecouncilActiveStatusAccTxnDetails(paymentId);
                                    Debug.print("Update Status " + Update_Status);
                                }

                                request.setAttribute("activeLevel", "Active");
                            } else {
                                request.setAttribute("activeLevel", "null");
                            }
                        } else {
                            request.setAttribute("PaidAmt", totalAmount1);
                        }
                    }
                    String activationDate = (String) session.getAttribute("activationDate");
                    System.out.print("activationDate" + activationDate);
                    Date checkActDate = null;
                    if (activationDate != null && activationDate.trim().length() != 0) {
                        checkActDate = sdf.parse(activationDate);
                        Debug.print("checkActDate:" + checkActDate);
                    } else {
                        checkActDate = new Date();
                    }

                    boolean update_stat = remote.updatePaymentStatus(objPayment);
                    Debug.print("Update Status " + update_stat);

                    boolean horseStatus = remote.isHorseExist(horseName);
                    if (horseStatus == true) {
                        String toMailIds[] = {emailId};
                        EmailContent email = new EmailContent();
                        email.setTo(toMailIds);
                        email.setFrom("anandv@digiblitz.com");
                        email.setSubject("Horse Member Matching");
                        String content = "<table width=\"526\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"border:1px solid #999;\"> " +
                                " <tr>" +
                                " <td height=\"463\" valign=\"top\" bgcolor=\"#FCFBF0\" style=\"padding-top:10px; padding-bottom:10px; padding-left:10px; padding-right:10px;\">" +
                                " <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> " +
                                "<tr>" +
                                "<td height=\"70\" valign=\"top\"><img src=\"images/emailHeader.jpg\" alt=\"HLC Online Services Dashboard Email Template\" width=\"515\" height=\"55\" /></td> " +
                                " </tr>" +
                                "  <tr>" +
                                "<td valign=\"top\">" +
                                "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">" +
                                "<tr>" +
                                "<td width=\"13\" height=\"12\" valign=\"top\"><img src=\"images/cornerTopLeft.jpg\" width=\"13\" height=\"12\" /></td> " +
                                "<td valign=\"top\" bgcolor=\"#FBF2F2\"></td>" +
                                "<td width=\"13\" height=\"12\" valign=\"top\"><img src=\"images/cornerTopRght.jpg\" width=\"13\" height=\"12\" /></td>" +
                                "</tr>" +
                                "<tr>" +
                                "<td valign=\"top\" background=\"images/left.jpg\">&nbsp;</td>" +
                                "<td valign=\"top\" bgcolor=\"#FBF2F2\">" +
                                "<span class=\"boldTxt\">Dear User</span>,<br /><br />" +
                                "<p>Your horse may already be registered.<p>" +
                                "<p>In an effort to reduce the number of duplicate horse registrations, the HLC " +
                                "would like to inform you that the horse name <strong>" + horseName + " </strong>is already in use in our database." +
                                "Please verify that your horse <strong>" + horseName + "</strong> has not be previously registered by a former owner or rider." +
                                "Please log in to the HLC Online Services, and access Horse Registration. Please search for the horse by name to determine if " +
                                "<strong>" + horseName + "</strong> has been registered before. If your horse has been registered in the past, please " +
                                "contact the HLC Sports Services Department at 703-779-0440.<p>" +
                                "<p>If you have confirmed that your horse has not been registered with the HLC previously, no further action is needed.<p><br />" +
                                "Thank You <br />" +
                                "<p>Sincerely," +
                                "<p>HLC Staff <p></td>" +
                                "<td valign=\"top\" background=\"images/Rght.jpg\">&nbsp;</td>" +
                                "</tr>" +
                                "<tr><td valign=\"top\" background=\"images/cornerBotLeft.jpg\">&nbsp;</td>" +
                                "<td valign=\"top\" background=\"images/cornerBot.jpg\">&nbsp;</td>" +
                                "<td valign=\"top\" background=\"images/cornerBotRght.jpg\">&nbsp;</td>" +
                                "</tr>" +
                                " </table>" +
                                "</td></tr>" +
                                "</table>";
                        email.setBody(content);
                        EmailEngine emailEngine = new EmailEngine();
                        boolean emailFlag = emailEngine.sendMimeEmail(email);
                        Debug.print("Email sent sucessfully :" + emailFlag);
                    }
                    if (OwnMailId != null && OwnMailId.length() != 0) {
                        Debug.print("OwnerMailId::: inside if condition" + OwnMailId);
                        String toMailIds2[] = {OwnMailId};
                        EmailContent email2 = new EmailContent();
                        email2.setTo(toMailIds2);
                        email2.setFrom("anandv@digiblitz.com");
                        email2.setSubject("Horse Member Registration");
                        String content2 = "<table width=\"526\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"border:1px solid #999;\"> " +
                                " <tr>" +
                                " <td height=\"463\" valign=\"top\" bgcolor=\"#FCFBF0\" style=\"padding-top:10px; padding-bottom:10px; padding-left:10px; padding-right:10px;\">" +
                                " <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> " +
                                "<tr>" +
                                "<td height=\"70\" valign=\"top\"><img src=\"images/emailHeader.jpg\" alt=\"HLC Online Services Dashboard Email Template\" width=\"515\" height=\"55\" /></td> " +
                                " </tr>" +
                                "  <tr>" +
                                "<td valign=\"top\">" +
                                "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">" +
                                "<tr>" +
                                "<td width=\"13\" height=\"12\" valign=\"top\"><img src=\"images/cornerTopLeft.jpg\" width=\"13\" height=\"12\" /></td> " +
                                "<td valign=\"top\" bgcolor=\"#FBF2F2\"></td>" +
                                "<td width=\"13\" height=\"12\" valign=\"top\"><img src=\"images/cornerTopRght.jpg\" width=\"13\" height=\"12\" /></td>" +
                                "</tr>" +
                                "<tr>" +
                                "<td valign=\"top\" background=\"images/left.jpg\">&nbsp;</td>" +
                                "<td valign=\"top\" bgcolor=\"#FBF2F2\">" +
                                "<span class=\"boldTxt\">Dear Member,</span><br />" +
                                "<p>Thank you for registering your horse <strong>" + horseName + " </strong> with the HLC.<p>" +
                                "<p>Please retain the following information for your records:<p>" +
                                "Horse Name: " + horseName + "<br />" +
                                "Horse Registration ID:" + horseMemberId + "<br />" +
                                "Horse Registration level:" + memName1 + ".This is a lifetime registration.<br/>" +
                                "Owner(s):" + OwnFName + " " + OwnLName + "<br/>" +
                                "Rider(s):" + ridFName + " " + ridLName + "<br/>" +
                                "<p>Additional information regarding " + horseName + " can be viewed by logging in to the HLC Online Services.<p>" +
                                "<p>Thank you.<p><br />" +
                                "<p>Sincerely, <br />" +
                                "<p>HLC Staff <p>" +
                                "</tr>" +
                                " </table>" +
                                "</td></tr>" +
                                "</table>";
                        email2.setBody(content2);
                        EmailEngine emailEngine2 = new EmailEngine();
                        boolean emailFlag2 = emailEngine2.sendMimeEmail(email2);
                        Debug.print("Email sent sucessfully :" + emailFlag2);
                    }

                    if (ridEmailId != null && ridEmailId.length() != 0) {
                        Debug.print("riderEmailId::: inside if condition" + ridEmailId);
                        String toMailIds3[] = {ridEmailId};
                        EmailContent email3 = new EmailContent();
                        email3.setTo(toMailIds3);
                        email3.setFrom("anandv@digiblitz.com");
                        email3.setSubject("Horse Member Registration");
                        String content3 = "<table width=\"526\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"border:1px solid #999;\"> " +
                                " <tr>" +
                                " <td height=\"463\" valign=\"top\" bgcolor=\"#FCFBF0\" style=\"padding-top:10px; padding-bottom:10px; padding-left:10px; padding-right:10px;\">" +
                                " <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> " +
                                "<tr>" +
                                "<td height=\"70\" valign=\"top\"><img src=\"images/emailHeader.jpg\" alt=\"HLC Online Services Dashboard Email Template\" width=\"515\" height=\"55\" /></td> " +
                                " </tr>" +
                                "  <tr>" +
                                "<td valign=\"top\">" +
                                "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">" +
                                "<tr>" +
                                "<td width=\"13\" height=\"12\" valign=\"top\"><img src=\"images/cornerTopLeft.jpg\" width=\"13\" height=\"12\" /></td> " +
                                "<td valign=\"top\" bgcolor=\"#FBF2F2\"></td>" +
                                "<td width=\"13\" height=\"12\" valign=\"top\"><img src=\"images/cornerTopRght.jpg\" width=\"13\" height=\"12\" /></td>" +
                                "</tr>" +
                                "<tr>" +
                                "<td valign=\"top\" background=\"images/left.jpg\">&nbsp;</td>" +
                                "<td valign=\"top\" bgcolor=\"#FBF2F2\">" +
                                "<span class=\"boldTxt\">Dear Member,</span><br />" +
                                "<p>You have been listed as the rider for <strong>" + horseName + " </strong>, recently registered with the HLC.<p>" +
                                "<p>Please retain the following information for your records:<p>" +
                                "Horse Name: " + horseName + "<br />" +
                                "Horse Registration ID:" + horseMemberId + "<br />" +
                                "Horse Registration level:" + memName1 + ".This is a lifetime registration.<br/>" +
                                "Owner(s):" + OwnFName + " " + OwnLName + "<br/>" +
                                "Rider(s):" + ridFName + " " + ridLName + "<br/>" +
                                "<p>Additional information regarding " + horseName + " can be viewed by logging in to the HLC Online Services. Thank you.<p>" +
                                "<p>Sincerely, <br />" +
                                "<p>HLC Staff <p>" +
                                "</tr>" +
                                " </table>" +
                                "</td></tr>" +
                                "</table>";
                        email3.setBody(content3);
                        EmailEngine emailEngine3 = new EmailEngine();
                        boolean emailFlag3 = emailEngine3.sendMimeEmail(email3);
                        Debug.print("Email sent sucessfully :" + emailFlag3);
                    }
                    return mapping.findForward("backtoList");
                /*  }
                else{
                }*/
                }
            }
        } catch (Exception ex) {
            System.err.println("Caught an exception.");
            ex.printStackTrace();
        }
        Debug.print("Out of Block");
        return null;
    }
}
