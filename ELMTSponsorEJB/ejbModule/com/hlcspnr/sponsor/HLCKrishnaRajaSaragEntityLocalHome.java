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
package com.hlcspnr.sponsor;

import com.hlcspnr.util.HLCSponsorDetails;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;
import javax.ejb.*;
import java.util.*;


/**
 * This is the local-home interface for KrishnaRajaSaragEntity enterprise bean.
 */
public interface HLCKrishnaRajaSaragEntityLocalHome extends EJBLocalHome {
    public HLCKrishnaRajaSaragEntityLocal create(HLCSponsorDetails objSponsor)  throws CreateException;
    public HLCKrishnaRajaSaragEntityLocal findByPrimaryKey(String sponsorId)  throws FinderException;
    
    public Collection findBySponsorId(String sponsorId) throws FinderException;
    public Collection findByAll() throws FinderException;
    public Collection findByRequestStatus(String requestStatus) throws FinderException;
    public Collection findByPlanId(String planId) throws FinderException;
    public Collection findByCompanyName(String companyName) throws FinderException;
    public Collection findBySalesPerson(String salesPersonId) throws FinderException;
    public Collection findBySponsor(String userId) throws FinderException;
    public Collection findByDates(Date fromDate, Date toDate) throws FinderException;
    public Collection findByPendingRequestForAdmin() throws FinderException;
    public Collection findByAllRequestStatusSponsors(String salesPersonId, String requestStatus) throws FinderException;
}
