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

import com.hlccountry.mail.util.HLCCountryMailPriceMaster;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;
import javax.ejb.CreateException;
import java.util.*;

/**
 * This is the local-home interface for ArabianSeaCountryMailPrice enterprise bean.
 */
public interface HLCArabianSeaCountryMailPriceLocalHome extends EJBLocalHome {
    
    HLCArabianSeaCountryMailPriceLocal findByPrimaryKey(String key)  throws FinderException;
    
    public HLCArabianSeaCountryMailPriceLocal create(HLCCountryMailPriceMaster objCountryMail) throws CreateException;
    
   // public Collection findByCountryMailTypeName(String countryMailTypeId) throws FinderException;
    
    public Collection findByCountryMailPrice() throws FinderException;
    
    public Collection findByCountryMailTypeName(String countryMailTypeName) throws FinderException;
    
}
