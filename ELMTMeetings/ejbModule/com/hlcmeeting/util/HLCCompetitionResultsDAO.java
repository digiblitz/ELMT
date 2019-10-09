/*
 * CompetitionResultsDAO.java
 *
 * Created on March 30, 2007, 5:02 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmeeting.util;

import javax.ejb.*;
import java.sql.*;
import javax.sql.*;
import java.util.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.naming.*;

/**
 *
 * @author karthikeyan
 */

public class HLCCompetitionResultsDAO {
    
    /** Creates a new instance of CompetitionResultsDAO */
    public HLCCompetitionResultsDAO() {
    }
    
    private static final String dbName = "java:/ELMTMSSQLDS";
    private Connection con = null;
    private PreparedStatement prepStmt = null;
    private ResultSet rs = null;
    
   /**
  * Name         :getUploadId
  * Description  :This method will return the registration upload id
  * @ param      :Nil
  * @return      :String
  * @throws      :SQLException
  */    
    public String getUploadId() throws SQLException{
        String uploadId = "";
        
        try{
            makeConnection();
            
            String selectStatement = "select newid() as reg_upload_id";
            Debug.print("insertCompResultUploadDetails getNextuploadId :" + selectStatement);
            PreparedStatement prepSelect = con.prepareStatement(selectStatement);
            ResultSet rs = prepSelect.executeQuery();
            rs.next();
            uploadId = rs.getString(1);
            rs.close();
            prepSelect.close();
            releaseConnection();
        }
        catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in getNextUploadId CompetitionResultsDAO:" + sql.getMessage());
        }
        Debug.print("getNextUploadId in CompetitionResultsDAO:" + uploadId);
        return uploadId;
    } 

  /**
  * Name         :insertHorseRegUploadDetails
  * Description  :This method will insert the uploaded competition result file details
  * @ param      :event_id,eventTypeId,result_file_path
  * @return      :String
  * @throws      :SQLException
  */   
    public String insertHorseRegUploadDetails(String event_id,String eventTypeId,String registration_file_path) throws SQLException {
        Debug.print("Inside insertHorseRegUploadDetails :");
        String uploadId=getUploadId();
        Debug.print("uploadId in vaigaisessionBean:" + uploadId);
        try {
            makeConnection();
            String insertStatement =
                    "insert into tblMeeCompRegistrationFileDetails " +
                    " (event_id,event_type_id,registration_file_path,reg_upload_id) values ( ? ,?, ? ,?) ";
            
            Debug.print("Query Log :"+insertStatement);
            
            prepStmt = con.prepareStatement(insertStatement);
            prepStmt.setString(1, event_id);
            Debug.print(" event_id : "+event_id);
            prepStmt.setString(2, eventTypeId);
            prepStmt.setString(3, registration_file_path);
            Debug.print(" registration_file_path : "+registration_file_path);
            prepStmt.setString(4, uploadId);
            Debug.print(" uploadId : "+uploadId);
            int cnt = prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection();
            Debug.print("Successfully Inserted insertHorseRegUploadDetails() CompetitionResultsDAO row count :" + cnt);
        }catch(Exception e){
            releaseConnection();
            Debug.print("Error while insertHorseRegUploadDetails CompetitionResultsDAO : "+e.getMessage());
        }finally {
            releaseConnection();
        }
        return uploadId;
    }
   /**
  * Name         :selectUploadHorseRegDetails
  * Description  :This method will return the uploaded horse registration file details
  * @ param      :
  * @return      :ArrayList
  * @throws      :SQLException
  */   
    public ArrayList selectUploadHorseRegDetails() throws SQLException {
        Debug.print("Inside selectUploadHorseRegDetails");
        ArrayList uploadDetails = new ArrayList();
        HLCUploadHorseRegVO uploadDetailsVO = new HLCUploadHorseRegVO();
        
        try {
            makeConnection();
           
            String selecStmt = "SELECT event_id,registration_file_path, upload_date,reg_upload_id," +
                    " active_status, validation_status FROM tblMeeCompRegistrationFileDetails " +
                    " order by upload_date desc";


                    
            Debug.print("Query Log :"+selecStmt);
            prepStmt = con.prepareStatement(selecStmt);
            rs = prepStmt.executeQuery();
            while(rs.next()) {
                uploadDetailsVO = new HLCUploadHorseRegVO();
                String event_id = rs.getString(1);
               // String event_name = rs.getString(2);
                String registration_file_path = rs.getString(2);
                java.sql.Date upload_date = DBHelper.toSQLDate(rs.getDate(3));
                String  reg_upload_id = rs.getString(4);
                boolean active_status = rs.getBoolean(5);
                boolean validation_status = rs.getBoolean(6);
                
                uploadDetailsVO.setEvent_id(event_id);
                //uploadDetailsVO.setEvent_name(event_name);
                uploadDetailsVO.setRegistration_file_path(registration_file_path);
                uploadDetailsVO.setUpload_date(upload_date);
                uploadDetailsVO.setReg_upload_id(reg_upload_id);
                uploadDetailsVO.setActive_status(active_status);
                uploadDetailsVO.setValidation_status(validation_status);
                Debug.print("uploadDetailsVO.toString() in bean :"+uploadDetailsVO.toString());
                uploadDetails.add(uploadDetailsVO);
            }
            prepStmt.close();
            releaseConnection();
            
        }catch(SQLException e){
            releaseConnection();
            Debug.print("Error while retrieving selectUploadHorseRegDetails in CompetitionResultsDAO: "+e.getMessage());
        }finally {
            releaseConnection();
        }
        return uploadDetails;
    }
     /**
  * Name         :selectHorseRegMappingTableDetails
  * Description  :This method will retrieve the result set meta data for the mapping table
  * @ param      :
  * @return      :ArrayList
  * @throws      :SQLException
  */   
    public ArrayList selectHorseRegMappingTableDetails() throws SQLException {
        Debug.print("Inside selectHorseRegMappingTableDetails ");
        ArrayList tblDetails = new ArrayList();
                        
        try {
            makeConnection();
            
            String selecStmt = "SELECT * FROM tblMeeTempCompRegistrationDetails";
            
            Debug.print("Query Log :"+selecStmt);
            
            prepStmt = con.prepareStatement(selecStmt);
                       
            rs = prepStmt.executeQuery();
            
            ResultSetMetaData rsmd = rs.getMetaData();
            int numberOfColumns = rsmd.getColumnCount();
            Debug.print("numberOfColumns :"+numberOfColumns);
            
            for(int i=1;i<=numberOfColumns;i++)
            {                                
                String colName = rsmd.getColumnName(i);
                Debug.print("colName "+i+" :"+colName);
                String colType = rsmd.getColumnTypeName(i);
                Debug.print("colType "+i+" :"+colType);
                
                String[] tblDet = {colName,colType};
                
                tblDetails.add(tblDet);                
            }
            
            prepStmt.close();
            releaseConnection();
                        
        }catch(SQLException e){
            releaseConnection();
            Debug.print("Error while retrieving selectHorseRegMappingTableDetails  in CompetitionResultsDAO: "+e.getMessage());
        }finally {
            releaseConnection();
        }
        return tblDetails;
     } 
    
  /**
  * Name         :insertHorseRegMapData
  * Author       :Punitha.R   
  * Description  :This method will insert the mapped datas to the comp result table
  * @ param      :compResultVo
  * @return      :boolean
  * @throws      :SQLException
  */   
    public boolean insertHorseRegMapData(HLCMapHorseRegVO compRegVO) throws SQLException {
        Debug.print("Inside insertHorseRegMapData ");
                                 
        Debug.print("MapHorseRegVO.toString() :"+compRegVO.toString());
        
        try {
            makeConnection();
           
                        
            String insertStatement = 
                "insert into tblMeeTempCompRegistrationDetails " +
                    " (event_id, event_type, event_level, event_division_amateur, event_division_age," +
                    " event_division_experience, horse_name, horse_member_id, horse_usef_id, rider_first_name, " +
                    " rider_last_name, rider_member_id, rider_usef_id,owner_first_name," +
                    " owner_last_name, owner_usea_id, owner_usef_id,reg_upload_id)" +
                    " values( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? ,? , ? , ? , ? , ? , ?) ";
            
            Debug.print("Query Log :"+insertStatement);
            
            prepStmt = con.prepareStatement(insertStatement);
            	
             prepStmt.setString(1,compRegVO.getEventId());
             prepStmt.setString(2,compRegVO.getEventType());
             prepStmt.setString(3,compRegVO.getEventLevel());
             prepStmt.setString(4,compRegVO.getEventDivisionAmateur());
             prepStmt.setString(5,compRegVO.getEventDivisionAge());
             prepStmt.setString(6,compRegVO.getEventDdivisionExperience());
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
             prepStmt.setString(18,compRegVO.getRegUploadId());
             
            int ct = prepStmt.executeUpdate();
                       
            prepStmt.close();
            releaseConnection();
                        
        }catch(SQLException e){
            releaseConnection();
            Debug.print("Error while inserting in CompetitionResultsDAO insertHorseRegMapData : "+e.getMessage());
        }finally {
            releaseConnection();
        }
        return true;
     }  
    
 /**
  * Name         :insertCompResultUploadDetails
  * Description  :This method will insert the uploaded competition result file details
  * @ param      :event_id,event_name,result_file_path
  * @return      :String
  * @throws      :SQLException
  */   
    public String insertCompResultUploadDetails(String event_id,String event_name,String result_file_path,String eventTypeId) throws SQLException {
        Debug.print("Inside insertCompResultUploadDetails :");
        
        String uploadId = "";
        
        try{
            makeConnection();
            
            String selectStatement = "select newid() as uploadId";
            Debug.print("insertCompResultUploadDetails getNextuploadId :" + selectStatement);
            PreparedStatement prepSelect = con.prepareStatement(selectStatement);
            ResultSet rs = prepSelect.executeQuery();
            rs.next();
            uploadId = rs.getString(1);
            rs.close();
            prepSelect.close();
            releaseConnection();
        }
        catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in getNextUploadId :" + sql.getMessage());
        }
             Debug.print("getNextUploadId :" + uploadId);
        
        try {
            makeConnection();
            
            String insertStatement = 
          "insert into " + DBHelper.USEA_COMP_RESULT_FILE_DETAILS + " (event_id, event_name,result_file_path,upload_id,event_type_id)"+
          " values ( ? , ? , ? , ? , ? ) ";
            
            Debug.print("Query Log :"+insertStatement);
            
            prepStmt = con.prepareStatement(insertStatement);
            prepStmt.setString(1, event_id);
             Debug.print(" event_id : "+event_id);
            prepStmt.setString(2, event_name);
            Debug.print(" event_name : "+event_name);
            prepStmt.setString(3, result_file_path);
            Debug.print(" result_file_path : "+result_file_path);
            prepStmt.setString(4, uploadId);
            Debug.print(" uploadId : "+uploadId);
            prepStmt.setString(5, eventTypeId);
            Debug.print(" eventTypeId : "+eventTypeId);
            
            int cnt = prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection();
            Debug.print("Successfully Inserted insertCompResultUploadDetails() row count :" + cnt);
        }catch(Exception e){
            releaseConnection();
            Debug.print("Error while insertCompResultUploadDetails in DAO : "+e.getMessage());
        }finally {
            releaseConnection();
        }
        return uploadId;
     }   
    
  /**
  * Name         :listCompResultUploadDetails
  * Description  :This method will return the uploaded competition result file details
  * @ param      :
  * @return      :ArrayList
  * @throws      :SQLException
  */   
    public ArrayList listCompResultUploadDetails() throws SQLException {
        Debug.print("Inside listCompResultUploadDetails ");
        ArrayList uploadDetails = new ArrayList();
        HLCCompUploadFileDetails uploadDetailsVO = new HLCCompUploadFileDetails();
                
        try {
            makeConnection();
                                    
            /*String selecStmt = "SELECT A.event_id,B.event_type_name,A.result_file_path,A.upload_date,A.upload_id,A.active_status, A.validation_status FROM "+DBHelper.USEA_COMP_RESULT_FILE_DETAILS+" A, "
                                +DBHelper.USEA_EVENT_TYPE_DETAILS+" B where A.event_type_id = B.event_type_id order by upload_date desc";*/
            
            String selecStmt = "SELECT A.event_id,B.event_type_name,A.result_file_path,A.upload_date,A.upload_id,A.active_status, "+
                                "A.validation_status FROM "+DBHelper.USEA_COMP_RESULT_FILE_DETAILS+" A, "+DBHelper.USEA_EVENT_TYPE_DETAILS+
                                " B where A.result_file_path != '' and A.event_type_id = B.event_type_id and "+
                                "(A.active_status = 0 OR A.validation_status = 0) order by upload_date desc";
            
            
            Debug.print("Query Log :"+selecStmt);
            
            prepStmt = con.prepareStatement(selecStmt);
                       
            rs = prepStmt.executeQuery();
            
            while(rs.next())
            {
                uploadDetailsVO = new HLCCompUploadFileDetails();
                
                String  event_id = rs.getString(1);                
                String  event_name = rs.getString(2);
                String result_file_path = rs.getString(3);
                java.sql.Date upload_date = DBHelper.toSQLDate(rs.getDate(4));
                String  upload_id = rs.getString(5);
                boolean active_status = rs.getBoolean(6);
                boolean valid_stat = rs.getBoolean(7);
                
                uploadDetailsVO.setEvent_id(event_id);
                uploadDetailsVO.setEvent_name(event_name);
                uploadDetailsVO.setResult_file_path(result_file_path);
                uploadDetailsVO.setUpload_date(upload_date);
                uploadDetailsVO.setUpload_id(upload_id);
                uploadDetailsVO.setActive_status(active_status);
                uploadDetailsVO.setValidation_status(valid_stat);
                
                Debug.print("uploadDetailsVO.toString() in bean :"+uploadDetailsVO.toString());
                
                uploadDetails.add(uploadDetailsVO);
                
            }
            
            prepStmt.close();
            releaseConnection();
                        
        }catch(SQLException e){
            releaseConnection();
            Debug.print("Error while retrieving listCompResultUploadDetails : "+e.getMessage());
        }finally {
            releaseConnection();
        }
        return uploadDetails;
     }   
    
    /**
  * Name         :listCompResultUploadDetailsOnYear
  * Description  :This method will return the uploaded competition result file details
  * @ param      :
  * @return      :ArrayList
  * @throws      :SQLException
  */   
    public ArrayList listCompResultUploadDetailsOnYear(String year) throws SQLException {
        Debug.print("Inside listCompResultUploadDetailsOnYear ");
        ArrayList uploadDetails = new ArrayList();
        HLCCompUploadFileDetails uploadDetailsVO = new HLCCompUploadFileDetails();
                
        try {
            makeConnection();
                                    
            /*String selecStmt = "SELECT A.event_id,B.event_type_name,A.result_file_path,A.upload_date,A.upload_id,A.active_status, A.validation_status FROM "+DBHelper.USEA_COMP_RESULT_FILE_DETAILS+" A, "
                                +DBHelper.USEA_EVENT_TYPE_DETAILS+" B where A.event_type_id = B.event_type_id order by upload_date desc";*/
            
            String selecStmt = "SELECT A.event_id,B.event_type_name,A.result_file_path,A.upload_date,A.upload_id,A.active_status,"+ 
                               "A.validation_status FROM "+DBHelper.USEA_COMP_RESULT_FILE_DETAILS+" A, "+DBHelper.USEA_EVENT_TYPE_DETAILS+ 
                                " B where A.result_file_path != '' and A.event_type_id = B.event_type_id and "+
                                "YEAR(A.upload_date) = ? order by upload_date desc";
            
            
            Debug.print("Query Log :"+selecStmt);
            
            prepStmt = con.prepareStatement(selecStmt);
            prepStmt.setString(1,year);
            
            rs = prepStmt.executeQuery();
            
            while(rs.next())
            {
                uploadDetailsVO = new HLCCompUploadFileDetails();
                
                String  event_id = rs.getString(1);                
                String  event_name = rs.getString(2);
                String result_file_path = rs.getString(3);
                java.sql.Date upload_date = DBHelper.toSQLDate(rs.getDate(4));
                String  upload_id = rs.getString(5);
                boolean active_status = rs.getBoolean(6);
                boolean valid_stat = rs.getBoolean(7);
                
                uploadDetailsVO.setEvent_id(event_id);
                uploadDetailsVO.setEvent_name(event_name);
                uploadDetailsVO.setResult_file_path(result_file_path);
                uploadDetailsVO.setUpload_date(upload_date);
                uploadDetailsVO.setUpload_id(upload_id);
                uploadDetailsVO.setActive_status(active_status);
                uploadDetailsVO.setValidation_status(valid_stat);
                
                Debug.print("uploadDetailsVO.toString() in bean :"+uploadDetailsVO.toString());
                
                uploadDetails.add(uploadDetailsVO);
                
            }
            
            prepStmt.close();
            releaseConnection();
                        
        }catch(SQLException e){
            releaseConnection();
            Debug.print("Error while retrieving listCompResultUploadDetailsOnYear : "+e.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            Debug.print("General Exception while retrieving listCompResultUploadDetailsOnYear : "+e.getMessage());
        }
        finally {
            releaseConnection();
        }
        return uploadDetails;
     } 
    
    /**
  * Name         :getCompResultMappingTableDetails
  * Description  :This method will retrieve the result set meta data for the mapping table
  * @ param      : upload_id
  * @return      :ArrayList
  * @throws      :SQLException
  */   
    public ArrayList getCompResultMappingTableDetails(String upload_id) throws SQLException {
        Debug.print("Inside getCompResultMappingTableDetails in DAO ");
        Debug.print("Inside getCompResultMappingTableDetails in DAO upload_id :"+upload_id);
        
        ArrayList tblDetails = new ArrayList();
                        
        try {
            makeConnection();
            
            //String selecStmt = "SELECT * FROM "+DBHelper.USEA_TEMP_COMP_RESULT;
            
            
            String selecStmt = "select  A.event_id, A.event_type_id, B.comp_result_field_id, B.comp_result_field_name, C.priority"  
                                +" from "+DBHelper.USEA_COMP_RESULT_FILE_DETAILS+" A, "+DBHelper.USEA_COMP_RESULT_FIELDMASTER+" B, "+DBHelper.USEA_COMP_RESULT_FIELDS+" C where A.event_type_id = C.event_type_id" 
                                +" and B.comp_result_field_id = C.comp_result_field_id and upload_id = ? ORDER BY C.priority DESC";
            
            Debug.print("Query Log :"+selecStmt);
            
            prepStmt = con.prepareStatement(selecStmt);
            prepStmt.setString(1,upload_id);
            
            rs = prepStmt.executeQuery();
            
            /*ResultSetMetaData rsmd = rs.getMetaData();
            int numberOfColumns = rsmd.getColumnCount();
            Debug.print("numberOfColumns :"+numberOfColumns);
            
            for(int i=1;i<=numberOfColumns;i++)
            {                                
                String colName = rsmd.getColumnName(i);
                Debug.print("colName "+i+" :"+colName);
                String colType = rsmd.getColumnTypeName(i);
                Debug.print("colType "+i+" :"+colType);
                
                String[] tblDet = {colName,colType};
                
                tblDetails.add(tblDet);                
            }*/
            
            while(rs.next()){                
                String col = rs.getString(4);
                tblDetails.add(col);
            }
                        
            prepStmt.close();
            releaseConnection();
                        
        }catch(SQLException e){
            releaseConnection();
            Debug.print("SQLException while retrieving getCompResultMappingTableDetails in DAO: "+e.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            Debug.print("General while retrieving getCompResultMappingTableDetails in DAO : "+e.getMessage());
        }
        finally {
            releaseConnection();
        }
        return tblDetails;
     } 
    
 /**
  * Name         :insertCompMapData
  * Description  :This method will insert the mapped datas to the comp result table
  * @ param      :compResultVo
  * @return      :boolean
  * @throws      :SQLException
  */   
    public boolean insertCompMapData(HLCCompResultVO compResultVo) throws SQLException {
        Debug.print("Inside insertCompMapData ");
                                 
        Debug.print("compResultVo.toString() :"+compResultVo.toString());
        
        try {
            makeConnection();
            
                        
            String insertStatement = "insert into " + DBHelper.USEA_TEMP_COMP_RESULT + " (event_type_id, event_id, event_type, event_level, event_division, starters, horse_name,"+
                    " horse_member_id, rider_first_name, rider_member_id, pinney_number, dressage_penalties, dressage_place, xc_phaseD_jump_penalties, xc_phaseD_elapsed_time, xc_phaseD_time_penalties,"+
                    " show_jump_time_penalties, show_jump_jump_penalties, to_date_points, to_date_place, dangerous_riding_penalties, final_points, final_place, first_inspection, last_inspection, "+
                    " phase_D_inspection, road_and_track_A, road_and_track_C, steeplechase_jump_penalties, steeplechase_time_penalties, usea_points, upload_id, rider_last_name) values ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? ) ";

            Debug.print("Query Log :"+insertStatement);
            
            prepStmt = con.prepareStatement(insertStatement);
            	    
             prepStmt.setString(1, compResultVo.getEvent_type_id());
             prepStmt.setString(2, compResultVo.getEvent_id());
             prepStmt.setString(3, compResultVo.getEvent_type());
             prepStmt.setString(4,compResultVo.getEvent_level());
             prepStmt.setString(5,compResultVo.getEvent_division());
             prepStmt.setString(6,compResultVo.getStarters());
             prepStmt.setString(7,compResultVo.getHorse_name());
             prepStmt.setString(8,compResultVo.getHorse_member_id());
             prepStmt.setString(9,compResultVo.getRider_first_name());
             prepStmt.setString(10,compResultVo.getRider_member_id());
             prepStmt.setString(11,compResultVo.getPinney_number());
             prepStmt.setString(12,compResultVo.getDressage_penalties());
             prepStmt.setString(13,compResultVo.getDressage_place());
             prepStmt.setString(14,compResultVo.getXc_phaseD_jump_penalties());
             prepStmt.setString(15,compResultVo.getXc_phaseD_elapsed_time());
             prepStmt.setString(16,compResultVo.getXc_phaseD_time_penalties());
             prepStmt.setString(17,compResultVo.getShow_jump_time_penalties());
             prepStmt.setString(18,compResultVo.getShow_jump_jump_penalties());
             prepStmt.setString(19,compResultVo.getTo_date_points());
             prepStmt.setString(20,compResultVo.getTo_date_place());
             prepStmt.setString(21,compResultVo.getDangerous_riding_penalties());
             prepStmt.setString(22,compResultVo.getFinal_points());
             prepStmt.setString(23,compResultVo.getFinal_place());
             prepStmt.setString(24,compResultVo.getFirst_inspection());
             prepStmt.setString(25,compResultVo.getLast_inspection());
             prepStmt.setString(26,compResultVo.getPhase_D_inspection());
             prepStmt.setString(27,compResultVo.getRoad_and_track_A());
             prepStmt.setString(28,compResultVo.getRoad_and_track_C());
             prepStmt.setString(29,compResultVo.getSteeplechase_jump_penalties());
             prepStmt.setString(30,compResultVo.getSteeplechase_time_penalties());
             prepStmt.setString(31,compResultVo.getUsea_points());
             prepStmt.setString(32,compResultVo.getUpload_id());
             prepStmt.setString(33,compResultVo.getRider_last_name());
             
            int ct = prepStmt.executeUpdate();
                       
            prepStmt.close();
            releaseConnection();
                        
        }catch(SQLException e){
            releaseConnection();
            Debug.print("Error while retrieving insertCompMapData : "+e.getMessage());
        }finally {
            releaseConnection();
        }
        return true;
     }
    
    /**
  * Name         :insertEventCompQualDetails
  * Description  :This method will insert the qualification details for event
  * @ param      :EventCompQualDetVO
  * @return      :boolean
  * @throws      :SQLException
  */   
    public boolean insertEventCompQualDetails(HLCEventCompQualDetVO CompQualDetail) throws SQLException {
        Debug.print("Inside insertEventCompQualDetails in DAO :");
        boolean result = false;
        
        Debug.print("CompQualDetail.toString() :"+CompQualDetail.toString());
        
        try {
            makeConnection();
            
                        
            String insertStatement = "insert into " + DBHelper.USEA_COMP_QUAL_DETAILS + " (event_type, event_level, rider_age, horse_age, event_division, age_from, age_to,"+
                    " horse_rank, amateur_status) values ( ? , ? , ? , ? , ? , ? , ? , ? , ? ) ";
        
            Debug.print("Query Log :"+insertStatement);
            
            prepStmt = con.prepareStatement(insertStatement);
            	    
             prepStmt.setString(1, CompQualDetail.getEvent_type());
             prepStmt.setString(2, CompQualDetail.getEvent_level());
             prepStmt.setString(3, CompQualDetail.getRider_age());
             prepStmt.setString(4, CompQualDetail.getHorse_age());
             prepStmt.setString(5, CompQualDetail.getEvent_division());
             prepStmt.setString(6, CompQualDetail.getAge_from());
             prepStmt.setString(7, CompQualDetail.getAge_to());
             prepStmt.setString(8, CompQualDetail.getHorse_rank());
             prepStmt.setBoolean(9, CompQualDetail.isAmateur_status());
                          
             int ct = prepStmt.executeUpdate();
             
             Debug.print("Records inserted count : "+ct);
             
             if(ct>0)
             {
                result = true;
             }
             
            prepStmt.close();
            releaseConnection();
                        
        }catch(SQLException e){
            releaseConnection();
            Debug.print("Error while insertEventCompQualDetails : "+e.getMessage());
        }finally {
            releaseConnection();
        }
        return result;
     }
    
    /**
  * Name         :getEventTypeDetails
  * Description  :This method will return the Event Type Details
  * @ param      :
  * @return      :ArrayList
  * @throws      :SQLException
  */   
    public ArrayList getEventTypeDetails() throws SQLException {
        Debug.print("Inside getEventTypeDetails : ");
        ArrayList eventTypeDetails = new ArrayList();
                        
        try {
            makeConnection();
            
            String selecStmt = "SELECT event_type_id,event_type_name FROM "+DBHelper.USEA_EVENT_TYPE_DETAILS;
            
            Debug.print("Query Log :"+selecStmt);
            
            prepStmt = con.prepareStatement(selecStmt);
                       
            rs = prepStmt.executeQuery();
            
            while(rs.next())
            {                               
                String  event_type_id = rs.getString(1);                
                String  event_type_name = rs.getString(2);
                
                Debug.print(" event_type_id :"+event_type_id);
                Debug.print(" event_type_name :"+event_type_name);
                
                String[] eventDet = {event_type_id,event_type_name};
                
                eventTypeDetails.add(eventDet);
                
            }
            
            prepStmt.close();
            releaseConnection();
                        
        }catch(SQLException e){
            releaseConnection();
            Debug.print("Error while retrieving getEventTypeDetails : "+e.getMessage());
        }finally {
            releaseConnection();
        }
        return eventTypeDetails;
     }  
    
    /**
  * Name         :insertCompResultDetailsByQuery
  * Description  :This method will insert the uploaded competition result sdetails
  * @ param      :String
  * @return      :boolean
  * @throws      :SQLException
  */   
    public boolean insertCompResultDetailsByQuery(String query) throws SQLException {
        Debug.print("Inside insertCompResultDetailsByQuery in DAO :");
                
        boolean status = false;
        
        try {
            makeConnection();
                                           
            Debug.print("Query Log :"+query);
            
            Statement stmt=con.createStatement();
            
            int rec = stmt.executeUpdate(query);
           
            if(rec>0)
            {
                status = true; 
            }
            
            Debug.print("insertCompResultDetailsByQuery in DAO rec count is :"+rec);
            
            stmt.close();
            releaseConnection();
            
        }catch(SQLException e){
            releaseConnection();
            Debug.print("SQLException while retrieving insertCompResultDetailsByQuery in CompetitionResultsDAO: "+e.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            Debug.print("General Exception while retrieving insertCompResultDetailsByQuery in CompetitionResultsDAO: "+e.getMessage());
        }
        finally {
            releaseConnection();
        }
        return status;
    }
    
     /**
  * Name         :updateCompResultEventDet
  * Description  :This method will update the event details on tbltempcompresults based on tblcompresultfiledetails 
  * @ param      :uploadId
  * @return      :boolean
  * @throws      :RemoteException
  */   
    
    public boolean updateCompResultEventDet(String uploadId) throws SQLException {
        Debug.print("Inside updateCompResultEventDet in dao :");
        Debug.print("uploadId :"+uploadId);
        boolean val = false;
        
        try {
            makeConnection();
           
            String selecStmt = "SELECT A.event_type_id, A.event_id, B.event_type_name FROM "+DBHelper.USEA_COMP_RESULT_FILE_DETAILS+" A, "
                    +DBHelper.USEA_EVENT_TYPE_DETAILS+" B  where A.event_type_id=B.event_type_id and A.upload_id = ?";
 
            Debug.print("Query Log :"+selecStmt);
            prepStmt = con.prepareStatement(selecStmt);
            prepStmt.setString(1,uploadId);
            
            String event_type_id = "";
            String event_id = "";
            String event_type_name="";
            rs = prepStmt.executeQuery();
            while(rs.next()) {               
                event_type_id = rs.getString(1);
                event_id = rs.getString(2);
                event_type_name=rs.getString(3);
            }
            prepStmt.close();
            releaseConnection();
            
            Debug.print("event_type_id :"+event_type_id);
            Debug.print("event_id :"+event_id);
            
            makeConnection();
            
            String updateStatement = "update " + DBHelper.USEA_TEMP_COMP_RESULT + " set  event_type_id = ?, event_id = ?, event_type = ? where upload_id = ?";
            
            prepStmt = con.prepareStatement(updateStatement);
            Debug.print("Query Log :"+updateStatement);
            
            prepStmt.setString(1, event_type_id);
            prepStmt.setString(2, event_id);
            prepStmt.setString(3, event_type_name);
            prepStmt.setString(4, uploadId);
            
            int cnt = prepStmt.executeUpdate();
            
            if(cnt>0)
                val = true;
            
            Debug.print("Successfully updated updateCompResultEventDet ......:" + cnt);
            
            prepStmt.close();
            
        }catch(SQLException e){
            releaseConnection();
            Debug.print("SQL Exception while retrieving updateCompResultEventDet in DAO: "+e.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            Debug.print("Error while retrieving updateCompResultEventDet in DAO: "+e.getMessage());
        }
        finally {
            releaseConnection();
        }
        return val;
    }
    
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
            Debug.print("getConnection() .........");
            
        } catch (Exception ex) {
            throw new EJBException("Unable to connect to database. " + ex.getMessage());
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
                Debug.print("connection released !!");
                con.close();
            }
        } catch (SQLException ex) {
            throw new EJBException("releaseConnection: " + ex.getMessage());
        }
    }
    
}
