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
package com.hlcmeeting.ICP;

import com.hlcmeeting.util.HLCInstructorDetails;
import com.hlcmeeting.util.HLCInstructorPriceMaster;
import com.hlcmeeting.util.HLCPaymentDetails;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;
import javax.ejb.CreateException;
import java.util.*;

/**
 * This is the local-home interface for MullaiyarDamICPEntity enterprise bean.
 */
public interface HLCMullaiyarDamICPEntityLocalHome extends EJBLocalHome {
    
    public HLCMullaiyarDamICPEntityLocal create(HLCInstructorDetails objInstDetail,  HLCPaymentDetails objPayment)
        throws CreateException;
    
    public HLCMullaiyarDamICPEntityLocal create( HLCInstructorPriceMaster objInstPriceMas)
        throws CreateException;
    
    //HLCInstructorDetails objInstDetail,  HLCPaymentDetails objPayment
    
    public HLCMullaiyarDamICPEntityLocal findByPrimaryKey(String key)  throws FinderException;
    
    public Collection findByMeetingId(String icpMeetingId) throws FinderException;
    
    
}
