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
package com.hlcevent.activity;

import com.hlccommon.util.HLCOraganizerRecapVO;
import javax.ejb.EJBLocalObject;
import java.util.*;

/**
 * This is the local interface for OrganizerRecap enterprise bean.
 */
public interface HLCOrganizerRecapLocal extends EJBLocalObject, HLCOrganizerRecapLocalBusiness {
    
    public void setRecapId(String recapId);
    public void setActivityMeetingId(String activityMeetingId);
    public void setActivityDateChangeStatus(boolean activityDateChangeStatus);
    public void setActivityOrganizerId(String activityOrganizerId);
    public void setNoOfRiders(int noOfRiders);
    public void setNoOfInstructors(int noOfInstructors);
    public void setNoOfCurrentMembers(int noOfCurrentMembers);
    public void setNoOfNewMembers(int noOfNewMembers);
    public void setNoOfRenewingMembers(int noOfRenewingMembers);
    public void setNoOfFullMembers(int noOfFullMembers);
    public void setNoOfJuniorMembers(int noOfJuniorMembers);
    public void setNoOfNonCompetingMembers(int noOfNonCompetingMembers);
    public void setActivityReportDate(Date activityReportDate);
    public void setTotalAmount(float totalAmount);
    public void setCloseDate(Date closeDate);
    public void setComments(String comments);
    public void setPublishComments(boolean publishComments);
    public void setSuggestions(String suggestions);
    public void setAddDate(Date addDate);
    public void setActiveStatus(boolean activeStatus);
    public void setRequestStatus(String requestStatus);
    
    //Getter
     
    public String getRecapId();
    public String getActivityMeetingId();
    public boolean isActivityDateChangeStatus();
    public String getActivityOrganizerId();
    public int getNoOfRiders();
    public int getNoOfInstructors();
    public int getNoOfCurrentMembers();
    public int getNoOfNewMembers();
    public int getNoOfRenewingMembers();
    public int getNoOfFullMembers();
    public int getNoOfJuniorMembers();
    public int getNoOfNonCompetingMembers();
    public Date getActivityReportDate();
    public float getTotalAmount();
    public Date getCloseDate();
    public String getComments();
    public boolean isPublishComments();
    public String getSuggestions();
    public Date getAddDate();
    public boolean isActiveStatus();
    public String getRequestStatus();
    
    public HLCOraganizerRecapVO getOraganizerRecapDetails();
}
