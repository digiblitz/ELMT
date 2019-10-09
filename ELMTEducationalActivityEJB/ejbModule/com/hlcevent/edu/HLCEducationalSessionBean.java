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
/*  Program Name    : EducationalSessionBean.java
 *  Created Date    : Sep 5, 2006 10:57:08 AM
 *  Author          : Suresh.K
 *  Version         : 1.20
 *  CopyrightInformation:
(c)2006,digiBlitz Technologies Inc/digiBlitz Technologies (P) Ltd. All rights reserved.
916 W. Broad Street Suite 205, FallsChurch, VA 22046.
This document is protected by copyright. No part of this document may be reproduced in any form by any means without
prior written authorization of Sun and its licensors. if any.
The information described in this document may be protected by one or more U.S.patents.foreign patents,or
pending applications.
 */
package com.hlcevent.edu;

import com.hlccommon.util.HLCActivityOrganizerVO;
import com.hlccommon.util.HLCActivityUserVO;
import com.hlccommon.util.Debug;
import com.hlccommon.util.HLCOraganizerRecapVO;
import com.hlccommon.util.HLCUserSearchResultVO;
import com.hlcevent.activity.*;
import com.hlcevent.activity.dao.*;
/*import com.hlcevent.activity.HLCActivityOrganizerLocal;
import com.hlcevent.activity.HLCActivityOrganizerLocalHome;
import com.hlcevent.activity.HLCActivityUserLocal;
import com.hlcevent.activity.HLCActivityUserLocalHome;
import com.hlcevent.activity.HLCOrganizerRecapLocal;
import com.hlcevent.activity.HLCOrganizerRecapLocalHome;
import com.hlcevent.activity.HLCPublicationLocal;
import com.hlcevent.activity.HLCPublicationLocalHome; */
import javax.ejb.*;
import java.sql.*;
import java.util.*;
import javax.naming.*;
import java.rmi.*;


import java.util.Date;

public class HLCEducationalSessionBean implements SessionBean, HLCEducationalSessionRemoteBusiness {

    private SessionContext context;
    private InitialContext ic;
    private HLCActivityOrganizerLocalHome objActOrgHome;
    private HLCActivityOrganizerLocal objActOrgRemote;
    private HLCPublicationLocalHome objPubHome;
    private HLCPublicationLocal objPubRemote;
    private HLCActivityUserLocalHome objUserHome;
    private HLCActivityUserLocal objUserRemote;
    private HLCOrganizerRecapLocalHome objRecapHome;
    private HLCOrganizerRecapLocal objRecapRemote;
    private String activityMeetingId;
    private String releaseId;
    private String recapId;
    private Connection con;

    // <editor-fold defaultstate="collapsed" desc="EJB infrastructure methods. Click the + sign on the left to edit the code.">
    // TODO Add code to acquire and use other enterprise resources (DataSource, JMS, enterprise bean, Web services)
    // TODO Add business methods or web service operations
    /**
     * @see javax.ejb.SessionBean#setSessionContext(javax.ejb.SessionContext)
     */
    public void setSessionContext(SessionContext aContext) {
        context = aContext;
        Debug.print("EducationalSessionBean setSessionContext");
    }

    /**
     * @see javax.ejb.SessionBean#ejbActivate()
     */
    public void ejbActivate() {
        Debug.print("EducationalSessionBean ejbActivate");
    }

    /**
     * @see javax.ejb.SessionBean#ejbPassivate()
     */
    public void ejbPassivate() {
        Debug.print("EducationalSessionBean ejbPassivate");
    }

    /**
     * @see javax.ejb.SessionBean#ejbRemove()
     */
    public void ejbRemove() {
        Debug.print("EducationalSessionBean ejbRemove");
    }
    // </editor-fold>
    public void ejbCreate() throws CreateException {
        Debug.print("EducationalSessionBean ejbCreate");
        try {
            InitialContext jndiContext = getInitialContext();
            Object objActOrg = jndiContext.lookup("HLCActivityOrganizerLocalHome");
            objActOrgHome = (HLCActivityOrganizerLocalHome) objActOrg;

            Object objPub = ic.lookup("HLCPublicationLocalHome");
            objPubHome = (HLCPublicationLocalHome) objPub;

            Object objUser = ic.lookup("HLCActivityUserLocalHome");
            objUserHome = (HLCActivityUserLocalHome) objUser;

            Object objRecap = ic.lookup("HLCOrganizerRecapLocalHome");
            objRecapHome = (HLCOrganizerRecapLocalHome) objRecap;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @Method Name    :createActivityOrganizer.
     * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
     * @param          :ActivityOrganizerVO objActivityDet,Vector publication.
     * @return         :boolean  value.
     * @throws         :RemoteException.
     */
    public boolean createActivityOrganizer(HLCActivityOrganizerVO objActivityDet, Vector publication) throws RemoteException {
        Debug.print("EducationalSessionBean createActivityOrganizer");
        boolean result = false;
        try {
            if (objActivityDet == null || publication == null) {
                result = false;
            } else {
                objActOrgRemote = objActOrgHome.create(objActivityDet, publication);
                result = true;
            }
        } catch (Exception exp) {
            result = false;
            exp.printStackTrace();
        } finally {
            return result;
        }
    }

    /**
     * @Method Name    :editActivityOrganizer.
     * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
     * @param          :ActivityOrganizerVO objActivityDet,Vector publication.
     * @return         :boolean  value.
     * @throws         :RemoteException.
     */
    public boolean editActivityOrganizer(HLCActivityOrganizerVO objActivityDet, Vector publication) throws RemoteException {
        Debug.print("EducationalSessionBean editActivityOrganizer");
        boolean result = false;
        try {
            if (objActivityDet.getActivityMeetingId() == null) {
                throw new EJBException("Activity MeetingId can't be empty");
            }
            if (activityMeetingExists(objActivityDet.getActivityMeetingId()) == false) {
                throw new EJBException("Activity MeetingId does not Exists" + objActivityDet.getActivityMeetingId());
            }
            //objActOrgRemote.setActivityMeetingId(objActivityDet.getActivityMeetingId());
            objActOrgRemote.setActivityName(objActivityDet.getActivityName());
            objActOrgRemote.setActivityDate(objActivityDet.getActivityDate());
            objActOrgRemote.setNoOfDays(objActivityDet.getNoOfDays());
            objActOrgRemote.setUseaAreaId(objActivityDet.getUseaAreaId());
            objActOrgRemote.setLocation(objActivityDet.getLocation());
            objActOrgRemote.setState(objActivityDet.getState());
            objActOrgRemote.setActivityOrganizerId(objActivityDet.getActivityOrganizerId());
            objActOrgRemote.setActivityTypeId(objActivityDet.getActivityTypeId());
            objActOrgRemote.setOtherActivityType(objActivityDet.getOtherActivityType());
            objActOrgRemote.setActivityFees(objActivityDet.getActivityFees());
            objActOrgRemote.setInstructorsCoaches(objActivityDet.getInstructorsCoaches());
            objActOrgRemote.setFacilities(objActivityDet.getFacilities());
            objActOrgRemote.setOtherFacilities(objActivityDet.getOtherFacilities());
            objActOrgRemote.setLandOwnerName(objActivityDet.getLandOwnerName());
            objActOrgRemote.setLandOwnerBusinessName(objActivityDet.getLandOwnerBusinessName());
            objActOrgRemote.setLandOwnerAddress(objActivityDet.getLandOwnerAddress());
            objActOrgRemote.setLandOwnerCity(objActivityDet.getLandOwnerCity());
            objActOrgRemote.setLandOwnerState(objActivityDet.getLandOwnerState());
            objActOrgRemote.setLandOwnerCountry(objActivityDet.getLandOwnerCountry());
            objActOrgRemote.setLandOwnerZip(objActivityDet.getLandOwnerZip());
            objActOrgRemote.setLandOwnerPhone(objActivityDet.getLandOwnerPhone());
            objActOrgRemote.setAdditionalSites(objActivityDet.isAdditionalSites());
            objActOrgRemote.setAdditionalSitesPath(objActivityDet.getAdditionalSitesPath());
            objActOrgRemote.setAddDate(objActivityDet.getAddDate());
            objActOrgRemote.setApprovedBy(objActivityDet.getApprovedBy());
            objActOrgRemote.setApprovedDate(objActivityDet.getApprovedDate());
            objActOrgRemote.setPostingType(objActivityDet.getPostingType());
            objActOrgRemote.setActiveStatus(objActivityDet.isActiveStatus());
            objActOrgRemote.setRequestStatus("Pending");
            //objActOrgRemote.setPublicationDetails(publication);
            editPublicationDetails(objActivityDet.getActivityMeetingId(), publication);
            result = true;
        } catch (Exception exp) {
            exp.printStackTrace();
        } finally {
            return result;
        }
    }

    /**
     * @Method Name    :editPublicationDetails.
     * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
     * @param          :String activityMeetingId,Vector publication.
     * @return         :void.
     * @throws         :None.
     */
    public void editPublicationDetails(String activityMeetingId, Vector publication) {
        Debug.print("EducationalSessionBean editPublicationDetails");
        try {
            if (publication != null && publication.size() != 0) {
                Enumeration pubEnum = publication.elements();

                String publicationEmail = "";
                String mailingFormat = "";
                String mailingBy = "";
                String mailingSortBy = "";
                String noOfCopies = "";
                String mailListValues = "";
                Debug.print("EducationalSessionBean editPublicationDetails:" + publication);
                if (pubEnum.hasMoreElements()) {
                    publicationEmail = (String) pubEnum.nextElement();
                    mailingFormat = (String) pubEnum.nextElement();
                    mailingBy = (String) pubEnum.nextElement();
                    mailingSortBy = (String) pubEnum.nextElement();
                    noOfCopies = (String) pubEnum.nextElement();
                    mailListValues = (String) pubEnum.nextElement();
                }
                ArrayList iName = (ArrayList) objPubHome.findByActivityMeetingId(activityMeetingId);
                Iterator e = iName.iterator();
                Debug.print("EducationalSessionBean editPublicationDetails iName:" + iName);
                if (e.hasNext()) {
                    HLCPublicationLocal localPubRemote = (HLCPublicationLocal) e.next();
                    localPubRemote.setPublicationEmail(publicationEmail);
                    localPubRemote.setMailingFormat(mailingFormat);
                    localPubRemote.setMailingBy(mailingBy);
                    localPubRemote.setMailingSortBy(mailingSortBy);
                    localPubRemote.setNoOfCopies(noOfCopies);
                    localPubRemote.setMailListValues(mailListValues);

                    Debug.print("EducationalSessionBean editPublicationDetails" + noOfCopies);
                }
            }
        } catch (Exception exp) {
            Debug.print("Exception in ActivityOrganizerBean editPublicationDetails" + exp.getMessage());
            exp.printStackTrace();
        }
    }

    /**
     * @Method Name    :changeStatusOrganizer.
     * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
     * @param          :String activityMeetingId, String requestStatus, String comments.
     * @return         :boolean  value.
     * @throws         :RemoteException.
     */
    public boolean changeStatusOrganizer(String activityMeetingId, String requestStatus, String comments) throws RemoteException {
        Debug.print("EducationalSessionBean editActivityOrganizer");
        boolean result = false;
        try {
            if (activityMeetingId == null) {
                throw new EJBException("Activity MeetingId can't be empty");
            }
            if (activityMeetingExists(activityMeetingId) == false) {
                throw new EJBException("Activity MeetingId does not Exists" + activityMeetingId);
            }
            //objActOrgRemote.setActivityMeetingId(objActivityDet.getActivityMeetingId());
            objActOrgRemote.setRequestStatus(requestStatus);
            objActOrgRemote.setComments(comments);
            result = true;
        } catch (Exception exp) {
            exp.printStackTrace();
        } finally {
            return result;
        }
    }

    /**
     * @Method Name    :deleteActivityOrganizer.
     * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
     * @param          :String activityMeetingId.
     * @return         :boolean  value.
     * @throws         :RemoteException, FinderException.
     */
    public boolean deleteActivityOrganizer(String activityMeetingId) throws RemoteException, FinderException {
        Debug.print("EducationalSessionBean deleteActivityOrganizer");
        boolean result = false;
        if (activityMeetingId == null) {
            throw new EJBException("ActivityMeetingId can't be empty");
        }
        if (activityMeetingExists(activityMeetingId) == false) {
            throw new EJBException("ActivityMeetingId Not Exists" + activityMeetingId);
        }
        try {
            objActOrgRemote.remove();
            result = true;
        } catch (Exception ex) {
            throw new EJBException("ActivityMeetingId: " + ex.getMessage());
        } finally {
            return result;
        }
    }

    /**
     * @Method Name    :activityMeetingExists.
     * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
     * @param          :String activityMeetingId.
     * @return         :boolean  value.
     * @throws         :None.
     */
    private boolean activityMeetingExists(String activityMeetingId) {
        Debug.print("EducationalSessionBean activityMeetingExists");
        if (activityMeetingId != this.activityMeetingId) {
            try {
                objActOrgRemote = objActOrgHome.findByPrimaryKey(activityMeetingId);
                this.activityMeetingId = activityMeetingId;
            } catch (Exception ex) {
                return false;
            }
        }
        Debug.print("Activity MeetingId :" + activityMeetingId);
        return true;
    }

    /**
     * @Method Name    :getActivityOraganizerMeeting.
     * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
     * @param          :String activityMeetingId.
     * @return         :ArrayList.
     * @throws         :RemoteException.
     */
    public ArrayList getActivityOraganizerMeeting(String activityMeetingId) throws RemoteException {
        Debug.print("EducationalSessionBean getActivityOraganizerMeeting");
        HLCActivityOrganizerVO objActivityDet;
        ArrayList publication;
        ArrayList result = new ArrayList();
        if (activityMeetingId == null) {
            throw new EJBException("null activityMeetingId");
        }
        if (activityMeetingExists(activityMeetingId) == false) {
            throw new EJBException("Exception :" + activityMeetingId);
        }
        result.add(objActOrgRemote.getActivityOrganizerDetails());
        result.add(objActOrgRemote.getPublicationDetails());
        return result;
    }

    /**
     * @Method Name    :getAllActivityByRequestStatus.
     * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
     * @param          :String requestStatus.
     * @return         :ArrayList.
     * @throws         :RemoteException.
     */
    public ArrayList getAllActivityByRequestStatus(String requestStatus) throws RemoteException {
        Debug.print("EducationalSessionBean getAllActivityByRequestStatus");
        ArrayList actLocalOrgList = new ArrayList();
        try {
            Collection result = objActOrgHome.findByActivityOrganizerRequestStatus(requestStatus);
            Iterator e = result.iterator();
            while (e.hasNext()) {
                HLCActivityOrganizerLocal objActOrglocalRemote = (HLCActivityOrganizerLocal) e.next();
                HLCActivityOrganizerVO objActivityDet = (HLCActivityOrganizerVO) objActOrglocalRemote.getActivityOrganizerDetails();
                Vector publication = objActOrglocalRemote.getPublicationDetails();
                ArrayList actOrgList = new ArrayList();
                actOrgList.add(objActivityDet);
                actOrgList.add(publication);
                actLocalOrgList.add(actOrgList);
            }
        } catch (Exception exp) {
            Debug.print("Exception in getAllActivityByRequestStatus:" + exp);
        }
        return actLocalOrgList;
    }

    /**
     * @Method Name    :getAllActivityByRequestStatusByOrganizer.
     * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
     * @param          :String organizerId , String requestStatus.
     * @return         :ArrayList.
     * @throws         :RemoteException.
     */
    public ArrayList getAllActivityByRequestStatusByOrganizer(String organizerId, String requestStatus) throws RemoteException {
        Debug.print("EducationalSessionBean getAllActivityByRequestStatusByOrganizer");
        ArrayList actLocalOrgList = new ArrayList();
        try {
            Collection result = objActOrgHome.findByActivityOrganizerRequestStatusByOrganizer(organizerId, requestStatus);
            Iterator e = result.iterator();
            while (e.hasNext()) {
                HLCActivityOrganizerLocal objActOrglocalRemote = (HLCActivityOrganizerLocal) e.next();
                HLCActivityOrganizerVO objActivityDet = (HLCActivityOrganizerVO) objActOrglocalRemote.getActivityOrganizerDetails();
                Vector publication = objActOrglocalRemote.getPublicationDetails();
                ArrayList actOrgList = new ArrayList();
                actOrgList.add(objActivityDet);
                actOrgList.add(publication);
                actLocalOrgList.add(actOrgList);
            }
        } catch (Exception exp) {
            Debug.print("Exception in getAllActivityByRequestStatusByOrganizer:" + exp);
        }
        return actLocalOrgList;
    }

    /**
     * @Method Name    :getMyEducationalActivity.
     * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
     * @param          :String activityOrganizerId.
     * @return         :ArrayList.
     * @throws         :RemoteException.
     */
    public ArrayList getMyEducationalActivity(String activityOrganizerId) throws RemoteException {
        Debug.print("EducationalSessionBean getAllActivityByRequestStatus");
        ArrayList actLocalOrgList = new ArrayList();
        try {
            Collection result = objActOrgHome.findByActivityOrganizerId(activityOrganizerId);
            Iterator e = result.iterator();
            while (e.hasNext()) {
                HLCActivityOrganizerLocal objActOrglocalRemote = (HLCActivityOrganizerLocal) e.next();
                HLCActivityOrganizerVO objActivityDet = (HLCActivityOrganizerVO) objActOrglocalRemote.getActivityOrganizerDetails();
                Vector publication = objActOrglocalRemote.getPublicationDetails();
                ArrayList actOrgList = new ArrayList();
                actOrgList.add(objActivityDet);
                actOrgList.add(publication);
                actLocalOrgList.add(actOrgList);
            }
        } catch (Exception exp) {
            Debug.print("Exception in getAllActivityByRequestStatus:" + exp);
        }
        return actLocalOrgList;
    }

    /**
     * @Method Name    :getAllActivityOraganizerMeeting.
     * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
     * @param          :None.
     * @return         :ArrayList.
     * @throws         :RemoteException.
     */
    public ArrayList getAllActivityOraganizerMeeting() throws RemoteException, FinderException {
        Debug.print("EducationalSessionBean getAllActivityOraganizerMeeting");
        Vector actOrgList = new Vector();
        ArrayList actLocalOrgList = new ArrayList();
        Collection result = objActOrgHome.findByAll();
        Debug.print("Result in getAllActivityOraganizerMeeting:" + result);
        Iterator e = result.iterator();
        while (e.hasNext()) {
            HLCActivityOrganizerLocal objActOrglocalRemote = (HLCActivityOrganizerLocal) e.next();
            Debug.print("Result in getAllActivityOraganizerMeeting:" + result);
            HLCActivityOrganizerVO objActivityDet = (HLCActivityOrganizerVO) objActOrglocalRemote.getActivityOrganizerDetails();
            Vector publication = objActOrglocalRemote.getPublicationDetails();
            ArrayList al = new ArrayList();
            al.add(objActivityDet);
            al.add(publication);
            actLocalOrgList.add(al);
        }
        return actLocalOrgList;
    }

    /**
     * @Method Name    :getActivityTypeDetails.
     * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
     * @param          :None.
     * @return         :ArrayList.
     * @throws         :RemoteException, FinderException.
     */
    public ArrayList getActivityTypeDetails() throws RemoteException, FinderException {
        Debug.print("EducationalSessionBean getActivityTypeDetails");
        ArrayList results = new HLCActivityOrganizerDAO().getActivityTypeMaster();
        return results;
    }

    /**
     * @Method Name    :createActivityUser.
     * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
     * @param          :ActivityUserVO objActUser.
     * @return         :boolean  value.
     * @throws         :RemoteException.
     */
    public boolean createActivityUser(HLCActivityUserVO objActUser) throws RemoteException {
        Debug.print("EducationalSessionBean createActivityUser");
        boolean result = false;
        try {
            if (objActUser == null) {
                result = false;
            } else {
                objUserRemote = objUserHome.create(objActUser);
                result = true;
            }
        } catch (Exception exp) {
            result = false;
            exp.printStackTrace();
        } finally {
            return result;
        }
    }

    /**
     * @Method Name    :changeStatusForUser.
     * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
     * @param          :tring releaseId, String requestStatus.
     * @return         :boolean  value.
     * @throws         :RemoteException.
     */
    public boolean changeStatusForUser(String releaseId, String requestStatus) throws RemoteException {
        Debug.print("EducationalSessionBean changeStatusForUser");
        boolean result = false;
        try {
            if (releaseId == null) {
                throw new EJBException("Activity User releaseId can't be empty");
            }
            if (activityUserExists(releaseId) == false) {
                throw new EJBException("Activity User does not Exists" + releaseId);
            }
            objUserRemote.setRequestStatus(requestStatus);
            result = true;
        } catch (Exception exp) {
            exp.printStackTrace();
        } finally {
            return result;
        }
    }

    /**
     * @Method Name    :getActivityUserDetail.
     * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
     * @param          :String releaseId.
     * @return         :boolean  value.
     * @throws         :RemoteException.
     */
    public HLCActivityUserVO getActivityUserDetail(String releaseId) throws RemoteException {
        Debug.print("EducationalSessionBean getActivityUserDetail");
        HLCActivityUserVO objActUser = null;
        ArrayList publication;
        ArrayList result = new ArrayList();
        if (releaseId == null) {
            throw new EJBException("null releaseId");
        }

        if (activityUserExists(releaseId) == false) {
            throw new EJBException("Exception :" + releaseId);
        } else {
            objActUser = objUserRemote.getActivityUserDetails();
            Debug.print("objActUser:" + objActUser);
        }
        return objActUser;
    }

    /**
     * @Method Name    :getUserMyEducationalActivity.
     * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
     * @param          :String userId.
     * @return         :ArrayList.
     * @throws         :RemoteException.
     */
    public ArrayList getUserMyEducationalActivity(String userId) throws RemoteException {
        Debug.print("EducationalSessionBean getUserMyEducationalActivity");
        ArrayList userActivityList = new ArrayList();
        try {
            userActivityList = new HLCActivityOrganizerDAO().getUserMyEducationalActivity(userId);
        } catch (Exception exp) {
            Debug.print("Exception in getUserMyEducationalActivity:" + exp);
        }
        return userActivityList;
    }

    /**
     * @Method Name    :activityUserExists.
     * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
     * @param          :String releaseId.
     * @return         :boolean value.
     * @throws         :RemoteException.
     */
    private boolean activityUserExists(String releaseId) {
        Debug.print("EducationalSessionBean activityUserExists");
        if (releaseId != this.releaseId) {
            try {
                objUserRemote = objUserHome.findByPrimaryKey(releaseId);
                this.releaseId = releaseId;
            } catch (Exception ex) {
                return false;
            }
        }
        Debug.print("Activity User Release ID :" + releaseId);
        return true;
    }

    /**
     * @Method Name    :getAllActivityUserByRequestStatus.
     * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
     * @param          :String activityMeetingId,  String requestStatus.
     * @return         :ArrayList.
     * @throws         :RemoteException.
     */
    public ArrayList getAllActivityUserByRequestStatus(String activityMeetingId, String requestStatus) throws RemoteException {
        Debug.print("EducationalSessionBean getAllActivityUserByRequestStatus");
        ArrayList actLocalOrgList = new ArrayList();
        try {
            Collection result = objUserHome.findByActivityUserRequestStatus(activityMeetingId, requestStatus);
            Iterator e = result.iterator();
            while (e.hasNext()) {
                HLCActivityUserLocal objUserLocalRemote = (HLCActivityUserLocal) e.next();
                HLCActivityUserVO objUserActDet = (HLCActivityUserVO) objUserLocalRemote.getActivityUserDetails();
                actLocalOrgList.add(objUserActDet);
            }
        } catch (Exception exp) {
            Debug.print("Exception in getAllActivityUserByRequestStatus:" + exp);
        }
        return actLocalOrgList;
    }

    /**
     * @Method Name    :createRecap.
     * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
     * @param          :OraganizerRecapVO objOrgRecap.
     * @return         :boolean  value.
     * @throws         :RemoteException.
     */
    public boolean createRecap(HLCOraganizerRecapVO objOrgRecap) throws RemoteException {
        Debug.print("EducationalSessionBean createRecap");
        boolean result = false;
        try {
            if (objOrgRecap == null) {
                result = false;
            } else {
                objRecapRemote = objRecapHome.create(objOrgRecap);
                result = true;
            }
        } catch (Exception exp) {
            result = false;
            exp.printStackTrace();
        } finally {
            return result;
        }
    }

    /**
     * @Method Name    :assignDatesForRecap.
     * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
     * @param          :String recapId, Date reportDate, Date closeDate.
     * @return         :boolean  value.
     * @throws         :RemoteException.
     */
    public boolean assignDatesForRecap(String recapId, Date reportDate, Date closeDate) throws RemoteException {
        Debug.print("EducationalSessionBean assignDatesForRecap");
        boolean result = false;
        if (recapId == null) {
            throw new EJBException("null releaseId");
        }

        if (activityRecapExists(recapId) == false) {
            Debug.print("Exception in assignDatesForRecap recapId doesn't exist:" + recapId);
        } else {
            objRecapRemote.setActivityReportDate(reportDate);
            objRecapRemote.setCloseDate(closeDate);
            objRecapRemote.setRequestStatus("Approved");
            result = true;
        }
        return result;
    }

    /**
     * @Method Name    :getOrganizerRecapDetails.
     * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
     * @param          :String recapId.
     * @return         :OraganizerRecapVO.
     * @throws         :RemoteException.
     */
    public HLCOraganizerRecapVO getOrganizerRecapDetails(String recapId) throws RemoteException {
        Debug.print("EducationalSessionBean getActivityUserDetail");
        HLCOraganizerRecapVO objOrgRecap = null;
        if (recapId == null) {
            throw new EJBException("null releaseId");
        }

        if (activityRecapExists(recapId) == false) {
            throw new EJBException("Exception :" + recapId);
        } else {
            objOrgRecap = objRecapRemote.getOraganizerRecapDetails();
        }
        return objOrgRecap;
    }

    /**
     * @Method Name    :getOrganizerRecapDetailsByMeetingId.
     * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
     * @param          :String activityMeetingId.
     * @return         :ArrayList.
     * @throws         :RemoteException.
     */
    public ArrayList getOrganizerRecapDetailsByMeetingId(String activityMeetingId) throws RemoteException {
        Debug.print("EducationalSessionBean getOrganizerRecapDetailsByMeetingId");
        ArrayList recapList = new ArrayList();
        try {
            Collection result = objRecapHome.findByActivityMeetingId(activityMeetingId);
            Iterator e = result.iterator();
            while (e.hasNext()) {
                HLCOrganizerRecapLocal objRecaplocalRemote = (HLCOrganizerRecapLocal) e.next();
                HLCOraganizerRecapVO objActRecapDet = (HLCOraganizerRecapVO) objRecaplocalRemote.getOraganizerRecapDetails();
                recapList.add(objActRecapDet);
            }
        } catch (Exception exp) {
            Debug.print("Exception in getOrganizerRecapDetailsByMeetingId:" + exp);
        }
        return recapList;
    }

    /**
     * @Method Name    :getAllOrganizerRecapDetails.
     * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
     * @param          :None.
     * @return         :ArrayList.
     * @throws         :RemoteException.
     */
    public ArrayList getAllOrganizerRecapDetails() throws RemoteException {
        Debug.print("EducationalSessionBean getAllOrganizerRecapDetails");
        ArrayList recapList = new ArrayList();
        try {
            Collection result = objRecapHome.findByAll();
            Iterator e = result.iterator();
            while (e.hasNext()) {
                HLCOrganizerRecapLocal objRecaplocalRemote = (HLCOrganizerRecapLocal) e.next();
                HLCOraganizerRecapVO objActRecapDet = (HLCOraganizerRecapVO) objRecaplocalRemote.getOraganizerRecapDetails();
                recapList.add(objActRecapDet);
            }
        } catch (Exception exp) {
            Debug.print("Exception in getAllOrganizerRecapDetails:" + exp);
        }
        return recapList;
    }

    /**
     * @Method Name    :getOrganizerRecapDetailsByOrganizerId.
     * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
     * @param          :String activityOrganizerId.
     * @return         :ArrayList.
     * @throws         :RemoteException.
     */
    public ArrayList getOrganizerRecapDetailsByOrganizerId(String activityOrganizerId) throws RemoteException {
        Debug.print("EducationalSessionBean getOrganizerRecapDetailsByOrganizerId:" + activityOrganizerId);
        ArrayList recapList = new ArrayList();
        try {
            Collection result = objRecapHome.findByOrganizerId(activityOrganizerId);
            Iterator e = result.iterator();
            while (e.hasNext()) {
                HLCOrganizerRecapLocal objRecaplocalRemote = (HLCOrganizerRecapLocal) e.next();
                HLCOraganizerRecapVO objActRecapDet = (HLCOraganizerRecapVO) objRecaplocalRemote.getOraganizerRecapDetails();
                recapList.add(objActRecapDet);
            }

        } catch (Exception exp) {
            Debug.print("Exception in getOrganizerRecapDetailsByOrganizerId:" + exp);
        }
        Debug.print("EducationalSessionBean getOrganizerRecapDetailsByOrganizerId: Size:" + recapList.size());
        return recapList;
    }

    /**
     * @Method Name    :activityRecapExists.
     * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
     * @param          :String recapId.
     * @return         :boolean value.
     * @throws         :None.
     */
    private boolean activityRecapExists(String recapId) {
        Debug.print("EducationalSessionBean activityRecapExists");
        if (recapId != this.recapId) {
            try {
                objRecapRemote = objRecapHome.findByPrimaryKey(recapId);
                this.recapId = recapId;
            } catch (Exception ex) {
                return false;
            }
        }
        Debug.print("Activity Recap  ID :" + releaseId);
        return true;
    }

    /**
     * @Method Name    :getUserContactDetails.
     * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
     * @param          :String userId.
     * @return         :ArrayList.
     * @throws         :RemoteException.
     */
    /* public ArrayList getUserContactDetails(String userId) throws RemoteException {
    Debug.print("EducationalSessionBean getUserContactDetails");
    ArrayList results = new HLCActivityOrganizerDAO().getUserContactDetails(userId);
    Debug.print("EducationalSessionBean getUserContactDetails Result :" + results);
    return results;
    }*/
    /**
     * @Method Name    :getUserContactDetails.
     * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
     * @param          :String userId.
     * @return         :ArrayList.
     * @throws         :RemoteException.
     */
    /* public ArrayList getUserContactDetailsForAdmin(String userId) throws RemoteException {
    Debug.print("EducationalSessionBean getUserContactDetailsForAdmin");
    ArrayList results = new HLCActivityOrganizerDAO().getUserContactDetailsForAdmin(userId);
    Debug.print("EducationalSessionBean getUserContactDetailsForAdmin Result :" + results);
    return results;
    }
     */
    /**
     * @Method Name    :getMemberContactDetails.
     * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
     * @param          :String memberId.
     * @return         :ArrayList.
     * @throws         :RemoteException.
     */
    public ArrayList getMemberContactDetails(String memberId) throws RemoteException {
        Debug.print("EducationalSessionBean getMemberContactDetails");
        ArrayList results = new HLCActivityOrganizerDAO().getMemberContactDetails(memberId);
         Debug.print("EducationalSessionBean getMemberContactDetails Result :" + results);
        return results;
    }
    
    /**
     * @Method Name    :getAnnualUserContactDetailsByLoginName.
     * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
     * @param          :String loginName.
     * @return         :ArrayList.
     * @throws         :RemoteException.
     */
    public ArrayList getAnnualUserContactDetailsByLoginName(String loginName) throws RemoteException {
        Debug.print("EducationalSessionBean getAnnualUserContactDetailsByLoginName");
        ArrayList results = new HLCActivityOrganizerDAO().getAnnualUserContactDetailsByLoginName(loginName);
        Debug.print("EducationalSessionBean getAnnualUserContactDetailsByLoginName Result :" + results);
        return results;
    }

    /**
     * @Method Name    :getMemberContactDetailsForAnnualMeeting.
     * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
     * @param          :String memberId.
     * @return         :ArrayList.
     * @throws         :RemoteException.
     */
    public ArrayList getMemberContactDetailsForAnnualMeeting(String memberId) throws RemoteException {
        Debug.print("EducationalSessionBean getMemberContactDetailsForAnnualMeeting");
        ArrayList results = new HLCActivityOrganizerDAO().getMemberContactDetailsForAnnualMeeting(memberId);
        Debug.print("EducationalSessionBean getMemberContactDetailsForAnnualMeeting Result :" + results);
        return results;
    }

    /**
     * @Method Name    :getAnnualUserContactDetails.
     * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
     * @param          :String userId.
     * @return         :ArrayList.
     * @throws         :RemoteException.
     */
    public ArrayList getAnnualUserContactDetails(String userId) throws RemoteException {
        Debug.print("EducationalSessionBean getAnnualUserContactDetails");
        ArrayList results = new HLCActivityOrganizerDAO().getAnnualUserContactDetails(userId);
        Debug.print("EducationalSessionBean getAnnualUserContactDetails Result :" + results);
        return results;
    }

    /**
     * @Method Name    :getActivityName.
     * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
     * @param          :String meetingId.
     * @return         :String.
     * @throws         :RemoteException.
     */
    public String getActivityName(String meetingId) throws RemoteException {
        Debug.print("EducationalSessionBean getMemberContactDetails");
        String activityName = new HLCActivityOrganizerDAO().getActivityName(meetingId);
        Debug.print("EducationalSessionBean getMemberContactDetails Result :" + activityName);
        return activityName;
    }

    /**
     * @Method Name    :getUserContactDetailsByUserCode.
     * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
     * @param          :String userCode.
     * @return         :ArrayList.
     * @throws         :RemoteException.
     */
    /* public ArrayList getUserContactDetailsByUserCode(String userCode) throws RemoteException {
    Debug.print("EducationalSessionBean getUserContactDetailsByUserCode");
    ArrayList results = new HLCActivityOrganizerDAO().getUserContactDetailsByUserCode(userCode);
    Debug.print("EducationalSessionBean getUserContactDetailsByUserCode Result :" + results);
    return results;
    }*/
    /**
     * @Method Name    :getAreaMaster.
     * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
     * @param          :None.
     * @return         :ArrayList.
     * @throws         :RemoteException.
     */
    public ArrayList getAreaMaster() throws RemoteException {
        Debug.print("EducationalSessionBean getAreaMaster");
        ArrayList areaMasterList = new HLCActivityOrganizerDAO().getAreaMaster();
        return areaMasterList;
    }

    /**
     * @Method Name    :searchUserByMemberId.
     * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
     * @param          :String memberId.
     * @return         :ArrayList.
     * @throws         :RemoteException.
     */
    /*  public ArrayList searchUserByMemberId(String memberId) throws RemoteException {
    Debug.print("EducationalSessionBean searchUserByMemberId() memberId:" + memberId);
    ArrayList memberList = new HLCActivityOrganizerDAO().selectMemberDetailsByMemberId(memberId);
    return memberList;
    }*/
    /**
     * @Method Name    :searchUserByLoginName.
     * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
     * @param          :String loginName.
     * @return         :ArrayList.
     * @throws         :RemoteException.
     */
    /*  public ArrayList searchUserByLoginName(String loginName) throws RemoteException {
    Debug.print("EducationalSessionBean searchUserByMemberId() loginName:" + loginName);
    ArrayList memberList = new HLCActivityOrganizerDAO().selectMemberDetailsByLoginName(loginName);
    return memberList;
    }*/
    /**
     * @Method Name    :getUserDetailsByUserId.
     * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
     * @param          :String userId.
     * @return         :UserSearchResultVO.
     * @throws         :RemoteException.
     */
    /*  public HLCUserSearchResultVO getUserDetailsByUserId(String userId) throws RemoteException {
    Debug.print("EducationalSessionBean getUserDetailsByUserId() userId:" + userId);
    HLCUserSearchResultVO memberList = new HLCActivityOrganizerDAO().selectMemberDetailsByUserId(userId);
    return memberList;
    }*/
    /**
     * @Method Name    :searchUserByGeneral.
     * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
     * @param          :String firstName, String lastName, String email, String zip.
     * @return         :ArrayList  value.
     * @throws         :RemoteException.
     */
    /*  public ArrayList searchUserByGeneral(String firstName, String lastName, String email, String zip) throws RemoteException {
    Debug.print("EducationalSessionBean searchUserByGeneral() firstName:" + firstName);
    ArrayList memberList = new HLCActivityOrganizerDAO().selectMemberDetailsByGeneralSearch( firstName, lastName, email, zip);
    return memberList;
    }*/
    /**
     * @Method Name    :getNextId.
     * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
     * @param          :None.
     * @return         :String  value.
     * @throws         :RemoteException.
     */
    public String getNextId() throws RemoteException {
        Debug.print("EducationalSessionBean Payment getNextId ");
        String nextPaymentId = "";
        try {
            nextPaymentId = new HLCActivityOrganizerDAO().getNextId();
        } catch (Exception e) {
            Debug.print("Exception in getNextId:" + e);
        }
        return nextPaymentId;
    }

    public String getOrganiserByEventId(String eventID) throws RemoteException {
        Debug.print("EducationalSessionBean Orgainser ID by  EventtId ");
        String event_id = null;
        try {
            if (eventID != null && eventID.trim().length() != 0) {
                Debug.print("EducationalSessionBean inside updateActivityOrganizerAdmin : " + eventID);
                event_id = new HLCActivityOrganizerDAO().getOrganiserByEventId(eventID);
            }
        } catch (Exception e) {
            Debug.print("Exception in updateActivityOrganizerAdmin:" + e);
        }
        Debug.print("EducationalSessionBean updateActivityOrganizerAdmin : " + event_id);
        return event_id;
    }
     public boolean updatePendingAmount(String userId, String paymentId, float pendingAmount) throws RemoteException{
        Debug.print("EducationalSessionBean updatePendingAmount paymentId: " + paymentId);
        boolean result  = false;
        try {
            if(paymentId!=null && paymentId.trim().length()!=0){
                Debug.print("EducationalSessionBean inside updatePendingAmount : " + paymentId);
                result = new HLCActivityOrganizerDAO().updatePendingAmount(userId, paymentId, pendingAmount);
            }
        }catch(Exception e){
            Debug.print("Exception in updatePendingAmount:" + e);
        }
        Debug.print("EducationalSessionBean updatePendingAmount : " + result);
        return result;
    }

    /**
     * @Method Name    :getInitialContext.
     * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
     * @param          :None.
     * @return         :InitialContext  value.
     * @throws         :RemoteException.
     */
    public InitialContext getInitialContext() throws javax.naming.NamingException {
        if (this.ic == null) {
            ic = new InitialContext();
        }
        System.out.println("Vaigai Bean: This is from getInitialContext()");
        return ic;
    }
}
