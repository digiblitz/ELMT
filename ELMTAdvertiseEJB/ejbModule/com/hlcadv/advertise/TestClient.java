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
package com.hlcadv.advertise;

import com.hlccommon.util.HLCAdvertiserVO;
import com.hlccommon.util.Debug;
import javax.naming.Context;
import java.util.Properties;
import java.util.*;

public class TestClient {
    public static void main( String [] args )   {
        try {
            Context jndiContext = getInitialContext();
            Object obj=jndiContext.lookup("ejb/HLCAdvertiseSessionBean");
            HLCAdvertiseSessionRemoteHome advHome = (HLCAdvertiseSessionRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(obj,HLCAdvertiseSessionRemoteHome.class);
            HLCAdvertiseSessionRemote advRemote = advHome.create();
            
            
//*================================ MEDIA Master Start=======================================================================*/            
            ///============================Create Media ================*/
//            boolean result = advRemote.createMedia("Suresh1" ,"This is from Media");
  //          Debug.print("Result:" + result);
            
            ///============================Edit Media ================*/
           // boolean result =  advRemote.editMedia("39c8df9b-60d7-4db6-a8af-9a6db64f277b", "Suresh" ,"This is from suresh");
            //Debug.print("Result:" + result);
            
            
            ///============================View particular Media ================*/
/*            String[] s = advRemote.getMediaDetails("39c8df9b-60d7-4db6-a8af-9a6db64f277b");
            
            Debug.print("Media Id:" + s[0]);
            Debug.print("Media Name:" + s[1]);
            Debug.print("Media Description:" + s[2]);
            Debug.print("Media Status:" + s[3]);
          */
             ///============================View All Media details================*/
          
          /*
           Vector v = (Vector)advRemote.getAllMediaDetails();
            Enumeration e = v.elements();
            while(e.hasMoreElements()){
                String[] s = (String [])e.nextElement();
                Debug.print("===============================");
                Debug.print("Media Id:" + s[0]);
                Debug.print("Media Name:" + s[1]);
                Debug.print("Media Description:" + s[2]);
                Debug.print("Media Status:" + s[3]);
                Debug.print("===============================");
            }
           */ 
            //======================Remove Media=====================//
           //boolean result = advRemote.deleteMedia("c43fb255-a8b9-4e38-838e-0cabe855c500");
           //Debug.print("Result:" + result);
            
            
            //*================================END of MEDIA Master=======================================================================*/
            //*================================Dimension Master=======================================================================*/            
                       
               ///============================Create Dimension Master ================*/
               //     boolean result = advRemote.createDimension("Suresh");
                  //  Debug.print("Result: in dimension" + result);
            
                ///============================Edit Dimension ================*/
                    //boolean result =  advRemote.editDimension("7dae8048-3dfb-48d8-bf6e-dd61d3bff694", "Suresh Kumar");
                    //Debug.print("Result:" + result);

            
            ///============================View particular Dimension ================*/
               /* String[] s = advRemote.getDimensionDetails("40f7653d-0885-4abf-8ca2-66bce603e971");

                Debug.print("Dimension Id:" + s[0]);
                Debug.print("Dimension Name:" + s[1]);
                Debug.print("Dimension Status:" + s[2]);
         */
                        ///============================View All Dimension details================*/
          /*
         
                       Vector v = (Vector)advRemote.getAllDimensionDetails();
                        Enumeration e = v.elements();
                        while(e.hasMoreElements()){
                            String[] s = (String [])e.nextElement();
                            Debug.print("===============================");
                            Debug.print("Dimension Id:" + s[0]);
                            Debug.print("Dimension Name:" + s[1]);
                            Debug.print("Dimension Status:" + s[2]);
                            Debug.print("===============================");
                        }
          */
                    
              //======================Remove Dimension=====================//
//                boolean result = advRemote.deleteDimension("7dae8048-3dfb-48d8-bf6e-dd61d3bff694");
  //              Debug.print("Result:" + result);
            //*================================END of Dimension Master=======================================================================*/
            
              //*================================Issue Master=======================================================================*/            
                       
               ///============================Create Issue Master ================*/
                   // boolean result = advRemote.createIssue("Jan-Mar","2459b5ca-af79-45d2-b006-2719865dd483");
                  // Debug.print("Result:" + result);
            
                ///============================Edit Dimension ================*/
                   // boolean result =  advRemote.editIssue("868bb2ad-3b08-4355-b9a9-214c2b590d2a","Nov-Dec" , "2459b5ca-af79-45d2-b006-2719865dd483");
                   // Debug.print("Result:" + result);

            
            ///============================View particular Issue ================*/
               /*
                String[] s = advRemote.getIssueDetails("868bb2ad-3b08-4355-b9a9-214c2b590d2a");

                Debug.print("Issue Id:" + s[0]);
                Debug.print("Issue Name:" + s[1]);
                Debug.print("Media Id:" + s[2]);
                Debug.print("Issue Status:" + s[3]);
              */
                        ///============================View All Issue details================*/
                
                 /*      Vector v = (Vector)advRemote.getAllIssueDetails();
                        Enumeration e = v.elements();
                        while(e.hasMoreElements()){
                            String[] s = (String [])e.nextElement();
                            Debug.print("===============================");
                            Debug.print("Issue Id:" + s[0]);
                            Debug.print("Issue Name:" + s[1]);
                            Debug.print("Media ID:" + s[2]);
                            Debug.print("Issue Status:" + s[3]);
                            Debug.print("===============================");
                        }
        */
                    
              //======================Remove Dimension=====================//
              // boolean result = advRemote.deleteIssue("868bb2ad-3b08-4355-b9a9-214c2b590d2a");
             //  Debug.print("Result:" + result);
            //*================================END of Issue Master=======================================================================*/
            
    //*================================Display Type Master=======================================================================*/            
                       
               ///============================Create Display Type Master ================*/
                /*   boolean result = advRemote.createDisplayType("Display Ads","109774c9-58c8-4640-8b04-16dc97552e9d","This is sample data for Display Type");
                   Debug.print("Result:" + result);
                   
                  result = advRemote.createDisplayType("Cover Ads","109774c9-58c8-4640-8b04-16dc97552e9d","This is sample data for Display Type");
                  Debug.print("Result:" + result);
                 */   
                   
            
                ///============================Edit Display Type ================*/
                 //   boolean result =  advRemote.editDisplayType("589af533-1de6-4b96-8fa9-39cc2888b110","Display Ads sample","109774c9-58c8-4640-8b04-16dc97552e9d","This is sample data for Display Type");
                  //  Debug.print("Result:" + result);

            
            ///============================View particular Display Type ================*/
            // String dispType [] = {displayTypeId,displayTypeName,mediaId,displayTypeDescription,dispStatus};
            
            /*
                String[] s = advRemote.getDisplayTypeDetails("589af533-1de6-4b96-8fa9-39cc2888b110");

                Debug.print("Display ID:" + s[0]);
                Debug.print("Display Name:" + s[1]);
                Debug.print("Media Id:" + s[2]);
                Debug.print("Display Desc :" + s[3]);
                Debug.print("Display Status:" + s[4]);
              */
                        ///============================View All Display Type details================*/
                
                 /*    Vector v = (Vector)advRemote.getAllDisplayTypeDetails();
                        Enumeration e = v.elements();
                        while(e.hasMoreElements()){
                            String[] s = (String [])e.nextElement();
                            Debug.print("===============================");
                            Debug.print("Display ID:" + s[0]);
                            Debug.print("Display Name:" + s[1]);
                            Debug.print("Media Id:" + s[2]);
                            Debug.print("Display Desc :" + s[3]);
                            Debug.print("Display Status:" + s[4]);
                            Debug.print("===============================");
                        }
                        */
                    
              //======================Remove Display Type =====================//
             // boolean result = advRemote.deleteDisplayType("589af533-1de6-4b96-8fa9-39cc2888b110");
            //  Debug.print("Result:" + result);
            //*================================END of Display Type Master=======================================================================*/
            
            //*================================Display Sub Type Master=======================================================================*/            
     //public boolean createDisplaySubType(String displaySubTypeName , String displayTypeId, String displaySubTypeDescription) throws RemoteException;  
     //public boolean editDisplaySubType(String displaySubTypeId, String displaySubTypeName , String displayTypeId, String displaySubTypeDescription) throws RemoteException;
     //public Vector getAllDisplaySubTypeDetails() throws RemoteException,FinderException;
     //public String[] getDisplaySubTypeDetails(String displaySubTypeId) throws RemoteException,FinderException;
     //public boolean deleteDisplaySubType(String displaySubTypeId) throws RemoteException,FinderException;  
               ///============================Create Display Sub Type Master ================*/
                  /* boolean result = advRemote.createDisplaySubType("Display SUb Ads","c008f417-5b6b-4b1c-9486-36c8df186209","This is sample data for Display SUb  Type");
                   Debug.print("Result:" + result);
                  result = advRemote.createDisplaySubType("Display Ads 22","c008f417-5b6b-4b1c-9486-36c8df186209","This is sample data for Display SUb  Type");
                   Debug.print("Result:" + result);
            */
                ///============================Edit Display Type ================*/
                  //  boolean result =  advRemote.editDisplaySubType("1ceae5f6-1fd1-4f0e-aa03-4b1bc0f4a465","SUresh sub 2","c008f417-5b6b-4b1c-9486-36c8df186209","This is sample data for Display SUb  Type");
                 //  Debug.print("Result:" + result);

            
            ///============================View particular Display Type ================*/
            
//String dispSubType [] = {displaySubTypeId,displaySubTypeName,displayTypeId,displaySubTypeDescription,dispStatus};
              /*  String[] s = advRemote.getDisplaySubTypeDetails("1ceae5f6-1fd1-4f0e-aa03-4b1bc0f4a465");

                Debug.print("Display sub ID:" + s[0]);
                Debug.print("Display sub Name:" + s[1]);
                Debug.print("Media Id:" + s[2]);
                Debug.print("Display sub Desc :" + s[3]);
                Debug.print("Display sub Status:" + s[4]);
                 Debug.print("Display sub Status:" + s[5]);
            */
                        ///============================View All Display Type details================*/
                
                   /*  Vector v = (Vector)advRemote.getAllDisplaySubTypeDetails();
                        Enumeration e = v.elements();
                        while(e.hasMoreElements()){
                            String[] s = (String [])e.nextElement();
                            Debug.print("===============================");
                            Debug.print("Display sub type ID:" + s[0]);
                            Debug.print("Display  sub type Name:" + s[1]);
                            Debug.print("Media sub type Id:" + s[2]);
                            Debug.print("Display sub type Desc :" + s[3]);
                            Debug.print("Display sub type Status:" + s[4]);
                           // Debug.print("Display sub typeStatus:" + s[5]);
                            Debug.print("===============================");
                        }
                    */
              //======================Remove Display Type =====================//
             // boolean result = advRemote.deleteDisplaySubType("9d08c281-d3f6-4461-a37f-f2004e6c9fb6");
             // Debug.print("Result:" + result);
            //*================================END of Issue Master=======================================================================*/
    
        //*================================Advertiser Details=======================================================================*/            
            
             HLCAdvertiserVO objAdvt = new HLCAdvertiserVO();
             ArrayList salAl1 = new ArrayList();
             ArrayList salAl2 = new ArrayList();
             ArrayList adsDet = new ArrayList();
             
           
/*            String issueId = (String)adsEnum.next();
            String advMapId = (String)adsEnum.next();
            String dimensionId = (String)adsEnum.next();
            String placement = (String)adsEnum.next();
            String discount = (String)adsEnum.next();
            String amount = (String)adsEnum.next();
       */     
             
      /*       salAl1.add("8d912f73-7f5b-4820-9ab1-f3c508522573");
             salAl1.add("0e6b8599-6100-44c8-b098-0be898c88c3e");
             salAl1.add("fb94730d-6b21-4063-80d2-4c18d8c2e12b");
             salAl1.add("Kumbakonam");
             salAl1.add("23");
             salAl1.add("3456");
             
             adsDet.add(salAl1);
             
             salAl2.add("8d912f73-7f5b-4820-9ab1-f3c508522573");
             salAl2.add("5b494d41-8d2c-4fe9-9432-15c17badd92c");
             salAl2.add("fb94730d-6b21-4063-80d2-4c18d8c2e12b");
             salAl2.add("SirKali");
             salAl2.add("45");
             salAl2.add("343434");
             
             adsDet.add(salAl2);
             
             
            objAdvt.setUserId("ff093ecd-e823-4e36-acb9-cef71534dd74");
            objAdvt.setMediaId("2459b5ca-af79-45d2-b006-2719865dd483");
            objAdvt.setAdvertiser("puni");
            objAdvt.setAdAgency("puni Group");
            objAdvt.setAgencyFirstName("Punitha");
            objAdvt.setAgencyMiddleName("P");
            objAdvt.setAgencyLastName("R");
            objAdvt.setAgencyPhone("9894307464");
            objAdvt.setAgencyFax("22222222222"); 
            objAdvt.setAgencyEmail("punitha@digiblitz.com");
            objAdvt.setAgencyAddress("suriya Street");
            objAdvt.setAgencySuite("suite");
            objAdvt.setAgencyCity("Sirkali");
            objAdvt.setAgencyState("Tamil Nadu");
            objAdvt.setAgencyCountry("India");
            objAdvt.setAgencyZip("4565646");
            objAdvt.setInvoiceTo("Puni invoice");
            objAdvt.setComments("This is from puni comments");
            objAdvt.setAdvPosted(true);
            objAdvt.setAdvPostedDate(new Date());
            objAdvt.setMaterialComingFrom("puni info tech");
            objAdvt.setMaterialComingDate(new Date());
            objAdvt.setAdvSuppliedOn("Sup On");
            objAdvt.setSalesPersonId("1234");
            objAdvt.setRequestStatus("Pending");
            objAdvt.setActiveStatus(true);
       */     
           // objAdvt.setAdsDetails(adsDet);
           //     boolean result  = advRemote.createAdvertiser(objAdvt,adsDet);
          //     Debug.print("Result:" + result);
             
/*                displaySubTypeId =  objSubTypelocalRemote.getDisplaySubTypeId();
               String displaySubTypeName =  objSubTypelocalRemote.getDisplaySubTypeName();
               String displayTypeIdVal = objSubTypelocalRemote.getDisplayTypeId();
  */          
       /*     ArrayList result  = advRemote.getPriceDetailsByMediaIdDispId("109774c9-58c8-4640-8b04-16dc97552e9d", "4677fec8-5b87-4a17-ac62-20418ab87cf4");
           Debug.print("Result:" + result);
            
            if(result!=null && result.size()!=0){
                for (Iterator it = result.iterator(); it.hasNext();) {
                     
                    String[] subType = (String []) it.next();
                    Debug.print("SubType Name:" + subType[1]);
                    Debug.print("===============================");
                    ArrayList  priceList = (ArrayList) it.next();
                    for (Iterator itP = priceList.iterator(); itP.hasNext();) {
                         String fqArray[] = (String []) itP.next();
                        // Debug.print("Frequence Name:" + fqArray[0]);
                        // Debug.print("Frequence Price:" + fqArray[1]);
                         Debug.print(fqArray[0] + ":"+ fqArray[1]);
                    }
                     Debug.print("===============================");
                }
                
            }    
           // Debug.print("Result :" + result);
            
          */  
            
        /*
            Debug.print("HI");
            ArrayList list = advRemote.getAdvertiserDetails("306a17fd-2895-4eaa-a0b1-c145e7e27a68");
            Debug.print("" +list);
            Iterator it = list.iterator();
           while(it.hasNext()){
                AdvertiserVO objAdv = (AdvertiserVO)it.next();
                Debug.print("===============================");
                Debug.print("Display sub type ID:" + objAdv.getAgencyEmail());
                Debug.print("Display  sub type Name:" + objAdv.getRequestStatus());
                Debug.print("==============================="); 
                
                ArrayList li = (ArrayList)it.next();
                Iterator its = li.iterator();
                while(its.hasNext()){
                    AdvertiseDetVO objAdvDet = (AdvertiseDetVO)its.next();
                    Debug.print("===============================");
                    Debug.print("Display objAdvDet:" + objAdvDet.getPlacement());
                    Debug.print("Display  objAdvDet:" + objAdvDet.getAmount());
                    Debug.print("==============================="); 
                }

           }
           */
            
/*            Debug.print("HI");
            ArrayList list = advRemote.getAllAdvertiserDetails();
            Debug.print("" +list);
            Iterator itall = list.iterator();
           while(itall.hasNext()){
                ArrayList al = (ArrayList) itall.next();
                Iterator it = al.iterator();
               while(it.hasNext()){
                    AdvertiserVO objAdv = (AdvertiserVO)it.next();
                    Debug.print("===============================");
                    Debug.print("Mail ID:" + objAdv.getAgencyEmail());
                    Debug.print("Request Status:" + objAdv.getRequestStatus());
                    Debug.print("==============================="); 

                    ArrayList li = (ArrayList)it.next();
                    Iterator its = li.iterator();
                    while(its.hasNext()){
                        AdvertiseDetVO objAdvDet = (AdvertiseDetVO)its.next();
                        Debug.print("===============================");
                        Debug.print("Placement:" + objAdvDet.getPlacement());
                        Debug.print("Amount:" + objAdvDet.getAmount());
                        Debug.print("==============================="); 
                    }
               }
           }
 *
 */
            /*
           Debug.print("HI");
            ArrayList list = advRemote.getAllAdvertiserStatusDetails("Pending");
            Debug.print("" +list);
            Iterator itall = list.iterator();
           while(itall.hasNext()){
                ArrayList al = (ArrayList) itall.next();
                Iterator it = al.iterator();
               while(it.hasNext()){
                    AdvertiserVO objAdv = (AdvertiserVO)it.next();
                    Debug.print("===============================");
                    Debug.print("Mail ID:" + objAdv.getAgencyEmail());
                    Debug.print("Request Status:" + objAdv.getRequestStatus());
                    Debug.print("==============================="); 

                    ArrayList li = (ArrayList)it.next();
                    Iterator its = li.iterator();
                    while(its.hasNext()){
                        AdvertiseDetVO objAdvDet = (AdvertiseDetVO)its.next();
                        Debug.print("===============================");
                        Debug.print("Placement:" + objAdvDet.getPlacement());
                        Debug.print("Amount:" + objAdvDet.getAmount());
                        Debug.print("==============================="); 
                    }
               }
           }
   */
            
            //boolean result = advRemote.advertisementStatusChange("d5d7adc8-db08-4f06-9382-27211ef5a957", "Active");
            //Debug.print("Result:" + result);
         // alAds.add(objAdsVO);
          // alAds.add(al);
          //  alAds.add(adsNamingDet);
             
             
             
            ArrayList list = advRemote.getAdvertiserDetails("9b183b42-61ff-4aeb-84a0-0571db37b430");
            Debug.print("" +list);
            Iterator it = list.iterator();
           //while(itall.hasNext()){
                //ArrayList al = (ArrayList) itall.next();
               // Iterator it = al.iterator();
               while(it.hasNext()){
                    HLCAdvertiserVO objAdv = (HLCAdvertiserVO)it.next();
                    Debug.print("===============================");
                    Debug.print("Mail ID:" + objAdv.getAgencyEmail());
                    Debug.print("Request Status:" + objAdv.getRequestStatus());
                    Debug.print("Added Date:" + objAdv.getAddDate());
                    
                    Debug.print("==============================="); 
                    ArrayList first = (ArrayList)it.next();
                    ArrayList li = (ArrayList)it.next();
                    Iterator its = li.iterator();
                    while(its.hasNext()){
                        //String priceDet[] = {mediaName, issueName, dispName, dispSubName, dimName, placement, amount };
                        String [] priceDet = (String [] )its.next();
                        Debug.print("===============================");
                        Debug.print("mediaName:" + priceDet[0]);
                        Debug.print("issueName:" + priceDet[1]);
                        Debug.print("dispName:" + priceDet[2]);
                        Debug.print("dispSubName:" + priceDet[3]);
                        Debug.print("dimName:" + priceDet[4]);
                        Debug.print("placement:" + priceDet[5]);
                        Debug.print("amount:" + priceDet[6]);
                        Debug.print("==============================="); 
                    }
               }
          // }
        }
        catch(Exception e) {
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
