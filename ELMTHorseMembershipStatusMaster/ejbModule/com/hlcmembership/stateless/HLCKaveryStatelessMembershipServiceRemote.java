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
package com.hlcmembership.stateless;

import com.hlchorse.member.util.HLCMembershipStatusMaster;
import javax.ejb.EJBObject;
import javax.ejb.FinderException;
import java.rmi.RemoteException;
import java.util.*;

/**
 * This is the remote interface for KaveryStatelessMembershipService enterprise bean.
 */
public interface HLCKaveryStatelessMembershipServiceRemote extends EJBObject, HLCKaveryStatelessMembershipServiceRemoteBusiness {
  public boolean addMembershipStatusMaster(HLCMembershipStatusMaster objMemberStat) throws RemoteException;
  public boolean editMembershipStatusMaster(HLCMembershipStatusMaster objMemberStat) throws RemoteException, FinderException;
  public boolean deleteMembershipStatusMaster(String statusId) throws RemoteException,FinderException;
 // public Collection getMembershipStatusId(String statusName) throws RemoteException, FinderException;
  public Collection getMembershipStatusDetail() throws RemoteException, FinderException;
  //public Collection getMembershipStatusDetail() throws RemoteException;
  public Vector getStatusDetail() throws RemoteException, FinderException;
  public HashMap getHorseMembersStatusWise(Date fromDate,Date toDate) throws RemoteException;
  public HashMap getHorseMembersMemberShipsByStatus(Date fromDate,Date toDate,String status) throws RemoteException;
  public String getHorseMemberShipsBySpecificTypeAndStatus(Date fromDate,Date toDate,String status,String membership_type_id)throws RemoteException;
  public HashMap getNewlyRegisteredHorseMembersMemTypeWise(Date fromDate,Date toDate) throws RemoteException;
  public HashMap getNumberofHorseEventWise(Date fromDate,Date toDate,String[] years) throws RemoteException;
  public HashMap getHorseMembersEventsMembershipWise(Date fromDate,Date toDate) throws RemoteException;






    
}
