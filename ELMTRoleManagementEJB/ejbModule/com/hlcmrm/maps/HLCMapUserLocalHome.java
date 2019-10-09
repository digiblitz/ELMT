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
package com.hlcmrm.maps;

import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;
import javax.ejb.CreateException;
import java.util.*;

/**
 * This is the local-home interface for MapUser enterprise bean.
 */
public interface HLCMapUserLocalHome extends EJBLocalHome {
    public HLCMapUserLocal findByPrimaryKey(String userMapId)  throws FinderException;
    public HLCMapUserLocal create(String userId, String mapPrivilegeId, String userPrivilegePath) throws CreateException;
    public Collection findByAll() throws FinderException;
    public Collection findByUserId(String userId) throws FinderException;
    public Collection findByMapPrivilegeId(String mapPrivilegeId) throws FinderException;
}
