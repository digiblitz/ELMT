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
package com.hlccountry.mail.price;

import javax.ejb.EJBLocalObject;


/**
 * This is the local interface for ArabianSeaCountryMailPrice enterprise bean.
 */
public interface HLCArabianSeaCountryMailPriceLocal extends EJBLocalObject, HLCArabianSeaCountryMailPriceLocalBusiness {
    
    public void setCountryMailTypeId(String countryMailTypeId);
    public void setCountryMailTypeName(String countryMailTypeName);
    public void setCountryMailPrice(String countryMailPrice);
    
    public String getCountryMailTypeId();
    public String getCountryMailTypeName();
    public String getCountryMailPrice();
    
}
