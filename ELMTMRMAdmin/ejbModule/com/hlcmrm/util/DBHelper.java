/*
 * DBHelper.java
 *
 * Created on August 28, 2006, 6:33 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmrm.util;

/**
 *
 * @author harmohan
 */
public class DBHelper {
    public static final String USEA_MEMBER_STATUS_MASTER = "tblMembershipStatusMaster"; 
    public static final String USEA_MEMBER_TYPE_MASTER = "tblMembershipTypeMaster";
    public static final String USEA_HORSE_SERVICETYPE_MASTER = "tblHorseServiceTypeMaster"; 
    public static final String USEA_COUNTRY_MAIL_PRICEMASTER = "tblCountryMailPriceMaster"; 
    public static final String USEA_CONTACT_TYPEMASTER = "tblContactTypeMaster"; 
    public static final String NON_USEA_ORG_MASTER= "tblNonUSEAOrgMaster";
    public static final String USEA_PUBLICATION_DETAILS ="tblMemberSubscription";
    
    public static final String USEA_DONATION_DETAILS= "tblDonationDetails";
    
    public static final String USEA_HORSE_COLOR= "tblColorMaster";
    public static final String USEA_HORSE_BREED= "tblBreedMaster";
    public static String USEA_USERTYPE_MASTER=  "tblUserTypeMaster";
     public static final String USEA_USER_MASTER= "tblUserMaster";
                      
  /* <!--Start:[DemographicDetails] For Demographic Type Addition, Editing and Deletion changes dated 11-Apr-2011 by Deepa-->*/
   
       public static final String USEA_Demo_MASTER="tblDemographicHuman";
       public static final String USEA_DemoType_MASTER="tblTypeMaster";
/*	<!--End:[DemographicDetails] For Demographic Type Addition, Editing and Deletion changes dated 11-Apr-2011 by Deepa-->*/
// Starts:[MemMgnt] For Breed Characteristic Detail Management Dated:21-4-2011
      public static String USEA_USEA_CHARACTERISTIC_MASTER=  "tblSpeciesCharacteristicMaster";
     public static String USEA_USEA_CHARACTERISTIC_DETAILS_MASTER=  "tblSpeciesCharacteristicDetailsMaster";
    // Ends:[MemMgnt] For Breed Characteristic Detail Management Dated:21-4-2011



     //=====Dhivya Here: Demographic Category==================
    public static final String HLC_TYPE_MASTER= "tblTypeMaster";
    public static final String HLC_DEMOGRAPHIC_NON_HUMAN= "tblDemographicNonHumanDetails";
    public static final String HLC_USER_TYPE_MASTER= "tblUserTypeMaster";
   

 //=====Dhivya Here: Breed Characteristics [Category and Details Management])========
    public static final String HLC_SPECIES_CHARACTERISTICS_MASTER= "tblSpeciesCharacteristicMaster";


    /** Creates a new instance of DBHelper */
    public DBHelper() {
    }
    
}
