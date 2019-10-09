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
package com.hlcentitybean.ujanidam;

import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;
import javax.ejb.CreateException;
import java.util.*;

/**
 * This is the local-home interface for UjaniDamEntity enterprise bean.
 */
public interface HLCUjaniDamEntityLocalHome extends EJBLocalHome {
    
    HLCUjaniDamEntityLocal findByPrimaryKey(String key)  throws FinderException;
    public HLCUjaniDamEntityLocal create(String newEventId,String organizerId,String eventTitle,Date beginDat,Date endDat,String areaId,
            String stateId,int compYear,String eventTypeId,String eventLevelNames,String oldEventId)  throws CreateException;
    public HLCUjaniDamEntityLocal create(String proCalId,String eventId,String eventTypeId,String eventLevelId)  throws CreateException; 
}
