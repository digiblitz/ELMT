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
package com.hlcmro.renewal;

import com.hlcmro.org.HLCVaigaiSessionRemote;
import com.hlcmro.org.HLCVaigaiSessionRemoteHome;
import com.hlcmro.util.Debug;
import com.hlcmro.util.HLCPaymentDetails;
import com.hlcmro.util.HLCRenewalOrganizerDetails;
import javax.naming.Context;
import java.util.Properties;
import java.util.Date;
import java.util.*;

///import com.ejb.productmanager.ProductManagerHomeRemote;
//import com.ejb.productmanager.ProductManagerRemote;

public class HLCTestClient {
    public static void main( String [] args )
    {
        try
        {
            Context jndiContext = getInitialContext();
            Object obj=jndiContext.lookup("ejb/HLCVaigaiSessionBean");
            HLCVaigaiSessionRemoteHome home = (HLCVaigaiSessionRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(obj,HLCVaigaiSessionRemoteHome.class);
            HLCVaigaiSessionRemote remote = home.create();
            
            HLCRenewalOrganizerDetails objRenewalDet = new HLCRenewalOrganizerDetails();
            HLCPaymentDetails objPayDet = new HLCPaymentDetails();

          
    /*
            objRenewalDet.setEventId(10000);
            objRenewalDet.setOrganizerId("100000");
            objRenewalDet.setCompetitionName("Sury");
            objRenewalDet.setCompetitionLocation("Chennai Ban");
            objRenewalDet.setCompetitionCity("Kumbakonam");
            objRenewalDet.setCompetitionState("Tamil Nadu");
            objRenewalDet.setCompetitionCountry("India");
            objRenewalDet.setCompetitionZip("632311");
            objRenewalDet.setCompetitionDate(new Date());
            objRenewalDet.setChampionshipArea("KKNaar");        
            objRenewalDet.setComManagementName("Suresh Kumar");
            objRenewalDet.setComManagementAddress("New Street");
            objRenewalDet.setComManagementCity("Chennai");
            objRenewalDet.setComManagementState("TN");
            objRenewalDet.setComManagementCountry("INDIA");
            objRenewalDet.setComManagementZip("632311");
            objRenewalDet.setComManagementPhone("9884299619");
            objRenewalDet.setComManagementFax("4832-342-423");
            objRenewalDet.setManagerUsefMemberId("USEFDBT0019");
            objRenewalDet.setManagerUseaMemberId("USEADBT0499");
            objRenewalDet.setManagerName(" Suresh Kumar");
            objRenewalDet.setSecretaryUsefMemberId("USEFDBT500");
            objRenewalDet.setSecretaryName("Puni R");
            
            
            objPayDet.setCcName("Suresh SBI");
            objPayDet.setCcType("MASTER");
            objPayDet.setCcNumber(41289432);
            objPayDet.setCcExpMonth(10);
            objPayDet.setCcExpYear(2006);
            objPayDet.setCcCvvid(1783);
            objPayDet.setBankName("KVB");
            objPayDet.setCheckDate(new Date());
            objPayDet.setCheckNumber(342846824);
            objPayDet.setAmount(12000);
            objPayDet.setPaymentDate(new Date());
            objPayDet.setPaymentStatus("yes");
            
            remote.addRenewal(objRenewalDet,objPayDet);
            Debug.print("Sucessfully Inserted");
        */
         
            //objRenewalDet = remote.getRenewalDetailByEventId("10000");
      /* 
         ArrayList al = remote.getRenewalDetailByEventId("10000");
            Iterator it = al.iterator();
            while(it.hasNext()){
                objRenewalDet = (RenewalOrganizerDetails) it.next();
                Debug.print("================================="); 
                Debug.print("Renewal ID:"+objRenewalDet.getRenewalId());
                Debug.print("=================================");
                Debug.print(" " + objRenewalDet.getEventId());
                Debug.print(objRenewalDet.getOrganizerId());
                Debug.print( objRenewalDet.getCompetitionName());
                Debug.print(objRenewalDet.getCompetitionLocation());
                Debug.print(objRenewalDet.getCompetitionCity());
                Debug.print(objRenewalDet.getCompetitionState());
                Debug.print(objRenewalDet.getCompetitionCountry());
                Debug.print(objRenewalDet.getCompetitionZip());
                Debug.print("" + objRenewalDet.getCompetitionDate());
                Debug.print(objRenewalDet.getChampionshipArea());        
                Debug.print(objRenewalDet.getComManagementName());
                Debug.print(objRenewalDet.getComManagementAddress());
                Debug.print(objRenewalDet.getComManagementCity());
                Debug.print(objRenewalDet.getComManagementState());
                Debug.print(objRenewalDet.getComManagementCountry());
                Debug.print(objRenewalDet.getComManagementZip());
                Debug.print(objRenewalDet.getComManagementPhone());
                Debug.print(objRenewalDet.getComManagementFax());
                Debug.print(objRenewalDet.getManagerUsefMemberId());
                Debug.print(objRenewalDet.getManagerUseaMemberId());
                Debug.print(objRenewalDet.getManagerName());
                Debug.print(objRenewalDet.getSecretaryUsefMemberId());
                Debug.print(objRenewalDet.getSecretaryName());
                Debug.print("=================================");
            }
            
            */
           
        /* 
             ArrayList al = remote.getRenewalDetailByOraganizer("100001");
            Iterator it = al.iterator();
            while(it.hasNext()){
                objRenewalDet = (RenewalOrganizerDetails) it.next();
                Debug.print("================================="); 
                Debug.print("Renewal ID:"+objRenewalDet.getRenewalId());
                Debug.print("=================================");
                Debug.print(" " + objRenewalDet.getEventId());
                Debug.print(objRenewalDet.getOrganizerId());
                Debug.print( objRenewalDet.getCompetitionName());
                Debug.print(objRenewalDet.getCompetitionLocation());
                Debug.print(objRenewalDet.getCompetitionCity());
                Debug.print(objRenewalDet.getCompetitionState());
                Debug.print(objRenewalDet.getCompetitionCountry());
                Debug.print(objRenewalDet.getCompetitionZip());
                Debug.print("" + objRenewalDet.getCompetitionDate());
                Debug.print(objRenewalDet.getChampionshipArea());        
                Debug.print(objRenewalDet.getComManagementName());
                Debug.print(objRenewalDet.getComManagementAddress());
                Debug.print(objRenewalDet.getComManagementCity());
                Debug.print(objRenewalDet.getComManagementState());
                Debug.print(objRenewalDet.getComManagementCountry());
                Debug.print(objRenewalDet.getComManagementZip());
                Debug.print(objRenewalDet.getComManagementPhone());
                Debug.print(objRenewalDet.getComManagementFax());
                Debug.print(objRenewalDet.getManagerUsefMemberId());
                Debug.print(objRenewalDet.getManagerUseaMemberId());
                Debug.print(objRenewalDet.getManagerName());
                Debug.print(objRenewalDet.getSecretaryUsefMemberId());
                Debug.print(objRenewalDet.getSecretaryName());
                Debug.print("=================================");
            }
         */
            
         /*       objRenewalDet = remote.getRenewalDetails("a6ccac1f-eb5f-4066-81a0-23c4de23e729");
                Debug.print("================================="); 
                Debug.print("Renewal ID:"+objRenewalDet.getRenewalId());
                Debug.print("=================================");
                Debug.print(" " + objRenewalDet.getEventId());
                Debug.print(objRenewalDet.getOrganizerId());
                Debug.print( objRenewalDet.getCompetitionName());
                Debug.print(objRenewalDet.getCompetitionLocation());
                Debug.print(objRenewalDet.getCompetitionCity());
                Debug.print(objRenewalDet.getCompetitionState());
                Debug.print(objRenewalDet.getCompetitionCountry());
                Debug.print(objRenewalDet.getCompetitionZip());
                Debug.print("" + objRenewalDet.getCompetitionDate());
                Debug.print(objRenewalDet.getChampionshipArea());        
                Debug.print(objRenewalDet.getComManagementName());
                Debug.print(objRenewalDet.getComManagementAddress());
                Debug.print(objRenewalDet.getComManagementCity());
                Debug.print(objRenewalDet.getComManagementState());
                Debug.print(objRenewalDet.getComManagementCountry());
                Debug.print(objRenewalDet.getComManagementZip());
                Debug.print(objRenewalDet.getComManagementPhone());
                Debug.print(objRenewalDet.getComManagementFax());
                Debug.print(objRenewalDet.getManagerUsefMemberId());
                Debug.print(objRenewalDet.getManagerUseaMemberId());
                Debug.print(objRenewalDet.getManagerName());
                Debug.print(objRenewalDet.getSecretaryUsefMemberId());
                Debug.print(objRenewalDet.getSecretaryName());
                Debug.print("=================================");
            */
           
             ArrayList al = remote.searchByCompitionName("Sury Jump");
            Iterator it = al.iterator();
            while(it.hasNext()){
                objRenewalDet = (HLCRenewalOrganizerDetails) it.next();
                Debug.print("================================="); 
                Debug.print("Renewal ID:"+objRenewalDet.getRenewalId());
                Debug.print("=================================");
                Debug.print(" " + objRenewalDet.getEventId());
                Debug.print(objRenewalDet.getOrganizerId());
                Debug.print( objRenewalDet.getCompetitionName());
                
                Debug.print("" + objRenewalDet.getCompetitionDate());
                
                Debug.print(objRenewalDet.getComManagementName());
                Debug.print(objRenewalDet.getComManagementAddress());
                Debug.print(objRenewalDet.getComManagementCity());
                Debug.print(objRenewalDet.getComManagementState());
                Debug.print(objRenewalDet.getComManagementCountry());
                Debug.print(objRenewalDet.getComManagementZip());
                Debug.print(objRenewalDet.getComManagementPhone());
                Debug.print(objRenewalDet.getComManagementFax());
                Debug.print(objRenewalDet.getManagerUsefMemberId());
                Debug.print(objRenewalDet.getManagerUseaMemberId());
                Debug.print(objRenewalDet.getManagerName());
                Debug.print(objRenewalDet.getSecretaryUsefMemberId());
                Debug.print(objRenewalDet.getSecretaryName());
                Debug.print("=================================");
            }
        
            
            
            
            
            
            
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
