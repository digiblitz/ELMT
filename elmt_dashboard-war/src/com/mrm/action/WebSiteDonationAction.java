/*
 * WebSiteDonationAction.java
 *
 * Created on February 14, 2008, 1:33 PM
 */

package com.mrm.action;  
import com.hlcHorse.Form.Display.HLCkaverySessionBeanStatlessRemote;
import com.hlcHorse.Form.Display.HLCkaverySessionBeanStatlessRemoteHome;
import com.hlccommon.util.Debug;
import com.hlcform.stateless.HLCkaverystatelessRemote;
import com.hlcform.stateless.HLCkaverystatelessRemoteHome;
import com.hlcform.util.HLCMemberDetails;
import com.hlckavery.stateless.HLCkaveryStatelessRemote;
import com.hlckavery.stateless.HLCkaveryStatelessRemoteHome;
import java.util.ArrayList;
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
 * @author Vidhya
 * @version
 */
public class WebSiteDonationAction extends Action {
    
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
        
        String fwd="";
        
        try {
            HttpSession session=request.getSession();
            
            String reqIp=request.getRemoteAddr();
            Debug.print("request.getRemoteAddr() :"+reqIp);
           
            String donationProcess = request.getParameter("donationProcess");
            Debug.print("DonationProcess in Servlet:"+donationProcess);
            
            String paymentId ="";
                                   
            MessageResources mr=getResources(request);
                   
            String namingfactory=mr.getMessage("ejbclient.namingfactory");
            String contextfactory=mr.getMessage("ejbclient.contextfactory");
            String urlprovider=mr.getMessage("ejbclient.urlprovider");
            String lookupip=mr.getMessage("ejbclient.ip");
            String jndiname3=mr.getMessage("jndi.kaverymrm");
            Context jndiContext = new InitialContext();
            
            String jndiname=mr.getMessage("jndi.usrreg");
            Object objref = jndiContext.lookup(jndiname);

                     
            HLCkaverystatelessRemoteHome home = (HLCkaverystatelessRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(objref,HLCkaverystatelessRemoteHome.class);
            HLCkaverystatelessRemote remote = home.create();
            
            Object objref1 = jndiContext.lookup("ejb/HLCkaverySessionBeanStatlessBean");
            HLCkaverySessionBeanStatlessRemoteHome home3 =(HLCkaverySessionBeanStatlessRemoteHome) PortableRemoteObject.narrow(objref1, HLCkaverySessionBeanStatlessRemoteHome.class);
            HLCkaverySessionBeanStatlessRemote remote3 = home3.create();


            HLCMemberDetails objMember = new HLCMemberDetails();
            HLCPaymentDetails objPayment = new HLCPaymentDetails();

            Object objref3 = jndiContext.lookup(jndiname3);
            HLCkaveryStatelessRemoteHome home2 = (HLCkaveryStatelessRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(objref3,HLCkaveryStatelessRemoteHome.class);
            HLCkaveryStatelessRemote remote2 = home2.create();
            
            paymentId = remote.getNextId();
            Debug.print("paymentId in member insert servlet :"+paymentId);
            
             ArrayList DonDetails = new ArrayList();
             String r11=request.getParameter("r11");
            
            if (donationProcess.equals("view")){
                Debug.print("donationProcess in WebSiteDonationAction :"+donationProcess);
                ArrayList donDet=new ArrayList();  
                donDet=remote2.getAllDonationDetails();
                request.setAttribute("donDet",donDet);
                fwd="frmViewDonationDetails";
              }
//==================================for User List===========================
             /*if(donationProcess.equals("list")){
                    Debug.print("\n Inside Donation Listing...\n");                                          
                    ArrayList objlist = (ArrayList)remote3.getMemberDonationDetails(userId);
                    session.setAttribute("userList",objlist);
                    fwd="frmDonationUserList";            
                }*/
            
             if(donationProcess.equals("insert")){
                 Debug.print("donationProcess in WebSiteDonationAction :"+donationProcess);
                 String userId =null;
                 boolean insertContact=false;
                 Debug.print("0");
                 String prefix=request.getParameter("txtPrefix");
                 String firstName="";
                 String lastName="";
                 String memCity="";
                 String memState="";
                 String address1=request.getParameter("sadd_txt");
                 String address2=request.getParameter("sadd_txt1");
                 Debug.print("01");
                 String city=request.getParameter("scity_txt");
                 String state=request.getParameter("sstate_txt");
                 String country=request.getParameter("scountry_txt");
                 String zip=request.getParameter("szip_txt");
                 String phoneNo=request.getParameter("sphone_txt");
                 String mobileNo=request.getParameter("smob_txt");
                 String faxNo=null;//request.getParameter(null);
                 String email=request.getParameter("email");
                 
                 String hlcNo=request.getParameter("hlcNo");
                 Debug.print("hlcNo :"+hlcNo);
                 if(hlcNo!=null && hlcNo.trim().length()!=0){
                 Debug.print("2");
                 Debug.print("hlcNo inside if:"+hlcNo);
                 userId=remote.getUserIdBasedOnMemberId(hlcNo);
                 firstName=request.getParameter("txtFName");
                 lastName=request.getParameter("txtLName");
                 memCity=request.getParameter("txtCity");
                 memState=request.getParameter("txtState");
                 }else{
                 Debug.print("3");
                 Debug.print("hlcNo inside else:"+hlcNo);
                 userId=remote.getUserIDByLoginName("hlcdonate");
                 firstName=request.getParameter("txtFirstName");
                 lastName=request.getParameter("txtLastName");
                 }
                 Debug.print("4");
                 Debug.print("userId in Servlet:"+userId); 
                
// for storing donation payment details 
                String cardselect=request.getParameter("cardselect");
                Debug.print("cardselect in servlet :"+cardselect);
                
                String cardNo=request.getParameter("cardNo");
                Debug.print("cardNo in servlet :"+cardNo);
                
                String cVVNo=request.getParameter("cVVNo");
                Debug.print("cVVNo in servlet :"+cVVNo);
                
                String printName=request.getParameter("printName");
                Debug.print("printName in servlet :"+printName);
                
                String expirymonth=request.getParameter("expirymonth");
                Debug.print("expirymonth in servlet :"+expirymonth);
                
                String expiryyear=request.getParameter("expiryyear");
                Debug.print("expiryyear in servlet :"+expiryyear);
                
                if(paymentId!=null && paymentId.trim().length()!=0){objPayment.setPaymentId(paymentId);
                }else{objPayment.setPaymentId(null);}
                if(userId!=null && userId.trim().length()!=0){objPayment.setUserId(userId);
                }else{objPayment.setUserId(null);}  
                if(prefix!=null && prefix.trim().length()!=0){objPayment.setPrefix(prefix);
                }else{objPayment.setPrefix(null);} 
                if(firstName!=null && firstName.trim().length()!=0){objPayment.setFirstName(firstName);
                }else{objPayment.setFirstName(null);} 
                if(lastName!=null && lastName.trim().length()!=0){objPayment.setLastName(lastName);
                }else{objPayment.setLastName(null);} 
                if(email!=null && email.trim().length()!=0){objPayment.setEmailId(email);
                }else{objPayment.setEmailId(null);}
                if(memCity!=null && memCity.trim().length()!=0){objPayment.setCity(memCity);
                }else{objPayment.setCity(null);} 
                if(memState!=null && memState.trim().length()!=0){objPayment.setState(memState);
                }else{objPayment.setState(null);} 
                
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
                
                if(request.getParameter("tot_amt1")!=null && request.getParameter("tot_amt1").trim().length()!=0)
                {
                    objPayment.setAmount(Double.valueOf(request.getParameter("tot_amt1")).doubleValue());
                }
                else
                {
                    objPayment.setAmount(0);
                }
                //objPayment.setPaymentStatus("card");
                boolean donPay=remote.insertWebSiteDonPayDetails(objPayment);
                Debug.print("donation insert status:"+donPay); 
                 
//for storing donation details                
                String donCbxCt=request.getParameter("donCbxCt");
                int donCt=Integer.parseInt(donCbxCt);
                Debug.print("donCt :"+donCt);
                
            for(int d=0;d<donCt;d++){
            Debug.print("5");
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
                Debug.print("6");
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
              
                    boolean donstat=remote.insertWebSiteDonationDetails(userId,DonId[0],DonationAmount,donatorName,paymentId);
                    Debug.print("donation "+d+"insert status:"+donstat);
                
                }//closing if
             }//closing for
                 Debug.print("7");
                 Debug.print(" DonDetails :"+DonDetails.size());
                 
     //for storing contact details            
                 if(hlcNo!=null && hlcNo.trim().length()!=0){
                 Debug.print("8");
                 Debug.print("hlcNo inside if:"+hlcNo);
                 }else{
                 Debug.print("9");
                 Debug.print("hlcNo inside else:"+hlcNo);
                 insertContact=remote.insertWebSiteContactDetails(userId,address1,address2,city,state,country,zip,phoneNo,mobileNo,faxNo);        
                 }
                 Debug.print("insertContact in Servlet:"+insertContact); 
                 
                 ArrayList donList=new ArrayList();
                 donList=remote3.getWebSiteDonationDetails(paymentId);
                 request.setAttribute("donList",donList);
                 Debug.print("donList size :"+donList.size());
                 fwd="insertDonation";
           
       }//closing insert
       }//closing try
        catch(Exception einsert){
                System.out.println("Donate Action Error " + einsert.toString());
        } 
        return mapping.findForward(fwd);
        
    }

}
       
    

