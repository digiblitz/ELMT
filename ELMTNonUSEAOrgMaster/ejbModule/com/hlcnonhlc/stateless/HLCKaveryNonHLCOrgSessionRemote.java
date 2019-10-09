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
package com.hlcnonhlc.stateless;

import com.hlcnonhlc.org.util.HLCNonHLCOrgMaster;
import javax.ejb.EJBObject;
import javax.ejb.FinderException;
import java.rmi.RemoteException;
import java.util.*;

/**
 * This is the remote interface for KaveryNonUSEAOrgSession enterprise bean.
 */
public interface HLCKaveryNonHLCOrgSessionRemote extends EJBObject, HLCKaveryNonHLCOrgSessionRemoteBusiness {
    
    public boolean addNonUSEAOrgMaster(HLCNonHLCOrgMaster objNonUSEA) throws RemoteException;
    public boolean editNonUSEAOrgMaster(HLCNonHLCOrgMaster objNonUSEA) throws RemoteException, FinderException;
    public boolean deleteNonUSEAOrgMaster(String nonuseaOrgId) throws RemoteException,FinderException;
   // public Collection getNonUSEAOrgId(String nonuseaOrgName) throws RemoteException, FinderException;
    
    public ArrayList getNonUSEAOrgDetails() throws RemoteException, FinderException;
    
    public Vector displayNonUSEAOrgDetails() throws RemoteException, FinderException;
}
