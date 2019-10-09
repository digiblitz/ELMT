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
import javax.ejb.CreateException;
import java.util.Collection;


/**
 * This is the local-home interface for MapPrice enterprise bean.
 */

public interface HLCMapPriceLocalHome extends EJBLocalHome {
    public HLCMapPriceLocal findByPrimaryKey(String advMapId)  throws FinderException;
    public HLCMapPriceLocal create(String displayTypeId , String displaySubTypeId, String issueId, String mediaId, String price) throws CreateException;
    public Collection findByAll() throws FinderException;
   // public Collection findByDimensionTypeName(String dimensionTypeName) throws FinderException; 
    public Collection findByMapDetails(String mediaId,String frequencyId,String displayTypeId, String displaySubTypeId) throws FinderException; 
    public Collection findByMapMediaDetails(String mediaId, String displayTypeId, String displaySubTypeId) throws FinderException;     
    
    
    
}
