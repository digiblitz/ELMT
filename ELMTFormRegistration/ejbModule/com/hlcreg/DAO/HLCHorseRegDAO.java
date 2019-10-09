/*
 * HorseRegDAO.java
 *
 * Created on October 6, 2006, 8:29 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcreg.DAO;
import com.hlccommon.util.HLCHorseDescriptionVO;
import com.hlccommon.util.HLCHorseGradeVO;
import com.hlccommon.util.HLCHorseRegListVO;
import com.hlccommon.util.HLCHorseRegisterationVO;
import com.hlccommon.util.HLCHorseRegistrationVO;
import com.hlccommon.util.HLCHorseRelationVO;
import com.hlccommon.util.HLCRequestHorseDetVO;
import com.hlcreg.util.DBHelper;
import com.hlcreg.util.Debug;
import java.rmi.RemoteException;
import javax.ejb.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
/**
 *
 * @author Suresh
 */
public class HLCHorseRegDAO {
    private static final String dbName = "java:/ELMTMSSQLDS";
    private Connection con = null;
    private PreparedStatement prepStmt = null;
    private ResultSet rs = null;
    private Object rs1 ;
    
    /** Creates a new instance of HorseRegDAO */
    public HLCHorseRegDAO() {
        
    }
    
    
    /**
     * Name         :deleteHorseServiceTypeMaster
     * Description  :This method will delete Horse Service type Master based on horse member id
     * @ param      : horse member id
     * @return      :boolean
     * @throws      :SQLException
     */
    
    public boolean deleteHorseServiceTypeMaster(String horseMemberId) throws SQLException {
        try {
            makeConnection();
            
            System.out.println("Inside deleteHorseServiceTypeMaster horseMemberId :  "+horseMemberId);
            String deleteStatement = "DELETE FROM "+DBHelper.USEA_HORSESERVICETYPE +" WHERE horse_member_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(deleteStatement);
            prepStmt.setString(1, horseMemberId);
            int cnt = prepStmt.executeUpdate();
            Debug.print("Successfully Delete the USEA_HORSESERVICETYPE No Of Record : "+cnt);
            prepStmt.close();
        } catch (Exception ex) {
            // releaseConnection(con);
            Debug.print("deleteHorseServiceTypeMaster : " + ex.getMessage());
        }finally{
            releaseConnection(con);
        }
        return true;
    }
    
    
//=============================================Insert Horse Member Details Details =========================================
    public String insertHorseMemberDetails(HLCHorseRegistrationVO objHrRegVO) {
        Debug.print("HorseRegDAO.insertHorseMemberDetails():");
        String horseMemberId = "";
        PreparedStatement prepStmt = null;
        boolean rslt = false;
        try{
            horseMemberId = getMemberId();
            rslt = insertMemberDetails(horseMemberId, objHrRegVO.getMembershipTypeId());
            Debug.print( "          Member Insert Result  Horse:" + rslt);
        } catch(Exception exp){
            Debug.print("Exception while getting HorseMemberId:" + exp.getMessage());
        }
        
        Debug.print("Member Id:" + horseMemberId);
        
        if(horseMemberId!=null && horseMemberId.trim().length()!=0 && rslt==true){
            makeConnection();
            try {
                String insertStatement = "insert into " + DBHelper.USEA_HORSE_MEMBERDETAILS  +
                        " (horse_member_id,  competition_name, registered_name, ba_registered_name, ba_past_name, " +
                        " rider_member_id, prev_rider_member_id, add_rider_member_id, owner_id, trainer_id, prev_owner_id, add_owner_id, " +
                        " prev_owner_name, add_owner_name, payment_id, request_status, horse_service_type_id, amount, registered_by," +
                        " company_status, company_name, contact_first_name, contact_last_name, membership_amount)" +
                        " values ( ?, ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?, ?, ?, ? , ? , ?, ? ,? ,? , ?, ?, ?) ";
                
                prepStmt = con.prepareStatement(insertStatement);
                Debug.print("     horseMemberId:" + horseMemberId);
                Debug.print(">>>>>>>>>>>>>>>>>>>>>>>>>>>>Amount:" + objHrRegVO.getAmount());
                String riderId = objHrRegVO.getRiderMemberId();
                String ownerId = objHrRegVO.getOwnerId();
                
                if(horseMemberId!=null && horseMemberId.trim().length()!=0 && riderId!=null && riderId.trim().length()!=0 &&
                        ownerId!=null && ownerId.trim().length()!=0){
                    prepStmt.setString(1, horseMemberId);
                    prepStmt.setString(2, objHrRegVO.getCompetitionName());
                    prepStmt.setString(3, objHrRegVO.getRegisteredName());
                    prepStmt.setString(4, objHrRegVO.getBaRegisteredName());
                    prepStmt.setString(5, objHrRegVO.getBaPastName());
                    prepStmt.setString(6, objHrRegVO.getRiderMemberId());
                    prepStmt.setString(7, objHrRegVO.getPrevRiderMemberId());
                    prepStmt.setString(8, objHrRegVO.getAddRiderMemberId());
                    prepStmt.setString(9, objHrRegVO.getOwnerId());
                    prepStmt.setString(10, objHrRegVO.getTrainerUserId());
                    prepStmt.setString(11, objHrRegVO.getPrevOwnerId());
                    prepStmt.setString(12, objHrRegVO.getAddOwnerId());
                    prepStmt.setString(13, objHrRegVO.getPrevOwnerName());
                    prepStmt.setString(14, objHrRegVO.getAddOwnerName());
                    prepStmt.setString(15, objHrRegVO.getPaymentId());
                    prepStmt.setString(16, "Pending");
                    // prepStmt.setString(16, objHrRegVO.getMembershipTypeId());
                    prepStmt.setString(17, null);
                    prepStmt.setString(18, objHrRegVO.getAmount());
                    prepStmt.setString(19, objHrRegVO.getRegisteredBy());
                    prepStmt.setBoolean(20, objHrRegVO.isCompanyStatus());
                    prepStmt.setString(21, objHrRegVO.getCompanyName());
                    prepStmt.setString(22, objHrRegVO.getContactFirstName());
                    prepStmt.setString(23, objHrRegVO.getContactLastName());
                    prepStmt.setString(24,objHrRegVO.getMembership_amount());
                    
                    int cnt = prepStmt.executeUpdate();
                    
                    Debug.print("Record Inserted succefully in insertHorseMemberDetails Details horseMemberId , Result:" + cnt + ", "+ horseMemberId );
                }
                prepStmt.close();
                releaseConnection(con);
            } catch(SQLException sql){
                try{
                    if(prepStmt!=null ){
                        prepStmt.close();
                    }
                    if(!con.isClosed()){
                        releaseConnection(con);
                    }
                } catch(Exception exp){
                    Debug.print("General Exception  in HorseRegDAO.insertHorseMemberDetails():" + exp.getMessage());
                }
                
                Debug.print("SQL Exception in HorseRegDAO.insertHorseMemberDetails():" + sql.getMessage());
            } catch(Exception e){
                try{
                    if(prepStmt!=null ){
                        prepStmt.close();
                    }
                    if(!con.isClosed()){
                        releaseConnection(con);
                    }
                } catch(Exception exp){
                    Debug.print("General Exception  in HorseRegDAO.insertHorseMemberDetails():" + exp.getMessage());
                }
                
                Debug.print("General Exception  in HorseRegDAO.insertHorseMemberDetails():" + e.getMessage());
            }
        }
        return horseMemberId;
    }
    
    //=============================================Insert Horse Member Details Details =========================================
    public boolean insertHorseDescriptionDetails(HLCHorseDescriptionVO objHrDescVO) {
        Debug.print("HorseRegDAO.insertHorseDescriptionDetails():");
        boolean result = false;
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String insertStatement = "insert into " + DBHelper.USEA_HORSE_DESCRIPTION  +
                    " (horse_member_id, color, gender, height, year_foaled, " +
                    " breed, country, sire, sire_breed, dam, dam_breed, breed2, sire_breed2, dam_breed2, imported_from, " +
                    " import_date, foreign_grade, foreign_points, assigned_grade, assigned_points, notes, spl_notes)" +
                    " values ( ?, ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? ,? , ? , ? , ? ) ";
            
            prepStmt = con.prepareStatement(insertStatement);
            
            String horseMemberId = objHrDescVO.getHorseMemberId();
            
            Debug.print("      horseMemberId:" + horseMemberId);
            
            if(horseMemberId!=null && horseMemberId.trim().length()!=0 ){
                prepStmt.setString(1, objHrDescVO.getHorseMemberId());
                prepStmt.setString(2, objHrDescVO.getColor());
                prepStmt.setString(3, objHrDescVO.getGender());
                prepStmt.setString(4, objHrDescVO.getHeight());
                prepStmt.setString(5, objHrDescVO.getYearFoaled());
                prepStmt.setString(6, objHrDescVO.getBreed());
                prepStmt.setString(7, objHrDescVO.getCountry());
                prepStmt.setString(8, objHrDescVO.getSire());
                prepStmt.setString(9, objHrDescVO.getSireBreed());
                prepStmt.setString(10, objHrDescVO.getDam());
                prepStmt.setString(11, objHrDescVO.getDamBreed());
                prepStmt.setString(12, objHrDescVO.getBreed2());
                prepStmt.setString(13, objHrDescVO.getSireBreed2());
                prepStmt.setString(14, objHrDescVO.getDamBreed2());
                prepStmt.setString(15, objHrDescVO.getImportedFrom());
                if(objHrDescVO.getImportDate()!=null){
                    prepStmt.setDate(16, DBHelper.toSQLDate(objHrDescVO.getImportDate()));
                } else{
                    prepStmt.setDate(16, null);
                }
                prepStmt.setString(17, objHrDescVO.getForeignGrade());
                prepStmt.setFloat(18, objHrDescVO.getForeignPoints());
                prepStmt.setString(19, objHrDescVO.getAssignedGrade());
                prepStmt.setFloat(20, objHrDescVO.getAssignedPoints());
                prepStmt.setString(21, objHrDescVO.getNotes());
                prepStmt.setString(22, objHrDescVO.getSplNotes());
                
                int cnt = prepStmt.executeUpdate();
                prepStmt.close();
                Debug.print("Record Inserted succefully in insertHorseDescriptionDetails Details: " + cnt);
                if(cnt>=1){
                    result = true;
                }
                
                Debug.print("HorseRegDAO insertHorseDescriptionDetails Status :" + result);
            }
            // prepStmt.close();
            releaseConnection(con);
        }
        
        catch(SQLException sql){
            try{
                if(prepStmt!=null ){
                    prepStmt.close();
                }
                if(!con.isClosed()){
                    releaseConnection(con);
                }
            } catch(Exception exp){
                Debug.print("General Exception  in HorseRegDAO.insertHorseDescriptionDetails():" + exp.getMessage());
            }
            Debug.print("SQL Exception in HorseRegDAO.insertHorseDescriptionDetails():" + sql.getMessage());
        } catch(Exception e){
            try{
                if(prepStmt!=null ){
                    prepStmt.close();
                }
                if(!con.isClosed()){
                    releaseConnection(con);
                }
            } catch(Exception exp){
                Debug.print("General Exception  in HorseRegDAO.insertHorseDescriptionDetails():" + exp.getMessage());
            }
            Debug.print("General Exception  in HorseRegDAO.insertHorseDescriptionDetails():" + e.getMessage());
        }
        return result;
    }
    //==========================================Get Horse_Grade_Level_Id==================================================
    
    /*
     * Author   :   punitha
     * method   :   getHorseGradeLevelId - it returns the  horse_grade_level_id based on the horse grade which is ungraded.
     * return   :   boolean
     * throws   :   SQLException, GeneralException
     */
    public String getHorseGradeLevelId(){
        Debug.print("HorseRegDAO getHorseGradeLevelId:");
        String gradeLevelId = null;
        String gradeName ="U";
        makeConnection();
        try{
            String selectStatement = "SELECT horse_grade_level_id from tblHorseGradeMaster where" +
                    " horse_grade_level_name = ?";
            PreparedStatement prepSelect = con.prepareStatement(selectStatement);
            Debug.print("getHorseGradeLevelId QUERY:" + selectStatement);
            Debug.print("getHorseGradeLevelId gradeName:" + gradeName);
            prepSelect.setString(1,gradeName);
            ResultSet rs = prepSelect.executeQuery();
            
            if(rs.next()){
                gradeLevelId = rs.getString(1);
                System.out.print(" Horse RegDAOgradeLevelId in getHorseGradeLevelId:" + gradeLevelId);
            }
            rs.close();
            prepSelect.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            throw new EJBException("SQL Exception in HorseRegDAO getHorseGradeLevelId:" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            throw new EJBException("General Exception  in HorseRegDAO getHorseGradeLevelId:" + e.getMessage());
        }
        return gradeLevelId;
    }
    //=============================================Insert Horse Grade Details Details =========================================
    /*
     * Author   :   punitha
     * method   :   insertHorseGradeDetails - it inserts the horse grade details based on the horse member id
     * return   :   boolean
     * throws   :   SQLException, GeneralException
     */
    public boolean insertHorseGradeDetails(HLCHorseGradeVO objHrGradeVO){
        Debug.print("objHrGradeVO.insertHorseGradeDetails():" + objHrGradeVO.getHorseMemberId());
        boolean result = false;
        PreparedStatement prepStmt = null;
        String gradeLevelId = getHorseGradeLevelId();
        System.out.print("gradeLevelId in insertHorseGradeDetails:" + gradeLevelId);
        
        
        try {
            makeConnection();
            String insertStatement = "insert into " + DBHelper.USEA_HORSE_GRADE  +
                    " (horse_member_id, career_grade_points, current_grade_points, career_award_points, current_award_points, " +
                    " horse_grade_level_id, assigned_points, grade_1_date, grade_2_date, grade_3_date) " +
                    " values ( ?, ? , ? , ? , ? , ? , ? , ? , ? , ? ) ";
            
            prepStmt = con.prepareStatement(insertStatement);
            String horseMemberId = objHrGradeVO.getHorseMemberId();
            Debug.print("horseMemberId in insertHorseGradeDetails:" + horseMemberId);
            
            if(horseMemberId!=null && horseMemberId.trim().length()!=0 ){
                prepStmt.setString(1, objHrGradeVO.getHorseMemberId());
                prepStmt.setFloat(2, objHrGradeVO.getCareerGradePoints());
                prepStmt.setFloat(3, objHrGradeVO.getCurrentGradePoints());
                prepStmt.setFloat(4, objHrGradeVO.getCareerAwardPoints());
                prepStmt.setFloat(5, objHrGradeVO.getCareerAwardPoints());
                //  prepStmt.setString(6, "7d47b140-08f6-4abb-a3cd-4ce4b06b6668");
                if(gradeLevelId!=null && gradeLevelId.trim().length()!=0){
                    prepStmt.setString(6, gradeLevelId);
                }
                prepStmt.setFloat(7, objHrGradeVO.getAssignedPoints());
                if(objHrGradeVO.getGrade1Date()!=null){
                    prepStmt.setDate(8, DBHelper.toSQLDate(objHrGradeVO.getGrade1Date()));
                } else{
                    prepStmt.setDate(8, null);
                }
                if(objHrGradeVO.getGrade1Date()!=null){
                    prepStmt.setDate(9, DBHelper.toSQLDate(objHrGradeVO.getGrade1Date()));
                } else{
                    prepStmt.setDate(9, null);
                }
                if(objHrGradeVO.getGrade1Date()!=null){
                    prepStmt.setDate(10, DBHelper.toSQLDate(objHrGradeVO.getGrade1Date()));
                } else{
                    prepStmt.setDate(10, null);
                }
                
                int cnt = prepStmt.executeUpdate();
                prepStmt.close();
                Debug.print("Record Inserted succefully in insertHorseGradeDetails Details: " + cnt);
                if(cnt>=1){
                    result = true;
                }
                
                Debug.print("HorseRegDAO insertHorseGradeDetails Status :" + result);
            }
            // prepStmt.close();
            //releaseConnection(con);
        } catch(Exception e){
            e.printStackTrace();
            try {
                prepStmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally{
            releaseConnection(con);
        }
        return result;
    }
    
    
    //=============================================Insert Horse Member Relation  Details Details =========================================
    public boolean insertHorseRelationDetails(HLCHorseRelationVO objHrRelVO) {
        Debug.print("HorseRegDAO.insertHorseRelationDetails():");
        boolean result = false;
        PreparedStatement prepStmt = null;
        
        try {
            makeConnection();
            String insertStatement = "insert into " + DBHelper.USEA_HORSE_RELATION  +
                    " (horse_member_id, relationship_type_id, user_id, add_date, relationship_status, request_id)" +
                    " values ( ?, ? , ? , ?, ?, ?) ";
            
            prepStmt = con.prepareStatement(insertStatement);
            
            Debug.print("getRelationshipTypeId:" + objHrRelVO.getRelationshipTypeId());
            Debug.print("getUserId:" + objHrRelVO.getUserId());
            Debug.print("getRequestId:" + objHrRelVO.getRequestId());
            
            if(objHrRelVO.getRequestId()!=null && objHrRelVO.getRequestId().trim().equalsIgnoreCase("null")){
                objHrRelVO.setRequestId(null);
            }
            
            String horseMemberId = objHrRelVO.getHorseMemberId();
            Debug.print("      horseMemberId:" + horseMemberId);
            if(horseMemberId!=null && horseMemberId.trim().length()!=0 ){
                prepStmt.setString(1, objHrRelVO.getHorseMemberId());
                prepStmt.setString(2, objHrRelVO.getRelationshipTypeId());
                prepStmt.setString(3, objHrRelVO.getUserId());
                prepStmt.setDate(4, DBHelper.toSQLDate(new Date()));
                prepStmt.setString(5, objHrRelVO.getRelationshipStatus());
                prepStmt.setString(6, objHrRelVO.getRequestId());
                
                int cnt = prepStmt.executeUpdate();
                prepStmt.close();
                Debug.print("Record Inserted succefully in insertHorseRelationDetails Details: " + cnt);
                if(cnt>=1){
                    result = true;
                }
                Debug.print("HorseRegDAO insertHorseRelationDetails Status :" + result);
            }
            // prepStmt.close();
            //releaseConnection(con);
        }
        
        catch(SQLException sql){
            sql.printStackTrace();
        } catch(Exception e){
            e.printStackTrace();
        } finally{
            releaseConnection(con);
        }
        return result;
    }
    
    //=============================================Insert Horse Member Relation  Details Details =========================================
    public boolean insertMembershipRefundDetails(String userId, String comments, float balanceAmount) {
        Debug.print("HorseRegDAO.insertMembershipRefundDetails():");
        boolean result = false;
        PreparedStatement prepStmt = null;
        
        try {
            makeConnection();
            String insertStatement = "insert into tblMembershipRefundDetails " +
                    " (user_id, comments, balance_amount, refund_status)" +
                    " values ( ? , ? , ?, ?) ";
            
            prepStmt = con.prepareStatement(insertStatement);
            
            prepStmt.setString(1, userId);
            prepStmt.setString(2, comments);
            prepStmt.setFloat(3, balanceAmount);
            prepStmt.setString(4, "Approved");
            
            int cnt = prepStmt.executeUpdate();
            //prepStmt.close();
            Debug.print("Record Inserted succefully in insertMembershipRefundDetails Details: " + cnt);
            if(cnt>=1){
                result = true;
            }
            // releaseConnection(con);
        }
        
        catch(SQLException sql){
            Debug.print("SQL Exception in HorseRegDAO.insertMembershipRefundDetails():" + sql.getMessage());
        } catch(Exception e){
            Debug.print("General Exception  in HorseRegDAO.insertMembershipRefundDetails():" + e.getMessage());
        } finally {
            try{
                if(prepStmt!=null ){
                    prepStmt.close();
                }
                if(!con.isClosed()){
                    releaseConnection(con);
                }
            } catch(Exception exp){
                Debug.print("General Exception  in HorseRegDAO.insertMembershipRefundDetails():" + exp.getMessage());
            }
        }
        return result;
    }
    
    //=====================================================================================
    
    public String getMemberId() throws SQLException {
        Debug.print("HorseRegDAO getMemberId");
        makeConnection();
        String memberId = null;
        int nextId = 0;
        try{
            String selectStatement = "SELECT max(cast(member_id as int)) from tblMemberDetails" ;
            PreparedStatement prepSelect = con.prepareStatement(selectStatement);
            ResultSet rs = prepSelect.executeQuery();
            
            if(rs.next()) {
                nextId = rs.getInt(1);
            }
            
            Debug.print("member Id returned from table before increment : "+nextId);
            
            if(nextId==0){
                nextId = 10000;
            } else{
                nextId = nextId+1;
            }
            
            Debug.print("member Id returned from table after increment: "+nextId);
            rs.close();
            prepSelect.close();
            memberId = Long.toString(nextId);
            Debug.print("member Id returned in HorseRegDAO getMemberId(): "+memberId);
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            throw new EJBException("SQL Exception in HorseRegDAO getMemberId:" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            throw new EJBException("General Exception  in HorseRegDAO getMemberId:" + e.getMessage());
        }
        return memberId;
    }
    
    
    //=============================================Insert User Registration Details =========================================
    public String insertUserDetails(String firstName, String lastName, java.util.Date dob, String  gender, String emailId, String loginName) {
        Debug.print("HorseRegDAO.insertUserDetails():");
        boolean result = false;
        PreparedStatement prepStmt = null;
        String userId  = "";
        try{
            userId = getNextId();
        } catch(Exception e){
            Debug.print("Exception while calling getNextId():" + e.getMessage());
        }
        
        Debug.print("HorseRegDAO.insertUserDetails() After Getting userId :" + userId);
        if(userId!=null && userId.trim().length()!=0){
            makeConnection();
            try {
                String insertStatement = "insert into " + DBHelper.USEA_MMS_USERMASTER + " (user_id, first_name,"+
                        "last_name,dob,gender,email_id, contact_type_id, login_name)" +
                        " values ( ? , ? , ? , ? , ?, ?, ?, ?) ";
                
                prepStmt = con.prepareStatement(insertStatement);
                prepStmt.setString(1, userId);
                prepStmt.setString(2, firstName);
                prepStmt.setString(3, lastName);
                if(dob!=null){
                    prepStmt.setDate(4, DBHelper.toSQLDate(dob));
                } else{
                    prepStmt.setDate(4, null);
                }
                prepStmt.setString(5, gender);
                prepStmt.setString(6, emailId);
                prepStmt.setString(7, "0cc42d09-340c-42e8-b773-54bdf0688527");
                prepStmt.setString(8, loginName);
                
                int cnt = prepStmt.executeUpdate();
                Debug.print("Record Inserted succefully into insertUserDetails "+cnt);
                result = true;
                
                Debug.print("HorseRegDAO insertUserDetails() Status :" + result);
                prepStmt.close();
                releaseConnection(con);
            } catch(SQLException sql){
                releaseConnection(con);
                Debug.print("SQL Exception in HorseRegDAO.insertUserDetails():" + sql.getMessage());
            } catch(Exception e){
                releaseConnection(con);
                Debug.print("General Exception  in HorseRegDAO.insertUserDetails():" + e.getMessage());
            }
        }
        return userId;
    }
    
    public String insertHorseUserDetails() {
        Debug.print("HorseRegDAO.insertHorseUserDetails():");
        boolean result = false;
        PreparedStatement prepStmt = null;
        String userId  = "";
        try{
            userId = getNextId();
        } catch(Exception e){
            Debug.print("Exception while calling getNextId():" + e.getMessage());
        }
        String userTypeId = selectUserTypeId("Horse");
        if(userTypeId.trim().length()==0){
            userTypeId = null;
        }
        Debug.print("HorseRegDAO.insertHorseUserDetails() After Getting userId :" + userId);
        if(userId!=null && userId.trim().length()!=0){
            makeConnection();
            try {
                String insertStatement = "insert into " + DBHelper.USEA_MMS_USERMASTER + " (user_id, user_type_id) values (? , ?)";
                prepStmt = con.prepareStatement(insertStatement);
                prepStmt.setString(1, userId);
                prepStmt.setString(2, userTypeId);
                int cnt = prepStmt.executeUpdate();
                Debug.print("Record Inserted succefully into insertHorseUserDetails "+cnt);
                result = true;
                
                Debug.print("HorseRegDAO insertHorseUserDetails() Status :" + result);
                prepStmt.close();
                releaseConnection(con);
            } catch(SQLException sql){
                releaseConnection(con);
                Debug.print("SQL Exception in HorseRegDAO.insertHorseUserDetails():" + sql.getMessage());
            } catch(Exception e){
                releaseConnection(con);
                Debug.print("General Exception  in HorseRegDAO.insertHorseUserDetails():" + e.getMessage());
            }
        }
        return userId;
    }
    
    
    //=============================================Insert User Contact Details =========================================
    public boolean insertContactDetails(String userId, String address1, String city, String  state, String country,
            String zip, String phoneNo, String faxNo) {
        Debug.print("HorseRegDAO.insertContactDetails():");
        boolean result = false;
        PreparedStatement prepStmt = null;
        if(userId!=null && userId.trim().length()!=0){
            makeConnection();
            try {
                String insertStatement = "insert into " + DBHelper.USEA_MMS_CONTACT_DETAILS + " (user_id, address1,"+
                        " city, state, country, zip, phone_no,  fax_no, contact_type_id)" +
                        " values ( ? , ? , ? , ? , ? , ? ,  ? , ? , ?) ";
                prepStmt = con.prepareStatement(insertStatement);
                prepStmt.setString(1, userId);
                prepStmt.setString(2, address1);
                prepStmt.setString(3, city);
                prepStmt.setString(4, state);
                prepStmt.setString(5, country);
                prepStmt.setString(6, zip);
                prepStmt.setString(7, phoneNo);
                //prepStmt.setString(8, mobileNo);
                prepStmt.setString(8, faxNo);
                prepStmt.setString(9, "0cc42d09-340c-42e8-b773-54bdf0688527");
                
                int cnt = prepStmt.executeUpdate();
                Debug.print("Record Inserted succefully into insertContactDetails "+cnt);
                result = true;
                
                Debug.print("HorseRegDAO insertContactDetails() Status :" + result);
                prepStmt.close();
                releaseConnection(con);
            } catch(SQLException sql){
                releaseConnection(con);
                Debug.print("SQL Exception in HorseRegDAO.insertContactDetails():" + sql.getMessage());
            } catch(Exception e){
                releaseConnection(con);
                Debug.print("General Exception  in HorseRegDAO.insertContactDetails():" + e.getMessage());
            }
        }
        return result;
    }
    
    //=============================================Insert User Registration Details =========================================
    public boolean insertMemberDetails(String memberId, String membershipTypeId) {
        Debug.print("HorseRegDAO.insertMemberDetails(): member_id : " + memberId);
        boolean result = false;
        PreparedStatement prepStmt = null;
        
        String userId = insertHorseUserDetails();
        String statusId = selectStatusId("Pending");
        if(memberId!=null && memberId.trim().length()!=0){
            makeConnection();
            try {
                String insertStatement = "insert into tblMemberDetails (member_id, membership_type_id, "+
                        "expiry_date, user_id, status_id) values ( ? , ? , ?, ?, ?) ";
                
                prepStmt = con.prepareStatement(insertStatement);
                prepStmt.setString(1, memberId);
                prepStmt.setString(2, membershipTypeId);
                prepStmt.setDate(3, DBHelper.toSQLDate(new Date()));
                prepStmt.setString(4, userId);
                prepStmt.setString(5, statusId);
                
                int cnt = prepStmt.executeUpdate();
                Debug.print("Record Inserted succefully into insertMemberDetails "+cnt);
                if(cnt>=1){
                    result = true;
                }
                
                Debug.print("HorseRegDAO insertMemberDetails() Status :" + result);
                prepStmt.close();
                releaseConnection(con);
            } catch(SQLException sql){
                releaseConnection(con);
                Debug.print("SQL Exception in HorseRegDAO.insertMemberDetails():" + sql.getMessage());
            } catch(Exception e){
                releaseConnection(con);
                Debug.print("General Exception  in HorseRegDAO.insertMemberDetails():" + e.getMessage());
            }
        }
        return result;
    }
    
    //=============================================Insert User Registration Details =========================================
    public boolean updateMemberDetails(String memberId, String membershipTypeId) {
        Debug.print("HorseRegDAO.updateMemberDetails(): member_id : " + memberId);
        boolean result = false;
        PreparedStatement prepStmt = null;
        
        String statusId = selectStatusId("Pending");
        if(memberId!=null && memberId.trim().length()!=0){
            makeConnection();
            try {
                String insertStatement = "update  tblMemberDetails set membership_type_id = ?, "+
                        " status_id = ? where member_id = ? ";
                
                prepStmt = con.prepareStatement(insertStatement);
                prepStmt.setString(1, membershipTypeId);
                prepStmt.setString(2, statusId);
                prepStmt.setString(3, memberId);
                
                int cnt = prepStmt.executeUpdate();
                Debug.print("Record Inserted succefully into updateMemberDetails "+cnt);
                if(cnt>=1){
                    result = true;
                }
                Debug.print("HorseRegDAO updateMemberDetails() Status :" + result);
                prepStmt.close();
                releaseConnection(con);
            } catch(SQLException sql){
                releaseConnection(con);
                Debug.print("SQL Exception in HorseRegDAO.updateMemberDetails():" + sql.getMessage());
            } catch(Exception e){
                releaseConnection(con);
                Debug.print("General Exception  in HorseRegDAO.updateMemberDetails():" + e.getMessage());
            }
        }
        return result;
    }
    
    //=============================================Insert User Registration Details =========================================
    public String insertRequestHorseMemberDetails(HLCRequestHorseDetVO reqHrDetVO) {
        Debug.print("HorseRegDAO.insertRequestHorseMemberDetails(): Horse_member_id : " + reqHrDetVO.getHorseMemberId());
        boolean result = false;
        PreparedStatement prepStmt = null;
        String reqId = "";
        try{
            reqId = getNextId();
        } catch(SQLException e){
            Debug.print("Exception while calling getNextId():" + e.getMessage());
        }
        //String userId = insertHorseUserDetails();
        String statusId = selectStatusId("Pending");
        if(reqHrDetVO!=null && reqHrDetVO.getHorseMemberId()!=null){
            makeConnection();
            try {
                String insertStatement = "insert into tblHorseChangeRequestDetails (request_id, horse_member_id, payment_id, "+
                        " req_owner_id, req_rider_id, req_horse_name, req_horse_reg_name, requested_by, req_amount, req_date, add_payment_status) " +
                        " values ( ?, ?, ?, ?, ?, ?, ?, ? ,?, ?, ?) ";
                
                prepStmt = con.prepareStatement(insertStatement);
                prepStmt.setString(1,reqId);
                prepStmt.setString(2, reqHrDetVO.getHorseMemberId());
                prepStmt.setString(3, reqHrDetVO.getPaymentId());
                prepStmt.setString(4, reqHrDetVO.getReqOwnerId());
                prepStmt.setString(5, reqHrDetVO.getReqRiderId());
                prepStmt.setString(6, reqHrDetVO.getReqHorseName());
                prepStmt.setString(7, reqHrDetVO.getReqHorseRegName());
                prepStmt.setString(8, reqHrDetVO.getRequestedBy());
                prepStmt.setFloat(9, reqHrDetVO.getReqAmount());
                prepStmt.setDate(10, DBHelper.toSQLDate(new Date()));
                prepStmt.setBoolean(11, reqHrDetVO.isAddPaymentStatus());
                
                int cnt = prepStmt.executeUpdate();
                Debug.print("Record Inserted succefully into insertRequestHorseMemberDetails "+cnt);
                if(cnt>=1){
                    result = true;
                }
                
                Debug.print("HorseRegDAO insertRequestHorseMemberDetails() Status :" + result);
                prepStmt.close();
                releaseConnection(con);
            } catch(SQLException sql){
                releaseConnection(con);
                Debug.print("SQL Exception in HorseRegDAO.insertRequestHorseMemberDetails():" + sql.getMessage());
            } catch(Exception e){
                releaseConnection(con);
                Debug.print("General Exception  in HorseRegDAO.insertRequestHorseMemberDetails():" + e.getMessage());
            }
        }
        return reqId;
    }
    
    
    //=============================================Insert User Registration Details =========================================
    public boolean insertServiceDetails(String horseMemberId, String serviceTypeId) {
        Debug.print("HorseRegDAO.insertServiceDetails():");
        boolean result = false;
        PreparedStatement prepStmt = null;
        
        
        try {
            makeConnection();
            String insertStatement = "insert into tblHorseServiceTypeDetails (horse_member_id, horse_service_type_id) "+
                    " values ( ? , ? ) ";
            
            prepStmt = con.prepareStatement(insertStatement);
            
            prepStmt.setString(1, horseMemberId);
            prepStmt.setString(2, serviceTypeId);
            
            int cnt = prepStmt.executeUpdate();
            Debug.print("Record Inserted succefully into insertContactDetails "+cnt);
            if(cnt>=1){
                result = true;
            }
            
            
            Debug.print("HorseRegDAO insertUserDetails() Status :" + result);
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.insertUserDetails():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.insertUserDetails():" + e.getMessage());
        } finally{
            releaseConnection(con);
        }
        return result;
    }
    
    
    public boolean deleteHorseServiceType(String horseMemberId) {
        Debug.print("HorseRegDAO.deleteRow()");
        boolean result = false;
        makeConnection();
        try{
            String deleteStatement = "delete from tblHorseServiceTypeDetails  where horse_member_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(deleteStatement);
            prepStmt.setString(1, horseMemberId);
            prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection(con);
            result = true;
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.deleteHorseServiceType():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.deleteHorseServiceType():" + e.getMessage());
        }
        return result;
    }
    
    public String getNextId() throws SQLException {
        Debug.print("HorseRegDAO getNextUserId");
        makeConnection();
        String nextId = null;
        try{
            String selectStatement = "select newid() as userId";
            Debug.print("HorseRegDAO getNextUserId:" + selectStatement);
            PreparedStatement prepSelect = con.prepareStatement(selectStatement);
            ResultSet rs = prepSelect.executeQuery();
            rs.next();
            nextId = rs.getString(1);
            rs.close();
            prepSelect.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            throw new EJBException("SQL Exception in getNextUserId:" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            throw new EJBException("General Exception  in getNextUserId:" + e.getMessage());
        }
        return nextId;
    }
    
    //=============================================select a particular Horse Details =========================================
    public ArrayList selectAllHorseDetails(String requestStatus, String paramMembershipTypeId, int pageNo){
        Debug.print("HorseRegDAO.selectAllHorseDetails():");
        ArrayList horseList = new ArrayList();
        PreparedStatement prepStmt = null;
        String command = "{call sp_get_registeredhorses(?,?,?)}";
        makeConnection();
        try {
                /* String selectStatement = " select A.horse_member_id, A.competition_name, A.rider_member_id, " +
                         " B.first_name, B.last_name, A.request_status, A.add_date, A.owner_id, A.membership_type_id, " +
                         " C.membership_type_name, A.registered_by  from tblHorseMemberDetails A," +
                         " tblUserMaster B, tblMembershipTypeMaster C , tblUserPaymentDetails D where A.owner_id = B.user_id and" +
                         " A.membership_type_id = C.membership_type_id and A.payment_id = D.payment_id " +
                         " and  A.request_status =? and A.membership_type_id  = ?";
                 */
            
                /* String selectStatement = "select A.horse_member_id, A.competition_name, A.rider_member_id, " +
                         " B.first_name, B.last_name, A.request_status, A.add_date, A.owner_id, E.membership_type_id, " +
                         " C.membership_type_name, A.registered_by from tblHorseMemberDetails " +
                         " A left join tblUserMaster B ON A.owner_id = B.user_id left join tblMemberDetails E ON " +
                         " A.horse_member_id = E.member_id left join tblMembershipStatusMaster F ON " +
                         " E.status_id = F.status_id left join tblMembershipTypeMaster C ON  " +
                         " C.membership_type_id = E.membership_type_id left join tblUserPaymentDetails D ON " +
                         " A.payment_id = D.payment_id where  E.status_id = ? and E.membership_type_id  = ? order by A.add_date desc ";
                 */
            
            //prepStmt = con.prepareStatement(selectStatement);
            CallableStatement cstmt = con.prepareCall(command);
            //prepStmt.setString(1,requestStatus);
            //prepStmt.setString(2,paramMembershipTypeId);
            cstmt.setInt(1,pageNo);
            cstmt.setString(2, requestStatus);
            cstmt.setString(3, paramMembershipTypeId);
            
            //cstmt.registerOutParameter(1, Types.VARCHAR);
            //cstmt.registerOutParameter(2, Types.VARCHAR);
            // ResultSet rs = prepStmt.executeQuery();
            ResultSet rs = cstmt.executeQuery();
            //Debug.print("Query:" + selectStatement);
            
            while(rs.next()){
                HLCHorseRegListVO objHrListVO = new HLCHorseRegListVO();
                //String horseId = rs.getString(1);
                String horseMemberId = rs.getString(1);
                String horseName = rs.getString(2);
                String riderID = rs.getString(3);
                String firstName = rs.getString(4);
                String lastName = rs.getString(5);
                String reqStatus = rs.getString(6);
                Date addDate = rs.getDate(7);
                String ownerId =  rs.getString(8);
                String membershipTypeId = rs.getString(9);
                String membershipTypeName = rs.getString(10);
                String registeredBy = rs.getString(11);
                String statusId = rs.getString(12);
                String paymentId = rs.getString(13);
                int rCnt = rs.getInt(14);
                
                // objHrListVO.setHorseId(horseId);
                objHrListVO.setHorseMemberId(horseMemberId);
                objHrListVO.setHorseName(horseName);
                objHrListVO.setRiderID(riderID);
                objHrListVO.setFirstName(firstName);
                objHrListVO.setLastName(lastName);
                objHrListVO.setReqStatus(reqStatus);
                objHrListVO.setAddDate(addDate);
                objHrListVO.setOwnerId(ownerId);
                objHrListVO.setMembershipTypeId(membershipTypeId);
                objHrListVO.setMembershipTypeName(membershipTypeName);
                objHrListVO.setPaymentId(paymentId);
                
                if(registeredBy!=null){
                    registeredBy = selectRegistrarName(registeredBy);
                }
                objHrListVO.setRegisteredBy(registeredBy);
                horseList.add(objHrListVO);
            }
            rs.close();
            //prepStmt.close();
            cstmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.selectAllHorseDetails():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.selectAllHorseDetails():" + e.getMessage());
        }
        return horseList;
    }
    
    //=============================================select a particular Horse Details =========================================
    public int countHorseRegisteration(String requestStatus, String paramMembershipTypeId){
        Debug.print("HorseRegDAO.countHorseRegisteration():");
        PreparedStatement prepStmt = null;
        int rowCnt = 0;
        makeConnection();
        try {
            String selectStatement = "select  count(*) from tblHorseMemberDetails " +
                    " A left join tblUserMaster B ON A.owner_id = B.user_id left join tblMemberDetails E ON " +
                    " A.horse_member_id = E.member_id left join tblMembershipStatusMaster F ON " +
                    " E.status_id = F.status_id left join tblMembershipTypeMaster C ON  " +
                    " C.membership_type_id = E.membership_type_id left join tblUserPaymentDetails D ON " +
                    " A.payment_id = D.payment_id where  E.status_id = ? and E.membership_type_id  = ? ";
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,requestStatus);
            prepStmt.setString(2,paramMembershipTypeId);
            ResultSet rs = prepStmt.executeQuery();
            Debug.print("Query:" + selectStatement);
            
            if(rs.next()){
                rowCnt = rs.getInt(1);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.countHorseRegisteration():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.countHorseRegisteration():" + e.getMessage());
        }
        return rowCnt;
    }
    
    //=============================================select a particular Horse Details =========================================
    public float getBalanceAmount(String paymentId){
        Debug.print("HorseRegDAO.getBalanceAmount():");
        PreparedStatement prepStmt = null;
        float balAmount = 0.0f;
        makeConnection();
        try {
            String selectStatement = "select  pending_amount from tblUserPaymentDetails " +
                    " where  payment_id = ?";
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,paymentId);
            
            ResultSet rs = prepStmt.executeQuery();
            
            if(rs.next()){
                balAmount = rs.getFloat(1);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.getBalanceAmount():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.getBalanceAmount():" + e.getMessage());
        }
        return balAmount;
    }
    
    //=============================================select a particular Horse Details =========================================
    public ArrayList selectAllStatusDetails(){
        Debug.print("HorseRegDAO.selectAllStatusDetails():");
        ArrayList statusList = new ArrayList();
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            
            String selectStatement = "select status_id, status_name from tblMembershipStatusMaster ";
            
            prepStmt = con.prepareStatement(selectStatement);
            ResultSet rs = prepStmt.executeQuery();
            while(rs.next()){
                String status_id = rs.getString(1);
                String status_name = rs.getString(2);
                String statusArray[] = {status_id, status_name};
                statusList.add(statusArray);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.selectAllStatusDetails():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.selectAllHorseDetails():" + e.getMessage());
        }
        Debug.print("HorseRegDAO.selectAllStatusDetails() Result Size:" + statusList.size());
        return statusList;
    }
    
    
    //=============================================select a particular Horse Details =========================================
  /*  public ArrayList selectAllHorseDetails(String requestStatus){
            Debug.print("HorseRegDAO.selectAllHorseDetails():");
            ArrayList horseList = new ArrayList();
            PreparedStatement prepStmt = null;
            makeConnection();
            try {
                 String selectStatement = " select A.horse_member_id, A.competition_name, A.rider_member_id, " +
                         " B.first_name, B.last_name, A.request_status, A.add_date, A.owner_id, A.membership_type_id, " +
                         " C.membership_type_name, A.registered_by  from tblHorseMemberDetails A," +
                         " tblUserMaster B, tblMembershipTypeMaster C  where A.owner_id = B.user_id and" +
                         " A.membership_type_id = C.membership_type_id " +
                         " and  A.request_status =?";
   
                prepStmt = con.prepareStatement(selectStatement);
                prepStmt.setString(1,requestStatus);
               // prepStmt.setString(2,paramMembershipTypeId);
                ResultSet rs = prepStmt.executeQuery();
                Debug.print("Query:"+prepStmt);
                while(rs.next()){
                    HorseRegListVO objHrListVO = new HorseRegListVO();
                    //String horseId = rs.getString(1);
                    String horseMemberId = rs.getString(1);
                    String horseName = rs.getString(2);
                    String riderID = rs.getString(3);
                    String firstName = rs.getString(4);
                    String lastName = rs.getString(5);
                    String reqStatus = rs.getString(6);
                    Date addDate = rs.getDate(7);
                    String ownerId =  rs.getString(8);
                    String membershipTypeId = rs.getString(9);
                    String membershipTypeName = rs.getString(10);
                    String registeredBy = rs.getString(11);
   
                   // objHrListVO.setHorseId(horseId);
                    objHrListVO.setHorseMemberId(horseMemberId);
                    objHrListVO.setHorseName(horseName);
                    objHrListVO.setRiderID(riderID);
                    objHrListVO.setFirstName(firstName);
                    objHrListVO.setLastName(lastName);
                    objHrListVO.setReqStatus(reqStatus);
                    objHrListVO.setAddDate(addDate);
                    objHrListVO.setOwnerId(ownerId);
                    objHrListVO.setMembershipTypeId(membershipTypeId);
                    objHrListVO.setMembershipTypeName(membershipTypeName);
   
                    if(registeredBy!=null){
                        registeredBy = selectRegistrarName(registeredBy);
                    }
                    objHrListVO.setRegisteredBy(registeredBy);
   
                    horseList.add(objHrListVO);
                }
                rs.close();
                prepStmt.close();
                 releaseConnection(con);
            }
        catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.selectAllHorseDetails():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.selectAllHorseDetails():" + e.getMessage());
        }
        return horseList;
    }
   
   */
    //=============================================select a particular Horse owner Details =========================================
    public ArrayList selectAllHorseDetailsByOwnerId(String ownerId, int rCnt){
        Debug.print("HorseRegDAO.selectAllHorseDetailsByOwnerId() ownerId:" + ownerId);
        ArrayList horseList = new ArrayList();
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
                /*
                 String selectStatement = " select DISTINCT A.horse_member_id, A.competition_name, A.add_date, " +
                        " C.membership_type_name, D.status_name " +
                        " from tblHorseMemberDetails A, tblMemberDetails B, tblMembershipTypeMaster C, " +
                        " tblMembershipStatusMaster D, tblHorseMemberRelationDetails E, tblUserMaster F " +
                        " where A.horse_member_id = B.member_id and B.membership_type_id = C.membership_type_id and " +
                        " A.horse_member_id = E.horse_member_id and B.status_id = D.status_id and " +
                        " E.user_id = F.user_id and F.user_id = ? order by A.add_date desc";
                 */
            
            String command = "{ call sp_get_user_registeredhorses(?,?)}";
            CallableStatement cstmt = con.prepareCall(command);
            
            cstmt.setInt(1,rCnt);
            cstmt.setString(2,ownerId);
            Debug.print("Calling sp_get_user_registeredhorses");
            
            ResultSet rs = cstmt.executeQuery();
            while(rs.next()){
                HLCHorseRegListVO objHrListVO = new HLCHorseRegListVO();
                String horseMemberId = rs.getString(1);
                String horseName = rs.getString(2);
                Date addDate = rs.getDate(3);
                String membershipTypeName = rs.getString(4);
                String statusName = rs.getString(5);
                
                objHrListVO.setHorseMemberId(horseMemberId);
                objHrListVO.setHorseName(horseName);
                objHrListVO.setAddDate(addDate);
                objHrListVO.setMembershipTypeName(membershipTypeName);
                objHrListVO.setStatusName(statusName);
                horseList.add(objHrListVO);
            }
            rs.close();
            cstmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.selectAllHorseDetailsByOwnerId():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.selectAllHorseDetailsByOwnerId():" + e.getMessage());
        }
        Debug.print("Size of User Horse:" + horseList.size());
        return horseList;
    }
    
    
    //=============================================select a particular Horse owner Details =========================================
    public ArrayList selectAllPendingPaymentsForHorse(String ownerId){
        Debug.print("HorseRegDAO.selectAllPendingPaymentsForHorse() ownerId:" + ownerId);
        ArrayList horseList = new ArrayList();
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
              /*  String selectStatement = " select DISTINCT A.horse_member_id, A.competition_name, A.add_date," +
                         " C.membership_type_name, D.status_name, G.amount,G.check_amount, G.pending_amount, A.payment_id from tblHorseMemberDetails A, " +
                         " tblMemberDetails B, tblMembershipTypeMaster C, tblMembershipStatusMaster D, " +
                         " tblUserMaster F , tblUserPaymentDetails G where A.horse_member_id = B.member_id and " +
                         " B.membership_type_id = C.membership_type_id and B.status_id = D.status_id and " +
                         " A.owner_id = F.user_id and A.owner_id = ? and " +
                         " A.payment_id = G.payment_id and  G.ssl_result =? order by A.add_date desc ";
               */
            
            String selectStatement = " select DISTINCT A.horse_member_id, A.competition_name, A.add_date, " +
                    " C.membership_type_name, D.status_name, G.amount,G.check_amount, G.pending_amount, " +
                    " A.payment_id from tblHorseMemberDetails A, tblMemberDetails B, tblMembershipTypeMaster C, " +
                    " tblMembershipStatusMaster D, tblUserMaster F , tblUserPaymentDetails G, " +
                    " tblHorseMemberRelationDetails H  where A.horse_member_id = B.member_id and " +
                    " B.membership_type_id = C.membership_type_id and B.status_id = D.status_id and " +
                    " A.horse_member_id = H.horse_member_id and A.payment_id = G.payment_id and G.ssl_result =? and " +
                    " F.user_id = H.user_id and H.user_id = ? " +
                    " order by A.add_date desc " ;
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,"1");
            prepStmt.setString(2,ownerId);
            ResultSet rs = prepStmt.executeQuery();
            
            while(rs.next()){
                HLCHorseRegListVO objHrListVO = new HLCHorseRegListVO();
                String horseMemberId = rs.getString(1);
                String horseName = rs.getString(2);
                Date addDate = rs.getDate(3);
                String membershipTypeName = rs.getString(4);
                String statusName = rs.getString(5);
                float amount = rs.getFloat(6);
                float checkAmount = rs.getFloat(7);
                float pendingAmount = rs.getFloat(8);
                String paymentId = rs.getString(9);
                
                objHrListVO.setHorseMemberId(horseMemberId);
                objHrListVO.setHorseName(horseName);
                objHrListVO.setAddDate(addDate);
                objHrListVO.setMembershipTypeName(membershipTypeName);
                objHrListVO.setStatusName(statusName);
                objHrListVO.setAmount(amount);
                objHrListVO.setCheckAmount(checkAmount);
                objHrListVO.setPendingAmount(pendingAmount);
                objHrListVO.setPaymentId(paymentId);
                horseList.add(objHrListVO);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.selectAllPendingPaymentsForHorse():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.selectAllPendingPaymentsForHorse():" + e.getMessage());
        }
        Debug.print("Size of User Horse:" + horseList.size());
        return horseList;
    }
    
    //=============================================select a particular Horse owner Details =========================================
    public ArrayList selectAllDuePaymentsForHorse(String ownerId){
        Debug.print("HorseRegDAO.selectAllDuePaymentsForHorse() ownerId:" + ownerId);
        ArrayList horseList = new ArrayList();
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
             /*   String selectStatement = "select DISTINCT A.horse_member_id, A.competition_name, A.add_date," +
                         " C.membership_type_name, D.status_name, G.amount,G.check_amount, G.pending_amount, A.payment_id from tblHorseMemberDetails A, " +
                         " tblMemberDetails B, tblMembershipTypeMaster C, tblMembershipStatusMaster D, " +
                         " tblUserMaster F , tblUserPaymentDetails G where A.horse_member_id = B.member_id and " +
                         " B.membership_type_id = C.membership_type_id and B.status_id = D.status_id and " +
                         " A.owner_id = F.user_id and A.owner_id = ? and " +
                         " A.payment_id = G.payment_id and  (G.ssl_result is null or G.ssl_result = ?)" +
                         " and  G.pending_amount > ? order by A.add_date desc ";
              */
            
            String selectStatement = " select DISTINCT A.horse_member_id, A.competition_name, A.add_date, " +
                    " C.membership_type_name, D.status_name, G.amount,G.check_amount, G.pending_amount, " +
                    " A.payment_id from tblHorseMemberDetails A, tblMemberDetails B, tblMembershipTypeMaster C, " +
                    " tblMembershipStatusMaster D, tblUserMaster F , tblUserPaymentDetails G, " +
                    " tblHorseMemberRelationDetails H  where A.horse_member_id = B.member_id and " +
                    " B.membership_type_id = C.membership_type_id and B.status_id = D.status_id and " +
                    " A.horse_member_id = H.horse_member_id and  A.payment_id = G.payment_id and (G.ssl_result is null or G.ssl_result = ?) and " +
                    " F.user_id = H.user_id and H.user_id = ? and  G.pending_amount > ? " +
                    " order by A.add_date desc";
            
            
            prepStmt = con.prepareStatement(selectStatement);
            
            prepStmt.setString(1,"0");
            prepStmt.setString(2,ownerId);
            prepStmt.setFloat(3,0);
            ResultSet rs = prepStmt.executeQuery();
            
            while(rs.next()){
                HLCHorseRegListVO objHrListVO = new HLCHorseRegListVO();
                String horseMemberId = rs.getString(1);
                String horseName = rs.getString(2);
                Date addDate = rs.getDate(3);
                String membershipTypeName = rs.getString(4);
                String statusName = rs.getString(5);
                float amount = rs.getFloat(6);
                float checkAmount = rs.getFloat(7);
                float pendingAmount = rs.getFloat(8);
                String paymentId = rs.getString(9);
                
                objHrListVO.setHorseMemberId(horseMemberId);
                objHrListVO.setHorseName(horseName);
                objHrListVO.setAddDate(addDate);
                objHrListVO.setMembershipTypeName(membershipTypeName);
                objHrListVO.setStatusName(statusName);
                objHrListVO.setAmount(amount);
                objHrListVO.setCheckAmount(checkAmount);
                objHrListVO.setPendingAmount(pendingAmount);
                objHrListVO.setPaymentId(paymentId);
                horseList.add(objHrListVO);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.selectAllDuePaymentsForHorse():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.selectAllDuePaymentsForHorse():" + e.getMessage());
        }
        Debug.print("Size of User Horse:" + horseList.size());
        return horseList;
    }
    
    //=============================================select a particular Horse owner Details =========================================
    public ArrayList selectAllPendingPaymentsForHorseUser(String ownerId){
        Debug.print("HorseRegDAO.selectAllPendingPaymentsForHorseUser() ownerId:" + ownerId);
        ArrayList horseList = new ArrayList();
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
                 /*
                  String selectStatement = " select A.horse_member_id , A.payment_id, B.amount, B.check_amount, B.pending_amount " +
                         " from tblHorseChangeRequestDetails A, tblUserPaymentDetails B " +
                         " where A.payment_id = B.payment_id and A.requested_by = ? and B.ssl_result = ? " +
                         " order by A.req_date desc ";
                  */
            
            String selectStatement = " select A.horse_member_id , A.payment_id, B.amount, B.check_amount, B.pending_amount, " +
                    " C.competition_name, D.membership_type_name from tblHorseChangeRequestDetails A, tblUserPaymentDetails B, " +
                    " tblHorseMemberDetails C, tblMembershipTypeMaster D, tblMemberDetails E " +
                    " where A.payment_id = B.payment_id and A.horse_member_id = C.horse_member_id and " +
                    " C.horse_member_id = E.member_id and E.membership_type_id = D.membership_type_id and " +
                    " A.requested_by = ? and B.ssl_result =? order by A.req_date desc";
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,ownerId);
            prepStmt.setString(2,"1");
            ResultSet rs = prepStmt.executeQuery();
            
            while(rs.next()){
                String hrMemId = rs.getString(1);
                String paymentId = rs.getString(2);
                String amount = rs.getString(3);
                String checkAmount = rs.getString(4);
                String pendingAmount = rs.getString(5);
                String hrName = rs.getString(6);
                String memStatus = rs.getString(7);
                String[] tempPList = {hrMemId, paymentId, amount, checkAmount, pendingAmount, hrName,memStatus };
                horseList.add(tempPList);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.selectAllPendingPaymentsForHorseUser():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.selectAllPendingPaymentsForHorseUser():" + e.getMessage());
        }
        Debug.print("Size of User Horse:" + horseList.size());
        return horseList;
    }
    
    //=============================================select a particular Horse owner Details =========================================
    public ArrayList selectAllDuePaymentsForHorseUser(String ownerId){
        Debug.print("HorseRegDAO.selectAllDuePaymentsForHorseUser() ownerId:" + ownerId);
        ArrayList horseList = new ArrayList();
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            /*  String selectStatement = " select A.horse_member_id , A.payment_id, B.amount, B.check_amount, B.pending_amount " +
                         " from tblHorseChangeRequestDetails A, tblUserPaymentDetails B " +
                         " where A.payment_id = B.payment_id and A.requested_by = ? and (B.ssl_result is null or B.ssl_result = ?) and " +
                         " B.pending_amount >? order by A.req_date desc ";
             */
            String selectStatement = " select A.horse_member_id , A.payment_id, B.amount, B.check_amount, B.pending_amount, " +
                    " C.competition_name, D.membership_type_name, A.add_payment_status, A.request_id " +
                    " from tblHorseChangeRequestDetails A, tblUserPaymentDetails B, " +
                    " tblHorseMemberDetails C, tblMembershipTypeMaster D, tblMemberDetails E " +
                    " where A.payment_id = B.payment_id and A.horse_member_id = C.horse_member_id and " +
                    " C.horse_member_id = E.member_id and E.membership_type_id = D.membership_type_id and " +
                    " A.requested_by = ? and (B.ssl_result is null or B.ssl_result = ?) and" +
                    " B.pending_amount >? order by A.req_date desc";
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,ownerId);
            prepStmt.setString(2,"0");
            prepStmt.setFloat(3,0);
            ResultSet rs = prepStmt.executeQuery();
            
            while(rs.next()){
                String hrMemId = rs.getString(1);
                String paymentId = rs.getString(2);
                String amount = rs.getString(3);
                String checkAmount = rs.getString(4);
                String pendingAmount = rs.getString(5);
                String hrName = rs.getString(6);
                String memStatus = rs.getString(7);
                boolean addHorseStatus = rs.getBoolean(8);
                String requestId = rs.getString(9);
                String[] tempPList = {hrMemId, paymentId, amount, checkAmount, pendingAmount,hrName,memStatus,String.valueOf(addHorseStatus), requestId};
                horseList.add(tempPList);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.selectAllDuePaymentsForHorseUser():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.selectAllDuePaymentsForHorseUser():" + e.getMessage());
        }
        Debug.print("Size of User Horse:" + horseList.size());
        return horseList;
    }
    
    //=============================================select a particular Horse owner Details =========================================
/*    public ArrayList selectAllPendingPaymentsForHorse(String ownerId){
            Debug.print("HorseRegDAO.selectAllPendingPaymentsForHorse() ownerId:" + ownerId);
            ArrayList horseList = new ArrayList();
            PreparedStatement prepStmt = null;
            makeConnection();
            try {
                 String selectStatement = " select DISTINCT A.horse_member_id, A.competition_name, A.add_date," +
                         " C.membership_type_name, D.status_name from tblHorseMemberDetails A, " +
                         " tblMemberDetails B, tblMembershipTypeMaster C, tblMembershipStatusMaster D, " +
                         " tblUserMaster F , tblUserPaymentDetails G where A.horse_member_id = B.member_id and " +
                         " B.membership_type_id = C.membership_type_id and B.status_id = D.status_id and " +
                         " A.owner_id = F.user_id and A.owner_id = ? and " +
                         " A.payment_id = G.payment_id and  G.ssl_result =? order by A.add_date desc ";
 
                prepStmt = con.prepareStatement(selectStatement);
                prepStmt.setString(1,ownerId);
                prepStmt.setString(2,"1");
                ResultSet rs = prepStmt.executeQuery();
 
                while(rs.next()){
                    HorseRegListVO objHrListVO = new HorseRegListVO();
                    String horseMemberId = rs.getString(1);
                    String horseName = rs.getString(2);
                    Date addDate = rs.getDate(3);
                    String membershipTypeName = rs.getString(4);
                    String statusName = rs.getString(5);
 
                    objHrListVO.setHorseMemberId(horseMemberId);
                    objHrListVO.setHorseName(horseName);
                    objHrListVO.setAddDate(addDate);
                    objHrListVO.setMembershipTypeName(membershipTypeName);
                    objHrListVO.setStatusName(statusName);
                    horseList.add(objHrListVO);
                }
                rs.close();
                prepStmt.close();
                releaseConnection(con);
            }
        catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.selectAllPendingPaymentsForHorse():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.selectAllPendingPaymentsForHorse():" + e.getMessage());
        }
        Debug.print("Size of User Horse:" + horseList.size());
        return horseList;
    }
 **/
    
    public int countHorseOwner(String ownerId){
        Debug.print("HorseRegDAO.countHorseOwner() ownerId:" + ownerId);
        int rCnt = 0;
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            
            String selectStatement = " select DISTINCT A.horse_member_id, A.competition_name, A.add_date, " +
                    " C.membership_type_name, D.status_name " +
                    " from tblHorseMemberDetails A, tblMemberDetails B, tblMembershipTypeMaster C, " +
                    " tblMembershipStatusMaster D, tblHorseMemberRelationDetails E, tblUserMaster F " +
                    " where A.horse_member_id = B.member_id and B.membership_type_id = C.membership_type_id and " +
                    " A.horse_member_id = E.horse_member_id and B.status_id = D.status_id and " +
                    " E.user_id = F.user_id and F.user_id = ? order by A.add_date desc";
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,ownerId);
            ResultSet rs = prepStmt.executeQuery();
            while(rs.next()){
                rCnt++;
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.countHorseOwner():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.countHorseOwner():" + e.getMessage());
        }
        return rCnt;
    }
    
    //=============================================select a particular Horse owner Details =========================================
    public ArrayList selectHorseRequestForm(boolean status){
        Debug.print("HorseRegDAO.selectHorseRequestForm() status:" + status);
        ArrayList horseList = new ArrayList();
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String selectStatement = " select A.request_id, A.horse_member_id , " +
                    " A.req_date, B.first_name, B.last_name , A.payment_id from tblHorseChangeRequestDetails A, " +
                    " tblUserMaster B where A.requested_by = B.user_id and " +
                    " A.req_status = ? and add_payment_status = ? order by A.req_date desc";
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setBoolean(1,status);
            prepStmt.setBoolean(2,false);
            ResultSet rs = prepStmt.executeQuery();
            while(rs.next()){
                HLCRequestHorseDetVO objReqList = new HLCRequestHorseDetVO();
                String requestId = rs.getString(1);
                String horseMemberId = rs.getString(2);
                Date reqDate = rs.getDate(3);
                String firstName = rs.getString(4);
                String lastName = rs.getString(5);
                String paymentId = rs.getString(6);
                
                objReqList.setRequestId(requestId);
                objReqList.setHorseMemberId(horseMemberId);
                objReqList.setReqDate(reqDate);
                objReqList.setRequestedBy(firstName + " " +  lastName);
                objReqList.setPaymentId(paymentId);
                
                horseList.add(objReqList);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.selectHorseRequestForm():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.selectHorseRequestForm():" + e.getMessage());
        }
        return horseList;
    }
    
       //----------------------------------------------------satheesh Start MMMA_0010: Human/Non-Human relationships -----------------------------------------------------------


   public Vector displayUserTypeDetails() throws RemoteException, FinderException {
        Vector V = new Vector();
        try {
            makeConnection();
            String selectStatement = "SELECT user_type_id, user_type_name FROM  " + DBHelper.USEA_USERTYPE_MASTER +
                    " where active_status = ? and user_type_name!=?";
            prepStmt = con.prepareStatement(selectStatement);
            System.out.println(" Query is ====> " + selectStatement);
            prepStmt.setBoolean(1, true);
            prepStmt.setString(2, "Human");
            rs = prepStmt.executeQuery();
            while (rs.next()) {
                String ID = rs.getString(1);
                String name = rs.getString(2);
                String[] userType = {ID, name};
                System.out.println("username" + name);
                V.addElement(userType);
            }
            System.out.println(" After ====> " + selectStatement);
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch (Exception e) {
            releaseConnection(con);

        }
        return V;
    }



















    public ArrayList selectHorseRequestForm1(String uTypeId){
        Debug.print("HorseRegDAO.selectHorseRequestForm() status:" + uTypeId);
        ArrayList horseList = new ArrayList();
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String selectStatement = "select relationship_type_id, " + " relationship_type_name,"
                        + " active_status from " + DBHelper.USEA_HORSE_REALTION + "  where species_id= ?";


            prepStmt = con.prepareStatement(selectStatement);
            System.out.println("select"+selectStatement);
            prepStmt.setString(1,uTypeId);
            
            ResultSet rs = prepStmt.executeQuery();
            System.out.println("sssss"+rs);
            while(rs.next()){
                HLCRequestHorseDetVO objReqList = new HLCRequestHorseDetVO();
                String relationshipId = rs.getString(1);
                System.out.println(" relationshipId"+ relationshipId);
                String relationshipname = rs.getString(2);
                String status = String.valueOf(rs.getInt(3));
                

                objReqList.setRequestId(relationshipId);
                objReqList.setHorseMemberId(relationshipname);
                objReqList.setPaymentId(status);
                 System.out.println("satus"+status);
                 System.out.println("satus"+relationshipId);
                 System.out.println("satus"+relationshipname);
                horseList.add(objReqList);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.selectHorseRequestForm():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.selectHorseRequestForm():" + e.getMessage());
        }
        return horseList;
    }





    public boolean isHorseBreedExist1(String SpecieId,String relationDesc) {
        Debug.print("HorseBreedDAO.isHorseBreedExist():" + SpecieId);
        boolean result = false;
        makeConnection();
        try {
            String selectStatement = "select count(0) from " + DBHelper.USEA_HORSE_REALTION + " where species_id = ? and relationship_type_name = ?";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            System.out.println("select statement"+selectStatement);
             System.out.println("sss"+selectStatement);
            prepStmt.setString(1, SpecieId);
            System.out.println("breedSpecieId==>"+SpecieId);
            prepStmt.setString(2, relationDesc);
             System.out.println("breedDesc==>"+relationDesc);
            // prepStmt.setString(2,breedCode);
            ResultSet rs = prepStmt.executeQuery();
             System.out.println("After executing query ==>");
           // int icountRecord=0;
            if (rs.next()) {
                String strCount=rs.getString(1);
                 System.out.println("strCount ==>"+strCount);
                //icountRecord=Integer.getInteger(strCount);
                 // System.out.println("icountRecord ==>"+icountRecord);
                if(Integer.parseInt(strCount) == 0)
                    result = true;
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch (SQLException e) {
            releaseConnection(con);
            Debug.print("Could not find any from HorseBreedDAO.isHorseBreedExist" + e.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in HorseBreedDAO.isHorseBreedExist:" + e.getMessage());
        }
        Debug.print("HorseBreedDAO.isHorseBreedExist():" + result);
        return result;
    }




    public boolean insertHorseBreed1(String relationDesc, String relationStatus, String SpecieId) {
        Debug.print("HorseBreedDAO.insertHorseBreed():");
        System.out.println(" breedSpecieId"+ SpecieId);
        boolean result = false;
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            //Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 12-Mar-2011
            String insertStatement = "insert into " + DBHelper.USEA_HORSE_REALTION + " (relationship_type_name,species_id,active_status) "
                                     + " values( ?,?,?  )";

            prepStmt = con.prepareStatement(insertStatement);
             System.out.println("sss"+insertStatement);
            if (relationDesc != null && relationDesc.trim().length() != 0) {
                // prepStmt.setString(1, breedCode);
                prepStmt.setString(1, relationDesc);
                prepStmt.setString(2, SpecieId);
                prepStmt.setString(3, relationStatus);
                int cnt = prepStmt.executeUpdate();
                Debug.print("Record Inserted succefully in insertHorseBreed Details: " + cnt);
                if (cnt >= 1) {
                    result = true;
                }

                Debug.print("HorseBreedDAO insertHorseBreed Status :" + result);
            }
            prepStmt.close();
            releaseConnection(con);
        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in HorseBreedDAO.insertHorseColor():" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in HorseBreedDAO.insertHorseBreed():" + e.getMessage());
        }
        return result;
    }




 public ArrayList selectHorseRelationshipDetailsByRequestId1(String reqId) throws SQLException{
        Debug.print("HorseRegDAO.selectHorseRelationshipDetailsByRequestId()");
        ArrayList horseRelList = new ArrayList();
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
        try {
            String selectStatement = "select b.user_type_name, a.relationship_type_id, a.relationship_type_name, a.active_status, a.species_id from "+ DBHelper.USEA_HORSE_REALTION+
          " a," + DBHelper.USEA_USERTYPE_MASTER + " b where a.relationship_type_id= ? and a.species_id = b.user_type_id ";

            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,reqId);
            rs = prepStmt.executeQuery();
            while(rs.next()){
               String UserTybeName = rs.getString(1);
                System.out.println("utybename"+UserTybeName);
                //String breedCode = rs.getString(2);
                String relationId = rs.getString(2);
                String relationDesc = rs.getString(3);
                String Status = String.valueOf(rs.getInt(4));
                String SpecieId = rs.getString(5);
                String tempHorseRelation[] = {UserTybeName, relationId, relationDesc, Status, SpecieId};
                horseRelList.add(tempHorseRelation);
                System.out.println("sss"+Status);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.selectHorseRelationshipDetailsByRequestId():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.selectHorseRelationshipDetailsByRequestId():" + e.getMessage());
        }
        return horseRelList;
    }

public boolean isHorserealtionExist(String relation,String relationId,String uTypeId) {
        Debug.print("HorseBreedDAO.isHorseBreedEditExist():");
        boolean result = false;
        makeConnection();
        try {
             //Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 12-Mar-2011
            String selectStatement = "select relationship_type_name from " + DBHelper.USEA_HORSE_REALTION+ " where relationship_type_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            //Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 12-Mar-2011
            String selectStatementExist = "select count(0) from " + DBHelper.USEA_HORSE_REALTION + " where species_id = ? and relationship_type_name = ?";
            //Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 12-Mar-2011
            PreparedStatement prepStmt1 =con.prepareStatement(selectStatementExist);
            System.out.println(""+selectStatementExist);
            //Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 12-Mar-2011
            prepStmt.setString(1,relationId);
            // prepStmt.setString(2,breedCode);

            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()) {
                //result = false;
                //Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 12-Mar-2011
                String BreedDescNew = rs.getString(1);
                //Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 12-Mar-2011
                if(BreedDescNew.equals( relation))
                {
                    System.out.println("caltheif block");
               result = true;

                }
                else{
               

                 prepStmt1.setString(1,uTypeId);
                 System.out.println("uTypeId"+uTypeId);
                 prepStmt1.setString(2, relation);
                  System.out.println("breedDesc"+relation);

                ResultSet rs1 = prepStmt1.executeQuery();
                 System.out.println("After executing query ==>");
               
                System.out.println("calthe else block");
                    if (rs1.next()){
                   String strCount=rs1.getString(1);
                   System.out.println("strCount ==>"+strCount);
                   if(Integer.parseInt(strCount) == 0)
                   {
                        result=true;
                        }
                    }


            }
            }

            
            if(rs != null){
                rs.close();
                prepStmt.close();
            }
            
            if(rs1 != null){

                prepStmt1.close();
            }
            releaseConnection(con);
        } catch (SQLException e) {
            releaseConnection(con);
            Debug.print("Could not find any from HorseBreedDAO.isHorseBreedEditExist" + e.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in HorseBreedDAO.isHorseBreedEditExist:" + e.getMessage());
        }
        Debug.print("HorseBreedDAO.isHorseBreedEditExist():" + result);
        return result;
    }




 public boolean updateHorserealtion(String breedDesc, String breedStatus, String breedId) {
        Debug.print("HorseBreedDAO.updateHorseBreed():");
        boolean result = false;
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
           
            String updateStatement = "update " + DBHelper.USEA_HORSE_REALTION + " set relationship_type_name = ? ,active_status =?"+
                    " where relationship_type_id = ?";
            System.out.println("update statement is ===>"+updateStatement);
            prepStmt = con.prepareStatement(updateStatement);
            // prepStmt.setString(1, breedCode);
            prepStmt.setString(1, breedDesc);
            prepStmt.setString(2, breedStatus);
            System.out.println("activestatus"+breedStatus);
            prepStmt.setString(3, breedId);
            int cnt = prepStmt.executeUpdate();
            Debug.print("Record Updated succefully in updateHorseBreed : " + cnt);
            if (cnt >= 1) {
                result = true;
            }
            Debug.print("HorseBreedDAO updateHorseBreed :" + result);
            prepStmt.close();
            releaseConnection(con);
        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in HorseBreedDAO.updateHorseBreed():" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in HorseBreedDAO.updateHorseBreed():" + e.getMessage());
        }
        return result;
    }













public boolean deleteBreed(String chkRoleIdArr[]) {
       
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String deleteStatement = "delete from " +DBHelper.USEA_HORSE_REALTION + " where  relationship_type_id = ?";
            for(int i=0;i<chkRoleIdArr.length;i++)
            {
                prepStmt = con.prepareStatement(deleteStatement);
                //prepStmt.setString(1, roleId);
                prepStmt.setString(1, chkRoleIdArr[i]);
               //Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 12-Mar-2011
                prepStmt.executeUpdate();
                prepStmt.close();
            }
            releaseConnection(con);
        }
        catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in RoleManagementDAOImpl.deleteRole:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in RoleManagementDAOImpl.deleteRole:" + e.getMessage());
        }
        return true;
    }





   //----------------------------------------------------satheesh End MMMA_0010: Human/Non-Human relationships -----------------------------------------------------------
    //=============================================select a particular Horse owner Details =========================================
    public HLCRequestHorseDetVO selectHorseRequestFormById(String reqId){
        Debug.print("HorseRegDAO.selectHorseRequestFormById() reqId:" + reqId);
        HLCRequestHorseDetVO objReqList = new HLCRequestHorseDetVO();
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String selectStatement = " select A.request_id, A.horse_member_id , " +
                    " A.req_date, B.first_name, B.last_name, A.payment_id, A.req_owner_id, " +
                    " A.req_rider_id, A.req_horse_name, A.req_horse_reg_name, A.req_amount, A.req_status " +
                    " from tblHorseChangeRequestDetails A, " +
                    " tblUserMaster B where A.requested_by = B.user_id and " +
                    " A.request_id= ? order by A.req_date desc";
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,reqId);
            ResultSet rs = prepStmt.executeQuery();
            while(rs.next()){
                String requestId = rs.getString(1);
                String horseMemberId = rs.getString(2);
                Date reqDate = rs.getDate(3);
                String firstName = rs.getString(4);
                String lastName = rs.getString(5);
                String payementId = rs.getString(6);
                String ownerId = rs.getString(7);
                String riderId = rs.getString(8);
                String hrName = rs.getString(9);
                String hrRegName = rs.getString(10);
                float amt = rs.getFloat(11);
                boolean reqStatus = rs.getBoolean(12);
                
                objReqList.setRequestId(requestId);
                objReqList.setHorseMemberId(horseMemberId);
                objReqList.setReqDate(reqDate);
                objReqList.setRequestedBy(firstName + " " +  lastName);
                objReqList.setPaymentId(payementId);
                objReqList.setReqOwnerId(ownerId);
                objReqList.setReqRiderId(riderId);
                objReqList.setReqHorseName(hrName);
                objReqList.setReqHorseRegName(hrRegName);
                objReqList.setReqAmount(amt);
                objReqList.setReqStatus(reqStatus);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.selectHorseRequestFormById():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.selectHorseRequestFormById():" + e.getMessage());
        }
        return objReqList;
    }
    
    
    
    //=============================================select a particular Horse owner Details =========================================
    public ArrayList selectAllHorseDetailsByRiderId(String riderMemberId){
        Debug.print("HorseRegDAO.selectAllHorseDetailsByRiderId() riderId:" + riderMemberId);
        ArrayList horseList = new ArrayList();
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String selectStatement = " select A.horse_member_id, A.competition_name, A.rider_member_id, " +
                    " B.first_name, B.last_name, A.request_status, A.add_date, A.owner_id ,  A.membership_type_id, " +
                    " C.membership_type_name, A.registered_by  from tblHorseMemberDetails A," +
                    " tblUserMaster B, tblMembershipTypeMaster C, tblUserPaymentDetails D " +
                    "  where A.owner_id = B.user_id and A.payment_id = D.payment_id and " +
                    " A.membership_type_id = C.membership_type_id  and  A.rider_member_id =? ";
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,riderMemberId);
            ResultSet rs = prepStmt.executeQuery();
            while(rs.next()){
                HLCHorseRegListVO objHrListVO = new HLCHorseRegListVO();
                //String horseId = rs.getString(1);
                String horseMemberId = rs.getString(1);
                String horseName = rs.getString(2);
                String riderID = rs.getString(3);
                String firstName = rs.getString(4);
                String lastName = rs.getString(5);
                String reqStatus = rs.getString(6);
                Date addDate = rs.getDate(7);
                String ownerUserId =  rs.getString(8);
                String membershipTypeId = rs.getString(9);
                String membershipTypeName = rs.getString(10);
                String registeredBy = rs.getString(11);
                
                // objHrListVO.setHorseId(horseId);
                objHrListVO.setHorseMemberId(horseMemberId);
                objHrListVO.setHorseName(horseName);
                objHrListVO.setRiderID(riderID);
                objHrListVO.setFirstName(firstName);
                objHrListVO.setLastName(lastName);
                objHrListVO.setReqStatus(reqStatus);
                objHrListVO.setAddDate(addDate);
                objHrListVO.setOwnerId(ownerUserId);
                objHrListVO.setMembershipTypeId(membershipTypeId);
                objHrListVO.setMembershipTypeName(membershipTypeName);
                
                if(registeredBy!=null){
                    registeredBy = selectRegistrarName(registeredBy);
                }
                
                objHrListVO.setRegisteredBy(registeredBy);
                
                horseList.add(objHrListVO);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.selectAllHorseDetailsByRiderId():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.selectAllHorseDetailsByRiderId():" + e.getMessage());
        }
        return horseList;
    }
    
    
    //=============================================Select Particular horse Details =========================================
    public HLCHorseRegisterationVO selectHorseDetailsByHorseMemberId(String paramHorseMemberId) throws SQLException{
        Debug.print("HorseRegDAO.selectHorseDetailsByHorseMemberId()");
        HLCHorseRegisterationVO objHrsRegDet = new HLCHorseRegisterationVO();
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
        try {
            String selectStatement = " select A.horse_member_id, A.competition_name, A.registered_name, " +
                    " A.ba_registered_name, A.ba_past_name, A.rider_member_id, A.prev_rider_member_id, " +
                    " A.add_rider_member_id , A.owner_id, A.trainer_id, D.first_name, D.last_name, " +
                    " A.add_date, A.comments, A.payment_id, B.color, B.gender, B.height, " +
                    " B.year_foaled, B.breed, B.country, B.sire, B.sire_breed, B.dam, " +
                    " B.dam_breed, B.breed2, B.sire_breed2, B.dam_breed2, B.imported_from, B.import_date," +
                    " B.foreign_grade, B.foreign_points, B.assigned_grade, B.assigned_points, B.notes, B.spl_notes, " +
                    " A.amount, A.registered_by, E.membership_type_name, A.horse_id, C.status_name, C.status_id, " +
                    " A.activation_date , A.upgradation_date , F.membership_type_id, E.priority_value, A.membership_amount from tblHorseMemberDetails A, tblHorseDescription B, tblUserMaster D, " +
                    " tblMembershipTypeMaster E, tblMemberDetails F, tblMembershipStatusMaster C " +
                    " where A.horse_member_id = B.horse_member_id and A.horse_member_id = F.member_id and " +
                    " E.membership_type_id = F.membership_type_id and A.owner_id = D.user_id and " +
                    " F.status_id = C.status_id and A.horse_member_id = ?";
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,paramHorseMemberId);
            rs = prepStmt.executeQuery();
            if(rs.next()){
                String horseMemberId = rs.getString(1);
                String competitionName = rs.getString(2);
                String registeredName =  rs.getString(3);
                String baRegisteredName =  rs.getString(4);
                String baPastName =  rs.getString(5);
                String riderMemberId =  rs.getString(6);
                String prevRiderMemberId =  rs.getString(7);
                String addRiderMemberId =  rs.getString(8);
                String ownerId=  rs.getString(9);
                String trainerId = rs.getString(10);
                String firstName = rs.getString(11);
                String lastName = rs.getString(12);
                Date addDate =  rs.getDate(13);
                String comments =  rs.getString(14);
                String paymentId =  rs.getString(15);
                String color =  rs.getString(16);
                String gender =  rs.getString(17);
                String height =  rs.getString(18);
                String yearFoaled =  rs.getString(19);
                String breed =  rs.getString(20);
                String country =  rs.getString(21);
                String sire =  rs.getString(22);
                String sireBreed =  rs.getString(23);
                String dam =  rs.getString(24);
                String damBreed =  rs.getString(25);
                String breed2 =  rs.getString(26);
                String sireBreed2 =  rs.getString(27);
                String damBreed2 =  rs.getString(28);
                String importedFrom =  rs.getString(29);
                Date importDate =  rs.getDate(30);
                String foreignGrade =  rs.getString(31);
                float foreignPoints =  rs.getFloat(32);
                String assignedGrade =  rs.getString(33);
                float assignedPoints =  rs.getFloat(34);
                String notes =  rs.getString(35);
                String splNotes =  rs.getString(36);
                String amount =  rs.getString(37);
                String registeredBy =  rs.getString(38);
                String memberTypeName =  rs.getString(39);
                String horseTypeId =  rs.getString(40);
                String statusName = rs.getString(41);
                String statusId = rs.getString(42);
                Date actDate = rs.getDate(43);
                Date upgrDate = rs.getDate(44);
                String membershipTypId = rs.getString(45);
                String priorityVal = rs.getString(46);
                String memberAmount = rs.getString(47);
                
                objHrsRegDet.setHorseMemberId(horseMemberId);
                objHrsRegDet.setCompetitionName(competitionName);
                objHrsRegDet.setRegisteredName(registeredName);
                objHrsRegDet.setBaRegisteredName(baRegisteredName);
                objHrsRegDet.setBaPastName(baPastName);
                objHrsRegDet.setRiderMemberId(riderMemberId);
                objHrsRegDet.setPrevRiderMemberId(prevRiderMemberId);
                objHrsRegDet.setAddRiderMemberId(addRiderMemberId);
                objHrsRegDet.setOwnerId(ownerId);
                objHrsRegDet.setTrainerId(trainerId);
                objHrsRegDet.setFirstName(firstName);
                objHrsRegDet.setLastName(lastName);
                objHrsRegDet.setAddDate(addDate);
                objHrsRegDet.setPaymentId(paymentId);
                objHrsRegDet.setComments(comments);
                objHrsRegDet.setMembershipAmount(memberAmount);
                
                String colorDesc = "";
                if(color!=null && color.trim().length()!=0){
                    colorDesc = selectHorseColorById(color);
                }
                objHrsRegDet.setColorId(color);
                objHrsRegDet.setColor(colorDesc);
                objHrsRegDet.setGender(gender);
                objHrsRegDet.setHeight(height);
                objHrsRegDet.setYearFoaled(yearFoaled);
                objHrsRegDet.setBreedId(breed);
                String breedDesc = "";
                if(breed!=null && breed.trim().length()!=0){
                    breedDesc = selectHorseBreedById(breed);
                }
                
                objHrsRegDet.setBreed(breedDesc);
                
                objHrsRegDet.setCountry(country);
                objHrsRegDet.setSire(sire);
                objHrsRegDet.setSireBreedId(sireBreed);
                String sirBreedDesc = "";
                if(sireBreed!=null && sireBreed.trim().length()!=0){
                    sirBreedDesc = selectHorseBreedById(sireBreed);
                }
                
                objHrsRegDet.setSireBreed(sirBreedDesc);
                
                objHrsRegDet.setDam(dam);
                
                objHrsRegDet.setDamBreedId(damBreed);
                String damBreedDesc = "";
                if(damBreed!=null && damBreed.trim().length()!=0){
                    damBreedDesc = selectHorseBreedById(damBreed);
                }
                
                objHrsRegDet.setDamBreed(damBreedDesc);
                
                objHrsRegDet.setBreed2Id(breed2);
                String breed2Desc = "";
                
                if(breed2!=null && breed2.trim().length()!=0){
                    breed2Desc = selectHorseBreedById(breed2);
                }
                
                objHrsRegDet.setBreed2(breed2Desc);
                
                objHrsRegDet.setSireBreed2Id(sireBreed2);
                
                String sireBreed2Desc = "";
                if(sireBreed2!=null && sireBreed2.trim().length()!=0){
                    sireBreed2Desc = selectHorseBreedById(sireBreed2);
                }
                
                objHrsRegDet.setSireBreed2(sireBreed2Desc);
                
                objHrsRegDet.setDamBreed2Id(damBreed2);
                String damBreed2Desc = "";
                if(damBreed2!=null && damBreed2.trim().length()!=0){
                    damBreed2Desc = selectHorseBreedById(damBreed2);
                }
                
                objHrsRegDet.setDamBreed2(damBreed2Desc);
                
                objHrsRegDet.setImportedFrom(importedFrom);
                objHrsRegDet.setImportDate(importDate);
                objHrsRegDet.setForeignGrade(foreignGrade);
                
                objHrsRegDet.setForeignPoints(foreignPoints);
                objHrsRegDet.setAssignedGrade(assignedGrade);
                objHrsRegDet.setAssignedPoints(assignedPoints);
                objHrsRegDet.setNotes(notes);
                objHrsRegDet.setSplNotes(splNotes);
                
                objHrsRegDet.setAmount(amount);
                objHrsRegDet.setRegisterByUserId(registeredBy);
                if(registeredBy!=null){
                    registeredBy = selectRegistrarName(registeredBy);
                }
                
                objHrsRegDet.setRegisteredBy(registeredBy);
                objHrsRegDet.setMemberTypeName(memberTypeName);
                objHrsRegDet.setStatusName(statusName);
                objHrsRegDet.setStatusId(statusId);
                
                objHrsRegDet.setActivationDate(actDate);
                objHrsRegDet.setUpgradationDate(upgrDate);
                objHrsRegDet.setMembershipTypeId(membershipTypId);
                objHrsRegDet.setPriorityValue(priorityVal);
            }
            
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.selectHorseDetailsByHorseMemberId():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.selectHorseDetailsByHorseMemberId():" + e.getMessage());
        }
        return objHrsRegDet;
    }
    
    //=============================================Select Particular horse Details =========================================
    public String[] selectHorsePaymentDetails(String paymentId) throws SQLException{
        Debug.print("HorseRegDAO.selectHorsePaymentDetails()");
        String horsePayment[] = {};
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
        try {
            String selectStatement = " select  check_number, payment_status, check_name, ssl_txn_id, bank_name, " +
                    " check_date, amount, check_amount, ssl_result, payment_id, pending_amount, " +
                    " cc_name, cc_type, ssl_result_message from tblUserPaymentDetails where payment_id = ?";
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,paymentId);
            rs = prepStmt.executeQuery();
            if(rs.next()){
                String checkNumber =  rs.getString(1);
                String paymentStatus =  rs.getString(2);
                String checkName =  rs.getString(3);
                String sslTxnId =  rs.getString(4);
                String bankName =  rs.getString(5);
                String checkDate =  rs.getString(6);
                String amount =  rs.getString(7);
                String checkAmount = rs.getString(8);
                String sslResult = rs.getString(9);
                String tempPaymentId = rs.getString(10);
                String pendingAmount = rs.getString(11);
                String ccName = rs.getString(12);
                String ccType = rs.getString(13);
                String sslResultMsg = rs.getString(14);
                
                String tempHorsePayment[] = {checkNumber, paymentStatus, checkName, sslTxnId, bankName, checkDate, amount,
                checkAmount, sslResult, tempPaymentId, pendingAmount, ccName, ccType, sslResultMsg};
                horsePayment = tempHorsePayment;
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.selectHorseDetailsByHorseMemberId():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.selectHorseDetailsByHorseMemberId():" + e.getMessage());
        }
        return horsePayment;
    }
    
    public ArrayList selectHorseChildPaymentDetails(String parentPaymentId) throws SQLException{
        Debug.print("SelectHorseChildPaymentDetails(parentPaymentId).HorseRegDAO: " + parentPaymentId);
        ArrayList paymentList = new ArrayList();
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        makeConnection();
        try{
            
            String selectStatement = "select check_number, payment_status, check_name, ssl_txn_id, bank_name, " +
                    "check_date, amount, check_amount, ssl_result, payment_id, pending_amount," +
                    "cc_name, cc_type, ssl_result_message, payment_id from tblUserPaymentDetails " +
                    " where parent_payment_id = ? order by payment_date";
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,parentPaymentId);
            rs = prepStmt.executeQuery();
            while(rs.next()){
                String checkNumber =  rs.getString(1);
                String paymentStatus =  rs.getString(2);
                String checkName =  rs.getString(3);
                String sslTxnId =  rs.getString(4);
                String bankName =  rs.getString(5);
                String checkDate =  rs.getString(6);
                String amount =  rs.getString(7);
                String checkAmount = rs.getString(8);
                String sslResult = rs.getString(9);
                String tempPaymentId = rs.getString(10);
                String pendingAmount = rs.getString(11);
                String ccName = rs.getString(12);
                String ccType = rs.getString(13);
                String sslResultMsg = rs.getString(14);
                String paymentId = rs.getString(15);
                
                String tempHorseChildPayment[] = {checkNumber, paymentStatus, checkName, sslTxnId, bankName, checkDate, amount,
                checkAmount, sslResult, tempPaymentId, pendingAmount, ccName, ccType, sslResultMsg, paymentId};
                
                paymentList.add(tempHorseChildPayment);
            }
            rs.close();
            pStmt.close();
            releaseConnection(con);
        } catch(SQLException sq){
            releaseConnection(con);
            Debug.print("SQLException in selectHorseChildPaymentDetails().HorseRegDAO:" + sq.getMessage());
        } catch(Exception childPayment){
            releaseConnection(con);
            Debug.print("General in selectHorseChildPaymentDetails().HorseRegDAO: " + childPayment.getMessage());
            
        }
        return paymentList;
    }
    //=============================================Select Particular horse Details =========================================
    public String[] selectHorseAddRequestByReqId(String reqId) throws SQLException{
        Debug.print("HorseRegDAO.selectHorseAddRequestByReqId()");
        String horsePayment[] = {};
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
        try {
            String selectStatement = "select A.horse_member_id, A.payment_id, A.req_amount, A.req_date, " +
                    " B.competition_name, (C.first_name + ' ' + C.last_name) as requested_by, A.requested_by " +
                    " from tblHorseChangeRequestDetails A, tblHorseMemberDetails B, tblUserMaster C " +
                    "  where A.horse_member_id = B.horse_member_id and A.requested_by = C.user_id and " +
                    " A.request_id =?";
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,reqId);
            rs = prepStmt.executeQuery();
            if(rs.next()){
                String hrMemberId =  rs.getString(1);
                String paymentId =  rs.getString(2);
                String reqAmount =  rs.getString(3);
                String reqDate =  rs.getString(4);
                String comName =  rs.getString(5);
                String reqBy =  rs.getString(6);
                String reqById = rs.getString(7);
                String tempHorsePayment[] = {hrMemberId, paymentId, reqAmount, reqDate, comName, reqBy, reqById};
                horsePayment = tempHorsePayment;
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.selectHorseAddRequestByReqId():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.selectHorseAddRequestByReqId():" + e.getMessage());
        }
        return horsePayment;
    }
    
    //=============================================Select Particular horse Details =========================================
    public ArrayList selectHorseRelationshipDetailsDetails(String horseMemberId) throws SQLException{
        Debug.print("HorseRegDAO.selectHorseRelationshipDetailsDetails()");
        ArrayList horseRelList = new ArrayList();
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
        try {
            /*   String selectStatement = " select  check_number, payment_status, check_name, ssl_txn_id, bank_name, " +
                          " check_date, amount from tblUserPaymentDetails where payment_id = ?";
             */
            String selectStatement = " select A.first_name, A.last_name, C.relationship_type_id, D.relationship_type_name, " +
                    " C.relationship_status, C.relation_id, B.horse_member_id from tblUserMaster A, " +
                    " tblHorseMemberDetails B, tblHorseMemberRelationDetails C, tblHorseRelationshipTypeMaster D " +
                    " where A.user_id = C.user_id and C.horse_member_id = B.horse_member_id and " +
                    " C.relationship_type_id = D.relationship_type_id and B.horse_member_id = ? order by D.relationship_type_name";
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,horseMemberId);
            rs = prepStmt.executeQuery();
            while(rs.next()){
                String firstName =  rs.getString(1);
                String last_name =  rs.getString(2);
                String relationship_type_id =  rs.getString(3);
                String relationship_type_name =  rs.getString(4);
                String relationship_status =  rs.getString(5);
                String relation_id =  rs.getString(6);
                String memId =  rs.getString(7);
                String tempHorseRelation[] = {firstName, last_name, relationship_type_id, relationship_type_name, relationship_status, relation_id, memId};
                horseRelList.add(tempHorseRelation);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.selectHorseRelationshipDetailsDetails():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.selectHorseRelationshipDetailsDetails():" + e.getMessage());
        }
        return horseRelList;
    }
    
    //=============================================Select Particular horse Details =========================================
    public ArrayList selectHorseRelationshipDetailsByRequestId(String requestId) throws SQLException{
        Debug.print("HorseRegDAO.selectHorseRelationshipDetailsByRequestId()");
        ArrayList horseRelList = new ArrayList();
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
        try {
            String selectStatement = " select A.first_name, A.last_name, C.relationship_type_id, D.relationship_type_name, " +
                    " C.relationship_status, C.relation_id, B.horse_member_id from tblUserMaster A, " +
                    " tblHorseMemberDetails B, tblHorseMemberRelationDetails C, tblHorseRelationshipTypeMaster D " +
                    " where A.user_id = C.user_id and C.horse_member_id = B.horse_member_id and " +
                    " C.relationship_type_id = D.relationship_type_id and C.request_id = ? order by D.relationship_type_name";
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,requestId);
            rs = prepStmt.executeQuery();
            while(rs.next()){
                String firstName =  rs.getString(1);
                String last_name =  rs.getString(2);
                String relationship_type_id =  rs.getString(3);
                String relationship_type_name =  rs.getString(4);
                String relationship_status =  rs.getString(5);
                String relation_id =  rs.getString(6);
                String memId =  rs.getString(7);
                String tempHorseRelation[] = {firstName, last_name, relationship_type_id, relationship_type_name, relationship_status, relation_id, memId};
                horseRelList.add(tempHorseRelation);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.selectHorseRelationshipDetailsByRequestId():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.selectHorseRelationshipDetailsByRequestId():" + e.getMessage());
        }
        return horseRelList;
    }
    
    //=============================================Select Particular horse Details =========================================
    public String[] selectHorseRelationshipDetailsByRelationId(String relationId) throws SQLException{
        Debug.print("HorseRegDAO.selectHorseRelationshipDetailsByRelationId()");
        String horseRelList[] = {};
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
        try {
            String selectStatement = " select A.first_name, A.last_name, C.relationship_type_id, D.relationship_type_name, " +
                    " C.relationship_status, C.relation_id, B.horse_member_id, B.competition_name, C.request_id from tblUserMaster A, " +
                    " tblHorseMemberDetails B, tblHorseMemberRelationDetails C, tblHorseRelationshipTypeMaster D " +
                    " where A.user_id = C.user_id and C.horse_member_id = B.horse_member_id and " +
                    " C.relationship_type_id = D.relationship_type_id and C.relation_id = ? order by D.relationship_type_name";
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,relationId);
            rs = prepStmt.executeQuery();
            if(rs.next()){
                String firstName =  rs.getString(1);
                String last_name =  rs.getString(2);
                String relationship_type_id =  rs.getString(3);
                String relationship_type_name =  rs.getString(4);
                String relationship_status =  rs.getString(5);
                String relation_id =  rs.getString(6);
                String memId =  rs.getString(7);
                String hrname =  rs.getString(8);
                String reqId = rs.getString(9);
                
                String tempHorseRelation[] = {firstName, last_name, relationship_type_id, relationship_type_name, relationship_status, relation_id, memId, hrname, reqId};
                horseRelList = tempHorseRelation;
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.selectHorseRelationshipDetailsDetails():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.selectHorseRelationshipDetailsDetails():" + e.getMessage());
        }
        return horseRelList;
    }
    
    //=============================================Update Owner Details =========================================
    public boolean updateOwnerRiderActivation(String relationId, String relationshipTypeId , String relationshipStatus) {
        Debug.print("HorseRegDAO.updateOwnerRiderActivation() relationId:" + relationId);
        Debug.print("HorseRegDAO.updateOwnerRiderActivation() relationshipStatus:" + relationshipStatus);
        boolean result = false;
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String updateStatement = "update  tblHorseMemberRelationDetails "+
                    " set relationship_status = ?, relationship_type_id = ?  where relation_id = ?";
            
            prepStmt = con.prepareStatement(updateStatement);
            prepStmt.setString(1, relationshipStatus);
            prepStmt.setString(2, relationshipTypeId);
            prepStmt.setString(3, relationId);
            
            int cnt = prepStmt.executeUpdate();
            Debug.print("Record Updated succefully in updateOwnerRiderActivation : " + cnt);
            if(cnt>=1){
                result = true;
            }
            
            Debug.print("HorseRegDAO updateOwnerRiderActivation Status :" + result);
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.updateOwnerRiderActivation():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.updateOwnerRiderActivation():" + e.getMessage());
        }
        return result;
    }
    
    //=============================================Update Owner Details =========================================
    public boolean updateBalanaceAmount(String paymentId, float balAmount) {
        Debug.print("HorseRegDAO.updateBalanaceAmount() paymentId:" + paymentId);
        boolean result = false;
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String updateStatement = "update  tblUserPaymentDetails set " +
                    " pending_amount = ? where  payment_id = ?";
            
            prepStmt = con.prepareStatement(updateStatement);
            prepStmt.setFloat(1, balAmount);
            prepStmt.setString(2, paymentId);
            
            int cnt = prepStmt.executeUpdate();
            Debug.print("Record Updated succefully updateBalanaceAmount : " + cnt);
            if(cnt>=1){
                result = true;
            }
            
            
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.updateBalanaceAmount():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.updateBalanaceAmount():" + e.getMessage());
        }
        Debug.print("HorseRegDAO updateBalanaceAmount Status :" + result);
        return result;
    }
    
    //=============================================Update Owner Details =========================================
    public boolean updatePendingAmount(String userId, String paymentId, float checkAmount) {
        Debug.print("HorseRegDAO.updatePendingAmount() paymentId:" + paymentId);
        boolean result = false;
        PreparedStatement prepStmt = null;
        float chAmt = 0.0f, totalAmount = 0.0f,finalChkAmt=0.0f;
        Debug.print("checkAmount:" + checkAmount);
        
        Debug.print("paymentId:" + paymentId);
        
        try{
            chAmt = getCheckAmount(paymentId);
            Debug.print("chAmt:" + chAmt);
        } catch(Exception e){
            Debug.print("Exception while calling getCheckAmount in updatePendingAmount:" + e.getMessage());
        }
        
        try{
            totalAmount = getTotalAmount(paymentId);
            Debug.print("totalAmount:" + totalAmount);
        } catch(Exception e){
            Debug.print("Exception while calling getCheckAmount in updatePendingAmount:" + e.getMessage());
        }
        if(checkAmount!=0){
            checkAmount = checkAmount + chAmt;
        }
        //createMembershipRefundDetails
        Debug.print("After calculating checkAmount:" + checkAmount);
        float pendingAmount = totalAmount - checkAmount;
        
        Debug.print(" pendingAmount after calculation in bean :"+pendingAmount);
        
        if(pendingAmount<0){
            float rFundAmt = -1;
            boolean reFund = insertMembershipRefundDetails(userId,"Over Paid Check Amount for Membership", (pendingAmount*rFundAmt));
            pendingAmount = 0;
        }
        
        Debug.print("After calculating pendingAmount:" + pendingAmount);
        makeConnection();
        try {
            String updateStatement = "update  tblUserPaymentDetails set " +
                    " check_amount = ?, pending_amount = ? where  payment_id = ?";
            
            prepStmt = con.prepareStatement(updateStatement);
            prepStmt.setFloat(1, checkAmount);
            prepStmt.setFloat(2, pendingAmount);
            prepStmt.setString(3, paymentId);
            
            int cnt = prepStmt.executeUpdate();
            Debug.print("Record Updated succefully updatePendingAmount : " + cnt);
            if(cnt>=1){
                result = true;
            }
            
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.updatePendingAmount():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.updatePendingAmount():" + e.getMessage());
        }
        Debug.print("HorseRegDAO updatePendingAmount Status :" + result);
        return result;
    }
    
    //=============================================Update Relationship Status =========================================
    public boolean updateRealtionshipStatus(String horseMemberId) {
        Debug.print("HorseRegDAO.updateRealtionshipStatus() horseMemberId:" + horseMemberId);
        boolean result = false;
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String updateStatement = "update  tblHorseMemberRelationDetails "+
                    " set relationship_status = ?  where horse_member_id = ?";
            
            prepStmt = con.prepareStatement(updateStatement);
            prepStmt.setString(1, "Active");
            prepStmt.setString(2, horseMemberId);
            
            int cnt = prepStmt.executeUpdate();
            Debug.print("Record Updated succefully in updateRealtionshipStatus : " + cnt);
            if(cnt>=1){
                result = true;
            }
            
            Debug.print("HorseRegDAO updateRealtionshipStatus Status :" + result);
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.updateRealtionshipStatus():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.updateRealtionshipStatus():" + e.getMessage());
        }
        return result;
    }
    
    //============================================= Update Owner Details =========================================
    public boolean updateRequestStatus(String requestId) {
        Debug.print("HorseRegDAO.updateRequestStatus() relationId:" + requestId);
        boolean result = false;
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String updateStatement = "update  tblHorseChangeRequestDetails "+
                    " set req_status = ?  where request_id = ?";
            
            prepStmt = con.prepareStatement(updateStatement);
            prepStmt.setBoolean(1, true);
            prepStmt.setString(2, requestId);
            
            int cnt = prepStmt.executeUpdate();
            
            Debug.print("Record Updated succefully in updateOwnerRiderActivation : " + cnt);
            if(cnt>=1){
                result = true;
            }
            
            Debug.print("HorseRegDAO updateRequestStatus Status :" + result);
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.updateRequestStatus():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.updateOwnerRiderActivation():" + e.getMessage());
        }
        return result;
    }
    
    //============================================= Update Owner Details =========================================
    public boolean updateRequestStatusForAdmin(String requestId) {
        Debug.print("HorseRegDAO.updateRequestStatus() relationId:" + requestId);
        boolean result = false;
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String updateStatement = "update  tblHorseMemberRelationDetails "+
                    " set active_status = ? , relationship_status = ? where request_id = ?";
            
            prepStmt = con.prepareStatement(updateStatement);
            prepStmt.setBoolean(1, true);
            prepStmt.setString(2, "Active");
            prepStmt.setString(3, requestId);
            
            int cnt = prepStmt.executeUpdate();
            
            Debug.print("Record Updated succefully in updateRequestStatusForAdmin : " + cnt);
            if(cnt>=1){
                result = true;
            }
            
            Debug.print("HorseRegDAO updateRequestStatus Status :" + result);
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.updateRequestStatusForAdmin():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.updateRequestStatusForAdmin():" + e.getMessage());
        }
        return result;
    }
    
    //=============================================Update Owner Details =========================================
    public boolean updateOwnerwithRelationship(String relationId, String relationshipTypeId , String userId)  {
        Debug.print("HorseRegDAO.updateOwnerwithRelationship() relationId:" + relationId);
        Debug.print("HorseRegDAO.updateOwnerwithRelationship() relationshipTypeId:" + relationshipTypeId);
        boolean result = false;
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String updateStatement = "update  tblHorseMemberRelationDetails "+
                    " set user_id = ?, relationship_type_id = ?  where relation_id = ?";
            
            prepStmt = con.prepareStatement(updateStatement);
            prepStmt.setString(1, userId);
            prepStmt.setString(2, relationshipTypeId);
            prepStmt.setString(3, relationId);
            
            int cnt = prepStmt.executeUpdate();
            Debug.print("Record Updated succefully in updateOwnerwithRelationship : " + cnt);
            if(cnt>=1){
                result = true;
            }
            
            Debug.print("HorseRegDAO updateOwnerwithRelationship Status :" + result);
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.updateOwnerwithRelationship():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.updateOwnerwithRelationship():" + e.getMessage());
        }
        return result;
    }
    
    //=============================================Update Owner Details =========================================
    public boolean updateRelationDateInfo(String relationId, String horsememberId, String userId)  {
        Debug.print("HorseRegDAO.updateRelationDateInfo() relationId:" + relationId);
        Debug.print("HorseRegDAO.updateRelationDateInfo() userId:" + userId);
        boolean result = false;
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String updateStatement = "update  tblHorseMemberRelationDetails "+
                    " set modify_date = ?, modified_by = ? where relation_id = ? and horse_member_id = ?";
            
            prepStmt = con.prepareStatement(updateStatement);
            prepStmt.setDate(1, DBHelper.toSQLDate(new Date()));
            prepStmt.setString(2, userId);
            prepStmt.setString(3, relationId);
            prepStmt.setString(4, horsememberId);
            
            int cnt = prepStmt.executeUpdate();
            Debug.print("Record Updated succefully in updateRelationDateInfo : " + cnt);
            if(cnt>=1){
                result = true;
            }
            
            Debug.print("HorseRegDAO updateRelationDateInfo Status :" + result);
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.updateRelationDateInfo():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.updateOwnerwithRelationship():" + e.getMessage());
        }
        return result;
    }
    
    //=============================================Update Owner Details =========================================
    public boolean updateTrainerRelationDateInfo(String relationId, String userId ,String horsememberId)  {
        Debug.print("HorseRegDAO.updateTrainerRelationDateInfo() relationId:" + relationId);
        boolean result = false;
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String updateStatement = "update  tblHorseMemberRelationDetails "+
                    " set modify_date = ?, modified_by = ? where relation_id = ? and horse_member_id = ?";
            
            prepStmt = con.prepareStatement(updateStatement);
            prepStmt.setDate(1, DBHelper.toSQLDate(new Date()));
            prepStmt.setString(2, userId);
            prepStmt.setString(3, relationId);
            prepStmt.setString(4, horsememberId);
            
            int cnt = prepStmt.executeUpdate();
            Debug.print("Record Updated succefully in updateTrainerRelationDateInfo : " + cnt);
            if(cnt>=1){
                result = true;
            }
            
            Debug.print("HorseRegDAO updateTrainerRelationDateInfo Status :" + result);
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.updateTrainerRelationDateInfo():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.updateTrainerRelationDateInfo():" + e.getMessage());
        }
        return result;
    }
    
    //=============================================Update Owner Details =========================================
    public boolean degradeOwnerRider(String relationId, String relationType)  {
        Debug.print("HorseRegDAO.degradeOwnerRider() relationId:" + relationId);
        boolean result = false;
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String updateStatement = "update  tblHorseMemberRelationDetails "+
                    " set relationship_type_id = ?  where relation_id = ?";
            
            prepStmt = con.prepareStatement(updateStatement);
            prepStmt.setString(1, relationType);
            prepStmt.setString(2, relationId);
            
            int cnt = prepStmt.executeUpdate();
            Debug.print("Record Updated succefully in degradeOwnerRider : " + cnt);
            if(cnt>=1){
                result = true;
            }
            
            Debug.print("HorseRegDAO degradeOwnerRider Status :" + result);
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.degradeOwnerRider():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.degradeOwnerRider():" + e.getMessage());
        }
        return result;
    }
    //=============================================Update Owner Details =========================================
    public boolean updateRiderDetails(String horseMemberId, String relTypId, String userId, String relationshipTypeId)  {
        Debug.print("HorseRegDAO.updateRiderDetails() horseMemberId:" + horseMemberId);
        boolean result = false;
        PreparedStatement prepStmt = null;
        
        try {
            makeConnection();
            String updateStatement = "update  tblHorseMemberRelationDetails "+
                    " set relationship_type_id = ?  where horse_member_id= ? and relationship_type_id= ?  and user_id= ? ";
            
            prepStmt = con.prepareStatement(updateStatement);
            prepStmt.setString(1, relationshipTypeId);
            prepStmt.setString(2, horseMemberId);
            prepStmt.setString(3, relTypId);
            prepStmt.setString(4, userId);
            
            int cnt = prepStmt.executeUpdate();
            Debug.print("Record Updated succefully in updateRiderDetails : " + cnt);
            if(cnt>=1){
                result = true;
            }
            
            Debug.print("HorseRegDAO updateRiderDetails Status :" + result);
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.updateRiderDetails():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.updateRiderDetails():" + e.getMessage());
        } finally{
            releaseConnection(con);
        }
        return result;
    }
    
//=============================================Update Request Status for Annual Details =========================================
    public boolean updateRequestStatus(String horseMemberId, String requestStatus, String  comments, Date actDate) {
        Debug.print("HorseRegDAO.updateRequestStatus():" + actDate);
        if(requestStatus!=null && requestStatus.trim().length()!=0){
            boolean res = updateStatusId(horseMemberId, requestStatus, actDate);
        }
        boolean result = false;
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String updateStatement = "update  tblHorseMemberDetails "+
                    " set comments = ?, activation_date= ?  where horse_member_id = ?";
            
            prepStmt = con.prepareStatement(updateStatement);
            
            prepStmt.setString(1, comments);
            prepStmt.setDate(2, DBHelper.toSQLDate(actDate));
            prepStmt.setString(3, horseMemberId);
            
            int cnt = prepStmt.executeUpdate();
            Debug.print("Record Updated succefully in updateRequestStatus : " + cnt);
            result = true;
            Debug.print("HorseRegDAO updateRequestStatus Status :" + result);
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.updateRequestStatus():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.updateRequestStatus():" + e.getMessage());
        }
        return result;
    }
    
    //=============================================Update Request Status for Annual Details =========================================
    public boolean updateStatusId(String horseMemberId, String requestStatus, Date actDate) {
        Debug.print("HorseRegDAO.updateStatusId():" + actDate);
        Debug.print("HorseRegDAO.updateStatusId():" + requestStatus);
        boolean result = false;
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String updateStatement = "update  tblMemberDetails "+
                    " set status_id = ?, activation_date = ? where member_id = ?";
            
            prepStmt = con.prepareStatement(updateStatement);
            
            prepStmt.setString(1, requestStatus);
            if(actDate==null){
                prepStmt.setDate(2, null);
            } else{
                prepStmt.setDate(2, DBHelper.toSQLDate(actDate));
            }
            prepStmt.setString(3, horseMemberId);
            
            int cnt = prepStmt.executeUpdate();
            Debug.print("Record Updated succefully in updateStatusId : " + cnt);
            result = true;
            Debug.print("HorseRegDAO updateStatusId Status :" + result);
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.updateStatusId():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.updateStatusId():" + e.getMessage());
        }
        return result;
    }
    
    //=============================================Update Request Status for Annual Details =========================================
    public String getStatusName(String statusName) {
        Debug.print("HorseRegDAO.getStatusName():");
        String result = "";
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String updateStatement = "select status_id from  tblMembershipStatusMaster "+
                    " where status_name = ?";
            
            prepStmt = con.prepareStatement(updateStatement);
            prepStmt.setString(1, statusName);
            ResultSet rs = prepStmt.executeQuery();
            if(rs.next()){
                result = rs.getString(1);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.getStatusName():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.getStatusName():" + e.getMessage());
        }
        return result;
    }
    
    
    //=============================================Update Request Status for Annual Details =========================================
    public float getCheckAmount(String paymentId) {
        Debug.print("HorseRegDAO.getCheckAmount() paymentId:" + paymentId);
        float result = 0.0f;
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String updateStatement = "select check_amount from  tblUserPaymentDetails"+
                    " where payment_id = ?";
            
            prepStmt = con.prepareStatement(updateStatement);
            prepStmt.setString(1, paymentId);
            ResultSet rs = prepStmt.executeQuery();
            if(rs.next()){
                result = rs.getFloat(1);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.getCheckAmount():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.getCheckAmount():" + e.getMessage());
        }
        return result;
    }
    
    //=============================================Update Request Status for Annual Details =========================================
    public float getTotalAmount(String paymentId) {
        Debug.print("HorseRegDAO.getTotalAmount() paymentId:" + paymentId);
        float result = 0.0f;
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String updateStatement = "select amount from  tblUserPaymentDetails "+
                    " where payment_id = ?";
            
            prepStmt = con.prepareStatement(updateStatement);
            prepStmt.setString(1, paymentId);
            ResultSet rs = prepStmt.executeQuery();
            if(rs.next()){
                result = rs.getFloat(1);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.getTotalAmount():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.getTotalAmount():" + e.getMessage());
        }
        return result;
    }
    
    
    
    
    //=============================================Update Owner Details =========================================
    public boolean updateOwnerDetails(String horseMemberId, String ownerId) {
        Debug.print("HorseRegDAO.updateOwnerDetails() horseMemberId:" + horseMemberId);
        Debug.print("HorseRegDAO.updateOwnerDetails() ownerId:" + ownerId);
        boolean result = false;
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String updateStatement = "update  tblHorseMemberDetails "+
                    " set owner_id = ?  where horse_member_id = ?";
            
            prepStmt = con.prepareStatement(updateStatement);
            
            prepStmt.setString(1, ownerId);
            prepStmt.setString(2, horseMemberId);
            
            int cnt = prepStmt.executeUpdate();
            Debug.print("Record Updated succefully in updateOwnerDetails : " + cnt);
            if(cnt>=1){
                result = true;
            }
            
            Debug.print("HorseRegDAO updateOwnerDetails Status :" + result);
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.updateOwnerDetails():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.updateOwnerDetails():" + e.getMessage());
        }
        return result;
    }
    
    //=============================================Update Owner Details =========================================
    public boolean updateTrainerDetails(String horseMemberId, String ownerId) {
        Debug.print("HorseRegDAO.updateTrainerDetails() horseMemberId:" + horseMemberId);
        Debug.print("HorseRegDAO.updateTrainerDetails() ownerId:" + ownerId);
        boolean result = false;
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String updateStatement = "update  tblHorseMemberDetails "+
                    " set trainer_id = ?  where horse_member_id = ?";
            
            prepStmt = con.prepareStatement(updateStatement);
            
            prepStmt.setString(1, ownerId);
            prepStmt.setString(2, horseMemberId);
            
            int cnt = prepStmt.executeUpdate();
            Debug.print("Record Updated succefully in updateTrainerDetails : " + cnt);
            if(cnt>=1){
                result = true;
            }
            
            Debug.print("HorseRegDAO updateTrainerDetails Status :" + result);
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.updateTrainerDetails():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.updateTrainerDetails():" + e.getMessage());
        }
        return result;
    }
    
    //=============================================Update Request Status for Annual Details =========================================
    public boolean updateDescriptionDetails(String horseMemberId, HLCHorseDescriptionVO objHrDescVO) {
        Debug.print("HorseRegDAO.updateDescriptionDetails():");
        boolean result = false;
        PreparedStatement prepStmt = null;
        
        try {
            makeConnection();
            String updateStatement = "update " + DBHelper.USEA_HORSE_DESCRIPTION  +
                    " set color=?, gender = ?, height = ?, year_foaled = ?, " +
                    " breed = ?, country = ?, sire= ?, sire_breed = ?, dam = ?, dam_breed = ?, breed2 = ?," +
                    " sire_breed2 = ?, dam_breed2 = ?, imported_from = ?, import_date = ? " +
                    " where horse_member_id=? ";
            
            if(horseMemberId!=null && horseMemberId.trim().length()!=0 ){
                prepStmt = con.prepareStatement(updateStatement);
                prepStmt.setString(1, objHrDescVO.getColor());
                prepStmt.setString(2, objHrDescVO.getGender());
                prepStmt.setString(3, objHrDescVO.getHeight());
                prepStmt.setString(4, objHrDescVO.getYearFoaled());
                prepStmt.setString(5, objHrDescVO.getBreed());
                prepStmt.setString(6, objHrDescVO.getCountry());
                prepStmt.setString(7, objHrDescVO.getSire());
                prepStmt.setString(8, objHrDescVO.getSireBreed());
                prepStmt.setString(9, objHrDescVO.getDam());
                prepStmt.setString(10, objHrDescVO.getDamBreed());
                prepStmt.setString(11, objHrDescVO.getBreed2());
                prepStmt.setString(12, objHrDescVO.getSireBreed2());
                prepStmt.setString(13, objHrDescVO.getDamBreed2());
                prepStmt.setString(14, objHrDescVO.getImportedFrom());
                if(objHrDescVO.getImportDate()!=null){
                    prepStmt.setDate(15, DBHelper.toSQLDate(objHrDescVO.getImportDate()));
                } else{
                    prepStmt.setDate(15, null);
                }
                prepStmt.setString(16, horseMemberId);
                
                //prepStmt.setString(21, objHrDescVO.getNotes());
                //prepStmt.setString(22, objHrDescVO.getSplNotes());
                
                int cnt = prepStmt.executeUpdate();
                prepStmt.close();
                Debug.print("Record Inserted succefully in updateDescriptionDetails Details: " + cnt);
                if(cnt>=1){
                    result = true;
                }
                
                Debug.print("HorseRegDAO updateDescriptionDetails Status :" + result);
            }
            // prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.updateDescriptionDetails():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.updateDescriptionDetails():" + e.getMessage());
        } finally{
            releaseConnection(con);
        }
        return result;
    }
    
    //=============================================Update Request Status for Annual Details =========================================
    public boolean updateDescriptionDetailsByAdmin(String horseMemberId, HLCHorseDescriptionVO objHrDescVO) {
        Debug.print("HorseRegDAO.updateDescriptionDetails():");
        boolean result = false;
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String updateStatement = "update " + DBHelper.USEA_HORSE_DESCRIPTION  +
                    " set color=?, gender = ?, height = ?, year_foaled = ?, " +
                    " breed = ?, country = ?, sire= ?, sire_breed = ?, dam = ?, dam_breed = ?, breed2 = ?," +
                    " sire_breed2 = ?, dam_breed2 = ?, imported_from = ?, import_date = ?, " +
                    " foreign_grade = ?, foreign_points = ?, assigned_grade = ?, assigned_points = ?" +
                    " where horse_member_id=? ";
            
            if(horseMemberId!=null && horseMemberId.trim().length()!=0 ){
                prepStmt = con.prepareStatement(updateStatement);
                prepStmt.setString(1, objHrDescVO.getColor());
                prepStmt.setString(2, objHrDescVO.getGender());
                prepStmt.setString(3, objHrDescVO.getHeight());
                prepStmt.setString(4, objHrDescVO.getYearFoaled());
                prepStmt.setString(5, objHrDescVO.getBreed());
                prepStmt.setString(6, objHrDescVO.getCountry());
                prepStmt.setString(7, objHrDescVO.getSire());
                prepStmt.setString(8, objHrDescVO.getSireBreed());
                prepStmt.setString(9, objHrDescVO.getDam());
                prepStmt.setString(10, objHrDescVO.getDamBreed());
                prepStmt.setString(11, objHrDescVO.getBreed2());
                prepStmt.setString(12, objHrDescVO.getSireBreed2());
                prepStmt.setString(13, objHrDescVO.getDamBreed2());
                prepStmt.setString(14, objHrDescVO.getImportedFrom());
                if(objHrDescVO.getImportDate()!=null){
                    prepStmt.setDate(15, DBHelper.toSQLDate(objHrDescVO.getImportDate()));
                } else{
                    prepStmt.setDate(15, null);
                }
                prepStmt.setString(16, objHrDescVO.getForeignGrade());
                prepStmt.setFloat(17, objHrDescVO.getForeignPoints());
                prepStmt.setString(18, objHrDescVO.getAssignedGrade());
                prepStmt.setFloat(19, objHrDescVO.getAssignedPoints());
                prepStmt.setString(20, horseMemberId);
                
                //prepStmt.setString(21, objHrDescVO.getNotes());
                //prepStmt.setString(22, objHrDescVO.getSplNotes());
                
                int cnt = prepStmt.executeUpdate();
                prepStmt.close();
                Debug.print("Record Inserted succefully in updateDescriptionDetails Details: " + cnt);
                if(cnt>=1){
                    result = true;
                }
                
                Debug.print("HorseRegDAO updateDescriptionDetails Status :" + result);
            }
            // prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.updateDescriptionDetails():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.updateDescriptionDetails():" + e.getMessage());
        }
        return result;
    }
    
    //=============================================Update Request Status for Annual Details =========================================
    public boolean updateHorseDetails(String horseMemberId, String riderId, String prevRiderId, String  addRiderId ) {
        Debug.print("HorseRegDAO.updateHorseDetails():");
        boolean result = false;
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String updateStatement = "update " + DBHelper.USEA_HORSE_MEMBERDETAILS  +
                    " set rider_member_id=?, prev_rider_member_id = ?, add_rider_member_id = ? " +
                    " where horse_member_id = ? ";
            
            if(horseMemberId!=null && horseMemberId.trim().length()!=0 ){
                prepStmt = con.prepareStatement(updateStatement);
                prepStmt.setString(1, riderId);
                prepStmt.setString(2, prevRiderId);
                prepStmt.setString(3, addRiderId);
                prepStmt.setString(4, horseMemberId);
                int cnt = prepStmt.executeUpdate();
                prepStmt.close();
                Debug.print("Record Inserted succefully in updateHorseDetails Details: " + cnt);
                if(cnt>=1){
                    result = true;
                }
                Debug.print("HorseRegDAO updateHorseDetails Status :" + result);
            }
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.updateHorseDetails():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.updateHorseDetails():" + e.getMessage());
        }
        return result;
    }
    
    //=============================================Update Request Status for Horse Details =========================================
    public boolean updateHorseDetailsByAdmin(String horseMemberId, String competitionName, String registereName,
            String baRegisteredName, String baPastName, String riderId, String prevRiderId, String  addRiderId ) {
        Debug.print("HorseRegDAO.updateHorseDetailsByAdmin():");
        boolean result = false;
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String updateStatement = "update " + DBHelper.USEA_HORSE_MEMBERDETAILS  +
                    " set competition_name = ? , registered_name=?, ba_registered_name=?, ba_past_name=?," +
                    " rider_member_id=?, prev_rider_member_id = ?, add_rider_member_id = ? " +
                    " where horse_member_id = ? ";
            
            if(horseMemberId!=null && horseMemberId.trim().length()!=0 ){
                prepStmt = con.prepareStatement(updateStatement);
                prepStmt.setString(1, competitionName);
                prepStmt.setString(2, registereName);
                prepStmt.setString(3, baRegisteredName);
                prepStmt.setString(4, baPastName);
                prepStmt.setString(5, riderId);
                prepStmt.setString(6, prevRiderId);
                prepStmt.setString(7, addRiderId);
                prepStmt.setString(8, horseMemberId);
                
                int cnt = prepStmt.executeUpdate();
                prepStmt.close();
                Debug.print("Record Inserted succefully in updateHorseDetailsByAdmin Details: " + cnt);
                if(cnt>=1){
                    result = true;
                }
                Debug.print("HorseRegDAO updateHorseDetailsByAdmin Status :" + result);
            }
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.updateHorseDetailsByAdmin():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.updateHorseDetailsByAdmin():" + e.getMessage());
        } finally{
            releaseConnection(con);
        }
        return result;
    }
    
    //=============================================Update Request Status for Horse Details =========================================
    public boolean updateRequetsHorseDetailsByAdmin(String horseMemberId, String competitionName, String registereName) {
        Debug.print("HorseRegDAO.updateHorseDetailsByAdmin(): horseMemberId" + horseMemberId);
        Debug.print("HorseRegDAO.updateHorseDetailsByAdmin(): competitionName" + competitionName);
        Debug.print("HorseRegDAO.updateHorseDetailsByAdmin(): registereName" + registereName);
                //Debug.print("HorseRegDAO.updateHorseDetailsByAdmin(): oldCompetitionName" + oldCompetitionName);
        boolean result = false;
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            boolean chkList = false;
            String updateStatement = "update " + DBHelper.USEA_HORSE_MEMBERDETAILS  + " set ";
            
            if(competitionName!=null && competitionName.trim().length()!=0){
                competitionName = competitionName.replaceAll("'","''");
                updateStatement = updateStatement + " competition_name = '" +  competitionName + "'" ;
                chkList = true;
            }
            
            if(registereName!=null && registereName.trim().length()!=0){
                registereName = registereName.replaceAll("'","''");
                if(chkList==true) updateStatement = updateStatement + ", ";
                updateStatement = updateStatement + " registered_name = '" +  registereName + "'" ;
            }
            
            // if(oldCompetitionName!=null && oldCompetitionName.trim().length()!=0){
                //oldCompetitionName = oldCompetitionName.replaceAll("'","''");
               // if(chkList==true) updateStatement = updateStatement + ", ";
               // updateStatement = updateStatement + " ba_past_name = '" +  oldCompetitionName + "'" ;
            //}
            updateStatement = updateStatement + " where horse_member_id = '" + horseMemberId.trim() + "'";
            
            Debug.print("updateStatement:" + updateStatement);
            
            prepStmt = con.prepareStatement(updateStatement);
            // prepStmt.setString(1, horseMemberId);
            
            int cnt = prepStmt.executeUpdate();
            prepStmt.close();
            Debug.print("Record Inserted succefully in updateHorseDetailsByAdmin Details: " + cnt);
            if(cnt>=1){
                result = true;
            }
            Debug.print("HorseRegDAO updateHorseDetailsByAdmin Status :" + result);
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.updateHorseDetailsByAdmin():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.updateHorseDetailsByAdmin():" + e.getMessage());
        }
        return result;
    }
    
    public boolean updateRequetsHorseDetailsByAdmin(String horseMemberId, String competitionName, String registereName, String oldCompetitionName) {
        Debug.print("HorseRegDAO.updateHorseDetailsByAdmin(): horseMemberId" + horseMemberId);
        Debug.print("HorseRegDAO.updateHorseDetailsByAdmin(): competitionName" + competitionName);
        Debug.print("HorseRegDAO.updateHorseDetailsByAdmin(): registereName" + registereName);
                Debug.print("HorseRegDAO.updateHorseDetailsByAdmin(): oldCompetitionName" + oldCompetitionName);
        boolean result = false;
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            boolean chkList = false;
            String updateStatement = "update " + DBHelper.USEA_HORSE_MEMBERDETAILS  + " set ";
            
            if(competitionName!=null && competitionName.trim().length()!=0){
                competitionName = competitionName.replaceAll("'","''");
                updateStatement = updateStatement + " competition_name = '" +  competitionName + "'" ;
                chkList = true;
            }
            
            if(registereName!=null && registereName.trim().length()!=0){
                registereName = registereName.replaceAll("'","''");
                if(chkList==true) updateStatement = updateStatement + ", ";
                updateStatement = updateStatement + " registered_name = '" +  registereName + "'" ;
            }
            
             if(oldCompetitionName!=null && oldCompetitionName.trim().length()!=0){
                oldCompetitionName = oldCompetitionName.replaceAll("'","''");
                if(chkList==true) updateStatement = updateStatement + ", ";
                updateStatement = updateStatement + " ba_past_name = '" +  oldCompetitionName + "'" ;
            }
            updateStatement = updateStatement + " where horse_member_id = '" + horseMemberId.trim() + "'";
            
            Debug.print("updateStatement:" + updateStatement);
            
            prepStmt = con.prepareStatement(updateStatement);
            // prepStmt.setString(1, horseMemberId);
            
            int cnt = prepStmt.executeUpdate();
            prepStmt.close();
            Debug.print("Record Inserted succefully in updateHorseDetailsByAdmin Details: " + cnt);
            if(cnt>=1){
                result = true;
            }
            Debug.print("HorseRegDAO updateHorseDetailsByAdmin Status :" + result);
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.updateHorseDetailsByAdmin():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.updateHorseDetailsByAdmin():" + e.getMessage());
        }
        return result;
    }
    
    
    //=============================================Update Request Status for Horse Details =========================================
    public boolean updateRequetsHorseDetailsForRider(String horseMemberId, String riderId) {
        Debug.print("HorseRegDAO.updateRequetsHorseDetailsForRider():");
        boolean result = false;
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String updateStatement = "update " + DBHelper.USEA_HORSE_MEMBERDETAILS  +
                    " set rider_member_id=? " +
                    " where horse_member_id = ? ";
            
            if(horseMemberId!=null && horseMemberId.trim().length()!=0 ){
                prepStmt = con.prepareStatement(updateStatement);
                prepStmt.setString(1, riderId);
                prepStmt.setString(2, horseMemberId);
                
                int cnt = prepStmt.executeUpdate();
                prepStmt.close();
                Debug.print("Record Inserted succefully in updateRequetsHorseDetailsForRider Details: " + cnt);
                if(cnt>=1){
                    result = true;
                }
                Debug.print("HorseRegDAO updateRequetsHorseDetailsForRider Status :" + result);
            }
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.updateRequetsHorseDetailsForRider():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.updateRequetsHorseDetailsForRider():" + e.getMessage());
        }
        return result;
    }
    
    //=============================================Update Request Status for Horse Details =========================================
    public boolean updateRequetsHorseDetailsForOwner(String horseMemberId, String ownerId) {
        Debug.print("HorseRegDAO.updateRequetsHorseDetailsForOwner():");
        boolean result = false;
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String updateStatement = "update " + DBHelper.USEA_HORSE_MEMBERDETAILS  +
                    " set owner_id=? " +
                    " where horse_member_id = ? ";
            
            if(horseMemberId!=null && horseMemberId.trim().length()!=0 ){
                prepStmt = con.prepareStatement(updateStatement);
                prepStmt.setString(1, ownerId);
                prepStmt.setString(2, horseMemberId);
                
                int cnt = prepStmt.executeUpdate();
                prepStmt.close();
                Debug.print("Record Inserted succefully in updateRequetsHorseDetailsForOwner Details: " + cnt);
                if(cnt>=1){
                    result = true;
                }
                Debug.print("HorseRegDAO updateRequetsHorseDetailsForOwner Status :" + result);
            }
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.updateRequetsHorseDetailsForOwner():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.updateRequetsHorseDetailsForOwner():" + e.getMessage());
        }
        return result;
    }
    
    //=============================================Update Owner Details =========================================
    public boolean upgradeHorseDetails(String horseMemberId, String membershipTypeId, String paymentId,String membershipAmount, String amount) {
        Debug.print("HorseRegDAO.upgradeHorseDetails() horseMemberId:" + horseMemberId);
        Debug.print("HorseRegDAO.upgradeHorseDetails() membershipTypeId:" + membershipTypeId);
        boolean result = false;
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String updateStatement = "update  tblHorseMemberDetails "+
                    " set payment_id = ? , upgradation_date = ?, membership_amount = ?, amount = ? where horse_member_id = ?";
            
            prepStmt = con.prepareStatement(updateStatement);
            
            prepStmt.setString(1, paymentId);
            prepStmt.setDate(2, DBHelper.toSQLDate(new Date()));
            prepStmt.setString(3, membershipAmount);
            prepStmt.setString(4, amount);
            prepStmt.setString(5, horseMemberId);
            
            int cnt = prepStmt.executeUpdate();
            Debug.print("Record Updated succefully in upgradeHorseDetails : " + cnt);
            if(cnt>=1){
                result = true;
            }
            
            Debug.print("HorseRegDAO upgradeHorseDetails Status :" + result);
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.upgradeHorseDetails():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.upgradeHorseDetails():" + e.getMessage());
        }
        result = updateMemberDetails(horseMemberId, membershipTypeId);
        return result;
    }
    
    public boolean upgradeNonRegisteredDetails(String horseMemberId, String membershipTypeId, String paymentId, String membershipAmount, String totalAmount) {
        
        Debug.print("HorseRegDAO.upgradeNonRegisteredDetails() Total Amount:" + totalAmount);
        
        boolean result = false;
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String updateStatement = "update  tblHorseMemberDetails "+
                    " set payment_id = ?, membership_amount = ?, amount = ? where horse_member_id = ?";
            
            prepStmt = con.prepareStatement(updateStatement);
            
            prepStmt.setString(1, paymentId);
            prepStmt.setString(2, membershipAmount);
            prepStmt.setString(3, totalAmount);
            prepStmt.setString(4, horseMemberId);
            
            int cnt = prepStmt.executeUpdate();
            Debug.print("Record Updated succefully in upgradeNonRegisteredDetails : " + cnt);
            if(cnt>=1){
                result = true;
            }
            
            Debug.print("HorseRegDAO upgradeNonRegisteredDetails Status :" + result);
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.upgradeNonRegisteredDetails():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.upgradeNonRegisteredDetails():" + e.getMessage());
        }
        result = updateMemberDetails(horseMemberId, membershipTypeId);
        return result;
    }
    
    //=============================================select a Breed Details =========================================
    public boolean isHorseExist(String horseName){
        Debug.print("HorseRegDAO.isHorseExist() breedId:" + horseName);
        PreparedStatement prepStmt = null;
        boolean result = false;
        makeConnection();
        try {
            String selectStatement = "select horse_member_id from tblHorseMemberDetails where competition_name= ?";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,horseName);
            ResultSet rs = prepStmt.executeQuery();
            if(rs.next()){
                result = true;
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.isHorseExist():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.isHorseExist():" + e.getMessage());
        }
        return result;
    }
    
    //=============================================select a Breed Details =========================================
    public boolean isRiderExist(String horseMemberId, String relTypId, String userId){
        Debug.print("HorseRegDAO.isRiderExist() horseMemberId:" + horseMemberId);
        PreparedStatement prepStmt = null;
        boolean result = false;
        makeConnection();
        try {
            String selectStatement = "select relation_id from tblHorseMemberRelationDetails " +
                    "where horse_member_id= ? and relationship_type_id= ?  and user_id= ?";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,horseMemberId);
            prepStmt.setString(2,relTypId);
            prepStmt.setString(3,userId);
            ResultSet rs = prepStmt.executeQuery();
            if(rs.next()){
                result = true;
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.isRiderExist():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.isRiderExist():" + e.getMessage());
        }
        return result;
    }
    
    //=============================================select a Breed Details =========================================
    public boolean isRiderExistInHrMember(String horseMemberId, String riderId){
        Debug.print("HorseRegDAO.isRiderExistInHrMember() horseMemberId:" + horseMemberId);
        PreparedStatement prepStmt = null;
        boolean result = false;
        makeConnection();
        try {
            String selectStatement = "select horse_id from tblHorseMemberDetails " +
                    "where horse_member_id= ? and rider_member_id= ?";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,horseMemberId);
            prepStmt.setString(2,riderId);
            ResultSet rs = prepStmt.executeQuery();
            if(rs.next()){
                result = true;
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.isRiderExistInHrMember():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.isRiderExistInHrMember():" + e.getMessage());
        }
        return result;
    }
    
    //=============================================select a Breed Details =========================================
    public String getRiderIdFromHrMember(String horseMemberId){
        Debug.print("HorseRegDAO.isRiderExistInHrMember() horseMemberId:" + horseMemberId);
        PreparedStatement prepStmt = null;
        String result = null;
        makeConnection();
        try {
            String selectStatement = "select rider_member_id from tblHorseMemberDetails " +
                    "where horse_member_id= ?";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,horseMemberId);
            ResultSet rs = prepStmt.executeQuery();
            if(rs.next()){
                result = rs.getString(1);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.isRiderExistInHrMember():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.isRiderExistInHrMember():" + e.getMessage());
        }
        return result;
    }
    
    //=============================================User Existing checking in  Horse Member Details =========================================
    public boolean isHorsePriOwnerExist(String horseMemberId, String userId){
        Debug.print("HorseRegDAO.isHorsePriOwnerExist() horseMemberId:" + horseMemberId);
        PreparedStatement prepStmt = null;
        boolean result = false;
        makeConnection();
        try {
            String selectStatement = "select owner_id from tblHorseMemberDetails where owner_id = ? " +
                    " and horse_member_id = ?";
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,userId);
            prepStmt.setString(2,horseMemberId);
            ResultSet rs = prepStmt.executeQuery();
            if(rs.next()){
                result = true;
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.isHorsePriOwnerExist():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.isHorsePriOwnerExist():" + e.getMessage());
        }
        return result;
    }
    
    //=============================================User Existing checking in  Horse Member Details =========================================
    public boolean isHorsePriRiderExist(String horseMemberId, String userId){
        Debug.print("HorseRegDAO.isHorsePriRiderExist() horseMemberId:" + horseMemberId);
        PreparedStatement prepStmt = null;
        boolean result = false;
        makeConnection();
        try {
            String selectStatement = "select A.rider_member_id from tblHorseMemberDetails A, tblMemberDetails B " +
                    " where A.rider_member_id = B.member_id and " +
                    " A.horse_member_id = ? and B.user_id = ?";
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,horseMemberId);
            prepStmt.setString(2,userId);
            ResultSet rs = prepStmt.executeQuery();
            if(rs.next()){
                result = true;
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.isHorsePriRiderExist():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.isHorsePriRiderExist():" + e.getMessage());
        }
        return result;
    }
    
    //=============================================User Existing checking in  Horse Member Details =========================================
    public boolean isHorsePriTrainerExist(String horseMemberId,String relationId){
        Debug.print("HorseRegDAO.isHorsePriTrainerExist() horseMemberId:" + horseMemberId+" relationId is "+relationId);
        PreparedStatement prepStmt = null;
        PreparedStatement prepStmt1 = null;
        boolean result = false;
        makeConnection();
        try {
            
            String selectStatement = "select * from tblHorseMemberRelationDetails where horse_member_id = ? and relationship_type_id = ?";
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,horseMemberId);
            prepStmt.setString(2,relationId);
            ResultSet rs = prepStmt.executeQuery();
            
            if(rs.next()){
                return true;
            } else{
                Debug.print("Inside else of count query");
                String updatememberDet = "update tblHorseMemberDetails set trainer_id = ? where horse_member_id = ?";
                prepStmt1 = con.prepareStatement(updatememberDet);
                
                prepStmt1.setString(1,null);
                prepStmt1.setString(2,horseMemberId);
                
                int count = prepStmt1.executeUpdate();
                if(count>0){
                    Debug.print("MemberDetailtable trainer details changed");
                }
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.isHorsePriTrainerExist():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.isHorsePriTrainerExist():" + e.getMessage());
        }
        return result;
    }
    
    //=============================================User Existing checking in  Horse Member Details =========================================
    public boolean isHorsePriOwnerRiderExistInRelation(String relationId, String userId){
        Debug.print("HorseRegDAO.isHorsePriOwnerRiderExistInRelation() horseMemberId:" + relationId);
        PreparedStatement prepStmt = null;
        boolean result = false;
        makeConnection();
        try {
            String selectStatement = "select user_id from tblHorseMemberRelationDetails where " +
                    " relation_id = ? and user_id = ?";
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,relationId);
            prepStmt.setString(2,userId);
            ResultSet rs = prepStmt.executeQuery();
            if(rs.next()){
                result = true;
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.isHorsePriOwnerRiderExistInRelation():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.isHorsePriOwnerRiderExistInRelation():" + e.getMessage());
        }
        return result;
    }
    
    //=============================================select a Breed Details =========================================
    public String selectHorseBreedById(String breedId){
        Debug.print("HorseRegDAO.selectHorseBreedById() breedId:" + breedId);
        PreparedStatement prepStmtBreed = null;
        String breedDesc = "";
        // makeConnection();
        try {
            String selectStatement = "select breed_desc from " + DBHelper.USEA_HORSE_BREED +
                    " where breed_id= ?";
            
            prepStmtBreed = con.prepareStatement(selectStatement);
            prepStmtBreed.setString(1,breedId);
            ResultSet rsBreed = prepStmtBreed.executeQuery();
            if(rsBreed.next()){
                breedDesc = rsBreed.getString(1);
            }
            rsBreed.close();
            prepStmtBreed.close();
            // releaseConnection(con);
        } catch(SQLException sql){
            //releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.selectHorseBreedById():" + sql.getMessage());
        } catch(Exception e){
            //releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.selectHorseBreedById():" + e.getMessage());
        }
        return breedDesc;
    }
    
    //=============================================select a Breed Details =========================================
    public String selectUserTypeId(String userTypeName){
        Debug.print("HorseRegDAO.selectUserTypeId() userTypeName:" + userTypeName);
        PreparedStatement prepStmtBreed = null;
        String userTypeId = "";
        makeConnection();
        try {
            String selectStatement = "select user_type_id from tblUserTypeMaster" +
                    " where user_type_name= ?";
            
            prepStmtBreed = con.prepareStatement(selectStatement);
            prepStmtBreed.setString(1,userTypeName);
            ResultSet rsBreed = prepStmtBreed.executeQuery();
            if(rsBreed.next()){
                userTypeId = rsBreed.getString(1);
            }
            rsBreed.close();
            prepStmtBreed.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.selectUserTypeId():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.selectUserTypeId():" + e.getMessage());
        }
        return userTypeId;
    }
    
    //=============================================select a All Country price Details =========================================
    public String selectHorseColorById(String colorId){
        Debug.print("HorseRegDAO.selectHorseColorById() colorId:" + colorId);
        String colorDesc  = "";
        PreparedStatement prepColorStmt = null;
        // makeConnection();
        try {
            String selectStatement = "select color_desc from " + DBHelper.USEA_HORSE_COLOR +
                    " where color_id= ?";
            
            prepColorStmt = con.prepareStatement(selectStatement);
            prepColorStmt.setString(1,colorId);
            ResultSet rsColor = prepColorStmt.executeQuery();
            if(rsColor.next()){
                colorDesc = rsColor.getString(1);
            }
            rsColor.close();
            prepColorStmt.close();
            // releaseConnection(con);
        } catch(SQLException sql){
            //releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.selectHorseColorById():" + sql.getMessage());
        } catch(Exception e){
            // releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.selectHorseColorById():" + e.getMessage());
        }
        return colorDesc;
    }
    
    
    public String selectRegistrarName(String userId){
        //Debug.print("HorseRegDAO.selectRegistrarName() userId:" + userId);
        String name  = "";
        PreparedStatement prepUserStmt = null;
        // makeConnection();
        try {
            String selectStatement = "select first_name, last_name from tblUserMaster where user_id= ?";
            
            prepUserStmt = con.prepareStatement(selectStatement);
            prepUserStmt.setString(1,userId);
            ResultSet rsUser = prepUserStmt.executeQuery();
            
            
            if(rsUser.next()){
                String firstName = rsUser.getString(1);
                String lastName = rsUser.getString(2);
                name = firstName + " "  + lastName;
            }
            
            
            rsUser.close();
            prepUserStmt.close();
            // releaseConnection(con);
        } catch(SQLException sql){
            //releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.selectRegistrarName():" + sql.getMessage());
        } catch(Exception e){
            // releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.selectRegistrarName():" + e.getMessage());
        }
        //   Debug.print("    registrar Name:" + name);
        return name;
    }
    
    
    //=============================================select a All Country price Details =========================================
    public String selectUserTypeName(String userTypeId){
        Debug.print("HorseRegDAO.selectUserTypeName() userTypeId:" + userTypeId);
        String userTypeName  = "";
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String selectStatement = "select user_type_name from tblUserTypeMaster where user_type_id= ?";
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,userTypeId);
            ResultSet rs = prepStmt.executeQuery();
            if(rs.next()){
                userTypeName = rs.getString(1);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.selectUserTypeName():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.selectUserTypeName():" + e.getMessage());
        }
        return userTypeName;
    }
    
    
    //=============================================select a All Country price Details =========================================
    public String selectHorseEditCharge(){
        Debug.print("HorseRegDAO.selectHorseEditCharge() ");
        String price  = "";
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String selectStatement = "select horse_service_type_amount from tblHorseServiceTypeMaster " +
                    "where horse_service_type_name = 'Horse / Rider / Owner Change'";
            
            prepStmt = con.prepareStatement(selectStatement);
            ResultSet rs = prepStmt.executeQuery();
            if(rs.next()){
                price = rs.getString(1);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.selectHorseEditCharge():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.selectHorseEditCharge():" + e.getMessage());
        }
        return price;
    }
    
    //=============================================select a All Country price Details =========================================
    public String[] selectHorseServiceCharge(){
        Debug.print("HorseRegDAO.selectHorseServiceCharge() ");
        String[] serviceList  = {};
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String selectStatement = "select horse_service_type_id, horse_service_type_name, " +
                    " horse_service_type_amount from tblHorseServiceTypeMaster " +
                    "where horse_service_type_name = 'Phone Service Charge'";
            
            prepStmt = con.prepareStatement(selectStatement);
            ResultSet rs = prepStmt.executeQuery();
            if(rs.next()){
                String typeId = rs.getString(1);
                String typeName = rs.getString(2);
                String typePrice = rs.getString(3);
                String tempServiceList[] = {typeId, typeName, typePrice};
                serviceList = tempServiceList;
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.selectHorseServiceCharge():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.selectHorseServiceCharge():" + e.getMessage());
        }
        return serviceList;
    }
    
    //=============================================select a All Country price Details =========================================
    public String[] selectEmailIds(String horseMemberId){
        Debug.print("HorseRegDAO.selectEmailIds() ");
        String[] serviceList  = {};
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String selectStatement = " select A.email_id " +
                    " from tblUserMaster A, tblHorseMemberRelationDetails B  " +
                    "where A.user_id = B.user_id and B.horse_member_id = ?";
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,horseMemberId);
            ResultSet rs = prepStmt.executeQuery();
            int i=0;
            while(rs.next()){
                String emailId = rs.getString(1);
                Debug.print("emailId in HorseRegDAO.selectEmailIds() ********" + emailId);
                if(emailId!=null && emailId.trim().length()!=0){
                    serviceList[i] = emailId;
                    i++;
                }
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.selectEmailIds():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.selectEmailIds():" + e.getMessage());
        }
        return serviceList;
    }
    
    //=============================================select a Owner Details =========================================
    public String selectOwnerIdFromHrMember(String hrMemberId){
        Debug.print("HorseRegDAO.selectOwnerIdFromHrMember() hrMemberId:" + hrMemberId);
        String ownerId  = "";
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String selectStatement = "select owner_id from tblHorseMemberDetails where horse_member_id= ?";
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,hrMemberId);
            ResultSet rs = prepStmt.executeQuery();
            if(rs.next()){
                ownerId = rs.getString(1);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.selectOwnerIdFromHrMember():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.selectOwnerIdFromHrMember():" + e.getMessage());
        }
        return ownerId;
    }
    
    //=============================================select a Owner Details =========================================
    public String selectTrainerIdFromHrMember(String hrMemberId){
        Debug.print("HorseRegDAO.selectTrainerIdFromHrMember() hrMemberId:" + hrMemberId);
        String trainerId  = "";
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String selectStatement = "select trainer_id from tblHorseMemberDetails where horse_member_id= ?";
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,hrMemberId);
            ResultSet rs = prepStmt.executeQuery();
            if(rs.next()){
                trainerId = rs.getString(1);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.selectTrainerIdFromHrMember():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.selectTrainerIdFromHrMember():" + e.getMessage());
        }
        return trainerId;
    }
    
    //=============================================select a Owner Details =========================================
    public String selectRelationIdFromMemberIdUserIdRelationId(String hrMemberId, String userId, String relationshipId){
        Debug.print("HorseRegDAO.selectRelationIdFromMemberIdUserIdRelationId() hrMemberId:" + hrMemberId);
        String relationId  = "";
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String selectStatement = "select relation_id from tblHorseMemberRelationDetails where horse_member_id= ? and " +
                    " relationship_type_id = ? and user_id = ?";
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,hrMemberId);
            prepStmt.setString(2,relationshipId);
            prepStmt.setString(3,userId);
            ResultSet rs = prepStmt.executeQuery();
            if(rs.next()){
                relationId = rs.getString(1);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.selectRelationIdFromMemberIdUserIdRelationId():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.selectRelationIdFromMemberIdUserIdRelationId():" + e.getMessage());
        }
        return relationId;
    }
    
    
    //=============================================select Horse Relationship TypeId =========================================
    public String selectRelationTypeId(String relTypeName){
        Debug.print("HorseRegDAO.selectRelationTypeId() relTypeName:" + relTypeName);
        String hrRelTypeId  = null;
        PreparedStatement prepStmt = null;
        
        try {
            makeConnection();
            String selectStatement = "select relationship_type_id from tblHorseRelationshipTypeMaster where relationship_type_name= ?";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,relTypeName.trim());
            ResultSet rs = prepStmt.executeQuery();
            if(rs.next()){
                hrRelTypeId = rs.getString(1);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.selectRelationTypeId():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.selectRelationTypeId():" + e.getMessage());
        } finally{
            releaseConnection(con);
        }
        return hrRelTypeId;
    }
    
    
    //=============================================GetUserId from memberId =========================================
    public String selectUserId(String memberId){
        Debug.print("HorseRegDAO.selectUserId() relTypeName:" + memberId);
        String userId  = null;
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String selectStatement = "select user_id from tblMemberDetails where member_id= ?";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,memberId.trim());
            ResultSet rs = prepStmt.executeQuery();
            if(rs.next()){
                userId = rs.getString(1);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.selectUserId():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.selectUserId():" + e.getMessage());
        }
        return userId;
    }
    
    //=============================================GetUserId from memberId =========================================
    public String selectMemberId(String userId){
        Debug.print("HorseRegDAO.selectMemberId() userId:" + userId);
        String memberId  = "";
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String selectStatement = "select member_id from tblMemberDetails where user_id= ?";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,userId.trim());
            ResultSet rs = prepStmt.executeQuery();
            if(rs.next()){
                memberId = rs.getString(1);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.selectMemberId():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.selectMemberId():" + e.getMessage());
        }
        return memberId;
    }
    
    //=============================================GetUserId from memberId =========================================
    public String selectStatusId(String statusName){
        Debug.print("HorseRegDAO.selectStatusId() statusName:" + statusName);
        String statusId  = "";
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String selectStatement = "select status_id from tblMembershipStatusMaster where status_name= ?";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,statusName.trim());
            ResultSet rs = prepStmt.executeQuery();
            if(rs.next()){
                statusId = rs.getString(1);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.selectStatusId():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.selectStatusId():" + e.getMessage());
        }
        Debug.print("HorseRegDAO.selectStatusId() statusId" + statusId);
        return statusId;
    }
    
    
    //=============================================select a particular Horse Details =========================================
    public ArrayList selectAllHorseRelationMaster(){
        Debug.print("HorseRegDAO.selectAllHorseRelationMaster():");
        ArrayList horseList = new ArrayList();
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String selectStatement = "select relationship_type_id, relationship_type_name from tblHorseRelationshipTypeMaster order by  relationship_type_name";
            
            prepStmt = con.prepareStatement(selectStatement);
            ResultSet rs = prepStmt.executeQuery();
            while(rs.next()){
                String relaTypId = rs.getString(1);
                String relaTyName = rs.getString(2);
                String hrList[] = {relaTypId, relaTyName};
                horseList.add(hrList);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.selectAllHorseRelationMaster():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.selectAllHorseRelationMaster():" + e.getMessage());
        }
        return horseList;
    }
    
    //=============================================Update Payment Details =========================================
    public boolean updatePaymentStatus(com.hlcreg.util.HLCPaymentVO objPaymentVO) {
        Debug.print("HorseRegDAO.updatePaymentStatus(): PaymentId" + objPaymentVO.getPaymentId());
        boolean result = false;
        PreparedStatement prepStmt = null;
        makeConnection();
        
        String ccNumber = objPaymentVO.getCcNumber();
        if (!(ccNumber.equals("0"))) {
            String temp = ccNumber.substring(0, 2);
            String temp1 = ccNumber.substring(2, 12);
            String temp2 = ccNumber.substring(12);
            temp1 = "***";
            ccNumber = temp+temp1+temp2;
            }
            Debug.print(ccNumber+"=testing ccnumber in HorseRegDAO");
        try {
            String updateStatement = "update  tblUserPaymentDetails " +
                    " set  cc_name = ?, cc_type = ?, cc_number = ?, cc_exp_month = ? ," +
                    " cc_exp_year = ?, cc_cvvid = ?, bank_name = ?, check_date = ?, check_number = ?, " +
                    " check_name = ?, amount = ?, " +
                    " ssl_result = ? , ssl_result_message = ? ,ssl_txn_id= ?," +
                    " ssl_approval_code = ? , ssl_cvv2_response = ? , ssl_avs_response = ?, " +
                    " ssl_transaction_type = ?, ssl_invoice_no = ? , ssl_email = ?, " +
                    "  payment_status = ?, payment_date = ?, parent_payment_id=?," +
                    "  ip_address= ?  where  payment_id = ?";
            
            prepStmt = con.prepareStatement(updateStatement);
            prepStmt.setString(1, objPaymentVO.getCcName());
            prepStmt.setString(2, objPaymentVO.getCcType());
            prepStmt.setString(3, ccNumber);
            prepStmt.setInt(4, objPaymentVO.getCcExpMonth());
            prepStmt.setInt(5, objPaymentVO.getCcExpYear());
            //prepStmt.setInt(6, objPaymentVO.getCcCvvid());
            prepStmt.setInt(6, 0);
            prepStmt.setString(7, objPaymentVO.getBankName());
            if(objPaymentVO.getCheckDate()!=null){
                prepStmt.setDate(8, DBHelper.toSQLDate(objPaymentVO.getCheckDate()));
            } else{
                prepStmt.setDate(8, null);
            }
            prepStmt.setString(9, objPaymentVO.getCheckNumber());
            prepStmt.setString(10, objPaymentVO.getCheckName());
            prepStmt.setDouble(11, objPaymentVO.getAmount());
            
            prepStmt.setString(12, objPaymentVO.getSslResult());
            prepStmt.setString(13, objPaymentVO.getSslResultMessage());
            prepStmt.setString(14, objPaymentVO.getSslTxnId());
            prepStmt.setString(15, objPaymentVO.getSslApprovalCode());
            prepStmt.setString(16, objPaymentVO.getSslCvv2Response());
            prepStmt.setString(17, objPaymentVO.getSslAvsResponse());
            prepStmt.setString(18, objPaymentVO.getSslTransactionType());
            prepStmt.setString(19, objPaymentVO.getSslInvoiceNo());
            prepStmt.setString(20, objPaymentVO.getSslEmail());
            prepStmt.setString(21, objPaymentVO.getPaymentStatus());
            prepStmt.setDate(22, DBHelper.toSQLDate(new Date()));
            prepStmt.setString(23, objPaymentVO.getParentPaymentId());
            prepStmt.setString(24, objPaymentVO.getIpAddress());
            prepStmt.setString(25, objPaymentVO.getPaymentId());
            
            
            int cnt = prepStmt.executeUpdate();
            Debug.print("Record Updated succefully in updatePaymentStatus : " + cnt);
            if(cnt>=1){
                result = true;
            }
            
            Debug.print("HorseRegDAO updatePaymentStatus Status :" + result);
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.updatePaymentStatus():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.updatePaymentStatus():" + e.getMessage());
        }
        return result;
    }
    
    
    //=============================================Update Payment Details =========================================
    public boolean updatePaymentCheckAmount(float checkAmount, String paymentId) {
        Debug.print("HorseRegDAO.updatePaymentCheckAmount():");
        
        boolean result = false;
        PreparedStatement prepStmt = null;
        makeConnection();
        
        try {
            String updateStatement = "update  tblUserPaymentDetails " +
                    " set check_amount = ? where payment_id = ?";
            
            prepStmt = con.prepareStatement(updateStatement);
            
            prepStmt.setFloat(1, checkAmount);
            prepStmt.setString(2, paymentId);
            
            int cnt = prepStmt.executeUpdate();
            Debug.print("Record Updated succefully in updatePaymentCheckAmount : " + cnt);
            result = true;
            Debug.print("HorseRegDAO updatePaymentCheckAmount Status :" + result);
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.updatePaymentCheckAmount():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.updatePaymentCheckAmount():" + e.getMessage());
        }
        return result;
    }
    
    public boolean updateTrainerInfo(String horseMemberId, String trainerId) throws SQLException {
        Debug.print("HorseRegDAO updateTrainerInfo");
        boolean result = false;
        try{
            makeConnection();
            String stmt = "update "+DBHelper.USEA_HORSE_MEMBERDETAILS+" set trainer_id = ? where horse_member_id = ?";
            PreparedStatement prepSelect = con.prepareStatement(stmt);
            
            prepSelect.setString(1,trainerId);
            prepSelect.setString(2,horseMemberId);
            int rs = prepSelect.executeUpdate();
            
            if(rs>0) {
                result = true;
            }
            
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            throw new EJBException("SQL Exception in HorseRegDAO getMemberId:" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            throw new EJBException("General Exception  in HorseRegDAO getMemberId:" + e.getMessage());
        } finally{
            releaseConnection(con);
        }
        return result;
    }
    
    public boolean deleteTrainerDetails(String horseMemberId) throws SQLException {
        Debug.print("HorseRegDAO deleteTrainerDetails");
        boolean result = false;
        boolean delResult = false;
        try{
            makeConnection();
            String delStmt = "delete from tblHorseMemberRelationDetails where horse_member_id = ? and relationship_type_id = " +
                    "(select relationship_type_id from tblHorseRelationshipTypeMaster where relationship_type_name = 'Trainer')";
            
            PreparedStatement preDel = con.prepareStatement(delStmt);
            preDel.setString(1,horseMemberId);
            int del = preDel.executeUpdate();
            if(del>0) delResult = true;
            
            if(delResult){
                String stmt = "update "+DBHelper.USEA_HORSE_MEMBERDETAILS+" set trainer_id = NULL where horse_member_id = ?";
                PreparedStatement prepSelect = con.prepareStatement(stmt);
                
                
                prepSelect.setString(1,horseMemberId);
                int rs = prepSelect.executeUpdate();
                
                if(rs>0) {
                    result = true;
                }
            }
            
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            throw new EJBException("SQL Exception in HorseRegDAO getMemberId:" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            throw new EJBException("General Exception  in HorseRegDAO getMemberId:" + e.getMessage());
        } finally{
            releaseConnection(con);
        }
        return result;
    }
    
    //====================================Update Horse Status by admin==============================
    
    public boolean approveHorseStatusByAdmin(String horseMemberId, String requestStatus, String  comments, Date activitationDate, String modifiedBy, Date currDate) {
        Debug.print("HorseRegDAO.approveHorseStatusByAdmin():" + activitationDate);
        Debug.print("modifiedById in HorseRegDAO.approveHorseStatusByAdmin():" + modifiedBy);
        Debug.print("currDate in HorseRegDAO.approveHorseStatusByAdmin():" + currDate);
        Debug.print("requestStatus in HorseRegDAO.approveHorseStatusByAdmin():" + requestStatus);
        if(requestStatus!=null && requestStatus.trim().length()!=0){
            boolean res = updateStatusId(horseMemberId, requestStatus, activitationDate);
        }
        boolean result = false;
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String updateStatement = "update  tblHorseMemberDetails "+
                    " set comments = ?, activation_date= ?, modified_by= ?, modify_date= ? where horse_member_id = ?";
            
            prepStmt = con.prepareStatement(updateStatement);
            
            prepStmt.setString(1, comments);
            prepStmt.setDate(2, DBHelper.toSQLDate(activitationDate));
            prepStmt.setString(3, modifiedBy);
            prepStmt.setDate(4, DBHelper.toSQLDate(currDate));
            prepStmt.setString(5, horseMemberId);
            
            int cnt = prepStmt.executeUpdate();
            Debug.print("Record Updated successfully in approveHorseStatusByAdmin : " + cnt);
            result = true;
            Debug.print("HorseRegDAO.approveHorseStatusByAdmin Status :" + result);
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.approveHorseStatusByAdmin():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.approveHorseStatusByAdmin():" + e.getMessage());
        }
        return result;
    }
    
    //=============================================Horse Registration Email Content =========================================
    public ArrayList selectUserDetails(String horseMemberId){
        Debug.print("HorseRegDAO.selectUserDetails() loginName:" + horseMemberId);
        ArrayList usrList = new ArrayList();
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String selectStatement = "select A.first_name, A.last_name from tblUserMaster A, tblMemberDetails B, " +
                    "tblHorseMemberDetails C where A.user_id = B.user_id and B.member_id= C.rider_member_id " +
                    "and C.horse_member_id=?";
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,horseMemberId);
            ResultSet rs = prepStmt.executeQuery();
            while(rs.next()){
                HLCHorseRegListVO objHrListVO = new HLCHorseRegListVO();
                
                String riderFirstNam = rs.getString(1);
                String riderLastNam = rs.getString(2);
                
                objHrListVO.setRiderFirstName(riderFirstNam);
                objHrListVO.setRiderLastName(riderLastNam);
                usrList.add(objHrListVO);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.selectUserDetails():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.selectUserDetails():" + e.getMessage());
        }
        return usrList;
    }
    //==================================================================
    public ArrayList selectOwnerDetails(String horseMemberId){
        Debug.print("HorseRegDAO.selectOwnerDetails() horseMemberId:" + horseMemberId);
        ArrayList usrList = new ArrayList();
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String selectStatement = "select A.first_name, A.last_name, A.email_id from tblUserMaster A, " +
                    "tblMemberDetails B, tblHorseMemberDetails C where C.owner_id = A.user_id and " +
                    "B.member_id= C.rider_member_id and C.horse_member_id=?";
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,horseMemberId);
            ResultSet rs = prepStmt.executeQuery();
            while(rs.next()){
                HLCHorseRegListVO objHrListVO = new HLCHorseRegListVO();
                
                String ownerFirstNam = rs.getString(1);
                String ownerLastNam = rs.getString(2);
                String ownerEmail = rs.getString(3);
                
                objHrListVO.setOwnerFirstName(ownerFirstNam);
                objHrListVO.setOwnerLastName(ownerLastNam);
                objHrListVO.setOwnerEmail(ownerEmail);
                usrList.add(objHrListVO);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.selectOwnerDetails():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.selectOwnerDetails():" + e.getMessage());
        }
        return usrList;
    }
    
    //=============================================Horse Registration Email Content =========================================
    public ArrayList selectUserDets(String memberId){
        Debug.print("HorseRegDAO.selectUserDets() memberId:" + memberId);
        ArrayList usrList = new ArrayList();
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String selectStatement = "select A.login_name, A.first_name, A.last_name, A.email_id, B.status_id, " +
                    " C.status_name from tblUserMaster A, tblMemberDetails B, tblMembershipStatusMaster C " +
                    " where A.user_id= B.user_id and B.status_id=C.status_id and B.member_id=?";
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,memberId);
            ResultSet rs = prepStmt.executeQuery();
            while(rs.next()){
                HLCHorseRegListVO objHrListVO = new HLCHorseRegListVO();
                
                String ownerlogNam = rs.getString(1);
                String ownerFirstNam = rs.getString(2);
                String ownerLastNam = rs.getString(3);
                String ownerEmail = rs.getString(4);
                String ownerStatId = rs.getString(5);
                String ownerStatName = rs.getString(6);
                
                
                objHrListVO.setOwnerLogName(ownerlogNam);
                objHrListVO.setOwnerFirstName(ownerFirstNam);
                objHrListVO.setOwnerLastName(ownerLastNam);
                objHrListVO.setOwnerEmail(ownerEmail);
                objHrListVO.setStatusId(ownerStatId);
                objHrListVO.setStatusName1(ownerStatName);
                
                usrList.add(objHrListVO);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.selectUserDets():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.selectUserDets():" + e.getMessage());
        }
        return usrList;
    }
    
    public ArrayList selectOwnerDetailsForMail(String logName){
        Debug.print("HorseRegDAO.selectOwnerDetailsForMail() logName:" + logName);
        ArrayList usrList = new ArrayList();
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String selectStatement = "select A.login_name, A.first_name, A.last_name, A.email_id, " +
                    " B.status_id, C.status_name from tblUserMaster A, tblMemberDetails B, " +
                    " tblMembershipStatusMaster C where A.user_id = B.user_id and " +
                    " B.status_id=C.status_id and A.login_name=?";
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,logName);
            ResultSet rs = prepStmt.executeQuery();
            while(rs.next()){
                HLCHorseRegListVO objHrListVO = new HLCHorseRegListVO();
                
                String ownerLogNam = rs.getString(1);
                String ownerFirstNam = rs.getString(2);
                String ownerLastNam = rs.getString(3);
                String ownerEmail = rs.getString(4);
                String ownerStatId = rs.getString(5);
                String ownerStatName = rs.getString(6);
                
                objHrListVO.setOwnerLogName(ownerLogNam);
                objHrListVO.setOwnerFirstName(ownerFirstNam);
                objHrListVO.setOwnerLastName(ownerLastNam);
                objHrListVO.setOwnerEmail(ownerEmail);
                objHrListVO.setStatusId(ownerStatId);
                objHrListVO.setStatusName1(ownerStatName);
                usrList.add(objHrListVO);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.selectOwnerDetailsForMail():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.selectOwnerDetailsForMail():" + e.getMessage());
        }
        return usrList;
    }
    
    public ArrayList selectPreOwnerRiderDetails(String horseMemberId){
        Debug.print("HorseRegDAO.selectPreOwnerRiderDetails() horseMemberId:" + horseMemberId);
        ArrayList usrList = new ArrayList();
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String selectStatement = "select A.relationship_type_id, C.relationship_type_name, A.relationship_status, " +
                    "B.first_name, B.last_name, B.email_id from tblHorseMemberRelationDetails A, " +
                    "tblUserMaster B, tblHorseRelationshipTypeMaster C where A.relationship_type_id=C.relationship_type_id " +
                    "and A.user_id=B.user_id and horse_member_id=?";
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,horseMemberId);
            ResultSet rs = prepStmt.executeQuery();
            while(rs.next()){
                HLCHorseRegListVO objHrListVO = new HLCHorseRegListVO();
                String relationId = rs.getString(1);
                String relationName = rs.getString(2);
                String relateStatus = rs.getString(3);
                String preFNam = rs.getString(4);
                String preLNam = rs.getString(5);
                String preEmail = rs.getString(6);
                
                
                objHrListVO.setRelationTypeId(relationId);
                objHrListVO.setRelationTypeName(relationName);
                objHrListVO.setRelationStatus(relateStatus);
                objHrListVO.setPreOwnerFName(preFNam);
                objHrListVO.setPreOwnerLName(preLNam);
                objHrListVO.setPreEmail(preEmail);
                usrList.add(objHrListVO);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.selectPreOwnerRiderDetails():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.selectPreOwnerRiderDetails():" + e.getMessage());
        }
        return usrList;
    }
    
    
    public String selectEmailId(String horseMemberId){
        Debug.print("HorseRegDAO.selectEmailId() horseMemberId:" + horseMemberId);
        String emailId  = "";
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String selectStatement = "select A.owner_id, B.user_id, B.email_id from tblHorseMemberDetails A, " +
                    "tblUserMaster B where A.owner_id=B.user_id and A.horse_member_id= ?";

            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,horseMemberId.trim());
            ResultSet rs = prepStmt.executeQuery();
            if(rs.next()){
                emailId = rs.getString(3);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.selectEmailId():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.selectEmailId():" + e.getMessage());
        }
        Debug.print("HorseRegDAO.selectStatusId() emailId" + emailId);
        return emailId;
    }
    
     public String insertRequestHorseMemberDetailsChange(HLCRequestHorseDetVO reqHrDetVO, boolean reqValue) {
        Debug.print("reqValue in insertRequestHorseMemberDetailsChange:" + reqValue);
        Debug.print("HorseRegDAO.insertRequestHorseMemberDetailsChange(): Horse_member_id : " + reqHrDetVO.getHorseMemberId());
        boolean result = false;
        PreparedStatement prepStmt = null;
        String reqId = "";
        try{
            reqId = getNextId();
        } catch(SQLException e){
            Debug.print("Exception while calling getNextId():" + e.getMessage());
        }
        //String userId = insertHorseUserDetails();
        String statusId = selectStatusId("Pending");
        if(reqHrDetVO!=null && reqHrDetVO.getHorseMemberId()!=null){
            makeConnection();
            try {
                String insertStatement = "insert into tblHorseChangeRequestDetails (request_id, horse_member_id, payment_id, "+
                        " req_owner_id, req_rider_id, req_horse_name, req_horse_reg_name, requested_by, req_amount, req_date, add_payment_status, req_status) " +
                        " values ( ?, ?, ?, ?, ?, ?, ?, ? ,?, ?, ?, ?) ";
                
                prepStmt = con.prepareStatement(insertStatement);
                prepStmt.setString(1,reqId);
                prepStmt.setString(2, reqHrDetVO.getHorseMemberId());
                prepStmt.setString(3, reqHrDetVO.getPaymentId());
                prepStmt.setString(4, reqHrDetVO.getReqOwnerId());
                prepStmt.setString(5, reqHrDetVO.getReqRiderId());
                prepStmt.setString(6, reqHrDetVO.getReqHorseName());
                prepStmt.setString(7, reqHrDetVO.getReqHorseRegName());
                prepStmt.setString(8, reqHrDetVO.getRequestedBy());
                prepStmt.setFloat(9, reqHrDetVO.getReqAmount());
                prepStmt.setDate(10, DBHelper.toSQLDate(new Date()));
                prepStmt.setBoolean(11, reqHrDetVO.isAddPaymentStatus());
                prepStmt.setBoolean(12, reqValue);
                
                int cnt = prepStmt.executeUpdate();
                Debug.print("Record Inserted succefully into insertRequestHorseMemberDetailsChange "+cnt);
                if(cnt>=1){
                    result = true;
                }
                
                Debug.print("HorseRegDAO insertRequestHorseMemberDetailsChange() Status :" + result);
                prepStmt.close();
                releaseConnection(con);
            } catch(SQLException sql){
                releaseConnection(con);
                Debug.print("SQL Exception in HorseRegDAO.insertRequestHorseMemberDetailsChange():" + sql.getMessage());
            } catch(Exception e){
                releaseConnection(con);
                Debug.print("General Exception  in HorseRegDAO.insertRequestHorseMemberDetailsChange():" + e.getMessage());
            }
        }
        return reqId;
    }
     
     public ArrayList selectHorseOwnRidChangeDetails(String requestId){
        Debug.print("HorseRegDAO.selectHorseOwnRidChangeDetails() requestId:" + requestId);
       ArrayList horseChangeList= new ArrayList();
       
       PreparedStatement prepStmt = null;
       makeConnection();
       
       try{
            String selectStatement = "select horse_member_id, payment_id, req_owner_id, req_rider_id, req_horse_name, " +
                    "req_horse_reg_name, requested_by, req_amount, req_status, req_date, add_payment_status from " +
                    "tblHorseChangeRequestDetails where request_id= ?";

            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,requestId);
            ResultSet rs = prepStmt.executeQuery();
            while(rs.next()){
                
                String horseMemId = rs.getString(1);
                String payId = rs.getString(2);
                String reqOwnerId = rs.getString(3);
                String reqRiderId = rs.getString(4);
                String reqHorseName = rs.getString(5);
                String reqHorseRegName = rs.getString(6);
                String requestBy = rs.getString(7);
                String reqAmt = rs.getString(8);
                String reqStat = rs.getString(9);
                Date reqDt = rs.getDate(10);
                boolean addPayStat = rs.getBoolean(11);
                
                String[] tmpDets ={horseMemId,payId,reqOwnerId,reqRiderId,reqHorseName,reqHorseRegName,requestBy,reqAmt,reqStat,String.valueOf(reqDt),String.valueOf(addPayStat)};
                horseChangeList.add(tmpDets);                  
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con); 
       }catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.selectHorseOwnRidChangeDetails():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception in HorseRegDAO.selectHorseOwnRidChangeDetails():" + e.getMessage());
        }
       
       return horseChangeList;
   } 
   
     public ArrayList selectHorseRelationDetails(String horseMemberId) throws SQLException{
        Debug.print("HorseRegDAO.selectHorseRelationDetails()");
        ArrayList horseRelList = new ArrayList();
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
        try {         
            String selectStatement = " select A.first_name, A.last_name, A.email_id, C.relationship_type_id, D.relationship_type_name, " +
                    " C.relationship_status, C.relation_id, B.horse_member_id from tblUserMaster A, " +
                    " tblHorseMemberDetails B, tblHorseMemberRelationDetails C, tblHorseRelationshipTypeMaster D " +
                    " where A.user_id = C.user_id and C.horse_member_id = B.horse_member_id and " +
                    " C.relationship_type_id = D.relationship_type_id and B.horse_member_id = ? order by D.relationship_type_name";
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,horseMemberId);
            rs = prepStmt.executeQuery();
            while(rs.next()){
                String firstName =  rs.getString(1);
                String last_name =  rs.getString(2);
                String emailId =  rs.getString(3);
                String relationship_type_id =  rs.getString(4);
                String relationship_type_name =  rs.getString(5);
                String relationship_status =  rs.getString(6);
                String relation_id =  rs.getString(7);
                String memId =  rs.getString(8);
                String tempHorseRelation[] = {firstName, last_name,emailId,relationship_type_id, relationship_type_name, relationship_status, relation_id, memId};
                horseRelList.add(tempHorseRelation);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseRegDAO.selectHorseRelationDetails():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseRegDAO.selectHorseRelationDetails():" + e.getMessage());
        }
        return horseRelList;
    }  
    
    //============================================= Database Connectivity=========================================
    private Connection makeConnection() {
        Debug.print("HorseRegDAO : makeConnection");
        try {
            InitialContext ic = new InitialContext();
            DataSource ds = (DataSource) ic.lookup(dbName);
            con = ds.getConnection();
        } catch (Exception ex) {
            throw new EJBException("Unable to connect to database in HorseRegDAO. " + ex.getMessage());
        }
        return con;
    }
    
    //=============================================Connection Release=========================================
    private void releaseConnection(Connection con) {
        Debug.print("HorseRegDAO releaseConnection");
        try {
            if(!con.isClosed())
                con.close();
        } catch (Exception e) {
            
        }
    }
}

