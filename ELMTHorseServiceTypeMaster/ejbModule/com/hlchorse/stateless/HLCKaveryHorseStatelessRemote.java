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
package com.hlchorse.stateless;

import com.hlchorse.service.util.HLCHorseServiceTypeMaster;
import javax.ejb.EJBObject;
import javax.ejb.FinderException;
import java.rmi.RemoteException;
import java.util.*;

/**
 * This is the remote interface for KaveryHorseStateless enterprise bean.
 */
public interface HLCKaveryHorseStatelessRemote extends EJBObject, HLCKaveryHorseStatelessRemoteBusiness {
    
    public boolean addHorseServiceTypeMaster(HLCHorseServiceTypeMaster objHorse) throws RemoteException;
    
    public boolean editHorseServiceTypeMaster(HLCHorseServiceTypeMaster objHorse) throws RemoteException, FinderException;
    
    public boolean deleteHorseServiceTypeMaster(String horseServiceTypeId) throws RemoteException,FinderException;
    
   // public Collection getHorseServiceTypeId(String horseServiceTypeName) throws RemoteException, FinderException;
    public String[] displayHorseServiceDetailsById(String horseServiceTypeId) throws RemoteException, FinderException ;
    
    public Collection getHorseServiceDetails() throws RemoteException, FinderException;
    
    public Vector displayHorseServiceDetails() throws RemoteException, FinderException;
    public Vector displayNonHumanServiceDetails(String userid)throws RemoteException, FinderException;
    
}
