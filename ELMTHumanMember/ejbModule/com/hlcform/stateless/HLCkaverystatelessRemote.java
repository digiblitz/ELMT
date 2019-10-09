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
package com.hlcform.stateless;

import com.hlcform.util.HLCContactDetails;
import com.hlcform.util.HLCEmployeeDetails;
import com.hlcform.util.HLCMemberDetails;
import com.hlcform.util.HLCMemberHistoryDetail;
import com.hlcform.util.HLCPaymentDetailVO;
import com.hlcform.util.HLCPaymentDetails;
import com.hlcform.util.HLCUserMaster;
import com.hlcform.util.HLCUserSearchResultVO;
import com.hlcform.util.HLCUsrSignUpVO;
import javax.ejb.*;
import java.rmi.RemoteException;
import java.util.*;
import java.sql.*;
import java.util.Date;

/**
 * This is the remote interface for kaverystateless enterprise bean.
 */

public interface HLCkaverystatelessRemote extends EJBObject, HLCkaverystatelessRemoteBusiness {
    
    public String addUserRegistration(HLCUserMaster objUserMaster) throws RemoteException;
    
    public void addContactDetails(HLCContactDetails objContact) throws RemoteException;
    
    public String addMemberDetails(HLCMemberDetails objMember, HLCPaymentDetails objPayment) throws RemoteException;
    
    public boolean addPaymentDetails(HLCPaymentDetails objPayment) throws RemoteException;
    
    public String addFamilyAddOn(HLCUserMaster objUserMaster,HLCContactDetails objContact) throws RemoteException;
    
    public boolean deleteContactDetail(String userId, String contactTypeName) throws RemoteException;
    
    public String getParentMemberId(String email) throws RemoteException;
    
    public Vector getUserIdByEmailId(String emailId) throws RemoteException;
    
    public int getJuniorMemberAge(String emailId, String memberTypeId) throws RemoteException;
    
    public String getFamilyaddOn(String memberId) throws RemoteException;
    
    public Vector getLoginStatus(String emailId, String pass) throws RemoteException, SQLException;
    public Vector getLoginStatusByUserId(String userId) throws RemoteException, SQLException;
    
    public Collection getSearchDetails(String firstName, String lastName, Date frmDate, Date toDate, String userTypeId) throws RemoteException;
    
    public void editUserDetails(HLCUserMaster objUserMaster, HLCContactDetails objContact) throws RemoteException;
    
    public boolean editMemberDetail(HLCMemberDetails objMemDet)throws RemoteException;
    
    public boolean getUserCode(String userCode)throws RemoteException;
    
    //public Vector displayMemberDetail(String memberId)throws RemoteException;
    
    public HLCMemberDetails displayMemberDetail(String memberId)throws RemoteException;
    
    public String[] getFamilyAddOnPrice(String memberTypeName)throws RemoteException;
    
    public boolean isMemberIdExist(String memberId)throws RemoteException;
    
    public boolean statusDeactivate(String memberId)throws RemoteException;
    public boolean statusActivate(String emailId)throws RemoteException;
    
    public boolean deleteMemberDetail(String memberId)throws RemoteException;
    public boolean updateFamilyMemeber(String memberId,String paymentId)throws RemoteException;
    
    public HLCContactDetails getContactDetailBasedOnUserId(String userId) throws RemoteException;
    
    public List getUserDetails(String userId) throws RemoteException;
    
    public boolean deleteMember(String memberId) throws RemoteException;
    
    public String[] getEmailAndPassword(String dob, String secQes, String secAns)throws RemoteException;
    
    public String[] getEmailOnEmail(String emailId)throws RemoteException;
    
    public boolean changePassword(String userId, String pass)throws RemoteException;
    
    public String getStatusIBasedOnStatus(String status)throws RemoteException;
    public Date getUserBirthDate(String userId)throws RemoteException;
    
    public boolean isMemberExist(String userId) throws RemoteException;
    public boolean isMemberExistBasedOnMemberAndUserId(String memberId, String userId) throws RemoteException;
    
    public String  getStatusByMemberId(String memberId) throws RemoteException;
    //karthik
    public boolean checkUserCodeExist(String userCode) throws RemoteException;
    //suresh here
    public boolean checkUserNameExist(String userName) throws RemoteException;
    public String getSecretQuestion(String userId) throws RemoteException;
    public String getSecretQuestionByLoginName(String loginName) throws RemoteException;
    
    public ArrayList checkUserExist(String firstName, String lastName, String emailId, String zip) throws RemoteException;
    public boolean checkSecurityQuestion(String userId, String secretQuestion, String secretAnswer) throws RemoteException;
    public boolean checkSecurityQuestionByLoginName(String loginName, String secretQuestion, String secretAnswer) throws RemoteException;
    
    public String[] getLoginDetails(String userId) throws RemoteException;
    public boolean updateLoginDetails(String userId, String loginName, String  password) throws RemoteException;
    public abstract boolean deactivateRequestStatus(String s, boolean flag)
        throws RemoteException;


    public boolean updateLoginDetailsByLoginName(String loginName, String  password) throws RemoteException;
    public Vector getUserIdByLoginName(String loginName) throws RemoteException;
    public ArrayList getLoginDetailsByEmailId(String emailId) throws RemoteException;
    public String getPasswordByLoginName(String loginName) throws RemoteException;
    public String getEmailByLoginName(String loginName) throws RemoteException;
    public String getUserIDByLoginName(String loginName) throws RemoteException;
    public boolean updatMemberFamilyAddOns(String memberId, int familyAddOns) throws RemoteException;
    public boolean insertDonationPriceDetails(String userId, String donationId,  String  donationPrice, String donatedBy,String paymentId)  throws RemoteException;
    public boolean insertWebSiteDonationDetails(String userId, String donationId,  String  donationPrice, String donatedBy,String paymentId)  throws RemoteException;
   
    public boolean insertNonUseaPriceDetails(String memberId, String nonOrgId,  String  nonUseaPrice) throws RemoteException;
    public Date getExpiredDate(String memberId) throws RemoteException;
    public String getUserIdBasedOnMemberId(String memberId) throws RemoteException;
    public boolean insertPublicationDetails(String memberId, String publicationId)  throws RemoteException;
    public boolean deleteNonUseaPriceDetails(String memberId) throws RemoteException;
    public boolean deleteDonationPriceDetails(String userId) throws RemoteException;
    public boolean deletePublicationDetails(String memberId) throws RemoteException;
    public Date calculateExpireDate(int interval, Date date) throws RemoteException;
    public ArrayList getMebershipTypeBasedOnPeriodValue(String userTypeId, int periodValue, String statusGrade, int memb_year) throws RemoteException;
    public HLCPaymentDetails getPaymentDetails(String paymentId) throws RemoteException;
    public boolean updatePaymentStatus(HLCPaymentDetailVO paymentVO) throws RemoteException;
    
    public boolean updateLoginDate(String userId) throws RemoteException;
    public boolean updateAmatureStatus(String memberId,String amatName,boolean posAmat,boolean decAmat) throws RemoteException;
    public boolean updateAddOnExistingMember(String memberId,String statusName,String parentId,String paymentId,String memb_typ_id) throws RemoteException;
    public String getEmailBasedOnUserId(String userId) throws RemoteException;
    public Date getApprovedDate(String memberId)throws RemoteException;
    public boolean updateApprovalDate(String memberId,Date approveDate) throws RemoteException;
    
    public ArrayList getMemberDetailsForUsrSign(String memberId) throws RemoteException;
    public ArrayList getUserDetailsForUsrSign(String firstName,String lastName) throws RemoteException;
    public HLCUsrSignUpVO getUsrDetForUsrSignOnUsrId(String userId) throws RemoteException;
    public boolean insertPayment(HLCPaymentDetailVO objPayment) throws RemoteException;
    public boolean insertWebSiteDonPayDetails(HLCPaymentDetails objPayment) throws RemoteException;
    public String getMemberIdBasedOnUserId(String userId) throws RemoteException;
    public String getMembershipTypeIdBasedOnUserId(String userId) throws RemoteException;
    public ArrayList getAdminPaymentDetails(String parentPaymentId) throws RemoteException,Exception;
    public boolean updateNSFPayment(HLCPaymentDetailVO paymentVO) throws RemoteException;
    public String[] loadServiceType() throws RemoteException,Exception;
    public ArrayList getNsfPaymentDetails(String parentPaymentId) throws RemoteException;
    public boolean updateNSFPendingAmt(String paymentId,float amount) throws RemoteException;
    public boolean updateNSFChargeStatus(String paymentId) throws RemoteException;
    public boolean checkSubpaymentExistance(String paymentId) throws RemoteException;
    public ArrayList getAdminSubPaymentDetails(String parentPaymentId) throws RemoteException;
    public String getLastSubPaymentIdOnParentId(String parentPaymentId) throws RemoteException;
    public boolean updateCheckAmountOnPaymentId(String paymentId, float check_amt) throws RemoteException;
    public boolean updateNSFChargeStatusForSubSequentPayments(String parentPaymentId, float amount) throws RemoteException;
    public HLCPaymentDetailVO getNSFCheckDetails(String paymentId) throws RemoteException;
    public boolean updateReverseEntryStatus(String paymentId) throws RemoteException;
    public boolean updateFutureExpDateByYear(String memberId) throws RemoteException;
    public boolean insertMemberHistoryDetails(HLCMemberHistoryDetail membHistory) throws RemoteException;
    public boolean insertHorseMemberHistoryDetails(HLCMemberHistoryDetail membHistory) throws RemoteException;
    public String getZipCodeOnUserId(String userId) throws RemoteException;
    public boolean updateMemberHistoryDetailStatus(String memberId, String status_Id, String paymentId) throws RemoteException;
    public boolean updateHorseMemberHistoryDetailStatus(String memberId, String status_Id, String paymentId, Date actDtAsFrmDt) throws RemoteException;
    
    public ArrayList getMembershipStatusFromHistory(String membId) throws RemoteException;
    public ArrayList getMembershipDetailsFromHistory(String membId) throws RemoteException;
    public boolean updateHistoryOnMemberSearch(String status_id, String payment_id, String user_id) throws RemoteException;
    
    public boolean updateAmateurStatusInHistoryDets(String memberId, String amatName, boolean posAmat, boolean decAmat) throws RemoteException;
    public boolean insertWebSiteContactDetails(String userId, String address1, String address2,  String city, String  state, String country,
            String zip, String phoneNo, String mobileNo, String faxNo) throws RemoteException;
    
    public String[] getUserDetailsForEmail(String userId) throws RemoteException;
    
    public ArrayList getUserContactDetails(String userId) throws RemoteException;
    public ArrayList getMemberContactDetails(String memberId) throws RemoteException;
    public ArrayList getUserContactDetailsForAdmin(String userId) throws RemoteException;
    public String getNextId() throws RemoteException;
    public ArrayList searchUserByMemberId(String memberId) throws RemoteException ;
    
    //====Dhivya Here:Assign Role to user=====================================
    public ArrayList searchUserByGeneral(String firstName, String lastName, String email, String zip, Date fromDate, Date toDate, String roleId, String radMem) throws RemoteException ;
    public ArrayList searchUserByLoginName(String loginName, String radMem) throws RemoteException;
    //=============Ends Here=====================================================
    public HLCUserSearchResultVO getUserDetailsByUserId(String userId) throws RemoteException;
    public ArrayList getUserContactDetailsByUserCode(String userCode) throws RemoteException; 
    public Vector displayMemberType() throws RemoteException;
    public Vector displayMembershipType() throws RemoteException;
    public HashMap getHumanMembersStatusWise(Date fromDate,Date toDate) throws RemoteException;
    public HashMap getHumanMembersMemberShipsByStatus(Date fromDate,Date toDate,String status) throws RemoteException;
    public String getHumanMembersSpecificMembershipByStatus(Date fromDate,Date toDate,String status,String membershipType) throws RemoteException;
    public ArrayList getHumanMembersAreaWise(Date fromDate,Date toDate) throws RemoteException;
    public ArrayList getHumanMembersAllAreasStatusWise(Date fromDate,Date toDate,String status) throws RemoteException;
    public ArrayList getHumanMembersAllMemberhipTypesByArea(Date fromDate,Date toDate,String area_id) throws RemoteException;
    public ArrayList getHumanMembersAllMemberhipTypesByAreaAndStatus(Date fromDate,Date toDate,String area_id,String status_id) throws RemoteException;

    public int getHumanMembersSpecificAreaAndMembershipType(Date fromDate,Date toDate,String area_id,String membership_type_id) throws RemoteException;
    public int getHumanMembersSpecificAreaAndMembershipTypeAndStatus(Date fromDate,Date toDate,String area_id,String membership_type_id,String status_id) throws RemoteException;
     public int getTotalUsersRegister() throws RemoteException;
    public boolean isUserIdExist(String userId)throws RemoteException;
    public ArrayList getAreaProgramDetailsFrmHistory(String memId)throws RemoteException;
    public ArrayList getAreaZipDetails(String memId)throws RemoteException;
     public ArrayList getAreaProgBasedOnAgeAndProgYear(int age, int progYear, String areaId)throws RemoteException;
    public ArrayList getAreaProgramDetailsFrmTypeMaster(int tempProgYear, String areaId)throws RemoteException;
    public ArrayList getAreaZipDetailsBasedOnUserId(String userId)throws RemoteException;
    public ArrayList getMembershipStatusFromHistoryForAreaProgram(String membId) throws RemoteException;
    public boolean getAreaMembHistDetails(HLCMemberDetails objMember, HLCPaymentDetails objPayment)throws RemoteException;
    public boolean insertAreaMembProgram(HLCMemberDetails objMember, HLCPaymentDetails objPayment)throws RemoteException;
    public int updateUserRequestStatus(String userId) throws RemoteException;
    public int getRolesCountForUser(String userId) throws RemoteException;
    public int getUserCountBasedOnRole() throws RemoteException;
    public LinkedHashMap getMemberJoined(String year) throws RemoteException;
    public String getErrorDets(String errorCode) throws RemoteException;
    public boolean isInvoiceIdExist(String invoiceId) throws RemoteException;
    public abstract ArrayList getQuarterlySalesByItemForYear(String s, String s1)
            throws RemoteException;

        public abstract ArrayList getQuarterWiseSalesAllItemsForYear(String s, String s1)
            throws RemoteException;

        public abstract ArrayList getQuarterlyVolumeSalesByItemForYear(String s, String s1)
            throws RemoteException;

        public abstract ArrayList getStatusWisePurchaseRequisition(Date date, Date date1)
            throws RemoteException;

        public abstract Double getPurchaseRequisitionPerStatus(String s)
            throws RemoteException;

        public abstract HashMap getStockReqAndAvailable(Date date)
            throws RemoteException;

        public abstract ArrayList getDefectiveStocksSuppliedByEachVendorForYear(String s)
            throws RemoteException;

        public abstract ArrayList getStocksSuppliedByEachVendorForYear(String s)
            throws RemoteException;
        
        //==================Dhivya Here: User View Mapping================================
        
        public boolean addUserCriteria(String usrId, String usrCrit) throws RemoteException;
        public String getusrCriteria(String usrId) throws RemoteException;
        public boolean updateUserCriteria(String usrId, String viewPnt, String usrCrit) throws RemoteException;
        public ArrayList getUserViewPoints(String userId)throws RemoteException;
     
        public ArrayList getEmpInfBasedOnId(String empId)throws RemoteException;
        public boolean checkEmpIdExist(String empId) throws RemoteException;
        public ArrayList selectCountryList()throws RemoteException;
        public Vector selectLocBasedOnCId(String cId)throws RemoteException;
        public ArrayList selectLOBList()throws RemoteException;
        public Vector selectDeptSuperVisorBasedOnCId(String lobId,String depId,String roleGrade)throws RemoteException;
        public Vector selectLOBBasedOnCId(String baseId,String curId)throws RemoteException;
        public String addEmpRegistration(HLCEmployeeDetails objEmpMaster) throws RemoteException;
        public ArrayList searchEmpBySearchCriteria(String fName, String lName,String empId,String supName) throws RemoteException;
        public boolean addUserSupMapping(String userId,String supName,String supEmail,String roleId,String depId) throws RemoteException;
        public ArrayList getActiveUserEmpIds() throws RemoteException;
        
        
        
}
