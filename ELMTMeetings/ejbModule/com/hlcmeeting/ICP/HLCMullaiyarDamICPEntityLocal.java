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

import javax.ejb.EJBLocalObject;
import java.util.*;


/**
 * This is the local interface for MullaiyarDamICPEntity enterprise bean.
 */
public interface HLCMullaiyarDamICPEntityLocal extends EJBLocalObject, HLCMullaiyarDamICPEntityLocalBusiness {
    
    /**==================Setter Method for Instructor Details=======================*/
    public void setComments(String comments);
    public void setLandOwnerCountry(String landOwnerCountry);
    public void setNoOfDays(String noOfDays);
    public void setIcpMeetingId(String icpMeetingId);
    public void setAssesmentLevel(String assesmentLevel);
    public void setAssesmentDate(String assesmentDate);
    public void setUseaAreaId(String useaAreaId);
    public void setAreaName(String areaName);
    public void setLocation(String location);
    public void setInstructorId(String instructorId);
    public void setHostMemberId(String hostMemberId);
    public void setShippingTypeId(String shippingTypeId);
    public void setAssessor(String assessor);
    public void setFacilities(String facilities);
    public void setLandOwnerName(String landOwnerName);
    public void setLandOwnerBusinessName(String landOwnerBusinessName);
    public void setLandOwnerFax(String landOwnerFax);
    public void setLandOwnerEmail(String landOwnerEmail);
    public void setLandOwnerAddress(String landOwnerAddress);
    public void setLandOwnerCity(String landOwnerCity);
    public void setLandOwnerState(String landOwnerState);
    public void setLandOwnerZip(String landOwnerZip);
    public void setLandOwnerPhone(String landOwnerPhone);
    public void setAddDate(String addDate);
    public void setApprovedBy(String approvedBy);
    public void setApprovedDate(String approvedDate);
    public void setPostingType(String postingType);
    public void setRequestStatus(String requestStatus);
    
    /**================Setter Method For Instructor PriceMaster====================================*/
    public void setPriceId(String PriceId);
    public void setEventRegistrationTypeId(String EventRegistrationTypeId);
   // public void setNoOfDays(String noOfDays);
    public void setPrice(String price);
    public void setEventRegistrationTypeName(String eventRegistrationTypeName);
    
   /**==================Setter method for Payment Details============================*/
    public void setPaymentId(String paymentId);
    public void setUserId(String userId);
    public void setUserTypeId(String userTypeId);
    public void setCcName(String ccName);
    public void setCcType(String ccType);
    public void setCcNumber(long ccNumber);
    public void setCcExpMonth(int ccExpMonth);
    public void setCcExpYear(int ccExpYear);
    public void setCcCvvid(int ccCvvid);
    public void setBankName(String bankName);
    public void setCheckDate(Date checkDate);
    public void setCheckNumber(long checkNumber);
    public void setAmount(double amount);
    public void setPaymentDate(Date paymentDate);
    public void setPaymentStatus(String paymentStatus);
    public void setCheckName(String checkName);
    
}
