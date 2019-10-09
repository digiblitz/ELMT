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
package com.hlcmrm.admin;

import javax.ejb.EJBLocalObject;
import java.util.*;


/**
 * This is the local interface for ArabianSeaAdmin enterprise bean.
 */
public interface HLCArabianSeaAdminLocal extends EJBLocalObject, HLCArabianSeaAdminLocalBusiness {
    public void setContactTypeId(String contactTypeId );
    public void setContactTypeName(String contactTypeName);
    //Start:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
    public void setContactTypeDescription(String contactTypeDescription);
    public void setContactType(String contactType);
    public void setStatus(String status);
    //End:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
   /* public void setCountryMailTypeId(String countryMailTypeId);
    public void setCountryMailTypeName(String countryMailTypeName);
    public void setCountryMailPrice(String countryMailPrice);
    public void setHorseServiceTypeId(String horseServiceTypeId);
    public void setHorseServiceTypeName(String horseServiceTypeName);
    public void setHorseServiceTypeAmount(String horseServiceTypeAmount );
    public void setStatusId(String statusId);
    public void setStatusName(String statusName);
    public void setDescription(String Description);
    public void setMembershipTypeId(String membershipTypeId);
    public void setMembershipIypeName(String membershipIypeName);
    public void setUserTypeId(String userTypeId);
    public void setMembershipAmount(String membershipAmount);
    public void setModifyDate(Date modifyDate);*/
    public void setNonuseaOrgId(String nonuseaOrgId);
    public void setNonuseaOrgName(String nonuseaOrgName);
    
    public String getContactTypeId();
    public String getContactTypeName();
    //Start:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
    public String getContactTypeDescription();
    public String getContactType();
    public String getStatus();
    //End:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
}
