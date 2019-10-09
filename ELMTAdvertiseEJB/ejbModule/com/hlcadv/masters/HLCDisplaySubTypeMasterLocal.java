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
package com.hlcadv.masters;

import javax.ejb.EJBLocalObject;
import java.util.*;


/**
 * This is the local interface for DisplaySubTypeMaster enterprise bean.
 */
public interface HLCDisplaySubTypeMasterLocal extends EJBLocalObject, HLCDisplaySubTypeMasterLocalBusiness {
    public Collection getDisplaySubType();
    public void setDisplaySubTypeId(String displaySubTypeId);
    public void setDisplaySubTypeName(String displaySubTypeName);
    public void setDisplayTypeId(String displayTypeId);
    public void setDisplaySubTypeDescription(String displaySubTypeDescription);
    public void setActiveStatus(boolean activeStatus);
    
    public String getDisplaySubTypeId();
    public String getDisplaySubTypeName();
    public String getDisplayTypeId();
    public String getDisplaySubTypeDescription();
    public boolean isActiveStatus();
    
}
