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
package com.hlcmember.type.master;

import com.hlcmember.type.util.HLCMembershipTypeMaster;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;
import javax.ejb.CreateException;
import java.util.*;

/**
 * This is the local-home interface for ArabianSeaMembershipTypeMaster enterprise bean.
 */
public interface HLCArabianSeaMembershipTypeMasterLocalHome extends EJBLocalHome {
    
    public HLCArabianSeaMembershipTypeMasterLocal create(HLCMembershipTypeMaster objMemTypeMaster) throws CreateException;
    public HLCArabianSeaMembershipTypeMasterLocal findByPrimaryKey(String membershipTypeId)  throws FinderException;
    public Collection findByMembershipTypeName(String membershipTypeName) throws FinderException;
    public Collection findByMembershipTypeDetails() throws FinderException;
    
    
}
