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
package com.hlcspnr.schedule;

import javax.ejb.EJBLocalObject;
import java.util.*;
import java.sql.SQLException;


/**
 * This is the local interface for SponsorPaymentSchedule enterprise bean.
 */
public interface HLCSponsorPaymentScheduleLocal extends EJBLocalObject, HLCSponsorPaymentScheduleLocalBusiness {
    public Collection getPaymentSchedule(String scheduleId);
    public void setScheduleId(String scheduleId);
    public void setSponsorId(String sponsorId);
    public void setScheduleTypeId(String scheduleTypeId);
    public void setDueDate(Date dueDate);
    public void setDueAmount(float dueAmount);
    public void setContinueStatus(boolean continueStatus);
    public void setFrequencyRate(String frequencyRate);
    public void setPaymentStatus(String paymentStatus);
    public void setSpecialBill(Vector specialBil);
    public Vector getSpecialBill();
    public String getScheduleId();
    public String getSponsorId();
    public String getScheduleTypeId();
    public Date getDueDate();
    public float getDueAmount();
    public boolean isContinueStatus();
    public String getFrequencyRate();
    public String getPaymentStatus();
}
