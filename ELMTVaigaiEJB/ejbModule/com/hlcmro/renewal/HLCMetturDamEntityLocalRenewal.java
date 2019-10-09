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

import com.hlcmro.util.HLCPaymentDetails;
import com.hlcmro.util.HLCRenewalOrganizerDetails;
import javax.ejb.EJBLocalObject;

/**
 * This is the local interface for MetturDamEntity enterprise bean.
 */
public interface HLCMetturDamEntityLocalRenewal extends EJBLocalObject, HLCMetturDamEntityLocalBusinessRenewal {
    public HLCRenewalOrganizerDetails getRenewalDetails();
    public HLCPaymentDetails getPaymentDetails();
    public void setPaymentDetails(HLCPaymentDetails objPayDet);
    
    public void setRenewalId(String renewalId);
    public void setEventId(int eventId);
    public void setOrganizerId(String organizerId);
    public void setCompetitionName(String competitionName);
    public void setCompetitionDate(java.util.Date competitionDate);
    public void setComManagementName(String comManagementName);
    public void setComManagementAddress(String comManagementAddress);
    public void setComManagementCity(String comManagementCity);
    public void setComManagementState(String comManagementState);
    public void setComManagementCountry(String comManagementCountry);
    public void setComManagementZip(String comManagementZip);
    public void setComManagementPhone(String comManagementPhone); 
    public void setComManagementFax(String comManagementFax);
    public void setManagerUsefMemberId(String managerUsefMemberId);
    public void setManagerUseaMemberId(String managerUseaMemberId);
    public void setManagerName(String managerName);
    public void setSecretaryUsefMemberId(String secretaryUsefMemberId);
    public void setSecretaryName(String secretaryName);
    
   // public PaymentDetails getRenewalPaymentDetails(String eventId);
}
