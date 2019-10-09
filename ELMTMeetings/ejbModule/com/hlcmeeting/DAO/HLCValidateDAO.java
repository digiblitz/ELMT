/*
 * ValidateDAO.java
 *
 * Created on April 12, 2007, 6:25 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmeeting.DAO;
import com.hlccommon.util.HLCCompRegVO;
import com.hlccommon.util.Debug;
import com.hlccommon.util.HLCRelationVO;
import com.hlcmeeting.util.HLCCompResultVO;
import com.hlcmeeting.util.DBHelper;
import java.util.Iterator;
import javax.ejb.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import java.util.ArrayList;
import java.util.Date;


/**
 *
 * @author Suresh
 */
public class HLCValidateDAO {
    private static final String dbName = "java:/ELMTMSSQLDS";
    private Connection con = null;
    private PreparedStatement prepStmt = null;
    private ResultSet rs = null;
    
    /** Creates a new instance of ValidateDAO */
    public HLCValidateDAO() {
    }
    
    //=============================================select a Breed Details =========================================
    public boolean isHorseExist(String horseName){
        Debug.print("ValidateDAO.isHorseExist() horseName:" + horseName);
        PreparedStatement prepStmt = null;
        boolean result = false;
        makeConnection();
        try {
            String selectStatement = "select horse_member_id from tblHorseMemberDetails where competition_name= ?";
            
            Debug.print("Query Log :"+selectStatement);
            
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
            Debug.print("SQL Exception in ValidateDAO.isHorseExist():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in ValidateDAO.isHorseExist():" + e.getMessage());
        }
        return result;
    }
    
    //=============================================select a Breed Details =========================================
    public boolean isHorseMemberExist(String horseMemberId, String horseName){
        Debug.print("ValidateDAO.isHorseMemberExist() breedId:" + horseName);
        PreparedStatement prepStmt = null;
        boolean result = false;
        makeConnection();
        try {
            String selectStatement = "select horse_member_id from tblHorseMemberDetails where competition_name= ? and horse_member_id = ?";
            
            Debug.print("Query Log :"+selectStatement);
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,horseName.replaceAll("'","''"));
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
            Debug.print("SQL Exception in ValidateDAO.isHorseMemberExist():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in ValidateDAO.isHorseMemberExist():" + e.getMessage());
        }
        Debug.print("Result:" + result);
        return result;
    }
    
    //=============================================select a Breed Details =========================================
    public boolean isHorseMemberExistFuzzy(String horseMemberId, String horseName){
        Debug.print("ValidateDAO.isHorseMemberExistFuzzy() horseName:" + horseName);
        PreparedStatement prepStmt = null;
        boolean result = false;
        makeConnection();
        try {
            String selectStatement = "select horse_member_id from tblHorseMemberDetails where horse_member_id = '"+horseMemberId.replaceAll("'","''")+"' and competition_name like '"+horseName+"%'";
            
            Debug.print("Query Log :"+selectStatement);
            
            prepStmt = con.prepareStatement(selectStatement);
            //prepStmt.setString(1,horseName);
            //prepStmt.setString(2,horseMemberId.replaceAll("'","''"));
            ResultSet rs = prepStmt.executeQuery();
            if(rs.next()){
                result = true;
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in ValidateDAO.isHorseMemberExistFuzzy():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in ValidateDAO.isHorseMemberExistFuzzy():" + e.getMessage());
        }
        Debug.print("Result:" + result);
        return result;
    }
    
    //=============================================select a Breed Details =========================================
    public ArrayList getHorseGroupDetails(String horseName){
        Debug.print("ValidateDAO.getHorseGroupDetails() horseName:" + horseName);
        PreparedStatement prepStmt = null;
        ArrayList horseList  = new ArrayList();
        makeConnection();
        try {
            String selectStatement = "select horse_member_id, competition_name from tblHorseMemberDetails where competition_name= ?";
            
            Debug.print("Query Log :"+selectStatement);
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,horseName);
            ResultSet rs = prepStmt.executeQuery();
            while(rs.next()){
                String horseMemberId =  rs.getString(1);
                horseName = rs.getString(2);
                String[] result = {horseMemberId, horseName};
                horseList.add(result);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in ValidateDAO.getHorseGroupDetails():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in ValidateDAO.getHorseGroupDetails():" + e.getMessage());
        }
        return horseList;
    }
    
    //=============================================select a Breed Details =========================================
    public ArrayList getHorseGroupNameDetails(String horseName){
        Debug.print("ValidateDAO.getHorseGroupNameDetails() horseName:" + horseName);
        PreparedStatement prepStmt = null;
        ArrayList horseList  = new ArrayList();
        makeConnection();
        try {
            String selectStatement = "select horse_member_id, competition_name from tblHorseMemberDetails " +
                    " where competition_name like '%" + horseName.replaceAll("'", "''")+ "%'";
            
            Debug.print("Query Log :"+selectStatement);
            
            prepStmt = con.prepareStatement(selectStatement);
            
            ResultSet rs = prepStmt.executeQuery();
            while(rs.next()){
                String horseMemberId =  rs.getString(1);
                horseName = rs.getString(2);
                String[] result = {horseMemberId, horseName};
                horseList.add(result);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in ValidateDAO.getHorseGroupNameDetails():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in ValidateDAO.getHorseGroupNameDetails():" + e.getMessage());
        }
       /* if(horseList.size()>=1){
            String tempAray[] = {"- Other", ""};
            horseList.add(tempAray);
        }
        **/
        return horseList;
    }
    
    
    //=============================================select a Breed Details =========================================
    public ArrayList selectRiderGroupDetails(String firstName, String lastName) {
        Debug.print("ValidateDAO.getRiderGroupDetails() ");
        PreparedStatement prepStmt = null;
        ArrayList riderList  = new ArrayList();
        makeConnection();
        try {
            String selectStatement = "select A.user_id, A.first_name, A.last_name, B.member_id from tblUserMaster A," +
                    " tblMemberDetails B where A.user_id = B.user_id  and  A.first_name like '" + firstName + "%' and " +
                    " A.last_name like '" +  lastName.replaceAll("'" , "''") + "%'";
            
            Debug.print("Query Log :"+selectStatement);
            
            prepStmt = con.prepareStatement(selectStatement);
            
            ResultSet rs = prepStmt.executeQuery();
            while(rs.next()){
                String userId =  rs.getString(1);
                firstName =  rs.getString(2);
                lastName =  rs.getString(3);
                String riderId = rs.getString(4);
                
                String[] result = {firstName, lastName, riderId};
                riderList.add(result);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in ValidateDAO.getRiderGroupDetails():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in ValidateDAO.getRiderGroupDetails():" + e.getMessage());
        }
        return riderList;
    }
    
    //=============================================select a Breed Details =========================================
    public boolean isRiderNameIDExist(String riderId, String firstName, String lastName){
        Debug.print("ValidateDAO.isRiderNameIDExist() ");
        PreparedStatement prepStmt = null;
        boolean result = false;
        makeConnection();
        try {
            String selectStatement = " select A.user_id, A.first_name, A.last_name, B.member_id from tblUserMaster A, tblMemberDetails B" +
                    " where A.user_id = B.user_id  and A.first_name = ? and " +
                    " A.last_name = ? and B.member_id = ?";
            
            Debug.print("Query Log :"+selectStatement);
            
            prepStmt = con.prepareStatement(selectStatement);
            
            prepStmt.setString(1,firstName.replaceAll("'","''"));
            prepStmt.setString(2,lastName.replaceAll("'","''"));
            prepStmt.setString(3,riderId);
            
            ResultSet rs = prepStmt.executeQuery();
            if(rs.next()){
                result = true;
            }
            
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in ValidateDAO.isRiderNameIDExist():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in ValidateDAO.isRiderNameIDExist():" + e.getMessage());
        }
        return result;
    }
    
    //============================================= isRiderNameIDExistFuzzy =========================================
    public boolean isRiderNameIDExistFuzzy(String riderId, String firstName, String lastName){
        Debug.print("ValidateDAO.isRiderNameIDExistFuzzy() ");
        PreparedStatement prepStmt = null;
        boolean result = false;
        makeConnection();
        try {
            String selectStatement = " select A.user_id, A.first_name, A.last_name, B.member_id from tblUserMaster A, tblMemberDetails B" +
                    " where A.user_id = B.user_id and B.member_id = '"+riderId+"' and A.first_name like '"+firstName+"%' and A.last_name = '"+lastName+"'";
           
            Debug.print("Query Log :"+selectStatement);
            
            prepStmt = con.prepareStatement(selectStatement);
            
            //prepStmt.setString(1,firstName);
           // prepStmt.setString(2,lastName);
            //prepStmt.setString(3,riderId);
            
            ResultSet rs = prepStmt.executeQuery();
            if(rs.next()){
                result = true;
            }
            
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in ValidateDAO.isRiderNameIDExistFuzzy():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in ValidateDAO.isRiderNameIDExistFuzzy():" + e.getMessage());
        }
        return result;
    }
    
    //=============================================select a Breed Details =========================================
    public ArrayList selectHorseRelation(String horseName){
        Debug.print("ValidateDAO.selectHorseRelation() ");
        PreparedStatement prepStmt = null;
        ArrayList horseRelationList = new ArrayList();
        makeConnection();
        try {
            String selectStatement = "select A.competition_name, A.horse_member_id, B.first_name, B.last_name, " +
                    " C.member_id, D.user_id from tblHorseMemberDetails A, tblUserMaster B, tblMemberDetails C, " +
                    " tblHorseMemberRelationDetails D, tblHorseRelationshipTypeMaster E " +
                    " where A.horse_member_id = D.horse_member_id and B.user_id = D.user_id and " +
                    " D.user_id = C.user_id and D.relationship_type_id = E.relationship_type_id and " +
                    " E.relationship_type_name = 'Rider' and A.competition_name like '%"+ horseName.replaceAll("'","''") +"%'";
            
            Debug.print(selectStatement);
            
            prepStmt = con.prepareStatement(selectStatement);
            
            ResultSet rs = prepStmt.executeQuery();
            while(rs.next()){
                horseName = rs.getString(1);
                String horseMemberId = rs.getString(2);
                String firstName = rs.getString(3);
                String lastName = rs.getString(4);
                String memberId = rs.getString(5);
                String userId= rs.getString(6);
                
                HLCRelationVO objRelationVO = new HLCRelationVO();
                
                objRelationVO.setHorseName(horseName);
                objRelationVO.setHorseMemberId(horseMemberId);
                objRelationVO.setFirstName(firstName);
                objRelationVO.setLastName(lastName);
                objRelationVO.setMemberId(memberId);
                objRelationVO.setUserId(userId);
                horseRelationList.add(objRelationVO);
            }
            
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in ValidateDAO.selectHorseRelation():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in ValidateDAO.selectHorseRelation():" + e.getMessage());
        }
        return horseRelationList;
    }
    
    //=============================================select a Breed Details =========================================
    public ArrayList selectCompetitionReg(String uploadId){
        Debug.print("ValidateDAO.selectCompetitionReg() ");
        PreparedStatement prepStmt = null;
        ArrayList horseList  = new ArrayList();
        makeConnection();
        try {
            String selectStatement = "select tmp_registration_id, event_id, event_type, " +
                    " event_level, event_division_amateur, event_division_age, event_division_experience, " +
                    " horse_name, horse_member_id, horse_usef_id, rider_first_name, rider_last_name, " +
                    " rider_member_id, rider_usef_id, owner_first_name, " +
                    " owner_last_name, owner_usea_id, owner_usef_id, comp_result_id" +
                    "  from tblMeeTempCompRegistrationDetails where reg_upload_id = ?";
            
            Debug.print("Query Log :"+selectStatement);
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,uploadId);
            ResultSet rs = prepStmt.executeQuery();
            while(rs.next()){
                HLCCompRegVO compRegVO = new HLCCompRegVO();
                String tmpRegistrationId = rs.getString(1);
                String eventId = rs.getString(2);
                String eventType = rs.getString(3);
                String eventLevel = rs.getString(4);
                String eventDivisionAmateur = rs.getString(5);
                String eventDivisionAge = rs.getString(6);
                String eventDivisionExperience = rs.getString(7);
                String horseName = rs.getString(8);
                String horseMemberId = rs.getString(9);
                String horseUsefId = rs.getString(10);
                String riderFirstName = rs.getString(11);
                String riderLastName = rs.getString(12);
                String riderMemberId = rs.getString(13);
                String riderUsefId = rs.getString(14);
                //String horseUsaEq = rs.getString(15);
                // String riderUsaEq = rs.getString(16);
                String ownerFirstName = rs.getString(15);
                String ownerLastName = rs.getString(16);
                String ownerUseaId = rs.getString(17);
                String ownerUsefId = rs.getString(18);
                String compResultId = rs.getString(19);
                
                compRegVO.setTmpRegistrationId(tmpRegistrationId);
                compRegVO.setEventId(eventId);
                compRegVO.setEventType(eventType);
                compRegVO.setEventLevel(eventLevel);
                compRegVO.setEventDivisionAmateur(eventDivisionAmateur);
                compRegVO.setEventDivisionAge(eventDivisionAge);
                compRegVO.setEventDivisionExperience(eventDivisionExperience);
                compRegVO.setHorseName(horseName);
                compRegVO.setHorseMemberId(horseMemberId);
                compRegVO.setHorseUsefId(horseUsefId);
                compRegVO.setRiderFirstName(riderFirstName);
                compRegVO.setRiderLastName(riderLastName);
                compRegVO.setRiderMemberId(riderMemberId);
                compRegVO.setRiderUsefId(riderUsefId);
                //compRegVO.setHorseUsaEq(horseUsaEq);
                //compRegVO.setRiderUsaEq(riderUsaEq);
                compRegVO.setOwnerFirstName(ownerFirstName);
                compRegVO.setOwnerLastName(ownerLastName);
                compRegVO.setOwnerUseaId(ownerUseaId);
                compRegVO.setOwnerUsefId(ownerUsefId);
                compRegVO.setCompResultId(compResultId);
                
                horseList.add(compRegVO);
            }
            
            Debug.print("selectCompetitionReg() size :"+horseList.size());
            
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in ValidateDAO.selectCompetitionReg():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in ValidateDAO.selectCompetitionReg():" + e.getMessage());
        }
        return horseList;
    }
    
    
     //=============================================select a Breed Details =========================================
    
    public ArrayList selectCompetitionResultDetails(String uploadId){
        Debug.print("ValidateDAO.selectCompetitionResultDetails() ");
        PreparedStatement prepStmt = null;
        ArrayList resultList  = new ArrayList();
        makeConnection();
        try {
            String selectStatement = "select tmp_result_id, event_type_id, event_id, event_type," +
                    " event_level, event_division, starters, horse_name, horse_member_id, rider_first_name, rider_last_name," +
                    " rider_member_id, pinney_number, dressage_penalties, dressage_place, xc_phaseD_jump_penalties, xc_phaseD_elapsed_time," +
                    " xc_phaseD_time_penalties, show_jump_time_penalties, show_jump_jump_penalties, to_date_points, to_date_place, dangerous_riding_penalties," +
                    " final_points, final_place, first_inspection, last_inspection, phase_D_inspection, road_and_track_A, road_and_track_C, steeplechase_jump_penalties," +
                    " steeplechase_time_penalties, usea_points, event_sub_division from "+DBHelper.USEA_TEMP_COMP_RESULT+" where upload_id = ?";
            
            Debug.print("Query Log :"+selectStatement);
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,uploadId);
            ResultSet rs = prepStmt.executeQuery();
            while(rs.next()){
                HLCCompResultVO compResVO = new HLCCompResultVO();
                compResVO.setTmp_result_id(rs.getString(1));
                compResVO.setEvent_type_id(rs.getString(2));
                compResVO.setEvent_id(rs.getString(3));
                compResVO.setEvent_type(rs.getString(4));
                compResVO.setEvent_level(rs.getString(5));
                compResVO.setEvent_division(rs.getString(6));
                compResVO.setStarters(rs.getString(7));
                compResVO.setHorse_name(rs.getString(8));
                compResVO.setHorse_member_id(rs.getString(9));
                compResVO.setRider_first_name(rs.getString(10));
                compResVO.setRider_last_name(rs.getString(11));
                compResVO.setRider_member_id(rs.getString(12));
                compResVO.setPinney_number(rs.getString(13));
                compResVO.setDressage_penalties(rs.getString(14));
                compResVO.setDressage_place(rs.getString(15));
                compResVO.setXc_phaseD_jump_penalties(rs.getString(16));
                compResVO.setXc_phaseD_elapsed_time(rs.getString(17));
                compResVO.setXc_phaseD_time_penalties(rs.getString(18));
                compResVO.setShow_jump_time_penalties(rs.getString(19));
                compResVO.setShow_jump_jump_penalties(rs.getString(20));
                compResVO.setTo_date_points(rs.getString(21));
                compResVO.setTo_date_place(rs.getString(22));
                compResVO.setDangerous_riding_penalties(rs.getString(23));                
                compResVO.setFinal_points(rs.getString(24));
                compResVO.setFinal_place(rs.getString(25));                
                compResVO.setFirst_inspection(rs.getString(26));
                compResVO.setLast_inspection(rs.getString(27));                
                compResVO.setPhase_D_inspection(rs.getString(28));
                compResVO.setRoad_and_track_A(rs.getString(29));                
                compResVO.setRoad_and_track_C(rs.getString(30));
                compResVO.setSteeplechase_jump_penalties(rs.getString(31));                
                compResVO.setSteeplechase_time_penalties(rs.getString(32));
                compResVO.setUsea_points(rs.getString(33));
                compResVO.setEvent_sub_division(rs.getString(34));
                
                //String eventDivId = getEventDivisionIdOnName(compResVO.getEvent_division());
                //compResVO.setEvent_division(eventDivId);
                
                resultList.add(compResVO);
            }
            
            Debug.print("selectCompetitionResultDetails() size :"+resultList.size());
            
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in ValidateDAO.selectCompetitionResultDetails():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in ValidateDAO.selectCompetitionResultDetails():" + e.getMessage());
        }
        return resultList;
    }
    
    public String getEventDivisionIdOnName(String eventDivName) throws SQLException
    {
                
        Debug.print("ValidateDAO.getEventDivisionIdOnName() :");
        Debug.print("eventDivName :"+eventDivName);
        
        PreparedStatement prepStmt = null;
        String eventDivId = null;
        
        makeConnection();
        try {
            String selectStatement = "select event_division_id from "+DBHelper.USEA_EVENT_DIVISION_MASTER+" where event_division_name = ?";
            
            Debug.print("Query Log :"+selectStatement);
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,eventDivName);
            ResultSet rs = prepStmt.executeQuery();
            
            while(rs.next()){               
                
                eventDivId = rs.getString(1);
            }
            
            Debug.print("eventDivId :"+eventDivId);
            
            rs.close();
            prepStmt.close();
            
            
        }
        catch(SQLException e)
        {
            Debug.print("SQLException in ValidateDAO.getEventDivisionIdOnName() :");
            e.printStackTrace();
        }
        catch(Exception e)
        {
            Debug.print("SQLException in ValidateDAO.getEventDivisionIdOnName() :");
            e.printStackTrace();
        }
        finally
        {
            releaseConnection(con);
        }
        
        return eventDivId;
    }
    
    //=============================================select a Breed Details =========================================
    public boolean isRiderExist(String riderId){
        Debug.print("ValidateDAO.isRiderExist() riderId:" + riderId);
        PreparedStatement prepStmt = null;
        boolean result = false;
        makeConnection();
        try {
            String selectStatement = "select user_id from tblMemberDetails " +
                    "where member_id= ? ";
            
            Debug.print("Query Log :"+selectStatement);
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,riderId);
            
            ResultSet rs = prepStmt.executeQuery();
            if(rs.next()){
                result = true;
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in ValidateDAO.isRiderExist():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in ValidateDAO.isRiderExist():" + e.getMessage());
        }
        return result;
    }
    
    public boolean insertHorseCompRegData(HLCCompRegVO compRegVO) throws SQLException {
        Debug.print("ValidateDAO.insertHorseCompRegData()");
        boolean result = false;
        try {
            makeConnection();
            String insertStatement =
                    "insert into tblMeeCompRegistrationDetails " +
                    " (event_id, event_type, event_level, event_division_amateur, event_division_age," +
                    " event_division_experience, horse_name, horse_member_id, horse_usef_id, rider_first_name, " +
                    " rider_last_name, rider_member_id, rider_usef_id, owner_first_name," +
                    " owner_last_name, owner_usea_id, owner_usef_id, comp_result_id, exception_status, tmp_registration_id, reg_upload_id)" +
                    " values( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? ,? , ? , ? , ? , ? , ? , ?, ?, ?) ";
            
            Debug.print("Query Log :"+insertStatement);
            prepStmt = con.prepareStatement(insertStatement);
            
            prepStmt.setString(1,compRegVO.getEventId());
            prepStmt.setString(2,compRegVO.getEventType());
            prepStmt.setString(3,compRegVO.getEventLevel());
            prepStmt.setString(4,compRegVO.getEventDivisionAmateur());
            prepStmt.setString(5,compRegVO.getEventDivisionAge());
            prepStmt.setString(6,compRegVO.getEventDivisionExperience());
            prepStmt.setString(7,compRegVO.getHorseName());
            prepStmt.setString(8,compRegVO.getHorseMemberId());
            prepStmt.setString(9,compRegVO.getHorseUsefId());
            prepStmt.setString(10,compRegVO.getRiderFirstName());
            prepStmt.setString(11,compRegVO.getRiderLastName());
            prepStmt.setString(12,compRegVO.getRiderMemberId());
            prepStmt.setString(13,compRegVO.getRiderUsefId());
            prepStmt.setString(14,compRegVO.getOwnerFirstName());
            prepStmt.setString(15,compRegVO.getOwnerLastName());
            prepStmt.setString(16,compRegVO.getOwnerUseaId());
            prepStmt.setString(17,compRegVO.getOwnerUsefId());
            prepStmt.setString(18,compRegVO.getCompResultId());
            prepStmt.setBoolean(19,compRegVO.isExceptionStatus());
            prepStmt.setString(20,compRegVO.getTmpRegistrationId());
            prepStmt.setString(21,compRegVO.getRegUploadId());
            
            
            int cnt = prepStmt.executeUpdate();
            
            if(cnt>=1) result = true;
            prepStmt.close();
            releaseConnection(con);
            
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in ValidateDAO.insertHorseCompRegData():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in ValidateDAO.insertHorseCompRegData():" + e.getMessage());
        }
        return result;
    }
    
    public boolean insertHorseCompResultData(HLCCompResultVO compResVO) throws SQLException {
        Debug.print("ValidateDAO.insertHorseCompResultData()");
        boolean result = false;
        String tmpnull="NULL";
        try {
            
            //String eventDivId = getEventDivisionIdOnName(compResVO.getEvent_division());
            
            makeConnection();
            String insertStatement = "INSERT INTO "+DBHelper.USEA_COMP_RESULT_DETAIL+" (tmp_result_id ,event_id ,event_type_id ,pinney_number ,dressage_penalties ,dressage_place ,"+
                                     "xc_phaseD_jump_penalties ,xc_phaseD_elapsed_time ,xc_phaseD_time_penalties ,show_jump_time_penalties ,show_jump_jump_penalties ,to_date_points ,"+
                                     "to_date_place ,dangerous_riding_penalties ,final_points ,final_place ,first_inspection ,last_inspection ,phase_D_inspection ,road_and_track_A ,"+
                                     "road_and_track_C ,steeplechase_jump_penalties ,steeplechase_time_penalties ,usea_points ,exception_status, event_division_id, event_sub_division, " +
                                     "horse_name, horse_member_id, rider_first_name, rider_last_name, rider_member_id, upload_id, event_level_id) VALUES "+
                                     "(  ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? ,?) ";
                    
            Debug.print("Query Log :"+insertStatement);
            prepStmt = con.prepareStatement(insertStatement);
            
            prepStmt.setString(1,compResVO.getTmp_result_id());
            prepStmt.setString(2,compResVO.getEvent_id());
            prepStmt.setString(3,compResVO.getEvent_type_id());    
            prepStmt.setString(4,compResVO.getPinney_number());           
            Debug.print("compResVO.getPinney_number() in DAO"+compResVO.getPinney_number());  
            
            String dressPenaltyTwoDigit="";
            String dressPenalty=compResVO.getDressage_penalties();
            if((dressPenalty.indexOf('.'))!=-1){
            int indexValue=dressPenalty.indexOf('.');
            String valAfterDecimal = (dressPenalty.substring(indexValue,dressPenalty.length()));
            int lengthAftDec=valAfterDecimal.length();
            if(lengthAftDec>=3){
            dressPenaltyTwoDigit = (dressPenalty.substring(0,indexValue+3));
            prepStmt.setString(5,dressPenaltyTwoDigit);
            }
            else{
            prepStmt.setString(5,dressPenalty);    
            }
            }else{
            prepStmt.setString(5,dressPenalty);        
            }
            Debug.print("dressPenalty in DAO:"+dressPenalty);
            Debug.print("dressPenaltyTwoDigit in DAO:"+dressPenaltyTwoDigit);
            prepStmt.setString(6,compResVO.getDressage_place());
            
            String xcPhaseDJumpPenalty=compResVO.getXc_phaseD_jump_penalties();
            if((xcPhaseDJumpPenalty.indexOf('.'))!=-1){
            int indexValue1=xcPhaseDJumpPenalty.indexOf('.');
            String xcPhaseDJumpPenaltyNoDigit = (xcPhaseDJumpPenalty.substring(0,indexValue1));
            prepStmt.setString(7,xcPhaseDJumpPenaltyNoDigit);
            }else{
            prepStmt.setString(7,xcPhaseDJumpPenalty);    
            }
            
            prepStmt.setString(8,compResVO.getXc_phaseD_elapsed_time());
            
            
            String xcPhaseDTimePenalty=compResVO.getXc_phaseD_time_penalties();
            if((xcPhaseDTimePenalty.indexOf('.'))!=-1){
            int indexValue2=xcPhaseDTimePenalty.indexOf('.');
            String valAfterDecimal = (xcPhaseDTimePenalty.substring(indexValue2,xcPhaseDTimePenalty.length()));
            int lengthAftDec=valAfterDecimal.length();
            if(lengthAftDec>=3){
            String xcPhaseDTimePenaltyTwoDigit = (xcPhaseDTimePenalty.substring(0,indexValue2+3));
            prepStmt.setString(9,xcPhaseDTimePenaltyTwoDigit);
            }
            else{
            prepStmt.setString(9,xcPhaseDTimePenalty);    
            }
            }else{
            prepStmt.setString(9,xcPhaseDTimePenalty);    
            }
            
            String showJumpTimePenalty=compResVO.getShow_jump_time_penalties();
            if((showJumpTimePenalty.indexOf('.'))!=-1){
            int indexValue3=showJumpTimePenalty.indexOf('.');
            String valAfterDecimal = (showJumpTimePenalty.substring(indexValue3,showJumpTimePenalty.length()));
            int lengthAftDec=valAfterDecimal.length();
            if(lengthAftDec>=3){
            String showJumpTimePenaltyTwoDigit = (showJumpTimePenalty.substring(0,indexValue3+3));
            prepStmt.setString(10,showJumpTimePenaltyTwoDigit);
            }
            else{
            prepStmt.setString(10,showJumpTimePenalty);    
            }
            }else{
            prepStmt.setString(10,showJumpTimePenalty);    
            }
            
            String showJumpJumpPenalty=compResVO.getShow_jump_jump_penalties();
            if((showJumpJumpPenalty.indexOf('.'))!=-1){
            int indexValue4=showJumpJumpPenalty.indexOf('.');
            String showJumpJumpPenaltyNoDigit = (showJumpJumpPenalty.substring(0,indexValue4));
            prepStmt.setString(11,showJumpJumpPenaltyNoDigit);
            }else{
            prepStmt.setString(11,showJumpJumpPenalty);    
            }
            prepStmt.setString(12,compResVO.getTo_date_points());
            prepStmt.setString(13,compResVO.getTo_date_place());
            
            String danRidingPenalty=compResVO.getDangerous_riding_penalties();
            if((danRidingPenalty.indexOf('.'))!=-1){
            int indexValue5=danRidingPenalty.indexOf('.');
            String danRidingPenaltyNoDigit = (danRidingPenalty.substring(0,indexValue5));
            prepStmt.setString(14,danRidingPenaltyNoDigit);
            }else{
            prepStmt.setString(14,danRidingPenalty);    
            }
           
            String finalPoints=compResVO.getFinal_points();
            if((finalPoints.indexOf('.'))!=-1){
            int indexValue6=finalPoints.indexOf('.');
            String valAfterDecimal = (finalPoints.substring(indexValue6,finalPoints.length()));
            int lengthAftDec=valAfterDecimal.length();
            if(lengthAftDec>=3){
            String finalPointsTwoDigit = (finalPoints.substring(0,indexValue6+3));
            prepStmt.setString(15,finalPointsTwoDigit);
            }
            else{
            prepStmt.setString(15,finalPoints);    
            }
            }else{
            prepStmt.setString(15,finalPoints);    
            }
            
            String finalPlace=compResVO.getFinal_place();
            if((finalPlace.indexOf('.'))!=-1){
            int indexValue7=finalPlace.indexOf('.');
            String finalPlaceNoDigit = (finalPlace.substring(0,indexValue7));
            prepStmt.setString(16,finalPlaceNoDigit);
            }else{
            prepStmt.setString(16,finalPlace);    
            }
 
            prepStmt.setString(17,compResVO.getFirst_inspection());
            //Debug.print("compResVO.getFirst_inspection() in DAO"+compResVO.getFirst_inspection());  
            prepStmt.setString(18,compResVO.getLast_inspection());
            //Debug.print("compResVO.getLast_inspection() in DAO"+compResVO.getLast_inspection());
            prepStmt.setString(19,compResVO.getPhase_D_inspection()); 
             //Debug.print("compResVO.getPhase_D_inspection() in DAO"+compResVO.getPhase_D_inspection());
            prepStmt.setString(20,compResVO.getRoad_and_track_A());
             //Debug.print("compResVO.getRoad_and_track_A() in DAO"+compResVO.getRoad_and_track_A());
            prepStmt.setString(21,compResVO.getRoad_and_track_C());
             //Debug.print("compResVO.getRoad_and_track_C() in DAO"+compResVO.getRoad_and_track_C());
          
 
            String steepleChaseJumpPenalty=compResVO.getSteeplechase_jump_penalties();
            if((steepleChaseJumpPenalty.indexOf('.'))!=-1){
            int indexValue8=steepleChaseJumpPenalty.indexOf('.');
            String steepleChaseJumpPenaltyNoDigit = (steepleChaseJumpPenalty.substring(0,indexValue8));
            prepStmt.setString(22,steepleChaseJumpPenaltyNoDigit);
            }else{
            prepStmt.setString(22,steepleChaseJumpPenalty);    
            }
            
            String steepleChaseTimePenalty=compResVO.getSteeplechase_time_penalties();
            if((steepleChaseTimePenalty.indexOf('.'))!=-1){
            int indexValue9=steepleChaseTimePenalty.indexOf('.');
            String valAfterDecimal = (steepleChaseTimePenalty.substring(indexValue9,steepleChaseTimePenalty.length()));
            int lengthAftDec=valAfterDecimal.length();
            if(lengthAftDec>=3){
            String steepleChaseTimePenaltyTwoDigit = (steepleChaseTimePenalty.substring(0,indexValue9+3));
            prepStmt.setString(23,steepleChaseTimePenaltyTwoDigit);
            }
            else{
            prepStmt.setString(23,steepleChaseTimePenalty);    
            }
            }else{
            prepStmt.setString(23,steepleChaseTimePenalty);    
            }
            
            String useaPoints=compResVO.getUsea_points();
            if((useaPoints.indexOf('.'))!=-1){
            int indexValue10=useaPoints.indexOf('.');
            String valAfterDecimal = (useaPoints.substring(indexValue10,useaPoints.length()));
            int lengthAftDec=valAfterDecimal.length();
            if(lengthAftDec>=2){
            String useaPointsOneDigit = (useaPoints.substring(0,indexValue10+2));
            prepStmt.setString(24,useaPointsOneDigit);
            }
            else{
            prepStmt.setString(24,useaPoints);    
            }
            }else{
            prepStmt.setString(24,useaPoints);    
            }
            
            prepStmt.setBoolean(25,compResVO.isExceptionStatus());            
            prepStmt.setString(26,compResVO.getEventDivId());
            prepStmt.setString(27,compResVO.getEvent_sub_division());
            
            prepStmt.setString(28,compResVO.getHorse_name());
            prepStmt.setString(29,compResVO.getHorse_member_id());
            prepStmt.setString(30,compResVO.getRider_first_name());
            prepStmt.setString(31,compResVO.getRider_last_name());
            prepStmt.setString(32,compResVO.getRider_member_id());
            prepStmt.setString(33,compResVO.getUpload_id());
            prepStmt.setString(34,compResVO.getEventLevelId());
           
            
            int cnt = prepStmt.executeUpdate();
            Debug.print("insert record cnt :"+cnt);
            
            if(cnt>=1) result = true;
            prepStmt.close();
            releaseConnection(con);
            
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in ValidateDAO.insertHorseCompResultData():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in ValidateDAO.insertHorseCompResultData():" + e.getMessage());
        }
        return result;
    }
    
    public boolean insertInvalidHorseCompResult(HLCCompResultVO compResVO) throws SQLException {
        Debug.print("ValidateDAO.insertInvalidHorseCompResult()");
        boolean result = false;
        
        try {
            
            makeConnection();
            
            String insertStatement = "INSERT INTO "+DBHelper.USEA_COMP_EXCPT_RESULT_DETAIL+" (tmp_result_id ,event_id ,event_type_id ,pinney_number ,dressage_penalties ,dressage_place ,"+
                                     "xc_phaseD_jump_penalties ,xc_phaseD_elapsed_time ,xc_phaseD_time_penalties ,show_jump_time_penalties ,show_jump_jump_penalties ,to_date_points ,"+
                                     "to_date_place ,dangerous_riding_penalties ,final_points ,final_place ,first_inspection ,last_inspection ,phase_D_inspection ,road_and_track_A ,"+
                                     "road_and_track_C ,steeplechase_jump_penalties ,steeplechase_time_penalties ,usea_points ,event_type ,event_level ,event_division ,starters ,"+
                                     "horse_name ,horse_member_id ,rider_first_name ,rider_last_name ,rider_member_id, event_sub_division) VALUES "+
                                     "( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? ) ";
                    
            Debug.print("Query Log :"+insertStatement);
            prepStmt = con.prepareStatement(insertStatement);
            
            prepStmt.setString(1,compResVO.getTmp_result_id());
            prepStmt.setString(2,compResVO.getEvent_id());
            prepStmt.setString(3,compResVO.getEvent_type_id());
            prepStmt.setString(4,compResVO.getPinney_number());
            prepStmt.setString(5,compResVO.getDressage_penalties());
            prepStmt.setString(6,compResVO.getDressage_place());
            prepStmt.setString(7,compResVO.getXc_phaseD_jump_penalties());
            prepStmt.setString(8,compResVO.getXc_phaseD_elapsed_time());
            prepStmt.setString(9,compResVO.getXc_phaseD_time_penalties());
            prepStmt.setString(10,compResVO.getShow_jump_time_penalties());
            prepStmt.setString(11,compResVO.getShow_jump_jump_penalties());
            prepStmt.setString(12,compResVO.getTo_date_points());
            prepStmt.setString(13,compResVO.getTo_date_place());
            prepStmt.setString(14,compResVO.getDangerous_riding_penalties());
            prepStmt.setString(15,compResVO.getFinal_points());
            prepStmt.setString(16,compResVO.getFinal_place());
            prepStmt.setString(17,compResVO.getFirst_inspection());
            prepStmt.setString(18,compResVO.getLast_inspection());
            prepStmt.setString(19,compResVO.getPhase_D_inspection());
            prepStmt.setString(20,compResVO.getRoad_and_track_A());            
            prepStmt.setString(21,compResVO.getRoad_and_track_C());
            prepStmt.setString(22,compResVO.getSteeplechase_jump_penalties());
            prepStmt.setString(23,compResVO.getSteeplechase_time_penalties());
            prepStmt.setString(24,compResVO.getUsea_points());
            prepStmt.setString(25,compResVO.getEvent_type());
            
            prepStmt.setString(26,compResVO.getEvent_level());
            prepStmt.setString(27,compResVO.getEvent_division());
            prepStmt.setString(28,compResVO.getStarters());
            prepStmt.setString(29,compResVO.getHorse_name());
            prepStmt.setString(30,compResVO.getHorse_member_id());
            prepStmt.setString(31,compResVO.getRider_first_name());
            prepStmt.setString(32,compResVO.getRider_last_name());
            prepStmt.setString(33,compResVO.getRider_member_id());            
            prepStmt.setString(34,compResVO.getEvent_sub_division());
            
            int cnt = prepStmt.executeUpdate();
            
            if(cnt>=1) result = true;
            prepStmt.close();
            releaseConnection(con);
            Debug.print("cnt :"+cnt);
            
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in ValidateDAO.insertInvalidHorseCompResult():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in ValidateDAO.insertInvalidHorseCompResult():" + e.getMessage());
        }
        return result;
    }
    
    //=============================================select a Breed Details =========================================
    public boolean updateValidationStatus(String regUploadId){
        Debug.print("ValidateDAO.updateValidationStatus() regUploadId:" + regUploadId);
        PreparedStatement prepStmt = null;
        boolean result = false;
        makeConnection();
        try {
            String selectStatement = "update tblMeeCompRegistrationFileDetails set validation_status = ? " +
                    " where reg_upload_id= ? ";
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setBoolean(1,true);
            prepStmt.setString(2,regUploadId);
            
            int cnt = prepStmt.executeUpdate();
            
            if(cnt>=1){
                result = true;
            }
            
            Debug.print("Result:" + result);
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in ValidateDAO.updateValidationStatus():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in ValidateDAO.updateValidationStatus():" + e.getMessage());
        }
        return result;
    }
    
     //=============================================select a Breed Details =========================================
    public boolean updateResultValidationStatus(String uploadId){
        Debug.print("ValidateDAO.updateResultValidationStatus() uploadId :" + uploadId);
        PreparedStatement prepStmt = null;
        boolean result = false;
        makeConnection();
        try {
            String selectStatement = "update "+DBHelper.USEA_COMP_RESULT_FILE_DETAILS+" set validation_status = ? " +
                    "where upload_id = ? ";
            Debug.print("selectStatement :"+selectStatement);
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setBoolean(1,true);
            prepStmt.setString(2,uploadId);
            
            int cnt = prepStmt.executeUpdate();
            
            if(cnt>=1){
                result = true;
            }
            
            Debug.print("cnt :" + cnt);
            Debug.print("Result:" + result);
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in ValidateDAO.updateResultValidationStatus():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in ValidateDAO.updateResultValidationStatus():" + e.getMessage());
        }
        return result;
    }
    
  //******************************Competition Result Upload Starter Count*************************************************************
    
    public ArrayList getCompResultStarterCount(String eventId, String uploadId) throws SQLException {
        Debug.print("ValidateDAO.getCompResultStarterCount()");
        ArrayList starterList = new ArrayList();
        try {      
            makeConnection();
            
          String selStatement = "SELECT TOP (100) PERCENT dbo.tblMeeCompResultFileDetails.event_id, " +
                  "dbo.tblMeeEventDetails.event_title, dbo.tblMeeEventDetails.event_begin_date, " +
                  "COUNT(dbo.tblMeeTempCompResults.tmp_result_id) AS Start_Count, " +
                  "CASE WHEN dbo.tblMeeTempCompResults.event_sub_division <> NULL " +
                  "THEN dbo.tblMeeTempCompResults.event_division + dbo.tblMeeTempCompResults.event_sub_division ELSE " +
                  "dbo.tblMeeTempCompResults.event_division END AS Div, dbo.tblMeeTempCompResults.event_division, " +
                  "dbo.tblMeeTempCompResults.event_sub_division, " +
                  "CASE WHEN dbo.tblMeeTempCompResults.event_division is NULL OR dbo.tblMeeTempCompResults.event_division = '' " +
                  "THEN dbo.tblMeeTempCompResults.event_level + dbo.tblMeeTempCompResults.event_sub_division " +
                  "ELSE dbo.tblMeeTempCompResults.event_level + dbo.tblMeeTempCompResults.event_division + dbo.tblMeeTempCompResults.event_sub_division END AS merged_division, " +
                  "dbo.tblMeeTempCompResults.event_level FROM dbo.tblMeeCompResultFileDetails INNER JOIN dbo.tblMeeEventTypeMaster ON " +
                  "dbo.tblMeeCompResultFileDetails.event_type_id = " +
                  "dbo.tblMeeEventTypeMaster.event_type_id INNER JOIN " +
                  "dbo.tblMeeEventDetails ON " +
                  "dbo.tblMeeCompResultFileDetails.event_id = " +
                  "dbo.tblMeeEventDetails.event_id INNER JOIN " +
                  "dbo.tblMeeTempCompResults ON " +
                  "dbo.tblMeeCompResultFileDetails.upload_id = " +
                  "dbo.tblMeeTempCompResults.upload_id " +
                  "WHERE (dbo.tblMeeTempCompResults.dressage_penalties NOT IN ('W', 'WD', 'S')) and " +
                  "dbo.tblMeeTempCompResults.event_id = ? and dbo.tblMeeTempCompResults.upload_id = ? " +
                  "GROUP BY dbo.tblMeeCompResultFileDetails.event_id, dbo.tblMeeEventDetails.event_title, " +
                  "dbo.tblMeeEventDetails.event_begin_date, (CASE WHEN " +
                  "dbo.tblMeeTempCompResults.event_sub_division <> NULL " +
                  "THEN dbo.tblMeeTempCompResults.event_division + dbo.tblMeeTempCompResults.event_sub_division ELSE " +
                  "dbo.tblMeeTempCompResults.event_division END), dbo.tblMeeTempCompResults.event_division, " +
                  "dbo.tblMeeTempCompResults.event_sub_division, dbo.tblMeeTempCompResults.event_level order by dbo.tblMeeTempCompResults.event_division, " +
                  "dbo.tblMeeTempCompResults.event_sub_division";
                    
            //Debug.print("Query Log :"+selStatement);
            prepStmt = con.prepareStatement(selStatement);
            
            prepStmt.setString(1, eventId);
            prepStmt.setString(2, uploadId);
            
            rs = prepStmt.executeQuery();            
            String starterCnt = "";
            String mergDiv="";
            String eveDiv="";
            String eveSubDiv="";
            String eveLevel="";
            int i=0;
            while(rs.next()){
                starterCnt = rs.getString(4);
                eveDiv = rs.getString(6);
                eveSubDiv = rs.getString(7);
                mergDiv = rs.getString(8);
                eveLevel = rs.getString(9);
                starterList.add(starterCnt);
                starterList.add(eveDiv);
                starterList.add(eveSubDiv);
                starterList.add(mergDiv);
                starterList.add(eveLevel);
                /*Debug.print("starterCnt in Loop getCompResultStarterCount:" + starterCnt);
                Debug.print("eveDiv in Loop getCompResultStarterCount:" + eveDiv);
                Debug.print("eveSubDiv in Loop getCompResultStarterCount:" + eveSubDiv);
                Debug.print("mergDiv in Loop getCompResultStarterCount:" + mergDiv);
                Debug.print("eveLevel in Loop getCompResultStarterCount:" + eveLevel);
                Debug.print("starterList in Loop getCompResultStarterCount:" + starterList);*/
                i++;               
                }
                rs.close();
                prepStmt.close();
                releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in ValidateDAO.getCompResultStarterCount():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in ValidateDAO.getCompResultStarterCount():" + e.getMessage());
        }
        return starterList;
    }
    
    
//******************************Competition Result Upload:-TempResultIds*************************************************************
    
    public ArrayList getCompResultTempResIds(String eventId, String mergDiv, String uploadId) throws SQLException {
        Debug.print("ValidateDAO.getCompResultTempResIds()");
        ArrayList starterDetList = new ArrayList();
        try {      
            makeConnection();
                 
                String selStatement1 = "SELECT TOP (100) PERCENT dbo.tblMeeCompResultFileDetails.event_id, " +
                  "dbo.tblMeeTempCompResults.tmp_result_id, dbo.tblMeeEventDetails.event_title, dbo.tblMeeEventDetails.event_begin_date, " +
                  "COUNT(dbo.tblMeeTempCompResults.tmp_result_id) AS Start_Count, " +
                  "CASE WHEN dbo.tblMeeTempCompResults.event_sub_division <> NULL " +
                  "THEN dbo.tblMeeTempCompResults.event_division + dbo.tblMeeTempCompResults.event_sub_division ELSE " +
                  "dbo.tblMeeTempCompResults.event_division END AS Div, dbo.tblMeeTempCompResults.event_division, " +
                  "dbo.tblMeeTempCompResults.event_sub_division, " +
                  "CASE WHEN dbo.tblMeeTempCompResults.event_division is NULL OR dbo.tblMeeTempCompResults.event_division = '' " +
                  "THEN dbo.tblMeeTempCompResults.event_level + dbo.tblMeeTempCompResults.event_sub_division " +
                  "ELSE dbo.tblMeeTempCompResults.event_level + dbo.tblMeeTempCompResults.event_division + dbo.tblMeeTempCompResults.event_sub_division END AS merged_division, " +
                  "dbo.tblMeeTempCompResults.event_level FROM dbo.tblMeeCompResultFileDetails INNER JOIN dbo.tblMeeEventTypeMaster ON " +
                  "dbo.tblMeeCompResultFileDetails.event_type_id = " +
                  "dbo.tblMeeEventTypeMaster.event_type_id INNER JOIN " +
                  "dbo.tblMeeEventDetails ON " +
                  "dbo.tblMeeCompResultFileDetails.event_id = " +
                  "dbo.tblMeeEventDetails.event_id INNER JOIN " +
                  "dbo.tblMeeTempCompResults ON " +
                  "dbo.tblMeeCompResultFileDetails.upload_id = " +
                  "dbo.tblMeeTempCompResults.upload_id " +
                  "WHERE (dbo.tblMeeTempCompResults.dressage_penalties NOT IN ('W', 'WD', 'S')) and " +
                  "dbo.tblMeeTempCompResults.event_id = ? and " +
                  "CASE WHEN dbo.tblMeeTempCompResults.event_division is NULL OR dbo.tblMeeTempCompResults.event_division = '' " +
                  "THEN dbo.tblMeeTempCompResults.event_level + dbo.tblMeeTempCompResults.event_sub_division " +
                  "ELSE dbo.tblMeeTempCompResults.event_level + dbo.tblMeeTempCompResults.event_division + dbo.tblMeeTempCompResults.event_sub_division END = ? " + 
                  "and dbo.tblMeeTempCompResults.upload_id = ? " +
                  "GROUP BY dbo.tblMeeCompResultFileDetails.event_id, dbo.tblMeeTempCompResults.tmp_result_id, dbo.tblMeeEventDetails.event_title, " +
                  "dbo.tblMeeEventDetails.event_begin_date, (CASE WHEN " +
                  "dbo.tblMeeTempCompResults.event_sub_division <> NULL " +
                  "THEN dbo.tblMeeTempCompResults.event_division + dbo.tblMeeTempCompResults.event_sub_division ELSE " +
                  "dbo.tblMeeTempCompResults.event_division END), dbo.tblMeeTempCompResults.event_division, " +
                  "dbo.tblMeeTempCompResults.event_sub_division, dbo.tblMeeTempCompResults.event_level order by dbo.tblMeeTempCompResults.event_division, " +
                  "dbo.tblMeeTempCompResults.event_sub_division";
                
            Debug.print("Query Log :"+selStatement1);
            prepStmt = con.prepareStatement(selStatement1);
            
            prepStmt.setString(1, eventId);
            prepStmt.setString(2, mergDiv);
            prepStmt.setString(3, uploadId);
            rs = prepStmt.executeQuery();                      
            String tempResId="";          
            int i=0;
            while(rs.next()){
                tempResId = rs.getString(2);                
                starterDetList.add(tempResId);                               
                //Debug.print("tempResId in Loop getCompResultTempResIds:" + tempResId);                
               
                i++;               
                }
                rs.close();
                prepStmt.close();
                releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in ValidateDAO.getCompResultTempResIds():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in ValidateDAO.getCompResultTempResIds():" + e.getMessage());
        }
        return starterDetList;
    }
  
//************************************Competition Result Upload:-ResultGroupId********************************************************    
    
   public String getCompResultGroupId(String eventId, ArrayList eveTempDets, String StarterCnt, String eveDivId, String eveSubDiv, String eveTypeId, String uploadId, String eventLevelId) throws SQLException {
        Debug.print("ValidateDAO.getCompResultGroupId()");
            
        String resId="";
        String starterCnt="";
        String eveDivision="";
        String eveSubDivision="";
        String groupId="";
        String tmpRes="";
        String tmpStartCnt="";
        String tmpeveDiv="";
        ArrayList tmpResDets=new ArrayList();
        int i=0;
        boolean result=false;        
         if(eveTempDets!=null && eveTempDets.size()!=0){
          //Debug.print("ValidateDAO.eveTempDets()"+eveTempDets);
          Debug.print("ValidateDAO.eveTempDets()"+eveTempDets.size());                        
            Iterator itr =eveTempDets.iterator();
            while(itr.hasNext()){
                resId =(String)itr.next();                                                                  
                 String[] tmpEveResStartDet = {resId};
                 tmpResDets.add(tmpEveResStartDet);    
              i++;
         }
            if(tmpResDets!=null){           
            try{
            groupId = getNextId();
            Debug.print("groupId:" + groupId);
            } catch(Exception e){
            Debug.print("Exception while calling getNextId():" + e.getMessage());
            }
            }
        
            if(groupId!=null){
            try {      
            makeConnection();      
                
                 String insertStatement = "insert into tblMeeCompGroupResultDetails " +
                    " (result_group_id, event_id, starters, event_division_id, event_sub_division, event_type_id, upload_id, event_level_id)" +
                    " values ( ?, ?, ?, ?, ?, ?, ?, ?) ";
                 
            PreparedStatement prepStmt = con.prepareStatement(insertStatement);            
            prepStmt.setString(1, groupId);
            prepStmt.setString(2, eventId);                        
            prepStmt.setString(3, StarterCnt);
            prepStmt.setString(4, eveDivId);
            prepStmt.setString(5, eveSubDiv);
            prepStmt.setString(6, eveTypeId);
            prepStmt.setString(7, uploadId);
            prepStmt.setString(8, eventLevelId);
            
            int cnt = prepStmt.executeUpdate();
           Debug.print("Record Inserted succefully into getCompResultGroupId() "+cnt);
           if(cnt >=1) result = true;
            Debug.print("ValidateDAO getCompResultGroupId() Status :" + result);
            prepStmt.close();
            releaseConnection(con); 
            }
            catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in ValidateDAO.getCompResultGroupId():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            e.printStackTrace();
        }  
            }
         }
        return groupId;
    }
 
 
   public String getNextId() throws CreateException {
        Debug.print("ValidateDAO groupId getNextId ");
        String nextGroupId = "";
        try {
            nextGroupId = retrieveNextId();
        }
        catch(Exception e){
            Debug.print("Exception in getNextId:" + e);
        }
        return nextGroupId;
     }        
   
    public String retrieveNextId() throws Exception {
        Debug.print("ValidateDAO getNextGroupId");
        makeConnection();
        String nextId = null;
        try{
            String selectStatement = "select newid() as userId";
            //Debug.print("ValidateDAO getNextGroupId:" + selectStatement);
            PreparedStatement prepSelect = con.prepareStatement(selectStatement);
            ResultSet rs = prepSelect.executeQuery();
            rs.next();
            nextId = rs.getString(1);
            Debug.print("nextId:" + nextId);
            rs.close();
            prepSelect.close();
            releaseConnection(con);
        }
        catch(SQLException sql){
            releaseConnection(con);
            throw new EJBException("SQL Exception in ValidateDAO getNextUserId:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            throw new EJBException("General Exception  in ValidateDAO getNextUserId:" + e.getMessage());
        }
        return nextId;
    } 
    
     public boolean updateCompResultGrpDets(String eventId, String resId, String groupId){
        //Debug.print("ValidateDAO.updateCompResultGrpDets() eventId :" + eventId);
        //Debug.print("ValidateDAO.updateCompResultGrpDets() resId :" + resId);
        Debug.print("ValidateDAO.updateCompResultGrpDets() groupId :" + groupId);
        PreparedStatement prepStmt = null;
        boolean result = false;
        makeConnection();

        try {
            String selectStatement = "update tblMeeCompResultDetails set result_group_id = ? " +
                    "where event_id = ? and tmp_result_id = ? ";
            
            //Debug.print("selectStatement :"+selectStatement);            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,groupId);
            prepStmt.setString(2,eventId);
            prepStmt.setString(3,resId);            
            int cnt = prepStmt.executeUpdate();            
            if(cnt>=1){
                result = true;
            }
            
            Debug.print("cnt :" + cnt);
            Debug.print("Result:" + result);
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in ValidateDAO.updateCompResultGrpDets():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in ValidateDAO.updateCompResultGrpDets():" + e.getMessage());
        }
        return result;
    } 

    public String getEventDivisionId(String eventDivisionName) {
            Debug.print("ValidateDAO.getEventDivisionId():");
            String eveDivisionId="";
            PreparedStatement prepStmt = null;
            
            makeConnection();
            try {
            String selectStatement = "select event_division_id from tblMeeEventDivisionMaster " +
                    "where event_division_name = ? "; 
            
            //Debug.print("selectStatement :"+selectStatement);            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,eventDivisionName);
                 
            ResultSet rs = prepStmt.executeQuery();
            rs.next();
            eveDivisionId = rs.getString(1);                     
            //Debug.print("eveDivisionId:" + eveDivisionId);
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in ValidateDAO.getEventDivisionId():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in ValidateDAO.getEventDivisionId():" + e.getMessage());
        }
        return eveDivisionId;
    }  

 public String getEventLevelId(String eventLevelName) {
            Debug.print("ValidateDAO.getEventLevelId():");
            String eveLevelId="";
            PreparedStatement prepStmt = null;
            
            makeConnection();
            try {
            String selectStatement = "select event_level_id from tblMeeEventLevelMaster where event_level_code = ? "; 
            
            Debug.print("selectStatement :"+selectStatement);            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,eventLevelName);
                 
            ResultSet rs = prepStmt.executeQuery();
            rs.next();
            eveLevelId = rs.getString(1);                     
            Debug.print("eveLevelId:" + eveLevelId);
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in ValidateDAO.getEventLevelId():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in ValidateDAO.getEventLevelId():" + e.getMessage());
        }
        return eveLevelId;
    }      
    
    public String getAnnualRegistraterId(String annualMeetingId) {
            Debug.print("ValidateDAO.getAnnualRegistraterId():");
            String ardId=null;
            PreparedStatement prepStmt = null;
            
            makeConnection();
            try {
            String selectStatement = "select ard_id from tblMeeAnnualRegistrationDetails where annual_meeting_id = ? "; 
            
            Debug.print("selectStatement :"+selectStatement);            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,annualMeetingId);
                 
            ResultSet rs = prepStmt.executeQuery();
            rs.next();
            ardId = rs.getString(1);                     
            Debug.print("ardId: in ValidateDAO" + ardId);
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in ValidateDAO.getAnnualRegistraterId():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in ValidateDAO.getAnnualRegistraterId():" + e.getMessage());
        }
        return ardId;
    } 
  
    //============================================= Database Connectivity=========================================
    private Connection makeConnection() {
        Debug.print("ValidateDAO : makeConnection");
        try {
            InitialContext ic = new InitialContext();
            DataSource ds = (DataSource) ic.lookup(dbName);
            con = ds.getConnection();
        } catch (Exception ex) {
            throw new EJBException("Unable to connect to database in ValidateDAO. " + ex.getMessage());
        }
        return con;
    }
    //=============================================Connection Release=========================================
    private void releaseConnection(Connection con) {
        Debug.print("ValidateDAO releaseConnection");
        try {
            if(!con.isClosed())
                con.close();
        } catch (Exception e) {
        }
    }
}
