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

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;
import java.util.*;


/**
 * This is the local-home interface for Publication enterprise bean.
 */
public interface HLCPublicationLocalHome extends EJBLocalHome {
    
    public HLCPublicationLocal findByPrimaryKey(String publicationId)  throws FinderException;
    
    public HLCPublicationLocal create(String activityMeetingId , String publicationEmail,  String mailingFormat, 
          String mailingBy, String mailingSortBy, String noOfCopies, String mailListValues)  throws CreateException;
    public Collection findByActivityMeetingId(String activityMeetingId) throws FinderException;
    
}
