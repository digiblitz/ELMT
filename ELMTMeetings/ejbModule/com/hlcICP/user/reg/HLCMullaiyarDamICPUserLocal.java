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
package com.hlcICP.user.reg;

import javax.ejb.EJBLocalObject;


/**
 * This is the local interface for MullaiyarDamICPUser enterprise bean.
 */
public interface HLCMullaiyarDamICPUserLocal extends EJBLocalObject, HLCMullaiyarDamICPUserLocalBusiness {
    
    public void setIcpMeetingId(String icpMeetingId);
    public void setIcpMeetingName(String icpMeetingName);
    public void setUserId(String userId);
    public void setMembershipStatus(String membershipStatus);
    public void setMemberId(String memberId);
    public void setRequestStatus(String requestStatus);
    public void setEmailId(String emailId);
    public void setReleaseId(String releaseId);
    public void setComments(String comments);
    
}
