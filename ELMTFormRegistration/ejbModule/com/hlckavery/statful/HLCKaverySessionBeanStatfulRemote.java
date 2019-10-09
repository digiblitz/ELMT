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
package com.hlckavery.statful;

import com.hlccommon.util.HLCHorseDescriptionVO;
import com.hlccommon.util.HLCHorseRegisterationVO;
import com.hlccommon.util.HLCHorseRegistrationVO;
import com.hlccommon.util.HLCHorseUserVO;
import com.hlccommon.util.HLCRequestHorseDetVO;
import com.hlcreg.util.HLCHorseDescription;
import com.hlcreg.util.HLCHorseMemberDetails;
import com.hlcreg.util.HLCHorseServiceTypeDetails;
import com.hlcreg.util.HLCPaymentDetails;
import javax.ejb.EJBObject;
import javax.ejb.FinderException;
import java.rmi.RemoteException;
import java.util.*;

/**
 * This is the remote interface for KaverySessionBeanStatful enterprise bean.
 */
public interface HLCKaverySessionBeanStatfulRemote extends EJBObject, HLCKaverySessionBeanStatfulRemoteBusiness {
  // public void getMemberIdAndStatusId ()throws RemoteException;
   public ArrayList getGenMemberId() throws RemoteException, FinderException;  
   public ArrayList getGenUserId() throws RemoteException, FinderException;
   public boolean addHorseServiceType(HLCHorseServiceTypeDetails objHorseService) throws RemoteException;
   public void addHorseRegistration(HLCHorseMemberDetails objHorseMem, HLCHorseDescription objHorseDesc,  HLCPaymentDetails objPayment) throws RemoteException;
   public boolean addPaymentDetails(HLCPaymentDetails objPayment) throws RemoteException;
   public boolean deleteHorseServiceType(String horseMemberId) throws RemoteException;
   public boolean deleteHorseMembership(String horseMemberId) throws RemoteException;
   
   public String getUserId(String memberId) throws RemoteException;
   public boolean editHorseMemberDetails( HLCHorseDescription objHorseDesc) throws RemoteException, FinderException;
   public boolean  getMemberStatus(String horseMemberId) throws RemoteException;
   public String createHorseRegistration(HLCHorseRegistrationVO objHrRegVO, HLCHorseDescriptionVO objHrDescVO,
           HLCHorseUserVO objMainOwner,HLCHorseUserVO objHorseTrainer, HLCHorseUserVO objAddOwner, String servicePrice, boolean companyStatus) throws RemoteException;
   
   public boolean createHorseRiderAndOwnerDetails(String horseMemberId, String riderId, String ownerId,
        HLCHorseUserVO objMainOwner, boolean companyStatus, boolean isCard, String reqId) throws RemoteException;

   public String getNextId() throws RemoteException;
   public ArrayList getAllHorseDetails(String requestStatus, String paramMembershipTypeId, int pageNo) throws RemoteException;
  // public ArrayList getAllHorseDetails(String requestStatus) throws RemoteException;
   public HLCHorseRegisterationVO getHorseDetailsByHorseMemberId(String horseMemberId) throws RemoteException;
   public boolean updateRequestStatus(String horseMemberId, String requestStatus, String comments, Date actDate) throws RemoteException;
   public ArrayList getAllHorseDetailsByOwnerId(String ownerId, int rCnt) throws RemoteException;
   public String getUserTypeName(String userTypeId) throws RemoteException;
   public boolean editHorseOwner(String horseMemberId, String ownerId, HLCHorseUserVO objMainOwner, boolean companyStatus,boolean relationStatus) throws RemoteException;
   public boolean editHorseTrainer(String horseMemberId, String trainerId, HLCHorseUserVO objMainTrainer, boolean companyStatus, boolean relationStatus, String previousRiderId, String userId) throws RemoteException;
   public boolean upgradeHorse(String horseMemberId, String membershipTypeId, String paymentId, String upgradeHorse, String membershipAmount, String amount) throws RemoteException;
    public boolean upgradeNonRegisteredHorse(String horseMemberId, String membershipTypeId, String paymentId, String membershipAmount, String totalAmount) throws RemoteException;
   public boolean isHorseExist(String horseName) throws RemoteException;
   public ArrayList getAllHorseDetailsByRiderId(String riderMemberId) throws RemoteException;
   public boolean updateHorseDetailsByOwner(String horseMemberId,String riderId, String prevRiderId, String  addRiderId, HLCHorseDescriptionVO objHrDescVO) throws RemoteException;
   public boolean updateHorseDetailsByAdmin(String horseMemberId, String competitionName, String registereName, String baRegisteredName, String baPastName,
           String riderId, String prevRiderId, String  addRiderId, HLCHorseDescriptionVO objHrDescVO) throws RemoteException;
   public ArrayList getAllStatusDetails() throws RemoteException;
   public String[] getHorsePaymentDetails(String paymentId) throws RemoteException;
   public ArrayList getHorseChildPaymentDetailsByAdmin(String parentPaymentId) throws RemoteException;
   public ArrayList getHorseRelationshipDetailsDetails(String horseMemberId) throws RemoteException;
   public boolean updateOwnerRiderActivation(String relationId, String relationshipTypeId, String relationshipStatusId) throws RemoteException;
   public String[] getHorseRelationshipDetailsByRelationId(String relationId) throws RemoteException;
   public ArrayList getAllHorseRelationMaster() throws RemoteException;
   public boolean isHorsePriOwnerExist(String horseMemberId, String userId) throws RemoteException;
   public boolean isHorsePriRiderExist(String horseMemberId, String userId) throws RemoteException;
   
   public boolean isHorsePriTrainerExist(String horseMemberId,String userId, String relationId) throws RemoteException;
   
   public boolean isHorsePriOwnerRiderExistInRelation(String relationId, String userId) throws RemoteException;
   public boolean updateHorseDescriptionByOwnerOrRider(String horseMemberId, HLCHorseDescriptionVO objHrDescVO) throws RemoteException;
   public String getOwnerIdFromHrMember(String hrMemberId) throws RemoteException;
   public String getRelationId(String hrMemberId, String userId, String relationshipId) throws RemoteException;
   public boolean updateOwnerwithRelationship(String relationId, String relationshipTypeId , String userId) throws RemoteException;
   public boolean deGradeOwnerRider(String relationId, String relationType) throws RemoteException;
   
   public String createHorseRequest(HLCRequestHorseDetVO reqHrDetVO) throws RemoteException;
   
   public String editHorseRiderAndOwnerDetails(String horseMemberId, String horseName, String horseRegisterName , String requestBy,
        String riderId, String ownerId, boolean ownerRequest , HLCHorseUserVO objMainOwner,
           boolean companyStatus, String paymentId, float amount, String serviceId,boolean reqValue) throws RemoteException;
   
   public String getHorseEditCharge() throws RemoteException;
   public String[] getHorseServiceCharge() throws RemoteException;
   public ArrayList getHorseRequestForm(boolean status) throws RemoteException;
   public ArrayList getHorseRequestForm1(String uTypeId) throws RemoteException;
   public HLCRequestHorseDetVO getHorseRequestFormById(String reqId) throws RemoteException;
   public boolean statusChangeForHorse(String horseMemberId, Date actDate) throws RemoteException;
   public boolean updateRequestHorseDetailsByAdmin(String horseMemberId, String relationId, String competitionName, String registereName,
           String riderId, String ownerId, HLCHorseUserVO objMainOwner, boolean companyStatus, boolean ownerStatus,String oldCompetitionName) throws RemoteException;
   
   public boolean updateRequestHorseDetailsByAdmin(String horseMemberId, String relationId, String competitionName, String registereName,
           String riderId, String ownerId, HLCHorseUserVO objMainOwner, boolean companyStatus, boolean ownerStatus) throws RemoteException;
   public String getMemberIdForRider(String userId) throws RemoteException;
   public boolean updatePaymentStatus(com.hlcreg.util.HLCPaymentVO objPaymentVO) throws RemoteException;
   public boolean updateRealtionshipStatus(String horseMemberId) throws RemoteException;
   public boolean updatePaymentCheckAmount(float checkAmount, String paymentId) throws RemoteException;
   public String[] getAllEmailIds(String horseMemberId) throws RemoteException;
   public int horseRowCount(String requestStatus, String paramMembershipTypeId) throws RemoteException;
   public int horseUserRowCount(String userId) throws RemoteException;
   public ArrayList getAllPendingPaymentsForHorse(String ownerId) throws RemoteException;
   public boolean createMembershipRefundDetails(String userId, String comments, float totalAmt, float checkAmt) throws RemoteException;
   public ArrayList getHorseRelationshipDetailsByRequestId(String reqId) throws RemoteException;
   public String[] getHorseAddRequestByReqId(String requestId) throws RemoteException;
   public float getBalanceAmount(String paymentId) throws RemoteException;
   public boolean updateBalanaceAmount(String paymentId, float balAmount) throws RemoteException;
   public boolean updatePendingAmount(String userId, String paymentId, float checkAmount) throws RemoteException;
   public ArrayList getAllPendingPaymentsForHorseUser(String userId) throws RemoteException;
   public ArrayList getAllDuePaymentsForHorse(String userId) throws RemoteException;
   public ArrayList getAllDuePaymentsForHorseUser(String userId) throws RemoteException;
   public String getStatusId(String statusName) throws RemoteException;
   public boolean updateRequestStatus(String requestId) throws RemoteException;
    public boolean updateHorseUpgradeForNotRegisterHorse(String horseMemberId, String competitionName, String registereName, String baRegisteredName, String baPastName,
         String riderId, String prevRiderId, String  addRiderId, HLCHorseDescriptionVO objHrDescVO,String serviceId) throws RemoteException;
    
   public boolean updateRequestStatusForAdmin(String requestId) throws RemoteException;
    public String selectHorseServiceTypeId(String typName) throws RemoteException;
//Add Trainer Information
    public boolean insertTrainerInfo(String horseMemberId, String trainerId, HLCHorseUserVO objMainTrainer,boolean companyStatus,String relationStatus) throws RemoteException;
    public boolean updateTrainerInfo(String horseMemberId, String trainerId) throws RemoteException;
    public boolean deleteTrainerInfo(String horseMemberId) throws RemoteException;
 // On Upgrade of FEH YEH
    public boolean upgrade_FEH_YEH_Horse(String horseMemberId, String membershipTypeId, String paymentId, String serviceTypeId, String membershipAmount) throws RemoteException;
    
    //==========================================Horse status update=========================================================
    
    public boolean approveHorseStatusByAdmin(String horseMemberId, String requestStatus, String comments, Date activitationDate, String modifiedBy, Date currDate) throws RemoteException;
    
   //============================================Horse Registration Email content================================= 
    
   public ArrayList getUserDets(String memberId) throws RemoteException;
   public ArrayList getUserDetails(String horseMemberId) throws RemoteException;
   public ArrayList getOwnerDetails(String horseMemberId) throws RemoteException;
   public ArrayList getOwnerDetailsForMail(String logName) throws RemoteException;
   public ArrayList getPreOwnerRiderDetails(String horseMemberId) throws RemoteException;
   public String getPrimaryOwnEmailId(String horseMemberId) throws RemoteException; 
   public ArrayList getHorseOwnRidChangeDetails(String requestId) throws RemoteException;
   public ArrayList getHorseRelationDetails(String horseMemberId) throws RemoteException;
 //----------------------------------------------------satheesh Start Human/Non-Human MMMA_0010: relationships-----------------------------------------------------------
public Vector displayUserTypeDetails()throws RemoteException,FinderException;
public boolean createHorseOwner(String relationDesc,String relationStatus,String SpecieId) throws RemoteException;
public ArrayList getHorseRelationshipDetailsByRequestId1(String reqId) throws RemoteException;
public boolean editHorserealtion(String relation,String relationStatus,String relationId,String uTypeId) throws RemoteException;
public boolean deleteBreed(String chkRoleIdArr[])throws RemoteException;
 //----------------------------------------------------satheesh End Human/Non-Human MMMA_0010: relationships -----------------------------------------------------------
}
