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
package com.hlcreg.form;

import com.hlcreg.util.HLCHorseDescription;
import com.hlcreg.util.HLCHorseMemberDetails;
import com.hlcreg.util.HLCHorseServiceTypeDetails;
import com.hlcreg.util.HLCPaymentDetails;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;
import javax.ejb.CreateException;
import java.util.*;

/**
 * This is the local-home interface for ArabianSeaEntity enterprise bean.
 */
public interface HLCArabianSeaEntityLocalHome extends EJBLocalHome {
    
    public HLCArabianSeaEntityLocal create(HLCHorseMemberDetails objHorseMem, HLCHorseDescription objHorseDesc,  HLCPaymentDetails objPayment)
        throws CreateException;
    public HLCArabianSeaEntityLocal findByPrimaryKey(String horseId) throws FinderException;
    
    public Collection findByGenMemberID() throws FinderException;
    
    public Collection findByGenUserID() throws FinderException;
    
    public Collection findByMemberIdUserID(String memberId) throws FinderException;
    
    public HLCArabianSeaEntityLocal create(HLCHorseServiceTypeDetails objHorseService) throws CreateException;
    
    public HLCArabianSeaEntityLocal create(HLCPaymentDetails objPayment) throws CreateException;
    
   // public Collection ejbFindByHorseMemberId(String horseMemberId) throws FinderException;
    
    
}
