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

import javax.ejb.EJBLocalObject;


/**
 * This is the local interface for Publication enterprise bean.
 */
public interface HLCPublicationLocal extends EJBLocalObject, HLCPublicationLocalBusiness {
        public void setPublicationId(String publicationId);
        public void setActivityMeetingId(String activityMeetingId);
        public void setPublicationEmail(String publicationEmail);
        public void setMailingFormat(String mailingFormat);
        public void setMailingBy(String mailingBy);
        public void setMailingSortBy(String mailingSortBy);
        public void setNoOfCopies(String noOfCopies);
        public void setMailListValues(String mailListValues);
        public String getPublicationId();
        public String getActivityMeetingId();
        public String getPublicationEmail();
        public String getMailingFormat();
        public String getMailingBy();
        public String getMailingSortBy();
        public String getNoOfCopies();
        public String getMailListValues();
}
