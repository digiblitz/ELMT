/*
 * EventRegPendingPaymentAction.java
 *
 * Created on January 18, 2008, 4:41 PM
 */

package com.mee.action;

import com.hlcmeeting.util.HLCPaymentDetails;
import com.mee.actionform.FormEventOrgRenewal;
import com.hlcmeeting.util.HLCEventRegPendingVO;
import com.hlcmro.util.HLCRenewalOrganizerDetails;
import com.hlcutil.Debug;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import com.hlcmeeting.session.HLCVaigaiSessionRemote;
import com.hlcmeeting.session.HLCVaigaiSessionRemoteHome;
import org.apache.struts.util.MessageResources;
/**
 *
 * @author Vidhya
 * @version
 */

public class EventRegPendingPaymentAction extends Action {
    
    /* forward name="success" path="" */
    private final static String SUCCESS = "success";
    
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
        
    
        try{
            MessageResources mr=getResources(request);
            FormEventOrgRenewal payForm=(FormEventOrgRenewal)form;
            HttpSession session = request.getSession(false);
            String cmd=request.getParameter("cmd");
            HLCVaigaiSessionRemote vaiRemote  = initializeVaigaiEJB(request);
            HLCPaymentDetails objPayDet = new HLCPaymentDetails();
            HLCEventRegPendingVO objPendingVO= new HLCEventRegPendingVO();
            HLCRenewalOrganizerDetails objRenewalDet = new HLCRenewalOrganizerDetails();
           if(cmd!=null && cmd.equalsIgnoreCase("viewPaymentDets")){                
             String tempusrId=request.getParameter("usrId");
             String paymentId1=request.getParameter("paymentId");
             String eventId=request.getParameter("eveId");
              
             if(eventId!=null && eventId.trim().length()!=0){
                objPendingVO=vaiRemote.getSingleEventRegPendingDetails(eventId);               
             }
             request.setAttribute("objPendingVO",objPendingVO);
             return mapping.findForward("ViewpendingPayment");
           }
            
            else if(cmd!=null && cmd.equalsIgnoreCase("initPayment")){
              Debug.print("Inside the cmd:"+cmd);
                String eventId= request.getParameter("eveId");
                Debug.print("eventId in the servlet:"+eventId);
                String paymentId= request.getParameter("tempPayId");
                Debug.print("paymentId in the servlet:"+paymentId);
                String totalAmt= request.getParameter("pendAmount");
                Debug.print("totalAmt in the servlet :"+totalAmt);
                request.setAttribute("eventId",eventId);
                request.setAttribute("paymentId",paymentId);
                request.setAttribute("totalAmt",totalAmt);
                return mapping.findForward("frmEventRegPendingPay");
          }
            else if(cmd!=null && cmd.equalsIgnoreCase("updatePendPay")){
                Debug.print("cmd in EventRegPendingPaymentAction :"+cmd);
                    String eventId=request.getParameter("initeventId");
                    Debug.print("eventId in the servlet:"+eventId);
                    String payId=request.getParameter("initpaymentId");
                    Debug.print("payId in the servlet:"+payId);
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
                    String  r11 = payForm.getR11();
                    System.out.print("Check Status :" + r11);
                    String userId=(String)session.getAttribute("userId");
                    Debug.print("userId in servlet :"+userId);
                    
             if(r11.equals("card")){
                        if(payForm.getCcNumber()==null || payForm.getCcNumber().equals("")){
                        CcNumber = 0;
                        }else {
                        CcNumber = Long.parseLong(payForm.getCcNumber());
                        }
                        if(payForm.getCcName()==null || payForm.getCcName().equals("")){
                        ccName ="";
                        }else {
                        ccName=payForm.getCcName();
                        }
                        if(!(payForm.getCcType().equals("")) && payForm.getCcType()!=null){
                        ccType = payForm.getCcType();  
                        }
                        if(payForm.getCcExpMonth()==null || payForm.getCcExpMonth().equals("")){
                        CcExpMonth =0;
                        }else {
                        CcExpMonth=Integer.parseInt(payForm.getCcExpMonth());
                        }
                        if(payForm.getCcExpYear()==null || payForm.getCcExpYear().equals("")){
                        CcExpYear =0;
                        }else{
                        CcExpYear = Integer.parseInt(payForm.getCcExpYear());
                        }
                        if(payForm.getCcCvvid()==null || payForm.getCcCvvid().equals("")){
                        CcCvvid =0;
                        }else{
                        CcCvvid = Integer.parseInt(payForm.getCcCvvid());
                        }
                        check_date= null;
                        objPayDet.setPaymentStatus("card");
                    }       
                  if(r11.equals("check")){
                        if(payForm.getCheckNumber()==null || payForm.getCheckNumber().equals("")){
                        checkNumber=0;
                        }else{
                        checkNumber= Long.parseLong(payForm.getCheckNumber());
                        }
                        if(payForm.getCheckDate().equals("")){
                        check_date= null;
                        }else{
                        check_date =(Date)sdf.parse(payForm.getCheckDate());
                        }
                        objPayDet.setPaymentStatus("Check");
                    }  
                 if(payForm.getAmount()==null || payForm.getAmount()==""){
                    amount=0; 
                    }else{
                    amount = Double.parseDouble(payForm.getAmount());
                    }
                    objRenewalDet.setEventId(Integer.parseInt(eventId));
                    objPayDet.setUserId(userId);
                    objPayDet.setCcName(ccName);
                    objPayDet.setCcType(ccType);
                    objPayDet.setPendPayccNumber(CcNumber);
                    objPayDet.setCcExpMonth(CcExpMonth);
                    objPayDet.setCcExpYear(CcExpYear);
                    objPayDet.setCcCvvid(CcCvvid);
                    objPayDet.setBankName(payForm.getBankName());
                    objPayDet.setCheckName(payForm.getNameOnchk());
                    objPayDet.setCheckDate(check_date);
                    objPayDet.setPendPayCheckNumber(checkNumber);
                    objPayDet.setAmount(amount);
                    objPayDet.setPaymentDate(new Date());
                    objPayDet.setPaymentId(payId);
                    
                    System.out.println("paymenId" + objPayDet.getPaymentId());
                    System.out.println("ccName" + ccName);
                    System.out.println("ccType" +ccType);
                    System.out.println("CcNumber" +CcNumber );
                    System.out.println("CcExpMonth" +CcExpMonth);
                    System.out.println("CcExpYear" +CcExpYear );
                    System.out.println("CcCvvid" +CcCvvid);
                    System.out.println("getBankName" +payForm.getBankName());
                    System.out.println("check_date" +check_date);
                    System.out.println("checkNumber" +checkNumber);
                    System.out.println("amount" +amount);
                    System.out.println("setPaymentDate" +new Date()); 
                
                   if(r11.equals("card")){
                        Debug.print("Sucessfully Redirect to NoVa");
                        session.setAttribute("objRenewalDet",objRenewalDet);
                        session.setAttribute("objPaymentList",objPayDet);
                       
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
                        expYear = expYear.substring(2);
                        Debug.print("expMon:" + expMon);
                        Debug.print("expYear:" + expYear);
                        String expDate = expMon + expYear;
                        request.setAttribute("expDate",expDate);
                        request.setAttribute("amount",String.valueOf(amount));
                        request.setAttribute("chkDigit",String.valueOf(CcCvvid));

                        StringBuffer reqURL = request.getRequestURL();
                        int lastIndex = reqURL.lastIndexOf("/") ;
                        String url ="";
                        if(lastIndex!=-1){
                            url = reqURL.substring(0,lastIndex+1);
                        }
                        String tmpNova  = mr.getMessage("userPendingPay.nova");
                        String nova = url + tmpNova;

                        request.setAttribute("nova",nova);
                        //===========================================
                        return mapping.findForward("novaPage");

                    }else{
                     boolean updateResult=false;
                     updateResult=vaiRemote.updatePendingPayment(objPayDet);
                     Debug.print("updateResult :"+updateResult);
                     request.setAttribute("eventId",eventId);
                     if(updateResult==true){
                     request.setAttribute("paymentMode","check"); 
                     request.setAttribute("amount",String.valueOf(objPayDet.getAmount()));
                     return mapping.findForward("forChkSuccess");
                     }else{
                     
                     request.setAttribute("paymentId",objPayDet.getPaymentId()); 
                     request.setAttribute("price",String.valueOf(objPayDet.getAmount()));    
                     return mapping.findForward("frmEventRegPendingPay");    
                     }
                    }                      
            }
                        
        }
        catch(Exception ex)
        {
            System.err.println("Caught an exception."+ex.getMessage());
            ex.printStackTrace();
        }
        return null;
      
    }
    private HLCVaigaiSessionRemote initializeVaigaiEJB(HttpServletRequest request) throws Exception{
        MessageResources mr=getResources(request);
        String namingfactory=mr.getMessage("ejbclient.namingfactory");
        String contextfactory=mr.getMessage("ejbclient.contextfactory");
        String urlprovider=mr.getMessage("ejbclient.urlprovider");
        String lookupip=mr.getMessage("ejbclient.ip");
        String jndiname=mr.getMessage("jndi.icp");
        
        System.setProperty(namingfactory,contextfactory);
        System.setProperty(urlprovider,lookupip);
        
        Context jndiContext = new InitialContext();
        Object objref = jndiContext.lookup(jndiname);
        HLCVaigaiSessionRemoteHome home = (HLCVaigaiSessionRemoteHome)
        javax.rmi.PortableRemoteObject.narrow(objref,HLCVaigaiSessionRemoteHome.class);
        HLCVaigaiSessionRemote remote = home.create();
        
        
        return remote;
    }
}
