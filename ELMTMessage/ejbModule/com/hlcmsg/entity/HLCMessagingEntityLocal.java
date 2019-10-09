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
package com.hlcmsg.entity;

import javax.ejb.EJBLocalObject;


/**
 * This is the local interface for MessagingEntity enterprise bean.
 */
public interface HLCMessagingEntityLocal extends EJBLocalObject, HLCMessagingEntityLocalBusiness {
    
     /*=======================Setter Method==================================*/
    public void setContactlistId(String contactlistId);
    public void setUserId(String userId);
    public void setFirstName(String firstName);
    public void setMiddleName(String middleName);
    public void setLastName(String lastName);
    public void setNickName(String nickName);
    public void setEmailId(String emailId);
    public void setAlternateEmailId(String alternateEmailId);
    public void setPhoneNo(String phoneNo);
    public void setMobileNo(String mobileNo);
    public void setFaxNo(String faxNo);
    public void setStreet(String street);
    public void setCity(String city);
    public void setState(String state);
    public void setCountry(String country);
    public void setZip(String zip);
    public void setActiveStatus(String activeStatus);
    
    
    public void setListId(String listId);
    public void setListName(String listName);
    public void setRegistrationEmailId(String registrationEmailId);
   // public void setActiveStatus(String activeStatus);
    public void setAddDate(String addDate);
    
}
