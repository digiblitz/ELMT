/*
 * MemberUpdateDAO.java
 *
 * Created on September 16, 2006, 12:06 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcform.util;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.sql.*;
import java.util.*;
import java.util.Date;
import javax.naming.*;



/**
 *
 * @author harmohan
 */
public class HLCMemberUpdateDAO {
    
    //=================Member Details =============================
    
    private String memberId;
    private String userId;
    private String membershipTypeId;
    private String membershipTypeName;
    private String nonuseaOrgId;
    private String nonuseaOrgName;
    private String nonuseaOrgMemberId;
    private String countryMailTypeId;
    private String countryMailTypeName;
    private String familyAddOns = "0";
    private String parentMemberId;
    private String endowmentTrustAmount;
    private Boolean activeStatus;
    private Date addDate;
    private String statusId;
    private String statusName;
    private Date expiryDate;
    private String registerDate;
    private String loginDate;
    String dateOfBirth = null;
    private String firstName;
    private String middleName;
    private String lastName;
    private String paymentId;
    private String amateurName;
    private boolean amateurDec1;
    private boolean amateurDec2;
    private String armbandQty;
    
    private String userTypeId;
    private String userTypeName;
    
    private static final String dbName = "java:/ELMTMSSQLDS";
    private Connection con = null;
    private PreparedStatement prepStmt = null;
    private ResultSet rs = null;
    
    /** Creates a new instance of MemberUpdateDAO */
    public HLCMemberUpdateDAO() {
    }
    
    /**
     * Name         :isContactTypeExist
     * Description  :This method will insert record into the Horse Member Details table
     * @ param      :
     * @return      :void
     * @throws      :SQLException
     */
    public boolean isContactTypeExist(String contactTypeId, String userId) throws SQLException {
        boolean bol = false;
        Debug.print("MemberUpdateDAO isContactTypeExist ");
        try {
            makeConnection();
            
            String str = "SELECT contact_type_id FROM "+DBHelper.USEA_CONTACT_DETAILS+" WHERE contact_type_id = ? AND user_id = ?";
            prepStmt = con.prepareStatement(str);
            prepStmt.setString(1, contactTypeId);
            prepStmt.setString(2, userId);
            rs = prepStmt.executeQuery();
            if (bol = rs.next()) {
                this.userId = rs.getString(1);
                Debug.print("Contact type Id exist : "+bol);
            }
            
        }catch (Exception e){
            prepStmt.close();
            releaseConnection();
            Debug.print("Error While checking contactType Exist: "+e.getMessage());
            e.printStackTrace();
        }finally {
            prepStmt.close();
            releaseConnection();
        }
        return bol;
    }
    
    /**
     * Name         :isMemberExist
     * Description  :This method will insert record into the Horse Member Details table
     * @ param      :
     * @return      :void
     * @throws      :SQLException
     */
    public boolean isMemberExist(String userId) throws SQLException {
        boolean bol = false;
        Debug.print("MemberUpdateDAO isMemberExist ");
        try {
            makeConnection();
            
            String str = "SELECT member_id FROM " + DBHelper.USEA_MMS_MEMBERDETAIL+" WHERE user_id = ? ";
            prepStmt = con.prepareStatement(str);
            prepStmt.setString(1, userId);
            rs = prepStmt.executeQuery();
            if (bol = rs.next()) {
                String member_id = rs.getString(1);
                Debug.print("MemberId  is exist : "+bol);
            }
            
        }catch (Exception e){
            prepStmt.close();
            releaseConnection();
            Debug.print("Error While checking isMemberExist : "+e.getMessage());
            e.printStackTrace();
        }finally {
            prepStmt.close();
            releaseConnection();
        }
        Debug.print("MemberId  is exist : " + bol);
        return bol;
    }
    
    /**
     * Name         :isMemberExist
     * Description  :This method will insert record into the Horse Member Details table
     * @ param      :
     * @return      :void
     * @throws      :SQLException
     */
    public boolean isMemberExistBasedOnMemberAndUserId(String memberId, String userId) throws SQLException {
        boolean bol = false;
        Debug.print("MemberUpdateDAO isMemberExistBasedOnMemberAndUserId ");
        try {
            makeConnection();
            
            String str = "SELECT member_id FROM " + DBHelper.USEA_MMS_MEMBERDETAIL + " WHERE user_id = ? and member_id = ?";
            prepStmt = con.prepareStatement(str);
            prepStmt.setString(1, userId);
            prepStmt.setString(2, memberId);
            rs = prepStmt.executeQuery();
            if (bol = rs.next()) {
                String member_id = rs.getString(1);
                Debug.print("MemberId  is exist : "+bol);
            }
            
        }catch (Exception e){
            prepStmt.close();
            releaseConnection();
            Debug.print("Error While checking isMemberExistBasedOnMemberAndUserId : "+e.getMessage());
            e.printStackTrace();
        }finally {
            prepStmt.close();
            releaseConnection();
        }
        Debug.print("MemberId  is exist : " + bol);
        return bol;
    }
    /**
     * Name         :getHumanMemberDetails
     * Description  :This method will insert record into the Horse Member Details table
     * @ param      :
     * @return      :void
     * @throws      :SQLException
     */
//{memberId,userId,membershipTypeName,nonuseaOrgName,nonuseaOrgMemberId,countryMailTypeName,familyAddOns,parentMemberId,
    //endowmentTrustAmount,statusName };
    public HLCMemberDetails getHumanMemberDetail(String memberId) throws SQLException {
        this.memberId = memberId;
        List list = new ArrayList();
        HLCMemberDetails objMemVO = new HLCMemberDetails();
        HLCMemberDetails objMemVO1 = null;
        String [] memberDetail = loadHumanMemberDetails(memberId,null);
        objMemVO.setMemberId(memberDetail[0]);
        objMemVO.setUserId(memberDetail[1]);
        objMemVO.setMembershipTypeName(memberDetail[2]);
        objMemVO.setNonuseaOrgName(memberDetail[3]);
        objMemVO.setNonUseaOrgMemberId(memberDetail[4]);
        objMemVO.setCountryMailTypeName(memberDetail[5]);
        objMemVO.setFamilyAddOns(memberDetail[6]);
        objMemVO.setParentMemberId(memberDetail[7]);
        objMemVO.setEndowmentTrustAmount(memberDetail[8]);
        objMemVO.setStatusName(memberDetail[9]);
        objMemVO.setFirstName(memberDetail[10]);
        objMemVO.setMiddleName(memberDetail[11]);
        objMemVO.setLastName(memberDetail[12]);
        objMemVO.setPaymentId(memberDetail[13]);
        objMemVO.setAmateurName(memberDetail[14]);
        objMemVO.setAmateurDec1(Boolean.valueOf(memberDetail[15]).booleanValue());
        objMemVO.setAmateurDec2(Boolean.valueOf(memberDetail[16]).booleanValue());
        objMemVO.setArmbandQty(memberDetail[17]);
        
        Debug.print("Full Or Life member : \n"+objMemVO);
        
        try {
            makeConnection();
            String countQuery = "SELECT member_id  FROM "+DBHelper.USEA_MMS_MEMBERDETAIL+
                    " WHERE parent_member_id = '"+memberId+"'";
            prepStmt = con.prepareStatement(countQuery);
            ResultSet rs1 = prepStmt.executeQuery();
            
            int i=0;
            String totalRecords = "0";
            ArrayList memDet = new ArrayList();
            while (rs1.next()){
                String memId = rs1.getString(1);
                memDet.add(memId);
                Debug.print("Member Id in Loop:" + memId);
                i++;
            }
            rs1.close();
            prepStmt.close();
            releaseConnection();
            
            Iterator itMem = memDet.iterator();
            while(itMem.hasNext()){
                String addOnMemberId = (String)itMem.next();
                if(addOnMemberId!=null&& addOnMemberId.trim().length()!=0){
                    objMemVO1 = new HLCMemberDetails();
                    String memberAddOnDetail[] = loadHumanMemberDetails(addOnMemberId,null);
                    objMemVO1.setMemberId(addOnMemberId);
                    objMemVO1.setUserId(memberAddOnDetail[1]);
                    objMemVO1.setMembershipTypeName(memberAddOnDetail[2]);
                    objMemVO1.setNonuseaOrgName(memberAddOnDetail[3]);
                    objMemVO1.setNonUseaOrgMemberId(memberAddOnDetail[4]);
                    objMemVO1.setCountryMailTypeName(memberAddOnDetail[5]);
                    objMemVO1.setFamilyAddOns(memberAddOnDetail[6]);
                    objMemVO1.setParentMemberId(memberAddOnDetail[7]);
                    objMemVO1.setEndowmentTrustAmount(memberAddOnDetail[8]);
                    objMemVO1.setStatusName(memberAddOnDetail[9]);
                    objMemVO1.setFirstName(memberAddOnDetail[10]);
                    objMemVO1.setMiddleName(memberAddOnDetail[11]);
                    objMemVO1.setLastName(memberAddOnDetail[12]);
                    list.add(objMemVO1);
                    Debug.print("Family Add On : \n"+objMemVO1);
                }
            }
            objMemVO.setFamilyAddOn(list);
            objMemVO.setTotalRecord(""+i);
            
        }catch(Exception e){
            // prepStmt.close();
            //releaseConnection();
            //e.printStackTrace();
            Debug.print(" Error While getting details if Member details ..."+e.getMessage());
        }finally {
            //prepStmt.close();
            // releaseConnection();
        }
        return objMemVO;
    }
    
    
    /**
     * Name         :loadHumanMemberDetails
     * Description  :This method will insert record into the Horse Member Details table
     * @ param      :
     * @return      :void
     * @throws      :SQLException
     */
    public String[] loadHumanMemberDetails(String memberId,String parentMemberId) {//throws SQLException {
        //this.memberId = memberId1;
        //this.parentMemberId = parentMemberId1;
        String memId = null;
        Vector vObj = new Vector();
        Debug.print("MemberUpdateDAO loadHumanMemberDetails "+memberId);
        try {
            makeConnection();
            
            String selectStatement = "SELECT  A.member_id, A.user_id , A.membership_type_id , A.expiry_date ,A.nonusea_org_id , A.nonusea_org_member_id ,"+
                    " A.country_mail_type_id , A.family_add_ons , A.parent_member_id ,A.endowment_trust_amount , A.status_id, " +
                    "B.first_name, B.middle_name, B.last_name, A.payment_id ,  A.amateur_name, A.amateur_dec1, A.amateur_dec2,armband_qty  FROM "+
                    DBHelper.USEA_MMS_MEMBERDETAIL+" A, "+DBHelper.USEA_MMS_USERMASTER+" B ";
            
            boolean checkFlag = true;
            if (memberId != null && memberId.trim().length() > 0) {
                selectStatement +=((checkFlag)?" WHERE ":" AND ")+" A.member_id = '"+memberId+"' AND A.user_id = B.user_id ";
                checkFlag = false;
            }
            if (parentMemberId != null && parentMemberId.trim().length() > 0)
                selectStatement +=((checkFlag)?" WHERE ":" AND ")+" A.parent_member_id = '"+parentMemberId+"' AND A.user_id = B.user_id ";
            
            Debug.print("Query is : "+selectStatement);
            
            prepStmt = con.prepareStatement(selectStatement);
            //prepStmt.setString(1, memberId);
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                memId = rs.getString(1);
                Debug.print("Member ID : "+memberId);
                this.userId = rs.getString(2);
                Debug.print("User ID : "+userId);
                this.membershipTypeId = rs.getString(3);
                Debug.print("membershipTypeId : "+membershipTypeId);
                this.expiryDate = rs.getDate(4);
                Debug.print("expiryDate : "+expiryDate);
                this.nonuseaOrgId = rs.getString(5);
                Debug.print("nonuseaOrgId : "+nonuseaOrgId);
                this.nonuseaOrgMemberId = rs.getString(6);
                Debug.print("nonuseaOrgMemberId : "+nonuseaOrgMemberId);
                this.countryMailTypeId = rs.getString(7);
                Debug.print("countryMailTypeId : "+countryMailTypeId);
                this.familyAddOns = rs.getString(8);
                Debug.print("familyAddOns : "+familyAddOns);
                this.parentMemberId = rs.getString(9);
                Debug.print("parentMemberId : "+parentMemberId);
                this.endowmentTrustAmount = rs.getString(10);
                Debug.print("endowmentTrustAmount : "+endowmentTrustAmount);
                this.statusId = rs.getString(11);
                this.firstName = rs.getString(12);
                this.middleName = rs.getString(13);
                this.lastName = rs.getString(14);
                this.paymentId = rs.getString(15);
                this.amateurName = rs.getString(16);
                this.amateurDec1 = rs.getBoolean(17);
                this.amateurDec2 = rs.getBoolean(18);
                this.armbandQty = rs.getString(19);
                
                Debug.print("  amateurDec1 in SessionBean:"+ amateurDec1);
                Debug.print("  amateurDec2 in SesssionBean :"+ amateurDec2);
            }
            rs = null;
            String str = "SELECT membership_type_name FROM "+DBHelper.USEA_MEMBERSHIP_TYPE+" WHERE membership_type_id = ? ";
            prepStmt = con.prepareStatement(str);
            prepStmt.setString(1, membershipTypeId);
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                this.membershipTypeName = rs.getString(1);
                Debug.print("membershipTypeName : "+membershipTypeName);
            }
            rs = null;
            str = "SELECT other_org_name FROM tblOtherOrgMaster WHERE other_org_id = ? ";
            prepStmt = con.prepareStatement(str);
            prepStmt.setString(1, nonuseaOrgId);
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                this.nonuseaOrgName = rs.getString(1);
                Debug.print("nonuseaOrgName  : "+nonuseaOrgName);
            }
            rs = null;
            str = "SELECT country_mail_type_name FROM "+DBHelper.USEA_COUNTRY_MAIL_PRICE_MASTER+" WHERE country_mail_type_id = ? ";
            prepStmt = con.prepareStatement(str);
            prepStmt.setString(1, countryMailTypeId);
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                this.countryMailTypeName = rs.getString(1);
                Debug.print("countryMailTypeName : "+countryMailTypeName);
            }
            rs = null;
            str = "SELECT status_name FROM "+DBHelper.USEA_STATUS_MASTER+" WHERE status_id = ? ";
            prepStmt = con.prepareStatement(str);
            prepStmt.setString(1, statusId);
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                this.statusName = rs.getString(1);
                Debug.print("statusName : "+statusName);
            }
            
            rs.close();
            // String [] vecStr = {memberId,userId,membershipTypeName,nonuseaOrgName,nonuseaOrgMemberId,countryMailTypeName,familyAddOns,parentMemberId,endowmentTrustAmount,statusName };
            // vObj.add(vecStr);
            prepStmt.close();
            // releaseConnection();
        }catch (Exception e){
            //prepStmt.close();
            // releaseConnection();
            Debug.print("Error While loading the member details : "+e.getMessage());
            e.printStackTrace();
        }finally {
            // prepStmt.close();
            releaseConnection();
        }
        String [] vecStr = {memId,userId,membershipTypeName,nonuseaOrgName,nonuseaOrgMemberId,countryMailTypeName,
        familyAddOns,parentMemberId,endowmentTrustAmount,statusName,firstName,middleName,lastName,paymentId,
        amateurName, String.valueOf(amateurDec1), String.valueOf(amateurDec2),armbandQty};
        return vecStr;
    }
    //deleteMember(memberId)
    /**
     * Name         :deactivateStatus
     * Description  :This method will deactivate the active status of Member Details table
     * @ param      :member id
     * @return      :boolean
     * @throws      :SQLException
     */
    public boolean deleteMember(String memberId) throws SQLException {
        Debug.print("MemberUpdateDAO deleteMember");
        try {
            makeConnection();
            Debug.print("memberId  ID : "+memberId);
            String str = "DELETE FROM  "+DBHelper.USEA_MMS_MEMBERDETAIL +" WHERE member_id = ?";
            PreparedStatement prepStmt = con.prepareStatement(str);
            prepStmt.setString(1, memberId);
            
            Debug.print("Before calling executeUpdate Mathod in deleteMember");
            int cnt = prepStmt.executeUpdate();
            
            Debug.print("Succefully Deleted Record in member details......"+cnt);
            //  prepStmt.close();
        }catch (Exception e){
            
            releaseConnection();
            Debug.print("Error while deleting member details : "+e.getMessage());
        }finally {
            prepStmt.close();
            releaseConnection();
        }
        return true;
    }
    
    /**
     * Name         :deactivateStatus
     * Description  :This method will deactivate the active status of Member Details table
     * @ param      :member id
     * @return      :boolean
     * @throws      :SQLException
     */
    public boolean deleteMemberBasedOnUserId(String userId,String memId) throws SQLException {
        Debug.print("MemberUpdateDAO deleteMember");
        try {
            // makeConnection();
            Debug.print("memberId  ID : "+memId+"   AND User ID : "+userId);
            String str = "DELETE FROM  "+DBHelper.USEA_CONTACT_DETAILS +" WHERE user_id = ?";
            PreparedStatement prepStmt = con.prepareStatement(str);
            prepStmt.setString(1, userId);
            
            Debug.print("Before calling executeUpdate Mathod in deleteMember");
            int cnt = prepStmt.executeUpdate();
            
            Debug.print("Succefully Deleted Record in User details......"+cnt);
            
            str = "DELETE FROM  "+DBHelper.USEA_MMS_MEMBERDETAIL +" WHERE member_id = ?";
            prepStmt = con.prepareStatement(str);
            prepStmt.setString(1, memId);
            
            Debug.print("Before calling executeUpdate Mathod in deleteMember");
            cnt = prepStmt.executeUpdate();
            
            Debug.print("Succefully Deleted Record For Family member details......"+cnt);
            
            
            str = "DELETE FROM  "+DBHelper.USEA_MMS_USERMASTER +" WHERE user_id = ?";
            prepStmt = con.prepareStatement(str);
            prepStmt.setString(1, userId);
            
            Debug.print("Before calling executeUpdate Mathod in deleteMember");
            cnt = prepStmt.executeUpdate();
            
            Debug.print("Succefully Deleted Record in Contact details......"+cnt);
            //  prepStmt.close();
        }catch (Exception e){
            
            releaseConnection();
            Debug.print("Error while deleting member details : "+e.getMessage());
        }finally {
            prepStmt.close();
            releaseConnection();
        }
        return true;
    }
    
    /**
     * Name         :deleteMemberDetail
     * Description  :This method will delete the Member Details table based on member Id
     * @ param      :member id
     * @return      :boolean
     * @throws      :SQLException
     */
    public boolean deleteMemberDetail(String memberId) throws SQLException {
        Debug.print("MemberUpdateDAO deleteMember");
        try {
            makeConnection();
            
         /*
            String selStmt = "SELECT member_id,user_id FROM "+DBHelper.USEA_MMS_MEMBERDETAIL +" WHERE parent_member_id = ?";
            prepStmt = con.prepareStatement(selStmt);
            prepStmt.setString(1, memberId);
            rs = prepStmt.executeQuery();
            while (rs.next()) {
                String memId = rs.getString("member_id");
                String uId = rs.getString("user_id");
                this.userId = DBHelper.getUserIdBasedOnMemberId(con, memId);
                Debug.print(" In memberUpdateDAO User ID : "+userId);
                boolean  bol = deleteMemberBasedOnUserId(uId,memId);
                Debug.print("Delete Status : "+bol);
          
            }
          
          */
            Debug.print("memberId  ID : "+memberId);
            /*String str = "DELETE FROM  "+DBHelper.USEA_MMS_MEMBERDETAIL +" WHERE parent_member_id = ?";
            PreparedStatement prepStmt = con.prepareStatement(str);
            prepStmt.setString(1, memberId);
             
            Debug.print("Before calling executeUpdate Mathod in deleteMember");
            int cnt = prepStmt.executeUpdate();
             
            Debug.print("Succefully Deleted Family add on in member details......"+cnt);*/
            
            // String str = "DELETE FROM  "+DBHelper.USEA_MMS_MEMBERDETAIL +" WHERE member_id = ?";
            String str = "update  "+DBHelper.USEA_MMS_MEMBERDETAIL +" set  parent_member_id = null WHERE member_id = ?";
            prepStmt = con.prepareStatement(str);
            prepStmt.setString(1, memberId);
            
            Debug.print("Before calling executeUpdate Mathod in deleteMember");
            int cnt = prepStmt.executeUpdate();
            
            Debug.print("Succefully Deleted Record in member details......"+cnt);
            //  prepStmt.close();
        }catch (Exception e){
            
            // releaseConnection();
            Debug.print("Error while deleting member details : "+e.getMessage());
        }finally {
            prepStmt.close();
            releaseConnection();
        }
        return true;
    }
    /**
     * Name         :activateStatus
     * Description  :This method will activate the active status of Member Details table
     * @ param      :member id
     * @return      :boolean
     * @throws      :SQLException
     */
    public boolean activateStatus(String emailId) throws Exception {
        Debug.print("MemberUpdateDAO deactivateStatus");
        try {
            makeConnection();
            //con.setAutoCommit(false);
            Debug.print("memberId  ID : "+memberId);
            String str = "update  "+DBHelper.USEA_MMS_USERMASTER +" set active_status = ? WHERE email_id = ?";
            PreparedStatement prepStmt = con.prepareStatement(str);
            prepStmt.setBoolean(1, true);
            prepStmt.setString(2,emailId);
            
            Debug.print("Before calling executeUpdate Mathod in ActiveStatus");
            int cnt = prepStmt.executeUpdate();
            // con.commit();
            // con.setAutoCommit(true);
            Debug.print("Succefully Activate Status FOR  User....."+cnt);
            //  prepStmt.close();
            // releaseConnection();
        }catch (Exception e){
            prepStmt.close();
            releaseConnection();
            Debug.print("Error while updating member details : "+e.getMessage());
        }finally {
            prepStmt.close();
            releaseConnection();
        }
        return true;
    }
    /**
     * Name         :deactivateStatus
     * Description  :This method will deactivate the active status of Member Details table
     * @ param      :member id
     * @return      :boolean
     * @throws      :SQLException
     */
    public boolean deactivateStatus(String memberId) throws Exception {
        Debug.print("MemberUpdateDAO Activate The Status");
        try {
            makeConnection();
            //con.setAutoCommit(false);
            Debug.print("memberId  ID : "+memberId);
            String str = "update  "+DBHelper.USEA_MMS_MEMBERDETAIL +" set active_status = ? WHERE WHERE member_id = ?";
            PreparedStatement prepStmt = con.prepareStatement(str);
            prepStmt.setBoolean(1, false);
            prepStmt.setString(2,memberId);
            
            Debug.print("Before calling executeUpdate Mathod in deactiveStatus");
            int cnt = prepStmt.executeUpdate();
            Debug.print("Succefully Update into human member details......"+cnt);
        }catch (Exception e){
            prepStmt.close();
            releaseConnection();
            Debug.print("Error while updating member details : "+e.getMessage());
        }finally {
            prepStmt.close();
            releaseConnection();
        }
        return true;
    }
    /**
     * Name         :updateHumanMemberDetails
     * Description  :This method will insert record into the Horse Member Details table
     * @ param      :
     * @return      :void
     * @throws      :SQLException
     */
    
    public boolean updateHumanMemberDetails(HLCMemberDetails objMemDet) throws Exception {
        
        try {
            
            Debug.print("MemberUpdateDAO updateHumanMemberDetails");
            boolean renewalStatus = objMemDet.isRenewalStatus();
            Debug.print("objMemDet.isRenewalStatus() in bean :"+objMemDet.isRenewalStatus());
            SimpleDateFormat formatter;
            String expDt = "";
            java.sql.Date dt = null;
            Date subExDate=null;
            String memTyName = objMemDet.getMembershipTypeName();
            Debug.print("Membership Type Name:" + memTyName);
            formatter = new SimpleDateFormat("yyyy-MM-dd");
            subExDate =(Date) formatter.parse(objMemDet.getSubExpDate());
            //subExpDate =(Date) formatter.parse(membHistory.getSubExpDate());
            Debug.print("Subscribing Expiry Date in updateHumanMemberDetails" + subExDate);
            if(renewalStatus==true){
                Debug.print("renewalStatus in updateHumanMemberDetails" + renewalStatus);
                if(memTyName.equals("Subscribing Member")){
                    Debug.print("memTyName in updateHumanMemberDetails" + memTyName);
                    dt = DBHelper.toSQLDate(new DBHelper().calculateExpireDate(12,subExDate));
                }else if(memTyName.equals("Life Member")){
                    Debug.print("memTyName in updateHumanMemberDetails" + memTyName);
                    dt = DBHelper.toSQLDate(new DBHelper().calculateExpireDate(240,new Date()));
                }else{
                    expDt = DBHelper.getExpiryDate();
                    dt = java.sql.Date.valueOf(expDt);
                }
            }
            
            Debug.print("Expired Date:" + memTyName);
            
            if (objMemDet.getUserId() != null){
                this.userId = objMemDet.getUserId();
                Debug.print("objMemDet.getUserId() :  "+userId);
            }
            if (objMemDet.getMembershipTypeId() != null){
                this.membershipTypeId = objMemDet.getMembershipTypeId();
                Debug.print("objMemDet.getMembershipTypeId() :  "+membershipTypeId);
            }
            if (objMemDet.getNonuseaOrgId() != null){
                this.nonuseaOrgId = objMemDet.getNonuseaOrgId();
                Debug.print("objMemDet.getNonuseaOrgId() :  "+nonuseaOrgId);
            }
            if (objMemDet.getNonUseaOrgMemberId() != null){
                this.nonuseaOrgMemberId = objMemDet.getNonUseaOrgMemberId();
            }
            if (objMemDet.getCountryMailTypeId() != null){
                this.countryMailTypeId = objMemDet.getCountryMailTypeId();
            }
            if (objMemDet.getFamilyAddOns() != null){
                this.familyAddOns = objMemDet.getFamilyAddOns();
            }
            if (objMemDet.getParentMemberId() != null){
                this.parentMemberId = objMemDet.getParentMemberId();
            }
            if (objMemDet.getEndowmentTrustAmount() != null){
                this.endowmentTrustAmount = objMemDet.getEndowmentTrustAmount();
            }
            if (objMemDet.getStatusId() != null){
                this.statusId = objMemDet.getStatusId();
            }
            if (objMemDet.getMemberId() != null){
                this.memberId = objMemDet.getMemberId();
            }
            
            if (objMemDet.getPaymentId() != null){
                this.paymentId = objMemDet.getPaymentId();
            }
            
            Debug.print("objMemDet.getAmateurName() : "+objMemDet.getAmateurName());
            
            Debug.print("objMemDet.isAmateurDec1() : "+objMemDet.isAmateurDec1());
            
            Debug.print("objMemDet.isAmateurDec2(): "+objMemDet.isAmateurDec2());
            
            
            this.amateurName =objMemDet.getAmateurName();
            this.amateurDec1 =objMemDet.isAmateurDec1();
            this.amateurDec2 =objMemDet.isAmateurDec2();
            if (objMemDet.getArmbandQty() != null){
                this.armbandQty = objMemDet.getArmbandQty();
            } else {
                this.armbandQty = "0";
            }
            
            
            Debug.print(" Member Details in updateHumanMemberDetails...: \n"+objMemDet);
            makeConnection();
            this.statusId = DBHelper.getStatusId(con);
            releaseConnection();
            
            makeConnection();
            //con.setAutoCommit(false);
            Debug.print("memberId  ID : "+objMemDet.getMemberId());
            String str = "";
            if(renewalStatus==true){
                str = "update  "+DBHelper.USEA_MMS_MEMBERDETAIL +" set user_id = ?, membership_type_id = ?,  expiry_date = ?, "+
                        "nonusea_org_id = ?, nonusea_org_member_id = ?, country_mail_type_id = ?, family_add_ons = ?, parent_member_id = ?,"+
                        " status_id = ?, payment_id = ?, amateur_name = ?, amateur_dec1 = ? , amateur_dec2 = ?, armband_qty = ? " +
                        "   WHERE member_id = ?";
                PreparedStatement prepStmt = con.prepareStatement(str);
                Debug.print("Inside the Human Member Details ....\n\n ");
                
                prepStmt.setString(1, userId);
                Debug.print("User ID : "+userId);
                prepStmt.setString(2, membershipTypeId);
                Debug.print("MemberUpdateDAO membershipTypeId : "+membershipTypeId);
                prepStmt.setDate(3, dt);
                Debug.print("Date : "+dt);
                prepStmt.setString(4, nonuseaOrgId);
                Debug.print("nonuseaOrgId : "+nonuseaOrgId);
                prepStmt.setString(5, nonuseaOrgMemberId);
                Debug.print("MemberUpdateDAO nonuseaOrgMemberId : "+nonuseaOrgMemberId);
                prepStmt.setString(6, countryMailTypeId);
                Debug.print("countryMailTypeId : "+countryMailTypeId);
                prepStmt.setInt(7, Integer.parseInt(familyAddOns));
                Debug.print("MemberUpdateDAO familyAddOns : "+familyAddOns);
                prepStmt.setString(8, parentMemberId);
                Debug.print("parentMemberId : "+parentMemberId);
                //  prepStmt.setDouble(9,0);
                //  Debug.print("MemberUpdateDAO endowmentTrustAmount : "+endowmentTrustAmount);
                prepStmt.setString(9, statusId);
                Debug.print("Status ID : "+statusId);
                prepStmt.setString(10, paymentId);
                Debug.print("paymentId : "+paymentId);
                prepStmt.setString(11, amateurName);
                Debug.print("amateurName : "+amateurName);
                prepStmt.setBoolean(12, amateurDec1);
                Debug.print("amateurDec1 : "+amateurDec1);
                prepStmt.setBoolean(13, amateurDec2);
                Debug.print("amateurDec2: "+amateurDec2);
                prepStmt.setInt(14, Integer.parseInt(armbandQty));
                Debug.print("armbandQty : "+armbandQty);
                prepStmt.setString(15, memberId);
                Debug.print("Member ID : "+memberId);
                
                Debug.print("Before calling executeUpdate Mathod");
                int cnt = prepStmt.executeUpdate();
                // con.commit();
                // con.setAutoCommit(true);
                Debug.print("Succefully Update For renewal details......"+cnt);
                prepStmt.close();
            } else {
                Debug.print("MemberShip Type for UpGrade:"+memTyName);
                Date exDate = null;
                if(memTyName.equals("Life Member")){
                    exDate = DBHelper.toSQLDate(new DBHelper().calculateExpireDate(240,new Date()));
                } else{
                    exDate = new DBHelper().getExpDateForCurrentYear();
                }
                
                Debug.print("Expiry Date for Upgrade:" + exDate);
                Debug.print("Expiry Date for Upgrade:" + DBHelper.toSQLDate(exDate));
                
                str = "update  "+DBHelper.USEA_MMS_MEMBERDETAIL +" set user_id = ?, membership_type_id = ?, "+
                        "nonusea_org_id = ?, nonusea_org_member_id = ?, country_mail_type_id = ?, family_add_ons = ?, parent_member_id = ?,"+
                        " status_id = ?, payment_id = ?, amateur_name = ?, amateur_dec1 = ? , amateur_dec2 = ?, armband_qty = ?, expiry_date = ? WHERE member_id = ?";
                PreparedStatement prepStmt = con.prepareStatement(str);
                
                Debug.print("Inside the Human Member Details ....\n\n ");
                
                prepStmt.setString(1, userId);
                Debug.print("User ID : "+userId);
                prepStmt.setString(2, membershipTypeId);
                Debug.print("MemberUpdateDAO membershipTypeId : "+membershipTypeId);
                Debug.print("Date : "+dt);
                prepStmt.setString(3, nonuseaOrgId);
                Debug.print("nonuseaOrgId : "+nonuseaOrgId);
                prepStmt.setString(4, nonuseaOrgMemberId);
                Debug.print("MemberUpdateDAO nonuseaOrgMemberId : "+nonuseaOrgMemberId);
                prepStmt.setString(5, countryMailTypeId);
                Debug.print("countryMailTypeId : "+countryMailTypeId);
                prepStmt.setInt(6, Integer.parseInt(familyAddOns));
                Debug.print("MemberUpdateDAO familyAddOns : "+familyAddOns);
                prepStmt.setString(7, parentMemberId);
                Debug.print("parentMemberId : "+parentMemberId);
                // prepStmt.setDouble(8, Double.valueOf(endowmentTrustAmount).doubleValue());
                // Debug.print("MemberUpdateDAO endowmentTrustAmount : "+endowmentTrustAmount);
                prepStmt.setString(8, statusId);
                Debug.print("Status ID : "+statusId);
                prepStmt.setString(9, paymentId);
                Debug.print("paymentId : "+paymentId);
                prepStmt.setString(10, amateurName);
                Debug.print("amateurName : "+amateurName);
                prepStmt.setBoolean(11, amateurDec1);
                Debug.print("amateurDec1 : "+amateurDec1);
                prepStmt.setBoolean(12, amateurDec2);
                Debug.print("amateurDec2: "+amateurDec2);
                prepStmt.setString(13, armbandQty);
                Debug.print("armbandQty "+armbandQty);
                prepStmt.setDate(14, DBHelper.toSQLDate(exDate));
                prepStmt.setString(15, memberId);
                Debug.print("Member ID : "+memberId);
                
                Debug.print("Before calling executeUpdate Mathod");
                int cnt = prepStmt.executeUpdate();
                // con.commit();
                // con.setAutoCommit(true);
                Debug.print("Succefully Update for Upgrade human member details......"+cnt);
                prepStmt.close();
            }
            
            
            
        }catch (Exception e){
            //prepStmt.close();
            releaseConnection();
            e.printStackTrace();
            Debug.print("Error while updating member details : "+e.getMessage());
        }finally {
            // prepStmt.close();
            releaseConnection();
            Debug.print("HumanMemeber Details Successfully updated.");
        }
        return true;
    }
    
    /**
     * Name         :updatFamilyAddOn
     * Description  :This method will insert record into the Horse Member Details table
     * @ param      :
     * @return      :void
     * @throws      :SQLException
     */
    public boolean updatFamilyAddOn(String memberId,String paymentId) throws Exception {
        Debug.print("MemberUpdateDAO updateHumanMemberDetails");
        
        try {
            
            String expDt = DBHelper.getExpiryDate();
            java.sql.Date dt = java.sql.Date.valueOf(expDt);
            
            
            makeConnection();
            
            this.statusId = DBHelper.getStatusId(con);
            
            //con.setAutoCommit(false);
            Debug.print("memberId  ID : "+memberId);
            String str = "update  "+DBHelper.USEA_MMS_MEMBERDETAIL +" set expiry_date = ?, status_id = ?, payment_id = ? WHERE member_id = ?";
            PreparedStatement prepStmt = con.prepareStatement(str);
            Debug.print("Inside the Human Member Details ....\n\n ");
            prepStmt.setDate(1, dt);
            Debug.print("Expiry Date : "+dt);
            prepStmt.setString(2, statusId);
            Debug.print("statusId : "+statusId);
            prepStmt.setString(3, paymentId);
            Debug.print("paymentId : "+paymentId);
            prepStmt.setString(4, memberId);
            Debug.print("Member ID : "+memberId);
            
            Debug.print("Before calling executeUpdate Mathod");
            int cnt = prepStmt.executeUpdate();
            // con.commit();
            // con.setAutoCommit(true);
            Debug.print("Succefully UpdateFamily Add On member details......"+cnt);
            
            prepStmt.close();
            
        }catch (Exception e){
            
            releaseConnection();
            Debug.print("Error while updating member details : "+e.getMessage());
        }finally {
            //prepStmt.close();
            releaseConnection();
        }
        return true;
    }
    
    
    /**
     * Name         :updatMemberFamilyAddOns
     * Description  :This method will insert record into the Horse Member Details table
     * @ param      :
     * @return      :boolean
     * @throws      :SQLException
     */
    public boolean updatMemberFamilyAddOns(String memberId, int familyAddOns) throws Exception {
        Debug.print("MemberUpdateDAO updatMemberFamilyAddOns");
        boolean result = false;
        try {
            makeConnection();
            Debug.print("memberId  ID : "+memberId);
            Debug.print("familyAddOns : " + familyAddOns);
            String str = "update  " + DBHelper.USEA_MMS_MEMBERDETAIL + " set family_add_ons = ?  WHERE member_id = ?";
            PreparedStatement prepStmt = con.prepareStatement(str);
            prepStmt.setInt(1, familyAddOns);
            prepStmt.setString(2, memberId);
            
            int cnt = prepStmt.executeUpdate();
            if(cnt>=1){
                result = true;
            }
            Debug.print("Succefully updatMemberFamilyAddOns details......" + cnt);
            prepStmt.close();
            
        }catch (Exception e){
            releaseConnection();
            Debug.print("Error while updatMemberFamilyAddOns details : "+e.getMessage());
        }finally {
            //prepStmt.close();
            releaseConnection();
        }
        return result;
    }
    
    /**
     * Name         :changePasword
     * Description  :This method will change password for a user
     * @ param      : userId, pass
     * @return      :void
     * @throws      :SQLException
     */
    public boolean changePasword(String userId,String pass) throws Exception {
        Debug.print("MemberUpdateDAO changePasword");
        int cnt = 0;
        try {
            makeConnection();
            
            //con.setAutoCommit(false);
            if (  (pass != null && pass.trim().length() > 0) &&
                    ( userId != null && userId.trim().length() > 0 )) {
                String str = "update  "+DBHelper.USEA_MMS_USERMASTER+" set password = ?  WHERE user_id = ?";
                PreparedStatement prepStmt = con.prepareStatement(str);
                prepStmt.setString(1, pass);
                prepStmt.setString(2, userId);
                // prepStmt.setString(3, oldPass);
                Debug.print("Before calling executeUpdate Mathod");
                cnt = prepStmt.executeUpdate();
                // con.commit();
                // con.setAutoCommit(true);
                Debug.print("Succefully Password Changed......"+cnt);
                prepStmt.close();
            }
        }catch (Exception e){
            // prepStmt.close();
            releaseConnection();
            Debug.print("Error while change password  : "+e.getMessage());
        }finally {
            releaseConnection();
        }
        if (cnt >0)
            return true;
        else
            return false;
    }
    //displayContactetails(userId)
    /**
     * Name         :getUserId
     * Description  :This method will return user id based on email Id
     * @ param      :email id
     * @return      :String
     * @throws      :SQLException
     */
    
    
    
    public HLCContactDetails displayContactDetails(String userId) throws SQLException {
        HLCContactDetails objCont = new HLCContactDetails();
        Debug.print(" displayContactDetails() User Id:" + userId);
        try {
            makeConnection();
            
            String selectStatement = "SELECT A.contact_id, A.contact_type_id, A.suite,address1, A.address2, A.city,state," +
                    " A.country, A.zip, A.phone_no, A. mobile_no, A.fax_no FROM  " +DBHelper.USEA_CONTACT_DETAILS +
                    " A , tblUserMaster B  WHERE A.user_id = B.user_id and A.contact_type_id = B.contact_type_id and " +
                    " A.user_id = '"+userId+"'";
            prepStmt = con.prepareStatement(selectStatement);
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                objCont.setContactId(rs.getString("contact_id"));
                objCont.setContactTypeId(rs.getString("contact_type_id"));
                objCont.setSuite(rs.getString("suite"));
                objCont.setAddress1(rs.getString("address1"));
                objCont.setAddress2(rs.getString("address2"));
                objCont.setCity(rs.getString("city"));
                objCont.setState(rs.getString("state"));
                objCont.setCountry(rs.getString("country"));
                objCont.setZip(rs.getString("zip"));
                objCont.setPhoneNo(rs.getString("phone_no"));
                objCont.setMobileNo(rs.getString("mobile_no"));
                objCont.setFaxNo(rs.getString("fax_no"));
                
            }
            Debug.print("Contact Details \n"+objCont);
            prepStmt.close();
            
        } catch (SQLException sqe) {
            releaseConnection();
            Debug.print("Error while getting User Id : "+sqe.getMessage());
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return objCont;
    }
    
    
    /**
     * Name         :getUserId
     * Description  :This method will return user id based on email Id
     * @ param      :email id
     * @return      :String
     * @throws      :SQLException
     */
    public String getUserId(String emailId) throws SQLException {
        String userId = null;
        try {
            makeConnection();
            
            String selectStatement = "SELECT user_id FROM  " +DBHelper.USEA_MMS_USERMASTER +
                    "  WHERE email_id = ?";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, emailId.trim());
            
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                userId = rs.getString(1);
            }
            Debug.print("User Id in MemberUpdateDAO For mail Id: "+emailId+"  is : "+userId);
            prepStmt.close();
            
        } catch (SQLException sqe) {
            releaseConnection();
            Debug.print("Error while getting User Id : "+sqe.getMessage());
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return userId;
    }
    /**
     * Name         :listFamiltAddOnPrice
     * Description  :This method will return membership price based on member ship name
     * @ param      :member ship type name
     * @return      :String
     * @throws      :SQLException
     */
    public String[] listFamiltAddOnPrice(String typeName) throws SQLException {
        String memTypId = null;
        String memPrice = null;
        try {
            makeConnection();
            
            String selectStatement = "SELECT membership_type_id,membership_amount FROM  " +DBHelper.USEA_MEMBERSHIP_TYPE +
                    "  WHERE membership_type_name = ?";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, typeName);
            
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                memTypId = rs.getString(1);
                memPrice = rs.getString(2);
            }
            Debug.print("   "+typeName+"   Member ship Price:  "+memPrice);
            prepStmt.close();
            
        } catch (SQLException sqe) {
            releaseConnection();
            Debug.print("Error while getting User Id : "+sqe.getMessage());
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        String memDet[] = {memTypId,memPrice};
        return memDet;
    }
    
    
    /**
     * Name         :deleteContactDetail
     * Description  :This method will create the databacse connection
     * @ param      :
     * @return      :void
     * @throws      :EJBException
     */
    public boolean deleteContactDetail(String userId, String contactTypeId) throws SQLException {
        try {
            makeConnection();
            //USEA_MSS_DISTRIBUTION_LIST
            System.out.println("Inside deleteInventoryMaster userId :  "+userId);
            String deleteStatement = "DELETE FROM "+DBHelper.USEA_CONTACT_DETAILS+" WHERE contact_type_id = ? AND user_id = ?";
            PreparedStatement prepStmt = con.prepareStatement(deleteStatement);
            prepStmt.setString(1, contactTypeId);
            prepStmt.setString(2, userId);
            int cnt = prepStmt.executeUpdate();
            Debug.print("Successfully Delete the ContactDetail. No Of Record : "+cnt);
            
            prepStmt.close();
        } catch (Exception ex) {
            releaseConnection();
            Debug.print("deleteContactDetail : " + ex.getMessage());
        }finally{
            releaseConnection();
        }
        return true;
    }
    
    /**
     * Name         :getNonuseaOrgId
     * Description  :This method will return Non Usea Org id based on Non Usea Org name
     * @ param      :Non USEA Org name
     * @return      :String
     * @throws      :SQLException
     */
    public String getNonuseaOrgId(String nonuseaOrgName) throws SQLException {
        String nonuseaOrgId = null;
        try {
            makeConnection();
            String selectStatement = "SELECT nonusea_org_id FROM  " +DBHelper.USEA_NONUSEA_ORGMASTER +
                    "  WHERE nonusea_org_name = ?";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, nonuseaOrgName);
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                nonuseaOrgId = rs.getString(1);
            }
            Debug.print("Non USEA ORG Id: "+nonuseaOrgId+"  for Member Type  : "+nonuseaOrgName);
            prepStmt.close();
        } catch (SQLException sqe) {
            releaseConnection();
            Debug.print("Error while getting Non USEA ORG id : "+sqe.getMessage());
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return nonuseaOrgId;
    }
    
    /**
     * Name         :getEmailbasedOnDob
     * Description  :This method will return email id and password
     * @ param      :dob, secrete question and secret answer
     * @return      :String array
     * @throws      :SQLException
     */
    public String[] getEmailBasedOnEmail(String emailId) throws SQLException {
        String email = null;
        String pass =null;
        
        try {
            makeConnection();
            if (emailId != null && emailId.trim().length() > 0) {
                String selectStatement = "SELECT email_id, password FROM  "+DBHelper.USEA_MMS_USERMASTER+
                        "  WHERE email_id = '"+emailId+"'";
                prepStmt = con.prepareStatement(selectStatement);
                //prepStmt.setString(1, dob);
                // prepStmt.setString(2, secQes);
                //prepStmt.setString(3, secAns);
                Debug.print("Query is : \n"+selectStatement);
                
                rs = prepStmt.executeQuery();
                if (rs.next()) {
                    email = rs.getString("email_id");
                    pass = rs.getString("password");
                }
                Debug.print("Email Id: "+email+"  And Password  : "+pass);
            }
            prepStmt.close();
        } catch (SQLException sqe) {
            releaseConnection();
            sqe.printStackTrace();
            Debug.print("Error while getting email Id and Password : "+sqe.getMessage());
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        String[] emailArr  = new String[5];
        if ((email != null && email.trim().length() > 0) && (pass != null && pass.trim().length() > 0)){
            emailArr[0] = email;
            emailArr[1] = pass;
            emailArr[2] = "true";
        }else {
            emailArr[0] = null;
            emailArr[1] = null;
            emailArr[2] = "false";
        }
        return emailArr;
    }
    /**
     * Name         :getEmailbasedOnDob
     * Description  :This method will return email id and password
     * @ param      :dob, secrete question and secret answer
     * @return      :String array
     * @throws      :SQLException
     */
    public String[] getEmailbasedOnDob(String dob, String secQes, String secAns) throws SQLException {
        String email = null;
        String pass =null;
        boolean bol = false;
        String s1 = secQes;
        String s2 = "'";
        String s3 = "";
        String str1 = null;
        Debug.print(" Replace String : "+s1);
        if (s1 != null && s1.trim().length() > 0) {
            str1 = DBHelper.replace(s1,s2,s3);
            Debug.print(" Replace String : "+str1);
            int index = s1.indexOf("'");
            if (index >= 0){
                secQes = str1;
            }
        }
        Debug.print(" Final String : "+secQes);
        
        try {
            makeConnection();
            //"  WHERE dob = '"+dob+"' AND secret_question = '"+secQes+"' AND secret_answer = '"+secAns+"'";
            
            if ((dob != null && dob.trim().length() >0) && (secQes != null && secAns.trim().length() >0) && (secAns != null && secAns.trim().length() >0) ){
                
                String selectStatement = "SELECT email_id, password FROM  "+DBHelper.USEA_MMS_USERMASTER+
                        "  WHERE dob = '"+dob+"' AND secret_question = '"+secQes+"' AND secret_answer = '"+secAns+"'";
                prepStmt = con.prepareStatement(selectStatement);
                // prepStmt.setString(1, dob);
                //prepStmt.setString(2, secQes);
                //prepStmt.setString(3, secAns);
                Debug.print("Query is : \n"+selectStatement);
                rs = prepStmt.executeQuery();
                Debug.print("Result Set : "+rs);
                if (rs.next()) {
                    email = rs.getString("email_id");
                    pass = rs.getString("password");
                }
                rs.close();
                Debug.print("Email Id: "+email+"  And Password  : "+pass);
                prepStmt.close();
            }
            
        } catch (SQLException sqe) {
            releaseConnection();
            sqe.printStackTrace();
            Debug.print("Error while getting email Id and Password : "+sqe.getMessage());
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        String[] emailArr  = new String[5];
        if ((email != null && email.trim().length() > 0) && (pass != null && pass.trim().length() > 0)){
            emailArr[0] = email;
            emailArr[1] = pass;
            emailArr[2] = "true";
        }else {
            emailArr[0] = null;
            emailArr[1] = null;
            emailArr[2] = "false";
        }
        // String[] emailArr = {email,pass};
        return emailArr;
    }
    /**
     * Name         :getEmailPassword
     * Description  :This method will return email password based on dob, secret question and answer
     * @ param      :dob,
     * @return      :String
     * @throws      :SQLException
     */
    public String getCountryMailTypeId(String countryMailName) throws SQLException {
        String countryMailTypeId = null;
        try {
            makeConnection();
            String selectStatement = "SELECT country_mail_type_id FROM  " +DBHelper.USEA_COUNTRY_MAIL_PRICE_MASTER +
                    "  WHERE country_mail_type_name = ?";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, countryMailName);
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                countryMailTypeId = rs.getString(1);
            }
            rs.close();
            Debug.print("Country Mail type Id: "+countryMailTypeId+"  for Member Type  : "+countryMailName);
            prepStmt.close();
        } catch (SQLException sqe) {
            releaseConnection();
            Debug.print("Error while getting country mail type id : "+sqe.getMessage());
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return countryMailTypeId;
    }
    /**
     * Name         :getMembershipTypeId
     * Description  :This method will return membership type id based on membership type name
     * @ param      :membership type name
     * @return      :String
     * @throws      :SQLException
     */
    public String getMembershipTypeId(String memberTypeName) throws SQLException {
        String memberTypeId = null;
        try {
            makeConnection();
            String selectStatement = "SELECT membership_type_id FROM  " +DBHelper.USEA_MEMBERSHIP_TYPE +
                    "  WHERE membership_type_name = ?";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, memberTypeName);
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                memberTypeId = rs.getString(1);
            }
            rs.close();
            Debug.print("MemberShip type  Id: "+memberTypeId+"  for Member Type  : "+memberTypeName);
            prepStmt.close();
        } catch (SQLException sqe) {
            releaseConnection();
            Debug.print("Error while getting memberTypeId : "+sqe.getMessage());
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return memberTypeId;
    }
    
    public String getstatusId(String status) throws SQLException {
        String statusId = null;
        Debug.print("getstatusId in MemberUpdateDAO");
        try {
            makeConnection();
            String selectStatement = "SELECT status_id from " + DBHelper.USEA_STATUS_MASTER + " WHERE status_name = ? ";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, status);
            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()) {
                statusId = rs.getString(1);
            }
            rs.close();
            Debug.print("Status Id in MemberUpdateDAO : "+statusId+" For status Name : "+status);
            prepStmt.close();
        }catch (Exception e) {
            releaseConnection();
            Debug.print(" Error While getting status id : "+e.getMessage());
        }finally{
            releaseConnection();
        }
        return statusId;
    }
    
    public String selectStatusByMemberId(String memberId) throws SQLException {
        String statusName = "";
        Debug.print("getstatusId in selectStatusByMemberId");
        try {
            makeConnection();
            String selectStatement = "select B.status_name from " + DBHelper.USEA_MMS_MEMBERDETAIL + " A, " +
                    DBHelper.USEA_STATUS_MASTER + " B " +
                    " where A.status_id = B.status_id and A.member_id = ?";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, memberId);
            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()) {
                statusName = rs.getString(1);
            }
            rs.close();
            prepStmt.close();
        }catch (Exception e) {
            releaseConnection();
            Debug.print(" Error While getting statusName : "+e.getMessage());
        }finally{
            releaseConnection();
        }
        return statusName;
    }
    
    
    public  String getSecretQuestion(String userId) throws SQLException {
        String secretQuestionName = "";
        Debug.print("getstatusId in getSecretQuestion" + userId);
        try {
            makeConnection();
            String selectStatement = "SELECT secret_question FROM  " + DBHelper.USEA_MMS_USERMASTER +
                    "  WHERE  user_id = ?";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, userId);
            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()) {
                secretQuestionName = rs.getString(1);
            }
            rs.close();
            prepStmt.close();
        }catch (Exception e) {
            releaseConnection();
            Debug.print(" Error While getting statusName : "+e.getMessage());
        }finally{
            releaseConnection();
        }
        return secretQuestionName;
    }
    
    public  String getSecretQuestionByLoginName(String loginName) throws SQLException {
        String secretQuestionName= "";
        Debug.print("getstatusId in getSecretQuestion" + loginName);
        try {
            makeConnection();
            String selectStatement = "SELECT secret_question FROM  " + DBHelper.USEA_MMS_USERMASTER +
                    "  WHERE  login_name = ?";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, loginName.trim());
            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()) {
                secretQuestionName = rs.getString(1);
            }
            rs.close();
            prepStmt.close();
        }catch (Exception e) {
            releaseConnection();
            Debug.print(" Error While getting getSecretQuestionByLoginName : "+e.getMessage());
        }finally{
            releaseConnection();
        }
        return secretQuestionName;
    }
    
    
    /**
     * Name         :checkUserExist
     * Description  :This method will return User Details based on Firstname, LastName, DOB.
     * @ param      : firstName, lastName, dob
     * @return      :ArrayList
     * @throws      :SQLException
     */
    public ArrayList checkUserExist(String firstName, String lastName, String emailId, String zip) throws SQLException {
        ArrayList userList = new ArrayList();
        try {
            makeConnection();
            String selectStatement = "SELECT A.user_id, A.user_type_id, A.contact_type_id, A.prefix, A.first_name, A.middle_name,"+
                    "A.last_name, A.sufix, A.dob, A.gender, A.email_id, B.phone_no, A.secret_question, A.secret_answer FROM  " + DBHelper.USEA_MMS_USERMASTER +
                    " A , " + DBHelper.USEA_CONTACT_DETAILS  + " B WHERE A.user_id=B.user_id and A.first_name like ? " +
                    " and A.contact_type_id = B.contact_type_id  and A.last_name= ? and A.email_id = ? and B.zip = ? ";
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,firstName.trim());
            prepStmt.setString(2, lastName.trim());
            prepStmt.setString(3, emailId);
            prepStmt.setString(4, zip);
            
            Debug.print("Query:" + selectStatement);
            rs = prepStmt.executeQuery();
            while (rs.next()) {
                String userId = rs.getString(1);
                String userTypeId =  rs.getString(2);
                String contactTypeId =  rs.getString(3);
                String prefix = rs.getString(4);
                String firstNameVal = rs.getString(5);
                String middleName = rs.getString(6);
                String lastNameVal = rs.getString(7);
                String sufix = rs.getString(8);
                String dobVal = rs.getString(9);
                String gender = rs.getString(10);
                String emailIdVal = rs.getString(11);
                String phoneNo = rs.getString(12);
                String secretQuestion = rs.getString(13);
                String secretAnswer = rs.getString(14);
                
                HLCUserMaster objUserMaster = new HLCUserMaster();
                objUserMaster.setUserId(userId);
                objUserMaster.setUserTypeId(userTypeId);
                objUserMaster.setContactTypeId(contactTypeId);
                objUserMaster.setPrefix(prefix);
                objUserMaster.setFirstName(firstNameVal);
                objUserMaster.setMiddleName(middleName);
                objUserMaster.setLastName(lastNameVal);
                objUserMaster.setSufix(sufix);
                objUserMaster.setDob(dobVal);
                objUserMaster.setGender(gender);
                objUserMaster.setEmailId(emailIdVal);
                objUserMaster.setPhoneNo(phoneNo);
                objUserMaster.setSecretQuestion(secretQuestion);
                objUserMaster.setSecretAnswer(secretAnswer);
                userList.add(objUserMaster);
            }
            rs.close();
            prepStmt.close();
        } catch (SQLException sqe) {
            releaseConnection();
            Debug.print("Error while calling checkUserExist : "+sqe.getMessage());
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return userList;
    }
    
    /**
     * Name         :checkSecurityQuestion
     * Description  :This method will return boolean value based on secretQuestion, secretAnswer.
     * @ param      : secretQuestion, secretAnswer
     * @return      : boolean
     * @throws      :SQLException
     */
    public boolean checkSecurityQuestion(String userId, String secretQuestion, String secretAnswer) throws SQLException {
        Debug.print("MemberUpdateDAO checkSecurityQuestion() secretQuestion:" + secretQuestion);
        Debug.print("MemberUpdateDAO checkSecurityQuestion() secretAnswer:" + secretAnswer);
        Debug.print("MemberUpdateDAO checkSecurityQuestion() userId:" + userId);
        boolean result = false;
        try {
            makeConnection();
            
            String selectStatement = "SELECT user_id , secret_question, secret_answer FROM  " + DBHelper.USEA_MMS_USERMASTER +
                    "  WHERE secret_question = ? and secret_answer = ? and user_id = ?";
            
            prepStmt = con.prepareStatement(selectStatement);
            Debug.print("Query:" + selectStatement);
            prepStmt.setString(1, secretQuestion.trim());
            prepStmt.setString(2, secretAnswer.trim());
            prepStmt.setString(3, userId);
            
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                result = true;
            }
            rs.close();
            Debug.print("MemberUpdateDAO checkSecurityQuestion() result: " + result);
            prepStmt.close();
        } catch (SQLException sqe) {
            releaseConnection();
            Debug.print("Error while calling checkUserExist : "+sqe.getMessage());
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return result;
    }
    
    
    /**
     * Name         :checkSecurityQuestion
     * Description  :This method will return boolean value based on secretQuestion, secretAnswer.
     * @ param      : secretQuestion, secretAnswer
     * @return      : boolean
     * @throws      :SQLException
     */
    public boolean checkSecurityQuestionByLoginName(String loginName, String secretQuestion, String secretAnswer) throws SQLException {
        Debug.print("MemberUpdateDAO checkSecurityQuestionByLoginName() secretQuestion:" + secretQuestion);
        Debug.print("MemberUpdateDAO checkSecurityQuestionByLoginName() secretAnswer:" + secretAnswer);
        Debug.print("MemberUpdateDAO checkSecurityQuestionByLoginName() loginName:" + loginName);
        boolean result = false;
        try {
            makeConnection();
            
            String selectStatement = "SELECT login_name , secret_question, secret_answer FROM  " + DBHelper.USEA_MMS_USERMASTER +
                    "  WHERE secret_question = ? and secret_answer = ? and login_name = ?";
            
            prepStmt = con.prepareStatement(selectStatement);
            Debug.print(" Query checkSecurityQuestionByLoginName:" + selectStatement);
            prepStmt.setString(1, secretQuestion.trim());
            prepStmt.setString(2, secretAnswer.trim());
            prepStmt.setString(3, loginName.trim());
            
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                result = true;
            }
            rs.close();
            Debug.print("MemberUpdateDAO checkSecurityQuestionByLoginName() result: " + result);
            
            prepStmt.close();
        } catch (SQLException sqe) {
            releaseConnection();
            Debug.print("Error while calling checkSecurityQuestionByLoginName : "+sqe.getMessage());
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return result;
    }
    
    /**
     * Name         :checkUserNameExist
     * Description  :This method will return boolean value based on secretQuestion, secretAnswer.
     * @ param      : secretQuestion, secretAnswer
     * @return      : boolean
     * @throws      :SQLException
     */
    public boolean checkUserNameExist(String loginName) throws SQLException {
        Debug.print("MemberUpdateDAO checkUserNameExist() userName:" + loginName);
        boolean result = false;
        try {
            makeConnection();
            
            String selectStatement = "SELECT login_name FROM  " + DBHelper.USEA_MMS_USERMASTER +
                    "  WHERE  login_name = ?";
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, loginName);
            
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                result = true;
            }
            rs.close();
            Debug.print("MemberUpdateDAO checkUserNameExist() result: " + result);
            prepStmt.close();
        } catch (SQLException sqe) {
            releaseConnection();
            Debug.print("Error while calling checkUserNameExist : "+sqe.getMessage());
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return result;
    }
    
    /**
     * Name         :checkUserCodeExist
     * Description  :This method will return boolean value based on userCode.
     * @ param      : userCode
     * @return      : boolean
     * @throws      :SQLException
     */
    public boolean checkUserCodeExist(String userCode) throws SQLException {
        Debug.print("MemberUpdateDAO checkUserCodeExist() userCode:" + userCode);
        boolean result = false;
        try {
            makeConnection();
            
            String selectStatement = "SELECT user_code FROM  " + DBHelper.USEA_MMS_USERMASTER +
                    "  WHERE user_code = ?";
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, userCode);
            
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                result = true;
            }
            rs.close();
            Debug.print("MemberUpdateDAO checkUserCodeExist() result: " + result);
            prepStmt.close();
        } catch (SQLException sqe) {
            releaseConnection();
            Debug.print("Error while calling checkUserCodeExist : "+sqe.getMessage());
            //sqe.printStackTrace();
        } catch (Exception e) {
            releaseConnection();
            Debug.print("General Error while calling checkUserCodeExist : "+e.getMessage());
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return result;
    }
    
    //=============================================Insert  details=========================================
    public boolean updateLoginDetails(String userId, String loginName, String  password) {
        Debug.print("MemberUpdateDAO.updateLoginDetails():");
        PreparedStatement prepStmt = null;
        boolean result = false;
        makeConnection();
        try {
            String updateStatement = "update " + DBHelper.USEA_MMS_USERMASTER + " set login_name = ? , password = ? " +
                    " where user_id = ?";
            prepStmt = con.prepareStatement(updateStatement);
            prepStmt.setString(1, loginName);
            prepStmt.setString(2, password);
            prepStmt.setString(3, userId);
            int cnt = prepStmt.executeUpdate();
            
            if(cnt==1){
                result = true;
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in MemberUpdateDAO.updateLoginDetails():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in MemberUpdateDAO.updateLoginDetails():" + e.getMessage());
        }
        return result;
    }
    
    //=============================================Insert  details=========================================

    public boolean deactivateRequestStatus(String userId, boolean active_status)
    {
        Debug.print("MemberUpdateDAO.deactivateRequestStatus():");
        PreparedStatement prepStmt = null;
        boolean result = false;
        makeConnection();
        System.out.println("before try deactivateRequestStatus");
        try
        {
            System.out.println("after try deactivateRequestStatus");
            String updateStatement = "update tblUserMaster set request_status= 'false'  where user_id = ? and active_status=?";
            System.out.println((new StringBuilder()).append("Query is ---------->").append(updateStatement).toString());
            prepStmt = con.prepareStatement(updateStatement);
            System.out.println((new StringBuilder()).append("User id is =========>").append(userId).append("  Active status ==============>").append(active_status).toString());
            prepStmt.setString(1, userId);
            prepStmt.setBoolean(2, active_status);
            System.out.println("Before execute Update statement");
            int cnt = prepStmt.executeUpdate();
            System.out.println((new StringBuilder()).append("After execute Update statement count is ").append(cnt).toString());
            if(cnt == 1)
                result = true;
            rs.close();
            prepStmt.close();
            releaseConnection();
        }
        catch(SQLException sql)
        {
            releaseConnection();
            Debug.print((new StringBuilder()).append("SQL Exception in MemberUpdateDAO.deactivateRequestStatus").append(sql.getMessage()).toString());
        }
        catch(Exception e)
        {
            releaseConnection();
            Debug.print((new StringBuilder()).append("General Exception  in MemberUpdateDAO.deactivateRequestStatus").append(e.getMessage()).toString());
        }
        return result;
    }
     //=============================================Insert  details=========================================
    

    public boolean updateLoginDetailsByLoginName(String loginName, String  password) {
        Debug.print("MemberUpdateDAO.updateLoginDetailsByLoginName():");
        PreparedStatement prepStmt = null;
        boolean result = false;
        makeConnection();
        try {
            String updateStatement = "update " + DBHelper.USEA_MMS_USERMASTER + " set password = ? " +
                    " where login_name = ?";
            prepStmt = con.prepareStatement(updateStatement);
            prepStmt.setString(1, password);
            prepStmt.setString(2, loginName);
            int cnt = prepStmt.executeUpdate();
            
            if(cnt==1){
                result = true;
            }
            
            prepStmt.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in MemberUpdateDAO.updateLoginDetailsByLoginName():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in MemberUpdateDAO.updateLoginDetailsByLoginName():" + e.getMessage());
        }
        return result;
    }
    
    //=============================================Insert  details=========================================
    public String[] selectLoginDetails(String userId) {
        Debug.print("MemberUpdateDAO.selectLoginDetails():");
        PreparedStatement prepStmt = null;
        String [] loginDet = {};
        makeConnection();
        try {
            String selectStatement = "SELECT login_name,password FROM  " + DBHelper.USEA_MMS_USERMASTER +
                    "  WHERE  user_id = ?";
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, userId);
            
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                String loginName = rs.getString(1);
                String password = rs.getString(2);
                String tempLoginDet[] = {loginName, password};
                loginDet = tempLoginDet;
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in MemberUpdateDAO.selectLoginDetails():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in MemberUpdateDAO.selectLoginDetails():" + e.getMessage());
        }
        return loginDet;
    }
    //=============================================Insert  details=========================================
    public String selectPasswordByLoginName(String loginName) {
        Debug.print("MemberUpdateDAO.selectPasswordByLoginName():");
        PreparedStatement prepStmt = null;
        String password = "";
        makeConnection();
        try {
            String selectStatement = "SELECT password FROM  " + DBHelper.USEA_MMS_USERMASTER +
                    "  WHERE  login_name = ?";
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, loginName.trim());
            
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                password = rs.getString(1);
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in MemberUpdateDAO.selectPasswordByLoginName():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in MemberUpdateDAO.selectPasswordByLoginName():" + e.getMessage());
        }
        return password;
    }
    
    //=============================================Insert  details=========================================
    public String selectEmailByLoginName(String loginName) {
        Debug.print("MemberUpdateDAO.selectEmailByLoginName():");
        PreparedStatement prepStmt = null;
        String password = "";
        makeConnection();
        try {
            String selectStatement = "SELECT email_id FROM  " + DBHelper.USEA_MMS_USERMASTER +
                    "  WHERE  login_name = ?";
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, loginName.trim());
            
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                password = rs.getString(1);
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in MemberUpdateDAO.selectEmailByLoginName():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in MemberUpdateDAO.selectEmailByLoginName():" + e.getMessage());
        }
        return password;
    }
     //=============================================Insert  details=========================================
    public String selectUserIDByLoginName(String loginName) {
        Debug.print("MemberUpdateDAO.selectUserIDByLoginName():");
        PreparedStatement prepStmt = null;
        String password = "";
        makeConnection();
        try {
            String selectStatement = "SELECT user_id FROM  " + DBHelper.USEA_MMS_USERMASTER +
                    "  WHERE  login_name = ?";
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, loginName.trim());
            
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                password = rs.getString(1);
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in MemberUpdateDAO.selectUserIDByLoginName():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in MemberUpdateDAO.selectUserIDByLoginName():" + e.getMessage());
        }
        return password;
    }
    
    //=============================================Insert  details=========================================
    public ArrayList selectLoginDetailsByEmailId(String emailId) {
        Debug.print("MemberUpdateDAO.selectLoginDetails():");
        PreparedStatement prepStmt = null;
        ArrayList loginDet = new ArrayList();
        makeConnection();
        try {
            String selectStatement = "SELECT login_name FROM  " + DBHelper.USEA_MMS_USERMASTER +
                    "  WHERE  email_id = ?";
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, emailId);
            
            rs = prepStmt.executeQuery();
            while (rs.next()) {
                String loginName = rs.getString(1);
                loginDet.add(loginName);
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in MemberUpdateDAO.selectLoginDetails():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in MemberUpdateDAO.selectLoginDetails():" + e.getMessage());
        }
        return loginDet;
    }
    //=============================================Insert  details=========================================
    public ArrayList selectMebershipTypeBasedOnPeriodValue(String userTypeId, int periodValue, String statusGrade, int memb_year) {
        Debug.print("MemberUpdateDAO.selecttMebershipTypeBasedOnPeriodValue() :");
        Debug.print("periodValue :"+periodValue);
        
        Debug.print("memb_year :"+memb_year);
        
        PreparedStatement prepStmt = null;
        ArrayList memberList = new ArrayList();
        makeConnection();
        try {
            
               /*Calendar tCal = Calendar.getInstance();
               int curYr = tCal.get(Calendar.YEAR);
               Debug.print("current Year :"+curYr);*/
            
            String selectStatement = "SELECT membership_type_id, membership_type_name, membership_amount, " +
                    " user_type_id, priority_value FROM  tblMembershipTypeMaster " +
                    " WHERE  user_type_id = ? and active_status = ? and membership_year = ? ";
            
            Debug.print("selectStatement :"+selectStatement);
            
            if(statusGrade.equalsIgnoreCase("upgrade")){
                selectStatement = selectStatement + "and priority_value>? order by membership_type_name";
            } else{
                selectStatement = selectStatement + "and priority_value<? order by membership_type_name";
            }
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, userTypeId);
            prepStmt.setBoolean(2, true);
            prepStmt.setInt(3, memb_year);
            prepStmt.setInt(4, periodValue);
            
            Debug.print("********** Query Starts **************");
            Debug.print("SELECT membership_type_id, membership_type_name, membership_amount, user_type_id, priority_value " +
                    "FROM  tblMembershipTypeMaster WHERE  user_type_id = "+userTypeId+" and active_status = true and " +
                    "membership_year = "+memb_year);
            
            if(statusGrade.equalsIgnoreCase("upgrade")){
                Debug.print("and priority_value>"+periodValue+" order by membership_type_name");
            } else{
                Debug.print("and priority_value<"+periodValue+" order by membership_type_name");
            }
            Debug.print("********** Query Ends **************");
            
            rs = prepStmt.executeQuery();
            while (rs.next()) {
                String memTypeId = rs.getString(1);
                String memTypeName = rs.getString(2);
                String memAmt = rs.getString(3);
                String userTypeIdVal= rs.getString(4);
                String periodValueVal = rs.getString(5);
                String [] memberDetails = {memTypeId, memTypeName, memAmt, userTypeIdVal, periodValueVal};
                memberList.add(memberDetails);
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in MemberUpdateDAO.selecttMebershipTypeBasedOnPeriodValue():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in MemberUpdateDAO.selecttMebershipTypeBasedOnPeriodValue():" + e.getMessage());
        }
        return memberList;
    }
    
    //=============================================Insert User Registration Details =========================================
    public String insertUserDetails(String firstName, String lastName, java.util.Date dob, String  gender,
            String emailId,  String prefix, String sufix, String user_type_id) {
        Debug.print("MemberUpdateDAO.insertUserDetails():");
        boolean result = false;
        PreparedStatement prepStmt = null;
        String userId  = "";
        try{
            userId = getNextId();
        } catch(Exception e){
            Debug.print("Exception while calling getNextId():" + e.getMessage());
        }
        
        Debug.print("MemberUpdateDAO.insertUserDetails() After Getting userId :" + userId);
        if(userId!=null && userId.trim().length()!=0){
            makeConnection();
            try {
                String insertStatement = "insert into " + DBHelper.USEA_MMS_USERMASTER + " (user_id,  first_name,"+
                        "last_name,dob,gender,email_id, prefix, sufix, contact_type_id, user_type_id)" +
                        " values ( ? , ? , ? , ? , ?, ? , ? , ? , ? , ?) ";
                
                prepStmt = con.prepareStatement(insertStatement);
                
                
                prepStmt.setString(1, userId);
                prepStmt.setString(2, firstName);
                prepStmt.setString(3, lastName);
                prepStmt.setDate(4, DBHelper.toSQLDate(dob));
                prepStmt.setString(5, gender);
                prepStmt.setString(6, emailId);
                prepStmt.setString(7, prefix);
                prepStmt.setString(8, sufix);
                prepStmt.setString(9, "0cc42d09-340c-42e8-b773-54bdf0688527");
                prepStmt.setString(10, user_type_id);
                
                int cnt = prepStmt.executeUpdate();
                Debug.print("Record Inserted succefully into insertUserDetails "+cnt);
                result = true;
                
                Debug.print("MemberUpdateDAO insertUserDetails() Status :" + result);
                prepStmt.close();
                releaseConnection();
            } catch(SQLException sql){
                releaseConnection();
                Debug.print("SQL Exception in MemberUpdateDAO.insertUserDetails():" + sql.getMessage());
            } catch(Exception e){
                releaseConnection();
                Debug.print("General Exception  in MemberUpdateDAO.insertUserDetails():" + e.getMessage());
            }
        }
        return userId;
    }
    
    
    //=============================================Insert User Contact Details =========================================
    public boolean insertContactDetails(String userId, String address1, String address2,  String city, String  state, String country,
            String zip, String phoneNo, String mobileNo, String faxNo) {
        Debug.print("MemberUpdateDAO.insertContactDetails():");
        boolean result = false;
        PreparedStatement prepStmt = null;
        if(userId!=null && userId.trim().length()!=0){
            makeConnection();
            try {
                String insertStatement = "insert into " + DBHelper.USEA_CONTACT_DETAILS + " (user_id, address1, address2,"+
                        " city, state, country, zip, phone_no, mobile_no, fax_no, contact_type_id)" +
                        " values ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?) ";
                prepStmt = con.prepareStatement(insertStatement);
                prepStmt.setString(1, userId);
                prepStmt.setString(2, address1);
                prepStmt.setString(3, address2);
                prepStmt.setString(4, city);
                prepStmt.setString(5, state);
                prepStmt.setString(6, country);
                prepStmt.setString(7, zip);
                prepStmt.setString(8, phoneNo);
                prepStmt.setString(9, mobileNo);
                prepStmt.setString(10, faxNo);
                prepStmt.setString(11, "0cc42d09-340c-42e8-b773-54bdf0688527");
                
                
                int cnt = prepStmt.executeUpdate();
                Debug.print("Record Inserted succefully into insertContactDetails "+cnt);
                result = true;
                
                Debug.print("MemberUpdateDAO insertContactDetails() Status :" + result);
                prepStmt.close();
                releaseConnection();
            } catch(SQLException sql){
                releaseConnection();
                Debug.print("SQL Exception in MemberUpdateDAO.insertContactDetails():" + sql.getMessage());
            } catch(Exception e){
                releaseConnection();
                Debug.print("General Exception  in MemberUpdateDAO.insertContactDetails():" + e.getMessage());
            }
        }
        return result;
    }
    
    public String getNextId() throws SQLException {
        Debug.print("MemberUpdateDAO getNextUserId");
        makeConnection();
        String nextId = null;
        try{
            String selectStatement = "select newid() as userId";
            Debug.print("MemberUpdateDAO getNextUserId:" + selectStatement);
            PreparedStatement prepSelect = con.prepareStatement(selectStatement);
            ResultSet rs = prepSelect.executeQuery();
            rs.next();
            nextId = rs.getString(1);
            rs.close();
            prepSelect.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in getNextUserId:" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in getNextUserId:" + e.getMessage());
        }
        return nextId;
    }
    
    public String getUserType(String userTypeName) throws SQLException {
        Debug.print("MemberUpdateDAO getUserType userTypeName:" + userTypeName);
        makeConnection();
        String userTypeId = "";
        try{
            String selectStatement = "SELECT user_type_id FROM "+DBHelper.USEA_MMS_TYPEMASTER+" where user_type_name = ?";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, userTypeName);
            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()) {
                userTypeId = rs.getString(1);
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in getUserType:" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in getUserType:" + e.getMessage());
        }
        Debug.print(" MemberUpdateDAO UserTypeId:" + userTypeId);
        return userTypeId;
    }
    
    
    public Date selectExpiredDate(String memberId) throws SQLException {
        Debug.print("MemberUpdateDAO selectExpiredDate:" + memberId);
        makeConnection();
        Date expDate = null;
        try{
            String selectStatement = "SELECT expiry_date FROM tblMemberDetails where member_id = ?";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, memberId);
            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()) {
                expDate = rs.getDate(1);
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in selectExpiredDate:" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in selectExpiredDate:" + e.getMessage());
        }
        Debug.print(" MemberUpdateDAO selectExpiredDate:" + expDate);
        return expDate;
    }
    
    
    //=============================================Update Payment Details form admin =========================================
    public boolean updatePaymentStatus(HLCPaymentDetailVO objPaymentVO) {
        Debug.print("AnnualMeetingDAO.updatePaymentStatus VO Value():" + objPaymentVO.toString());
        
        boolean result = false;
        PreparedStatement prepStmt = null;
        makeConnection();
        
        try {
            String updateStatement = "update  tblUserPaymentDetails " +
                    " set  cc_name = ?, cc_type = ?, cc_number = ?, cc_exp_month = ? ," +
                    " cc_exp_year = ?, cc_cvvid = ?, bank_name = ?, check_date = ?, check_number = ?, " +
                    " check_name = ?, " +
                    " ssl_result = ? , ssl_result_message = ? ,ssl_txn_id= ?," +
                    " ssl_approval_code = ? , ssl_cvv2_response = ? , ssl_avs_response = ?, " +
                    " ssl_transaction_type = ?, ssl_invoice_no = ? , ssl_email = ?, " +
                    "  payment_status = ?, payment_date = ?, parent_payment_id=?," +
                    "  ip_address= ?  where  payment_id = ?";
            
            prepStmt = con.prepareStatement(updateStatement);
            
            prepStmt.setString(1, objPaymentVO.getCcName());
            prepStmt.setString(2, objPaymentVO.getCcType());
             // following code for changing the card no from real to dummy as per client saying in mail 13-March-2008.
            String ccNumber=objPaymentVO.getCcNumber();
             if (!(ccNumber.equals("0"))) {
            String temp = ccNumber.substring(0, 2);
            String temp1 = ccNumber.substring(2, 12);
            String temp2 = ccNumber.substring(12);
            temp1 = "***";
            ccNumber = temp+temp1+temp2;
             }
            Debug.print(ccNumber+"=testing ccnumber for renewal");
            // code ends here for card no. change
            prepStmt.setString(3,ccNumber );
            prepStmt.setInt(4, objPaymentVO.getCcExpMonth());
            prepStmt.setInt(5, objPaymentVO.getCcExpYear());
            // DO NOT write/store ANY CVV information in the tblUserPaymentDetails.So commented. as in email 13-March-2008.
            
            //int ccCvvid = objPaymentVO.getCcCvvid();
            int ccCvvid = 0;
            prepStmt.setInt(6, ccCvvid);
            prepStmt.setString(7, objPaymentVO.getBankName());
            if(objPaymentVO.getCheckDate()!=null){
                prepStmt.setDate(8, DBHelper.toSQLDate(objPaymentVO.getCheckDate()));
            } else{
                prepStmt.setDate(8, null);
            }
            prepStmt.setString(9, objPaymentVO.getCheckNumber());
            prepStmt.setString(10, objPaymentVO.getCheckName());
            //prepStmt.setDouble(11, objPaymentVO.getAmount());
            
            prepStmt.setString(11, objPaymentVO.getSslResult());
            prepStmt.setString(12, objPaymentVO.getSslResultMessage());
            prepStmt.setString(13, objPaymentVO.getSslTxnId());
            prepStmt.setString(14, objPaymentVO.getSslApprovalCode());
            prepStmt.setString(15, objPaymentVO.getSslCvv2Response());
            prepStmt.setString(16, objPaymentVO.getSslAvsResponse());
            prepStmt.setString(17, objPaymentVO.getSslTransactionType());
            prepStmt.setString(18, objPaymentVO.getSslInvoiceNo());
            prepStmt.setString(19, objPaymentVO.getSslEmail());
            prepStmt.setString(20, objPaymentVO.getPaymentStatus());
            prepStmt.setDate(21, DBHelper.toSQLDate(new Date()));
            prepStmt.setString(22, objPaymentVO.getParentPaymentId());
            prepStmt.setString(23, objPaymentVO.getIpAddress());
            prepStmt.setString(24, objPaymentVO.getPaymentId());
            
            
            int cnt = prepStmt.executeUpdate();
            Debug.print("Record Updated succefully in updatePaymentStatus : " + cnt);
            if(cnt>=1){
                result = true;
            }
            
            Debug.print("HorseRegDAO updatePaymentStatus Status :" + result);
            prepStmt.close();
            releaseConnection();
        }
        
        catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in AnnualMeetingDAO.updatePaymentStatus():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in AnnualMeetingDAO.updatePaymentStatus():" + e.getMessage());
        }
        return result;
    }
    
    /**
     * Name         :updateLoginDate
     * Description  :This method will update the login date of a user in user master table
     * @ param      :userId
     * @return      :boolean
     * @throws      :Exception
     */
    public boolean updateLoginDate(String userId) throws Exception {
        Debug.print("MemberUpdateDAO updateLoginDate() :");
        try {
            makeConnection();
            
            Debug.print("Login Date : "+DBHelper.toSQLDate(new Date()));
            Debug.print("userId : "+userId);
            
            String str = "update  "+DBHelper.USEA_MMS_USERMASTER +" set login_date = ? WHERE user_id = ?";
            PreparedStatement prepStmt = con.prepareStatement(str);
            prepStmt.setDate(1, DBHelper.toSQLDate(new Date()));
            prepStmt.setString(2, userId);
            
            Debug.print("Before calling executeUpdate Mathod in updateLoginDate");
            int cnt = prepStmt.executeUpdate();
            
            Debug.print("Succefully updated login date FOR User....."+cnt);
            
            prepStmt.close();
            
        }catch (Exception e){
            
            releaseConnection();
            Debug.print("Error while updating user login date details : "+e.getMessage());
        }finally {
            
            releaseConnection();
        }
        return true;
    }
    
    /**
     * Name         :updateLoginDate
     * Description  :This method will update the login date of a user in user master table
     * @ param      :userId
     * @return      :boolean
     * @throws      :Exception
     */
    public boolean updateNSFPendingAmt(String paymentId,float amount) throws SQLException {
        Debug.print("MemberUpdateDAO updateNSFPendingAmt() :");
        boolean status = false;
        float originalAmt = 0;
        float pendingAmt = 0;
        
        try {
            makeConnection();
            PreparedStatement prepStmt=null;
            ResultSet rs= null;
            
            Debug.print("Getting actual amount and pending amount from Top payment :");
            String str = "select amount, pending_amount from "+DBHelper.USEA_PAYMENT_DETAILS +" WHERE payment_id = ?";
            Debug.print("Query Log :"+str);
            
            prepStmt = con.prepareStatement(str);
            prepStmt.setString(1, paymentId);
            rs = prepStmt.executeQuery();
            
            if(rs.next()){
                originalAmt = rs.getFloat(1);
                pendingAmt = rs.getFloat(2);
            }
            
            Debug.print("originalAmt :"+originalAmt);
            Debug.print("pendingAmt :"+pendingAmt);
            
            //float calcPendAmt = amount+pendingAmt;
            float calcChkAmt = originalAmt-amount;
            Debug.print("calcChkAmt :"+calcChkAmt);
            
            rs.close();
            prepStmt.close();
            releaseConnection();
            
            makeConnection();
            prepStmt=null;
            
            Debug.print("Updating pending amount and check amount to Top payment :");
            String str1 = "update  "+DBHelper.USEA_PAYMENT_DETAILS +" set pending_amount = ?, check_amount = ? WHERE payment_id = ?";
            Debug.print("Query Log :"+str1);
            
            prepStmt = con.prepareStatement(str1);
            prepStmt.setFloat(1, amount);
            prepStmt.setFloat(2, calcChkAmt);
            prepStmt.setString(3, paymentId);
            
            int cnt = prepStmt.executeUpdate();
            if(cnt>0)
                status = true;
            
            Debug.print("Succefully updated updateNSFPendingAmt cnt :"+cnt);
            
            prepStmt.close();
            
        }catch (SQLException e){
            
            releaseConnection();
            Debug.print("SQLException while updating updateNSFPendingAmt details in DAO : "+e.getMessage());
        } catch (Exception e){
            
            releaseConnection();
            Debug.print("Error while updating updateNSFPendingAmt details in DAO: "+e.getMessage());
        }finally {
            
            releaseConnection();
        }
        return status;
    }
    
    /**
     * Name         :updateLoginDate
     * Description  :This method will update the login date of a user in user master table
     * @ param      :userId
     * @return      :boolean
     * @throws      :Exception
     */
    public boolean updateAddOnExistingMember(String memberId,String statusName,String parentId,String paymentId,String memb_typ_id) throws Exception {
        Debug.print("MemberUpdateDAO updateAddOnExistingMember() :");
        try {
            
            Debug.print("memberId :"+memberId);
            Debug.print("statusName :"+statusName);
            Debug.print("parentId :"+parentId);
            Debug.print("paymentId :"+paymentId);
            Debug.print("memb_typ_id :"+memb_typ_id);
            
            /*String typId=getMembershipTypeId("Family Member");
            Debug.print("getMembershipTypeId(Family Member) :"+typId);*/
            
            java.sql.Date dt = null;
            String expDt = DBHelper.getExpiryDate();
            dt = java.sql.Date.valueOf(expDt);
            Debug.print("Exp Date :"+dt);
            
            String ststId=getstatusId(statusName);
            Debug.print("getstatusId(statusName) :"+ststId);
            
            makeConnection();
            
            String str = "update  "+DBHelper.USEA_MMS_MEMBERDETAIL+" set membership_type_id = ?, expiry_date = ?, status_id = ?, parent_member_id = ?, payment_id = ?  WHERE member_id = ?";
            PreparedStatement prepStmt = con.prepareStatement(str);
            
            prepStmt.setString(1, memb_typ_id);
            prepStmt.setDate(2, dt);
            prepStmt.setString(3, ststId);
            prepStmt.setString(4, parentId);
            prepStmt.setString(5, paymentId);
            prepStmt.setString(6, memberId);
            
            Debug.print("Before calling executeUpdate Mathod in updateAddOnExistingMember");
            int cnt = prepStmt.executeUpdate();
            
            Debug.print("Succefully updated updateAddOnExistingMember ....."+cnt);
            
            prepStmt.close();
            
        }catch (Exception e){
            
            releaseConnection();
            Debug.print("Error while MemberUpdateDAO updateAddOnExistingMember details : "+e.getMessage());
        }finally {
            
            releaseConnection();
        }
        return true;
    }
    
    /**
     * Name         :updateAmatureStatus
     * Description  :This method will update the login date of a user in user master table
     * @ param      :userId
     * @return      :boolean
     * @throws      :Exception
     */
    public boolean updateAmatureStatus(String memberId,String amatName,boolean posAmat,boolean decAmat) throws Exception {
        Debug.print("MemberUpdateDAO updateAmatureStatus() :");
        try {
            makeConnection();
            
            Debug.print("memberId : "+memberId);
            
            String str = "update  "+DBHelper.USEA_MMS_MEMBERDETAIL +" set amateur_name = ?, amateur_dec1 = ? , amateur_dec2 = ? WHERE member_id = ?";
            PreparedStatement prepStmt = con.prepareStatement(str);
            
            prepStmt.setString(1,amatName);
            Debug.print("amatName :"+amatName);
            prepStmt.setBoolean(2,posAmat);
            Debug.print("posAmat :"+posAmat);
            prepStmt.setBoolean(3,decAmat);
            Debug.print("decAmat :"+decAmat);
            prepStmt.setString(4,memberId);
            Debug.print("memberId :"+memberId);
            
            Debug.print("Before calling executeUpdate Mathod in updateAmatureStatus");
            int cnt = prepStmt.executeUpdate();
            
            Debug.print("Succefully updated updateAmatureStatus for member id :"+memberId+" :"+cnt);
            
            prepStmt.close();
            
        }catch (Exception e){
            
            releaseConnection();
            Debug.print("Error while updating updateAmatureStatus details : "+e.getMessage());
        }finally {
            
            releaseConnection();
        }
        return true;
    }
    
    //=================================Update Amateur Status in History Details Table======================================
    
     public boolean updateAmateurStatusInHistoryDets(String memberId, String amatName, boolean posAmat, boolean decAmat) throws Exception {
        Debug.print("MemberUpdateDAO updateAmateurStatusInHistoryDets() :");
        try {
            makeConnection();
            
            Debug.print("memberId : "+memberId);
            
          String str = "update  "+DBHelper.USEA_MEMBERSHIP_HISTORY_DETAILS +" set amateur_name = ?, amateur_dec1 = ? , amateur_dec2 = ? WHERE member_id = ? and active_status = ?";
          PreparedStatement prepStmt = con.prepareStatement(str);
            
            prepStmt.setString(1,amatName);
            Debug.print("amatName :"+amatName);
            prepStmt.setBoolean(2,posAmat);
            Debug.print("posAmat :"+posAmat);
            prepStmt.setBoolean(3,decAmat);
            Debug.print("decAmat :"+decAmat);
            prepStmt.setString(4,memberId);
            Debug.print("memberId :"+memberId);
            prepStmt.setBoolean(5,true);           
            
            Debug.print("Before calling executeUpdate Mathod in updateAmateurStatusInHistoryDets");
            int cnt = prepStmt.executeUpdate();
            
            Debug.print("Succefully updated updateAmateurStatusInHistoryDets for member id :"+memberId+" :"+cnt);
            
            prepStmt.close();
            
        }catch (Exception e){
            
            releaseConnection();
            Debug.print("Error while updating updateAmateurStatusInHistoryDets details : "+e.getMessage());
        }finally {
            
            releaseConnection();
        }
        return true;
    }
    
    /**
     * Name         :updateApprovalDate
     * Description  :This method will update the login date of a user in user master table
     * @ param      :userId
     * @return      :boolean
     * @throws      :Exception
     */
    public boolean updateApprovalDate(String memberId,Date approveDate) throws Exception {
        Debug.print("MemberUpdateDAO updateApprovalDate() :");
        try {
            makeConnection();
            
            Debug.print("memberId : "+memberId);
            
            String str = "update  "+DBHelper.USEA_MMS_MEMBERDETAIL +" set activation_date = ? WHERE member_id = ?";
            PreparedStatement prepStmt = con.prepareStatement(str);
            
            prepStmt.setDate(1,DBHelper.toSQLDate(approveDate));
            Debug.print("approveDate :"+approveDate);
            prepStmt.setString(2,memberId);
            Debug.print("memberId :"+memberId);
            
            Debug.print("Before calling executeUpdate Mathod in updateApprovalDate");
            int cnt = prepStmt.executeUpdate();
            
            Debug.print("Succefully updated updateApprovalDate for member id :"+memberId+" :"+cnt);
            
            prepStmt.close();
            
        }catch (Exception e){
            
            releaseConnection();
            Debug.print("Error while updating updateApprovalDate details : "+e.getMessage());
        }finally {
            
            releaseConnection();
        }
        return true;
    }
    
    /**
     * Name         :updateNSFPayment
     * Description  :This method will update payment based on the payment Id
     * @ param      :PaymentDetailVO
     * @return      :boolean
     * @throws      :SQLException
     */
    public boolean updateNSFPayment(HLCPaymentDetailVO objPaymentVO) throws SQLException {
        
        boolean stat = false;
        Debug.print("MemberUpdateDAO.updateNSFPayment() :");
        
        try {
            
            PreparedStatement prepStmt = null;
            makeConnection();
            
            String updateStatement = "update "+DBHelper.USEA_PAYMENT_DETAILS+
                    " set  cc_name = ?, cc_type = ?, cc_number = ?, cc_exp_month = ? ," +
                    " cc_exp_year = ?, cc_cvvid = ?, bank_name = ?, check_date = ?, check_number = ?, " +
                    " check_name = ?, " +
                    " ssl_result = ? , ssl_result_message = ? ,ssl_txn_id= ?," +
                    " ssl_approval_code = ? , ssl_cvv2_response = ? , ssl_avs_response = ?, " +
                    " ssl_transaction_type = ?, ssl_invoice_no = ? , ssl_email = ?, " +
                    "  payment_status = ?, ip_address= ?, pending_amount = ?, nsf_charge_status = ? where payment_id = ?";
            
            prepStmt = con.prepareStatement(updateStatement);
            Debug.print("Query Log :"+updateStatement);
            
            prepStmt.setString(1, objPaymentVO.getCcName());
            prepStmt.setString(2, objPaymentVO.getCcType());
            // following code for changing the card no from real to dummy as per client saying in mail 13-March-2008.
            String ccNumber=objPaymentVO.getCcNumber();
             if (!(ccNumber.equals("0"))) {
            String temp = ccNumber.substring(0, 2);
            String temp1 = ccNumber.substring(2, 12);
            String temp2 = ccNumber.substring(12);
            temp1 = "***";
            ccNumber = temp+temp1+temp2;
             }
            Debug.print(ccNumber+"=testing ccnumber for renewal");
            // code ends here for card no. change
            prepStmt.setString(3,ccNumber );
            prepStmt.setInt(4, objPaymentVO.getCcExpMonth());
            prepStmt.setInt(5, objPaymentVO.getCcExpYear());
            // DO NOT write/store ANY CVV information in the tblUserPaymentDetails.So commented. as in email 13-March-2008.
            
            //int ccCvvid = objPaymentVO.getCcCvvid();
            int ccCvvid = 0;
            prepStmt.setInt(6, ccCvvid);
            prepStmt.setString(7, objPaymentVO.getBankName());
            if(objPaymentVO.getCheckDate()!=null){
                prepStmt.setDate(8, DBHelper.toSQLDate(objPaymentVO.getCheckDate()));
            } else{
                prepStmt.setDate(8, null);
            }
            prepStmt.setString(9, objPaymentVO.getCheckNumber());
            prepStmt.setString(10, objPaymentVO.getCheckName());
            //prepStmt.setDouble(11, objPaymentVO.getAmount());
            
            prepStmt.setString(11, objPaymentVO.getSslResult());
            prepStmt.setString(12, objPaymentVO.getSslResultMessage());
            prepStmt.setString(13, objPaymentVO.getSslTxnId());
            prepStmt.setString(14, objPaymentVO.getSslApprovalCode());
            prepStmt.setString(15, objPaymentVO.getSslCvv2Response());
            prepStmt.setString(16, objPaymentVO.getSslAvsResponse());
            prepStmt.setString(17, objPaymentVO.getSslTransactionType());
            prepStmt.setString(18, objPaymentVO.getSslInvoiceNo());
            prepStmt.setString(19, objPaymentVO.getSslEmail());
            prepStmt.setString(20, objPaymentVO.getPaymentStatus());
               /* prepStmt.setDate(21, DBHelper.toSQLDate(new Date()));
                prepStmt.setString(22, objPaymentVO.getParentPaymentId());*/
            prepStmt.setString(21, objPaymentVO.getIpAddress());
            prepStmt.setFloat(22, objPaymentVO.getPendingAmount());
            prepStmt.setBoolean(23, objPaymentVO.isNsf_charge_status());
            prepStmt.setString(24, objPaymentVO.getPaymentId());
            
            int cnt = prepStmt.executeUpdate();
            Debug.print("Record Updated succefully in updatePaymentStatus cnt : " + cnt);
            if(cnt>=1){
                stat = true;
            }
            
            Debug.print("MemberUpdateDAO updatePaymentStatus Status :" + stat);
            prepStmt.close();
            releaseConnection();
            
        } catch (SQLException e){
            
            Debug.print("SQLException while updatePaymentStatus in DAO : "+e.getMessage());
        } catch (Exception e){
            
            Debug.print("general exception while updatePaymentStatus in DAO : "+e.getMessage());
        }finally {
            //prepStmt.close();
            releaseConnection();
        }
        return stat;
    }
    
    public boolean updateNSFChargeStatus(String paymentId) throws SQLException {
        
        boolean stat = false;
        Debug.print("MemberUpdateDAO.updateNSFChargeStatus() :");
        
        try {
            
            PreparedStatement prepStmt = null;
            makeConnection();
            
            String updateStatement = "update "+DBHelper.USEA_PAYMENT_DETAILS+
                    " set nsf_charge_status = ?, nsf_date = getdate() where payment_id = ?";
            
            prepStmt = con.prepareStatement(updateStatement);
            Debug.print("Query Log :"+updateStatement);
            
            prepStmt.setBoolean(1, true);
            prepStmt.setString(2, paymentId);
            
            int cnt = prepStmt.executeUpdate();
            Debug.print("Record Updated succefully in updateNSFChargeStatus cnt : " + cnt);
            if(cnt>=1){
                stat = true;
            }
            
            Debug.print("MemberUpdateDAO updateNSFChargeStatus Status :" + stat);
            prepStmt.close();
            
        } catch (SQLException e){
            
            Debug.print("SQLException while updateNSFChargeStatus in DAO : "+e.getMessage());
        } catch (Exception e){
            
            Debug.print("general exception while updateNSFChargeStatus in DAO : "+e.getMessage());
        }finally {
            //prepStmt.close();
            releaseConnection();
        }
        return stat;
    }
    
    /**
     * Name         :getLastSubPaymentIdOnParentId
     * Description  :This method will get the most recent sub payment's payment id and returns.
     * @ param      :parentPaymentId
     * @return      :String
     * @throws      :SQLException
     */
    public String getLastSubPaymentIdOnParentId(String parentPaymentId) throws SQLException {
        
        Debug.print("MemberUpdateDAO.getLastSubPaymentIdOnParentId() : "+parentPaymentId);
        String paymentId = "";
        
        try {
            makeConnection();
            
            String str = "select TOP 1 payment_id from "+DBHelper.USEA_PAYMENT_DETAILS+" where parent_payment_id = ? order by payment_date DESC";
            Debug.print("Query Log :"+str);
            
            prepStmt = con.prepareStatement(str);
            prepStmt.setString(1, parentPaymentId);
            
            rs = prepStmt.executeQuery();
            while (rs.next()) {
                paymentId = rs.getString(1);
                Debug.print("Top selected paymentId : "+paymentId);
            }
            
        } catch (SQLException ex){
            prepStmt.close();
            releaseConnection();
            Debug.print("SQL Exception in MemberUpdateDAO.getLastSubPaymentIdOnParentId() : ");
            ex.printStackTrace();
        } catch (Exception e){
            prepStmt.close();
            releaseConnection();
            Debug.print("General Exception in MemberUpdateDAO.getLastSubPaymentIdOnParentId() : ");
            e.printStackTrace();
        } finally {
            prepStmt.close();
            releaseConnection();
        }
        return paymentId;
    }
    
    /**
     * Name         :getNSFCheckDetails
     * Description  :This method will get NSF Check Details based on the payment Id
     * @ param      :paymentId
     * @return      :PaymentDetailVO
     * @throws      :RemoteException
     */
    public HLCPaymentDetailVO getNSFCheckDetails(String paymentId) throws SQLException {
        
        Debug.print("MemberUpdateDAO.getNSFCheckDetails() : "+paymentId);
        
        HLCPaymentDetailVO paymentdet = new HLCPaymentDetailVO();
        
        try {
            makeConnection();
            
            String str = "select TOP 1 check_amount, check_number, payment_status, check_name, check_date, bank_name, nsf_date, pending_amount, payment_id, parent_payment_id, reverse_entry_status from "
                    +DBHelper.USEA_PAYMENT_DETAILS+" where nsf_charge_status = ? AND nsf_status = ? AND "
                    +"( payment_id = ? OR parent_payment_id = ? ) order by nsf_date desc ";
            
            Debug.print("Query Log :"+str);
            
            prepStmt = con.prepareStatement(str);
            prepStmt.setBoolean(1, true);
            prepStmt.setBoolean(2, false);
            prepStmt.setString(3, paymentId);
            prepStmt.setString(4, paymentId);
            
            rs = prepStmt.executeQuery();
            while (rs.next()) {
                
                paymentdet.setCheckAmount(rs.getFloat(1));
                paymentdet.setCheckNumber(rs.getString(2));
                paymentdet.setPaymentStatus(rs.getString(3));
                paymentdet.setCheckName(rs.getString(4));
                paymentdet.setCheckDate(rs.getDate(5));
                paymentdet.setBankName(rs.getString(6));
                paymentdet.setNsfDate(rs.getDate(7));
                paymentdet.setPendingAmount(rs.getFloat(8));
                paymentdet.setPaymentId(rs.getString(9));
                paymentdet.setParentPaymentId(rs.getString(10));
                paymentdet.setReverse_entry_status(rs.getBoolean(11));
            }
            
        } catch (SQLException ex){
            prepStmt.close();
            releaseConnection();
            Debug.print("SQL Exception in MemberUpdateDAO.getNSFCheckDetails() : ");
            ex.printStackTrace();
        } catch (Exception e){
            prepStmt.close();
            releaseConnection();
            Debug.print("General Exception in MemberUpdateDAO.getNSFCheckDetails() : ");
            e.printStackTrace();
        } finally {
            prepStmt.close();
            releaseConnection();
        }
        return paymentdet;
    }
    
    /**
     * Name         :updateCheckAmountOnPaymentId
     * Description  :This method will update check amt on payment id.
     * @ param      :paymentId, check_amt
     * @return      :boolean
     * @throws      :SQLException
     */
    
    public boolean updateCheckAmountOnPaymentId(String paymentId, float check_amt) throws SQLException {
        
        boolean stat = false;
        Debug.print("MemberUpdateDAO.updateCheckAmountOnPaymentId() :");
        Debug.print("paymentId :"+paymentId);
        Debug.print("check_amt :"+check_amt);
        
        try {
            
            PreparedStatement prepStmt = null;
            makeConnection();
            
            String updateStatement = "update "+DBHelper.USEA_PAYMENT_DETAILS+
                    " set check_amount = ? where payment_id = ?";
            
            prepStmt = con.prepareStatement(updateStatement);
            Debug.print("Query Log :"+updateStatement);
            
            prepStmt.setFloat(1, check_amt);
            prepStmt.setString(2, paymentId);
            
            int cnt = prepStmt.executeUpdate();
            Debug.print("Record Updated succefully in updateCheckAmountOnPaymentId cnt : " + cnt);
            if(cnt>=1){
                stat = true;
            }
            
            Debug.print("MemberUpdateDAO updateCheckAmountOnPaymentId Status :" + stat);
            prepStmt.close();
            
        } catch (SQLException e){
            
            Debug.print("SQLException while updateCheckAmountOnPaymentId in DAO : "+e.getMessage());
        } catch (Exception e){
            
            Debug.print("general exception while updateCheckAmountOnPaymentId in DAO : "+e.getMessage());
        }finally {
            //prepStmt.close();
            releaseConnection();
        }
        return stat;
    }
    
    /**
     * Name         :updateNSFChargeStatusForSubSequentPayments
     * Description  :This method will update nsf_charge_status on parent_payment_id and amt.
     * @ param      :parentPaymentId, amount
     * @return      :boolean
     * @throws      :SQLException
     */
    
    public boolean updateNSFChargeStatusForSubSequentPayments(String parentPaymentId, float amount) throws SQLException {
        
        boolean stat = false;
        Debug.print("MemberUpdateDAO.updateNSFChargeStatusForSubSequentPayments() :");
        Debug.print("parentPaymentId :"+parentPaymentId);
        Debug.print("amount :"+amount);
        
        try {
            
            PreparedStatement prepStmt = null;
            makeConnection();
            
            String updateStatement = "update tblUserPaymentDetails set nsf_charge_status = ?" +
                    " where parent_payment_id = ? and amount < ?";
            
            prepStmt = con.prepareStatement(updateStatement);
            Debug.print("Query Log :"+updateStatement);
            
            prepStmt.setBoolean(1, true);
            prepStmt.setString(2, parentPaymentId);
            prepStmt.setFloat(3, amount);
            
            int cnt = prepStmt.executeUpdate();
            Debug.print("Record Updated succefully in updateNSFChargeStatusForSubSequentPayments cnt : " + cnt);
            if(cnt>=1){
                stat = true;
            }
            
            Debug.print("MemberUpdateDAO updateNSFChargeStatusForSubSequentPayments Status :" + stat);
            prepStmt.close();
            
        } catch (SQLException e){
            
            Debug.print("SQLException while updateNSFChargeStatusForSubSequentPayments in DAO : "+e.getMessage());
        } catch (Exception e){
            
            Debug.print("general exception while updateNSFChargeStatusForSubSequentPayments in DAO : "+e.getMessage());
        }finally {
            //prepStmt.close();
            releaseConnection();
        }
        return stat;
    }
    
    /**
     * Name         :updateReverseEntryStatus
     * Description  :This method will update Reverse Entry Status on payment id.
     * @ param      :paymentId
     * @return      :boolean
     * @throws      :SQLException
     */
    
    public boolean updateReverseEntryStatus(String paymentId) throws SQLException {
        
        boolean stat = false;
        Debug.print("MemberUpdateDAO.updateReverseEntryStatus() :");
        Debug.print("paymentId :"+paymentId);
        
        try {
            
            PreparedStatement prepStmt = null;
            makeConnection();
            
            String updateStatement = "update "+DBHelper.USEA_PAYMENT_DETAILS+
                    " set reverse_entry_status = ? where payment_id = ?";
            
            prepStmt = con.prepareStatement(updateStatement);
            Debug.print("Query Log :"+updateStatement);
            
            prepStmt.setBoolean(1, true);
            prepStmt.setString(2, paymentId);
            
            int cnt = prepStmt.executeUpdate();
            Debug.print("Record Updated succefully in updateReverseEntryStatus cnt : " + cnt);
            if(cnt>=1){
                stat = true;
            }
            
            Debug.print("MemberUpdateDAO updateReverseEntryStatus Status :" + stat);
            prepStmt.close();
            
        } catch (SQLException e){
            
            Debug.print("SQLException while updateReverseEntryStatus in DAO : "+e.getMessage());
        } catch (Exception e){
            
            Debug.print("general exception while updateReverseEntryStatus in DAO : "+e.getMessage());
        }finally {
            //prepStmt.close();
            releaseConnection();
        }
        return stat;
    }
    
    /**
     * Name         :insertMemberHistoryDetails
     * Description  :This method insert Member History Details
     * @ param      :MemberHistoryDetail
     * @return      :boolean
     * @throws      :EJBException
     */
    
    public boolean insertMemberHistoryDetails(HLCMemberHistoryDetail membHistory) throws SQLException{
        Debug.print("MemberUpdateDAO.insertMemberHistoryDetails():");
        boolean result = false;
        PreparedStatement prepStmt = null;
        Debug.print("membHistory.toString() :"+membHistory.toString());
        java.sql.Date toDt = null;
        java.sql.Date fromDt = null;
        Date subExpDate = null;
        Calendar cal = new GregorianCalendar();
        SimpleDateFormat formatter;
        boolean active_status = true;
        
        makeConnection();
        
        try {
            
            if(membHistory.getMembership_action().equalsIgnoreCase("Register")) {
                Debug.print("MemberUpdateDAO.insertMemberHistoryDetails() : Inside Register Block ");
                
                if(membHistory.getMembership_type_name().equalsIgnoreCase("Subscribing Member")) {
                    fromDt = DBHelper.toSQLDate(new Date());
                    Debug.print("from Date for Subscribing Member renewal is :"+fromDt.toString());
                    
                    toDt = DBHelper.toSQLDate(new DBHelper().calculateExpireDate(12,new Date()));
                    Debug.print("to Date for Subscribing Member renewal is :"+toDt.toString());
                } else {
                    int year = cal.get(Calendar.YEAR);
                    formatter = new SimpleDateFormat("yyyy-MM-dd");
                    Debug.print("Current year is : "+year);
                    
                       /* String fromDate = "2007-01-12";
                        fromDate = year+fromDate.substring(4,10);
                        Date dt2 =(Date) formatter.parse(fromDate);*/
                    
                    if(year==membHistory.getTo_year()) {
                        fromDt = DBHelper.toSQLDate(new Date());
                        Debug.print("From Date for "+membHistory.getMembership_type_name()+" Registeration is : "+fromDt.toString());
                    } else {
                        if(cal.get(Calendar.MONTH)==11) {
                            fromDt = DBHelper.toSQLDate(new Date());
                            Debug.print("From Date for "+membHistory.getMembership_type_name()+" Registeration is : "+fromDt.toString());
                        } else {
                            String fromDate = "2007-12-01";
                            fromDate = year+fromDate.substring(4,10);
                            Date dt2 =(Date) formatter.parse(fromDate);
                            
                            fromDt = DBHelper.toSQLDate(dt2);
                            Debug.print("From Date for "+membHistory.getMembership_type_name()+" Registeration is : "+fromDt.toString());
                        }
                    }
                    
                    if(membHistory.getMembership_type_name().equalsIgnoreCase("Life Member")) {
                        fromDt = DBHelper.toSQLDate(new Date());
                        Debug.print("From Date for "+membHistory.getMembership_type_name()+" Registeration is : "+fromDt.toString());
                        
                        toDt = null;
                        Debug.print("to Date for "+membHistory.getMembership_type_name()+" Registeration is :"+toDt);
                    } else {
                        String toDate = "2007-11-30";
                        toDate = membHistory.getTo_year()+toDate.substring(4,10);
                        toDt = java.sql.Date.valueOf(toDate);
                        Debug.print("to Date for "+membHistory.getMembership_type_name()+" Registeration is :"+toDt.toString());
                    }
                    
                }
                
                int year1 = cal.get(Calendar.YEAR);
                if(year1<membHistory.getTo_year()) {
                    if(cal.get(Calendar.MONTH)!=11) {
                        active_status = false;
                    }
                }
                
                if(membHistory.getMembership_type_name().equalsIgnoreCase("Subscribing Member")) {
                    active_status = true;
                }
                
            }
            
            if(membHistory.getMembership_action().equalsIgnoreCase("Renew")) {
                Debug.print("MemberUpdateDAO.insertMemberHistoryDetails() : Inside Renew Block ");
                
                if(membHistory.getMembership_type_name().equalsIgnoreCase("Subscribing Member")) {
                    
                   formatter = new SimpleDateFormat("yyyy-MM-dd");
                   //String s = formatter.format(membHistory.getSubExpDate());
                   //Debug.print("Subscribing Expiry Date in insertMemberHistoryDetails 1:" + s);
                   subExpDate =(Date) formatter.parse(membHistory.getSubExpDate()); 
                    //subExpDate=membHistory.getSubscribingExDate();
                    Debug.print("Subscribing Expiry Date in insertMemberHistoryDetails 2:" + subExpDate);
                    fromDt = DBHelper.toSQLDate(subExpDate);//DBHelper.toSQLDate(new Date());
                    Debug.print("from Date for Subscribing Member renewal is :"+fromDt.toString());
                   
                    toDt = DBHelper.toSQLDate(new DBHelper().calculateExpireDate(12,subExpDate));
                    Debug.print("to Date for Subscribing Member renewal is :"+toDt.toString());
                } else if(membHistory.getMembership_type_name().equalsIgnoreCase("Life Member")) {
                    fromDt = DBHelper.toSQLDate(new Date());
                    Debug.print("From Date for "+membHistory.getMembership_type_name()+" Registeration is : "+fromDt.toString());
                    
                    toDt = null;
                    Debug.print("to Date for "+membHistory.getMembership_type_name()+" Registeration is :"+toDt);
                } else {
                    // Get the components of the date
                    int year = cal.get(Calendar.YEAR);
                    formatter = new SimpleDateFormat("yyyy-MM-dd");
                    Debug.print("Current year is : "+year);
                    
                    if(membHistory.getTo_year()==year) {
                        fromDt = DBHelper.toSQLDate(new Date());
                        Debug.print("From Date for "+membHistory.getMembership_type_name()+" renewal is : "+fromDt.toString());
                    } else {
                        if(cal.get(Calendar.MONTH)==11) {
                            fromDt = DBHelper.toSQLDate(new Date());
                            Debug.print("From Date for "+membHistory.getMembership_type_name()+" renewal is : "+fromDt.toString());
                        } else {
                            String fromDate = "2007-12-01";
                            fromDate = year+fromDate.substring(4,10);
                            
                            Date dt2 =(Date) formatter.parse(fromDate);
                            fromDt = DBHelper.toSQLDate(dt2);
                            Debug.print("From Date for "+membHistory.getMembership_type_name()+" renewal is : "+fromDt.toString());
                        }
                    }
                    
                    String toDate = "2007-11-30";
                    toDate = membHistory.getTo_year()+toDate.substring(4,10);
                    toDt = java.sql.Date.valueOf(toDate);
                    Debug.print("to Date for "+membHistory.getMembership_type_name()+" renewal is :"+toDt.toString());
                }
                
                int year1 = cal.get(Calendar.YEAR);
                if(year1<membHistory.getTo_year()) {
                    
                    if(cal.get(Calendar.MONTH)==11) {
                        String str = "select activity_id, from_date from "+DBHelper.USEA_MEMBERSHIP_HISTORY_DETAILS +" WHERE active_status = ? and member_id = ?";
                        Debug.print("sel activity id Query Log :"+str);
                        
                        prepStmt = con.prepareStatement(str);
                        prepStmt.setBoolean(1, true);
                        prepStmt.setString(2, membHistory.getMemberId());
                        
                        rs = prepStmt.executeQuery();
                        String activity_id = null;
                        
                        if(rs.next()){
                            activity_id = rs.getString(1);
                            Debug.print(" activity_id :"+activity_id);
                        }
                        
                        if(activity_id!=null) {
                            String str2 = "update "+DBHelper.USEA_MEMBERSHIP_HISTORY_DETAILS +" set active_status = ? where activity_id = ?";
                            Debug.print("update qry log :"+str2);
                            
                            PreparedStatement prepStmt1 = con.prepareStatement(str2);
                            prepStmt1.setBoolean(1, false);
                            prepStmt1.setString(2, activity_id);
                            
                            Debug.print("Before calling executeUpdate Mathod in updateMemberHistoryDetail");
                            int cnt = prepStmt1.executeUpdate();
                            
                            Debug.print("Succefully updateMemberHistoryDetail....."+cnt);
                            
                        }
                        
                    } else {
                        active_status = false;
                    }
                } else {
                    String str = "select activity_id, from_date from "+DBHelper.USEA_MEMBERSHIP_HISTORY_DETAILS +" WHERE active_status = ? and member_id = ?";
                    Debug.print("sel activity id Query Log :"+str);
                    
                    prepStmt = con.prepareStatement(str);
                    prepStmt.setBoolean(1, true);
                    prepStmt.setString(2, membHistory.getMemberId());
                    
                    rs = prepStmt.executeQuery();
                    String activity_id = null;
                    
                    if(rs.next()){
                        activity_id = rs.getString(1);
                        Debug.print(" activity_id :"+activity_id);
                    }
                    
                    if(activity_id!=null) {
                        String str2 = "update "+DBHelper.USEA_MEMBERSHIP_HISTORY_DETAILS +" set active_status = ? where activity_id = ?";
                        Debug.print("update qry log :"+str2);
                        
                        PreparedStatement prepStmt1 = con.prepareStatement(str2);
                        prepStmt1.setBoolean(1, false);
                        prepStmt1.setString(2, activity_id);
                        
                        Debug.print("Before calling executeUpdate Mathod in updateMemberHistoryDetail");
                        int cnt = prepStmt1.executeUpdate();
                        
                        Debug.print("Succefully updateMemberHistoryDetail....."+cnt);
                        
                    }
                }
                
                if(membHistory.getMembership_type_name().equalsIgnoreCase("Subscribing Member")) {
                    active_status = true;
                }
                
            }
            
            if(membHistory.getMembership_action().equalsIgnoreCase("Downgrade")) {
                Debug.print("MemberUpdateDAO.insertMemberHistoryDetails() : Inside Downgrade Block ");
                
                if(membHistory.getMembership_type_name().equalsIgnoreCase("Subscribing Member")) {
                    fromDt = DBHelper.toSQLDate(new Date());
                    Debug.print("from Date for Subscribing Member renewal is :"+fromDt.toString());
                    
                    toDt = DBHelper.toSQLDate(new DBHelper().calculateExpireDate(12,new Date()));
                    Debug.print("to Date for Subscribing Member renewal is :"+toDt.toString());
                } else {
                    int year = cal.get(Calendar.YEAR);
                    formatter = new SimpleDateFormat("yyyy-MM-dd");
                    Debug.print("Current year is : "+year);
                    
                    if(year==membHistory.getTo_year()) {
                        fromDt = DBHelper.toSQLDate(new Date());
                        Debug.print("From Date for "+membHistory.getMembership_type_name()+" Downgrade is : "+fromDt.toString());
                    } else {
                        if(cal.get(Calendar.MONTH)==11) {
                            fromDt = DBHelper.toSQLDate(new Date());
                            Debug.print("From Date for "+membHistory.getMembership_type_name()+" Downgrade is : "+fromDt.toString());
                        } else {
                            String fromDate = "2007-12-01";
                            fromDate = year+fromDate.substring(4,10);
                            Date dt2 =(Date) formatter.parse(fromDate);
                            fromDt = DBHelper.toSQLDate(dt2);
                            Debug.print("From Date for "+membHistory.getMembership_type_name()+" Downgrade is : "+fromDt.toString());
                        }
                    }
                    
                    String toDate = "2007-11-30";
                    toDate = membHistory.getTo_year()+toDate.substring(4,10);
                    toDt = java.sql.Date.valueOf(toDate);
                    Debug.print("to Date for "+membHistory.getMembership_type_name()+" Downgrade is :"+toDt.toString());
                }
                
                int year1 = cal.get(Calendar.YEAR);
                if(year1<membHistory.getTo_year()) {
                    
                    if(cal.get(Calendar.MONTH)==11) {
                        String str = "select activity_id, from_date from "+DBHelper.USEA_MEMBERSHIP_HISTORY_DETAILS +" WHERE active_status = ? and member_id = ?";
                        Debug.print("sel activity id Query Log :"+str);
                        
                        prepStmt = con.prepareStatement(str);
                        prepStmt.setBoolean(1, true);
                        prepStmt.setString(2, membHistory.getMemberId());
                        
                        rs = prepStmt.executeQuery();
                        String activity_id = null;
                        
                        if(rs.next()){
                            activity_id = rs.getString(1);
                            Debug.print(" activity_id :"+activity_id);
                        }
                        
                        if(activity_id!=null) {
                            String str2 = "update "+DBHelper.USEA_MEMBERSHIP_HISTORY_DETAILS +" set active_status = ? where activity_id = ?";
                            Debug.print("update qry log :"+str2);
                            
                            PreparedStatement prepStmt1 = con.prepareStatement(str2);
                            prepStmt1.setBoolean(1, false);
                            prepStmt1.setString(2, activity_id);
                            
                            Debug.print("Before calling executeUpdate Mathod in updateMemberHistoryDetail");
                            int cnt = prepStmt1.executeUpdate();
                            
                            Debug.print("Succefully updateMemberHistoryDetail....."+cnt);
                            
                        }
                    } else {
                        active_status = false;
                    }
                } else {
                    String str = "select activity_id, from_date from "+DBHelper.USEA_MEMBERSHIP_HISTORY_DETAILS +" WHERE active_status = ? and member_id = ?";
                    Debug.print("sel activity id Query Log :"+str);
                    
                    prepStmt = con.prepareStatement(str);
                    prepStmt.setBoolean(1, true);
                    prepStmt.setString(2, membHistory.getMemberId());
                    
                    rs = prepStmt.executeQuery();
                    String activity_id = null;
                    
                    if(rs.next()){
                        activity_id = rs.getString(1);
                        Debug.print(" activity_id :"+activity_id);
                    }
                    
                    if(activity_id!=null) {
                        String str2 = "update "+DBHelper.USEA_MEMBERSHIP_HISTORY_DETAILS +" set active_status = ? where activity_id = ?";
                        Debug.print("update qry log :"+str2);
                        
                        PreparedStatement prepStmt1 = con.prepareStatement(str2);
                        prepStmt1.setBoolean(1, false);
                        prepStmt1.setString(2, activity_id);
                        
                        Debug.print("Before calling executeUpdate Mathod in updateMemberHistoryDetail");
                        int cnt = prepStmt1.executeUpdate();
                        
                        Debug.print("Succefully updateMemberHistoryDetail....."+cnt);
                        
                    }
                }
                
                if(membHistory.getMembership_type_name().equalsIgnoreCase("Subscribing Member")) {
                    active_status = true;
                }
                
            }
            
            if(membHistory.getMembership_action().equalsIgnoreCase("Upgrade")) {
                Debug.print("MemberUpdateDAO.insertMemberHistoryDetails() : Inside Upgrade Block ");
                
                // Get the components of the date
                int year = cal.get(Calendar.YEAR);
                formatter = new SimpleDateFormat("yyyy-MM-dd");
                Debug.print("Current year is : "+year);
                
                            /*String fromDate = "2007-01-12";
                            fromDate = year+fromDate.substring(4,10);
                            Date dt2 =(Date) formatter.parse(fromDate);*/
                fromDt = DBHelper.toSQLDate(new Date());
                Debug.print("From Date for "+membHistory.getMembership_type_name()+" Upgrade is : "+fromDt.toString());
                
                if(membHistory.getMembership_type_name().equalsIgnoreCase("Life Member")) {
                    toDt = null;
                    Debug.print("to Date for "+membHistory.getMembership_type_name()+" Upgrade is :"+toDt);
                } else {
                    
                    if(cal.get(Calendar.MONTH)==11) {
                        String toDate = "2007-11-30";
                        int year1 = cal.get(Calendar.YEAR)+1;
                        toDate = year1+toDate.substring(4,10);
                        toDt = java.sql.Date.valueOf(toDate);
                        Debug.print("to Date for "+membHistory.getMembership_type_name()+" Upgrade is :"+toDt.toString());
                    } else {
                        String toDate = "2007-11-30";
                        toDate = year+toDate.substring(4,10);
                        toDt = java.sql.Date.valueOf(toDate);
                        Debug.print("to Date for "+membHistory.getMembership_type_name()+" Upgrade is :"+toDt.toString());
                    }
                }
                
                String str = "select activity_id, from_date from "+DBHelper.USEA_MEMBERSHIP_HISTORY_DETAILS +" WHERE active_status = ? and member_id = ?";
                Debug.print("sel activity id Query Log :"+str);
                
                prepStmt = con.prepareStatement(str);
                prepStmt.setBoolean(1, true);
                prepStmt.setString(2, membHistory.getMemberId());
                
                rs = prepStmt.executeQuery();
                String activity_id = null;
                
                if(rs.next()){
                    activity_id = rs.getString(1);
                    Debug.print(" activity_id :"+activity_id);
                }
                
                if(activity_id!=null) {
                    
                    DateFormat dateFormat = new SimpleDateFormat( "MM/dd/yyyy");
                    Calendar cl = Calendar.getInstance();
                    
                    //Date dt = new Date("10/01/2007");
                    //cl.setTimeInMillis(from_date.getTime());
                    cl .setTimeInMillis(System.currentTimeMillis());
                    
                    cl.add(Calendar.DAY_OF_MONTH, -1);
                    Date prevDat = new Date(dateFormat.format(new Date(cl.getTimeInMillis() ) ));
                    Debug.print("Previous date : "+prevDat.toString());
                    
                    String str2 = "update "+DBHelper.USEA_MEMBERSHIP_HISTORY_DETAILS +" set active_status = ? , to_date = ? where activity_id = ?";
                    Debug.print("update qry log :"+str2);
                    
                    PreparedStatement prepStmt1 = con.prepareStatement(str2);
                    prepStmt1.setBoolean(1, false);
                    prepStmt1.setDate(2, DBHelper.toSQLDate(prevDat));
                    prepStmt1.setString(3, activity_id);
                    
                    Debug.print("Before calling executeUpdate Mathod in updateMemberHistoryDetail");
                    int cnt = prepStmt1.executeUpdate();
                    
                    Debug.print("Succefully updateMemberHistoryDetail....."+cnt);
                    
                }
                
            }
            
            Debug.print("active_status before inserting :"+active_status);
            
            String insertStatement = "insert into " + DBHelper.USEA_MEMBERSHIP_HISTORY_DETAILS + " (member_id, membership_type_id,"+
                    "status_id , from_date, to_date, zip_code, payment_id, active_status, user_id, amateur_name, amateur_dec1, amateur_dec2) " +
                    "values ( ? , ? , ? , ? , ?, ? , ? , ? , ? , ? , ? , ?) ";
            
            prepStmt = con.prepareStatement(insertStatement);
            Debug.print("insertStatement :"+insertStatement);
            
            prepStmt.setString(1, membHistory.getMemberId());
            prepStmt.setString(2, membHistory.getMembership_type_id());
            prepStmt.setString(3, membHistory.getStatus_id());
            
            prepStmt.setDate(4, fromDt);
            prepStmt.setDate(5, toDt);
            
            prepStmt.setString(6, membHistory.getZip_code());
            prepStmt.setString(7, membHistory.getPayment_id());
            prepStmt.setBoolean(8, active_status);
            prepStmt.setString(9, membHistory.getUser_id());
            prepStmt.setString(10, membHistory.getAmatName());
            prepStmt.setBoolean(11, membHistory.isAmatDec1());
            prepStmt.setBoolean(12, membHistory.isAmatDec2());
            
            int cnt = prepStmt.executeUpdate();
            Debug.print("Record Inserted succefully into insertMemberHistoryDetails "+cnt);
            
            if(cnt>0)
                result = true;
            
            Debug.print("MemberUpdateDAO insertMemberHistoryDetails() Status :" + result);
            prepStmt.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in MemberUpdateDAO.insertMemberHistoryDetails():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in MemberUpdateDAO.insertMemberHistoryDetails():" + e.getMessage());
        }
        
        return result;
    }
    
    
    /**
     * Name         :insertHorseMemberHistoryDetails
     * Description  :This method insert Horse Member History Details
     * @ param      :MemberHistoryDetail
     * @return      :boolean
     * @throws      :EJBException
     */
    
    public boolean insertHorseMemberHistoryDetails(HLCMemberHistoryDetail membHistory) throws SQLException{
        Debug.print("MemberUpdateDAO.insertHorseMemberHistoryDetails():");
        boolean result = false;
        PreparedStatement prepStmt = null;
        Debug.print("membHistory.toString() :"+membHistory.toString());
        java.sql.Date toDt = null;
        java.sql.Date fromDt = null;
        Calendar cal = new GregorianCalendar();
        SimpleDateFormat formatter;
        boolean active_status = true;
        
        makeConnection();
        
        try {
            
            fromDt = DBHelper.toSQLDate(new Date());
            Debug.print("From Date for "+membHistory.getMembership_type_name()+" is : "+fromDt.toString());
            
            
            
            if(membHistory.getMembership_action().equalsIgnoreCase("Upgrade")) {
                Debug.print("MemberUpdateDAO.insertHorseMemberHistoryDetails() : Inside Upgrade Block ");
                
                String str = "select activity_id, from_date from "+DBHelper.USEA_MEMBERSHIP_HISTORY_DETAILS +" WHERE active_status = ? and member_id = ?";
                Debug.print("sel activity id Query Log :"+str);
                
                prepStmt = con.prepareStatement(str);
                prepStmt.setBoolean(1, true);
                prepStmt.setString(2, membHistory.getMemberId());
                
                rs = prepStmt.executeQuery();
                String activity_id = null;
                
                if(rs.next()){
                    activity_id = rs.getString(1);
                    Debug.print(" activity_id :"+activity_id);
                }
                
                if(activity_id!=null) {
                    
                    DateFormat dateFormat = new SimpleDateFormat( "MM/dd/yyyy");
                    Calendar cl = Calendar.getInstance();
                    
                    //Date dt = new Date("10/01/2007");
                    //cl.setTimeInMillis(from_date.getTime());
                    cl .setTimeInMillis(System.currentTimeMillis());                    
                    cl.add(Calendar.DAY_OF_MONTH, -1);
                    Date prevDat = new Date(dateFormat.format(new Date(cl.getTimeInMillis() ) ));
                    Debug.print("Previous date : "+prevDat.toString());
                    
                    String str2 = "update "+DBHelper.USEA_MEMBERSHIP_HISTORY_DETAILS +" set active_status = ? , to_date = ? where activity_id = ?";
                    Debug.print("update qry log :"+str2);
                    
                    PreparedStatement prepStmt1 = con.prepareStatement(str2);
                    prepStmt1.setBoolean(1, false);
                    prepStmt1.setDate(2, DBHelper.toSQLDate(prevDat));
                    prepStmt1.setString(3, activity_id);
                    
                    Debug.print("Before calling executeUpdate Mathod in insertHorseMemberHistoryDetails");
                    int cnt = prepStmt1.executeUpdate();
                    
                    Debug.print("Succefully insertHorseMemberHistoryDetails....."+cnt);
                    
                }
                
            }
            
            Debug.print("active_status before inserting :"+active_status);
            
            String insertStatement = "insert into " + DBHelper.USEA_MEMBERSHIP_HISTORY_DETAILS + " (member_id, membership_type_id,"+
                    "status_id , from_date, to_date, zip_code, payment_id, active_status, user_id) values ( ? , ? , ? , ? , ?, ? , ? , ? , ? ) ";
            
            prepStmt = con.prepareStatement(insertStatement);
            Debug.print("insertStatement :"+insertStatement);
            
            prepStmt.setString(1, membHistory.getMemberId());
            prepStmt.setString(2, membHistory.getMembership_type_id());
            prepStmt.setString(3, membHistory.getStatus_id());
            
            prepStmt.setDate(4, fromDt);
            prepStmt.setDate(5, toDt);
            
            prepStmt.setString(6, null);
            prepStmt.setString(7, membHistory.getPayment_id());
            prepStmt.setBoolean(8, true);
            prepStmt.setString(9, membHistory.getUser_id());
            
            int cnt = prepStmt.executeUpdate();
            Debug.print("Record Inserted succefully into insertHorseMemberHistoryDetails "+cnt);
            
            if(cnt>0)
                result = true;
            
            Debug.print("MemberUpdateDAO insertHorseMemberHistoryDetails() Status :" + result);
            prepStmt.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in MemberUpdateDAO.insertHorseMemberHistoryDetails():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in MemberUpdateDAO.insertHorseMemberHistoryDetails():" + e.getMessage());
        }
        
        return result;
    }
    
    /**
     * Name         :getZipCodeOnUserId
     * Description  :This method will get Zip Code On UserId
     * @ param      :userId
     * @return      :String
     * @throws      :SQLException
     */
    
    public String getZipCodeOnUserId(String userId) throws SQLException {
        boolean bol = false;
        Debug.print("MemberUpdateDAO isContactTypeExist ");
        String zip = null;
        
        try {
            makeConnection();
            
            String str = "SELECT zip FROM "+DBHelper.USEA_CONTACT_DETAILS+" A, "+DBHelper.USEA_CONTACT_TYPEMASTER+" B, "+DBHelper.USEA_MMS_USERMASTER+" C where C.user_id = A.user_id and B.contact_type_id = A.contact_type_id"+
                    " and B.contact_type_name = 'Primary' and C.user_id = ? ";
            
            Debug.print("Query : "+str);
            
            prepStmt = con.prepareStatement(str);
            prepStmt.setString(1, userId);
            
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                zip= rs.getString(1);
                Debug.print("Zip code is : "+zip);
            }
            
        }catch (Exception e){
            prepStmt.close();
            releaseConnection();
            Debug.print("Error While checking getZipCodeOnUserId Exist: "+e.getMessage());
            e.printStackTrace();
        }finally {
            prepStmt.close();
            releaseConnection();
        }
        return zip;
    }
    
    public String getExistingMemberId(String loginName) throws SQLException {
        boolean bol = false;
        Debug.print("***********************MemberUpdateDAO getExistingMemberId******************************** ");
        String member_id = null;
        
        try {
            makeConnection();
            
            String str = "SELECT member_id from tblMemberDetails A, tblUserMaster B "+
                    " WHERE A.user_id = B.user_id and B.login_name = ? ";
            
            Debug.print("Query : "+str);
            
            prepStmt = con.prepareStatement(str);
            prepStmt.setString(1, loginName);
            
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                member_id= rs.getString(1);
                Debug.print("member id is : "+member_id);
            }
            
        }catch (Exception e){
            prepStmt.close();
            releaseConnection();
            Debug.print("Error While getting Member Id: "+e.getMessage());
            e.printStackTrace();
        }finally {
            prepStmt.close();
            releaseConnection();
        }
        return member_id;
    }
    
    public String[] selectUserDetailsForEmail(String userId) {
        Debug.print("HLCMemberUpdateDAO.selectUserDetailsForEmail():");
        PreparedStatement prepStmt = null;
        String [] userDet = {};
        makeConnection();
        try {
            String selectStatement = "SELECT A.user_id, A.first_name, A.last_name, B.city, B.state FROM tblUserMaster A, tblContactDetails B " +
                      " WHERE  A.user_id = B.user_id and A.user_id=? ";
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, userId);
            
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                String userID = rs.getString(1);
                String firstName = rs.getString(2);
                String lastName = rs.getString(3);
                String city = rs.getString(4);
                String state = rs.getString(5);
                String tempLoginDet[] = {userID, firstName, lastName, city, state};
                userDet = tempLoginDet;
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in HLCMemberUpdateDAO.selectUserDetailsForEmail():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in HLCMemberUpdateDAO.selectUserDetailsForEmail():" + e.getMessage());
        }
        return userDet;
    }
    
     public ArrayList getUserContactDetails(String userId) {  
        Debug.print("HLCMemberUpdateDAO getUserContactDetails() " + userId);
        ArrayList userContactList = new ArrayList();
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
   	try {
          String selectStatement = " SELECT A.prefix, A.first_name, A.middle_name, A.last_name, A.sufix, A.email_id, B.suite, B.address1, " +
                  " B.address2, B.city, B.state, B.country, B.zip, B.phone_no, B.mobile_no, B.fax_no FROM " +
                  DBHelper.USEA_MMS_USERMASTER + " A, " +  DBHelper.USEA_CONTACT_DETAILS + " B WHERE A.user_id = B.user_id AND " +
                  "A.contact_type_id = B.contact_type_id AND A.user_id = ?";

             prepStmt = con.prepareStatement(selectStatement);
             prepStmt.setString(1,userId);
             Debug.print("HLCMemberUpdateDAO getUserContactDetails() Query: " + selectStatement);
             rs = prepStmt.executeQuery();
            ArrayList al = null;
            while(rs.next()){
                String prefix = rs.getString(1);
                String first_name = rs.getString(2);
                String middle_name = rs.getString(3);
                String last_name = rs.getString(4);
                String sufix = rs.getString(5);
                String email_id = rs.getString(6);
                String suite = rs.getString(7);
                String address1 = rs.getString(8);
                String address2 = rs.getString(9);
                String city = rs.getString(10);
                String state = rs.getString(11);
                String country = rs.getString(12);
                String zip = rs.getString(13);
                String phone_no = rs.getString(14);
                String mobile_no = rs.getString(15);
                String fax_no = rs.getString(16);
               // boolean addressStatus = rs.getBoolean(17);
                
                //String addStst = String.valueOf(addressStatus);
                //String splNotes = rs.getString(18);
                userContactList.add(prefix);
                userContactList.add(first_name);
                userContactList.add(middle_name);
                userContactList.add(last_name);
                userContactList.add(sufix);
                userContactList.add(email_id);
                userContactList.add(suite);
                userContactList.add(address1);
                userContactList.add(address2);
                userContactList.add(city);
                userContactList.add(state);
                userContactList.add(country);
                userContactList.add(zip);
                userContactList.add(phone_no);
                userContactList.add(mobile_no);
                userContactList.add(fax_no);
               // userContactList.add(addStst);
              //  userContactList.add(splNotes);
            }
            rs.close();
            releaseConnection();
            Debug.print("HLCMemberUpdateDAO in getUserContactDetails:" + userContactList);
        } 
        catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in HLCMemberUpdateDAO.selectUserDetailsForEmail():" + sql.getMessage());
        }
        catch(Exception e){
             releaseConnection();
            Debug.print("General Exception  in HLCMemberUpdateDAO.selectUserDetailsForEmail():" + e.getMessage());
        }
        return userContactList;
    }  
     
    public ArrayList getMemberContactDetails(String memberId){  
        Debug.print("HLCMemberUpdateDAO getMemberContactDetails() " + memberId);
         ArrayList userContactList = new ArrayList();
         PreparedStatement prepStmt = null;
         ResultSet rs = null;
         makeConnection();
   	try {
          String selectStatement ="SELECT A.prefix, A.first_name, A.middle_name, A.last_name, A.sufix, A.email_id, " +
                  " B.suite, B.address1, B.address2, B.city, B.state, B.country, B.zip, B.phone_no, " +
                  " B.mobile_no, B.fax_no , A.user_id FROM " + DBHelper.USEA_MMS_USERMASTER  + " A, " + DBHelper.USEA_CONTACT_DETAILS  + " B, " +
                  DBHelper.USEA_MMS_MEMBERDETAIL  + " C  WHERE C.user_id = A.user_id AND A.user_id = B.user_id AND A.contact_type_id = B.contact_type_id " +
                  " AND C.member_id = ?";
          
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,memberId);
            Debug.print("HLCMemberUpdateDAO getMemberContactDetails() Query: " + selectStatement);
            rs = prepStmt.executeQuery();
            ArrayList al = null;
            while(rs.next()){
                String prefix = rs.getString(1);
                String first_name = rs.getString(2);
                String middle_name = rs.getString(3);
                String last_name = rs.getString(4);
                String sufix = rs.getString(5);
                String email_id = rs.getString(6);
                String suite = rs.getString(7);
                String address1 = rs.getString(8);
                String address2 = rs.getString(9);
                String city = rs.getString(10);
                String state = rs.getString(11);
                String country = rs.getString(12);
                String zip = rs.getString(13);
                String phone_no = rs.getString(14);
                String mobile_no = rs.getString(15);
                String fax_no = rs.getString(16);

                userContactList.add(prefix);
                userContactList.add(first_name);
                userContactList.add(middle_name);
                userContactList.add(last_name);
                userContactList.add(sufix);
                userContactList.add(email_id);
                userContactList.add(suite);
                userContactList.add(address1);
                userContactList.add(address2);
                userContactList.add(city);
                userContactList.add(state);
                userContactList.add(country);
                userContactList.add(zip);
                userContactList.add(phone_no);
                userContactList.add(mobile_no);
                userContactList.add(fax_no);
            }
            rs.close();
            releaseConnection();
            Debug.print("HLCMemberUpdateDAO in getMemberContactDetails:" + userContactList);
        } 
         catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in HLCMemberUpdateDAO.getMemberContactDetails():" + sql.getMessage());
        }
        catch(Exception e){
             releaseConnection();
            Debug.print("General Exception  in HLCMemberUpdateDAO.getMemberContactDetails():" + e.getMessage());
        }
        return userContactList;
    }
     
   public ArrayList getUserContactDetailsForAdmin(String userId) {
        Debug.print("HLCMemberUpdateDAO getUserContactDetailsForAdmin() " + userId);
        ArrayList userContactList = new ArrayList();
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
   	try {
          String selectStatement = " SELECT A.prefix, A.first_name, A.middle_name, A.last_name, A.sufix, A.email_id, B.suite, B.address1, " +
                  " B.address2, B.city, B.state, B.country, B.zip, B.phone_no, B.mobile_no, B.fax_no,A.address_status,A.spl_notes FROM " +
                  DBHelper.USEA_MMS_USERMASTER + " A, " +  DBHelper.USEA_CONTACT_DETAILS + " B WHERE A.user_id = B.user_id AND " +
                  "A.contact_type_id = B.contact_type_id AND A.user_id = ?";

             prepStmt = con.prepareStatement(selectStatement);
             prepStmt.setString(1,userId);
             Debug.print("HLCMemberUpdateDAO getUserContactDetailsForAdmin() Query: " + selectStatement);
             rs = prepStmt.executeQuery();
            ArrayList al = null;
            while(rs.next()){
                String prefix = rs.getString(1);
                String first_name = rs.getString(2);
                String middle_name = rs.getString(3);
                String last_name = rs.getString(4);
                String sufix = rs.getString(5);
                String email_id = rs.getString(6);
                String suite = rs.getString(7);
                String address1 = rs.getString(8);
                String address2 = rs.getString(9);
                String city = rs.getString(10);
                String state = rs.getString(11);
                String country = rs.getString(12);
                String zip = rs.getString(13);
                String phone_no = rs.getString(14);
                String mobile_no = rs.getString(15);
                String fax_no = rs.getString(16);
                boolean addressStatus = rs.getBoolean(17);
                String addStst = String.valueOf(addressStatus);
                String splNotes = rs.getString(18);
                userContactList.add(prefix);
                userContactList.add(first_name);
                userContactList.add(middle_name);
                userContactList.add(last_name);
                userContactList.add(sufix);
                userContactList.add(email_id);
                userContactList.add(suite);
                userContactList.add(address1);
                userContactList.add(address2);
                userContactList.add(city);
                userContactList.add(state);
                userContactList.add(country);
                userContactList.add(zip);
                userContactList.add(phone_no);
                userContactList.add(mobile_no);
                userContactList.add(fax_no);
                userContactList.add(addStst);
                userContactList.add(splNotes);
            }
            rs.close();
            releaseConnection();
            Debug.print("HLCMemberUpdateDAO in getUserContactDetailsForAdmin:" + userContactList);
        } 
        catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in HLCMemberUpdateDAO.getMemberContactDetails():" + sql.getMessage());
        }
        catch(Exception e){
             releaseConnection();
            Debug.print("General Exception  in HLCMemberUpdateDAO.getMemberContactDetails():" + e.getMessage());
        }
        return userContactList;
    }
  
  public ArrayList selectMemberDetailsByMemberId(String memberId){  
        Debug.print("HLCMemberUpdateDAO selectMemberDetailsByMemberId() " + memberId);
         ArrayList memberDetails = new ArrayList();
         PreparedStatement prepStmt = null;
         ResultSet rs = null;
         makeConnection(); 
        
   	try {
          String selectStatement = "select A.first_name, A.last_name, A.login_name, " +
                  " A.password, B.member_id , A.email_id, A.user_id, E.membership_type_name, D.status_name, C.state from tblUserMaster A, " +
                  "tblMemberDetails B, tblContactDetails C, tblMembershipStatusMaster D, tblMembershipTypeMaster E" +
                  " where A.user_id = C.user_id AND A.contact_type_id = C.contact_type_id and A.user_id = B.user_id and" +
                  " B.status_id = D.status_id and B.membership_type_id = E.membership_type_id and B.member_id = ? ";

             prepStmt = con.prepareStatement(selectStatement);
             prepStmt.setString(1,memberId.trim());
              Debug.print("HLCMemberUpdateDAO selectMemberDetailsByMemberId() Query: " + selectStatement);
             rs = prepStmt.executeQuery();
            while(rs.next()){
                String firstName1 =  rs.getString(1);
                String lastName1 = rs.getString(2);
                String loginName = rs.getString(3);
                String password = rs.getString(4);
                String tempMemberId = rs.getString(5);
                String emailId = rs.getString(6);
                String userId1 = rs.getString(7);
                String membershipTypeName1 = rs.getString(8);
                String statusName1 = rs.getString(9);
                String state = rs.getString(10);
                
                HLCUserSearchResultVO objUserSearch = new HLCUserSearchResultVO();
                
                objUserSearch.setFirstName(firstName1);
                objUserSearch.setLastName(lastName1);
                objUserSearch.setLoginName(loginName);
                objUserSearch.setPassword(password);
                objUserSearch.setMemberId(tempMemberId);
                objUserSearch.setEmailId(emailId);
                objUserSearch.setUserId(userId1);
                objUserSearch.setMembershipTypeName(membershipTypeName1);
                objUserSearch.setStatusName(statusName1);
                objUserSearch.setState(state);
                memberDetails.add(objUserSearch);
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
        } 
       catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in HLCMemberUpdateDAO.selectMemberDetailsByMemberId():" + sql.getMessage());
        }
        catch(Exception e){
             releaseConnection();
            Debug.print("General Exception  in HLCMemberUpdateDAO.selectMemberDetailsByMemberId():" + e.getMessage());
        }
        return memberDetails;
    }

  //==========Start:Dhivya Here: Added dates and role name variables for Assign roles to user========================
   
     public ArrayList selectMemberDetailsByGeneralSearch(String fName, String lName, String email, String zip, Date fromDate, Date toDate, String roleId, String radName){
        Debug.print("HLCMemberUpdateDAO selectMemberDetailsByGeneralSearch() " + fName);
         ArrayList memberDetails = new ArrayList();
         PreparedStatement prepStmt = null;
         PreparedStatement prepStmt1 = null;
         ResultSet rs = null;
         ResultSet rs1 = null;
         String selectStatement="";
         makeConnection();
   	try {
            /*String selectStatement = "select A.first_name, A.last_name, A.login_name, A.password, B.member_id , " +
                  " A.email_id, A.user_id, E.membership_type_name, D.status_name, C.state, G.role_name " +
                  " from tblUserMaster A left join tblMemberDetails B ON " +
                  " A.user_id = B.user_id left join tblContactDetails C ON " +
                  " A.user_id = C.user_id left join tblMembershipStatusMaster D ON " +
                  " B.status_id = D.status_id left join tblMembershipTypeMaster E ON " +
                  " B.membership_type_id = E.membership_type_id left join tblMapUserPrivilege F ON " +
                  " A.user_id = F.user_id, tblRoleMaster G where A.contact_type_id = C.contact_type_id and F.role_id = G.role_id ";*/

          if(radName!=null && radName.equalsIgnoreCase("members")){

         selectStatement = "select A.first_name, A.last_name, A.login_name, A.password, B.member_id , " +
                  " A.email_id, A.user_id, E.membership_type_name, D.status_name, C.state, F.role_id, G.role_name " +
                  " from tblUserMaster A, tblMemberDetails B, tblContactDetails C, tblMembershipStatusMaster D, tblMembershipTypeMaster E, " +
                  "tblMapUserPrivilege F, tblRoleMaster G where A.user_id = B.user_id and A.user_id = C.user_id and B.status_id = D.status_id " +
                  "and B.membership_type_id = E.membership_type_id and A.user_id = F.user_id and A.contact_type_id = C.contact_type_id and F.role_id = G.role_id";
            }
          else if(radName!=null && radName.equalsIgnoreCase("nonMembers")){
  selectStatement = "select A.first_name, A.last_name, A.login_name, A.password, " +
                  " A.email_id, A.user_id, C.state " +
                  " from tblUserMaster A LEFT OUTER JOIN tblMemberDetails B ON A.user_id = B.user_id, tblContactDetails C " +
                  " where A.user_id = C.user_id and " +
                  " A.contact_type_id = C.contact_type_id and B.user_id IS NULL";

            }
                if(fName!=null && fName.trim().length()!=0){
                    fName = fName.replaceAll("'","''");
                    selectStatement = selectStatement + " and A.first_name like '" + fName.trim() + "%' ";
                }
         
                if(lName!=null && lName.trim().length()!=0){
                    lName = lName.replaceAll("'","''");
                    selectStatement = selectStatement + " and A.last_name like '" + lName.trim() + "%' ";
                }
                
                if(email!=null && email.trim().length()!=0){
                    selectStatement = selectStatement + " and A.email_id = '" + email.trim() + "' " ;
                }

                if(zip!=null && zip.trim().length()!=0){
                     selectStatement = selectStatement + " and C.zip = '" + zip.trim() + "' " ;
                }

            if(fromDate!=null && toDate!=null){
                     selectStatement = selectStatement + " and A.register_date Between '" + DBHelper.toSQLDate(fromDate) + "' and '" + DBHelper.toSQLDate(toDate) + "' " ;
                }
if(radName!=null && radName.equalsIgnoreCase("members")){
            if(roleId!=null && roleId.trim().length()!=0){
                     selectStatement = selectStatement + " and F.role_id = '" + roleId.trim() + "' " ;
                }
            }
             selectStatement = selectStatement +" ORDER BY A.last_name,A.first_name ASC" ;
             Debug.print("Query : " + selectStatement);

             prepStmt = con.prepareStatement(selectStatement);
             Debug.print("HLCMemberUpdateDAO selectMemberDetailsByGeneralSearch() Query: " + selectStatement);
             rs = prepStmt.executeQuery();

                String firstName2 =  "";
                String lastName2 = "";
                String loginName1 = "";
                String password ="";
                String emailId ="";
                String userId2 ="";
                String state ="";

            while(rs.next()){
 HLCUserSearchResultVO objUserSearch = new HLCUserSearchResultVO();
       if(radName!=null && radName.equalsIgnoreCase("members")){
                 firstName2 =  rs.getString(1);
                 lastName2 = rs.getString(2);
                 loginName1 = rs.getString(3);
                 password = rs.getString(4);
                String tempMemberId = rs.getString(5);
                 emailId = rs.getString(6);
                 userId2 = rs.getString(7);
                String membershipTypeName2 =  rs.getString(8);
                String statusName2 =  rs.getString(9);
                 state = rs.getString(10);
                String rolId = rs.getString(11);
                String rolName = rs.getString(12);

                //Debug.print("tempMemberId in DAO"+tempMemberId);
               
                
                objUserSearch.setFirstName(firstName2);
                objUserSearch.setLastName(lastName2);
                objUserSearch.setLoginName(loginName1);
                objUserSearch.setPassword(password);
       
                objUserSearch.setMemberId(tempMemberId);
                objUserSearch.setMembershipTypeName(membershipTypeName2);
                objUserSearch.setStatusName(statusName2);
               
                objUserSearch.setEmailId(emailId);
                objUserSearch.setUserId(userId2);
               
                objUserSearch.setState(state);
                objUserSearch.setRoleId(rolId);
                objUserSearch.setRoleName(rolName);
            }else if(radName!=null && radName.equalsIgnoreCase("nonMembers")){

                 firstName2 =  rs.getString(1);
                 lastName2 = rs.getString(2);
                 loginName1 = rs.getString(3);
                 password = rs.getString(4);
                 emailId = rs.getString(5);               
                 userId2 = rs.getString(6);
                 state = rs.getString(7);
                 
  objUserSearch.setFirstName(firstName2);
                objUserSearch.setLastName(lastName2);
                objUserSearch.setLoginName(loginName1);
                objUserSearch.setPassword(password);

                objUserSearch.setEmailId(emailId);
                objUserSearch.setUserId(userId2);

                objUserSearch.setState(state);
 
            }

                memberDetails.add(objUserSearch);

              //  Debug.print("memberDetails in the DAO"+memberDetails.size());
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
        }
        catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in HLCMemberUpdateDAO.selectMemberDetailsByGeneralSearch():" + sql.getMessage());
        }
        catch(Exception e){
            e.printStackTrace();
             releaseConnection();
            Debug.print("General Exception  in HLCMemberUpdateDAO.selectMemberDetailsByGeneralSearch():" + e.getMessage());
        }       
        return memberDetails;
    }

   //==========Ends Here=============================================================
     
   public ArrayList selectMemberDetailsByLoginName(String loginName, String radName){
        Debug.print("HLCMemberUpdateDAO selectMemberDetailsByLoginName() " + loginName);
        ArrayList memberDetails = new ArrayList();
      
        String selectStatement="";
       HLCUserSearchResultVO objUserSearch = new HLCUserSearchResultVO();
        makeConnection();
   	try {
            /*String selectStatement = "select A.first_name, A.last_name, A.login_name, " +
                  " A.password, B.member_id , A.email_id, A.user_id, E.membership_type_name, D.status_name, C.state from tblUserMaster A, " +
                  "tblMemberDetails B, tblContactDetails C, tblMembershipStatusMaster D, tblMembershipTypeMaster E" +
                  " where A.user_id = C.user_id AND A.contact_type_id = C.contact_type_id and A.user_id = B.user_id and" +
                  " B.status_id = D.status_id and B.membership_type_id = E.membership_type_id and A.login_name = ? ";*/
            
//===========Commented by Dhivya:Assign role to User=============================
     /* selectStatement = " select A.first_name, A.last_name, A.login_name, " +
                  " A.password, A.email_id, A.user_id, B.state  from tblUserMaster A, tblContactDetails B where" +
                  " A.user_id = B.user_id AND A.contact_type_id = B.contact_type_id and A.login_name = '" + loginName.trim() + "'";
        */
//===============Ends Here====================================================

     if(radName!=null && radName.equalsIgnoreCase("members")){
  selectStatement = " select A.first_name, A.last_name, A.login_name, " +
                  " A.password, A.email_id, A.user_id, B.state, C.member_id, E.membership_type_name, D.status_name "
                  + "from tblUserMaster A, tblContactDetails B, tblMemberDetails C, tblMembershipStatusMaster D, tblMembershipTypeMaster E "
                  + "where A.user_id = B.user_id AND A.contact_type_id = B.contact_type_id and C.status_id = D.status_id and "
                  + "C.membership_type_id = E.membership_type_id "
                  + "and A.user_id=C.user_id and A.login_name = '" + loginName.trim() + "'";

   prepStmt = con.prepareStatement(selectStatement);
             //prepStmt.setString(1,loginName.trim());
             Debug.print("HLCMemberUpdateDAO selectMemberDetailsByLoginName() 1st Query: " + selectStatement);
             rs = prepStmt.executeQuery();

            while(rs.next()){
                String firstName3 =  rs.getString(1);
                String lastName3 = rs.getString(2);
                String loginName1 = rs.getString(3);
                String password = rs.getString(4);
                //String tempMemberId = rs.getString(5);
                String emailId = rs.getString(5);
                String userId3 = rs.getString(6);
                String state = rs.getString(7);

                String tempMemberId =rs.getString(8);


                String membershipTypeName3 =rs.getString(9);
                String statusName3 =rs.getString(10);

//Debug.print("String tempMemberId"+tempMemberId);


                objUserSearch.setFirstName(firstName3);
                objUserSearch.setLastName(lastName3);
                objUserSearch.setLoginName(loginName1);
                objUserSearch.setPassword(password);
                objUserSearch.setEmailId(emailId);
                objUserSearch.setUserId(userId3);
                objUserSearch.setState(state);
                objUserSearch.setMemberId(tempMemberId);
                objUserSearch.setMembershipTypeName(membershipTypeName3);
                objUserSearch.setStatusName(statusName3);

//Debug.print("tempMemberId in the method loginNameDAO"+tempMemberId);

   memberDetails.add(objUserSearch);
            }
            

            }

if(radName!=null && radName.equalsIgnoreCase("nonMembers")){


  selectStatement = "select A.first_name, A.last_name, A.login_name, A.password, " +
                  " A.email_id, A.user_id, C.state " +
                  " from tblUserMaster A LEFT OUTER JOIN tblMemberDetails B ON A.user_id = B.user_id, tblContactDetails C " +
                  " where A.user_id = C.user_id and " +
                  " A.contact_type_id = C.contact_type_id and B.user_id IS NULL and A.login_name = '" + loginName.trim() + "'";

prepStmt = con.prepareStatement(selectStatement);
             //prepStmt.setString(1,loginName.trim());
             Debug.print("HLCMemberUpdateDAO selectMemberDetailsByLoginName() 2nd Query: " + selectStatement);
             rs = prepStmt.executeQuery();

  
   String firstName2 =  "";
                String lastName2 = "";
                String loginName1 = "";
                String password ="";
                String emailId ="";
                String userId2 ="";
                String state ="";

       while(rs.next()){
              firstName2 =  rs.getString(1);
                 lastName2 = rs.getString(2);
                 loginName1 = rs.getString(3);
                 password = rs.getString(4);
                 emailId = rs.getString(5);
                 userId2 = rs.getString(6);
                 state = rs.getString(7);

  objUserSearch.setFirstName(firstName2);
                objUserSearch.setLastName(lastName2);
                objUserSearch.setLoginName(loginName1);
                objUserSearch.setPassword(password);

                objUserSearch.setEmailId(emailId);
                objUserSearch.setUserId(userId2);

                objUserSearch.setState(state);
                
                memberDetails.add(objUserSearch);
}
}
  
            rs.close();
           prepStmt.close();
            releaseConnection();
            
        }
         catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in HLCMemberUpdateDAO.selectMemberDetailsByGeneralSearch():" + sql.getMessage());
        }
        catch(Exception e){     
             releaseConnection();
             e.printStackTrace();
           // Debug.print("General Exception  in HLCMemberUpdateDAO.selectMemberDetailsByGeneralSearch():" + e.getMessage());
        }
    
        return memberDetails;
    }
   
     private String selectMemeberId(String userId){  
        Debug.print("HLCMemberUpdateDAO selectMemeberId() " + userId);
         PreparedStatement prepStmt = null;
       
        // ResultSet rs = null;
         String memberId4 = null;
         makeConnection();
   	try {
          String selectStatement ="select member_id from tblMemberDetails where user_id = ?";

            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,userId.trim());
            ResultSet rs= prepStmt.executeQuery();
            if(rs.next()){
                memberId4 = rs.getString(1);
            }
            rs.close();
           prepStmt.close();
            releaseConnection();
         
            Debug.print("HLCMemberUpdateDAO in selectMemeberId memberId:" + memberId4);
        } 
       catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in HLCMemberUpdateDAO.selectMemeberId():" + sql.getMessage());
        }
        catch(Exception e){
             releaseConnection();
            Debug.print("General Exception  in HLCMemberUpdateDAO.selectMemeberId():" + e.getMessage());
        }  
        return memberId4;
    }
     
      public HLCUserSearchResultVO selectMemberDetailsByUserId(String userId){  
        Debug.print("HLCMemberUpdateDAO selectMemberDetailsByUserId() " + userId);
         HLCUserSearchResultVO objUserSearch = new HLCUserSearchResultVO();
         PreparedStatement prepStmt = null;
         ResultSet rs = null;
         makeConnection();
   	try {
          String selectStatement = " select A.first_name, A.last_name, A.login_name, " +
                  " A.password, A.email_id, A.user_id  from tblUserMaster A, tblContactDetails B where " +
                  " A.user_id = B.user_id AND A.contact_type_id = B.contact_type_id and  A.user_id = ?";
          
             prepStmt = con.prepareStatement(selectStatement);
             prepStmt.setString(1,userId.trim());
             Debug.print("HLCMemberUpdateDAO selectMemberDetailsByUserId() Query: " + selectStatement);
             rs = prepStmt.executeQuery();
             
            if(rs.next()){
                String firstName5 =  rs.getString(1);
                String lastName5 = rs.getString(2);
                String tempLoginName = rs.getString(3);
                String password = rs.getString(4);
                String emailId = rs.getString(5);
                String tempUserId = rs.getString(6);
                
                objUserSearch.setFirstName(firstName5);
                objUserSearch.setLastName(lastName5);
                objUserSearch.setLoginName(tempLoginName);
                objUserSearch.setPassword(password);
                objUserSearch.setEmailId(emailId);
                objUserSearch.setUserId(userId);
            }
            
            rs.close();
            releaseConnection();
        }
        catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in HLCMemberUpdateDAO.selectMemberDetailsByUserId():" + sql.getMessage());
        }
        catch(Exception e){
             releaseConnection();
            Debug.print("General Exception  in HLCMemberUpdateDAO.selectMemberDetailsByUserId():" + e.getMessage());
        }  
            if(objUserSearch.getUserId()!=null && objUserSearch.getUserId().trim().length()!=0){
                String memberId5 = selectMemeberId(objUserSearch.getUserId().trim());
                objUserSearch.setMemberId(memberId5);
            }
        return objUserSearch;
    }
      
   public ArrayList getUserContactDetailsByUserCode(String userCode) {  
        Debug.print("HLCMemberUpdateDAO getUserContactDetailsByUserCode() " + userCode);
         ArrayList userContactList = new ArrayList();
         PreparedStatement prepStmt = null;
         ResultSet rs = null;
         makeConnection();
   	try {
          String selectStatement = " SELECT A.prefix, A.first_name, A.middle_name, A.last_name, A.sufix, A.email_id, B.suite, B.address1, " +
                  " B.address2, B.city, B.state, B.country, B.zip, B.phone_no, B.mobile_no, B.fax_no FROM " +
                  DBHelper.USEA_MMS_USERMASTER + " A, " +  DBHelper.USEA_CONTACT_DETAILS + " B WHERE A.user_id = B.user_id AND " +
                  "A.contact_type_id = B.contact_type_id AND A.user_code = ?";
          
             prepStmt = con.prepareStatement(selectStatement);
             prepStmt.setString(1,userCode);
              Debug.print("HLCMemberUpdateDAO getUserContactDetailsByUserCode() Query: " + selectStatement);
             rs = prepStmt.executeQuery();
            ArrayList al = null;
            while(rs.next()){
                String prefix = rs.getString(1);
                String first_name = rs.getString(2);
                String middle_name = rs.getString(3);
                String last_name = rs.getString(4);
                String sufix = rs.getString(5);
                String email_id = rs.getString(6);
                String suite = rs.getString(7);
                String address1 = rs.getString(8);
                String address2 = rs.getString(9);
                String city = rs.getString(10);
                String state = rs.getString(11);
                String country = rs.getString(12);
                String zip = rs.getString(13);
                String phone_no = rs.getString(14);
                String mobile_no = rs.getString(15);
                String fax_no = rs.getString(16);
                userContactList.add(prefix);
                userContactList.add(first_name);
                userContactList.add(middle_name);
                userContactList.add(last_name);
                userContactList.add(sufix);
                userContactList.add(email_id);
                userContactList.add(suite);
                userContactList.add(address1);
                userContactList.add(address2);
                userContactList.add(city);
                userContactList.add(state);
                userContactList.add(country);
                userContactList.add(zip);
                userContactList.add(phone_no);
                userContactList.add(mobile_no);
                userContactList.add(fax_no);
            }
            rs.close();
            releaseConnection();
            Debug.print("HLCMemberUpdateDAO in getUserContactDetailsByUserCode:" + userContactList);
        } 
       catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in HLCMemberUpdateDAO.getUserContactDetailsByUserCode():" + sql.getMessage());
        }
        catch(Exception e){
             releaseConnection();
            Debug.print("General Exception  in HLCMemberUpdateDAO.getUserContactDetailsByUserCode():" + e.getMessage());
        }  
        return userContactList;
    }
   
    /**
  * Name         :listMemberType() 
  * Description  :This method will retrieve the listSpecification details
  * @ param      :none
  * @return      :Vector
  * @throws      :SQLException
  */     
  public Vector listMemberType() throws SQLException {
        Debug.print("Inside the listMemberType");
        Vector vObj = new Vector();
        ResultSet rs = null;
          try {
                makeConnection();
              //For Debugs Starts Here
              //  String slelctStr = "SELECT membership_type_id, membership_type_name FROM " + DBHelper.USEA_MEMBERSHIP_TYPE+" where user_type_id='caafe0ba-6a20-49bf-aa86-8f95f7d9346d' and active_status='true' and membership_year='2009'";
                 String slelctStr = "SELECT mem_type_id, mem_type_name FROM tblMeeAnnualMembershipTypeMaster";
               //For Debugs Ends Here
                Debug.print("Query:" + slelctStr);
                PreparedStatement prepStmt = con.prepareStatement(slelctStr);
               // prepStmt.setString(1, activityId);
                 rs = prepStmt.executeQuery();
                System.out.println("Inside the listMemberType   ");
                while (rs.next()) {
                    this.userTypeId = rs.getString(1);
                    this.userTypeName = rs.getString(2);
                    String [] userList  = {userTypeId,userTypeName};
                    vObj.add(userList);
                }
                releaseConnection();
          }catch (SQLException e){
              e.printStackTrace();
          }finally {
              releaseConnection();
          }
          return vObj;
}   
  
  /**
  * Name         :listMembershipType() 
  * Description  :This method will retrieve the listSpecification details
  * @ param      :none
  * @return      :Vector
  * @throws      :SQLException
  */     
  public Vector listMembershipType() throws SQLException {
        Debug.print("Inside the listUserType");
        Vector vObj = new Vector();
          try {
                makeConnection();
                String slelctStr = "SELECT membership_type_id,membership_type_name FROM "+ DBHelper.USEA_MEMBERSHIP_TYPE;
                PreparedStatement prepStmt = con.prepareStatement(slelctStr);
               // prepStmt.setString(1, activityId);
                rs = prepStmt.executeQuery();
                System.out.println("Inside the listUserType   ");
                while (rs.next()) {
                    this.userTypeId = rs.getString(1);
                    this.userTypeName = rs.getString(2);
                    String [] userList  = {userTypeId,userTypeName};
                    vObj.add(userList);
                }
                releaseConnection();
          }catch (SQLException e){
              e.printStackTrace();
          }finally {
              releaseConnection();
          }
          return vObj;
    }   
   public boolean insertAreaMembProgram(HLCMemberDetails objMember, HLCPaymentDetails objPayment) throws SQLException {
        boolean bol = false;
        boolean bol1 = true;
        String statusId1="";
        Debug.print("MemberUpdateDAO insertAreaMembProgram ");
        try {
            makeConnection();
            String selectStatement = "select status_id from tblMemberDetails where "+
                                    "member_id = ? ";
            prepStmt = con.prepareStatement(selectStatement);
            String memberId=objMember.getMemberId();
            prepStmt.setString(1, memberId);
            
            Debug.print("objMember.getMemberId: "+memberId);
            Debug.print("selectStatement in insertAreaMembProgram:"+selectStatement);
            rs = prepStmt.executeQuery();
            if (bol1 = rs.next()) {
                Debug.print("in the loop");
                statusId1 = rs.getString(1);
                Debug.print("bol1: "+bol1);
                Debug.print("statusId in insertAreaMembProgram: "+statusId1);
            }
            Debug.print("statusId in insertAreaMembProgram: "+statusId1);
            String insertStatement = "insert into " + DBHelper.USEA_MMS_AREA_MEMBER_HISTORY_DETAILS + " (user_id," +
                        "member_id, area_member_type_id, payment_id, activation_date, active_status, status_id)" +
                        " values ( ? , ? , ? , ? , ?, ?, ?) ";
            prepStmt = con.prepareStatement(insertStatement);         
            prepStmt.setString(1, objMember.getUserId());
            prepStmt.setString(2, objMember.getMemberId());
            prepStmt.setString(3, objMember.getAreaMembTypId());
            prepStmt.setString(4, objMember.getPaymentId());
            if(objMember.getActivationDate()!=null){
            prepStmt.setDate(5, DBHelper.toSQLDate(objMember.getActivationDate()));
            }else{
             prepStmt.setDate(5,null);   
            }
            prepStmt.setBoolean(6, true);
            prepStmt.setString(7, statusId1);
            int cnt = prepStmt.executeUpdate();
                Debug.print("Record Inserted succefully into insertAreaMembProgram " + cnt);
                bol = true;
            prepStmt.close();
            releaseConnection();

        } catch (Exception e) {      
            Debug.print("Error While checking insertAreaMembProgram : " + e.getMessage());
            e.printStackTrace();
        }     
                return bol;
    } 
    public ArrayList selectAreaProgramDetailsFrmTypeMaster(int tempProgYear, String areaId) {
        Debug.print("selectAreaProgramDetailsFrmTypeMaster():");
        
        ArrayList list = new ArrayList();
        PreparedStatement prepStmt = null;
        AreaProgramVO areaProg = new AreaProgramVO();
        makeConnection();
        try {
            
            String selectStatement = "select A.area_member_type_id, A.area_id, A.program_name, " +
                    "A.program_year, A.area_member_price, A.url, A.description, " +
                    "A.min_age, A.max_age, A.upgradable, B.area_name, A.transaction_type_id from " +
                    "tblAreaMemberTypeMaster A, " +
                    "tblMeeAreaMaster B where " +
                    "A.area_id=B.area_id and A.area_id= ?";

            if(tempProgYear!=0){
                selectStatement = selectStatement +" and A.program_year= ?";
            } 
                     
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, areaId);
            if(tempProgYear!=0){
                prepStmt.setInt(2, tempProgYear);
            }
            
            ResultSet rs = prepStmt.executeQuery();
            System.out.println(selectStatement + "=selectStatement in selectAreaProgramDetailsFrmTypeMaster()");
            while(rs.next()){
     
                String areaMemTypeId=rs.getString(1);
                String areaId1=rs.getString(2);
                String progName=rs.getString(3);
                int progYear=rs.getInt(4);
                String areaMemPrice=rs.getString(5);
                String url=rs.getString(6);
                String description=rs.getString(7);
                int minAge=rs.getInt(8);
                int maxAge=rs.getInt(9);
                boolean upgrade=rs.getBoolean(10);
                String areaName=rs.getString(11);
                String transacTypId=rs.getString(12);
                                           
                areaProg.setAreaMembTypId(areaMemTypeId);
                areaProg.setAreaId(areaId1);
                areaProg.setProgName(progName);
                areaProg.setProgYear(progYear);
                areaProg.setAreaMembPrice(areaMemPrice);
                areaProg.setUrl(url);
                areaProg.setDescription(description);
                areaProg.setMinAge(minAge);
                areaProg.setMaxAge(maxAge);
                areaProg.setUpgradable(upgrade);
                areaProg.setAreaName(areaName);  
                areaProg.setTransacTypId(transacTypId);
                list.add(areaProg);

            }
            System.out.println(list.size() + "=listsize");
            rs.close();
            prepStmt.close();
            releaseConnection();

        } catch (SQLException sql) {

            Debug.print("SQL Exception in MemberUpdateDAO.selectAreaProgramDetailsFrmTypeMaster():" + sql.getMessage());
        } catch (Exception e) {

            Debug.print("General Exception  in MemberUpdateDAO.selectAreaProgramDetailsFrmTypeMaster():" + e.getMessage());
        }
        return list;
    }
     public ArrayList selectAreaProgBasedOnAgeAndProgYear(int age, int progYear, String areaId) {
        Debug.print("selectAreaProgBasedOnAgeAndProgYear():");
        
        ArrayList list1 = new ArrayList();
        PreparedStatement prepStmt = null;
        AreaProgramVO areaProg = new AreaProgramVO();
        makeConnection();
        try {
            
            String selectStatement = "select A.area_id, A.area_Name, B.area_member_type_id, B.description, B.area_member_price, " +
                    "B.url, B.program_name, B.program_year, B.transaction_type_id from tblMeeAreaMaster A, tblAreaMemberTypeMaster B " +
                    "where A.area_id = B.area_id and ? between B.min_age and B.max_age and B.area_id= ?";

           if(progYear!=0){
                selectStatement = selectStatement +" and B.program_year=?";
            }  
                    
            prepStmt = con.prepareStatement(selectStatement);

            prepStmt.setInt(1,age);            
            prepStmt.setString(2,areaId);
            if(progYear!=0){
              prepStmt.setInt(3,progYear);
            }
                    
            ResultSet rs = prepStmt.executeQuery();
            System.out.println(selectStatement + "=selectStatement in selectAreaProgBasedOnAgeAndProgYear()");
            while(rs.next()) {                                             
                String areaId1=rs.getString(1);
                String areaName=rs.getString(2);
                String areaMemTypeId=rs.getString(3);
                String descrip=rs.getString(4);
                String areaMembPrice=rs.getString(5);
                String url=rs.getString(6);
                String progName=rs.getString(7);
                String progYear1=rs.getString(8);
                String transacTypId=rs.getString(9);
                
                String [] temp={areaId1,areaName,areaMemTypeId,descrip,areaMembPrice,url,progName,progYear1,transacTypId};
                list1.add(temp);

            }
            System.out.println(list1.size() + "=listsize");
            rs.close();
            prepStmt.close();
            releaseConnection();

        } catch (SQLException sql) {

            Debug.print("SQL Exception in MemberUpdateDAO.selectAreaProgBasedOnAgeAndProgYear():" + sql.getMessage());
        } catch (Exception e) {

            Debug.print("General Exception  in MemberUpdateDAO.selectAreaProgBasedOnAgeAndProgYear():" + e.getMessage());
        }
        return list1;
    }
     
      public ArrayList selectAreaZipDetails(String memberId) {
        Debug.print("selectAreaZipDetails():");
        
        ArrayList list = new ArrayList();
        PreparedStatement prepStmt = null;      
        makeConnection();
        try {
            
            String selectStatement = "select A.area_name, A.area_id, C.zip, year(E.dob) from tblmeeAreaMaster A, tblMemberDetails B, " +
                    "tblContactDetails C, tblMeeMapStateZip D, tblUserMaster E, tblMeeStateMaster F " +
                    "where A.area_id = D.area_id and B.user_id = C.user_id and " +
                    "C.zip >= D.zip_code_from and C.zip <= D.zip_code_to and " +
                    "C.contact_type_id = E.contact_type_id and D.state_id = F.state_id " +
                    "and C.user_id = E.user_id and B.member_id =?";

            prepStmt = con.prepareStatement(selectStatement);

            prepStmt.setString(1, memberId);

            ResultSet rs = prepStmt.executeQuery();
            System.out.println(selectStatement + "=selectStatement in selectAreaZipDetails()");
            while(rs.next()){                  
                String areaName=rs.getString(1); 
                String areaId=rs.getString(2);  
                String zip=rs.getString(3);
                String year=rs.getString(4);
                
                String [] tempList={areaName,areaId,zip,year};
                list.add(tempList);

            }
            System.out.println(list.size() + "=listsize");
            rs.close();
            prepStmt.close();
            releaseConnection();

        } catch (SQLException sql) {

            Debug.print("SQL Exception in MemberUpdateDAO.selectAreaZipDetails():" + sql.getMessage());
        } catch (Exception e) {

            Debug.print("General Exception  in MemberUpdateDAO.selectAreaZipDetails():" + e.getMessage());
        }
        return list;
    }
      
       public ArrayList selectAreaProgramDetailsFrmHistory(String memberId) {
        Debug.print("selectAreaProgramDetailsFrmHistory():");
        
        ArrayList list = new ArrayList();
        PreparedStatement prepStmt = null;
        AreaProgramVO areaProg = new AreaProgramVO();
        makeConnection();
        try {
            
            String selectStatement = "select A.area_member_history_id, A.area_member_type_id, B.area_id, B.program_name, " +
                    "B.program_year, B.area_member_price, B.url, B.description, " +
                    "B.min_age, B.max_age, B.upgradable, C.area_name, B.transaction_type_id from " +
                    "tblAreaMemberHistoryDetails A , tblAreaMemberTypeMaster B, " +
                    "tblMeeAreaMaster C where A.area_member_type_id=B.area_member_type_id " +
                    "and B.area_id=C.area_id and A.member_id= ? and A.active_status= ?";

            prepStmt = con.prepareStatement(selectStatement);

            prepStmt.setString(1, memberId);
            prepStmt.setBoolean(2, true);
            
            ResultSet rs = prepStmt.executeQuery();
            System.out.println(selectStatement + "=selectStatement in selectAreaProgramDetailsFrmHistory()");
            while(rs.next()){
                
                String areaMemHistId=rs.getString(1);
                String areaMemTypeId=rs.getString(2);
                String areaId=rs.getString(3);
                String progName=rs.getString(4);
                int progYear=rs.getInt(5);
                String areaMemPrice=rs.getString(6);
                String url=rs.getString(7);
                String description=rs.getString(8);
                int minAge=rs.getInt(9);
                int maxAge=rs.getInt(10);
                boolean upgrade=rs.getBoolean(11);
                String areaName=rs.getString(12);
                String transacTypId=rs.getString(13);
                
            
                areaProg.setAreaMembHistoryId(areaMemHistId);
                Debug.print("selectAreaProgramDetailsFrmHistory():" + areaMemHistId);
                areaProg.setAreaMembTypId(areaMemTypeId);
                areaProg.setAreaId(areaId);
                areaProg.setProgName(progName);
                System.out.println("----------------"+progName);
                System.out.println("areaProg.getProgName"+areaProg.getProgName());
                System.out.println("memberId in DAO: "+memberId);
                areaProg.setProgYear(progYear);
                areaProg.setAreaMembPrice(areaMemPrice);
                areaProg.setUrl(url);
                areaProg.setDescription(description);
                areaProg.setMinAge(minAge);
                areaProg.setMaxAge(maxAge);
                areaProg.setUpgradable(upgrade);
                areaProg.setAreaName(areaName);   
                areaProg.setTransacTypId(transacTypId);
                list.add(areaProg);

            }
            System.out.println(list.size() + "=listsize");
            rs.close();
            prepStmt.close();
            releaseConnection();

        } catch (SQLException sql) {

            Debug.print("SQL Exception in MemberUpdateDAO.selectAreaProgramDetailsFrmHistory():" + sql.getMessage());
        } catch (Exception e) {

            Debug.print("General Exception  in MemberUpdateDAO.selectAreaProgramDetailsFrmHistory():" + e.getMessage());
        }
        return list;
    }
       
        public boolean isUserIdExist(String userId) throws SQLException {
        boolean bol = false;
        Debug.print("MemberUpdateDAO isUserIdExist :"+userId);
        try {
            makeConnection();

            String str = "SELECT user_id FROM " + DBHelper.USEA_MMS_AREA_MEMBER_HISTORY_DETAILS + " WHERE user_id = ? ";
            prepStmt = con.prepareStatement(str);
            prepStmt.setString(1, userId);
            rs = prepStmt.executeQuery();
            if (bol = rs.next()) {
                String userId1 = rs.getString(1);
                Debug.print("USERID is exist : " + bol);
            }

        } catch (Exception e) {
            prepStmt.close();
            releaseConnection();
            Debug.print("Error While checking isUserIdExist : " + e.getMessage());
            e.printStackTrace();
        } finally {
            prepStmt.close();
            releaseConnection();
        }
        Debug.print("USERID  is exist : " + bol);
        return bol;
    }   
        
        public ArrayList selectAreaZipDetailsBasedOnUserId(String userId) {
            Debug.print("selectAreaZipDetailsBasedOnUserId():");
            
            ArrayList list = new ArrayList();
            PreparedStatement prepStmt = null;      
            makeConnection();
            try {
                
                String selectStatement = "select distinct A.area_name, A.area_id, C.zip, year(E.dob) from tblmeeAreaMaster A, tblMemberDetails B, " +
                        "tblContactDetails C, tblMeeMapStateZip D, tblUserMaster E, tblMeeStateMaster F " +
                        "where A.area_id = D.area_id and " +
                        "C.zip >= D.zip_code_from and C.zip <= D.zip_code_to and " +
                        "C.contact_type_id = E.contact_type_id and D.state_id = F.state_id " +
                        "and C.user_id = E.user_id and E.user_id =?";

                prepStmt = con.prepareStatement(selectStatement);

                prepStmt.setString(1, userId);

                ResultSet rs = prepStmt.executeQuery();
                System.out.println(selectStatement + "=selectStatement in selectAreaZipDetailsBasedOnUserId()");
                while(rs.next()){                  
                    String areaName=rs.getString(1); 
                    String areaId=rs.getString(2);  
                    String zip=rs.getString(3);
                    String year=rs.getString(4);
                    
                    String [] tempList={areaName,areaId,zip,year};
                    list.add(tempList);

                }
                System.out.println(list.size() + "=listsize");
                rs.close();
                prepStmt.close();
                releaseConnection();

            } catch (SQLException sql) {

                Debug.print("SQL Exception in MemberUpdateDAO.selectAreaZipDetailsBasedOnUserId():" + sql.getMessage());
            } catch (Exception e) {

                Debug.print("General Exception  in MemberUpdateDAO.selectAreaZipDetailsBasedOnUserId():" + e.getMessage());
            }
            return list;
        }
        
        public ArrayList getQuarterlySalesByItemForYear(String year, String item) {
            Debug.print("getQuarterlySalesByItemForYear");
            String q1 = null; String q2 = null; String q3 = null; String q4 = null;
            String s1 = null;
            ArrayList vObj = new ArrayList();
            LinkedHashMap list = new LinkedHashMap();
            PreparedStatement prepStmt = null;
            ResultSet rs = null;
            try {
              makeConnection();
              String slelctStr = "select Q1,Q2,Q3,Q4 from tblMfgValueSales where item_no=? and creation_date=? ";

              prepStmt = this.con.prepareStatement(slelctStr);
              prepStmt.setString(1, item);
              prepStmt.setString(2, year);
              rs = prepStmt.executeQuery();

              System.out.println("Inside the getQuarterlySalesByItemForYear   ");
              while (rs.next()) {
                q1 = rs.getString(1);

                q2 = rs.getString(2);
                q3 = rs.getString(3);
                q4 = rs.getString(4);
              }
              rs.close();
              prepStmt.close();
            } catch (SQLException e) {
              Debug.print("Exception " + e.getMessage());
              releaseConnection();
            }
            releaseConnection();
            list.put("Q1", new Double(q1));
            list.put("Q2", new Double(q2));
            list.put("Q3", new Double(q3));
            list.put("Q4", new Double(q4));

            vObj.add(list);
            return vObj;
          }

          public ArrayList getQuarterWiseSalesAllItemsForYear(String quarter, String year)
          {
            Debug.print("getQuarterWiseSalesAllItemsForYear");
            String q1 = null; String q2 = null; String q3 = null; String q4 = null;
            String s1 = null;
            int count = 0;
            ArrayList vObj = new ArrayList();

            String item_name = null;
            HashMap list = new HashMap();
            PreparedStatement prepStmt = null;
            ResultSet rs = null;
            PreparedStatement prepStmt1 = null;
            ResultSet rs1 = null;
            String query1 = "select item_name,Q1 from tblMfgValueSales where creation_date=?";
            String query2 = "select item_name,Q2 from tblMfgValueSales where creation_date=?";
            String query3 = "select item_name,Q3 from tblMfgValueSales where creation_date=?";
            String query4 = "select item_name,Q4 from tblMfgValueSales where creation_date=?";
            try
            {
              makeConnection();
              System.out.println("Year " + year + " quater " + quarter);
              if (quarter.equalsIgnoreCase("Q1"))
                prepStmt = this.con.prepareStatement(query1);
              else if (quarter.equalsIgnoreCase("Q2"))
                prepStmt = this.con.prepareStatement(query2);
              else if (quarter.equalsIgnoreCase("Q3"))
                prepStmt = this.con.prepareStatement(query3);
              else if (quarter.equalsIgnoreCase("Q4")) {
                prepStmt = this.con.prepareStatement(query4);
              }
              prepStmt.setString(1, year);
              rs = prepStmt.executeQuery();
              System.out.println("Inside the getQuarterlySalesByItemForYear   ");
              while (rs.next()) {
                item_name = rs.getString(1);
                Double quarter_amount = Double.valueOf(rs.getString(2));
                Debug.print("Item " + item_name);
                if (item_name.equalsIgnoreCase("Engine Assy")) {
                  Debug.print("Inside Engine Assembly");
                  list.put("Engine", quarter_amount); continue;
                }if (item_name.equalsIgnoreCase("Transmission Module")) {
                  Debug.print("Inside Transmission Assembly");
                  list.put("Transmission", quarter_amount); continue;
                }if (item_name.equalsIgnoreCase("Engine Control Module")) {
                  Debug.print("Inside Engine Control Module");
                  list.put("ECM", quarter_amount);
                }
              }

              rs.close();
              prepStmt.close();
            } catch (Exception er) {
              Debug.print(er.getMessage());
              releaseConnection();
            }
            releaseConnection();
            vObj.add(list);
            return vObj;
          }

          public ArrayList getQuarterlyVolumeSalesByItemForYear(String year, String item) {
            Debug.print("getQuarterlySalesByItemForYear");
            String q1 = null; String q2 = null; String q3 = null; String q4 = null;
            String s1 = null;
            ArrayList vObj = new ArrayList();
            LinkedHashMap list = new LinkedHashMap();
            PreparedStatement prepStmt = null;
            ResultSet rs = null;
            try {
              makeConnection();
              String slelctStr = "select Q1,Q2,Q3,Q4 from tblvolumesales where item_no=? and creation_date=?";

              prepStmt = this.con.prepareStatement(slelctStr);
              prepStmt.setString(1, item);
              prepStmt.setString(2, year);
              rs = prepStmt.executeQuery();

              System.out.println("Inside the getQuarterlySalesByItemForYear   ");
              while (rs.next()) {
                q1 = rs.getString(1);

                q2 = rs.getString(2);
                q3 = rs.getString(3);
                q4 = rs.getString(4);
              }
              rs.close();
              prepStmt.close();
            } catch (SQLException e) {
              Debug.print("Exception " + e.getMessage());
              releaseConnection();
            }
            releaseConnection();
            list.put("Q1", new Double(q1));
            list.put("Q2", new Double(q2));
            list.put("Q3", new Double(q3));
            list.put("Q4", new Double(q4));
            vObj.add(list);

            return vObj;
          }

          public ArrayList getStatusWisePurchaseRequisition(java.util.Date startDate, java.util.Date endDate) {
            Debug.print("getStatusWisePurchaseRequisition");
            String q1 = null; String q2 = null; String q3 = null; String q4 = null; String s1 = null;
            ArrayList vObj = new ArrayList(); Double db = null;
            HashMap list = new HashMap(); PreparedStatement prepStmt = null; ResultSet rs = null;
            PreparedStatement prepStmt1 = null; ResultSet rs1 = null;
            String query = "select distinct(status) from tblMfgPur";
            Debug.print("Start Date " + startDate + "  EndDate " + endDate);
           
            try
            {
              makeConnection();
              prepStmt = this.con.prepareStatement(query);
              rs = prepStmt.executeQuery();
              while (rs.next())
              {
                String status = rs.getString(1);
                System.out.println("Status" + status);
                String query_1 = "select count(*) from tblMfgPur where creation_date Between '" + DBHelper.toSQLDate(startDate) + "' and '" + DBHelper.toSQLDate(endDate) +" and status = '"+status+"'";
                prepStmt1 = this.con.prepareStatement(query_1);

                System.out.println("Start " + startDate.getMonth() + "/" + startDate.getDate() + "/" + (startDate.getYear() + 1900));
                System.out.println("End " + endDate.getMonth() + "/" + endDate.getDate() + "/" + (endDate.getYear() + 1900));

               // prepStmt1.setString(1, startDate.getMonth() + "/" + startDate.getDate() + "/" + (startDate.getYear() + 1900));
              //  prepStmt1.setString(2, endDate.getMonth() + "/" + endDate.getDate() + "/" + (endDate.getYear() + 1900));
              //  prepStmt1.setString(2, status);
                rs1 = prepStmt1.executeQuery();
                while (rs1.next())
                {
                  System.out.println("String output:" + rs1.getString(1));
                  db = Double.valueOf(rs1.getString(1));
                  System.out.println(db);
                }

                list.put(status, db);
              }

              rs.close(); prepStmt.close();
            }
            catch (Exception e)
            {
              Debug.print("Exception " + e.getMessage());
              releaseConnection();
            }

            vObj.add(list);
            return vObj;
          }

          public Double getPurchaseRequisitionPerStatus(String status) {
            Debug.print("getPurchaseRequisitionPerStatus");
            String q1 = null; String q2 = null; String q3 = null; String q4 = null; String s1 = null;
            Double count = null;
            PreparedStatement prepStmt = null; ResultSet rs = null;
            String query = "select count(*) from tblMfgPur where status=?";
            try
            {
              makeConnection();
              prepStmt = this.con.prepareStatement(query);
              prepStmt.setString(1, status);
              rs = prepStmt.executeQuery();
              while (rs.next())
              {
                count = Double.valueOf(rs.getString(1));
              }
              rs.close(); prepStmt.close();
            }
            catch (Exception e)
            {
              Debug.print(e.getMessage());
              releaseConnection();
            }
            releaseConnection();
            return count;
          }

          public HashMap getStockReqAndAvailable(java.util.Date startDate)
          {
            Debug.print("getStockReqAndAvailable");
            ArrayList data1 = null; ArrayList data2 = null; ArrayList total = null;
            HashMap list = new HashMap();
            PreparedStatement prepStmt = null;
            ResultSet rs = null;
            String itemName = null;
            double stockReq = 0.0D;
            double stockAvail = 0.0D;
            System.out.println("Date selected is" + startDate);
            System.out.println("Date again" + (startDate.getMonth() + 1) + "/" + startDate.getDate() + "/" + (startDate.getYear() + 1900));
            String query = "select item_name,stock_req,stock_avail from tblMfgStock where creation_date like '" + DBHelper.toSQLDate(startDate) + "'";
            try
            {
              makeConnection();
              prepStmt = this.con.prepareStatement(query);
              prepStmt.setString(1, startDate.getMonth() + 1 + "/" + startDate.getDate() + "/" + (startDate.getYear() + 1900));
              rs = prepStmt.executeQuery();
              while (rs.next())
              {
                itemName = rs.getString(1);
                stockReq = rs.getDouble(2);
                stockAvail = rs.getDouble(3);
                System.out.println("Item Name " + itemName + " Stock " + stockReq + " Avail" + stockAvail);
                data1 = new ArrayList();
                data1.add(new Double(stockReq));
                data1.add(new Double(stockAvail));

                if (itemName.equalsIgnoreCase("Engine Assy")) {
                  list.put("Engine", data1); continue;
                }if (itemName.equalsIgnoreCase("Transmission Module")) {
                  list.put("Transmission", data1); continue;
                }if (itemName.equalsIgnoreCase("Engine Control Module")) {
                  list.put("ECM", data1);
                }
              }
              rs.close(); prepStmt.close(); releaseConnection();
            }
            catch (Exception e)
            {
              Debug.print("Exception Occured" + e.getMessage());
              releaseConnection();
            }
            return list;
          }

          public ArrayList getDefectiveStocksSuppliedByEachVendorForYear(String year)
          {
            Debug.print("Inside getDefectiveStocksSuppliedByEachVendorForYear for year " + year);
            String vendorNo = null; String vendorName = null; Double sum = null;
            ArrayList list = new ArrayList(); HashMap map = new HashMap();
            PreparedStatement pst = null; ResultSet rs = null;
            PreparedStatement pst1 = null; ResultSet rs1 = null;
            String query = "select distinct(vendor_no) from tblMfgVendor";
            String query_1 = "select vendor_name,sum(defective) from tblMfgVendor where vendor_no=? and year(creation_date) = ? order by vendor_name";
            try
            {
              makeConnection();
              pst = this.con.prepareStatement(query);
              rs = pst.executeQuery();
              while (rs.next())
              {
                vendorNo = rs.getString(1);
                Debug.print("Vendor No" + vendorNo);
                pst1 = this.con.prepareStatement(query_1);
                pst1.setString(1, vendorNo);
                pst1.setString(2, year);
                rs1 = pst1.executeQuery();
                while (rs1.next())
                {
                  vendorName = rs1.getString(1);
                  sum = new Double(rs1.getDouble(2));
                  Debug.print("Vendor Name" + vendorName + " sum is " + sum.doubleValue());
                  map.put(vendorName, sum);
                }

                rs1.close(); pst1.close();
              }
              rs.close(); pst.close(); releaseConnection();
            }
            catch (Exception e)
            {
              Debug.print("Exception Occured" + e.getMessage());
              releaseConnection();
            }
            list.add(map);
            return list;
          }

          public ArrayList getStocksSuppliedByEachVendorForYear(String year)
          {
            Debug.print("Inside getStocksSuppliedByEachVendorForYear for year" + year);
            String vendorNo = null; String vendorName = null; Double sum = null;
            ArrayList list = new ArrayList(); HashMap map = new HashMap();
            PreparedStatement pst = null; ResultSet rs = null;
            PreparedStatement pst1 = null; ResultSet rs1 = null;
            String query = "select distinct(vendor_no) from tblMfgVendor";
            String query_1 = "select vendor_name,sum(lot_received)  from tblMfgVendor where vendor_no=? and year(creation_date) = ? order by vendor_name";
            try
            {
              makeConnection();
              pst = this.con.prepareStatement(query);
              rs = pst.executeQuery();
              while (rs.next())
              {
                vendorNo = rs.getString(1);
                Debug.print("Vendor No" + vendorNo);
                pst1 = this.con.prepareStatement(query_1);
                pst1.setString(1, vendorNo);
                pst1.setString(2, year);
                rs1 = pst1.executeQuery();
                while (rs1.next())
                {
                  vendorName = rs1.getString(1);
                  sum = new Double(rs1.getDouble(2));
                  Debug.print("Vendor Name" + vendorName + " sum is " + sum.doubleValue());
                  map.put(vendorName, sum);
                }

                rs1.close(); pst1.close();
              }
              rs.close(); pst.close(); releaseConnection();
            }
            catch (Exception e)
            {
              Debug.print("Exception Occured" + e.getMessage());
              releaseConnection();
            }
            list.add(map);
            return list;
          }
          
        //==============================Dhivya Here: User View Mapping======================================================        
          
          
          public boolean addUserCriteria(String usrId, String usrCrit) throws SQLException {
              Debug.print("MemberUpdateDAO addUserCriteria");
              boolean result = false;
              try {
                  makeConnection();
                  Debug.print("usrId : "+usrId);
                  Debug.print("usrCrit : "+usrCrit);
                  String insertStatement = "insert into tblMapUserView (user_id, user_criteria) values ( ? , ?)";
                  
                  PreparedStatement prepStmt = con.prepareStatement(insertStatement);
                  prepStmt.setString(1, usrId);
                  prepStmt.setString(2, usrCrit);
                 
                  
                  int cnt = prepStmt.executeUpdate();
                  Debug.print("Record Inserted succefully into addUserCriteria "+cnt);
                   result = true;
                   prepStmt.close();
                   releaseConnection();
              }catch (Exception e){
                  
                  releaseConnection();
                  Debug.print("Error while executing addUserCriteria() : "+e.getMessage());
              }finally {
               //   prepStmt.close();
               //   releaseConnection();
              }
              return true;
          }  
          
          public String getusrCriteria(String userId) throws SQLException {
        	  String usrCriteria = "";
              try {
                  makeConnection();
                  String selectStatement = "SELECT user_criteria from tblMapUserView where user_id=?"; 
                  
                  prepStmt = con.prepareStatement(selectStatement);
                  prepStmt.setString(1,userId);
                              
                  Debug.print("Query:" + selectStatement);
                  rs = prepStmt.executeQuery();
                  while (rs.next()) {
                       usrCriteria = rs.getString(1);
                  
                  }
                  rs.close();
                  prepStmt.close();
              } catch (SQLException sqe) {
                  releaseConnection();
                  Debug.print("Error while calling getusrCriteria : "+sqe.getMessage());
                  //sqe.printStackTrace();
              } finally {
                  releaseConnection();
              }
              return usrCriteria;
          } 
          
          public boolean updateUserCriteria(String usrId, String viewPnt, String usrCrit) throws Exception {
              Debug.print("MemberUpdateDAO updateUserCriteria()");
              
              boolean result=false;
              boolean result1=false;
              boolean result2=false;
              boolean output=false;
              String usrViePnt=null;
              String str="";
              PreparedStatement prepStmt=null;
              try {
                  makeConnection();
                  //con.setAutoCommit(false);
                  Debug.print("usrId : "+usrId);
                  Debug.print("viewPnt : "+viewPnt);
                  
                   str ="select view_point_id from tblMapUserView where " +
                  		"user_id=?";
                   prepStmt = con.prepareStatement(str);
                  prepStmt.setString(1, usrId);
                 
                  rs = prepStmt.executeQuery();
                  while (rs.next()) {
                       usrViePnt = rs.getString(1);
                  
                  
                  if(usrViePnt!=null && usrViePnt.trim().length()!=0){
                	  Debug.print("MemberUpdateDAO updateUserCriteria() inside if");
                	   str ="select count(*) from tblMapUserView where user_id=? and view_point_id=?";
                         prepStmt = con.prepareStatement(str);
                        prepStmt.setString(1, usrId);
                        prepStmt.setString(2, viewPnt);
                       
                        ResultSet rs1 = prepStmt.executeQuery();
                        if(rs1.next()){
                        	int cnt = rs1.getInt(1);
                        	if(cnt>0){	
                    	   Debug.print("MemberUpdateDAO updateUserCriteria() inside cnt if");
                    	   result=true;  
                        	}else{
                         	   
                         	   Debug.print("MemberUpdateDAO updateUserCriteria() inside cnt else if");
                         	   str = "insert into tblMapUserView (user_id, view_point_id, user_criteria) values ( ? , ?, ?)";
                                
                                 prepStmt = con.prepareStatement(str);
                                prepStmt.setString(1, usrId);
                                prepStmt.setString(2, viewPnt);
                                prepStmt.setString(3, usrCrit);
                               
                                
                                int cnt1 = prepStmt.executeUpdate();  
                         	   
                                result1=true; 
                            }
                     	  
                       }
                        rs1.close();
                	  
                  }else if(usrViePnt==null){
                	  
                	  Debug.print("MemberUpdateDAO updateUserCriteria() inside usrView Null"); 
               	   str = "update tblMapUserView set view_point_id=? where user_id=?";
                      
                       prepStmt = con.prepareStatement(str);
                      prepStmt.setString(2, usrId);
                      prepStmt.setString(1, viewPnt);
                      
                      
                      int cnt2 = prepStmt.executeUpdate(); 
                      
                      result2=true; 
                  }
                  
              }
                  
                  if(result==true || result==false){
                	  output=result;
                  }
                  else if(result1==true || result1==false){
                	 output=result1;
                 }else if(result2==true || result2==true){
                	 output=result2;
                 }
                  rs.close();
              }catch (Exception e){
                  prepStmt.close();
                  releaseConnection();
                 e.printStackTrace();
              }finally {
                  prepStmt.close();
                  releaseConnection();
              }
              return result;
          }
          
          
          public ArrayList selectUserViewPoints(String userId) {
              Debug.print("MemberUpdateDAO.selectUserViewPoints():");
              PreparedStatement prepStmt = null;
              ArrayList usrViewPnt = new ArrayList();
              makeConnection();
              try {
                  String selectStatement = "SELECT A.view_point_id, B.view_name from " +
                  		"tblMapUserView A, tblViewMaster B where " +
                  		"A.view_point_id=B.view_id and A.user_id=?";
                  
                  prepStmt = con.prepareStatement(selectStatement);
                  prepStmt.setString(1, userId);
                  
                  rs = prepStmt.executeQuery();
                  while (rs.next()) {
                      String viewPntId = rs.getString(1);
                      String viewPntName = rs.getString(2);
                      
                      String tempObj []={viewPntId,viewPntName};
                      usrViewPnt.add(tempObj);
                  }
                  rs.close();
                  prepStmt.close();
                  releaseConnection();
              } catch(SQLException sql){
                  releaseConnection();
                  Debug.print("SQL Exception in MemberUpdateDAO.selectUserViewPoints():" + sql.getMessage());
              } catch(Exception e){
                  releaseConnection();
                  Debug.print("General Exception  in MemberUpdateDAO.selectUserViewPoints():" + e.getMessage());
              }
              return usrViewPnt;
          }
          public String addEmpRegistration(HLCEmployeeDetails objEmpMaster) throws SQLException
          {
        	  //PreparedStatement prepStmt = null;
        	   String userId1="";
        	  makeConnection();
              try {
            	  String insertStatement = "insert into tblusermaster (first_name,middle_name,last_name,sufix,email_id, password,login_name,spl_notes,user_type_id,contact_type_id,active_status)"+
                              " values ( ? , ? , ? , ? , ? , ? , ? , ?,? ,?,?) ";
            	  prepStmt = con.prepareStatement(insertStatement);
            	  prepStmt.setString(1, objEmpMaster.getFirstName());
                  prepStmt.setString(2, objEmpMaster.getMiddleName());
                  prepStmt.setString(3, objEmpMaster.getLastName());
                  prepStmt.setString(4, objEmpMaster.getSufix());
                  prepStmt.setString(5, objEmpMaster.getEmailId());
                  prepStmt.setString(6, objEmpMaster.getPassword());
                  prepStmt.setString(7, objEmpMaster.getLoginName());
                  prepStmt.setString(8, objEmpMaster.getEmployeeId());
                  prepStmt.setString(9, "CAAFE0BA-6A20-49BF-AA86-8F95F7D9346D");
                  prepStmt.setString(10, "0CC42D09-340C-42E8-B773-54BDF0688527");
                  prepStmt.setBoolean(11,false);
                  int count=prepStmt.executeUpdate();
                  if(count==1)
                  {
                	  userId1= selectUserId(con,objEmpMaster.getLoginName());
                	boolean  empInserStat= insertEmployeeOPM(con,objEmpMaster,userId1);
                	  insertContactDetails(con,userId1);
                	  //empInserStat=true;
                	  
                  }
                  prepStmt.close();
            	  
            	  
              }
              catch(Exception e)
              {
            	  System.out.println("exception in  addEmpRegistration"+e);
            	  
              }
        	  finally{
        		  con.close();
        	  }
        	  return userId1;
          }
      
          public String selectUserId(Connection con,String loginName)
          {
        	  String user_id="";
        	  try
        	  {
        		  String selectStmt =  "select user_id from tblusermaster where login_name = ?";
        		  prepStmt = con.prepareStatement(selectStmt);
        		  prepStmt.setString(1, loginName);
        		  rs = prepStmt.executeQuery();
        		  while(rs.next())
        		  {
        			  user_id = rs.getString(1);
        		  }
        		  rs.close();
        		  prepStmt.close();   		  
        		  
        	  }
        	  catch(Exception e)
        	  {
        		  System.out.println("exception in  selectUserId"+e);
        	  }
        	  finally
        	  {
        		    		  
        	  }
        	  return user_id;
          }
      public boolean insertEmployeeOPM(Connection con,HLCEmployeeDetails objEmpMaster,String userId)
      {
    	  boolean empStatus = false;
    	  try
    	  {
    		  String insertStatement = "insert into tblOrganizationalPositionMapping (emp_id,user_id,base_country,base_location,current_country, " +
    		  		" current_location,designation,lob_id,department_id,rept_supervisor_name,rept_supervisor_email_id)"+
                      " values ( ? , ? , ? , ? , ? , ? , ? , ? , ?,?,? ) ";
    	  prepStmt = con.prepareStatement(insertStatement);
    	  prepStmt.setString(1, objEmpMaster.getEmployeeId());
          prepStmt.setString(2, userId);
          prepStmt.setString(3, objEmpMaster.getCounBaseLoc());
          prepStmt.setString(4, objEmpMaster.getBaseLoc());
          prepStmt.setString(5, objEmpMaster.getCurCoun());
          prepStmt.setString(6, objEmpMaster.getCurLoc());
          prepStmt.setString(7, objEmpMaster.getDesig());
          prepStmt.setString(8, objEmpMaster.getLob());
          prepStmt.setString(9, objEmpMaster.getDept());
          prepStmt.setString(10, objEmpMaster.getSupName());
          prepStmt.setString(11, objEmpMaster.getSupEmail());
          
          int count=prepStmt.executeUpdate();
          if(count>=1)
          {
        	  empStatus=true;
    		  prepStmt.close();   	
          }
    		  
    	  }
    	  catch(Exception e)
    	  {
    		  System.out.println("exception in  insertEmployeeOPM"+e);
    	  }
    	  finally
    	  {
    		    		  
    	  }
    	  return empStatus;
      }
      
      
      public boolean insertContactDetails(Connection con,String userId)
      {
    	  boolean empStatus = false;
    	  try
    	  {
    		  System.out.print("User Id is from insertContactDetails: "+userId);
              String insertStatement = "insert into tblcontactdetails (contact_type_id,user_id,address1) values ( ? , ? , ?)";
              prepStmt = con.prepareStatement(insertStatement);
              prepStmt.setString(1,"0CC42D09-340C-42E8-B773-54BDF0688527");
              prepStmt.setString(2,userId);
              prepStmt.setString(3,"EmpInseTest");
              
        prepStmt.executeUpdate();
         
    		  
    	  }
    	  catch(Exception e)
    	  {
    		  System.out.println("exception in  insertContactDetails"+e);
    	  }
    	  finally
    	  {
    		    		  
    	  }
    	  return empStatus;
      }
      public boolean checkEmpIdExist(String empId) throws SQLException {
          Debug.print("MemberUpdateDAO checkEmpIdExist() :" + empId);
          boolean result = false;
          try {
              makeConnection();
              
              String selectStatement = "SELECT login_name FROM  " + DBHelper.USEA_MMS_USERMASTER +
                      "  WHERE  spl_notes = ?";
              
              prepStmt = con.prepareStatement(selectStatement);
              prepStmt.setString(1, empId);
              
              rs = prepStmt.executeQuery();
              if (rs.next()) {
                  result = true;
              }
              rs.close();
              Debug.print("MemberUpdateDAO checkEmpIdExist() result: " + result);
              prepStmt.close();
          } catch (SQLException sqe) {
              releaseConnection();
              Debug.print("Error while calling checkEmpIdExist : "+sqe.getMessage());
              //sqe.printStackTrace();
          } finally {
              releaseConnection();
          }
          return result;
      }
      public ArrayList getEmpInfBasedOnId(String empId) {
          Debug.print("MemberUpdateDAO.selectUserViewPoints():");
          PreparedStatement prepStmt = null;
          String userName="";
          String password="";
          String email="";
          String employeeId="";
          ArrayList empDetList = new ArrayList();
          makeConnection();
          try {
          /* CallableStatement cs=con.prepareCall("{call GetEmpDetFromExter(?)}");
           cs.setString(1, empId);
           //cs.registerOutParameter(parameterName, sqlType, scale)
	       rs=cs.executeQuery();*/
        	  String selectStatement = "SELECT emp_id,user_name,password,email from employee_info where emp_id = ?";
              
              prepStmt = con.prepareStatement(selectStatement);
              prepStmt.setString(1, empId);
              rs = prepStmt.executeQuery();
	       while (rs.next()) {
	    	   employeeId = rs.getString(1);
	    	   userName = rs.getString(2);
	    	   password = rs.getString(3);
	    	   email = rs.getString(4);
	           
	           String tempObj []={employeeId,userName,password,email};
	           empDetList.add(tempObj);
	       }
	       rs.close();
	       //cs.close();
	       releaseConnection();
       
          } catch(SQLException sql){
              releaseConnection();
              Debug.print("SQL Exception in MemberUpdateDAO.getEmpInfBasedOnId():" + sql.getMessage());
          } catch(Exception e){
              releaseConnection();
              Debug.print("General Exception  in MemberUpdateDAO.getEmpInfBasedOnId():" + e.getMessage());
          }
          return empDetList;
      }
      public ArrayList selectCountryList() {
          Debug.print("MemberUpdateDAO.selectCountryList():");
          PreparedStatement prepStmt = null;
          ArrayList counList = new ArrayList();
          makeConnection();
          try {
              String selectStatement = "select cnt_id,cnt_name from count_master";
              
              prepStmt = con.prepareStatement(selectStatement);
              
              
              rs = prepStmt.executeQuery();
              while (rs.next()) {
                  String cnt_id = rs.getString(1);
                  String cnt_name = rs.getString(2);
                  
                  String tempObj []={cnt_id,cnt_name};
                  counList.add(tempObj);
              }
              rs.close();
              prepStmt.close();
              releaseConnection();
          } catch(SQLException sql){
              releaseConnection();
              Debug.print("SQL Exception in MemberUpdateDAO.selectCountryList():" + sql.getMessage());
          } catch(Exception e){
              releaseConnection();
              Debug.print("General Exception  in MemberUpdateDAO.selectCountryList():" + e.getMessage());
          }
          return counList;
      }
      public Vector selectLocBasedOnCId(String cId) {
          Debug.print("MemberUpdateDAO.selectLocBasedOnCId():");
          PreparedStatement prepStmt = null;
          Vector locList = new Vector();
          makeConnection();
          try {
              String selectStatement = "select loc_id,loc_name from " +
              		".dbo.map_count_loc where cnt_id = ?";
              
              prepStmt = con.prepareStatement(selectStatement);
              prepStmt.setString(1, cId);
              
              rs = prepStmt.executeQuery();
              while (rs.next()) {
                  String loc_id = rs.getString(1);
                  String loc_name = rs.getString(2);
                  
                  String tempObj []={loc_id,loc_name};
                  locList.add(tempObj);
              }
              rs.close();
              prepStmt.close();
              releaseConnection();
          } catch(SQLException sql){
              releaseConnection();
              Debug.print("SQL Exception in MemberUpdateDAO.selectLocBasedOnCId():" + sql.getMessage());
          } catch(Exception e){
              releaseConnection();
              Debug.print("General Exception  in MemberUpdateDAO.selectLocBasedOnCId():" + e.getMessage());
          }
          return locList;
      }
      public ArrayList selectLOBList() {
          Debug.print("MemberUpdateDAO.selectLOBList():");
          PreparedStatement prepStmt = null;
          ArrayList lobList = new ArrayList();
          makeConnection();
          try {
              String selectStatement = "select lob_id,lob_name from lob_master";
              
              prepStmt = con.prepareStatement(selectStatement);
              
              
              rs = prepStmt.executeQuery();
              while (rs.next()) {
                  String lob_id = rs.getString(1);
                  String lob_name = rs.getString(2);
                  
                  String tempObj []={lob_id,lob_name};
                  lobList.add(tempObj);
              }
              rs.close();
              prepStmt.close();
              releaseConnection();
          } catch(SQLException sql){
              releaseConnection();
              Debug.print("SQL Exception in MemberUpdateDAO.selectLOBList():" + sql.getMessage());
          } catch(Exception e){
              releaseConnection();
              Debug.print("General Exception  in MemberUpdateDAO.selectLOBList():" + e.getMessage());
          }
          return lobList;
      }
   /*   public Vector selectDeptSuperVisorBasedOnCId(String[] lobId,String[] depId) {
          Debug.print("MemberUpdateDAO.selectDeptSuperVisorBasedOnCId():");
          PreparedStatement prepStmt = null;
          Vector lobDepList = new Vector();
          String selectStatement=null;
          StringTokenizer stDept=null;
         // System.out.println("lobId.toString()---"+lobId[0].toString());
          StringTokenizer stLob=new StringTokenizer(lobId[0].toString(),",");
          if(depId!=null)
          {
           stDept=new StringTokenizer(depId[0].toString(),",");
          }
          while(stLob.hasMoreTokens()){
        	  makeConnection();
          try {
        	  
              
            
             // for(int i=0;i<=lobId.length;i++){
          //  if((lobId[0].toString()!=null && lobId[0].toString().trim().length()!=0) && (depId.toString() !=null && depId.toString().trim().length()!=0))
        	  if(lobId!=null && depId !=null )
             {
            	 selectStatement = " select supVisior_name,supVisior_email from map_lob_dept_sup_master ";
            	  selectStatement= selectStatement + " where lob_id = '" + stLob.nextToken().toString() + "' and  dep_id = '" + stDept.nextToken().toString() + "' ";
            	  //and F.role_id = '" + roleId.trim() + "' "
            }
              else
              {
            	   selectStatement = " select dep_id,dep_name from map_lob_dept_sup_master ";
            	  selectStatement= selectStatement + " where lob_id = '" + stLob.nextToken().toString() + "'";
             }
            	  System.out.println("selectStatement========="+selectStatement);
              prepStmt = con.prepareStatement(selectStatement);
            
              
              rs = prepStmt.executeQuery();
              while (rs.next()) {
            	  if(lobId!=null && depId !=null )
            	  {
            		  String supVisior_name = rs.getString(1);
                      String supVisior_email = rs.getString(2);
                      String tempObj []={supVisior_name,supVisior_email};
                      lobDepList.add(tempObj);
                
            	  }
            	  else
            	  {
            		  String dep_id = rs.getString(1);
                      String dep_name = rs.getString(2);
                      String tempObj []={dep_id,dep_name};
                      lobDepList.add(tempObj);
            	  }
                  
                 
                 
              }
              //}
              rs.close();
              prepStmt.close();
              releaseConnection();
          } catch(SQLException sql){
              releaseConnection();
              Debug.print("SQL Exception in MemberUpdateDAO.selectDeptSuperVisorBasedOnCId():" + sql.getMessage());
          } catch(Exception e){
              releaseConnection();
              Debug.print("General Exception  in MemberUpdateDAO.selectDeptSuperVisorBasedOnCId():" + e.getMessage());
          }
          }
          return lobDepList;
      }
     */
      public Vector selectDeptSuperVisorBasedOnCId(String lobId,String depId,String roleGrade) {
          Debug.print("MemberUpdateDAO.selectDeptSuperVisorBasedOnCId():");
          PreparedStatement prepStmt = null;
          Vector lobDepList = new Vector();
          String selectStatement=null;
          StringTokenizer stDept=null;
         // System.out.println("lobId.toString()---"+lobId[0].toString());
         /*StringTokenizer stLob=new StringTokenizer(lobId[0].toString(),",");
          if(depId!=null)
          {
           stDept=new StringTokenizer(depId[0].toString(),",");
          }
          while(stLob.hasMoreTokens()){*/
        	  makeConnection();
          try {
        	  
              
            
             // for(int i=0;i<=lobId.length;i++){
          //  if((lobId[0].toString()!=null && lobId[0].toString().trim().length()!=0) && (depId.toString() !=null && depId.toString().trim().length()!=0))
        	  if(lobId!=null && depId !=null )
             {
            	 selectStatement = " select supVisior_name,supVisior_email from map_lob_dept_sup_master ";
            	  selectStatement= selectStatement + " where lob_id in (" + lobId + ") and  dep_id in ( " + depId + " ) and role_grade >  '" + roleGrade + "' ";
            	  //and F.role_id = '" + roleId.trim() + "' "
            }
              else
              {
            	   selectStatement = " select distinct dep_id,dep_name from map_lob_dept_sup_master ";
            	  selectStatement= selectStatement + " where lob_id in ( " + lobId + ")";
             }
            	  System.out.println("selectStatement========="+selectStatement);
              prepStmt = con.prepareStatement(selectStatement);
            
              
              rs = prepStmt.executeQuery();
              while (rs.next()) {
            	  if(lobId!=null && depId !=null )
            	  {
            		  String supVisior_name = rs.getString(1);
                      String supVisior_email = rs.getString(2);
                      String tempObj []={supVisior_name,supVisior_email};
                      lobDepList.add(tempObj);
                
            	  }
            	  else
            	  {
            		  String dep_id = rs.getString(1);
                      String dep_name = rs.getString(2);
                      String tempObj []={dep_id,dep_name};
                      lobDepList.add(tempObj);
            	  }
                  
                 
                 
              }
              //}
              rs.close();
              prepStmt.close();
              releaseConnection();
          } catch(SQLException sql){
              releaseConnection();
              Debug.print("SQL Exception in MemberUpdateDAO.selectDeptSuperVisorBasedOnCId():" + sql.getMessage());
          } catch(Exception e){
              releaseConnection();
              Debug.print("General Exception  in MemberUpdateDAO.selectDeptSuperVisorBasedOnCId():" + e.getMessage());
          }
          //}
          return lobDepList;
      }
   public Vector selectLOBBasedOnCId(String bId,String cId) {
          Debug.print("MemberUpdateDAO.selectLOBBasedOnCId():");
          PreparedStatement prepStmt = null;
          Vector lobList = new Vector();
          makeConnection();
          try {
              String selectStatement = "select lob_id,lob_name from map_country_lob where coun_id = ? and cur_coun_id = ?";
              
              prepStmt = con.prepareStatement(selectStatement);
              prepStmt.setString(1, bId);
              prepStmt.setString(2, cId);
              
              rs = prepStmt.executeQuery();
              while (rs.next()) {
                  String lob_id = rs.getString(1);
                  String lob_name = rs.getString(2);
                  
                  String tempObj []={lob_id,lob_name};
                  lobList.add(tempObj);
              }
              rs.close();
              prepStmt.close();
              releaseConnection();
          } catch(SQLException sql){
              releaseConnection();
              Debug.print("SQL Exception in MemberUpdateDAO.selectLOBBasedOnCId():" + sql.getMessage());
          } catch(Exception e){
              releaseConnection();
              Debug.print("General Exception  in MemberUpdateDAO.selectLOBBasedOnCId():" + e.getMessage());
          }
          return lobList;
      }
   
   public ArrayList searchEmpBySearchCriteria(String fName, String lName,String empId,String supName)
   {
	   ArrayList empSearchList = new ArrayList();
	   PreparedStatement prepStmt = null;
       ResultSet rs = null;
       String selectStatement="";
       makeConnection();
 	try {
 		/*selectStatement = "select a.spl_notes,a.first_name,a.last_name,b.designation,a.email_id,a.user_id,a.active_status from tblusermaster a,tblOrganizationalPositionMapping b " +
 				"where a.user_id = b.user_id ";
 		selectStatement="select a.spl_notes,a.first_name,a.last_name,b.designation,a.email_id,a.user_id,a.active_status,d.role_name from tblusermaster a,tblOrganizationalPositionMapping b,tblMapUserPrivilege c,tblrolemaster d " +
 				"where a.user_id = b.user_id and a.user_id=c.user_id and c.role_id=d.role_id ";*/
 		/* selectStatement="select a.spl_notes,a.first_name,a.last_name,b.designation,a.email_id,a.user_id,a.active_status,'' from tblusermaster a,tblOrganizationalPositionMapping b " +
 		 		"	where a.user_id = b.user_id and a.user_id not in(select user_id from tblMapUserPrivilege) ";
 		 		
 		 		 if(empId!=null && empId.trim().length()!=0){
 	                  selectStatement = selectStatement + " and a.spl_notes = '" + empId.trim() + "' " ;
 	              }
 		 				
 		 selectStatement = selectStatement+	" union select a.spl_notes,a.first_name,a.last_name,b.designation,a.email_id,a.user_id,a.active_status,d.role_name " +
 		 		"	from tblusermaster a,tblOrganizationalPositionMapping b,tblMapUserPrivilege c,tblrolemaster d 	where a.user_id = b.user_id and b.user_id=c.user_id and c.role_id=d.role_id ";
 		 
 		 
 		 selectStatement="select a.spl_notes,a.first_name,a.last_name,b.designation,a.email_id,a.user_id,a.active_status,'',c.sup_email,c.sup_name,c.active_status from tblusermaster a,tblOrganizationalPositionMapping b, tblmapsupuserdetails c " +
  		 		"	where a.user_id = b.user_id and a.user_id = c.user_id and a.user_id not in(select user_id from tblMapUserPrivilege) and  a.user_id in(select user_id from tblmapsupuserdetails) ";
  		 		
  		 		 if(empId!=null && empId.trim().length()!=0){
  	                  selectStatement = selectStatement + " and a.spl_notes = '" + empId.trim() + "' " ;
  	              }
  		 				
  		 selectStatement = selectStatement+	" union select a.spl_notes,a.first_name,a.last_name,b.designation,a.email_id,a.user_id,a.active_status,d.role_name ,e.sup_email,e.sup_name,e.active_status " +
  		 		"	from tblusermaster a,tblOrganizationalPositionMapping b,tblMapUserPrivilege c,tblrolemaster d ,tblmapsupuserdetails e 	where a.user_id = b.user_id and b.user_id=c.user_id and c.role_id=d.role_id and a.user_id=e.user_id  ";*/
  		 
  		/*selectStatement="select a.spl_notes,a.first_name,a.last_name,e.dep_name,a.email_id,a.user_id,a.active_status,d.role_name, " +
  				" c.sup_email,c.sup_name,c.active_status,a.login_name,e.dep_id,d.role_id " +
  				" from tblusermaster a,tblOrganizationalPositionMapping b ,tblmapsupuserdetails c ,tblrolemaster d,map_lob_dept_sup_master e " +
  				" where a.user_id = b.user_id and a.user_id = c.user_id  and c.role_id=d.role_id and c.role_id=d.role_id and c.dep_id=e.dep_id ";
  		 		
  		 		 if(empId!=null && empId.trim().length()!=0){
  	                  selectStatement = selectStatement + " and a.spl_notes like '" + empId.trim() + "%' " ;
  	              }
  		 		 if(fName!=null && fName.trim().length()!=0){
  	                  fName = fName.replaceAll("'","''");
  	                  selectStatement = selectStatement + " and a.first_name like '" + fName.trim() + "%' ";
  	              }
  	       
  	              if(lName!=null && lName.trim().length()!=0){
  	                  lName = lName.replaceAll("'","''");
  	                  selectStatement = selectStatement + " and a.last_name like '" + lName.trim() + "%' ";
  	              }
  	            if(supName!=null && supName.trim().length()!=0){
                    selectStatement = selectStatement + " and c.sup_name like '" + supName.trim() + "%' " ;
                }
  		 				
  		 selectStatement = selectStatement+	" union select a.spl_notes,a.first_name,a.last_name,f.dep_name,a.email_id,a.user_id,a.active_status,d.role_name ," +
  		 		" e.sup_email,e.sup_name,e.active_status,a.login_name,f.dep_id,d.role_id   " +
  		 		" from tblusermaster a,tblOrganizationalPositionMapping b,tblMapUserPrivilege c,tblrolemaster d ," +
  		 		" tblmapsupuserdetails e ,map_lob_dept_sup_master f	where a.user_id = b.user_id and b.user_id=c.user_id and c.role_id=d.role_id " +
  		 		" and a.user_id=e.user_id and c.role_id=e.role_id and e.dep_id=f.dep_id  ";*/
 		selectStatement= "select a.spl_notes,a.first_name,a.last_name,f.dep_name,a.email_id,a.user_id,a.active_status,d.role_name ," +
 				" e.sup_email,e.sup_name,e.active_status,a.login_name,f.dep_id,d.role_id, dense_rank() OVER (ORDER BY a.spl_notes) as rank, (select count(1) from tblmapsupuserdetails where a.user_id=user_id group by user_id) as empCount from tblusermaster a,tblOrganizationalPositionMapping b,tblMapUserPrivilege c,tblrolemaster d ," +
 				" tblmapsupuserdetails e ,map_lob_dept_sup_master f where a.user_id = b.user_id and b.user_id=c.user_id and " +
 				" c.role_id=d.role_id  and a.user_id=e.user_id and c.role_id=e.role_id and e.dep_id=f.dep_id ";
 		
  		
  		
              if(fName!=null && fName.trim().length()!=0){
                  fName = fName.replaceAll("'","''");
                  selectStatement = selectStatement + " and a.first_name like '" + fName.trim() + "%' ";
              }
       
              if(lName!=null && lName.trim().length()!=0){
                  lName = lName.replaceAll("'","''");
                  selectStatement = selectStatement + " and a.last_name like '" + lName.trim() + "%' ";
              }
              
              if(empId!=null && empId.trim().length()!=0){
            	  selectStatement = selectStatement + " and a.spl_notes like '" + empId.trim() + "%' " ;
              }
              if(supName!=null && supName.trim().length()!=0){
                  selectStatement = selectStatement + " and e.sup_name like '" + supName.trim() + "%' " ;
              }

             
           //selectStatement = selectStatement +" ORDER BY A.last_name,A.first_name ASC" ;
              selectStatement = selectStatement +" group by a.spl_notes," +
 				" a.first_name,a.last_name,f.dep_name,a.email_id,a.user_id,a.active_status,d.role_name , e.sup_email,e.sup_name," +
 				" e.active_status,a.login_name,f.dep_id,d.role_id   ORDER BY rank ASC" ;
              
           Debug.print("Query : " + selectStatement);

           prepStmt = con.prepareStatement(selectStatement);
           Debug.print("HLCMemberUpdateDAO searchEmpBySearchCriteria() Query: " + selectStatement);
           rs = prepStmt.executeQuery();
           while(rs.next()){
        	  
        	   String emplId=rs.getString(1);
        	   String firstName=rs.getString(2);
        	   String lastName=rs.getString(3);        	  
        	   String designation=rs.getString(4);
        	   String emailId=rs.getString(5);
        	   String userId=rs.getString(6);
        	   Boolean act_stat = rs.getBoolean(7);
        	   String roleName=rs.getString(8);
        	   String supEmail=rs.getString(9);
        	   String supName1=rs.getString(10);
        	   Boolean map_act_stat = rs.getBoolean(11);
        	   String loginName=rs.getString(12);
        	   String depId=rs.getString(13);
        	   String roleId=rs.getString(14);
        	   String rank = rs.getString(15);
        	   String empCount = rs.getString(16);
        	   String actStat=act_stat.toString();
        	   String mapActStat=map_act_stat.toString();
        	   //Debug.print("actStat====="+actStat);
        	   String temp[]={emplId,firstName,lastName,designation,emailId,userId,actStat,roleName,supEmail,supName1,mapActStat,loginName,depId,roleId,rank,empCount};
        	   empSearchList.add(temp);
        	  
        	   
        	   
           }
           rs.close();
           prepStmt.close();
           releaseConnection();
 		
 	   }
 	catch(SQLException sql){
        releaseConnection();
        Debug.print("SQL Exception in MemberUpdateDAO.searchEmpBySearchCriteria():" + sql.getMessage());
    } catch(Exception e){
        releaseConnection();
        Debug.print("General Exception  in MemberUpdateDAO.searchEmpBySearchCriteria():" + e.getMessage());
    }
	return empSearchList;
	   
   }
   
   public boolean addUsrSupMapping(String userId,String supName,String supEmail,String roleId,String depId) throws SQLException
   {
 	  //PreparedStatement prepStmt = null;
 	  boolean empInserStat=false;
 	  makeConnection();
       try {
     	  String insertStatement = "insert into tblmapsupuserdetails (user_id,sup_name,sup_email,role_id,dep_id)"+
                       " values ( ? , ? , ?,?,? ) ";
     	  prepStmt = con.prepareStatement(insertStatement);
     	  prepStmt.setString(1, userId);
           prepStmt.setString(2, supName);
           prepStmt.setString(3, supEmail);
           prepStmt.setString(4, roleId);
           prepStmt.setString(5, depId);
           int count=prepStmt.executeUpdate();
           if(count>=1)
           {
        	   empInserStat=true;
           }
          
           prepStmt.close();
     	  
     	  
       }
       catch(Exception e)
       {
     	  System.out.println("exception in  addUsrSupMapping"+e);
     	  
       }
 	  finally{
 		  con.close();
 	  }
 	  return empInserStat;
   }
   
   public ArrayList getActiveUserEmpIds(){
       Debug.print("RoleManagementDAOImpl.getActiveUserEmpIds():");
       ArrayList activeEmpIdList =  new ArrayList();
       PreparedStatement prepStmt = null;
       ResultSet rs = null;
       makeConnection();
  	try {
  		String selectStatement=" select distinct a.spl_notes  from tblusermaster a,tblOrganizationalPositionMapping b,tblMapUserPrivilege c,  tblrolemaster d , " +
  				" tblmapsupuserdetails e ,map_lob_dept_sup_master f where a.user_id = b.user_id and b.user_id=c.user_id and  c.role_id=d.role_id " +
  				" and a.user_id=e.user_id and c.role_id=e.role_id and e.dep_id=f.dep_id and " +
  				" a.spl_notes not in (select b.spl_notes from tblmapsupuserdetails a,tblusermaster b where a.user_id=b.user_id and a.active_status='0')" ;

       prepStmt = con.prepareStatement(selectStatement);
      
       
      Debug.print("Select getActiveUserEmpIds Query ---->"+ selectStatement);
    
      
       rs = prepStmt.executeQuery();
      
      
       while(rs.next()){
       
           //String roleName = rs.getString(1);
           String empId = rs.getString(1);
           //String privilegeName = rs.getString(3);             
           //String tempArray[]={roleName,entityName,privilegeName};
           String tempArray[]={empId};
           activeEmpIdList.add(tempArray);
          
       }
           rs.close();
           prepStmt.close();
           releaseConnection();
           //Debug.print("RoleManagementDAOImpl.selectMenuBasedOnRoleEntitySubPrivilege():" + roleEntityMapSubList);
       }
       catch(SQLException sql){
           releaseConnection();
           Debug.print("SQL Exception in MemberUpdateDAO.getActiveUserEmpIds():" + sql.getMessage());
       }
       catch(Exception e){
           releaseConnection();
           Debug.print("General Exception  in MemberUpdateDAO.getActiveUserEmpIds():" + e.getMessage());
       }
       return activeEmpIdList;
   }
      /*
	  public ArrayList selectCountryList()throws RemoteException;
        public ArrayList selectLocBasedOnCId(String cId)throws RemoteException;
        public ArrayList selectLOBList()throws RemoteException;
        public ArrayList selectDeptSuperVisorBasedOnCId(String paramValue,String lobId)throws RemoteException;
	  */
    /**
     * Name         :makeConnection
     * Description  :This method will Create a databacse connection
     * @ param      :
     * @return      :void
     * @throws      :EJBException
     */
    
    private void makeConnection() {
        try {
            InitialContext ic = new InitialContext();
            DataSource ds = (DataSource) ic.lookup(dbName);
            con = ds.getConnection();
            Debug.print(" Opening a connection...");
        } catch (Exception ex) {
            Debug.print("Unable to connect to database. " + ex.getMessage());
        }
    }
    
   
    
    /**
     * Name         :releaseConnection
     * Description  :This method will release the databacse connection
     * @ param      :
     * @return      :void
     * @throws      :EJBException
     */
    private void releaseConnection() {
        try {
            //prepStmt.close();
            //rs.close();
            if(!con.isClosed()){
                con.close();
            }
            Debug.print(" Closing a connection...");
        } catch (SQLException ex) {
            Debug.print("releaseConnection: " + ex.getMessage());
        }
    }
}