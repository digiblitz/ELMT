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

import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;
import javax.ejb.CreateException;
import java.util.Collection;


/**
 * This is the local-home interface for CogginsDetail enterprise bean.
 */
public interface HLCCogginsDetailLocalHome extends EJBLocalHome {
    public HLCCogginsDetailLocal findByPrimaryKey(String cogginsId) throws FinderException;
    public HLCCogginsDetailLocal create(String eventId,String allState,
	        String inState,String outOfState,String noState,String others) throws CreateException;
    public Collection findByAll() throws FinderException;
    public Collection findByEventId(String eventId) throws FinderException; 
}
