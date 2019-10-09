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
package com.hlchorse.member.status;

import javax.ejb.EJBLocalObject;


/**
 * This is the local interface for ArabianSeaHorseMembershipStatus enterprise bean.
 */
public interface HLCArabianSeaHorseMembershipStatusLocal extends EJBLocalObject, HLCArabianSeaHorseMembershipStatusLocalBusiness {
    
    public void setStatusId(String statusId);
    public void setStatusName(String statusName);
    public void setDescription(String Description);
    
    public String getStatusId();
    public String getStatusName();
    public String getDescription();
}
