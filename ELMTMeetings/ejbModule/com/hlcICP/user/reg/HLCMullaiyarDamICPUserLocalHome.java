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
package com.hlcICP.user.reg;

import com.hlcmeeting.util.HLCMeeICPUserDetails;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;
import javax.ejb.CreateException;

/**
 * This is the local-home interface for MullaiyarDamICPUser enterprise bean.
 */
public interface HLCMullaiyarDamICPUserLocalHome extends EJBLocalHome {
    
    public HLCMullaiyarDamICPUserLocal findByPrimaryKey(String key)  throws FinderException;
    
    public HLCMullaiyarDamICPUserLocal create(HLCMeeICPUserDetails objMICPUser)
        throws CreateException;
    
    
}
