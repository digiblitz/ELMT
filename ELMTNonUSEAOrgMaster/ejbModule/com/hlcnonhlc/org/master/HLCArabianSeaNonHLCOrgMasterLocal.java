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
package com.hlcnonhlc.org.master;

import javax.ejb.EJBLocalObject;


/**
 * This is the local interface for ArabianSeaNonUSEAOrgMaster enterprise bean.
 */
public interface HLCArabianSeaNonHLCOrgMasterLocal extends EJBLocalObject, HLCArabianSeaNonHLCOrgMasterLocalBusiness {
    
    public void setNonuseaOrgId(String nonuseaOrgId);
    public void setNonuseaOrgName(String nonuseaOrgName);
    
    public  String  getNonuseaOrgId();
    public  String  getNonuseaOrgName();
}
