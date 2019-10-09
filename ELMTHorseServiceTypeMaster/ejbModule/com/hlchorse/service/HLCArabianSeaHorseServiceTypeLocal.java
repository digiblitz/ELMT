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
package com.hlchorse.service;

import javax.ejb.EJBLocalObject;


/**
 * This is the local interface for ArabianSeaHorseServiceType enterprise bean.
 */
public interface HLCArabianSeaHorseServiceTypeLocal extends EJBLocalObject, HLCArabianSeaHorseServiceTypeLocalBusiness {
    
    public void setHorseServiceTypeId(String horseServiceTypeId);
    public void setHorseServiceTypeName(String horseServiceTypeName);
    public void setHorseServiceTypeAmount(String horseServiceTypeAmount );
    public void setActiveStatus(int active_status);//12042011
    public void setUserTypeId(String user_type_id);//12042011
    
    public String getHorseServiceTypeId();
    public String getHorseServiceTypeName();
    public String getHorseServiceTypeAmount();
    public String getUserTypeId();//12042011
    public int getActiveStatus();//12042011

    
}
