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
package com.hlcinv.session;

import com.hlcinv.util.HLCInventoryMasterVO;
import java.rmi.RemoteException;
import java.util.List;
import javax.ejb.EJBObject;
import javax.ejb.FinderException;


/**
 * This is the remote interface for SaraswathySession enterprise bean.
 */
public interface HLCSaraswathySessionRemote extends EJBObject, HLCSaraswathySessionRemoteBusiness {
    
    public List displayINVBasedOnSubCategoryId(String subCategoryId) throws RemoteException, FinderException;
    public List displayINVBasedOnCategoryId(String categoryId) throws RemoteException, FinderException;
    public HLCInventoryMasterVO displayINVBasedOnProdId(String prodId) throws RemoteException, FinderException;
    
     public boolean deleteINVMST(String productId) throws RemoteException;
     public boolean insertINVMST(HLCInventoryMasterVO objINV) throws RemoteException;
     public boolean updateINVMST(HLCInventoryMasterVO objINV) throws RemoteException;
}
