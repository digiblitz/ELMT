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
package com.hlcspnr.plan;

import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;
import javax.ejb.*;
import java.util.*;


/**
 * This is the local-home interface for SponsorPlan enterprise bean.
 */

public interface HLCSponsorPlanLocalHome extends EJBLocalHome {
   public HLCSponsorPlanLocal findByPrimaryKey(String planId)  throws FinderException;
   public HLCSponsorPlanLocal create(String planName , String planDescription ,String planAmount) throws CreateException;
   public Collection findByPlanId(String planId) throws FinderException;
   public Collection findByAll() throws FinderException;
   public Collection findByPlanName(String planName) throws FinderException;
   public Collection findByEditPlanName(String planId, String planName) throws FinderException;
}
