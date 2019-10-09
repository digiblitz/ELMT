/*
 * AdminMembPaymentAction.java
 *
 * Created on October 16, 2006, 7:23 PM
 */

package com.mrm.action;

import com.hlcaccounts.session.HLCMahanadhiSessionRemote;
import com.hlcaccounts.session.HLCMahanadhiSessionRemoteHome;
import com.hlcaccounts.util.HLCAccTransactionVO;
import com.hlcaccounts.util.HLCAccTxnTypeDetailVO;
import com.hlcform.stateless.HLCkaverystatelessRemote;
import com.hlcform.stateless.HLCkaverystatelessRemoteHome;
import com.hlcform.util.HLCMemberDetails;
import com.hlcform.util.HLCPaymentDetailVO;
import com.hlcform.util.HLCPaymentDetails;
import com.hlckavery.statful.HLCKaverySessionBeanStatfulRemote;
import com.hlckavery.statful.HLCKaverySessionBeanStatfulRemoteHome;
import com.hlcmrm.util.Debug;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
 * @author karthikeyan
 * @version
 */

public class AdminMembPaymentAction extends Action {
    
     
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        String fwd="";
        HttpSession session = request.getSession();
        
        String reqIp=request.getRemoteAddr();
        Debug.print(" Request IP :"+reqIp);
        
        MessageResources mr=getResources(request);
        
        String namingfactory=mr.getMessage("ejbclient.namingfactory");
        String contextfactory=mr.getMessage("ejbclient.contextfactory");
        String urlprovider=mr.getMessage("ejbclient.urlprovider");
        String lookupip=mr.getMessage("ejbclient.ip");
        String jndiname=mr.getMessage("jndi.usrreg");
        String jndiKavery=mr.getMessage("jndi.kavery");
        
        try
        {
            
        System.setProperty(namingfactory,contextfactory);
        System.setProperty(urlprovider,lookupip);
        Context jndiContext = new InitialContext();
        Object objref = jndiContext.lookup(jndiname);
        
        HLCkaverystatelessRemoteHome home = (HLCkaverystatelessRemoteHome)
        javax.rmi.PortableRemoteObject.narrow(objref,HLCkaverystatelessRemoteHome.class);
        HLCkaverystatelessRemote remote = home.create();
        
        Object objref1 = jndiContext.lookup(jndiKavery);
       
        HLCKaverySessionBeanStatfulRemoteHome home1 = (HLCKaverySessionBeanStatfulRemoteHome)
        javax.rmi.PortableRemoteObject.narrow(objref1,HLCKaverySessionBeanStatfulRemoteHome.class);
        HLCKaverySessionBeanStatfulRemote remote1 = home1.create();

//Transaction         
        String mahanadhiJndi=mr.getMessage("jndi.acc");
        Debug.print("mahanadhiJndi  :" +mahanadhiJndi);
        Object maha=jndiContext.lookup(mahanadhiJndi);
        HLCMahanadhiSessionRemoteHome mahaHome = (HLCMahanadhiSessionRemoteHome)
        javax.rmi.PortableRemoteObject.narrow(maha,HLCMahanadhiSessionRemoteHome.class);
        HLCMahanadhiSessionRemote mahaRemote = mahaHome.create();        
                
        String process=request.getParameter("process");
       
        if(process!=null)
        {
               
        if(process.equalsIgnoreCase("disp"))
        {
            Debug.print("request.getParameter(process) :"+process);
            String payId=request.getParameter("pid");
            String amt=(String)session.getAttribute("amt");
            String usrStat=request.getParameter("usr");
            String userId=request.getParameter("userId");
            String defStat=request.getParameter("defStat");
            String cStatus=request.getParameter("cardStatus");
            
            String singlePayId = remote.getLastSubPaymentIdOnParentId(payId);
            
            if(singlePayId==null || singlePayId.trim().length()==0){
                singlePayId = "";
            }
            
            if(userId==null)
            {
                userId=(String)session.getAttribute("userId");
            }
            
            Debug.print("Defeciency payment Status :"+defStat);
            Debug.print("userId :"+userId);
            Debug.print("Payment Id :"+payId);
            Debug.print("amount :"+amt);
            
            request.setAttribute("cStatus",cStatus);
            request.setAttribute("userId",userId);
            request.setAttribute("payId",payId);
            request.setAttribute("amt",amt);
            request.setAttribute("usrStat",usrStat);
            request.setAttribute("defStat",defStat);
            request.setAttribute("singlePayId",singlePayId);
            session.setAttribute("usrStat",usrStat);
            session.setAttribute("newRetrypayId",payId);
            //session.setAttribute("amt",null);
            
            fwd="dispPayment";
        }
        
         if(process.equalsIgnoreCase("payment"))
        {
            String chkAmt2 = null;

                    String newPaymentId = "";
                    //VaigaiSessionRemote vaiRemote = initializeVaigaiEJB(request);
                    newPaymentId = remote.getNextId();
                    Debug.print("new generated PaymentId in admin insert servlet :" + newPaymentId);

                    Debug.print("request.getParameter(process) :" + process);
                    session.setAttribute("histpayId", newPaymentId);
                    
                    HLCPaymentDetails payDet=(HLCPaymentDetails)session.getAttribute("objPayment");
                    HLCAccTransactionVO PhoneVO =  (HLCAccTransactionVO)session.getAttribute("PhoneVO");
                    HLCAccTransactionVO transDetVO =  (HLCAccTransactionVO) session.getAttribute("mailOptions");
                    HLCAccTransactionVO armbandTxnVO=(HLCAccTransactionVO) session.getAttribute("armBand");
                     HLCAccTransactionVO PhaccTxnVO4 =  (HLCAccTransactionVO) session.getAttribute("PhaccTxnVO4");
                      HLCAccTransactionVO memberTxnVO =  (HLCAccTransactionVO) session.getAttribute("member");
                      HLCAccTransactionVO PhoneVO1 =  (HLCAccTransactionVO) session.getAttribute("PhaccTxnVO1");
                      HLCAccTransactionVO upgrdemember1 =  (HLCAccTransactionVO) session.getAttribute("upgrdemember");
                      if(upgrdemember1!=null)
                      {
                          upgrdemember1.setPayment_id(newPaymentId);
                          upgrdemember1.setPayment_mode(request.getParameter("cardselect"));
                          session.setAttribute("upgrdemember",upgrdemember1);
                      }
                      
                      if(PhoneVO1!=null)
                      {
                          PhoneVO1.setPayment_id(newPaymentId);
                     PhoneVO1.setPayment_mode(request.getParameter("cardselect"));
                     session.setAttribute("PhaccTxnVO1",PhoneVO1);
                      }
                     if(PhoneVO!=null)
                     {
                     PhoneVO.setPayment_id(newPaymentId);
                     PhoneVO.setPayment_mode(request.getParameter("cardselect"));
                     session.setAttribute("PhoneVO",PhoneVO);
                     }
                    payDet.setPaymentId(newPaymentId);
                    if(transDetVO!=null)
                    {
                    transDetVO.setPayment_id(newPaymentId);
                    transDetVO.setPayment_mode(request.getParameter("cardselect"));
                    session.setAttribute("mailOptions",transDetVO);
                    }
                    if(armbandTxnVO!=null)
                    {
                    armbandTxnVO.setPayment_mode(request.getParameter("cardselect"));
                    armbandTxnVO.setPayment_id(newPaymentId);
                    session.setAttribute("armBand",armbandTxnVO);
                    }
                    
                    if(PhaccTxnVO4!=null)
                    {
                    PhaccTxnVO4.setPayment_mode(request.getParameter("cardselect"));
                    PhaccTxnVO4.setPayment_id(newPaymentId);
                    session.setAttribute("PhaccTxnVO4",PhaccTxnVO4);
                    }
                    if(memberTxnVO!=null)
                    {
                    memberTxnVO.setPayment_mode(request.getParameter("cardselect"));
                    memberTxnVO.setPayment_id(newPaymentId);
                    session.setAttribute("member",memberTxnVO);
                    }
                    String donationCount = (String) session.getAttribute("donCt");
                int donCount = 0;
                if(donationCount!=null || donationCount.trim().length()!=0){
                    
                    donCount = Integer.parseInt(donationCount);
                    ArrayList donationTxnVO = (ArrayList) session.getAttribute("donationNo");
                    Iterator itr = donationTxnVO.iterator();
                    while(itr.hasNext()){
                        HLCAccTransactionVO donaTxnVO = (HLCAccTransactionVO)itr.next();
                          donaTxnVO.setPayment_id(newPaymentId);
                          donaTxnVO.setPayment_mode(request.getParameter("cardselect"));
                    }
                    session.setAttribute("donationNo",donationTxnVO);
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
                        Iterator itr1 = accTxnVO.iterator();
                        while(itr1.hasNext()){
                            HLCAccTransactionVO familyTxnVO =  (HLCAccTransactionVO) itr1.next(); 
                            familyTxnVO.setPayment_id(newPaymentId);
                            familyTxnVO.setPayment_mode(request.getParameter("cardselect"));
                        }
                         session.setAttribute("familyTxnVO",accTxnVO);
                    }
                }
                      
                     //Upgrade  Member
                Debug.print("Above Upgrade section");                
                if(session.getAttribute("renStat")!=null){
                    String renStat = (String) session.getAttribute("renStat");
                    if(renStat.equalsIgnoreCase("upgrade")){
                        HLCAccTransactionVO upgrdemember =  (HLCAccTransactionVO) session.getAttribute("upgrdemember");
                        if(upgrdemember!=null){
                            upgrdemember.setPayment_id(newPaymentId) ; 
                            upgrdemember.setPayment_mode(request.getParameter("cardselect"));
                        }
                        session.setAttribute("upgrdemember",upgrdemember);
                    }
                }
                        
                        
                        
                     if(session.getAttribute("renewMemb")!=null){
                    HLCAccTransactionVO renewMemb =  (HLCAccTransactionVO) session.getAttribute("renewMemb");
                    if(renewMemb!=null){
                        renewMemb.setPayment_id(newPaymentId) ; 
                            renewMemb.setPayment_mode(request.getParameter("cardselect"));   
                    }
                    session.setAttribute("renewMemb",renewMemb);
                     }
                    
                    
                    
                    if(session.getAttribute("cbxct")!=null){
                    if(session.getAttribute("renewFamilyMemb")!=null){
                        String cbxcnt = (String) session.getAttribute("cbxct");
                        int lstCount = 0;
                        if(cbxcnt!=null || cbxcnt.trim().length()!=0){
                            int size = Integer.parseInt(cbxcnt);
                            ArrayList renew_TxnVO = (ArrayList) session.getAttribute("renewFamilyMemb");
                            Iterator itr2 = renew_TxnVO.iterator();
                            while(itr2.hasNext()){
                                HLCAccTransactionVO renew_Family = (HLCAccTransactionVO)itr2.next();
                                renew_Family.setPayment_id(newPaymentId);
                                renew_Family.setPayment_mode(request.getParameter("cardselect"));
                                
                            }
                            session.setAttribute("renewFamilyMemb",renew_TxnVO);
                        }
                    }
                    }
                            
                            
                            HLCMemberDetails membVO=(HLCMemberDetails)session.getAttribute("objMember");
                            if(membVO!=null)
                            {
                                          membVO.setPaymentId(newPaymentId);
                                          
                                          session.setAttribute("objMember",membVO);
                            }
                            
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
                                    renew_Family.setPayment_id(newPaymentId);
                                    renew_Family.setPayment_mode(request.getParameter("cardselect"));
                                }
                                session.setAttribute("familyChckBox",renew_TxnVO);
                            }
                            }
                                            
                    String paymentId = request.getParameter("paymentId");

                    Debug.print("request.getParameter(paymentId) :" + paymentId);

                    String userId = "";
                    userId = request.getParameter("userId");
                    Debug.print("userId :" + userId);

                    String defStat = request.getParameter("defStat");
                    Debug.print("request.getParameter(defStat) :" + defStat);
                    session.setAttribute("defStat", defStat);

                    String usrStat = request.getParameter("usrStat");
                    session.setAttribute("usrStat", usrStat);

                    String ccStatus = request.getParameter("ccStatus");
                    session.setAttribute("ccStatus", ccStatus);
                    Debug.print("ccStatus :" + ccStatus);

                    Object objref3 = jndiContext.lookup(jndiKavery);
                    Debug.print("jndiKavery:" + jndiKavery);

                    HLCKaverySessionBeanStatfulRemoteHome kaveryHome = (HLCKaverySessionBeanStatfulRemoteHome) javax.rmi.PortableRemoteObject.narrow(objref3, HLCKaverySessionBeanStatfulRemoteHome.class);
                    HLCKaverySessionBeanStatfulRemote kaveryRemote = kaveryHome.create();

                    /*
                     * payment details
                     */

                    String r11 = request.getParameter("r11");

                    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyy");

                    HLCPaymentDetailVO objPayment = new HLCPaymentDetailVO();
                    objPayment.setIpAddress(reqIp);
                    objPayment.setPaymentId(paymentId);
                    objPayment.setUserId(userId);
                    

                    // -------------------------- choose for payment mode check ----------------------- 

                    Date check_date = null;

                    if (r11.equalsIgnoreCase("check")) {

                        if (request.getParameter("checkDate") != null && request.getParameter("checkDate").trim().length() != 0) {
                            check_date = (Date) sdf.parse(request.getParameter("checkDate"));
                            Debug.print("check_date :" + check_date.toString());
                        } else {
                            check_date = null;
                        }

                        objPayment.setUserId(userId);
                        objPayment.setCcName(null);
                        objPayment.setCcType(null);
                        objPayment.setCcNumber("0");
                        objPayment.setCcExpMonth(0);
                        objPayment.setCcExpYear(0);
                        objPayment.setCcCvvid(0);
                        objPayment.setBankName(request.getParameter("inFavorof"));
                        objPayment.setCheckDate(check_date);

                        if (request.getParameter("checkNo") != null && request.getParameter("checkNo").trim().length() != 0) {
                            objPayment.setCheckNumber(request.getParameter("checkNo"));
                        } else {
                            objPayment.setCheckNumber("0");

                        }

                        if (request.getParameter("chckAmount") != null && request.getParameter("chckAmount").trim().length() != 0) {
                            objPayment.setCheckAmount(Float.valueOf(request.getParameter("chckAmount")).floatValue());

                            String comment = "Excess Check Payment Done For Membership Registration";

                            float chkAmt = Float.valueOf(request.getParameter("tot_amt")).floatValue();
                            float checkAmount = Float.valueOf(request.getParameter("chckAmount")).floatValue();

                            /*if(chkAmt>checkAmount)
                            {
                            float diffAmt = chkAmt-checkAmount;
                            Debug.print("Check and Total Amount difference :"+diffAmt);
                            objPayment.setPendingAmount(diffAmt);
                            }
                            boolean refundStat=kaveryRemote.createMembershipRefundDetails(userId,comment,chkAmt,objPayment.getCheckAmount());
                            Debug.print("kaveryRemote.createMembershipRefundDetails() :"+refundStat);*/

                            String singlePayId = request.getParameter("singlePayId");
                            Debug.print("singlePayId is " + singlePayId);
                            Debug.print("singlePayId is " + request.getParameter("chckAmount"));

                            if (singlePayId != null && singlePayId.trim().length() != 0) {
                                remote.updateCheckAmountOnPaymentId(singlePayId, checkAmount);
                            }

                        } else {
                            objPayment.setCheckAmount(0);
                        }

                        if (request.getParameter("tot_amt") != null && request.getParameter("tot_amt").trim().length() != 0) {
                            objPayment.setAmount(Double.valueOf(request.getParameter("tot_amt")).doubleValue());
                        } else {
                            objPayment.setAmount(0);
                        }

                        String nameOnchk = request.getParameter("nameOnchk");

                        objPayment.setCheckName(nameOnchk);

                        objPayment.setPaymentDate(new Date());

                        objPayment.setPaymentStatus("check");

                    }


                    //------------------------- choose for payment mode card ------------------------

                    int CcExpMonth = 0;
                    int CcExpYear = 0;
                    int CcCvvid = 0;
                    double amount = 0;
                    long CcNumber = 0;
                    long checkNumber = 0;



                    Date comp_date = null;
                    String ccType = null;
                    String ccName = null;

                    if (r11.equalsIgnoreCase("card")) {

                        //Debug.print("inside payment card select ......"+objPayment.getUserId());

                        if (request.getParameter("printName") != null && request.getParameter("printName").trim().length() != 0) {
                            objPayment.setCcName(request.getParameter("printName"));
                            payDet.setCcName(request.getParameter("printName"));
                        } else {
                            objPayment.setCcName(null);
                            payDet.setCcName(null);
                        }

                        if (request.getParameter("cardselect") != null && request.getParameter("cardselect").trim().length() != 0) {
                            objPayment.setCcType(request.getParameter("cardselect"));
                            payDet.setCcType(request.getParameter("cardselect"));
                        } else {
                            objPayment.setCcType(null);
                            payDet.setCcType(null);

                        }

                        if (request.getParameter("cardNo") != null && request.getParameter("cardNo").trim().length() != 0) {
                            objPayment.setCcNumber(request.getParameter("cardNo"));
                            payDet.setCcNumber(request.getParameter("cardNo"));
                        } else {
                            objPayment.setCcNumber("0");
                            payDet.setCcNumber("0");
                        }

                        if (request.getParameter("expirymonth") != null && request.getParameter("expirymonth").trim().length() != 0) {
                            objPayment.setCcExpMonth(Integer.parseInt(request.getParameter("expirymonth")));
                            payDet.setCcExpMonth(Integer.parseInt(request.getParameter("expirymonth")));
                        } else {
                            objPayment.setCcExpMonth(0);
                            payDet.setCcExpMonth(0);
                        }

                        if (request.getParameter("expiryyear") != null && request.getParameter("expiryyear").trim().length() != 0) {
                            objPayment.setCcExpYear(Integer.parseInt(request.getParameter("expiryyear")));
                            payDet.setCcExpYear(Integer.parseInt(request.getParameter("expiryyear")));
                        } else {
                            objPayment.setCcExpYear(0);
                            payDet.setCcExpYear(0);
                        }

                        if (request.getParameter("cVVNo") != null && request.getParameter("cVVNo").trim().length() != 0) {
                            if (!request.getParameter("cVVNo").equals("")) {
                                objPayment.setCcCvvid(Integer.parseInt(request.getParameter("cVVNo")));
                                payDet.setCcCvvid(Integer.parseInt(request.getParameter("cVVNo")));

                            } else {
                                objPayment.setCcCvvid(0);
                                payDet.setCcCvvid(0);

                            }
                        } else {
                            objPayment.setCcCvvid(0);
                            payDet.setCcCvvid(0);
                            request.setAttribute("chkDigit", "0");
                        }

                        objPayment.setBankName(null);
                        objPayment.setCheckDate(null);
                        objPayment.setCheckNumber("0");
                        objPayment.setCheckName(null);
                        objPayment.setCheckAmount(0);

                        if (request.getParameter("tot_amt") != null && request.getParameter("tot_amt").trim().length() != 0) {
                            objPayment.setAmount(Double.valueOf(request.getParameter("tot_amt")).doubleValue());
                        } else {
                            objPayment.setAmount(0);
                        }

                        objPayment.setPaymentDate(new Date());
                        objPayment.setPaymentStatus("card");

                        Debug.print("inside payment card final ......" + objPayment.getAmount());
                    }


                    if (r11.equalsIgnoreCase("card")) {

                        String expMon = String.valueOf(objPayment.getCcExpMonth());
                        String expYear = String.valueOf(objPayment.getCcExpYear());
                        if (expMon.trim().length() == 1) {
                            expMon = "0" + expMon;
                        }
                        // if(expYear.trim().length()==1) {
                        Debug.print("expYear:" + expYear);
                        //expYear = expYear.substring(2);
                        //
                        Debug.print("expMon:" + expMon);
                        Debug.print("expYear:" + expYear);

                        String expDate = expMon + expYear;
                        String email = (String) session.getAttribute("emailId");
                        Debug.print("inside payment card select ......" + objPayment.toString());
                        Debug.print("Redirecting to nova from servlet ....");

                        //request.setAttribute("chkDigit",String.valueOf(objPayment.getCcCvvid()));

                        if (defStat != null) {
                            if (defStat.equalsIgnoreCase("yes")) {

                                objPayment.setParentPaymentId(paymentId);
                                objPayment.setPaymentId(newPaymentId);

                            }
                        }

                        if (!usrStat.equalsIgnoreCase("user")) {
                            objPayment.setParentPaymentId(paymentId);
                            objPayment.setPaymentId(newPaymentId);

                            String amt = (String) session.getAttribute("pendingAmt");
                            objPayment.setAmount(Double.valueOf(amt).doubleValue());

                        }

                        session.setAttribute("objPayment", payDet);

                        request.setAttribute("email", email);
                        request.setAttribute("cardNo", String.valueOf(objPayment.getCcNumber()));
                        request.setAttribute("expDate", expDate);
                        request.setAttribute("amount", String.valueOf(objPayment.getAmount()));

                        Debug.print("Payment Details in Servlet:" + objPayment.toString());
                        StringBuffer reqURL = request.getRequestURL();
                        int lastIndex = reqURL.lastIndexOf("/");
                        String url = "";
                        if (lastIndex != -1) {
                            url = reqURL.substring(0, lastIndex + 1);
                        }
                        String tmpNova = mr.getMessage("adminmember.nova");
                        String nova = url + tmpNova;
                        Debug.print("succsssullll from app file nova........::" + nova);
                        Debug.print("succsssullll from app file tmpNova ........::" + tmpNova);
                        request.setAttribute("nova", nova);
                        int intVId = 0;
                        //String inVoiceId1 = (String) session.getAttribute("inVoiceId1");
                        String inVoiceId1="1";
                     System.out.println("inVoiceId1 in servlet********" + inVoiceId1);
                     if (inVoiceId1.equalsIgnoreCase("0")) {
                            intVId = 1;
                        } else {
                            intVId = 1;
                        }
                 session.setAttribute("sessionInvoiceId","1");    
            request.setAttribute("AMT", request.getParameter("tot_amt"));
            request.setAttribute("PAYMENTACTION", "Authorization");
            request.setAttribute("CREDITCARDTYPE", request.getParameter("cardselect"));
            request.setAttribute("ACCT", request.getParameter("cardNo"));
            request.setAttribute("EXPDATE", expDate);
            request.setAttribute("IPADDRESS", reqIp);
            request.setAttribute("FIRSTNAME", request.getParameter("printName"));
            request.setAttribute("CVVNo", request.getParameter("cVVNo"));
            request.setAttribute("STREET", "1 Main St");
            request.setAttribute("CITY", "San Jose");
            request.setAttribute("STATE", "CA");
            request.setAttribute("ZIP", "95131");
            request.setAttribute("COUNTRYCODE", "US");
            request.setAttribute("EMAIL", email);
            request.setAttribute("intVId", String.valueOf(intVId));
            System.out.println("intVId in servlet&&&&&&&" + intVId);
            String purposeFor=(String)session.getAttribute("purpose");
            if(purposeFor.equalsIgnoreCase("memberregistration"))
               {
                 request.setAttribute("purpose", "memberregistration");
            }
            else
            {
                request.setAttribute("purpose", "membershiprenewal");
            }
                        fwd = "testing";
                    }
                 else
                 {
                                                           
                    if(defStat!=null)
                    {
                        if(defStat.equalsIgnoreCase("yes"))
                        {
                        
                        Debug.print("inside deficiency payment block :");
                        
                        objPayment.setParentPaymentId(paymentId);
                        objPayment.setPaymentId(newPaymentId);
                       
                        Debug.print("Payment Details in Servlet:" + objPayment);
                        
                        String amt =(String)session.getAttribute("pendingAmt");
                        
                        if(!usrStat.equalsIgnoreCase("user"))
                        {
                            objPayment.setAmount(Double.valueOf(amt).doubleValue()); 
                        }
                        
                        boolean pStat=remote.insertPayment(objPayment);
                        Debug.print("remote.insertPayment(objPayment) :"+pStat);
                        
                         if(!usrStat.equalsIgnoreCase("user")){
                            String chckAmount = request.getParameter("chckAmount");
                            String totalAmount = request.getParameter("tot_amt");                            
                            String pendAmt = request.getParameter("pendAmt");
                            Debug.print("Check Amount is in Admin Pay "+chckAmount);
                            Debug.print("Total Amount is in Admin Pay "+totalAmount);                            
                            if(pendAmt==null && pendAmt.trim().length()==0){
                                pendAmt = "0";
                            }
                            if(totalAmount!=null && chckAmount!=null){
                                    double chkAmt = Double.parseDouble(chckAmount);
                                    double totAmt = Double.parseDouble(totalAmount);
                                    double dpendAmt = Double.parseDouble(pendAmt);
                                    if(dpendAmt>0){
                                        double extraAmt = chkAmt - dpendAmt;
                                        Debug.print("Check Amount is in Admin Pay "+chkAmt);
                                        Debug.print("Total Amount is in Admin Pay "+totAmt);
                                        Debug.print("Extra Amount Through Admin Pay "+extraAmt);

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
                                            Debug.print("B4 insert "+ overpayfinalDet.toString());
                                            boolean extraPay = mahaRemote.insertAccountTxnDetails(overpayfinalDet);
                                        }
                                        }
                                    if(chkAmt>0){
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
                                            Debug.print("B4 insert "+ overpayfinalDet.toString());
                                            boolean extraPay = mahaRemote.insertAccountTxnDetails(overpayfinalDet);
                                            }                                        
                                    }
                                    if(chkAmt>=totAmt){
                                        if(paymentId!=null || paymentId.trim().length()!=0){
                                            boolean Update_Status = mahaRemote.updateRecouncilActiveStatusAccTxnDetails(paymentId);
                                            Debug.print("Update Status "+Update_Status);
                                       }
                                    }
                            }
                        }
                        
                        
/*                        // Making the active Status and reconcile status as true on full payment
                        //Setting the reconcile & active Status TRUE
                        if(session.getAttribute("loggedBy")!=null){

                                String logBy="user";
                                String loggedBy="";
                                loggedBy=(String)session.getAttribute("loggedBy");
                                logBy=loggedBy;

                                if(usrStat!=null){
                                    String chckAmount = request.getParameter("chckAmount");
                                    String totalAmount = request.getParameter("tot_amt");
                                    Debug.print("Check Amount is in Admin Pay "+chckAmount);
                                    Debug.print("Total Amount is in Admin Pay "+totalAmount);
                                    if(totalAmount!=null && chckAmount!=null){
                                            double chkAmt = Double.parseDouble(chckAmount);
                                            double totAmt = Double.parseDouble(totalAmount);
                                            Debug.print("Check Amount is in Admin Pay "+chkAmt);
                                            Debug.print("Total Amount is in Admin Pay "+totAmt);
                                            if(totAmt>0){
                                                if(paymentId!=null || paymentId.trim().length()!=0){
                                                    boolean Update_Status = mahaRemote.updateRecouncilActiveStatusAccTxnDetails(paymentId);
                                                    Debug.print("Update Status "+Update_Status);
                                               }
                                            }
                                        }
                                }
                        }*/
                        
                        session.setAttribute("defStat",null);
                    }
                    else
                    {
                        Debug.print("inside update payment block :");
                        String singlePayId = request.getParameter("singlePayId");
                        Debug.print("singlePayId is "+singlePayId);
                        //Parent Payment Info Avoided
                        if(singlePayId==null){
                            boolean stat=remote.updatePaymentStatus(objPayment);
                            Debug.print("remote.updatePaymentStatus(objPayment) :"+stat);
                        }
                        
                         if(request.getParameter("chckAmount")!=null && request.getParameter("chckAmount").trim().length()!=0)
                        {
                            Debug.print("request.getParameter(chckAmount) :"+request.getParameter("chckAmount"));
                            boolean pendStat=kaveryRemote.updatePendingAmount(userId,paymentId,Float.valueOf(request.getParameter("chckAmount")).floatValue());
                            Debug.print("kaveryRemote.updatePendingAmount() : "+pendStat);
                            
                            chkAmt2 = request.getParameter("chckAmount");
                            
                            // Making the active Status and reconcile status as true on full payment
                            //Setting the reconcile & active Status TRUE
                             if(!usrStat.equalsIgnoreCase("user")){
                                String chckAmount = request.getParameter("chckAmount");
                                String totalAmount = request.getParameter("tot_amt");
                                String pendAmt = request.getParameter("pendAmt");
                                Debug.print("Check Amount is in Admin Pay "+chckAmount);
                                Debug.print("Total Amount is in Admin Pay "+totalAmount);  
                                if(pendAmt==null && pendAmt.trim().length()==0){
                                    pendAmt = "0";
                                }
                                if(totalAmount!=null && chckAmount!=null){
                                        double chkAmt = Double.parseDouble(chckAmount);
                                        double totAmt = Double.parseDouble(totalAmount);
                                        double dpendAmt = Double.parseDouble(pendAmt);
                                       
                                        Debug.print("Check Amount is in Admin Pay "+chkAmt);
                                        Debug.print("Total Amount is in Admin Pay "+totAmt);
                                        Debug.print("Pending Amount is in Admin Pay "+dpendAmt);

                                        if(dpendAmt>0){
                                            double extraAmt = chkAmt - dpendAmt;
                                            Debug.print("Extra Amount Through Admin Pay "+extraAmt);

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
                                                Debug.print("B4 insert "+ overpayfinalDet.toString());
                                                boolean extraPay = mahaRemote.insertAccountTxnDetails(overpayfinalDet);
                                            }
                                            }
                                        if(chkAmt>0){
                                            double extraAmt = chkAmt - totAmt;
                                            Debug.print("Inside Check Amount Greater "+extraAmt);
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
                                                Debug.print("B4 insert "+ overpayfinalDet.toString());
                                                boolean extraPay = mahaRemote.insertAccountTxnDetails(overpayfinalDet);
                                                }                                        
                                        }
                                        if(chkAmt>=totAmt){
                                            if(paymentId!=null || paymentId.trim().length()!=0){
                                                boolean Update_Status = mahaRemote.updateRecouncilActiveStatusAccTxnDetails(paymentId);
                                                Debug.print("Update Status "+Update_Status);
                                           }
                                        }
                                }
                            }
                        }
                    }
                        
                    }
                    
                    request.setAttribute("chkAmt",chkAmt2);
                    request.setAttribute("amount",String.valueOf(objPayment.getAmount()));
                    
                    fwd="backtoList";
                 }
                
         }      
        
        }
        }
        
         catch (Exception ex) {
            System.err.println("Caught an exception.");
            ex.printStackTrace();
        }
        
        return mapping.findForward(fwd);
        
    }       
}
