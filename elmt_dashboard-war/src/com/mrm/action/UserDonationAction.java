/*
 * UserDonationAction.java
 *
 * Created on January 9, 2007, 3:37 PM
 */
package com.mrm.action;

import com.hlcHorse.Form.Display.HLCkaverySessionBeanStatlessRemote;
import com.hlcHorse.Form.Display.HLCkaverySessionBeanStatlessRemoteHome;
import com.hlcaccounts.session.HLCMahanadhiSessionRemote;
import com.hlcaccounts.session.HLCMahanadhiSessionRemoteHome;
import com.hlcaccounts.util.HLCAccTransactionVO;
import com.hlcaccounts.util.HLCAccTxnTypeDetailVO;
import com.hlccommon.util.Debug;
import com.hlcform.stateless.HLCkaverystatelessRemote;
import com.hlcform.stateless.HLCkaverystatelessRemoteHome;
import com.hlcform.util.HLCMemberDetails;
import com.hlckavery.stateless.HLCkaveryStatelessRemote;
import com.hlckavery.stateless.HLCkaveryStatelessRemoteHome;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.rmi.*;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.util.MessageResources;
import com.hlcform.util.HLCPaymentDetails;

/**
 *
 * @author vijitha
 * @version
 */
public class UserDonationAction extends Action {

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
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String fwd = "";

        try {
            HttpSession session = request.getSession();

            String reqIp = request.getRemoteAddr();
            Debug.print("request.getRemoteAddr() :" + reqIp);

            String userId = (String) session.getAttribute("userId");
            Debug.print("userId in Servlet:" + userId);

            String donationProcess = request.getParameter("donationProcess");
            Debug.print("DonationProcess in Servlet:" + donationProcess);

            String paymentId = "";
             String logBy="user";
            MessageResources mr = getResources(request);

            String namingfactory = mr.getMessage("ejbclient.namingfactory");
            String contextfactory = mr.getMessage("ejbclient.contextfactory");
            String urlprovider = mr.getMessage("ejbclient.urlprovider");
            String lookupip = mr.getMessage("ejbclient.ip");
            String jndiname3 = mr.getMessage("jndi.kaverymrm");
            String mahanadhiJndi=mr.getMessage("jndi.acc");
            Context jndiContext = new InitialContext();

            String jndiname = mr.getMessage("jndi.usrreg");
            Object objref = jndiContext.lookup(jndiname);

            HLCkaverystatelessRemoteHome home = (HLCkaverystatelessRemoteHome) javax.rmi.PortableRemoteObject.narrow(objref, HLCkaverystatelessRemoteHome.class);
            HLCkaverystatelessRemote remote = home.create();

            Object objref1 = jndiContext.lookup("ejb/HLCkaverySessionBeanStatlessBean");
            HLCkaverySessionBeanStatlessRemoteHome home3 = (HLCkaverySessionBeanStatlessRemoteHome) PortableRemoteObject.narrow(objref1, HLCkaverySessionBeanStatlessRemoteHome.class);
            HLCkaverySessionBeanStatlessRemote remote3 = home3.create();


            HLCMemberDetails objMember = new HLCMemberDetails();
            HLCPaymentDetails objPayment = new HLCPaymentDetails();

            Object objref3 = jndiContext.lookup(jndiname3);
            HLCkaveryStatelessRemoteHome home2 = (HLCkaveryStatelessRemoteHome) javax.rmi.PortableRemoteObject.narrow(objref3, HLCkaveryStatelessRemoteHome.class);
            HLCkaveryStatelessRemote remote2 = home2.create();

            paymentId = remote.getNextId();
            Debug.print("paymentId in member insert servlet :" + paymentId);
               Object maha=jndiContext.lookup(mahanadhiJndi);
        HLCMahanadhiSessionRemoteHome mahaHome = (HLCMahanadhiSessionRemoteHome)
        javax.rmi.PortableRemoteObject.narrow(maha,HLCMahanadhiSessionRemoteHome.class);
        HLCMahanadhiSessionRemote mahaRemote = mahaHome.create();
              ArrayList don_txnVO = new ArrayList();
             HLCAccTransactionVO donaaccTxnVO = new HLCAccTransactionVO();
            ArrayList DonDetails = new ArrayList();
            String r11 = request.getParameter("r11");
             String staff_user_id = (String) session.getAttribute("staff_user_id");

            if (donationProcess.equals("view")) {
                Debug.print("Inside the Donation View Servlet.....");
                ArrayList donDet = new ArrayList();
                donDet = remote2.getAllDonationDetails();
                request.setAttribute("donDet", donDet);
                fwd = "frmViewDonationDetails";
            }
//==================================for User List===========================
            if (donationProcess.equals("list")) {
                Debug.print("\n Inside Donation Listing...\n");
                ArrayList objlist = (ArrayList) remote3.getMemberDonationDetails(userId);
                session.setAttribute("userList", objlist);
                fwd = "frmDonationUserList";
            }

            if (donationProcess.equals("insert")) {
               
                 
                
                
                Debug.print(" DonDetails :"+DonDetails.size());
                
                       /*
                        * payment details
                        */
            
            objMember.setPaymentId(paymentId);
            
            objPayment.setPaymentId(paymentId);
            
            objPayment.setIpAddress(reqIp);
            
            // active status added as per client's requirements july-18-08
            objPayment.setActiveStatus(false);
            
           // -------------------------- choose for payment mode check ----------------------- 
           
            Date check_date=null;
           
            Debug.print("Radio Button value in Servelt:"+r11);

            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyy");
            String donAmt=null;
            boolean donstat=false;
                if(r11.equalsIgnoreCase("check")){
             
                if(request.getParameter("checkDate")!=null && request.getParameter("checkDate").trim().length()!=0)
                {
                    check_date = (Date)sdf.parse(request.getParameter("checkDate"));
                    Debug.print("check_date :"+check_date.toString());
                }
                else
                {
                    check_date =null;
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
                                
                if(request.getParameter("checkNo")!=null && request.getParameter("checkNo").trim().length()!=0)
                {
                    objPayment.setCheckNumber(request.getParameter("checkNo"));
                }
                else
                {
                    objPayment.setCheckNumber("0");
                }
                
                if(request.getParameter("tot_amt1")!=null && request.getParameter("tot_amt1").trim().length()!=0)
                {
                    objPayment.setAmount(Double.valueOf(request.getParameter("tot_amt1")).doubleValue());
                }
                else
                {
                    objPayment.setAmount(0);
                }
                
                String nameOnchk=request.getParameter("nameOnchk");
                
                objPayment.setCheckName(nameOnchk);
                  
                objPayment.setPaymentDate(new Date());
                    
                objPayment.setPaymentStatus("check");  
                
                 boolean pstat=remote.addPaymentDetails(objPayment);
                 Debug.print("Payment insert status in check mode :"+pstat);
                 }
//for donation   
String donCbxCt=request.getParameter("donCbxCt");
                int donCt=Integer.parseInt(donCbxCt);
                Debug.print("donCt :"+donCt);
                
            for(int d=0;d<donCt;d++){
        
            String doncbname="donCb"+d;
            String dontbname="donTb"+d;
            String donNametbname="donNametb"+d;
            
	    System.out.print("dontbname :"+dontbname);
            System.out.print("doncbname :"+doncbname);
            System.out.print("donNametbname :"+donNametbname);
            Debug.print("userId from session :"+userId);
            
            String donChkVal=request.getParameter(doncbname);
            Debug.print("donChkVal in servlet :"+donChkVal);
            
            if(donChkVal!=null)
            {
                String[] DonId=donChkVal.split("#");
                Debug.print("-------------------------------------------");
                Debug.print("DonId :"+DonId[0]);
                Debug.print("DonName :"+DonId[1]); 
                Debug.print("DonPrice :"+DonId[2]);
                Debug.print("-------------------------------------------");
                
                
                String donatorName=request.getParameter(donNametbname);
                Debug.print("donatorName :"+donatorName);
                
                String DonationAmount=request.getParameter(dontbname);
                Debug.print("DonationAmount :"+DonationAmount);
                
                String checkNo=request.getParameter("checkNo");
                Debug.print("checkNo in servlet :"+checkNo);
                
                String checkDate=request.getParameter("checkDate");
                Debug.print("checkDate in servlet :"+checkDate);
                
                String inFavorof=request.getParameter("inFavorof");
                Debug.print("inFavorof in servlet :"+inFavorof);
                
                String nameOnchk1=request.getParameter("nameOnchk");
                Debug.print("nameOnchk in servlet :"+nameOnchk1);
               
                String cardselect=request.getParameter("cardselect");
                
                
                String cardNo=request.getParameter("cardNo");
                
                
                String cVVNo=request.getParameter("cVVNo");
                
                
                String printName=request.getParameter("printName");
                
                
                String expirymonth=request.getParameter("expirymonth");
                
                
                String expiryyear=request.getParameter("expiryyear");
                
                
                if(r11.equalsIgnoreCase("check"))
                {
                    Debug.print("paymentId in servlet :"+paymentId);
                    donstat=remote.insertDonationPriceDetails(userId,DonId[0],DonationAmount,donatorName,paymentId);
                    Debug.print("donation "+d+"insert status:"+donstat);
                }
                
               String[] donVal={userId,DonId[0],DonationAmount,donatorName};
               DonDetails.add(donVal);
               
                   
                    if(DonId[0]!=null || DonId[0].trim().length()!=0){
                        
                        HLCAccTxnTypeDetailVO accTxnDet = mahaRemote.getDonationAccTxnTypeDetails(DonId[0]);
                        //AccTransactionVO accTxnVO = null ;
                        donaaccTxnVO = new HLCAccTransactionVO();
                        
                        String accNo = accTxnDet.getAccount_no();
                        String accClassname = accTxnDet.getClass_name();
                        String accItemNo = accTxnDet.getItem_no();
                        String accAccNo = accTxnDet.getSub_account_no();
                        String accTranName = accTxnDet.getTransaction_name();
                        String accTyped = accTxnDet.getTransaction_type();
                        String accTypeId = accTxnDet.getTransaction_type_id();
                        
                          if(r11.equalsIgnoreCase("check"))
                          {
                            donaaccTxnVO.setPayment_mode("check");
                            donaaccTxnVO.setActive_status(true);
                            donaaccTxnVO.setReconcile_status(true);
                          }   
                         if(r11.equalsIgnoreCase("card"))
                          {
                            donaaccTxnVO.setPayment_mode("card");
                            donaaccTxnVO.setActive_status(true);
                          }   
                       
                       
                        
                        if(DonationAmount!=null && DonationAmount.trim().length()!=0){
                            Debug.print("Inside Donation Amount is "+DonationAmount);
                            donaaccTxnVO.setAmount(Float.parseFloat(DonationAmount));
                        } else{
                            Debug.print("Inside Donation Amount is "+DonationAmount);
                            donaaccTxnVO.setAmount(0);
                        }
                        
                        donaaccTxnVO.setAccount_type("Income");
                        donaaccTxnVO.setClass_Typ(accClassname);
                        //donaaccTxnVO.setUser_id(userId);
                        //donaaccTxnVO.setIp_address(reqIp);
                        donaaccTxnVO.setAccount_no(accNo);
                        donaaccTxnVO.setItem_no(accItemNo);
                        System.out.println(accTranName+"=accTranName");
                        donaaccTxnVO.setDescription(accTranName);
                        donaaccTxnVO.setPayment_id(paymentId);
                        donaaccTxnVO.setSub_account_no(accAccNo);
                        
                        if(r11.equalsIgnoreCase("check")){
                            // Setting staff user_id and ip_address
                            if(session.getAttribute("loggedBy")!=null) {
                                String loggedBy="";
                                loggedBy=(String)session.getAttribute("loggedBy");
                                logBy=loggedBy;
                                
                                donaaccTxnVO.setStaff_user_id(staff_user_id);
                                donaaccTxnVO.setStaff_ip_address(reqIp);
                            }
                            if(r11.equalsIgnoreCase("check")){
                            //End Setting staff_user_id and ip_address
                            boolean status = mahaRemote.insertAccountTxnDetails(donaaccTxnVO);
                            Debug.print("mahaRemote.insertAccountTxnDetails(donaaccTxnVO) for Member addon no"+status);
                            }  
                        } else    {
                                don_txnVO.add(donaaccTxnVO);
                                
                            }
                         
                    }
              
                }

         }        
        if(donstat==true){            
            fwd="insertDonation";
            }            
                
           
      
          //------------------------- choose for payment mode card ------------------------
                
                int CcExpMonth = 0;
                int CcExpYear = 0;
                int CcCvvid =0;
                double amount =0;
                long CcNumber = 0;
                long checkNumber=0;
                
                
             
                Date comp_date = null;
                String ccType=null;
                String ccName=null;
        
           /*     if(r11.equalsIgnoreCase("card")){
                   
                    
                objPayment.setUserId(userId);   
                
                Debug.print("inside payment card select ......"+objPayment.getUserId());
                Debug.print("inside payment card select ......"+objPayment);   
                
                if(request.getParameter("printName")!=null && request.getParameter("printName").trim().length()!=0)
                {
                    objPayment.setCcName(request.getParameter("printName"));
                }
                else
                {
                    objPayment.setCcName(null);
                }
                
                if(request.getParameter("cardselect")!=null && request.getParameter("cardselect").trim().length()!=0)
                {
                    objPayment.setCcType(request.getParameter("cardselect"));
                }
                else
                {
                    objPayment.setCcType(null);
                
                }
                
                 if(request.getParameter("cardNo")!=null && request.getParameter("cardNo").trim().length()!=0)
                {
                     objPayment.setCcNumber(request.getParameter("cardNo"));
                 }
                 else
                 {
                    objPayment.setCcNumber("0");
                 }
                
                if(request.getParameter("expirymonth")!=null && request.getParameter("expirymonth").trim().length()!=0)
                {
                    objPayment.setCcExpMonth(Integer.parseInt(request.getParameter("expirymonth")));
                }
                else
                {
                    objPayment.setCcExpMonth(0);
                }
                
                 if(request.getParameter("expiryyear")!=null && request.getParameter("expiryyear").trim().length()!=0)
                {
                    objPayment.setCcExpYear(Integer.parseInt(request.getParameter("expiryyear")));
                }
                else
                {
                    objPayment.setCcExpYear(0);
                }
                
                if(request.getParameter("cVVNo")!=null && request.getParameter("cVVNo").trim().length()!=0)
                {
                    if(!request.getParameter("cVVNo").equals(""))
                    {
                        objPayment.setCcCvvid(Integer.parseInt(request.getParameter("cVVNo")));
                        request.setAttribute("chkDigit",request.getParameter("cVVNo"));
                    }
                    else
                    {
                        objPayment.setCcCvvid(0);
                        request.setAttribute("chkDigit","0");
                    }
                }
                 else
                {
                    objPayment.setCcCvvid(0);
                    request.setAttribute("chkDigit","0");
                 }
                
                objPayment.setBankName(null);
             
                objPayment.setCheckDate(null);
                objPayment.setCheckNumber("0");
                objPayment.setCheckName(null);
               
                 if(request.getParameter("tot_amt1")!=null && request.getParameter("tot_amt1").trim().length()!=0)
                {
                    objPayment.setAmount(Double.valueOf(request.getParameter("tot_amt1")).doubleValue());
                }
                else
                {
                    objPayment.setAmount(0);
                }
                
                objPayment.setPaymentDate(new Date());
                objPayment.setPaymentStatus("card");
                
                    Debug.print("inside payment card final ......"+objPayment.getAmount());   
                    
                    session.setAttribute("DonDetails",null);
                    session.setAttribute("objPayment",null);
                    
                    session.setAttribute("DonDetails",DonDetails);
                    session.setAttribute("objPayment",objPayment);
                    
                    String expYear = request.getParameter("expiryyear").substring(2);
                    
                    String expDate=request.getParameter("expirymonth")+expYear;
                    
                    request.setAttribute("email",session.getAttribute("emailId"));
                    request.setAttribute("cardNo",objPayment.getCcNumber());
                    request.setAttribute("expDate",expDate);
                    request.setAttribute("amount",request.getParameter("tot_amt"));
                    StringBuffer reqURL = request.getRequestURL();
                    int lastIndex = reqURL.lastIndexOf("/") ;
                    String url ="";
                    if(lastIndex!=-1){
                        url = reqURL.substring(0,lastIndex+1);
                    }
                    String tmpNova  = mr.getMessage("donate.nova");
                    String nova = url + tmpNova;
                    Debug.print("succsssullll from app file nova........::" + nova);
                    Debug.print("succsssullll from app file tmpNova ........::" + tmpNova);
                    request.setAttribute("nova",nova);
                    session.setAttribute("donationNo",don_txnVO);
                    session.setAttribute("donCt",String.valueOf(donCt)); 
                 
          
                    fwd="novaPage";
                    
                }*/
                
            if(r11.equalsIgnoreCase("card")){
                   
                  /* Debug.print("paymentId in servlet :"+paymentId);
                    donstat1=remote.insertDonationPriceDetails(userId,DonId[0],DonationAmount,donatorName,paymentId);
                    Debug.print("donation "+d+"insert status:"+donstat);*/
                    
                objPayment.setUserId(userId);   
                
               
                Debug.print("inside payment card select ......"+objPayment.getUserId());
                //Debug.print("inside payment card select ......"+objPayment);   
                
                if(request.getParameter("printName")!=null && request.getParameter("printName").trim().length()!=0)
                {
                    objPayment.setCcName(request.getParameter("printName"));
                }
                else
                {
                    objPayment.setCcName(null);
                }
                
                if(request.getParameter("cardselect")!=null && request.getParameter("cardselect").trim().length()!=0)
                {
                    objPayment.setCcType(request.getParameter("cardselect"));
                }
                else
                {
                    objPayment.setCcType(null);
                
                }
                
                 if(request.getParameter("cardNo")!=null && request.getParameter("cardNo").trim().length()!=0)
                {
                     objPayment.setCcNumber(request.getParameter("cardNo"));
                 }
                 else
                 {
                    objPayment.setCcNumber("0");
                 }
                
                if(request.getParameter("expirymonth")!=null && request.getParameter("expirymonth").trim().length()!=0)
                {
                    objPayment.setCcExpMonth(Integer.parseInt(request.getParameter("expirymonth")));
                }
                else
                {
                    objPayment.setCcExpMonth(0);
                }
                                       
                if(request.getParameter("cVVNo")!=null && request.getParameter("cVVNo").trim().length()!=0)
                {
                    if(!request.getParameter("cVVNo").equals(""))
                    {
                        objPayment.setCcCvvid(Integer.parseInt(request.getParameter("cVVNo")));
                        request.setAttribute("chkDigit",request.getParameter("cVVNo"));
                    }
                    else
                    {
                        objPayment.setCcCvvid(0);
                        request.setAttribute("chkDigit","0");
                    }
                }
                 else
                {
                    objPayment.setCcCvvid(0);
                    request.setAttribute("chkDigit","0");
                 }
                
                objPayment.setBankName(null);
             
                objPayment.setCheckDate(null);
                objPayment.setCheckNumber("0");
                objPayment.setCheckName(null);
               
                 if(request.getParameter("tot_amt1")!=null && request.getParameter("tot_amt1").trim().length()!=0)
                {
                    objPayment.setAmount(Double.valueOf(request.getParameter("tot_amt1")).doubleValue());
                }
                else
                {
                    objPayment.setAmount(0);
                }
                
                objPayment.setPaymentDate(new Date());
                objPayment.setPaymentStatus("card");
                                     
                   /* Debug.print("inside payment card final ......"+objPayment.getAmount());   
                    
                    session.setAttribute("DonDetails",null);
                    session.setAttribute("objPayment",null);
                    
                    session.setAttribute("DonDetails",DonDetails);
                    session.setAttribute("objPayment",objPayment);
                    */
                
                    String expYear = request.getParameter("expiryyear");
                    
                    String expDate=request.getParameter("expirymonth")+expYear;
                     //System.out.println("expDate"+expDate);
      
   if(request.getParameter("expiryyear")!=null && request.getParameter("expiryyear").trim().length()!=0)
                {
                    objPayment.setCcExpYear(Integer.parseInt(expYear));
                }
                else
                {
                    objPayment.setCcExpYear(0);
                }                   
                    /*request.setAttribute("email",session.getAttribute("emailId"));
                    request.setAttribute("cardNo",objPayment.getCcNumber());
                    request.setAttribute("expDate",expDate);
                    request.setAttribute("amount",request.getParameter("tot_amt"));
                    StringBuffer reqURL = request.getRequestURL();
                    int lastIndex = reqURL.lastIndexOf("/") ;
                    String url ="";
                    if(lastIndex!=-1){
                        url = reqURL.substring(0,lastIndex+1);
                    }
                    String tmpNova  = mr.getMessage("donate.nova");
                    String nova = url + tmpNova;
                    Debug.print("succsssullll from app file nova........::" + nova);
                    Debug.print("succsssullll from app file tmpNova ........::" + tmpNova);
                    request.setAttribute("nova",nova);
                    session.setAttribute("donationNo",don_txnVO);
                    session.setAttribute("donCt",String.valueOf(donCt)); 
                 */
        //String reqIp=request.getRemoteAddr();
        
                     
        //String inVId=request.getParameter("inVoiceId");
      
        int intVId=0;
        String inVoiceId1="1";
        if(inVoiceId1.equalsIgnoreCase("0")){
           intVId=0; 
        }else{
           intVId=1;  
        }
            
    request.setAttribute("AMT", request.getParameter("tot_amt1"));
    request.setAttribute("PAYMENTACTION", "Authorization");
    request.setAttribute("CREDITCARDTYPE",request.getParameter("cardselect"));     
    request.setAttribute("ACCT", request.getParameter("cardNo"));  
    request.setAttribute("EXPDATE", expDate);  
    request.setAttribute("IPADDRESS", reqIp);  
    request.setAttribute("FIRSTNAME", request.getParameter("printName"));
    request.setAttribute("CVVNo",request.getParameter("cVVNo"));
    request.setAttribute("STREET", "1 Main St");
    request.setAttribute("CITY", "San Jose");
    request.setAttribute("STATE", "CA");
    request.setAttribute("ZIP", "95131");     
    request.setAttribute("COUNTRYCODE", "US");
    request.setAttribute("EMAIL", (String)session.getAttribute("emailId")); 
    session.setAttribute("objPayment", objPayment); 
    session.setAttribute("donCt",String.valueOf(donCt)); 
    session.setAttribute("donationNo",don_txnVO);
    request.setAttribute("transacName", "donateReg"); 
    session.setAttribute("DonDetails",DonDetails);
    session.setAttribute("URLPATH","DonationAction.do?donationProcess=view");
    String sessionInvoiceId = "1";
    request.setAttribute("purpose","newDonation");
    session.setAttribute("sessionInvoiceId",sessionInvoiceId);
                        
     //intVId++; 
     request.setAttribute("intVId", String.valueOf(intVId));
    System.out.println("intVId in servlet&&&&&&&"+intVId);
    fwd="testing";
                    
                }      
                
               
                
      
            }
       
        } catch (Exception einsert) {
            System.out.println("Donate Action Error " + einsert.toString());
        }
        return mapping.findForward(fwd);

    }
}
       
    

