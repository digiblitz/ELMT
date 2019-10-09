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
package com.hlcmsg.session;

import com.hlcmsg.util.HLCGroup;
import com.hlcmsg.util.HLCGroupMessage;
import com.hlcmsg.util.HLCGroupUser;
import com.hlcmsg.util.HLCMsgContactListMaster;
import com.hlcmsg.util.HLCMsgDistributionListMaster;
import com.hlcmsg.util.HLCMsgPaginationVO;
import com.hlcmsg.util.HLCUserMaster;
import com.hlcmsg.util.HLClMsgDBRequestDetailsVO;
import javax.ejb.EJBObject;
import java.rmi.RemoteException;
import javax.ejb.FinderException;
import java.util.*;
import java.sql.*;


/**
 * This is the remote interface for MessageSession enterprise bean.
 */
public interface HLCMessageSessionRemote extends EJBObject, HLCMessageSessionRemoteBusiness {
    
    public boolean addContactList(HLCMsgContactListMaster objContactList)throws RemoteException;
    public boolean editContactListMaster(HLCMsgContactListMaster objContactList) throws RemoteException, FinderException, SQLException;
    public boolean deleteContactListMaster(String contactlistId) throws RemoteException,FinderException;
    public Vector displayContactListBasedOnContactListID(String contactlistId) throws RemoteException, FinderException;
    public HLCMsgContactListMaster displayContactListBasedOnContactListIDVO(String contactListId) throws RemoteException, FinderException;
    public Vector displayContactListBasedOnUserId(String userId) throws RemoteException, FinderException;
    public List displayContactListVO(String userId) throws RemoteException, FinderException;
    public Vector displayContactListBasedOnChar(String ch) throws RemoteException, FinderException;
    public HLCMsgPaginationVO getContactDetails(String sortableName,int offset, int rowSize, String userId) throws RemoteException;
    public ArrayList displayDistribitionListNameAndmailId(String userId) throws RemoteException, FinderException; 
    
    public boolean addDisttList(HLCMsgDistributionListMaster objDistList)throws RemoteException;
    public boolean editDistributionListMaster(HLCMsgDistributionListMaster objDistList) throws RemoteException, FinderException;
    public boolean deleteDistributionListMaster(String listId) throws RemoteException,FinderException;
    public boolean deleteDistMasterBasedOnContactId(String contactId) throws RemoteException;
    public boolean deleteDistListBasedOnListName(String listName) throws RemoteException;
   // public Collection displayDistributiontList(String listName) throws RemoteException, FinderException;
    public Vector displayDistList(String listName) throws RemoteException, FinderException;
    public Vector displayDistribitionListNameAndNoOfMembers(String userId) throws RemoteException, FinderException;
    public Vector displayAddressBook(String userId) throws RemoteException, FinderException;
    public Vector displayMemberAdded(String listName) throws RemoteException, FinderException;
    
    /*=======================Message Grouping====================================*/
   
    public HLCGroup createGroup(HLCGroup objGrpMst)throws RemoteException;
    public boolean updateGroup(HLCGroup objGrpMst)throws RemoteException;
// Display the list of groups available for the current user    
    public ArrayList getAvailableGroup(String userID) throws RemoteException; 
    public boolean createGroupUser(HLCGroupUser objGrpUsr)throws RemoteException;
    public boolean deleteMsgGroupUser(String groupId, String email) throws RemoteException, FinderException;
    public boolean createGroupMessage(HLCGroupMessage objGM)throws RemoteException;
    public List displayCategory() throws RemoteException, FinderException;
    public boolean isValidGroupName(String userId, String groupName) throws RemoteException, FinderException;
    public HLCGroup getGroupDetails(String groupName) throws RemoteException, FinderException;
    public HLCGroup getGroupDetailsBasedOnGroupId(String groupId) throws RemoteException, FinderException;
    public boolean isValidEmail(String emailId) throws RemoteException, FinderException;
    public List searchByGroup(String categoryId,String groupName) throws RemoteException, FinderException;
    public boolean isValidOwner(String userId)  throws RemoteException, FinderException;
    public List getGroupName(String userId) throws RemoteException, FinderException;
    public boolean deactivateGroupStatus(String groupId) throws RemoteException, FinderException;
    public List getGroupMyGroupsList(String ownerId) throws RemoteException, FinderException;
    public List getGroupMyMessages(String fromUserId,String subject,String groupId) throws RemoteException, FinderException;
    public List getGroupMyMembers(String ownerId,String subject,String groupId) throws RemoteException, FinderException;
    public HLCGroupMessage getPostedMessages(String messageId) throws RemoteException, FinderException;
    public List getMessagesAndReplies(String groupId, String parentMessageId) throws RemoteException, FinderException;
    public List getEmailBasedOnGroupName(String groupName) throws RemoteException;
    
    public HLCUserMaster addUserGroupUser(HLCUserMaster objUsr, HLCUserMaster secContact) throws RemoteException;
    public String getUserIdEmail(String emailId) throws RemoteException, FinderException;
    
    /*==================Database Addresses ======================================*/
    
    public List getMembershipTypes() throws RemoteException, FinderException;
    public List getMembershipStatus() throws RemoteException, FinderException;
    
    public List SearchAndListDBAddress(String fromZipCode, String toZipCode, String memberTypeId, String statusId) throws RemoteException, FinderException;
    public boolean createMsgDBRequestDetails(HLClMsgDBRequestDetailsVO objMBDRD) throws RemoteException, FinderException;
    
     public List getMsgDownloadReqList(String userId) throws RemoteException, FinderException;
     public List getAdminMsgDownloadReqList() throws RemoteException, FinderException;
     public HLClMsgDBRequestDetailsVO getMsgDownloadReqListDetail(String dbReqId) throws RemoteException, FinderException;
     public List getStateByAreaId(String areaId) throws RemoteException;
     public boolean updateRequestStatus(String  dbReqId, String status, String comments) throws RemoteException;
     public List getUserDetailForDownload(String[] dbReqId) throws RemoteException, FinderException;
     public String getEmailId(String userId)throws RemoteException, FinderException;
     public boolean setModeratorByGroupId(String modUserId, String groupId)throws RemoteException;
    
    
    
    
    
}
