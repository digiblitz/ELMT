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
package com.hlcmro.masters;

import javax.ejb.EJBLocalObject;


/**
 * This is the local interface for CrossCountry enterprise bean.
 */
public interface HLCCrossCountryLocal extends EJBLocalObject, HLCCrossCountryLocalBusiness {
    public String getCrossCountryId();
    public String getEventId();
    public String getDivision();
    public String getLength();
    public String getSpeed();
    public String getCourseDescription();
    public String getAddInformation();

    //Setters methods
    public void setCrossCountryId(String crossCountryId);
    public void setEventId(String eventId);
    public void setDivision(String division);
    public void setLength(String length);
    public void setSpeed(String speed);
    public void setCourseDescription(String courseDescription);
    public void setAddInformation(String addInformation);
    
    
}
