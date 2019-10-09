/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mrm.action;

import com.hlcaccounts.session.HLCMahanadhiSessionRemote;
import com.hlcaccounts.session.HLCMahanadhiSessionRemoteHome;
import com.hlcaccounts.util.HLCAccTransactionVO;
import com.hlcaccounts.util.HLCAccTxnTypeDetailVO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import com.hlcform.stateless.HLCkaverystatelessRemote;
import com.hlcform.stateless.HLCkaverystatelessRemoteHome;
import com.hlcform.util.AreaProgramVO;
import com.hlcform.util.Debug;
import java.util.ArrayList;
import java.util.Iterator;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;
import org.apache.struts.util.MessageResources;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.hlcform.util.HLCPaymentDetails;
import com.hlcform.util.HLCMemberDetails;
import com.hlcmeeting.session.HLCVaigaiSessionRemote;
import com.hlcmeeting.session.HLCVaigaiSessionRemoteHome;
import com.util.email.EmailContent;
import com.util.email.EmailEngine;
import java.util.Calendar;


/**
 *
 * @author Radhadatta
 */
public class ActionAreaMembership extends Action {
    
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
        
           
        HLCPaymentDetails objPayment = new HLCPaymentDetails();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyy");
       HLCMemberDetails objMember = new HLCMemberDetails();
        
        String paymentId ="";
        HLCVaigaiSessionRemote vaiRemote  = initializeVaigaiEJB(request);
        paymentId = vaiRemote.getNextId();
        Debug.print("paymentId in member insert servlet :"+paymentId);
        
        String userId=(String)session.getAttribute("userId");
        String membId=(String)session.getAttribute("memberId");
        String staff_user_id = (String) session.getAttribute("staff_user_id");
        String emailid = (String) session.getAttribute("emailId");
        
        String reqIp=request.getRemoteAddr();
        Debug.print(" Request IP :"+reqIp);
        
         objPayment.setIpAddress(reqIp);
         
        String cmd=request.getParameter("cmd");        
        if(cmd!=null && cmd.equalsIgnoreCase("initPage")){                    
        if(membId==null){
            String stat="Sorry You should first register as a member !!";
            request.setAttribute("stat",stat);
         return mapping.findForward("frmMyAreaRidProgram");   
        }
        else if(membId!=null && membId.trim().length()!=0){
            ArrayList membStatMembYear=remote.getMembershipStatusFromHistoryForAreaProgram(membId);
            String memYear="";
            String membStatus="";
            String memTypeName="";
            if(membStatMembYear!=null && membStatMembYear.size()!=0){
               Iterator itr = membStatMembYear.iterator();  
               while(itr.hasNext()){
                 String[] membList = (String[])itr.next();
                 memTypeName=membList[0];
                  memYear=membList[1];
                  membStatus=membList[2];                   
               }                  
            }
                       
  if(memYear!=null && memYear.equalsIgnoreCase("2009") && membStatus!=null && membStatus.equalsIgnoreCase("Active") && !(memTypeName.equalsIgnoreCase("Subscribing Member")) && !(memTypeName.equalsIgnoreCase("Life Member"))){     
        boolean result= remote.isUserIdExist(userId);
        if(result==true){
         ArrayList areaProgList= remote.getAreaProgramDetailsFrmHistory(membId);         
         if(areaProgList!=null && areaProgList.size()!=0){
                    System.out.println("areaProgList"+areaProgList);
                    Iterator it = areaProgList.iterator();
                    int i=0;	
                    String areaMemTypId1="";
                    while(it.hasNext()){
                    AreaProgramVO areaProgVO1 = (AreaProgramVO)it.next();

                    areaMemTypId1=areaProgVO1.getAreaMembTypId();
                    String areaName1=areaProgVO1.getAreaName();	
                    String progName1=areaProgVO1.getProgName();
                    String progDesc1=areaProgVO1.getDescription();
                    String urlName1=areaProgVO1.getUrl();
                    String membPrice1=areaProgVO1.getAreaMembPrice();
                    boolean upgrade=areaProgVO1.isUpgradable();
                    String transacTypId=areaProgVO1.getTransacTypId();
                 
                    if(upgrade==false){
                      request.setAttribute("areaProgList",areaProgList);
                      request.setAttribute("upgradeStatus","False"); 
                        
                    }else {
                        
         ArrayList areaZipList= remote.getAreaZipDetails(membId); 
        ArrayList areaProgListForUpgrdTrue=new ArrayList();
            if(areaZipList!=null && areaZipList.size()!=0){            
            Iterator itr = areaZipList.iterator(); 
            String year="";
            String areaId="";
            String progAreaName="";
            while(itr.hasNext()){
                String[] list = (String[])itr.next();
               String areaName = list[0];
                 areaId = list[1];
                String zip = list[2];
                year = list[3];
            }
            int progYear=0;
            int minAge=0;
            int maxAge=0;
            int tempProgYear=Integer.parseInt(memYear);
      ArrayList progList= remote.getAreaProgramDetailsFrmTypeMaster(tempProgYear,areaId);
           if(progList!=null && progList.size()!=0){
           Iterator itr1 = progList.iterator();           
           while(itr1.hasNext()){
           AreaProgramVO areaProgVO = (AreaProgramVO)itr1.next();
            progYear=areaProgVO.getProgYear();
            minAge=areaProgVO.getMinAge();
            maxAge=areaProgVO.getMaxAge();
            progAreaName=areaProgVO.getAreaName();
           }  
           }            
           int tempYear=Integer.parseInt(year);
           int compAge=progYear-tempYear; 
           Debug.print("compAge:" + compAge);
           
           if(compAge!=0){
              areaProgListForUpgrdTrue= remote.getAreaProgBasedOnAgeAndProgYear(compAge,progYear,areaId);             
             request.setAttribute("areaProgListForUpgrdTrue",areaProgListForUpgrdTrue);
              Debug.print("areaProgListForUpgrdTrue.size():" + areaProgListForUpgrdTrue.size());
              Debug.print("areaProgListForUpgrdTrue:" + areaProgListForUpgrdTrue);
             }             
            }     
  
                        request.setAttribute("upgradeStatus","True");                        
                        request.setAttribute("areaName1",areaName1);
                        request.setAttribute("progName1",progName1);
                        request.setAttribute("progDesc1",progDesc1);
                        request.setAttribute("note","ACTIVE MEMBER");
                        request.setAttribute("areaMemTypId1",areaMemTypId1);
                        
                    }                                        
                   }                                                
                }
         
        return mapping.findForward("frmMyAreaRidProgram"); 
        }else if(result==false){
        ArrayList areaZipList= remote.getAreaZipDetails(membId); 
        ArrayList progDetails=new ArrayList();
            if(areaZipList!=null && areaZipList.size()!=0){            
            Iterator itr = areaZipList.iterator(); 
            String year="";
            String areaId="";
            String progAreaName="";
            while(itr.hasNext()){
                String[] list = (String[])itr.next();
               String areaName = list[0];
                 areaId = list[1];
                String zip = list[2];
                year = list[3];
            }
            int progYear=0;
            int minAge=0;
            int maxAge=0;
            int tempProgYear=2009;
      ArrayList progList= remote.getAreaProgramDetailsFrmTypeMaster(tempProgYear,areaId);
           if(progList!=null && progList.size()!=0){
           Iterator itr1 = progList.iterator();           
           while(itr1.hasNext()){
           AreaProgramVO areaProgVO = (AreaProgramVO)itr1.next();
            progYear=areaProgVO.getProgYear();
            minAge=areaProgVO.getMinAge();
            maxAge=areaProgVO.getMaxAge();
            progAreaName=areaProgVO.getAreaName();
           }  
           }            
           int tempYear=Integer.parseInt(year);
           int compAge=progYear-tempYear; 
           Debug.print("compAge:" + compAge);
           
           if(compAge!=0){
             progDetails= remote.getAreaProgBasedOnAgeAndProgYear(compAge,progYear,areaId);                                  
             request.setAttribute("progDetails",progDetails);
              Debug.print("progDetails.size():" + progDetails.size());
              Debug.print("progDetails:" + progDetails);
             
             }             
            }           
         return mapping.findForward("frmMyAreaRidProgram"); 
        }   
       }
  else if(memYear!=null && memYear.equalsIgnoreCase("2009") && membStatus!=null && !(membStatus.equalsIgnoreCase("Active")) && !(memTypeName.equalsIgnoreCase("Subscribing Member")) && !(memTypeName.equalsIgnoreCase("Life Member"))){       
    String stat2="In order to join your Area Rider Program your Membership status should be ACTIVE.";
    request.setAttribute("stat1",stat2); 
    return mapping.findForward("frmMyAreaRidProgram");  
   }
 /*else if(memYear!=null && memYear.equalsIgnoreCase("2009") && memTypeName.equalsIgnoreCase("Subscribing Member") && memTypeName.equalsIgnoreCase("Non-Competing Member")){       
    String stat3="You are not appicable for Area Rider Program.";                  
    request.setAttribute("stat1",stat3); 
    return mapping.findForward("frmMyAreaRidProgram");  
   }*/
            
  else if(memTypeName.equalsIgnoreCase("Subscribing Member") && membStatus!=null && !(membStatus.equalsIgnoreCase("Active"))){
      String stat2="In order to join your Area Rider Program your Membership status should be ACTIVE.";
    request.setAttribute("stat1",stat2); 
    return mapping.findForward("frmMyAreaRidProgram");   
  }                                 
 else if(memTypeName.equalsIgnoreCase("Subscribing Member") && membStatus!=null && membStatus.equalsIgnoreCase("Active")){
     Debug.print("inside Subcribing member Scetion:");
        boolean result= remote.isUserIdExist(userId);
        if(result==true){
         ArrayList areaProgList= remote.getAreaProgramDetailsFrmHistory(membId);         
         if(areaProgList!=null && areaProgList.size()!=0){
                    System.out.println("areaProgList"+areaProgList);
                    Iterator it = areaProgList.iterator();
                    int i=0;	
                    String areaMemTypId1="";
                    while(it.hasNext()){
                    AreaProgramVO areaProgVO1 = (AreaProgramVO)it.next();

                    areaMemTypId1=areaProgVO1.getAreaMembTypId();
                    String areaName1=areaProgVO1.getAreaName();	
                    String progName1=areaProgVO1.getProgName();
                    String progDesc1=areaProgVO1.getDescription();
                    String urlName1=areaProgVO1.getUrl();
                    String membPrice1=areaProgVO1.getAreaMembPrice();
                    boolean upgrade=areaProgVO1.isUpgradable();
                    String transacTypId=areaProgVO1.getTransacTypId();
                 
                    if(upgrade==false){
                      request.setAttribute("areaProgList",areaProgList);
                      request.setAttribute("upgradeStatus","False"); 
                        
                    }else {
                        
         ArrayList areaZipList= remote.getAreaZipDetails(membId); 
        ArrayList areaProgListForUpgrdTrue=new ArrayList();
            if(areaZipList!=null && areaZipList.size()!=0){            
            Iterator itr = areaZipList.iterator(); 
            String year="";
            String areaId="";
            String progAreaName="";
            while(itr.hasNext()){
                String[] list = (String[])itr.next();
               String areaName = list[0];
                 areaId = list[1];
                String zip = list[2];
                year = list[3];
            }
            int progYear=0;
            int minAge=0;
            int maxAge=0;
            java.util.Calendar c2 = java.util.Calendar.getInstance();     
          int year2 = c2.get(Calendar.YEAR);
            //int tempProgYear=Integer.parseInt(memYear);
      ArrayList progList= remote.getAreaProgramDetailsFrmTypeMaster(0,areaId);
           if(progList!=null && progList.size()!=0){
           Iterator itr1 = progList.iterator();           
           while(itr1.hasNext()){
           AreaProgramVO areaProgVO = (AreaProgramVO)itr1.next();
            progYear=areaProgVO.getProgYear();
            minAge=areaProgVO.getMinAge();
            maxAge=areaProgVO.getMaxAge();
            progAreaName=areaProgVO.getAreaName();
           }  
           }            
           int tempYear=Integer.parseInt(year);
           int compAge=progYear-tempYear; 
           Debug.print("compAge:" + compAge);
           
           if(compAge!=0){
              areaProgListForUpgrdTrue= remote.getAreaProgBasedOnAgeAndProgYear(compAge,0,areaId);             
             request.setAttribute("areaProgListForUpgrdTrue",areaProgListForUpgrdTrue);
              Debug.print("areaProgListForUpgrdTrue.size():" + areaProgListForUpgrdTrue.size());
              Debug.print("areaProgListForUpgrdTrue:" + areaProgListForUpgrdTrue);
             }             
            }     
  
                        request.setAttribute("upgradeStatus","True");                        
                        request.setAttribute("areaName1",areaName1);
                        request.setAttribute("progName1",progName1);
                        request.setAttribute("progDesc1",progDesc1);
                        request.setAttribute("note","ACTIVE MEMBER");
                        request.setAttribute("areaMemTypId1",areaMemTypId1);
                        
                    }                                        
                   }                                                
                }else{
              request.setAttribute("areaProgListForUpgrdTrue",null);
              request.setAttribute("progDetails",null);
              request.setAttribute("areaProgList",null);
              
             
                }
         
        return mapping.findForward("frmMyAreaRidProgram"); 
        }else if(result==false){
        ArrayList areaZipList= remote.getAreaZipDetails(membId); 
        ArrayList progDetails=new ArrayList();
            if(areaZipList!=null && areaZipList.size()!=0){            
            Iterator itr = areaZipList.iterator(); 
            String year="";
            String areaId="";
            String progAreaName="";
            while(itr.hasNext()){
                String[] list = (String[])itr.next();
               String areaName = list[0];
                 areaId = list[1];
                String zip = list[2];
                year = list[3];
            }
            int progYear=0;
            int minAge=0;
            int maxAge=0;
             java.util.Calendar c3 = java.util.Calendar.getInstance();     
          int year3 = c3.get(Calendar.YEAR);
            //int tempProgYear=2009;
      ArrayList progList= remote.getAreaProgramDetailsFrmTypeMaster(0,areaId);
           if(progList!=null && progList.size()!=0){
           Iterator itr1 = progList.iterator();           
           while(itr1.hasNext()){
           AreaProgramVO areaProgVO = (AreaProgramVO)itr1.next();
            progYear=areaProgVO.getProgYear();
            minAge=areaProgVO.getMinAge();
            maxAge=areaProgVO.getMaxAge();
            progAreaName=areaProgVO.getAreaName();
           }  
           }            
           int tempYear=Integer.parseInt(year);
           int compAge=progYear-tempYear; 
           Debug.print("compAge:" + compAge);
           
           if(compAge!=0){
             progDetails= remote.getAreaProgBasedOnAgeAndProgYear(compAge,0,areaId);                                  
             request.setAttribute("progDetails",progDetails);
              Debug.print("progDetails.size():" + progDetails.size());
              Debug.print("progDetails:" + progDetails);
             
             }             
            }           
         return mapping.findForward("frmMyAreaRidProgram"); 
        } 
 }          
           
 else if(memTypeName.equalsIgnoreCase("Life Member")){
     Debug.print("inside Life member Scetion:");
        boolean result= remote.isUserIdExist(userId);
        if(result==true){
         ArrayList areaProgList= remote.getAreaProgramDetailsFrmHistory(membId);         
         if(areaProgList!=null && areaProgList.size()!=0){
                    System.out.println("areaProgList"+areaProgList);
                    Iterator it = areaProgList.iterator();
                    int i=0;	
                    String areaMemTypId1="";
                    while(it.hasNext()){
                    AreaProgramVO areaProgVO1 = (AreaProgramVO)it.next();

                    areaMemTypId1=areaProgVO1.getAreaMembTypId();
                    String areaName1=areaProgVO1.getAreaName();	
                    String progName1=areaProgVO1.getProgName();
                    String progDesc1=areaProgVO1.getDescription();
                    String urlName1=areaProgVO1.getUrl();
                    String membPrice1=areaProgVO1.getAreaMembPrice();
                    boolean upgrade=areaProgVO1.isUpgradable();
                    String transacTypId=areaProgVO1.getTransacTypId();
                 
                    if(upgrade==false){
                      request.setAttribute("areaProgList",areaProgList);
                      request.setAttribute("upgradeStatus","False"); 
                        
                    }else {
                        
         ArrayList areaZipList= remote.getAreaZipDetails(membId); 
        ArrayList areaProgListForUpgrdTrue=new ArrayList();
            if(areaZipList!=null && areaZipList.size()!=0){            
            Iterator itr = areaZipList.iterator(); 
            String year="";
            String areaId="";
            String progAreaName="";
            while(itr.hasNext()){
                String[] list = (String[])itr.next();
               String areaName = list[0];
                 areaId = list[1];
                String zip = list[2];
                year = list[3];
            }
            int progYear=0;
            int minAge=0;
            int maxAge=0;
            java.util.Calendar c2 = java.util.Calendar.getInstance();     
          int year2 = c2.get(Calendar.YEAR);
            //int tempProgYear=Integer.parseInt(memYear);
      ArrayList progList= remote.getAreaProgramDetailsFrmTypeMaster(0,areaId);
           if(progList!=null && progList.size()!=0){
           Iterator itr1 = progList.iterator();           
           while(itr1.hasNext()){
           AreaProgramVO areaProgVO = (AreaProgramVO)itr1.next();
            progYear=areaProgVO.getProgYear();
            minAge=areaProgVO.getMinAge();
            maxAge=areaProgVO.getMaxAge();
            progAreaName=areaProgVO.getAreaName();
           }  
           }            
           int tempYear=Integer.parseInt(year);
           int compAge=progYear-tempYear; 
           Debug.print("compAge:" + compAge);
           
           if(compAge!=0){
              areaProgListForUpgrdTrue= remote.getAreaProgBasedOnAgeAndProgYear(compAge,0,areaId);             
             request.setAttribute("areaProgListForUpgrdTrue",areaProgListForUpgrdTrue);
              Debug.print("areaProgListForUpgrdTrue.size():" + areaProgListForUpgrdTrue.size());
              Debug.print("areaProgListForUpgrdTrue:" + areaProgListForUpgrdTrue);
             }             
            }     
  
                        request.setAttribute("upgradeStatus","True");                        
                        request.setAttribute("areaName1",areaName1);
                        request.setAttribute("progName1",progName1);
                        request.setAttribute("progDesc1",progDesc1);
                        request.setAttribute("note","ACTIVE MEMBER");
                        request.setAttribute("areaMemTypId1",areaMemTypId1);
                        
                    }                                        
                   }                                                
                }
         
        return mapping.findForward("frmMyAreaRidProgram"); 
        }else if(result==false){
        ArrayList areaZipList= remote.getAreaZipDetails(membId); 
        ArrayList progDetails=new ArrayList();
            if(areaZipList!=null && areaZipList.size()!=0){            
            Iterator itr = areaZipList.iterator(); 
            String year="";
            String areaId="";
            String progAreaName="";
            while(itr.hasNext()){
                String[] list = (String[])itr.next();
               String areaName = list[0];
                 areaId = list[1];
                String zip = list[2];
                year = list[3];
            }
            int progYear=0;
            int minAge=0;
            int maxAge=0;
             java.util.Calendar c3 = java.util.Calendar.getInstance();     
          int year3 = c3.get(Calendar.YEAR);
            //int tempProgYear=2009;
      ArrayList progList= remote.getAreaProgramDetailsFrmTypeMaster(0,areaId);
           if(progList!=null && progList.size()!=0){
           Iterator itr1 = progList.iterator();           
           while(itr1.hasNext()){
           AreaProgramVO areaProgVO = (AreaProgramVO)itr1.next();
            progYear=areaProgVO.getProgYear();
            minAge=areaProgVO.getMinAge();
            maxAge=areaProgVO.getMaxAge();
            progAreaName=areaProgVO.getAreaName();
           }  
           }            
           int tempYear=Integer.parseInt(year);
           int compAge=progYear-tempYear; 
           Debug.print("compAge:" + compAge);
           
           if(compAge!=0){
             progDetails= remote.getAreaProgBasedOnAgeAndProgYear(compAge,0,areaId);                                  
             request.setAttribute("progDetails",progDetails);
              Debug.print("progDetails.size():" + progDetails.size());
              Debug.print("progDetails:" + progDetails);
             
             }             
            }           
         return mapping.findForward("frmMyAreaRidProgram"); 
        } 
 }           
   else if(!(memYear.equalsIgnoreCase("2009")) && !(memTypeName.equalsIgnoreCase("Life Member") && !(memTypeName.equalsIgnoreCase("Subscribing Member")))){                              
        String stat1="You are not, or have not purchased a USEA membership " +
        "for the upcoming membership year. You must be an " +
        "Active USEA member for " +
        "2009 to join your Area Rider Program.";
        request.setAttribute("stat1",stat1);     
        return mapping.findForward("frmMyAreaRidProgram");       
   }        
        }
        }
        else if(cmd!=null && cmd.equalsIgnoreCase("insertAreaProg")){
            
       String areaMembTypeId=request.getParameter("areaMembTypId");
       String areaMembId = "";
                String areaName = "";
                String progName = "";
                 String transacTypId = "";
                 String membPrice = "";
                  String progDesc = "";
       if(areaMembTypeId!=null){
                String areaMembValue[] = areaMembTypeId.split("#");
                 areaMembId = areaMembValue[0];
                 areaName = areaMembValue[1];
                 progName = areaMembValue[2];
                 transacTypId = areaMembValue[3];
                 membPrice = areaMembValue[4];
                 progDesc = areaMembValue[5];
       }
       String r11=request.getParameter("r11");
       Debug.print("r11:" + r11);
       String TotalAmount=request.getParameter("totalAmount");
       Debug.print("TotalAmount:" + TotalAmount);
       
       
       if(userId!=null && userId.trim().length()!=0){
           objMember.setUserId(userId);       
       }else {
           objMember.setUserId(null);
       }
       
       if(areaMembId!=null && areaMembId.trim().length()!=0){
           objMember.setAreaMembTypId(areaMembId);       
       }else {
           objMember.setAreaMembTypId(null);
       }
       
       if(membId!=null && membId.trim().length()!=0){
           objMember.setMemberId(membId);       
       }else {
           objMember.setMemberId(null);
       }
       
       objMember.setPaymentId(paymentId);
       objPayment.setPaymentId(paymentId);
       objMember.setAraeName(areaName);
       objMember.setAreaProgName(progName);
       objMember.setAreaProgYear("2009");
       objMember.setAreaProgDesc(progDesc);
      
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
            objPayment.setPaymentId(paymentId);
            if(request.getParameter("totalAmount")!=null && request.getParameter("totalAmount").trim().length()!=0) {
                objPayment.setAmount(Double.valueOf(request.getParameter("totalAmount")).doubleValue());
            } else {
                objPayment.setAmount(0);
            }
            
            objPayment.setPaymentDate(new Date());
            objPayment.setPaymentStatus("card");
            objMember.setActivationDate(new Date());     
            
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
            
            objPayment.setUserId(userId);
            objPayment.setCcName(null);
            objPayment.setCcType(null);
            objPayment.setCcNumber("0");
            objPayment.setCcExpMonth(0);
            objPayment.setCcExpYear(0);
            objPayment.setCcCvvid(0);
            objPayment.setBankName(request.getParameter("bankName"));
            objPayment.setCheckDate(check_date);
            //objMember.setActivationDate(act_date_check);
            
            if(request.getParameter("chkBalAmt")!=null && request.getParameter("chkBalAmt").trim().length()!=0) {
                //objPayment.setCheckAmount(Float.valueOf(request.getParameter("chckAmount")).floatValue());
                
                Debug.print("request.getParameter(chkBalAmt) :"+request.getParameter("chkBalAmt"));
                
                chk_amt=Double.valueOf(request.getParameter("chkBalAmt")).doubleValue();
                Debug.print("Double.valueOf(request.getParameter(chkBalAmt)).doubleValue() :"+chk_amt);
                
           
                 chkAmt=Float.valueOf(request.getParameter("chkBalAmt")).floatValue();                              
                objPayment.setCheckAmount(chkAmt);
                
            } else {
                objPayment.setCheckAmount(0);
            }
            
            if(request.getParameter("checkNumber")!=null && request.getParameter("checkNumber").trim().length()!=0) {
                objPayment.setCheckNumber(request.getParameter("checkNumber"));
            } else {
                objPayment.setCheckNumber("0");
            }
            
            if(request.getParameter("totalAmount")!=null && request.getParameter("totalAmount").trim().length()!=0) {
                objPayment.setAmount(Double.valueOf(request.getParameter("totalAmount")).doubleValue());
            } else {
                objPayment.setAmount(0);
            }
         
            String nameOnchk=request.getParameter("nameCheck");            
            objPayment.setCheckName(nameOnchk);            
            objPayment.setPaymentDate(new Date());          
            objPayment.setPaymentStatus("check");
            objMember.setActivationDate(new Date());     
            
                   
            if(objPayment.getAmount()<=objPayment.getCheckAmount()){                
               actStat="Active";
               objPayment.setPendingAmount(0);
            }else if(objPayment.getCheckAmount()>objPayment.getAmount()){             
             float tempPendingAmt=(float)(objPayment.getAmount() - objPayment.getCheckAmount());               
             objPayment.setPendingAmount(tempPendingAmt);
            } 
            
          boolean output=remote.addPaymentDetails(objPayment); 
           Debug.print("output :"+output);
           
          boolean output1=remote.getAreaMembHistDetails(objMember,objPayment); 
          result = remote.insertAreaMembProgram(objMember,objPayment); 
          Debug.print("result :"+result);
        }
             
      HLCAccTransactionVO PhaccTxnVO = null;
        HLCAccTransactionVO accTxnVO = new HLCAccTransactionVO();
       
                PhaccTxnVO = new HLCAccTransactionVO();                            
                HLCAccTxnTypeDetailVO transTxnDet = mahaRemote.getAccTransacTypDetsForAreaRiderProg(transacTypId);
                
                String accNo = transTxnDet.getAccount_no();
                String accClassname = transTxnDet.getClass_name();
                String accItemNo = transTxnDet.getItem_no();
                String accAccNo = transTxnDet.getSub_account_no();
                String accTranName = transTxnDet.getTransaction_name();
                String accTyped = transTxnDet.getTransaction_type();
                String accTypeId = transTxnDet.getTransaction_type_id();
                
                Debug.print("Inside Phone Account No "+accNo);
                Debug.print("Inside Phone Family Class Name "+accClassname);
                Debug.print("Inside Phone Item No "+accItemNo);
                Debug.print("Inside Phone Sub-Account No "+accAccNo);
                Debug.print("Inside Phone Transaction Name "+accTranName);
                Debug.print("Inside Phone Transaction Type "+accTyped);
                Debug.print("Inside Phone Transaction Type Id "+accTypeId);
                
                if(r11.equalsIgnoreCase("check")){
                    PhaccTxnVO.setPayment_mode("check");
                    PhaccTxnVO.setActive_status(false);
                    PhaccTxnVO.setStaff_user_id(staff_user_id);
                    PhaccTxnVO.setStaff_ip_address(reqIp);                  
                }
                if(r11.equalsIgnoreCase("card")){
                    String cardselect = request.getParameter("ccType");
                    PhaccTxnVO.setPayment_mode(cardselect);
                    PhaccTxnVO.setActive_status(false);
                }
                PhaccTxnVO.setSub_account_no(accAccNo);
                PhaccTxnVO.setPayment_id(paymentId);
                PhaccTxnVO.setAccount_type("Income");
                PhaccTxnVO.setClass_Typ(accClassname);
                
                PhaccTxnVO.setAccount_no(accNo);
                PhaccTxnVO.setItem_no(accItemNo);               
                PhaccTxnVO.setDescription(accTranName);
                PhaccTxnVO.setAmount(Float.parseFloat(membPrice));
                
                if(r11.equals("check")){
            if(PhaccTxnVO!=null){
                boolean status = mahaRemote.insertAccountTxnDetails(PhaccTxnVO);
             if(objPayment.getCheckAmount()>=objPayment.getAmount()){     
           boolean Update_Status = mahaRemote.updateRecouncilActiveStatusAccTxnDetails(paymentId); 
             }
                Debug.print("mahaRemote.insertAccountTxnDetails(phoneVO) for Phone Service :"+status);
            }
        } else{
            Debug.print("mahaRemote.insertAccountTxnDetails(PhaccTxnVO)Area Program");
            session.setAttribute("PhaccTxnVO",PhaccTxnVO);
        }
          
       String tempOldAreaProgram = request.getParameter("oldAreaProgram");
        Debug.print("tempOldAreaProgram is "+tempOldAreaProgram);  
       if(tempOldAreaProgram!=null && tempOldAreaProgram.trim().length()!=0){
           
        String tempOldTransacTypId = request.getParameter("oldTransacTypId");
        Debug.print("tempOldTransacTypId is "+tempOldTransacTypId);
        String tempOldAreaPrice= request.getParameter("oldAreaPrice");
        Debug.print("tempOldAreaPrice is "+tempOldAreaPrice);  
       
      HLCAccTransactionVO PhaccTxnVO1 = null;
        HLCAccTransactionVO accTxnVO1 = new HLCAccTransactionVO();
       
                PhaccTxnVO1 = new HLCAccTransactionVO();                            
                HLCAccTxnTypeDetailVO transTxnDet1 = mahaRemote.getAccTransacTypDetsForAreaRiderProg(tempOldTransacTypId);
                
                String accNo1 = transTxnDet1.getAccount_no();
                String accClassname1 = transTxnDet1.getClass_name();
                String accItemNo1 = transTxnDet1.getItem_no();
                String accAccNo1 = transTxnDet1.getSub_account_no();
                String accTranName1 = transTxnDet1.getTransaction_name();
                String accTyped1 = transTxnDet1.getTransaction_type();
                String accTypeId1 = transTxnDet1.getTransaction_type_id();
                
                Debug.print("Inside Area Program Account No "+accNo1);
                Debug.print("Inside Area Program Family Class Name "+accClassname1);
                Debug.print("Inside Area Program Item No "+accItemNo1);
                Debug.print("Inside Area Program Sub-Account No "+accAccNo1);
                Debug.print("Inside Area Program Transaction Name "+accTranName1);
                Debug.print("Inside Area Program Transaction Type "+accTyped1);
                Debug.print("Inside Area Program Transaction Type Id "+accTypeId1);
                
                if(r11.equalsIgnoreCase("check")){
                    PhaccTxnVO1.setPayment_mode("check");
                    PhaccTxnVO1.setActive_status(false);
                    PhaccTxnVO1.setStaff_user_id(staff_user_id);
                    PhaccTxnVO1.setStaff_ip_address(reqIp);                  
                }
                if(r11.equalsIgnoreCase("card")){
                    String cardselect1 = request.getParameter("ccType");
                    PhaccTxnVO1.setPayment_mode(cardselect1);
                    PhaccTxnVO1.setActive_status(false);
                }
                String tempOldAreaPrice1="";
                if(tempOldAreaPrice!=null || tempOldAreaPrice.trim().length()!=0){
                tempOldAreaPrice1 ="-"+tempOldAreaPrice;
                PhaccTxnVO1.setAmount(Float.parseFloat(tempOldAreaPrice1));
                } else{
                PhaccTxnVO1.setAmount(0);
                }
                                            
                PhaccTxnVO1.setSub_account_no(accAccNo1);
                PhaccTxnVO1.setPayment_id(paymentId);
                PhaccTxnVO1.setAccount_type("Income");
                PhaccTxnVO1.setClass_Typ(accClassname1);
                
                PhaccTxnVO1.setAccount_no(accNo1);
                PhaccTxnVO1.setItem_no(accItemNo1);               
                PhaccTxnVO1.setDescription(accTranName1);
                                
                if(r11.equals("check")){
            if(PhaccTxnVO1!=null){
                boolean status = mahaRemote.insertAccountTxnDetails(PhaccTxnVO1);
                Debug.print("mahaRemote.insertAccountTxnDetails(PhaccTxnVO1) for Area Program :"+status);
            }
        } else{
            Debug.print("mahaRemote.insertAccountTxnDetails(PhaccTxnVO1)Area Program");
            session.setAttribute("PhaccTxnVO1",PhaccTxnVO1);
        }             
       }           
    if(r11.equals("card")){
           Debug.print("Sucessfully Redirect to NoVa:");  
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
             session.setAttribute("objPayment",objPayment);
             session.setAttribute("objMember",objMember);
             request.setAttribute("email",emailid);
            request.setAttribute("cardNo",String.valueOf(CcNumber));
            request.setAttribute("chkDigit",String.valueOf(CcCvvid));
            String expMon = String.valueOf(CcExpMonth);
            String expYear = String.valueOf(CcExpYear);
            if(expMon.trim().length()==1) {
                expMon = "0" + expMon;
            }
           
            Debug.print("expYear:" + expYear);
          //  expYear = expYear.substring(2);
           
            Debug.print("expMon:" + expMon);
            Debug.print("expYear:" + expYear);
            
            String expDate = expMon + expYear;
            request.setAttribute("expDate",expDate);
            request.setAttribute("amount",String.valueOf(amount));
            StringBuffer reqURL = request.getRequestURL();
            int lastIndex = reqURL.lastIndexOf("/") ;
            //String url ="";
            //if(lastIndex!=-1){
               // url = reqURL.substring(0,lastIndex+1);
            //}
            //String tmpNova  = mr.getMessage("areaMemb.nova");
            //String nova = url + tmpNova;
            Debug.print("succsssullll from app file payp[al........::");
            //Debug.print("succsssullll from app file tmpNova ........::" + tmpNova);
            //request.setAttribute("nova",nova);
           // String inVoiceId1 = (String) session.getAttribute("inVoiceId1");
                     // activation date is set in session for history record details table and setting it into session.
                  if(request.getParameter("activeDatVal")!=null && request.getParameter("activeDatVal").trim().length()!=0) {
                        session.setAttribute("activation_date",request.getParameter("activeDatVal"));
                     }
                 
                  //String inVId = (String)session.getAttribute("inVoiceId1");
                 // int invoiceFromForm= Integer.parseInt(inVId);
                //System.out.println("inVId in servlet from request: " + inVId);
                int intVId = 0;
                 String inVoiceId1="1";
                if (inVoiceId1.equalsIgnoreCase("0")) {
                    intVId = 1;
                } else {
                    intVId = 1;
                }
                System.out.println("payment id in first servlet"+paymentId);
               // objPayment.setPaymentId(paymentId);
                request.setAttribute("AMT", request.getParameter("totalAmount"));
                request.setAttribute("PAYMENTACTION", "Authorization");
                request.setAttribute("CREDITCARDTYPE", request.getParameter("ccType"));
                request.setAttribute("ACCT", request.getParameter("ccNumber"));
                request.setAttribute("EXPDATE", expDate);
                request.setAttribute("IPADDRESS", reqIp);
                request.setAttribute("FIRSTNAME", request.getParameter("ccName"));
                request.setAttribute("CVVNo", request.getParameter("ccCvvid"));
                request.setAttribute("STREET", "1 Main St");
                request.setAttribute("CITY", "San Jose");
                request.setAttribute("STATE", "CA");
                request.setAttribute("ZIP", "95131");
                request.setAttribute("COUNTRYCODE", "US");
                request.setAttribute("EMAIL",emailid);
                session.setAttribute("objPayment", objPayment);
                session.setAttribute("paymentId", paymentId);
                request.setAttribute("purpose", "areaMemb");
                //intVId++; 
                //invoiceFromForm++;
                //session.setAttribute("incrementalInvoice",inVId);
                request.setAttribute("intVId",String.valueOf(intVId));
                //request.setAttribute("intVId", String.valueOf(intVId));
                System.out.println("intVId in servlet InsertHorseRegAction &&&&&&&" + intVId);
                return mapping.findForward("testing");
         //   return mapping.findForward("novaPage");
          
        }
         
        if(r11.equalsIgnoreCase("check")){          
                             
        if(result==true){
           String emailId = (String)session.getAttribute("emailId");
                        String toMailIds[] = {emailId};
                        EmailContent email=new EmailContent();
                        email.setTo(toMailIds);
                        email.setFrom("dashboard@useventing.com");
                        email.setSubject("Area Member Program");
                        
                     String content = "<table width=\"526\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"border:1px solid #999;\"> " +
                                " <tr>" +
                                " <td height=\"463\" valign=\"top\" bgcolor=\"#FCFBF0\" style=\"padding-top:10px; padding-bottom:10px; padding-left:10px; padding-right:10px;\">" +
                                " <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> " +
                                "<tr>" +
                                "<td height=\"70\" valign=\"top\"><img src=\"images/emailHeader.jpg\" alt=\"USEA Online Services Dashboard Email Template\" width=\"515\" height=\"55\" /></td> " +
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
                                "<span class=\"boldTxt\">Dear Member</span>,<br />"+                               
                                "<p>Thank you for your application, you have registered for <strong>"+objMember.getAreaProgName()+" ("+progDesc+")</strong></p>"+
                                "<p><strong>USEA Area Membership Program Details :</strong><br/><br />"+
                                "<strong>Area Name:</strong>"+objMember.getAraeName()+" <br/>"+
                                "<strong>Area Program Name:</strong>"+objMember.getAreaProgName()+ "<br/>"+                                                                                                      
                                " </table>"+
                                "</td></tr>"+
                                "</table>";
                                email.setBody(content); 
        
                EmailEngine emailEngine = new EmailEngine();
                boolean emailFlag = emailEngine.sendMimeEmail(email);
                Debug.print("Email sent sucessfully :"+emailFlag); 
                        
        request.setAttribute("areaName",objMember.getAraeName());                                     
        request.setAttribute("amount",String.valueOf(chkAmt));
        request.setAttribute("areaRidProg",objMember.getAreaProgName());
        request.setAttribute("progYear",objMember.getAreaProgYear());
        request.setAttribute("actStat",actStat);
        request.setAttribute("areaRidProgDesc",progDesc);
       return mapping.findForward("frmMyAreaProgCheckSuccess");
        }
        }
        }
     }catch(Exception e){
         e.printStackTrace();
         
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