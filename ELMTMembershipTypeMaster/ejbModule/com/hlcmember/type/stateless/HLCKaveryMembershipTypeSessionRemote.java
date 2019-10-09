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
package com.hlcmember.type.stateless;

//import com.form.util.*;
import com.hlcmember.type.util.HLCMembershipTypeMaster;
import javax.ejb.EJBObject;
import javax.ejb.FinderException;
import java.rmi.RemoteException;
import java.util.*;

/**
 * This is the remote interface for KaveryMembershipTypeSession enterprise bean.
 */
public interface HLCKaveryMembershipTypeSessionRemote extends EJBObject, HLCKaveryMembershipTypeSessionRemoteBusiness {

    public boolean addMembershipTypeMaster(HLCMembershipTypeMaster objMemberType) throws RemoteException;
    public boolean editMembershipTypeMaster(HLCMembershipTypeMaster objMemberType) throws RemoteException, FinderException;
    public boolean editMemberStatus(String activeStat, String memberId) throws RemoteException;
    public boolean deleteMembershipTypeMaster(String membershipTypeId) throws RemoteException,FinderException;
   // public Collection getMembershipTypeId(String membershipTypeName) throws RemoteException, FinderException;
    public Collection getMembershipTypeDetails() throws RemoteException, FinderException;
    public Vector displayUserTypeDetails() throws RemoteException, FinderException;
    public Vector displayHumanUserTypeDetails() throws RemoteException, FinderException ;
    public String[] getMembershipTypeDetail(String memberTypeId) throws RemoteException, FinderException;

    public Vector displayMembershipTypeDetails() throws RemoteException, FinderException;

    public Vector displayMembershipType(String userTypeId) throws RemoteException, FinderException;
    public Vector displayMembershipTypeDetails1(int membership_year) throws RemoteException, FinderException;

    public Vector displayMembershipDetailByMemberIDe(String membershipTypeId, String statusId, int rCnt) throws RemoteException, FinderException;
    public int memberRowCount(String membershipTypeId, String statusId) throws RemoteException;

   // public Vector selectDetailsForMember(String memberShipTypeId)throws RemoteException, FinderException;

   // public Vector selectDetailsForMember(String memberShipTypeId, String statusId)throws RemoteException, FinderException;

     public Vector selectDetailsForMember(String memberId)throws RemoteException, FinderException;

     public ArrayList displayMembershipDetailForHorse(String userTypeId, String membershipTypeId, String statusId) throws RemoteException;
     public boolean editHorseMemberStatus(String activeStat, String horseMemberId) throws RemoteException;
     public Vector displayAnnualMemberDetails() throws RemoteException, FinderException;

    //MemberDetails displayMembershipDetailByMemberIDe(String memberId);
     public ArrayList getMailPreference(String memId) throws RemoteException;
     public ArrayList getFamilyAddOnId(String memberId) throws RemoteException;
     public boolean updateUserMemberFlag(String uId,String splNotes, boolean value) throws RemoteException;

     public ArrayList getAccTxnTypDetails() throws RemoteException;
     public int getMembershipTypeYearOnId(String membershipTypeId) throws RemoteException;
     public String[] selectNextYrPrice(String membershipTypName, int membershipYear) throws RemoteException;
     public Vector displayMembershipTypeOnYear(String userTypeId,int year) throws RemoteException;
     public Vector displayMembershipTypeOnYearAndTypeName(String userTypeName,int year) throws RemoteException;
     public ArrayList getMembershipTypeForRegisteration() throws RemoteException;
     public ArrayList getMembershipTypeIdAndAmt(String MembershipTypeName,int year) throws RemoteException;
     public int selectPriorityValue(String membershipTypId) throws RemoteException;
     //for bug starts
     public Vector displayNonHumanMembershipTypeDetail(String uTypeId)throws RemoteException;
     public String getUserTypeIdOnName(String uname)throws RemoteException,FinderException;
     public Vector displayHumanMembershipTypeDetail()throws RemoteException;
     public boolean editHumanMembershipTypeMaster(HLCMembershipTypeMaster objMemberType) throws RemoteException, FinderException;
     //for bug ends


}
