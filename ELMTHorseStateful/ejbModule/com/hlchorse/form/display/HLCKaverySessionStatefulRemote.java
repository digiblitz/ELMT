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
package com.hlchorse.form.display;

import com.hlchorse.form.util.HLCHorseDisplayVO;
import java.sql.SQLException;
import javax.ejb.EJBObject;
import java.rmi.RemoteException;
import java.util.Vector;
import com.hlchorse.form.util.HLCUserRegVO;


/**
 * This is the remote interface for KaverySessionStateful enterprise bean.
 */
public interface HLCKaverySessionStatefulRemote extends EJBObject, HLCKaverySessionStatefulRemoteBusiness {
    
    public HLCHorseDisplayVO displayHorseDetails (String memberId) throws RemoteException;
    public HLCUserRegVO displayUserRegistrationForm (String email) throws RemoteException;
    public Vector getRiderDetails(String riderId) throws RemoteException;
    public Vector getOwnerDetails(String loginName) throws RemoteException;
    public Vector displayHorseMember() throws RemoteException;
    public Vector displayMemberUserList(String userTypeName, int rCnt) throws RemoteException;
    public int userRowCount(String userTypeId)throws RemoteException;
    public Vector getHorseDetailsBasedOnOwner(String loginName) throws RemoteException;
    public boolean editActiveStatus(String activeStat, String userId) throws RemoteException;
    public boolean getMemberShipStatus(String horseMemberId) throws RemoteException;
    public HLCUserRegVO selectUserRegistrationForm1(String userId)throws RemoteException;
    public String getHumanUserTypeId() throws RemoteException,SQLException;
    
    public HLCUserRegVO displayUserRegistrationFormOnUserId(String userId) throws RemoteException;
   
}
