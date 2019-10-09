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

import com.hlccommon.util.HLCActivityOrganizerVO;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;
import javax.ejb.CreateException;
import java.util.*;

/**
 * This is the local-home interface for ActivityOrganizer enterprise bean.
 */
public interface HLCActivityOrganizerLocalHome extends EJBLocalHome {
    public HLCActivityOrganizerLocal create(HLCActivityOrganizerVO objActivityDet,Vector publication) throws CreateException;
    public HLCActivityOrganizerLocal findByPrimaryKey(String activityMeetingId) throws FinderException;
    public Collection findByAll() throws FinderException;
    public Collection findByActivityOrganizerRequestStatus(String requestStatus) throws FinderException;
    public Collection findByActivityOrganizerRequestStatusByOrganizer(String organizerId, String requestStatus) throws FinderException;
    public Collection findByActivityName(String activityName) throws FinderException;
    public Collection findByActivityDate(Date activityDate) throws FinderException;
    public Collection findByActivityOrganizerId(String activityOrganizerId) throws FinderException;
}
