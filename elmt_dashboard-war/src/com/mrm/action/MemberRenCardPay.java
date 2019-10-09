/*
 * MemberRenCardPay.java
 *
 * Created on November 10, 2007, 3:25 PM
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
import java.util.*;
import javax.servlet.http.*;
import com.hlcform.util.HLCUserMaster;
import com.hlcform.util.HLCContactDetails;
import com.hlcrole.management.*;
/**
 *
 * @author hari
 * @version
 */

public class MemberRenCardPay extends Action {
    
    private final static String SUCCESS = "success";
    
    public ActionForward execute(ActionMapping mapping, ActionForm  form, HttpServletRequest request, HttpServletResponse response)
    throws Exception {
        String fwd="";
        String amount1="";
        String paymentIds="";
        Date act_date_card=new Date();
        
        HLCkaverystatelessRemote remote2=null;
        //String parentId=null;
        //MemberDetails objMember = new MemberDetails();
        String newMembId ="";
        try{
            HttpSession session = request.getSession();
            MessageResources mr=getResources(request);
            String namingfactory=mr.getMessage("ejbclient.namingfactory");
            String contextfactory=mr.getMessage("ejbclient.contextfactory");
            String urlprovider=mr.getMessage("ejbclient.urlprovider");
            String lookupip=mr.getMessage("ejbclient.ip");        
            String jndimemb=mr.getMessage("jndi.usrreg");
            
            String jndiname2=mr.getMessage("jndi.rolemanagement");
            String defrole=mr.getMessage("default.role");
            Debug.print("defrole :" +defrole);
            
            System.setProperty(namingfactory,contextfactory);
            System.setProperty(urlprovider,lookupip);
            Context jndiContext = new InitialContext();
     
            Object objPayss= jndiContext.lookup("ejb/HLCPaymentSessionBean");
            HLCPaymentSessionRemoteHome objPaySessHome = (HLCPaymentSessionRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(objPayss,HLCPaymentSessionRemoteHome.class);
            HLCPaymentSessionRemote objPaySessRemote = objPaySessHome.create();
            
            Object objrefMemb = jndiContext.lookup(jndimemb);
            HLCkaverystatelessRemoteHome home2 = (HLCkaverystatelessRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(objrefMemb,HLCkaverystatelessRemoteHome.class);
            remote2 = home2.create();
            
            String mahanadhiJndi=mr.getMessage("jndi.acc");
            Debug.print("mahanadhiJndi  :" +mahanadhiJndi);
            Object maha=jndiContext.lookup(mahanadhiJndi);
            HLCMahanadhiSessionRemoteHome mahaHome = (HLCMahanadhiSessionRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(maha,HLCMahanadhiSessionRemoteHome.class);
            HLCMahanadhiSessionRemote mahaRemote = mahaHome.create();
            
            String name1 = "ejb/HLCKaveryMembershipTypeJNDI";
            Object obj=jndiContext.lookup(name1);
            HLCKaveryMembershipTypeSessionRemoteHome memberHome = (HLCKaveryMembershipTypeSessionRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(obj,HLCKaveryMembershipTypeSessionRemoteHome.class);
            HLCKaveryMembershipTypeSessionRemote memberRemote = memberHome.create();
            
            String jndiname3=mr.getMessage("jndi.rolemanagement");
            Object objref2 = jndiContext.lookup(jndiname3);
            Debug.print("ActionRoleMangement.jndiname:" + jndiname3);
            HLCBrahmaputraSessionRemoteHome roleHome = (HLCBrahmaputraSessionRemoteHome)PortableRemoteObject.narrow(objref2,HLCBrahmaputraSessionRemoteHome.class);
            HLCBrahmaputraSessionRemote roleRemote = roleHome.create();
            
            
            ArrayList historyTable = new ArrayList();
            
            HLCPaymentDetails objPayment = (HLCPaymentDetails) session.getAttribute("objPaymentList");
            String userId = (String)session.getAttribute("userId");
            paymentIds=objPayment.getPaymentId();
            
            Debug.print("Return from Paypal UserId:" + (String)session.getAttribute("userId"));
            Debug.print("Return from Paypal UserCode:" + (String)session.getAttribute("userCode"));
            Debug.print("Return from Paypal memeberId:" + (String)session.getAttribute("memberId"));
            
            Debug.print("Return from Paypal:");
            
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
                email.setFrom("dashboard@useventing.com");
                email.setSubject("Member Renewal Decline");
                //System.out.println("toMailIds: " + toMailIds);
                String content = "<table width=\"526\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"border:1px solid #999;\"> " +
                        " <tr>" +
                        " <td height=\"463\" valign=\"top\" bgcolor=\"#FCFBF0\" style=\"padding-top:10px; padding-bottom:10px; padding-left:10px; padding-right:10px;\">" +
                        " <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> " +
                        "<tr>" +
                        "<td height=\"70\" valign=\"top\"><img src=\"images/emailHeader.jpg\" alt=\"USEA Online Services Dashboard Email Template\" width=\"515\" height=\"55\" /></td> " +
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
                        //"AVSCODE: " + payRes.getSslAvsResponse() + "<br/>" +
                        //"CVV2MATCH:" + payRes.getSslCvv2Response() + "<br/>" +
                        "Corrective action:" + payRes.getFinalCorecAction() + "<br/></p>" +
                        "<span class=\"boldRedTxt\">USEA Team</span></td>" +
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
            
            Debug.print("paymentId in nova servlet :"+objPayment.getPaymentId());
            
            /*String sslResult = request.getParameter("ssl_result");
            String sslResultMessage =  request.getParameter("ssl_result_message");
            String sslTxnId =  request.getParameter("ssl_txn_id");
            String sslApprovalCode = request.getParameter("ssl_approval_code");
            String sslCvv2Response = request.getParameter("ssl_cvv2_response");
            String sslAvsResponse = request.getParameter("ssl_avs_response");
            String sslTransactionType =  request.getParameter("ssl_transaction_type");
            String sslInvoiceNo = request.getParameter("ssl_invoice_number");
            String sslEmail = request.getParameter("ssl_email");
            
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
            
            Debug.print("paymentId in nova servlet :"+objPayment.getPaymentId());*/
            
            act_date_card=(java.util.Date)session.getAttribute("act_date");
            Debug.print("act_date_card from session :"+act_date_card.toString());
            
            if(objPayment!=null){
                amount1=String.valueOf(objPayment.getAmount());
            }
            HLCMemberDetails objMember = (HLCMemberDetails) session.getAttribute("objMember");
            
            Debug.print("objMemDet.getAmateurName() : "+objMember.getAmateurName());
            Debug.print("objMemDet.isAmateurDec1() : "+objMember.isAmateurDec1());
            Debug.print("objMemDet.isAmateurDec2(): "+objMember.isAmateurDec2());
            objMember.setRenewalStatus(true);
            Debug.print("Edit Member Details");
            boolean udate=remote2.editMemberDetail(objMember);
            System.out.println("Updation of Member Detail Status :" +udate);
            String tempMid=(String)session.getAttribute("memberId");
            
            Debug.print("Line 172:");
            String memTId = objMember.getMembershipTypeId();
            String memTname = objMember.getMembershipTypeName();
            String useId = objMember.getUserId();
            String[] historyEntry = {"renew",useId,memTId,memTname,tempMid};
            historyTable.add(historyEntry);
            
            if(sslResult.equals("0")){
                boolean stat=memberRemote.editMemberStatus("Active",tempMid);
                Debug.print("memberRemote.editMemberStatus(Active,membid) :"+stat);
                
                boolean actDat=remote2.updateApprovalDate(tempMid,act_date_card);
                Debug.print("remote.updateApprovalDate(updatMid,new Date()) :"+actDat);
            }
            /*
             * Family add-on upgrade / remove block
             */
            
            ArrayList delFam=new ArrayList();
            delFam=(ArrayList)session.getAttribute("delFam");
            
            ArrayList updateFam=new ArrayList();
            updateFam=(ArrayList)session.getAttribute("updateFam");
            
            objMember=(HLCMemberDetails)session.getAttribute("objMember");
            
            if(delFam!=null) {
                for(int x=0;x<delFam.size();x++){
                    String delMemId=(String)delFam.get(x);
                    System.out.println("Inside Delete Block on add-on for Member id :"+delMemId);
                    boolean stat=remote2.deleteMemberDetail(delMemId);
                    System.out.println("Deletion of add-on "+x+" Status :" +stat);
                }
            }
            
            if(updateFam!=null){
                Debug.print("updateFam.size() :"+updateFam.size());
                for(int x=0;x<updateFam.size();x++){
                    String updatMid=(String)updateFam.get(x);
                    
                    System.out.println("Inside Update Block on add-on for Member id :" +updatMid);
                    Debug.print("objMember.getPaymentId() :"+objMember.getPaymentId());
                    
                    boolean sta=remote2.updateFamilyMemeber(updatMid,objMember.getPaymentId());
                    
                    Debug.print("Line 735:");
                    String usermId =remote2.getUserIdBasedOnMemberId(updatMid);
                    String famembId = (String) session.getAttribute("sessfamembId");
                    Debug.print("famem Type Id "+famembId);
                    Debug.print("useId is "+useId);
                    Debug.print("fam memb Id"+updatMid);
                    String[] historyEntry1 = {"renew",usermId,famembId,"Family Member",updatMid};
                    historyTable.add(historyEntry1);
                    
                    System.out.println("Update of add-on "+x+" Status :" +sta);
                    
                    if(sslResult.equals("0")){
                        boolean stat=memberRemote.editMemberStatus("Active",updatMid);
                        Debug.print("memberRemote.editMemberStatus(Active,membid) :"+stat);
                        
                        boolean actDat=remote2.updateApprovalDate(updatMid,act_date_card);
                        Debug.print("remote.updateApprovalDate(updatMid,new Date()) :"+actDat);
                        
                        String selYear = (String) session.getAttribute("selYear");
                        Debug.print("Inside famUser1.equalsIgnoreCase(\"no\") "+selYear);
                        if(selYear!=null){
                            int sel_year = Integer.parseInt(selYear);
                            java.util.Calendar toDay = java.util.Calendar.getInstance();
                            int newyear = toDay.get(Calendar.YEAR);
                            int new_month = toDay.get(Calendar.MONTH);
                            if(sel_year != newyear){
                                remote2.updateFutureExpDateByYear(updatMid);
                            }
                        }
                    }
                }
            }
            Debug.print("Non HLC Details");
            
            String membId=(String)session.getAttribute("memberId");
            
            boolean nonUseaStat=remote2.deleteNonUseaPriceDetails(membId);
            Debug.print("remote.deleteNonUseaPriceDetails(membId) for member id :"+membId+" :" +nonUseaStat);
            
            boolean pubDelStat=remote2.deletePublicationDetails(membId);
            Debug.print("remote.deletePublicationDetails(membId) for member id :"+membId+" :" +pubDelStat);
            
            ArrayList pubDet=new ArrayList();
            pubDet=(ArrayList)session.getAttribute("pubDet");
            
                    /*
                     * Publication insertion block
                     */
            
            if(pubDet!=null) {
                Debug.print("pubDet.size() :" +pubDet.size());
                for(int i=0;i<pubDet.size();i++) {
                    String[] pubId=(String[])pubDet.get(i);
                    Debug.print("pubId.length :" +pubId.length);
                    boolean pubStat=remote2.insertPublicationDetails(pubId[0],pubId[1]);
                    Debug.print("remote.insertPublicationDetails() for member id :"+pubId[0]+" publication id : "+pubId[1]+" Status :" +pubStat);
                }
            }
            
                    /*
                     * Non-Usea Amt insertion block
                     */
            ArrayList nonUseaDet=new ArrayList();
            nonUseaDet=(ArrayList)session.getAttribute("nonUseaDet");
            
            if(nonUseaDet!=null) {
                for(int c=0;c<nonUseaDet.size();c++) {
                    String[] OrgTypId=(String[])nonUseaDet.get(c);
                    boolean nonstat=remote2.insertNonUseaPriceDetails(membId,OrgTypId[1],OrgTypId[2]);
                    Debug.print("non hlc "+c+"insert status :"+nonstat);
                }
            }
            
            String loginName=(String)session.getAttribute("loginName");
            Debug.print("loginName from session :"+loginName);
            String memberId=(String)session.getAttribute("memberId");
            Debug.print("memberId from after reg :"+memberId);
            
            ArrayList donationDet=new ArrayList();
            donationDet=(ArrayList)session.getAttribute("donationDet");
            
            if(donationDet!=null) {
                Debug.print("donationDet.size() :"+donationDet.size());
                
                for(int d=0;d<donationDet.size();d++) {
                    String[] donDet=(String[])donationDet.get(d);
                    Debug.print("paymentId in servlet :"+paymentIds);
                    
                    boolean donstat=remote2.insertDonationPriceDetails(donDet[0],donDet[1],donDet[2],donDet[3],paymentIds);
                    Debug.print("donation "+d+"insert status:"+donstat);
                }
            }
            
            Vector addFamNonWeb=new Vector();
            Vector addFamWeb=new Vector();
            ArrayList addonMemb=new ArrayList();
            HLCUserMaster objUserMaster=new HLCUserMaster();
            HLCContactDetails objContactDetail=new HLCContactDetails();
            
            addFamNonWeb=(Vector)session.getAttribute("addFamNonWeb");
            addFamWeb=(Vector)session.getAttribute("addFamWeb");
            addonMemb=(ArrayList)session.getAttribute("addonMemb");
            
            String parentId=remote2.getParentMemberId(loginName);
            System.out.println("parentId :"+parentId);
            
               /*
                * Block for Family Add-on insertion
                */
            
            String famembId=request.getParameter("famembId");
            
            if(session.getAttribute("updateAddct")!=null) {
                String[] updateAddct=(String[])session.getAttribute("updateAddct");
                
                int adno=Integer.parseInt(updateAddct[1]);
                
                boolean status=remote2.updatMemberFamilyAddOns(updateAddct[0],adno);
                Debug.print("remote.updatMemberFamilyAddOns(parentId,addct) :"+status);
            }
            
            if(addFamNonWeb!=null) {
                
                Debug.print("----- inside family add-on non web usr add block ------");
                Debug.print("addFamNonWeb.size() :"+addFamNonWeb.size());
                
                for(int i=0;i<addFamNonWeb.size();i++) {
                    
                    objMember = new HLCMemberDetails();
                    
                    objUserMaster=new HLCUserMaster();
                    objContactDetail=new HLCContactDetails();
                    HLCContactDetails objContactDetail1=new HLCContactDetails();
                    HLCPaymentDetails objPayment1 = new HLCPaymentDetails();
                    
                    ArrayList nonWebFam=new ArrayList();
                    nonWebFam=(ArrayList)addFamNonWeb.elementAt(i);
                    
                    if(nonWebFam!=null) {
                        
                        Debug.print("Array List nonWebFam.size() :"+nonWebFam.size());
                        
                        if(nonWebFam.size()!=0) {
                            
                            objUserMaster=(HLCUserMaster)nonWebFam.get(0);
                            objContactDetail=(HLCContactDetails)nonWebFam.get(1);
                            objMember=(HLCMemberDetails)nonWebFam.get(2);
                            objPayment1=(HLCPaymentDetails)nonWebFam.get(3);
                            
                            String memberid=(String)session.getAttribute("memberId");
                            Debug.print("objMember1.setParentMemberId(memberid) :"+memberid);
                            
                            objMember.setParentMemberId(memberid);
                            
                            Debug.print("::::::: Inside family add-on "+i+"non web user block ::::::");
                            
                            String usrid=remote2.addFamilyAddOn(objUserMaster,objContactDetail);
                            
                            System.out.println("after add-on "+i+" user details insertion usrid :"+usrid);
                            
                            objMember.setUserId(usrid);
                            
                            String memb=remote2.addMemberDetails(objMember, objPayment1);
                            System.out.println("after add-on "+i+" member details insertion member-Id :"+memb);
                            
                            Debug.print("Line 1052");
                            memTId = objMember.getMembershipTypeId();
                            memTname = objMember.getMembershipTypeName();
                            useId = objMember.getUserId();
                            String membTypeId = remote2.getMembershipTypeIdBasedOnUserId(usrid);
                            String[] historyEntry1 = {"register",usrid,membTypeId,"Family Member",memb};
                            historyTable.add(historyEntry1);
                            
                            if(sslResult.equals("0")){
                                String selYear = (String) session.getAttribute("selYear");
                                Debug.print("Inside add-on non web usr add block "+selYear);
                                if(selYear!=null){
                                    int sel_year = Integer.parseInt(selYear);
                                    java.util.Calendar toDay = java.util.Calendar.getInstance();
                                    int newyear = toDay.get(Calendar.YEAR);
                                    int new_month = toDay.get(Calendar.MONTH);
                                    if(sel_year != newyear){
                                        remote2.updateFutureExpDateByYear(memb);
                                    }
                                }
                            }
                            
                            if(sslResult.equals("0")) {
                                boolean stat=memberRemote.editMemberStatus("Active",memb);
                                Debug.print("memberRemote.editMemberStatus(Active,membid) :"+stat);
                                
                                boolean actDat=remote2.updateApprovalDate(memb,act_date_card);
                                Debug.print("remote.updateApprovalDate(memb,new Date()) :"+actDat);
                                
                            }
                            
                            Vector uid=new Vector();
                            
                            boolean rolestat=roleRemote.createDefaultRole(usrid,defrole);
                            Debug.print("rolestatus :" +rolestat);
                            
                            String membid = (String)session.getAttribute("memberId");
                            
                /* =====================================
                 *
                 * Sending confirmation E-mail--1
                 *
                 * ====================================*/
                            
                            String toMailIds[] = {objUserMaster.getEmailId()};
                            EmailContent email=new EmailContent();
                            email.setTo(toMailIds);
                            email.setFrom("info@digiblitz.com");
                            email.setSubject("Your Account Info !");
                            
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
                                    "<span class=\"boldTxt\">Dear " +objUserMaster.getFirstName()+"</span>,<br /><br />"+
                                    "<p>Please save this email for your records. Your account information is as follows:.<p>"+"<p>----------------------------<p>"+"<p>Your Member Id :"+memb+"<p> Your Parent Member Id : "+membId+"<p> ----------------------------<p>"+
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
                    }
                }
            }
            
            if(addFamWeb!=null) {
                
                Debug.print("----- inside family add-on web usr add block ------");
                Debug.print("addFamWeb.size() :"+addFamWeb.size());
                
                for(int i=0;i<addFamWeb.size();i++) {
                    Debug.print("::::::: Inside family add-on "+i+"existing web user block ::::::");
                    
                    ArrayList famWeb=new ArrayList();
                    famWeb=(ArrayList)addFamWeb.get(i);
                    
                    HLCMemberDetails objMember1 = new HLCMemberDetails();
                    HLCPaymentDetails objPayment1 = new HLCPaymentDetails();
                    
                    String memberid=(String)session.getAttribute("memberId");
                    Debug.print("objMember1.setParentMemberId(memberid) :"+memberid);
                    
                    if(famWeb!=null) {
                        Debug.print("Array List famWeb.size() :"+famWeb.size());
                        
                        if(famWeb.size()!=0) {
                            objMember1=(HLCMemberDetails)famWeb.get(0);
                            objPayment1=(HLCPaymentDetails)famWeb.get(1);
                            
                            objMember1.setParentMemberId(memberid);
                            
                            String memb=remote2.addMemberDetails(objMember1, objPayment1);
                            System.out.println("add-on "+i+" existing web user details insertion status :"+memb);
                            
                            Debug.print("Line 1203");
                            memTId = objMember.getMembershipTypeId();
                            memTname = objMember.getMembershipTypeName();
                            String membTypeId = remote2.getMembershipTypeIdBasedOnUserId(objMember1.getUserId());
                            useId = objMember.getUserId();
                            String[] historyEntry1 = {"register",objMember1.getUserId(),membTypeId,"Family Member",memb};
                            historyTable.add(historyEntry1);
                            
                            if(sslResult.equals("0")){
                                String selYear = (String) session.getAttribute("selYear");
                                Debug.print("Inside existing web user block "+selYear);
                                if(selYear!=null){
                                    int sel_year = Integer.parseInt(selYear);
                                    java.util.Calendar toDay = java.util.Calendar.getInstance();
                                    int newyear = toDay.get(Calendar.YEAR);
                                    int new_month = toDay.get(Calendar.MONTH);
                                    if(sel_year != newyear){
                                        remote2.updateFutureExpDateByYear(memb);
                                    }
                                }
                            }
                            
                            if(sslResult.equals("0")) {
                                boolean stat=memberRemote.editMemberStatus("Active",memb);
                                Debug.print("memberRemote.editMemberStatus(Active,membid) :"+stat);
                                
                                boolean actDat=remote2.updateApprovalDate(memb,act_date_card);
                                Debug.print("remote.updateApprovalDate(memb,new Date()) :"+actDat);
                                
                            }
                            
                            // getting member id after insert
                            
                            String mId=remote2.getParentMemberId(loginName);
                            Debug.print("mId :"+mId);
                            
                /* =====================================
                 *
                 * Sending confirmation E-mail---2
                 *
                 * ====================================*/
                            
                            String toMailIds[] = {objMember.getEmailId()};
                            EmailContent email=new EmailContent();
                            email.setTo(toMailIds);
                            email.setFrom("info@digiblitz.com");
                            email.setSubject("Your Account Info !");
                            
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
                                    "<span class=\"boldTxt\">Dear Member,</span>,<br /><br />"+
                                    "<p>Please save this email for your records. Your account information is as follows:.<p>"+"<p>----------------------------<p>"+"<p>Your Member Id :"+memb+"<p> Parent Member Id : "+mId+"<p> ----------------------------<p>"+
                                    
                                    "Thank you for using the service provided by <span class=\"boldTxt\">HLC</span>.</p>"+
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
                    }
                }
            }
            
            
            if(addonMemb!=null) {
                
                Debug.print("----- inside family add-on Existing Member add block ------");
                Debug.print("addonMemb.size() :"+addonMemb.size());
                String membStatus="Pending";
                
                if(sslResult.equals("0")) {
                    membStatus="Active";
                }
                
                for(int i=0;i<addonMemb.size();i++) {
                    Debug.print("::::::: Inside family add-on "+i+"existing Member block ::::::");
                    
                    String[] addExMemb=(String[])addonMemb.get(i);
                    
                    Debug.print("addExMemb memberId :"+addExMemb[0]);
                    Debug.print("addExMemb nameAmat1 :"+addExMemb[1]);
                    Debug.print("addExMemb amatCrdPoss1 :"+addExMemb[2]);
                    Debug.print("addExMemb amatCrdElig1 :"+addExMemb[3]);
                    
                    String parId=(String)session.getAttribute("memberId");
                    String sessfamembId = (String)session.getAttribute("sessfamembId");
                    boolean upStatus=remote2.updateAddOnExistingMember(addExMemb[0],membStatus,parId,paymentIds,sessfamembId);
                    Debug.print("updateAddOnExistingMember(exMid,Pending,parId,paymentId) :"+upStatus);
                    
                    Debug.print("Line 1339:");
                    memTId = objMember.getMembershipTypeId();
                    memTname = objMember.getMembershipTypeName();
                    useId = remote2.getUserIdBasedOnMemberId(addExMemb[0]);
                    String membTypeId = remote2.getMembershipTypeIdBasedOnUserId(useId);
                    String[] historyEntry1 = {"renew",useId,sessfamembId,"Family Member",addExMemb[0]};
                    historyTable.add(historyEntry1);
                    
                    if(sslResult.equals("0")){
                        String selYear = (String) session.getAttribute("selYear");
                        Debug.print("Inside existing Member block "+selYear);
                        if(selYear!=null){
                            int sel_year = Integer.parseInt(selYear);
                            java.util.Calendar toDay = java.util.Calendar.getInstance();
                            int newyear = toDay.get(Calendar.YEAR);
                            int new_month = toDay.get(Calendar.MONTH);
                            if(sel_year != newyear){
                                remote2.updateFutureExpDateByYear(addExMemb[0]);
                            }
                        }
                    }
                    
                    if(sslResult.equals("0")){
                        boolean appStat=remote2.updateApprovalDate(addExMemb[0],act_date_card);
                        Debug.print("remote.updateApprovalDate(mid,appDate) :"+appStat);
                        
                        boolean stat=memberRemote.editMemberStatus("Active",addExMemb[0]);
                        Debug.print("memberRemote.editMemberStatus(Active,membid) :"+stat);
                    }
                    
                    boolean amatCrdPossMembStatus1;
                    
                    if(addExMemb[2]!=null){
                        amatCrdPossMembStatus1=true;
                    } else{
                        amatCrdPossMembStatus1=false;
                    }
                    
                    Debug.print("amatCrdPossMembStatus1 :"+amatCrdPossMembStatus1);
                    
                    boolean amatCrdEligMembStatus1;
                    
                    if(addExMemb[3]!=null){
                        amatCrdEligMembStatus1=true;
                    } else{
                        amatCrdEligMembStatus1=false;
                    }
                    
                    Debug.print("amatCrdEligMembStatus1 :"+amatCrdEligMembStatus1);
                    
                    boolean amatStat=remote2.updateAmatureStatus(addExMemb[0],addExMemb[1],amatCrdPossMembStatus1,amatCrdEligMembStatus1);
                    Debug.print("remote.updateAmatureStatus(addExMemb[0],addExMemb[1],amatCrdPossMembStatus1,amatCrdEligMembStatus1) :"+amatStat);
                    
                    String usrId=remote2.getUserIdBasedOnMemberId(addExMemb[0]);
                    Debug.print("remote.getUserIdBasedOnMemberId(addExMemb[0]) :"+usrId);
                    
                    String toMailId="";
                    toMailId=remote2.getEmailBasedOnUserId(usrId);
                    Debug.print("remote.getEmailBasedOnUserId(usrId) :"+toMailId);
                    
                /* =====================================
                 *
                 * Sending confirmation E-mail----3
                 *
                 * ====================================*/
                    
                    String toMailIds[] = {toMailId};
                    EmailContent email=new EmailContent();
                    email.setTo(toMailIds);
                    email.setFrom("info@digiblitz.com");
                    email.setSubject("Your Account Info !");
                    
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
                            "<span class=\"boldTxt\">Dear Member,</span>,<br /><br />"+
                            "<p>Please save this email for your records. Your account information is as follows:.<p>"+"<p>----------------------------<p>"+"<p>Your Member Id :"+addExMemb[0]+"<p> Parent Member Id : "+parId+"<p> ----------------------------<p>"+
                        /*"<p>Your account would be activated through your confirmation by visiting the following link: <p>"+
                        "<a href=http://192.168.3.98:8090/dashboad-war/uservalidate.do?email="+request.getParameter("email")+"> Click Here to Activate your Account </a>"+*/
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
            }
            
            if(sslResult.equals("0")) {
                boolean stat=memberRemote.editMemberStatus("Active",objMember.getMemberId());
                Debug.print("memberRemote.editMemberStatus(Active,membid) :"+stat);
                
                boolean actDat=remote2.updateApprovalDate(objMember.getMemberId(),act_date_card);
                Debug.print("remote.updateApprovalDate(objMember.getMemberId(),new Date()) :"+actDat);
            }
            
            if(sslResult.equals("0")){
                
                // Update of Future Year
                if(!memTname.equalsIgnoreCase("Life Member")){
                    if(!memTname.equalsIgnoreCase("Subscribing Member")){
                        String selYear = (String) session.getAttribute("selYear");
                        Debug.print("selYear is "+selYear);
                        Debug.print("memTname is "+memTname);
                        Debug.print("tempMid is "+tempMid);
                        if(selYear!=null){
                            int sel_year = Integer.parseInt(selYear);
                            java.util.Calendar toDay = java.util.Calendar.getInstance();
                            int newyear = toDay.get(Calendar.YEAR);
                            int new_month = toDay.get(Calendar.MONTH);
                            if(sel_year != newyear){
                                remote2.updateFutureExpDateByYear(tempMid);
                            }
                        }
                    }
                }
                // End update of next year
                
                if(objPayment!=null){
                    
                    objPayment.setSslResult(sslResult);
                    objPayment.setSslResultMessage(sslResultMessage);
                    objPayment.setSslTxnId(sslTxnId);
                    objPayment.setSslApprovalCode(sslApprovalCode);
                    objPayment.setSslCvv2Response(sslCvv2Response);
                    objPayment.setSslAvsResponse(sslAvsResponse);
                    objPayment.setSslTransactionType(sslTransactionType);
                    objPayment.setSslInvoiceNo(sslInvoiceNo);
                    objPayment.setSslEmail(sslEmail);
                    objPayment.setCheckAmount(0);
                    
                    //boolean pstat=remote2.addPaymentDetails(objPayment);
                    //Debug.print("Sucessfully Payment Inserted:" + objPayment.toString());
                    String sessionInvoiceId = (String) session.getAttribute("sessionInvoiceId");
                        boolean idExist = remote2.isInvoiceIdExist(sessionInvoiceId);
                        Debug.print("idExist value is:" + idExist);
                    
                        boolean pstat = false;
                        if (!idExist) {
                    pstat=objPaySessRemote.createPayment(objPaypal);
                    Debug.print("Sucessfully Payment Inserted:" + objPayment.toString());
                    session.setAttribute("sessionPStat", "true");
                        }
                    
                    //Phone Band
                    HLCAccTransactionVO PhoneVO =  (HLCAccTransactionVO) session.getAttribute("PhoneVO");
                    if(PhoneVO!=null){
                        PhoneVO.setActive_status(true);
                        boolean status = mahaRemote.insertAccountTxnDetails(PhoneVO);
                        Debug.print("mahaRemote.insertAccountTxnDetails(PhoneVO) for Member addon no"+status);
                    }
                    
                    // Mail Options Entry
                    HLCAccTransactionVO transDetVO =  (HLCAccTransactionVO) session.getAttribute("mailOptions");
                    if(transDetVO!=null){
                        transDetVO.setActive_status(true);
                        boolean status = mahaRemote.insertAccountTxnDetails(transDetVO);
                        Debug.print("mahaRemote.insertAccountTxnDetails(transDetVO) for Member addon no"+status);
                    }
                    
                    //Arm Band
                    HLCAccTransactionVO armbandTxnVO =  (HLCAccTransactionVO) session.getAttribute("armBand");
                    if(armbandTxnVO!=null){
                        armbandTxnVO.setActive_status(true);
                        boolean status = mahaRemote.insertAccountTxnDetails(armbandTxnVO);
                        Debug.print("mahaRemote.insertAccountTxnDetails(armbandTxnVO) for Member addon no"+status);
                    }
                    
                    //Donation Process
                    String donationCount = (String) session.getAttribute("donCt");
                    int donCount = 0;
                    if(donationCount!=null || donationCount.trim().length()!=0){
                        donCount = Integer.parseInt(donationCount);
                        ArrayList donationTxnVO = (ArrayList) session.getAttribute("donationNo");
                        Iterator itr = donationTxnVO.iterator();
                        while(itr.hasNext()){
                            HLCAccTransactionVO donaTxnVO = (HLCAccTransactionVO)itr.next();
                            donaTxnVO.setActive_status(true);
                            boolean status = mahaRemote.insertAccountTxnDetails(donaTxnVO);
                            Debug.print("mahaRemote.insertAccountTxnDetails(Donation) for Member addon no"+status);
                        }
                    }
                    
                    //Member Transaction
                    if(session.getAttribute("member")!=null){
                        HLCAccTransactionVO memberTxnVO =  (HLCAccTransactionVO) session.getAttribute("member");
                        if(memberTxnVO!=null){
                            memberTxnVO.setActive_status(true);
                            boolean status = mahaRemote.insertAccountTxnDetails(memberTxnVO);
                            Debug.print("mahaRemote.insertAccountTxnDetails(Member) for Member addon no"+status);
                        }
                    }
                    
                    //Family Account
                    String familycount = (String) session.getAttribute("addCount");
                    Debug.print("familycount is "+familycount);
                    int fcount = 0;
                    if(familycount!=null || familycount.trim().length()!=0){
                        fcount = Integer.parseInt(familycount);
                        if(fcount>0){
                            ArrayList accTxnVO = (ArrayList) session.getAttribute("familyTxnVO");
                            Debug.print("Size of familyTxnVO is "+accTxnVO.size());
                            Iterator itr = accTxnVO.iterator();
                            while(itr.hasNext()){
                                HLCAccTransactionVO familyTxnVO =  (HLCAccTransactionVO) itr.next();
                                familyTxnVO.setActive_status(true);
                                boolean status = mahaRemote.insertAccountTxnDetails(familyTxnVO);
                                Debug.print("mahaRemote.insertAccountTxnDetails(familyTxnVO) for Member addon no"+status);
                            }
                        }
                    }
                    
                    //Upgrade  Member
                    Debug.print("Above Upgrade section");
                    if(session.getAttribute("upgrdemember")!=null){
                        HLCAccTransactionVO upgrdemember =  (HLCAccTransactionVO) session.getAttribute("upgrdemember");
                        if(upgrdemember!=null){
                            upgrdemember.setActive_status(true);
                            boolean status = mahaRemote.insertAccountTxnDetails(upgrdemember);
                            Debug.print("mahaRemote.insertAccountTxnDetails(upgrdemember)  "+status);
                        }
                    }
                    
                    Debug.print("Above renewMemb section");
                    //Renew Member
                    if(session.getAttribute("renewMemb")!=null){
                        HLCAccTransactionVO renewMemb =  (HLCAccTransactionVO) session.getAttribute("renewMemb");
                        if(renewMemb!=null){
                            renewMemb.setActive_status(true);
                            boolean status = mahaRemote.insertAccountTxnDetails(renewMemb);
                            Debug.print("mahaRemote.insertAccountTxnDetails(renewMemb)  "+status);
                        }
                    }
                    
                    Debug.print("Above family add on renew");
                    if(session.getAttribute("famIs")!=null){
                        ArrayList famIs = (ArrayList)session.getAttribute("famIs");
                        if(famIs.size()!=0){
                            for(int i=0;i<famIs.size();i++){
                                String famid = (String) famIs.get(i);
                                boolean update = remote2.updateFutureExpDateByYear(famid);
                                Debug.print("update is "+update);
                            }
                        }
                    }
                    
                    //Renew Family Process
                    Debug.print("==========================================================");
                    Debug.print("Above Renew Family AddOns section");
                    //if(session.getAttribute("cbxct")!=null){
                        if(session.getAttribute("familyChckBox")!=null){
                            String cbxcnt = (String) session.getAttribute("cbxct");
                            Debug.print("cbxcnt: "+cbxcnt);
                            int lstCount = 0;
                            if(cbxcnt!=null || cbxcnt.trim().length()!=0){
                                int size = Integer.parseInt(cbxcnt);
                                ArrayList renew_TxnVO = (ArrayList) session.getAttribute("familyChckBox");
                                Debug.print("renew_TxnVO Size: "+renew_TxnVO.size());
                                Iterator itr = renew_TxnVO.iterator();
                                while(itr.hasNext()){
                                    HLCAccTransactionVO renew_Family = (HLCAccTransactionVO)itr.next();
                                    renew_Family.setActive_status(true);
                                    boolean status = mahaRemote.insertAccountTxnDetails(renew_Family);
                                    Debug.print("mahaRemote.insertAccountTxnDetails(renew_TxnVO) for Member addon no"+status);
                                }
                            }
                        }
                    //}
                    Debug.print("=====================email---4===========================================");
                    //Setting the reconcile & active Status TRUE
                    if(paymentIds!=null || paymentIds.trim().length()!=0){
                        boolean Update_Status = mahaRemote.updateRecouncilActiveStatusAccTxnDetails(paymentIds);
                        Debug.print("Update Status "+Update_Status);
                    }
                    
                    double payAmt=objPayment.getAmount();
                    String amt=String.valueOf(payAmt);
                    
                    objMember=(HLCMemberDetails)session.getAttribute("objMember");
                    
                    request.setAttribute("membTyp",objMember.getMembershipTypeName());
                    request.setAttribute("amount",amt);
                    String memID = (String) session.getAttribute("memberId");
                    String[] userDet=remote2.getUserDetailsForEmail(userId);
                    String memTypeName=objMember.getMembershipTypeName();
                    if(pstat == true){
                        String emailId = (String)session.getAttribute("emailId");
                        String toMailIds[] = {emailId};
                        EmailContent email=new EmailContent();
                        email.setTo(toMailIds);
                        email.setFrom("info@digiblitz.com");
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
                                "Your Membership ID:"+objMember.getMemberId()+" <br/>"+
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
                                "Your Membership ID:"+objMember.getMemberId()+" <br/>"+
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
                                "Your Membership ID:"+objMember.getMemberId()+" <br/>"+
                                "Membership Type:"+objMember.getMembershipTypeName()+ "<br/>"+
                                "Expiration Date:"+remote2.getExpiredDate(memID)+ "<br/>"+
                                "First Name:"+userDet[1]+"<br/>"+
                                "Last Name: "+userDet[2]+"<br/>"+
                                "City:"+userDet[3]+ "<br/>"+ 
                                "State:"+userDet[4]+ "<br/></p>"+
                                "<p>Your membership packet will arrive within 3-4 weeks.  </p> <br/>"+
                                "<p><span class=\"asterisk\"><strong>Special Note:</strong></span> This membership level allows you to compete from Beginner novice level thru Training. However, you must upgrade to a full or life member in order to compete at the preliminary level and above.  (Reminder, if you are moving up to the preliminary level do not forget to upgrade your horse!.)</p><br/>"+
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
                        
                        //// History Table Entries
                        Debug.print("History Table Entries");
                        //ArrayList historyTable = (ArrayList) session.getAttribute("historyTable");
                        if(historyTable!=null){
                            Debug.print("Size of the history is "+historyTable.size());
                            if(historyTable.size()>0){
                                Iterator itr = historyTable.iterator();
                                if(itr.hasNext()){
                                    
                                    HLCMemberHistoryDetail detailVO = new HLCMemberHistoryDetail();
                                    while(itr.hasNext()){
                                        String[] historyEntry3 = (String[]) itr.next();
                                        
                                        String regiTyp = "";
                                        String statid= "";
                                        String usemmId = "";
                                        String memTyeId = "";
                                        String memTypNme = "";
                                        String tempMembId= "";
                                        String zipCode = "";
                                        if(sslResult.equals("0")){
                                            statid=remote2.getStatusIBasedOnStatus("Active");
                                        } else{
                                            statid=remote2.getStatusIBasedOnStatus("Pending");
                                        }
                                        if(historyEntry3[0]==null){
                                            regiTyp = "Register";
                                        } else{
                                            regiTyp = historyEntry3[0];
                                        }
                                        usemmId = historyEntry3[1];
                                        memTyeId = historyEntry3[2];
                                        memTypNme = historyEntry3[3];
                                        tempMembId = historyEntry3[4];
                                        Date new_year = new Date();
                                        if(tempMembId!=null && tempMembId.trim().length()!=0) {
                                            new_year = remote2.getExpiredDate(tempMembId);
                                        }
                                        
                                        String temp = new_year.toString();
                                        Debug.print("temp is "+new_year.toString());
                                        //2008-11-30
                                        String[] splitr = temp.split("-");
                                        System.out.println("splitr[0]"+splitr[0]);
                                        
                                        String histPay_id = (String) session.getAttribute("histpayId");
                                        
                                        int newVal = 0;
                                        if(splitr[0]!=null){
                                            newVal = Integer.parseInt(splitr[0]);
                                        }
                                        
                                        if(useId!=null && useId.trim().length()!=0){
                                            zipCode = remote2.getZipCodeOnUserId(useId);
                                        } else{
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
                                        detailVO.setUser_id(usemmId);
                                        detailVO.setSubExpDate(objMember.getSubExpDate());
                                        detailVO.setAmatName(objMember.getAmateurName());
                                        detailVO.setAmatDec1(objMember.isAmateurDec1());
                                        detailVO.setAmatDec2(objMember.isAmateurDec2());
                    
                                        Debug.print("Near History Updat Status :"+objMember.getSubExpDate());
                                        Debug.print("History Updat Status in MemberRenCardPay");
                                        
                                        boolean historyUpdate = remote2.insertMemberHistoryDetails(detailVO);
                                        Debug.print("historyUpdate "+historyUpdate);
                                    }
                                }
                            }
                        }
                    }
                }
                String sesMem = (String) session.getAttribute("memberId");
                session.setAttribute("exp_date",remote2.getExpiredDate(sesMem));
                Debug.print("sesMem is "+sesMem);
                fwd="confirm";
            } else{
                
                Debug.print("Transaction Declined..:");
                Debug.print("amount1:" + amount1);
                
                objPayment.setSslResult(sslResult);
                objPayment.setSslResultMessage(sslResultMessage);
                objPayment.setSslTxnId(sslTxnId);
                objPayment.setSslApprovalCode(sslApprovalCode);
                objPayment.setSslCvv2Response(sslCvv2Response);
                objPayment.setSslAvsResponse(sslAvsResponse);
                objPayment.setSslTransactionType(sslTransactionType);
                objPayment.setSslInvoiceNo(sslInvoiceNo);
                objPayment.setSslEmail(sslEmail);
                objPayment.setCheckAmount(0);
                
                boolean pstat=remote2.addPaymentDetails(objPayment);
                Debug.print("Sucessfully Payment Inserted:" + objPayment.toString());
                
                Debug.print("Declined Payment Sucessfully Inserted Status :" + pstat);
                
                //Phone Band
                HLCAccTransactionVO PhoneVO =  (HLCAccTransactionVO) session.getAttribute("PhoneVO");
                if(PhoneVO!=null){
                    PhoneVO.setActive_status(false);
                    boolean status = mahaRemote.insertAccountTxnDetails(PhoneVO);
                    Debug.print("mahaRemote.insertAccountTxnDetails(PhoneVO) for Member addon no"+status);
                }
                
                //Arm Band
                HLCAccTransactionVO armbandTxnVO =  (HLCAccTransactionVO) session.getAttribute("armBand");
                if(armbandTxnVO!=null){
                    armbandTxnVO.setActive_status(false);
                    boolean status = mahaRemote.insertAccountTxnDetails(armbandTxnVO);
                    Debug.print("mahaRemote.insertAccountTxnDetails(armbandTxnVO) for Member addon no"+status);
                }
                
                //Donation Process
                String donationCount = (String) session.getAttribute("donCt");
                int donCount = 0;
                if(donationCount!=null || donationCount.trim().length()!=0){
                    
                    donCount = Integer.parseInt(donationCount);
                    ArrayList donationTxnVO = (ArrayList) session.getAttribute("donationNo");
                    Iterator itr = donationTxnVO.iterator();
                    while(itr.hasNext()){
                        HLCAccTransactionVO donaTxnVO = (HLCAccTransactionVO)itr.next();
                        donaTxnVO.setActive_status(false);
                        boolean status = mahaRemote.insertAccountTxnDetails(donaTxnVO);
                        Debug.print("mahaRemote.insertAccountTxnDetails(Donation) for Member addon no"+status);
                    }
                }
                
                //Member Transaction
                if(session.getAttribute("member")!=null){
                    HLCAccTransactionVO memberTxnVO =  (HLCAccTransactionVO) session.getAttribute("member");
                    if(memberTxnVO!=null){
                        memberTxnVO.setActive_status(false);
                        boolean status = mahaRemote.insertAccountTxnDetails(memberTxnVO);
                        Debug.print("mahaRemote.insertAccountTxnDetails(Member) for Member addon no"+status);
                    }
                }
                
                //Family Account
                String familycount = (String) session.getAttribute("addCount");
                Debug.print("familycount is "+familycount);
                int fcount = 0;
                if(familycount!=null || familycount.trim().length()!=0){
                    fcount = Integer.parseInt(familycount);
                    if(fcount>0){
                        ArrayList accTxnVO = (ArrayList) session.getAttribute("familyTxnVO");
                        Debug.print("Size of familyTxnVO is "+accTxnVO.size());
                        Iterator itr = accTxnVO.iterator();
                        while(itr.hasNext()){
                            HLCAccTransactionVO familyTxnVO =  (HLCAccTransactionVO) itr.next();
                            familyTxnVO.setActive_status(false);
                            boolean status = mahaRemote.insertAccountTxnDetails(familyTxnVO);
                            Debug.print("mahaRemote.insertAccountTxnDetails(familyTxnVO) for Member addon no"+status);
                        }
                    }
                }
                
                //Upgrade  Member
                
                if(session.getAttribute("upgrdemember")!=null){
                    HLCAccTransactionVO upgrdemember =  (HLCAccTransactionVO) session.getAttribute("upgrdemember");
                    if(upgrdemember!=null){
                        upgrdemember.setActive_status(false);
                        boolean status = mahaRemote.insertAccountTxnDetails(upgrdemember);
                        Debug.print("mahaRemote.insertAccountTxnDetails(upgrdemember)  "+status);
                    }
                }
                
                //Renew Member
                if(session.getAttribute("renewMemb")!=null){
                    HLCAccTransactionVO renewMemb =  (HLCAccTransactionVO) session.getAttribute("renewMemb");
                    if(renewMemb!=null){
                        renewMemb.setActive_status(false);
                        boolean status = mahaRemote.insertAccountTxnDetails(renewMemb);
                        Debug.print("mahaRemote.insertAccountTxnDetails(renewMemb)  "+status);
                    }
                }
                
                //Renew Family Process
                Debug.print("=========================================================================================");
                Debug.print("Above Renew Family AddOns section");
                //if(session.getAttribute("cbxct")!=null){
                    String cbxcnt = (String) session.getAttribute("cbxct");
                    Debug.print("cbxcnt: "+cbxcnt);
                    int lstCount = 0;
                    if(cbxcnt!=null || cbxcnt.trim().length()!=0){
                        int size = Integer.parseInt(cbxcnt);
                        ArrayList renew_TxnVO = (ArrayList) session.getAttribute("familyChckBox");
                        if(renew_TxnVO!=null && renew_TxnVO.size()>0){
                            Iterator itr = renew_TxnVO.iterator();
                            while(itr.hasNext()){
                                HLCAccTransactionVO renew_Family = (HLCAccTransactionVO)itr.next();
                                renew_Family.setActive_status(false);
                                boolean status = mahaRemote.insertAccountTxnDetails(renew_Family);
                                Debug.print("mahaRemote.insertAccountTxnDetails(renew_TxnVO) for Member addon no"+status);
                            }
                        }
                    }
                    Debug.print("======================================================================================");
               //}
                
                if(historyTable!=null){
                    Debug.print("*************************Size of the history In Decline Part is: "+historyTable.size());
                    if(historyTable.size()>0){
                        Iterator itr = historyTable.iterator();
                        if(itr.hasNext()){
                            
                            HLCMemberHistoryDetail detailVO = new HLCMemberHistoryDetail();
                            while(itr.hasNext()){
                                String[] historyEntry3 = (String[]) itr.next();
                                
                                String regiTyp = "";
                                String statid= "";
                                String usemmId = "";
                                String memTyeId = "";
                                String memTypNme = "";
                                String tempMembId= "";
                                String zipCode = "";
                                statid=remote2.getStatusIBasedOnStatus("Pending");
                                if(historyEntry3[0]==null){
                                    regiTyp = "Register";
                                } else{
                                    regiTyp = historyEntry3[0];
                                }
                                usemmId = historyEntry3[1];
                                memTyeId = historyEntry3[2];
                                memTypNme = historyEntry3[3];
                                tempMembId = historyEntry3[4];
                                Date new_year = new Date();
                                if(tempMembId!=null && tempMembId.trim().length()!=0) {
                                    new_year = remote2.getExpiredDate(tempMembId);
                                }
                                
                                String temp = new_year.toString();
                                Debug.print("temp is "+new_year.toString());
                                //2008-11-30
                                String[] splitr = temp.split("-");
                                System.out.println("splitr[0]"+splitr[0]);
                                
                                String histPay_id = (String) session.getAttribute("histpayId");
                                
                                int newVal = 0;
                                if(splitr[0]!=null){
                                    newVal = Integer.parseInt(splitr[0]);
                                }
                                
                                if(useId!=null && useId.trim().length()!=0){
                                    zipCode = remote2.getZipCodeOnUserId(useId);
                                } else{
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
                                detailVO.setUser_id(usemmId);
                                detailVO.setSubExpDate(objMember.getSubExpDate());
                                detailVO.setAmatName(objMember.getAmateurName());
                                detailVO.setAmatDec1(objMember.isAmateurDec1());
                                detailVO.setAmatDec2(objMember.isAmateurDec2());
                                Debug.print("Near History Updat Status :"+objMember.getSubExpDate());
                                Debug.print("History Updat Status");
                                
                                boolean historyUpdate = remote2.insertMemberHistoryDetails(detailVO);
                                Debug.print("*******************************historyUpdate In Decline Section: "+historyUpdate);
                            }
                        }
                    }
                }
                
                
                
                request.setAttribute("amount",amount1);
                HLCPaymentDetailVO objPay = new HLCPaymentDetailVO();
                objPay.setPaymentId(objPayment.getPaymentId());
                objPay.setParentPaymentId(objPayment.getParentPaymentId());
                //objPay.setActiveStaus(objPayment.);
                objPay.setAmount(objPayment.getAmount());
                objPay.setCcCvvid(objPayment.getCcCvvid());
                objPay.setCcExpMonth(objPayment.getCcExpMonth());
                objPay.setCcExpYear(objPayment.getCcExpYear());
                objPay.setCcName(objPayment.getCcName());
                objPay.setCcNumber(objPayment.getCardNo());
                objPay.setCcType(objPayment.getCcType());
                objPay.setPaymentStatus(objPayment.getPaymentStatus());
                objPay.setPendingAmount(objPayment.getPendingAmount());
                objPay.setSslApprovalCode(objPayment.getSslApprovalCode());
                objPay.setSslAvsResponse(objPayment.getSslAvsResponse());
                objPay.setSslCvv2Response(objPayment.getSslCvv2Response());
                objPay.setSslEmail(objPayment.getSslEmail());
                objPay.setSslInvoiceNo(objPayment.getSslInvoiceNo());
                objPay.setSslResult(objPayment.getSslResult());
                objPay.setSslResultMessage(objPayment.getSslResultMessage());
                objPay.setSslTransactionType(objPayment.getSslTransactionType());
                objPay.setSslTxnId(objPayment.getSslTxnId());
                objPay.setUserId(objPayment.getUserId());
                
                session.setAttribute("objPayDet",objPay);
                if(session.getAttribute("loggedBy")==null && Integer.parseInt(sslResult)==1){
                    String emailId = (String)session.getAttribute("emailId");
                    String toMailIds[] = {emailId};
                    EmailContent email=new EmailContent();
                    email.setTo(toMailIds);
                    email.setFrom("anandv@digiblitz.com");
                    email.setSubject("Your Registration Status"); 
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
                                "<p>We are sorry, but your payment for membership with the HLC has been declined. This registration is not active. Please try another form of payment or contact us at (703) 779-0440 and press 1.</p><br/>"+
                                "<p>Sincerely,</p><br/>"+
                                "<p>HLC Staff</p>"+
                                " </table>"+
                                "</td></tr>"+
                                "</table>";
                                email.setBody(content);
                                EmailEngine emailEngine = new EmailEngine();
                                boolean emailFlag = emailEngine.sendMimeEmail(email);
                                Debug.print("Email sent sucessfully :"+emailFlag);
                        
                }
                fwd="decline";
            }
            return mapping.findForward(fwd);
        } catch(Exception e){
            e.printStackTrace();
            Debug.print("Error in MemberRenCardPay "+e);
        }
        return null;
    }
    public static Context getInitialContext() throws javax.naming.NamingException {
        Properties p =new Properties();
        p.setProperty( "java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory" );
        p.setProperty( "java.naming.factory.url.pkgs", "org.jboss.naming:org.jnp.interfaces" );
        p.setProperty( "java.naming.provider.url", "localhost:11199" );
        return new javax.naming.InitialContext(p);
    }
    
}
