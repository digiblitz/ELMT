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
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;
import javax.ejb.CreateException;
import java.util.Collection;
import java.util.ArrayList;

/**
 * This is the local-home interface for Advertise enterprise bean.
 */
public interface HLCAdvertiseLocalHome extends EJBLocalHome {
    public HLCAdvertiseLocal findByPrimaryKey(String advertisementId)  throws FinderException;
    public HLCAdvertiseLocal create(HLCAdvertiserVO objAdvt, ArrayList adsDet) throws CreateException;
    public Collection findByAll() throws FinderException;
    public Collection findByAdsRequestStatus(String requestStatus) throws FinderException;
    public Collection findByAdsMediaBasedRequestStatus(String mediaId,String requestStatus) throws FinderException;
    public Collection findByAdsMediaId(String mediaId) throws FinderException;
    public Collection findByAdsAgency(String adAgency) throws FinderException;
    public Collection findByUser(String userId) throws FinderException;
    public Collection findByUserAds(String userId) throws FinderException;
   // public Collection findByAdsId(String advertisementId) throws FinderException; 
}
