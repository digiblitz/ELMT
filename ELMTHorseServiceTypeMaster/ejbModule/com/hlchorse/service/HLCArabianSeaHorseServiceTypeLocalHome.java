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
package com.hlchorse.service;

import com.hlchorse.service.util.HLCHorseServiceTypeMaster;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;
import javax.ejb.CreateException;
import java.util.*;

/**
 * This is the local-home interface for ArabianSeaHorseServiceType enterprise bean.
 */
public interface HLCArabianSeaHorseServiceTypeLocalHome extends EJBLocalHome {
    
    public HLCArabianSeaHorseServiceTypeLocal findByPrimaryKey(String key)  throws FinderException;
    
    public HLCArabianSeaHorseServiceTypeLocal create(HLCHorseServiceTypeMaster objHorseTypeMaster) throws CreateException;
    
    //Debugs Starts by Lakshmi
  //  public Collection findByHorseTypeName(String horseTypeName,String userTypeId) throws FinderException;
    public Collection findByHorseTypeName(String horseTypeId,String horseTypeName,String userTypeId) throws FinderException;
    //Debugs Ends by Lakshmi
    
    public Collection findByHorseServiceTypeDatails() throws FinderException;
    
    
}
