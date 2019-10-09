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
package com.hlcmrm.admin;

import com.hlcmrm.util.HLCContactTypeMaster;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;
import javax.ejb.CreateException;
import java.util.*;

/**
 * This is the local-home interface for ArabianSeaAdmin enterprise bean.
 */
public interface HLCArabianSeaAdminLocalHome extends EJBLocalHome {
    
    public HLCArabianSeaAdminLocal findByPrimaryKey(String key)  throws FinderException;
    
    public HLCArabianSeaAdminLocal create(HLCContactTypeMaster objContactTypeMaster) throws CreateException;
    
    public Collection findByContactTypeName() throws FinderException;
    
   // public Collection findByContactTypeID(String contactTypeName)throws FinderException;
    public Collection findByContactTypeID(String contactTypeId,String contactTypeName)throws FinderException;
    
}
