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
package com.hlccountry.mail.stateless;

import com.hlccountry.mail.util.HLCCountryMailPriceMaster;
import javax.ejb.EJBObject;
import javax.ejb.*;
import java.rmi.RemoteException;
import java.util.*;

/**
 * This is the remote interface for KaveryCountryMailPriceSeaaion enterprise bean.
 */
public interface HLCKaveryCountryMailPriceSeaaionRemote extends EJBObject, HLCKaveryCountryMailPriceSeaaionRemoteBusiness {
    
    public boolean addCountryMailPriceMaster(HLCCountryMailPriceMaster objCountryMail) throws RemoteException;
    public boolean editCountryMailPriceMaster(HLCCountryMailPriceMaster objCountryMail) throws RemoteException, FinderException;
    public boolean deleteCountryMailPriceMaster(String countryMailTypeId) throws RemoteException,FinderException;
   // public Collection getCountryMailTypeID(String countryMailTypeName) throws RemoteException, FinderException;
    public String[]  getCountryMailDetailById(String countryMailTypeId) throws RemoteException, FinderException;
    public Collection getCountryMailDetail() throws RemoteException, FinderException;
    public Vector displayCountryMailDetail() throws RemoteException, FinderException;
    public HLCCountryMailPriceMaster getCountryMailDetailByCntMailId(String countryMailTypeId) throws RemoteException, FinderException;
    public ArrayList getCountryMailDetails(int membershipYear) throws RemoteException;
    public ArrayList getAllMembershipType() throws RemoteException;
}
