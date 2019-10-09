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

import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;
import java.util.List;
import java.util.Collection;
import javax.ejb.CreateException;


/**
 * This is the local-home interface for DimensionDetail enterprise bean.
 */
public interface HLCDimensionDetailLocalHome extends EJBLocalHome {

    public HLCDimensionDetailLocal findByPrimaryKey(String dimensionId)  throws FinderException;
    public HLCDimensionDetailLocal create(String displaySubTypeId , String dimensionTypeId, String mediaId, String dimensionName, String height,
             String width,  String units,  String imagePath) throws CreateException;
    public Collection findByAll() throws FinderException;
    public List findByMediaTypeAndDisplayType(String mediaId, String displayId) throws FinderException;
    
   // public Collection findByDimensionTypeName(String dimensionTypeName) throws FinderException; 
    
}
