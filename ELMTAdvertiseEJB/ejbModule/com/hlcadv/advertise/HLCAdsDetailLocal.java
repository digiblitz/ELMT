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

import com.hlccommon.util.HLCAdvertiseDetVO;
import javax.ejb.EJBLocalObject;

/**
 * This is the local interface for AdsDetail enterprise bean.
 */
public interface HLCAdsDetailLocal extends EJBLocalObject, HLCAdsDetailLocalBusiness {
    public HLCAdvertiseDetVO getAdvertiseDetVO();
    public void setAdvDetailId(String advDetailId);
    public void setAdvertisementId(String advertisementId);
    public void setIssueId(String issueId);
    public void setAdvMapId(String advMapId);
    public void setDimensionId(String dimensionId);
    public void setPlacement(String placement);
    public void setDiscount(String discount);
    public void setAmount(String amount);
    public String getAdvDetailId();
    public String getAdvertisementId();
    public String getIssueId();
    public String getAdvMapId();
    public String getDimensionId();
    public String getPlacement();
    public String getDiscount();
    public String getAmount();
}
