/*
 * EndorseRetryPayAction.java
 *
 * Created on January 4, 2008, 3:32 PM
 */

package com.mee.action;

import com.hlcmro.display.*;
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
import com.hlcmro.util.*;
import com.hlcmro.org.*;
import java.util.Properties;
import java.util.Date;
import java.text.*;
import java.util.*;
import com.hlccommon.util.Debug;
import org.apache.struts.util.MessageResources;
import com.hlcform.stateless.*;
import com.hlcform.util.HLCPaymentDetails;
/**
 *
 * @author Vidhya
 * @version
 */
public class EndorseRetryPayAction extends Action {
    
  public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception {
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
              
                FormEventOrgRenewal endForm=(FormEventOrgRenewal)form; 
                HLCRenewalOrganizerDetails objRenewalDet = new HLCRenewalOrganizerDetails();
                HLCPaymentDetailsVO objPayDet = new HLCPaymentDetailsVO();
                HLCEndorsedFormVO objEndorseDet = new HLCEndorsedFormVO();
                String eventProcess = request.getParameter("eventProcess");
                Debug.print("eventProcess: " + eventProcess);
                
                if(eventProcess!=null){
                    if(eventProcess.equalsIgnoreCase("initRetryPayment")){
                    Debug.print("eventProcess in EndorseRetryPayAction :"+eventProcess);
                    String eveId=request.getParameter("eventId");
                    String payId=request.getParameter("paymentId");
                    String price=request.getParameter("amt");
                    String button=request.getParameter("butSbmt");
                    request.setAttribute("eventId",eveId);
                    request.setAttribute("paymentId",payId);
                    request.setAttribute("price",price);
                    if(button!=null){
                        if(button.equalsIgnoreCase("Retry Payment")){
                        return mapping.findForward("frmEndorseRetryPayment"); 
                        }
                        else if(button.equalsIgnoreCase("Register OmniBus")){
                        request.setAttribute("noPayment","noPay");    
                        return mapping.findForward("forEndorseSuccess"); 
                        }
                        else if(button.equalsIgnoreCase("Back To List")){
                        return mapping.findForward("frmMeeOrgEventListings"); 
                        }
                    }
                     
                    }
                    if(eventProcess.equalsIgnoreCase("updatePayment")){
                    Debug.print("eventProcess in EndorseRetryPayAction :"+eventProcess);
                    HLCVaigaiSessionRemoteHome vaigaihome = (HLCVaigaiSessionRemoteHome)
                    javax.rmi.PortableRemoteObject.narrow(obj,HLCVaigaiSessionRemoteHome.class);
                    HLCVaigaiSessionRemote remoteVaigai = vaigaihome.create();
                    String payId=remoteVaigai.getPaymtId();
                    //retrieve PaymentDetails Object set in Servlet 1
                    HLCPaymentDetails objPayment=(HLCPaymentDetails)session.getAttribute("objPayment");
                    objPayment.setPaymentId(payId);
                    
                    String reqIp=request.getRemoteAddr();
                    String eventId=request.getParameter("eventId");
                    //String payId=request.getParameter("paymentId");
                     
                    String eveTitle=request.getParameter("eveTitle");
                    SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyy");
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
                    String  r11 = endForm.getR11();
                    System.out.print("Check Status :" + r11);
                    String userId=(String)session.getAttribute("userId");
                    Debug.print("userId in servlet :"+userId);
                    boolean insertResult=false;
                    
                    if(r11.equals("card")){
                        if(endForm.getCcNumber()==null || endForm.getCcNumber().equals("")){
                        CcNumber = 0;
                        objPayment.setCcNumber(endForm.getCcNumber());  
                        }else {
                        CcNumber = Long.parseLong(endForm.getCcNumber());
                        objPayment.setCcNumber(endForm.getCcNumber());  
                        }
                        if(endForm.getCcName()==null || endForm.getCcName().equals("")){
                        ccName ="";
                        objPayment.setCcName(ccName);
                        }else {
                        ccName=endForm.getCcName();
                        objPayment.setCcName(ccName);
                        }
                        if(!(endForm.getCcType().equals("")) && endForm.getCcType()!=null){
                        ccType = endForm.getCcType();  
                        objPayment.setCcType(ccType);
                        }
                        if(endForm.getCcExpMonth()==null || endForm.getCcExpMonth().equals("")){
                        CcExpMonth =0;
                        objPayment.setCcExpMonth(CcExpMonth);
                        }else {
                        CcExpMonth=Integer.parseInt(endForm.getCcExpMonth());
                        objPayment.setCcExpMonth(CcExpMonth);
                        }
                        if(endForm.getCcExpYear()==null || endForm.getCcExpYear().equals("")){
                        CcExpYear =0;
                        objPayment.setCcExpYear(CcExpYear);
                        }else{
                        CcExpYear = Integer.parseInt(endForm.getCcExpYear());
                        objPayment.setCcExpYear(CcExpYear);
                        }
                        if(endForm.getCcCvvid()==null || endForm.getCcCvvid().equals("")){
                        CcCvvid =0;
                        objPayment.setCcCvvid(CcCvvid);
                        }else{
                        CcCvvid = Integer.parseInt(endForm.getCcCvvid());
                        objPayment.setCcCvvid(CcCvvid);
                        }
                        session.setAttribute("cardselect",ccType);
                        session.setAttribute("objPayment",objPayment);
                        
                        check_date= null;
                        objPayDet.setPaymentStatus("card");
                    }
                    if(r11.equals("check")){
                        if(endForm.getCheckNumber()==null || endForm.getCheckNumber().equals("")){
                        checkNumber=0;
                        }else{
                        checkNumber= Long.parseLong(endForm.getCheckNumber());
                        }
                        if(endForm.getCheckDate().equals("")){
                        check_date= null;
                        }else{
                        check_date =(Date)sdf.parse(endForm.getCheckDate());
                        }
                        objPayDet.setPaymentStatus("Check");
                    }
                    
                    if(endForm.getAmount()==null || endForm.getAmount()=="" ){
                    amount=0; 
                    }else{
                    amount = Double.parseDouble(endForm.getAmount());
                    } 
                    objRenewalDet.setEventId(Integer.parseInt(eventId));
                    objPayDet.setUserId(userId);
                    objPayDet.setCcName(ccName);
                    objPayDet.setCcType(ccType);
                    objPayDet.setCcNumber(CcNumber);
                    objPayDet.setCcExpMonth(CcExpMonth);
                    objPayDet.setCcExpYear(CcExpYear);
                    objPayDet.setCcCvvid(CcCvvid);
                    objPayDet.setBankName(endForm.getBankName());
                    objPayDet.setCheckName(endForm.getNameOnchk());
                    objPayDet.setCheckDate(check_date);
                    objPayDet.setChkNumber(endForm.getCheckNumber());
                    objPayDet.setAmount(amount);
                    objPayDet.setPaymentDate(new Date());
                    objPayDet.setPaymentId(payId);
                    
                    System.out.println("paymenId" + objPayDet.getPaymentId());
                    
                    System.out.println("getBankName" +endForm.getBankName());
                    System.out.println("check_date" +check_date);
                    System.out.println("checkNumber" +checkNumber);
                    System.out.println("amount" +amount);
                    System.out.println("setPaymentDate" +new Date()); 
                
                   if(r11.equals("card")){
                        Debug.print("Sucessfully Redirect to Paypal");
                        //session.setAttribute("objRenewalDet",objRenewalDet);
                        //session.setAttribute("objPaymentList",objPayDet);
                       
                        Debug.print("Sucessfully Payment objRenewalDet:" + objRenewalDet.toString());
                        Debug.print("Sucessfully Payment objPaymentList:" + objPayDet.toString());
                       //===========================================
                        request.setAttribute("email","suryey@yahoo.co.in");
                        request.setAttribute("cardNo",String.valueOf(CcNumber));
                        String expMon = String.valueOf(CcExpMonth);
                        String expYear = String.valueOf(CcExpYear);
                        if(expMon.trim().length()==1) {
                             expMon = "0" + expMon;
                        }
                        Debug.print("expYear:" + expYear);
                        //expYear = expYear.substring(2);
                        Debug.print("expMon:" + expMon);
                        Debug.print("expYear:" + expYear);
                        String expDate = expMon + expYear;
                        request.setAttribute("expDate",expDate);
                        request.setAttribute("amount",String.valueOf(amount));
                        request.setAttribute("chkDigit",String.valueOf(CcCvvid));
                        session.setAttribute("eveTitle",eveTitle);
                        StringBuffer reqURL = request.getRequestURL();
                        int lastIndex = reqURL.lastIndexOf("/") ;
                        String url ="";
                        if(lastIndex!=-1){
                            url = reqURL.substring(0,lastIndex+1);
                        }
                        //String tmpNova  = mr.getMessage("orgRetryPayment.nova");
                        //String nova = url + tmpNova;

                        //request.setAttribute("nova",nova);
                        //PayPal Part ===========================================
                       // String inVoiceId1 = (String) session.getAttribute("inVoiceId1");
                         //System.out.println("inVoiceId1 in servlet********" + inVoiceId1);
                         String inVoiceId1="1";
                           int intVId = 0;
                        if (inVoiceId1.equalsIgnoreCase("0")) {
                            intVId = 1;
                        } else {
                            intVId = 1;
                        } 
                        session.setAttribute("sessionInvoiceId","1");    
                        request.setAttribute("AMT", (String)endForm.getAmount());
                        request.setAttribute("PAYMENTACTION", "Authorization");
                        request.setAttribute("CREDITCARDTYPE", (String)endForm.getCcType());
                        request.setAttribute("ACCT", (String)endForm.getCcNumber());
                        request.setAttribute("EXPDATE", expDate);
                        request.setAttribute("IPADDRESS", reqIp);
                        request.setAttribute("FIRSTNAME", (String)endForm.getCcName());
                        request.setAttribute("CVVNo", (String)endForm.getCcCvvid());
                        request.setAttribute("STREET", "1 Main St");
                        request.setAttribute("CITY", "San Jose");
                        request.setAttribute("STATE", "CA");
                        request.setAttribute("ZIP", "95131");
                        request.setAttribute("COUNTRYCODE", "US");
                        request.setAttribute("EMAIL", (String) session.getAttribute("emailId"));
                        //session.setAttribute("objPayment",objPayDet);
                         session.setAttribute("objPayment",objPayment);
                        session.setAttribute("cardselect",request.getParameter("ccType"));

                        //intVId++; 
                        request.setAttribute("intVId", String.valueOf(intVId));
                        System.out.println("intVId in servlet&&&&&&&" + intVId);
                        request.setAttribute("purpose", "endorsedForm"); 
                        return mapping.findForward("testing");

                    }else{
                     boolean updateResult=false;
                     updateResult=remote.updatePayment(objPayDet);
                     Debug.print("updateResult :"+updateResult);
                     request.setAttribute("eventId",eventId);
                     if(updateResult==true){
                     request.setAttribute("paymentMode","check"); 
                     request.setAttribute("amount",String.valueOf(objPayDet.getAmount()));
                     return mapping.findForward("forEndorseSuccess");
                     }else{
                     
                     request.setAttribute("paymentId",objPayDet.getPaymentId()); 
                     request.setAttribute("price",String.valueOf(objPayDet.getAmount()));    
                     return mapping.findForward("frmEndorseRetryPayment");    
                     }
                    }    
                          
            }//closing updatePayment   
            }//closing eventProcess!=null
        }catch(Exception e){
        e.printStackTrace();    
        }
  return null;  
  }  
}
