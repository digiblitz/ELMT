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
package com.hlcmember.type.master;

import javax.ejb.EJBLocalObject;
import java.util.*;


/**
 * This is the local interface for ArabianSeaMembershipTypeMaster enterprise bean.
 */
public interface HLCArabianSeaMembershipTypeMasterLocal extends EJBLocalObject, HLCArabianSeaMembershipTypeMasterLocalBusiness {
    public  void setMembershipTypeId(String membershipTypeId);
    public  void setMembershipTypeName(String membershipTypeName);
    public  void setUserTypeId(String userTypeId);
    public  void setMembershipAmount(String membershipAmount);
    //for bug starts
    public void setDuration(String duration);
    public void setActive_Status(int active_status);
    //for bug ends
    public  void setModifyDate(Date modifyDate);
    public void setUserTypeName(String userTypeName);
    public void setPeriodValue(String periodValue);
    public void setTransaction_type_id(String transaction_type_id);
    public void setMembership_year(int membershipYear);
    public  String getMembershipTypeId();
    public  String getMembershipTypeName();
    public  String getUserTypeId();
    public String getUserTypeName();
    public  String getMembershipAmount();
    //for bug starts
    public String getDuration();
    public  int getActive_Status();
    //for bug ends
    public  Date getModifyDate();
    public String getPeriodValue();
    public String getTransaction_type_id();
    public int getMembership_year();

}
