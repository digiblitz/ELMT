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
package com.hlcHorse.Form.Display;

import javax.ejb.EJBObject;
import java.rmi.RemoteException;
import java.util.*;


/**
 * This is the remote interface for kaverySessionBeanStatless enterprise bean.
 */
public interface HLCkaverySessionBeanStatlessRemote extends EJBObject, HLCkaverySessionBeanStatlessRemoteBusiness {
    
     public Vector displayMembershipType(String h_name) throws RemoteException;
     //punitha comments
     public ArrayList getMapDetForMemberAndEventLevel(String membershipTypeId) throws RemoteException;
     public boolean generateMappingMemWithEventLevel(String membershipTypeId,String eventTypeId, ArrayList eventLevelList) throws RemoteException;
     public ArrayList displayAllMembershipType() throws RemoteException;
     public ArrayList displayAllCompResultDetails() throws RemoteException;
     //end
     public Vector displayHorseServiceType(String h_name) throws RemoteException;
     public Vector displayHorsePhoneServiceType(String h_name) throws RemoteException;
    
    public Vector areUAMember() throws RemoteException;
    
    public Vector membershipTo() throws RemoteException;
    
    public String[] getFamilyAddOnPrice(String memberTypeName, int currentYear)throws RemoteException;
    public String[] getFamilyAddOn(String memberTypeName) throws RemoteException;
    
    //suresh here
    public ArrayList getMemberNonUseaDetails(String memberId)throws RemoteException;
    public ArrayList getMemberDonationDetails(String userId)throws RemoteException;
     public Hashtable getSearchHorseDetails(String horseName, String regName, String baRegName, String baPastName , 
                                String prevRiderMemberId, String paramYearFoaled, String paramBreed, String paramSire, String paramDam ) throws RemoteException;
    public String getCountryName(String userId)throws RemoteException;
    public String getCountryPrice(String memTypeId, String countryName) throws RemoteException;
    //public ArrayList searchHorse(String hrMemberId, String hrName, String ownName, String riderName) throws RemoteException;
    public ArrayList searchHorse(String hrMemberId, String hrName, String ownName, String riderName , String ownLastName , String riderLastName) throws RemoteException;    
    public boolean generateMappingEventTypeWithResultFields(String eventTypeId, ArrayList compResultFieldList) throws RemoteException;
    public ArrayList getMappingDetailsForEventTypeAndCompResult(String  eventTypeId)  throws RemoteException;
    
    public boolean generateMappingEventTypeWithEventLevels(String eventTypeId, ArrayList eventLevelList) throws RemoteException;
    public ArrayList getMappingDetailsForEventTypeAndEventLevels(String  eventTypeId)  throws RemoteException;
    public Vector getMembershipTypesOnCurYr(String userTypeName) throws RemoteException;
    
    public ArrayList getWebSiteDonationDetails(String donationPaymentId)throws RemoteException;
    
    public String getMemberStatus(String memberId)throws RemoteException;
            
}
