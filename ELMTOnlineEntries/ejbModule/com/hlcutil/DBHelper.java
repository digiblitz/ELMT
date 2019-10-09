/*
 * DBHelper.java
 *
 * Created on October 29, 2007, 1:20 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcutil;

/**
 *
 * @author Dhivya
 */
public final class DBHelper {
    
    /** Creates a new instance of DBHelper */
    public DBHelper() {
    }
    public static final String dbName = "java:/ELMTMSSQLDS";
    public static final String OE_EVENT_DETAILS = "tblMeeEventDetails"; 
    public static final String OE_EVENT_RENEWAL_DETAILS = "tblMeeEventOrganizerRenewalDetails";
    public static final String OE_MAPSTATEZIP = "tblMeeMapStateZip"; 
    public static final String OE_CHAMP_DETAILS = "tblOEChampionshipDetails"; 
    public static final String OE_PROVISIONAL_CALENDAR = "tblOEProvisionalCalendar"; 
    public static final String OE_EVENT_LEVEL = "tblMeeMapEventLevel";
    public static final String OE_EVENT_TYPE_MASTER = "tblMeeEventTypeMaster";
    public static final String OE_EVENT_LEVEL_MASTER = "tblMeeEventLevelMaster";
    public static final String OE_EVENT_TYPE_DETAILS = "tblMeeEventTypeDetails";
    public static final String OE_AREA_MASTER = "tblMeeAreaMaster";
    public static final String OE_STATE_MASTER = "tblMeeStateMaster";
    public static final String OE_AREA_CHAIR = "tblMeeMapAreaChair";
    public static final String OE_MEE_COMP_QUALIFICATION_DETAILS = "tblMeeCompQualificationDetails";
    public static final String OE_USER_TYPE_MASTER = "tblUserTypeMaster";
    public static final String OE_EVENT_DIVISION_MASTER = "tblMeeEventDivisionMaster";
    public static final String OE_MEMBERSHIP_TYPE_MASTER = "tblMembershipTypeMaster";
     public static final String OE_MEE_MAP_CHAMPIONSHIPLEVEL = "tblMeeMapChampionshipLevel";
    public static final String OE_MEMBER_DETAILS = "tblMemberDetails";
    public static final String OE_CONTACT_DETAILS = "tblContactDetails";
    public static final String OE_USER_MASTER = "tblUserMaster";
    
    public static final String OE_HORSE_MEMBER_DETAILS = "tblHorseMemberDetails";
    public static final String OE_HORSE_REL_DETAILS = "tblHorseMemberRelationDetails";
    public static final String OE_HORSE_DESC = "tblHorseDescription";
    public static final String OE_COLOR_MASTER = "tblColorMaster";
    public static final String OE_BREED_MASTER = "tblBreedMaster";
    public static final String OE_PRICE_ITEM_MASTER = "tblOEPriceItemMaster";
    public static final String OE_COMP_RESULTS = "tblMeeCompResultDetails";
    public static final String OE_PRICE_MATRIX = "tblOEPriceMatrix";
    public static final String OE_FIXED_PRICE = "tblOEFixedPriceDetails";
    public static final String OE_COMP_REG_DETAILS = "tblMeeCompRegistrationDetails";
    
    public static final String USEA_COMP_RESULT_FILE_DETAILS = "tblMeeCompResultFileDetails";
    public static final String OE_PAYMENT = "tblOEPaymentDetails";
    public static final String OE_REGISTRATION_DETAILS = "tblMeeCompRegistrationDetails"; 
    public static final String OE_COMP_RES_FIELDS = "tblMeeMapCompResultFields";
    public static final String OE_COMP_RES_FIELDS_MASTER = "tblMeeCompResultFieldMaster";
    public static final String OE_MEE_COMP_RESULT_DETAILS = "tblMeeCompResultDetails"; 
   
     public static final java.sql.Date toSQLDate(java.util.Date inDate) {
        return new java.sql.Date(inDate.getTime());
    }
}
