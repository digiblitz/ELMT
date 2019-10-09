/*
 * InviteUser.java
 *
 * Created on October 15, 2006, 6:04 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.message.action;


import com.hlcform.util.Debug;
import com.mrm.actionform.InsertUserRegActionForm;
import com.hlcmsg.session.HLCMessageSessionRemote;
import com.hlcmsg.session.HLCMessageSessionRemoteHome;
import com.hlcmsg.util.HLCGroupUser;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.naming.Context;
import javax.naming.InitialContext;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;


import com.hlcmsg.util.HLCUserMaster;
/**
 *
 * @author vijitha
 */
public class InviteUser extends Action{
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception {
        
        InsertUserRegActionForm usrbean=(InsertUserRegActionForm)form;  
      try {  
      String method =  request.getParameter("method");
      HLCMessageSessionRemote msgRemote =  initializeEJB(request);  
      if(!isNotNull(method)){
            String groupId = request.getParameter("gId");
            String ownerId  = request.getParameter("oId");
            String emailId = request.getParameter("eId");        
            Debug.print("Invite User Details : groupId="+groupId+",ownerId="+ownerId+",emailId="+emailId);                      
            HLCGroupUser group = new HLCGroupUser();
            if(msgRemote.isValidEmail(emailId)) {
                group.setGroupId(groupId);
                group.setOwnerId(ownerId);
                group.setUserId(msgRemote.getUserIdEmail(emailId));              
               boolean flag = msgRemote.createGroupUser(group);
               Debug.print("Inserted sucessfully for group user:" + flag);
                return mapping.findForward("loginView"); 
            }else {
                Debug.print("Needs to created User Details :ownerId="+usrbean.getOwnerId()+",GroupId="+usrbean.getGroupId());
                usrbean.setGroupId(groupId);
                usrbean.setOwnerId(ownerId);
                return mapping.findForward("UserReg"); 
           }  
            
        } else {
                                  
                 HLCUserMaster objUserMaster = new HLCUserMaster();
                 HLCUserMaster secContact = new HLCUserMaster();
            
                      
              /*
               * Inserting User Details by ejb/HLCMemberRegistrationJNDI
               */
                System.out.println("inside user registration ..........");
             
                System.out.println(usrbean.getUSelect());
                System.out.println(usrbean.getFname());
                System.out.println(usrbean.getMname());
                System.out.println(usrbean.getLname());
                System.out.println(usrbean.getSname());
                System.out.println(usrbean.getBirthmonth());
                System.out.println(usrbean.getBirthday());
                System.out.println(usrbean.getBirthyear());
                System.out.println(usrbean.getGender());
                System.out.println(usrbean.getPhone());
                System.out.println(usrbean.getMobile());
                System.out.println(usrbean.getFax());
                System.out.println(request.getParameter("email"));
                System.out.println(request.getParameter("cpwd"));
                System.out.println(request.getParameter("QSelect"));
                System.out.println(usrbean.getAns());
                System.out.println(request.getParameter("priAdd_cbx"));
                System.out.println(request.getParameter("secAdd"));
                System.out.println(usrbean.getPadd_txt());
                System.out.println(usrbean.getPadd_txt2());
                System.out.println(usrbean.getPcountry_sel());
                System.out.println(usrbean.getPstate_sel());
                System.out.println(usrbean.getPcity_txt());
                System.out.println(usrbean.getPzip_txt());
                System.out.println(request.getParameter("sadd_txt"));
                System.out.println(request.getParameter("sadd_txt1"));
                System.out.println(request.getParameter("scountry_txt"));
                System.out.println(request.getParameter("sstate_txt"));
                System.out.println(request.getParameter("scity_txt"));
                System.out.println(request.getParameter("szip_txt"));
                System.out.println(request.getParameter("myselect"));                              
                String bday=usrbean.getBirthyear()+"-"+usrbean.getBirthmonth()+"-"+usrbean.getBirthday();
                System.out.println(bday);
                objUserMaster.setOwnerId(usrbean.getOwnerId());
                objUserMaster.setUserId(usrbean.getGroupId());
                objUserMaster.setUserTypeName("");
                objUserMaster.setPrefix(usrbean.getUSelect());
                objUserMaster.setFirstName(usrbean.getFname());
                objUserMaster.setMiddleName(usrbean.getMname());
                objUserMaster.setLastName(usrbean.getLname());
                objUserMaster.setSufix(usrbean.getSname());
                objUserMaster.setDob(bday);
                objUserMaster.setGender(usrbean.getGender());
                objUserMaster.setEmailId(request.getParameter("email"));
                objUserMaster.setPassword(request.getParameter("cpwd"));
                objUserMaster.setSecretQuestion(request.getParameter("QSelect"));
                objUserMaster.setSecretAnswer(usrbean.getAns());
                objUserMaster.setCommunicationAddress(request.getParameter("myselect"));
               // remote.editUserDetails(objUserMaster); 
               
                objUserMaster.setContactType(request.getParameter("priAdd_cbx"));
                objUserMaster.setAddress1(usrbean.getPadd_txt());
                objUserMaster.setAddress2(usrbean.getPadd_txt2());
                objUserMaster.setCity(usrbean.getPcity_txt());
                objUserMaster.setState(usrbean.getPstate_sel());
                objUserMaster.setCountry(usrbean.getPcountry_sel());
                objUserMaster.setZip(usrbean.getPzip_txt());
                objUserMaster.setPhoneNo(usrbean.getPhone());
                objUserMaster.setMobileNo(usrbean.getMobile());
                objUserMaster.setFaxNo(usrbean.getFax());
             
                
                if(request.getParameter("secAdd")!=null) {                
                secContact.setContactType(request.getParameter("secAdd"));
                secContact.setAddress1(request.getParameter("sadd_txt"));
                secContact.setAddress2(request.getParameter("sadd_txt1"));
                secContact.setCity(request.getParameter("scity_txt"));
                secContact.setState(request.getParameter("sstate_txt"));
                secContact.setCountry(request.getParameter("scountry_txt"));
                secContact.setZip(request.getParameter("szip_txt"));
              
               }             
                
            HLCUserMaster HLCUserMaster =  msgRemote.addUserGroupUser(objUserMaster,secContact);
            
            Debug.print("Newly create User Details :"+HLCUserMaster);
           
        }
      
       }catch(Exception e) {
      e.printStackTrace();
     }
      
       return mapping.findForward("confirmation"); 
    }
   
    
   private HLCMessageSessionRemote initializeEJB(HttpServletRequest request) throws Exception{
        
        MessageResources mr=getResources(request);
        String namingfactory=mr.getMessage("ejbclient.namingfactory");
        String contextfactory=mr.getMessage("ejbclient.contextfactory");
        String urlprovider=mr.getMessage("ejbclient.urlprovider");
        String lookupip=mr.getMessage("ejbclient.ip");
        String jndiname=mr.getMessage("jndi.contactDetail");
        
        System.setProperty(namingfactory,contextfactory);
        System.setProperty(urlprovider,lookupip);
        
        Context jndiContext = new InitialContext();
        Object objref = jndiContext.lookup(jndiname);
        HLCMessageSessionRemoteHome home = (HLCMessageSessionRemoteHome)
        javax.rmi.PortableRemoteObject.narrow(objref,HLCMessageSessionRemoteHome.class);
        HLCMessageSessionRemote remote = home.create();
        
        return remote;
        
    }
    
         private boolean isNotNull(String data) {
        return (data!=null && data.trim().length()>0) ? true :false;
    }

}
