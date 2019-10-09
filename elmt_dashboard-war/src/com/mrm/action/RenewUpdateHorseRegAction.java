/*******************************************************************************
 * * * Copyright: 2019 digiBlitz Foundation
 *  * * 
 *  * * License: digiBlitz Public License 1.0 (DPL) 
 *  * * Administered by digiBlitz Foundation. www.digiblitz.org/dpl/
 *  * * 
 *  * * Inventor: Suresh Kannan (Maya Suresh Kannan Balabisegan ) (www.sureshkannan.org)
 *  * * 
 *  * * Authors: Suresh Kannan (Maya Suresh Kannan Balabisegan )& digiBlitz.
 *  * * 
 *  * * "Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software in accordance to the rules & restrictions of the digiBlitz Public License."
 ******************************************************************************/
package com.mrm.action;
/*
 * RenewUpdateHorseRegAction.java
 *
 * Created on August 27, 2006, 4:04 PM
 */

import com.hlckavery.statful.HLCKaverySessionBeanStatfulRemote;
import com.hlckavery.statful.HLCKaverySessionBeanStatfulRemoteHome;
import com.hlcreg.util.Debug;
import com.hlcreg.util.HLCHorseDescription;
import com.hlcreg.util.HLCHorseMemberDetails;
import com.hlcreg.util.HLCPaymentDetails;
import com.mrm.actionform.RenewUpdateHorseRegActionForm;
import com.hlcreg.util.HLCHorseServiceTypeDetails;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import org.apache.struts.util.MessageResources;
import java.util.regex.*;

/**
 *
 * @author karthikeyan
 * @version
 */

public class RenewUpdateHorseRegAction extends Action {
    
     String memberId = null;
     String userId = null;
     String fwd="";
     
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
         try {
             
                    MessageResources mr=getResources(request);
                    
                    String namingfactory=mr.getMessage("ejbclient.namingfactory");
                    String contextfactory=mr.getMessage("ejbclient.contextfactory");
                    String urlprovider=mr.getMessage("ejbclient.urlprovider");
                    String lookupip=mr.getMessage("ejbclient.ip");
                    String jndiname=mr.getMessage("jndi.kavery");
                                      
                    System.setProperty(namingfactory,contextfactory);
                    System.setProperty(urlprovider,lookupip);
                    Context jndiContext = new InitialContext();
                    Object objref = jndiContext.lookup(jndiname);
          
                HLCKaverySessionBeanStatfulRemoteHome home = (HLCKaverySessionBeanStatfulRemoteHome)
                javax.rmi.PortableRemoteObject.narrow(objref,HLCKaverySessionBeanStatfulRemoteHome.class);
                HLCKaverySessionBeanStatfulRemote remote = home.create();
                              
                HLCHorseMemberDetails objHorseMember = new HLCHorseMemberDetails();
                HLCHorseDescription objHorseDesc = new HLCHorseDescription();
                HLCPaymentDetails objPayment = new HLCPaymentDetails();
                 
                RenewUpdateHorseRegActionForm udatefrm=(RenewUpdateHorseRegActionForm)form;
                
                 String feedisp=udatefrm.getFeeDisp();
                 
                 Pattern pat=Pattern.compile("#");
                 String str[]=pat.split(feedisp);
                 String id=str[0];
                 String mname=str[1];
                 String amt=str[2];
                 System.out.println(id+"  "+mname+ "  "+amt);
                 
                 HttpSession session=request.getSession();
                 userId=(String)session.getAttribute("userId");
               
                SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy");
                Date imp_date =null;
               
                String mid=request.getParameter("memid");
                System.out.println("mid :"+mid);
                objHorseDesc.setHorseMemberId(mid);
               
                objHorseDesc.setHorseMembershipTypeId(id);
                
                  if(udatefrm.getImportedFrom()!=null && udatefrm.getImportedFrom().trim().length()!=0)
                {
                    objHorseDesc.setImportedFrom(udatefrm.getImportedFrom());
                }
                  else
                  {
                     objHorseDesc.setImportedFrom(null);
                  }
                
                if(udatefrm.getDateImported()!=null && udatefrm.getDateImported().trim().length()!=0)
                {
                          
                    imp_date=(Date)sdf.parse(udatefrm.getDateImported());
                }
                else
                {
                    imp_date=null;
                }
                
                objHorseDesc.setImportDate(imp_date);
                
                 if(udatefrm.getForeignGrade()!=null && udatefrm.getForeignGrade().trim().length()!=0)
                {
                     objHorseDesc.setForeignGrade(udatefrm.getForeignGrade());
                }
                else
                {
                    objHorseDesc.setForeignGrade(null);
                }
                
               if(udatefrm.getForeignPoints()!=null && udatefrm.getForeignPoints().trim().length()!=0)
                {
                    objHorseDesc.setForeignPoints(Integer.parseInt(udatefrm.getForeignPoints()));
                }
                else
                {
                    objHorseDesc.setForeignPoints(0);
                }
                
                  if(udatefrm.getAssignedGrade()!=null && udatefrm.getAssignedGrade().trim().length()!=0)
                {
                    objHorseDesc.setAssignedGrade(udatefrm.getAssignedGrade());
                }
                else
                {
                    objHorseDesc.setAssignedGrade(null);
                }
                
                if(udatefrm.getAssignedPoints()!=null && udatefrm.getAssignedPoints().trim().length()!=0)
                {
                     objHorseDesc.setAssignedPoints(Integer.parseInt(udatefrm.getAssignedPoints()));
                }
                else
                {
                     objHorseDesc.setAssignedPoints(0);
                }
                
                
                System.out.println(objHorseDesc.getHorseMemberId());
                System.out.println(objHorseDesc.getHorseMembershipTypeId());
                System.out.println(objHorseDesc.getImportedFrom());
                System.out.println(objHorseDesc.getImportDate());
                System.out.println(objHorseDesc.getForeignGrade());
                System.out.println(objHorseDesc.getForeignPoints());
                System.out.println(objHorseDesc.getAssignedGrade());
                System.out.println(objHorseDesc.getAssignedPoints());
                                
                session.setAttribute("objHorseDesc",objHorseDesc);
               // boolean bol = remote.editHorseMemberDetails(objHorseDesc);
                
                //System.out.println("Update Result is for horse details : "+bol);
                
                String r11= request.getParameter("r11");
              
                 //------------------------- choose for payment mode card ------------------------
                
                if(r11.equalsIgnoreCase("card")){
                   
                objPayment.setUserId(userId);   
                
                 System.out.println("user id - objPayment.getUserId() :" +objPayment.getUserId());
                
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
                    objPayment.setCcCvvid(Integer.parseInt(request.getParameter("cVVNo")));
                }
                 else
                {
                    objPayment.setCcCvvid(0);
                 }
                
                objPayment.setBankName(null);
             
                objPayment.setCheckDate(null);
                objPayment.setCheckNumber("0");
                
                if(udatefrm.getTotalAmount()!=null && udatefrm.getTotalAmount().trim().length()!=0)
                {
                    objPayment.setAmount(Double.valueOf(udatefrm.getTotalAmount()).doubleValue());
                }
                else
                {
                    objPayment.setAmount(0);
                }
                
                objPayment.setPaymentDate(new Date());
                objPayment.setPaymentStatus("card");
                
                 objPayment.setUserId(userId);
                 session.setAttribute("objPaymentList",objPayment);
                 System.out.println("Inside Renew Horse Member Registration with Credit Card:");
                           
                // remote.addPaymentDetails(objPayment);
                                              
                }
                               
                
                
      // -------------------------- choose for payment mode check ----------------------- 
                
                if(r11.equalsIgnoreCase("check")){
                Date check_date=null;
                
                objPayment.setUserId(userId);   
                System.out.println("user id - objPayment.getUserId() :" +objPayment.getUserId());
                  
                if(request.getParameter("checkDate")!=null && request.getParameter("checkDate").trim().length()!=0)
                {
                    check_date =(Date)sdf.parse(request.getParameter("checkDate"));
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
                
                if(udatefrm.getTotalAmount()!=null && udatefrm.getTotalAmount().trim().length()!=0)
                {
                    objPayment.setAmount(Double.valueOf(udatefrm.getTotalAmount()).doubleValue());
                }
                else
                {
                    objPayment.setAmount(0);
                }
                
                objPayment.setPaymentDate(new Date());
                    
                objPayment.setPaymentStatus("check");  
                
                remote.addPaymentDetails(objPayment);
                                              
                }
               
                
     
                
                           
      //------------- Horse classification cbx id  ---------------
                
         String[] nam=new String[100];
         String[] chkd=new String[100];
         int count=0;
         String cid="";
         
         String hi=request.getParameter("cbxct");       
         int hid=Integer.parseInt(hi);
         
         boolean bl=remote.deleteHorseServiceType(mid);
         System.out.println("deleteHorseServiceType :"+bl);                 
         
         Enumeration en=request.getParameterNames();
         while(en.hasMoreElements())
         {
             String na=(String)en.nextElement();
             nam[count]=na;
             System.out.println("Param names :"+nam[count]);
             count++;
         }
         
         for(int i=0;i<hid;i++)
         {
              String chbx="classification"+Integer.toString(i);
              System.out.println("classifications made : "+chbx);
          for(int y=0;y<nam.length;y++)
          {
                  
             if(chbx.equals(nam[y])){
                chkd[y] =request.getParameter(chbx);
                System.out.println("checked val: "+chkd[y]);
                
                Pattern cpat=Pattern.compile("#");
                String cstr[]=cpat.split(chkd[y]);
                cid=cstr[0];
                System.out.println("cbx id :" +cid);
                
                HLCHorseServiceTypeDetails objHorseService = new HLCHorseServiceTypeDetails();
                
                objHorseService.setHorseMemberId(mid);
                objHorseService.setHorseServiceTypeId(cid);
                
                System.out.println(objHorseService.getHorseMemberId());
                System.out.println(objHorseService.getHorseServiceTypeId());
                
                boolean classst = remote.addHorseServiceType(objHorseService);
                System.out.println(" Result for classification : "+classst);
                                             
             }
          }
         }
    
                /*
                 * Card Payment Processing
                 *
                 */
                
                   
             if(r11.equals("card")){
                    
                int CcExpMonth = 0;
                int CcExpYear = 0;
                int CcCvvid =0;
                double amount =0;
                long CcNumber = 0;
                long checkNumber=0;
                    
                Debug.print("Sucessfully Redirect to NoVa:");
                 //=======================================
                    String email = (String) session.getAttribute("emailId");
                    
                     if(request.getParameter("expirymonth")==null){
                            CcExpMonth =0;
                        }
                        else {
                            CcExpMonth=Integer.parseInt(request.getParameter("expirymonth"));
                        }
                    
                        if(request.getParameter("expiryyear")==null){
                            CcExpYear =0;
                        }
                        else{
                            CcExpYear = Integer.parseInt(request.getParameter("expiryyear"));
                        }
                    
                        if(request.getParameter("cVVNo")==null){
                            CcCvvid =0;
                        }
                        else{
                            CcCvvid = Integer.parseInt(request.getParameter("cVVNo"));
                        }
                         if(request.getParameter("cardNo")==null){
                            CcNumber =0;
                        }
                        else{
                            CcNumber = Long.parseLong(request.getParameter("cardNo"));
                        }
                    
                     if(request.getParameter("totalAmount")!=null && request.getParameter("totalAmount").trim().length()!=0) {
                        amount = Double.valueOf(request.getParameter("totalAmount")).doubleValue();
                        System.out.println("amount :" +amount);
                     }
                     else {
                        amount = 0.00;
                     }
                      
                    
                    request.setAttribute("email",email);
                    request.setAttribute("cardNo",String.valueOf(CcNumber));
                    String expMon = String.valueOf(CcExpMonth);
                    String expYear = String.valueOf(CcExpYear);
                    
                    if(expMon.trim().length()==1) {
                         expMon = "0" + expMon;
                    }
                   // if(expYear.trim().length()==1) {
                    Debug.print("expYear:" + expYear);
                    expYear = expYear.substring(2);
                    //
                    Debug.print("expMon:" + expMon);
                    Debug.print("expYear:" + expYear);

                    String expDate = expMon + expYear;
                    request.setAttribute("expDate",expDate);
                    request.setAttribute("amount",String.valueOf(amount));
                    request.setAttribute("chkDigit",String.valueOf(CcCvvid));
                    //===========================================
                    
                    fwd="successInsertRedirHorseRen";
                }
                
             else{
                     fwd="RenewUpdateHorseReg";
                  
                 }
         
         
        } catch (Exception ex) {
            System.err.println("Caught an exception.");
            ex.printStackTrace();
        }
         return mapping.findForward(fwd);
    }
        public static Context getInitialContext() throws javax.naming.NamingException {
        Properties p =new Properties();
        p.setProperty( "java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory" );
        p.setProperty( "java.naming.factory.url.pkgs", "org.jboss.naming:org.jnp.interfaces" );
        p.setProperty( "java.naming.provider.url", "localhost:11199" );
        return new javax.naming.InitialContext(p);
   
          
    }
    
}
