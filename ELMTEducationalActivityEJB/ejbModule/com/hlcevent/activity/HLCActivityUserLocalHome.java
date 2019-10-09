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
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;
import javax.ejb.CreateException;
import java.util.*;

/**
 * This is the local-home interface for ActivityUser enterprise bean.
 */

public interface HLCActivityUserLocalHome extends EJBLocalHome {
    public HLCActivityUserLocal create(HLCActivityUserVO objActivityUser) throws CreateException;
    public HLCActivityUserLocal findByPrimaryKey(String releaseId) throws FinderException;
    public Collection findByAll() throws FinderException;
    public Collection findByActivityUserRequestStatus(String activityMeetingId, String requestStatus) throws FinderException;
    public Collection findByUserId(String userId) throws FinderException;
  //  public Collection findByEventLevelId(String eventLevelId) throws FinderException;
 //   public Collection findByActivityDateBefore(Date activityDate) throws FinderException;
 //   public Collection findByActivityDateAfter(Date activityDate) throws FinderException;
  //  public Collection findByRecordsFromToActivityDate(String activityMeetingId, Date fromActivityDate,Date toActivityDate) throws FinderException;
   // public Collection findByActivityOrganizerId(String activityOrganizerId) throws FinderException;    
}
