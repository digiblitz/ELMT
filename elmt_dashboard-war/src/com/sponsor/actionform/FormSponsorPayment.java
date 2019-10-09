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
/*  Program Name    : FormSponsorPayment.java
 *  Created Date    : September 8, 2006, 4:25 AM
 *  Author          : Punitha.R
 *  Version         : 1.1
 *  CopyrightInformation:
    (c)2006,digiBlitz Technologies Inc/digiBlitz Technologies (P) Ltd. All rights reserved.
    916 W. Broad Street Suite 205, FallsChurch, VA 22046.
    
    This document is protected by copyright. No part of this document may be reproduced in any form by any means without
    prior written authorization of Sun and its licensors. if any.
 
    The information described in this document may be protected by one or more U.S.patents.foreign patents,or
    pending applications.
 */

package com.sponsor.actionform;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author suresh
 * @version
 */

public class FormSponsorPayment extends org.apache.struts.action.ActionForm {
    
        private String ccName;
        private String ccType;
        private String ccNumber;
        private String ccExpMonth;
        private String ccExpYear;
        private String ccCvvid;
        private String bankName;
      
        private String checkDate;
        private String checkNumber;
        private String amount;
        private String insertProcess;
        private String paymentDate;
        private String paymentStatus;
       private String r11;
    
    /**
     *
     */
    public FormSponsorPayment() {
        super();
        // TODO Auto-generated constructor stub
    }
    
   
        // getters
         public String getCcName() {
            return ccName;
        }
        public String getR11() {
            return r11;
        } 
        public String getCcType() {
            return ccType;
        }
        public String getCcNumber() {
            return ccNumber;
        }
        public String getCcExpMonth() {
            return ccExpMonth;
        }
        public String getCcExpYear() {
        return ccExpYear;
        }
        public String getCcCvvid() {
            return ccCvvid;
        }
        public String getBankName() {
            return bankName;
        }
        public String getCheckDate() {
            return checkDate;
        }
        public String getCheckNumber() {
            return checkNumber;
        }
        public String getAmount() {
            return amount;
        }
        public String getPaymentDate() {
            return paymentDate;
        }
        public String getPaymentStatus() {
            return paymentStatus;
        }
        
        
         public void setR11(String r11) {
            this.r11 = r11;
        }
         public void setCcName(String ccName) {
            this.ccName = ccName;
        }
        public void setCcType(String ccType) {
            this.ccType = ccType;
        }
        public void setCcNumber(String ccNumber) {
            this.ccNumber = ccNumber;
        }
        public void setCcExpMonth(String ccExpMonth) {
            this.ccExpMonth = ccExpMonth;
        }
        public void setCcExpYear(String ccExpYear) {
            this.ccExpYear = ccExpYear;
        }
        public void setCcCvvid(String ccCvvid) {
            this.ccCvvid = ccCvvid;
        }
        public void setBankName(String bankName) {
            this.bankName = bankName;
        }
        public void setCheckDate(String checkDate) {
            this.checkDate = checkDate;
        }
        public void setCheckNumber(String checkNumber) {
            this.checkNumber = checkNumber;
        }
        public void setAmount(String amount) {
            this.amount = amount;
        }
        public void setPaymentDate(String paymentDate) {
            this.paymentDate = paymentDate;
        }
        public void setPaymentStatus(String paymentStatus) {
            this.paymentStatus = paymentStatus;
        }
}