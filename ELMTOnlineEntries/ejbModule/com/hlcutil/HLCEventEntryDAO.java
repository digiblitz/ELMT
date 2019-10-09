/*
 * EventEntryDAO.java
 *
 * Created on November 12, 2007, 2:15 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcutil;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import javax.ejb.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import java.util.*;
import java.util.Date;
/**
 *
 * @author Vidhya
 */
public class HLCEventEntryDAO {
    private Connection con = null;
    private PreparedStatement prepStmt = null;
    private ResultSet rs = null;
    private PreparedStatement prepStmt1 = null;
    private ResultSet rs1 = null;
    /** Creates a new instance of EventEntryDAO */
    public HLCEventEntryDAO() {
    }
    public HLCMemberVO selectMemberDetails(String memberId) throws SQLException {
        HLCMemberVO memVO = new HLCMemberVO();
        Debug.print("Inside DAO");
        try {
            makeConnection();
            
            String selStatement = "select A.member_id, C.first_name, C.last_name, C.dob, B.address1, B.city, " +
                    "B.state, B.zip, A.amateur_dec1, A.amateur_dec2, D.membership_type_name, C.email_id from " + DBHelper.OE_MEMBER_DETAILS + " A, " + DBHelper.OE_CONTACT_DETAILS + " B, " + DBHelper.OE_USER_MASTER + " C, " + DBHelper.OE_MEMBERSHIP_TYPE_MASTER + " D " +
                    "where A.user_id = B.user_id and  B.user_id = C.user_id and B.contact_type_id =" +
                    "C.contact_type_id and A.membership_type_id=D.membership_type_id and A.member_id = ? ";
            
            Debug.print("Query :"+selStatement);
            PreparedStatement prepStmt = con.prepareStatement(selStatement);
            prepStmt.setString(1,memberId);
            
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                memVO.setMemberId(rs.getString(1));
                memVO.setMemberName((rs.getString(2) + " " + rs.getString(3)));
                memVO.setAge(rs.getDate(4));
                memVO.setAddress(rs.getString(5));
                memVO.setCity(rs.getString(6));
                memVO.setState(rs.getString(7));
                memVO.setZipcode(rs.getString(8));
                memVO.setDecAmaStatus1(rs.getBoolean(9));
                memVO.setDecAmaStatus2(rs.getBoolean(10));
                memVO.setMembTypName(rs.getString(11));
                memVO.setEmailId(rs.getString(12));
                memVO.setFirstName(rs.getString(2));
                memVO.setLastName(rs.getString(3));
            }
            prepStmt.close();
            releaseConnection();
        }catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in EventEntryDAO.selectMemberDetails():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in EventEntryDAO.selectMemberDetails():" + e.getMessage());
        }
        return memVO;
    }
    
    public ArrayList getHorseDetails(String userId) throws RemoteException{
        Debug.print("Inside DAO");
        Debug.print("userId in getHorseDetails()"+userId);
        ArrayList horseDet = new ArrayList();
        try {
            makeConnection();
            
            String selStmt1 ="select A.horse_id, A.horse_member_id,A.competition_name,D.color_desc, C.gender, E.breed_desc from " +
                    DBHelper.OE_HORSE_MEMBER_DETAILS+" A,"+DBHelper.OE_HORSE_REL_DETAILS+" B, "+DBHelper.OE_HORSE_DESC+" C, "+DBHelper.OE_COLOR_MASTER+" D, "+DBHelper.OE_BREED_MASTER+" E "+
                    " where A.horse_member_id=B.horse_member_id and A.horse_member_id=C.horse_member_id and B.horse_member_id=C.horse_member_id and "+
                    "C.color=D.color_id and C.breed=E.breed_id and B.user_id= ? ";
            
            Debug.print("Query: "+selStmt1);
            prepStmt = con.prepareStatement(selStmt1);
            prepStmt.setString(1,userId);
            Debug.print("userId in set"+userId);
            rs = prepStmt.executeQuery();
            
            while(rs.next()) {
                String horseId = rs.getString(1);
                String horseMemberId = rs.getString(2);
                String horseName = rs.getString(3);
                String color = rs.getString(4);
                String gender=rs.getString(5);
                String breed=rs.getString(6);
                
                HLCHorseVO horseVO = new HLCHorseVO();
                horseVO.setHorseId(horseId);
                horseVO.setHorseMemberId(horseMemberId);
                horseVO.setHorseName(horseName);
                horseVO.setColor(color);
                horseVO.setBreed(gender);
                horseVO.setGender(breed);
                horseDet.add(horseVO);
            }
            
            Debug.print("EventEntryDAO.getHorseDetails"+horseDet.size());
            rs.close();
            prepStmt.close();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in EventEntryDAO.getHorseDetails():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in EventEntryDAO.getHorseDetails():" + e.getMessage());
        } finally {
            releaseConnection();
        }
        return horseDet;
    }
    public ArrayList selectAllPriceItems(String result) throws RemoteException{
        Debug.print("Inside DAO");
        ArrayList itemList = new ArrayList();
        try {
            makeConnection();
            String selStmt1 ="select entry_item_id, entry_item_name, org_edit_status, active_status from tblOEPriceItemMaster " +
                    " where active_status = ?";
            Debug.print("Query: "+selStmt1);
            prepStmt = con.prepareStatement(selStmt1);
            prepStmt.setString(1,result);
            Debug.print("result in selectAllPriceItems"+result);
            rs = prepStmt.executeQuery();
            while(rs.next()) {
                String itemId = rs.getString(1);
                String itemName = rs.getString(2);
                boolean orgEditStatus = rs.getBoolean(3);
                boolean activeStatus = rs.getBoolean(4);
                String tempList[] = {itemId, itemName, String.valueOf(orgEditStatus), String.valueOf(activeStatus)};
                itemList.add(tempList);
            }
            Debug.print("EventEntryDAO.selectAllPriceItems"+itemList.size());
            rs.close();
            prepStmt.close();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in EventEntryDAO.selectAllPriceItems():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in EventEntryDAO.selectAllPriceItems():" + e.getMessage());
        } finally {
            releaseConnection();
        }
        return itemList;
    }
    public ArrayList selectSingleItemDetails(String itemID) throws RemoteException{
        Debug.print("Inside DAO");
        ArrayList itemList = new ArrayList();
        try {
            makeConnection();
            String selStmt1 ="select entry_item_id, entry_item_name, org_edit_status, active_status from tblOEPriceItemMaster where entry_item_id = ?" ;
            Debug.print("Query: "+selStmt1);
            prepStmt = con.prepareStatement(selStmt1);
            prepStmt.setString(1,itemID);
            rs = prepStmt.executeQuery();
            while(rs.next()) {
                String itemId = rs.getString(1);
                String itemName = rs.getString(2);
                boolean orgEditStatus = rs.getBoolean(3);
                boolean activeStatus = rs.getBoolean(4);
                String tempList[] = {itemId, itemName, String.valueOf(orgEditStatus), String.valueOf(activeStatus)};
                itemList.add(tempList);
            }
            Debug.print("EventEntryDAO.getAllPriceItems"+itemList.size());
            rs.close();
            prepStmt.close();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in EventEntryDAO.selectSingleItemDetails():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in EventEntryDAO.selectSingleItemDetails():" + e.getMessage());
        } finally {
            releaseConnection();
        }
        return itemList;
    }
    public boolean updateHorseDetails(String horseMemberId,String userId) {
        Debug.print("DITRegDAO.updateHorseDetails():");
        boolean result = false;
        PreparedStatement prepStmt = null;
        if(horseMemberId!=null && horseMemberId.trim().length()!=0 && userId!=null && userId.trim().length()!=0){
            makeConnection();
            try {
                String updateStatement = "update " + DBHelper.OE_HORSE_MEMBER_DETAILS + " set trainer_id = ? " +
                        " where horse_member_id = ?";
                
                prepStmt = con.prepareStatement(updateStatement);
                prepStmt.setString(1,userId);
                prepStmt.setString(2,horseMemberId);
                
                int cnt = prepStmt.executeUpdate();
                Debug.print("Record Inserted succefully into updateHorseDetails "+cnt);
                
                if(cnt>=1){
                    result = true;
                }
                
                Debug.print("EventEntryDAO updateHorseDetails() Status :" + result);
                prepStmt.close();
                releaseConnection();
            } catch(SQLException sql){
                releaseConnection();
                Debug.print("SQL Exception in EventEntryDAO.updateHorseDetails():" + sql.getMessage());
            } catch(Exception e){
                releaseConnection();
                Debug.print("General Exception  in EventEntryDAO.updateChampStatus():" + e.getMessage());
            }
        }
        return result;
    }
    public boolean updatePriceItemstatus(String itemId,String status) {
        Debug.print("DITRegDAO.updatePriceItemstatus():");
        boolean result = false;
        boolean actStatus=Boolean.parseBoolean(status);
        PreparedStatement prepStmt = null;
        if(itemId!=null && itemId.trim().length()!=0 && status!=null && status.trim().length()!=0){
            makeConnection();
            try {
                String updateStatement = "update " + DBHelper.OE_PRICE_ITEM_MASTER + " set active_status = ? " +
                        " where entry_item_id = ?";
                
                prepStmt = con.prepareStatement(updateStatement);
                prepStmt.setBoolean(1,actStatus);
                prepStmt.setString(2,itemId);
                
                int cnt = prepStmt.executeUpdate();
                Debug.print("Record Inserted succefully into updatePriceItemstatus "+cnt);
                
                if(cnt>=1){
                    result = true;
                }
                
                Debug.print("EventEntryDAO updatePriceItemstatus() Status :" + result);
                prepStmt.close();
                releaseConnection();
            } catch(SQLException sql){
                releaseConnection();
                Debug.print("SQL Exception in EventEntryDAO.updatePriceItemstatus():" + sql.getMessage());
            } catch(Exception e){
                releaseConnection();
                Debug.print("General Exception  in EventEntryDAO.updatePriceItemstatus():" + e.getMessage());
            }
        }
        return result;
    }
    public boolean updatePriceItem(String itemId,String itemName,String orgStatus) {
        Debug.print("DITRegDAO.updatePriceItem():");
        boolean result = false;
        boolean actStatus=Boolean.parseBoolean(orgStatus);
        PreparedStatement prepStmt = null;
        if(itemId!=null && itemId.trim().length()!=0 ){
            makeConnection();
            try {
                String updateStatement = "update " + DBHelper.OE_PRICE_ITEM_MASTER + " set entry_item_name = ? , org_edit_status = ? " +
                        " where entry_item_id = ?";
                
                prepStmt = con.prepareStatement(updateStatement);
                prepStmt.setString(1,itemName);
                prepStmt.setBoolean(2,actStatus);
                prepStmt.setString(3,itemId);
                int cnt = prepStmt.executeUpdate();
                Debug.print("Record Inserted succefully into updatePriceItem "+cnt);
                
                if(cnt>=1){
                    result = true;
                }
                
                Debug.print("EventEntryDAO updatePriceItem() Status :" + result);
                prepStmt.close();
                releaseConnection();
            } catch(SQLException sql){
                releaseConnection();
                Debug.print("SQL Exception in EventEntryDAO.updatePriceItem():" + sql.getMessage());
            } catch(Exception e){
                releaseConnection();
                Debug.print("General Exception  in EventEntryDAO.updatePriceItem():" + e.getMessage());
            }
        }
        return result;
    }
    public boolean insertHorseRelationDetails(String horseMemberId,String userId,String trainerId) {
        Debug.print("DITRegDAO.insertHorseRelationDetails():");
        boolean result = false;
        PreparedStatement prepStmt = null;
        if(horseMemberId!=null && horseMemberId.trim().length()!=0 && userId!=null && userId.trim().length()!=0){
            makeConnection();
            try {
                String insertStatement = "insert into tblHorseMemberRelationDetails (horse_member_id, relationship_type_id, user_id, relationship_status)"+
                        " values (?, ?, ?, ?)";
                Debug.print("query :"+insertStatement);
                prepStmt = con.prepareStatement(insertStatement);
                prepStmt.setString(1,horseMemberId);
                prepStmt.setString(2,trainerId);
                prepStmt.setString(3,userId);
                prepStmt.setString(4,"Active");
                
                int cnt = prepStmt.executeUpdate();
                Debug.print("Record updated succefully into insertHorseRelationDetails "+cnt);
                
                if(cnt>=1){
                    result = true;
                }
                
                Debug.print("EventEntryDAO insertHorseRelationDetails() Status :" + result);
                prepStmt.close();
                releaseConnection();
            } catch(SQLException sql){
                releaseConnection();
                Debug.print("SQL Exception in EventEntryDAO.insertHorseRelationDetails():" + sql.getMessage());
            } catch(Exception e){
                releaseConnection();
                Debug.print("General Exception  in EventEntryDAO.updateHorseRelationDetails():" + e.getMessage());
            }
        }
        return result;
    }
    public String selectUserId(String userName)  throws RemoteException {
        Debug.print("EventEntryDAO.selectUserId():");
        String userId = "";;
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String selectStatement = "SELECT user_id from " + DBHelper.OE_USER_MASTER + " WHERE login_name = ? ";
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, userName);
            ResultSet  rs = prepStmt.executeQuery();
            if (rs.next()) {
                userId = rs.getString(1);
            }
            rs.close();
            Debug.print("EventEntryDAO selectUserId userId:" + userId);
            prepStmt.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in EventEntryDAO.selectUserId():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in EventEntryDAO.selectUserId():" + e.getMessage());
        }
        return userId;
    }
    public String insertUserDetails(String loginName, String firstName, String lastName, String emailId, String contactId)  throws RemoteException {
        Debug.print("EventEntryDAO.insertUserDetails():");
        boolean result = false;
        String userId="";
        PreparedStatement prepStmt = null;
        try{
            userId = getNextId();
        } catch(Exception e){
            Debug.print("Exception while calling getNextId():" + e.getMessage());
        }
        if(userId!=null && userId.trim().length()!=0){
            makeConnection();
            try {
                String insertStatement = "insert into "+ DBHelper.OE_USER_MASTER +" (user_id, login_name, first_name, last_name, email_id, contact_type_id)" +
                        " values (?, ?, ?, ?, ?, ?) ";
                
                prepStmt = con.prepareStatement(insertStatement);
                
                prepStmt.setString(1, userId);
                prepStmt.setString(2, loginName);
                prepStmt.setString(3, firstName);
                prepStmt.setString(4, lastName);
                prepStmt.setString(5, emailId);
                prepStmt.setString(6, contactId);
                int cnt = prepStmt.executeUpdate();
                Debug.print("Record Inserted succefully into EventEntryDAO insertUserDetails "+cnt);
                if(cnt>=1){
                    result = true;
                }
                
                Debug.print("EventEntryDAO insertUserDetails() Status :" + result);
                prepStmt.close();
                releaseConnection();
            } catch(SQLException sql){
                releaseConnection();
                Debug.print("SQL Exception in EventEntryDAO.insertUserDetails():" + sql.getMessage());
            } catch(Exception e){
                releaseConnection();
                Debug.print("General Exception  in EventEntryDAO.insertPublicationDetails():" + e.getMessage());
            }
        }
        return userId;
    }
    public boolean insertContactDetails(String userId,String address,String country,String state,String city, String zipcode,String phone,String fax,String contactId)  throws RemoteException {
        Debug.print("EventEntryDAO.insertPublicationDetails():");
        boolean result = false;
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String insertStatement = "insert into tblContactDetails " +
                    " (user_id, address1, country, state, city, zip, phone_no, fax_no, contact_type_id)" +
                    " values ( ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
            
            prepStmt = con.prepareStatement(insertStatement);
            
            prepStmt.setString(1, userId);
            prepStmt.setString(2, address);
            prepStmt.setString(3, country);
            prepStmt.setString(4, state);
            prepStmt.setString(5, city);
            prepStmt.setString(6, zipcode);
            prepStmt.setString(7, phone);
            prepStmt.setString(8, fax);
            prepStmt.setString(9, contactId);
            
            int cnt = prepStmt.executeUpdate();
            Debug.print("Record Inserted succefully into EventEntryDAO insertPublicationDetails "+cnt);
            if(cnt>=1){
                result = true;
            }
            
            Debug.print("EventEntryDAO insertPublicationDetails() Status :" + result);
            prepStmt.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in EventEntryDAO.insertPublicationDetails():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in EventEntryDAO.insertPublicationDetails():" + e.getMessage());
        }
        return result;
    }
    public boolean insertPriceItem(String itemName,String orgStatus)  throws RemoteException {
        Debug.print("EventEntryDAO.insertPriceItem():");
        boolean result = false;
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String insertStatement = "insert into "+DBHelper.OE_PRICE_ITEM_MASTER+"(entry_item_name, org_edit_status)" +
                    " values ( ?, ?) ";
            
            prepStmt = con.prepareStatement(insertStatement);
            prepStmt.setString(1, itemName);
            prepStmt.setString(2, orgStatus);
            
            int cnt = prepStmt.executeUpdate();
            Debug.print("Record Inserted succefully into EventEntryDAO insertPriceItem "+cnt);
            if(cnt>=1){
                result = true;
            }
            Debug.print("EventEntryDAO insertPriceItem() Status :" + result);
            prepStmt.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in EventEntryDAO.insertPriceItem():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in EventEntryDAO.insertPriceItem():" + e.getMessage());
        }
        return result;
    }
    public String insertNewPriceItem(String itemName,String orgStatus)  throws RemoteException {
        Debug.print("EventEntryDAO.insertNewPriceItem():");
        String itemId = "";
        boolean result=false;
        PreparedStatement prepStmt = null;
        try{
            itemId = getNextId();
        } catch(Exception e){
            Debug.print("Exception while calling getNextId():" + e.getMessage());
        }
        makeConnection();
        try {
            if(itemId!=null){
                String insertStatement = "insert into "+DBHelper.OE_PRICE_ITEM_MASTER+"(entry_item_id, entry_item_name, org_edit_status)" +
                        " values (?, ?, ?) ";
                
                prepStmt = con.prepareStatement(insertStatement);
                prepStmt.setString(1, itemId);
                prepStmt.setString(2, itemName);
                prepStmt.setString(3, orgStatus);
                
                int cnt = prepStmt.executeUpdate();
                Debug.print("Record Inserted succefully into EventEntryDAO insertNewPriceItem "+cnt);
                if(cnt>=1){
                    result = true;
                }
                Debug.print("EventEntryDAO insertNewPriceItem() Status :" + result);
                prepStmt.close();
                releaseConnection();
            }
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in EventEntryDAO.insertNewPriceItem():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in EventEntryDAO.insertNewPriceItem():" + e.getMessage());
        }
        return itemId;
    }
    public String selectRiderId(String horseMemberId){
        Debug.print("HorseRegDAO.selectMemberId() horseMemberId:" + horseMemberId);
        String memberId  = "";
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String selectStatement = "select rider_member_id from tblHorseMemberDetails where horse_member_id= ?";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,horseMemberId.trim());
            ResultSet rs = prepStmt.executeQuery();
            if(rs.next()){
                memberId = rs.getString(1);
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in HorseRegDAO.selectRiderId():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in HorseRegDAO.selectRiderId():" + e.getMessage());
        }
        return memberId;
    }
    
    public ArrayList selectEventDetailsBasedOnEventId(String eventId,String eventTypeID) throws SQLException {
        Debug.print("EventEntryrDAO selectEventDetailsBasedOnEventId()");
        ArrayList eventLevelDetails = new ArrayList();
        makeConnection();
        try{
            
            String selectStatement = "select A.event_type_id, D.event_type_name, A.event_level_id, B.event_level_code  from "+
                    "tblMeeMapEventLevel A, tblMeeEventLevelMaster B, tblMeeEventTypeDetails C, tblMeeEventTypeMaster D  where "+
                    "A.event_level_id = B.event_level_id and A.map_type_id=C.map_type_id and  A.event_type_id = d.event_type_id and "+
                    " C.event_id= ? and A.event_type_id = ? ";
            
            Debug.print("Query :"+selectStatement);
            PreparedStatement prepSelect = con.prepareStatement(selectStatement);
            prepSelect.setString(1,eventId);
            prepSelect.setString(2,eventTypeID);
            ResultSet rs = prepSelect.executeQuery();
            while (rs.next()){
                String eventTypeId = rs.getString(1);
                String eventTypeName = rs.getString(2);
                String eventLevelId = rs.getString(3);
                String eventLevelCode = rs.getString(4);
                
                String [] levelDet = {eventTypeId, eventTypeName, eventLevelId, eventLevelCode};
                eventLevelDetails.add(levelDet);
            }
            rs.close();
            prepSelect.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in EventEntryrDAO.selectEventDetailsBasedOnEventId():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in EventEntryrDAO.selectEventDetailsBasedOnEventId():" + e.getMessage());
        }
        return eventLevelDetails;
    }
    public ArrayList selectEventDetailsByEventId(String eventId) throws SQLException {
        Debug.print("EventEntryrDAO selectEventDetailsByEventId()");
        ArrayList eventLevelDetails = new ArrayList();
        makeConnection();
        try{
            
            String selectStatement = "select A.event_type_id, D.event_type_name, A.event_level_id, B.event_level_code  from "+
                    "tblMeeMapEventLevel A, tblMeeEventLevelMaster B, tblMeeEventTypeDetails C, tblMeeEventTypeMaster D  where "+
                    "A.event_level_id = B.event_level_id and A.map_type_id=C.map_type_id and  A.event_type_id = d.event_type_id and "+
                    " C.event_id= ? ";
            
            Debug.print("Query :"+selectStatement);
            PreparedStatement prepSelect = con.prepareStatement(selectStatement);
            prepSelect.setString(1,eventId);
            
            ResultSet rs = prepSelect.executeQuery();
            while (rs.next()){
                String eventTypeId = rs.getString(1);
                String eventTypeName = rs.getString(2);
                String eventLevelId = rs.getString(3);
                String eventLevelCode = rs.getString(4);
                
                String [] levelDet = {eventTypeId, eventTypeName, eventLevelId, eventLevelCode};
                eventLevelDetails.add(levelDet);
            }
            rs.close();
            prepSelect.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in EventEntryrDAO.selectEventDetailsByEventId():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in EventEntryrDAO.selectEventDetailsByEventId():" + e.getMessage());
        }
        return eventLevelDetails;
    }
    public ArrayList selectEventDetailsBasedOnChamp(String eventId,String eventTypeID) throws SQLException {
        Debug.print("EventEntryrDAO selectEventDetailsBasedOnChamp()");
        ArrayList eventLevelDetails = new ArrayList();
        makeConnection();
        try{
            
            String selectStatement = "select A.event_type_id, D.event_type_name, A.event_level_id, B.event_level_code, E.championship_status from "+
                    "tblMeeMapEventLevel A, tblMeeEventLevelMaster B, tblMeeEventTypeDetails C, tblMeeEventTypeMaster D,tblOEChampionshipDetails E  where "+
                    "A.event_level_id = B.event_level_id and A.map_type_id=C.map_type_id and  A.event_type_id = d.event_type_id and "+
                    "A.event_level_id = E.event_level_id and A.event_type_id = E.event_type_id and C.event_id=E.event_id and "+
                    "A.event_type_id = ? and C.event_id=? and E.championship_status= ? ";
            
            Debug.print("Query :"+selectStatement);
            PreparedStatement prepSelect = con.prepareStatement(selectStatement);
            prepSelect.setString(1,eventTypeID);
            prepSelect.setString(2,eventId);
            prepSelect.setBoolean(3,true);
            ResultSet rs = prepSelect.executeQuery();
            while (rs.next()){
                String eventTypeId = rs.getString(1);
                String eventTypeName = rs.getString(2);
                String eventLevelId = rs.getString(3);
                String eventLevelCode = rs.getString(4);
                boolean champStatus=rs.getBoolean(5);
                
                String [] levelDet = {eventTypeId, eventTypeName, eventLevelId, eventLevelCode, String.valueOf(champStatus)};
                eventLevelDetails.add(levelDet);
            }
            rs.close();
            prepSelect.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in EventEntryrDAO.selectEventDetailsBasedOnChamp():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in EventEntryrDAO.selectEventDetailsBasedOnChamp():" + e.getMessage());
        }
        return eventLevelDetails;
    }
    public ArrayList selectEventDetailsByChamp(String eventId) throws SQLException {
        Debug.print("EventEntryrDAO selectEventDetailsByChamp()");
        ArrayList eventLevelDetails = new ArrayList();
        makeConnection();
        try{
            
            String selectStatement = "select A.event_type_id, D.event_type_name, A.event_level_id, B.event_level_code, E.championship_status from "+
                    "tblMeeMapEventLevel A, tblMeeEventLevelMaster B, tblMeeEventTypeDetails C, tblMeeEventTypeMaster D,tblOEChampionshipDetails E  where "+
                    "A.event_level_id = B.event_level_id and A.map_type_id=C.map_type_id and  A.event_type_id = d.event_type_id and "+
                    "A.event_level_id = E.event_level_id and A.event_type_id = E.event_type_id and C.event_id=E.event_id and "+
                    " C.event_id=? and E.championship_status= ? ";
            
            Debug.print("Query :"+selectStatement);
            PreparedStatement prepSelect = con.prepareStatement(selectStatement);
            prepSelect.setString(1,eventId);
            prepSelect.setBoolean(2,true);
            ResultSet rs = prepSelect.executeQuery();
            while (rs.next()){
                String eventTypeId = rs.getString(1);
                String eventTypeName = rs.getString(2);
                String eventLevelId = rs.getString(3);
                String eventLevelCode = rs.getString(4);
                boolean champStatus=rs.getBoolean(5);
                
                String [] levelDet = {eventTypeId, eventTypeName, eventLevelId, eventLevelCode, String.valueOf(champStatus)};
                eventLevelDetails.add(levelDet);
            }
            rs.close();
            prepSelect.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in EventEntryrDAO.selectEventDetailsByChamp():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in EventEntryrDAO.selectEventDetailsByChamp():" + e.getMessage());
        }
        return eventLevelDetails;
    }
    public ArrayList selectItemNames() throws SQLException {
        Debug.print("EventEntryDAO selectItemNames()");
        ArrayList itemList = new ArrayList();
        makeConnection();
        try{
            String selectStatement = "select entry_item_id, entry_item_name from tblOEPriceItemMaster where org_edit_status = ? and active_status=? ";
            PreparedStatement prepSelect = con.prepareStatement(selectStatement);
            prepSelect.setBoolean(1,true);
            prepSelect.setBoolean(2,true);
            ResultSet rs = prepSelect.executeQuery();
            while (rs.next()){
                String itemId = rs.getString(1);
                String itemName = rs.getString(2);
                String tempList[] = {itemId, itemName};
                itemList.add(tempList);
            }
            rs.close();
            prepSelect.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in EventEntryDAO.selectItemNames():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in EventEntryDAO.selectItemNames():" + e.getMessage());
        }
        return itemList;
    }
    public boolean isTrainerDetailsExist(String horseMemberId) throws SQLException {
        Debug.print("EventEntryDAO isTrainerDetailsExist() horseMemberId :" + horseMemberId);
        boolean result = false;
        try {
            makeConnection();
            
            String selectStatement = "select A.horse_member_id from tblHorseMemberRelationDetails A, " +
                    "tblHorseRelationshipTypeMaster B where B.relationship_type_name = 'trainer' and " +
                    "A.relationship_type_id = B.relationship_type_id and A.horse_member_id = ?";
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,horseMemberId);
            
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                result = true;
            }
            rs.close();
            Debug.print("EventEntryDAO isTrainerDetailsExist() result: " + result);
            prepStmt.close();
        } catch (SQLException sqe) {
            releaseConnection();
            Debug.print("Error while calling isTrainerDetailsExist : "+sqe.getMessage());
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return result;
    }
    public ArrayList selectAllHorseDetailsByOwnerId(String ownerId){
        Debug.print("HorseRegDAO.selectAllHorseDetailsByOwnerId() ownerId:" + ownerId);
        ArrayList horseList = new ArrayList();
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String selectStatement = " select DISTINCT A.horse_member_id, A.competition_name, A.add_date, " +
                    " C.membership_type_name, D.status_name " +
                    " from tblHorseMemberDetails A, tblMemberDetails B, tblMembershipTypeMaster C, " +
                    " tblMembershipStatusMaster D, tblHorseMemberRelationDetails E, tblUserMaster F " +
                    " where A.horse_member_id = B.member_id and B.membership_type_id = C.membership_type_id and " +
                    " A.horse_member_id = E.horse_member_id and B.status_id = D.status_id and " +
                    " E.user_id = F.user_id and F.user_id = ? and D.status_name='active' order by A.add_date desc";
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,ownerId);
            
            ResultSet rs = prepStmt.executeQuery();
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
            prepStmt.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in HorseRegDAO.selectAllHorseDetailsByOwnerId():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in HorseRegDAO.selectAllHorseDetailsByOwnerId():" + e.getMessage());
        }
        Debug.print("Size of User Horse:" + horseList.size());
        return horseList;
    }
    public boolean isItemNameExist(String itemName) {
        Debug.print("EventEntryDAO isItemNameExist");
        Debug.print(" itemName in DAO:" + itemName);
        
        makeConnection();
        boolean result = false;
        try{
            String selectStatement = "select entry_item_id from "+DBHelper.OE_PRICE_ITEM_MASTER+" where entry_item_name = ?";
            PreparedStatement prepSelect = con.prepareStatement(selectStatement);
            prepSelect.setString(1, itemName);
            
            ResultSet rs = prepSelect.executeQuery();
            result = rs.next();
            Debug.print(" Result:" + result);
            rs.close();
            prepSelect.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in EventEntryDAO.isItemNameExist():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in EventEntryDAO.isItemNameExist():" + e.getMessage());
        }
        return result;
    }
    public boolean isEditItemNameExist(String itemId,String itemName) {
        Debug.print("EventEntryDAO isEditItemNameExist");
        Debug.print(" itemId in DAO:" + itemId);
        Debug.print(" itemName in DAO:" + itemName);
        makeConnection();
        boolean result = false;
        try{
            String selectStatement = "select entry_item_id from "+DBHelper.OE_PRICE_ITEM_MASTER+" where entry_item_name = ? and entry_item_id != ?";
            PreparedStatement prepSelect = con.prepareStatement(selectStatement);
            prepSelect.setString(1, itemName);
            prepSelect.setString(2, itemId);
            ResultSet rs = prepSelect.executeQuery();
            result = rs.next();
            Debug.print(" Result:" + result);
            rs.close();
            prepSelect.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in EventEntryDAO.isEditItemNameExist():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in EventEntryDAO.isEditItemNameExist():" + e.getMessage());
        }
        return result;
    }
    public ArrayList selectEventLevelDetails(String eventId) throws SQLException {
        Debug.print("EventEntryrDAO selectEventDetails()");
        ArrayList eventLevelDetails = new ArrayList();
        makeConnection();
        try{
            
            String selectStatement = "select A.event_type_id, D.event_type_name, A.event_level_id, B.event_level_code  from "+
                    DBHelper.OE_EVENT_LEVEL+" A, "+DBHelper.OE_EVENT_LEVEL_MASTER+" B, "+DBHelper.OE_EVENT_TYPE_DETAILS+" C, "+DBHelper.OE_EVENT_TYPE_MASTER+" D  where "+
                    "A.event_level_id = B.event_level_id and A.map_type_id=C.map_type_id and  A.event_type_id = d.event_type_id and C.event_id= ? ";
            
            Debug.print("Query :"+selectStatement);
            PreparedStatement prepSelect = con.prepareStatement(selectStatement);
            prepSelect.setString(1,eventId);
            ResultSet rs = prepSelect.executeQuery();
            
            while (rs.next()){
                String eventTypeId = rs.getString(1);
                String eventTypeName = rs.getString(2);
                String eventLevelId = rs.getString(3);
                String eventLevelCode = rs.getString(4);
                
                String [] levelDet = {eventTypeId, eventTypeName, eventLevelId, eventLevelCode};
                eventLevelDetails.add(levelDet);
            }
            rs.close();
            prepSelect.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in EventEntryrDAO.selectEventDetailsBasedOnEventId():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in EventEntryrDAO.selectEventDetailsBasedOnEventId():" + e.getMessage());
        }
        return eventLevelDetails;
    }
    public ArrayList selectEventLevelDetailsForChamp(String eventId) throws SQLException {
        Debug.print("EventEntryrDAO selectEventDetails()");
        ArrayList eventLevelDetails = new ArrayList();
        makeConnection();
        try{
            
            String selectStatement = "select A.event_type_id, D.event_type_name, A.event_level_id, B.event_level_code, E.championship_status from "+
                    "tblMeeMapEventLevel A, tblMeeEventLevelMaster B, tblMeeEventTypeDetails C, tblMeeEventTypeMaster D,tblOEChampionshipDetails E  where "+
                    "A.event_level_id = B.event_level_id and A.map_type_id=C.map_type_id and  A.event_type_id = d.event_type_id and "+
                    "A.event_level_id = E.event_level_id and A.event_type_id = E.event_type_id and C.event_id=E.event_id and "+
                    " E.event_id=? and E.championship_status= ? ";
            
            Debug.print("Query :"+selectStatement);
            PreparedStatement prepSelect = con.prepareStatement(selectStatement);
            prepSelect.setString(1,eventId);
            prepSelect.setBoolean(2,true);
            ResultSet rs = prepSelect.executeQuery();
            
            while (rs.next()){
                String eventTypeId = rs.getString(1);
                String eventTypeName = rs.getString(2);
                String eventLevelId = rs.getString(3);
                String eventLevelCode = rs.getString(4);
                boolean champ = rs.getBoolean(5);
                
                String [] levelDet = {eventTypeId, eventTypeName, eventLevelId, eventLevelCode, String.valueOf(champ)};
                eventLevelDetails.add(levelDet);
            }
            rs.close();
            prepSelect.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in EventEntryrDAO.selectEventDetailsBasedOnEventId():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in EventEntryrDAO.selectEventDetailsBasedOnEventId():" + e.getMessage());
        }
        return eventLevelDetails;
    }
    public ArrayList selectEventTypeDetails(String eventId) throws SQLException {
        Debug.print("EventEntryrDAO selectEventDetails()");
        ArrayList eventTypeDetails = new ArrayList();
        makeConnection();
        try{
            
            String selectStatement = "select DISTINCT A.event_type_id, D.event_type_name from "+
                    DBHelper.OE_EVENT_LEVEL+" A, "+DBHelper.OE_EVENT_LEVEL_MASTER+" B, "+DBHelper.OE_EVENT_TYPE_DETAILS+" C, "+DBHelper.OE_EVENT_TYPE_MASTER+" D  where "+
                    "A.event_level_id = B.event_level_id and A.map_type_id=C.map_type_id and  A.event_type_id = d.event_type_id and C.event_id= ? ";
            
            Debug.print("Query :"+selectStatement);
            PreparedStatement prepSelect = con.prepareStatement(selectStatement);
            prepSelect.setString(1,eventId);
            ResultSet rs = prepSelect.executeQuery();
            
            while (rs.next()){
                String eventTypeId = rs.getString(1);
                String eventTypeName = rs.getString(2);
                
                String [] typeDet = {eventTypeId, eventTypeName};
                eventTypeDetails.add(typeDet);
            }
            rs.close();
            prepSelect.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in EventEntryrDAO.selectEventDetailsBasedOnEventId():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in EventEntryrDAO.selectEventDetailsBasedOnEventId():" + e.getMessage());
        }
        return eventTypeDetails;
    }
    public ArrayList selectEventTypeDetailsForChamp(String eventId) throws SQLException {
        Debug.print("EventEntryrDAO selectEventDetails()");
        ArrayList eventTypeDetails = new ArrayList();
        makeConnection();
        try{
            
            String selectStatement = "select DISTINCT A.event_type_id, D.event_type_name from "+
                    "tblMeeMapEventLevel A, tblMeeEventLevelMaster B, tblMeeEventTypeDetails C, tblMeeEventTypeMaster D,tblOEChampionshipDetails E  where "+
                    "A.event_level_id = B.event_level_id and A.map_type_id=C.map_type_id and  A.event_type_id = d.event_type_id and "+
                    "A.event_level_id = E.event_level_id and A.event_type_id = E.event_type_id and C.event_id=E.event_id and "+
                    " E.event_id=? and E.championship_status= ? ";
            Debug.print("Query :"+selectStatement);
            PreparedStatement prepSelect = con.prepareStatement(selectStatement);
            prepSelect.setString(1,eventId);
            prepSelect.setBoolean(2,true);
            ResultSet rs = prepSelect.executeQuery();
            
            while (rs.next()){
                String eventTypeId = rs.getString(1);
                String eventTypeName = rs.getString(2);
                
                String [] typeDet = {eventTypeId, eventTypeName};
                eventTypeDetails.add(typeDet);
            }
            rs.close();
            prepSelect.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in EventEntryrDAO.selectEventDetailsBasedOnEventId():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in EventEntryrDAO.selectEventDetailsBasedOnEventId():" + e.getMessage());
        }
        return eventTypeDetails;
    }
    public ArrayList getEventTypeDetWithoutChamp(String eventId) throws SQLException {
        Debug.print("EventEntryrDAO selectEventDetails()"+eventId);
        ArrayList eventTypeDetails = new ArrayList();
        makeConnection();
        try{
            
            String selectStatement = "select DISTINCT A.event_type_id, D.event_type_name from "+
                    "tblMeeMapEventLevel A, tblMeeEventLevelMaster B, tblMeeEventTypeDetails C, tblMeeEventTypeMaster D,tblOEChampionshipDetails E  where "+
                    "A.event_level_id = B.event_level_id and A.map_type_id=C.map_type_id and  A.event_type_id = d.event_type_id and "+
                    "A.event_level_id = E.event_level_id and A.event_type_id = E.event_type_id and C.event_id=E.event_id and "+
                    " E.event_id=? and E.championship_status= ? ";
            Debug.print("Query :"+selectStatement);
            PreparedStatement prepSelect = con.prepareStatement(selectStatement);
            prepSelect.setString(1,eventId);
            prepSelect.setBoolean(2,false);
            ResultSet rs = prepSelect.executeQuery();
            
            while (rs.next()){
                String eventTypeId = rs.getString(1);
                String eventTypeName = rs.getString(2);
                
                String [] typeDet = {eventTypeId, eventTypeName};
                eventTypeDetails.add(typeDet);
            }
            rs.close();
            prepSelect.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in EventEntryrDAO.selectEventDetailsBasedOnEventId():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in EventEntryrDAO.selectEventDetailsBasedOnEventId():" + e.getMessage());
        }
        return eventTypeDetails;
    }
    
    public boolean insertPriceMatrixDetails(HLCPriceMatrixVO objPrice) {
        Debug.print("EventCalendarDAO.insertPriceMatrixDetails():");
        
        boolean result = false;
        PreparedStatement prepStmt = null;
        
        makeConnection();
        try {
            String insertStatement = "insert into " + DBHelper.OE_PRICE_MATRIX + " " +
                    " (event_id, entry_item_id, event_type_id, event_level_id, item_type, " +
                    " charge_type, due_date_price, aft_due_date_price, deposit_price, championship_status)" +
                    " values ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? ) ";
            
            
            prepStmt = con.prepareStatement(insertStatement);
            prepStmt.setString(1, objPrice.getEventId());
            prepStmt.setString(2, objPrice.getEntryItemId());
            prepStmt.setString(3, objPrice.getEventTypeId());
            prepStmt.setString(4, objPrice.getEventLevelId());
            prepStmt.setString(5, objPrice.getItemType());
            prepStmt.setString(6, objPrice.getChargeType());
            prepStmt.setFloat(7, objPrice.getDueDatePrice());
            prepStmt.setFloat(8, objPrice.getAfterDueDatePrice());
            prepStmt.setFloat(9, objPrice.getDepositPrice());
            prepStmt.setBoolean(10, objPrice.getChampionshipStatus());
            
            
            int cnt = prepStmt.executeUpdate();
            Debug.print("Record Inserted succefully into insertPriceMatrixDetails "+cnt);
            
            if(cnt>=1){
                result = true;
            }
            Debug.print("EventCalendarDAO insertPriceMatrixDetails() Status :" + result);
            prepStmt.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in EventCalendarDAO.insertValidationDetails():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in EventCalendarDAO.insertValidationDetails():" + e.getMessage());
        }
        
        return result;
    }
    public ArrayList selectPriceMatrixDetails(String eventId, String itemId) throws SQLException {
        Debug.print("EventCalendarDAO.selectPriceMatrixDetails():");
        ArrayList valList = new ArrayList();
        
        try {
            makeConnection();
            
            String selStatement = "select A.oe_price_id, A.event_id, A.entry_item_id, B.entry_item_name, "+
                    "A.event_type_id, C.event_type_name, A.event_level_id, D.event_level_code, "+
                    "A.item_type, A.charge_type, A.due_date_price, A.aft_due_date_price, "+
                    "A.deposit_price, A.quantity, A.championship_status, A.approve_status from " + DBHelper.OE_PRICE_MATRIX + " A, "+
                    DBHelper.OE_PRICE_ITEM_MASTER + " B," + DBHelper.OE_EVENT_TYPE_MASTER + " C, " + DBHelper.OE_EVENT_LEVEL_MASTER + " D where "+
                    "A.entry_item_id=B.entry_item_id and A.event_type_id=C.event_type_id and "+
                    "A.event_level_id=D.event_level_id and A.event_id= ? and A.entry_item_id= ? and A.championship_status=? ";
            
            PreparedStatement prepStmt = con.prepareStatement(selStatement);
            prepStmt.setString(1,eventId);
            prepStmt.setString(2,itemId);
            prepStmt.setBoolean(3,false);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                HLCPriceMatrixVO priceVO = new HLCPriceMatrixVO();
                
                priceVO.setPriceId(rs.getString(1));
                priceVO.setEventId(rs.getString(2));
                priceVO.setEntryItemId(rs.getString(3));
                priceVO.setEntryItemName(rs.getString(4));
                priceVO.setEventTypeId(rs.getString(5));
                priceVO.setEventTypeName(rs.getString(6));
                priceVO.setEventLevelId(rs.getString(7));
                priceVO.setEventLevelName(rs.getString(8));
                priceVO.setItemType(rs.getString(9));
                priceVO.setChargeType(rs.getString(10));
                priceVO.setDueDatePrice(rs.getFloat(11));
                priceVO.setAfterDueDatePrice(rs.getFloat(12));
                priceVO.setDepositPrice(rs.getFloat(13));
                priceVO.setQuantity(rs.getInt(14));
                priceVO.setChampionshipStatus(rs.getBoolean(15));
                priceVO.setApproveStatus(rs.getString(16));
                valList.add(priceVO);
            }
            prepStmt.close();
            releaseConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return valList;
    }
    public ArrayList selectPriceMatrixDetailsForChamp(String eventId, String itemId) throws SQLException {
        Debug.print("EventCalendarDAO.selectPriceMatrixDetails():");
        ArrayList valList = new ArrayList();
        
        try {
            makeConnection();
            
            String selStatement = "select A.oe_price_id, A.event_id, A.entry_item_id, B.entry_item_name, "+
                    "A.event_type_id, C.event_type_name, A.event_level_id, D.event_level_code, "+
                    "A.item_type, A.charge_type, A.due_date_price, A.aft_due_date_price, "+
                    "A.deposit_price, A.quantity, A.championship_status, A.approve_status from " + DBHelper.OE_PRICE_MATRIX + " A, "+
                    DBHelper.OE_PRICE_ITEM_MASTER + " B," + DBHelper.OE_EVENT_TYPE_MASTER + " C, " + DBHelper.OE_EVENT_LEVEL_MASTER + " D where "+
                    "A.entry_item_id=B.entry_item_id and A.event_type_id=C.event_type_id and "+
                    "A.event_level_id=D.event_level_id and A.event_id= ? and A.entry_item_id= ? and A.championship_status= ? ";
            
            PreparedStatement prepStmt = con.prepareStatement(selStatement);
            prepStmt.setString(1,eventId);
            prepStmt.setString(2,itemId);
            prepStmt.setBoolean(3,true);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                HLCPriceMatrixVO priceVO = new HLCPriceMatrixVO();
                
                priceVO.setPriceId(rs.getString(1));
                priceVO.setEventId(rs.getString(2));
                priceVO.setEntryItemId(rs.getString(3));
                priceVO.setEntryItemName(rs.getString(4));
                priceVO.setEventTypeId(rs.getString(5));
                priceVO.setEventTypeName(rs.getString(6));
                priceVO.setEventLevelId(rs.getString(7));
                priceVO.setEventLevelName(rs.getString(8));
                priceVO.setItemType(rs.getString(9));
                priceVO.setChargeType(rs.getString(10));
                priceVO.setDueDatePrice(rs.getFloat(11));
                priceVO.setAfterDueDatePrice(rs.getFloat(12));
                priceVO.setDepositPrice(rs.getFloat(13));
                priceVO.setQuantity(rs.getInt(14));
                priceVO.setChampionshipStatus(rs.getBoolean(15));
                priceVO.setApproveStatus(rs.getString(16));
                valList.add(priceVO);
            }
            prepStmt.close();
            releaseConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return valList;
    }
    
    public ArrayList selectEventTypeDetailsByItemId(String eventId,String itemId) throws SQLException {
        Debug.print("EventEntryrDAO selectEventTypeDetailsByItemId()");
        ArrayList eventTypeDetails = new ArrayList();
        makeConnection();
        try{
            
            String selectStatement = "select Distinct A.event_type_id, C.event_type_name from "+DBHelper.OE_PRICE_MATRIX+" A, "+
                    DBHelper.OE_EVENT_TYPE_MASTER+" C where A.event_type_id=C.event_type_id "+
                    "and A.event_id= ? and A.entry_item_id= ? and championship_status= ? ";
            Debug.print("Query :"+selectStatement);
            PreparedStatement prepSelect = con.prepareStatement(selectStatement);
            prepSelect.setString(1,eventId);
            prepSelect.setString(2,itemId);
            prepSelect.setBoolean(3,false);
            ResultSet rs = prepSelect.executeQuery();
            
            while (rs.next()){
                String eventTypeId = rs.getString(1);
                String eventTypeName = rs.getString(2);
                
                String [] typeDet = {eventTypeId, eventTypeName};
                eventTypeDetails.add(typeDet);
            }
            rs.close();
            prepSelect.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in EventEntryrDAO.selectEventTypeDetailsByItemId():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in EventEntryrDAO.selectEventTypeDetailsByItemId():" + e.getMessage());
        }
        return eventTypeDetails;
    }
    public ArrayList selectEventTypeDetailsByItemIdForChamp(String eventId,String itemId) throws SQLException {
        Debug.print("EventEntryrDAO selectEventTypeDetailsByItemIdForChamp()");
        ArrayList eventTypeDetails = new ArrayList();
        makeConnection();
        try{
            
            String selectStatement = "select Distinct A.event_type_id, C.event_type_name from "+DBHelper.OE_PRICE_MATRIX+" A, "+
                    DBHelper.OE_EVENT_TYPE_MASTER+" C where A.event_type_id=C.event_type_id "+
                    "and A.event_id= ? and A.entry_item_id= ? and championship_status= ? ";
            
            Debug.print("Query :"+selectStatement);
            PreparedStatement prepSelect = con.prepareStatement(selectStatement);
            prepSelect.setString(1,eventId);
            prepSelect.setString(2,itemId);
            prepSelect.setBoolean(3,true);
            ResultSet rs = prepSelect.executeQuery();
            
            while (rs.next()){
                String eventTypeId = rs.getString(1);
                String eventTypeName = rs.getString(2);
                
                String [] typeDet = {eventTypeId, eventTypeName};
                eventTypeDetails.add(typeDet);
            }
            rs.close();
            prepSelect.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in EventEntryrDAO.selectEventTypeDetailsByItemIdForChamp():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in EventEntryrDAO.selectEventTypeDetailsByItemIdForChamp():" + e.getMessage());
        }
        return eventTypeDetails;
    }
    
    public boolean updatePriceMatrixDetails(HLCPriceMatrixVO priceVO) {
        Debug.print("EventCalendarDAO.updatePriceMatrixDetails():");
        boolean result = false;
        String priceId  = priceVO.getPriceId();
        
        PreparedStatement prepStmt = null;
        
        makeConnection();
        try {
            String updateStatement = "update " + DBHelper.OE_PRICE_MATRIX + " set " +
                    "due_date_price=?, aft_due_date_price=?, deposit_price=?, approve_status=?, approved_by=?  where oe_price_id = ? ";
            
            prepStmt = con.prepareStatement(updateStatement);
            prepStmt.setFloat(1, priceVO.getDueDatePrice());
            prepStmt.setFloat(2, priceVO.getAfterDueDatePrice());
            prepStmt.setFloat(3, priceVO.getDepositPrice());
            prepStmt.setString(4, priceVO.getApproveStatus());
            prepStmt.setString(5, priceVO.getApprovedBY());
            prepStmt.setString(6, priceId);
            
            
            int cnt = prepStmt.executeUpdate();
            Debug.print("Record Inserted succefully into updatePriceMatrixDetails(): "+cnt);
            
            if(cnt>=1){
                result = true;
            }
            Debug.print("EventCalendarDAO updatePriceMatrixDetails(): Status :" + result);
            prepStmt.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in EventCalendarDAO.updatePriceMatrixDetails():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in EventCalendarDAO.updatePriceMatrixDetails():" + e.getMessage());
        }
        
        
        return result;
    }
    public boolean isPriceMatrixExist(String eventId, String itemId, String eventTypeId, String eventLevelId) throws SQLException {
        Debug.print("EventEntryDAO.isPriceMatrixExist():");
        boolean result = false;
        try {
            makeConnection();
            
            String selectStatement = "SELECT oe_price_id FROM " + DBHelper.OE_PRICE_MATRIX + " " +
                    " WHERE event_id=? and entry_item_id = ? and event_type_id= ? and event_level_id=? ";
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,eventId);
            prepStmt.setString(2,itemId);
            prepStmt.setString(3,eventTypeId);
            prepStmt.setString(4,eventLevelId);
            
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                result = true;
            }
            rs.close();
            Debug.print("EventCalendarDAO isPriceMatrixExist() result: " + result);
            prepStmt.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in EventCalendarDAO.isPriceMatrixExist():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in EventCalendarDAO.isPriceMatrixExist():" + e.getMessage());
        }
        return result;
    }
    public ArrayList getEventTiles(String year,String organizerId) throws RemoteException{
        Debug.print("Inside DAO");
        ArrayList eventList = new ArrayList();
        String selStmt1=null;
        try {
            makeConnection();
            
            selStmt1 ="select event_id, event_title from "+DBHelper.OE_PROVISIONAL_CALENDAR+" where competition_year=? and " +
                    "org_approval_status=? and area_chair_approval_status=? and usea_approval_status=? and organizer_id=? ";
            
            Debug.print("Query: "+selStmt1);
            prepStmt = con.prepareStatement(selStmt1);
            prepStmt.setString(1,year);
            prepStmt.setString(2,"Approved");
            prepStmt.setString(3,"Approved");
            prepStmt.setString(4,"Approved");
            prepStmt.setString(5,organizerId);
            /*if(year!=null){
            prepStmt.setString(3,"Pending");
            prepStmt.setString(4,"Pending");
            }else if(year!=null){
            prepStmt.setString(3,"Approved");
            prepStmt.setString(4,"Approved");
            }*/
            rs = prepStmt.executeQuery();
            while(rs.next()) {
                String eventId = rs.getString(1);
                String eventName = rs.getString(2);
                String tempList[] = {eventId, eventName};
                eventList.add(tempList);
            }
            Debug.print("EventEntryDAO.getEventTiles"+eventList.size());
            rs.close();
            prepStmt.close();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in EventEntryDAO.getEventTiles():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in EventEntryDAO.getEventTiles():" + e.getMessage());
        } finally {
            releaseConnection();
        }
        return eventList;
    }
    
    public ArrayList selectHorseValidationDetails(String horseMemberId, String divisionId) throws SQLException {
        Debug.print("EventEntryrDAO selectEventDetails()");
        ArrayList horseValidDetails = new ArrayList();
        makeConnection();
        try{
            
            String selectStatement = "select A.horse_member_id, B.color_code, C.xc_phaseD_jump_penalties, "+
                    "C.xc_phaseD_time_penalties, C.final_points, D.membership_type_id, "+
                    "A.year_foaled from "+DBHelper.OE_HORSE_DESC+" A, "+DBHelper.OE_COLOR_MASTER+" B, "+
                    DBHelper.OE_COMP_RESULTS+" C, "+DBHelper.OE_MEMBER_DETAILS+" D "+
                    "where A.color = B.color_id and A.horse_member_id = D.member_id "+
                    "and A.horse_member_id = C.horse_member_id and A.horse_member_id = ? and C.event_division_id = ?";
            
            PreparedStatement prepSelect = con.prepareStatement(selectStatement);
            prepSelect.setString(1,horseMemberId);
            prepSelect.setString(2,divisionId);
            ResultSet rs = prepSelect.executeQuery();
            while (rs.next()){
                
                String horseMembId = rs.getString(1);
                String colorCode = rs.getString(2);
                String jumpPenalties = rs.getString(3);
                String timePenalties = rs.getString(4);
                String finalPoints = rs.getString(5);
                String membTypeId = rs.getString(6);
                String priorityValue = "0";
                String membTypeName = "";
                if(membTypeId!=null && membTypeId.trim().length()!=0){
                    priorityValue = getMembershipPriority(membTypeId);
                    membTypeName = getMembershipTypeName(membTypeId);
                }
                String yearFoaled = rs.getString(7);
                
                String [] horseDet = {horseMembId, colorCode, jumpPenalties, timePenalties, finalPoints, membTypeId, priorityValue, yearFoaled, membTypeName};
                horseValidDetails.add(horseDet);
            }
            rs.close();
            prepSelect.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in EventEntryrDAO.selectEventDetailsBasedOnEventId():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in EventEntryrDAO.selectEventDetailsBasedOnEventId():" + e.getMessage());
        }
        return horseValidDetails;
    }
    
    public ArrayList getQualificationDetails(String eventTypeId, String eventLevelId, String champStatus, String usrTypeId, String divisionId) throws SQLException {
        Debug.print("EventEntryrDAO getQualificationDetails()");
        ArrayList qualificationDetails = new ArrayList();
        makeConnection();
        
        try{
            String selectStatement = "select A.user_type_id, A.event_type_id, A.event_level_id, A.event_division_id, A.membership_type_name, "+
                    "A.age_min, A.event_level_rank, A.qualification_period, A.min_rank, A.max_rank, A.min_count, "+
                    "A.max_xc_jump_penalties, A.max_xc_time_penalties, A.membership_type_id, C.event_division_name, " +
                    "A.age_max from "+DBHelper.OE_MEE_COMP_QUALIFICATION_DETAILS+" A, "+DBHelper.OE_EVENT_DIVISION_MASTER+" C " +
                    "where A.event_division_id = C.event_division_id and A.event_division_id = ? and "+
                    "A.event_type_id = ? and A.event_level_id = ? and A.championship_status = ? and A.user_type_id = ?";
            
            PreparedStatement prepSelect = con.prepareStatement(selectStatement);
            prepSelect.setString(1, divisionId);
            prepSelect.setString(2,eventTypeId);
            prepSelect.setString(3,eventLevelId);
            prepSelect.setString(4,champStatus);
            prepSelect.setString(5,usrTypeId);
            
            ResultSet rs = prepSelect.executeQuery();
            
            while (rs.next()){
                String userTypeId = rs.getString(1);
                String evTypeId = rs.getString(2);
                String evLevelId = rs.getString(3);
                String eventDivisionId = rs.getString(4);
                String membershipTypeName = rs.getString(5);
                String minAge = rs.getString(6);
                String eventLevelRank = rs.getString(7);
                String qualificationPeriod = rs.getString(8);
                String minRank = rs.getString(9);
                String maxRank = rs.getString(10);
                String minCount = rs.getString(11);
                String jumpPenalties = rs.getString(12);
                String timePenalties = rs.getString(13);
                String membershipTypeId = rs.getString(14);
                String priority = "0";
                if(membershipTypeId!=null && membershipTypeId.trim().length()!=0){
                    priority = getMembershipPriority(membershipTypeId);
                }
                String divisionName = rs.getString(15);
                String maxAge = rs.getString(16);
                
                String [] horseDet = {userTypeId, evTypeId, evLevelId, eventDivisionId, membershipTypeName, minAge, eventLevelRank,
                qualificationPeriod, minRank, maxRank, minCount, jumpPenalties, timePenalties, membershipTypeId, divisionName, maxAge, priority};
                qualificationDetails.add(horseDet);
            }
            rs.close();
            prepSelect.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in EventEntryrDAO.selectEventDetailsBasedOnEventId():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in EventEntryrDAO.selectEventDetailsBasedOnEventId():" + e.getMessage());
        }
        return qualificationDetails;
    }
    
    public ArrayList getRiderDetails(String riderId, String divisionId) throws SQLException {
        Debug.print("EventEntryrDAO getRiderDetails()");
        ArrayList riderDetails = new ArrayList();
        makeConnection();
        
        try{
            
            String selectStatement = "select A.event_id, A.event_type_id, A.event_division_id, A.final_points, A.xc_phaseD_jump_penalties, "+
                    "A.xc_phaseD_time_penalties, B.membership_type_id, C.membership_type_name, C.priority_value "+
                    "from "+DBHelper.OE_COMP_RESULTS+" A, "+DBHelper.OE_MEMBER_DETAILS+" B, "+DBHelper.OE_MEMBERSHIP_TYPE_MASTER+" C "+
                    "where B.membership_type_id = C.membership_type_id and A.rider_member_id = B.member_id and rider_member_id=? and A.event_division_id = ?";
            
            PreparedStatement prepSelect = con.prepareStatement(selectStatement);
            prepSelect.setString(1,riderId);
            prepSelect.setString(2,divisionId);
            ResultSet rs = prepSelect.executeQuery();
            
            while (rs.next()){
                String eventId = rs.getString(1);
                String eventTypeId = rs.getString(2);
                String eventDivisionId = rs.getString(3);
                String finalPoints = rs.getString(4);
                String jumpPenalties = rs.getString(5);
                String timePenalties = rs.getString(6);
                String membershipTypeId = rs.getString(7);
                String membershipTypeName = rs.getString(8);
                String priorityValue = rs.getString(9);
                
                String [] riderDet = {eventId, eventTypeId, eventDivisionId, finalPoints, "0", timePenalties, membershipTypeId, membershipTypeName, priorityValue};
                riderDetails.add(riderDet);
            }
            rs.close();
            prepSelect.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in EventEntryrDAO.selectEventDetailsBasedOnEventId():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in EventEntryrDAO.selectEventDetailsBasedOnEventId():" + e.getMessage());
        }
        return riderDetails;
    }
    
/*    public ArrayList getValidEventDetails(int jumpPenalties, int timePenalties, int finalPoints, String priority, String userTypeId, String divisionId, String chStatus) throws SQLException {
        Debug.print("EventEntryrDAO getRiderDetails()");
        ArrayList validEventDetails = new ArrayList();
        makeConnection();
        
        int pri = 0;
        if(priority!=null && priority.trim().length()!=0) pri = Integer.parseInt(priority);
        
        try{
            
            String selectStatement = "select A.event_type_id, A.event_level_id, A.event_division_id, A.membership_type_name, "+
                    "A.championship_status, B.event_level_code, C.event_type_name, D.event_division_name, E.priority_value from "+
                    DBHelper.OE_MEE_COMP_QUALIFICATION_DETAILS+" A, "+DBHelper.OE_EVENT_LEVEL_MASTER+" B, "+DBHelper.OE_EVENT_TYPE_MASTER+" C, "+
                    DBHelper.OE_EVENT_DIVISION_MASTER+" D, "+DBHelper.OE_MEMBERSHIP_TYPE_MASTER+" E where ";
            
            if(finalPoints!=0){
                selectStatement = selectStatement+"A.min_rank <= '"+finalPoints+"' and A.max_rank >= '"+finalPoints+"' and ";
            }
            
            if(jumpPenalties!=0){
                selectStatement = selectStatement+"A.max_xc_jump_penalties >= '"+jumpPenalties+"' and ";
            }
            
            if(timePenalties!=0){
                selectStatement = selectStatement+"A.max_xc_time_penalties >= '"+timePenalties+"' and ";
            }
            
            if(chStatus!=null && chStatus.trim().length()!=0){
                selectStatement = selectStatement+"A.championship_status = '"+chStatus+"' and ";
            }
            
            if(pri!=0){
                selectStatement = selectStatement+"E.priority_value = '"+pri+"' and ";
            }
            
            if(userTypeId!=null && userTypeId.trim().length()!=0){
                selectStatement = selectStatement+"A.user_type_id = '"+userTypeId+"' and ";
            }
            
            if(divisionId!=null && divisionId.trim().length()!=0){
                selectStatement = selectStatement+"A.event_division_id = '"+divisionId+"' and ";
            }
            
            selectStatement = selectStatement +" A.event_level_id = B.event_level_id and A.event_type_id = C.event_type_id and A.event_division_id = D.event_division_id "+
                    "and A.membership_type_id = E.membership_type_id";
            
            Debug.print(selectStatement);
            
            PreparedStatement prepSelect = con.prepareStatement(selectStatement);
            
            ResultSet rs = prepSelect.executeQuery();
            while (rs.next()){
                String eventTypeId = rs.getString(1);
                String eventLevelId = rs.getString(2);
                String eventDivisionId = rs.getString(3);
                String mTypeName = rs.getString(4);
                String champStatus = rs.getString(5);
                String eventLevelCode = rs.getString(6);
                String eventTypeName = rs.getString(7);
                String eventDivisionName = rs.getString(8);
                int prio = rs.getInt(9);
                
                Debug.print(eventTypeId+" "+eventLevelId+" "+eventDivisionId+" "+mTypeName+" "+champStatus+" "+eventLevelCode+" "+eventTypeName+" "+eventDivisionName+" "+prio);
                String priorityVal = ""+prio;
                
                String [] validDetails = {eventTypeId, eventLevelId, eventDivisionId, mTypeName, champStatus, eventLevelCode, eventTypeName, eventDivisionName, priorityVal};
                validEventDetails.add(validDetails);
            }
            rs.close();
            prepSelect.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in EventEntryrDAO.selectEventDetailsBasedOnEventId():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in EventEntryrDAO.selectEventDetailsBasedOnEventId():" + e.getMessage());
        }
        return validEventDetails;
    }*/
    
    public int getRecordCountForHorse(String qualificationPeriod, String dateToValidate, String horseMemberId){
        int cnt = 0;
        PreparedStatement prepStmt = null;
        makeConnection();
        
        try {
            
            if(qualificationPeriod!=null && qualificationPeriod.trim().length()!=0){
                
                String selectStatement = "select count(*) from "+DBHelper.OE_COMP_RESULTS+" where add_date >DATEADD(month, -?, ?) and horse_member_id = ?";
                
                prepStmt = con.prepareStatement(selectStatement);
                prepStmt.setInt(1,Integer.parseInt(qualificationPeriod));
                prepStmt.setString(2,dateToValidate);
                prepStmt.setString(3,horseMemberId);
                ResultSet rs = prepStmt.executeQuery();
                if(rs.next()){
                    cnt = rs.getInt(1);
                }
                rs.close();
                prepStmt.close();
            }else{
                cnt = 0;
            }
            releaseConnection();
            
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in EventEntryrDAO.getRecordCount():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in EventEntryrDAO.getRecordCount():" + e.getMessage());
        }
        return cnt;
    }
    
    public int getRecordCountForRider(String qualificationPeriod, String dateToValidate, String riderId){
        int cnt = 0;
        PreparedStatement prepStmt = null;
        makeConnection();
        
        try {
            
            if(qualificationPeriod!=null && qualificationPeriod.trim().length()!=0){
                String selectStatement = "select count(*) from "+DBHelper.OE_COMP_RESULTS+" where add_date >DATEADD(month, -?, ?) and rider_member_id = ?";
                
                prepStmt = con.prepareStatement(selectStatement);
                prepStmt.setInt(1,Integer.parseInt(qualificationPeriod));
                prepStmt.setString(2,dateToValidate);
                prepStmt.setString(3,riderId);
                ResultSet rs = prepStmt.executeQuery();
                if(rs.next()){
                    cnt = rs.getInt(1);
                }
                rs.close();
                prepStmt.close();
            }else{
                cnt = 0;
            }
            
            releaseConnection();
            
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in EventEntryrDAO.getRecordCount():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in EventEntryrDAO.getRecordCount():" + e.getMessage());
        }
        return cnt;
    }
    public ArrayList getOrgCompRegList(int year, String eventId, String status, String organizerId) throws SQLException {
        ArrayList organizerList = new ArrayList();
        Debug.print("Inside DAO");
        try {
            makeConnection();
            
            String selStatement = "select registration_id, event_id, organizer_id, competition_year, event_type, event_level,"+
                    " event_division, horse_member_id, rider_member_id, qualify_status from "+DBHelper.OE_COMP_REG_DETAILS+" where"+
                    " competition_year = ? and event_id = ? and organizer_id = ? ";
            
            if(status!=null && status.trim().length()!=0){
                selStatement = selStatement +" and qualify_status = ? ";
            }
            Debug.print("Query: "+selStatement);
            PreparedStatement prepStmt = con.prepareStatement(selStatement);
            prepStmt.setInt(1,year);
            prepStmt.setString(2,eventId);
            prepStmt.setString(3,organizerId);
            if(status!=null && status.trim().length()!=0){
                prepStmt.setString(4,status);
            }
            
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                HLCCompRegistrationVO compVO = new HLCCompRegistrationVO();
                compVO.setRegistrationId(rs.getString(1));
                compVO.setEventId(rs.getString(2));
                compVO.setOrganizerId(rs.getString(3));
                compVO.setCompetitionYear(rs.getInt(4));
                compVO.setEventType(rs.getString(5));
                compVO.setEventLevel(rs.getString(6));
                compVO.setEventDivision(rs.getString(7));
                compVO.setHorseMemberId(rs.getString(8));
                compVO.setRiderMemberId(rs.getString(9));
                compVO.setQualifystatus(rs.getString(10));
                organizerList.add(compVO);
            }
            Debug.print("ArrayList Size: "+organizerList.size());
            prepStmt.close();
            releaseConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return organizerList;
    }
    public ArrayList selectAllItemNamesForStaff() throws SQLException {
        Debug.print("EventEntryDAO selectAllItemNamesForStaff()");
        ArrayList itemList = new ArrayList();
        makeConnection();
        try{
            String selectStatement = "select entry_item_id, entry_item_name from tblOEPriceItemMaster where org_edit_status = ? and active_status=? ";
            PreparedStatement prepSelect = con.prepareStatement(selectStatement);
            prepSelect.setBoolean(1,false);
            prepSelect.setBoolean(2,true);
            ResultSet rs = prepSelect.executeQuery();
            while (rs.next()){
                String itemId = rs.getString(1);
                String itemName = rs.getString(2);
                String tempList[] = {itemId, itemName};
                itemList.add(tempList);
            }
            rs.close();
            prepSelect.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in EventEntryDAO.selectAllItemNamesForStaff():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in EventEntryDAO.selectAllItemNamesForStaff():" + e.getMessage());
        }
        return itemList;
    }
    
    public boolean insertFixedPriceMatrix(HLCPriceMatrixVO objPrice) {
        Debug.print("EventCalendarDAO.insertFixedPriceMatrix():");
        
        boolean result = false;
        PreparedStatement prepStmt = null;
        
        makeConnection();
        try {
            String insertStatement = "insert into " + DBHelper.OE_FIXED_PRICE + " " +
                    " (entry_item_id, event_type_id, event_level_id, area_id, due_date_price, aft_due_date_price, deposit_price, championship_status)" +
                    " values ( ? , ? , ? , ? , ? , ? , ? , ? ) ";
            
            
            prepStmt = con.prepareStatement(insertStatement);
            prepStmt.setString(1, objPrice.getEntryItemId());
            prepStmt.setString(2, objPrice.getEventTypeId());
            prepStmt.setString(3, objPrice.getEventLevelId());
            prepStmt.setString(4, objPrice.getAreaId());
            prepStmt.setFloat(5, objPrice.getDueDatePrice());
            prepStmt.setFloat(6, objPrice.getAfterDueDatePrice());
            prepStmt.setFloat(7, objPrice.getDepositPrice());
            prepStmt.setBoolean(8, objPrice.getChampionshipStatus());
            
            
            int cnt = prepStmt.executeUpdate();
            Debug.print("Record Inserted succefully into insertPriceMatrixDetails "+cnt);
            
            if(cnt>=1){
                result = true;
            }
            Debug.print("EventCalendarDAO insertPriceMatrixDetails() Status :" + result);
            prepStmt.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in EventCalendarDAO.insertValidationDetails():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in EventCalendarDAO.insertValidationDetails():" + e.getMessage());
        }
        
        return result;
    }
    public ArrayList selectFixedPriceMatrixDetails(String itemId, String eventTypeId) throws SQLException {
        Debug.print("EventCalendarDAO.selectFixedPriceMatrixDetails():");
        ArrayList valList = new ArrayList();
        
        try {
            makeConnection();
            
            String selStatement = "select A.oe_fix_price_id, A.entry_item_id, B.entry_item_name, "+
                    "A.event_type_id, C.event_type_name, A.event_level_id, D.event_level_code, A.area_id, "+
                    " A.due_date_price, A.aft_due_date_price, A.deposit_price, A.championship_status from " + DBHelper.OE_FIXED_PRICE + " A, "+
                    DBHelper.OE_PRICE_ITEM_MASTER + " B," + DBHelper.OE_EVENT_TYPE_MASTER + " C, " + DBHelper.OE_EVENT_LEVEL_MASTER + " D where "+
                    "A.entry_item_id=B.entry_item_id and A.event_type_id=C.event_type_id and "+
                    "A.event_level_id=D.event_level_id  and A.entry_item_id = ? and A.event_type_id = ? ";
            
            
            Debug.print("selStatement: "+selStatement);
            PreparedStatement prepStmt = con.prepareStatement(selStatement);
            prepStmt.setString(1,itemId);
            prepStmt.setString(2,eventTypeId);
            
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                HLCPriceMatrixVO priceVO = new HLCPriceMatrixVO();
                
                priceVO.setPriceId(rs.getString(1));
                priceVO.setEntryItemId(rs.getString(2));
                priceVO.setEntryItemName(rs.getString(3));
                priceVO.setEventTypeId(rs.getString(4));
                priceVO.setEventTypeName(rs.getString(5));
                priceVO.setEventLevelId(rs.getString(6));
                priceVO.setEventLevelName(rs.getString(7));
                priceVO.setAreaId(rs.getString(8));
                priceVO.setDueDatePrice(rs.getFloat(9));
                priceVO.setAfterDueDatePrice(rs.getFloat(10));
                priceVO.setDepositPrice(rs.getFloat(11));
                priceVO.setChampionshipStatus(rs.getBoolean(12));
                
                valList.add(priceVO);
            }
            prepStmt.close();
            releaseConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return valList;
    }
    public ArrayList getFixedPriceMatrixForChamp(String areaId) throws SQLException {
        Debug.print("EventCalendarDAO.getFixedPriceMatrixForChamp():");
        ArrayList valList = new ArrayList();
        if(areaId!=null && areaId.trim().length()!=0){
            try {
                makeConnection();
                
                String selStatement = "select A.oe_fix_price_id, A.entry_item_id, B.entry_item_name, "+
                        " A.event_level_id, D.event_level_code, A.area_id, A.due_date_price, A.aft_due_date_price, A.deposit_price, A.championship_status from " + DBHelper.OE_FIXED_PRICE + " A, "+
                        DBHelper.OE_PRICE_ITEM_MASTER + " B, " + DBHelper.OE_EVENT_LEVEL_MASTER + " D where "+
                        "A.entry_item_id=B.entry_item_id  and A.event_level_id=D.event_level_id  and A.area_id = ? ";
                
                
                Debug.print("selStatement: "+selStatement);
                PreparedStatement prepStmt = con.prepareStatement(selStatement);
                prepStmt.setString(1,areaId);
                
                ResultSet rs = prepStmt.executeQuery();
                while (rs.next()){
                    HLCPriceMatrixVO priceVO = new HLCPriceMatrixVO();
                    
                    priceVO.setPriceId(rs.getString(1));
                    priceVO.setEntryItemId(rs.getString(2));
                    priceVO.setEntryItemName(rs.getString(3));
                    priceVO.setEventLevelId(rs.getString(4));
                    priceVO.setEventLevelName(rs.getString(5));
                    priceVO.setAreaId(rs.getString(6));
                    priceVO.setDueDatePrice(rs.getFloat(7));
                    priceVO.setAfterDueDatePrice(rs.getFloat(8));
                    priceVO.setDepositPrice(rs.getFloat(9));
                    priceVO.setChampionshipStatus(rs.getBoolean(10));
                    
                    valList.add(priceVO);
                }
                prepStmt.close();
                releaseConnection();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return valList;
    }
    public boolean updateFixedPriceMatrixDetails(HLCPriceMatrixVO priceVO) {
        Debug.print("EventCalendarDAO.updateFixedPriceMatrixDetails():");
        boolean result = false;
        String priceId  = priceVO.getPriceId();
        boolean deleteResult=false;
        
        if(priceId!=null && priceId.trim().length()!=0){
            try{
                deleteResult = deletePriceDetails(priceId);
                Debug.print("EventCalendarDAO.deleteValidDetails() result: " +  deleteResult);
            } catch(Exception e){
                Debug.print("General Exception in EventCalendarDAO.deleteValidDetails():" + e.getMessage());
            }
        }
        if(deleteResult==true){
            PreparedStatement prepStmt = null;
            makeConnection();
            try {
                
                String insertStatement = "insert into " + DBHelper.OE_FIXED_PRICE + " " +
                        " (entry_item_id, event_type_id, event_level_id, area_id, due_date_price, aft_due_date_price, deposit_price, championship_status)" +
                        " values ( ? , ? , ? , ? , ? , ? , ? , ? ) ";
                
                prepStmt = con.prepareStatement(insertStatement);
                prepStmt.setString(1, priceVO.getEntryItemId());
                prepStmt.setString(2, priceVO.getEventTypeId());
                prepStmt.setString(3, priceVO.getEventLevelId());
                prepStmt.setString(4, priceVO.getAreaId());
                prepStmt.setFloat(5, priceVO.getDueDatePrice());
                prepStmt.setFloat(6, priceVO.getAfterDueDatePrice());
                prepStmt.setFloat(7, priceVO.getDepositPrice());
                prepStmt.setBoolean(8, priceVO.getChampionshipStatus());
                
                
                int cnt = prepStmt.executeUpdate();
                Debug.print("Record Inserted succefully into updateFixedPriceMatrixDetails(): "+cnt);
                
                if(cnt>=1){
                    result = true;
                }
                Debug.print("EventCalendarDAO updateFixedPriceMatrixDetails(): Status :" + result);
                prepStmt.close();
                releaseConnection();
            } catch(SQLException sql){
                releaseConnection();
                Debug.print("SQL Exception in EventCalendarDAO.updateFixedPriceMatrixDetails():" + sql.getMessage());
            } catch(Exception e){
                releaseConnection();
                Debug.print("General Exception  in EventCalendarDAO.updateFixedPriceMatrixDetails():" + e.getMessage());
            }
            
        }
        return result;
    }
    
    public boolean deletePriceDetails(String priceId) throws SQLException {
        Debug.print("EventCalendarDAO deletePriceDetails():" + priceId);
        boolean result = false;
        try {
            makeConnection();
            
            String selectStatement = "delete FROM " + DBHelper.OE_FIXED_PRICE + " WHERE oe_fix_price_id = ? ";
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,priceId);
            
            int cnt = prepStmt.executeUpdate();
            if (cnt >= 1) {
                result = true;
            }
            Debug.print("EventCalendarDAO deletePriceDetails() result: " + result);
            prepStmt.close();
        } catch (SQLException sqe) {
            releaseConnection();
            Debug.print("Error while calling deletePriceDetails : "+sqe.getMessage());
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return result;
    }
    public HLCCompRegistrationVO getSingleCompRegDetails(String registrationId) throws SQLException {
        HLCCompRegistrationVO regVO = new HLCCompRegistrationVO();
        Debug.print("Inside DAO");
        try {
            makeConnection();
            
            String selStatement = "Select A.registration_id, A.tmp_registration_id, A.event_id, A.organizer_id, A.competition_year," +
                    " A.event_type, A.event_level, A.event_division, A.event_sub_division, A.event_division_amateur, " +
                    " A.event_division_age, A.event_division_experience, A.horse_name, A.horse_member_id, B.event_title, " +
                    " A.horse_usef_id , A.rider_first_name , A.rider_last_name, A.rider_member_id , A.rider_usef_id," +
                    " A.owner_first_name , A.owner_last_name , A.owner_usea_id , A.owner_usef_id," +
                    " A.comp_result_id, A.qualification_file_path, A.qualify_status, A.comments, A.oe_payment_id" +
                    " from tblMeeCompRegistrationDetails A, tblOEProvisionalCalendar B where A.event_id=B.event_id and A.registration_id = ?";
            
            PreparedStatement prepStmt = con.prepareStatement(selStatement);
            prepStmt.setString(1,registrationId);
            
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                regVO.setRegistrationId(rs.getString(1));
                regVO.setTmpRegistrationId(rs.getString(2));
                regVO.setEventId(rs.getString(3));
                regVO.setOrganizerId(rs.getString(4));
                regVO.setCompetitionYear(rs.getInt(5));
                regVO.setEventType(rs.getString(6));
                regVO.setEventLevel(rs.getString(7));
                regVO.setEventDivision(rs.getString(8));
                regVO.setEventSubDivision(rs.getString(9));
                regVO.setEventDivAmateur(rs.getString(10));
                regVO.setEventDivAge(rs.getString(11));
                regVO.setEventDivExp(rs.getString(12));
                regVO.setHorseName(rs.getString(13));
                regVO.setHorseMemberId(rs.getString(14));
                regVO.setEventTitle(rs.getString(15));
                regVO.setHorseUSEFId(rs.getString(16));
                regVO.setRiderFirstName(rs.getString(17));
                regVO.setRiderLastName(rs.getString(18));
                regVO.setRiderMemberId(rs.getString(19));
                regVO.setRiderUSEFId(rs.getString(20));
                regVO.setOwnerFirstName(rs.getString(21));
                regVO.setOwnerLastName(rs.getString(22));
                regVO.setOwnerUSEAId(rs.getString(23));
                regVO.setOwnerUSEFId(rs.getString(24));
                regVO.setCompResultId(rs.getString(25));
                regVO.setQualFilePath(rs.getString(26));
                regVO.setQualifystatus(rs.getString(27));
                regVO.setQuaComments(rs.getString(28));
                regVO.setPaymemtId(rs.getString(29));
            }
            prepStmt.close();
            releaseConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return regVO;
    }
    
    public boolean approveSingleCompRegDetails(String registrationId, String qualifyStatus, String comments) throws SQLException {
        Debug.print("Inside DAO:approveSingleCompRegDetails");
        boolean result = false;
        int upCnt = 0;
        try {
            makeConnection();
            
            String updateStatement = "update " + DBHelper.OE_COMP_REG_DETAILS+" set qualify_status = ?, "+
                    "comments = ? where registration_id= ?";
            
            Debug.print("updateStatement in approveSingleCompRegDetails "+updateStatement);
            PreparedStatement prepStmt = con.prepareStatement(updateStatement);
            
            prepStmt.setString(1,qualifyStatus);
            prepStmt.setString(2,comments);
            prepStmt.setString(3,registrationId);
            
            upCnt = prepStmt.executeUpdate();
            if(upCnt!=0) result = true;
            
            prepStmt.close();
            releaseConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public boolean insertCompResultUploadDetails(String event_id,String event_name,String result_file_path,String eventTypeId) throws SQLException {
        Debug.print("Inside insertCompResultUploadDetails :");
        boolean result = false;
        String uploadId = "";
        
        try{
            makeConnection();
            
            String selectStatement = "select newid() as uploadId";
            PreparedStatement prepSelect = con.prepareStatement(selectStatement);
            ResultSet rs = prepSelect.executeQuery();
            rs.next();
            uploadId = rs.getString(1);
            rs.close();
            prepSelect.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in getNextUploadId :" + sql.getMessage());
        }
        
        try {
            makeConnection();
            
            String insertStatement = "insert into " + DBHelper.USEA_COMP_RESULT_FILE_DETAILS + " (event_id, event_name,result_file_path,upload_id,event_type_id)"+
                    " values ( ? , ? , ? , ? , ? ) ";
            
            prepStmt = con.prepareStatement(insertStatement);
            prepStmt.setString(1, event_id);
            prepStmt.setString(2, event_name);
            prepStmt.setString(3, result_file_path);
            prepStmt.setString(4, uploadId);
            prepStmt.setString(5, eventTypeId);
            
            int cnt = prepStmt.executeUpdate();
            if(cnt!=0) result = true;
            prepStmt.close();
            releaseConnection();
            Debug.print("Successfully Inserted insertCompResultUploadDetails() row count :" + cnt);
        }catch(Exception e){
            releaseConnection();
            Debug.print("Error while insertCompResultUploadDetails in DAO : "+e.getMessage());
        }finally {
            releaseConnection();
        }
        return result;
    }
    
    public String getNextIdforPayment() throws SQLException {
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
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in getNextIdforPayment():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in getNextIdforPayment():" + e.getMessage());
        }
        return nextId;
    }
    
    public String insertRowPayment(HLCPaymentDetailVO paymentVO) throws SQLException {
        Debug.print("PaymentBean insertRowPayment");
        
        boolean paymentResult = false;
        String oePaymentId = "";
        makeConnection();
        try{
            String insertStatement = "insert into " + DBHelper.OE_PAYMENT + " (user_id,cc_name,cc_type, cc_number, cc_exp_month ," +
                    " cc_exp_year, cc_cvvid, bank_name, check_date, check_number, check_name, amount, payment_status, " +
                    " ssl_result, ssl_result_message, ssl_txn_id, ssl_approval_code, ssl_cvv2_response, ssl_avs_response, " +
                    " ssl_transaction_type, ssl_invoice_no, ssl_email,check_amount,oe_payment_id, pending_amount, parent_payment_id, ip_address)" +
                    " values ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? ,?, ?, ?, ?, ?, ?, ?, ?, ? , ?, ?, ?, ? , ? )";
            
            PreparedStatement prepStmt = con.prepareStatement(insertStatement);
            
            prepStmt.setString(1, paymentVO.getUserId());
            prepStmt.setString(2, paymentVO.getCcName());
            prepStmt.setString(3, paymentVO.getCcType());
            prepStmt.setString(4, paymentVO.getCcNumber());
            prepStmt.setInt(5, paymentVO.getCcExpMonth() );
            prepStmt.setInt(6, paymentVO.getCcExpYear());
            prepStmt.setInt(7, paymentVO.getCcCvvid());
            prepStmt.setString(8, paymentVO.getBankName());
            if(paymentVO.getCheckDate()!=null) prepStmt.setDate(9, DBHelper.toSQLDate(paymentVO.getCheckDate()));
            else prepStmt.setDate(9, null);
            prepStmt.setString(10, paymentVO.getCheckNumber());
            prepStmt.setString(11, paymentVO.getCheckName());
            prepStmt.setDouble(12, paymentVO.getAmount());
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
            prepStmt.setFloat(23, paymentVO.getCheckAmount());
            prepStmt.setString(24, paymentVO.getPaymentId());
            prepStmt.setFloat(25, paymentVO.getPendingAmount());
            prepStmt.setString(26, paymentVO.getParentPaymentId());
            prepStmt.setString(27, paymentVO.getIpAddress());
            
            int cnt = prepStmt.executeUpdate();
            
            if(cnt!=0){
                paymentResult = true;
                oePaymentId = paymentVO.getPaymentId();
            } else paymentResult = false;
            
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
        return oePaymentId;
    }
    
    public boolean insertEventRegistrationDetails(HLCCompRegistrationVO compVO, String oePaymentId) throws SQLException {
        Debug.print("PaymentBean insertEventRegistrationDetails");
        
        boolean result = false;
        makeConnection();
        try{
            String insertStatement = "insert into " + DBHelper.OE_REGISTRATION_DETAILS + "(event_id, organizer_id, competition_year, " +
                    "event_type, event_level, horse_name, horse_member_id, rider_first_name, rider_last_name, rider_user_id, " +
                    "owner_first_name, owner_last_name, rider_member_id, qualification_file_path, oe_payment_id, event_division) values " +
                    "( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?, ?, ?, ?)";
            
            PreparedStatement prepStmt = con.prepareStatement(insertStatement);
            
            prepStmt.setString(1, compVO.getEventId());
            prepStmt.setString(2, compVO.getOrganizerId());
            prepStmt.setInt(3, compVO.getCompetitionYear());
            prepStmt.setString(4, compVO.getEventType());
            prepStmt.setString(5, compVO.getEventLevel());
            prepStmt.setString(6, compVO.getHorseName());
            prepStmt.setString(7, compVO.getHorseMemberId());
            prepStmt.setString(8, compVO.getRiderFirstName());
            prepStmt.setString(9, compVO.getRiderLastName());
            prepStmt.setString(10, compVO.getRiderUserId());
            prepStmt.setString(11, compVO.getOwnerFirstName());
            prepStmt.setString(12, compVO.getOwnerLastName());
            prepStmt.setString(13, compVO.getRiderMemberId());
            prepStmt.setString(14, compVO.getQualFilePath());
            prepStmt.setString(15, oePaymentId);
            prepStmt.setString(16, compVO.getEventDivision());
            
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
    
    public ArrayList getHorseDetailsByHorseId(String horseMemberId) throws SQLException {
        Debug.print("EventEntryDAO getHorseDetailsByHorseId():"+horseMemberId);
        ArrayList horseDetails = new ArrayList();
        
        makeConnection();
        try{
            String selectStatement = "select A.competition_name, A.registered_name, A.rider_member_id, A.owner_id, B.first_name, "+
                    "B.last_name from "+DBHelper.OE_HORSE_MEMBER_DETAILS+" A, "+DBHelper.OE_USER_MASTER+" B, "+DBHelper.OE_MEMBER_DETAILS+" C where "+
                    "horse_member_id = ? and A.owner_id = B.user_id and A.rider_member_id = C.member_id";
            
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            
            prepStmt.setString(1, horseMemberId);
            
            ResultSet rs = prepStmt.executeQuery();
            
            while (rs.next()){
                String horseFirstName = rs.getString(1);
                String horseLastName = rs.getString(2);
                String riderId = rs.getString(3);
                String ownerId = rs.getString(4);
                String ownerFirstName = rs.getString(5);
                String ownerLastName = rs.getString(6);
                
                String [] horseDet = {horseFirstName, horseLastName, riderId, ownerId, ownerFirstName, ownerLastName};
                horseDetails.add(horseDet);
            }
            
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
        return horseDetails;
    }
    
    public ArrayList getEventFixedAmount(String evTypeId, String evLevelId, String chStatus) throws SQLException {
        ArrayList fixedPriceDetails = new ArrayList();
        
        makeConnection();
        try{
            String selectStatement = "select sum(due_date_price), sum(aft_due_date_price), sum(deposit_price) from " +
                    DBHelper.OE_FIXED_PRICE+" where event_level_id = ? and event_type_id = ? and championship_status = ?";
            
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            
            prepStmt.setString(1, evLevelId);
            prepStmt.setString(2, evTypeId);
            prepStmt.setString(3,chStatus);
            
            ResultSet rs = prepStmt.executeQuery();
            
            while (rs.next()){
                int dueDatePrice = rs.getInt(1);
                int afterDueDate = rs.getInt(2);
                int depositPrice = rs.getInt(3);
                
                int [] prDet = {dueDatePrice,afterDueDate,depositPrice};
                fixedPriceDetails.add(prDet);
            }
            
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
        return fixedPriceDetails;
    }
    
    public ArrayList getOrganizerPriceDetails(String eventId, String chStatus, String eventLevelId, String eventTypeId) throws SQLException {
        ArrayList orgPriceDetails = new ArrayList();
        
        makeConnection();
        try{
            String selectStatement = "select sum(due_date_price), sum(aft_due_date_price), sum(deposit_price) " +
                    "from "+DBHelper.OE_PRICE_MATRIX+" where event_id = ? and approve_status = ? and championship_status = ? " +
                    "and item_type = ? and event_level_id = ? and event_type_id = ?";
            
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            
            prepStmt.setString(1, eventId);
            prepStmt.setString(2, "Approved");
            prepStmt.setString(3, chStatus);
            prepStmt.setString(4, "fixed");
            prepStmt.setString(5, eventLevelId);
            prepStmt.setString(6, eventTypeId);
            
            ResultSet rs = prepStmt.executeQuery();
            
            while (rs.next()){
                int dueDatePrice = rs.getInt(1);
                int afterDueDate = rs.getInt(2);
                int depositPrice = rs.getInt(3);
                
                int [] prDet = {dueDatePrice,afterDueDate,depositPrice};
                orgPriceDetails.add(prDet);
            }
            
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
        return orgPriceDetails;
    }
    
    public ArrayList getMyCompRegList(int year, String riderUserId) throws SQLException {
        ArrayList ownList = new ArrayList();
        Debug.print("Inside DAO");
        try {
            makeConnection();
            
            String selStatement = "select registration_id, event_id, organizer_id, competition_year, event_type, event_level,"+
                    " event_division, horse_member_id, rider_member_id, rider_user_id, qualify_status from "+DBHelper.OE_COMP_REG_DETAILS+" where"+
                    " competition_year = ? and rider_user_id = ?";
            
            
            Debug.print("Query: "+selStatement);
            PreparedStatement prepStmt = con.prepareStatement(selStatement);
            prepStmt.setInt(1,year);
            prepStmt.setString(2,riderUserId);
            
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                HLCCompRegistrationVO compVO = new HLCCompRegistrationVO();
                compVO.setRegistrationId(rs.getString(1));
                compVO.setEventId(rs.getString(2));
                compVO.setOrganizerId(rs.getString(3));
                compVO.setCompetitionYear(rs.getInt(4));
                compVO.setEventType(rs.getString(5));
                compVO.setEventLevel(rs.getString(6));
                compVO.setEventDivision(rs.getString(7));
                compVO.setHorseMemberId(rs.getString(8));
                compVO.setRiderMemberId(rs.getString(9));
                compVO.setRiderUserId(rs.getString(10));
                compVO.setQualifystatus(rs.getString(11));
                ownList.add(compVO);
            }
            Debug.print("ArrayList Size: "+ownList.size());
            prepStmt.close();
            releaseConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return ownList;
    }
    
    public HLCCompRegistrationVO getMySingleCompRegDetails(String registrationId) throws SQLException {
        HLCCompRegistrationVO regVO = new HLCCompRegistrationVO();
        Debug.print("Inside DAO");
        try {
            makeConnection();
            
            String selStatement = "Select A.registration_id, A.tmp_registration_id, A.event_id, A.organizer_id, A.competition_year," +
                    " A.event_type, A.event_level, A.event_division, A.event_sub_division, A.event_division_amateur, " +
                    " A.event_division_age, A.event_division_experience, A.horse_name, A.horse_member_id, B.event_title, " +
                    " A.horse_usef_id , A.rider_first_name , A.rider_last_name, A.rider_member_id, A.rider_user_id, A.rider_usef_id," +
                    " A.owner_first_name , A.owner_last_name , A.owner_usea_id , A.owner_usef_id," +
                    " A.comp_result_id, A.qualification_file_path, A.qualify_status, A.oe_payment_id, D.first_name, D.last_name" +
                    " from tblMeeCompRegistrationDetails A, tblOEProvisionalCalendar B, tblMemberDetails C, tblUserMaster D  " +
                    " where A.event_id=B.event_id and A.organizer_id=C.member_id and C.user_id=D.user_id and A.registration_id = ?";
            
            PreparedStatement prepStmt = con.prepareStatement(selStatement);
            prepStmt.setString(1,registrationId);
            
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                regVO.setRegistrationId(rs.getString(1));
                regVO.setTmpRegistrationId(rs.getString(2));
                regVO.setEventId(rs.getString(3));
                regVO.setOrganizerId(rs.getString(4));
                regVO.setCompetitionYear(rs.getInt(5));
                regVO.setEventType(rs.getString(6));
                regVO.setEventLevel(rs.getString(7));
                regVO.setEventDivision(rs.getString(8));
                regVO.setEventSubDivision(rs.getString(9));
                regVO.setEventDivAmateur(rs.getString(10));
                regVO.setEventDivAge(rs.getString(11));
                regVO.setEventDivExp(rs.getString(12));
                regVO.setHorseName(rs.getString(13));
                regVO.setHorseMemberId(rs.getString(14));
                regVO.setEventTitle(rs.getString(15));
                regVO.setHorseUSEFId(rs.getString(16));
                regVO.setRiderFirstName(rs.getString(17));
                regVO.setRiderLastName(rs.getString(18));
                regVO.setRiderMemberId(rs.getString(19));
                regVO.setRiderUserId(rs.getString(20));
                regVO.setRiderUSEFId(rs.getString(21));
                regVO.setOwnerFirstName(rs.getString(22));
                regVO.setOwnerLastName(rs.getString(23));
                regVO.setOwnerUSEAId(rs.getString(24));
                regVO.setOwnerUSEFId(rs.getString(25));
                regVO.setCompResultId(rs.getString(26));
                regVO.setQualFilePath(rs.getString(27));
                regVO.setQualifystatus(rs.getString(28));
                regVO.setPaymemtId(rs.getString(29));
                regVO.setOrgFirstName(rs.getString(30));
                regVO.setOrgLastName(rs.getString(31));
            }
            prepStmt.close();
            releaseConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return regVO;
    }
    
    public boolean isFixedPriceMatrixExist(String itemId, String eventTypeId, String eventLevelId, String areaId) throws SQLException {
        Debug.print("EventEntryDAO.isFixedPriceMatrixExist():");
        boolean result = false;
        try {
            makeConnection();
            
            String selectStatement = "SELECT oe_fix_price_id FROM " + DBHelper.OE_FIXED_PRICE + " WHERE entry_item_id='"+itemId+
                    "' and event_level_id='"+eventLevelId+"' ";
            
            if(eventTypeId!=null && eventTypeId.trim().length()!=0){
                selectStatement = selectStatement + " and event_type_id = '" + eventTypeId + "' " ;
            }
            if(areaId!=null && areaId.trim().length()!=0){
                selectStatement = selectStatement + " and area_id = '" + areaId + "' " ;
            }
            
            prepStmt = con.prepareStatement(selectStatement);
            
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                result = true;
            }
            rs.close();
            Debug.print("EventCalendarDAO isFixedPriceMatrixExist() result: " + result);
            prepStmt.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in EventCalendarDAO.isFixedPriceMatrixExist():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in EventCalendarDAO.isFixedPriceMatrixExist():" + e.getMessage());
        }
        return result;
    }
    
    public ArrayList getEventLevelDetailsWithChStatus(String eventId, String compYear) throws SQLException {
        Debug.print("EventEntryrDAO selectEventDetails()");
        ArrayList eventTypeDetails = new ArrayList();
        makeConnection();
        try{
            
            String selectStatement = "select A.event_type_id, A.event_level_id, B.event_level_code, C.event_type_name, " +
                    "A.championship_status from "+DBHelper.OE_CHAMP_DETAILS+" A, "+DBHelper.OE_EVENT_LEVEL_MASTER+" B, " +
                    DBHelper.OE_EVENT_TYPE_MASTER+" C, "+DBHelper.OE_PROVISIONAL_CALENDAR+" D where A.event_id = ? " +
                    "and A.event_type_id = C.event_type_id and A.event_level_id = B.event_level_id and " +
                    "A.pro_calendar_id = D.pro_calendar_id and D.competition_year = ?";
            
            Debug.print("Query :"+selectStatement);
            PreparedStatement prepSelect = con.prepareStatement(selectStatement);
            prepSelect.setString(1, eventId);
            prepSelect.setString(2, compYear);
            ResultSet rs = prepSelect.executeQuery();
            
            while (rs.next()){
                String eventTypeId = rs.getString(1);
                String eventLevelId = rs.getString(2);
                String eventLevelCode = rs.getString(3);
                String eventTypeName = rs.getString(4);
                String champStatus = rs.getString(5);
                
                String [] typeDet = {eventTypeId, eventLevelId, eventLevelCode, eventTypeName, champStatus};
                eventTypeDetails.add(typeDet);
            }
            rs.close();
            prepSelect.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in EventEntryrDAO.selectEventDetailsBasedOnEventId():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in EventEntryrDAO.selectEventDetailsBasedOnEventId():" + e.getMessage());
        }
        return eventTypeDetails;
    }
    
    public HLCCalendarVO getEventFullDetails(String eventId, String compYear) throws SQLException {
        Debug.print("EventEntryrDAO selectEventDetails()");
        HLCCalendarVO calVO = new HLCCalendarVO();
        makeConnection();
        try{
            
            String selectStatement = "select event_id, organizer_id, competition_year, event_type_id, event_levels, " +
                    "oe_begin_date, oe_end_date, oe_extended_due_date from "+DBHelper.OE_PROVISIONAL_CALENDAR+" where event_id = ? " +
                    "and competition_year = ?";
            
            Debug.print("Query :"+selectStatement);
            PreparedStatement prepSelect = con.prepareStatement(selectStatement);
            prepSelect.setString(1,eventId);
            prepSelect.setString(2,compYear);
            ResultSet rs = prepSelect.executeQuery();
            
            while (rs.next()){
                calVO.setEventId(rs.getString(1));
                calVO.setOrganizerId(rs.getString(2));
                calVO.setCompYear(rs.getInt(3));
                calVO.setEventTypeId(rs.getString(4));
                calVO.setEventLevel(rs.getString(5));
                calVO.setBeginDate(rs.getDate(6));
                calVO.setEndDate(rs.getDate(7));
                calVO.setExtDueDate(rs.getDate(8));
                Debug.print(calVO.toString());
            }
            rs.close();
            prepSelect.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in EventEntryrDAO.selectEventDetailsBasedOnEventId():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in EventEntryrDAO.selectEventDetailsBasedOnEventId():" + e.getMessage());
        }
        return calVO;
    }
    
    public String getEventLevelName(String eventLevelId) throws SQLException {
        Debug.print("EventEntryrDAO selectEventDetails()");
        String evlevelName = "";
        makeConnection();
        try{
            
            String selectStatement = "select event_level_code from "+DBHelper.OE_EVENT_LEVEL_MASTER+" where event_level_id = ?";
            
            Debug.print("Query :"+selectStatement);
            PreparedStatement prepSelect = con.prepareStatement(selectStatement);
            prepSelect.setString(1,eventLevelId);
            ResultSet rs = prepSelect.executeQuery();
            
            while (rs.next()){
                evlevelName = rs.getString(1);
            }
            rs.close();
            prepSelect.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in EventEntryrDAO.selectEventDetailsBasedOnEventId():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in EventEntryrDAO.selectEventDetailsBasedOnEventId():" + e.getMessage());
        }
        return evlevelName;
    }
    
    public String getEventTypeName(String eventTypeId) throws SQLException {
        Debug.print("EventEntryrDAO selectEventDetails()");
        String evTypeName = "";
        makeConnection();
        try{
            
            String selectStatement = "select event_type_name from "+DBHelper.OE_EVENT_TYPE_MASTER+" where event_type_id = ?";
            
            Debug.print("Query :"+selectStatement);
            PreparedStatement prepSelect = con.prepareStatement(selectStatement);
            prepSelect.setString(1,eventTypeId);
            ResultSet rs = prepSelect.executeQuery();
            
            while (rs.next()){
                evTypeName = rs.getString(1);
            }
            rs.close();
            prepSelect.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in EventEntryrDAO.selectEventDetailsBasedOnEventId():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in EventEntryrDAO.selectEventDetailsBasedOnEventId():" + e.getMessage());
        }
        return evTypeName;
    }
    
    
    public ArrayList selectEventTypes(String eventId) throws SQLException {
        Debug.print("EventEntryrDAO selectEventTypes()");
        ArrayList eventTypeDetails = new ArrayList();
        makeConnection();
        try{
            
            String selectStatement = "select DISTINCT A.event_type_id, D.event_type_name from "+ DBHelper.OE_CHAMP_DETAILS+" A, "+DBHelper.OE_EVENT_TYPE_MASTER+" D "+
                    "where A.event_type_id = D.event_type_id and A.event_id= ? ";
            
            Debug.print("Query :"+selectStatement);
            PreparedStatement prepSelect = con.prepareStatement(selectStatement);
            prepSelect.setString(1,eventId);
            ResultSet rs = prepSelect.executeQuery();
            
            while (rs.next()){
                String eventTypeId = rs.getString(1);
                String eventTypeName = rs.getString(2);
                
                String [] typeDet = {eventTypeId, eventTypeName};
                eventTypeDetails.add(typeDet);
            }
            rs.close();
            prepSelect.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in EventEntryrDAO.selectEventTypes():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in EventEntryrDAO.selectEventTypes():" + e.getMessage());
        }
        return eventTypeDetails;
    }
    
    public ArrayList getOrgCompResLabelList(String eventTypeId) throws SQLException {
        ArrayList organizerList = new ArrayList();
        Debug.print("Inside DAO");
        try {
            makeConnection();
            
            String selStatement = "select A.comp_result_field_id, B.comp_result_field_name from "+DBHelper.OE_COMP_RES_FIELDS+" A, "+DBHelper.OE_COMP_RES_FIELDS_MASTER+" B "+
                    "where A.comp_result_field_id=B.comp_result_field_id and A.event_type_id=?";
            Debug.print("Query :"+selStatement);
            
            PreparedStatement prepStmt = con.prepareStatement(selStatement);
            prepStmt.setString(1,eventTypeId);
            
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                String compResultFileldId = rs.getString(1);
                String compResultFileldName = rs.getString(2);
                
                String [] typeDet = {compResultFileldId, compResultFileldName};
                organizerList.add(typeDet);
            }
            Debug.print("ArrayList Size: "+organizerList.size());
            prepStmt.close();
            releaseConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return organizerList;
    }
    
    public ArrayList getOrgCompResList(int year, String eventId, String eventTypeId, String
            organizerId) throws SQLException {
        ArrayList organizerList = new ArrayList();
        Debug.print("Inside DAO");
        try {
            makeConnection();
            
            String selStatement = "select registration_id, event_id, event_type, event_level, event_division, horse_name, " +
                    "horse_member_id, rider_first_name, rider_last_name, rider_member_id, rider_user_id from "+DBHelper.OE_REGISTRATION_DETAILS+
                    " where competition_year=? and event_id=? and event_type=? and organizer_id=? ";
            
            Debug.print("Query: "+selStatement);
            PreparedStatement prepStmt = con.prepareStatement(selStatement);
            prepStmt.setInt(1,year);
            prepStmt.setString(2,eventId);
            prepStmt.setString(3,eventTypeId);
            prepStmt.setString(4,organizerId);
            
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                HLCCompRegistrationVO compVO = new HLCCompRegistrationVO();
                compVO.setRegistrationId(rs.getString(1));
                compVO.setEventId(rs.getString(2));
                compVO.setEventType(rs.getString(3));
                compVO.setEventLevel(rs.getString(4));
                compVO.setEventDivision(rs.getString(5));
                compVO.setHorseName(rs.getString(6));
                compVO.setHorseMemberId(rs.getString(7));
                compVO.setRiderFirstName(rs.getString(8));
                compVO.setRiderLastName(rs.getString(9));
                compVO.setRiderMemberId(rs.getString(10));
                compVO.setRiderUserId(rs.getString(11));
                organizerList.add(compVO);
            }
            Debug.print("ArrayList Size: "+organizerList.size());
            prepStmt.close();
            releaseConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return organizerList;
    }
    
    public boolean insertCompResultDetails(HLCCompetitionResultVO compResVO) throws SQLException {
        Debug.print("EventEntryDAO insertCompResultDetails");
        
        boolean result = false;
        makeConnection();
        try{
            String insertStatement = "insert into " + DBHelper.OE_MEE_COMP_RESULT_DETAILS +" (event_id, event_type_id, event_division_id, event_sub_division, horse_name, "+
                    "horse_member_id, rider_first_name, rider_last_name,  rider_member_id, pinney_number, dressage_penalties, dressage_place, "+
                    "xc_phaseD_jump_penalties, xc_phaseD_elapsed_time,  xc_phaseD_time_penalties, show_jump_time_penalties, "+
                    "show_jump_jump_penalties, to_date_points, to_date_place, dangerous_riding_penalties, final_points, final_place, "+
                    "first_inspection, last_inspection, phase_D_inspection, road_and_track_A, road_and_track_C, steeplechase_jump_penalties, "+
                    "steeplechase_time_penalties, usea_points, exception_status, event_level_id, competition_year, rider_user_id) values ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? "+
                    ", ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?,  ?, ?, ?, ? )";
            
            PreparedStatement prepStmt = con.prepareStatement(insertStatement);
            
            prepStmt.setString(1, compResVO.getEventId());
            prepStmt.setString(2, compResVO.getEventTypeId());
            prepStmt.setString(3, compResVO.getEventDivId());
            prepStmt.setString(4, compResVO.getEventSubDivision());
            prepStmt.setString(5, compResVO.getHorseName());
            prepStmt.setString(6, compResVO.getHorseMemberId());
            prepStmt.setString(7, compResVO.getRiderFirstName());
            prepStmt.setString(8, compResVO.getRiderLastName());
            prepStmt.setString(9, compResVO.getRiderMemberId());
            prepStmt.setString(10, compResVO.getPinneyNumber());
            prepStmt.setString(11, compResVO.getDressagePenal());
            prepStmt.setString(12, compResVO.getDressagePlace());
            prepStmt.setString(13, compResVO.getXc_phase_jmpPenal());
            prepStmt.setString(14, compResVO.getXc_phase_elapsTime());
            prepStmt.setString(15, compResVO.getXc_phase_timePenal());
            prepStmt.setString(16, compResVO.getShow_jmp_timePenal());
            prepStmt.setString(17, compResVO.getShow_jmp_jmpPenal());
            prepStmt.setString(18, compResVO.getToDatePts());
            prepStmt.setString(19, compResVO.getToDatePlace());
            prepStmt.setString(20, compResVO.getDangerRidPenal());
            prepStmt.setString(21, compResVO.getFinalPoints());
            prepStmt.setString(22, compResVO.getFinalPlace());
            prepStmt.setString(23, compResVO.getFirstInspec());
            prepStmt.setString(24, compResVO.getLastInspec());
            prepStmt.setString(25, compResVO.getPhase_DInspec());
            prepStmt.setString(26, compResVO.getRdTrackA());
            prepStmt.setString(27, compResVO.getRdTrackC());
            prepStmt.setString(28, compResVO.getSteepleCh_jmpPenal());
            prepStmt.setString(29, compResVO.getSteepleCh_timePenal());
            prepStmt.setString(30, compResVO.getUseaPoints());
            prepStmt.setBoolean(31, compResVO.isExpectationStatus());
            prepStmt.setString(32, compResVO.getEventLevelId());
            prepStmt.setInt(33, compResVO.getCompYear());
            prepStmt.setString(34, compResVO.getRiderUserId());
            int cnt = prepStmt.executeUpdate();
            
            if(cnt!=0){
                result = true;
            } else result = false;
            
            prepStmt.close();
            releaseConnection();
            Debug.print("EventEntryDAO Sucessfully insertCompResultDetails");
            
        } catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in insertCompResultDetails:" +
                    sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in insertCompResultDetails:" +
                    e.getMessage());
        }
        return result;
    }
    public String getEventLevelId(String eventLevelName) throws SQLException {
        Debug.print("EventEntryrDAO getEventLevelId()");
        String evlevelName = "";
        makeConnection();
        try{
            
            String selectStatement = "select event_level_id from "+DBHelper.OE_EVENT_LEVEL_MASTER+" where event_level_code = ?";
            
            Debug.print("Query :"+selectStatement);
            PreparedStatement prepSelect = con.prepareStatement(selectStatement);
            prepSelect.setString(1,eventLevelName);
            ResultSet rs = prepSelect.executeQuery();
            
            while (rs.next()){
                evlevelName = rs.getString(1);
            }
            rs.close();
            prepSelect.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in EventEntryrDAO.getEventLevelId():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in EventEntryrDAO.getEventLevelId():" + e.getMessage());
        }
        return evlevelName;
    }
    
    public String getEventDivId(String divisionName) throws SQLException {
        Debug.print("EventEntryrDAO getEventDivId()");
        String evlevelName = "";
        makeConnection();
        try{
            
            String selectStatement = "select event_division_id from "+DBHelper.OE_EVENT_DIVISION_MASTER+" where event_division_name = ?";
            
            Debug.print("Query :"+selectStatement);
            PreparedStatement prepSelect = con.prepareStatement(selectStatement);
            prepSelect.setString(1,divisionName);
            ResultSet rs = prepSelect.executeQuery();
            
            while (rs.next()){
                evlevelName = rs.getString(1);
            }
            rs.close();
            prepSelect.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in EventEntryrDAO.getEventDivId():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in EventEntryrDAO.getEventDivId():" + e.getMessage());
        }
        return evlevelName;
    }
    
    public boolean isCompResultExist(String eventId, String eventTypeId, String eventLevelId) throws SQLException {
        Debug.print("EventEntryDAO.isCompResultExist():");
        boolean result = false;
        try {
            makeConnection();
            
            String selectStatement = "SELECT comp_result_id FROM " + DBHelper.OE_MEE_COMP_RESULT_DETAILS + " " +
                    " WHERE event_id=? and event_type_id= ? and event_level_id=? ";
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,eventId);
            prepStmt.setString(2,eventTypeId);
            prepStmt.setString(3,eventLevelId);
            
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                result = true;
            }
            rs.close();
            Debug.print("EventCalendarDAO isCompResultExist() result: " + result);
            prepStmt.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in EventCalendarDAO.isCompResultExist():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in EventCalendarDAO.isCompResultExist():" + e.getMessage());
        }
        return result;
    }
    
    public boolean checkHorseAvailability(String eventId, String horseMemberId, String compYear) throws SQLException {
        Debug.print("EventEntryDAO.isCompResultExist():");
        boolean result = false;
        try {
            makeConnection();
            
            String selectStatement = "SELECT horse_member_id from " +DBHelper.OE_COMP_REG_DETAILS+" WHERE event_id=? " +
                    "and horse_member_id = ? and competition_year = ?";
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,eventId);
            prepStmt.setString(2,horseMemberId);
            prepStmt.setString(3,compYear);
            
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                result = true;
            }
            rs.close();
            Debug.print("EventCalendarDAO isCompResultExist() result: " + result);
            prepStmt.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in EventCalendarDAO.isCompResultExist():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in EventCalendarDAO.isCompResultExist():" + e.getMessage());
        }
        return result;
    }
    
    public ArrayList getMyEventTiles(int year,String riderUserId) throws RemoteException{
        Debug.print("Inside DAO getMyEventTiles");
        ArrayList eventList = new ArrayList();
        String selStmt1=null;
        String eventTypeName=null;
        try {
            makeConnection();
            
            selStmt1 ="select Distinct A.event_id, B.event_title, B.event_type_id from "+DBHelper.OE_REGISTRATION_DETAILS+" A, "+DBHelper.OE_PROVISIONAL_CALENDAR+" B "+
                    " where A.event_id = B.event_id and A.competition_year=? and A.rider_user_id=? ";
            
            Debug.print("Query: "+selStmt1);
            prepStmt = con.prepareStatement(selStmt1);
            prepStmt.setInt(1,year);
            prepStmt.setString(2,riderUserId);
            rs = prepStmt.executeQuery();
            while(rs.next()) {
                String eventId = rs.getString(1);
                String eventTitle = rs.getString(2);
                eventTypeName = rs.getString(2);
                
                HLCCompResultVO calVO = new HLCCompResultVO();
                calVO.setEventId(eventId);
                calVO.setEventTitle(eventTitle);
                calVO.setEventTypeName(eventTypeName);
                eventList.add(calVO);
            }
            Debug.print("EventEntryDAO.getMyEventTiles"+eventList.size());
            rs.close();
            prepStmt.close();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in EventEntryDAO.getMyEventTiles():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in EventEntryDAO.getMyEventTiles():" + e.getMessage());
        } finally {
            releaseConnection();
        }
        return eventList;
    }
    
    public String getEventTypeId(String eventTypeName) throws SQLException {
        Debug.print("EventEntryrDAO getEventTypeId()");
        Debug.print("EventEntryrDAO getEventTypeId()"+eventTypeName);
        String eveTypeId = "";
        makeConnection();
        try{
            String selectStatement = "select event_type_id from "+DBHelper.OE_EVENT_TYPE_MASTER+" where event_type_name = ?";
            Debug.print("Query :"+selectStatement);
            
            PreparedStatement prepSelect = con.prepareStatement(selectStatement);
            prepSelect.setString(1,eventTypeName);
            ResultSet rs = prepSelect.executeQuery();
            
            while (rs.next()){
                eveTypeId = rs.getString(1);
            }
            Debug.print("EventEntryrDAO getEventTypeId()"+eveTypeId);
            rs.close();
            prepSelect.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in EventEntryrDAO.getEventTypeId():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in EventEntryrDAO.getEventTypeId():" + e.getMessage());
        }
        return eveTypeId;
    }
    
    public ArrayList getMyCompResultList(String riderUserId, String eventId, int year) throws RemoteException{
        Debug.print("Inside DAO getMyCompResultList");
        ArrayList eventList = new ArrayList();
        String selStmt1=null;
        String eventTypeName=null;
        try {
            makeConnection();
            
            selStmt1 ="select A.comp_result_id, A.event_id, A.rider_member_Id, A.rider_first_name, A.rider_last_name, " +
                    " A.horse_member_id, A.horse_name, B.event_title, C.event_type_name from tblMeeCompResultDetails A, " +
                    " tblOEProvisionalCalendar B, tblMeeEventTypeMaster C where A.event_id=B.event_id and " +
                    " A.event_type_id= C.event_type_id and A.competition_year=B.competition_year " +
                    " and A.rider_user_id = ? and A.event_id= ? and A.competition_year= ?";
            
            Debug.print("Query: "+selStmt1);
            prepStmt = con.prepareStatement(selStmt1);
            prepStmt.setString(1,riderUserId);
            prepStmt.setString(2,eventId);
            prepStmt.setInt(3,year);
            rs = prepStmt.executeQuery();
            while(rs.next()) {
                String compResId = rs.getString(1);
                eventId = rs.getString(2);
                String riderMemId = rs.getString(3);
                String riderFName = rs.getString(4);
                String riderLName = rs.getString(5);
                String horseMemId = rs.getString(6);
                String horseName = rs.getString(7);
                String eveTitle = rs.getString(8);
                String eveTypeName = rs.getString(9);
                
                HLCCompResultVO calVO = new HLCCompResultVO();
                calVO.setCompResultId(compResId);
                calVO.setEventId(eventId);
                calVO.setRiderMemberId(riderMemId);
                calVO.setRiderFirstName(riderFName);
                calVO.setRiderLastName(riderLName);
                calVO.setHorseMemberId(horseMemId);
                calVO.setHorseName(horseName);
                calVO.setEventTitle(eveTitle);
                calVO.setEventTypeName(eveTypeName);
                eventList.add(calVO);
            }
            Debug.print("EventEntryDAO.getMyCompResultList"+eventList.size());
            rs.close();
            prepStmt.close();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in EventEntryDAO.getMyCompResultList():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in EventEntryDAO.getMyCompResultList():" + e.getMessage());
        } finally {
            releaseConnection();
        }
        return eventList;
    }
    
    public HLCCompResultVO getMyCompResultView(String compResultId) throws SQLException {
        HLCCompResultVO resVO = new HLCCompResultVO();
        Debug.print("Inside DAO getMyCompResultView ");
        Debug.print("compResultId Inside DAO getMyCompResultView "+ compResultId);
        try {
            makeConnection();
            
            String selStatement = "Select A.comp_result_id, A.tmp_result_id, A.event_id, A.event_type_id, A.event_level_id," +
                    " C.event_level_code, A.event_division_id, D.event_division_name, A.event_sub_division, A.horse_name, A.horse_member_id, B.event_title, " +
                    " A.rider_user_id, A.rider_first_name , A.rider_last_name, A.rider_member_id, A.pinney_number," +
                    " A.dressage_penalties , A.dressage_place , A.xc_phaseD_jump_penalties , A.xc_phaseD_elapsed_time," +
                    " A.xc_phaseD_time_penalties, A.show_jump_time_penalties, A.show_jump_jump_penalties, A.to_date_points," +
                    " A.to_date_place, A.dangerous_riding_penalties, A.final_points, A.final_place, A.first_inspection," +
                    " A.last_inspection, A.phase_D_inspection, A.road_and_track_A, A.road_and_track_C, " +
                    " A.steeplechase_jump_penalties, A.steeplechase_time_penalties, A.usea_points, A.exception_status, " +
                    " F.event_type_name from tblMeeCompResultDetails A, tblOEProvisionalCalendar B, tblMeeEventLevelMaster C, " +
                    " tblMeeEventDivisionMaster D, tblMeeEventTypeMaster F " +
                    " where A.event_id = B.event_id and A.event_level_id = C.event_level_id and " +
                    " A.event_division_id = D.event_division_id and A.event_type_id= F.event_type_id " +
                    " and A.comp_result_id = ? ";
            
            PreparedStatement prepStmt = con.prepareStatement(selStatement);
            
            Debug.print("prepStmt Inside DAO getMyCompResultView "+ selStatement);
            prepStmt.setString(1,compResultId);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                Debug.print("setCompResultId"+rs.getString(1));
                Debug.print("setEventId"+rs.getString(3));
                resVO.setCompResultId(rs.getString(1));
                resVO.setTempResultId(rs.getString(2));
                resVO.setEventId(rs.getString(3));
                resVO.setEventTypeId(rs.getString(4));
                resVO.setEveLevelId(rs.getString(5));
                resVO.setEventLevel(rs.getString(6));
                resVO.setDivisionId(rs.getString(7));
                resVO.setDivisionName(rs.getString(8));
                resVO.setEventSubDivision(rs.getString(9));
                resVO.setHorseName(rs.getString(10));
                resVO.setHorseMemberId(rs.getString(11));
                resVO.setEventTitle(rs.getString(12));
                resVO.setRiderUserId(rs.getString(13));
                resVO.setRiderFirstName(rs.getString(14));
                resVO.setRiderLastName(rs.getString(15));
                resVO.setRiderMemberId(rs.getString(16));
                resVO.setPinneyNumber(rs.getString(17));
                resVO.setDressagePenal(rs.getString(18));
                resVO.setDressagePlace(rs.getString(19));
                resVO.setXc_phase_jmpPenal(rs.getString(20));
                resVO.setXc_phase_elapsTime(rs.getString(21));
                resVO.setXc_phase_timePenal(rs.getString(22));
                resVO.setShow_jmp_timePenal(rs.getString(23));
                resVO.setShow_jmp_jmpPenal(rs.getString(24));
                resVO.setToDatePts(rs.getString(25));
                resVO.setToDatePlace(rs.getString(26));
                resVO.setDangerRidPenal(rs.getString(27));
                resVO.setFinalPoints(rs.getString(28));
                resVO.setFinalPlace(rs.getString(29));
                resVO.setFirstInspec(rs.getString(30));
                resVO.setLastInspec(rs.getString(31));
                resVO.setPhase_DInspec(rs.getString(32));
                resVO.setRdTrackA(rs.getString(33));
                resVO.setRdTrackC(rs.getString(34));
                resVO.setSteepleCh_jmpPenal(rs.getString(35));
                resVO.setSteepleCh_timePenal(rs.getString(36));
                resVO.setUseaPoints(rs.getString(37));
                resVO.setExpectationStatus(rs.getBoolean(38));
                resVO.setEventTypeName(rs.getString(39));
            }
            prepStmt.close();
            releaseConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return resVO;
    }
    
    public ArrayList getEventLevelDetWithoutChamp(String eventId) throws SQLException {
        Debug.print("EventEntryrDAO getEventLevelDetWithoutChamp()");
        ArrayList eventLevelDetails = new ArrayList();
        makeConnection();
        try{
            
            String selectStatement = "select E.event_type_id, D.event_type_name, " +
                    " E.event_level_id, F.event_level_code from tblOEChampionshipDetails E, " +
                    " tblMeeEventTypeMaster D, tblMeeEventLevelMaster F " +
                    " where E.event_type_id = D.event_type_id and " +
                    " E.event_level_id = F.event_level_id and E.event_id= ? and E.championship_status= ? ";
            
            Debug.print("Query :"+selectStatement);
            PreparedStatement prepSelect = con.prepareStatement(selectStatement);
            prepSelect.setString(1,eventId);
            prepSelect.setBoolean(2,false);
            ResultSet rs = prepSelect.executeQuery();
            
            while (rs.next()){
                Debug.print("EventEntryrDAO eventTypeId() in getEventLevelDetWithoutChamp"+rs.getString(1));
                String eventTypeId = rs.getString(1);
                String eventTypeName = rs.getString(2);
                String eventLevelId = rs.getString(3);
                String eventLevelCode = rs.getString(4);
                //boolean champ = rs.getBoolean(5);
                
                String [] levelDet = {eventTypeId, eventTypeName, eventLevelId, eventLevelCode, String.valueOf(false)};
                eventLevelDetails.add(levelDet);
            }
            rs.close();
            prepSelect.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in EventEntryrDAO.getEventLevelDetWithoutChamp():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in EventEntryrDAO.getEventLevelDetWithoutChamp():" + e.getMessage());
        }
        return eventLevelDetails;
    }
    
    public ArrayList selectEventLevelDetailsWithChamp(String eventId) throws SQLException {
        Debug.print("EventEntryrDAO selectEventDetails()");
        ArrayList eventLevelDetails = new ArrayList();
        makeConnection();
        try{
            
            String selectStatement = "select E.event_type_id, D.event_type_name, " +
                    " E.event_level_id, F.event_level_code from tblOEChampionshipDetails E, " +
                    " tblMeeEventTypeMaster D, tblMeeEventLevelMaster F " +
                    " where E.event_type_id = D.event_type_id and " +
                    " E.event_level_id = F.event_level_id and E.event_id= ? and E.championship_status= ? ";
            
            Debug.print("Query :"+selectStatement);
            PreparedStatement prepSelect = con.prepareStatement(selectStatement);
            prepSelect.setString(1,eventId);
            prepSelect.setBoolean(2,true);
            ResultSet rs = prepSelect.executeQuery();
            
            while (rs.next()){
                Debug.print("EventEntryrDAO eventTypeId() in selectEventLevelDetailsWithChamp "+rs.getString(1));
                String eventTypeId = rs.getString(1);
                String eventTypeName = rs.getString(2);
                String eventLevelId = rs.getString(3);
                String eventLevelCode = rs.getString(4);
                // boolean champ = rs.getBoolean(5);
                
                String [] levelDet = {eventTypeId, eventTypeName, eventLevelId, eventLevelCode, String.valueOf(true)};
                eventLevelDetails.add(levelDet);
            }
            rs.close();
            prepSelect.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in EventEntryrDAO.selectEventDetailsBasedOnEventId():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in EventEntryrDAO.selectEventDetailsBasedOnEventId():" + e.getMessage());
        }
        return eventLevelDetails;
    }
    
    public String getMembershipPriority(String membershipTypeId) throws SQLException {
        Debug.print("EventEntryrDAO getMembershipPriority()");
        String priority = "";
        String selectStatement = "Select priority_value from "+DBHelper.OE_MEMBERSHIP_TYPE_MASTER+" where membership_type_id = ?";
        
        Debug.print("Query :"+selectStatement);
        PreparedStatement preSelect = con.prepareStatement(selectStatement);
        preSelect.setString(1,membershipTypeId);
        ResultSet rs1 = preSelect.executeQuery();
        
        while (rs1.next()){
            priority = rs1.getString(1);
        }
        rs1.close();
        preSelect.close();
        return priority;
    }
    
    public String getMembershipTypeName(String membershipTypeId) throws SQLException {
        Debug.print("EventEntryrDAO getMembershipPriority()");
        String membTypeName = "";
        String selectStatement = "Select membership_type_name from "+DBHelper.OE_MEMBERSHIP_TYPE_MASTER+" where membership_type_id = ?";
        
        Debug.print("Query :"+selectStatement);
        PreparedStatement prSelect = con.prepareStatement(selectStatement);
        prSelect.setString(1,membershipTypeId);
        ResultSet rs2 = prSelect.executeQuery();
        
        while (rs2.next()){
            membTypeName = rs2.getString(1);
        }
        rs2.close();
        prSelect.close();
        return membTypeName;
    }
    
    public ArrayList getAllEventTiles(int year) throws RemoteException{
        Debug.print("Inside DAO");
        ArrayList eventList = new ArrayList();
        String selStmt1=null;
        try {
            makeConnection();
            
            selStmt1 ="select event_id, event_title from "+DBHelper.OE_PROVISIONAL_CALENDAR+" where competition_year=? ";
            
            Debug.print("Query: "+selStmt1);
            prepStmt = con.prepareStatement(selStmt1);
            prepStmt.setInt(1,year);
            
            rs = prepStmt.executeQuery();
            while(rs.next()) {
                String eventId = rs.getString(1);
                String eventName = rs.getString(2);
                String tempList[] = {eventId, eventName};
                eventList.add(tempList);
            }
            Debug.print("EventEntryDAO.getEventTiles"+eventList.size());
            rs.close();
            prepStmt.close();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in EventEntryDAO.getEventTiles():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in EventEntryDAO.getEventTiles():" + e.getMessage());
        } finally {
            releaseConnection();
        }
        return eventList;
    }
    
    public ArrayList getCompResultList(String eventId, int year) throws RemoteException{
        Debug.print("Inside DAO getCompResultList");
        ArrayList eventList = new ArrayList();
        String selStmt1=null;
        String eventTypeName=null;
        try {
            makeConnection();
            
            selStmt1 ="select A.comp_result_id, A.event_id, A.rider_member_Id, A.rider_first_name, A.rider_last_name, " +
                    " A.horse_member_id, A.horse_name, B.event_title, C.event_type_name from tblMeeCompResultDetails A, " +
                    " tblOEProvisionalCalendar B, tblMeeEventTypeMaster C where A.event_id=B.event_id and " +
                    " A.event_type_id= C.event_type_id and A.competition_year=B.competition_year and A.event_id= ?" +
                    " and A.competition_year = ?";
            
            Debug.print("Query: "+selStmt1);
            prepStmt = con.prepareStatement(selStmt1);
            prepStmt.setString(1,eventId);
            prepStmt.setInt(2,year);
            rs = prepStmt.executeQuery();
            while(rs.next()) {
                String compResId = rs.getString(1);
                eventId = rs.getString(2);
                String riderMemId = rs.getString(3);
                String riderFName = rs.getString(4);
                String riderLName = rs.getString(5);
                String horseMemId = rs.getString(6);
                String horseName = rs.getString(7);
                String eveTitle = rs.getString(8);
                String eveTypeName = rs.getString(9);
                
                HLCCompResultVO calVO = new HLCCompResultVO();
                calVO.setCompResultId(compResId);
                calVO.setEventId(eventId);
                calVO.setRiderMemberId(riderMemId);
                calVO.setRiderFirstName(riderFName);
                calVO.setRiderLastName(riderLName);
                calVO.setHorseMemberId(horseMemId);
                calVO.setHorseName(horseName);
                calVO.setEventTitle(eveTitle);
                calVO.setEventTypeName(eveTypeName);
                eventList.add(calVO);
            }
            Debug.print("EventEntryDAO.getCompResultList"+eventList.size());
            rs.close();
            prepStmt.close();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in EventEntryDAO.getCompResultList():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in EventEntryDAO.getCompResultList():" + e.getMessage());
        } finally {
            releaseConnection();
        }
        return eventList;
    }
    
    public boolean isCompResExist(int year, String eventId, String horseMemberId, String riderUserId) throws SQLException {
        Debug.print("EventEntryDAO.isCompResExist():");
        boolean result = false;
        try {
            makeConnection();
            
            String selectStatement = "SELECT comp_result_id FROM " + DBHelper.OE_MEE_COMP_RESULT_DETAILS + " " +
                    " WHERE competition_year=? and event_id=? and horse_member_id= ? and rider_user_id=? ";
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setInt(1,year);
            prepStmt.setString(2,eventId);
            prepStmt.setString(3,horseMemberId);
            prepStmt.setString(4,riderUserId);
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                result = true;
            }
            rs.close();
            Debug.print("EventCalendarDAO isCompResExist() result: " + result);
            prepStmt.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in EventCalendarDAO.isCompResExist():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in EventCalendarDAO.isCompResExist():" + e.getMessage());
        }
        return result;
    }
    
    public String getTrainerId() throws SQLException {
        Debug.print("EventEntryDAO.isCompResExist():");
        String trainerId = "";
        try {
            makeConnection();
            
            String selectStatement = "select relationship_type_id from tblHorseRelationshipTypeMaster where relationship_type_name = 'trainer'";
            
            prepStmt = con.prepareStatement(selectStatement);
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                trainerId = rs.getString(1);
            }
            rs.close();
            Debug.print("trainerId gettrainerId(): " + trainerId);
            prepStmt.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in EventCalendarDAO.isCompResExist():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in EventCalendarDAO.isCompResExist():" + e.getMessage());
        }
        return trainerId;
    }
    
    public String selectContactTypeId()  throws RemoteException {
        Debug.print("EventEntryDAO.selectContactTypeId():");
        String userId = "";;
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String selectStatement = "SELECT contact_type_id from tblContactTypeMaster WHERE contact_type_name = ? ";
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,"Primary");
            ResultSet  rs = prepStmt.executeQuery();
            if (rs.next()) {
                userId = rs.getString(1);
            }
            rs.close();
            //  prepStmt.close();
            Debug.print("EventEntryDAO selectUserId selectContactTypeId:" + userId);
            prepStmt.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in EventEntryDAO.selectContactTypeId():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in EventEntryDAO.selectContactTypeId():" + e.getMessage());
        }
        return userId;
    }
    
     public String selectOwnerId(String horseMemberId){
        Debug.print("HorseRegDAO.selectOwnerId() horseMemberId:" + horseMemberId);
        String ownerId  = "";
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String selectStatement = "select owner_id from tblHorseMemberDetails where horse_member_id= ?";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,horseMemberId.trim());
            ResultSet rs = prepStmt.executeQuery();
            if(rs.next()){
                ownerId = rs.getString(1);
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in HorseRegDAO.selectOwnerId():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in HorseRegDAO.selectOwnerId():" + e.getMessage());
        }
        return ownerId;
    }
     
     public String selectUserName(String userId)  throws RemoteException {
        Debug.print("EventEntryDAO.selectUserName():");
        String userName = "";;
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String selectStatement = "SELECT  login_name from " + DBHelper.OE_USER_MASTER + " WHERE user_id = ? ";
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, userId);
            ResultSet  rs = prepStmt.executeQuery();
            if (rs.next()) {
                userName = rs.getString(1);
            }
            rs.close();
            Debug.print("EventEntryDAO selectUserName userName:" + userName);
            prepStmt.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in EventEntryDAO.selectUserName():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in EventEntryDAO.selectUserName():" + e.getMessage());
        }
        return userName;
    }
    /*********************************************************************************************************************************/
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
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in getNextUserId:" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in getNextUserId:" + e.getMessage());
        }
        return nextId;
    }
    private void makeConnection() {
        try {
            InitialContext ic = new InitialContext();
            DataSource ds = (DataSource) ic.lookup(DBHelper.dbName);
            con = ds.getConnection();
        } catch (Exception ex) {
            throw new EJBException("Unable to connect to database. " + ex.getMessage());
        }
    }
    
    private void releaseConnection() {
        try {
            con.close();
        } catch (SQLException ex) {
            throw new EJBException("releaseConnection: " + ex.getMessage());
        }
    }
}