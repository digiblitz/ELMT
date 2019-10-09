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
package com.hlcmro.org;

import com.hlcmro.exception.HLCMissingPrimaryKeyException;
import com.hlcmro.util.HLCEventDetailsVO;
import com.hlcmro.util.HLCOtherDetailsVO;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;
import javax.ejb.*;
import java.util.*;

/**
 * This is the local-home interface for MetturDamEntity enterprise bean.
 */
public interface HLCMetturDamEntityLocalHome extends EJBLocalHome {
   
   /* public MetturDamEntityLocal create(long eventId, String organizeId,String eventSecretaryId,
        String entryFee, String otherEntryFee, String doubleEntryFee, String checkPayableTo,
        String pinniesCharge, String pinniesPosition, String awardTrophy, String awardPrize,
        String awardOthers, String dateAvailable, String availableFrom, String availableFromOther,
        String availablePosition, String stablingLimited, String stallPernightPrice, String perStallPrice,
        String perStallFromTime, String perStallFromDate, String perStallToTime, String perStallToDate,
        String noOfStalls, String noOfTempStalls, String noOfTempPermanentStalls, String milesFromEvent,
        String veterinarianName, String veterinarianPhone, String veterinarianPlace, String accomodationCamping,
        String directions)
        throws CreateException, MissingPrimaryKeyException;
   
*/
    public HLCMetturDamEntityLocal create(HLCEventDetailsVO objEventDet, Vector accommodation, Vector crossCountry,
            Vector cogginsDetails, Vector divisionLevels, Vector hDressage, Vector judgesDetails,
            HLCOtherDetailsVO othersDet, Vector refundRuleDetails, Vector tentativeTime) throws CreateException,HLCMissingPrimaryKeyException;
    
    public HLCMetturDamEntityLocal findByPrimaryKey(String eventId) throws FinderException;
    public Collection findByAll() throws FinderException;
    public Collection findByAllByStautsId(String statusId) throws FinderException;
    public Collection findByOrganizerId(String organizerId) throws FinderException;
    

 //   public Collection findByEventtId(String eventId) throws FinderException;
 //   public Collection findByCoggins(long eventId) throws FinderException;
  //  public Collection findByJudges(long eventId) throws FinderException;
  //  public Collection findByFunds(long eventId) throws FinderException;
   // public Collection findByJudges(long eventId) throws FinderException;
   // public Collection findByJudges(long eventId) throws FinderException;

   // public Collection findAll(String eventId) throws FinderException;
}
