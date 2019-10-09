/*
 * HumanMemberPaymentAction.java
 *
 * Created on October 12, 2006, 8:10 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package com.mrm.action;

import com.hlcaccounts.session.HLCMahanadhiSessionRemote;
import com.hlcaccounts.session.HLCMahanadhiSessionRemoteHome;
import com.hlcaccounts.util.HLCAccTransactionVO;
import com.hlccommon.util.HLCPaymentDetailVO;
import com.hlcform.stateless.HLCkaverystatelessRemote;
import com.hlcform.stateless.HLCkaverystatelessRemoteHome;
import com.hlcform.util.HLCMemberDetails;
import com.hlcform.util.HLCMemberHistoryDetail;
import com.hlccommon.util.Debug;
import com.hlccommon.util.HLCPaymentResultVO;
import com.hlcmember.type.stateless.HLCKaveryMembershipTypeSessionRemote;
import com.hlcmember.type.stateless.HLCKaveryMembershipTypeSessionRemoteHome;
import com.util.email.EmailContent;
import com.util.email.EmailEngine;
import com.hlcpayment.HLCPaymentSessionRemote;
import com.hlcpayment.HLCPaymentSessionRemoteHome;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.util.MessageResources;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import com.hlcform.util.HLCPaymentDetails;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.*;
import com.hlcform.util.HLCUserMaster;
import com.hlcform.util.HLCContactDetails;
import com.hlcrole.management.*;

/**
 *
 * @author karthikeyan
 */
public class HumanMemberPaymentAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String fwd = "";
        String amount1 = "";
        String paymentIds = "";
        Date act_date_card = new Date();

        HLCkaverystatelessRemote remote2 = null;
        //String parentId=null;
        HLCMemberDetails objMember = new HLCMemberDetails();
        String newMembId = "";
        try {
            HttpSession session = request.getSession();
            MessageResources mr = getResources(request);
            String namingfactory = mr.getMessage("ejbclient.namingfactory");
            String contextfactory = mr.getMessage("ejbclient.contextfactory");
            String urlprovider = mr.getMessage("ejbclient.urlprovider");
            String lookupip = mr.getMessage("ejbclient.ip");
            String jndimemb = mr.getMessage("jndi.usrreg");

            String jndiname2 = mr.getMessage("jndi.rolemanagement");
            String defrole = mr.getMessage("default.role");
            Debug.print("defrole :" + defrole);

            System.setProperty(namingfactory, contextfactory);
            System.setProperty(urlprovider, lookupip);
            Context jndiContext = new InitialContext();

            Object objPayss = jndiContext.lookup("ejb/HLCPaymentSessionBean");
            HLCPaymentSessionRemoteHome objPaySessHome = (HLCPaymentSessionRemoteHome) javax.rmi.PortableRemoteObject.narrow(objPayss, HLCPaymentSessionRemoteHome.class);
            HLCPaymentSessionRemote objPaySessRemote = objPaySessHome.create();

            Object objrefMemb = jndiContext.lookup(jndimemb);
            HLCkaverystatelessRemoteHome home2 = (HLCkaverystatelessRemoteHome) javax.rmi.PortableRemoteObject.narrow(objrefMemb, HLCkaverystatelessRemoteHome.class);
            remote2 = home2.create();

            String mahanadhiJndi = mr.getMessage("jndi.acc");
            Debug.print("mahanadhiJndi  :" + mahanadhiJndi);
            Object maha = jndiContext.lookup(mahanadhiJndi);
            HLCMahanadhiSessionRemoteHome mahaHome = (HLCMahanadhiSessionRemoteHome) javax.rmi.PortableRemoteObject.narrow(maha, HLCMahanadhiSessionRemoteHome.class);
            HLCMahanadhiSessionRemote mahaRemote = mahaHome.create();

            String name1 = "ejb/HLCKaveryMembershipTypeJNDI";
            Object obj = jndiContext.lookup(name1);
            HLCKaveryMembershipTypeSessionRemoteHome memberHome = (HLCKaveryMembershipTypeSessionRemoteHome) javax.rmi.PortableRemoteObject.narrow(obj, HLCKaveryMembershipTypeSessionRemoteHome.class);
            HLCKaveryMembershipTypeSessionRemote memberRemote = memberHome.create();

            ArrayList historyTable = new ArrayList();

            HLCPaymentDetails objPayment = (HLCPaymentDetails) session.getAttribute("objPayment");
            String userId = (String) session.getAttribute("userId");
            paymentIds = objPayment.getPaymentId();

            Debug.print("Return from Nova UserId:" + (String) session.getAttribute("userId"));
            Debug.print("Return from Nova UserCode:" + (String) session.getAttribute("userCode"));
            Debug.print("Return from Nova memeberId:" + (String) session.getAttribute("memberId"));

            Debug.print("Return from Nova:");
            HLCPaymentResultVO payRes = (HLCPaymentResultVO) session.getAttribute("payRes");
            HLCPaymentDetailVO objPaypal = (HLCPaymentDetailVO) session.getAttribute("objPayDet");
            String[] results = (String[]) session.getAttribute("results");
            String statusId3 = (String) session.getAttribute("statusId3");
            String statusId = (String) session.getAttribute("statusId");
            System.out.println("payRes in card servlet: " + payRes);
            System.out.println("results in card servlet: " + results);
            System.out.println("statusId3 in card servlet: " + statusId3);
            System.out.println("statusId in card servlet: " + statusId);
            
            if (statusId3 == null || statusId3.equalsIgnoreCase("Failure")) {
                String errorCd = payRes.getErrorCd();
                String lngMsg = payRes.getLngMsg();

                String emailId = (String)session.getAttribute("emailId");
                    String toMailIds[] = {emailId};
                EmailContent email = new EmailContent();
                email.setTo(toMailIds);
                email.setFrom("info@digiblitz.com");
                email.setSubject("Member Registration Decline");
                //System.out.println("toMailIds: " + toMailIds);
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
                        "<p>Payment has been declined and the details are as follows:<p>" +
                        "<span class=\"boldTxt\">Declined Details:</span>.</p>" +
                        "TIMESTAMP:" + payRes.getTmStmp() + " <br/>" +
                        "L_ERRORCODE0:" + payRes.getErrorCd() + "<br/>" +
                        "L_LONGMESSAGE0:" + payRes.getLngMsg() + "<br/>" +
                        "AMT:" + payRes.getAmount() + "<br/>" +
                       // "AVSCODE: " + payRes.getSslAvsResponse() + "<br/>" +
                       // "CVV2MATCH:" + payRes.getSslCvv2Response() + "<br/>" +
                        "Corrective action:" + payRes.getFinalCorecAction() + "<br/></p>" +
                        "<span class=\"boldRedTxt\">HLC Team</span></td>" +
                        "<td valign=\"top\" background=\"images/Rght.jpg\">&nbsp;</td>" +
                        "</tr>" +
                        "<tr><td valign=\"top\" background=\"images/cornerBotLeft.jpg\">&nbsp;</td>" +
                        "<td valign=\"top\" background=\"images/cornerBot.jpg\">&nbsp;</td>" +
                        "<td valign=\"top\" background=\"images/cornerBotRght.jpg\">&nbsp;</td>" +
                        "</tr>" +
                        " </table>" +
                        "</td></tr>" +
                        "+<tr>" +
                        "<td valign=\"top\" style=\"padding:10px;\">" +
                        "</td>" +
                        "</tr>" +
                        " <tr>" +
                        "<td style=\"border-top:1px solid #CC3333; padding-top:8px;\" align=\"right\"><img src=\"images/logo-eMailFooter.jpg\" width=\"63\" height=\"65\" /></td>" +
                        "</tr>" +
                        "</table>";

                email.setBody(content);
                EmailEngine emailEngine = new EmailEngine();
                boolean emailFlag = emailEngine.sendMimeEmail(email);
                Debug.print("Email sent sucessfully :" + emailFlag);

                HLCPaymentDetailVO objPay = new HLCPaymentDetailVO();
                
                objPay.setAmount(objPayment.getAmount());
                
                session.setAttribute("objPayDet",objPay);

                request.setAttribute("msg", "Payment Declined");
                request.setAttribute("errorCd", errorCd);
                request.setAttribute("lngMsg", lngMsg);

                return mapping.findForward("decline");
            }
             String sslResult = objPaypal.getSslResult();
                String sslResultMessage = objPaypal.getSslResultMessage();
                String sslTxnId = objPaypal.getSslTxnId();
                String sslApprovalCode = request.getParameter("ssl_approval_code");
                String sslCvv2Response = objPaypal.getSslCvv2Response();
                String sslAvsResponse = objPaypal.getSslAvsResponse();
                String sslTransactionType = objPaypal.getSslTransactionType();
                String sslInvoiceNo = objPaypal.getSslInvoiceNo();
                String sslEmail = objPaypal.getSslEmail();
            
             Debug.print("Sucessfully Return objPaymentList:" + objPayment.toString());
            
            Debug.print("paymentId in nova servlet :"+objPayment.getPaymentId());
            
            act_date_card=(java.util.Date)session.getAttribute("act_date");
            Debug.print("act_date_card from session :"+act_date_card.toString());
            
            //String areaMembTypeId=(String)session.getAttribute("areaMembTypeId");
                
           if(objPayment!=null){
                     
                     amount1=String.valueOf(objPayment.getAmount());
                     
             }
            

            Debug.print("Sucessfully return from Nova sslResult:" + sslResult);
            Debug.print("Sucessfully return from Nova sslResultMessage:" + sslResultMessage);
            Debug.print("Sucessfully return from Nova sslTxnId:" + sslTxnId);
            Debug.print("Sucessfully return from Nova sslApprovalCode:" + sslApprovalCode);
            Debug.print("Sucessfully return from Nova sslCvv2Response:" + sslCvv2Response);
            Debug.print("Sucessfully return from Nova sslAvsResponse:" + sslAvsResponse);
            Debug.print("Sucessfully return from Nova sslTransactionType:" + sslTransactionType);
            Debug.print("Sucessfully return from Nova sslInvoiceNo:" + sslInvoiceNo);
            Debug.print("Sucessfully return from Nova sslEmail:" + sslEmail);

            Debug.print("Sucessfully Return objPaymentList:" + objPayment.toString());

            Debug.print("paymentId in nova servlet :" + objPayment.getPaymentId());

            act_date_card = (java.util.Date) session.getAttribute("act_date");
            Debug.print("act_date_card from session :" + act_date_card.toString());

            if (objPayment != null) {

                amount1 = String.valueOf(objPayment.getAmount());

            }

            if (sslResult.equals("0")) {

                if (objPayment != null) {

                    //amount1=String.valueOf(objPayment.getAmount());

                    HLCPaymentDetailVO objPayDet = new HLCPaymentDetailVO();

                    objPayDet.setPaymentId(objPayment.getPaymentId());
                    objPayDet.setUserId(userId);
                    objPayDet.setIpAddress(objPayment.getIpAddress());
                    objPayDet.setCcName(objPayment.getCcName());
                    objPayDet.setCcType(objPayment.getCcType());
                    //objPayDet.setCcNumber(objPayment.getCcNumber());
                    objPayDet.setCcExpMonth(objPayment.getCcExpMonth());
                    objPayDet.setCcExpYear(objPayment.getCcExpYear());
                    objPayDet.setCcCvvid(objPayment.getCcCvvid());
                    objPayDet.setBankName(objPayment.getBankName());
                    objPayDet.setAmount(objPayment.getAmount());
                    objPayDet.setPaymentStatus(objPayment.getPaymentStatus());
                    objPayDet.setCcNumber(objPayment.getCcNumber());

                    Debug.print("objPayDet.getCcNumber() in payment nova servlet :" + objPayDet.getCcNumber());

                    objPayDet.setSslResult(sslResult);
                    objPayDet.setSslResultMessage(sslResultMessage);
                    objPayDet.setSslTxnId(sslTxnId);
                    objPayDet.setSslApprovalCode(sslApprovalCode);
                    objPayDet.setSslCvv2Response(sslCvv2Response);
                    objPayDet.setSslAvsResponse(sslAvsResponse);
                    objPayDet.setSslTransactionType(sslTransactionType);
                    objPayDet.setSslInvoiceNo(sslInvoiceNo);
                    objPayDet.setSslEmail(sslEmail);
                    objPayDet.setCheckAmount(0);
                    objPayDet.setInVoiceID(objPaypal.getInVoiceID());
                    objPayDet.setPpAuthorizationID(objPaypal.getPpAuthorizationID());
                    objPayDet.setPpCorrelationID(objPaypal.getPpCorrelationID());
                    objPayDet.setPaymentStatus(objPaypal.getPpPaymentStatus());
                    objPayDet.setPpFeeAmt(objPaypal.getPpFeeAmt()) ;
                    objPayDet.setPpPaymentType(objPaypal.getPpPaymentType());
                    objPayDet.setPpReasonCode(objPaypal.getPpReasonCode());
                    objPayDet.setPpParentTransactionID(objPaypal.getPpParentTransactionID());
                    System.out.println("PPAuthorizarion id is"+objPaypal.getPpAuthorizationID());
                    System.out.println("Correlation id is"+objPaypal.getPpCorrelationID());
                    
                    System.out.println("PPAuthorizarion id is"+objPaypal.getPpAuthorizationID());
                    
                    
                      String sessionInvoiceId = (String) session.getAttribute("sessionInvoiceId");
                        boolean idExist = remote2.isInvoiceIdExist(sessionInvoiceId);
                        Debug.print("idExist value is:" + idExist);
                    
                        boolean resultPay = false;
                        if (!idExist) {
                     resultPay = objPaySessRemote.createPayment(objPayDet);
                      Debug.print("Sucessfully Payment Inserted:" + objPayment.toString());
                    session.setAttribute("sessionResultPay", "true");
                        }




                    //Phone Band
                    HLCAccTransactionVO PhoneVO = (HLCAccTransactionVO) session.getAttribute("PhoneVO");
                    if (PhoneVO != null) {
                        PhoneVO.setActive_status(true);
                        boolean status = mahaRemote.insertAccountTxnDetails(PhoneVO);
                        Debug.print("mahaRemote.insertAccountTxnDetails(PhoneVO) for Member addon no" + status);
                    }

                    // Mail Options Entry
                    HLCAccTransactionVO transDetVO = (HLCAccTransactionVO) session.getAttribute("mailOptions");
                    if (transDetVO != null) {
                        transDetVO.setActive_status(true);
                        boolean status = mahaRemote.insertAccountTxnDetails(transDetVO);
                        Debug.print("mahaRemote.insertAccountTxnDetails(transDetVO) for Member addon no" + status);
                    }

                    //Arm Band
                    HLCAccTransactionVO armbandTxnVO = (HLCAccTransactionVO) session.getAttribute("armBand");
                    if (armbandTxnVO != null) {
                        armbandTxnVO.setActive_status(true);
                        boolean status = mahaRemote.insertAccountTxnDetails(armbandTxnVO);
                        Debug.print("mahaRemote.insertAccountTxnDetails(armbandTxnVO) for Member addon no" + status);
                    }

                    //Donation Process
                    String donationCount = (String) session.getAttribute("donCt");
                    int donCount = 0;
                    if (donationCount != null || donationCount.trim().length() != 0) {

                        donCount = Integer.parseInt(donationCount);
                        ArrayList donationTxnVO = (ArrayList) session.getAttribute("donationNo");
                        Iterator itr = donationTxnVO.iterator();
                        while (itr.hasNext()) {
                            HLCAccTransactionVO donaTxnVO = (HLCAccTransactionVO) itr.next();
                            donaTxnVO.setActive_status(true);
                            boolean status = mahaRemote.insertAccountTxnDetails(donaTxnVO);
                            Debug.print("mahaRemote.insertAccountTxnDetails(Donation) for Member addon no" + status);
                        }
                    }

                    //Member Transaction
                    if (session.getAttribute("member") != null) {
                        HLCAccTransactionVO memberTxnVO = (HLCAccTransactionVO) session.getAttribute("member");
                        if (memberTxnVO != null) {
                            memberTxnVO.setActive_status(true);
                            boolean status = mahaRemote.insertAccountTxnDetails(memberTxnVO);
                            Debug.print("mahaRemote.insertAccountTxnDetails(Member) for Member addon no" + status);
                        }
                    }

                    //Family Account
                    String familycount = (String) session.getAttribute("addCount");
                    Debug.print("familycount is " + familycount);
                    int fcount = 0;
                    if (familycount != null || familycount.trim().length() != 0) {
                        fcount = Integer.parseInt(familycount);
                        if (fcount > 0) {
                            ArrayList accTxnVO = (ArrayList) session.getAttribute("familyTxnVO");
                            Debug.print("Size of familyTxnVO is " + accTxnVO.size());
                            Iterator itr = accTxnVO.iterator();
                            while (itr.hasNext()) {
                                HLCAccTransactionVO familyTxnVO = (HLCAccTransactionVO) itr.next();
                                familyTxnVO.setActive_status(true);
                                boolean status = mahaRemote.insertAccountTxnDetails(familyTxnVO);
                                Debug.print("mahaRemote.insertAccountTxnDetails(familyTxnVO) for Member addon no" + status);
                            }
                        }
                    }

                    //Upgrade  Member
                    Debug.print("Above Upgrade section");
                    if (session.getAttribute("renStat") != null) {
                        String renStat = (String) session.getAttribute("renStat");
                        if (renStat.equalsIgnoreCase("upgrade")) {
                            HLCAccTransactionVO upgrdemember = (HLCAccTransactionVO) session.getAttribute("upgrdemember");
                            if (upgrdemember != null) {
                                upgrdemember.setActive_status(true);
                                boolean status = mahaRemote.insertAccountTxnDetails(upgrdemember);
                                Debug.print("mahaRemote.insertAccountTxnDetails(upgrdemember)  " + status);
                            }
                        }
                    }

                    Debug.print("Above renewMemb section");
                    //Renew Member
                    if (session.getAttribute("renewMemb") != null) {
                        HLCAccTransactionVO renewMemb = (HLCAccTransactionVO) session.getAttribute("renewMemb");
                        if (renewMemb != null) {
                            renewMemb.setActive_status(true);
                            boolean status = mahaRemote.insertAccountTxnDetails(renewMemb);
                            Debug.print("mahaRemote.insertAccountTxnDetails(renewMemb)  " + status);
                        }
                    }

                    Debug.print("Above family add on renew");
                    if (session.getAttribute("famIs") != null) {
                        ArrayList famIs = (ArrayList) session.getAttribute("famIs");
                        if (famIs.size() != 0) {
                            for (int i = 0; i < famIs.size(); i++) {
                                String famid = (String) famIs.get(i);
                                boolean update = remote2.updateFutureExpDateByYear(famid);
                                Debug.print("update is " + update);
                            }
                        }
                    }

                    //Renew Family Process  
                    Debug.print("Above Renew Family AddOns section");
                    if (session.getAttribute("cbxct") != null) {
                        if (session.getAttribute("renewFamilyMemb") != null) {
                            String cbxcnt = (String) session.getAttribute("cbxct");
                            int lstCount = 0;
                            if (cbxcnt != null || cbxcnt.trim().length() != 0) {
                                int size = Integer.parseInt(cbxcnt);
                                ArrayList renew_TxnVO = (ArrayList) session.getAttribute("renewFamilyMemb");
                                Iterator itr = renew_TxnVO.iterator();
                                while (itr.hasNext()) {
                                    HLCAccTransactionVO renew_Family = (HLCAccTransactionVO) itr.next();
                                    renew_Family.setActive_status(true);
                                    boolean status = mahaRemote.insertAccountTxnDetails(renew_Family);
                                    Debug.print("mahaRemote.insertAccountTxnDetails(renew_TxnVO) for Member addon no" + status);
                                }
                            }
                        }
                    }

//Setting the reconcile & active Status TRUE
                    if (paymentIds != null || paymentIds.trim().length() != 0) {
                        boolean Update_Status = mahaRemote.updateRecouncilActiveStatusAccTxnDetails(paymentIds);
                        Debug.print("Update Status " + Update_Status);
                    }

                    double payAmt = objPayment.getAmount();
                    String amt = String.valueOf(payAmt);

                    objMember = (HLCMemberDetails) session.getAttribute("objMember");

                    request.setAttribute("membTyp", objMember.getMembershipTypeName());
                    request.setAttribute("amount", amt);

                    if (objMember != null) {
                        if (resultPay == true) {
                        //boolean udate=remote2.editMemberDetail(objMember);
                        //System.out.println("Updation of Member Detail Status :" +udate);


                        }
                    }
                    //String memID = (String)session.getAttribute("memberId");
                    //Debug.print("memID in servlet:"+memID);
                    //String[] userDet=remote2.getUserDetailsForEmail(userId);
                    //String memTypeName=objMember.getMembershipTypeName();
                    if (resultPay == true) {
                        fwd = "confirm";
                    /* String emailId = (String)session.getAttribute("emailId");
                    String toMailIds[] = {emailId};
                    EmailContent email=new EmailContent();
                    email.setTo(toMailIds);
                    email.setFrom("anandv@digiblitz.com");
                    email.setSubject("Your Registration Status");
                    if(memTypeName.equalsIgnoreCase("Full Member") || memTypeName.equalsIgnoreCase("Junior Member") || memTypeName.equalsIgnoreCase("Life Member")){
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
                    "<span class=\"boldTxt\">Dear User</span>,<br />"+
                    "<p>Thank you for your application, your membership has been received and processed</p><br/>"+
                    "<p><strong>HLC Membership Details :</strong><br/><br />"+
                    "Your Membership ID:"+memID+" <br/>"+
                    "Membership Type:"+objMember.getMembershipTypeName()+ "<br/>"+
                    "Expiration Date:"+remote2.getExpiredDate(memID)+ "<br/>"+
                    "First Name:"+userDet[1]+"<br/>"+
                    "Last Name: "+userDet[2]+"<br/>"+
                    "City:"+userDet[3]+ "<br/>"+ 
                    "State:"+userDet[4]+ "<br/></p>"+
                    "<p>Please allow 3-4 weeks for delivery of your membership card packet .</p> <br/>"+
                    "<p><span class=\"asterisk\"><strong>Note:</strong></span> All 2008 medical cards are WHITE and are downloadable from our website on regular paper . Please do not hesitate to contact the HLC Member Service Dept. if we can be of further assistance! </p><br/>"+
                    "<p><strong>HLC Member Service Department</strong></p><br/>"+
                    " </table>"+
                    "</td></tr>"+
                    "</table>";
                    email.setBody(content);
                    }else if(memTypeName.equalsIgnoreCase("Subscribing Member") || memTypeName.equalsIgnoreCase("Non-Competing Member")){
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
                    "<span class=\"boldTxt\">Dear User</span>,<br />"+
                    "<p>Thank you for your application, your membership has been received and processed</p><br/>"+
                    "<p><strong>HLC Membership Details :</strong><br/><br />"+
                    "Your Membership ID:"+memID+" <br/>"+
                    "Membership Type:"+objMember.getMembershipTypeName()+ "<br/>"+
                    "Expiration Date:"+remote2.getExpiredDate(memID)+ "<br/>"+
                    "First Name:"+userDet[1]+"<br/>"+
                    "Last Name: "+userDet[2]+"<br/>"+
                    "City:"+userDet[3]+ "<br/>"+ 
                    "State:"+userDet[4]+ "<br/></p>"+
                    "<p>Your membership packet will arrive within 3-4 weeks.  </p> <br/>"+
                    "<p><span class=\"asterisk\"><strong>Special Note:</strong></span> Subscribers  & Non-Competing members , this membership does not provide competing privileges at any level of competition. </p><br/>"+
                    "<p>Please do not hesitate to contact the HLC Member Service Dept. if we can be of further assistance!</p><br/>"+
                    "<p><strong>HLC Member Service Department</strong></p><br/>"+
                    " </table>"+
                    "</td></tr>"+
                    "</table>";
                    email.setBody(content);   
                    }else if(memTypeName.equalsIgnoreCase("Family Member")){
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
                    "<span class=\"boldTxt\">Dear User</span>,<br />"+
                    "<p>Thank you for your application, your membership has been received and processed</p><br/>"+
                    "<p><strong>HLC Membership Details :</strong><br/><br />"+
                    "Your Membership ID:"+memID+" <br/>"+
                    "Membership Type:"+objMember.getMembershipTypeName()+ "<br/>"+
                    "Expiration Date:"+remote2.getExpiredDate(memID)+ "<br/>"+
                    "First Name:"+userDet[1]+"<br/>"+
                    "Last Name: "+userDet[2]+"<br/>"+
                    "City:"+userDet[3]+ "<br/>"+ 
                    "State:"+userDet[4]+ "<br/></p>"+
                    "<p>Your membership packet will arrive within 3-4 weeks.  </p> <br/>"+
                    "<p><span class=\"asterisk\"><strong>Special Note:</strong></span> This membership level allows you to compete from Beginner novice level thru Training. However, you must upgrade to a full or life member in order to compete at the preliminary level and above.  (Reminder, if youï¿½re moving up to the preliminary level donï¿½t forget to upgrade your horse!.)</p><br/>"+
                    "<p><span class=\"asterisk\"><strong>Note:</strong></span> All 2008 medical cards are WHITE and are downloadable from our website on regular paper .</p></br/>"+
                    "<p>Please do not hesitate to contact the HLC Member Service Dept. if we can be of further assistance!</p></br/>"+
                    "<p><strong>HLC Member Service Department</strong></p><br/>"+
                    " </table>"+
                    "</td></tr>"+
                    "</table>";
                    email.setBody(content);    
                    }
                    //email.setAttachments();
                    EmailEngine emailEngine = new EmailEngine();
                    boolean emailFlag = emailEngine.sendMimeEmail(email);
                    Debug.print("Email sent sucessfully :"+emailFlag);
                    fwd="confirm";*/
                    }
                }
            } else {

                Debug.print("Transaction Declined..:");
                Debug.print("amount1:" + amount1);

                HLCPaymentDetailVO objPayDet = new HLCPaymentDetailVO();

                objPayDet.setPaymentId(objPayment.getPaymentId());
                objPayDet.setUserId(userId);
                objPayDet.setCcName(objPayment.getCcName());
                objPayDet.setCcType(objPayment.getCcType());
                //objPayDet.setCcNumber(objPayment.getCcNumber());
                objPayDet.setCcExpMonth(objPayment.getCcExpMonth());
                objPayDet.setCcExpYear(objPayment.getCcExpYear());
                objPayDet.setCcCvvid(objPayment.getCcCvvid());
                objPayDet.setBankName(objPayment.getBankName());
                objPayDet.setAmount(objPayment.getAmount());
                objPayDet.setPaymentStatus(objPayment.getPaymentStatus());
                objPayDet.setCcNumber(objPayment.getCcNumber());
                objPayDet.setIpAddress(objPayment.getIpAddress());

                Debug.print("objPayDet.getCcNumber() in payment nova servlet :" + objPayDet.getCcNumber());

                objPayDet.setSslResult(sslResult);
                objPayDet.setSslResultMessage(sslResultMessage);
                objPayDet.setSslTxnId(sslTxnId);
                objPayDet.setSslApprovalCode(sslApprovalCode);
                objPayDet.setSslCvv2Response(sslCvv2Response);
                objPayDet.setSslAvsResponse(sslAvsResponse);
                objPayDet.setSslTransactionType(sslTransactionType);
                objPayDet.setSslInvoiceNo(sslInvoiceNo);
                objPayDet.setSslEmail(sslEmail);
                objPayDet.setCheckAmount(0);

                /*
                objPayDet.setPaymentId(objPayment.getPaymentId());
                objPayDet.setUserId(objPayment.getUserId());
                objPayDet.setAmount(objPayment.getAmount());
                objPayDet.setPaymentStatus("card");
                objPayDet.setPaymentDate(new Date());
                objPayDet.setCheckAmount(0);
                 */

                Debug.print("objPayDet.toString() :" + objPayDet.toString());
                boolean resultPay = objPaySessRemote.createPayment(objPayDet);

                Debug.print("Declined Payment Sucessfully Inserted Status :" + resultPay);

                //Phone Band
                HLCAccTransactionVO PhoneVO = (HLCAccTransactionVO) session.getAttribute("PhoneVO");
                if (PhoneVO != null) {
                    PhoneVO.setActive_status(false);
                    boolean status = mahaRemote.insertAccountTxnDetails(PhoneVO);
                    Debug.print("mahaRemote.insertAccountTxnDetails(PhoneVO) for Member addon no" + status);
                }


                //Arm Band
                HLCAccTransactionVO armbandTxnVO = (HLCAccTransactionVO) session.getAttribute("armBand");
                if (armbandTxnVO != null) {
                    armbandTxnVO.setActive_status(false);
                    boolean status = mahaRemote.insertAccountTxnDetails(armbandTxnVO);
                    Debug.print("mahaRemote.insertAccountTxnDetails(armbandTxnVO) for Member addon no" + status);
                }

                //Donation Process
                String donationCount = (String) session.getAttribute("donCt");
                int donCount = 0;
                if (donationCount != null || donationCount.trim().length() != 0) {

                    donCount = Integer.parseInt(donationCount);
                    ArrayList donationTxnVO = (ArrayList) session.getAttribute("donationNo");
                    Iterator itr = donationTxnVO.iterator();
                    while (itr.hasNext()) {
                        HLCAccTransactionVO donaTxnVO = (HLCAccTransactionVO) itr.next();
                        donaTxnVO.setActive_status(false);
                        boolean status = mahaRemote.insertAccountTxnDetails(donaTxnVO);
                        Debug.print("mahaRemote.insertAccountTxnDetails(Donation) for Member addon no" + status);
                    }
                }

                //Member Transaction
                if (session.getAttribute("member") != null) {
                    HLCAccTransactionVO memberTxnVO = (HLCAccTransactionVO) session.getAttribute("member");
                    if (memberTxnVO != null) {
                        memberTxnVO.setActive_status(false);
                        boolean status = mahaRemote.insertAccountTxnDetails(memberTxnVO);
                        Debug.print("mahaRemote.insertAccountTxnDetails(Member) for Member addon no" + status);
                    }
                }

                //Family Account
                String familycount = (String) session.getAttribute("addCount");
                Debug.print("familycount is " + familycount);
                int fcount = 0;
                if (familycount != null || familycount.trim().length() != 0) {
                    fcount = Integer.parseInt(familycount);
                    if (fcount > 0) {
                        ArrayList accTxnVO = (ArrayList) session.getAttribute("familyTxnVO");
                        Debug.print("Size of familyTxnVO is " + accTxnVO.size());
                        Iterator itr = accTxnVO.iterator();
                        while (itr.hasNext()) {
                            HLCAccTransactionVO familyTxnVO = (HLCAccTransactionVO) itr.next();
                            familyTxnVO.setActive_status(false);
                            boolean status = mahaRemote.insertAccountTxnDetails(familyTxnVO);
                            Debug.print("mahaRemote.insertAccountTxnDetails(familyTxnVO) for Member addon no" + status);
                        }
                    }
                }

                //Upgrade  Member

                if (session.getAttribute("renStat") != null) {
                    String renStat = (String) session.getAttribute("renStat");
                    if (renStat.equalsIgnoreCase("upgrade")) {
                        HLCAccTransactionVO upgrdemember = (HLCAccTransactionVO) session.getAttribute("upgrdemember");
                        if (upgrdemember != null) {
                            upgrdemember.setActive_status(false);
                            boolean status = mahaRemote.insertAccountTxnDetails(upgrdemember);
                            Debug.print("mahaRemote.insertAccountTxnDetails(upgrdemember)  " + status);
                        }
                    }
                }

                //Renew Member
                if (session.getAttribute("renewMemb") != null) {
                    HLCAccTransactionVO renewMemb = (HLCAccTransactionVO) session.getAttribute("renewMemb");
                    if (renewMemb != null) {
                        renewMemb.setActive_status(false);
                        boolean status = mahaRemote.insertAccountTxnDetails(renewMemb);
                        Debug.print("mahaRemote.insertAccountTxnDetails(renewMemb)  " + status);
                    }
                }

                //Renew Family Process
                Debug.print("Above Renew Family AddOns section");
                if (session.getAttribute("cbxct") != null) {
                    String cbxcnt = (String) session.getAttribute("cbxct");
                    int lstCount = 0;
                    if (cbxcnt != null || cbxcnt.trim().length() != 0) {
                        int size = Integer.parseInt(cbxcnt);
                        ArrayList renew_TxnVO = (ArrayList) session.getAttribute("renew_fam");
                        if (renew_TxnVO != null && renew_TxnVO.size() > 0) {
                            Iterator itr = renew_TxnVO.iterator();
                            while (itr.hasNext()) {
                                HLCAccTransactionVO renew_Family = (HLCAccTransactionVO) itr.next();
                                renew_Family.setActive_status(false);
                                boolean status = mahaRemote.insertAccountTxnDetails(renew_Family);
                                Debug.print("mahaRemote.insertAccountTxnDetails(renew_TxnVO) for Member addon no" + status);
                            }
                        }
                    }
                }



                request.setAttribute("amount", amount1);
                session.setAttribute("objPayDet", objPayDet);
                //for decline payment
                if (session.getAttribute("loggedBy") == null && Integer.parseInt(sslResult) == 1) {
                    String emailId = (String) session.getAttribute("emailId");
                    String toMailIds[] = {emailId};
                    EmailContent email = new EmailContent();
                    email.setTo(toMailIds);
                    email.setFrom("info@digiblitz.com");
                    email.setSubject("Your Registration Status");
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
                            "<span class=\"boldTxt\">Dear User</span>,<br />" +
                            "<p>We are sorry, but your payment for membership with the HLC has been declined. This registration is not active. Please try another form of payment or contact us at (703) 779-0440 and press 1.</p><br/>" +
                            "<p>Sincerely,</p><br/>" +
                            "<p>HLC Staff</p>" +
                            " </table>" +
                            "</td></tr>" +
                            "</table>";
                    email.setBody(content);
                    EmailEngine emailEngine = new EmailEngine();
                    boolean emailFlag = emailEngine.sendMimeEmail(email);
                    Debug.print("Email sent sucessfully :" + emailFlag);

                }
                fwd = "decline";


            /* roll back method for member details table
            parentId=(String)session.getAttribute("parentId");
            boolean stat=remote2.deleteMemberDetail(parentId);
            Debug.print("Succesfully rolled Back Member Details Status :"+stat);*/

            }
            //======================================== Membership Registration / Renew insert Block ===================================

            Debug.print("===========================================================");
            Debug.print("Membership Registration / Renew insert Block in payment nova for credit card payment ");
            Debug.print("===========================================================");

            Object objref1 = jndiContext.lookup(jndimemb);

            objMember = new HLCMemberDetails();
            objPayment = new HLCPaymentDetails();
            HLCUserMaster objUserMaster = new HLCUserMaster();
            HLCContactDetails objContactDetail = new HLCContactDetails();

            HLCkaverystatelessRemoteHome home = (HLCkaverystatelessRemoteHome) javax.rmi.PortableRemoteObject.narrow(objref1, HLCkaverystatelessRemoteHome.class);
            HLCkaverystatelessRemote remote = home.create();

            Object objref2 = jndiContext.lookup(jndiname2);
            Debug.print("ActionRoleMangement.jndiname:" + jndiname2);

            HLCBrahmaputraSessionRemoteHome roleHome = (HLCBrahmaputraSessionRemoteHome) PortableRemoteObject.narrow(objref2, HLCBrahmaputraSessionRemoteHome.class);
            HLCBrahmaputraSessionRemote roleRemote = roleHome.create();

            userId = (String) session.getAttribute("userId");
            String membId = (String) session.getAttribute("memberId");

            String paymentId = "";
            paymentId = remote.getNextId();
            Debug.print("paymentId in member insert servlet :" + paymentId);

            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyy");
            String donAmt = null;

            String loginName = (String) session.getAttribute("loginName");
            Debug.print("loginName from session :" + loginName);

            objMember.setLoginName(loginName);

            String emailIdSes = (String) session.getAttribute("emailId");
            objMember.setEmailId(emailIdSes);

            objMember.setUserId(userId);
            Debug.print("userId from session :" + userId);


            /*               
             * Block for Deleting Family Add-ons if request is from renewal form
             */
            String result = "";

            String renstat = (String) session.getAttribute("renstat");
            Debug.print("session.getAttribute(renstat) :" + renstat);

            if (renstat != null) {
                System.out.println("inside member renewal block........ ");

                String renStat = (String) session.getAttribute("renStat");
                Debug.print("session.getAttribute(renStat) :" + renStat);

                boolean nonUseaStat = remote.deleteNonUseaPriceDetails(membId);
                Debug.print("remote.deleteNonUseaPriceDetails(membId) for member id :" + membId + " :" + nonUseaStat);

                boolean pubDelStat = remote.deletePublicationDetails(membId);
                Debug.print("remote.deletePublicationDetails(membId) for member id :" + membId + " :" + pubDelStat);

                ArrayList pubDet = new ArrayList();
                pubDet = (ArrayList) session.getAttribute("pubDet");

                if (pubDet != null) {
                    Debug.print("pubDet.size() :" + pubDet.size());

                    for (int i = 0; i < pubDet.size(); i++) {
                        String[] pubId = (String[]) pubDet.get(i);

                        Debug.print("pubId.length :" + pubId.length);

                        boolean pubStat = remote.insertPublicationDetails(pubId[0], pubId[1]);
                        Debug.print("remote.insertPublicationDetails() for member id :" + pubId[0] + " publication id : " + pubId[1] + " Status :" + pubStat);
                    }

                }

                /*
                 * Family add-on upgrade / remove block 
                 */

                ArrayList delFam = new ArrayList();
                delFam = (ArrayList) session.getAttribute("delFam");

                ArrayList updateFam = new ArrayList();
                updateFam = (ArrayList) session.getAttribute("updateFam");

                objMember = (HLCMemberDetails) session.getAttribute("objMember");
                System.out.println("Membership Type ID selected ++++++++++++++++++++"+objMember.getMembershipTypeId());
                if (delFam != null) {

                    for (int x = 0; x < delFam.size(); x++) {
                        String delMemId = (String) delFam.get(x);

                        System.out.println("Inside Delete Block on add-on for Member id :" + delMemId);

                        boolean stat = remote.deleteMemberDetail(delMemId);
                        System.out.println("Deletion of add-on " + x + " Status :" + stat);
                    }
                }

                if (updateFam != null) {
                    Debug.print("updateFam.size() :" + updateFam.size());

                    for (int x = 0; x < updateFam.size(); x++) {

                        String updatMid = (String) updateFam.get(x);

                        System.out.println("Inside Update Block on add-on for Member id :" + updatMid);
                        Debug.print("objMember.getPaymentId() :" + objMember.getPaymentId());

                        boolean sta = remote.updateFamilyMemeber(updatMid, objMember.getPaymentId());

                        Debug.print("Line 735:");
                        String useId = remote.getUserIdBasedOnMemberId(updatMid);
                        String famembId = (String) session.getAttribute("sessfamembId");
                        Debug.print("famem Type Id " + famembId);
                        Debug.print("useId is " + useId);
                        Debug.print("fam memb Id" + updatMid);
                        String[] historyEntry = {renStat, useId, famembId, "Family Member", updatMid};
                        historyTable.add(historyEntry);

                        System.out.println("Update of add-on " + x + " Status :" + sta);

                        if (sslResult.equals("0")) {
                            boolean stat = memberRemote.editMemberStatus("Active", updatMid);
                            Debug.print("memberRemote.editMemberStatus(Active,membid) :" + stat);

                            boolean actDat = remote.updateApprovalDate(updatMid, act_date_card);
                            Debug.print("remote.updateApprovalDate(updatMid,new Date()) :" + actDat);

                        }

                    }


                }

                System.out.println("Inside Edit Member Registration with Credit Card:");

                boolean udate = remote.editMemberDetail(objMember);
                System.out.println("Updation of Member Detail Status in credit card :" + udate);

                Debug.print("Line 759:");
                String memTId = objMember.getMembershipTypeId();
                String memTname = objMember.getMembershipTypeName();
                String useId = objMember.getUserId();
                String[] historyEntry = {renStat, useId, memTId, memTname, objMember.getMemberId()};
                historyTable.add(historyEntry);

                ///// For Future Year Expiry Date
                if (session.getAttribute("changeExp") != null) {
                    String tmpId = (String) session.getAttribute("changeExp");
                    Debug.print("Inside renewExp " + tmpId);
                    boolean update_future = remote.updateFutureExpDateByYear(tmpId);
                    Debug.print("Inside renew Update opt " + update_future);

                    Debug.print("Family Member Date change");
                    HLCMemberDetails objvo = remote.displayMemberDetail(tmpId);
                    List ids = objvo.getFamilyAddOn();
                    Debug.print("Size of the addons " + ids.size());
                    if (ids.size() > 0) {
                        Iterator itMem = ids.iterator();
                        while (itMem.hasNext()) {
                            HLCMemberDetails addonDet = (HLCMemberDetails) itMem.next();
                            String addonMembId = addonDet.getMemberId();
                            Debug.print("Inside addOnMemberId " + addonMembId);
                            if (addonMembId != null && addonMembId.trim().length() != 0) {
                                boolean update_addons = remote.updateFutureExpDateByYear(addonMembId);
                                Debug.print("Inside Update Add Ons " + update_addons);
                            }
                        }
                    }
                }
                /*              Debug.print("!!!!!New User Exp Entry!!!!!");
                if(session.getAttribute("newUserId")!=null){
                String newId = (String) session.getAttribute("newUserId");
                if(newId!=null && newId.trim().length()!=0){
                boolean update_addons = remote.updateFutureExpDateByYear(newId);
                Debug.print(" New User exp date update "+update_addons);
                }
                }
                if(session.getAttribute("newfamily")!=null){
                String tempMid1 = (String)session.getAttribute("newfamily");
                MemberDetails objvo1 = remote.displayMemberDetail(tempMid1);
                Debug.print("Inside family future year "+tempMid1);
                List ids1 = objvo1.getFamilyAddOn();
                Debug.print("Size of the addons "+ids1.size());
                if(ids1.size()>0){
                Iterator itMem1 = ids1.iterator();
                while(itMem1.hasNext()){
                MemberDetails addonDet1 = (MemberDetails)itMem1.next();
                String addonMembId1 = addonDet1.getMemberId();
                Debug.print("Inside addOnMemberId "+addonMembId1);
                if(addonMembId1!=null&& addonMembId1.trim().length()!=0){
                boolean update_addons1 = remote.updateFutureExpDateByYear(addonMembId1);
                Debug.print("Inside Update Add Ons 1"+update_addons1);
                }
                }
                }
                }*/

                ///// End Expiry date
                session.setAttribute("status_membId", objMember.getMemberId());

                request.setAttribute("memberId", objMember.getMemberId());

                if (sslResult.equals("0")) {
                    boolean stat = memberRemote.editMemberStatus("Active", objMember.getMemberId());
                    Debug.print("memberRemote.editMemberStatus(Active,membid) :" + stat);

                    boolean actDat = remote.updateApprovalDate(objMember.getMemberId(), act_date_card);
                    Debug.print("remote.updateApprovalDate(objMember.getMemberId(),new Date()) :" + actDat);

                }

                session.setAttribute("exp_date", remote.getExpiredDate(objMember.getMemberId()));

            } else {
                //-------- reg new member ---------------

                objPayment.setUserId(null);
                objMember = (HLCMemberDetails) session.getAttribute("objMember");
                Debug.print("addMemberDetails 1 :");
                result = remote.addMemberDetails(objMember, objPayment);
                //String memID = (String)session.getAttribute("memberId");
                Debug.print("memID in servlet:" + result);
                String[] userDet = remote2.getUserDetailsForEmail(userId);
                String memTypeName = objMember.getMembershipTypeName();
                if (result != null && Integer.parseInt(sslResult) == 0) {
                    String emailId = (String) session.getAttribute("emailId");
                    String toMailIds[] = {emailId};
                    EmailContent email = new EmailContent();
                    email.setTo(toMailIds);
                    email.setFrom("info@digiblitz.com");
                    email.setSubject("Your Registration Status");

                    if (memTypeName.equalsIgnoreCase("Full Member") || memTypeName.equalsIgnoreCase("Junior Member") || memTypeName.equalsIgnoreCase("Life Member")) {
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
                                "<span class=\"boldTxt\">Dear User</span>,<br />" +
                                "<p>Thank you for your application, your membership has been received and processed</p><br/>" +
                                "<p><strong>HLC Membership Details :</strong><br/><br />" +
                                "Your Membership ID:" + result + " <br/>" +
                                "Membership Type:" + objMember.getMembershipTypeName() + "<br/>" +
                                "Expiration Date:" + remote2.getExpiredDate(result) + "<br/>" +
                                "First Name:" + userDet[1] + "<br/>" +
                                "Last Name: " + userDet[2] + "<br/>" +
                                "City:" + userDet[3] + "<br/>" +
                                "State:" + userDet[4] + "<br/></p>" +
                                "<p>Please allow 3-4 weeks for delivery of your membership card packet .</p> <br/>" +
                                "<p><span class=\"asterisk\"><strong>Note:</strong></span> All 2008 medical cards are WHITE and are downloadable from our website on regular paper . Please do not hesitate to contact the HLC Member Service Dept. if we can be of further assistance! </p><br/>" +
                                "<p><strong>HLC Member Service Department</strong></p><br/>" +
                                " </table>" +
                                "</td></tr>" +
                                "</table>";
                        email.setBody(content);
                    } else if (memTypeName.equalsIgnoreCase("Subscribing Member") || memTypeName.equalsIgnoreCase("Non-Competing Member")) {
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
                                "<span class=\"boldTxt\">Dear User</span>,<br />" +
                                "<p>Thank you for your application, your membership has been received and processed</p><br/>" +
                                "<p><strong>HLC Membership Details :</strong><br/><br />" +
                                "Your Membership ID:" + result + " <br/>" +
                                "Membership Type:" + objMember.getMembershipTypeName() + "<br/>" +
                                "Expiration Date:" + remote2.getExpiredDate(result) + "<br/>" +
                                "First Name:" + userDet[1] + "<br/>" +
                                "Last Name: " + userDet[2] + "<br/>" +
                                "City:" + userDet[3] + "<br/>" +
                                "State:" + userDet[4] + "<br/></p>" +
                                "<p>Your membership packet will arrive within 3-4 weeks.  </p> <br/>" +
                                "<p><span class=\"asterisk\"><strong>Special Note:</strong></span> Subscribers  & Non-Competing members , this membership does not provide competing privileges at any level of competition. </p><br/>" +
                                "<p>Please do not hesitate to contact the HLC Member Service Dept. if we can be of further assistance!</p><br/>" +
                                "<p><strong>HLC Member Service Department</strong></p><br/>" +
                                " </table>" +
                                "</td></tr>" +
                                "</table>";
                        email.setBody(content);
                    } else if (memTypeName.equalsIgnoreCase("Family Member")) {
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
                                "<span class=\"boldTxt\">Dear User</span>,<br />" +
                                "<p>Thank you for your application, your membership has been received and processed</p><br/>" +
                                "<p><strong>HLC Membership Details :</strong><br/><br />" +
                                "Your Membership ID:" + result + " <br/>" +
                                "Membership Type:" + objMember.getMembershipTypeName() + "<br/>" +
                                "Expiration Date:" + remote2.getExpiredDate(result) + "<br/>" +
                                "First Name:" + userDet[1] + "<br/>" +
                                "Last Name: " + userDet[2] + "<br/>" +
                                "City:" + userDet[3] + "<br/>" +
                                "State:" + userDet[4] + "<br/></p>" +
                                "<p>Your membership packet will arrive within 3-4 weeks.  </p> <br/>" +
                                "<p><span class=\"asterisk\"><strong>Special Note:</strong></span> This membership level allows you to compete from Beginner novice level thru Training. However, you must upgrade to a full or life member in order to compete at the preliminary level and above.  (Reminder, if you are moving up to the preliminary level do not forget to upgrade your horse!.)</p><br/>" +
                                "<p><span class=\"asterisk\"><strong>Note:</strong></span> All 2008 medical cards are WHITE and are downloadable from our website on regular paper .</p></br/>" +
                                "<p>Please do not hesitate to contact the HLC Member Service Dept. if we can be of further assistance!</p></br/>" +
                                "<p><strong>HLC Member Service Department</strong></p><br/>" +
                                " </table>" +
                                "</td></tr>" +
                                "</table>";
                        email.setBody(content);
                    }
                    //email.setAttachments();
                    EmailEngine emailEngine = new EmailEngine();
                    boolean emailFlag = emailEngine.sendMimeEmail(email);
                    Debug.print("Email sent sucessfully :" + emailFlag);
                }
                newMembId = result;

                Debug.print("Line 814:");
                String memTId = objMember.getMembershipTypeId();
                String memTname = objMember.getMembershipTypeName();
                String useId = objMember.getUserId();
                String[] historyEntry = {"Register", useId, memTId, memTname, result};
                historyTable.add(historyEntry);

                session.setAttribute("memberId", result);

                System.out.println("Inside Add New Member Registration with Credit Card:" + result);

                ArrayList pubDet = new ArrayList();
                pubDet = (ArrayList) session.getAttribute("pubDet");

                if (pubDet != null) {
                    Debug.print("pubDet.size() :" + pubDet.size());

                    for (int i = 0; i < pubDet.size(); i++) {
                        String[] pubId = (String[]) pubDet.get(i);

                        Debug.print("pubId.length :" + pubId.length);

                        boolean pubStat = remote.insertPublicationDetails(result, pubId[1]);
                        Debug.print("remote.insertPublicationDetails() for member id :" + result + " publication id : " + pubId[1] + " Status :" + pubStat);
                    }

                }

                if (sslResult.equals("0")) {
                    boolean stat = memberRemote.editMemberStatus("Active", result);
                    Debug.print("memberRemote.editMemberStatus(Active,membid) :" + stat);

                    boolean actDat = remote.updateApprovalDate(result, act_date_card);
                    Debug.print("remote.updateApprovalDate(result,new Date()) :" + actDat);

                }

                request.setAttribute("memberId", result);
                session.setAttribute("status_membId", result);

                /*
                 * Donation Amt insertion block
                 */

                String memberId = remote.getParentMemberId(loginName);
                Debug.print("memberId from after reg :" + memberId);

                ArrayList donationDet = new ArrayList();
                donationDet = (ArrayList) session.getAttribute("donationDet");

                if (donationDet != null) {
                    Debug.print("donationDet.size() :" + donationDet.size());

                    for (int d = 0; d < donationDet.size(); d++) {
                        String[] donDet = (String[]) donationDet.get(d);
                        Debug.print("paymentId in servlet :" + paymentIds);

                        boolean donstat = remote.insertDonationPriceDetails(donDet[0], donDet[1], donDet[2], donDet[3], paymentIds);
                        Debug.print("donation " + d + "insert status:" + donstat);

                    }
                }


                /*
                 * Non-Usea Amt insertion block
                 */

                ArrayList nonUseaDet = new ArrayList();
                nonUseaDet = (ArrayList) session.getAttribute("nonUseaDet");

                if (nonUseaDet != null) {

                    for (int c = 0; c < nonUseaDet.size(); c++) {

                        String[] OrgTypId = (String[]) nonUseaDet.get(c);

                        boolean nonstat = remote.insertNonUseaPriceDetails(result, OrgTypId[1], OrgTypId[2]);
                        Debug.print("non hlc " + c + "insert status :" + nonstat);

                    }

                }
                session.setAttribute("exp_date", remote.getExpiredDate(newMembId));
            }


            if (renstat != null) {

                /*
                 * Donation Amt insertion block
                 */

                String memberId = remote.getParentMemberId(loginName);
                Debug.print("memberId from after reg :" + memberId);

                ArrayList donationDet = new ArrayList();
                donationDet = (ArrayList) session.getAttribute("donationDet");

                if (donationDet != null) {
                    Debug.print("donationDet.size() :" + donationDet.size());

                    for (int d = 0; d < donationDet.size(); d++) {
                        String[] donDet = (String[]) donationDet.get(d);
                        Debug.print("paymentId in servlet :" + paymentIds);

                        boolean donstat = remote.insertDonationPriceDetails(donDet[0], donDet[1], donDet[2], donDet[3], paymentIds);
                        Debug.print("donation " + d + "insert status:" + donstat);

                    }
                }


                /*
                 * Non-Usea Amt insertion block
                 */

                ArrayList nonUseaDet = new ArrayList();
                nonUseaDet = (ArrayList) session.getAttribute("nonUseaDet");

                if (nonUseaDet != null) {

                    for (int c = 0; c < nonUseaDet.size(); c++) {

                        String[] OrgTypId = (String[]) nonUseaDet.get(c);

                        boolean nonstat = remote.insertNonUseaPriceDetails(OrgTypId[0], OrgTypId[1], OrgTypId[2]);
                        Debug.print("non hlc " + c + "insert status :" + nonstat);

                    }

                }
            }
            // String email=(String)session.getAttribute("emailId");
            String parentId = remote.getParentMemberId(loginName);

            /*
             * Insertion for Full Member, Life Member 
             */

            String renStat = (String) session.getAttribute("renStat");
            Debug.print("renStat :" + renStat);

            Debug.print("::::::::::: Inside Register / Upgrade / Downgrade add new family-add-on's Block :::::::::::");

            Vector addFamNonWeb = new Vector();
            Vector addFamWeb = new Vector();
            ArrayList addonMemb = new ArrayList();

            addFamNonWeb = (Vector) session.getAttribute("addFamNonWeb");
            addFamWeb = (Vector) session.getAttribute("addFamWeb");
            addonMemb = (ArrayList) session.getAttribute("addonMemb");

            System.out.println("parentId :" + parentId);

            /*
             * Block for Family Add-on insertion
             */

            String famembId = request.getParameter("famembId");

            if (session.getAttribute("updateAddct") != null) {
                String[] updateAddct = (String[]) session.getAttribute("updateAddct");

                int adno = Integer.parseInt(updateAddct[1]);

                boolean status = remote.updatMemberFamilyAddOns(updateAddct[0], adno);
                Debug.print("remote.updatMemberFamilyAddOns(parentId,addct) :" + status);
            }

            if (addFamNonWeb != null) {

                Debug.print("----- inside family add-on non web usr add block ------");
                Debug.print("addFamNonWeb.size() :" + addFamNonWeb.size());

                for (int i = 0; i < addFamNonWeb.size(); i++) {

                    objMember = new HLCMemberDetails();

                    objUserMaster = new HLCUserMaster();
                    objContactDetail = new HLCContactDetails();
                    HLCContactDetails objContactDetail1 = new HLCContactDetails();
                    HLCPaymentDetails objPayment1 = new HLCPaymentDetails();

                    ArrayList nonWebFam = new ArrayList();
                    nonWebFam = (ArrayList) addFamNonWeb.elementAt(i);

                    if (nonWebFam != null) {

                        Debug.print("Array List nonWebFam.size() :" + nonWebFam.size());

                        if (nonWebFam.size() != 0) {

                            objUserMaster = (HLCUserMaster) nonWebFam.get(0);
                            objContactDetail = (HLCContactDetails) nonWebFam.get(1);
                            objMember = (HLCMemberDetails) nonWebFam.get(2);
                            objPayment1 = (HLCPaymentDetails) nonWebFam.get(3);

                            String memberid = (String) session.getAttribute("memberId");
                            Debug.print("objMember1.setParentMemberId(memberid) :" + memberid);

                            objMember.setParentMemberId(memberid);

                            Debug.print("::::::: Inside family add-on " + i + "non web user block ::::::");

                            String usrid = remote.addFamilyAddOn(objUserMaster, objContactDetail);

                            /*String tempmembId = remote.getMemberIdBasedOnUserId(usrid);
                            String[] historyEntry1 = {renStat,usrid,famembId,"Family Member",tempmembId};
                            historyTable.add(historyEntry1);        */

                            System.out.println("after add-on " + i + " user details insertion usrid :" + usrid);

                            objMember.setUserId(usrid);
                            Debug.print("addMemberDetails 2 :");
                            String memb = remote.addMemberDetails(objMember, objPayment1);
                            System.out.println("after add-on " + i + " member details insertion member-Id :" + memb);

                            Debug.print("Line 1052");
                            String memTId = objMember.getMembershipTypeId();
                            String memTname = objMember.getMembershipTypeName();
                            String useId = objMember.getUserId();
                            String membTypeId = remote.getMembershipTypeIdBasedOnUserId(usrid);
                            String[] historyEntry = {renStat, usrid, membTypeId, "Family Member", memb};
                            historyTable.add(historyEntry);

                            if (sslResult.equals("0")) {
                                boolean stat = memberRemote.editMemberStatus("Active", memb);
                                Debug.print("memberRemote.editMemberStatus(Active,membid) :" + stat);

                                boolean actDat = remote.updateApprovalDate(memb, act_date_card);
                                Debug.print("remote.updateApprovalDate(memb,new Date()) :" + actDat);

                            }

                            Vector uid = new Vector();

                            boolean rolestat = roleRemote.createDefaultRole(usrid, defrole);
                            Debug.print("rolestatus :" + rolestat);

                            String membid = (String) session.getAttribute("memberId");

                            /* =====================================
                             *
                             * Sending confirmation E-mail
                             *
                             * ====================================*/

                            String toMailIds[] = {objUserMaster.getEmailId()};
                            EmailContent email = new EmailContent();
                            email.setTo(toMailIds);
                            email.setFrom("anandv@digiblitz.com");
                            email.setSubject("Your Account Info !");

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
                                    "<span class=\"boldTxt\">Dear " + objUserMaster.getFirstName() + "</span>,<br /><br />" +
                                    "<p>Please save this email for your records. Your account information is as follows:.<p>" + "<p>----------------------------<p>" + "<p>Your Member Id :" + memb + "<p> Your Parent Member Id : " + membId + "<p> ----------------------------<p>" +
                                    "Thank you for using the service provided by <span class=\"boldTxt\">HLC</span>.</p>" +
                                    "Thank You <br />" +
                                    "------------------ <br />" +
                                    "<span class=\"boldRedTxt\">HLC Team</span></td>" +
                                    "<td valign=\"top\" background=\"images/Rght.jpg\">&nbsp;</td>" +
                                    "</tr>" +
                                    "<tr><td valign=\"top\" background=\"images/cornerBotLeft.jpg\">&nbsp;</td>" +
                                    "<td valign=\"top\" background=\"images/cornerBot.jpg\">&nbsp;</td>" +
                                    "<td valign=\"top\" background=\"images/cornerBotRght.jpg\">&nbsp;</td>" +
                                    "</tr>" +
                                    " </table>" +
                                    "</td></tr>" +
                                    "+<tr>" +
                                    "<td valign=\"top\" style=\"padding:10px;\">" +
                                    "<img src=\"images/pic.jpg\" width=\"272\" height=\"76\" style=\"float:right; padding-left:8px; padding-bottom:8px;\" />" +
                                    "<p>The easiest way to access your day to day HLC activities online or offline where ever you are and when ever you want." +
                                    "</p>If you are a NEW VISITOR, register now and ENJOY the following privileges:" +
                                    "<ul>" +
                                    "<li>Unlimited shopping online.</li>" +
                                    "<li>Place advertisements online and/or on-site.</li>" +
                                    "<li>Sponsor competitions held by HLC.</li>" +
                                    "</ul>" +
                                    "Also, REGISTER NOW! and become a member of HLC to access and 	enjoy the following privileges as per your Membership Type and as " +
                                    "per your ï¿½Roleï¿½ assigned:" +
                                    "<ul>" +
                                    "<li>Compete in Equestrian Events held by HLC.</li>" +
                                    "<li>Take part in other events like; Annual Meetings, Educational events," +
                                    "Activity Meetings held by HLC etc.</li>" +
                                    "<li>Send Messages to other members.</li>" +
                                    "<li>Create your own Distribution Lists.</li>" +
                                    "<li>Create/Join a group and share your thoughts and common ideas.</li>" +
                                    " <li>Unlimited Shopping online.</li>" +
                                    " <li>Place advertisements online and/or on-site.</li>" +
                                    " <li>Sponsor competitions held by HLC.</li>" +
                                    "</ul>" +
                                    "and much more..." +
                                    "So go ahead and <a href=\"#\">REGISTER NOW!</a></td>" +
                                    "</tr>" +
                                    " <tr>" +
                                    "<td style=\"border-top:1px solid #CC3333; padding-top:8px;\" align=\"right\"><img src=\"images/logo-eMailFooter.jpg\" width=\"63\" height=\"65\" /></td>" +
                                    "</tr>" +
                                    "</table>";


                            email.setBody(content);
                            //email.setAttachments();

                            EmailEngine emailEngine = new EmailEngine();
                            boolean emailFlag = emailEngine.sendMimeEmail(email);
                            Debug.print("Email sent sucessfully :" + emailFlag);
                        }
                    }
                }
            }

            if (addFamWeb != null) {

                Debug.print("----- inside family add-on web usr add block ------");
                Debug.print("addFamWeb.size() :" + addFamWeb.size());

                for (int i = 0; i < addFamWeb.size(); i++) {
                    Debug.print("::::::: Inside family add-on " + i + "existing web user block ::::::");

                    ArrayList famWeb = new ArrayList();
                    famWeb = (ArrayList) addFamWeb.get(i);

                    HLCMemberDetails objMember1 = new HLCMemberDetails();
                    HLCPaymentDetails objPayment1 = new HLCPaymentDetails();

                    String memberid = (String) session.getAttribute("memberId");
                    Debug.print("objMember1.setParentMemberId(memberid) :" + memberid);

                    if (famWeb != null) {
                        Debug.print("Array List famWeb.size() :" + famWeb.size());

                        if (famWeb.size() != 0) {
                            objMember1 = (HLCMemberDetails) famWeb.get(0);
                            objPayment1 = (HLCPaymentDetails) famWeb.get(1);

                            objMember1.setParentMemberId(memberid);
                            Debug.print("addMemberDetails 3 :");
                            String memb = remote.addMemberDetails(objMember1, objPayment1);
                            System.out.println("add-on " + i + " existing web user details insertion status :" + memb);

                            Debug.print("Line 1203");
                            String memTId = objMember.getMembershipTypeId();
                            String memTname = objMember.getMembershipTypeName();
                            String membTypeId = remote.getMembershipTypeIdBasedOnUserId(objMember1.getUserId());
                            String useId = objMember.getUserId();
                            String[] historyEntry = {renStat, objMember1.getUserId(), membTypeId, "Family Member", memb};
                            historyTable.add(historyEntry);

                            if (sslResult.equals("0")) {
                                boolean stat = memberRemote.editMemberStatus("Active", memb);
                                Debug.print("memberRemote.editMemberStatus(Active,membid) :" + stat);

                                boolean actDat = remote.updateApprovalDate(memb, act_date_card);
                                Debug.print("remote.updateApprovalDate(memb,new Date()) :" + actDat);

                            }

                            // getting member id after insert

                            String mId = remote.getParentMemberId(loginName);
                            Debug.print("mId :" + mId);

                            /* =====================================
                             *
                             * Sending confirmation E-mail
                             *
                             * ====================================*/

                            String toMailIds[] = {objMember.getEmailId()};
                            EmailContent email = new EmailContent();
                            email.setTo(toMailIds);
                            email.setFrom("info@digiblitz.com");
                            email.setSubject("Your Account Info !");

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
                                    "<span class=\"boldTxt\">Dear Member,</span>,<br /><br />" +
                                    "<p>Please save this email for your records. Your account information is as follows:.<p>" + "<p>----------------------------<p>" + "<p>Your Member Id :" + memb + "<p> Parent Member Id : " + mId + "<p> ----------------------------<p>" +
                                    "Thank you for using the service provided by <span class=\"boldTxt\">United States Eventing Association</span>.</p>" +
                                    "Thank You <br />" +
                                    "------------------ <br />" +
                                    "<span class=\"boldRedTxt\">HLC Team</span></td>" +
                                    "<td valign=\"top\" background=\"images/Rght.jpg\">&nbsp;</td>" +
                                    "</tr>" +
                                    "<tr><td valign=\"top\" background=\"images/cornerBotLeft.jpg\">&nbsp;</td>" +
                                    "<td valign=\"top\" background=\"images/cornerBot.jpg\">&nbsp;</td>" +
                                    "<td valign=\"top\" background=\"images/cornerBotRght.jpg\">&nbsp;</td>" +
                                    "</tr>" +
                                    " </table>" +
                                    "</td></tr>" +
                                    "+<tr>" +
                                    "<td valign=\"top\" style=\"padding:10px;\">" +
                                    "<img src=\"images/pic.jpg\" width=\"272\" height=\"76\" style=\"float:right; padding-left:8px; padding-bottom:8px;\" />" +
                                    "<p>The easiest way to access your day to day HLC activities online or offline where ever you are and when ever you want." +
                                    "</p>If you are a NEW VISITOR, register now and ENJOY the following privileges:" +
                                    "<ul>" +
                                    "<li>Unlimited shopping online.</li>" +
                                    "<li>Place advertisements online and/or on-site.</li>" +
                                    "<li>Sponsor competitions held by HLC.</li>" +
                                    "</ul>" +
                                    "Also, REGISTER NOW! and become a member of HLC to access and 	enjoy the following privileges as per your Membership Type and as " +
                                    "per your ï¿½Roleï¿½ assigned:" +
                                    "<ul>" +
                                    "<li>Compete in Equestrian Events held by HLC.</li>" +
                                    "<li>Take part in other events like; Annual Meetings, Educational events," +
                                    "Activity Meetings held by HLC etc.</li>" +
                                    "<li>Send Messages to other members.</li>" +
                                    "<li>Create your own Distribution Lists.</li>" +
                                    "<li>Create/Join a group and share your thoughts and common ideas.</li>" +
                                    " <li>Unlimited Shopping online.</li>" +
                                    " <li>Place advertisements online and/or on-site.</li>" +
                                    " <li>Sponsor competitions held by HLC.</li>" +
                                    "</ul>" +
                                    "and much more..." +
                                    "So go ahead and <a href=\"#\">REGISTER NOW!</a></td>" +
                                    "</tr>" +
                                    " <tr>" +
                                    "<td style=\"border-top:1px solid #CC3333; padding-top:8px;\" align=\"right\"><img src=\"images/logo-eMailFooter.jpg\" width=\"63\" height=\"65\" /></td>" +
                                    "</tr>" +
                                    "</table>";


                            email.setBody(content);
                            //email.setAttachments();

                            EmailEngine emailEngine = new EmailEngine();
                            boolean emailFlag = emailEngine.sendMimeEmail(email);
                            Debug.print("Email sent sucessfully :" + emailFlag);

                        }
                    }
                }
            }


            if (addonMemb != null) {

                Debug.print("----- inside family add-on Existing Member add block ------");
                Debug.print("addonMemb.size() :" + addonMemb.size());
                String membStatus = "Pending";

                if (sslResult.equals("0")) {
                    membStatus = "Active";
                }

                for (int i = 0; i < addonMemb.size(); i++) {
                    Debug.print("::::::: Inside family add-on " + i + "existing Member block ::::::");

                    String[] addExMemb = (String[]) addonMemb.get(i);

                    Debug.print("addExMemb memberId :" + addExMemb[0]);
                    Debug.print("addExMemb nameAmat1 :" + addExMemb[1]);
                    Debug.print("addExMemb amatCrdPoss1 :" + addExMemb[2]);
                    Debug.print("addExMemb amatCrdElig1 :" + addExMemb[3]);

                    String parId = (String) session.getAttribute("memberId");
                    String sessfamembId = (String) session.getAttribute("sessfamembId");
                    boolean upStatus = remote.updateAddOnExistingMember(addExMemb[0], membStatus, parId, paymentIds, sessfamembId);
                    Debug.print("updateAddOnExistingMember(exMid,Pending,parId,paymentId) :" + upStatus);

                    Debug.print("Line 1339:");
                    String memTId = objMember.getMembershipTypeId();
                    String memTname = objMember.getMembershipTypeName();
                    String useId = remote.getUserIdBasedOnMemberId(addExMemb[0]);
                    String membTypeId = remote.getMembershipTypeIdBasedOnUserId(useId);
                    String[] historyEntry = {renStat, useId, sessfamembId, "Family Member", addExMemb[0]};
                    historyTable.add(historyEntry);

                    if (sslResult.equals("0")) {
                        boolean appStat = remote.updateApprovalDate(addExMemb[0], act_date_card);
                        Debug.print("remote.updateApprovalDate(mid,appDate) :" + appStat);

                        boolean stat = memberRemote.editMemberStatus("Active", addExMemb[0]);
                        Debug.print("memberRemote.editMemberStatus(Active,membid) :" + stat);

                    }

                    boolean amatCrdPossMembStatus1;

                    if (addExMemb[2] != null) {
                        amatCrdPossMembStatus1 = true;

                    } else {
                        amatCrdPossMembStatus1 = false;
                    }
                    Debug.print("amatCrdPossMembStatus1 :" + amatCrdPossMembStatus1);

                    boolean amatCrdEligMembStatus1;

                    if (addExMemb[3] != null) {
                        amatCrdEligMembStatus1 = true;

                    } else {
                        amatCrdEligMembStatus1 = false;
                    }
                    Debug.print("amatCrdEligMembStatus1 :" + amatCrdEligMembStatus1);

                    boolean amatStat = remote.updateAmatureStatus(addExMemb[0], addExMemb[1], amatCrdPossMembStatus1, amatCrdEligMembStatus1);
                    Debug.print("remote.updateAmatureStatus(addExMemb[0],addExMemb[1],amatCrdPossMembStatus1,amatCrdEligMembStatus1) :" + amatStat);

                    String usrId = remote.getUserIdBasedOnMemberId(addExMemb[0]);
                    Debug.print("remote.getUserIdBasedOnMemberId(addExMemb[0]) :" + usrId);

                    String toMailId = "";
                    toMailId = remote.getEmailBasedOnUserId(usrId);
                    Debug.print("remote.getEmailBasedOnUserId(usrId) :" + toMailId);

                    /* =====================================
                     *
                     * Sending confirmation E-mail
                     *
                     * ====================================*/

                    String toMailIds[] = {toMailId};
                    EmailContent email = new EmailContent();
                    email.setTo(toMailIds);
                    email.setFrom("info@digiblitz.com");
                    email.setSubject("Your Account Info !");

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
                            "<span class=\"boldTxt\">Dear Member,</span>,<br /><br />" +
                            "<p>Please save this email for your records. Your account information is as follows:.<p>" + "<p>----------------------------<p>" + "<p>Your Member Id :" + addExMemb[0] + "<p> Parent Member Id : " + parId + "<p> ----------------------------<p>" +
                            /*"<p>Your account would be activated through your confirmation by visiting the following link: <p>"+
                            "<a href=http://192.168.3.98:8090/dashboad-war/uservalidate.do?email="+request.getParameter("email")+"> Click Here to Activate your Account </a>"+*/
                            "Thank you for using the service provided by <span class=\"boldTxt\">United States Eventing Association</span>.</p>" +
                            "Thank You <br />" +
                            "------------------ <br />" +
                            "<span class=\"boldRedTxt\">HLC Team</span></td>" +
                            "<td valign=\"top\" background=\"images/Rght.jpg\">&nbsp;</td>" +
                            "</tr>" +
                            "<tr><td valign=\"top\" background=\"images/cornerBotLeft.jpg\">&nbsp;</td>" +
                            "<td valign=\"top\" background=\"images/cornerBot.jpg\">&nbsp;</td>" +
                            "<td valign=\"top\" background=\"images/cornerBotRght.jpg\">&nbsp;</td>" +
                            "</tr>" +
                            " </table>" +
                            "</td></tr>" +
                            "+<tr>" +
                            "<td valign=\"top\" style=\"padding:10px;\">" +
                            "<img src=\"images/pic.jpg\" width=\"272\" height=\"76\" style=\"float:right; padding-left:8px; padding-bottom:8px;\" />" +
                            "<p>The easiest way to access your day to day HLC activities online or offline where ever you are and when ever you want." +
                            "</p>If you are a NEW VISITOR, register now and ENJOY the following privileges:" +
                            "<ul>" +
                            "<li>Unlimited shopping online.</li>" +
                            "<li>Place advertisements online and/or on-site.</li>" +
                            "<li>Sponsor competitions held by HLC.</li>" +
                            "</ul>" +
                            "Also, REGISTER NOW! and become a member of HLC to access and 	enjoy the following privileges as per your Membership Type and as " +
                            "per your ï¿½Roleï¿½ assigned:" +
                            "<ul>" +
                            "<li>Compete in Equestrian Events held by HLC.</li>" +
                            "<li>Take part in other events like; Annual Meetings, Educational events," +
                            "Activity Meetings held by HLC etc.</li>" +
                            "<li>Send Messages to other members.</li>" +
                            "<li>Create your own Distribution Lists.</li>" +
                            "<li>Create/Join a group and share your thoughts and common ideas.</li>" +
                            " <li>Unlimited Shopping online.</li>" +
                            " <li>Place advertisements online and/or on-site.</li>" +
                            " <li>Sponsor competitions held by HLC.</li>" +
                            "</ul>" +
                            "and much more..." +
                            "So go ahead and <a href=\"#\">REGISTER NOW!</a></td>" +
                            "</tr>" +
                            " <tr>" +
                            "<td style=\"border-top:1px solid #CC3333; padding-top:8px;\" align=\"right\"><img src=\"images/logo-eMailFooter.jpg\" width=\"63\" height=\"65\" /></td>" +
                            "</tr>" +
                            "</table>";


                    email.setBody(content);
                    //email.setAttachments();

                    EmailEngine emailEngine = new EmailEngine();
                    boolean emailFlag = emailEngine.sendMimeEmail(email);
                    Debug.print("Email sent sucessfully :" + emailFlag);

                }
            }

            if (session.getAttribute("changeExp") != null) {
                Debug.print("Histroy Table size for family " + historyTable.size());
                if (historyTable.size() > 0) {
                    Iterator itr = historyTable.iterator();
                    if (itr.hasNext()) {
                        while (itr.hasNext()) {
                            String[] historyEntry = (String[]) itr.next();
                            String memTypNme = historyEntry[3];
                            String tempMembId = historyEntry[4];
                            if (memTypNme != null && memTypNme.trim().length() != 0) {
                                if (memTypNme.equalsIgnoreCase("Family Member")) {
                                    if (tempMembId != null && tempMembId.trim().length() != 0) {
                                        boolean update_addons = remote.updateFutureExpDateByYear(tempMembId);
                                        Debug.print(" Update on the family member  " + update_addons);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            Debug.print("!!!!!New User Exp Entry!!!!!");
            Debug.print("newMembId is " + newMembId);
            if (newMembId != null && newMembId.trim().length() != 0) {
                if (session.getAttribute("updateFuture") != null) {
                    String updateFuture = (String) session.getAttribute("updateFuture");
                    if (updateFuture.equalsIgnoreCase("update")) {
                        boolean update_newuser = remote.updateFutureExpDateByYear(newMembId);
                        Debug.print(" New User exp date update " + update_newuser);
                        String tempMid1 = newMembId;
                        /*if(historyTable.size()>0){
                        Iterator itr = historyTable.iterator();
                        if(itr.hasNext()){
                        while(itr.hasNext()){
                        String[] historyEntry = (String[]) itr.next();
                        String memTypNme = historyEntry[3];
                        String tempMembId = historyEntry[4];
                        if(memTypNme!=null && memTypNme.trim().length()!=0){
                        if(memTypNme.equalsIgnoreCase("Family Member"))   {
                        if(tempMembId!=null && tempMembId.trim().length()!=0){
                        boolean update_addons = remote.updateFutureExpDateByYear(tempMembId);
                        Debug.print(" Update on the family member  "+update_addons);
                        }
                        }
                        }
                        }
                        }
                        }  */
                        HLCMemberDetails objvo1 = remote.displayMemberDetail(tempMid1);
                        Debug.print("Inside family future year " + tempMid1);
                        List ids1 = objvo1.getFamilyAddOn();
                        Debug.print("Size of the addons " + ids1.size());
                        if (ids1.size() > 0) {
                            Iterator itMem1 = ids1.iterator();
                            while (itMem1.hasNext()) {
                                HLCMemberDetails addonDet1 = (HLCMemberDetails) itMem1.next();
                                String addonMembId1 = addonDet1.getMemberId();
                                Debug.print("Inside addOnMemberId " + addonMembId1);
                                if (addonMembId1 != null && addonMembId1.trim().length() != 0) {
                                    boolean update_addons1 = remote.updateFutureExpDateByYear(addonMembId1);
                                    Debug.print("Inside Update Add Ons 1" + update_addons1);
                                }
                            }
                        }
                    }
                }
            }

            if (renstat == null) {
                session.setAttribute("exp_date", remote.getExpiredDate(newMembId));
            }

            //// History Table Entries
            Debug.print("History Table Entries");
            //ArrayList historyTable = (ArrayList) session.getAttribute("historyTable");
            if (historyTable != null) {
                Debug.print("Size of the history is " + historyTable.size());
                if (historyTable.size() > 0) {
                    Iterator itr = historyTable.iterator();
                    if (itr.hasNext()) {

                        HLCMemberHistoryDetail detailVO = new HLCMemberHistoryDetail();
                        while (itr.hasNext()) {
                            String[] historyEntry = (String[]) itr.next();

                            String regiTyp = "";
                            String statid = "";
                            String useId = "";
                            String memTyeId = "";
                            String memTypNme = "";
                            String tempMembId = "";
                            String zipCode = "";
                            if (sslResult.equals("0")) {
                                statid = remote2.getStatusIBasedOnStatus("Active");
                            } else {
                                statid = remote2.getStatusIBasedOnStatus("Pending");
                            }
                            if (historyEntry[0] == null) {
                                regiTyp = "Register";
                            } else {
                                regiTyp = historyEntry[0];
                            }
                            useId = historyEntry[1];
                            memTyeId = historyEntry[2];
                            memTypNme = historyEntry[3];
                            tempMembId = historyEntry[4];
                            Date new_year = new Date();
                            if (tempMembId != null && tempMembId.trim().length() != 0) {
                                new_year = remote2.getExpiredDate(tempMembId);
                            }

                            String temp = new_year.toString();
                            Debug.print("temp is " + new_year.toString());
                            //2008-11-30
                            String[] splitr = temp.split("-");
                            System.out.println("splitr[0]" + splitr[0]);

                            String histPay_id = (String) session.getAttribute("histpayId");

                            int newVal = 0;
                            if (splitr[0] != null) {
                                newVal = Integer.parseInt(splitr[0]);
                            }

                            if (useId != null && useId.trim().length() != 0) {
                                zipCode = remote2.getZipCodeOnUserId(useId);
                            } else {
                                zipCode = "";
                            }

                            detailVO.setTo_year(newVal);
                            detailVO.setPayment_id(histPay_id);
                            detailVO.setMemberId(tempMembId);
                            detailVO.setMembership_action(regiTyp);
                            detailVO.setMembership_type_id(memTyeId);
                            detailVO.setMembership_type_name(memTypNme);
                            detailVO.setZip_code(zipCode);
                            detailVO.setStatus_id(statid);
                            detailVO.setUser_id(useId);
                            detailVO.setAmatName(objMember.getAmateurName());
                            detailVO.setAmatDec1(objMember.isAmateurDec1());
                            detailVO.setAmatDec2(objMember.isAmateurDec2());

                            Debug.print("History Updat Status in HumanMemberPaymentAction");

                            boolean historyUpdate = remote2.insertMemberHistoryDetails(detailVO);
                            Debug.print("historyUpdate " + historyUpdate);
                        }
                    }
                }
            }
            /*    }          
            else{                
            Debug.print("Not Inserted..:");
            Debug.print("amount1:" + amount1);
            request.setAttribute("amount",amount1);
            fwd="decline";               
            }*/

            session.setAttribute("objPaymentList", null);
            session.setAttribute("objMember", null);

            //================================================================
            return mapping.findForward(fwd);
        } catch (Exception ecommon) {
            ecommon.printStackTrace();
            Debug.print("" + ecommon);

        // roll back method for member details 

        //               boolean stat=remote2.deleteMemberDetail(parentId);
        //               Debug.print("Succesfully rolled Back Member Details Status :"+stat);

        }
        return null;
    }

    public static Context getInitialContext() throws javax.naming.NamingException {
        Properties p = new Properties();
        p.setProperty("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
        p.setProperty("java.naming.factory.url.pkgs", "org.jboss.naming:org.jnp.interfaces");
        p.setProperty("java.naming.provider.url", "");
        return new javax.naming.InitialContext(p);
    }
}
