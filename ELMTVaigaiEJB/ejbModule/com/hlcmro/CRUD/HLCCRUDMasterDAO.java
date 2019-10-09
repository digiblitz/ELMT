/*
 * CRUDMasterDAO.java
 *
 * Created on December 20, 2006, 1:11 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmro.CRUD;

import com.hlcmro.util.DBHelper;
import com.hlcmro.util.Debug;
import com.hlcmro.util.HLCEndorsedFormVO;
import com.hlcmro.util.HLCEventDetailsVO;
import com.hlcmro.util.HLCPaymentDetails;
import com.hlcmro.util.HLCPaymentDetailsVO;
import com.hlcmro.util.HLCRenewalOrganizerDetails;
import java.util.ArrayList;
import java.util.Vector;
import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.naming.InitialContext;
import javax.sql.*;
import java.sql.*;
import java.util.Date;

/**
 *
 * @author harmohan
 */
public class HLCCRUDMasterDAO {
     private Connection con;
    /** Creates a new instance of CRUDMasterDAO */
    public HLCCRUDMasterDAO() {
    }
//insertMapDressageTest(eventLevelId, eventSubLevelId)
  /*----------------------------Dressage test for horse Dressage Trails CRUD Operation------------------------*/
/**
 * Name         :isRecordExist
 * Description  :This method is used to check if the record exist with that name in the tblMeeEventSubLevelMaster table
 * @ param      :event sub level name
 * @return      :boolean
 * @throws      :SQLException
 */    
 public boolean isMapDressageTestExist(String eventLevelId, String eventSubLevelId) throws SQLException {
     boolean bol = false;
     int cnt = 0;
     try{
         makeConnection();
         String selectStr = "Select count(*) as cnt from "+DBHelper.USEA_MRO_MAP_EVENT_SUB_LEVEL +
             "  WHERE event_level_id = ? AND event_sub_level_id = ?";
         PreparedStatement prepStmt = con.prepareStatement(selectStr);
                prepStmt.setString(1,eventLevelId);
                prepStmt.setString(2,eventSubLevelId);
                ResultSet rs = prepStmt.executeQuery();
                if(rs.next()){
                    cnt =Integer.parseInt(rs.getString(1));
                }
                Debug.print("Number of Record Exist : "+cnt);
                prepStmt.close();
                releaseConnection();
     }catch(Exception e){
         releaseConnection();
         Debug.print(" Error : "+e.getMessage());
         e.printStackTrace();
     }
     if (cnt > 0)
         bol = true;
     return bol;
 }    
/**
 * Name         :insertMapDressageTest
 * Description  :This method is used to insert the mapping details for Dressage Test for Advanced Level and Arena Size
 * @ param      :event sub level name
 * @return      :boolean
 * @throws      :SQLException
 */

    public boolean insertMapDressageTest( String eventLevelId, String eventSubLevelId) throws SQLException {
       
        boolean stat = false; 
        int cnt = 0;
        makeConnection();

        String insertStatement =
            "insert into "+DBHelper.USEA_MRO_MAP_EVENT_SUB_LEVEL +" (event_level_id, event_sub_level_id) values (?,?)";
        PreparedStatement prepStmt = con.prepareStatement(insertStatement);
        try {
                prepStmt.setString(1,eventLevelId);
                prepStmt.setString(2,eventSubLevelId);
                cnt = prepStmt.executeUpdate();
                Debug.print(" Inserted Successfully into Map Event Sub Level  : "+cnt);
        }catch (SQLException e) { 
                Debug.print(" Error While inserting in insertMapDressageTest in CRUDMasterDAO : "+e.getMessage());
                e.printStackTrace();
        }finally {
            prepStmt.close();
            releaseConnection();
        }
        if (cnt > 0)
            stat = true;
        
        return stat;
    }     
/**
 * Name         :isRecordExist
 * Description  :This method is used to check if the record exist with that name in the tblMeeEventSubLevelMaster table
 * @ param      :event sub level name
 * @return      :boolean
 * @throws      :SQLException
 */    
 public boolean isRecordExist(String eventSubLevelName) throws SQLException {
     boolean bol = false;
     int cnt = 0;
     try{
         makeConnection();
         String selectStr = "Select count(*) as cnt from "+DBHelper.USEA_MRO_SUB_LEVEL_MASTER +
             "  WHERE event_sub_level_name = ?";
         PreparedStatement prepStmt = con.prepareStatement(selectStr);
                prepStmt.setString(1,eventSubLevelName);
                ResultSet rs = prepStmt.executeQuery();
                if(rs.next()){
                    cnt =Integer.parseInt(rs.getString(1));
                }
                Debug.print("Number of Record Exist : "+cnt);
                prepStmt.close();
                releaseConnection();
     }catch(Exception e){
         releaseConnection();
         Debug.print(" Error : "+e.getMessage());
         e.printStackTrace();
     }
     if (cnt > 0)
         bol = true;
     return bol;
 }
 
/**
 * Name         :insertRowDHT
 * Description  :This method is used to insert record into tblMeeEventSubLevelMaster table
 * @ param      :event sub level name
 * @return      :boolean
 * @throws      :SQLException
 */

    public boolean insertRowDHT( String event_sub_level_name) throws SQLException {
       
        boolean stat = false; 
        int cnt = 0;
        makeConnection();

        String insertStatement =
            "insert into "+DBHelper.USEA_MRO_SUB_LEVEL_MASTER +" (event_sub_level_name) values (?)";
        PreparedStatement prepStmt = con.prepareStatement(insertStatement);
        try {
                prepStmt.setString(1,event_sub_level_name);
                cnt = prepStmt.executeUpdate();
                Debug.print(" Inserted Successfully into Event Sub Level Master : "+cnt);
        }catch (SQLException e) { 
                Debug.print(" Error While inserting in insertRowDHT in CRUDMasterDAO : "+e.getMessage());
                e.printStackTrace();
        }finally {
            prepStmt.close();
            releaseConnection();
        }
        if (cnt > 0)
            stat = true;
        
        return stat;
    }    
    
/**
 * Name         :deletetRowDHT
 * Description  :This method is used to delete a record based on the event sub level id from tblMeeEventSubLevelMaster table
 * @ param      :event_sub_level_id
 * @return      :boolean
 * @throws      :SQLException
 */

    public boolean deletetRowDHT(String event_sub_level_id) throws SQLException {
        boolean bol = false;
        makeConnection();
        PreparedStatement psDelete = null;
        int intChkReturn = 0;
        try {
            StringBuffer sbString = new StringBuffer();
            sbString.append("UPDATE "+DBHelper.USEA_MRO_SUB_LEVEL_MASTER);
            sbString.append("  SET active_status = ? WHERE event_sub_level_id =?");
            psDelete = con.prepareStatement(sbString.toString());
            psDelete.setBoolean(1, false);
            psDelete.setString(2, event_sub_level_id);
            intChkReturn = psDelete.executeUpdate();
            Debug.print(" Succeffully Deleted event sub level master : "+intChkReturn);
            bol = true;

        }
        catch (Exception ex) {
             releaseConnection();
             Debug.print("Error:  "+ex.getMessage());
        }
        finally {
             psDelete.close();
             releaseConnection();
        }
        return bol;
    }
/**
 * Name         :activateRowDHT
 * Description  :This method is used to delete a record based on the event sub level id from tblMeeEventSubLevelMaster table
 * @ param      :event_sub_level_id
 * @return      :boolean
 * @throws      :SQLException
 */

    public boolean activateRowDHT(String event_sub_level_id) throws SQLException {
        boolean bol = false;
        makeConnection();
        PreparedStatement psDelete = null;
        int intChkReturn = 0;
        try {
            StringBuffer sbString = new StringBuffer();
            sbString.append("UPDATE "+DBHelper.USEA_MRO_SUB_LEVEL_MASTER);
            sbString.append("  SET active_status = ? WHERE event_sub_level_id =?");
            psDelete = con.prepareStatement(sbString.toString());
            psDelete.setBoolean(1, true);
            psDelete.setString(2, event_sub_level_id);
            intChkReturn = psDelete.executeUpdate();
            Debug.print(" Succeffully activated event sub level master : "+intChkReturn);
            bol = true;

        }
        catch (Exception ex) {
             releaseConnection();
             Debug.print("Error:  "+ex.getMessage());
        }
        finally {
             psDelete.close();
             releaseConnection();
        }
        return bol;
    }    
	
    
   /** Name		:updateRowDHT
     * Description	:This method will be used to update the tblMeeEventSubLevelMaster table
     * 				 
     * Exception	:SQLException
     * @param   	:eventSubLevelName, eventSubLevelId
     * @return  	:boolean value
     */
    public boolean updateRowDHT(String eventSubLevelName, String eventSubLevelId) throws SQLException {
        boolean bol = false;
        makeConnection();
        PreparedStatement psUpdate = null;
        int intRtrnStatus = 0;
        StringBuffer sbCrossCountryId = new StringBuffer();
        try {
            sbCrossCountryId.append(" UPDATE "+DBHelper.USEA_MRO_SUB_LEVEL_MASTER);
            sbCrossCountryId.append(" SET event_sub_level_name = ? ");
            sbCrossCountryId.append(" WHERE event_sub_level_id = ? ");
            psUpdate = con.prepareStatement(sbCrossCountryId.toString());

            psUpdate.setString(1,eventSubLevelName);
            psUpdate.setString(2, eventSubLevelId);

            intRtrnStatus = psUpdate.executeUpdate();
            Debug.print(" Succefully Update Event sub Level Master : "+intRtrnStatus);
            bol = true;
        }
        catch (Exception e) {
            releaseConnection();
            Debug.print(e.getMessage(), e);
        }
        finally {
            psUpdate.close();
            releaseConnection();
        }
        return bol;
    }
        

/** Name	:loadDHT
  * Description	:This method will be used to display the content of tblMeeEventSubLevelMaster based on eventSubLevelId
  * 				 
  * Exception	:SQLException
  * @param   	:eventSubLevelId
  * @return  	:Vector
  */
    public Vector loadDHT(String eventSubLevelId) throws SQLException {
        Vector vecObj = new Vector();
        try {
                makeConnection();
                String selectStr = "select event_sub_level_id, event_sub_level_name, active_status,add_date from "+
                DBHelper.USEA_MRO_SUB_LEVEL_MASTER +" where event_sub_level_id = ? ";
                PreparedStatement prepStmt = con.prepareStatement(selectStr);
                prepStmt.setString(1,eventSubLevelId);
                //prepStmt.setBoolean(2,true);
                ResultSet rs = prepStmt.executeQuery();
                while (rs.next()){
                    String eventSubLevelID =rs.getString(1);
                    String eventSubLevelName = rs.getString(2);
                    String activeStatus = rs.getString(3);
                    String AddDate = rs.getString(4);
                    String [] eventName = {eventSubLevelID, eventSubLevelName,activeStatus,AddDate};
                    vecObj.add(eventName);
                }
                prepStmt.close();
                releaseConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return vecObj;
    }    
/** Name	:loadDHTAll
  * Description	:This method will be used to display the the record from the table tblMeeEventSubLevelMaster
  * 				 
  * Exception	:SQLException
  * @param   	:None
  * @return  	:Vector
  */
    public Vector loadDHTAll(boolean status) throws SQLException {
        Vector vecObj = new Vector();
        try {
                makeConnection();
                System.out.println("Inside the Loop loadDHT");
                String Event_id = "select event_sub_level_id from "+DBHelper.USEA_MRO_SUB_LEVEL_MASTER+" WHERE active_status = ?";
                PreparedStatement prepStmt1 = con.prepareStatement(Event_id);
                prepStmt1.setBoolean(1,status);
                ResultSet rs1 = prepStmt1.executeQuery();
                System.out.println("Event Id Inside the loadDHT  ");
                while (rs1.next()){
                    String eventID = rs1.getString(1);
                    Debug.print("Event Id Inside the loadDHT  "+eventID);
                    String selectStr = "select event_sub_level_id, event_sub_level_name, active_status,add_date from "+
                    DBHelper.USEA_MRO_SUB_LEVEL_MASTER +" where event_sub_level_id = ?";
                    PreparedStatement prepStmt = con.prepareStatement(selectStr);
                    prepStmt.setString(1,eventID);
                    ResultSet rs = prepStmt.executeQuery();
                    while (rs.next()){
                        String eventSubLevelID =rs.getString(1);
                        String eventSubLevelName = rs.getString(2);
                        String activeStatus = rs.getString(3);
                        String AddDate = rs.getString(4);
                        String [] eventName = {eventSubLevelID, eventSubLevelName,activeStatus,AddDate};
                        vecObj.add(eventName);
                    }
                    prepStmt.close();
                }
                prepStmt1.close();
                releaseConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return vecObj;
    }    
    
  /*-------------------------End of Dressage test for horse trails CRUD Operation---------------------------*/ 
    
  /*----------------------------Dressage test for Arena Size CRUD Operation------------------------*/
    
    /**
 * Name         :isArenaRecordExist
 * Description  :This method is used to check if the record exist with that name in the tblMeeArenaSizeMaster table
 * @ param      :Arena size Name
 * @return      :boolean
 * @throws      :SQLException
 */    
 public boolean isArenaRecordExist(String arenaSizeName) throws SQLException {
     boolean bol = false;
     int cnt = 0;
     try{
         makeConnection();
         String selectStr = "Select count(*) as cnt from "+DBHelper.USEA_MRO_ARENA_SIZE_MASTER +
             "  WHERE arena_size_name = ?";
         PreparedStatement prepStmt = con.prepareStatement(selectStr);
                prepStmt.setString(1,arenaSizeName);
                ResultSet rs = prepStmt.executeQuery();
                if(rs.next()){
                    cnt =Integer.parseInt(rs.getString(1));
                }
                Debug.print("Number of Record Exist : "+cnt);
                prepStmt.close();
                releaseConnection();
     }catch(Exception e){
         releaseConnection();
         Debug.print(" Error : "+e.getMessage());
         e.printStackTrace();
     }
     if (cnt > 0)
         bol = true;
     return bol;
 }
 
/**
 * Name         :insertArenaSize
 * Description  :This method is used to insert record into tblMeeArenaSizeMaster table
 * @ param      :arenaSizeName
 * @return      :boolean
 * @throws      :SQLException
 */

    public boolean insertArenaSize( String arenaSizeName) throws SQLException {
       
        boolean stat = false; 
        int cnt = 0;
        makeConnection();

        String insertStatement =
            "insert into "+DBHelper.USEA_MRO_ARENA_SIZE_MASTER +" (arena_size_name) values (?)";
        PreparedStatement prepStmt = con.prepareStatement(insertStatement);
        try {
                prepStmt.setString(1,arenaSizeName);
                cnt = prepStmt.executeUpdate();
                Debug.print(" Inserted Successfully into Arena Size Master : "+cnt);
        }catch (SQLException e) { 
                Debug.print(" Error While inserting in insertArenaSize in CRUDMasterDAO : "+e.getMessage());
                e.printStackTrace();
        }finally {
            prepStmt.close();
            releaseConnection();
        }
        if (cnt > 0)
            stat = true;
        
        return stat;
    }    
    
/**
 * Name         :deletetArenaSize
 * Description  :This method is used to delete a record based on the event sub level id from tblMeeArenaSizeMaster table
 * @ param      :arenaSizeId
 * @return      :boolean
 * @throws      :SQLException
 */

    public boolean deletetArenaSize(String arenaSizeId) throws SQLException {
        boolean bol = false;
        makeConnection();
        PreparedStatement psDelete = null;
        int intChkReturn = 0;
        try {
            StringBuffer sbString = new StringBuffer();
            sbString.append("UPDATE "+DBHelper.USEA_MRO_ARENA_SIZE_MASTER);
            sbString.append("  SET active_status = ? WHERE arena_size_id =?");
            psDelete = con.prepareStatement(sbString.toString());
            psDelete.setBoolean(1, false);
            psDelete.setString(2, arenaSizeId);
            intChkReturn = psDelete.executeUpdate();
            Debug.print(" Succeffully Deleted Arena Size master : "+intChkReturn);
            bol = true;

        }
        catch (Exception ex) {
             releaseConnection();
             Debug.print("Error:  "+ex.getMessage());
        }
        finally {
             psDelete.close();
             releaseConnection();
        }
        return bol;
    }
/**
 * Name         :activateArenaSize
 * Description  :This method is used to delete a record based on the event sub level id from tblMeeArenaSizeMaster table
 * @ param      :arenaSizeId
 * @return      :boolean
 * @throws      :SQLException
 */

    public boolean activateArenaSize(String arenaSizeId) throws SQLException {
        boolean bol = false;
        makeConnection();
        PreparedStatement psDelete = null;
        int intChkReturn = 0;
        try {
            StringBuffer sbString = new StringBuffer();
            sbString.append("UPDATE "+DBHelper.USEA_MRO_ARENA_SIZE_MASTER);
            sbString.append("  SET active_status = ? WHERE arena_size_id =?");
            psDelete = con.prepareStatement(sbString.toString());
            psDelete.setBoolean(1, true);
            psDelete.setString(2, arenaSizeId);
            intChkReturn = psDelete.executeUpdate();
            Debug.print(" Succeffully activated Arena Size master : "+intChkReturn);
            bol = true;

        }
        catch (Exception ex) {
             releaseConnection();
             Debug.print("Error:  "+ex.getMessage());
        }
        finally {
             psDelete.close();
             releaseConnection();
        }
        return bol;
    }    
	
    
   /** Name		:updateArenaSize
     * Description	:This method will be used to update the tblMeeArenaSizeMaster table
     * 				 
     * Exception	:SQLException
     * @param   	:arenaSizeName, arenaSizeId
     * @return  	:boolean value
     */
    public boolean updateArenaSize(String arenaSizeName, String arenaSizeId) throws SQLException {
        boolean bol = false;
        makeConnection();
        PreparedStatement psUpdate = null;
        int intRtrnStatus = 0;
        StringBuffer sbCrossCountryId = new StringBuffer();
        try {
            sbCrossCountryId.append(" UPDATE "+DBHelper.USEA_MRO_ARENA_SIZE_MASTER);
            sbCrossCountryId.append(" SET arena_size_name = ? ");
            sbCrossCountryId.append(" WHERE arena_size_id = ? ");
            psUpdate = con.prepareStatement(sbCrossCountryId.toString());

            psUpdate.setString(1,arenaSizeName);
            psUpdate.setString(2, arenaSizeId);

            intRtrnStatus = psUpdate.executeUpdate();
            Debug.print(" Succefully Update Arena Size Master : "+intRtrnStatus);
            bol = true;
        }
        catch (Exception e) {
            releaseConnection();
            Debug.print(e.getMessage(), e);
        }
        finally {
            psUpdate.close();
            releaseConnection();
        }
        return bol;
    }
        

/** Name	:loadArenaSize
  * Description	:This method will be used to display the content of tblMeeArenaSizeMaster based on eventSubLevelId
  * 				 
  * Exception	:SQLException
  * @param   	:arenaSizeId
  * @return  	:Vector
  */
    public Vector loadArenaSize(String arenaSizeId) throws SQLException {
        Vector vecObj = new Vector();
        try {
                makeConnection();
                String selectStr = "select arena_size_id, arena_size_name, active_status,add_date from "+
                DBHelper.USEA_MRO_ARENA_SIZE_MASTER +" where arena_size_id = ? ";
                PreparedStatement prepStmt = con.prepareStatement(selectStr);
                prepStmt.setString(1,arenaSizeId);
               // prepStmt.setBoolean(2,true);
                ResultSet rs = prepStmt.executeQuery();
                while (rs.next()){
                    String arenaSizeID =rs.getString(1);
                    String arenaSizeName = rs.getString(2);
                    String activeStatus = rs.getString(3);
                    String AddDate = rs.getString(4);
                    String [] arenaName = {arenaSizeID, arenaSizeName,activeStatus,AddDate};
                    vecObj.add(arenaName);
                }
                prepStmt.close();
                releaseConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return vecObj;
    }    
/** Name	:loadAllArenaSize
  * Description	:This method will be used to display the the record from the table tblMeeArenaSizeMaster
  * 				 
  * Exception	:SQLException
  * @param   	:None
  * @return  	:Vector
  */
    public Vector loadAllArenaSize(boolean status) throws SQLException {
        Vector vecObj = new Vector();
        try {
                makeConnection();
                System.out.println("Inside the Loop loadAllArenaSize");
                String Arena_id = "select arena_size_id from "+DBHelper.USEA_MRO_ARENA_SIZE_MASTER+" WHERE active_status = ?";
                PreparedStatement prepStmt1 = con.prepareStatement(Arena_id);
                prepStmt1.setBoolean(1,status);
                ResultSet rs1 = prepStmt1.executeQuery();
                System.out.println("Arena Id Inside the loadAllArenaSize  ");
                while (rs1.next()){
                    String arenaID = rs1.getString(1);
                    Debug.print("Arena Id Inside the loadAllArenaSize  "+arenaID);
                    String selectStr = "select arena_size_id, arena_size_name, active_status,add_date from "+
                    DBHelper.USEA_MRO_ARENA_SIZE_MASTER +" where arena_size_id = ?";
                    PreparedStatement prepStmt = con.prepareStatement(selectStr);
                    prepStmt.setString(1,arenaID);
                    ResultSet rs = prepStmt.executeQuery();
                    while (rs.next()){
                        String arenaSizeID =rs.getString(1);
                        String arenaSizeName = rs.getString(2);
                        String activeStatus = rs.getString(3);
                        String AddDate = rs.getString(4);
                        String [] arenaName = {arenaSizeID, arenaSizeName,activeStatus,AddDate};
                        vecObj.add(arenaName);
                    }
                    prepStmt.close();
                }
                prepStmt1.close();
                releaseConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return vecObj;
    } 
    
  /*----------------------------Dressage test for Arena Size CRUD Operation------------------------*/
    
/*----------------------------Officials CRUD Operation------------------------*/
    
    /**
 * Name         :isOfficialRecordExist
 * Description  :This method is used to check if the record exist with that name in the tblMeeJudgeTypeMaster  table
 * @ param      :judgeTypeName
 * @return      :boolean
 * @throws      :SQLException
 */    
 public boolean isOfficialRecordExist(String judgeTypeName) throws SQLException {
     boolean bol = false;
     int cnt = 0;
     try{
         makeConnection();
         String selectStr = "Select count(*) as cnt from "+DBHelper.USEA_MRO_JUDGE_TYPE_MSATER +
             "  WHERE judge_type_name = ?";
         PreparedStatement prepStmt = con.prepareStatement(selectStr);
                prepStmt.setString(1,judgeTypeName);
                ResultSet rs = prepStmt.executeQuery();
                if(rs.next()){
                    cnt =Integer.parseInt(rs.getString(1));
                }
                Debug.print("Number of Record Exist : "+cnt);
                prepStmt.close();
                releaseConnection();
     }catch(Exception e){
         releaseConnection();
         Debug.print(" Error : "+e.getMessage());
         e.printStackTrace();
     }
     if (cnt > 0)
         bol = true;
     return bol;
 }
 
/**
 * Name         :insertArenaSize
 * Description  :This method is used to insert record into tblMeeJudgeTypeMaster  table
 * @ param      :judgeTypeName
 * @return      :boolean
 * @throws      :SQLException
 */

    public boolean insertOfficial( String judgeTypeName) throws SQLException {
       
        boolean stat = false; 
        int cnt = 0;
        makeConnection();

        String insertStatement =
            "insert into "+DBHelper.USEA_MRO_JUDGE_TYPE_MSATER +" (judge_type_name) values (?)";
        PreparedStatement prepStmt = con.prepareStatement(insertStatement);
        try {
                prepStmt.setString(1,judgeTypeName);
                cnt = prepStmt.executeUpdate();
                Debug.print(" Inserted Successfully into Judge Type Master : "+cnt);
        }catch (SQLException e) { 
                Debug.print(" Error While inserting in insertOfficial in CRUDMasterDAO : "+e.getMessage());
                e.printStackTrace();
        }finally {
            prepStmt.close();
            releaseConnection();
        }
        if (cnt > 0)
            stat = true;
        
        return stat;
    }    
    
/**
 * Name         :deletetOfficial
 * Description  :This method is used to delete a record based on the event sub level id from tblMeeJudgeTypeMaster table
 * @ param      :judgeTypeId
 * @return      :boolean
 * @throws      :SQLException
 */

    public boolean deletetOfficial(String judgeTypeId) throws SQLException {
        boolean bol = false;
        makeConnection();
        PreparedStatement psDelete = null;
        int intChkReturn = 0;
        try {
            StringBuffer sbString = new StringBuffer();
            sbString.append("UPDATE "+DBHelper.USEA_MRO_JUDGE_TYPE_MSATER);
            sbString.append("  SET active_status = ? WHERE judge_type_id =?");
            psDelete = con.prepareStatement(sbString.toString());
            psDelete.setBoolean(1, false);
            psDelete.setString(2, judgeTypeId);
            intChkReturn = psDelete.executeUpdate();
            Debug.print(" Succeffully Deleted Judge Type master : "+intChkReturn);
            bol = true;

        }
        catch (Exception ex) {
             releaseConnection();
             Debug.print("Error:  "+ex.getMessage());
        }
        finally {
             psDelete.close();
             releaseConnection();
        }
        return bol;
    }
/**
 * Name         :activateJudgeType
 * Description  :This method is used to delete a record based on the event sub level id from tblMeeJudgeTypeMaster table
 * @ param      :judgeTypeId
 * @return      :boolean
 * @throws      :SQLException
 */

    public boolean activateJudgeType(String judgeTypeId) throws SQLException {
        boolean bol = false;
        makeConnection();
        PreparedStatement psDelete = null;
        int intChkReturn = 0;
        try {
            StringBuffer sbString = new StringBuffer();
            sbString.append("UPDATE "+DBHelper.USEA_MRO_JUDGE_TYPE_MSATER);
            sbString.append("  SET active_status = ? WHERE judge_type_id =?");
            psDelete = con.prepareStatement(sbString.toString());
            psDelete.setBoolean(1, true);
            psDelete.setString(2, judgeTypeId);
            intChkReturn = psDelete.executeUpdate();
            Debug.print(" Succeffully activated Judge Type Master : "+intChkReturn);
            bol = true;

        }
        catch (Exception ex) {
             releaseConnection();
             Debug.print("Error:  "+ex.getMessage());
        }
        finally {
             psDelete.close();
             releaseConnection();
        }
        return bol;
    }    
	
    
   /** Name		:updateJudgeType
     * Description	:This method will be used to update the tblMeeJudgeTypeMaster  table
     * 				 
     * Exception	:SQLException
     * @param   	:judgeTypeName, judgeTypeId
     * @return  	:boolean value
     */
    public boolean updateJudgeType(String judgeTypeName, String judgeTypeId) throws SQLException {
        boolean bol = false;
        makeConnection();
        PreparedStatement psUpdate = null;
        int intRtrnStatus = 0;
        StringBuffer JudgeType = new StringBuffer();
        try {
            JudgeType.append(" UPDATE "+DBHelper.USEA_MRO_JUDGE_TYPE_MSATER);
            JudgeType.append(" SET judge_type_name = ? ");
            JudgeType.append(" WHERE judge_type_id = ? ");
            psUpdate = con.prepareStatement(JudgeType.toString());

            psUpdate.setString(1,judgeTypeName);
            psUpdate.setString(2, judgeTypeId);

            intRtrnStatus = psUpdate.executeUpdate();
            Debug.print(" Succefully Update Judge Type Master : "+intRtrnStatus);
            bol = true;
        }
        catch (Exception e) {
            releaseConnection();
            Debug.print(e.getMessage(), e);
        }
        finally {
            psUpdate.close();
            releaseConnection();
        }
        return bol;
    }
        

/** Name	:loadArenaSize
  * Description	:This method will be used to display the content of tblMeeJudgeTypeMaster  based on judgeTypeId
  * 				 
  * Exception	:SQLException
  * @param   	:arenaSizeId
  * @return  	:Vector
  */
    public Vector loadJudgetype(String judgeTypeId) throws SQLException {
        Vector vecObj = new Vector();
        try {
                makeConnection();
                String selectStr = "select judge_type_id, judge_type_name, active_status,add_date from "+
                DBHelper.USEA_MRO_JUDGE_TYPE_MSATER +" where judge_type_id = ? ";
                PreparedStatement prepStmt = con.prepareStatement(selectStr);
                prepStmt.setString(1,judgeTypeId);
                //prepStmt.setBoolean(2,true);
                ResultSet rs = prepStmt.executeQuery();
                while (rs.next()){
                    String judgeTypeID =rs.getString(1);
                    String judgeTypeName = rs.getString(2);
                    String activeStatus = rs.getString(3);
                    String AddDate = rs.getString(4);
                    String [] judgeName = {judgeTypeID, judgeTypeName,activeStatus,AddDate};
                    vecObj.add(judgeName);
                }
                prepStmt.close();
                releaseConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return vecObj;
    }    
/** Name	:loadAllJudgeType
  * Description	:This method will be used to display the the record from the table tblMeeJudgeTypeMaster 
  * 				 
  * Exception	:SQLException
  * @param   	:None
  * @return  	:Vector
  */
    public Vector loadAllJudgeType(boolean status) throws SQLException {
        Vector vecObj = new Vector();
        try {
                makeConnection();
                System.out.println("Inside the Loop loadAllJudgeType");
                String Judge_id = "select judge_type_id from "+DBHelper.USEA_MRO_JUDGE_TYPE_MSATER;
                PreparedStatement prepStmt1 = con.prepareStatement(Judge_id);
                ResultSet rs1 = prepStmt1.executeQuery();
                System.out.println("Arena Id Inside the loadAllJudgeType  ");
                while (rs1.next()){
                    String judgeID = rs1.getString(1);
                    Debug.print("Arena Id Inside the loadAllArenaSize  "+judgeID);
                    String selectStr = "select judge_type_id, judge_type_name, active_status,add_date from "+
                    DBHelper.USEA_MRO_JUDGE_TYPE_MSATER +" where judge_type_id = ? and active_status= ?";
                    PreparedStatement prepStmt = con.prepareStatement(selectStr);
                    prepStmt.setString(1,judgeID);
                    prepStmt.setBoolean(2,status);
                    
                    ResultSet rs = prepStmt.executeQuery();
                    while (rs.next()){
                        String judgeTypeID =rs.getString(1);
                        String judgeTypeName = rs.getString(2);
                        String activeStatus = rs.getString(3);
                        String AddDate = rs.getString(4);
                        String [] judgeName = {judgeTypeID, judgeTypeName,activeStatus,AddDate};
                        vecObj.add(judgeName);
                    }
                    prepStmt.close();
                }
                prepStmt1.close();
                releaseConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return vecObj;
    } 
    
/*----------------------------REFUNDS CRUD Operation------------------------*/
/**
 * Name         :isMapRefundRuleExist
 * Description  :This method is used to check if the record exist with that name in the tblMeeEventSubLevelMaster table
 * @ param      :event sub level name
 * @return      :boolean
 * @throws      :SQLException
 */    
 public boolean isMapRefundRuleExist(String refundRuleTypeId, String refundRuleSubTypeId) throws SQLException {
     boolean bol = false;
     int cnt = 0;
     try{
         makeConnection();
         String selectStr = "Select count(*) as cnt from "+DBHelper.USEA_MRO_MAP_REFUND_RULE +
             "  WHERE refund_rule_type_id = ? AND refund_rule_sub_type_id = ?";
         PreparedStatement prepStmt = con.prepareStatement(selectStr);
                prepStmt.setString(1,refundRuleTypeId);
                prepStmt.setString(2,refundRuleSubTypeId);
                ResultSet rs = prepStmt.executeQuery();
                if(rs.next()){
                    cnt =Integer.parseInt(rs.getString(1));
                }
                Debug.print("Number of Record Exist : "+cnt);
                prepStmt.close();
                releaseConnection();
     }catch(Exception e){
         releaseConnection();
         Debug.print(" Error : "+e.getMessage());
         e.printStackTrace();
     }
     if (cnt > 0)
         bol = true;
     return bol;
 }    
/**
 * Name         :insertMapDressageTest
 * Description  :This method is used to insert the mapping details for Dressage Test for Advanced Level and Arena Size
 * @ param      :event sub level name
 * @return      :boolean
 * @throws      :SQLException
 */

    public boolean insertMapRefundRule( String refundRuleTypeId, String refundRuleSubTypeId) throws SQLException {
       
        boolean stat = false; 
        int cnt = 0;
        makeConnection();

        String insertStatement =
            "insert into "+DBHelper.USEA_MRO_MAP_REFUND_RULE +" (refund_rule_type_id, refund_rule_sub_type_id) values (?,?)";
        PreparedStatement prepStmt = con.prepareStatement(insertStatement);
        try {
                prepStmt.setString(1,refundRuleTypeId);
                prepStmt.setString(2,refundRuleSubTypeId);
                cnt = prepStmt.executeUpdate();
                Debug.print(" Inserted Successfully into Map Refund Rule  : "+cnt);
        }catch (SQLException e) { 
                Debug.print(" Error While inserting in insertMapRefundRule in CRUDMasterDAO : "+e.getMessage());
                e.printStackTrace();
        }finally {
            prepStmt.close();
            releaseConnection();
        }
        if (cnt > 0)
            stat = true;
        
        return stat;
    }    
/**
 * Name         :isRefundMasterRecordExist
 * Description  :This method is used to check if the record exist with that name in the tblMeeRefundRuleMaster   table
 * @ param      :refundRuleTypeName
 * @return      :boolean
 * @throws      :SQLException
 */    
 public boolean isRefundMasterRecordExist(String refundRuleTypeName) throws SQLException {
     boolean bol = false;
     int cnt = 0;
     try{
         makeConnection();
         String selectStr = "Select count(*) as cnt from "+DBHelper.USEA_MRO_REFUND_RULE_MASTER +
             "  WHERE refund_rule_type_name = ?";
         PreparedStatement prepStmt = con.prepareStatement(selectStr);
                prepStmt.setString(1,refundRuleTypeName);
                ResultSet rs = prepStmt.executeQuery();
                if(rs.next()){
                    cnt =Integer.parseInt(rs.getString(1));
                }
                Debug.print("Number of Record Exist : "+cnt);
                prepStmt.close();
                releaseConnection();
     }catch(Exception e){
         releaseConnection();
         Debug.print(" Error : "+e.getMessage());
         e.printStackTrace();
     }
     if (cnt > 0)
         bol = true;
     return bol;
 }
 
 
 /**
 * Name         :isRefundMasterRecordEditExist
 * Description  :This method is used to check if the record exist with that name in the tblMeeRefundRuleMaster   table
 * @ param      :refundRuleTypeName
 * @return      :boolean
 * @throws      :SQLException
 */    
 public boolean isRefundMasterRecordEditExist(String refund_rule_type_id, String refundRuleTypeName) throws SQLException {
     boolean bol = false;
     int cnt = 0;
     try{
         makeConnection();
         String selectStr = "Select refund_rule_type_id from "+DBHelper.USEA_MRO_REFUND_RULE_MASTER +
             "  WHERE refund_rule_type_name = ? and refund_rule_type_id!=?";
         PreparedStatement prepStmt = con.prepareStatement(selectStr);
                prepStmt.setString(1,refundRuleTypeName);
                prepStmt.setString(2,refund_rule_type_id);
                ResultSet rs = prepStmt.executeQuery();
                bol =  rs.next();
                Debug.print("Number of Record Exist : "+bol);
                prepStmt.close();
                releaseConnection();
     }catch(Exception e){
         releaseConnection();
         Debug.print(" Error : "+e.getMessage());
         e.printStackTrace();
     }
     return bol;
 }
 
/**
 * Name         :insertRefundRuleMaster
 * Description  :This method is used to insert record into tblMeeRefundRuleMaster table
 * @ param      :refundRuleTypeName
 * @return      :boolean
 * @throws      :SQLException
 */

    public boolean insertRefundRuleMaster( String refundRuleTypeName) throws SQLException {
       
        boolean stat = false; 
        int cnt = 0;
        makeConnection();

        String insertStatement =
            "insert into "+DBHelper.USEA_MRO_REFUND_RULE_MASTER +" (refund_rule_type_name) values (?)";
        PreparedStatement prepStmt = con.prepareStatement(insertStatement);
        try {
                prepStmt.setString(1,refundRuleTypeName);
                cnt = prepStmt.executeUpdate();
                Debug.print(" Inserted Successfully into Refund Rule Master : "+cnt);
        }catch (SQLException e) { 
                Debug.print(" Error While inserting in insertRefundRuleMaster in CRUDMasterDAO : "+e.getMessage());
                e.printStackTrace();
        }finally {
            prepStmt.close();
            releaseConnection();
        }
        if (cnt > 0)
            stat = true;
        
        return stat;
    }    
    
/**
 * Name         :deletetRefundRuleMaster
 * Description  :This method is used to delete a record based on the Refund rule Type id from tblMeeRefundRuleMaster table
 * @ param      :refundRuleTypeId
 * @return      :boolean
 * @throws      :SQLException
 */

    public boolean deletetRefundRuleMaster(String refundRuleTypeId) throws SQLException {
        boolean bol = false;
        makeConnection();
        PreparedStatement psDelete = null;
        int intChkReturn = 0;
        try {
            StringBuffer sbString = new StringBuffer();
            sbString.append("UPDATE "+DBHelper.USEA_MRO_REFUND_RULE_MASTER);
            sbString.append("  SET active_status = ? WHERE refund_rule_type_id =?");
            psDelete = con.prepareStatement(sbString.toString());
            psDelete.setBoolean(1, false);
            psDelete.setString(2, refundRuleTypeId);
            intChkReturn = psDelete.executeUpdate();
            Debug.print(" Succeffully Deleted Refund Rule Master : "+intChkReturn);
            bol = true;

        }
        catch (Exception ex) {
             releaseConnection();
             Debug.print("Error:  "+ex.getMessage());
        }
        finally {
             psDelete.close();
             releaseConnection();
        }
        return bol;
    }
/**
 * Name         :activateRefundRuleMaster
 * Description  :This method is used to delete a record based on the refund Rule Type id from tblMeeRefundRuleMaster table
 * @ param      :refundRuleTypeId
 * @return      :boolean
 * @throws      :SQLException
 */

    public boolean activateRefundRuleMaster(String refundRuleTypeId) throws SQLException {
        boolean bol = false;
        makeConnection();
        PreparedStatement psDelete = null;
        int intChkReturn = 0;
        try {
            StringBuffer sbString = new StringBuffer();
            sbString.append("UPDATE "+DBHelper.USEA_MRO_REFUND_RULE_MASTER);
            sbString.append("  SET active_status = ? WHERE refund_rule_type_id =?");
            psDelete = con.prepareStatement(sbString.toString());
            psDelete.setBoolean(1, true);
            psDelete.setString(2, refundRuleTypeId);
            intChkReturn = psDelete.executeUpdate();
            Debug.print(" Succeffully activated Refund Rule Master : "+intChkReturn);
            bol = true;

        }
        catch (Exception ex) {
             releaseConnection();
             Debug.print("Error:  "+ex.getMessage());
        }
        finally {
             psDelete.close();
             releaseConnection();
        }
        return bol;
    }    
	
    
   /** Name		:updateRefundRuleMaster
     * Description	:This method will be used to update the tblMeeRefundRuleMaster table
     * 				 
     * Exception	:SQLException
     * @param   	:refundRuleTypeName, refundRuleTypeId
     * @return  	:boolean value
     */
    public boolean updateRefundRuleMaster(String refundRuleTypeName, String refundRuleTypeId) throws SQLException {
        boolean bol = false;
        makeConnection();
        PreparedStatement psUpdate = null;
        int intRtrnStatus = 0;
        StringBuffer refundRuleType = new StringBuffer();
        try {
            refundRuleType.append(" UPDATE "+DBHelper.USEA_MRO_REFUND_RULE_MASTER);
            refundRuleType.append(" SET refund_rule_type_name = ? ");
            refundRuleType.append(" WHERE refund_rule_type_id = ? ");
            psUpdate = con.prepareStatement(refundRuleType.toString());

            psUpdate.setString(1,refundRuleTypeName);
            psUpdate.setString(2, refundRuleTypeId);

            intRtrnStatus = psUpdate.executeUpdate();
            Debug.print(" Succefully Update Refund Rule Master : "+intRtrnStatus);
            bol = true;
        }
        catch (Exception e) {
            releaseConnection();
            Debug.print(e.getMessage(), e);
        }
        finally {
            psUpdate.close();
            releaseConnection();
        }
        return bol;
    }
        

/** Name	:loadRefundRuleMaster
  * Description	:This method will be used to display the content of tblMeeRefundRuleMaster based on refundRuleTypeId
  * 				 
  * Exception	:SQLException
  * @param   	:refundRuleTypeId
  * @return  	:Vector
  */
    public Vector loadRefundRuleMaster(String refundRuleTypeId) throws SQLException {
        Vector vecObj = new Vector();
        try {
                makeConnection();
                String selectStr = "select refund_rule_type_id, refund_rule_type_name, active_status,add_date from "+
                DBHelper.USEA_MRO_REFUND_RULE_MASTER +" where refund_rule_type_id = ? ";
                PreparedStatement prepStmt = con.prepareStatement(selectStr);
                prepStmt.setString(1,refundRuleTypeId);
                //prepStmt.setBoolean(2,true);
                ResultSet rs = prepStmt.executeQuery();
                while (rs.next()){
                    String refundRuleID =rs.getString(1);
                    String refundRuleName = rs.getString(2);
                    String activeStatus = rs.getString(3);
                    String AddDate = rs.getString(4);
                    String [] refundName = {refundRuleID, refundRuleName,activeStatus,AddDate};
                    vecObj.add(refundName);
                }
                prepStmt.close();
                releaseConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return vecObj;
    }    
/** Name	:loadAllRefundRuleMaster
  * Description	:This method will be used to display the  record from the table tblMeeRefundRuleMaster 
  * 				 
  * Exception	:SQLException
  * @param   	:None
  * @return  	:Vector
  */
    public Vector loadAllRefundRuleMaster(boolean status) throws SQLException {
        Vector vecObj = new Vector();
        try {
                makeConnection();
                System.out.println("Inside the Loop loadAllRefundRuleMaster");
                String Judge_id = "select refund_rule_type_id from "+DBHelper.USEA_MRO_REFUND_RULE_MASTER;
                PreparedStatement prepStmt1 = con.prepareStatement(Judge_id);
                ResultSet rs1 = prepStmt1.executeQuery();
                System.out.println("Refund Rule Id Inside the loadAllRefundRuleMaster  ");
                while (rs1.next()){
                    String judgeID = rs1.getString(1);
                    Debug.print("Refund Rule Id Inside the loadAllRefundRuleMaster  "+judgeID);
                    String selectStr = "select refund_rule_type_id, refund_rule_type_name, active_status,add_date from "+
                    DBHelper.USEA_MRO_REFUND_RULE_MASTER +" where refund_rule_type_id = ? and active_status=?";
                    PreparedStatement prepStmt = con.prepareStatement(selectStr);
                    prepStmt.setString(1,judgeID);
                    prepStmt.setBoolean(2,status);
                    
                    ResultSet rs = prepStmt.executeQuery();
                    while (rs.next()){
                        String refundRuleID =rs.getString(1);
                        String refundRuleName = rs.getString(2);
                        String activeStatus = rs.getString(3);
                        String AddDate = rs.getString(4);
                        String [] refundName = {refundRuleID, refundRuleName,activeStatus,AddDate};
                        vecObj.add(refundName);
                    }
                    prepStmt.close();
                }
                prepStmt1.close();
                releaseConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return vecObj;
    }     
    
/*----------------------------REFUNDS SUB MASTER CRUD Operation------------------------*/
    
/**
 * Name         :isRefundSubMasterRecordExist
 * Description  :This method is used to check if the record exist with that name in the tblMeeRefundRuleSubTypeMaster table
 * @ param      :refundRuleSubTypeName
 * @return      :boolean
 * @throws      :SQLException
 */    
    
 public boolean isRefundSubMasterRecordExist(String refundRuleSubTypeName) throws SQLException {
     boolean bol = false;
     int cnt = 0;
     try{
         makeConnection();
         String selectStr = "Select count(*) as cnt from "+DBHelper.USEA_MRO_REFUND_SUB_RULE_SUB_MASTER +
             "  WHERE refund_rule_sub_type_name = ?";
         PreparedStatement prepStmt = con.prepareStatement(selectStr);
                prepStmt.setString(1,refundRuleSubTypeName);
                ResultSet rs = prepStmt.executeQuery();
                if(rs.next()){
                    cnt =Integer.parseInt(rs.getString(1));
                }
                Debug.print("Number of Record Exist : "+cnt);
                prepStmt.close();
                releaseConnection();
     }catch(Exception e){
         releaseConnection();
         Debug.print(" Error : "+e.getMessage());
         e.printStackTrace();
     }
     if (cnt > 0)
         bol = true;
     return bol;
 }
 
 
 /**
 * Name         :isRefundSubMasterRecordEditExist
 * Description  :This method is used to check if the record exist with that name in the tblMeeRefundRuleSubTypeMaster table
 * @ param      :refundRuleSubTypeName
 * @return      :boolean
 * @throws      :SQLException
 */    
    
 public boolean isRefundSubMasterRecordEditExist(String refund_rule_sub_type_id, String refundRuleSubTypeName) throws SQLException {
     boolean bol = false;
     int cnt = 0;
     try{
         makeConnection();
         String selectStr = "Select refund_rule_sub_type_id from "+DBHelper.USEA_MRO_REFUND_SUB_RULE_SUB_MASTER +
             "  WHERE refund_rule_sub_type_name = ? and refund_rule_sub_type_id!=?";
         PreparedStatement prepStmt = con.prepareStatement(selectStr);
                prepStmt.setString(1,refundRuleSubTypeName);
                prepStmt.setString(2,refund_rule_sub_type_id);
                ResultSet rs = prepStmt.executeQuery();
                bol = rs.next();
                Debug.print("Number of Record Exist : "+cnt);
                prepStmt.close();
                releaseConnection();
     }catch(Exception e){
         releaseConnection();
         Debug.print(" Error : "+e.getMessage());
         e.printStackTrace();
     }
     return bol;
 }
 
/**
 * Name         :insertRefundRuleSubMaster
 * Description  :This method is used to insert record into tblMeeRefundRuleSubTypeMaster table
 * @ param      :refundRuleSubTypeName
 * @return      :boolean
 * @throws      :SQLException
 */

    public boolean insertRefundRuleSubMaster( String refundRuleSubTypeName,boolean status) throws SQLException {
       
        boolean stat = false; 
        int cnt = 0;
        makeConnection();

        String insertStatement =
            "insert into "+DBHelper.USEA_MRO_REFUND_SUB_RULE_SUB_MASTER +" (refund_rule_sub_type_name,text_box_status) values (?,?)";
        PreparedStatement prepStmt = con.prepareStatement(insertStatement);
        try {
                prepStmt.setString(1,refundRuleSubTypeName);
                prepStmt.setBoolean(2,status);
                cnt = prepStmt.executeUpdate();
                Debug.print(" Inserted Successfully into Refund Sub Rule Master : "+cnt);
        }catch (SQLException e) { 
                Debug.print(" Error While inserting in insertRefundRuleSubMaster in CRUDMasterDAO : "+e.getMessage());
                e.printStackTrace();
        }finally {
            prepStmt.close();
            releaseConnection();
        }
        if (cnt > 0)
            stat = true;
        
        return stat;
    }    
    
/**
 * Name         :deletetRefundRuleMaster
 * Description  :This method is used to delete a record based on the Refund rule Sub Type id from tblMeeRefundRuleSubTypeMaster table
 * @ param      :refundRuleSubTypeId
 * @return      :boolean
 * @throws      :SQLException
 */

    public boolean deletetRefundRuleSubMaster(String refundRuleSubTypeId) throws SQLException {
        boolean bol = false;
        makeConnection();
        PreparedStatement psDelete = null;
        int intChkReturn = 0;
        try {
            StringBuffer sbString = new StringBuffer();
            sbString.append("UPDATE "+DBHelper.USEA_MRO_REFUND_SUB_RULE_SUB_MASTER);
            sbString.append("  SET active_status = ? WHERE refund_rule_sub_type_id =?");
            psDelete = con.prepareStatement(sbString.toString());
            psDelete.setBoolean(1, false);
            psDelete.setString(2, refundRuleSubTypeId);
            intChkReturn = psDelete.executeUpdate();
            Debug.print(" Succeffully Deleted Refund Rule Sub Type Master : "+intChkReturn);
            bol = true;
        }
        catch (Exception ex) {
             releaseConnection();
             Debug.print("Error:  "+ex.getMessage());
        }
        finally {
             psDelete.close();
             releaseConnection();
        }
        return bol;
    }
/**
 * Name         :activateRefundRuleSubMaster
 * Description  :This method is used to delete a record based on the refund Rule Sub Type id from tblMeeRefundRuleSubTypeMaster table
 * @ param      :refundRuleSubTypeId
 * @return      :boolean
 * @throws      :SQLException
 */

    public boolean activateRefundRuleSubMaster(String refundRuleSubTypeId) throws SQLException {
        boolean bol = false;
        makeConnection();
        PreparedStatement psDelete = null;
        int intChkReturn = 0;
        try {
            StringBuffer sbString = new StringBuffer();
            sbString.append("UPDATE "+DBHelper.USEA_MRO_REFUND_SUB_RULE_SUB_MASTER);
            sbString.append("  SET active_status = ? WHERE refund_rule_sub_type_id =?");
            psDelete = con.prepareStatement(sbString.toString());
            psDelete.setBoolean(1, true);
            psDelete.setString(2, refundRuleSubTypeId);
            intChkReturn = psDelete.executeUpdate();
            Debug.print(" Succeffully activated Refund Rule Sub Type Master : "+intChkReturn);
            bol = true;
        }
        catch (Exception ex) {
             releaseConnection();
             Debug.print("Error:  "+ex.getMessage());
        }
        finally {
             psDelete.close();
             releaseConnection();
        }
        return bol;
    }    
	
    
   /** Name		:updateRefundRuleSubMaster
     * Description	:This method will be used to update the tblMeeRefundRuleSubTypeMaster table
     * 				 
     * Exception	:SQLException
     * @param   	:refundRuleSubTypeName, refundRuleSubTypeId
     * @return  	:boolean value
     */
    public boolean updateRefundRuleSubMaster(String refundRuleSubTypeName, String refundRuleSubTypeId, boolean status) throws SQLException {
        boolean bol = false;
        makeConnection();
        PreparedStatement psUpdate = null;
        int intRtrnStatus = 0;
        StringBuffer refundRuleSubType = new StringBuffer();
        try {
            refundRuleSubType.append(" UPDATE "+DBHelper.USEA_MRO_REFUND_SUB_RULE_SUB_MASTER);
            refundRuleSubType.append(" SET refund_rule_sub_type_name = ?, ");
            refundRuleSubType.append(" text_box_status = ? ");
            refundRuleSubType.append(" WHERE refund_rule_sub_type_id = ? ");
            
            psUpdate = con.prepareStatement(refundRuleSubType.toString());

            psUpdate.setString(1,refundRuleSubTypeName);
            psUpdate.setBoolean(2,status);
            psUpdate.setString(3, refundRuleSubTypeId);

            intRtrnStatus = psUpdate.executeUpdate();
            Debug.print(" Succefully Update Refund Rule Sub Type Master : "+intRtrnStatus);
            bol = true;
        }
        catch (Exception e) {
            releaseConnection();
            Debug.print(e.getMessage(), e);
        }
        finally {
            psUpdate.close();
            releaseConnection();
        }
        return bol;
    }
        

/** Name	:loadRefundRuleSubMaster
  * Description	:This method will be used to display the content of tblMeeRefundRuleSubTypeMaster based on refundRuleSubTypeId
  * 				 
  * Exception	:SQLException
  * @param   	:refundRuleSubTypeId
  * @return  	:Vector
  */
    public Vector loadRefundRuleSubMaster(String refundRuleSubTypeId) throws SQLException {
        Vector vecObj = new Vector();
        try {
                makeConnection();
                String selectStr = "select refund_rule_sub_type_id, refund_rule_sub_type_name, active_status,add_date,text_box_status from "+
                DBHelper.USEA_MRO_REFUND_SUB_RULE_SUB_MASTER+" where refund_rule_sub_type_id = ? ";
                PreparedStatement prepStmt = con.prepareStatement(selectStr);
                prepStmt.setString(1,refundRuleSubTypeId);
               // prepStmt.setBoolean(2,true);
                ResultSet rs = prepStmt.executeQuery();
                while (rs.next()){
                    String refundRuleSubID =rs.getString(1);
                    String refundRuleSubName = rs.getString(2);
                    String activeStatus = rs.getString(3);
                    String AddDate = rs.getString(4);
                    String boxStatus = rs.getString(5);
                    String [] refundSubName = {refundRuleSubID, refundRuleSubName,activeStatus,AddDate,boxStatus};
                    vecObj.add(refundSubName);
                }
                prepStmt.close();
                releaseConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return vecObj;
    }    
/** Name	:loadAllRefundRuleSubMaster
  * Description	:This method will be used to display the the record from the table tblMeeRefundRuleSubTypeMaster 
  * 				 
  * Exception	:SQLException
  * @param   	:None
  * @return  	:Vector
  */
    public Vector loadAllRefundRuleSubMaster(boolean status) throws SQLException {
        Vector vecObj = new Vector();
        try {
                makeConnection();
                System.out.println("Inside the Loop loadAllRefundRuleSubMaster");
                String refundSubId = "select refund_rule_sub_type_id from "+DBHelper.USEA_MRO_REFUND_SUB_RULE_SUB_MASTER;
                PreparedStatement prepStmt1 = con.prepareStatement(refundSubId);
                ResultSet rs1 = prepStmt1.executeQuery();
                System.out.println("Refund Rule Id Inside the loadAllRefundRuleSubMaster  ");
                while (rs1.next()){
                    String refundSubID = rs1.getString(1);
                    Debug.print("Refund Rule Id Inside the loadAllRefundRuleSubMaster  "+refundSubID);
                    String selectStr = "select refund_rule_sub_type_id, refund_rule_sub_type_name, active_status,add_date,text_box_status from "+
                    DBHelper.USEA_MRO_REFUND_SUB_RULE_SUB_MASTER +" where refund_rule_sub_type_id = ? and active_status=?";
                    PreparedStatement prepStmt = con.prepareStatement(selectStr);
                    prepStmt.setString(1,refundSubID);
					prepStmt.setBoolean(2,status);
                    
                    ResultSet rs = prepStmt.executeQuery();
                    while (rs.next()){
                        String refundRuleSubID =rs.getString(1);
                        String refundRuleSubName = rs.getString(2);
                        String activeStatus = rs.getString(3);
                        String AddDate = rs.getString(4);
                        String boxStatus = rs.getString(5);
                        String [] refundSubName = {refundRuleSubID, refundRuleSubName,activeStatus,AddDate,boxStatus};
                        vecObj.add(refundSubName);
                    }
                    prepStmt.close();
                }
                prepStmt1.close();
                releaseConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return vecObj;
    }      
    
/*---------------------------- EVENT DIVISION MASTER CRUD Operation------------------------*/
    
/**
 * Name         :isEventDivisionExist
 * Description  :This method is used to check if the record exist with that name in the tblMeeEventTypeMaster table
 * @ param      :divisionName
 * @return      :boolean
 * @throws      :SQLException
 */    
    
 public boolean isEventDivisionExist(String divisionName) throws SQLException {
      Debug.print("isEventDivisionExist in CRUDMasterDAO" );
     boolean bol = false;
     int cnt = 0;
     try{
         makeConnection();
         String selectStr = "Select count(*) as cnt from "+DBHelper.USEA_MRO_DIVISION_MASTER +
             "  WHERE event_division_name = ?";
         PreparedStatement prepStmt = con.prepareStatement(selectStr);
                prepStmt.setString(1,divisionName);
                ResultSet rs = prepStmt.executeQuery();
                if(rs.next()){
                    cnt =Integer.parseInt(rs.getString(1));
                }
                Debug.print("Number of Record Exist : "+cnt);
                prepStmt.close();
                releaseConnection();
     }catch(Exception e){
         releaseConnection();
         Debug.print(" Error : "+e.getMessage());
         e.printStackTrace();
     }
     if (cnt > 0)
         bol = true;
     return bol;
 }
 
 /**
 * Name         :isEventDivisionEditExist
 * Description  :This method is used to check if the record exist with that name in the tblMeeEventTypeMaster table
 * @ param      :divisionName,divsionTypeId
 * @return      :boolean
 * @throws      :SQLException
 */    
    
 public boolean isEventDivisionEditExist(String divisionName, String divsionTypeId) throws SQLException {
      Debug.print("isEventDivisionExist in CRUDMasterDAO"  + divsionTypeId);
     boolean bol = false;
     int cnt = 0;
     try{
         makeConnection();
         String selectStr = "Select event_division_id from "+DBHelper.USEA_MRO_DIVISION_MASTER +
             "  WHERE event_division_name = ? and event_division_id!=?";

            PreparedStatement prepStmt = con.prepareStatement(selectStr);
            prepStmt.setString(1,divisionName);
            prepStmt.setString(2,divsionTypeId);
            ResultSet rs = prepStmt.executeQuery();

            if(rs.next()){
                bol=true;
            }
            
            prepStmt.close();
            releaseConnection();
     }catch(Exception e){
         releaseConnection();
         Debug.print(" Error : "+e.getMessage());
         e.printStackTrace();
     }
    return bol;
 }
 
/**
 * Name         :insertEventDivision
 * Description  :This method is used to insert record into tblMeeEventTypeMaster table
 * @ param      :eventTypeName
 * @return      :boolean
 * @throws      :SQLException
 */

    public boolean insertEventDivision( String divisionName) throws SQLException {
        Debug.print("insertEventDivision in CRUDMasterDAO"  + divisionName);
        boolean stat = false; 
        int cnt = 0;
        makeConnection();

        String insertStatement =
            "insert into "+DBHelper.USEA_MRO_DIVISION_MASTER +" (event_division_name) values (?)";
        PreparedStatement prepStmt = con.prepareStatement(insertStatement);
        try {
                prepStmt.setString(1,divisionName);
                cnt = prepStmt.executeUpdate();
                Debug.print(" Inserted Successfully into Event Type Master : "+cnt);
        }catch (SQLException e) { 
                Debug.print(" Error While inserting in insertEventDivision in CRUDMasterDAO : "+e.getMessage());
                e.printStackTrace();
        }finally {
            prepStmt.close();
            releaseConnection();
        }
        if (cnt > 0)
            stat = true;
        
        return stat;
    }    
    
/**
 * Name         :deletetEventDivision
 * Description  :This method is used to delete a record based on the Event Type id from tblMeeEventTypeMaster table
 * @ param      :divisionId
 * @return      :boolean
 * @throws      :SQLException
 */

    public boolean deletetEventDivision(String divisionId) throws SQLException {
         Debug.print("deletetEventDivision in CRUDMasterDAO"  + divisionId);
        boolean bol = false;
        makeConnection();
        PreparedStatement psDelete = null;
        int intChkReturn = 0;
        try {
            StringBuffer sbString = new StringBuffer();
            sbString.append("UPDATE "+DBHelper.USEA_MRO_DIVISION_MASTER);
            sbString.append("  SET active_status = ? WHERE event_division_id =?");
            psDelete = con.prepareStatement(sbString.toString());
            psDelete.setBoolean(1, false);
            psDelete.setString(2, divisionId);
            intChkReturn = psDelete.executeUpdate();
            Debug.print(" Succeffully Deleted Event Type Master : "+intChkReturn);
            bol = true;
        }
        catch (Exception ex) {
             releaseConnection();
             Debug.print("Error:  "+ex.getMessage());
        }
        finally {
             psDelete.close();
             releaseConnection();
        }
        return bol;
    }
/**
 * Name         :activateEventDivison
 * Description  :This method is used to delete a record based on the event Type id from tblMeeEventTypeMaster table
 * @ param      :eventTypeId
 * @return      :boolean
 * @throws      :SQLException
 */

    public boolean activateEventDivison(String divisionId) throws SQLException {
        Debug.print("activateEventDivison in CRUDMasterDAO"  + divisionId);
        boolean bol = false;
        makeConnection();
        PreparedStatement psDelete = null;
        int intChkReturn = 0;
        try {
            StringBuffer sbString = new StringBuffer();
            sbString.append("UPDATE "+DBHelper.USEA_MRO_DIVISION_MASTER);
            sbString.append("  SET active_status = ? WHERE event_division_id =?");
            psDelete = con.prepareStatement(sbString.toString());
            psDelete.setBoolean(1, true);
            psDelete.setString(2, divisionId);
            intChkReturn = psDelete.executeUpdate();
            Debug.print(" Succeffully activated Event Type Master : "+intChkReturn);
            bol = true;
        }
        catch (Exception ex) {
             releaseConnection();
             Debug.print("Error:  "+ex.getMessage());
        }
        finally {
             psDelete.close();
             releaseConnection();
        }
        return bol;
    }    
	
    
   /** Name		:updateEventDivsion
     * Description	:This method will be used to update the tblMeeEventTypeMaster table
     * 				 
     * Exception	:SQLException
     * @param   	:eventTypeName, eventTypeId
     * @return  	:boolean value
     */
    public boolean updateEventDivsion(String divisionName, String divisionId) throws SQLException {
        Debug.print("updateEventDivsion in CRUDMasterDAO"  + divisionId);
        boolean bol = false;
        makeConnection();
        PreparedStatement psUpdate = null;
        int intRtrnStatus = 0;
        StringBuffer eventType = new StringBuffer();
        try {
            eventType.append(" UPDATE "+DBHelper.USEA_MRO_DIVISION_MASTER);
            eventType.append(" SET event_division_name = ? ");
            eventType.append(" WHERE event_division_id = ? ");
            psUpdate = con.prepareStatement(eventType.toString());
            

            psUpdate.setString(1, divisionName);
            psUpdate.setString(2, divisionId);

            intRtrnStatus = psUpdate.executeUpdate();
            Debug.print(" Succefully Update Event Type Master : "+intRtrnStatus);
            if(intRtrnStatus >=1){
                bol = true;
            }
        }
        catch (Exception e) {
            releaseConnection();
            Debug.print(e.getMessage(), e);
        }
        finally {
            psUpdate.close();
            releaseConnection();
        }
        return bol;
    }
        

/** Name	:loadEventDivision
  * Description	:This method will be used to display the content of tblMeeEventTypeMaster based on eventTypeId
  * 				 
  * Exception	:SQLException
  * @param   	:eventTypeId
  * @return  	:Vector
  */
    public Vector loadEventDivision(String divisionId) throws SQLException {
         Debug.print("loadEventDivision in CRUDMasterDAO"  + divisionId);
        Vector vecObj = new Vector();
        try {
                makeConnection();
                String selectStr = "select event_division_id, event_division_name, active_status,add_date from "+
                DBHelper.USEA_MRO_DIVISION_MASTER+" where event_division_id = ? ";
                PreparedStatement prepStmt = con.prepareStatement(selectStr);
                prepStmt.setString(1,divisionId);
                Debug.print("selectStr:" + selectStr);
                ResultSet rs = prepStmt.executeQuery();
                while (rs.next()){
                    String divisionID =rs.getString(1);
                    String divisionName = rs.getString(2);
                    String activeStatus = rs.getString(3);
                    String AddDate = rs.getString(4);
                    String [] divisionTypeName = {divisionID, divisionName,activeStatus,AddDate};
                    vecObj.add(divisionTypeName);
                }
                prepStmt.close();
                releaseConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return vecObj;
    }    
/** Name	:loadAllEventDivision
  * Description	:This method will be used to display the the record from the table tblMeeEventTypeMaster 
  * 				 
  * Exception	:SQLException
  * @param   	:None
  * @return  	:Vector
  */
    public Vector loadAllEventDivision(boolean status) throws SQLException {
        Vector vecObj = new Vector();
        try {
                makeConnection();
                System.out.println("Inside the Loop loadAllEventTypeMaster");
                String tempDivisionId = "select event_division_id from "+DBHelper.USEA_MRO_DIVISION_MASTER;
                PreparedStatement prepStmt1 = con.prepareStatement(tempDivisionId);
                ResultSet rs1 = prepStmt1.executeQuery();
                System.out.println("Refund Rule Id Inside the loadAllEventTypeMaster tempDivisionId: " + tempDivisionId);
                while (rs1.next()){
                    String divisionId = rs1.getString(1);
                    Debug.print("Refund Rule Id Inside the loadAllEventTypeMaster  "+divisionId);
                    String selectStr = "select event_division_id, event_division_name, active_status,add_date from "+
                    DBHelper.USEA_MRO_DIVISION_MASTER +" where event_division_id = ? and active_status = ?";
                    PreparedStatement prepStmt = con.prepareStatement(selectStr);
                    prepStmt.setString(1,divisionId);
                    prepStmt.setBoolean(2,status);
                     
                    ResultSet rs = prepStmt.executeQuery();
                    while (rs.next()){
                        String divisionID =rs.getString(1);
                        String divisionName = rs.getString(2);
                        String activeStatus = rs.getString(3);
                        String AddDate = rs.getString(4);
                        String [] divisionTypeName = {divisionID, divisionName,activeStatus,AddDate};
                        vecObj.add(divisionTypeName);
                    }
                    prepStmt.close();
                }
                prepStmt1.close();
                releaseConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return vecObj;
    } 
    /** Name	:loadAllEventDivision
  * Description	:This method will be used to display the the record from the table tblMeeEventTypeMaster 
  * 				 
  * Exception	:SQLException
  * @param   	:None
  * @return  	:Vector
  */
    public Vector loadAllEventDivision() throws SQLException {
        Vector vecObj = new Vector();
        
         try {
            makeConnection();
            String selectStatement = "select event_division_id, event_division_name, active_status,add_date from "+
                    DBHelper.USEA_MRO_DIVISION_MASTER;
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            ResultSet rs = prepStmt.executeQuery();

            while(rs.next()){
                        String divisionID =rs.getString(1);
                        String divisionName = rs.getString(2);
                        String activeStatus = rs.getString(3);
                        String AddDate = rs.getString(4);
                        String [] divisionTypeName = {divisionID, divisionName,activeStatus,AddDate};
                        vecObj.add(divisionTypeName);
            }
            prepStmt.close();
            releaseConnection();
        }
    
        catch(Exception e) {
            e.printStackTrace();
        }
        return vecObj;
    } 
    /*---------------------------- EVENT TYPE MASTER CRUD Operation------------------------*/
    
/**
 * Name         :isEventTypeMasterRecordExist
 * Description  :This method is used to check if the record exist with that name in the tblMeeEventTypeMaster table
 * @ param      :eventTypeName
 * @return      :boolean
 * @throws      :SQLException
 */    
    
 public boolean isEventTypeMasterRecordExist(String eventTypeName) throws SQLException {
     boolean bol = false;
     int cnt = 0;
     try{
         makeConnection();
         String selectStr = "Select count(*) as cnt from "+DBHelper.USEA_MRO_TYPE_MASTER +
             "  WHERE event_type_name = ?";
         PreparedStatement prepStmt = con.prepareStatement(selectStr);
                prepStmt.setString(1,eventTypeName);
                ResultSet rs = prepStmt.executeQuery();
                if(rs.next()){
                    cnt =Integer.parseInt(rs.getString(1));
                }
                Debug.print("Number of Record Exist : "+cnt);
                prepStmt.close();
                releaseConnection();
     }catch(Exception e){
         releaseConnection();
         Debug.print(" Error : "+e.getMessage());
         e.printStackTrace();
     }
     if (cnt > 0)
         bol = true;
     return bol;
 }
 
 /**
 * Name         :isEventTypeMasterRecordEditExist
 * Description  :This method is used to check if the record exist with that name in the tblMeeEventTypeMaster table
 * @ param      :eventTypeName
 * @return      :boolean
 * @throws      :SQLException
 */    
    
 public boolean isEventTypeMasterRecordEditExist(String eventTypeName, String eventTypeId) throws SQLException {
     boolean bol = false;
     int cnt = 0;
     try{
         makeConnection();
         String selectStr = "Select event_type_id from "+DBHelper.USEA_MRO_TYPE_MASTER +
             "  WHERE event_type_name = ? and event_type_id!=?";
         Debug.print(selectStr);
         PreparedStatement prepStmt = con.prepareStatement(selectStr);
            prepStmt.setString(1,eventTypeName);
            prepStmt.setString(2,eventTypeId);
            ResultSet rs = prepStmt.executeQuery();
            bol = rs.next();
            Debug.print("Record Already Exist : "+bol);
            prepStmt.close();
            releaseConnection();
     }catch(Exception e){
         releaseConnection();
         Debug.print(" Error : "+e.getMessage());
         e.printStackTrace();
     }
    return bol;
 }
 
/**
 * Name         :insertEventTypeMaster
 * Description  :This method is used to insert record into tblMeeEventTypeMaster table
 * @ param      :eventTypeName
 * @return      :boolean
 * @throws      :SQLException
 */

    public boolean insertEventTypeMaster( String eventTypeName) throws SQLException {
       
        boolean stat = false; 
        int cnt = 0;
        makeConnection();

        String insertStatement =
            "insert into "+DBHelper.USEA_MRO_TYPE_MASTER +" (event_type_name) values (?)";
        PreparedStatement prepStmt = con.prepareStatement(insertStatement);
        try {
                prepStmt.setString(1,eventTypeName);
                cnt = prepStmt.executeUpdate();
                Debug.print(" Inserted Successfully into Event Type Master : "+cnt);
        }catch (SQLException e) { 
                Debug.print(" Error While inserting in insertEventTypeMaster in CRUDMasterDAO : "+e.getMessage());
                e.printStackTrace();
        }finally {
            prepStmt.close();
            releaseConnection();
        }
        if (cnt > 0)
            stat = true;
        
        return stat;
    }    
    
/**
 * Name         :deletetEventTypeMaster
 * Description  :This method is used to delete a record based on the Event Type id from tblMeeEventTypeMaster table
 * @ param      :eventTypeId
 * @return      :boolean
 * @throws      :SQLException
 */

    public boolean deletetEventTypeMaster(String eventTypeId) throws SQLException {
        boolean bol = false;
        makeConnection();
        PreparedStatement psDelete = null;
        int intChkReturn = 0;
        try {
            StringBuffer sbString = new StringBuffer();
            sbString.append("UPDATE "+DBHelper.USEA_MRO_TYPE_MASTER);
            sbString.append("  SET active_status = ? WHERE event_type_id =?");
            psDelete = con.prepareStatement(sbString.toString());
            psDelete.setBoolean(1, false);
            psDelete.setString(2, eventTypeId);
            intChkReturn = psDelete.executeUpdate();
            Debug.print(" Succeffully Deleted Event Type Master : "+intChkReturn);
            bol = true;
        }
        catch (Exception ex) {
             releaseConnection();
             Debug.print("Error:  "+ex.getMessage());
        }
        finally {
             psDelete.close();
             releaseConnection();
        }
        return bol;
    }
/**
 * Name         :activateEventTypeMaster
 * Description  :This method is used to delete a record based on the event Type id from tblMeeEventTypeMaster table
 * @ param      :eventTypeId
 * @return      :boolean
 * @throws      :SQLException
 */

    public boolean activateEventTypeMaster(String eventTypeId) throws SQLException {
        boolean bol = false;
        makeConnection();
        PreparedStatement psDelete = null;
        int intChkReturn = 0;
        try {
            StringBuffer sbString = new StringBuffer();
            sbString.append("UPDATE "+DBHelper.USEA_MRO_TYPE_MASTER);
            sbString.append("  SET active_status = ? WHERE event_type_id =?");
            psDelete = con.prepareStatement(sbString.toString());
            psDelete.setBoolean(1, true);
            psDelete.setString(2, eventTypeId);
            intChkReturn = psDelete.executeUpdate();
            Debug.print(" Succeffully activated Event Type Master : "+intChkReturn);
            bol = true;
        }
        catch (Exception ex) {
             releaseConnection();
             Debug.print("Error:  "+ex.getMessage());
        }
        finally {
             psDelete.close();
             releaseConnection();
        }
        return bol;
    }    
	
    
   /** Name		:updateEventTypeMaster
     * Description	:This method will be used to update the tblMeeEventTypeMaster table
     * 				 
     * Exception	:SQLException
     * @param   	:eventTypeName, eventTypeId
     * @return  	:boolean value
     */
    public boolean updateEventTypeMaster(String eventTypeName, String eventTypeId) throws SQLException {
        boolean bol = false;
        makeConnection();
        PreparedStatement psUpdate = null;
        int intRtrnStatus = 0;
        StringBuffer eventType = new StringBuffer();
        try {
            eventType.append(" UPDATE "+DBHelper.USEA_MRO_TYPE_MASTER);
            eventType.append(" SET event_type_name = ? ");
            eventType.append(" WHERE event_type_id = ? ");
            psUpdate = con.prepareStatement(eventType.toString());
            

            psUpdate.setString(1,eventTypeName);
            psUpdate.setString(2, eventTypeId);

            intRtrnStatus = psUpdate.executeUpdate();
            Debug.print(" Succefully Update Event Type Master : "+intRtrnStatus);
            if(intRtrnStatus >=1){
                bol = true;
            }
        }
        catch (Exception e) {
            releaseConnection();
            Debug.print(e.getMessage(), e);
        }
        finally {
            psUpdate.close();
            releaseConnection();
        }
        return bol;
    }
        

/** Name	:loadEventTypeMaster
  * Description	:This method will be used to display the content of tblMeeEventTypeMaster based on eventTypeId
  * 				 
  * Exception	:SQLException
  * @param   	:eventTypeId
  * @return  	:Vector
  */
    public Vector loadEventTypeMaster(String eventTypeId) throws SQLException {
        Vector vecObj = new Vector();
        try {
                makeConnection();
                String selectStr = "select event_type_id, event_type_name, active_status,add_date from "+
                DBHelper.USEA_MRO_TYPE_MASTER+" where event_type_id = ? ";
                PreparedStatement prepStmt = con.prepareStatement(selectStr);
                prepStmt.setString(1,eventTypeId);
                //prepStmt.setBoolean(2,true);
                ResultSet rs = prepStmt.executeQuery();
                while (rs.next()){
                    String eventID =rs.getString(1);
                    String eventName = rs.getString(2);
                    String activeStatus = rs.getString(3);
                    String AddDate = rs.getString(4);
                    String [] eventTypeName = {eventID, eventName,activeStatus,AddDate};
                    vecObj.add(eventTypeName);
                }
                prepStmt.close();
                releaseConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return vecObj;
    }    
/** Name	:loadAllEventTypeMaster
  * Description	:This method will be used to display the the record from the table tblMeeEventTypeMaster 
  * 				 
  * Exception	:SQLException
  * @param   	:None
  * @return  	:Vector
  */
    public Vector loadAllEventTypeMaster(boolean status) throws SQLException {
        Vector vecObj = new Vector();
        try {
                makeConnection();
                System.out.println("Inside the Loop loadAllEventTypeMaster");
                String selectStr = "select event_type_id, event_type_name, active_status,add_date from "+ DBHelper.USEA_MRO_TYPE_MASTER ;
                PreparedStatement prepStmt = con.prepareStatement(selectStr);
                     
                ResultSet rs = prepStmt.executeQuery();
                while (rs.next()){
                    String eventTypeID =rs.getString(1);
                    String eventName = rs.getString(2);
                    String activeStatus = rs.getString(3);
                    String AddDate = rs.getString(4);
                    String [] eventTypeName = {eventTypeID, eventName,activeStatus,AddDate};
                    vecObj.add(eventTypeName);
                }
                
                prepStmt.close();
                releaseConnection();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return vecObj;
    } 
    
  /*----------------------------DIVISION EVENT LEVEL MASTER CRUD Operation------------------------*/
    
/**
 * Name         :isEventLevelMasterRecordExist
 * Description  :This method is used to check if the record exist with that name in the tblMeeEventLevelMaster table
 * @ param      :eventLevelName
 * @return      :boolean
 * @throws      :SQLException
 */    
    
 public boolean isEventLevelMasterRecordExist(String eventLevelName, String eventLevelCode) throws SQLException {
     boolean bol = false;
     int cnt = 0;
     try{
         makeConnection();
         String selectStr = "Select event_level_id from "+DBHelper.USEA_MRO_LEVEL_MASTER +
             "  WHERE event_level_name = ? AND event_level_code = ? ";
         PreparedStatement prepStmt = con.prepareStatement(selectStr);
                prepStmt.setString(1,eventLevelName);
                prepStmt.setString(2,eventLevelCode);
                ResultSet rs = prepStmt.executeQuery();
                bol = rs.next();
                Debug.print("Number of Record Exist : "+cnt);
                prepStmt.close();
                releaseConnection();
     }catch(Exception e){
         releaseConnection();
         Debug.print(" Error : "+e.getMessage());
         e.printStackTrace();
     }
    return bol;
 }
 
 
 /**
 * Name         :isEventLevelMasterRecordExist
 * Description  :This method is used to check if the record exist with that name in the tblMeeEventLevelMaster table
 * @ param      :eventLevelName
 * @return      :boolean
 * @throws      :SQLException
 */    
    
 public boolean isEventLevelMasterRecordEditExist(String eventLevelId, String eventLevelName, String eventLevelCode) throws SQLException {
     boolean bol = false;
     int cnt = 0;
     try{
         makeConnection();
         String selectStr = "Select event_level_id from "+DBHelper.USEA_MRO_LEVEL_MASTER +
             "  WHERE event_level_name = ? AND event_level_code = ? and event_level_id != ?";
         PreparedStatement prepStmt = con.prepareStatement(selectStr);
                prepStmt.setString(1,eventLevelName);
                prepStmt.setString(2,eventLevelCode);
                prepStmt.setString(3,eventLevelId);
                
                ResultSet rs = prepStmt.executeQuery();
                bol = rs.next();
                Debug.print("Number of Record Exist : "+bol);
                prepStmt.close();
                releaseConnection();
     }catch(Exception e){
         releaseConnection();
         Debug.print(" Error : "+e.getMessage());
         e.printStackTrace();
     }
     return bol;
 }
 
 
/**
 * Name         :insertEventLevelMaster
 * Description  :This method is used to insert record into tblMeeEventLevelMaster table
 * @ param      :eventLevelName
 * @return      :boolean
 * @throws      :SQLException
 */

    public boolean insertEventLevelMaster( String eventLevelName, String eventLevelCode, String jumpingEfforts) throws SQLException {
       
        boolean stat = false; 
        int cnt = 0;
        makeConnection();

        String insertStatement =
            "insert into "+DBHelper.USEA_MRO_LEVEL_MASTER +" (event_level_name, event_level_code, jumping_efforts) values (?,?,?)";
        PreparedStatement prepStmt = con.prepareStatement(insertStatement);
        try {
                prepStmt.setString(1,eventLevelName);
                prepStmt.setString(2,eventLevelCode);
                prepStmt.setString(3,jumpingEfforts);
                
                cnt = prepStmt.executeUpdate();
                Debug.print(" Inserted Successfully into Event Level Master : "+cnt);
        }catch (SQLException e) { 
                Debug.print(" Error While inserting in insertEventLevelMaster in CRUDMasterDAO : "+e.getMessage());
                e.printStackTrace();
        }finally {
            prepStmt.close();
            releaseConnection();
        }
        if (cnt > 0)
            stat = true;
        
        return stat;
    }    
    
/**
 * Name         :deletetEventLevelMaster
 * Description  :This method is used to delete a record based on the Event Type id from tblMeeEventLevelMaster table
 * @ param      :eventTypeId
 * @return      :boolean
 * @throws      :SQLException
 */

    public boolean deletetEventLevelMaster(String eventLevelId) throws SQLException {
        boolean bol = false;
        makeConnection();
        PreparedStatement psDelete = null;
        int intChkReturn = 0;
        try {
            StringBuffer sbStr = new StringBuffer();
            sbStr.append("UPDATE "+DBHelper.USEA_MRO_LEVEL_MASTER);
            sbStr.append("  SET active_status = ? WHERE event_level_id =?");
            Debug.print(" Delete string : "+sbStr.toString());
            psDelete = con.prepareStatement(sbStr.toString());
            psDelete.setBoolean(1, false);
            psDelete.setString(2, eventLevelId);
            intChkReturn = psDelete.executeUpdate();
            Debug.print(" Succeffully Deleted Event Level Master : "+intChkReturn);
            if (intChkReturn > 0 )
                bol = true;
        }
        catch (Exception ex) {
             releaseConnection();
             Debug.print("Error:  "+ex.getMessage());
        }
        finally {
             psDelete.close();
             releaseConnection();
        }
        return bol;
    }
/**
 * Name         :activateEventLevelMaster
 * Description  :This method is used to delete a record based on the event level id from tblMeeEventLevelMaster table
 * @ param      :eventLevelId
 * @return      :boolean
 * @throws      :SQLException
 */

    public boolean activateEventLevelMaster(String eventLevelId) throws SQLException {
        boolean bol = false;
        makeConnection();
        PreparedStatement psDelete = null;
        int intChkReturn = 0;
        try {
            StringBuffer sbString = new StringBuffer();
            sbString.append("UPDATE "+DBHelper.USEA_MRO_LEVEL_MASTER);
            sbString.append("  SET active_status = ? WHERE event_level_id =?");
            psDelete = con.prepareStatement(sbString.toString());
            psDelete.setBoolean(1, true);
            psDelete.setString(2, eventLevelId);
            intChkReturn = psDelete.executeUpdate();
            Debug.print(" Succeffully activated Event Level Master : "+intChkReturn);
            bol = true;
        }
        catch (Exception ex) {
             releaseConnection();
             Debug.print("Error:  "+ex.getMessage());
        }
        finally {
             psDelete.close();
             releaseConnection();
        }
        return bol;
    }    
	
    
   /** Name		:updateEventLevelMaster
     * Description	:This method will be used to update the tblMeeEventLevelMaster table
     * 				 
     * Exception	:SQLException
     * @param   	:eventLevelName, eventLevelId
     * @return  	:boolean value
     */
    public boolean updateEventLevelMaster(String eventLevelName, String eventLevelCode, String jumpingEfforts,String eventLevelId) throws SQLException {
        boolean bol = false;
        makeConnection();
        PreparedStatement psUpdate = null;
        int intRtrnStatus = 0;
        StringBuffer eventLevel = new StringBuffer();
        try {
            eventLevel.append(" UPDATE "+DBHelper.USEA_MRO_LEVEL_MASTER);
            eventLevel.append(" SET event_level_name = ?, event_level_code = ?, jumping_efforts = ? ");
            eventLevel.append(" WHERE event_level_id = ? ");
            psUpdate = con.prepareStatement(eventLevel.toString());

            psUpdate.setString(1,eventLevelName);
            psUpdate.setString(2,eventLevelCode);
            psUpdate.setString(3,jumpingEfforts);
            psUpdate.setString(4, eventLevelId);

            intRtrnStatus = psUpdate.executeUpdate();
            Debug.print(" Succefully Update Event Level Master : "+intRtrnStatus);
            if(intRtrnStatus>=1){
                bol = true;
            }
        }
        catch (Exception e) {
            releaseConnection();
            Debug.print(e.getMessage(), e);
        }
        finally {
            psUpdate.close();
            releaseConnection();
        }
        return bol;
    }
        

/** Name	:loadEventLevelMaster
  * Description	:This method will be used to display the content of tblMeeEventLevelMaster based on eventTypeId
  * 				 
  * Exception	:SQLException
  * @param   	:eventLevelId
  * @return  	:Vector
  */
    public Vector loadEventLevelMaster(String eventLevelId) throws SQLException {
        Vector vecObj = new Vector();
        try {
                makeConnection();
                String selectStr = "select event_level_id, event_level_name, event_level_code,jumping_efforts,active_status,add_date from "+
                DBHelper.USEA_MRO_LEVEL_MASTER+" where event_level_id = ? ";
                PreparedStatement prepStmt = con.prepareStatement(selectStr);
                prepStmt.setString(1,eventLevelId);
                //prepStmt.setBoolean(2,true);
                ResultSet rs = prepStmt.executeQuery();
                while (rs.next()){
                    String eventLevelID =rs.getString(1);
                    String eventLevelName = rs.getString(2);
                    String eventLevelCode = rs.getString(3);
                    String jumpingEffort = rs.getString(4);
                    String activeStatus = rs.getString(5);
                    String AddDate = rs.getString(6);
                    String [] eventTypeName = {eventLevelID,eventLevelName, eventLevelCode, jumpingEffort,activeStatus,AddDate};
                    vecObj.add(eventTypeName);
                }
                prepStmt.close();
                releaseConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return vecObj;
    }    
/** Name	:loadAllEventLevelMaster
  * Description	:This method will be used to display the the record from the table tblMeeEventTypeMaster 
  * 				 
  * Exception	:SQLException
  * @param   	:None
  * @return  	:Vector
  */
    public Vector loadAllEventLevelMaster(boolean status) throws SQLException {
        Vector vecObj = new Vector();
        try {
                makeConnection();
                    String selectStr = "select event_level_id, event_level_name, event_level_code,jumping_efforts, active_status,add_date from "+
                    DBHelper.USEA_MRO_LEVEL_MASTER;
                    PreparedStatement prepStmt = con.prepareStatement(selectStr);
                     
                    ResultSet rs = prepStmt.executeQuery();
                    while (rs.next()){
                        String eventLevelID =rs.getString(1);
                        String eventLevelName = rs.getString(2);
                        String eventLevelCode = rs.getString(3);
                        String jumpingEffort = rs.getString(4);
                        String activeStatus = rs.getString(5);
                        String AddDate = rs.getString(6);
                        String [] eventTypeName = {eventLevelID,eventLevelName, eventLevelCode, jumpingEffort,activeStatus,AddDate};
                        vecObj.add(eventTypeName);
                    }
                    prepStmt.close();
                releaseConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return vecObj;
    } 
    
    
  /*----------------------------END Of the Oficials CRUD Operation------------------------*/  
/**************************************For Organizer Event List==========================================*/        
 public ArrayList getAllOrgEventDetails(String organizerId){
        Debug.print("CRUDMasterDAO.getAllOrgEventDetails() ownerId:" + organizerId);
        ArrayList eventList = new ArrayList();
        PreparedStatement prepStmt = null;
        boolean isExist=false;
        boolean isEnryFeeExist=false;
        String provisionalId=null;
        makeConnection();
        java.sql.Date tempDate = new java.sql.Date((new java.util.Date()).getTime());
        String currentDate = String.valueOf(tempDate).substring(5, 7) + "/" + String.valueOf(tempDate).substring(8, 10) + "/" + String.valueOf(tempDate).substring(0, 4);
        
        try {
            String selectStatement = "select A.event_id, A.event_title, A.event_begin_date, B.area_name, A.status_id, C.request_status, C.payment_id, A.add_date "+  
                                     "from tblMeeEventDetails A inner join tblMeeAreaMaster B ON "+
                                     "B.area_id = A.championship_area left join tblMeeEventOrganizerRenewalDetails C ON "+
                                     "C.event_id = A.event_id WHERE A.organizer_id = ? and A.event_end_date >=? order by A.event_begin_date asc";
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,organizerId);
            prepStmt.setString(2,currentDate);
                     
            ResultSet rs = prepStmt.executeQuery();
            while(rs.next()){
                HLCEventDetailsVO objVO = new HLCEventDetailsVO();
                String eventId = rs.getString(1);
            if(eventId!=null){
            HLCCRUDMasterDAO objDAO = new HLCCRUDMasterDAO();
            isExist=objDAO.isOrgEventDetExist(eventId);
            provisionalId=objDAO.getProvIdForOrgMeetList(eventId);
             isEnryFeeExist=objDAO.isEntryFeeDetExists(eventId);
            }    
                String eventTitle = rs.getString(2);
                Date eveBegDate = rs.getDate(3);
                String areaName = rs.getString(4);
                String statusId = rs.getString(5);
                String requestStatus=rs.getString(6);
                String payId=rs.getString(7);
                Date addDt=rs.getDate(8);
                
                objVO.setEventId(eventId);
                objVO.setEventTitle(eventTitle);
                objVO.setEveBegDt(eveBegDate);
                objVO.setAreaName(areaName);
                objVO.setStatusId(statusId);
                objVO.setRequestStatus(requestStatus);
                objVO.setPaymentId(payId);
                objVO.setAddDate(addDt);
                objVO.setPayExist(isExist);
                objVO.setProvisId(provisionalId);
                objVO.setEntryFeeExist(isEnryFeeExist);
                eventList.add(objVO);
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in CRUDMasterDAO.getAllOrgEventDetails():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in CRUDMasterDAO.getAllOrgEventDetails():" + e.getMessage());
        }
        Debug.print("Size of Event List:" + eventList.size());
        return eventList;
    } 
  
  public boolean isOrgEventDetExist(String eventId) throws SQLException {
        Debug.print("CRUDMasterDAO.isOrgEventDetExist():");
        boolean result = false;
        PreparedStatement prepStmt = null;
        try {
            makeConnection();
            
            String selectStatement = "SELECT event_id FROM tblMeeEventOrganizerRenewalDetails WHERE  event_id= ? ";
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,eventId);
            
            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()) {
                result = true;
            }
            rs.close();
            Debug.print("CRUDMasterDAO isOrgEventDetExist() result: " + result);
            prepStmt.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in CRUDMasterDAO.isOrgEventDetExist():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in CRUDMasterDAO.isOrgEventDetExist():" + e.getMessage());
        }
        return result;
    }  
    public String isOrgMainEventRegExist(String eventId) throws SQLException {
        Debug.print("CRUDMasterDAO.isOrgMainEventRegExist():");
        boolean result = false;
        String eveSecId="";
        PreparedStatement prepStmt = null;
        try {
            makeConnection();
            
            String selectStatement = "SELECT event_secretary_id FROM tblMeeEventDetails WHERE  event_id= ? ";
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,eventId);
            
            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()) {
                result = true;
                eveSecId=rs.getString(1);
            }
            rs.close();
            Debug.print("CRUDMasterDAO isOrgMainEventRegExist() result: " + result);
            prepStmt.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in CRUDMasterDAO.isOrgMainEventRegExist():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in CRUDMasterDAO.isOrgMainEventRegExist():" + e.getMessage());
        }
        return eveSecId;
    }  
  public HLCEndorsedFormVO getSingleEndorsedFormDetails(String eventId) throws SQLException {
        HLCEndorsedFormVO endorseVO = new HLCEndorsedFormVO();
        Debug.print("Inside DAO");
        try {
            makeConnection();
            
            String selStatement = "select A.renewal_id, A.event_id, A.organizer_id, A.competition_name, " +
                    " A.competition_date, A.com_management_name, A.com_management_address, A.com_management_city," +
                    " A.com_management_state, A.com_management_country, A.com_management_zip, A.com_management_phone," +
                    " A.com_management_fax, A.manager_usef_member_id, A.manager_usea_member_id, A.manager_name, " +
                    " A.secretary_usef_member_id, A.secretary_name, A.request_status, A.active_status, A.add_date, " +
                    " B.competition_location, B.competition_city, B.competition_state, B.competition_country, " +
                    " B.competition_zip, B.championship_area, C.payment_id, C.cc_name, C.cc_type, C.cc_number, C.cc_exp_month, " +
                    " C.cc_exp_year, C.cc_cvvid, C.bank_name, C.check_date, C.check_number, C.check_name, C.check_amount, " +
                    " C.amount, C.payment_date, C.payment_status, C.pending_amount, C.ssl_txn_id, C.ssl_email, " +
                    " C.ip_address, C.user_comments, C.staff_comments, D.state_name, E.area_name from " +
                    " tblMeeEventOrganizerRenewalDetails A, tblMeeEventDetails B, tblUserPaymentDetails C, " +
                    " tblMeeStateMaster D, tblMeeAreaMaster E where A.event_id=B.event_id and A.payment_id=C.payment_id " +
                    " and B.competition_state=D.state_id and B.championship_area=E.area_id and A.event_id= ?";
            
            Debug.print("Query :"+selStatement);
            PreparedStatement prepStmt = con.prepareStatement(selStatement);
            prepStmt.setString(1,eventId);
            
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                endorseVO.setRenewId(rs.getString(1));
                endorseVO.setEventId(rs.getString(2));
                endorseVO.setOrgId(rs.getString(3));
                endorseVO.setCompName(rs.getString(4));
                endorseVO.setCompDate(rs.getDate(5));
                endorseVO.setCom_Mgt_Name(rs.getString(6));
                endorseVO.setCom_Mgt_Addrs(rs.getString(7));
                endorseVO.setCom_Mgt_City(rs.getString(8));
                endorseVO.setCom_Mgt_State(rs.getString(9));
                endorseVO.setCom_Mgt_Country(rs.getString(10));
                endorseVO.setCom_Mgt_Zip(rs.getString(11));
                endorseVO.setCom_Mgt_Phone(rs.getString(12));
                endorseVO.setCom_Mgt_Fax(rs.getString(13));
                endorseVO.setManager_usef_memberId(rs.getString(14));
                endorseVO.setManager_usea_memberId(rs.getString(15));
                endorseVO.setMgr_Name(rs.getString(16));
                endorseVO.setSecretary_usef_memberId(rs.getString(17));
                endorseVO.setSectryName(rs.getString(18));
                endorseVO.setReqStatus(rs.getString(19));
                endorseVO.setActiveStatus(rs.getBoolean(20));
                endorseVO.setAddDate(rs.getDate(21));
                endorseVO.setCompLocation(rs.getString(22));
                endorseVO.setCompCity(rs.getString(23));
                endorseVO.setCompState(rs.getString(24));
                endorseVO.setCompCountry(rs.getString(25));
                endorseVO.setCompZip(rs.getString(26));
                endorseVO.setCompArea(rs.getString(27));
                endorseVO.setPaymentId(rs.getString(28));
                endorseVO.setCcName(rs.getString(29));
                endorseVO.setCcType(rs.getString(30));
                endorseVO.setCcNumber(rs.getString(31));
                endorseVO.setCc_exp_Month(rs.getInt(32));
                endorseVO.setCc_exp_Year(rs.getInt(33));
                endorseVO.setCc_CvvId(rs.getInt(34));
                endorseVO.setBankName(rs.getString(35));
                endorseVO.setChkDt(rs.getDate(36));
                endorseVO.setChkNumber(rs.getInt(37));
                endorseVO.setChkName(rs.getString(38));
                endorseVO.setChkAmount(rs.getFloat(39));
                endorseVO.setAmt(rs.getFloat(40));
                endorseVO.setPaymentDate(rs.getDate(41));
                endorseVO.setPaymentStatus(rs.getString(42));
                endorseVO.setPendingAmount(rs.getFloat(43));
                endorseVO.setSslTxnId(rs.getString(44));
                endorseVO.setSslEmail(rs.getString(45));
                endorseVO.setIpAddress(rs.getString(46));
                endorseVO.setUserComments(rs.getString(47));
                endorseVO.setStaffComments(rs.getString(48));
                endorseVO.setStatName(rs.getString(49));
                endorseVO.setAreaName(rs.getString(50));
            }
            prepStmt.close();
            releaseConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return endorseVO;
    }
  
  public String insertRowPayment(HLCPaymentDetailsVO paymentVO) throws SQLException {
        Debug.print("PaymentBean insertRowPayment");
        Debug.print(" paymentVO.getCheckNumber() PaymentBean insertRowPayment"+paymentVO.getCheckNumber());
        
        boolean paymentResult = false;
        boolean result = false;
        String paymentId="";
        String ccNumber = "";
        long ccNumber1=paymentVO.getCcNumber();
        ccNumber=String.valueOf(ccNumber1);
        if (!(ccNumber.equals("0"))) {
            String temp = ccNumber.substring(0, 2);
            String temp1 = ccNumber.substring(2, 12);
            String temp2 = ccNumber.substring(12);
            temp1 = "***";
            ccNumber = temp+temp1+temp2;
            }
        try{
            paymentId = getNextId();
            Debug.print("paymentId in CRUDMasterDAO"+paymentId);
            } catch(Exception e){
            Debug.print("Exception while calling getNextId():" + e.getMessage());
            }
        if(paymentId!=null && paymentId.trim().length()!=0){
        makeConnection();
        try{
            String insertStatement = "insert into " + DBHelper.USEA_PAYMENT + " (user_id,cc_name,cc_type, cc_number, cc_exp_month ," +
                    " cc_exp_year, cc_cvvid, bank_name, check_date, check_number, amount, payment_date, payment_status, " +
                    " ssl_result, ssl_result_message, ssl_txn_id, ssl_approval_code, ssl_cvv2_response, ssl_avs_response, " +
                    " ssl_transaction_type, ssl_invoice_no, ssl_email, payment_id, check_name)" +
                      " values ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? ,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement prepStmt = con.prepareStatement(insertStatement);
            
            prepStmt.setString(1, paymentVO.getUserId());
            prepStmt.setString(2, paymentVO.getCcName());
            prepStmt.setString(3, paymentVO.getCcType());
            prepStmt.setString(4, ccNumber);
            prepStmt.setInt(5, paymentVO.getCcExpMonth() );
            prepStmt.setInt(6, paymentVO.getCcExpYear());
            prepStmt.setInt(7, paymentVO.getCcCvvid());
            prepStmt.setString(8, paymentVO.getBankName());
            if(paymentVO.getCheckDate()!=null) prepStmt.setDate(9, DBHelper.toSQLDate(paymentVO.getCheckDate()));
            else prepStmt.setDate(9, null);
            prepStmt.setString(10, String.valueOf(paymentVO.getCheckNumber()));
            prepStmt.setDouble(11, paymentVO.getAmount());
            prepStmt.setDate(12, DBHelper.toSQLDate(new Date()));
            prepStmt.setString(13, paymentVO.getPaymentStatus());
            prepStmt.setString(14, paymentVO.getSslResult());
            prepStmt.setString(15, paymentVO.getSslResultMessage());
            prepStmt.setString(16, paymentVO.getSslTxnId());
            prepStmt.setString(17, paymentVO.getSslApprovalCode());
            prepStmt.setString(18, paymentVO.getSslCvv2Response());
            prepStmt.setString(19, paymentVO.getSslAvsResponse());
            prepStmt.setString(20, paymentVO.getSslTransactionType());
            prepStmt.setString(21, paymentVO.getSslInvoiceNo());
            prepStmt.setString(22, paymentVO.getSslEmail());
            prepStmt.setString(23, paymentId);
            prepStmt.setString(24, paymentVO.getCheckName());
          
            int cnt = prepStmt.executeUpdate();
                Debug.print("Record Inserted succefully into insertRowPayment "+cnt);
                if(cnt >=1) result = true;
                Debug.print("CRUDMasterDAO insertRowPayment() Status :" + result);
                prepStmt.close();
                releaseConnection();
            
        } catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in insertRowPayment:" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in insertRowPayment:" + e.getMessage());
        }
        }
        return paymentId;
    } 
  
    public boolean updateRowPayment(HLCPaymentDetailsVO paymentVO) throws SQLException {
        Debug.print("PaymentBean updateRowPayment");
        
        boolean paymentResult = false;
        boolean result = false;
        String ccNumber = "";
        long ccNumber1=paymentVO.getCcNumber();
        ccNumber=String.valueOf(ccNumber1);
        if (!(ccNumber.equals("0"))) {
            String temp = ccNumber.substring(0, 2);
            String temp1 = ccNumber.substring(2, 12);
            String temp2 = ccNumber.substring(12);
            temp1 = "***";
            ccNumber = temp+temp1+temp2;
            }
        String paymentId=paymentVO.getPaymentId(); 
        if(paymentId!=null && paymentId.trim().length()!=0){
        makeConnection();
        try{
            String updateStatement = "update " + DBHelper.USEA_PAYMENT + " set user_id=?, cc_name=?,  cc_type=?,  cc_number=?,  cc_exp_month=?, " +
                    "  cc_exp_year=?,  cc_cvvid=?,  bank_name=?,  check_date=?,  check_number=?,  amount=?,  payment_date=?,  payment_status=?, " +
                    "  ssl_result=?,  ssl_result_message=?,  ssl_txn_id=?,  ssl_approval_code=?,  ssl_cvv2_response=?,  ssl_avs_response=?, " +
                    "  ssl_transaction_type=?,  ssl_invoice_no=?,  ssl_email=? where payment_id=? ";
            Debug.print("Query :"+updateStatement);
            PreparedStatement prepStmt = con.prepareStatement(updateStatement);
            
            prepStmt.setString(1, paymentVO.getUserId());
            prepStmt.setString(2, paymentVO.getCcName());
            prepStmt.setString(3, paymentVO.getCcType());
            prepStmt.setString(4, ccNumber);
            prepStmt.setInt(5, paymentVO.getCcExpMonth() );
            prepStmt.setInt(6, paymentVO.getCcExpYear());
            prepStmt.setInt(7, paymentVO.getCcCvvid());
            prepStmt.setString(8, paymentVO.getBankName());
            if(paymentVO.getCheckDate()!=null) prepStmt.setDate(9, DBHelper.toSQLDate(paymentVO.getCheckDate()));
            else prepStmt.setDate(9, null);
            prepStmt.setLong(10, paymentVO.getCheckNumber());
            prepStmt.setDouble(11, paymentVO.getAmount());
            prepStmt.setDate(12, DBHelper.toSQLDate(new Date()));
            prepStmt.setString(13, paymentVO.getPaymentStatus());
            prepStmt.setString(14, paymentVO.getSslResult());
            prepStmt.setString(15, paymentVO.getSslResultMessage());
            prepStmt.setString(16, paymentVO.getSslTxnId());
            prepStmt.setString(17, paymentVO.getSslApprovalCode());
            prepStmt.setString(18, paymentVO.getSslCvv2Response());
            prepStmt.setString(19, paymentVO.getSslAvsResponse());
            prepStmt.setString(20, paymentVO.getSslTransactionType());
            prepStmt.setString(21, paymentVO.getSslInvoiceNo());
            prepStmt.setString(22, paymentVO.getSslEmail());
            prepStmt.setString(23, paymentId);
          
            int cnt = prepStmt.executeUpdate();
                Debug.print("Record updated succefully into updateRowPayment "+cnt);
                if(cnt >=1) result = true;
                Debug.print("CRUDMasterDAO updateRowPayment() Status :" + result);
                prepStmt.close();
                releaseConnection();
            
        } catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in updateRowPayment:" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in updateRowPayment:" + e.getMessage());
        }
        }
        return result;
    } 
   public boolean insertEndorseRenDetails(HLCRenewalOrganizerDetails objRenewalDet, String paymentId) throws SQLException {
        Debug.print("PaymentBean insertEndorseRenDetails");
        
        boolean result = false;
        makeConnection();
        try{
            
            String insertStatement = "insert into " + DBHelper.USEA_ORG_RENEWAL + " (event_id, organizer_id, competition_name, " +
                    " competition_date, com_management_name, com_management_address, com_management_city, " +
                    "com_management_state, com_management_country, com_management_zip, com_management_phone, com_management_fax, manager_usef_member_id, manager_usea_member_id, manager_name, " +
                    "secretary_usef_member_id, secretary_name, payment_id)" +
                    " values ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? ,? , ? , ? , ?, ? ) ";

            PreparedStatement prepStmt = con.prepareStatement(insertStatement);
            prepStmt.setInt(1, objRenewalDet.getEventId());
            prepStmt.setString(2, objRenewalDet.getOrganizerId());
            prepStmt.setString(3,objRenewalDet.getCompetitionName());
            prepStmt.setDate(4, DBHelper.toSQLDate(objRenewalDet.getCompetitionDate()));
            prepStmt.setString(5, objRenewalDet.getComManagementName());
            prepStmt.setString(6,objRenewalDet.getComManagementAddress());
            prepStmt.setString(7, objRenewalDet.getComManagementCity());
            prepStmt.setString(8, objRenewalDet.getComManagementState());
            prepStmt.setString(9, objRenewalDet.getComManagementCountry());
            prepStmt.setString(10, objRenewalDet.getComManagementZip());
            prepStmt.setString(11, objRenewalDet.getComManagementPhone());
            prepStmt.setString(12, objRenewalDet.getComManagementFax());
            prepStmt.setString(13, objRenewalDet.getManagerUsefMemberId());
            prepStmt.setString(14, objRenewalDet.getManagerUseaMemberId());
            prepStmt.setString(15, objRenewalDet.getManagerName());
            prepStmt.setString(16, objRenewalDet.getSecretaryUsefMemberId());
            prepStmt.setString(17, objRenewalDet.getSecretaryName());
            prepStmt.setString(18, paymentId);
            
            int cnt = prepStmt.executeUpdate();
            
            if(cnt!=0){
                result = true;
            } else result = false;
            
            prepStmt.close();
            releaseConnection();
            Debug.print("PaymentBean Sucessfully insertRowPayment");
            
        } catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in insertRowPayment:" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in insertRowPayment:" + e.getMessage());
        }
        return result;
    }
    public boolean updateEndorseEventByAdmin(String eventId,String status) {
        Debug.print("DITRegDAO.updateEndorseEventByAdmin():");
        boolean result = false;
        PreparedStatement prepStmt = null;
        if(eventId!=null && eventId.trim().length()!=0 && status!=null && status.trim().length()!=0){
            makeConnection();
            try {
                String updateStatement = "update " + DBHelper.USEA_ORG_RENEWAL + " set request_status = ? " +
                        " where event_id = ?";
                
                prepStmt = con.prepareStatement(updateStatement);
                prepStmt.setString(1,status);
                prepStmt.setString(2,eventId);
                
                int cnt = prepStmt.executeUpdate();
                Debug.print("Record Inserted succefully into updateEndorseEventByAdmin "+cnt);
                
                if(cnt>=1){
                    result = true;
                }
                
                Debug.print("EventEntryDAO updateEndorseEventByAdmin() Status :" + result);
                prepStmt.close();
                releaseConnection();
            } catch(SQLException sql){
                releaseConnection();
                Debug.print("SQL Exception in EventEntryDAO.updateEndorseEventByAdmin():" + sql.getMessage());
            } catch(Exception e){
                releaseConnection();
                Debug.print("General Exception  in EventEntryDAO.updatePriceItemstatus():" + e.getMessage());
            }
        }
        return result;
    }
     public boolean updatePaymentIdInHist(String eventId,int year,String paymentId) {
        Debug.print("DITRegDAO.updatePaymentIdInHist():");
        boolean result = false;
        PreparedStatement prepStmt = null;
        if(eventId!=null && eventId.trim().length()!=0 && paymentId!=null && paymentId.trim().length()!=0 && year!=0){
            makeConnection();
            try {
                String updateStatement = "update " + DBHelper.USEA_EVENT_HIST_DETAILS + " set payment_id = ? " +
                        " where event_id = ? and competition_year = ? ";
                
                prepStmt = con.prepareStatement(updateStatement);
                prepStmt.setString(1,paymentId);
                prepStmt.setString(2,eventId);
                prepStmt.setInt(3,year);
                
                int cnt = prepStmt.executeUpdate();
                Debug.print("Record Inserted succefully into updatePaymentIdInHist "+cnt);
                
                if(cnt>=1){
                    result = true;
                }
                
                Debug.print("EventEntryDAO updatePaymentIdInHist() Status :" + result);
                prepStmt.close();
                releaseConnection();
            } catch(SQLException sql){
                releaseConnection();
                Debug.print("SQL Exception in EventEntryDAO.updatePaymentIdInHist():" + sql.getMessage());
            } catch(Exception e){
                releaseConnection();
                Debug.print("General Exception  in EventEntryDAO.updatePriceItemstatus():" + e.getMessage());
            }
        }
        return result;
    }
/**********************************************For Total Amount in Endorsement Form************************************/
public ArrayList getIssueIdBasedOnEventId(String eventId) throws SQLException{
        Debug.print("Inside CRUDMasterDAO" +eventId);
        ArrayList eventList = new ArrayList();
        PreparedStatement prepStmt = null;
        try {
            makeConnection();
            if(eventId!=null && eventId.trim().length()!=0){
            String selStmt1 ="select issue_id,year(event_begin_date) from " + DBHelper.USEA_MMS_EVENTDETAILS + " where event_id=? ";

            Debug.print("Query: "+selStmt1);
            
            prepStmt = con.prepareStatement(selStmt1);
            prepStmt.setString(1,eventId);
            ResultSet rs = prepStmt.executeQuery();
            
            while(rs.next()) {
                String eveId=rs.getString(1);
                String compYear = rs.getString(2);
                
                String tempList[] = {eveId, compYear};
                eventList.add(tempList);
            }
            Debug.print("CRUDMasterDAO.getIssueIdBasedOnEventId"+eventList.size());
            rs.close();
            prepStmt.close();
            }
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in CRUDMasterDAO.getIssueIdBasedOnEventId():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in CRUDMasterDAO.getIssueIdBasedOnEventId():" + e.getMessage());
        } finally {
            releaseConnection();
        }
        return eventList;
    }
 public boolean isEventRegDateValid(String seasonId,int compYear,String currentDate) throws SQLException {
        Debug.print("CRUDMasterDAO.isEventRegDateValid():");
        boolean result = false;
        PreparedStatement prepStmt = null;
        try {
            makeConnection();
            
            String selectStatement = "select event_reg_date_id from " + DBHelper.USEA_EVENT_REG_DATES + " where  " +
                    "event_grace_date>='"+currentDate+"' and issue_id='"+seasonId+"' and competition_year='"+compYear+"' ";
            
            prepStmt = con.prepareStatement(selectStatement);
            
            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()) {
                result = true;
            }
            rs.close();
            Debug.print("CRUDMasterDAO isEventRegDateValid() result: " + result);
            prepStmt.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in CRUDMasterDAO.isEventRegDateValid():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in CRUDMasterDAO.isEventRegDateValid():" + e.getMessage());
        }
        return result;
    }  
  public float getDueDatePrice(String seasonId) throws SQLException {
        Debug.print("CRUDMasterDAO.getDueDatePrice():");
        float price = 0;
        PreparedStatement prepStmt = null;
        try {
            makeConnection();
            
            String selectStatement = "select sum(due_date_price) from "+ DBHelper.USEA_EVENT_REG_PRICE_DETAILS +  
                    " where issue_id=? " ;
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,seasonId);
            
            ResultSet rs = prepStmt.executeQuery();
            while(rs.next()) {
            price=rs.getFloat(1);
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in CRUDMasterDAO.getDueDatePrice():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in CRUDMasterDAO.getDueDatePrice():" + e.getMessage());
        }
        return price;
    } 
  
   public float getAfterDueDatePrice(String seasonId) throws SQLException {
        Debug.print("CRUDMasterDAO.getAfterDueDatePrice():");
        float price = 0;
        PreparedStatement prepStmt = null;
        try {
            makeConnection();
            
            String selectStatement = "select sum(aft_due_date_price) from "+ DBHelper.USEA_EVENT_REG_PRICE_DETAILS +  
                    " where issue_id=? " ;
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,seasonId);
            
            ResultSet rs = prepStmt.executeQuery();
            while(rs.next()) {
            price=rs.getFloat(1);
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in CRUDMasterDAO.getAfterDueDatePrice():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in CRUDMasterDAO.getAfterDueDatePrice():" + e.getMessage());
        }
        return price;
    } 
   public boolean isIssueIdExists(String seasonId,int compYear) throws SQLException {
        Debug.print("CRUDMasterDAO.isIssueIdExists():");
        boolean result = false;
        PreparedStatement prepStmt = null;
        try {
            makeConnection();
            
            String selectStatement = "select event_reg_date_id  from "+DBHelper.USEA_EVENT_REG_DATES +" where issue_id= ? and competition_year=? ";            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,seasonId);
            prepStmt.setInt(2,compYear);
            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()) {
                result = true;
            }
            rs.close();
            Debug.print("CRUDMasterDAO isIssueIdExists() result: " + result);
            prepStmt.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in CRUDMasterDAO.isIssueIdExists():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in CRUDMasterDAO.isIssueIdExists():" + e.getMessage());
        }
        return result;
    }  
/*******************************************************************************************************************/  
    public String getNextId() throws CreateException {
        Debug.print("EducationalSessionBean Payment getNextId ");
        String nextPaymentId = "";
        try {
            nextPaymentId = retrieveNextId();
        }
        catch(Exception e){
            Debug.print("Exception in getNextId:" + e);
        }
        return nextPaymentId;
     }      
    
   public String retrieveNextId() throws Exception {
        Debug.print("ActivityOrganizerDAO getNextUserId");
        makeConnection();
        String nextId = null;
        try{
            String selectStatement = "select newid() as userId";
            Debug.print("ActivityOrganizerDAO getNextUserId:" + selectStatement);
            PreparedStatement prepSelect = con.prepareStatement(selectStatement);
            ResultSet rs = prepSelect.executeQuery();
            rs.next();
            nextId = rs.getString(1);
            rs.close();
            prepSelect.close();
            releaseConnection();
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in ActivityOrganizerDAO getNextUserId:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in ActivityOrganizerDAO getNextUserId:" + e.getMessage());
        }
        return nextId;
    } 
/********************************************************for dynamic content in endorse*************************************/
  public ArrayList getPriceDetsForEndorse(String issueId, int compYr) throws SQLException{
        Debug.print("Inside CRUDMasterDAO" +issueId);
        Debug.print("Inside CRUDMasterDAO" +compYr);
        ArrayList priceList = new ArrayList();
        PreparedStatement prepStmt = null;
        try {
            makeConnection();
            if(issueId!=null && issueId.trim().length()!=0 && compYr!=0){
            String selStmt1 ="select A.issue_id, B.issue_name, CONVERT(varchar,A.event_due_date,107), " +
                    " CONVERT(varchar,A.event_grace_date,107), C.event_price_type_id, " +
                    " C.due_date_price, C.aft_due_date_price, D.event_price_type_name from tblMeeEventRegistrationDates A, tblAdvIssueMaster B, " +
                    " tblMeeEventRegistrationPriceDetails C, tblMeeEventRegistrationPriceMaster D" +
                    " where A.issue_id=B.issue_id and A.issue_id=C.issue_id and " +
                    " C.event_price_type_id= D.event_price_type_id and A.issue_id= ? and A.competition_year = ?";

            Debug.print("Query: "+selStmt1);
            
            prepStmt = con.prepareStatement(selStmt1);
            prepStmt.setString(1,issueId);
            prepStmt.setInt(2,compYr);
            ResultSet rs = prepStmt.executeQuery();
            
            while(rs.next()) {
                String issId=rs.getString(1);
                String issName = rs.getString(2);
                String eveDueDt = rs.getString(3);
                String eveGrcDt = rs.getString(4);
                String evePriceId = rs.getString(5);
                float dueDtPrice = rs.getFloat(6);             
                float aftDueDtPrice = rs.getFloat(7);
                String evePriceTypName = rs.getString(8);
                String tempList[] = {issId,issName,eveDueDt,eveGrcDt,evePriceId,String.valueOf(dueDtPrice),String.valueOf(aftDueDtPrice),evePriceTypName};
                priceList.add(tempList);
            }
            Debug.print("CRUDMasterDAO.getPriceDetsForEndorse"+priceList.size());
            rs.close();
            prepStmt.close();
            }
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in CRUDMasterDAO.getPriceDetsForEndorse():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in CRUDMasterDAO.getPriceDetsForEndorse():" + e.getMessage());
        } finally {
            releaseConnection();
        }
        return priceList;
    }
  
   public String getProvIdForOrgMeetList(String eventId) throws SQLException {
        Debug.print("CRUDMasterDAO.getProvIdForOrgMeetList():");
        boolean result = false;
        String proId="";
        PreparedStatement prepStmt = null;
        try {
            makeConnection();
            
            String selectStatement = "SELECT pro_calendar_id FROM tblOEProvisionalCalendar WHERE event_id= ? ";
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,eventId);
            
            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()) {
                result = true;
                proId=rs.getString(1);
            }
            rs.close();
            //Debug.print("CRUDMasterDAO getProvIdForOrgMeetList() result: " + result);
            prepStmt.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in CRUDMasterDAO.getProvIdForOrgMeetList():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in CRUDMasterDAO.getProvIdForOrgMeetList():" + e.getMessage());
        }
        return proId;
    }  
  
   public boolean isEntryFeeDetExists(String eventId) throws SQLException {
    boolean result = false;
    Debug.print("This is from isEntryFeeDetExists");
    try  {
        makeConnection();
        String selectStatement = "select event_id from tblMeeEntryFees where event_id = ? ";
        PreparedStatement prepStmt = con.prepareStatement(selectStatement);
        prepStmt.setString(1, eventId);
        ResultSet rs = prepStmt.executeQuery();
        result = rs.next();
        prepStmt.close();
        releaseConnection();
    }
    catch(Exception ede) {
      ede.printStackTrace();
      Debug.print("Exception occur while checking existence of isEntryFeeDetExists" + ede);
    }
    return result;
    }
  public ArrayList getAllEveSecreEventDetails(String userId){
        Debug.print("CRUDMasterDAO.getAllOrgEventDetails() userId:" + userId);
        ArrayList eventList = new ArrayList();
        PreparedStatement prepStmt = null;
        boolean isExist=false;
        boolean isEnryFeeExist=false;
        String provisionalId=null;
        makeConnection();
        java.sql.Date tempDate = new java.sql.Date((new java.util.Date()).getTime());
        String currentDate = String.valueOf(tempDate).substring(5, 7) + "/" + String.valueOf(tempDate).substring(8, 10) + "/" + String.valueOf(tempDate).substring(0, 4);
        
        try {
            String selectStatement = "select A.event_id, A.event_title, A.event_begin_date, B.area_name," +
                    "A.status_id, C.request_status, C.payment_id, A.add_date " +
                    "from tblMeeEventDetails A inner join tblMeeAreaMaster B ON " +
                    "B.area_id = A.championship_area left join " +
                    "tblMeeEventOrganizerRenewalDetails C ON " +
                    "C.event_id = A.event_id left join tblMeeSecretaryRelationDetails D ON " +
                    "D.event_id = A.event_id and D.event_secretary_id = A.event_secretary_id WHERE " +
                    "D.event_secretary_id=? " +
                    "and A.event_end_date >=?";
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,userId);
            prepStmt.setString(2,currentDate);
                     
            ResultSet rs = prepStmt.executeQuery();
            while(rs.next()){
                HLCEventDetailsVO objVO = new HLCEventDetailsVO();
                String eventId = rs.getString(1);
            if(eventId!=null){
            HLCCRUDMasterDAO objDAO = new HLCCRUDMasterDAO();
            isExist=objDAO.isOrgEventDetExist(eventId);
            provisionalId=objDAO.getProvIdForOrgMeetList(eventId);
             isEnryFeeExist=objDAO.isEntryFeeDetExists(eventId);
            }    
                String eventTitle = rs.getString(2);
                Date eveBegDate = rs.getDate(3);
                String areaName = rs.getString(4);
                String statusId = rs.getString(5);
                String requestStatus=rs.getString(6);
                String payId=rs.getString(7);
                Date addDt=rs.getDate(8);
                
                objVO.setEventId(eventId);
                objVO.setEventTitle(eventTitle);
                objVO.setEveBegDt(eveBegDate);
                objVO.setAreaName(areaName);
                objVO.setStatusId(statusId);
                objVO.setRequestStatus(requestStatus);
                objVO.setPaymentId(payId);
                objVO.setAddDate(addDt);
                objVO.setPayExist(isExist);
                objVO.setProvisId(provisionalId);
                objVO.setEntryFeeExist(isEnryFeeExist);
                eventList.add(objVO);
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in CRUDMasterDAO.getAllEveSecreEventDetails():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in CRUDMasterDAO.getAllEveSecreEventDetails():" + e.getMessage());
        }
        Debug.print("Size of Event List:" + eventList.size());
        return eventList;
    } 
  
public ArrayList getEvePriceDets(String issueId) throws SQLException{
        Debug.print("Inside CRUDMasterDAO getEvePriceDets()");
        ArrayList eventList = new ArrayList();
        PreparedStatement prepStmt = null;
        float individualPrice=0;
        try {
            makeConnection();
            
            String selStmt1 ="select event_price_type_id, event_price_type_name, transaction_type_id from tblMeeEventRegistrationPriceMaster ";

            Debug.print("Query: "+selStmt1);
            
            prepStmt = con.prepareStatement(selStmt1);            
            ResultSet rs = prepStmt.executeQuery();          
            while(rs.next()) {
                String evePriceTypeId=rs.getString(1);
              if(evePriceTypeId!=null){             
              String selStmt2 ="select A.due_date_price " +
                      "from tblMeeEventRegistrationPriceDetails A, tblMeeEventRegistrationPriceMaster B where " +
                      "A.event_price_type_id=B.event_price_type_id and A.issue_id=? " +
                      "and A.event_price_type_id=?";   
              
            PreparedStatement prepStmt1 = con.prepareStatement(selStmt2);
            prepStmt1.setString(1,issueId);
            prepStmt1.setString(2,evePriceTypeId);
              
            ResultSet rs1 = prepStmt1.executeQuery();
            if(rs1.next()) {
            individualPrice=rs1.getFloat(1);
            }  
            rs1.close();
            prepStmt1.close();
              }                 
                String evePriceName = rs.getString(2);
                String transacTypId = rs.getString(3);
                
                String tempList[] = {evePriceTypeId,evePriceName,transacTypId,String.valueOf(individualPrice)};
                eventList.add(tempList);
            }
            Debug.print("CRUDMasterDAO.getEvePriceDets"+eventList.size());
            rs.close();
            prepStmt.close();
            
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in CRUDMasterDAO.getEvePriceDets():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in CRUDMasterDAO.getEvePriceDets():" + e.getMessage());
        } finally {
            releaseConnection();
        }
        return eventList;
    }
   
    public float getTotalPay(String eveId)
  {
      String payId = "";
      PreparedStatement prepStmt = null;
      float totalPay=0;
      ResultSet rs1=null;
      try {
            makeConnection();
            
            String selectStatement = "select payment_id from tblMeeEventOrganizerRenewalDetails"+  
                    " where event_id=? " ;
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,eveId);
            
            ResultSet rs = prepStmt.executeQuery();
            while(rs.next()) {
            payId=(String)rs.getString(1);
            }
            if(payId!=null){
               String selectStatement1 = "select amount from tblUserPaymentDetails where payment_id=?";
               
            prepStmt = con.prepareStatement(selectStatement1);
            prepStmt.setString(1,payId);
            
             rs1 = prepStmt.executeQuery();
            while(rs1.next()) {
            totalPay=rs1.getFloat(1);
            }                          
            }
  
            rs.close();   
            prepStmt.close();
            rs1.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in CRUDMasterDAO.getTotalPay():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in CRUDMasterDAO.getTotalPay():" + e.getMessage());
        }
      return totalPay;
  }
   
 
  public boolean updateOmniCommts(HLCEventDetailsVO objEventDet) throws SQLException {
        Debug.print("CRUDMasterDAO.updateOmniCommts():");
        boolean result = false;
        
        PreparedStatement prepStmt = null;
        ArrayList omniList =new ArrayList();
        try {
            makeConnection();
            
            if(objEventDet.getEventId()!=null){
                Debug.print("CRUDMasterDAO.updateOmniCommts():"+objEventDet.getEventId());
             HLCCRUDMasterDAO objDAO=new HLCCRUDMasterDAO();            
            omniList=objDAO.selectOmniComments(objEventDet.getEventId());          
            }
       if(omniList!=null && omniList.size()==0){
          
            HLCCRUDMasterDAO objDAO=new HLCCRUDMasterDAO();     
           
           result=objDAO.insertOmnibusDets(objEventDet); 
            Debug.print("CRUDMasterDAO.updateOmniCommts(): result of insert"+result);
       }     
       else{     
                      
String selectStatement = "update tblMeeOmnibusComments set secretary_comment=?, accomodation_comment=?, direction_comment=?, dressage_test_comment=?," +
                    "tentative_time_schedule_comment=?, official_comment=?, entries_comment=?, awards_comment=?, start_time_comment=?, stabling_vet_comment=?," +
                    "cross_country_comment=?, other_comment=?, event_title_comment=?, event_fees_directory_comment=?, event_management_comment=?, event_website=? where event_id=?"; 

            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,objEventDet.getSecretaryComments());
            prepStmt.setString(2,objEventDet.getAccomodation());
            prepStmt.setString(3,objEventDet.getDirections());
            prepStmt.setString(4,objEventDet.getDressageComment());
            prepStmt.setString(5,objEventDet.getTimeComment());
            prepStmt.setString(6,objEventDet.getOfficialComments());
            prepStmt.setString(7,objEventDet.getEntriesComments());
            prepStmt.setString(8,objEventDet.getAwardCommts());
            prepStmt.setString(9,objEventDet.getStartTimeCommts());
            prepStmt.setString(10,objEventDet.getStabVetCommts());
            prepStmt.setString(11,objEventDet.getCrsCountCommts());
            prepStmt.setString(12,objEventDet.getOtherCommts());
            prepStmt.setString(13,objEventDet.getEveTitleComments());
            prepStmt.setString(14,objEventDet.getEveFeeComments());
            prepStmt.setString(15,objEventDet.getEveMgtComments());
            prepStmt.setString(16,objEventDet.getEveWebSite());
            prepStmt.setString(17,objEventDet.getEventId());
           
            int cnt = prepStmt.executeUpdate();
            if (cnt>=1) {
                result = true;
            }
        } 
            Debug.print("CRUDMasterDAO updateOmniCommts() result: " + result);
            prepStmt.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in CRUDMasterDAO.updateOmniCommts():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in CRUDMasterDAO.updateOmniCommts():" + e.getMessage());
        }
        return result;
    } 
  
 
  public ArrayList selectOmniComments(String eventId) throws SQLException{
        Debug.print("Inside CRUDMasterDAO in selectOmniComments" +eventId);
        ArrayList commtList = new ArrayList();
        PreparedStatement prepStmt = null;
        try {
            makeConnection();
            if(eventId!=null && eventId.trim().length()!=0){
            String selStmt1 ="select organizer_id, secretary_comment, accomodation_comment, direction_comment, dressage_test_comment," +
                    "tentative_time_schedule_comment, official_comment, entries_comment, awards_comment, start_time_comment, stabling_vet_comment," +
                    "cross_country_comment, other_comment, event_title_comment, event_fees_directory_comment, event_management_comment, event_website from tblMeeOmnibusComments where event_id=?";

            Debug.print("Query: "+selStmt1);
            
            prepStmt = con.prepareStatement(selStmt1);
            prepStmt.setString(1,eventId);
            ResultSet rs = prepStmt.executeQuery();
            
            while(rs.next()) {
                String orgId=rs.getString(1);
                String secretComt = rs.getString(2);
                String accomCommt=rs.getString(3);
                String directCommt = rs.getString(4);
                String dressCommt=rs.getString(5);
                String tentTimeCommt = rs.getString(6);
                String offCommt=rs.getString(7);
                String entriesCommt = rs.getString(8);
                String awardsCommt=rs.getString(9);
                String stTimeCommt = rs.getString(10);
                String stabVetCommt=rs.getString(11);
                String crsCounCommt = rs.getString(12);
                String othrCommt = rs.getString(13);
                String eveTitleCommt = rs.getString(14);
                String eveFeeCommt = rs.getString(15);
                String eveMagtCommt = rs.getString(16);
                String eveWebSite = rs.getString(17);
                
                String tempList[] = {orgId,secretComt,accomCommt,directCommt,dressCommt,tentTimeCommt,offCommt,entriesCommt,awardsCommt,stTimeCommt,stabVetCommt,crsCounCommt,othrCommt,eveTitleCommt,eveFeeCommt,eveMagtCommt,eveWebSite};
                commtList.add(tempList);
            }
            Debug.print("CRUDMasterDAO.selectOmniComments"+commtList.size());
            rs.close();
            prepStmt.close();
            }
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in CRUDMasterDAO.selectOmniComments():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in CRUDMasterDAO.selectOmniComments():" + e.getMessage());
        } finally {
            releaseConnection();
        }
        return commtList;
    } 
  
public boolean insertOmnibusDets(HLCEventDetailsVO objEventDet) throws SQLException {
        Debug.print("CRUDMasterDAO.insertOmnibusDets():");
        boolean result = false;
        PreparedStatement prepStmt = null;
        try {
            makeConnection();
            
            String selectStatement = "insert into tblMeeOmnibusComments (event_id, organizer_id, secretary_comment ," +
                    " accomodation_comment, direction_comment, dressage_test_comment, tentative_time_schedule_comment, official_comment, " +
                    "entries_comment, awards_comment, start_time_comment, stabling_vet_comment, cross_country_comment, other_comment, event_title_comment, event_fees_directory_comment, event_management_comment, event_website) " +
                    "values (? , ? , ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,objEventDet.getEventId());
            prepStmt.setString(2,objEventDet.getOrganizeId());
            prepStmt.setString(3,objEventDet.getSecretaryComments());
            prepStmt.setString(4,objEventDet.getAccomodation());
            prepStmt.setString(5,objEventDet.getDirections());
            prepStmt.setString(6,objEventDet.getDressageComment());
            prepStmt.setString(7,objEventDet.getTimeComment());
            prepStmt.setString(8,objEventDet.getOfficialComments());
            prepStmt.setString(9,objEventDet.getEntriesComments());
            prepStmt.setString(10,objEventDet.getAwardCommts());
            prepStmt.setString(11,objEventDet.getStartTimeCommts());
            prepStmt.setString(12,objEventDet.getStabVetCommts());
            prepStmt.setString(13,objEventDet.getCrsCountCommts());
            prepStmt.setString(14,objEventDet.getOtherCommts());
            prepStmt.setString(15,objEventDet.getEveTitleComments());
            prepStmt.setString(16,objEventDet.getEveFeeComments());
            prepStmt.setString(17,objEventDet.getEveMgtComments());
            prepStmt.setString(18,objEventDet.getEveWebSite());
            
            int cnt = prepStmt.executeUpdate();
            if (cnt>=1) {
                result = true;
            }
           
            Debug.print("CRUDMasterDAO insertOmnibusDets() result: " + result);
            prepStmt.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in CRUDMasterDAO.insertOmnibusDets():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in CRUDMasterDAO.insertOmnibusDets():" + e.getMessage());
        }
        return result;
    } 
  public ArrayList selectCompetitionMgtDetails(String eventId) throws SQLException{
        Debug.print("Inside CRUDMasterDAO in selectCompetitionMgtDetails" +eventId);
        ArrayList compMgtList = new ArrayList();
        PreparedStatement prepStmt = null;
        try {
            makeConnection();
            if(eventId!=null && eventId.trim().length()!=0){
            String selStmt1 ="select com_management_name, com_management_address, com_management_city, com_management_state, " +
                    "com_management_country, com_management_zip, com_management_phone, com_management_fax, " +
                    "manager_usef_member_id, manager_usea_member_id, manager_name, secretary_usef_member_id, " +
                    "secretary_name, email_id from tblMeeEventOrganizerRenewalDetails where event_id=?";

            Debug.print("Query in selectCompetitionMgtDetails: "+selStmt1);
            
            prepStmt = con.prepareStatement(selStmt1);
            prepStmt.setString(1,eventId);
            ResultSet rs = prepStmt.executeQuery();
            
            while(rs.next()) {
                String compMgtName=rs.getString(1);
                String compMgtAdd = rs.getString(2);
                String compMgtCity=rs.getString(3);
                String compMgtState = rs.getString(4);
                String compMgtCountry=rs.getString(5);
                String compMgtZip = rs.getString(6);
                String compMgtPh=rs.getString(7);
                String compMgtFax = rs.getString(8);
                String mgrUsefMemId=rs.getString(9);
                String mgrUseaMemId = rs.getString(10);
                String mgrName=rs.getString(11);
                String secreUsefMemId = rs.getString(12);
                String secreName = rs.getString(13);
                String emailId = rs.getString(14);
               
                
                String tempList[] = {compMgtName,compMgtAdd,compMgtCity,compMgtState,compMgtCountry,compMgtZip,compMgtPh,compMgtFax,mgrUsefMemId,mgrUseaMemId,mgrName,secreUsefMemId,secreName,emailId};
                compMgtList.add(tempList);
            }
            Debug.print("CRUDMasterDAO.selectCompetitionMgtDetails"+compMgtList.size());
            rs.close();
            prepStmt.close();
            }
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in CRUDMasterDAO.selectCompetitionMgtDetails():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in CRUDMasterDAO.selectCompetitionMgtDetails():" + e.getMessage());
        } finally {
            releaseConnection();
        }
        return compMgtList;
    } 
  
  
/*************************************************** Database Routines *****************************************************/
    private void makeConnection() {
       
        try {
            InitialContext ic = new InitialContext();
            DataSource ds = (DataSource) ic.lookup("java:/ELMTMSSQLDS");
            con = ds.getConnection();
        } catch (Exception ex) {
            Debug.print("In CRUDMasterDAO Unable to connect to database.makeConnection() :  " +ex.getMessage());
        }
    }
     // makeConnection

    private void releaseConnection() {
       

        try {
            con.close();
        } catch (SQLException ex) {
            Debug.print("In CRUDMasterDAO releaseConnection() : " + ex.getMessage());
        }
    }
     // releaseConnection    
    
}
