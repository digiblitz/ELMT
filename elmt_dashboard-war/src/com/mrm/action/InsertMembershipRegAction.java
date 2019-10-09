/*
 * InsertMembershipRegAction.java
 *
 * Created on September 7, 2006, 2:12 AM
 */

package com.mrm.action;

import com.hlcform.stateless.HLCkaverystatelessRemote;
import com.hlcform.stateless.HLCkaverystatelessRemoteHome;
import com.hlcform.util.HLCContactDetails;
import com.hlcform.util.Debug;
import com.hlcform.util.HLCMemberDetails;
import com.hlcform.util.HLCPaymentDetails;
import com.hlcform.util.HLCUserMaster;
import com.mrm.actionform.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.*;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.util.MessageResources;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.text.*;
import java.util.regex.*;

/**
 *
 * @author karthikeyan
 * @version
 */

public class InsertMembershipRegAction extends Action {
       
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        String userId = null;
        
         try {
        
                    MessageResources mr=getResources(request);
                    
                    String namingfactory=mr.getMessage("ejbclient.namingfactory");
                    String contextfactory=mr.getMessage("ejbclient.contextfactory");
                    String urlprovider=mr.getMessage("ejbclient.urlprovider");
                    String lookupip=mr.getMessage("ejbclient.ip");
                    String jndiname=mr.getMessage("jndi.humanjndi");
                                      
                    System.setProperty(namingfactory,contextfactory);
                    System.setProperty(urlprovider,lookupip);
                    Context jndiContext = new InitialContext();
                    HLCkaverystatelessRemoteHome home =(HLCkaverystatelessRemoteHome) jndiContext.lookup(jndiname);    
                               
                    HLCkaverystatelessRemote remote = home.create();
                
                    HLCUserMaster objUserMaster = new HLCUserMaster();
                    HLCContactDetails objContact = new HLCContactDetails();
                    HLCMemberDetails objMember = new HLCMemberDetails();
                    HLCPaymentDetails objPayment = new HLCPaymentDetails();

                    System.out.println("\n after InitialContext Beginning emp Client...\n");
                    
                    InsertMembershipRegActionForm membbean=(InsertMembershipRegActionForm)form;
                    
     //------------------------------ Selected Membership Type Id -----------------------------
                    
                   String seldisp=membbean.getSelDisp();
                 
                   Pattern mempat=Pattern.compile("#");
                   String mem[]=mempat.split(seldisp);
                   String memid=mem[0];
                   String memname=mem[1];
                   String memamt=mem[2];
                   System.out.println(memid+"  "+memname+ "  "+memamt);
                   
      // ------------------------ Split Non HLC Memb Name -------------------------------------
                
                   String selmem=membbean.getMemType();
                 
                   Pattern nonhlcpat=Pattern.compile("#");
                   String nonhlc[]=nonhlcpat.split(selmem);
                   String nonid=nonhlc[0];
                   String nonmname=nonhlc[1];
                   String nonamt=nonhlc[2];
                   System.out.println(nonid+"  "+nonmname+ "  "+nonamt);
                   
      //============================== Get User Id & DOB =======================================
                    
                int k=0;
                String mail = "manas@digiblitz.com";
                String date = null;
                Collection col = null;
               
                Vector vObj=new Vector();
               vObj = remote.getUserIdByEmailId(mail);
               // vObj = remote.getLoginStatus(login, pass);
              Enumeration it = vObj.elements();
              
                while(it.hasMoreElements()){
                    String s = (String)it.nextElement();
                   // for (int i=0;i<s.length; i++) {
                    if (k==0){
                             userId = s;k++;
                         }else {
                             date = s;
                         }
                    //userId = s;
                        System.out.println("In Client Program user ID     "+userId);
                        
                   // }
                }
                   
     //----------------------------- Payment details ---------------------------------------
    
                SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyy");
                Date imp_date =null;
                               
                int CcExpMonth = 0;
                int CcExpYear = 0;
                int CcCvvid =0;
                double amount =0;
                long CcNumber = 0;
                long checkNumber=0;
                Date check_date = null;
                Date comp_date = null;
                String ccType=null;
                String ccName=null;
                
                String  r11 = membbean.getR11();
                
      //------------------------- choose for payment mode card ------------------------
                
                if(r11.equals("card")){
                
                objPayment.setUserId(userId);
                objPayment.setCcName(membbean.getPrName());
                objPayment.setCcType(membbean.getCtypSelect());
                objPayment.setCcNumber(membbean.getCrdNo());
                objPayment.setCcExpMonth(Integer.parseInt(membbean.getExpirymonth()));
                objPayment.setCcExpYear(Integer.parseInt(membbean.getExpiryyear()));
                objPayment.setCcCvvid(Integer.parseInt(membbean.getCvvNo()));
                objPayment.setAmount(Double.valueOf(membbean.getTotAmt()).doubleValue());
                objPayment.setPaymentStatus("card");
                
                objPayment.setBankName(null);
                objPayment.setCheckDate(new Date());
                objPayment.setCheckNumber("0");
                objPayment.setPaymentDate(new Date());
                
                System.out.println(objPayment.getCcNumber());
                System.out.println(objPayment.getCcExpMonth());
                System.out.println(objPayment.getCcExpYear());
                System.out.println(objPayment.getCcCvvid());
                System.out.println(objPayment.getBankName());
                System.out.println(objPayment.getCheckDate());
                System.out.println(objPayment.getCheckNumber());
                System.out.println(objPayment.getAmount());
                System.out.println(objPayment.getPaymentDate());
                System.out.println(objPayment.getPaymentStatus());
                               
                }
                
      // -------------------------- choose for payment mode check ----------------------- 
                
                if(r11.equals("check")){
             
                check_date =(Date)sdf.parse(membbean.getChkDat());
                
                objPayment.setUserId(userId);
                objPayment.setCcName(null);
                objPayment.setCcType(null);
                objPayment.setCcNumber("0");
                objPayment.setCcExpMonth(0);
                objPayment.setCcExpYear(0);
                objPayment.setCcCvvid(0);
                
                objPayment.setBankName(membbean.getBank());
                objPayment.setCheckDate(check_date);
                objPayment.setCheckNumber(membbean.getChkNo());
                objPayment.setAmount(Double.valueOf(membbean.getTotAmt()).doubleValue());
                objPayment.setPaymentDate(new Date());
                objPayment.setPaymentStatus("check");  
                
                System.out.println(objPayment.getCcNumber());
                System.out.println(objPayment.getCcExpMonth());
                System.out.println(objPayment.getCcExpYear());
                System.out.println(objPayment.getCcCvvid());
                System.out.println(objPayment.getBankName());
                System.out.println(objPayment.getCheckDate());
                System.out.println(objPayment.getCheckNumber());
                System.out.println(objPayment.getAmount());
                System.out.println(objPayment.getPaymentDate());
                System.out.println(objPayment.getPaymentStatus());
                
                }
            
    //------------------------------ Selected Mail Address Type Id -----------------------------
                    
                   String maddrid=membbean.getMailAddr();
                 
                   Pattern mailpat=Pattern.compile("#");
                   String str[]=mailpat.split(maddrid);
                   String ctryid=str[0];
                   String ctname=str[1];
                   String camt=str[2];
                   System.out.println("mail addr :" + ctryid+"  "+ctname+ "  "+camt);
                
                
    //==============================Member Details=======================================
                   
    //-------------------------- Full or Life Member Rec Insert ---------------------------------------
             
                if(memid.equalsIgnoreCase("Full Member")||memid.equalsIgnoreCase("Life Member"))
                {
                objMember.setUserId(userId);
                objMember.setEmailId("manas@digiblitz.com");
                objMember.setMembershipTypeId(memid);
                
                    if(!memname.equalsIgnoreCase("Select One"))
                {
                objMember.setNonuseaOrgId(nonid);
                objMember.setNonUseaOrgMemberId(membbean.getMemId());
                }
                
                String don=request.getParameter("donAmt_sel");
                
                    if(don.equalsIgnoreCase("Others"))
                {
                    objMember.setEndowmentTrustAmount(request.getParameter("otherdon"));
                }
                    else
                    {
                        objMember.setEndowmentTrustAmount(don);
                    }
                
                objMember.setCountryMailTypeId(ctryid);
                objMember.setFamilyAddOns(request.getParameter("famMemb"));
                Debug.print("request.getParameter(famMemb) :"+request.getParameter("famMemb"));
               
                String fammem=request.getParameter("famMemb");
                                
                if(fammem.equalsIgnoreCase("One"))
                {
                                     
                    Date s_date =null;
                    String dob=membbean.getBirthmonth1()+"/"+membbean.getBirthday1()+"/"+membbean.getBirthyear1();
                    s_date=(Date)sdf.parse(dob);
                    
                    System.out.println(s_date);
                    
                objUserMaster.setCommunicationAddress("Primary");
                objUserMaster.setPrefix(membbean.getSelect1());
                objUserMaster.setFirstName(request.getParameter("fname1"));
                objUserMaster.setMiddleName(request.getParameter("mname1"));
                objUserMaster.setLastName(request.getParameter("lname1"));
                objUserMaster.setSufix(request.getParameter("suffix1"));
                objUserMaster.setDob(dob);
                objUserMaster.setGender(membbean.getGender1());
                objUserMaster.setEmailId("manas@yahoo.com");
                objUserMaster.setPassword(null);
                objUserMaster.setSecretQuestion(null);
                objUserMaster.setSecretAnswer(null);
               
                remote.addUserRegistration(objUserMaster);
               
                objContact.setContactTypeId("0cc42d09-340c-42e8-b773-54bdf0688527");
                objContact.setContactType("Primary");
                objContact.setSuite(null);
                objContact.setAddress1(null);
                objContact.setAddress2(null);
                objContact.setCity(null);
                objContact.setState(null);
                objContact.setCountry(null);
                objContact.setZip(null);
                objContact.setPhoneNo(membbean.getPhone1());
                objContact.setMobileNo(membbean.getMobile1());
                objContact.setFaxNo(membbean.getFax1());
                
                remote.addContactDetails(objContact);
                
            /*    System.out.println(objUserMaster.getCommunicationAddress());
                System.out.println(objUserMaster.getPrefix(membbean.getSelect1()));
                System.out.println(objUserMaster.getFirstName(request.getParameter()));
                System.out.println(objUserMaster.getMiddleName(request.getParameter()));
                System.out.println(objUserMaster.setLastName(request.getParameter()));
                System.out.println(objUserMaster.getSufix(request.getParameter()));
                System.out.println(objUserMaster.getDob());
                System.out.println(objUserMaster.getGender());
                System.out.println(objUserMaster.getEmailId());
                System.out.println(objUserMaster.getPassword());
                System.out.println(objUserMaster.getSecretQuestion());
                System.out.println(objUserMaster.getSecretAnswer(s));
                
                System.out.println(objContact.getContactTypeId());
                 System.out.println(objContact.getContactType());
                 System.out.println(objContact.getSuite());
                 System.out.println(objContact.getAddress1());
                 System.out.println(objContact.getAddress2());
                 System.out.println(objContact.getCity());
                 System.out.println(objContact.getState());
                 System.out.println(objContact.getCountry());
                 System.out.println(objContact.getZip());
                 System.out.println(objContact.getPhoneNo());
                 System.out.println(objContact.getMobileNo());
                 System.out.println(objContact.getFaxNo());*/
                
                }
                
                 if(fammem.equalsIgnoreCase("Two"))
                {
               
                    Date s_date =null;
                    String dob=membbean.getBirthmonth2()+"/"+membbean.getBirthday2()+"/"+membbean.getBirthyear2();
                    s_date=(Date)sdf.parse(dob);
                    
                    System.out.println(s_date);
                    
                objUserMaster.setCommunicationAddress("Primary");
                objUserMaster.setPrefix(membbean.getSelect2());
                objUserMaster.setFirstName(request.getParameter("fname2"));
                objUserMaster.setMiddleName(request.getParameter("mname2"));
                objUserMaster.setLastName(request.getParameter("lname2"));
                objUserMaster.setSufix(request.getParameter("suffix2"));
                objUserMaster.setDob(dob);
                objUserMaster.setGender(membbean.getGender2());
                objUserMaster.setEmailId("manas@yahoo.com");
                objUserMaster.setPassword(null);
                objUserMaster.setSecretQuestion(null);
                objUserMaster.setSecretAnswer(null);
               
                remote.addUserRegistration(objUserMaster);
               
                objContact.setContactTypeId("0cc42d09-340c-42e8-b773-54bdf0688527");
                objContact.setContactType("Primary");
                objContact.setSuite(null);
                objContact.setAddress1(null);
                objContact.setAddress2(null);
                objContact.setCity(null);
                objContact.setState(null);
                objContact.setCountry(null);
                objContact.setZip(null);
                objContact.setPhoneNo(membbean.getPhone2());
                objContact.setMobileNo(membbean.getMobile2());
                objContact.setFaxNo(membbean.getFax2());
                
                remote.addContactDetails(objContact);
                    
                 }
                   
             if(fammem.equalsIgnoreCase("Three"))
                {
               
                   
                    Date s_date =null;
                    String dob=membbean.getBirthmonth3()+"/"+membbean.getBirthday3()+"/"+membbean.getBirthyear3();
                    s_date=(Date)sdf.parse(dob);
                    
                    System.out.println(s_date);
                    
                objUserMaster.setCommunicationAddress("Primary");
                objUserMaster.setPrefix(membbean.getSelect3());
                objUserMaster.setFirstName(request.getParameter("fname3"));
                objUserMaster.setMiddleName(request.getParameter("mname3"));
                objUserMaster.setLastName(request.getParameter("lname3"));
                objUserMaster.setSufix(request.getParameter("suffix3"));
                objUserMaster.setDob(dob);
                objUserMaster.setGender(membbean.getGender3());
                objUserMaster.setEmailId("manas@yahoo.com");
                objUserMaster.setPassword(null);
                objUserMaster.setSecretQuestion(null);
                objUserMaster.setSecretAnswer(null);
               
                remote.addUserRegistration(objUserMaster);
               
                objContact.setContactTypeId("0cc42d09-340c-42e8-b773-54bdf0688527");
                objContact.setContactType("Primary");
                objContact.setSuite(null);
                objContact.setAddress1(null);
                objContact.setAddress2(null);
                objContact.setCity(null);
                objContact.setState(null);
                objContact.setCountry(null);
                objContact.setZip(null);
                objContact.setPhoneNo(membbean.getPhone3());
                objContact.setMobileNo(membbean.getMobile3());
                objContact.setFaxNo(membbean.getFax3());
                
                remote.addContactDetails(objContact);
                    
                 }
                   
                if(fammem.equalsIgnoreCase("Four"))
                {
               
                    Date s_date =null;
                    String dob=membbean.getBirthmonth4()+"/"+membbean.getBirthday4()+"/"+membbean.getBirthyear4();
                    s_date=(Date)sdf.parse(dob);
                    
                    System.out.println(s_date);
                    
                objUserMaster.setCommunicationAddress("Primary");
                objUserMaster.setPrefix(membbean.getSelect4());
                objUserMaster.setFirstName(request.getParameter("fname4"));
                objUserMaster.setMiddleName(request.getParameter("mname4"));
                objUserMaster.setLastName(request.getParameter("lname4"));
                objUserMaster.setSufix(request.getParameter("suffix4"));
                objUserMaster.setDob(dob);
                objUserMaster.setGender(membbean.getGender4());
                objUserMaster.setEmailId("manas@yahoo.com");
                objUserMaster.setPassword(null);
                objUserMaster.setSecretQuestion(null);
                objUserMaster.setSecretAnswer(null);
               
                remote.addUserRegistration(objUserMaster);
               
                objContact.setContactTypeId("0cc42d09-340c-42e8-b773-54bdf0688527");
                objContact.setContactType("Primary");
                objContact.setSuite(null);
                objContact.setAddress1(null);
                objContact.setAddress2(null);
                objContact.setCity(null);
                objContact.setState(null);
                objContact.setCountry(null);
                objContact.setZip(null);
                objContact.setPhoneNo(membbean.getPhone4());
                objContact.setMobileNo(membbean.getMobile4());
                objContact.setFaxNo(membbean.getFax4());
                
                remote.addContactDetails(objContact);
                    
                 }
                
                }
                   
               if(memname.equalsIgnoreCase("Family Member"))
                {
                objMember.setParentMemberId(membbean.getMemNo());
               }
              remote.addMemberDetails(objMember, objPayment);
          //  addOnMemberId = remote.getMemberId("manas@digiblitz.com");
          //  System.out.println("Add On member Id is : "+addOnMemberId);
                 
               
                System.out.println(objUserMaster.getGender());
                System.out.println(objContact.getPhoneNo());
                System.out.println(objContact.getMobileNo());
                System.out.println(objContact.getFaxNo());
                System.out.println(objUserMaster.getEmailId());
                System.out.println(objUserMaster.getPassword());
                /*System.out.println(objUserMaster.);
                System.out.println(usrbean.getAns());
                System.out.println(usrbean.getPriAdd_cbx());
                System.out.println(usrbean.getSecAdd_cbx());
                System.out.println(usrbean.getPadd_txt());
                System.out.println(usrbean.getPadd_txt2());
                System.out.println(usrbean.getPcountry_sel());
                System.out.println(usrbean.getPstate_sel());
                System.out.println(usrbean.getPcity_txt());
                System.out.println(usrbean.getPzip_txt());
                System.out.println(usrbean.getSadd_txt());
                System.out.println(usrbean.getSadd_txt1());
                System.out.println(usrbean.getScountry_txt());
                System.out.println(usrbean.getSstate_txt());
                System.out.println(usrbean.getScity_txt());
                System.out.println(usrbean.getSzip_txt());
                System.out.println(usrbean.getComAdd_sel());*/
   }  
                 catch (Exception ex) {
            System.err.println("Caught an exception.");
            ex.printStackTrace();
        }
        
        return mapping.findForward("InsertMembershipReg");
    }
        
    }
 
         
            


