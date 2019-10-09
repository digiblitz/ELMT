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
package com.hlcmsg.distribution;

import com.hlcmsg.util.HLCMsgDistributionListMaster;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;
import javax.ejb.CreateException;
import java.util.*;

/**
 * This is the local-home interface for MessagedistributionEntity enterprise bean.
 */
public interface HLCMessagedistributionEntityLocalHome extends EJBLocalHome {
    
    public HLCMessagedistributionEntityLocal findByPrimaryKey(String key)  throws FinderException;
    
    public HLCMessagedistributionEntityLocal create(HLCMsgDistributionListMaster objMsgDist) throws CreateException;
    
    public Collection findByDistribitionListName(String listName) throws FinderException;
    
    
}
