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
/*  Program Name    : FormChennaiServlet.java
 *  Created Date    : 
 *  Author          : Punitha.R
 *  Version         : 1.2
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
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;

import org.apache.struts.action.ActionForward;

import java.util.*;
import java.util.Date;
import java.io.Serializable;


public class FormChennaiServlet extends ActionForm implements Serializable{
    
 
    public FormChennaiServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
  
        private String companyName;
        private String planId;
        private String sponsorAmount;
        private String comments;

        public String getCompanyName() {
        return companyName;
        }
        public String getComments() {
        return comments;
        }
        public String getPlanId() {
        return planId;
        }
        public String getSponsorAmount() {
        return sponsorAmount;
        }
        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }
        public void setComments(String comments) {
            this.comments = comments;
        }
        public void setPlanId(String planId) {
            this.planId = planId;
        }
        public void setSponsorAmount(String sponsorAmount) {
            this.sponsorAmount = sponsorAmount;
        }
      

}
  
 
