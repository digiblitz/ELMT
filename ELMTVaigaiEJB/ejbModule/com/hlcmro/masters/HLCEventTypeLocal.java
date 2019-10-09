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
import java.util.*;


/**
 * This is the local interface for EventType enterprise bean.
 */
public interface HLCEventTypeLocal extends EJBLocalObject, HLCEventTypeLocalBusiness {
    
// getters
public String getEventDetailId();
public String getEventId();
public String getMapTypeId();

//Setters methods
public void setEventDetailId(String eventDetailId);
public void setEventId(String eventId);
public void setMapTypeId(String mapTypeId);

public void setEvTypeList(String [] evTypeList);
public String [] getEvTypeList();

}
