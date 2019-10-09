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
 * This is the local interface for HorseDressage enterprise bean.
 */
public interface HLCHorseDressageLocal extends EJBLocalObject, HLCHorseDressageLocalBusiness {
    // getters
    public String getDressageId();
    public String getEventId();
    public String getDressageMapId();
    public String getArenaSizeId();
    //setter
    public void setDressageId(String dressageId);
    public void setEventId(String eventId);
    public void setDressageMapId(String dressageMapId);
    public void setArenaSizeId(String arenaSizeId);
}
