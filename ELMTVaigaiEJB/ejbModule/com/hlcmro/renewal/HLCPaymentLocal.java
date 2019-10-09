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
import javax.ejb.EJBLocalObject;
import java.util.*;



public interface HLCPaymentLocal extends EJBLocalObject, HLCPaymentLocalBusiness {
        public HLCPaymentDetails getPaymentDetails();
        public void setPaymentId(String paymentId);
        public void setUserId(String userId);
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
}
