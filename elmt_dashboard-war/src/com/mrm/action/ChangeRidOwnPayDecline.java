/*
 * HorseCardRePayAction.java
 *
 * Created on February 13, 2007, 5:01 PM
 */
package com.mrm.action;

import com.hlcaccounts.util.HLCAccTransactionVO;
import com.hlccommon.util.Debug;
import com.hlccommon.util.HLCHorseRegListVO;
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
public class ChangeRidOwnPayDecline extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try {
            String process = (String) request.getParameter("process");
            HttpSession session = request.getSession();
            MessageResources mr = getResources(request);
            String jndiname = mr.getMessage("jndi.kavery");
            Context jndiContext = new InitialContext();
            Object objref = jndiContext.lookup(jndiname);

            HLCKaverySessionBeanStatfulRemoteHome home = (HLCKaverySessionBeanStatfulRemoteHome) javax.rmi.PortableRemoteObject.narrow(objref, HLCKaverySessionBeanStatfulRemoteHome.class);
            HLCKaverySessionBeanStatfulRemote remote = home.create();


            HLCPaymentVO objPayment = new HLCPaymentVO();
             HLCPaymentDetailVO payDetVO = new HLCPaymentDetailVO();
             ArrayList txnDetails = new ArrayList();
              HLCAccTransactionVO accTxnChangePay = new HLCAccTransactionVO();

            String jndihorse = mr.getMessage("jndi.kavery");
            Object objPayss = jndiContext.lookup("ejb/HLCPaymentSessionBean");
            Object objhorse = jndiContext.lookup(jndihorse);


            HLCPaymentSessionRemoteHome objPaySessHome = (HLCPaymentSessionRemoteHome) javax.rmi.PortableRemoteObject.narrow(objPayss, HLCPaymentSessionRemoteHome.class);
            HLCPaymentSessionRemote objPaySessRemote = objPaySessHome.create();

            String sesUserId = (String) session.getAttribute("userId");
            if (process.equalsIgnoreCase("chngrepay")) {
                Debug.print("Inside Repay of ChnageHorseRePayAction");

                String horseName = (String) request.getParameter("horseName");
                String horseMemberId = (String) request.getParameter("horseMemberId");
                String totalAmount = (String) request.getParameter("totalAmount");
                String paymentId = (String) request.getParameter("paymentId");
                String horseName1 = (String) request.getParameter("horseName1");
                String riderId =(String) request.getParameter("riderId");
                String OwnerMailId = (String)request.getParameter("OwnerMailId");
                String riderEmailId = (String)request.getParameter("riderEmailId");            
                String competitionName = (String) request.getParameter("competitionName");
                String membershipName1 =(String) request.getParameter("membershipName1");
                String tempOwnerName = (String)request.getParameter("tempOwnerName");
                String tempRiderName = (String)request.getParameter("tempRiderName"); 
                /*  boolean pri_rider =false;
                boolean pri_owner = false;
                pri_owner = remote.isHorsePriOwnerExist(horseMemberId,sesUserId);
                pri_rider = remote.isHorsePriRiderExist(horseMemberId,sesUserId);
                Debug.print("Primary Owner is Avilable "+pri_owner);
                Debug.print("Primary Rider is Avilable "+pri_rider);
                if(pri_owner ==false && pri_rider==false){
                Debug.print("Sorry u r not able to pay the pending amount:" );
                return mapping.findForward("UserDuePayMessage");
                }
                else if(pri_owner == true || pri_rider==true){
                }*/
                request.setAttribute("horseName", horseName);
                request.setAttribute("horseMemberId", horseMemberId);
                request.setAttribute("paymentId", paymentId);
                request.setAttribute("totalAmount", totalAmount);
                request.setAttribute("horseName1",horseName1);
                request.setAttribute("riderId",riderId);
                request.setAttribute("OwnerMailId",OwnerMailId);
                request.setAttribute("riderEmailId",riderEmailId);
                request.setAttribute("competitionName",competitionName);
                request.setAttribute("membershipName1",membershipName1);
                request.setAttribute("tempOwnerName",tempOwnerName);
                request.setAttribute("tempRiderName",tempRiderName);

                Debug.print("horseName" + horseName);
                Debug.print("horseMemberId" + horseMemberId);
                Debug.print("paymentId" + paymentId);
                Debug.print("totalAmount" + totalAmount);
                return mapping.findForward("goToChangeRepay");
            }

            if (process.equalsIgnoreCase("updatePayment")) {
                Debug.print("Inside the payment option");


                String emailId = (String) session.getAttribute("emailId");
                FormHorseUpgrade fbean = (FormHorseUpgrade) form;
                System.out.println("FormHorseUpgrade =" + fbean);
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

                String paymentId = request.getParameter("paymentId");

                String horseName = request.getParameter("horseName");
                String horseMemberId = request.getParameter("horseMemberId");

                String tempAmount = (String) request.getParameter("tempAmount");
                String horseName1 = (String) request.getParameter("horseName1");
                String riderId =(String) request.getParameter("riderId");
                String OwnerMailId = (String)request.getParameter("OwnerMailId");
                String riderEmailId = (String)request.getParameter("riderEmailId");            
                String competitionName = (String) request.getParameter("competitionName");
                String membershipName1 =(String) request.getParameter("membershipName1");
                String tempOwnerName = (String)request.getParameter("tempOwnerName");
                String tempRiderName = (String)request.getParameter("tempRiderName"); 

                Debug.print("tempAmount" + tempAmount);
                Debug.print("horseName" + horseName);
                Debug.print("horseMemberId" + horseMemberId);
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
                    objPayment.setPaymentStatus("Credit Card");
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
                    objPayment.setPaymentStatus("Check");

                }
                String totAmount = request.getParameter("totalAmount");
                System.out.println("tempAmount1" + tempAmount);
                if (totAmount == null || totAmount == "") {
                    totalAmount = 0;
                } else {
                    totalAmount = Double.parseDouble(totAmount);
                }
                Debug.print("totAmount" + totAmount);
                System.out.println("totalAmount" + totalAmount);
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
                payDetVO.setAmount(totalAmount);
                payDetVO.setPaymentDate(new Date());
                payDetVO.setPaymentId(paymentId);
                //IP Address
                payDetVO.setIpAddress(addr);


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
                     String preRidLName="";                      

                        String relaId="";
                        String relaTypeName="";
                        String preFName="";
                        String preLName="";
                        String preOwnerEmail="";
                        String preRiderEmail="";
                        String preOwnerFName="";
                        String preOwnerLName="";
                        String preOwnEmail="";
                        String preRiderFName="";
                        String preRiderLName="";
                        String preRidEmail="";
                        String OwnerLogName=""; 
                        
               ArrayList objPreOwnRidDet = new ArrayList();
                        objPreOwnRidDet = remote.getPreOwnerRiderDetails(horseMemberId);
                        session.setAttribute("commonOwnersRiders",objPreOwnRidDet);
                        String ownerNames="";
                        String riderNames="";                       
                        if(objPreOwnRidDet!=null && objPreOwnRidDet.size()!=0){
                          Iterator itr = objPreOwnRidDet.iterator();
                          int i=0;
                          int j=0;
                          while(itr.hasNext()) {
                          HLCHorseRegListVO objOwnerInfo =(HLCHorseRegListVO)itr.next();                         
                            relaId=objOwnerInfo.getRelationTypeId();
                           Debug.print("relaId:::" + relaId);
                            relaTypeName=objOwnerInfo.getRelationTypeName();
                           Debug.print("relaTypeName:::" + relaTypeName);
                           if(relaTypeName!=null && (relaTypeName.equalsIgnoreCase("Previous Owner"))){
                               preFName=objOwnerInfo.getPreOwnerFName();
                                preOwnerFName=preFName; 
                                preLName=objOwnerInfo.getPreOwnerLName();
                                preOwnerLName=preLName;
                                preOwnerEmail=objOwnerInfo.getPreEmail();
                                preOwnEmail=preOwnerEmail;
                           } 
                           if(relaTypeName!=null && (relaTypeName.equalsIgnoreCase("Previous Rider"))){    
                               String preRidFName1=objOwnerInfo.getPreOwnerFName();
                                preRiderFName=preRidFName1; 
                               String preRidLName1=objOwnerInfo.getPreOwnerLName();
                                preRiderLName=preRidLName; 
                               preRiderEmail=objOwnerInfo.getPreEmail();
                                preRidEmail=preRiderEmail;
                           } 
                           if(relaTypeName!=null && (relaTypeName.equalsIgnoreCase("Owner"))){  
                                preFName=objOwnerInfo.getPreOwnerFName();
                                preLName=objOwnerInfo.getPreOwnerLName();
                                String tempOwnerNames=preFName+" "+preLName;
                                if(i==0){
                                ownerNames=tempOwnerNames;
                                System.out.println("ownerNames inside if :"+ownerNames);
                                }else{
                                ownerNames=ownerNames+","+tempOwnerNames; 
                                System.out.println("ownerNames inside else :"+ownerNames);
                                }
                               i++;
                              }
                           if(relaTypeName!=null && (relaTypeName.equalsIgnoreCase("Rider"))){  
                                preFName=objOwnerInfo.getPreOwnerFName();
                                preLName=objOwnerInfo.getPreOwnerLName();
                                String tempRiderNames=preFName+" "+preLName;
                                if(j==0){
                                riderNames=tempRiderNames;
                                }else{
                                riderNames=riderNames+","+tempRiderNames;    
                                }
                                j++;
                                }
                           }  
                        }    
                          
                    
                    System.out.println("expYear:" + expYear);
                    //expYear = expYear.substring(2);
                    System.out.println("expMon:" + expMon);
                    //System.out.println("expYear:" + expYear);
                    String expDate = expMon + expYear;
                    request.setAttribute("expDate", expDate);
                    request.setAttribute("amount", String.valueOf(totalAmount));
                    request.setAttribute("chkDigit", String.valueOf(CcCvvid));

                    String res = "";


                    session.setAttribute("amount", String.valueOf(totalAmount));
                    session.setAttribute("paymentId", paymentId);
                    session.setAttribute("horseName", horseName);
                    session.setAttribute("horseMemberId", horseMemberId);
                    session.setAttribute("totalAmount", String.valueOf(totalAmount));
                    session.setAttribute("horseName1",horseName1);
                    session.setAttribute("riderId",riderId);
                    session.setAttribute("OwnerMailId",OwnerMailId);
                    session.setAttribute("riderEmailId",riderEmailId);
                    session.setAttribute("competitionName",competitionName);
                    session.setAttribute("membershipName1",membershipName1);
                    session.setAttribute("tempOwnerName",tempOwnerName);
                    session.setAttribute("tempRiderName",tempRiderName);
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
             request.setAttribute("intVId","1");
             
             System.out.println("invoiceid in the retry servlet..........."+inVId);
             session.setAttribute("sessionInvoiceId","1");
            String purposeOfPayment=(String)session.getAttribute("purpose"); 
             if(purposeOfPayment.equalsIgnoreCase("horseriderownerchange"))
             {
                 request.setAttribute("purpose","horseriderownerchange");
             }
             else{
                request.setAttribute("purpose","addriderowner");
             }
            txnDetails = (ArrayList) session.getAttribute("txnDetails");
            if(txnDetails!=null){
            Iterator itr=txnDetails.iterator();
             while(itr.hasNext()){
               accTxnChangePay = (HLCAccTransactionVO)itr.next();
               accTxnChangePay.setPayment_mode(request.getParameter("ccType"));
               //String transactionid=accTxnChangePay.getTransaction_id();
              // accTxnChangePay.setTransaction_id(transactionid);
             }
            txnDetails.add(accTxnChangePay);
           // session.setAttribute("txnDetails", txnDetails);
            }
            //session.getAttribute("objOwnerVO");
            //session.setAttribute("objOwnerVO",objOwnerVO);
            
            session.setAttribute("txnDetails", txnDetails);
             session.setAttribute("objPaymentVO",payDetVO);
             //session.setAttribute("objPayment",objPayment1);
                 return mapping.findForward("testing");

                    /*StringBuffer reqURL = request.getRequestURL();
                    int lastIndex = reqURL.lastIndexOf("/");
                    String url = "";
                    if (lastIndex != -1) {
                        url = reqURL.substring(0, lastIndex + 1);
                    }
                    String tmpNova = mr.getMessage("changehorserepay.nova");
                    String nova = url + tmpNova;
                    Debug.print("succsssullll from app file nova........::" + nova);
                    Debug.print("succsssullll from app file tmpNova ........::" + tmpNova);
                    request.setAttribute("nova", nova);
                    return mapping.findForward("novaPage");*/

                //return mapping.findForward("frmHorseSuccessRedir");
                } else {

                    Debug.print("paymentId in check:" + paymentId);
                    Debug.print("horseRegVO.setRegisteredBy(sesUserId);" + sesUserId);
                    Debug.print("amount:" + totalAmount);
                    Debug.print("horseRegVO.setRegisteredBy(sesUserId)");


                    boolean update_stat = remote.updatePaymentStatus(objPayment);
                    Debug.print("Update Status " + update_stat);


                    if (update_stat == true) {
                        String toMailIds[] = {emailId};
                        EmailContent email = new EmailContent();
                        email.setTo(toMailIds);
                        email.setFrom("info@hlc.com");
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
                                "<p>Dear User," +
                                "<p>You have successfully upgraded your payment Request. It will be reviewed and Approved within 24 hrs.<p>" +
                                "Thank you for using this service.<p>" +
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
                                "per your �Role� assigned:" +
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
                        EmailEngine emailEngine = new EmailEngine();
                        boolean emailFlag = emailEngine.sendMimeEmail(email);
                        Debug.print("Email sent sucessfully :" + emailFlag);
                        request.setAttribute("horseMemberId", horseMemberId);
                        request.setAttribute("horseName", horseName);
                        request.setAttribute("totalAmount", String.valueOf(totalAmount));

                        return mapping.findForward("changeRidOwnSuccess");
                    }

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
