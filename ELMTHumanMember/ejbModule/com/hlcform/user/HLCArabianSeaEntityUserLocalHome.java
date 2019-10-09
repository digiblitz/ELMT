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
package com.hlcform.user;

import com.hlcform.util.HLCContactDetails;
import com.hlcform.util.HLCMemberDetails;
import com.hlcform.util.HLCPaymentDetails;
import com.hlcform.util.HLCUserMaster;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;
import javax.ejb.CreateException;
import java.util.*;

/**
 * This is the local-home interface for ArabianSeaEntityUser enterprise bean.
 */
public interface HLCArabianSeaEntityUserLocalHome extends EJBLocalHome {
    
    public HLCArabianSeaEntityUserLocal create(HLCUserMaster objUserMaster) throws CreateException;
    
    public HLCArabianSeaEntityUserLocal create(HLCContactDetails objContact) throws CreateException;
    
    public HLCArabianSeaEntityUserLocal create(HLCMemberDetails objMember, HLCPaymentDetails objPayment) throws CreateException;
    
    public HLCArabianSeaEntityUserLocal create(HLCPaymentDetails objPayment) throws CreateException;
    
    public HLCArabianSeaEntityUserLocal findByPrimaryKey(String key)  throws FinderException;
    
    public Collection findByContactTypeId(String contactType) throws FinderException;
    
    public Collection findByUserEmailId(String emailId) throws FinderException;
    
    public Collection findByUserLoginName(String loginName) throws FinderException;
    
    public Collection findByJuniorMemberEmailId(String emailId, String memberTypeId) throws FinderException;
    
    public Collection findByFamilyAddOnMemberID( String memberId) throws FinderException;
    
    public Collection findBySearchcondition(String firstName, String lastName, Date frmDate, Date toDate, String userTypeId) throws FinderException;
    
    public Collection findByLoginMailId(String emailId, String pass) throws FinderException;
    
   // public Collection findByMailId(String mailId) throws FinderException;

    
    
}
