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
package com.hlcmrm.refund;

import com.hlcmrm.util.HLCMembershipRefundDetails;
import com.hlcmrm.util.HLCMembershipRefundTypeDetails;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;
import javax.ejb.CreateException;
import java.rmi.RemoteException;
import java.util.*;

/**
 * This is the local-home interface for RefundDetails enterprise bean.
 */
public interface HLCRefundDetailsLocalHome extends EJBLocalHome {
    
    public HLCRefundDetailsLocal create(HLCMembershipRefundDetails objRefDetails)
        throws CreateException;
    public HLCRefundDetailsLocal create(HLCMembershipRefundTypeDetails objRefTypeDetails) 
        throws CreateException;
    
   public HLCRefundDetailsLocal findByPrimaryKey(String key)  throws FinderException;
   
   public Vector findByMemberId(String memberId)
       throws FinderException;
   
   public Collection findByRefundList() throws FinderException;
    
    
}
