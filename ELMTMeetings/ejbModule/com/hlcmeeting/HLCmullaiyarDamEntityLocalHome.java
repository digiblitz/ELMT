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
package com.hlcmeeting;

import com.hlcmeeting.util.HLCMeeAnnualDetails;
import com.hlcmeeting.util.HLCMeeAnnualRegistrationDetails;
import com.hlcmeeting.util.HLCPaymentDetails;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;
import javax.ejb.CreateException;
import java.util.*;

/**
 * This is the local-home interface for mullaiyarDamEntity enterprise bean.
 */
public interface HLCmullaiyarDamEntityLocalHome extends EJBLocalHome {
    public HLCmullaiyarDamEntityLocal create(HLCMeeAnnualDetails objAnnaulDetails,  HLCPaymentDetails objPayment)
        throws CreateException;
    
    public HLCmullaiyarDamEntityLocal create(HLCMeeAnnualRegistrationDetails objAnnualReg) throws CreateException;
    
    public HLCmullaiyarDamEntityLocal findByPrimaryKey(String key)  throws FinderException;
    
    public Collection findByMemberId(String memberId) throws FinderException;
    
    public Collection findByUserTypeId(String memberId) throws FinderException;
    
    public Collection findByUserDetails(String userId) throws FinderException;
    
    public Collection findByMeetingID(String meetingId) throws FinderException;
    
    public Collection findDueDateLateDate(Date currentDate, String specificationId, String userTypeId) throws FinderException;
    
    public Collection findDueDatePrice(String specificationId, String userTypeId) throws FinderException;
    
    
}
