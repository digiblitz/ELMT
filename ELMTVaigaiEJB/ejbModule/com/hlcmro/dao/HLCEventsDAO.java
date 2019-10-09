/*
 * EventsDAO.java
 *
 * Created on September 22, 2006, 2:16 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package com.hlcmro.dao;

import com.hlcmro.util.HLCAreaChairsVO;
import com.hlcmro.util.HLCAreaStateZipVO;
import com.hlcmro.util.DBHelper;
import com.hlcmro.util.Debug;
import com.hlcmro.util.HLCEJBAllJNDIs;
import com.hlcmro.util.HLCEventDetailsVO;
import com.hlcmro.util.HLCEventRequestVO;
import java.text.SimpleDateFormat;
import javax.ejb.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import java.util.*;

/**
 *
 * @author suresh
 */
public class HLCEventsDAO {

    private Connection con;

    /** Creates a new instance of EventsDAO */
    public HLCEventsDAO() {
    }
    private SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

    public Vector getDivLevelByEventId(String eventId) {
        Debug.print("EventsDAO getDivLevelByEventId:" + eventId);
        Vector eventList = new Vector();
        makeConnection();
        try {
            String selectStatement = "select A.event_type_id, A.event_type_name, B.event_level_code, C.event_id,  B.event_level_id, C.map_type_id from " +
                    DBHelper.USEA_MRO_TYPE_MASTER + " A, " + DBHelper.USEA_MRO_LEVEL_MASTER + " B, " + DBHelper.USEA_MRO_EVENT_TYPE_DETAILS + " C, " +
                    DBHelper.USEA_MRO_MAP_EVENT_LEVEL + " D  where C.map_type_id = D.map_type_id and D.event_type_id = A.event_type_id and  " +
                    " D.event_level_id = B.event_level_id and C.event_id = ?";

            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            Debug.print("EventTypeBean getDivLevelByEventId:" + selectStatement);
            prepStmt.setLong(1, Long.parseLong(eventId));
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()) {
                String eventTypeId = rs.getString(1);
                String eventTypeName = rs.getString(2);
                String eventLevelCode = rs.getString(3);
                String eventIdVal = rs.getString(4);
                String eventLevelId = rs.getString(5);
                String mapTypeId = rs.getString(6);
                String evtTypeList[] = {eventTypeId, eventTypeName, eventLevelCode, eventIdVal, eventLevelId, mapTypeId};
                //Debug.print("EventTypeBean getDivLevelByEventId:" + evtTypeList);
                eventList.add(evtTypeList);
            }

            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch (SQLException sql) {
            releaseConnection(con);
            throw new EJBException("SQL Exception in getDivLevelByEventId:" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            throw new EJBException("General Exception  in getDivLevelByEventId:" + e.getMessage());
        }
        return eventList;
    }

    public Vector getHorseDressageByEventId(String eventId) {
        Debug.print("HorseDressageBean getHorseDressageByEventId");
        Vector eventList = new Vector();
        makeConnection();
        try {
            String selectStatement = " select D.dressage_id, A.event_level_name, B.event_sub_level_name, C.arena_size_name, D.dressage_map_id " +
                    " from " + DBHelper.USEA_MRO_LEVEL_MASTER + " A, " + DBHelper.USEA_MRO_SUB_LEVEL_MASTER + " B , " + DBHelper.USEA_MRO_ARENA_SIZE_MASTER + " C, " +
                    DBHelper.USEA_MRO_HORSE_DRESSAGE_DETAILS + " D, " + DBHelper.USEA_MRO_MAP_EVENT_SUB_LEVEL + " E " +
                    " where D.dressage_map_id = E.dressage_map_id and D.arena_size_id = C.arena_size_id and " +
                    " E.event_level_id = A.event_level_id and E.event_sub_level_id = B.event_sub_level_id and " +
                    " D.event_id = ? ";

            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            Debug.print("HorseDressageBean ejbFindByEventId query:" + selectStatement);
            prepStmt.setLong(1, Long.parseLong(eventId));
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()) {
                String dressageId = rs.getString(1);
                String eventLevelName = rs.getString(2);
                String eventSubLevelName = rs.getString(3);
                String arenaSizeName = rs.getString(4);
                String dressageMapId = rs.getString(5);
                String horseList[] = {dressageId, eventLevelName, eventSubLevelName, arenaSizeName, dressageMapId};
                eventList.addElement(horseList);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch (SQLException sql) {
            releaseConnection(con);
            throw new EJBException("SQL Exception in getHorseDressageByEventId:" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            throw new EJBException("General Exception  in getHorseDressageByEventId:" + e.getMessage());
        }
        return eventList;
    }

    public Vector getRefundRuleByEventId(String eventId) {
        Debug.print("RefundRuleDetailBean getRefundRuleByEventId");
        Vector eventList = new Vector();
        makeConnection();
        try {
            String selectStatement = " select C.refund_id, C.event_id, A.refund_rule_type_name, B.refund_rule_sub_type_name, C.refund_map_id, C.amount from " +
                    DBHelper.USEA_MRO_REFUND_RULE_MASTER + " A, " + DBHelper.USEA_MRO_REFUND_SUB_RULE_SUB_MASTER + " B, " + DBHelper.USEA_MRO_REFUND_RULE_DETAILS + " C, " +
                    DBHelper.USEA_MRO_MAP_REFUND_RULE + " D where C.refund_map_id = D.refund_map_id and " +
                    " D.refund_rule_type_id = A.refund_rule_type_id and D.refund_rule_sub_type_id = B.refund_rule_sub_type_id " +
                    " and  C.event_id = ?";

            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setLong(1, Long.parseLong(eventId));
            Debug.print("RefundRuleDetailBean ejbFindByEventId Query:" + selectStatement);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()) {
                String refundId = rs.getString(1);
                String eventIdVal = rs.getString(2);
                String refundRuleTypeName = rs.getString(3);
                String refundRuleSubTypeName = rs.getString(4);
                String refundMapId = rs.getString(5);
                String amount = rs.getString(6);
                String refundList[] = {refundId, eventIdVal, refundRuleTypeName, refundRuleSubTypeName, refundMapId, amount};
                eventList.addElement(refundList);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch (SQLException sql) {
            releaseConnection(con);
            throw new EJBException("SQL Exception in getRefundRuleByEventId:" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            throw new EJBException("General Exception  in getRefundRuleByEventId:" + e.getMessage());
        }
        return eventList;
    }

    //=====Getting Area Master =====/
    public ArrayList selectAllAreaMaster() {
        Debug.print("EventsDAO.selectAllAreaMaster():");
        ArrayList areaList = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
        try {
            String selectStatement = " SELECT area_id, area_code, area_name from " + DBHelper.USEA_MRO_AREA_MASTER;
            prepStmt = con.prepareStatement(selectStatement);
            rs = prepStmt.executeQuery();
            areaList = new ArrayList();
            while (rs.next()) {
                String area_id = rs.getString(1);
                String area_code = rs.getString(2);
                String area_name = rs.getString(3);
                String[] strAreaList = {area_id, area_code, area_name};
                areaList.add(strAreaList);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        // Debug.print("EventsDAO.selectAllAreaMaster():" + areaList);
        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in EventsDAO.selectAllAreaMaster():" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in EventsDAO.selectAllAreaMaster():" + e.getMessage());
        }
        return areaList;
    }
    //=====Getting Area Zip code range and State Master based on areaID=====/
    public ArrayList selectAreaStateMasteronID(String areaID) {
        Debug.print("EventsDAO.selectAreaStateMasteronID(): areaID " + areaID);
        ArrayList areaList = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
        try {
            String selectStatement = "SELECT A.area_id,A.area_code,A.area_name,B.state_id,B.state_name, B.state_code," +
                    "C.map_zip_id,C.zip_code_from,C.zip_code_to from tblMeeAreaMaster A,tblMeeStateMaster B," +
                    "tblMeeMapStateZip C where A.area_id=C.area_id and B.state_id=C.state_id and A.area_id=?";

            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, areaID);
            rs = prepStmt.executeQuery();
            areaList = new ArrayList();
            while (rs.next()) {
                String area_id = rs.getString(1);
                String area_code = rs.getString(2);
                String area_name = rs.getString(3);
                String state_id = rs.getString(4);
                String state_name = rs.getString(5);
                String state_code = rs.getString(6);
                String map_zip_id = rs.getString(7);
                String zip_code_from = rs.getString(8);
                String zip_code_to = rs.getString(9);

                HLCAreaStateZipVO objAreaSteVO = new HLCAreaStateZipVO();

                objAreaSteVO.setArea_code(area_code);
                objAreaSteVO.setArea_id(area_id);
                objAreaSteVO.setArea_name(area_name);
                objAreaSteVO.setMap_zip_id(map_zip_id);
                objAreaSteVO.setState_code(state_code);
                objAreaSteVO.setState_id(state_id);
                objAreaSteVO.setState_name(state_name);
                objAreaSteVO.setZip_code_from(zip_code_from);
                objAreaSteVO.setZip_code_to(zip_code_to);

                areaList.add(objAreaSteVO);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in EventsDAO.selectAreaStateMasteronID():" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in EventsDAO.selectAreaStateMasteronID():" + e.getMessage());
        } finally {
            releaseConnection(con);
        }
        return areaList;
    }
//=====Getting Area Chair =====/
    public ArrayList selectAllAreaChairs() {
        Debug.print("EventsDAO.selectAllAreaChairs():");
        ArrayList areaChair = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
        try {
            String selectStatement = " SELECT user_id, user_code, first_name , email_id from " + DBHelper.USEA_MMS_USERMASTER + " where user_code is NOT null";
            prepStmt = con.prepareStatement(selectStatement);
            rs = prepStmt.executeQuery();
            areaChair = new ArrayList();
            while (rs.next()) {
                String user_id = rs.getString(1);
                String user_code = rs.getString(2);
                String first_name = rs.getString(3);
                String email_id = rs.getString(3);
                String[] strAreaChair = {user_id, user_code, first_name, email_id};
                areaChair.add(strAreaChair);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
            Debug.print("EventsDAO.selectAllAreaChairs():" + areaChair);
        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in EventsDAO.selectAllAreaChairs():" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in EventsDAO.selectAllAreaChairs():" + e.getMessage());
        }
        return areaChair;
    }

//=====Getting Area State Master =====/
    public ArrayList selectAllStateMaster() {
        Debug.print("EventsDAO.selectAllStateMaster():");
        ArrayList stateList = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
        try {
            String selectStatement = " SELECT state_id, state_name, state_code from " + DBHelper.USEA_MRO_STATE_MASTER;
            prepStmt = con.prepareStatement(selectStatement);
            rs = prepStmt.executeQuery();
            stateList = new ArrayList();
            while (rs.next()) {
                String state_id = rs.getString(1);
                String state_name = rs.getString(2);
                String state_code = rs.getString(3);
                String[] strStateList = {state_id, state_name, state_code};
                stateList.add(strStateList);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        // Debug.print("EventsDAO.selectAllAreaMaster():" + stateList);
        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in EventsDAO.selectAllStateMaster():" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in EventsDAO.selectAllStateMaster():" + e.getMessage());
        }
        return stateList;
    }
//=============================================Insert Map State Zip details=========================================
    public boolean insertMapStateZip(String areaId, String stateId, String zipCodeFrom, String zipCodeTo) {
        Debug.print("EventsDAO.insertMapStateZip():");
        PreparedStatement prepStmt = null;
        if (stateId.trim().length() == 0) {
            stateId = null;
        }
        makeConnection();
        try {
            String insertStatement = "insert into " + DBHelper.USEA_MRO_MAP_STATE_ZIP + " (area_id, state_id, zip_code_from, zip_code_to) " +
                    " values( ? , ? , ? , ? )";
            prepStmt = con.prepareStatement(insertStatement);
            prepStmt.setString(1, areaId);
            prepStmt.setString(2, stateId);
            prepStmt.setString(3, zipCodeFrom);
            prepStmt.setString(4, zipCodeTo);
            prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection(con);
        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in EventsDAO.insertMapStateZip():" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in EventsDAO.insertMapStateZip():" + e.getMessage());
        }
        return true;
    }
    //=============================================Checking existing Zipcode range=========================================
    public boolean isZipCodeRangeExists(String fromCode, String toCode) {
        Debug.print("EventsDAO.isZipCodeRangeExists():");
        PreparedStatement prepStmt = null;
        boolean result = false;
        ResultSet rs = null;
        makeConnection();
        try {
            String selectStatement = " SELECT state_id from " + DBHelper.USEA_MRO_MAP_STATE_ZIP +
                    "   where zip_code_from = ? and zip_code_to = ?";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, fromCode);
            prepStmt.setString(2, toCode);
            rs = prepStmt.executeQuery();
            result = rs.next();
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch (SQLException sql) {
            Debug.print("SQL Exception in EventsDAO.isZipCodeRangeExists():" + sql.getMessage());
        } catch (Exception e) {
            Debug.print("General Exception  in EventsDAO.isZipCodeRangeExists():" + e.getMessage());
        } finally {
            releaseConnection(con);
        }
        Debug.print("EventsDAO.isZipCodeRangeExists() result:" + result);
        return result;
    }

//=============================================Checking existing Zipcode range=========================================
    public boolean isStateAreaExists(String areaId, String stateId) {
        Debug.print("EventsDAO.isZipCodeRangeExists():");
        PreparedStatement prepStmt = null;
        boolean result = false;
        ResultSet rs = null;
        makeConnection();
        try {
            String selectStatement = " SELECT state_id from " + DBHelper.USEA_MRO_MAP_STATE_ZIP +
                    "   where area_id = ? and state_id = ?";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, areaId);
            prepStmt.setString(2, stateId);
            rs = prepStmt.executeQuery();
            result = rs.next();
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch (SQLException sql) {
            Debug.print("SQL Exception in EventsDAO.isZipCodeRangeExists():" + sql.getMessage());
        } catch (Exception e) {
            Debug.print("General Exception  in EventsDAO.isZipCodeRangeExists():" + e.getMessage());
        } finally {
            releaseConnection(con);
        }
        Debug.print("EventsDAO.isZipCodeRangeExists() result:" + result);
        return result;
    }
//=============================================Insert State Area Chair details=========================================
    public boolean insertMapAreaAndAreaChair(String areaId, String areaChairId) {
        Debug.print("EventsDAO.insertMapAreaAndAreaChair():");
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String insertStatement = "insert into " + DBHelper.USEA_MRO_MAP_AREA_CHAIR + " (area_id, area_chair_id) " +
                    " values( ? , ? )";
            prepStmt = con.prepareStatement(insertStatement);
            prepStmt.setString(1, areaId);
            prepStmt.setString(2, areaChairId);
            prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection(con);
        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in EventsDAO.insertMapAreaAndAreaChair():" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in EventsDAO.insertMapAreaAndAreaChair():" + e.getMessage());
        }
        return true;
    }
//=============================================Edit  State Area Chair details=========================================
    public boolean updateMapAreaAndAreaChair(String mapAreaId, String areaId, String areaChairId) {
        Debug.print("EventsDAO.updateMapAreaAndAreaChair(): MapAreaId:" + mapAreaId);
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String insertStatement = "update " + DBHelper.USEA_MRO_MAP_AREA_CHAIR + " set area_id = ?, area_chair_id = ? " +
                    " where map_area_id = ? ";
            prepStmt = con.prepareStatement(insertStatement);
            prepStmt.setString(1, areaId);
            prepStmt.setString(2, areaChairId);
            prepStmt.setString(3, mapAreaId);
            prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection(con);
        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in EventsDAO.updateMapAreaAndAreaChair():" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in EventsDAO.updateMapAreaAndAreaChair():" + e.getMessage());
        }
        return true;
    }
//=============================================Edit  State Area Chair details=========================================
    public int stateCount(String areaId) {
        Debug.print("EventsDAO.stateCount(): MapAreaId:" + areaId);
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        int stateCount = 0;
        makeConnection();
        try {
            String selectStatement = " SELECT count(*) from " + DBHelper.USEA_MRO_MAP_STATE_ZIP + " where  area_id= ? ";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, areaId);
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                stateCount = rs.getInt(1);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
            Debug.print("EventsDAO.stateCount():" + stateCount);
        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in EventsDAO.stateCount():" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in EventsDAO.stateCount():" + e.getMessage());
        }
        return stateCount;
    }

//=============================================Getting Area and Area Chair State Zip=========================================
    public ArrayList selectAllMappingDetailsForAreaAndAreaChair() {
        Debug.print("EventsDAO.selectAllMappingDetailsForStateZip():");
        ArrayList AreaChairList = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
        try {
            String selectStatement = " SELECT a.map_area_id, a.area_id , a.area_chair_id ,b.area_code, b.area_name, c.user_id, " +
                    "c.user_code, c.first_name,c.middle_name,c.last_name,c.email_id from " + DBHelper.USEA_MRO_MAP_AREA_CHAIR + " a , " + DBHelper.USEA_MRO_AREA_MASTER + " b , " + DBHelper.USEA_MMS_USERMASTER + " c " +
                    " where a.area_id = b.area_id and a.area_chair_id = c.user_code";

            prepStmt = con.prepareStatement(selectStatement);
            rs = prepStmt.executeQuery();
            AreaChairList = new ArrayList();
            while (rs.next()) {
                String mapAreaId = rs.getString(1);
                String areaId = rs.getString(2);
                String areaChairId = rs.getString(3);
                String areaCode = rs.getString(4);
                String areaName = rs.getString(5);
                String userId = rs.getString(6);
                String userCode = rs.getString(7);
                String firstName = rs.getString(8);
                String middleName = rs.getString(9);
                String lastName = rs.getString(10);
                String emailId = rs.getString(11);
                HLCAreaChairsVO objACVO = new HLCAreaChairsVO();

                objACVO.setMapAreaId(mapAreaId);
                objACVO.setAreaId(areaId);
                objACVO.setAreaChairId(areaChairId);
                objACVO.setAreaCode(areaCode);
                objACVO.setAreaName(areaName);
                objACVO.setUserId(userId);
                objACVO.setUserCode(userCode);
                objACVO.setFirstName(firstName);
                objACVO.setMiddleName(middleName);
                objACVO.setLastName(lastName);
                objACVO.setEmailId(emailId);
                AreaChairList.add(objACVO);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
            Debug.print("EventsDAO.selectAllMappingDetailsForStateZip():" + AreaChairList);
        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in EventsDAO.selectAllMappingDetailsForStateZip():" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in EventsDAO.selectAllMappingDetailsForStateZip():" + e.getMessage());
        }
        return AreaChairList;
    }

//=============================================Getting Area and Area Chair State Zip=========================================
    public HLCAreaChairsVO selectMappingDetailsForAreaAndAreaChair(String mapAreaId) {
        Debug.print("EventsDAO.selectMappingDetailsForAreaAndAreaChair():");
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        HLCAreaChairsVO objACVO = new HLCAreaChairsVO();
        makeConnection();
        try {
            String selectStatement = " SELECT a.map_area_id, a.area_id , a.area_chair_id ,b.area_code, b.area_name, c.user_id, " +
                    "c.user_code, c.first_name,c.middle_name,c.last_name,c.email_id from " + DBHelper.USEA_MRO_MAP_AREA_CHAIR + " a , " + DBHelper.USEA_MRO_AREA_MASTER + " b , " + DBHelper.USEA_MMS_USERMASTER + " c " +
                    " where a.area_id = b.area_id and a.area_chair_id = c.user_code and a.map_area_id = ? ";

            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, mapAreaId);
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                String mapAreaIdVal = rs.getString(1);
                String areaId = rs.getString(2);
                String areaChairId = rs.getString(3);
                String areaCode = rs.getString(4);
                String areaName = rs.getString(5);
                String userId = rs.getString(6);
                String userCode = rs.getString(7);
                String firstName = rs.getString(8);
                String middleName = rs.getString(9);
                String lastName = rs.getString(10);
                String emailId = rs.getString(11);


                objACVO.setMapAreaId(mapAreaIdVal);
                objACVO.setAreaId(areaId);
                objACVO.setAreaChairId(areaChairId);
                objACVO.setAreaCode(areaCode);
                objACVO.setAreaName(areaName);
                objACVO.setUserId(userId);
                objACVO.setUserCode(userCode);
                objACVO.setFirstName(firstName);
                objACVO.setMiddleName(middleName);
                objACVO.setLastName(lastName);
                objACVO.setEmailId(emailId);
            }

            rs.close();
            prepStmt.close();
            releaseConnection(con);
            Debug.print("EventsDAO.selectMappingDetailsForAreaAndAreaChair():" + objACVO);
        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in EventsDAO.selectMappingDetailsForAreaAndAreaChair():" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in EventsDAO.selectMappingDetailsForAreaAndAreaChair():" + e.getMessage());
        }
        return objACVO;
    }
//=============================================Getting Area and Area Chair State Zip=========================================
    public String[] selectStateByAreaId(String areaId) {
        Debug.print("EventsDAO.selectStateByAreaId():");
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        String stateDetail[] = {};
        makeConnection();
        try {
            String selectStatement = " select A.state_id, A.state_name, A.state_code, B.zip_code_from, B.zip_code_to  " +
                    " from " + DBHelper.USEA_MRO_STATE_MASTER + " a , " + DBHelper.USEA_MRO_MAP_STATE_ZIP +
                    " b , " + DBHelper.USEA_MRO_AREA_MASTER + " c " +
                    " where B.area_id = C.area_id and B.state_id = A.state_id and B.area_id = ? ";

            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, areaId);
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                String stateId = rs.getString(1);
                String stateName = rs.getString(2);
                String stateCode = rs.getString(3);
                String zipCodeFrom = rs.getString(4);
                String zipCodeTo = rs.getString(5);
                String tempStateDetail[] = {stateId, stateName, stateCode, zipCodeFrom, zipCodeTo};
                stateDetail = tempStateDetail;
            }

            rs.close();
            prepStmt.close();
            releaseConnection(con);
            Debug.print("EventsDAO.selectStateByAreaId():" + stateDetail);
        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in EventsDAO.selectStateByAreaId():" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in EventsDAO.selectStateByAreaId():" + e.getMessage());
        }
        return stateDetail;
    }
//=============================================Delete Mapping Records with fieldValue details=========================================
    public boolean deleteRow(String fieldName, String fieldValue, String tableName) {
        Debug.print("EventsDAO.deleteRow()");
        boolean result = false;
        makeConnection();
        try {
            String deleteStatement = "delete from " + tableName + "  where " + fieldName + " = ? ";
            Debug.print("RoleManagementDAOImpl.deleteRow():" + "\n" + deleteStatement + ":" + fieldValue);
            PreparedStatement prepStmt = con.prepareStatement(deleteStatement);
            prepStmt.setString(1, fieldValue);
            prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection(con);
            result = true;
        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in deleteRow:" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in deleteRow:" + e.getMessage());
        }
        return result;
    }
//=============================================Delete Mapping Records with fieldValue details=========================================
    public boolean deleteRow(String fieldName1, String fieldValue1, String fieldName2, String fieldValue2, String tableName) {
        Debug.print("EventsDAO.deleteRow()");
        boolean result = false;
        makeConnection();
        try {
            String deleteStatement = "delete from " + tableName + "  where " + fieldName1 + " = ? and " + fieldName2 + " = ?";
            PreparedStatement prepStmt = con.prepareStatement(deleteStatement);
            prepStmt.setString(1, fieldValue1);
            prepStmt.setString(2, fieldValue2);
            prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection(con);
            result = true;
        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in deleteRow:" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in deleteRow:" + e.getMessage());
        }
        return result;
    }

//=============================================Delete Mapping Records with fieldValue details=========================================
    public ArrayList getZipCodesOnStateId(String stateId, String zipValue) throws SQLException {
        Debug.print("EventsDAO.getZipCodesOnStateId()  " + zipValue + "  stateId " + stateId);
        PreparedStatement prepStmt = null;
        PreparedStatement prepStmt1 = null;
        ArrayList zipValues = null;
        makeConnection();
        try {

            String sql = "select A.area_id, B.area_name from " + DBHelper.USEA_MRO_MAP_STATE_ZIP + " A, " + DBHelper.USEA_MRO_AREA_MASTER + " B where A.area_id = B.area_id " +
                    "and A.state_id = ? and ? between A.zip_code_from and A.zip_code_to ";

            prepStmt = con.prepareStatement(sql);
            prepStmt.setString(1, stateId);
            prepStmt.setString(2, zipValue);
            ResultSet rs = prepStmt.executeQuery();

            while (rs.next()) {
                zipValues = new ArrayList();
                String area_id = rs.getString(1);
                String area_name = rs.getString(2);
                String val[] = {area_id, area_name};
                zipValues.add(val);
            }
        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in deleteRow:" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in deleteRow:" + e.getMessage());
        } finally {
            if (prepStmt1 != null) {
                prepStmt1.close();
            }
            prepStmt.close();
            releaseConnection(con);
        }

        return zipValues;
    }

    /*=================================Event Registration Request==========================================*/
    public boolean insertEventRegiRequest(HLCEventRequestVO requestVO, String userId) throws SQLException {
        Debug.print("EventsDAO.insertEventRegiRequest()");
        boolean result = false;
        try {
           boolean eventTbl = insertEventRequestDetails(requestVO,userId);
            //boolean evntTypeDet = insertEventTypeDetails(requestVO.getEvent_id(), requestVO.getMaping_type_id());
            
        Debug.print("EventsDAO.requestVO.getEvent_type_level_id(): "+requestVO.getEvent_type_level_id());
            boolean evntTypeDet = insertEventTypeDetails(requestVO.getEvent_id(),requestVO.getEvent_type_level_id());
            
            if (eventTbl == true && evntTypeDet == true) {
                result = true;
            }
        } catch (Exception e) {

        }
        return result;
    }
    /*=================================Insert Event Type Details==========================================*/

   /* public boolean insertEventTypeDetails(String eventId, ArrayList mapping_ids) throws SQLException {
        boolean result = false;
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            if (mapping_ids != null) {
                if (mapping_ids.size() > 0) {
                    Iterator itr = mapping_ids.iterator();
                    while (itr.hasNext()) {
                        String map_id = (String) itr.next();
                        if (map_id != null && eventId != null) {
                            String query = "insert into " + DBHelper.USEA_MRO_EVENT_TYPE_DETAILS + " (event_id, map_type_id) values (?,?)";

                            prepStmt = con.prepareStatement(query);
                            prepStmt.setString(1, eventId);
                            prepStmt.setString(2, map_id);

                            int count = prepStmt.executeUpdate();
                            if (count > 0) {
                                result = true;
                            }
                        } else {
                            Debug.print("Null values in event r mapping Id " + map_id + " evntId " + eventId);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            Debug.print("SQLException " + e);
            e.printStackTrace();
        } catch (Exception e) {
            Debug.print("General Exception " + e);
            e.printStackTrace();
        } finally {
            releaseConnection(con);
        }
        return result;
    }
    */
  
 public boolean insertEventTypeDetails(String eventId, ArrayList eventTypeLevelId) throws SQLException{
        boolean result = false;
        String proCalendarId = "";
        PreparedStatement prepStmt = null;
        makeConnection();
        try{
            Debug.print("insertEventTypeDetails.eventTypeLevelId: "+eventTypeLevelId);
            if(eventTypeLevelId!=null){
                if(eventTypeLevelId.size()>0){
                    Iterator itr = eventTypeLevelId.iterator();
                    while(itr.hasNext()){
                    String selectQuery = "select pro_calendar_id from tblOEProvisionalCalendar where " +
                            "event_id=?";
                    prepStmt = con.prepareStatement(selectQuery);
                    prepStmt.setString(1,eventId);
                    ResultSet rs = prepStmt.executeQuery();
            
            while(rs.next()){
                proCalendarId = rs.getString(1);
            }
                    Debug.print("proCalendarId in DAO: "+proCalendarId);
                    String eventTypeLevelId1 = (String)itr.next();
                    Debug.print("eventTypeLevelId1 in DAO: "+eventTypeLevelId1);
                    String[] eventTypeLevelId2 = eventTypeLevelId1.split("#");
                    String eventTypeId = eventTypeLevelId2[0];
                    String eventLevelId = eventTypeLevelId2[1];
                    Debug.print("eventTypeId in DAO: "+eventTypeId);
                    Debug.print("eventLevelId in DAO: "+eventLevelId);
                        if(eventTypeId!=null && eventLevelId!=null && eventId!=null){
                            String query ="insert into tblOEChampionshipDetails (championship_id, pro_calendar_id, event_id, event_type_id, event_level_id) " +
                                          "values (newid(), ?, ?, ?, ?)";
                            
                            prepStmt = con.prepareStatement(query);
                            prepStmt.setString(1,proCalendarId);
                            prepStmt.setString(2,eventId);
                            prepStmt.setString(3,eventTypeId);
                            prepStmt.setString(4,eventLevelId);
                            
                            int count = prepStmt.executeUpdate();
                            if(count>0){
                                result = true;
                                Debug.print("count in DAO: "+count);
                            }
                        } else{
                            Debug.print("Null values DAO evntId "+eventId);
                        }
                    }
                }
            }
        } catch(SQLException e){
            Debug.print("SQLException "+e);
            e.printStackTrace();
        } catch(Exception e){
            Debug.print("General Exception "+e);
            e.printStackTrace();
        } finally{
            releaseConnection(con);
        }
        return result;
    }
    
    
    /*=================================Insert Event Registration Request==========================================*/

    /*public boolean insertEventRequestDetails(HLCEventRequestVO requestVO, String userId) throws SQLException {
        boolean result = false;
        Debug.print("UserId in EventsDAO" + userId);
        PreparedStatement prepStmt = null;
        try {
            makeConnection();

            String query = "insert into " + DBHelper.USEA_MMS_EVENTDETAILS + "(event_id, organizer_id, event_title, event_begin_date, event_end_date, competition_location, competition_city," +
                    "competition_state, competition_country, competition_zip, championship_area, status_id, issue_id, user_id) values " +
                    " (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            prepStmt = con.prepareStatement(query);
            prepStmt.setString(1, requestVO.getEvent_id());
            prepStmt.setString(2, requestVO.getOrganizer_id());
            prepStmt.setString(3, requestVO.getEvent_title());
            prepStmt.setDate(4, DBHelper.toSQLDate(requestVO.getEveBegDate()));
            prepStmt.setDate(5, DBHelper.toSQLDate(requestVO.getEveEndDate()));
            prepStmt.setString(6, requestVO.getCompetition_location());
            prepStmt.setString(7, requestVO.getCompetition_city());
            prepStmt.setString(8, requestVO.getCompetition_state());
            prepStmt.setString(9, requestVO.getCompetition_country());
            prepStmt.setString(10, requestVO.getCompetition_zip());
            prepStmt.setString(11, requestVO.getChampionship_area());
            prepStmt.setString(12, requestVO.getStatus_id());
            prepStmt.setString(13, requestVO.getIssueId());
            prepStmt.setString(14, userId);
            int count = prepStmt.executeUpdate();
            if (count > 0) {
                result = true;
            }
            Debug.print("Event Details successfully inserted into MeeEventDetails:" + result);
            prepStmt.close();
            releaseConnection(con);
        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in insertEventRequestDetails:" + sql.getMessage());
            sql.printStackTrace();
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in insertEventRequestDetails:" + e.getMessage());
            e.printStackTrace();
        } finally {
            releaseConnection(con);
        }
        return result;
    }*/
 
  public boolean insertEventRequestDetails(HLCEventRequestVO requestVO, String userId) throws SQLException {
        boolean result = false;
        java.util.Date beginDate = requestVO.getEveBegDate();
        java.util.Date endDate = requestVO.getEveEndDate();
        int noOfDays = (int) ((endDate.getTime() - beginDate.getTime())/(1000*60*60*24));
        Debug.print("noOfDays in EventsDAO: "+noOfDays);
        Debug.print("requestVO.getCompetition_state() in EventsDAO: "+requestVO.getCompetition_state());
        Debug.print("requestVO.getChampionship_area() in EventsDAO: "+requestVO.getChampionship_area());
        int competitionYear = beginDate.getYear()+1900;
        Debug.print("UserId in EventsDAO"+userId);
        PreparedStatement prepStmt = null;
        try{
            makeConnection();
            String query = "insert into tblOEProvisionalCalendar (pro_calendar_id, event_id, organizer_id, event_title, begin_date, " +
                    "end_date, area_id, state_id, competition_year, no_of_days, usea_approval_status, location, city, zip, issue_id) values (newid(), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            prepStmt = con.prepareStatement(query);
            prepStmt.setString(1, requestVO.getEvent_id());
            prepStmt.setString(2, requestVO.getOrganizer_id());
            prepStmt.setString(3, requestVO.getEvent_title());
            prepStmt.setDate(4, DBHelper.toSQLDate(requestVO.getEveBegDate()));
            prepStmt.setDate(5, DBHelper.toSQLDate(requestVO.getEveEndDate()));
            prepStmt.setString(6, requestVO.getChampionship_area());
            prepStmt.setString(7, requestVO.getCompetition_state());
            prepStmt.setInt(8, competitionYear);
            prepStmt.setInt(9, noOfDays);
            prepStmt.setString(10, "Pending");
            prepStmt.setString(11,requestVO.getCompetition_location());
            prepStmt.setString(12,requestVO.getCompetition_city());
            prepStmt.setString(13,requestVO.getCompetition_zip());
            prepStmt.setString(14,requestVO.getIssueId());
            
            int count = prepStmt.executeUpdate();
            if(count>0){
                result =true;
            }
            Debug.print("Event Details successfully inserted into tblOEProvisionalCalendar:" +result);
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in tblOEProvisionalCalendar:" + sql.getMessage());
            sql.printStackTrace();
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in tblOEProvisionalCalendar:" + e.getMessage());
            e.printStackTrace();
        } finally{
            releaseConnection(con);
        }
        return result;
    }
 
    //==========================================from Ganapathy for Area Chair Details based on Member Id=======
    public ArrayList getAreaChairDetails(String status, int year, String memberId) throws SQLException {
        Debug.print("EventsDAO.getAreaChairDetails().. MemberId: " + memberId + "::Year:" + year + "::Status:" + status);
        PreparedStatement prepStmt = null;
        ArrayList eventRegList = new ArrayList();
        makeConnection();
        try {

            String sql = "select A.event_id, A.event_title,A.event_begin_date,A.event_end_date from " + DBHelper.USEA_MMS_EVENTDETAILS + " A, " + DBHelper.USEA_MRO_MAP_AREA_CHAIR + " B where A.championship_area = B.area_id " +
                    "and A.status_id = ? and year(A.event_begin_date) = ? and B.area_chair_id = ? ";

            prepStmt = con.prepareStatement(sql);
            prepStmt.setString(1, status);
            prepStmt.setInt(2, year);
            prepStmt.setString(3, memberId);
            ResultSet rs = prepStmt.executeQuery();

            while (rs.next()) {
                String event_id = rs.getString(1);
                String event_title = rs.getString(2);
                String event_begin_date = rs.getString(3);
                String event_end_date = rs.getString(4);
                String val[] = {event_id, event_title, event_begin_date, event_end_date};
                eventRegList.add(val);
            }
        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in getAreaChairDetails:" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in getAreaChairDetails:" + e.getMessage());
        } finally {
            if (prepStmt != null) {
                prepStmt.close();
            }

            releaseConnection(con);
        }
        return eventRegList;
    }
    //=============================================from Ganapathy for Getting Area Chair Member Id based on Area Id.
    public String getAreaChairMemberId(String areaId) throws SQLException {
        Debug.print("EventsDAO.getAreaChairMemberId().. AreaId: " + areaId);
        PreparedStatement prepStmt = null;
        String areaChairMemberId = "";
        makeConnection();
        try {

            String sql = "select A.area_chair_id from " + DBHelper.USEA_MRO_MAP_AREA_CHAIR + " A where A.area_id = ?";

            prepStmt = con.prepareStatement(sql);
            prepStmt.setString(1, areaId);

            ResultSet rs = prepStmt.executeQuery();

            while (rs.next()) {
                areaChairMemberId = rs.getString(1);
            }
        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in getAreaChairMemberId:" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in getAreaChairMemberId:" + e.getMessage());
        } finally {
            if (prepStmt != null) {
                prepStmt.close();
            }

            releaseConnection(con);
        }

        return areaChairMemberId;
    }

    /*=================================Area Chair Event Registration Request List==========================================*/
    public HLCEventRequestVO getEventRequestDetails(String eventId) throws SQLException {
        Debug.print("EventsDAO.getEventRequestDetails() Event Id:" + eventId);
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        HLCEventRequestVO objEventReqVO = new HLCEventRequestVO();
        makeConnection();
        try {
            String selectStatement = "select A.event_id, A.organizer_id, A.event_title, " +
                    " A.event_begin_date, A.event_end_date, A.competition_location, A.competition_city, " +
                    " A.competition_state, A.competition_country, A.competition_zip, A.championship_area, " +
                    " A.status_id, B.issue_id, B.issue_name, Year(A.event_begin_date) from tblMeeEventDetails A, tblAdvIssueMaster B " +
                    " where A.issue_id = B.issue_id" +
                    " and A.event_id = ? ";

            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, eventId);
            ArrayList mappingTypeIds = new ArrayList();
            String selectStatement1 = "select map_type_id from " + DBHelper.USEA_MRO_EVENT_TYPE_DETAILS + " where event_id=? ";
            PreparedStatement prepStmt2 = con.prepareStatement(selectStatement1);
            prepStmt2.setString(1, eventId);
            ResultSet rs2 = prepStmt2.executeQuery();
            while (rs2.next()) {
                String mappingTypeId = rs2.getString(1);
                mappingTypeIds.add(mappingTypeId);
            }
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                eventId = rs.getString(1);
                String organizerId = rs.getString(2);
                String eventTitle = rs.getString(3);
                java.util.Date eventBeginDate = rs.getDate(4);
                java.util.Date eventEndDate = rs.getDate(5);
                String competitionLocation = rs.getString(6);
                String competitionCity = rs.getString(7);
                String competitionState = rs.getString(8);
                String competitionCountry = rs.getString(9);
                String competitionZip = rs.getString(10);
                String championshipArea = rs.getString(11);
                String statusId = rs.getString(12);
                String issId = rs.getString(13);
                String issName = rs.getString(14);
                int comYr = rs.getInt(15);
                //java.util.Date addDate = new java.util.Date(rs.getDate(13).getTime());

                objEventReqVO.setEvent_id(eventId);
                objEventReqVO.setOrganizer_id(organizerId);
                objEventReqVO.setEvent_title(eventTitle);
                objEventReqVO.setEveBegDate(eventBeginDate);
                objEventReqVO.setEveEndDate(eventEndDate);
                objEventReqVO.setCompetition_location(competitionLocation);
                objEventReqVO.setCompetition_city(competitionCity);
                objEventReqVO.setCompetition_state(competitionState);
                objEventReqVO.setCompetition_country(competitionCountry);
                objEventReqVO.setCompetition_zip(competitionZip);
                objEventReqVO.setChampionship_area(championshipArea);
                objEventReqVO.setStatus_id(statusId);
                objEventReqVO.setIssueId(issId);
                objEventReqVO.setIssueName(issName);
                objEventReqVO.setCompYear(comYr);
                objEventReqVO.setMaping_type_id(mappingTypeIds);

            }
            prepStmt.close();
            releaseConnection(con);
            Debug.print("EventsDAO.getEventRequestDetails():" + objEventReqVO);

        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in EventsDAO.getEventRequestDetails():" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in EventsDAO.getEventRequestDetails():" + e.getMessage());
        }

        return objEventReqVO;
    }

    /*=================================Area Chair Event Registration Request Edit==========================================*/
    public String updateEventRequest(HLCEventRequestVO requestVO) {
        Debug.print("EventsDAO.updateEventRequest():");
        PreparedStatement prepStmt = null;
        ArrayList mapIdList = new ArrayList();
        boolean deleteResult = false;
        boolean result = false;
        String eventId = requestVO.getEvent_id();
        mapIdList = requestVO.getMaping_type_id();
        Debug.print("EventId in EventsDAO :" + eventId);
        Debug.print("mapId in EventsDAO :" + mapIdList);
        makeConnection();
        if (eventId != null && eventId.trim().length() != 0) {
            try {

                String query = "update " + DBHelper.USEA_MMS_EVENTDETAILS + " set organizer_id = ?, event_title = ?, " +
                        "event_begin_date = ? , event_end_date = ?, competition_location = ?, competition_city = ?," +
                        "competition_state = ?, competition_country = ?, competition_zip = ?, championship_area = ?, issue_id = ?" +
                        " where event_id = ? ";

                prepStmt = con.prepareStatement(query);
                requestVO.setEvent_id(eventId);

                prepStmt.setString(1, requestVO.getOrganizer_id());
                prepStmt.setString(2, requestVO.getEvent_title());
                prepStmt.setDate(3, DBHelper.toSQLDate(requestVO.getEveBegDate()));
                prepStmt.setDate(4, DBHelper.toSQLDate(requestVO.getEveEndDate()));
                prepStmt.setString(5, requestVO.getCompetition_location());
                prepStmt.setString(6, requestVO.getCompetition_city());
                prepStmt.setString(7, requestVO.getCompetition_state());
                prepStmt.setString(8, requestVO.getCompetition_country());
                prepStmt.setString(9, requestVO.getCompetition_zip());
                prepStmt.setString(10, requestVO.getChampionship_area());
                prepStmt.setString(11, requestVO.getIssueId());
                prepStmt.setString(12, eventId);

                int cnt = prepStmt.executeUpdate();
                Debug.print("Record updates successfully into MeeEventDetails table in updateEventRequest" + cnt);
                /* if(cnt>=1){
                result = true;
                }
                Debug.print("EventsDAO MeeEventDetails table Status in updateEventRequest:" + result);*/
                prepStmt.close();
                releaseConnection(con);
            } catch (SQLException sql) {
                releaseConnection(con);
                Debug.print("SQL Exception in EventsDAO.updateEventRequest():" + sql.getMessage());
            } catch (Exception e) {
                releaseConnection(con);
                Debug.print("General Exception  in EventsDAO.updateEventRequest():" + e.getMessage());
            }
        }
        if (eventId != null && eventId.trim().length() != 0) {
            try {
                deleteResult = deleteTableRow("event_id", eventId, "tblMeeEventTypeDetails");
                Debug.print("EventsDAO.deleteTableRow() result1: " + deleteResult);

            } catch (Exception e) {
                Debug.print("General Exception in EventsDAO.deleteTableRow():" + e.getMessage());
            }
        }
        //if(deleteResult==true){
        try {
            if (eventId != null && eventId.trim().length() != 0) {
                Iterator itMapList = mapIdList.iterator();
                while (itMapList.hasNext()) {
                    String mapId = (String) itMapList.next();
                    if (mapId != null && mapId.trim().length() != 0) {
                        result = insertEventTypeLevelsRequest(eventId, mapId);
                        Debug.print(" eventId inside insertEventTypeLevelsRequest1:" + eventId);
                        Debug.print(" mapId inside insertEventTypeLevelsRequest1:" + mapId);
                    }
                }
            }
        } catch (Exception e) {
            Debug.print("General Exception in EventsDAO.updateEventRequest():" + e.getMessage());
        }
        //}

        return eventId;
    }
    //=================================Delete Table Row===================================
    public boolean deleteTableRow(String fieldName, String fieldValue, String tableName) {
        Debug.print("EventsDAO.deleteTableRow()");
        boolean result = false;
        makeConnection();
        try {
            String deleteStatement = "delete from " + tableName + " where " + fieldName + " = ? ";
            Debug.print("EventCalendarDAO.deleteTableRow():" + "\n" + deleteStatement + ":" + fieldValue);
            PreparedStatement prepStmt = con.prepareStatement(deleteStatement);
            prepStmt.setString(1, fieldValue);
            int i = prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection(con);
            if (i >= 1) {
                result = true;
            } else {
                result = false;
            }
        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in EventsDAO.deleteTableRow:" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in EventsDAO.deleteTableRow:" + e.getMessage());
        }
        return result;
    }
    //=================================Update Event Type Details===================================
    public boolean insertEventTypeLevelsRequest(String eventId, String mapId) throws SQLException {
        Debug.print(" eventId inside insertEventTypeLevelsRequest2:" + eventId);
        Debug.print(" mapId inside insertEventTypeLevelsRequest2:" + mapId);
        boolean result = false;
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            if (mapId != null && eventId != null) {
                String query = "insert into " + DBHelper.USEA_MRO_EVENT_TYPE_DETAILS + " (event_id, map_type_id) values (?,?)";
                prepStmt = con.prepareStatement(query);
                prepStmt.setString(1, eventId);
                prepStmt.setString(2, mapId);

                int cnt = prepStmt.executeUpdate();
                Debug.print("Record Inserted successfully into insertEventTypeLevelsRequest " + cnt);

                if (cnt >= 1) {
                    result = true;
                }
            } else {
                Debug.print("Null values in event r mapping Id " + mapId + " evntId " + eventId);
            }
            prepStmt.close();
            releaseConnection(con);
        } catch (SQLException e) {
            Debug.print("SQLException " + e);
            e.printStackTrace();
        } catch (Exception e) {
            Debug.print("General Exception " + e);
            e.printStackTrace();
        }
        return result;
    }

    /*=================================Area Chair Event Registration Request Approve==========================================*/
    public boolean changeEventRequestStatus(String eventId, String approveStatus) throws SQLException {
        Debug.print("changeEventRequestStatus eventId:" + eventId + "::status:" + approveStatus);
        boolean result = false;
        int upCnt = 0;
        makeConnection();
        try {

            String updateStatement = "update " + DBHelper.USEA_MMS_EVENTDETAILS + " set status_id = ? " +
                    "where event_id= ?";
            Debug.print("updateStatement query in changeEventRequestStatus:" + updateStatement);
            PreparedStatement prepStmt = con.prepareStatement(updateStatement);
            prepStmt.setString(1, approveStatus);
            prepStmt.setString(2, eventId);

            upCnt = prepStmt.executeUpdate();
            if (upCnt != 0) {
                result = true;
            }
            prepStmt.close();
            releaseConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    /*=================================New Event Registration Issue Names==========================================*/

    public ArrayList selectAllIssues() {
        Debug.print("EventsDAO.selectAllIssues():");
        ArrayList issueList = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
        try {
            String selectStatement = " SELECT issue_id, issue_name from " + DBHelper.USEA_ADV_ISSUE_MASTER + " " +
                    " where omnibus_status='True' ";

            prepStmt = con.prepareStatement(selectStatement);
            rs = prepStmt.executeQuery();
            issueList = new ArrayList();
            while (rs.next()) {
                String issId = rs.getString(1);
                String issName = rs.getString(2);
                String[] strIssueList = {issId, issName};
                issueList.add(strIssueList);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in EventsDAO.selectAllIssues():" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in EventsDAO.selectAllIssues():" + e.getMessage());
        }
        return issueList;
    }
    /*=================================Insert New Event History Details==========================================*/

    public boolean insertEventHistoryDets(HLCEventRequestVO requestVO, int compyear, String appStatus) throws SQLException {
        Debug.print(" EventsDAO inside insertEventHistoryDets:");
        boolean result = false;
        PreparedStatement prepStmt = null;

        try {
            makeConnection();

            String query = "insert into " + DBHelper.USEA_MEE_Event_HISTORY_DETAILS + " (event_id, event_title, organizer_id," +
                    " competition_year, approval_status, payment_id) values (?, ?, ?, ?, ?, ?)";
            prepStmt = con.prepareStatement(query);

            prepStmt.setString(1, requestVO.getEvent_id());
            prepStmt.setString(2, requestVO.getEvent_title());
            prepStmt.setString(3, requestVO.getOrganizer_id());
            prepStmt.setInt(4, compyear);
            prepStmt.setString(5, appStatus);
            prepStmt.setString(6, requestVO.getPayId());

            int cnt = prepStmt.executeUpdate();
            Debug.print("Record Inserted successfully into insertEventHistoryDets " + cnt);

            if (cnt >= 1) {
                result = true;
            }

            prepStmt.close();
            releaseConnection(con);
        } catch (SQLException e) {
            Debug.print("SQLException " + e);
            e.printStackTrace();
        } catch (Exception e) {
            Debug.print("General Exception " + e);
            e.printStackTrace();
        }
        return result;
    }
    /*=================================Update New Event History Details==========================================*/

    public boolean updateEventHistoryDets(HLCEventRequestVO requestVO, int comYr) {
        Debug.print("EventsDAO.updateEventHistoryDets():");
        PreparedStatement prepStmt = null;
        ArrayList mapIdList = new ArrayList();

        boolean result = false;
        String eventId = requestVO.getEvent_id();
        Debug.print("EventId in EventsDAO :" + eventId);
        Debug.print("comYr in EventsDAO :" + comYr);
        makeConnection();
        if (eventId != null && eventId.trim().length() != 0 && comYr != 0) {
            try {

                String query = "update " + DBHelper.USEA_MEE_Event_HISTORY_DETAILS + " set organizer_id = ?, event_title = ? " +
                        " where event_id = ? and competition_year = ? ";

                prepStmt = con.prepareStatement(query);
                requestVO.setEvent_id(eventId);
                requestVO.setCompYear(comYr);

                prepStmt.setString(1, requestVO.getOrganizer_id());
                prepStmt.setString(2, requestVO.getEvent_title());
                prepStmt.setString(3, eventId);
                prepStmt.setInt(4, comYr);

                int cnt = prepStmt.executeUpdate();
                Debug.print("Record updates successfully into History table in updateEventHistoryDets" + cnt);
                if (cnt >= 1) {
                    result = true;
                }
                Debug.print("EventsDAO History table Status in updateEventHistoryDets:" + result);
                prepStmt.close();
                releaseConnection(con);
            } catch (SQLException sql) {
                releaseConnection(con);
                Debug.print("SQL Exception in EventsDAO.updateEventHistoryDets():" + sql.getMessage());
            } catch (Exception e) {
                releaseConnection(con);
                Debug.print("General Exception  in EventsDAO.updateEventHistoryDets():" + e.getMessage());
            }
        }
        return result;
    }

    /*=================================for update Event Details=============================================================*/
    public boolean updateMainEventRegDetails(HLCEventDetailsVO objEventDet) {
        Debug.print("EventsDAO.updateMainEventRegDetails():");
        PreparedStatement prepStmt = null;
        boolean result = false;
        String eventId = objEventDet.getEventId();
        Debug.print("EventId in EventsDAO.updateMainEventRegDetails :" + eventId);
        makeConnection();
        if (eventId != null && eventId.trim().length() != 0) {
            try {

                String query = "update " + DBHelper.USEA_MMS_EVENTDETAILS + " set organizer_id= ?, event_title= ?, " +
                        " event_secretary_id= ?, entry_fee= ?, other_entry_fee= ?, membership_applicable= ?, double_entry_fee_status= ?, " +
                        " office_fee= ?, check_payable_to= ?, pinnies_charge= ?, pinnies_position= ?, award_trophy= ?, award_prize= ?, " +
                        " award_others= ? , date_available= ?, available_from= ?, available_from_other= ?, available_position= ?, " +
                        " stabling_limited= ?, stall_pernight_price= ?, per_stall_price= ?, per_stall_from_time= ?, per_stall_from_date= ?, " +
                        " per_stall_to_time= ?, per_stall_to_date= ?, no_of_stalls= ?, no_of_temp_stalls= ?, no_of_temp_permanent_stalls= ?," +
                        " miles_from_event= ?, veterinarian_name= ?, veterinarian_phone= ?, veterinarian_place= ?," +
                        " accomodation_camping= ?, directions= ?, footing_desc= ? where event_id= ? ";

                prepStmt = con.prepareStatement(query);
                //objEventDet.setEventId(eventId);

                prepStmt.setString(1, objEventDet.getOrganizeId());
                prepStmt.setString(2, objEventDet.getEventTitle());
                prepStmt.setString(3, objEventDet.getEventSecretaryId());
                prepStmt.setString(4, objEventDet.getEntryFee());
                prepStmt.setString(5, objEventDet.getOtherEntryFee());
                prepStmt.setString(6, objEventDet.getMembershipApplicable());
                prepStmt.setString(7, objEventDet.getDoubleEntryFee());
                prepStmt.setString(8, objEventDet.getOfficeFee());
                prepStmt.setString(9, objEventDet.getCheckPayableTo());
                prepStmt.setString(10, objEventDet.getPinniesCharge());
                prepStmt.setString(11, objEventDet.getPinniesPosition());
                prepStmt.setString(12, objEventDet.getAwardTrophy());
                prepStmt.setString(13, objEventDet.getAwardPrize());
                prepStmt.setString(14, objEventDet.getAwardOthers());
                prepStmt.setString(15, objEventDet.getDateAvailable());
                prepStmt.setString(16, objEventDet.getAvailableFrom());
                prepStmt.setString(17, objEventDet.getAvailableFromOther());
                prepStmt.setString(18, objEventDet.getAvailablePosition());

                if (objEventDet.getStablingLimited() != null && objEventDet.getStablingLimited().trim().length() != 0 && objEventDet.getStablingLimited().equals("1")) {
                    prepStmt.setBoolean(19, true);
                } else {
                    prepStmt.setBoolean(19, false);
                }

                if (objEventDet.getStallPernightPrice() == null || objEventDet.getStallPernightPrice().trim().length() != 0) {
                    prepStmt.setFloat(20, 0);
                    Debug.print("       stallPernightPrice: 0");
                } else {
                    prepStmt.setFloat(20, Float.parseFloat(objEventDet.getStallPernightPrice()));
                    Debug.print("       stallPernightPrice:" + Float.parseFloat(objEventDet.getStallPernightPrice()));
                }

                if (objEventDet.getPerStallPrice() == null || objEventDet.getPerStallPrice().trim().length() != 0) {
                    prepStmt.setFloat(21, 0);
                    Debug.print("       perStallPrice: 0");
                } else {
                    prepStmt.setFloat(21, Float.parseFloat(objEventDet.getPerStallPrice()));
                    Debug.print("       perStallPrice:" + Float.parseFloat(objEventDet.getPerStallPrice()));
                }

                prepStmt.setString(22, objEventDet.getPerStallFromTime());

                Debug.print("       perStallFromTime:" + objEventDet.getPerStallFromTime());

                if (objEventDet.getPerStallFromDate() != null && objEventDet.getPerStallFromDate().trim().length() != 0) {
                    Debug.print("       before perStallFromDate:" + objEventDet.getPerStallFromDate());
                    java.util.Date perDate = sdf.parse(objEventDet.getPerStallFromDate());
                    prepStmt.setDate(23, DBHelper.toSQLDate(perDate));
                    Debug.print("       perStallFromDate:" + perDate);
                } else {
                    java.sql.Date dt;// = new Date(); 
                    dt = null;
                    prepStmt.setDate(23, dt);
                    Debug.print("       perStallFromDate: Null");
                }

                prepStmt.setString(24, objEventDet.getPerStallToTime());

                if (objEventDet.getPerStallToDate() != null && objEventDet.getPerStallToDate().trim().length() != 0) {
                    Debug.print("       before perStallToDate:" + objEventDet.getPerStallToDate());
                    java.util.Date pTODate = sdf.parse(objEventDet.getPerStallToDate());
                    prepStmt.setDate(25, DBHelper.toSQLDate(pTODate));
                    Debug.print("       perStallToDate: " + pTODate);
                } else {
                    java.sql.Date dt;// = new Date(); 
                    dt = null;
                    prepStmt.setDate(25, dt);
                    Debug.print("       perStallToDate: Null");
                }
                prepStmt.setString(26, objEventDet.getNoOfStalls());
                prepStmt.setString(27, objEventDet.getNoOfTempStalls());
                prepStmt.setString(28, objEventDet.getNoOfTempPermanentStalls());
                prepStmt.setString(29, objEventDet.getMilesFromEvent());
                prepStmt.setString(30, objEventDet.getVeterinarianName());
                prepStmt.setString(31, objEventDet.getVeterinarianPhone());
                prepStmt.setString(32, objEventDet.getVeterinarianPlace());
                prepStmt.setString(33, objEventDet.getAccomodationCamping());
                prepStmt.setString(34, objEventDet.getDirections());
                prepStmt.setString(35, objEventDet.getFootingDesc());
                //prepStmt.setString(36, "Approved");
                prepStmt.setLong(36, Long.parseLong(objEventDet.getEventId()));

                int cnt = prepStmt.executeUpdate();
                Debug.print("Record updated successfully into tblMeeEventDetails table in updateMainEventRegDetails" + cnt);
                if (cnt >= 1) {
                    result = true;
                }
                Debug.print("EventsDAO tblMeeEventDetails table Status in updateMainEventRegDetails:" + result);
                prepStmt.close();
                releaseConnection(con);
            } catch (SQLException sql) {
                releaseConnection(con);
                Debug.print("SQL Exception in EventsDAO.updateMainEventRegDetails():" + sql.getMessage());
            } catch (Exception e) {
                releaseConnection(con);
                Debug.print("General Exception  in EventsDAO.updateMainEventRegDetails():" + e.getMessage());
            }
        }
        return result;
    }

    /*****************************************************************************************/
    public ArrayList getUserEveRegDetails(String userId, String status) {
        Debug.print("EventsDAO.getUserEveRegDetails() ownerId:" + userId + "" + status);
        ArrayList userEveList = new ArrayList();
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String selectStatement = "select event_id, event_title, " +
                    "add_date, organizer_id from tblMeeEventDetails " +
                    "where user_id= ? and status_id= ?";

            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, userId);
            prepStmt.setString(2, status);

            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()) {
                HLCEventDetailsVO objVO = new HLCEventDetailsVO();
                String eventId = rs.getString(1);
                String eventTitle = rs.getString(2);
                java.util.Date addDate = rs.getDate(3);
                String orgId = rs.getString(4);

                objVO.setEventId(eventId);
                objVO.setEventTitle(eventTitle);
                objVO.setAddDate(addDate);
                objVO.setOrganizeId(orgId);
                userEveList.add(objVO);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in EventsDAO.getUserEveRegDetails():" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in EventsDAO.getUserEveRegDetails():" + e.getMessage());
        }
        Debug.print("Size of userEveList:" + userEveList.size());
        return userEveList;
    }
    //=====================================================================================================================
    public ArrayList getEventsConductedYearWise(int startYear, int endYear) {
        Debug.print("EventsDAO. getEventsConductedYearWise Start Year " + startYear + " End-Year" + endYear);
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        //java.sql.Date fromDate=DBHelper.toSQLDate(startDate);
        //java.sql.Date toDate=DBHelper.toSQLDate(endDate);
        ArrayList yearWise = new ArrayList();
        HashMap yearWiseTotal = null;
        double count;

        String query = "select count(*) from tblMeeEventDetails where  active_status='true' and year(event_begin_date)=?";
        makeConnection();
        try {

            prepStmt = con.prepareStatement(query);
            if (startYear == endYear) {
                Debug.print("Inside HLCEventsDAO for equality startYear " + startYear + " and End-year" + endYear);
                count = 0;
                prepStmt.setString(1, String.valueOf(endYear));
                //prepStmt.setDate(2,fromDate);
                //prepStmt.setDate(3,toDate);
                rs = prepStmt.executeQuery();
                while (rs.next()) {
                    count = Double.parseDouble(rs.getString(1));
                }
                yearWiseTotal = new HashMap();
                yearWiseTotal.put(String.valueOf(endYear), new Double(count));
                yearWise.add(yearWiseTotal);
            } else {

                while (startYear <= endYear) {
                    count = 0;
                    Debug.print("Inside HLCEventsDAO for startYear " + startYear + " and End-year" + endYear);
                    prepStmt = con.prepareStatement(query);
                    prepStmt.setString(1, String.valueOf(startYear));
                    rs = prepStmt.executeQuery();
                    while (rs.next()) {
                        count = Double.parseDouble(rs.getString(1));
                    }
                    yearWiseTotal = new HashMap();
                    yearWiseTotal.put(String.valueOf(startYear), new Double(count));
                    yearWise.add(yearWiseTotal);
                    startYear = startYear + 1;
                }

            }

            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
            Debug.print(e.getMessage());
            releaseConnection(con);
        }

        return yearWise;
    }
    //===========================================getEventsConductedAreaWise method defination ====================
    public ArrayList getEventsConductedAreaWise(java.util.Date startDate, java.util.Date endDate) {
        Debug.print("EventsDAO. getEventsConductedAreaWise :");
        ArrayList eventsAreaWiseCount = new ArrayList();
        HashMap eventsCount = null;
        double count;
        String area_Name = null, area_ID = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        PreparedStatement ps1 = null;
        ResultSet rs1 = null;
        java.sql.Date fromDate = DBHelper.toSQLDate(startDate);
        java.sql.Date toDate = DBHelper.toSQLDate(endDate);
        String query_1 = "select area_name,area_id from tblMeeAreaMaster where active_status='true'";
        String query_2 = "select count(*) from tblMeeEventDetails where status_id='Approved' and active_status='true' and (event_begin_date>=? and event_begin_date<=?) and  championship_area=?";
        makeConnection();
        try {
            ps = con.prepareStatement(query_1);
            rs = ps.executeQuery();
            while (rs.next()) {
                count = 0;
                area_Name = rs.getString(1);
                area_ID = rs.getString(2);
                ps1 = con.prepareStatement(query_2);
                ps1.setDate(1, fromDate);
                ps1.setDate(2, toDate);
                ps1.setString(3, area_ID);
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    count = Double.parseDouble(rs1.getString(1));
                }
                //Debug.print("Area Name"+area_Name+"  Total"+count);
                eventsCount = new HashMap();
                eventsCount.put(area_Name, new Double(count));
                eventsAreaWiseCount.add(eventsCount);
                rs1.close();
                ps1.close();
            }
            rs.close();
            ps.close();
            releaseConnection(con);
        } catch (Exception e) {
            Debug.print("Error Occured " + e.getMessage());
            e.printStackTrace();
            releaseConnection(con);
        }
        return eventsAreaWiseCount;
    }
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public ArrayList getEventsConductedTypeWise(java.util.Date startDate, java.util.Date endDate) {
        Debug.print("EventsDAO. getEventsConductedTypeWise :");
        ArrayList eventsTypeCount = new ArrayList();
        HashMap type1 = new HashMap();
        HashMap type2 = new HashMap();
        HashMap type3 = new HashMap();
        HashMap type4 = new HashMap();
        HashMap type5 = new HashMap();
        HashMap type6 = new HashMap();
        HashMap type7 = new HashMap();
        double count1 = 0, count2 = 0, count3 = 0, count4 = 0, count5 = 0, count6 = 0, count7 = 0;
        java.sql.Date fromDate = DBHelper.toSQLDate(startDate);
        java.sql.Date toDate = DBHelper.toSQLDate(endDate);
        String event_ID = null, map_type_ID = null, event_type_ID = null;
        String query_1 = "select event_id from tblMeeEventDetails where  active_status='true' and (event_begin_date>=? and event_begin_date<=?) ";//removing status id='Approved' from query
        String query_2 = "select map_type_id from tblMeeEventTypeDetails where event_id=?";
        String query_3 = "select event_type_id from tblMeeMapEventLevel where map_type_id=?";
        PreparedStatement ps1 = null, ps2 = null, ps3 = null;
        ResultSet rs1 = null, rs2 = null, rs3 = null;
        makeConnection();
        try {
            ps1 = con.prepareStatement(query_1);
            ps1.setDate(1, fromDate);
            ps1.setDate(2, toDate);
            rs1 = ps1.executeQuery();
            while (rs1.next()) {
                event_ID = rs1.getString(1);
                ps2 = con.prepareStatement(query_2);
                ps2.setString(1, event_ID);
                rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    map_type_ID = rs2.getString(1);
                    ps3 = con.prepareStatement(query_3);
                    ps3.setString(1, map_type_ID);
                    rs3 = ps3.executeQuery();
                    while (rs3.next()) {
                        event_type_ID = rs3.getString(1);
                        if (event_type_ID.equalsIgnoreCase("B2B77C0B-FFE0-484B-9D77-1E4EBE3D7517")) {
                            count1 = count1 + 1;
                        } else if (event_type_ID.equalsIgnoreCase("2833A332-3AF3-4D98-B8FC-46263448385D")) {
                            count2 = count2 + 1;
                        } else if (event_type_ID.equalsIgnoreCase("90AFD446-D139-44C5-8F37-4EEC8358EB1E")) {
                            count3 = count3 + 1;
                        } else if (event_type_ID.equalsIgnoreCase("445C7C02-49FE-402F-A1CF-AC1FB3E51D77")) {
                            count4 = count4 + 1;
                        } else if (event_type_ID.equalsIgnoreCase("6845BA45-AB6C-48C3-964C-B3C7A6571513")) {
                            count5 = count5 + 1;
                        } else if (event_type_ID.equalsIgnoreCase("1EB79456-E38E-4E04-B67D-B7296C5CE64B")) {
                            count6 = count6 + 1;
                        } else if (event_type_ID.equalsIgnoreCase("AC6E6D2A-C80A-4FCE-8FA0-D02D087FB118")) {
                            count7 = count7 + 1;
                        }
                    }
                    rs3.close();
                    ps3.close();
                }
                rs2.close();
                ps2.close();
            }
            rs1.close();
            ps1.close();
            releaseConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
            releaseConnection(con);
        }
        type1.put("YEH", new Double(count1));
        type2.put("TEST", new Double(count2));
        type3.put("HT", new Double(count3));
        type4.put("3D", new Double(count4));
        type5.put("CT", new Double(count5));
        type6.put("FEH", new Double(count6));
        type7.put("2D", new Double(count7));
        eventsTypeCount.add(type1);
        eventsTypeCount.add(type2);
        eventsTypeCount.add(type3);
        eventsTypeCount.add(type4);
        eventsTypeCount.add(type5);
        eventsTypeCount.add(type6);
        eventsTypeCount.add(type7);
        return eventsTypeCount;

    }
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public ArrayList getEventsConductedAreaWiseSpecificEventType(java.util.Date startDate, java.util.Date endDate, String event_type_id) {
        Debug.print("EventsDAO. getEventsConductedAreaWiseSpecificEventType :");
        ArrayList eventsTypeCount = new ArrayList();
        HashMap type1 = new HashMap();
        HashMap type2 = new HashMap();
        HashMap type3 = new HashMap();
        HashMap type4 = new HashMap();
        HashMap type5 = new HashMap();
        HashMap type6 = new HashMap();
        HashMap type7 = new HashMap();
        HashMap type8 = new HashMap();
        HashMap type9 = new HashMap();
        HashMap type10 = new HashMap();
        double count1 = 0, count2 = 0, count3 = 0, count4 = 0, count5 = 0, count6 = 0, count7 = 0, count8 = 0, count9 = 0, count10 = 0;
        java.sql.Date fromDate = DBHelper.toSQLDate(startDate);
        java.sql.Date toDate = DBHelper.toSQLDate(endDate);
        String event_ID = null, map_type_ID = null, event_type_ID_DB = null, championship_area_id = null;
        String query_1 = "select event_id,championship_area from tblMeeEventDetails where  active_status='true' and (event_begin_date>=? and event_begin_date<=?) ";//removing status_id='Approved'
        String query_2 = "select map_type_id from tblMeeEventTypeDetails where event_id=?";
        String query_3 = "select event_type_id from tblMeeMapEventLevel where map_type_id=?";
        PreparedStatement ps1 = null, ps2 = null, ps3 = null;
        ResultSet rs1 = null, rs2 = null, rs3 = null;
        makeConnection();
        try {
            ps1 = con.prepareStatement(query_1);
            ps1.setDate(1, fromDate);
            ps1.setDate(2, toDate);
            rs1 = ps1.executeQuery();
            while (rs1.next()) {
                event_ID = rs1.getString(1);
                championship_area_id = rs1.getString(2);
                ps2 = con.prepareStatement(query_2);
                ps2.setString(1, event_ID);
                rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    map_type_ID = rs2.getString(1);
                    ps3 = con.prepareStatement(query_3);
                    ps3.setString(1, map_type_ID);
                    rs3 = ps3.executeQuery();
                    while (rs3.next()) {
                        event_type_ID_DB = rs3.getString(1);
                        if (event_type_ID_DB.equalsIgnoreCase(event_type_id)) {
                            if (championship_area_id.equalsIgnoreCase("fd39e89e-7806-4332-a7d1-09736886e194")) {
                                count1 = count1 + 1;
                            } else if (championship_area_id.equalsIgnoreCase("440ac928-187d-4f6e-adb7-4b2435b22104")) {
                                count2 = count2 + 1;
                            } else if (championship_area_id.equalsIgnoreCase("d12543d7-cb00-43ba-87f5-594cd6e32bd5")) {
                                count3 = count3 + 1;
                            } else if (championship_area_id.equalsIgnoreCase("c9db9ed2-c5d6-4671-8b44-87d34c72251c")) {
                                count4 = count4 + 1;
                            } else if (championship_area_id.equalsIgnoreCase("a41186f2-717a-4042-89ba-90ae4160a1f7")) {
                                count5 = count5 + 1;
                            } else if (championship_area_id.equalsIgnoreCase("f627a4ea-fd66-4419-ac4e-af9caef2f587")) {
                                count6 = count6 + 1;
                            } else if (championship_area_id.equalsIgnoreCase("09133f6d-48b0-4f07-846d-b44599a7d570")) {
                                count7 = count7 + 1;
                            } else if (championship_area_id.equalsIgnoreCase("fcefcf4f-db92-42f8-b005-b4c7f2650ec6")) {
                                count8 = count8 + 1;
                            } else if (championship_area_id.equalsIgnoreCase("33e1594b-e12a-4652-9279-b85a741d4d05")) {
                                count9 = count9 + 1;
                            } else if (championship_area_id.equalsIgnoreCase("c830b545-6adb-4260-9ad1-dd36f663ed4b")) {
                                count10 = count10 + 1;
                            }


                        }

                    }
                    
                }
               
            }
         rs3.close();ps3.close(); rs2.close();ps2.close(); rs1.close();ps1.close();releaseConnection(con); 
        }   
        
        catch (Exception e) {
               Debug.print("Exception Occurs"+e.getMessage());
               e.printStackTrace();
               releaseConnection(con);
        }
        
        type7.put("Area I",new Double(count7));
        type2.put("Area II",new Double(count2));
        type1.put("Area III",new Double(count1));
        type5.put("Area IV",new Double(count5));
        type6.put("Area V",new Double(count6));
        type8.put("Area VI",new Double(count8));
        type3.put("Area VII",new Double(count3));
        type10.put("Area VIII",new Double(count10));
        type4.put("Area IX",new Double(count4));
        type9.put("Area X",new Double(count9));
        eventsTypeCount.add(type7);
        eventsTypeCount.add(type2);
        eventsTypeCount.add(type1);
        eventsTypeCount.add(type5);
        eventsTypeCount.add(type6);
        eventsTypeCount.add(type8);
        eventsTypeCount.add(type3);
        eventsTypeCount.add(type10);
        eventsTypeCount.add(type4);
        eventsTypeCount.add(type9);
        return eventsTypeCount; 
    }
//==================================================================================
    public Double getHumanMembersParticipatedInEvents(java.util.Date startDate,java.util.Date endDate)
    {
        Debug.print("HLCEventsDAO getHumanMembersParticipatedInEvents");
        PreparedStatement ps=null;ResultSet rs=null;Double db=null;
         java.sql.Date fromDate = DBHelper.toSQLDate(startDate);
        java.sql.Date toDate = DBHelper.toSQLDate(endDate);
        String query="select count(*) from tblMeeCompResultDetails where (add_date>=? and add_date<=?)";
        makeConnection();
        try
        {
            ps=con.prepareStatement(query);
            ps.setDate(1,fromDate);
            ps.setDate(2,toDate);
            rs=ps.executeQuery();
            while(rs.next())
            
                db=Double.valueOf(rs.getString(1));
            
            rs.close();ps.close();releaseConnection(con);
        }
        catch(Exception r)
        {
            r.printStackTrace();
            Debug.print(r.getMessage());
            releaseConnection(con);
        }
        return db;
    }
 //=======================================================================================
    public ArrayList getHumanMembersParticipatedForAllHumanMemberships(java.util.Date startDate,java.util.Date endDate)
    {
        Debug.print("Inside HumanMembersParticipatedForAllHumanMemberships");
        ArrayList humanMembersParticipatedForAllHumanMemberships=new ArrayList();
        String query_1="select rider_member_id from tblMeeCompResultDetails where (add_date>=? and add_date<=?)";
        String query_2="select membership_type_id from tblMemberDetails where member_id=?";
        PreparedStatement ps=null;ResultSet rs=null;PreparedStatement ps1=null;ResultSet rs1=null;
        java.sql.Date fromDate = DBHelper.toSQLDate(startDate);
        java.sql.Date toDate = DBHelper.toSQLDate(endDate);
        String rider_member_ID=null,membership_type_ID=null;
        double type1=0,type2=0,type3=0,type4=0,type5=0,type6=0;
        HashMap memb1 = new HashMap();
        HashMap memb2 = new HashMap();
        HashMap memb3 = new HashMap();
        HashMap memb4 = new HashMap();
        HashMap memb5 = new HashMap();
        HashMap memb6 = new HashMap();
        makeConnection();
        try
        {
            ps=con.prepareStatement(query_1);
            ps.setDate(1,fromDate);
            ps.setDate(2,toDate);
            rs=ps.executeQuery();
            while(rs.next())
            {
                rider_member_ID=rs.getString(1);
                ps1=con.prepareStatement(query_2);
                ps1.setString(1,rider_member_ID);
                rs1=ps1.executeQuery();
                while(rs1.next())
                {
                   membership_type_ID= rs1.getString(1);
                     if(membership_type_ID.equalsIgnoreCase("60BC8025-B043-4771-AD1E-09C9BC160AEE"))
                         type1=type1+1;
                   else if(membership_type_ID.equalsIgnoreCase("A95FAB3A-4C2F-47F5-9161-0AE519290131"))
                         type2=type2+1;
                   else if(membership_type_ID.equalsIgnoreCase("E750ED08-5C4B-4FCA-BFD7-52EBB22BDB2B"))
                         type3=type3+1;
                   else if(membership_type_ID.equalsIgnoreCase("74F758E9-6E03-40DB-A402-9F6B4BD36606"))
                         type4=type4+1;
                   else if(membership_type_ID.equalsIgnoreCase("C57DD232-C425-417F-BC27-CE2C9B7EBC83"))
                         type5=type5+1;
                   else if(membership_type_ID.equalsIgnoreCase("19EB90A6-5B73-46BD-9CD5-DFFB6DC96E12"))
                         type6=type6+1;
                }
                 rs1.close();ps1.close();   
            }
            rs.close();ps.close();releaseConnection(con);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            Debug.print("Exception occured"+e.getMessage());
            releaseConnection(con);
        }
        memb1.put("JR",new Double(type1));
        memb2.put("LIFE",new Double(type2));
        memb3.put("FULL",new Double(type3));
        memb4.put("SUB",new Double(type4));
        memb5.put("FAM",new Double(type5));
        memb6.put("FAM",new Double(type6));
        humanMembersParticipatedForAllHumanMemberships.add(memb1);
        humanMembersParticipatedForAllHumanMemberships.add(memb2);
        humanMembersParticipatedForAllHumanMemberships.add(memb3);
        humanMembersParticipatedForAllHumanMemberships.add(memb4);
        humanMembersParticipatedForAllHumanMemberships.add(memb5);
        humanMembersParticipatedForAllHumanMemberships.add(memb6);
        return humanMembersParticipatedForAllHumanMemberships;
        
        
    }
    public ArrayList getHumanMembersParticipatedEventTypeWise(java.util.Date startDate,java.util.Date endDate)
    {
        Debug.print("Inside getHumanMembersParticipatedEventTypeWise");
        PreparedStatement ps=null;ResultSet rs=null;
        PreparedStatement ps1=null;ResultSet rs1=null;
        ArrayList getHumanMembersParticipatedEventTypeWise=new ArrayList();
        HashMap memb1 = new HashMap();
        HashMap memb2 = new HashMap();
        HashMap memb3 = new HashMap();
        HashMap memb4 = new HashMap();
        HashMap memb5 = new HashMap();
        HashMap memb6 = new HashMap();HashMap memb7 = new HashMap();
        String event_type_ID=null; 
        java.sql.Date fromDate = DBHelper.toSQLDate(startDate);
        java.sql.Date toDate = DBHelper.toSQLDate(endDate);
         double type1=0,type2=0,type3=0,type4=0,type5=0,type6=0,type7=0;
        String query_1="select event_type_id from tblMeeCompResultDetails where (add_date>=? and add_date<=?)";
        makeConnection();
        try
        {
            ps=con.prepareStatement(query_1);
            ps.setDate(1,fromDate);
            ps.setDate(2,toDate);
            rs=ps.executeQuery();
            while(rs.next())
            {
                event_type_ID=rs.getString(1);
                if(event_type_ID.equalsIgnoreCase("b2b77c0b-ffe0-484b-9d77-1e4ebe3d7517"))
                    type1=type1+1;
                else if(event_type_ID.equalsIgnoreCase("2833a332-3af3-4d98-b8fc-46263448385d"))
                    type2=type2+1;
                else if(event_type_ID.equalsIgnoreCase("90afd446-d139-44c5-8f37-4eec8358eb1e"))
                    type3=type3+1;
                else if(event_type_ID.equalsIgnoreCase("445c7c02-49fe-402f-a1cf-ac1fb3e51d77"))
                    type4=type4+1;
                else if(event_type_ID.equalsIgnoreCase("6845ba45-ab6c-48c3-964c-b3c7a6571513"))
                    type5=type5+1;
                else if(event_type_ID.equalsIgnoreCase("1eb79456-e38e-4e04-b67d-b7296c5ce64b"))
                    type6=type6+1;
                else if(event_type_ID.equalsIgnoreCase("ac6e6d2a-c80a-4fce-8fa0-d02d087fb118"))
                    type7=type7+1;
                        
                     
            }
             rs.close();ps=null;releaseConnection(con);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            releaseConnection(con);
        }
        memb1.put("YEH",new Double(type1));
        memb2.put("TEST",new Double(type2));
        memb3.put("HT",new Double(type3));
        memb4.put("3D",new Double(type4));
        memb5.put("CT",new Double(type5));
        memb6.put("FEH",new Double(type6));
        memb7.put("2D",new Double(type7));
        getHumanMembersParticipatedEventTypeWise.add(memb1);
        getHumanMembersParticipatedEventTypeWise.add(memb2);
        getHumanMembersParticipatedEventTypeWise.add(memb3);
        getHumanMembersParticipatedEventTypeWise.add(memb4);
        getHumanMembersParticipatedEventTypeWise.add(memb5);
        getHumanMembersParticipatedEventTypeWise.add(memb6);
        getHumanMembersParticipatedEventTypeWise.add(memb7);
        return getHumanMembersParticipatedEventTypeWise;
       
    }
    public ArrayList getHumanMemeberParticipatedAreaWise(java.util.Date startDate,java.util.Date endDate)
    {
         Debug.print("Inside getHumanMemeberParticipatedAreaWise");
        PreparedStatement ps=null;ResultSet rs=null;
        PreparedStatement ps1=null;ResultSet rs1=null;
        ArrayList getHumanMembersParticipatedAreaWise=new ArrayList();
        HashMap area1 = null;
        String area_ID=null;String area_name=null; 
        java.sql.Date fromDate = DBHelper.toSQLDate(startDate);
        java.sql.Date toDate = DBHelper.toSQLDate(endDate);
         double count;
         String query_1="select area_id, area_name from tblMeeAreaMaster where active_status='true'";
         String query_2="select  count(*) from tblMeeEventDetails A,tblMeeCompResultDetails B where A.event_id=B.event_id and (A.add_date>=? and A.add_date<=?)and A.championship_area= ?";
         makeConnection();
         try{
            ps=con.prepareStatement(query_1);
            rs=ps.executeQuery();
            while(rs.next())
            {
                count=0;
                area_ID=rs.getString(1);
                area_name=rs.getString(2);
                
            ps1=con.prepareStatement(query_2);
            ps1.setDate(1, fromDate);
            ps1.setDate(2, toDate);
            ps1.setString(3, area_ID);
            rs1=ps1.executeQuery();
             while(rs1.next())
             {
                 count=Double.parseDouble(rs1.getString(1));
                
             }
            area1=new HashMap();
            area1.put(area_name,new Double(count));
            getHumanMembersParticipatedAreaWise.add(area1);
            rs1.close();ps1.close();
             
         }
            rs.close();ps.close(); 
         }
         catch(Exception e)
         {
             e.printStackTrace();
            releaseConnection(con);
         }
         releaseConnection(con);
        return  getHumanMembersParticipatedAreaWise;
    }
     public ArrayList getHumanMemberParticipatedMembeAreaWise(java.util.Date startDate,java.util.Date endDate ,String area){
     
         Debug.print("Inside getHumanMemberParticipatedMembeAreaWise");
        PreparedStatement ps=null;ResultSet rs=null;
        PreparedStatement ps1=null;ResultSet rs1=null;
        ArrayList getHumanMemberParticipatedMembeAreaWise=new ArrayList();
        String rider_memb_ID=null;String membership_type_ID=null; 
         double type1=0,type2=0,type3=0,type4=0,type5=0,type6=0,type7=0;
        java.sql.Date fromDate = DBHelper.toSQLDate(startDate);
        java.sql.Date toDate = DBHelper.toSQLDate(endDate);
        HashMap memb1 = new HashMap();
        HashMap memb2 = new HashMap();
        HashMap memb3 = new HashMap();
        HashMap memb4 = new HashMap();
        HashMap memb5 = new HashMap();
        HashMap memb6 = new HashMap();
        String query_1="select  B.rider_member_id from tblMeeEventDetails A,tblMeeCompResultDetails B where A.event_id=B.event_id and (A.add_date>=? and A.add_date<=?) and A.championship_area=? ";
        String query_2="select membership_type_id from tblMemberDetails where member_id=?";
         
         try
         {
             
             makeConnection();
             
              ps=con.prepareStatement(query_1);
              ps.setDate(2, fromDate);
              ps.setDate(1, toDate);
              ps.setString(3, area);
              rs=ps.executeQuery();
              while(rs.next())
              {
                 rider_memb_ID=rs.getString(1);
                // System.out.println("Rider Id"+rider_memb_ID);
                 ps1=con.prepareStatement(query_2);
                 ps1.setString(1, rider_memb_ID);
                 rs1=ps1.executeQuery();
                 while(rs1.next())
                 
                      membership_type_ID= rs1.getString(1);
                 // System.out.println("Membership Id"+membership_type_ID);   
                     if(membership_type_ID.equalsIgnoreCase("60BC8025-B043-4771-AD1E-09C9BC160AEE"))
                         type1=type1+1;
                   else if(membership_type_ID.equalsIgnoreCase("A95FAB3A-4C2F-47F5-9161-0AE519290131"))
                         type2=type2+1;
                   else if(membership_type_ID.equalsIgnoreCase("E750ED08-5C4B-4FCA-BFD7-52EBB22BDB2B"))
                         type3=type3+1;
                   else if(membership_type_ID.equalsIgnoreCase("74F758E9-6E03-40DB-A402-9F6B4BD36606"))
                         type4=type4+1;
                   else if(membership_type_ID.equalsIgnoreCase("C57DD232-C425-417F-BC27-CE2C9B7EBC83"))
                         type5=type5+1;
                   else if(membership_type_ID.equalsIgnoreCase("19EB90A6-5B73-46BD-9CD5-DFFB6DC96E12"))
                         type6=type6+1;
                     
                 
                 rs1.close();ps1.close();
              }
              rs.close();ps.close(); releaseConnection(con);
         }
         catch(Exception e)
         {
             e.printStackTrace();
            releaseConnection(con);
         }
         
        memb1.put("JR",new Double(type1));
        memb2.put("LIFE",new Double(type2));
        memb3.put("FULL",new Double(type3));
        memb4.put("SUB",new Double(type4));
        memb5.put("FAM",new Double(type5));
        memb6.put("FAM",new Double(type6));
        getHumanMemberParticipatedMembeAreaWise.add(memb1);
        getHumanMemberParticipatedMembeAreaWise.add(memb2);
        getHumanMemberParticipatedMembeAreaWise.add(memb3);
        getHumanMemberParticipatedMembeAreaWise.add(memb4);
        getHumanMemberParticipatedMembeAreaWise.add(memb5);
        getHumanMemberParticipatedMembeAreaWise.add(memb6);
        
         
         return getHumanMemberParticipatedMembeAreaWise;
     }
    public ArrayList getMemberParticipatedEventAreaWise(java.util.Date startDate,java.util.Date endDate,String eventType) {
    
        Debug.print("Inside getMemberParticipatedEventAreaWise");
        PreparedStatement ps=null;ResultSet rs=null;
        PreparedStatement ps1=null;ResultSet rs1=null;
        ArrayList getMemberParticipatedEventAreaWise=new ArrayList();
        HashMap area1 = null;
        String area_ID=null;String area_name=null; 
        java.sql.Date fromDate = DBHelper.toSQLDate(startDate);
        java.sql.Date toDate = DBHelper.toSQLDate(endDate);
        double count;
        String query_1="select area_id, area_name from tblMeeAreaMaster where active_status='true'";
        String query_2="select count(*) from tblMeeEventDetails A,tblMeeCompResultDetails B where A.event_id=B.event_id and (A.add_date>=? and A.add_date<=?)A.championship_area=? and B.event_type_id=? ";
         makeConnection();
         try{
            ps=con.prepareStatement(query_1);
            rs=ps.executeQuery();
            while(rs.next()){
               count=0;
              area_ID=rs.getString(1);
              area_name=rs.getString(2);
              
              ps1=con.prepareStatement(query_2);
              ps1.setDate(1, fromDate);
              ps1.setDate(2, toDate);
              ps1.setString(3, area_ID);
              ps1.setString(4,eventType);
              rs1=ps1.executeQuery();
              while(rs1.next())
              {
                   count=Double.parseDouble(rs1.getString(1));
              }   
            area1=new HashMap();
            area1.put(area_name,new Double(count));
            getMemberParticipatedEventAreaWise.add(area1);
            rs1.close();ps1.close();  
            }
         }
         catch(Exception e)
         {
           e.printStackTrace();
           releaseConnection(con);
         }
        
        return getMemberParticipatedEventAreaWise;
    }
 
 public ArrayList selectChampionEveLevelDets(String eventId){
        Debug.print("EventsDAO.selectChampionEveLevelDets():");
        Debug.print("EventsDAO.selectChampionEveLevelDets(): eventId"+eventId);
       // Debug.print("EventsDAO.selectChampionEveLevelDets(): eveTypeId"+eveTypeId);
        ArrayList eveLevList = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
       
        String selectStatement="";
        try {
                    
          
             selectStatement="SELECT distinct A.pro_calendar_id, A.event_level_id, " +
                    "B.event_level_code, A.event_type_id, C.event_type_name from tblOEChampionshipDetails A, " +
                    "tblMeeEventLevelMaster B, tblMeeEventTypeMaster C where A.event_level_id=B.event_level_id " +
                    "and A.event_type_id=C.event_type_id and A.event_id=? order by event_type_name"; 
             
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,eventId);
                      
            Debug.print("EventsDAO.selectChampionEveLevelDets(): eventId" +eventId);              
            rs = prepStmt.executeQuery();
            eveLevList = new ArrayList();
            while(rs.next()){
                String proCalId = rs.getString(1);             
                String eveLevelId = rs.getString(2); 
                String eveLevCode = rs.getString(3);
                String eveTypId = rs.getString(4);
                String eveTypeName = rs.getString(5);
                //  Debug.print("EventsDAO.selectChampionEveLevelDets(): proCalId" +proCalId); 
                  //Debug.print("EventsDAO.selectChampionEveLevelDets(): eveTypeId" +eveTypId); 
                 // Debug.print("EventsDAO.selectChampionEveLevelDets(): eveLevelId" +eveLevelId);
                 // Debug.print("EventsDAO.selectChampionEveLevelDets(): eveLevCode" +eveLevCode); 
                  
                String[] eveList = {proCalId,eveLevelId,eveLevCode,eveTypId,eveTypeName};
                eveLevList.add(eveList);
            }  
          Debug.print("EventsDAO.selectChampionEveLevelDets(): eveLevList1" +eveLevList.size());              
       // }
                                                     
            rs.close();
            prepStmt.close();
            releaseConnection(con);       
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in EventsDAO.selectChampionEveLevelDets():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in EventsDAO.selectChampionEveLevelDets():" + e.getMessage());
        }
        return eveLevList;
    }
     
 public ArrayList selectAllEveTypes(){
        Debug.print("EventsDAO.selectAllEveTypes():");
        ArrayList typeList = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
        try {
            String selectStatement="select event_type_id from tblMeeEventTypeMaster";
            prepStmt = con.prepareStatement(selectStatement);
            rs = prepStmt.executeQuery();
            typeList = new ArrayList();
            while(rs.next()){
                String type_id = rs.getString(1);                         
                String[] strAreaList = {type_id};
                typeList.add(strAreaList);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
            // Debug.print("EventsDAO.selectAllAreaMaster():" + areaList);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in EventsDAO.selectAllEveTypes():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in EventsDAO.selectAllEveTypes():" + e.getMessage());
        }
        return typeList;
    }

 public boolean editEveTitle(String eventId, String eveTitle) {
        Debug.print("EventsDAO.editEveTitle():");
        boolean result = false;
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String insertStatement ="update tblOEProvisionalCalendar set event_title=? where event_id=?";
            prepStmt = con.prepareStatement(insertStatement);
            
            prepStmt.setString(1,eveTitle);
            prepStmt.setString(2,eventId);
                     
            int count = prepStmt.executeUpdate();
            if(count>0){
                result = true;
            }
            //Debug.print("EventsDAO.insertEventFee() result:"+result);
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in EventsDAO.editEveTitle():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in EventsDAO.editEveTitle():" + e.getMessage());
        }
        return result;
    }
 
 public boolean editEveTitleInMee(String eventId, String eveTitle) {
        Debug.print("EventsDAO.editEveTitleInMee():"+eveTitle);
        Debug.print("VaigaiSessionBean editEveTitleInMee eventId:"+eventId);
        boolean result = false;
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String insertStatement ="update tblMeeEventDetails set event_title=? where event_id=?";
            prepStmt = con.prepareStatement(insertStatement);
            
            prepStmt.setString(1,eveTitle);
            prepStmt.setString(2,eventId);
                     
            int count = prepStmt.executeUpdate();
            if(count>0){
                result = true;
            }
            //Debug.print("EventsDAO.insertEventFee() result:"+result);
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in EventsDAO.editEveTitleInMee():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in EventsDAO.editEveTitleInMee():" + e.getMessage());
        }
        return result;
    }
   

 
    
//==============Data Base Conectivity =====================================================================================
    private Connection makeConnection() {
        Debug.print("EventsDAO : makeConnection");
        try {
            InitialContext ic = new InitialContext();
            DataSource ds = (DataSource) ic.lookup(HLCEJBAllJNDIs.USEA_DB);
            con = ds.getConnection();
        } catch (Exception ex) {
            throw new EJBException("Unable to connect to database. " + ex.getMessage());
        }
        return con;
    }

    private void releaseConnection(Connection con) {
        Debug.print("EventsDAO releaseConnection");
        try {
            if (!con.isClosed()) {
                con.close();
            }
        } catch (Exception e) {

        }
    }
}
