/*
 * UsrSearchSignup.java
 *
 * Created on January 31, 2007, 4:10 PM
 */

package com.mrm.action;

import com.hlccommon.util.Debug;
import com.hlcform.util.HLCContactDetails;
import com.hlcform.util.HLCMemberDetails;
import com.hlcform.util.HLCPaymentDetails;
import com.hlcform.util.HLCUserMaster;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import org.apache.struts.util.MessageResources;
import com.hlcrole.management.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mysql.dao.MySQLDAO;
import com.hlcform.stateless.*;
/**
 *
 * @author hari
 * @version
 */

public class UsrSearchSignup extends Action {
    
    private final static String SUCCESS = "success";
    
    public String nullCheck(String value){
        if(value==null || value.trim().length()==0){
            value="";
        }
        return value;
    }
    
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try{
                    MessageResources mr=getResources(request);
                    
                    String namingfactory=mr.getMessage("ejbclient.namingfactory");
                    String contextfactory=mr.getMessage("ejbclient.contextfactory");
                    String urlprovider=mr.getMessage("ejbclient.urlprovider");
                    String lookupip=mr.getMessage("ejbclient.ip");
                    String jndiname=mr.getMessage("jndi.usrreg");
                                      
                    System.setProperty(namingfactory,contextfactory);
                    System.setProperty(urlprovider,lookupip);
                    Context jndiContext = new InitialContext();
                    Object objref = jndiContext.lookup(jndiname);
             
                HLCkaverystatelessRemoteHome home = (HLCkaverystatelessRemoteHome)
                javax.rmi.PortableRemoteObject.narrow(objref,HLCkaverystatelessRemoteHome.class);
                HLCkaverystatelessRemote remote = home.create();
                                
                String jndiname2=mr.getMessage("jndi.rolemanagement");
                String defrole=mr.getMessage("default.role");
                Debug.print("defrole :" +defrole);
                
                System.setProperty(namingfactory,contextfactory);
                System.setProperty(urlprovider,lookupip);
                
                Object objref2 = jndiContext.lookup(jndiname2); 
                Debug.print("ActionRoleMangement.jndiname:" + jndiname2);
                        
                HLCBrahmaputraSessionRemoteHome roleHome = (HLCBrahmaputraSessionRemoteHome)PortableRemoteObject.narrow(objref2,HLCBrahmaputraSessionRemoteHome.class);
                HLCBrahmaputraSessionRemote roleRemote = roleHome.create();
                
                HLCUserMaster objUserMaster = new HLCUserMaster();
                HLCContactDetails objContact = new HLCContactDetails();
                HLCMemberDetails objMember = new HLCMemberDetails();
                HLCPaymentDetails objPayment = new HLCPaymentDetails();
                
        String process= (String) request.getParameter("process");
        
        if(process.equalsIgnoreCase("register")){
                Debug.print("Inside the User Search Sign up");
                String salutation = (String) request.getParameter("USelect");
                String fname=(String) request.getParameter("fname");
                String mname = (String) request.getParameter("mname");
                String lname = (String) request.getParameter("lname");                
                String gender = (String) request.getParameter("gender");                
                String email = (String) request.getParameter("email");     
                String usrname = (String) request.getParameter("usrname");
                String usrCode = (String) request.getParameter("usrCode");
                String pAdd1_txt = (String) request.getParameter("padd_txt");
                String padd_txt2 = (String) request.getParameter("padd_txt2");
                String pCountry_sel = (String) request.getParameter("pcountry_sel");
                String pstate_sel = (String) request.getParameter("pstate_sel");
                String pCity_txt = (String) request.getParameter("pcity_txt");
                String pzip_txt = (String) request.getParameter("pzip_txt");
                String pphone_txt = (String) request.getParameter("pphone_txt");
                String pmob_txt = (String) request.getParameter("pmob_txt");
                String pfax_txt = (String) request.getParameter("pfax_txt");
                String sadd_txt = (String) request.getParameter("sadd_txt");
                String sadd_txt1 = (String) request.getParameter("sadd_txt1");
                String sCountry_sel = (String) request.getParameter("sCountry_txt");
                String sState_sel = (String) request.getParameter("sState_txt");
                String scity_txt = (String) request.getParameter("scity_txt");
                String szip_txt = (String) request.getParameter("szip_txt");
                String sphone_txt = (String) request.getParameter("sphone_txt");
                String smob_txt = (String) request.getParameter("smob_txt");
                String sfax_txt = (String) request.getParameter("sfax_txt");
                String day_yr =(String) request.getParameter("birthyear");
                String day_mon =(String) request.getParameter("birthmonth");
                String day_day =(String) request.getParameter("birthday");
                String bday=null;
                
                if((day_yr.trim().length()!=0)&&(day_mon.trim().length()!=0)&&(day_day.trim().length()!=0)){
                    bday =request.getParameter("birthyear")+"-"+request.getParameter("birthmonth")+"-"+request.getParameter("birthday");
                    System.out.println(bday);                       
                }
               
                objUserMaster.setUserTypeName("Human");
                objUserMaster.setUserCode(nullCheck(usrCode));
                objUserMaster.setPrefix(salutation);
                objUserMaster.setFirstName(nullCheck(fname));
                objUserMaster.setMiddleName(nullCheck(mname));
                objUserMaster.setLastName(nullCheck(lname));
                objUserMaster.setGender(nullCheck(gender));
                objUserMaster.setEmailId(nullCheck(email));
                objUserMaster.setSufix("");
                objUserMaster.setDob(bday);
                objUserMaster.setPassword(null);
                objUserMaster.setSecretQuestion("");
                objUserMaster.setSecretAnswer("");
                String finalPrimaryPh=""; 
                String finalSecondaryPh="";
                String finalPriFaxNo ="";
                String finalSecFaxNo ="";
                String finalPriMobNo="";
                String finalSecMobNo ="";
               //Primary Phone Number Striping
               if(request.getParameter("pphone_txt")!=null)
                {
                Debug.print("request.getParameter(pphone_txt) value:"+request.getParameter("pphone_txt"));
                
                StringTokenizer strTkns = new StringTokenizer(request.getParameter("pphone_txt"),"[](),.-{}");
                                               
                        while(strTkns.hasMoreTokens()){
                            try{
                                String phNo = (String)strTkns.nextToken();
                                
                                if(phNo!=null && phNo.trim().length()!=0){
                                    Debug.print("ph no Added from Stokenizer:" + phNo);
                                    finalPrimaryPh=finalPrimaryPh+phNo;
                                }
                            }
                                 catch(Exception e){
                                    Debug.print("ph no tokanizing exception:" + e);
                                }
                            }
                            
                Debug.print("finally appended primary phNo :"+finalPrimaryPh);
                }
               //Secondary Phone Number Striping
               if(request.getParameter("sphone_txt")!=null)
                {
                Debug.print("request.getParameter(pphone_txt) value:"+request.getParameter("sphone_txt"));
                
                StringTokenizer strTkns = new StringTokenizer(request.getParameter("sphone_txt"),"[](),.-{}");
                                               
                        while(strTkns.hasMoreTokens()){
                            try{
                                String phNo = (String)strTkns.nextToken();
                                
                                if(phNo!=null && phNo.trim().length()!=0){
                                    Debug.print("ph no Added from Stokenizer:" + phNo);
                                    finalSecondaryPh=finalSecondaryPh+phNo;
                                }
                            }
                                 catch(Exception e){
                                    Debug.print("ph no tokanizing exception:" + e);
                                }
                            }
                            
                Debug.print("finally appended Secondary phNo :"+finalSecondaryPh);
                }
               //Primary Mobile Number Striping
               if(request.getParameter("pmob_txt")!=null)
                {
                Debug.print("request.getParameter(pmob_txt) value:"+request.getParameter("pmob_txt"));
                
                StringTokenizer strTkns = new StringTokenizer(request.getParameter("pmob_txt"),"[](),.-{}");
                                               
                        while(strTkns.hasMoreTokens()){
                            try{
                                String PriMobNo = (String)strTkns.nextToken();
                                
                                if(PriMobNo!=null && PriMobNo.trim().length()!=0){
                                    Debug.print("MobileNo no Added from Stokenizer:" + PriMobNo);
                                    finalPriMobNo=finalPriMobNo+PriMobNo;
                                }
                            }
                                 catch(Exception e){
                                    Debug.print("MobileNo no tokanizing exception:" + e);
                                }
                            }
                            
                Debug.print("finally appended primary MobileNo :"+finalPriMobNo);
                }
               //Secondary Mobile Number Striping
               if(request.getParameter("smob_txt")!=null)
                {
                Debug.print("request.getParameter(smob_txt) value:"+request.getParameter("smob_txt"));
                
                StringTokenizer strTkns = new StringTokenizer(request.getParameter("smob_txt"),"[](),.-{}");
                                               
                        while(strTkns.hasMoreTokens()){
                            try{
                                String SecMobNo = (String)strTkns.nextToken();
                                
                                if(SecMobNo!=null && SecMobNo.trim().length()!=0){
                                    Debug.print("MobileNo no Added from Stokenizer:" + SecMobNo);
                                    finalSecMobNo=finalSecMobNo+SecMobNo;
                                }
                            }
                                 catch(Exception e){
                                    Debug.print("MobileNo tokanizing exception:" + e);
                                }
                            }
                            
                Debug.print("finally appended Secondary Mobile No :"+finalSecMobNo);
                }                
               //Primary FAX  Number Striping
               if(request.getParameter("pfax_txt")!=null)
                {
                Debug.print("request.getParameter(pfax_txt) value:"+request.getParameter("pfax_txt"));
                
                StringTokenizer strTkns = new StringTokenizer(request.getParameter("pfax_txt"),"[](),.-{}");
                                               
                        while(strTkns.hasMoreTokens()){
                            try{
                                String faxNo = (String)strTkns.nextToken();
                                
                                if(faxNo!=null && faxNo.trim().length()!=0){
                                    Debug.print("faxNo no Added from Stokenizer:" + faxNo);
                                    finalPriFaxNo=finalPriFaxNo+faxNo;
                                }
                            }
                                 catch(Exception e){
                                    Debug.print("faxNo no tokanizing exception:" + e);
                                }
                            }
                            
                Debug.print("finally appended primary FAX No :"+finalPriFaxNo);
                }
               //Secondary FAX Number Striping
               if(request.getParameter("sfax_txt")!=null)
                {
                Debug.print("request.getParameter(sfax_txt) value:"+request.getParameter("sfax_txt"));
                
                StringTokenizer strTkns = new StringTokenizer(request.getParameter("sfax_txt"),"[](),.-{}");
                                               
                        while(strTkns.hasMoreTokens()){
                            try{
                                String faxNo = (String)strTkns.nextToken();
                                
                                if(faxNo!=null && faxNo.trim().length()!=0){
                                    Debug.print("FaxNo no Added from Stokenizer:" + faxNo);
                                    finalSecFaxNo=finalSecFaxNo+faxNo;
                                }
                            }
                                 catch(Exception e){
                                    Debug.print("faxNo tokanizing exception:" + e);
                                }
                            }
                            
                Debug.print("finally appended Secondary FAX No :"+finalSecFaxNo);
                }                
                if(usrname!=null && usrname.trim().length()!=0)
                    objUserMaster.setLoginName(usrname);
                else
                    objUserMaster.setLoginName(null);
                
                if(request.getParameter("nonUseaEmail")!=null ){
                    objUserMaster.setNonUseaEmailStatus(true);
                    System.out.println("commit nonUseaEMail status--true");
                }
                else {
                    objUserMaster.setNonUseaEmailStatus(false);
                    System.out.println("commit nonUseaEMail status--false");
                }
                 if(request.getParameter("nonUseaMail")!=null){
                    objUserMaster.setNonUseaMailingStatus(true);
                    System.out.println("commit nonUseaMail status--true");
                }
                else {
                    objUserMaster.setNonUseaMailingStatus(false);
                    System.out.println("commit nonUseaMail status--false");
                }
                if(request.getParameter("addr").equalsIgnoreCase("Secondary")){
                    objUserMaster.setCommunicationAddress("Primary");
                }
                if(request.getParameter("addr").equalsIgnoreCase("Primary")){
                    objUserMaster.setCommunicationAddress(request.getParameter("myselect"));
                }
                Debug.print("objUserMaster is "+objUserMaster.toString());
                String usrId = remote.addUserRegistration(objUserMaster);
                Vector uid=new Vector();
                Debug.print("usrId after reg :" +usrId);
 
                //boolean rolestat=roleRemote.createDefaultRole(usrId,defrole);
                //Debug.print("rolestatus :" +rolestat);    
                
                objContact.setContactTypeId("0cc42d09-340c-42e8-b773-54bdf0688527");
                objContact.setUserId(usrId);
                objContact.setContactType("Primary");
                objContact.setAddress1(nullCheck(pAdd1_txt));
                objContact.setAddress2(nullCheck(padd_txt2));
                objContact.setCity(nullCheck(pCity_txt));
                objContact.setState(nullCheck(pstate_sel));
                objContact.setCountry(nullCheck(pCountry_sel));
                objContact.setZip(nullCheck(pzip_txt));
                objContact.setPhoneNo(nullCheck(finalPrimaryPh));
                objContact.setMobileNo(nullCheck(finalPriMobNo));
                objContact.setFaxNo(nullCheck(finalPriFaxNo));
                        //Debug.print("objContact is "+objContact.toString());
                remote.addContactDetails(objContact);

                boolean result = new MySQLDAO().insertUserDetailToMqSQL(objUserMaster, objContact);
                Debug.print("                MySql Result :" + result);
                if(request.getParameter("addr").equalsIgnoreCase("Primary"))
                {
                
                objContact.setContactType("Secondary");
                objContact.setAddress1(nullCheck(request.getParameter("sadd_txt")));
                objContact.setAddress2(nullCheck(request.getParameter("sadd_txt1")));
                objContact.setCity(nullCheck(request.getParameter("scity_txt")));
                objContact.setCountry(nullCheck(request.getParameter("sCountry_txt")));
                objContact.setState(nullCheck(request.getParameter("sState_txt")));
                objContact.setZip(nullCheck(request.getParameter("szip_txt")));
                objContact.setPhoneNo(finalSecondaryPh);
                objContact.setMobileNo(finalSecMobNo);
                objContact.setFaxNo(finalSecFaxNo);
                objContact.setUserId(usrId);
                remote.addContactDetails(objContact);
                              
                }
                request.setAttribute("pwd",request.getParameter("cpwd"));
                request.setAttribute("loginName",request.getParameter("usrname"));
                request.setAttribute("usrId",usrId);
                
                //remote.addContactDetails(objContact);        
                Debug.print("Inside Regi");
                return mapping.findForward("confirm");
            }
        }
        //return mapping.findForward(SUCCESS);
        catch(Exception e){
        }
        Debug.print("Out of Regi");
       return mapping.findForward("confirm");
}
    
}

