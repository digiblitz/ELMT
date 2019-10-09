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
package com.hlcadv.advertise;

import com.hlccommon.util.HLCAdvertiserVO;
import javax.ejb.EJBLocalObject;
import java.util.*;

/**
 * This is the local interface for Advertise enterprise bean.
 */
public interface HLCAdvertiseLocal extends EJBLocalObject, HLCAdvertiseLocalBusiness {
        public HLCAdvertiserVO getAdvertiseDetails();
        public void setAdvertisementId(String advertisementId);
        public void setUserId(String userId);
        public void setMediaId(String mediaId) ;
        public void setAdvertiser(String advertiser);
        public void setAdAgency(String adAgency);
        public void setAgencyFirstName(String agencyFirstName);
        public void setAgencyMiddleName(String agencyMiddleName);
        public void setAgencyLastName(String agencyLastName) ;
        public void setAgencyPhone(String agencyPhone );
        public void setAgencyFax(String agencyFax);
        public void setAgencyEmail(String agencyEmail );
        public void setAgencyAddress(String agencyAddress);
        public void setAgencySuite(String agencySuite);
        public void setAgencyCity(String agencyCity);
        public void setAgencyState(String agencyState);
        public void setAgencyCountry(String agencyCountry);
        public void setAgencyZip(String agencyZip);
        public void setInvoiceTo(String invoiceTo);
        public void setComments(String comments);
        public void setAdvPosted(boolean advPosted);
        public void setAdvPostedDate(Date advPostedDate);
        public void setMaterialComingFrom(String materialComingFrom);
        public void setMaterialComingDate(Date materialComingDate);
        public void setAdvSuppliedOn(String advSuppliedOn);
        public void setSalesPersonId(String salesPersonId);
        public void setRequestStatus(String requestStatus);
        public void setActiveStatus(boolean activeStatus);
        public void setAddDate(Date addDate);
        
        public ArrayList getAdsDetails();
        public void setAdsDetails(ArrayList adsDet);
}
