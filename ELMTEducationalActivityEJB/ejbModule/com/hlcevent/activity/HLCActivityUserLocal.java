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

import com.hlccommon.util.HLCActivityUserVO;
import javax.ejb.EJBLocalObject;
import java.util.*;

/**
 * This is the local interface for ActivityUser enterprise bean.
 */
public interface HLCActivityUserLocal extends EJBLocalObject, HLCActivityUserLocalBusiness {
    public void setReleaseId(String releaseId);
    public void setActivityMeetingId(String activityMeetingId);
    public void setUserId(String userId);
    public void setNoOfHorses(String noOfHorses);
    public void setEventLevelId(String eventLevelId);
    public void setMembershipStatus(boolean membershipStatus);
    public void setMemberId(String memberId);
    public void setAddDate(Date addDate);
    public void setActiveStatus(boolean activeStatus);
    public void setRequestStatus(String requestStatus);
    public String getReleaseId();
    public String getActivityMeetingId();
    public String getUserId();
    public String getNoOfHorses();
    public String getEventLevelId();
    public boolean isMembershipStatus();
    public String getMemberId();
    public Date getAddDate();
    public boolean isActiveStatus();
    public String getRequestStatus();
    public HLCActivityUserVO getActivityUserDetails();
}
