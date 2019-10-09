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
package com.hlcadv.masters;

import javax.ejb.EJBLocalObject;
import java.util.*;



/**
 * This is the local interface for IssueMaster enterprise bean.
 */
public interface HLCIssueMasterLocal extends EJBLocalObject, HLCIssueMasterLocalBusiness {
    public Collection getIssue();
    public void setIssueId(String issueId);
    public void setIssueName(String issueName);
    public void setMediaId(String mediaId);
    public void setActiveStatus(boolean activeStatus);
    public String getIssueId();
    public String getIssueName();
    public String getMediaId();
    public boolean isActiveStatus();
}
