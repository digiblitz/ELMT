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
 * This is the local interface for DimensionDetail enterprise bean.
 */
public interface HLCDimensionDetailLocal extends EJBLocalObject, HLCDimensionDetailLocalBusiness {
    public Collection getDimDetails();
    public void setDimensionId(String dimensionId);
    public void setDisplaySubTypeId(String displaySubTypeId);
    public void setDimensionTypeId(String dimensionTypeId);
    public void setMediaId(String mediaId);
    public void setDimensionName(String dimensionName);
    public void setHeight(String height);
    public void setWidth(String width);
    public void setUnits(String units);
    public void setImagePath(String imagePath);
    public String getDimensionId();
    public String getDisplaySubTypeId();
    public String getDimensionTypeId();
    public String getMediaId();
    public String getDimensionName();
    public String getHeight();
    public String getWidth();
    public String getUnits();
    public String getImagePath();
    
    
}
