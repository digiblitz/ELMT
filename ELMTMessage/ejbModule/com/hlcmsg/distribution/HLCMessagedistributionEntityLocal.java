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
package com.hlcmsg.distribution;

import javax.ejb.EJBLocalObject;


/**
 * This is the local interface for MessagedistributionEntity enterprise bean.
 */
public interface HLCMessagedistributionEntityLocal extends EJBLocalObject, HLCMessagedistributionEntityLocalBusiness {
    
    public void setListId(String listId);
    public void setListName(String listName);
    public void setContactlistId(String contactlistId);
    public void setEmailId(String emailId);
    //public void setRegistrationEmailId(String registrationEmailId){ this.registrationEmailId = registrationEmailId; }
   // public void setActiveStatus(String activeStatus){ this.activeStatus =activeStatus ; }
    public void setAddDate(String addDate);
    
}
