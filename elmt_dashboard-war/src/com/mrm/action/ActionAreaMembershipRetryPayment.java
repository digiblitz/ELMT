/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mrm.action;
import com.hlcaccounts.util.HLCAccTransactionVO;
import com.hlcaccounts.session.HLCMahanadhiSessionRemote;
import com.hlcaccounts.session.HLCMahanadhiSessionRemoteHome;
import com.hlcform.stateless.HLCkaverystatelessRemote;
import com.hlcform.stateless.HLCkaverystatelessRemoteHome;
import com.hlccommon.util.*;
import com.hlckavery.statful.*;
import com.hlcmrm.util.Debug;
import com.hlcmeeting.session.*;
import com.hlccommon.util.HLCPaymentDetailVO;
import com.hlcpayment.HLCPaymentSessionRemote;
import com.hlcpayment.HLCPaymentSessionRemoteHome;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.naming.Context;
import javax.naming.InitialContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.util.MessageResources;
import com.hlcform.util.HLCPaymentDetails;

/**
 *
 * @author dhivya
 */
public class ActionAreaMembershipRetryPayment extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    //private final static String SUCCESS = "success";
    
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
        
        
        
        try
        {
          MessageResources mr=getResources(request);
        String namingfactory=mr.getMessage("ejbclient.namingfactory");
        String contextfactory=mr.getMessage("ejbclient.contextfactory");
        String urlprovider=mr.getMessage("ejbclient.urlprovider");
        String lookupip=mr.getMessage("ejbclient.ip");
        String jndiname=mr.getMessage("jndi.usrreg");
         String mahanadhiJndi=mr.getMessage("jndi.acc");
        Debug.print("mahanadhiJndi  :" +mahanadhiJndi);

        System.setProperty(namingfactory,contextfactory);
        System.setProperty(urlprovider,lookupip);
        Context jndiContext = new InitialContext();
        Object objref = jndiContext.lookup(jndiname);

        HLCkaverystatelessRemoteHome home = (HLCkaverystatelessRemoteHome)
        javax.rmi.PortableRemoteObject.narrow(objref,HLCkaverystatelessRemoteHome.class);
        HLCkaverystatelessRemote remote = home.create(); 
        HttpSession session=request.getSession();
           
        Object maha=jndiContext.lookup("ejb/HLCMahanadhiSessionBean");
        HLCMahanadhiSessionRemoteHome mahaHome = (HLCMahanadhiSessionRemoteHome)
        javax.rmi.PortableRemoteObject.narrow(maha,HLCMahanadhiSessionRemoteHome.class);
        HLCMahanadhiSessionRemote mahaRemote = mahaHome.create();
        
         Object objPayss= jndiContext.lookup("ejb/HLCPaymentSessionBean");
            HLCPaymentSessionRemoteHome objPaySessHome = (HLCPaymentSessionRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(objPayss,HLCPaymentSessionRemoteHome.class);
            HLCPaymentSessionRemote objPaySessRemote = objPaySessHome.create();
       String emailid = (String) session.getAttribute("emailId");
       
        HLCPaymentDetailVO objPayDet = new HLCPaymentDetailVO();
        //PaymentDetails objPayment=new PaymentDetails();
        String reqIp=request.getRemoteAddr();
         SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyy");
         
          String userId=(String)session.getAttribute("userId");
          
         String cmd=request.getParameter("cmd");
        
        if(cmd!=null && cmd.equalsIgnoreCase("initRetry")){
            
            String payId=request.getParameter("pid");
            String amt=(String)session.getAttribute("amount");
           
            String usrStat=request.getParameter("usr");
            
            String area=request.getParameter("area");
            String progName=request.getParameter("progName");
            String progDesc=request.getParameter("progDesc");
            String progYr=request.getParameter("progYr");
            
            
    
            request.setAttribute("payId",payId);
            request.setAttribute("amt",amt);
            request.setAttribute("usrStat",usrStat);            
            session.setAttribute("usrStat",usrStat);
            request.setAttribute("area",area);
            request.setAttribute("progName",progName);
            request.setAttribute("progDesc",progDesc); 
            request.setAttribute("progYr",progYr); 
   
         return mapping.findForward("frmAreaRidProgramRetry");      
            
        }
        
       else if(cmd!=null && cmd.equalsIgnoreCase("areaPayment")){
           
       String r11=request.getParameter("r11");
       Debug.print("r11:" + r11);
       String TotalAmount=request.getParameter("totalAmount");
       Debug.print("TotalAmount:" + TotalAmount);     
       //String paymentId=request.getParameter("paymentId");
             String paymentId=(String)session.getAttribute("paymentId");
            String area=request.getParameter("area");
            String progName=request.getParameter("progName");
            String progDesc=request.getParameter("progDesc");
            String progYr=request.getParameter("progYr");
            HLCPaymentDetails objPayment=new HLCPaymentDetails();
            
        Debug.print("request.getParameter(paymentId) in areaPayment section:"+paymentId); 
      
        objPayment.setPaymentId(paymentId);
        HLCAccTransactionVO PhoneVO =  (HLCAccTransactionVO) session.getAttribute("PhaccTxnVO");
                if(PhoneVO!=null){
                      PhoneVO.setPayment_id(paymentId);
                      PhoneVO.setPayment_mode(request.getParameter("ccType"));
                    
                }
        session.setAttribute("PhaccTxnVO",PhoneVO);
        HLCAccTransactionVO PhoneVO1 =  (HLCAccTransactionVO) session.getAttribute("PhaccTxnVO1");   
               if(PhoneVO1!=null){
               PhoneVO1.setPayment_id(paymentId);
               PhoneVO1.setPayment_mode(request.getParameter("ccType"));
               }
        session.setAttribute("PhaccTxnVO1",PhoneVO1);
                
      
        int CcExpMonth = 0;
        int CcExpYear = 0;
        int CcCvvid =0;
        double amount =0;
        long CcNumber = 0;
        long checkNumber=0;
        Date act_date_card = new Date();
        boolean result=false;
        if(r11.equals("card")){                 
             if(request.getParameter("ccName")!=null && request.getParameter("ccName").trim().length()!=0) {
                objPayment.setCcName(request.getParameter("ccName"));
            } else {
                objPayment.setCcName(null);
            }
            
            if(request.getParameter("ccType")!=null && request.getParameter("ccType").trim().length()!=0) {
                objPayment.setCcType(request.getParameter("ccType"));
            } else {
                objPayment.setCcType(null);
                
            }
            
            if(request.getParameter("ccNumber")!=null && request.getParameter("ccNumber").trim().length()!=0) {
                objPayment.setCcNumber(request.getParameter("ccNumber"));
            } else {
                objPayment.setCcNumber("0");
            }
            
            if(request.getParameter("ccExpMonth")!=null && request.getParameter("ccExpMonth").trim().length()!=0) {
                objPayment.setCcExpMonth(Integer.parseInt(request.getParameter("ccExpMonth")));
            } else {
                objPayment.setCcExpMonth(0);
            }
            
            if(request.getParameter("ccExpYear")!=null && request.getParameter("ccExpYear").trim().length()!=0) {
                objPayment.setCcExpYear(Integer.parseInt(request.getParameter("ccExpYear")));
            } else {
                objPayment.setCcExpYear(0);
            }
            
            if(request.getParameter("ccCvvid")!=null && request.getParameter("ccCvvid").trim().length()!=0) {
                if(!request.getParameter("ccCvvid").equals("")) {
                    objPayment.setCcCvvid(Integer.parseInt(request.getParameter("ccCvvid")));
                    request.setAttribute("ccCvvid",request.getParameter("ccCvvid"));
                } else {
                    objPayment.setCcCvvid(0);
                    request.setAttribute("ccCvvid","0");
                }
            } else {
                objPayment.setCcCvvid(0);
                request.setAttribute("ccCvvid","0");
            }
            
            objPayment.setBankName(null);       
            objPayment.setCheckDate(null);
            objPayment.setCheckNumber("0");
            objPayment.setCheckName(null);
            objPayment.setCheckAmount(0);
            objPayment.setIpAddress(reqIp);
            
            if(request.getParameter("totalAmount")!=null && request.getParameter("totalAmount").trim().length()!=0) {
                objPayment.setAmount(Double.valueOf(request.getParameter("totalAmount")).doubleValue());
            } else {
                objPayment.setAmount(0);
            }
            
            objPayment.setPaymentDate(new Date());
            objPayment.setPaymentStatus("card");
           
            
            if(request.getParameter("activeDatVal")!=null && request.getParameter("activeDatVal").trim().length()!=0) {
                act_date_card = (Date)sdf.parse(request.getParameter("activeDatVal"));
            }
            
            Debug.print("final act_date for card mode :"+act_date_card.toString());                             
            session.setAttribute("act_date",act_date_card);   
            
            
        }
   
        Date check_date=null;
        Date act_date_check=null;
        double chk_amt=0;
        float chkAmt=0;
        String actStat=null;  
        if(r11.equalsIgnoreCase("check")){ 
              if(request.getParameter("checkDate")!=null && request.getParameter("checkDate").trim().length()!=0) {
                check_date = (Date)sdf.parse(request.getParameter("checkDate"));
                Debug.print("check_date :"+check_date.toString());
            } else {
                check_date =null;
            }
            if(request.getParameter("activeDate")!=null && request.getParameter("activeDate").trim().length()!=0) {
                act_date_check = (Date)sdf.parse(request.getParameter("activeDate"));
            }else {
                act_date_check=null;
            }
            
            objPayDet.setUserId(userId);
            objPayDet.setCcName(null);
            objPayDet.setCcType(null);
            objPayDet.setCcNumber("0");
            objPayDet.setCcExpMonth(0);
            objPayDet.setCcExpYear(0);
            objPayDet.setCcCvvid(0);
            objPayDet.setBankName(request.getParameter("bankName"));
            objPayDet.setCheckDate(check_date);
            
            
            if(request.getParameter("chkBalAmt")!=null && request.getParameter("chkBalAmt").trim().length()!=0) {
                //objPayment.setCheckAmount(Float.valueOf(request.getParameter("chckAmount")).floatValue());
                
                Debug.print("request.getParameter(chkBalAmt) :"+request.getParameter("chkBalAmt"));
                
                chk_amt=Double.valueOf(request.getParameter("chkBalAmt")).doubleValue();
                Debug.print("Double.valueOf(request.getParameter(chkBalAmt)).doubleValue() :"+chk_amt);
                
           
                 chkAmt=Float.valueOf(request.getParameter("chkBalAmt")).floatValue();                              
                objPayDet.setCheckAmount(chkAmt);
                
            } else {
                objPayDet.setCheckAmount(0);
            }
            
            if(request.getParameter("checkNumber")!=null && request.getParameter("checkNumber").trim().length()!=0) {
                objPayDet.setCheckNumber(request.getParameter("checkNumber"));
            } else {
                objPayDet.setCheckNumber("0");
            }
            
            if(request.getParameter("totalAmount")!=null && request.getParameter("totalAmount").trim().length()!=0) {
                objPayDet.setAmount(Double.valueOf(request.getParameter("totalAmount")).doubleValue());
            } else {
                objPayDet.setAmount(0);
            }
         
            String nameOnchk=request.getParameter("nameCheck");            
            objPayDet.setCheckName(nameOnchk);            
            objPayDet.setPaymentDate(new Date());          
            objPayDet.setPaymentStatus("check");
                                 
            if(objPayDet.getAmount()<=objPayDet.getCheckAmount()){                
               actStat="Active";
               objPayDet.setPendingAmount(0);
            }else if(objPayDet.getCheckAmount()>objPayDet.getAmount()){             
             float tempPendingAmt=(float)(objPayDet.getAmount() - objPayDet.getCheckAmount());               
             objPayDet.setPendingAmount(tempPendingAmt);
            } 
            
            boolean stat=objPaySessRemote.updateDeclinePaymentStatus(objPayDet);
           Debug.print("remote.updateDeclinePaymentStatus(objPayDet) :"+stat);
           
       if(objPayDet.getCheckAmount()>=objPayDet.getAmount()){     
           boolean Update_Status = mahaRemote.updateRecouncilActiveStatusAccTxnDetails(paymentId); 
        }
           
        request.setAttribute("areaName",area);                                     
        request.setAttribute("amount",String.valueOf(chkAmt));
        request.setAttribute("areaRidProg",progName);
        request.setAttribute("progYear",progYr);
        request.setAttribute("actStat",actStat);
        request.setAttribute("areaRidProgDesc",progDesc);   
    
       return mapping.findForward("frmMyAreaProgRetryCheckSuccess");
       }       
    
      if(r11.equals("card")){
           Debug.print("Sucessfully Redirect to Paypal:");  
        if(request.getParameter("activeDatVal")!=null && request.getParameter("activeDatVal").trim().length()!=0) {             
          session.setAttribute("activation_date",request.getParameter("activeDatVal"));     
         }           
            if(request.getParameter("ccExpMonth")==null){
                CcExpMonth =0;
            } else {
                CcExpMonth=Integer.parseInt(request.getParameter("ccExpMonth"));
            }
            
            if(request.getParameter("ccExpYear")==null){
                CcExpYear =0;
            } else{
                CcExpYear = Integer.parseInt(request.getParameter("ccExpYear"));
            }
            
            if(request.getParameter("ccCvvid")==null && request.getParameter("ccCvvid").trim().length()==0){
                CcCvvid =0;
            } else if(!request.getParameter("ccCvvid").equals("") && request.getParameter("ccCvvid").trim().length()!=0){
                CcCvvid = Integer.parseInt(request.getParameter("ccCvvid"));
            } else {
                CcCvvid =0;
            }
            
            if(request.getParameter("ccNumber")==null){
                CcNumber =0;
            } else{
                CcNumber = Long.parseLong(request.getParameter("ccNumber"));
            }
            
            if(request.getParameter("totalAmount")!=null && request.getParameter("totalAmount").trim().length()!=0) {
                amount = Double.valueOf(request.getParameter("totalAmount")).doubleValue();
            } else {
                amount = 0.00;
            }   
            //String inVoiceId1 = (String) session.getAttribute("inVoiceId1");
           String inVoiceId1="1";
                         System.out.println("inVoiceId1 in servlet********" + inVoiceId1);
                           int intVId = 0;
                        if (inVoiceId1.equalsIgnoreCase("0")) {
                            intVId = 1;
                        } else {
                            intVId = 1;
                        } 
             //session.setAttribute("objPaymentList",objPayDet);        
             request.setAttribute("email",emailid);
            request.setAttribute("cardNo",String.valueOf(CcNumber));
            request.setAttribute("chkDigit",String.valueOf(CcCvvid));
            
            session.setAttribute("areaName",area);                                                
            session.setAttribute("areaRidProg",progName);
            session.setAttribute("progYear",progYr);         
            session.setAttribute("areaRidProgDesc",progDesc);
          
            String expMon = String.valueOf(CcExpMonth);
            String expYear = String.valueOf(CcExpYear);
            if(expMon.trim().length()==1) {
                expMon = "0" + expMon;
            }
           
            Debug.print("expYear:" + expYear);
           // expYear = expYear.substring(2);
           
            Debug.print("expMon:" + expMon);
            Debug.print("expYear:" + expYear);
            
            String expDate = expMon + expYear;
            request.setAttribute("expDate",expDate);
            request.setAttribute("amount",String.valueOf(amount));
            //StringBuffer reqURL = request.getRequestURL();
            //int lastIndex = reqURL.lastIndexOf("/") ;
            //String url ="";
            //if(lastIndex!=-1){
              //  url = reqURL.substring(0,lastIndex+1);
            //}
            //String tmpNova  = mr.getMessage("areaMembRetry.nova");
            //String nova = url + tmpNova;
           // Debug.print("succsssullll from app file nova........::" + nova);
            //Debug.print("succsssullll from app file tmpNova ........::" + tmpNova);
            //request.setAttribute("nova",nova);
            request.setAttribute("purpose","areaMemb");
             request.setAttribute("AMT",String.valueOf(amount));
             request.setAttribute("PAYMENTACTION", "Authorization");
             request.setAttribute("IPADDRESS", reqIp);
             request.setAttribute("FIRSTNAME", (String)request.getParameter("ccName"));
             request.setAttribute("CREDITCARDTYPE",request.getParameter("ccType"));
             request.setAttribute("ACCT",(String)request.getParameter("ccNumber"));
             request.setAttribute("EXPDATE",expDate);
             request.setAttribute("CVVNo",(String)request.getParameter("ccCvvid"));
             session.setAttribute("objPayment",objPayment);
             request.setAttribute("STREET", "1 Main St");
                        request.setAttribute("CITY", "San Jose");
                        request.setAttribute("STATE", "CA");
                        request.setAttribute("ZIP", "95131");
                        request.setAttribute("COUNTRYCODE", "US");
                        request.setAttribute("EMAIL", "payments@useventing.com");
                        session.setAttribute("cardselect",request.getParameter("ccType"));
                        request.setAttribute("intVId", String.valueOf(intVId));
                        
             session.setAttribute("sessionInvoiceId","1");
             return mapping.findForward("testing");
          
        }
   }
        }catch (Exception ex) {
            System.err.println("Caught an exception.");
            ex.printStackTrace();
        }
         
     return null;
        
    }
}