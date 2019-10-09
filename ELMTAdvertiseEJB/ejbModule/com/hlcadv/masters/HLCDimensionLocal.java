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
import java.util.Collection;

/**
 * This is the local interface for Dimension enterprise bean.
 */
public interface HLCDimensionLocal extends EJBLocalObject, HLCDimensionLocalBusiness {
    public Collection getDimension();
    public void setDimensionTypeId(String dimensionTypeId);
    public void setDimensionTypeName(String dimensionTypeName);
    public void setActiveStatus(boolean activeStatus);
    public String getDimensionTypeId();
    public String getDimensionTypeName();
    public boolean isActiveStatus();
}
