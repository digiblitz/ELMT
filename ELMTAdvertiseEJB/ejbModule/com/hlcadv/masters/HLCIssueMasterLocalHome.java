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
import java.util.*;


/**
 * This is the local-home interface for IssueMaster enterprise bean.
 */
public interface HLCIssueMasterLocalHome extends EJBLocalHome {
    public HLCIssueMasterLocal findByPrimaryKey(String issueId)  throws FinderException;
    public HLCIssueMasterLocal create(String issueName, String mediaId) throws CreateException;
    public Collection findByAll() throws FinderException;
    public Collection findByIssueName(String issueName) throws FinderException;
    public Collection findByIssueEditName(String issueId, String issueName) throws FinderException;
    public Collection findByMediaId(String mediaId) throws FinderException;
}
