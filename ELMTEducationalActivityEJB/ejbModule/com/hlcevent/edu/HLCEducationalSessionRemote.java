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
package com.hlcevent.edu;

import com.hlccommon.util.HLCActivityOrganizerVO;
import com.hlccommon.util.HLCActivityUserVO;
import com.hlccommon.util.HLCOraganizerRecapVO;
import com.hlccommon.util.HLCUserSearchResultVO;
import javax.ejb.EJBObject;
import java.rmi.RemoteException;
import javax.ejb.FinderException;
import java.util.*;


/**
 * This is the remote interface for EducationalSession enterprise bean.
 */

public interface HLCEducationalSessionRemote extends EJBObject, HLCEducationalSessionRemoteBusiness {
    //=================================Activity Organizer Details===========================================//
    public boolean createActivityOrganizer(HLCActivityOrganizerVO objActivityDet,Vector publication) throws RemoteException;
    public boolean editActivityOrganizer(HLCActivityOrganizerVO objActivityDet,Vector publication) throws RemoteException;
    public boolean deleteActivityOrganizer(String activityMeetingId) throws RemoteException,FinderException;
    public ArrayList getActivityOraganizerMeeting(String activityMeetingId) throws RemoteException;
    public ArrayList getAllActivityOraganizerMeeting() throws RemoteException,FinderException;
    public boolean changeStatusOrganizer(String activityMeetingId, String requestStatus, String comments) throws RemoteException;
    public ArrayList getAllActivityByRequestStatus(String requestStatus) throws RemoteException;
    public ArrayList getAllActivityByRequestStatusByOrganizer(String organizerId , String requestStatus) throws RemoteException;
    public ArrayList getActivityTypeDetails() throws RemoteException,FinderException;
    public ArrayList getMyEducationalActivity(String activityOrganizerId) throws RemoteException;    
    
    //================================= User Activity  Details===========================================//
     public boolean createActivityUser(HLCActivityUserVO objActUser) throws RemoteException;
     public HLCActivityUserVO getActivityUserDetail(String releaseId) throws RemoteException;
     public boolean changeStatusForUser(String releaseId, String requestStatus) throws RemoteException;
     public ArrayList getAllActivityUserByRequestStatus(String activityMeetingId, String requestStatus) throws RemoteException;
     public ArrayList getUserMyEducationalActivity(String userId) throws RemoteException;
     //public ArrayList getUserContactDetails(String userId) throws RemoteException;
     //public ArrayList getUserContactDetailsForAdmin(String userId) throws RemoteException;
     public ArrayList getMemberContactDetails(String memberId) throws RemoteException; 
     public ArrayList getMemberContactDetailsForAnnualMeeting(String memberId) throws RemoteException;
     public ArrayList getAnnualUserContactDetails(String userId) throws RemoteException;  
     //public ArrayList getUserContactDetailsByUserCode(String userCode) throws RemoteException; 
     public ArrayList getAnnualUserContactDetailsByLoginName(String loginName) throws RemoteException;
     
     
//================================= User Activity  Details ===========================================//
    public boolean createRecap(HLCOraganizerRecapVO objOrgRecap) throws RemoteException;
    public boolean assignDatesForRecap(String recapId, Date reportDate, Date closeDate) throws RemoteException;
    public HLCOraganizerRecapVO getOrganizerRecapDetails(String recapId) throws RemoteException;
    public ArrayList getOrganizerRecapDetailsByOrganizerId(String activityOrganizerId) throws RemoteException;
    public ArrayList getOrganizerRecapDetailsByMeetingId(String activityMeetingId) throws RemoteException;
    public ArrayList getAllOrganizerRecapDetails() throws RemoteException;
    public String getActivityName(String meetingId) throws RemoteException;
    public ArrayList getAreaMaster() throws RemoteException;
    
    //public ArrayList searchUserByMemberId(String memberId) throws RemoteException ;
    //public ArrayList searchUserByLoginName(String loginName) throws RemoteException;
    //public ArrayList searchUserByGeneral(String firstName, String lastName, String email, String zip) throws RemoteException ;
    //public HLCUserSearchResultVO getUserDetailsByUserId(String userId) throws RemoteException;
    
     public String getNextId() throws RemoteException;
      public String getOrganiserByEventId(String eventID) throws RemoteException;
      public boolean updatePendingAmount(String userId, String paymentId, float pendingAmount) throws RemoteException;
     
}
