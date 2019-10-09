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
 * This is the local interface for DisplayTypeMaster enterprise bean.
 */
public interface HLCDisplayTypeMasterLocal extends EJBLocalObject, HLCDisplayTypeMasterLocalBusiness {
    public Collection getDisplayType();
    public void setDisplayTypeId(String displayTypeId);
    public void setDisplayTypeName(String displayTypeName);
    public void setMediaId(String mediaId);
    public void setDisplayTypeDescription(String displayTypeDescription);
    public void setActiveStatus(boolean activeStatus);
    
    public String getDisplayTypeId();
    public String getDisplayTypeName();
    public String getMediaId();
    public String getDisplayTypeDescription();
    public boolean isActiveStatus();
    
}
