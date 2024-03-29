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

import com.hlcmro.util.HLCEventDetailsVO;
import com.hlcmro.util.HLCOtherDetailsVO;
import javax.ejb.EJBLocalObject;
import java.util.*;

/**
 * This is the local interface for MetturDamEntity enterprise bean.
 */
public interface HLCMetturDamEntityLocal extends EJBLocalObject, HLCMetturDamEntityLocalBusiness {
    
    public HLCEventDetailsVO getEventDetails();
    //Setters methods
    
    public void setOrganizeId(String organizeId);
    public void setEventTitle(String eventTitle); 
    public void setEventSecretaryId(String eventSecretaryId); 

    public void setEntryFee(String entryFee);

    public void setOtherEntryFee(String otherEntryFee);
    public void setMembershipApplicable(String membershipApplicable);

    public void setDoubleEntryFee(String doubleEntryFee); 
    
    public void  setOfficeFee(String officeFee);

    public void setCheckPayableTo(String checkPayableTo);

    public void setPinniesCharge(String pinniesCharge);

    public void setPinniesPosition(String pinniesPosition);

    public void setAwardTrophy(String awardTrophy);

    public void setAwardPrize(String awardPrize);

    public void setAwardOthers(String awardOthers);

    public void setDateAvailable(String dateAvailable);

    public void setAvailableFrom(String availableFrom); 

    public void setAvailableFromOther(String availableFromOther);

    public void setAvailablePosition(String availablePosition);

    /*==============Stabling Details=================*/
    public void setStablingLimited(String stablingLimited);

    public void setStallPernightPrice(String stallPernightPrice);

    public void setPerStallPrice(String perStallPrice);

    public void setPerStallFromTime(String perStallFromTime);

    public void setPerStallFromDate(String perStallFromDate);

    public void setPerStallToTime(String perStallToTime); 

    public void setPerStallToDate(String perStallToDate); 

    public void setNoOfStalls(String noOfStalls); 

    public void setNoOfTempStalls(String noOfTempStalls);

    public void setNoOfTempPermanentStalls(String noOfTempPermanentStalls);

    public void setMilesFromEvent(String milesFromEvent); 

    /*==============VETERINARIAN Details=================*/
    public void setVeterinarianName(String veterinarianName);

    public void setVeterinarianPhone(String veterinarianPhone);
    public void setVeterinarianPlace(String veterinarianPlace);
    /*==============Accomodation Details=================*/
    public void setAccomodationCamping(String accomodationCamping);
    /*==============Direction Details=================*/
    public void setDirections(String directions);
     
    public void setFootingDesc(String footingDesc);
    public void setAddDate(Date addDate);
    
    public void setStatusId(String statusId);
    
    public void setComments(String comments);

//Other Table setter and getter.
    /*================ Division Methods =====================*/
    public Vector getDivisionLevels();
    public void setDivisionLevels(Vector divisionLevels);
    /*================ Judges Methods =====================*/
    public Vector getJudgesDetails();
    public void setJudgesDetails(Vector judgesDetails);
    /*================ Refund Rule Methods =====================*/
    public Vector getRefundRuleDetails();
    public void setRefundRuleDetails(Vector refundRuleDetails);
   /*================ Coggins Methods =====================*/
    public Vector getCogginsDetails();
    public void setCogginsDetails(Vector cogginsDetails);
    
   /*================ Tentative Time Methods =====================*/
    public Vector getTentativeTime();
    public void setTentativeTime(Vector tentativeTime);
    
    /*================ Horse Dressing Methods =====================*/
    public Vector getHorseDressingDetails();
    public void setHorseDressingDetails(Vector hDressage);
    
    /*================ Cross Country Details Methods =====================*/
    public Vector getCrossCountryDetails();
    public void setCrossCountryDetails(Vector crossCountry);
    
    /*================ Cross Country Details Methods =====================*/
    public HLCOtherDetailsVO getOtherDetails();
    public void setOtherDetails(HLCOtherDetailsVO othersDet);
    
    /*================ Accommdation Details Methods =====================*/
    public Vector getAccommodationDetails();
    public void setAccommodationDetails(Vector accommodation);
    
    
    
    
}
