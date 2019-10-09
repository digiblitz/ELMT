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
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;
import javax.ejb.CreateException;
import java.util.*;


/**
 * This is the local-home interface for OrganizerRecap enterprise bean.
 */
public interface HLCOrganizerRecapLocalHome extends EJBLocalHome {
    
    public HLCOrganizerRecapLocal findByPrimaryKey(String key)  throws FinderException;
    public HLCOrganizerRecapLocal create(HLCOraganizerRecapVO objOrgRecap) throws CreateException;

    public ArrayList findByAll() throws FinderException;
    public ArrayList findByActivityMeetingId(String activityMeetingId) throws FinderException;
    public ArrayList findByOrganizerId(String activityOrganizerId) throws FinderException;
    
}
