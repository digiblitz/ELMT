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
package com.hlcspnr.sponsor;

import com.hlcspnr.util.HLCSponsorDetails;
import javax.ejb.EJBLocalObject;
import java.util.*;


/**
 * This is the local interface for KrishnaRajaSaragEntity enterprise bean.
 */
public interface HLCKrishnaRajaSaragEntityLocal extends EJBLocalObject, HLCKrishnaRajaSaragEntityLocalBusiness {
        public HLCSponsorDetails getSponsorDetails();
        public void setUserId(String userId);
        public void setCompanyName(String companyName);
        public void setComments(String comments);
        public void setPlanId(String planId);
      //  public void setStatusId(String statusId);
        public void setSalesPersonId(String salesPersonId);
        public void setContractStartDate(Date contractStartDate);
        public void setcontractEndDate(Date contractEndDate);
        public void setSponsorAmount(String sponsorAmount);
        public void setRequestStatus(String requestStatus);
        public void setFilePath(String filePath);

        public void setAddDate(Date addDate);             
}
