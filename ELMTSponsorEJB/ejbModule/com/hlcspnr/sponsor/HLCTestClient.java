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
package com.hlcspnr.sponsor;

import com.hlcspnr.util.Debug;
import javax.naming.Context;
import java.util.Properties;
import java.util.*;

///import com.ejb.productmanager.ProductManagerHomeRemote;
//import com.ejb.productmanager.ProductManagerRemote;

public class HLCTestClient {
    public static void main( String [] args )
    {
        try
        {
            Context jndiContext = getInitialContext();
            Object obj=jndiContext.lookup("ejb/HLCSponsorSessionBean");
            HLCSponsorSessionRemoteHome home = (HLCSponsorSessionRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(obj,HLCSponsorSessionRemoteHome.class);
            HLCSponsorSessionRemote remote = home.create();
            
       // RenewalOrganizerDetails objRenewalDet = new RenewalOrganizerDetails();
       // PaymentDetails objPayDet = new PaymentDetails();

        //SponsorDetails objSponsor = new SponsorDetails();
        
       // objSponsor.setUserId("ff093ecd-e823-4e36-acb9-cef71534dd74");
       // objSponsor.setCompanyName("DigiBlitz Tech");
      //  objSponsor.setComments("This is for testing purpose");
      //  objSponsor.setPlanId("a189eb99-9365-4d3a-ac8c-e9e7b3854d28");
      //  objSponsor.setStatusId("b9f3f76a-1aa7-4e19-b457-43104413828e");
        //objSponsor.setSalesPersonId("328328");
        //objSponsor.setContractDtartDate(new Date());
        //objSponsor.setcontractEndDate(new Date());
            
        //System.out.println(date.get);
        
        //remote.salesPersonUpdate("e1defc24-4338-4600-8b3b-cb2b912e004b","54321",new Date(),new Date(),"15000");
         
            
      
            
            
        /*==========================Schedule Making==========================================*/    
        //7c7f5db2-d79b-4681-b564-4ab826db449a -- Recurring.
            //82427742-f358-44fa-93e5-529cf750a6c1 ---- Special.
            //c6c39b45-916b-4779-86ef-6dad99fd8dc1 ---Manual
     /*         String a = "12/12/2007";
        Date date =new Date(a);
        
          Vector v = new Vector();
            ArrayList a1 = new ArrayList();
            a1.add(new Float(10000.0));
            a1.add(new Date());
            v.add(a1);
            ArrayList a2 = new ArrayList();
            a2.add(new Float(5000.0));
            a2.add(new Date());
            v.add(a2);
           
           // v = null;
            
            boolean result;
         /* boolean result = remote.createPaymentSchedule( "7c7f5db2-d79b-4681-b564-4ab826db449a" , "c6c39b45-916b-4779-86ef-6dad99fd8dc1",
            date, 15000 , true,"2", "Pending",v);
            System.out.print("Sucessfully inserted.. Manual" + result);
          
            result = remote.createPaymentSchedule( "7c7f5db2-d79b-4681-b564-4ab826db449a" , "7c7f5db2-d79b-4681-b564-4ab826db449a",
            date, 15000 , true,"2", "Pending",v);
            System.out.print("Sucessfully inserted.. Recurring" + result);
      
                    result = remote.createPaymentSchedule( "7c7f5db2-d79b-4681-b564-4ab826db449a" , "82427742-f358-44fa-93e5-529cf750a6c1",
            date, 15000 , true,"2", "Pending",v);
        */  
           // System.out.print("Sucessfully inserted.. Special" + result);
           /*==========================Schedule Making End==========================================*/    
           /*  if(endForm.getCompetitionDate() != null && !(endForm.getCompetitionDate().equals(""))){
                comp_date =(Date)sdf.parse(endForm.getCompetitionDate());
                System.out.print("comp_date  " + comp_date);
            }
            */
            
            /*==========================Schedule Fetching==========================================*/
       /*     Vector v  = (Vector)remote.getPaymentScheduleDetails("bc0fe13c-d7ad-48e4-98d3-15d01e641184");
            Debug.print("This is from Client");
            Enumeration e = v.elements();
            if(e.hasMoreElements()){
                String scheduleId = (String)e.nextElement();
                String sponsorId =  (String)e.nextElement();
                 Debug.print("This is from Client");
                String scheduleTypeId = (String)e.nextElement();
                 Debug.print("This is from Client");
                Date dueDate =  (Date)e.nextElement();
                 Debug.print("This is from Client");
                float dueAmount =  ((Float)e.nextElement()).floatValue();
                 Debug.print("This is from Client" + dueAmount);
                //boolean continueStatus = ((Boolean)e.nextElement()).booleanValue();
                Debug.print("This is from Client");
                String frequencyRate = (String)e.nextElement();
                String paymentStatus =(String)e.nextElement();
                Debug.print("=================================");
                Debug.print(scheduleId);
                Debug.print(sponsorId);
                Debug.print(scheduleTypeId);
                Debug.print("" + dueDate);
                Debug.print("" + dueAmount);
               // Debug.print("" + continueStatus);
                Debug.print("" + frequencyRate);
                Debug.print("" + paymentStatus);       
                Debug.print("================================="); 
            }
            */
            
            /*==========================Schedule Status Change ==========================================*/    
            
           // boolean result = remote.paymentScheduleStatusChange("d8e29ce2-6610-4667-97cd-27dae60fc25d","Waiting Stage");
            
           // Debug.print("Result :" + result);
             /*==========================Schedule Fetching All Details==========================================*/
     /*    Vector v  = (Vector)remote.getPaymentScheduleDetails("cd90b630-6f43-4e63-957b-09507434757d");
            Enumeration e = v.elements();
            if(e.hasMoreElements()){
                String scheduleId = (String)e.nextElement();
                String sponsorId =  (String)e.nextElement();
                String scheduleTypeId = (String)e.nextElement();
                Date dueDate =  (Date)e.nextElement();
                float dueAmount =  ((Float)e.nextElement()).floatValue();
                //boolean continueStatus = ((Boolean)e.nextElement()).booleanValue();
                String continueStatus = (String)e.nextElement();
                String frequencyRate = (String)e.nextElement();
                String paymentStatus =(String)e.nextElement();
                Debug.print("=================================");
                Debug.print(scheduleId);
                Debug.print(sponsorId);
                Debug.print(scheduleTypeId);
                Debug.print("" + dueDate);
                Debug.print("" + dueAmount);
                Debug.print("" + continueStatus);
                Debug.print("sdf" + frequencyRate);
                Debug.print("fsda" + paymentStatus);       
                Debug.print("================================="); 
            }
        */  
            /*==========================Schedule Fetching End==========================================*/    
            
    /*       Vector v  = (Vector)remote.getPaymentSchedule("7c7f5db2-d79b-4681-b564-4ab826db449a");
            
            Enumeration e = v.elements();
            while(e.hasMoreElements()){
                ArrayList a = (ArrayList) e.nextElement();
                Iterator it = a.iterator();
                if(it.hasNext()){
                String scheduleId = (String)it.next();
                String sponsorId =  (String)it.next();
                String scheduleTypeId = (String)it.next();
                Date dueDate =  (Date)it.next();
                float dueAmount =  ((Float)it.next()).floatValue();
                //boolean continueStatus = ((Boolean)e.nextElement()).booleanValue();
                String continueStatus = (String)it.next();
                String frequencyRate = (String)it.next();
                String paymentStatus =(String)it.next();
                Debug.print("=================================");
                Debug.print(scheduleId);
                Debug.print(sponsorId);
                Debug.print(scheduleTypeId);
                Debug.print("" + dueDate);
                Debug.print("" + dueAmount);
                Debug.print("" + continueStatus);
                Debug.print( frequencyRate);
                Debug.print( paymentStatus);       
                Debug.print("================================="); 
                }
            }
       
     **/     
         
            
       
       /* SponsorDetails  objSponsor = remote.getSponsorDetails("3e4239d2-16e7-47e8-9132-a307f336d055");
           Debug.print("================================="); 
            Debug.print("Sponsor ID:"+objSponsor.getSponsorId());
            Debug.print("=================================");
            Debug.print(objSponsor.getUserId());
            Debug.print( objSponsor.getCompanyName());
            Debug.print(objSponsor.getComments());
            Debug.print(objSponsor.getPlanId());
            Debug.print(objSponsor.getStatusId());
            Debug.print(objSponsor.getSalesPersonId());
            Debug.print("" + objSponsor.getContractDtartDate());
            Debug.print("" + objSponsor.getcontractEndDate());       
            Debug.print("================================="); 
       
        
           // SponsorDetails  objSponsor = new SponsorDetails();
            objSponsor.setSponsorId("33ce5d50-edc4-4197-8856-e38217b27b3f");
            objSponsor.setUserId("ff093ecd-e823-4e36-acb9-cef71534dd74");
            objSponsor.setCompanyName("Puni Info Tech");
            objSponsor.setComments("THis is one is update checking");
            objSponsor.setPlanId("a189eb99-9365-4d3a-ac8c-e9e7b3854d28");
            objSponsor.setStatusId("b9f3f76a-1aa7-4e19-b457-43104413828e");
            objSponsor.setSalesPersonId("1234567");
            objSponsor.setContractStartDate(new Date());
            objSponsor.setcontractEndDate(new Date());
          */  
        /*    boolean result = remote.createPlan("SUreshfd" , "This is sample Plan" , "2345454");
            Debug.print("Result :" + result);
            result = remote.createPlan("SUreshfd2" , "This is sample Plan" , "2345454");
            Debug.print("Result :" + result);
           */
            boolean result = remote.createPlan("sdds" , "This is sample Plan" , "2345454");
            Debug.print("Result :" + result);
         
            
            
           /* Vector v = (Vector)remote.getAllPlanDetails();
            
            //getAllPlanDetails()
            Enumeration e = v.elements();
            while(e.hasMoreElements()){
                String []  s = (String []) e.nextElement();
                Debug.print("================================="); 
                Debug.print(s[0]);
                Debug.print(s[1]);
                Debug.print("================================="); 
            }
             */    
            /*================ This is to get all Plan ID ==========*/
          /*  Vector v = (Vector)remote.getAllRequestStatusSponsors("Pending");
            
           // getAllPlanDetails()
            Enumeration e = v.elements();
            while(e.hasMoreElements()){
                SponsorDetails  objSponsor = (SponsorDetails) e.nextElement();
                Debug.print("================================="); 
                Debug.print("Sponsor ID:"+objSponsor.getSponsorId());
                Debug.print("=================================");
                Debug.print(objSponsor.getUserId());
                Debug.print( objSponsor.getCompanyName());
                Debug.print(objSponsor.getComments());
                Debug.print(objSponsor.getPlanId());
                Debug.print(objSponsor.getStatusId());
                Debug.print(objSponsor.getSalesPersonId());
                Debug.print("" + objSponsor.getContractStartDate());
                Debug.print("" + objSponsor.getcontractEndDate());       
                Debug.print("================================="); 
            }
          
            */    
                //========================This is for Particular PlanID =======*/
/*                  String plan [] = (String[] )remote.getPlanDetails("59e5f363-6741-4333-bdd1-2ceb1952cf31");
                System.out.print(plan[0]);
                System.out.print(plan[1]);
                System.out.print(plan[2]);
                System.out.print(plan[3]);
                System.out.print("===============");
  */         
            //remote.deleteSponsorPlan("59e5f363-6741-4333-bdd1-2ceb1952cf31");
            
           // Debug.print("========Successfully deleted..========================="); 
            // EventDetails objEventDet = remote.getEventDetails(10002);
            //Debug.print("========Successfully Editted========================="); 
          /*  //EventDetails objEventDet = remote.getEventDetails(10000);
            Debug.print("================================="); 
            Debug.print("Renewal ID:"+objEventDet.getEventId());
            Debug.print("=================================");
            Debug.print(objEventDet.getOrganizeId());
            Debug.print( objEventDet.getEventSecretaryId());
            Debug.print(objEventDet.getEntryFee());
            Debug.print(objEventDet.getOtherEntryFee());
            Debug.print(objEventDet.getOfficeFee());
            Debug.print(objEventDet.getCheckPayableTo());
            Debug.print(objEventDet.getPinniesCharge());
            Debug.print(objEventDet.getPinniesPosition());
            Debug.print(objEventDet.getAwardTrophy());        
            Debug.print(objEventDet.getAwardPrize());
            Debug.print(objEventDet.getAwardOthers());
            Debug.print(objEventDet.getDateAvailable());
            Debug.print(objEventDet.getAvailableFrom());
            Debug.print(objEventDet.getAvailableFromOther());
            Debug.print(objEventDet.getAvailablePosition());
            Debug.print(objEventDet.getStablingLimited());
            Debug.print(objEventDet.getStallPernightPrice());
            Debug.print(objEventDet.getPerStallPrice());
            Debug.print(objEventDet.getPerStallFromTime());
            Debug.print(objEventDet.getPerStallFromDate());
            Debug.print(objEventDet.getPerStallToTime());
            Debug.print(objEventDet.getPerStallToDate());
            Debug.print(objEventDet.getNoOfStalls());
            Debug.print(objEventDet.getNoOfTempStalls());
            Debug.print(objEventDet.getNoOfTempPermanentStalls());
            Debug.print(objEventDet.getMilesFromEvent());
            Debug.print(objEventDet.getVeterinarianName());
            Debug.print(objEventDet.getVeterinarianPhone());
            Debug.print(objEventDet.getVeterinarianPlace());
            Debug.print(objEventDet.getAccomodationCamping());
            Debug.print(objEventDet.getDirections());
            Debug.print(objEventDet.getFootingDesc());
            Debug.print("=================================");
           */
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
    }
    public static Context getInitialContext()
        throws javax.naming.NamingException {
        Properties p =new Properties();
        p.setProperty( "java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory" );
        p.setProperty( "java.naming.factory.url.pkgs", "org.jboss.naming:org.jnp.interfaces" );
        p.setProperty( "java.naming.provider.url", "localhost:11199" );
        return new javax.naming.InitialContext(p);
    }
}
