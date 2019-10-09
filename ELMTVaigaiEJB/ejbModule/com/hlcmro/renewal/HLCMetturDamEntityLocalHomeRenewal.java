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
package com.hlcmro.renewal;

import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;
import java.util.*;
import com.hlcmro.util.HLCPaymentDetails;
import com.hlcmro.util.HLCRenewalOrganizerDetails;
import javax.ejb.CreateException;


/**
 * This is the local-home interface for MetturDamEntity enterprise bean.
 */
public interface HLCMetturDamEntityLocalHomeRenewal extends EJBLocalHome {
    
    public HLCMetturDamEntityLocalRenewal findByPrimaryKey(String renewalId)  throws FinderException;

    public HLCMetturDamEntityLocalRenewal create(HLCRenewalOrganizerDetails objRenewalDet,HLCPaymentDetails objPayDet)  throws CreateException;
    
    public Collection findByEventId(String eventId) throws FinderException;
    public Collection findRenewalByStatus(String status) throws FinderException;
    public Collection findByPaymentOrganizerId(String organizerId) throws FinderException;
    public Collection findByCompititionName(String cmpName) throws FinderException;
    public Collection findByCompititionDate(java.util.Date cmpDate) throws FinderException;
   // public Collection findByRenewalFrmToDate(Date startDate,Date endDate) throws FinderException; 
}
