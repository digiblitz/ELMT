/*
 * EventCalendarDAO.java
 *
 * Created on October 29, 2007, 1:40 PM
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
 * @author Dhivya
 */
public class HLCEventCalendarDAO {
    private Connection con = null;
    private PreparedStatement prepStmt = null;
    private ResultSet rs = null;
    private PreparedStatement prepStmt1 = null;
    private ResultSet rs1 = null;
    /** Creates a new instance of EventCalendarDAO */
    public HLCEventCalendarDAO() {
    }
    
 /**********************************************************PROVISIONAL CALENDAR**************************************************************/
    public boolean calenderDetailsExist(String calenderYear) throws SQLException {
        Debug.print("EventCalendarDAO CalenderDetailsExist() Year:" + calenderYear);
        boolean result = false;
        try {
            makeConnection();
            
            String selectStatement = "SELECT competition_year FROM " + DBHelper.OE_PROVISIONAL_CALENDAR + " WHERE competition_year=?";
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,calenderYear);
            
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                result = true;
            }
            rs.close();
            Debug.print("EventCalendarDAO CalenderDetailsExist() result: " + result);
            prepStmt.close();
        } catch (SQLException sqe) {
            releaseConnection();
            Debug.print("Error while calling CalenderDetailsExist : "+sqe.getMessage());
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return result;
    }
    
    public ArrayList selectEventDetails(int presentYear) throws RemoteException{
        Debug.print("Inside DAO");
        Debug.print("presentYear in first"+presentYear);
        ArrayList eventList = new ArrayList();
        
        try {
            makeConnection();
            
            /*String selStmt = "select A.event_id, A.organizer_id ,A.event_title," +
                    " DATEADD(year, 1, A.event_begin_date) as event_begin_date,DATEADD(year, 1, A.event_end_date) as event_end_date, " +
                    " B.area_id,B.state_id from "+DBHelper.OE_EVENT_DETAILS+" A, "+DBHelper.OE_MAPSTATEZIP+" B,"
                    + DBHelper.OE_EVENT_RENEWAL_DETAILS + " C where C.event_id=A.event_id and C.competition_zip >= B.zip_code_from" +
                    " and C.competition_zip <= B.zip_code_to and year(A.event_begin_date) = ?";*/
            String selStmt="select A.event_id, A.organizer_id ,A.event_title, " +
                    "DATEADD(year, 1, A.event_begin_date) as event_begin_date, DATEADD(year, 1, A.event_end_date) as event_end_date,"+
                    "B.area_id,B.state_id from "+DBHelper.OE_EVENT_DETAILS+" A , "+DBHelper.OE_MAPSTATEZIP+" B "+
                    "where A.competition_state = B.state_id and A.competition_zip >= B.zip_code_from "+
                    "and A.competition_zip <= B.zip_code_to and year(A.event_begin_date) = ? order by A.event_begin_date asc";
            
            Debug.print("Query: "+selStmt);
            prepStmt = con.prepareStatement(selStmt);
            prepStmt.setInt(1,presentYear);
            Debug.print("presentYear in set"+presentYear);
            
            java.util.Calendar c6 = java.util.Calendar.getInstance();
            int Curryear = c6.get(Calendar.YEAR);
            System.out.println("Current year = "+Curryear);
            int temyear=Curryear+1;
            System.out.println("temyear year = "+temyear);
            rs = prepStmt.executeQuery();
            
            while(rs.next()) {
                String eventId = rs.getString(1);
                String organizerId = rs.getString(2);
                String eventtitle = rs.getString(3);
                Date beginDate = rs.getDate(4);
                Date endDate = rs.getDate(5);
                String areaId = rs.getString(6);
                String stateId = rs.getString(7);
                Debug.print("EventId: "+eventId);
                Debug.print("organizerId: "+organizerId);
                Debug.print("eventtitle: "+eventtitle);
                Debug.print("beginDate: "+beginDate);
                Debug.print("endDate: "+endDate);
                Debug.print("areaId: "+areaId);
                Debug.print("stateId: "+stateId);
                
                HLCCalendarVO calVO = new HLCCalendarVO();
                calVO.setEventId(eventId);
                calVO.setOrganizerId(organizerId);
                calVO.setEventTitle(eventtitle);
                calVO.setBeginDate(beginDate);
                calVO.setEndDate(endDate);
                calVO.setAreaId(areaId);
                calVO.setStateId(stateId);
                calVO.setCompYear(temyear);
                eventList.add(calVO);
            }
            Debug.print("eventList in DAO"+eventList.size());
            
            Debug.print("eventList in DAO"+eventList.size());
            rs.close();
            prepStmt.close();
        } catch(SQLException sqe){
            //throw new EJBException(sqe);
            Debug.print("Exception in EventCalendarDAO.selectEventDetails()");
            sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return eventList;
    }
    
    public ArrayList getEventDetailsBasedOnEventId(String eventId) throws RemoteException{
        Debug.print("Inside DAO");
        Debug.print("eventId in getEventDet()"+eventId);
        ArrayList eventDet = new ArrayList();
        try {
            makeConnection();
            
            /*String selStmt1 = "select A.event_id, D.event_type_id, F.event_level_id, F.event_level_code from " +
                    DBHelper.OE_EVENT_DETAILS + " A, " + DBHelper.OE_EVENT_LEVEL+ " D, "+ DBHelper.OE_EVENT_TYPE_MASTER +
                    " E, " +DBHelper.OE_EVENT_LEVEL_MASTER+ " F, " +DBHelper.OE_EVENT_TYPE_DETAILS+ "  G where G.map_type_id = D.map_type_id and" +
                    " D.event_type_id = E.event_type_id and D.event_level_id = F.event_level_id and" +
                    " A.event_id = ?";*/
            String selStmt1 ="select A.event_id, D.event_type_id, F.event_level_id, F.event_level_code, E.event_type_name from "+
                    DBHelper.OE_EVENT_DETAILS+"  A ,  "+DBHelper.OE_EVENT_LEVEL+" D , "+DBHelper.OE_EVENT_TYPE_MASTER+" E , " +
                    DBHelper.OE_EVENT_LEVEL_MASTER+ " F , " +DBHelper.OE_EVENT_TYPE_DETAILS+ " G "+
                    "where G.event_id = A.event_id and G.map_type_id = D.map_type_id and D.event_type_id = E.event_type_id and "+
                    "D.event_level_id = F.event_level_id and A.event_id = ? order by A.event_begin_date asc";
            
            Debug.print("Query: "+selStmt1);
            prepStmt = con.prepareStatement(selStmt1);
            prepStmt.setString(1,eventId);
            Debug.print("evetId in set"+eventId);
            rs = prepStmt.executeQuery();
            
            while(rs.next()) {
                String eventId1 = rs.getString(1);
                String eventTypeId = rs.getString(2);
                String eventlevelId = rs.getString(3);
                String eventlevelName = rs.getString(4);
                String eventTypeName=rs.getString(5);
                Debug.print("eventId1: "+eventId1);
                Debug.print("eventTypeId: "+eventTypeId);
                Debug.print("eventlevelId: "+eventlevelId);
                Debug.print("eventlevelName: "+eventlevelName);
                Debug.print("eventTypeName: "+eventTypeName);
                
                HLCCalendarVO calVO = new HLCCalendarVO();
                calVO.setEventId(eventId1);
                calVO.setEventTypeId(eventTypeId);
                calVO.setEventLevelId(eventlevelId);
                calVO.setEventLevel(eventlevelName);
                calVO.setEventTypeNames(eventTypeName);
                eventDet.add(calVO);
            }
            
            Debug.print("eventDet in EventCalendarDAO"+eventDet.size());
            
            Debug.print("eventDet in EventCalendarDAO"+eventDet.size());
            rs.close();
            prepStmt.close();
        } catch(SQLException sqe){
            //throw new EJBException(sqe);
            Debug.print("Exception in EventCalendarDAO.getEventDets()");
            sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return eventDet;
    }
    
    public  String getProvCalId(String eventId, int year)  throws SQLException {
        Debug.print("EventCalendarDAO getProvCalId() ");
        String provCalId = null;
        makeConnection();
        try {
            String selectStatement = "SELECT pro_calendar_id from " + DBHelper.OE_PROVISIONAL_CALENDAR + " where event_Id = ? and" +
                    " competition_year = ?";
            PreparedStatement prepSelect = con.prepareStatement(selectStatement);
            
            prepSelect.setString(1,eventId);
            Debug.print("evetId in set"+eventId);
            prepSelect.setInt(2,year);
            rs = prepSelect.executeQuery();
            
            while (rs.next()) {
                provCalId = rs.getString(1);
            }
            
            rs.close();
            prepSelect.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in getProvCalId():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in getProvCalId():" + e.getMessage());
        }
        Debug.print("Finally returned Provisional Id: " + provCalId);
        return provCalId;
    }
    
    /*public boolean insertChampionshipDetails(String proCalId,String eventId,String eventTypeId,String eventLevelId) {
        Debug.print("EventCalendarDAO.insertChampionshipDetails():");
        boolean result = false;
        PreparedStatement prepStmt = null;
        //String provCalId = "";
        
        makeConnection();
        try {
            String insertStatement = "insert into " + DBHelper.OE_CHAMP_DETAILS + " "+
                    " (pro_calendar_id, event_id, event_type_id, event_level_id)" +
                    " values ( ?, ?, ?, ?) ";
            
            prepStmt = con.prepareStatement(insertStatement);
            //Debug.print("EventCalendarDAO insertEventDetails:" + provCalId);
            
            //if(provCalId!=null || provCalId.trim().length()!=0){
            prepStmt.setString(1, proCalId);
            prepStmt.setString(2, eventId);
            prepStmt.setString(3, eventTypeId);
            prepStmt.setString(4, eventLevelId);
            
            int cnt = prepStmt.executeUpdate();
            Debug.print("Record Inserted successfully in insertChampionshipDetails(): " + cnt);
            result = true;
            Debug.print("EventCalendarDAO.insertChampionshipDetails(): Status :" + result);
            //}
            prepStmt.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in EventCalendarDAO.insertChampionshipDetails():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in EventCalendarDAO.insertChampionshipDetails():" + e.getMessage());
        }
        return result;
        
    }*/
    
    public String insertCalenderDetails(String eventId,String organizerId,String eventTitle,
            Date beginDat,Date endDat,String areaId,String stateId,int compYear,String eventTypeId,String eventLevelNames) {
        Debug.print("PriveDetailDAO.insertCalenderDetails():");
        String calenderId = "";
        boolean result=false;
        PreparedStatement prepStmt = null;
        try{
            calenderId = getNextId();
            } catch(Exception e){
            Debug.print("Exception while calling getNextId():" + e.getMessage());
            }
        if(calenderId!=null && calenderId.trim().length()!=0){
            makeConnection();
            try {
                String insertStatement = "insert into " + DBHelper.OE_PROVISIONAL_CALENDAR + " "+ 
                        " (pro_calendar_id,event_id, organizer_id, event_title, begin_date, end_date, area_id, state_id, competition_year, event_type_id, event_levels)" +
                        " values (? , ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
                
                Debug.print("Query: "+insertStatement);
                prepStmt = con.prepareStatement(insertStatement);
                prepStmt.setString(1, calenderId);
                prepStmt.setString(2, eventId);
                prepStmt.setString(3, organizerId);
                prepStmt.setString(4, eventTitle);
                prepStmt.setDate(5, DBHelper.toSQLDate(beginDat));
                prepStmt.setDate(6, DBHelper.toSQLDate(endDat));
                prepStmt.setString(7, areaId);
                prepStmt.setString(8, stateId);
                prepStmt.setInt(9,compYear);
                prepStmt.setString(10,eventTypeId);
                prepStmt.setString(11,eventLevelNames);
                
                int cnt = prepStmt.executeUpdate();
                Debug.print("Record Inserted succefully into insertCalenderDetails "+cnt);
                if(cnt >=1) result = true;
                Debug.print("EventCalendarDAO insertCalenderDetails() Status :" + result);
                prepStmt.close();
                releaseConnection();
            } catch(SQLException sql){
                releaseConnection();
                Debug.print("SQL Exception in EventCalendarDAO.insertCalenderDetails():" + sql.getMessage());
            } catch(Exception e){
                releaseConnection();
                Debug.print("General Exception  in EventCalendarDAO.insertCalenderDetails():" + e.getMessage());
            }
        }
        return calenderId;
    }
     public String insertChampionshipDetails(String provCalId,String eventId,String eventTypeId,String eventLevelId) {
            Debug.print("UjaniDamEntityBean.insertChampionshipDetails():");
            boolean result = false;
           
            PreparedStatement prepStmt = null;
            
            makeConnection();
            try {
                 String insertStatement = "insert into " + DBHelper.OE_CHAMP_DETAILS + " "+
                         " (pro_calendar_id, event_id, event_type_id, event_level_id)" +
                         " values ( ?, ?, ?, ?) ";

                    prepStmt = con.prepareStatement(insertStatement);
                    prepStmt.setString(1, provCalId);
                    prepStmt.setString(2, eventId);
                    prepStmt.setString(3, eventTypeId);
                    prepStmt.setString(4, eventLevelId);
                                        
                    int cnt = prepStmt.executeUpdate();
                    Debug.print("Record Inserted successfully in insertChampionshipDetails(): " + cnt);
                    result = true;
                    Debug.print("EventCalendarDAO.insertChampionshipDetails(): Status :" + result);
               
                 prepStmt.close();
                 releaseConnection();
        }
        catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in UjaniDamEntityBean.insertChampionshipDetails():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in UjaniDamEntityBean.insertChampionshipDetails():" + e.getMessage());
        }
    return provCalId;   
    }
    public boolean updateChampStatus(String eventLevelId, String eventTypeId, String provisionalId) {
        Debug.print("DITRegDAO.updateChampStatus():");
        boolean result = false;
        PreparedStatement prepStmt = null;
        if(eventLevelId!=null && eventLevelId.trim().length()!=0 && provisionalId!=null && provisionalId.trim().length()!=0){
            makeConnection();
            try {
                String updateStatement = "update " + DBHelper.OE_CHAMP_DETAILS + " set championship_status = ? " +
                        " where event_level_id = ? and event_type_id = ? and pro_calendar_id = ?";
                
                prepStmt = con.prepareStatement(updateStatement);
                prepStmt.setBoolean(1, true);
                prepStmt.setString(2, eventLevelId);
                prepStmt.setString(3, eventTypeId);
                prepStmt.setString(4, provisionalId);
                
                int cnt = prepStmt.executeUpdate();
                Debug.print("Record Inserted succefully into updateChampStatus "+cnt);
                
                if(cnt>=1){
                    result = true;
                }
                
                Debug.print("EventCalendarDAO updateChampStatus() Status :" + result);
                prepStmt.close();
                releaseConnection();
            } catch(SQLException sql){
                releaseConnection();
                Debug.print("SQL Exception in EventCalendarDAO.updateChampStatus():" + sql.getMessage());
            } catch(Exception e){
                releaseConnection();
                Debug.print("General Exception  in EventCalendarDAO.updateChampStatus():" + e.getMessage());
            }
        }
        return result;
    }
    
    public ArrayList selectAllEventLevels() throws SQLException {
        Debug.print("EventCalendarDAO selectAllEventLevels");
        ArrayList eventList = new ArrayList();
        makeConnection();
        try{
            String selectStatement = "select event_level_id, event_level_code from " + DBHelper.OE_EVENT_LEVEL_MASTER + " order by event_level_code";
            PreparedStatement prepSelect = con.prepareStatement(selectStatement);
            ResultSet rs = prepSelect.executeQuery();
            while (rs.next()){
                String eventLevelId = rs.getString(1);
                String eventLevelCode = rs.getString(2);
                String tempList[] = {eventLevelId, eventLevelCode};
                eventList.add(tempList);
            }
            rs.close();
            prepSelect.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in EventCalendarDAO.selectAllEventLevels():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in EventCalendarDAO.selectAllEventLevels():" + e.getMessage());
        }
        return eventList;
    }
    
    public ArrayList selectLevelsChecked(String calendarId) throws SQLException {
        Debug.print("EventCalendarDAO selectLevelsChecked");
        ArrayList subjectList = new ArrayList();
        makeConnection();
        try{
            /*String selectStatement = "select A.event_level_id, A.event_level_code, B.event_level_id,A.event_type_id from " + DBHelper.OE_EVENT_LEVEL_MASTER + " A ," +
                    DBHelper.OE_CHAMP_DETAILS + " B where  A.event_level_id = B.event_level_id and B.pro_calendar_id = ? order by A.event_level_code ";*/
            
            String selectStatement="select A.event_level_id, A.event_level_code, C.event_type_id, C.event_type_name, B.pro_calendar_id, " +
                    "B.championship_status from "+DBHelper.OE_EVENT_LEVEL_MASTER+"  A ,"+DBHelper.OE_CHAMP_DETAILS+"  B , "+DBHelper.OE_EVENT_TYPE_MASTER+" C where "+   
                    "A.event_level_id = B.event_level_id and C.event_type_id = B.event_type_id and B.pro_calendar_id = ? order by A.event_level_code ";
            Debug.print("Query :"+selectStatement);
            PreparedStatement prepSelect = con.prepareStatement(selectStatement);
            prepSelect.setString(1,calendarId);
            ResultSet rs = prepSelect.executeQuery();
            while (rs.next()){
                String eventLevelId = rs.getString(1);
                String eventLevelCode = rs.getString(2);
                String eventTypeId = rs.getString(3);
                String eventTypeName = rs.getString(4);
                calendarId = rs.getString(5);
                boolean champStatus = rs.getBoolean(6);
                String tempSubList[] = {eventLevelId, eventLevelCode, eventTypeId, eventTypeName,calendarId,String.valueOf(champStatus)};
                subjectList.add(tempSubList);
            }
            rs.close();
            prepSelect.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in EventCalendarDAO.selectLevelsChecked():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in EventCalendarDAO.selectLevelsChecked():" + e.getMessage());
        }
        return subjectList;
    }
    public ArrayList getOrganizerList(int year, String acStatus, String organizerId) throws SQLException {
        ArrayList organizerList = new ArrayList();
        Debug.print("Inside DAO");
        try {
            makeConnection();
            
            String selStatement = "Select A.pro_calendar_id, A.begin_date, A.end_date, A.event_title, "+
                    "B.area_code, B.area_name, C.state_name, A.org_approval_status, "+
                    "A.renewal_status, A.event_id, A.area_chair_approval_status from "+DBHelper.OE_PROVISIONAL_CALENDAR+" A, "+DBHelper.OE_AREA_MASTER+" B, "+
                    DBHelper.OE_STATE_MASTER+" C  where A.area_id=B.area_id and A.state_id=C.state_id "+
                    "and A.organizer_id = ? and A.competition_year = ? order by A.begin_date, A.event_title";
            
            /*if(acStatus!=null && acStatus.trim().length()!=0){
                selStatement = selStatement +" and A.area_chair_approval_status = ?";
            }
            selStatement = selStatement+" order by A.begin_date";*/
            
            PreparedStatement prepStmt = con.prepareStatement(selStatement);
            prepStmt.setString(1,organizerId);
            prepStmt.setInt(2,year);
            /*if(acStatus!=null && acStatus.trim().length()!=0){
                prepStmt.setString(3,acStatus);
            }*/
            
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                HLCCalendarVO calVO = new HLCCalendarVO();
                calVO.setCalenderId(rs.getString(1));
                calVO.setBeginDate(rs.getDate(2));
                calVO.setEndDate(rs.getDate(3));
                calVO.setEventTitle(rs.getString(4));
                calVO.setAreaName(rs.getString(6));
                calVO.setStateName(rs.getString(7));
                calVO.setOrgApprovalStatus(rs.getString(8));
                calVO.setRenewStat(new Boolean(rs.getBoolean(9)));
                calVO.setEventId(rs.getString(10));
                calVO.setArearChairApproStatus(rs.getString(11));
                organizerList.add(calVO);
            }
            Debug.print("ArrayList Size: "+organizerList.size());
            prepStmt.close();
            releaseConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return organizerList;
    }
    
    public HLCCalendarVO getSingleEventDetails(String provisionalId) throws SQLException {
        HLCCalendarVO calVO = new HLCCalendarVO();
        Debug.print("Inside DAO");
        String selStatement=null;
        PreparedStatement prepStmt=null;
        ResultSet rs=null;
        try {
            makeConnection();           
             selStatement = "Select A.pro_calendar_id, A.organizer_id, A.begin_date, A.end_date, " +
                    " A.no_of_days, A.oe_begin_date, A.oe_end_date, A.oe_extended_due_date, " +
                    " A.event_title, E.user_id, E.first_name, E.last_name, B.area_code, B.area_name, C.state_name, " +
                    " A.org_approval_status, A.renewal_status, A.event_desc, A.event_type_id, A.event_levels, "+
                    " A.org_comments, A.event_id, A.area_chair_approval_status, " +
                    " A.area_chair_comments, A.usea_approval_status, A.staff_comments ,A.area_id, " +
                    " A.state_id, A.issue_id, A.organizer_id from "+DBHelper.OE_PROVISIONAL_CALENDAR+" A, " +DBHelper.OE_AREA_MASTER+" B, "+DBHelper.OE_STATE_MASTER+
                    " C, "+DBHelper.OE_MEMBER_DETAILS+" D, "+DBHelper.OE_USER_MASTER+" E where A.area_id=B.area_id and A.state_id=C.state_id " +
                    " and D.user_id=E.user_id and A.organizer_id=D.member_id and A.pro_calendar_id = ?";
            
             prepStmt = con.prepareStatement(selStatement);
            prepStmt.setString(1,provisionalId);
            
             rs = prepStmt.executeQuery();
            while (rs.next()){
                calVO.setCalenderId(rs.getString(1));
                calVO.setOrganizerId(rs.getString(2));
                calVO.setBeginDate(rs.getDate(3));
                calVO.setEndDate(rs.getDate(4));
                calVO.setNoDays(rs.getInt(5));                
                calVO.setEntryStrtDate(rs.getDate(6));
                calVO.setEntryEndDate(rs.getDate(7));
                calVO.setExtDueDate(rs.getDate(8));
                calVO.setEventTitle(rs.getString(9));
                calVO.setUserId(rs.getString(10));
                calVO.setOrgFName(rs.getString(11));
                calVO.setOrgLName(rs.getString(12));
                calVO.setAreaCode(rs.getString(13));
                calVO.setAreaName(rs.getString(14));
                calVO.setStateName(rs.getString(15));
                calVO.setOrgApprovalStatus(rs.getString(16));
                calVO.setRenewStat(new Boolean(rs.getBoolean(17)));
                calVO.setEventDesc(rs.getString(18));
                calVO.setEventTypeId(rs.getString(19));
                calVO.setEventLevel(rs.getString(20));
                calVO.setOrgComments(rs.getString(21));
                calVO.setEventId(rs.getString(22));
                calVO.setArearChairApproStatus(rs.getString(23));
                calVO.setAreaChairCommt(rs.getString(24));
                calVO.setApprovalStatus(rs.getString(25));
                calVO.setStaffComments(rs.getString(26));
                calVO.setAreaId(rs.getString(27));
                calVO.setStateId(rs.getString(28));
                calVO.setEveIssueId(rs.getString(29));
                calVO.setOrganizerId(rs.getString(30));
            }
            if(provisionalId!=null && provisionalId.trim().length()!=0){
                
                 selStatement = "Select B.event_level_code from tblOEChampionshipDetails A, " +
                        " tblMeeEventLevelMaster B, tblOEProvisionalCalendar C " +
                        " where A.event_level_id=B.event_level_id and A.event_id=C.event_id " +
                        " and C.pro_calendar_id = ?"; 
                                                                                        
                        /* "Select B.event_level_code from tblOEChampionshipDetails A, " +
                        " tblMeeEventLevelMaster B, tblOEProvisionalCalendar C " +
                        " where A.event_level_id=B.event_level_id and A.event_id=C.event_id " +
                        " and A.championship_status='true'and " +
                        " C.area_chair_approval_status='Approved' and " +
                        " C.org_approval_status='Approved' and " +
                        " C.usea_approval_status='Approved' and C.pro_calendar_id = ?";*/
                 
             prepStmt = con.prepareStatement(selStatement);
            prepStmt.setString(1,provisionalId);
            
             rs = prepStmt.executeQuery();
            while (rs.next()){
                calVO.setChmpLevel(rs.getString(1));
            }
            }
            prepStmt.close();
            releaseConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return calVO;
    }
    
    public boolean approveSingleEventDetails(String provisionalId, String orgStatus, String orgComments) throws SQLException {
        Debug.print("Inside DAO");
        boolean result = false;
        int upCnt = 0;
        try {
            makeConnection();
            
            String updateStatement = "update "+DBHelper.OE_PROVISIONAL_CALENDAR+" set org_approval_status = ?, "+
                    "org_comments = ? where pro_calendar_id= ?";
            
            PreparedStatement prepStmt = con.prepareStatement(updateStatement);
            prepStmt.setString(1,orgStatus);
            prepStmt.setString(2,orgComments);
            prepStmt.setString(3,provisionalId);
            
            upCnt = prepStmt.executeUpdate();
            if(upCnt!=0) result = true;
            
            prepStmt.close();
            releaseConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public ArrayList getAreaChairList(int year, String acStatus, String acId) throws SQLException {
        ArrayList areaChairList = new ArrayList();
        Debug.print("Inside DAO");
        try {
            makeConnection();
            
            String selStatement = "select C.pro_calendar_id, C.begin_date, C.end_date, C.event_title, B.area_name, "+
                    "D.state_name, C.org_approval_status, C.renewal_status, C.event_id, C.area_chair_approval_status, "+
                    "A.area_id, C.organizer_id from "+ DBHelper.OE_AREA_CHAIR+" A, "+DBHelper.OE_AREA_MASTER+" B, "+
                    DBHelper.OE_PROVISIONAL_CALENDAR+" C, "+DBHelper.OE_STATE_MASTER+" D  where A.area_id = B.area_id "+
                    "and A.area_id = C.area_id and B.area_id = C.area_id and c.state_id = D.state_id and A.area_chair_id = ? "+
                    "and C.competition_year = ? order by C.begin_date,C.event_title";
            
           /* if(acStatus!=null && acStatus.trim().length()!=0){
                selStatement = selStatement +" and C.org_approval_status = ?";
            }
            selStatement = selStatement+" order by C.begin_date";*/
            
            PreparedStatement prepStmt = con.prepareStatement(selStatement);
            prepStmt.setString(1,acId);
            prepStmt.setInt(2,year);
            /*if(acStatus!=null && acStatus.trim().length()!=0){
                prepStmt.setString(3,acStatus);
            }*/
            
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                HLCCalendarVO calVO = new HLCCalendarVO();
                calVO.setCalenderId(rs.getString(1));
                calVO.setBeginDate(rs.getDate(2));
                calVO.setEndDate(rs.getDate(3));
                calVO.setEventTitle(rs.getString(4));
                calVO.setAreaName(rs.getString(5));
                calVO.setStateName(rs.getString(6));
                calVO.setOrgApprovalStatus(rs.getString(7));
                calVO.setRenewStat(new Boolean(rs.getBoolean(8)));
                calVO.setEventId(rs.getString(9));
                calVO.setArearChairApproStatus(rs.getString(10));
                calVO.setAreaId(rs.getString(11));
                calVO.setOrganizerId(rs.getString(12));
                
                areaChairList.add(calVO);
            }
            Debug.print("ArrayList Size: "+areaChairList.size());
            prepStmt.close();
            releaseConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return areaChairList;
    }
    
    public boolean approveAreaChairDetails(String provisionalId, String acStatus, String acComments) throws SQLException {
        Debug.print("Inside DAO");
        boolean result = false;
        int upCnt = 0;
        try {
            makeConnection();
            
            String updateStatement = "update "+DBHelper.OE_PROVISIONAL_CALENDAR+" set area_chair_approval_status = ?, "+
                    "area_chair_comments = ? where pro_calendar_id= ?";
            
            PreparedStatement prepStmt = con.prepareStatement(updateStatement);
            prepStmt.setString(1,acStatus);
            prepStmt.setString(2,acComments);
            prepStmt.setString(3,provisionalId);
            
            upCnt = prepStmt.executeUpdate();
            if(upCnt!=0) result = true;
            
            prepStmt.close();
            releaseConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public boolean updateEventDetByOrg(HLCCalendarVO calVO) {
        Debug.print("EventCalendarDAO.updateEventDetByOrg():");
        boolean result = false;
        PreparedStatement prepStmt = null;
        String empId = "";
        String tmpCategoryId = "";
        String catId = null;
        String calendarId  = calVO.getCalenderId();
        String eventId=calVO.getEventId();
        boolean renStatus=false;
        String eventTypeName = "";
        String insertStatement=null;
        Debug.print("calendarId in EventCalendarDAO.updateEventDetByOrg()"+calendarId);
        if(calendarId!=null && calendarId.trim().length()!=0){
            makeConnection();
            try {
                 insertStatement = "update "+DBHelper.OE_PROVISIONAL_CALENDAR+" set  event_title = ?, begin_date = ?,"+
                        " no_of_days =?, end_date =?, oe_begin_date = ?, oe_end_date = ?, oe_extended_due_date = ?, area_id =?, state_id =?, event_levels =?, " +
                        " org_comments =?, modify_date =?, modified_by =?, ip_address =?,"+
                        " renewal_status=?, event_type_id=?, staff_comments=?, area_chair_comments=?,"+
                        " usea_approval_status=?, issue_id=?, organizer_id=? where pro_calendar_id = ?";
                                         
               prepStmt = con.prepareStatement(insertStatement);                
               calVO.setCalenderId(calendarId);
                               
                prepStmt.setString(1, calVO.getEventTitle());
                if(calVO.getBeginDate()!=null){
                    prepStmt.setDate(2, DBHelper.toSQLDate(calVO.getBeginDate()));
                } else{
                    prepStmt.setDate(2, null);
                }
                 prepStmt.setInt(3, calVO.getNoDays());
                 
                if(calVO.getEndDate()!=null){
                    prepStmt.setDate(4, DBHelper.toSQLDate(calVO.getEndDate()));
                } else{
                    prepStmt.setDate(4, null);
                }
               
                
               if(calVO.getEntryStrtDate()!=null){
                    prepStmt.setDate(5, DBHelper.toSQLDate(calVO.getEntryStrtDate()));
                } else{
                    prepStmt.setDate(5, null);
                }
                
                if(calVO.getEntryEndDate()!=null){
                    prepStmt.setDate(6, DBHelper.toSQLDate(calVO.getEntryEndDate()));
                } else{
                    prepStmt.setDate(6, null);
                }
                
                if(calVO.getExtDueDate()!=null){
                    prepStmt.setDate(7, DBHelper.toSQLDate(calVO.getExtDueDate()));
                } else{
                    prepStmt.setDate(7, null);
                } 
                prepStmt.setString(8, calVO.getAreaId());
                prepStmt.setString(9, calVO.getStateId());
                prepStmt.setString(10, calVO.getEventLevel());
                prepStmt.setString(11, calVO.getOrgComments());
                if(calVO.getModifyDate()!=null){
                    prepStmt.setDate(12, DBHelper.toSQLDate(calVO.getModifyDate()));
                } else{
                    prepStmt.setDate(12, null);
                }
                prepStmt.setString(13, calVO.getModifyBy());
                prepStmt.setString(14, calVO.getIpAddress());
                renStatus=calVO.getRenewStat().booleanValue();
                prepStmt.setBoolean(15,renStatus);
                Debug.print("calVO.getRenewStat() :"+calVO.getRenewStat());
                Debug.print("renStatus :"+renStatus);
                eventTypeName = calVO.getEventTypeNames();
                prepStmt.setString(16, eventTypeName);
                Debug.print("eventTypeName :"+eventTypeName);
                if(calVO.getStaffComments()!=null){
                prepStmt.setString(17, calVO.getStaffComments());
                }else{
                 prepStmt.setString(17, null);   
                }
                if(calVO.getAreaChairCommt()!=null){
                prepStmt.setString(18, calVO.getAreaChairCommt());
                }else{
                prepStmt.setString(18, null);    
                }
                if(calVO.getApprovalStatus()!=null){
                prepStmt.setString(19, calVO.getApprovalStatus());
                }else{
                prepStmt.setString(19, null);    
                }
                System.out.println("Inside DAO : Event Issue Id :"+calVO.getEveIssueId());
                prepStmt.setString(20, calVO.getEveIssueId());
                if(calVO.getOrganizerId()!=null){
                prepStmt.setString(21, calVO.getOrganizerId());
                }else{
                prepStmt.setString(21, null);    
                }
                prepStmt.setString(22, calendarId);  
                                                                                        
                int cnt = prepStmt.executeUpdate();
                Debug.print("Record Inserted succefully into provisional calendar table in updateEventDetByOrg"+cnt);
                if(cnt>=1){
                    result = true;
                }
                Debug.print("EventCalendarDAO provisional calendar table Status in updateEventDetByOrg:" + result);
                prepStmt.close();
                releaseConnection();
            } catch(SQLException sql){
                releaseConnection();
                Debug.print("SQL Exception in EventCalendarDAO.updateEventDetByOrg():" + sql.getMessage());
            } catch(Exception e){
                releaseConnection();
                Debug.print("General Exception  in EventCalendarDAO.updateEventDetByOrg():" + e.getMessage());
            }
        }
        /*if(result==true) {
            try{
                boolean deleteResult = deleteChampDetails(eventId);
                Debug.print("EventCalendarDAO.deleteChampDetails() result: " +  deleteResult);
            } catch(Exception e){
                Debug.print("General Exception in EventCalendarDAO.deleteChampDetails():" + e.getMessage());
            }
        }*/
        return result;
    }
    
    public boolean deleteChampDetails(String eventId) throws SQLException {
        Debug.print("EventCalendarDAO deleteChampDetails() Year:" + eventId);
        boolean result = false;
        try {
            makeConnection();
            
            String selectStatement = "delete FROM " + DBHelper.OE_CHAMP_DETAILS + " WHERE event_id = ? ";
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,eventId);
            
            int cnt = prepStmt.executeUpdate();
            if (cnt >= 1) {
                result = true;
            }
            Debug.print("EventCalendarDAO deleteChampDetails() result: " + result);
            prepStmt.close();
            releaseConnection();
            }catch (SQLException sqe) {
            releaseConnection();
            Debug.print("Error while calling deleteChampDetails : "+sqe.getMessage());
            } 
        return result;
    }
    
    public boolean updateEventDetByAreaChair(HLCCalendarVO calVO) {
        Debug.print("EventCalendarDAO.updateEventDetByAreaChair():");
        boolean result = false;
        PreparedStatement prepStmt = null;
        String empId = "";
        String tmpCategoryId = "";
        String catId = null;
        String calendarId  = calVO.getCalenderId();
        String eventId=calVO.getEventId();
        boolean renStatus=false;
        String insertStatement=null;
        Debug.print("calendarId in EventCalendarDAO.updateEventDetByAreaChair()"+calendarId);
        if(calendarId!=null && calendarId.trim().length()!=0){
            makeConnection();
            try {
                 insertStatement = "update "+DBHelper.OE_PROVISIONAL_CALENDAR+" set  event_title = ?, begin_date = ?,"+
                        " no_of_days =?, end_date =?, oe_begin_date = ?, oe_end_date = ?, oe_extended_due_date = ?, area_id =?, state_id =?, event_levels =?, " +
                        " modify_date =?, modified_by =?, ip_address =?, area_chair_comments=? "+
                        " where pro_calendar_id = ?" ;
                                         
               prepStmt = con.prepareStatement(insertStatement);                
               calVO.setCalenderId(calendarId);
                               
                prepStmt.setString(1, calVO.getEventTitle());
                if(calVO.getBeginDate()!=null){
                    prepStmt.setDate(2, DBHelper.toSQLDate(calVO.getBeginDate()));
                } else{
                    prepStmt.setDate(2, null);
                }
                 prepStmt.setInt(3, calVO.getNoDays());
                 
                if(calVO.getEndDate()!=null){
                    prepStmt.setDate(4, DBHelper.toSQLDate(calVO.getEndDate()));
                } else{
                    prepStmt.setDate(4, null);
                }
               
                
               if(calVO.getEntryStrtDate()!=null){
                    prepStmt.setDate(5, DBHelper.toSQLDate(calVO.getEntryStrtDate()));
                } else{
                    prepStmt.setDate(5, null);
                }
                
                if(calVO.getEntryEndDate()!=null){
                    prepStmt.setDate(6, DBHelper.toSQLDate(calVO.getEntryEndDate()));
                } else{
                    prepStmt.setDate(6, null);
                }
                
                if(calVO.getExtDueDate()!=null){
                    prepStmt.setDate(7, DBHelper.toSQLDate(calVO.getExtDueDate()));
                } else{
                    prepStmt.setDate(7, null);
                } 
                prepStmt.setString(8, calVO.getAreaId());
                prepStmt.setString(9, calVO.getStateId());
                prepStmt.setString(10, calVO.getEventLevel());
                if(calVO.getModifyDate()!=null){
                    prepStmt.setDate(11, DBHelper.toSQLDate(calVO.getModifyDate()));
                } else{
                    prepStmt.setDate(11, null);
                }
                prepStmt.setString(12, calVO.getModifyBy());
                prepStmt.setString(13, calVO.getIpAddress());
                prepStmt.setString(14, calVO.getAreaChairCommt());
                prepStmt.setString(15, calendarId);  
                                                                                        
                int cnt = prepStmt.executeUpdate();
                Debug.print("Record Inserted succefully into provisional calendar table in updateEventDetByAreaChair"+cnt);
                if(cnt>=1){
                    result = true;
                }
                Debug.print("EventCalendarDAO provisional calendar table Status in updateEventDetByAreaChair:" + result);
                prepStmt.close();
                releaseConnection();
            } catch(SQLException sql){
                releaseConnection();
                Debug.print("SQL Exception in EventCalendarDAO.updateEventDetByAreaChair():" + sql.getMessage());
            } catch(Exception e){
                releaseConnection();
                Debug.print("General Exception  in EventCalendarDAO.updateEventDetByAreaChair():" + e.getMessage());
            }
        }
        
        return result;
    }
    
    
    public boolean updateEventDetByStaff(HLCCalendarVO calVO) {
        Debug.print("EventCalendarDAO.updateEventDetByStaff():");
        boolean result = false;
        PreparedStatement prepStmt = null;
        String empId = "";
        String tmpCategoryId = "";
        String catId = null;
        String calendarId  = calVO.getCalenderId();
        String eventId=calVO.getEventId();
        boolean renStatus=false;
        String insertStatement=null;
        Debug.print("calendarId in EventCalendarDAO.updateEventDetByOrg()"+calendarId);
        if(calendarId!=null && calendarId.trim().length()!=0){
            makeConnection();
            try {
                 insertStatement = "update "+DBHelper.OE_PROVISIONAL_CALENDAR+" set  event_title = ?, begin_date = ?,"+
                        " no_of_days =?, end_date =?, oe_begin_date = ?, oe_end_date = ?, oe_extended_due_date = ?, area_id =?, state_id =?, event_levels =?, " +
                        " modify_date =?, modified_by =?, ip_address =?,"+
                        " staff_comments=? where pro_calendar_id = ?" ;
                                         
               prepStmt = con.prepareStatement(insertStatement);                
               calVO.setCalenderId(calendarId);
                               
                prepStmt.setString(1, calVO.getEventTitle());
                if(calVO.getBeginDate()!=null){
                    prepStmt.setDate(2, DBHelper.toSQLDate(calVO.getBeginDate()));
                } else{
                    prepStmt.setDate(2, null);
                }
                 prepStmt.setInt(3, calVO.getNoDays());
                 
                if(calVO.getEndDate()!=null){
                    prepStmt.setDate(4, DBHelper.toSQLDate(calVO.getEndDate()));
                } else{
                    prepStmt.setDate(4, null);
                }
               
                
               if(calVO.getEntryStrtDate()!=null){
                    prepStmt.setDate(5, DBHelper.toSQLDate(calVO.getEntryStrtDate()));
                } else{
                    prepStmt.setDate(5, null);
                }
                
                if(calVO.getEntryEndDate()!=null){
                    prepStmt.setDate(6, DBHelper.toSQLDate(calVO.getEntryEndDate()));
                } else{
                    prepStmt.setDate(6, null);
                }
                
                if(calVO.getExtDueDate()!=null){
                    prepStmt.setDate(7, DBHelper.toSQLDate(calVO.getExtDueDate()));
                } else{
                    prepStmt.setDate(7, null);
                } 
                prepStmt.setString(8, calVO.getAreaId());
                prepStmt.setString(9, calVO.getStateId());
                prepStmt.setString(10, calVO.getEventLevel());
                if(calVO.getModifyDate()!=null){
                    prepStmt.setDate(11, DBHelper.toSQLDate(calVO.getModifyDate()));
                } else{
                    prepStmt.setDate(11, null);
                }
                prepStmt.setString(12, calVO.getModifyBy());
                prepStmt.setString(13, calVO.getIpAddress());
                prepStmt.setString(14, calVO.getStaffComments());       
                prepStmt.setString(15, calendarId);  
                                                                                        
                int cnt = prepStmt.executeUpdate();
                Debug.print("Record Inserted succefully into provisional calendar table for updateEventDetByStaff"+cnt);
                if(cnt>=1){
                    result = true;
                }
                Debug.print("EventCalendarDAO provisional calendar table Status in updateEventDetByStaff:" + result);
                prepStmt.close();
                releaseConnection();
            } catch(SQLException sql){
                releaseConnection();
                Debug.print("SQL Exception in EventCalendarDAO.updateEventDetByStaff():" + sql.getMessage());
            } catch(Exception e){
                releaseConnection();
                Debug.print("General Exception  in EventCalendarDAO.updateEventDetByStaff():" + e.getMessage());
            }
        }
       
        return result;
    }
   
    public ArrayList selectAllAreaMaster(){
        Debug.print("EventCalendarDAO.selectAllAreaMaster():");
        ArrayList areaList = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
        try {
            String selectStatement=" SELECT area_id, area_code, area_name from " + DBHelper.OE_AREA_MASTER+" order by area_code" ;
            prepStmt = con.prepareStatement(selectStatement);
            rs = prepStmt.executeQuery();
            areaList = new ArrayList();
            while(rs.next()){
                String area_id = rs.getString(1);
                String area_code = rs.getString(2);
                String area_name = rs.getString(3);
                String[] strAreaList = {area_id, area_code, area_name};
                areaList.add(strAreaList);
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
            // Debug.print("EventsDAO.selectAllAreaMaster():" + areaList);
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in EventCalendarDAO.selectAllAreaMaster():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in EventCalendarDAO.selectAllAreaMaster():" + e.getMessage());
        }
        return areaList;
    }
    
    public ArrayList selectAllStateMaster(){
        Debug.print("EventCalendarDAO.selectAllAreaMaster():");
        ArrayList stateList = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
        try {
            String selectStatement=" SELECT state_id, state_name, state_code from " + DBHelper.OE_STATE_MASTER ;
            prepStmt = con.prepareStatement(selectStatement);
            rs = prepStmt.executeQuery();
            stateList = new ArrayList();
            while(rs.next()){
                String stateId = rs.getString(1);
                String stateName = rs.getString(2);
                String stateCode = rs.getString(3);
                String[] strStateList = {stateId, stateName, stateCode};
                stateList.add(strStateList);
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
            // Debug.print("EventsDAO.selectAllAreaMaster():" + areaList);
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in EventCalendarDAO.selectAllStateMaster():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in EventCalendarDAO.selectAllStateMaster():" + e.getMessage());
        }
        return stateList;
    }
    
    public boolean insertChampDetails(String provisionalId, String eventId ,String eventTypeId, String eventLevelId) {
        Debug.print("EventCalendarDAO.insertChampDetails():");
        PreparedStatement prepStmt = null;
        boolean result = false;
        boolean deleteResult = false;
        makeConnection();
        /*try{
        deleteResult=deleteChampDetails(eventId);
        }catch(Exception e){
        Debug.print("General Exception in EventCalendarDAO.deleteChampDetails():" + e.getMessage());
        }*/
        
        try {
            String insertStatement = "insert into "+DBHelper.OE_CHAMP_DETAILS+" (pro_calendar_id, event_id, event_type_id, event_level_id)" +
                    " values ( ? , ? , ? , ? ) ";
            
            prepStmt = con.prepareStatement(insertStatement);
            prepStmt.setString(1, provisionalId);
            prepStmt.setString(2, eventId);
            prepStmt.setString(3, eventTypeId);
            prepStmt.setString(4, eventLevelId);
            
            int cnt = prepStmt.executeUpdate();
            Debug.print("Record Inserted succefully into insertChampDetails "+cnt);
            
            if(cnt>=1){
                result = true;
            }
            Debug.print("EventCalendarDAO insertChampDetails() result :" + result);
            prepStmt.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in EventCalendarDAO.insertChampDetails():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in EventCalendarDAO.insertChampDetails():" + e.getMessage());
        }
        return result;
    }
    
    public ArrayList selectUseaStaffList(int year, String areaId){
        Debug.print("EventCalendarDAO.selectUseaStaffList():");
        ArrayList proCalDet = new ArrayList();
        
        
        makeConnection();
        try {
            String selectStatement ="Select A.pro_calendar_id, A.organizer_id, A.begin_date, A.end_date, A.event_title," +
                    " B.area_code, B.area_name, C.state_name, A.area_chair_approval_status, A.event_id from tblOEProvisionalCalendar A," +
                    " tblMeeAreaMaster B, tblMeeStateMaster C where A.area_id=B.area_id and A.state_id=C.state_id and A.competition_year= ? and A.area_id = ? order by A.begin_date,A.event_title";
            
            PreparedStatement prepSelect = con.prepareStatement(selectStatement);
            prepSelect.setInt(1,year);
            prepSelect.setString(2,areaId);
            ResultSet rs = prepSelect.executeQuery();
            
            while(rs.next()){
                String proCalId = rs.getString(1);
                String organizerId = rs.getString(2);
                Date beginDate=rs.getDate(3);
                Date endDate=rs.getDate(4);
                String eventTitle=rs.getString(5);
                String areaCode=rs.getString(6);
                String areaName=rs.getString(7);
                String stateName=rs.getString(8);
                String areaChApprovStat=rs.getString(9);
                String eveId=rs.getString(10);
                
        HLCEventCalendarDAO objDAO = new HLCEventCalendarDAO();
        boolean output1=objDAO.eveDetailsExistsInEveTable(eveId);         
                
                HLCCalendarVO objEventDetails = new HLCCalendarVO();
                
                objEventDetails.setCalenderId(proCalId);
                objEventDetails.setOrganizerId(organizerId);
                objEventDetails.setBeginDate(beginDate);
                objEventDetails.setEndDate(endDate);
                objEventDetails.setEventTitle(eventTitle);
                objEventDetails.setAreaCode(areaCode);
                objEventDetails.setAreaName(areaName);
                objEventDetails.setStateName(stateName);
                objEventDetails.setArearChairApproStatus(areaChApprovStat);
                objEventDetails.setEventId(eveId);
                objEventDetails.setResStatus(output1);
                
                proCalDet.add(objEventDetails);
                
            }
            rs.close();
            prepSelect.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            sql.printStackTrace();
        } catch(Exception e){
            releaseConnection();
            e.printStackTrace();
        }
        return proCalDet;
    }
    
    public ArrayList selectEventDetailsList(String eventId, String eventTitle, String areaId){
        Debug.print("EventCalendarDAO.selectEventDetailsList():");
        ArrayList eventList = new ArrayList();
        Debug.print("eventId inEventCalendarDAO.selectEventDetailsList():"+eventId);
        Debug.print("eventTitle in EventCalendarDAO.selectEventDetailsList():"+eventTitle);
        Debug.print("areaId in EventCalendarDAO.selectEventDetailsList():"+areaId);
        
        makeConnection();
        try {
            String selectStatement ="select A.event_id, A.organizer_id, A.event_title, "+
                    "A.event_begin_date, A.event_end_date, B.area_id, B.state_id, C.area_name, D.state_name, A.status_id from "+DBHelper.OE_PROVISIONAL_CALENDAR+" A , "+DBHelper.OE_MAPSTATEZIP+" B ,"+
                    DBHelper.OE_AREA_MASTER+" C , "+DBHelper.OE_STATE_MASTER+" D where A.competition_state = B.state_id and A.competition_zip >= B.zip_code_from "+
                    "and A.competition_zip <= B.zip_code_to and B.area_id = C.area_id and B.state_id = D.state_id ";
            
            if(eventId!=null && eventId.trim().length()!=0){
                selectStatement = selectStatement + " and A.event_id = '" + eventId + "' " ;
            }
            if(eventTitle!=null && eventTitle.trim().length()!=0){
                selectStatement = selectStatement + " and A.event_title = '" + eventTitle + "' " ;
            }
            if(areaId!=null && areaId.trim().length()!=0){
                selectStatement = selectStatement + " and B.area_id = '" + areaId + "' " ;
            }            
            selectStatement = selectStatement+" order by A.event_begin_date";
            Debug.print("Query :"+selectStatement);
            PreparedStatement prepSelect = con.prepareStatement(selectStatement);
            
            ResultSet rs = prepSelect.executeQuery();
            
            while(rs.next()){
                String eveId = rs.getString(1);
                String organizerId = rs.getString(2);
                String eventtitle = rs.getString(3);
                Date beginDate = rs.getDate(4);
                Date endDate = rs.getDate(5);
                String arId = rs.getString(6);
                String stateId = rs.getString(7);
                String areaName=rs.getString(8);
                String stateName=rs.getString(9);
                String status=rs.getString(10);
                                
                HLCCalendarVO calVO = new HLCCalendarVO();
                calVO.setEventId(eveId);
                calVO.setOrganizerId(organizerId);
                calVO.setEventTitle(eventtitle);
                calVO.setBeginDate(beginDate);
                calVO.setEndDate(endDate);
                calVO.setAreaId(arId);
                calVO.setStateId(stateId);
                calVO.setAreaName(areaName);
                calVO.setStateName(stateName);
                calVO.setStatus(status);
                eventList.add(calVO);
                
            }
            rs.close();
            prepSelect.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in EventCalendarDAO.selectEventDetailsList():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in EventCalendarDAO.selectEventDetailsList():" + e.getMessage());
        }
        return eventList;
    }
    
      public ArrayList searchEventDetailsListAdmin(String eventId, String eventTitle, String areaId, int compYear){
        Debug.print("EventCalendarDAO.searchEventDetailsListAdmin():");
        ArrayList eventList = new ArrayList();
        Debug.print("eventId inEventCalendarDAO.selectEventDetailsList():"+eventId);
        Debug.print("eventTitle in EventCalendarDAO.selectEventDetailsList():"+eventTitle);
        Debug.print("areaId in EventCalendarDAO.selectEventDetailsList():"+areaId);
        
        makeConnection();
        try {
            String selectStatement = "select A.event_id, A.organizer_id, A.event_title, A.begin_date, A.end_date, " +
                    "B.state_name, A.usea_approval_status, A.area_id, C.area_name, A.event_type_id, A.event_levels, A.org_approval_status, oe_begin_date, oe_end_date from tblOEProvisionalCalendar A, tblMeeStateMaster B, " +
                    "tblMeeAreaMaster C where A.state_id = B.state_id and A.area_id = C.area_id";
                    
            if(eventId!=null && eventId.trim().length()!=0){
                selectStatement = selectStatement + " and A.event_id = '" + eventId + "' " ;
            }
            if(eventTitle!=null && eventTitle.trim().length()!=0){
                eventTitle = eventTitle.replaceAll("'","''");
                selectStatement = selectStatement + " and A.event_title = '" + eventTitle + "' " ;
            }
            if(areaId!=null && areaId.trim().length()!=0){
                selectStatement = selectStatement + " and A.area_id = '" + areaId + "' " ;
            }
            if(compYear!=0){
                selectStatement = selectStatement + " and year(A.begin_date) = '" + compYear + "' " ;               
            }
            selectStatement = selectStatement+" order by A.begin_date";
            Debug.print("Query :"+selectStatement);
            PreparedStatement prepSelect = con.prepareStatement(selectStatement);
            
            ResultSet rs = prepSelect.executeQuery();
            
            while(rs.next()){
                String eveId = rs.getString(1);
                String organizerId = rs.getString(2);
                String eventtitle = rs.getString(3);
                Date beginDate = rs.getDate(4);
                Date endDate = rs.getDate(5);
                String stateName=rs.getString(6);
                String status=rs.getString(7);
                String areId=rs.getString(8);
                String areaName = rs.getString(9);
                String eventType=rs.getString(10);    
                String eventLevel=rs.getString(11);   
                String eventStatus=rs.getString(12);
                Date oeBegDt=rs.getDate(13);
                Date oeEndDt=rs.getDate(14);
                
                HLCCalendarVO calVO = new HLCCalendarVO();
                calVO.setEventId(eveId);
                calVO.setOrganizerId(organizerId);
                calVO.setEventTitle(eventtitle);
                calVO.setBeginDate(beginDate);
                calVO.setEndDate(endDate);
                calVO.setStateName(stateName);
                calVO.setStatus(status);
                calVO.setAreaId(areId);
                calVO.setAreaName(areaName);
                calVO.setAreaCode(areaName);
                calVO.setEventTypeNames(eventType);
                calVO.setEventLevel(eventLevel);
                calVO.setOrgApprovalStatus(eventStatus);
                calVO.setEntryStrtDate(oeBegDt);
                calVO.setEntryEndDate(oeEndDt);
                
                eventList.add(calVO);
                
            }
            rs.close();
            prepSelect.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in EventCalendarDAO.searchEventDetailsListAdmin():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in EventCalendarDAO.searchEventDetailsListAdmin():" + e.getMessage());
        }
        return eventList;
    }
    
    
    
    public HLCCalendarVO getSingleEventDetailsByEventId(String eventId) throws SQLException {
        HLCCalendarVO calVO = new HLCCalendarVO();
        Debug.print("Inside DAO");
        try {
            makeConnection();
            
            String selStatement = "select A.event_id, A.organizer_id, A.event_title, "+
                    "A.event_begin_date, A.event_end_date, B.area_id, B.state_id, C.area_name, D.state_name, A.status_id from "+DBHelper.OE_EVENT_DETAILS+" A , "+DBHelper.OE_MAPSTATEZIP+" B ,"+
                    DBHelper.OE_AREA_MASTER+" C , "+DBHelper.OE_STATE_MASTER+" D where A.competition_state = B.state_id and A.competition_zip >= B.zip_code_from "+
                    "and A.competition_zip <= B.zip_code_to and B.area_id = C.area_id and B.state_id = D.state_id and A.event_id= ? ";
            Debug.print("Query :"+selStatement);
            PreparedStatement prepStmt = con.prepareStatement(selStatement);
            prepStmt.setString(1,eventId);
            
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                calVO.setEventId(rs.getString(1));
                calVO.setOrganizerId(rs.getString(2));
                calVO.setEventTitle(rs.getString(3));
                calVO.setBeginDate(rs.getDate(4));
                calVO.setEndDate(rs.getDate(5));
                calVO.setAreaId(rs.getString(6));
                calVO.setStateId(rs.getString(7));
                calVO.setAreaName(rs.getString(8));
                calVO.setStateName(rs.getString(9));
                calVO.setStatus(rs.getString(10));
            }
            prepStmt.close();
            releaseConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return calVO;
    }
    //=================================
    public boolean approveEventDetailsByUStaff(String provisionalId, String uStaffStatus, String uStaffComments) throws SQLException {
        Debug.print("Inside EventCalendarDAO.approveEventDetailsByUStaff()");
        boolean result = false;
        int upCnt = 0;
        try {
            makeConnection();
            
            String updateStatement = "update "+DBHelper.OE_PROVISIONAL_CALENDAR+" set usea_approval_status = ?, "+
                    "staff_comments = ? where pro_calendar_id= ?";
            
            PreparedStatement prepStmt = con.prepareStatement(updateStatement);
            prepStmt.setString(1,uStaffStatus);
            prepStmt.setString(2,uStaffComments);
            prepStmt.setString(3,provisionalId);
            
            upCnt = prepStmt.executeUpdate();
            if(upCnt!=0) result = true;
            
            prepStmt.close();
            releaseConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public ArrayList selectApprovedEveCalendarDets(String areaId){
        
    
        Debug.print("EventCalendarDAO.selectApprovedEveCalendarDets():");
        ArrayList proCalDet = new ArrayList();
        
        
        makeConnection();
         java.sql.Date tempDate = new java.sql.Date((new java.util.Date()).getTime());
        String currentDate = String.valueOf(tempDate).substring(5, 7) + "/" + String.valueOf(tempDate).substring(8, 10) + "/" + String.valueOf(tempDate).substring(0, 4);
        try {
            
            String selectStatement ="Select A.pro_calendar_id, A.organizer_id, A.begin_date, A.end_date, A.event_title," +
                    " E.user_id, E.first_name, E.last_name, B.area_code, B.area_name, C.state_name, A.area_chair_approval_status, A.org_approval_status, A.usea_approval_status, A.event_id, A.old_event_id, A.oe_begin_date, A.oe_end_date from "+
                    DBHelper.OE_PROVISIONAL_CALENDAR+" A, " +DBHelper.OE_AREA_MASTER+" B, "+DBHelper.OE_STATE_MASTER+" C, "+DBHelper.OE_MEMBER_DETAILS+" D, "+DBHelper.OE_USER_MASTER+" E " +
                    " where A.area_id=B.area_id and A.state_id=C.state_id and D.user_id=E.user_id and A.organizer_id=D.member_id "+
                    " and A.area_chair_approval_status='Approved' and A.org_approval_status='Approved' and " +
                    " A.usea_approval_status='Approved' and A.end_date>='"+currentDate+"' order by A.begin_date asc";
           
            if(areaId!=null && areaId.trim().length()!=0){
                selectStatement="Select A.pro_calendar_id, A.organizer_id, A.begin_date, A.end_date, A.event_title," +
                    " E.user_id, E.first_name, E.last_name, B.area_code, B.area_name, C.state_name, A.area_chair_approval_status, A.org_approval_status, A.usea_approval_status, A.event_id, A.old_event_id, A.oe_begin_date, A.oe_end_date from "+
                    DBHelper.OE_PROVISIONAL_CALENDAR+" A, " +DBHelper.OE_AREA_MASTER+" B, "+DBHelper.OE_STATE_MASTER+" C, "+DBHelper.OE_MEMBER_DETAILS+" D, "+DBHelper.OE_USER_MASTER+" E " +
                    " where A.area_id=B.area_id and A.state_id=C.state_id and D.user_id=E.user_id and A.organizer_id=D.member_id "+
                    " and A.area_chair_approval_status='Approved' and A.org_approval_status='Approved' and " +
                    " A.usea_approval_status='Approved' and A.end_date>='"+currentDate+"' and B.area_id='" +areaId+"' order by A.begin_date asc";
            }
            
            PreparedStatement prepSelect = con.prepareStatement(selectStatement);
            //prepSelect.setInt(1,year);
            ResultSet rs = prepSelect.executeQuery();
            HLCEventCalendarDAO eveCalDAO = new HLCEventCalendarDAO();
            ArrayList objEveLevels =new ArrayList();
            while(rs.next()){
                String proCalId = rs.getString(1);
                
           if(proCalId!=null && proCalId.trim().length()!=0){
               
              objEveLevels=eveCalDAO.selectEveLevTypDets(proCalId);  
           }     
                
                String organizerId = rs.getString(2);
                Date beginDate=rs.getDate(3);
                Date endDate=rs.getDate(4);
                String eventTitle=rs.getString(5);
                String userId=rs.getString(6);
                String firstName=rs.getString(7);
                String lastName=rs.getString(8);
                String areaCode=rs.getString(9);
                String areaName=rs.getString(10);
                String stateName=rs.getString(11);
                String areaChApprovStat=rs.getString(12);
                String orgApprovStat=rs.getString(13);
                String uStaffApprovStat=rs.getString(14);
                String newEveId=rs.getString(15);
                String oldEveId=rs.getString(16);
                Date entryStartDate=rs.getDate(17);
                Date entryCloseDate=rs.getDate(18);
                Debug.print("entryStartDate in selectApprovedEveCalendarDets: "+entryStartDate);
                Debug.print("entryCloseDate in selectApprovedEveCalendarDets: "+entryCloseDate);
                
                HLCCalendarVO objEventDetails = new HLCCalendarVO();
                
                objEventDetails.setCalenderId(proCalId);
                objEventDetails.setOrganizerId(organizerId);
                objEventDetails.setBeginDate(beginDate);
                objEventDetails.setEndDate(endDate);
                objEventDetails.setEventTitle(eventTitle);
                objEventDetails.setUserId(userId);
                objEventDetails.setOrgFName(firstName);
                objEventDetails.setOrgLName(lastName);
                objEventDetails.setAreaCode(areaCode);
                objEventDetails.setAreaName(areaName);
                objEventDetails.setStateName(stateName);
                objEventDetails.setArearChairApproStatus(areaChApprovStat);
                objEventDetails.setOrgApprovalStatus(orgApprovStat);
                objEventDetails.setApprovalStatus(uStaffApprovStat);
                objEventDetails.setEventId(newEveId);
                objEventDetails.setOldEventId(oldEveId);
                objEventDetails.setEntryStrtDate(entryStartDate);
                objEventDetails.setEntryEndDate(entryCloseDate);
                objEventDetails.setEveLevels(objEveLevels);
                proCalDet.add(objEventDetails);
                
            }
            rs.close();
            prepSelect.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            sql.printStackTrace();
        } catch(Exception e){
            releaseConnection();
            e.printStackTrace();
        }
        return proCalDet;
        
    }
    /**********************************************For Event Entries************************************************************************************/
    public Vector selectAllEventTypes() throws SQLException {
        Vector eveType = new Vector();
        Debug.print("EventCalendarDAO.selectAllEventTypes():");
        try {
            makeConnection();
            String selectStatement = "select event_type_id, event_type_name, active_status, add_date from " + DBHelper.OE_EVENT_TYPE_MASTER;
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            ResultSet rs = prepStmt.executeQuery();
            
            while(rs.next()){
                String id = rs.getString(1);
                String name = rs.getString(2);
                String status = rs.getString(3);
                String add_date = rs.getString(4);
                String[] types= {id,name,status,add_date};
                eveType.add(types);
            }
            prepStmt.close();
            releaseConnection();
        } catch(Exception ede){
            ede.printStackTrace();
            Debug.print("Exception occur while getting all Event Types in EventCalendarDAO.selectAllEventTypes()" + ede);
        }
        return eveType;
    }
    
    public ArrayList selectUserTypes() throws SQLException {
        Debug.print("EventCalendarDAO selectUserTypes()");
        ArrayList userList = new ArrayList();
        makeConnection();
        try{
            String selectStatement = "select user_type_id, user_type_name from " + DBHelper.OE_USER_TYPE_MASTER + " where active_status='true'";
            PreparedStatement prepSelect = con.prepareStatement(selectStatement);
            ResultSet rs = prepSelect.executeQuery();
            while (rs.next()){
                String userTypeId = rs.getString(1);
                String userTypeName = rs.getString(2);
                String tempList[] = {userTypeId, userTypeName};
                userList.add(tempList);
            }
            rs.close();
            prepSelect.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in EventCalendarDAO.selectUserTypes():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in EventCalendarDAO.selectUserTypes():" + e.getMessage());
        }
        return userList;
    }
    
    public ArrayList selectDivisions() throws SQLException {
        Debug.print("EventCalendarDAO selectDivisions()");
        ArrayList divList = new ArrayList();
        makeConnection();
        try{
            String selectStatement = "select event_division_id, event_division_name from " +
                    DBHelper.OE_EVENT_DIVISION_MASTER +" where active_status='true'";
            PreparedStatement prepSelect = con.prepareStatement(selectStatement);
            ResultSet rs = prepSelect.executeQuery();
            while (rs.next()){
                String eventDivId = rs.getString(1);
                String eventDivName = rs.getString(2);
                String tempList[] = {eventDivId, eventDivName};
                divList.add(tempList);
            }
            rs.close();
            prepSelect.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in EventCalendarDAO.selectDivisions():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in EventCalendarDAO.selectDivisions():" + e.getMessage());
        }
        return divList;
    }
    
    public ArrayList selectMembershipTypes(String userTypeId) throws SQLException {
        Debug.print("EventCalendarDAO.selectMembershipTypes()");
        
        String membershipTypeName =null;
        ArrayList membTypeList = new ArrayList();
        String selectStatement=null;
        String membershipTypeId=null;
        try {
            makeConnection();
            selectStatement = "SELECT membership_type_id, membership_type_name FROM  " +
                    DBHelper.OE_MEMBERSHIP_TYPE_MASTER +" WHERE user_type_id = ? and membership_year='2007' and active_status='true'";
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, userTypeId);
            Debug.print("EventCalendarDAO.userTypeId()"+userTypeId);
            rs = prepStmt.executeQuery();
            while (rs.next()) {
                
                membershipTypeId = rs.getString(1);
                membershipTypeName = rs.getString(2);
                Debug.print("EventCalendarDAO.membershipTypeName()"+membershipTypeName);
                Debug.print("EventCalendarDAO.membershipTypeId()"+membershipTypeId);
                String tempList[] = {membershipTypeId, membershipTypeName};
                membTypeList.add(tempList);
            }
            
            if(userTypeId!=null && membershipTypeName==null){
                Debug.print("EventCalendarDAO.userTypeId()"+userTypeId);
                Debug.print("EventCalendarDAO.membershipTypeName()"+membershipTypeName);
                
                selectStatement="SELECT membership_type_id, membership_type_name FROM  " +
                        DBHelper.OE_MEMBERSHIP_TYPE_MASTER + " WHERE user_type_id = ? and active_status='true'";
                
                prepStmt = con.prepareStatement(selectStatement);
                prepStmt.setString(1, userTypeId);
                Debug.print("EventCalendarDAO.userTypeId()"+userTypeId);
                rs = prepStmt.executeQuery();
                while (rs.next()) {
                    membershipTypeId = rs.getString(1);
                    membershipTypeName = rs.getString(2);
                    Debug.print("EventCalendarDAO.membershipTypeName()"+membershipTypeName);
                    Debug.print("EventCalendarDAO.membershipTypeId()"+membershipTypeId);
                
                    String tempList[] = {membershipTypeId, membershipTypeName};
                    membTypeList.add(tempList);
                }
            }
            
            rs.close();
            prepStmt.close();
        } catch (SQLException sqe) {
            throw new EJBException(sqe);
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return membTypeList;
    }
    
    public ArrayList getEventLevelsForArea(String areaId) throws SQLException {
        Debug.print("EventCalendarDAO getEventLevelsForArea()");
        ArrayList eventLevelDetails = new ArrayList();
        PreparedStatement prepSelect = null;
        ResultSet rs= null;
        makeConnection();
        if(areaId!=null && areaId.trim().length()!=0){
        try{
            
            String selectStatement="select A.area_id, A.event_level_id, B.event_level_code from " +
                    " tblMeeMapChampionshipLevel A, tblMeeEventLevelMaster B " +
                    " where A.event_level_id = B.event_level_id and A.area_id = ?";
            
            prepSelect = con.prepareStatement(selectStatement);
            prepSelect.setString(1,areaId);
            rs = prepSelect.executeQuery();
            
            while (rs.next()){
                areaId = rs.getString(1);
                String eventLevelId = rs.getString(2);
                String eventLevelCode = rs.getString(3);
                String [] levelDet = {areaId, eventLevelId, eventLevelCode};
                eventLevelDetails.add(levelDet);
            }
            
            rs.close();
            prepSelect.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in EventCalendarDAO.getEventLevelsForArea():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in EventCalendarDAO.getEventLevelsForArea():" + e.getMessage());
        }
    }
        return eventLevelDetails;
    }
    
    public ArrayList getEventLevelsBasedOnEventId(String eventId) throws SQLException {
        Debug.print("EventCalendarDAO getEventLevelsBasedOnEventId()");
        ArrayList eventLevelDetails = new ArrayList();
        PreparedStatement prepSelect = null;
        ResultSet rs= null;
        makeConnection();
        try{
            
            String selectStatement = "select A.event_type_id, A.event_level_id, B.event_level_code from " +
                    DBHelper.OE_EVENT_LEVEL+" A, "+DBHelper.OE_EVENT_LEVEL_MASTER+" B where A.event_level_id = B.event_level_id and A.event_type_id = ?";
            
            prepSelect = con.prepareStatement(selectStatement);
            prepSelect.setString(1,eventId);
            rs = prepSelect.executeQuery();
            while (rs.next()){
                String eventTypeId = rs.getString(1);
                String eventLevelId = rs.getString(2);
                String eventLevelCode = rs.getString(3);
                String [] levelDet = {eventTypeId, eventLevelId, eventLevelCode};
                eventLevelDetails.add(levelDet);
            }
            rs.close();
            prepSelect.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in EventCalendarDAO.getEventLevelsBasedOnEventId():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in EventCalendarDAO.getEventLevelsBasedOnEventId():" + e.getMessage());
        }
        return eventLevelDetails;
    }
    
 
    public boolean insertValidationDetails(HLCValidationVO objValidation) {
        Debug.print("EventCalendarDAO.insertValidationDetails():");
        
        boolean result = false;
        PreparedStatement prepStmt = null;
        
        makeConnection();
        try {
            String insertStatement = "insert into " + DBHelper.OE_MEE_COMP_QUALIFICATION_DETAILS + " " +
                    " (user_type_id, event_type_id, event_level_id, area_id, membership_type_id, event_division_id, membership_type_name, " +
                    " championship_status, amateur_status, age_min, age_max, event_level_rank, qualification_period, min_rank, min_count, " +
                    " max_rank, max_xc_jump_penalties, max_xc_time_penalties)" +
                    " values ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?, ? , ? , ? , ? , ?) ";
            
            
            prepStmt = con.prepareStatement(insertStatement);
            prepStmt.setString(1, objValidation.getUserTypeId());
            prepStmt.setString(2, objValidation.getEventTypeId());
            prepStmt.setString(3, objValidation.getEventLevelId());
            prepStmt.setString(4, objValidation.getAreaId());
            prepStmt.setString(5, objValidation.getMemTypId());
            prepStmt.setString(6, objValidation.getEventDivisionId());
            prepStmt.setString(7, objValidation.getMembTypeName());
            prepStmt.setBoolean(8, objValidation.getChampStatus());
            prepStmt.setBoolean(9, objValidation.getAmateurStatus());
            prepStmt.setInt(10, objValidation.getMinAge());
            prepStmt.setInt(11, objValidation.getMaxAge());
            prepStmt.setString(12, objValidation.getEveLevelRank());
            prepStmt.setString(13, objValidation.getQualicPeriod());
            prepStmt.setString(14, objValidation.getMinRank());
            prepStmt.setInt(15, objValidation.getMinCount());
            prepStmt.setString(16, objValidation.getMaxRank());
            prepStmt.setString(17, objValidation.getMaxXcJmpenal());
            prepStmt.setString(18, objValidation.getMaxXcTimepenal());
            
            int cnt = prepStmt.executeUpdate();
            Debug.print("Record Inserted succefully into insertValidationDetails "+cnt);
            
            if(cnt>=1){
                result = true;
            }
            Debug.print("EventCalendarDAO insertValidationDetails() Status :" + result);
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
    
    public ArrayList getValidationDetails(String eventTypId, String usrTypId, String divisionId, String areaId, String chmpStatus) throws SQLException {
        Debug.print("EventCalendarDAO.getValidationDetails():");
        ArrayList valList = new ArrayList();
        String selStatement=null;
        PreparedStatement prepStmt=null;
        ResultSet rs=null;
        
        Debug.print("eventTypId in getValidationDetails>>>>>>>>>>>>> "+eventTypId);
        Debug.print("usrTypId in getValidationDetails>>>>>>>>>>>>> "+usrTypId);
        Debug.print("divisionId in getValidationDetails>>>>>>>>>>>>> "+divisionId);
        Debug.print("areaId in getValidationDetails>>>>>>>>>>>>> "+areaId);
        Debug.print("chmpStatus in getValidationDetails>>>>>>>>>>>>> "+chmpStatus);
        
        try {
            makeConnection();
            
  if(eventTypId!=null && eventTypId.trim().length()!=0 && usrTypId!=null && usrTypId.trim().length()!=0){          
          if(chmpStatus.equalsIgnoreCase("false") && (!(divisionId!=null && divisionId.trim().length()!=0))
              && (!(areaId!=null && areaId.trim().length()!=0))){  

            selStatement = "select A.qualification_id, A.user_type_id, A.event_type_id, A.event_level_id, A.area_id," +
                    " B.event_level_code, A.event_division_id, " +
                    " A.membership_type_name, A.championship_status, A.amateur_status," +
                    " A.age_min, A.age_max, A.event_level_rank, A.qualification_period,A.min_rank," +
                    " A.min_count,A.max_rank, A.max_xc_jump_penalties, A.max_xc_time_penalties " +
                    " from " + DBHelper.OE_MEE_COMP_QUALIFICATION_DETAILS + " A, " + DBHelper.OE_EVENT_LEVEL_MASTER+ " B " +
                    " where A.event_level_id = B.event_level_id " +
                    " and A.event_type_id= ? and A.user_type_id= ? and A.championship_status= ? and A.event_division_id is NULL" +
                    " and A.area_id is NULL";
            
            Debug.print("Inside Condition1 in getValidationDetails>>>>>>>>> selStatement1:"+ selStatement);
                        
            prepStmt = con.prepareStatement(selStatement);
            prepStmt.setString(1,eventTypId);
            prepStmt.setString(2,usrTypId);
            prepStmt.setString(3,chmpStatus);
            rs = prepStmt.executeQuery();
          }            
          if(areaId!=null && areaId.trim().length()!=0 && chmpStatus.equalsIgnoreCase("true") && (!(divisionId!=null && divisionId.trim().length()!=0))
           || areaId!=null && areaId.trim().length()!=0 && chmpStatus.equalsIgnoreCase("false") && (!(divisionId!=null && divisionId.trim().length()!=0))){
                selStatement = "select A.qualification_id, A.user_type_id, A.event_type_id, A.event_level_id, A.area_id," +
                        " B.event_level_code, A.event_division_id, " +
                        " A.membership_type_name, A.championship_status,A.amateur_status," +
                        " A.age_min, A.age_max, A.event_level_rank, A.qualification_period,A.min_rank," +
                        " A.min_count, A.max_rank, A.max_xc_jump_penalties, A.max_xc_time_penalties " +
                        " from " + DBHelper.OE_MEE_COMP_QUALIFICATION_DETAILS + " A, " + DBHelper.OE_EVENT_LEVEL_MASTER+ " B " +
                        " where A.event_level_id = B.event_level_id " +
                        " and A.event_type_id= ? and A.user_type_id= ? and A.area_id = ? and A.championship_status= ? and A.event_division_id is NULL";
                
            Debug.print("Inside Condition 2 in getValidationDetails>>>>>>>>>selStatement2:"+ selStatement);    
                
                prepStmt = con.prepareStatement(selStatement);
                prepStmt.setString(1,eventTypId);
                prepStmt.setString(2,usrTypId);
                prepStmt.setString(3,areaId);
                prepStmt.setString(4,chmpStatus);              
                rs = prepStmt.executeQuery();              
            }
            
             if((divisionId!=null && divisionId.trim().length()!=0 && areaId!=null && areaId.trim().length()!=0 && chmpStatus.equalsIgnoreCase("true")) ||
                   (divisionId!=null && divisionId.trim().length()!=0 && areaId!=null && areaId.trim().length()!=0 && chmpStatus.equalsIgnoreCase("false"))){
              
                selStatement = "select A.qualification_id, A.user_type_id, A.event_type_id, A.event_level_id, A.area_id," +
                        " B.event_level_code, A.event_division_id, " +
                        " A.membership_type_name, A.championship_status, A.amateur_status," +
                        " A.age_min, A.age_max, A.event_level_rank, A.qualification_period, A.min_rank," +
                        " A.min_count, A.max_rank, A.max_xc_jump_penalties, A.max_xc_time_penalties " +
                        " from " + DBHelper.OE_MEE_COMP_QUALIFICATION_DETAILS + " A, " + DBHelper.OE_EVENT_LEVEL_MASTER+ " B " +
                        " where A.event_level_id = B.event_level_id " +
                        " and A.event_type_id= ? and A.user_type_id= ? and A.event_division_id = ? and A.area_id = ? and A.championship_status= ? ";
               
               Debug.print("Inside Condition 3 in getValidationDetails>>>>>>>>>selStatement3:"+ selStatement);     
                prepStmt = con.prepareStatement(selStatement);
                 prepStmt.setString(1,eventTypId);
                prepStmt.setString(2,usrTypId);
                prepStmt.setString(3,divisionId);
                prepStmt.setString(4,areaId);
                prepStmt.setString(5,chmpStatus);
                rs = prepStmt.executeQuery();              
            }  
              if(divisionId!=null && divisionId.trim().length()!=0 && chmpStatus.equalsIgnoreCase("false") && (!(areaId!=null && areaId.trim().length()!=0))){
                selStatement = "select A.qualification_id, A.user_type_id, A.event_type_id, A.event_level_id, A.area_id," +
                        " B.event_level_code, A.event_division_id, " +
                        " A.membership_type_name, A.championship_status, A.amateur_status," +
                        " A.age_min, A.age_max, A.event_level_rank, A.qualification_period, A.min_rank," +
                        " A.min_count, A.max_rank, A.max_xc_jump_penalties, A.max_xc_time_penalties " +
                        " from " + DBHelper.OE_MEE_COMP_QUALIFICATION_DETAILS + " A, " + DBHelper.OE_EVENT_LEVEL_MASTER+ " B " +
                        " where A.event_level_id = B.event_level_id " +
                        " and A.event_type_id= ? and A.user_type_id= ? and A.event_division_id = ? and A.championship_status= ? and A.area_id is NULL ";
                
              Debug.print("Inside Condition 4 in getValidationDetails>>>>>>>>>selStatement4:"+ selStatement);     
                prepStmt = con.prepareStatement(selStatement);
                prepStmt.setString(1,eventTypId);
                prepStmt.setString(2,usrTypId);                
                prepStmt.setString(3,divisionId);
                prepStmt.setString(4,chmpStatus);
                rs = prepStmt.executeQuery();              
    }  
       }     
             while (rs.next()){
                HLCValidationVO valVO = new HLCValidationVO();
                valVO.setQualificationId(rs.getString(1));
                valVO.setUserTypeId(rs.getString(2));
                valVO.setEventTypeId(rs.getString(3));
                valVO.setEventLevelId(rs.getString(4));
                valVO.setAreaId(rs.getString(5));
                valVO.setEventLevelNames(rs.getString(6));
                valVO.setEventDivisionId(rs.getString(7));
                valVO.setMembTypeName(rs.getString(8));
                valVO.setChampStatus(rs.getBoolean(9));
                valVO.setAmateurStatus(rs.getBoolean(10));
                valVO.setMinAge(rs.getInt(11));
                valVO.setMaxAge(rs.getInt(12));
                valVO.setEveLevelRank(rs.getString(13));
                valVO.setQualicPeriod(rs.getString(14));
                valVO.setMinRank(rs.getString(15));
                valVO.setMinCount(rs.getInt(16));
                valVO.setMaxRank(rs.getString(17));
                valVO.setMaxXcJmpenal(rs.getString(18));
                valVO.setMaxXcTimepenal(rs.getString(19));
                valList.add(valVO);
            }
            
            prepStmt.close();
            releaseConnection();
        }catch (Exception e) {
            releaseConnection();
            e.printStackTrace();
        }
        return valList;
    }
    
    public boolean updateValidationDetails(HLCValidationVO validVO) {
        Debug.print("EventCalendarDAO.updateValidationDetails():");
        boolean deleteResult=false;
        boolean result = false;
        String qualificationId  = validVO.getQualificationId();
        if(qualificationId!=null && qualificationId.trim().length()!=0){
            try{
                deleteResult = deleteValidDetails(qualificationId);
                Debug.print("EventCalendarDAO.deleteValidDetails() result: " +  deleteResult);
            } catch(Exception e){
                Debug.print("General Exception in EventCalendarDAO.deleteValidDetails():" + e.getMessage());
            }
        }
        if(deleteResult==true){
            
            PreparedStatement prepStmt = null;
            
            makeConnection();
            try {
                String insertStatement = "insert into " + DBHelper.OE_MEE_COMP_QUALIFICATION_DETAILS + " " +
                    " (user_type_id, event_type_id, event_level_id, area_id, membership_type_id, event_division_id, membership_type_name, " +
                    " championship_status, amateur_status, age_min, age_max, event_level_rank, qualification_period, min_rank, min_count, " +
                    " max_rank, max_xc_jump_penalties, max_xc_time_penalties)" +
                    " values ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?, ? , ? , ? , ? , ?) ";
                
               prepStmt = con.prepareStatement(insertStatement);
            prepStmt.setString(1, validVO.getUserTypeId());
            prepStmt.setString(2, validVO.getEventTypeId());
            prepStmt.setString(3, validVO.getEventLevelId());
            prepStmt.setString(4, validVO.getAreaId());
            prepStmt.setString(5, validVO.getMemTypId());
            prepStmt.setString(6, validVO.getEventDivisionId());
            prepStmt.setString(7, validVO.getMembTypeName());
            prepStmt.setBoolean(8, validVO.getChampStatus());
            prepStmt.setBoolean(9, validVO.getAmateurStatus());
            prepStmt.setInt(10, validVO.getMinAge());
            prepStmt.setInt(11, validVO.getMaxAge());
            prepStmt.setString(12, validVO.getEveLevelRank());
            prepStmt.setString(13, validVO.getQualicPeriod());
            prepStmt.setString(14, validVO.getMinRank());
            prepStmt.setInt(15, validVO.getMinCount());
            prepStmt.setString(16, validVO.getMaxRank());
            prepStmt.setString(17, validVO.getMaxXcJmpenal());
            prepStmt.setString(18, validVO.getMaxXcTimepenal());
                
                int cnt = prepStmt.executeUpdate();
                Debug.print("Record Inserted succefully into updateValidationDetails(): "+cnt);
                
                if(cnt>=1){
                    result = true;
                }
                Debug.print("EventCalendarDAO updateValidationDetails(): Status :" + result);
                prepStmt.close();
                releaseConnection();
            } catch(SQLException sql){
                releaseConnection();
                Debug.print("SQL Exception in EventCalendarDAO.updateValidationDetails():" + sql.getMessage());
            } catch(Exception e){
                releaseConnection();
                Debug.print("General Exception  in EventCalendarDAO.updateValidationDetails():" + e.getMessage());
            }
            
        }
        return result;
    }
    
    public boolean deleteValidDetails(String qualificationId) throws SQLException {
        Debug.print("EventCalendarDAO deleteValidDetails():" + qualificationId);
        boolean result = false;
        try {
            makeConnection();
            
            String selectStatement = "delete FROM " + DBHelper.OE_MEE_COMP_QUALIFICATION_DETAILS + " WHERE qualification_id = ? ";
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,qualificationId);
            
            int cnt = prepStmt.executeUpdate();
            if (cnt >= 1) {
                result = true;
            }
            Debug.print("EventCalendarDAO deleteValidDetails() result: " + result);
            prepStmt.close();
        } catch (SQLException sqe) {
            releaseConnection();
            Debug.print("Error while calling deleteValidDetails : "+sqe.getMessage());
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return result;
    }
    
    public ArrayList getValidationDetsForEdit(String eventTypId, String usrTypId, String divisionId, String areaId, String chmpStatus) throws SQLException {
        Debug.print("EventCalendarDAO.getValidationDetsForEdit():");
        ArrayList valList = new ArrayList();
        String selStatement=null;
        String selStatement5=null;
        PreparedStatement prepStmt=null;
         ResultSet rs =null;
         
        Debug.print("eventTypId in getValidationDetsForEdit>>>>>>>>>>>>> "+eventTypId);
        Debug.print("usrTypId in getValidationDetsForEdit>>>>>>>>>>>>> "+usrTypId);
        Debug.print("divisionId in getValidationDetsForEdit>>>>>>>>>>>>> "+divisionId);
        Debug.print("areaId in getValidationDetsForEdit>>>>>>>>>>>>> "+areaId);
        Debug.print("chmpStatus in getValidationDetsForEdit>>>>>>>>>>>>> "+chmpStatus);
        
        try {
           makeConnection();
     
  if(eventTypId!=null && eventTypId.trim().length()!=0 && usrTypId!=null && usrTypId.trim().length()!=0){ 
                
    if(chmpStatus.equalsIgnoreCase("false") && (!(divisionId!=null && divisionId.trim().length()!=0)
          && (!(areaId!=null && areaId.trim().length()!=0)))){           
            
             selStatement = "select A.qualification_id, A.user_type_id, A.event_type_id, A.event_level_id, " +
                     "A.area_id, A.membership_type_id, A.event_division_id, A.membership_type_name, " +
                     "A.championship_status, A.amateur_status, A.age_min, A.age_max, " +
                     "A.event_level_rank, A.qualification_period, A.min_rank, A.min_count,A.max_rank, " +
                     "A.max_xc_jump_penalties, A.max_xc_time_penalties, B.event_level_code "+
                     "from tblMeeMapEventLevel C left join tblMeeCompQualificationDetails A " +
                     "on A.event_type_id = C.event_type_id and A.event_level_id = C.event_level_id " +
                     "inner join tblMeeEventLevelMaster B " +
                     "on B.event_level_id = A.event_level_id "+
                     "where A.event_type_id = ? " +
                     "and A.user_type_id= ? and " +
                     "A.championship_status = ? " +
                     "and A.event_division_id is NULL and A.area_id is NULL";
            
             Debug.print("selStatement in Condition1.getValidationDetsForEdit>>>>>>>>>>>>> "+selStatement);
            prepStmt = con.prepareStatement(selStatement);
            prepStmt.setString(1,eventTypId);
            prepStmt.setString(2,usrTypId);
            prepStmt.setString(3,chmpStatus);           
            rs = prepStmt.executeQuery();
    }            
        if((divisionId!=null && divisionId.trim().length()!=0 && areaId!=null && areaId.trim().length()!=0 && chmpStatus.equalsIgnoreCase("true")) ||
         (divisionId!=null && divisionId.trim().length()!=0 && areaId!=null && areaId.trim().length()!=0 && chmpStatus.equalsIgnoreCase("false"))){
        
               selStatement = "select A.qualification_id, A.user_type_id, A.event_type_id, A.event_level_id, " +
                     "A.area_id, A.membership_type_id, A.event_division_id, A.membership_type_name, " +
                     "A.championship_status, A.amateur_status, A.age_min, A.age_max, " +
                     "A.event_level_rank, A.qualification_period, A.min_rank, A.min_count,A.max_rank, " +
                     "A.max_xc_jump_penalties, A.max_xc_time_penalties, B.event_level_code "+
                     "from tblMeeMapEventLevel C left join tblMeeCompQualificationDetails A " +
                     "on A.event_type_id = C.event_type_id and A.event_level_id = C.event_level_id " +
                     "inner join tblMeeEventLevelMaster B " +
                     "on B.event_level_id = A.event_level_id "+
                    " where A.event_type_id = ? and A.user_type_id= ? and A.event_division_id = ? and A.area_id = ? and A.championship_status = ?";
              
               Debug.print("selStatement in Condition2.getValidationDetsForEdit>>>>>>>>>>>>> "+selStatement);
            prepStmt = con.prepareStatement(selStatement);
            prepStmt.setString(1,eventTypId);
            prepStmt.setString(2,usrTypId);
            prepStmt.setString(3,divisionId);
            prepStmt.setString(4,areaId);
            prepStmt.setString(5,chmpStatus);
            rs = prepStmt.executeQuery();           
          }
             
    if(divisionId!=null && divisionId!=null && divisionId.trim().length()!=0 && chmpStatus.equalsIgnoreCase("false") && (!(areaId!=null && areaId.trim().length()!=0))){              
               selStatement = "select A.qualification_id, A.user_type_id, A.event_type_id, A.event_level_id, " +
                     "A.area_id, A.membership_type_id, A.event_division_id, A.membership_type_name, " +
                     "A.championship_status, A.amateur_status, A.age_min, A.age_max, " +
                     "A.event_level_rank, A.qualification_period, A.min_rank, A.min_count,A.max_rank, " +
                     "A.max_xc_jump_penalties, A.max_xc_time_penalties, B.event_level_code "+
                     "from tblMeeMapEventLevel C left join tblMeeCompQualificationDetails A " +
                     "on A.event_type_id = C.event_type_id and A.event_level_id = C.event_level_id " +
                     "inner join tblMeeEventLevelMaster B " +
                     "on B.event_level_id = A.event_level_id "+
                    " where A.event_type_id = ? and A.user_type_id= ? and A.event_division_id = ? and A.championship_status = ?" +
                    " and A.area_id is NULL";
            
            Debug.print("selStatement in Condition3.getValidationDetsForEdit>>>>>>>>>>>>> "+selStatement);
            prepStmt = con.prepareStatement(selStatement);
            prepStmt.setString(1,eventTypId);
            prepStmt.setString(2,usrTypId);
            prepStmt.setString(3,divisionId);           
            prepStmt.setString(4,chmpStatus);
            rs = prepStmt.executeQuery();           
          }     
             
        if(areaId!=null && areaId.trim().length()!=0 && chmpStatus.equalsIgnoreCase("true") && (!(divisionId!=null && divisionId.trim().length()!=0))
        || areaId!=null && areaId.trim().length()!=0 && chmpStatus.equalsIgnoreCase("false") && (!(divisionId!=null && divisionId.trim().length()!=0))){              
               selStatement = "select A.qualification_id, A.user_type_id, A.event_type_id, A.event_level_id, " +
                     "A.area_id, A.membership_type_id, A.event_division_id, A.membership_type_name, " +
                     "A.championship_status, A.amateur_status, A.age_min, A.age_max, " +
                     "A.event_level_rank, A.qualification_period, A.min_rank, A.min_count,A.max_rank, " +
                     "A.max_xc_jump_penalties, A.max_xc_time_penalties, B.event_level_code "+
                     "from tblMeeMapEventLevel C left join tblMeeCompQualificationDetails A " +
                     "on A.event_type_id = C.event_type_id and A.event_level_id = C.event_level_id " +
                     "inner join tblMeeEventLevelMaster B " +
                     "on B.event_level_id = A.event_level_id "+
                    " where A.event_type_id = ? and A.user_type_id= ? and " +
                    " A.area_id = ? and A.championship_status = ? and A.event_division_id is NULL";
           
           Debug.print("selStatement in Condition4.getValidationDetsForEdit>>>>>>>>>>>>> "+selStatement);   
           prepStmt = con.prepareStatement(selStatement);
           prepStmt.setString(1,eventTypId);
           prepStmt.setString(2,usrTypId);
           prepStmt.setString(3,areaId);
           prepStmt.setString(4,chmpStatus);          
           rs = prepStmt.executeQuery();           
          } 
        }
          
           while (rs.next()){
                HLCValidationVO valVO = new HLCValidationVO();
                valVO.setQualificationId(rs.getString(1));
                valVO.setUserTypeId(rs.getString(2));
                valVO.setEventTypeId(rs.getString(3));
                valVO.setEventLevelId(rs.getString(4));                   
                valVO.setAreaId(rs.getString(5));
                valVO.setMemTypId(rs.getString(6));
                valVO.setEventDivisionId(rs.getString(7));
                //valVO.setDivisionName(rs.getString(7));
                valVO.setMembTypeName(rs.getString(8));
                valVO.setChampStatus(rs.getBoolean(9));
                valVO.setAmateurStatus(rs.getBoolean(10));
                valVO.setMinAge(rs.getInt(11));
                valVO.setMaxAge(rs.getInt(12));
                valVO.setEveLevelRank(rs.getString(13));
                valVO.setQualicPeriod(rs.getString(14));
                valVO.setMinRank(rs.getString(15));
                valVO.setMinCount(rs.getInt(16));
                valVO.setMaxRank(rs.getString(17));
                valVO.setMaxXcJmpenal(rs.getString(18));
                valVO.setMaxXcTimepenal(rs.getString(19));  
                valVO.setEventLevelNames(rs.getString(20));   
                valList.add(valVO);
            }   
                        
            prepStmt.close();
            releaseConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return valList;
    }
    
   
    
    
   /* public boolean insertAreaEventLevelMapping(String areaId, String evtLevelId){
        Debug.print("EventCalendarDAO.insertAreaEventLevelMapping():");
        PreparedStatement prepStmt = null;
        boolean result=false;
        
        makeConnection();
        try {
            String insertStatement = "insert into tblMeeMapChampionshipLevel (area_id , event_level_id) " +
                    " values( ? , ? )";
            prepStmt = con.prepareStatement(insertStatement);
            prepStmt.setString(1, areaId);
            prepStmt.setString(2, evtLevelId);
            
            int cnt = prepStmt.executeUpdate();
            Debug.print("Record Inserted succefully into insertAreaEventLevelMapping "+cnt);
            
            if(cnt>=1){
                result = true;
            }
            prepStmt.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in EventCalendarDAO.insertAreaEventLevelMapping():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in EventCalendarDAO.insertAreaEventLevelMapping():" + e.getMessage());
        }
        
        return result;
        
    }
    */
    public boolean getMapDetailsExists(String areaId, String eventLevelId) throws SQLException {
        Debug.print("EventCalendarDAO getMapDetailsExists():" + areaId);
        Debug.print("EventCalendarDAO getMapDetailsExists():" + eventLevelId);
        boolean result = false;
        try {
            makeConnection();
            
            String selectStatement = "SELECT area_id, event_level_id FROM " + DBHelper.OE_MEE_MAP_CHAMPIONSHIPLEVEL + " " +
                    " WHERE area_id=? and event_level_id = ?";
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,areaId);
            prepStmt.setString(2,eventLevelId);
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                result = true;
            }
            rs.close();
            Debug.print("EventCalendarDAO getMapDetailsExists() result: " + result);
            prepStmt.close();
        } catch (SQLException sqe) {
            releaseConnection();
            Debug.print("Error while calling getMapDetailsExists : "+sqe.getMessage());
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return result;
    }
    
    
    public boolean ValidationDetailsExist(String eventTypId, String usrTypId, String divisionId, String areaId, String chmpStatus) throws SQLException {
        Debug.print("EventCalendarDAO ValidationDetailsExist():" + eventTypId);
        Debug.print("EventCalendarDAO ValidationDetailsExist():" + usrTypId);
        
        boolean result = false;
        String selectStatement=null;
        
        try {
            makeConnection();
            
       if(eventTypId!=null && eventTypId.trim().length()!=0 && usrTypId!=null && usrTypId.trim().length()!=0){
                
       if(chmpStatus.equalsIgnoreCase("false") && (!(divisionId!=null && divisionId.trim().length()!=0)
          && (!(areaId!=null && areaId.trim().length()!=0)))){  
           
             selectStatement = "SELECT event_type_id, user_type_id, championship_status, event_division_id, area_id FROM " + DBHelper.OE_MEE_COMP_QUALIFICATION_DETAILS + " " +
                    " WHERE event_type_id=? and user_type_id = ? and championship_status = ? and event_division_id is NULL and area_id is NULL";
            
            Debug.print("selectStatement in ValidationDetailsExist():" + selectStatement);            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,eventTypId);
            prepStmt.setString(2,usrTypId);
            prepStmt.setString(3,chmpStatus);
            rs = prepStmt.executeQuery();
            
              if (rs.next()) {
                result = true;
            }
       } 
     if(areaId!=null && areaId.trim().length()!=0 && chmpStatus.equalsIgnoreCase("true") && (!(divisionId!=null && divisionId.trim().length()!=0))
     || areaId!=null && areaId.trim().length()!=0 && chmpStatus.equalsIgnoreCase("false") && (!(divisionId!=null && divisionId.trim().length()!=0))){              
                
                 selectStatement = "SELECT event_type_id, user_type_id, area_id, championship_status, event_division_id FROM " + DBHelper.OE_MEE_COMP_QUALIFICATION_DETAILS + " " +
                    " WHERE event_type_id=? and user_type_id = ? and area_id = ? and championship_status = ? and event_division_id is NULL";
            
            Debug.print("selectStatement in ValidationDetailsExist():" + selectStatement);            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,eventTypId);
            prepStmt.setString(2,usrTypId);
            prepStmt.setString(3,areaId);            
            prepStmt.setString(4,chmpStatus);
            rs = prepStmt.executeQuery();           
             if (rs.next()) {
                result = true;
            }                 
      }                    
   if((divisionId!=null && divisionId.trim().length()!=0 && areaId!=null && areaId.trim().length()!=0 && chmpStatus.equalsIgnoreCase("true")) ||
      (divisionId!=null && divisionId.trim().length()!=0 && areaId!=null && areaId.trim().length()!=0 && chmpStatus.equalsIgnoreCase("false"))){  
                
                selectStatement = "SELECT event_type_id, user_type_id, event_division_id, area_id, championship_status FROM " + DBHelper.OE_MEE_COMP_QUALIFICATION_DETAILS + " " +
                                  " WHERE event_type_id=? and user_type_id = ? and event_division_id = ? and area_id = ? and championship_status = ?";
            
            Debug.print("selectStatement in ValidationDetailsExist():" + selectStatement);            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,eventTypId);
            prepStmt.setString(2,usrTypId);
            prepStmt.setString(3,divisionId);
            prepStmt.setString(4,areaId);
            prepStmt.setString(5,chmpStatus);
            rs = prepStmt.executeQuery();           
             if (rs.next()) {
                result = true;
            }                 
      }          
            
   if(divisionId!=null && divisionId!=null && divisionId.trim().length()!=0 && chmpStatus.equalsIgnoreCase("false") && (!(areaId!=null && areaId.trim().length()!=0))){          
                
           selectStatement = "SELECT event_type_id, user_type_id, event_division_id, championship_status, area_id FROM " + DBHelper.OE_MEE_COMP_QUALIFICATION_DETAILS + " " +
                     " WHERE event_type_id=? and user_type_id = ? and event_division_id = ? and championship_status = ? and area_id is NULL";
            
            Debug.print("selectStatement in ValidationDetailsExist():" + selectStatement);            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,eventTypId);
            prepStmt.setString(2,usrTypId);
            prepStmt.setString(3,divisionId);
            prepStmt.setString(4,chmpStatus);
            rs = prepStmt.executeQuery();           
             if (rs.next()) {
                result = true;
            }                 
      }                    
       }     
       rs.close();
            Debug.print("EventCalendarDAO ValidationDetailsExist() result: " + result);
            prepStmt.close();
        } catch (SQLException sqe) {
            releaseConnection();
            sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return result;
    }
    
  public ArrayList getEventLevelsBasedOnAreaId(String areaId) throws SQLException {
        Debug.print("EventCalendarDAO getEventLevelsBasedOnAreaId()");
        ArrayList eventLevelDetails = new ArrayList();
        PreparedStatement prepSelect = null;
        ResultSet rs= null;
        makeConnection();
        try{
            
            String selectStatement = "select A.area_id, A.event_level_id, B.event_level_code " +
                    " from " + DBHelper.OE_MEE_MAP_CHAMPIONSHIPLEVEL + " A, " + DBHelper.OE_EVENT_LEVEL_MASTER+ " B where A.event_level_id = B.event_level_id and A.area_id = ?";
            
            prepSelect = con.prepareStatement(selectStatement);
            prepSelect.setString(1,areaId);
            rs = prepSelect.executeQuery();
            while (rs.next()){
                areaId = rs.getString(1);
                String eventLevelId = rs.getString(2);
                String eventLevelCode = rs.getString(3);
                String [] levelDet = {areaId, eventLevelId, eventLevelCode};
                eventLevelDetails.add(levelDet);
            }
            rs.close();
            prepSelect.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in EventCalendarDAO.getEventLevelsBasedOnAreaId():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in EventCalendarDAO.getEventLevelsBasedOnAreaId():" + e.getMessage());
        }
        return eventLevelDetails;
    }  
    
  public boolean mappingAreaWithEventLevel(String areaId, ArrayList eventLevelList){
        Debug.print("EventCalendarDAO.mappingAreaWithEventLevel():");
        PreparedStatement prepStmt = null;
        boolean deleteResult=false;
        boolean result = false;       
        if(areaId!=null && areaId.trim().length()!=0){
            try{
                deleteResult = deleteTableRow("area_id", areaId, "tblMeeMapChampionshipLevel");               
                Debug.print("EventCalendarDAO.deleteTableRow() result: " +  deleteResult);
            } catch(Exception e){
                Debug.print("General Exception in EventCalendarDAO.deleteTableRow():" + e.getMessage());
            }
        }       
        if(deleteResult==true){       
        try {              
           if(areaId!=null && areaId.trim().length()!=0 ){
                 Iterator itEvtLevelList = eventLevelList.iterator();
                while(itEvtLevelList.hasNext()){
                    String evtLevelId = (String)itEvtLevelList.next();
                    if(evtLevelId!=null && evtLevelId.trim().length()!=0){
                       result= insertAreaEventLevelMapping(areaId, evtLevelId);
                    }
                }
            }                
        } catch(Exception e){
                Debug.print("General Exception in EventCalendarDAO.mappingAreaWithEventLevel():" + e.getMessage());
            } 
        }        
        else{
           try{      
             if(areaId!=null && areaId.trim().length()!=0 ){
                 Iterator itEvtLevelList = eventLevelList.iterator();
                while(itEvtLevelList.hasNext()){
                    String evtLevelId = (String)itEvtLevelList.next();
                    if(evtLevelId!=null && evtLevelId.trim().length()!=0){
                       result= insertAreaEventLevelMapping(areaId, evtLevelId);
                    }
                }
            } 
           }
           catch(Exception e){
                Debug.print("General Exception in EventCalendarDAO.mappingAreaWithEventLevel():" + e.getMessage());
            } 
        }             
        return result;        
    }
    
   public boolean deleteTableRow(String fieldName, String fieldValue, String tableName) {
        Debug.print("EventCalendarDAO.deleteTableRow()");
        boolean result = false;
        makeConnection();
        try{
            String deleteStatement = "delete from " + tableName + "  where " + fieldName  + " = ? ";
            Debug.print("EventCalendarDAO.deleteTableRow():" + "\n" + deleteStatement + ":" +  fieldValue);
            PreparedStatement prepStmt = con.prepareStatement(deleteStatement);
            prepStmt.setString(1, fieldValue);
            int i = prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection();
            if (i>=1) {
                result = true;
            }
            else{
                result =false;
            }
        }
        catch(SQLException sql){
           releaseConnection();
           Debug.print("SQL Exception in EventCalendarDAO.deleteTableRow:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in EventCalendarDAO.deleteTableRow:" + e.getMessage());
        }        
        return result;
    }
 
    public boolean insertAreaEventLevelMapping(String areaId, String evtLevelId){
        Debug.print("EventCalendarDAO.insertAreaEventLevelMapping():");
        PreparedStatement prepStmt = null;
        boolean result=false;
        
        makeConnection();
        try {
            String insertStatement = "insert into tblMeeMapChampionshipLevel (area_id , event_level_id) " +
                    " values( ? , ? )";
            prepStmt = con.prepareStatement(insertStatement);
            prepStmt.setString(1, areaId);
            prepStmt.setString(2, evtLevelId);
            
            int cnt = prepStmt.executeUpdate();
            Debug.print("Record Inserted succefully into insertAreaEventLevelMapping "+cnt);
            
            if(cnt>=1){
                result = true;
            }
            prepStmt.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in EventCalendarDAO.insertAreaEventLevelMapping():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in EventCalendarDAO.insertAreaEventLevelMapping():" + e.getMessage());
        }       
        return result;
        
    }
    public ArrayList getProvCalIdBasedOnYear(String year) throws SQLException {
        Debug.print("EventCalendarDAO getProvCalIdBasedOnYear()");
        ArrayList eventLevelDetails = new ArrayList();
        PreparedStatement prepSelect = null;
        ResultSet rs= null;
        makeConnection();
        try{
            
            String selectStatement = "select pro_calendar_id from " + DBHelper.OE_PROVISIONAL_CALENDAR + " where competition_year = ?";
            
            prepSelect = con.prepareStatement(selectStatement);
            prepSelect.setString(1,year);
            rs = prepSelect.executeQuery();
            while (rs.next()){
                String proCalId = rs.getString(1);
                String [] levelDet = {proCalId};
                eventLevelDetails.add(levelDet);
            }
            rs.close();
            prepSelect.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in EventCalendarDAO.getProvCalIdBasedOnYear():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in EventCalendarDAO.getProvCalIdBasedOnYear():" + e.getMessage());
        }
        return eventLevelDetails;
    }
    
     public boolean deleteCalendarDetails(String calId) throws SQLException {
        Debug.print("EventCalendarDAO deleteCalendarDetails() calId:" + calId);
        boolean result = false;
        boolean deleteResult=false;
        try {
            makeConnection();
            String selectStatement = "delete FROM " + DBHelper.OE_CHAMP_DETAILS + " WHERE pro_calendar_id = ? ";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,calId);
            
            int cnt = prepStmt.executeUpdate();
            if (cnt >= 1) {
                deleteResult = true;
            }
            Debug.print("EventCalendarDAO deleteCalendarDetails() result for champ : " + deleteResult);
            prepStmt.close();
            releaseConnection();
        } catch (SQLException sqe) {
            releaseConnection();
            Debug.print("Error while calling deleteCalendarDetails for champ : "+sqe.getMessage());
        }
        if(deleteResult==true){
        try {
            makeConnection();
            String selectStatement = "delete FROM " + DBHelper.OE_PROVISIONAL_CALENDAR + " WHERE pro_calendar_id = ? ";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,calId);
            int cnt = prepStmt.executeUpdate();
            if (cnt >= 1) {
                result = true;
            }
            Debug.print("EventCalendarDAO deleteCalendarDetails() result: " + result);
            prepStmt.close();
            releaseConnection();
        } catch (SQLException sqe) {
            releaseConnection();
            Debug.print("Error while calling deleteCalendarDetails : "+sqe.getMessage());
        }
        }
        return result;
    }
    
  public boolean insertChampionshipDet(String provisionalId,String eventId,String eventType,String eventLevel,String champStatus,String divisionId) {
        Debug.print("EventCalendarDAO.insertChampionshipDet():"+divisionId);
        PreparedStatement prepStmt = null;
        boolean result = false;
        boolean deleteResult = false;
       
        makeConnection();
        try {
            String insertStatement = "insert into "+DBHelper.OE_CHAMP_DETAILS+" (pro_calendar_id, event_id, event_type_id, event_level_id, championship_status, event_division_id)" +
                    " values ( ? , ? , ? , ? , ? , ? ) ";
            
            prepStmt = con.prepareStatement(insertStatement);
            prepStmt.setString(1, provisionalId);
            prepStmt.setString(2, eventId);
            prepStmt.setString(3, eventType);
            prepStmt.setString(4, eventLevel);
            prepStmt.setString(5, champStatus);
            prepStmt.setString(6, divisionId);
            int cnt = prepStmt.executeUpdate();
            Debug.print("Record Inserted succefully into insertChampionshipDet "+cnt);
            
            if(cnt>=1){
                result = true;
            }
            Debug.print("EventCalendarDAO insertChampionshipDet() result :" + result);
            prepStmt.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in EventCalendarDAO.insertChampionshipDet():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in EventCalendarDAO.insertChampionshipDet():" + e.getMessage());
        }
       
        return result;
  }
  
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
   
   
   public ArrayList selectEventDetsApprovedByAll(String provisionalId, String eventId){
        Debug.print("EventCalendarDAO.selectEventDetsApprovedByAll():");
        ArrayList proCalDet = new ArrayList();
        
        
        makeConnection();
        try {

            String selectStatement ="Select A.pro_calendar_id, A.organizer_id, A.begin_date, A.end_date, A.event_title," +
                    " E.user_id, E.first_name, E.last_name, B.area_code, B.area_name, C.state_name, A.area_chair_approval_status, A.org_approval_status, A.usea_approval_status, A.event_id, A.old_event_id, A.competition_year from "+DBHelper.OE_PROVISIONAL_CALENDAR+" A," +
                    DBHelper.OE_AREA_MASTER+" B, "+DBHelper.OE_STATE_MASTER+" C, "+DBHelper.OE_MEMBER_DETAILS+" D, "+DBHelper.OE_USER_MASTER+" E " +
                    " where A.area_id=B.area_id and A.state_id=C.state_id and D.user_id=E.user_id and A.organizer_id=D.member_id "+
                    " and A.pro_calendar_id= ? and event_id= ?";
                   
            PreparedStatement prepSelect = con.prepareStatement(selectStatement);
            prepSelect.setString(1,provisionalId);
            prepSelect.setString(2,eventId);
            ResultSet rs = prepSelect.executeQuery();
            
            while(rs.next()){
                String proCalId = rs.getString(1);
                String organizerId = rs.getString(2);
                Date beginDate=rs.getDate(3);
                Date endDate=rs.getDate(4);
                String eventTitle=rs.getString(5);
                String userId=rs.getString(6);
                String firstName=rs.getString(7);
                String lastName=rs.getString(8);
                String areaCode=rs.getString(9);
                String areaName=rs.getString(10);
                String stateName=rs.getString(11);
                String areaChApprovStat=rs.getString(12);
                String orgApprovStat=rs.getString(13);
                String uStaffApprovStat=rs.getString(14);
                 eventId=rs.getString(15);
                 String oldEventId=rs.getString(16);
                 int compYear=rs.getInt(17);
                
                HLCCalendarVO objEventDetails = new HLCCalendarVO();
                
                objEventDetails.setCalenderId(proCalId);
                objEventDetails.setOrganizerId(organizerId);
                objEventDetails.setBeginDate(beginDate);
                objEventDetails.setEndDate(endDate);
                objEventDetails.setEventTitle(eventTitle);
                objEventDetails.setUserId(userId);
                objEventDetails.setOrgFName(firstName);
                objEventDetails.setOrgLName(lastName);
                objEventDetails.setAreaCode(areaCode);
                objEventDetails.setAreaName(areaName);
                objEventDetails.setStateName(stateName);
                objEventDetails.setArearChairApproStatus(areaChApprovStat);
                objEventDetails.setOrgApprovalStatus(orgApprovStat);
                objEventDetails.setApprovalStatus(uStaffApprovStat);
                objEventDetails.setEventId(eventId);
                objEventDetails.setOldEventId(oldEventId);
                objEventDetails.setCompYear(compYear);
                proCalDet.add(objEventDetails);
                
            }
            rs.close();
            prepSelect.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            sql.printStackTrace();
        } catch(Exception e){
            releaseConnection();
            e.printStackTrace();
        }
        return proCalDet;
    } 
  
   
 public ArrayList selectOldEventDetails(String oldEventId) throws RemoteException{
        Debug.print("Inside DAO");
        Debug.print("oldEventId in first"+oldEventId);
        ArrayList eventList = new ArrayList();
        
        try {
            makeConnection();                    
            String selStmt="select A.competition_location, A.competition_city ,A.competition_country, " +
                    "A.issue_id, A.entry_fee, A.other_entry_fee, A.membership_applicable, A.double_entry_fee_status,"+
                    "A.office_fee, A.check_payable_to, A.pinnies_charge, A.pinnies_position, A.award_trophy, " +
                    "A.award_prize, A.award_others, A.date_available, A.available_from, A.available_from_other," +
                    "A.available_position, A.stabling_limited, A.stall_pernight_price," +
                    "A.per_stall_price, A.per_stall_from_time, A.per_stall_from_date, A.per_stall_to_time, A.per_stall_to_date, " +
                    "A.no_of_stalls, A.no_of_temp_stalls, A.no_of_temp_permanent_stalls, A.miles_from_event, A.veterinarian_name, " +
                    "A.veterinarian_phone, A.veterinarian_place, A.accomodation_camping, A.directions," +
                    "A.footing_desc, A.comments,A.organizer_id, A.event_title, A.competition_zip, A.event_secretary_id from "+DBHelper.OE_EVENT_DETAILS+" A "+
                    "where event_id= ?";
            
            Debug.print("Query: "+selStmt);
            prepStmt = con.prepareStatement(selStmt);
            prepStmt.setString(1,oldEventId);
            Debug.print("oldEventId in set"+oldEventId);                        
            rs = prepStmt.executeQuery();
            
            while(rs.next()) {              
                String compLocation = rs.getString(1);
                String compCity = rs.getString(2);              
                String compCountry = rs.getString(3);
                String issueId = rs.getString(4);
                float entryFee = rs.getFloat(5);
                String otherEntryFee = rs.getString(6);
                String membApplicable = rs.getString(7);
                String doubleEntryFee = rs.getString(8);
                float offFee = rs.getFloat(9);
                String chkPayTo = rs.getString(10);
                float pinneyChrg = rs.getFloat(11);
                int pinneyPos = rs.getInt(12);
                String awdTrophy = rs.getString(13);
                String awdPrize = rs.getString(14);
                String awdOthers = rs.getString(15);
                Date dateAvail = rs.getDate(16);
                String availableFrom = rs.getString(17);
                String availFrmOther = rs.getString(18);
                int availPosition = rs.getInt(19);
                boolean stabLimited = rs.getBoolean(20);
                float stallPerNightPrice = rs.getFloat(21);
                float perStallPrice = rs.getFloat(22);
                String perStallFrmTime = rs.getString(23);
                Date perStallFrmDt = rs.getDate(24);
                String perStallToTime = rs.getString(25);
                Date perStallToDt = rs.getDate(26);
                int noOfStalls = rs.getInt(27);
                int noOfTempStalls = rs.getInt(28);
                int noOfTempPermantStalls = rs.getInt(29);
                String milesFrmEvent = rs.getString(30);
                String vertName = rs.getString(31);
                String vertPhne = rs.getString(32);
                String vertPlace = rs.getString(33);
                String accomCamp = rs.getString(34);
                String direct = rs.getString(35);
                String footingDesc = rs.getString(36);
                String comments = rs.getString(37);
                String orgId = rs.getString(38);  
                Debug.print("orgId in set"+orgId);   
                String eveTitle = rs.getString(39);
                Debug.print("eveTitle in set"+eveTitle);
                String compZip = rs.getString(40);
                String eveSecId = rs.getString(41);
                
                
            HLCEventRequestVO objEventDetails = new HLCEventRequestVO(); 
            
            objEventDetails.setCompetition_location(compLocation);
            objEventDetails.setCompetition_city(compCity);
            objEventDetails.setCompetition_country(compCountry);
            objEventDetails.setIssueId(issueId);
            objEventDetails.setEntry_fee(entryFee);
            objEventDetails.setOther_entry_fee(otherEntryFee);
            objEventDetails.setMembership_applicable(membApplicable);
            objEventDetails.setDouble_entry_fee_status(doubleEntryFee);
            objEventDetails.setOffice_fee(offFee);
            objEventDetails.setCheck_payable_to(chkPayTo);
            objEventDetails.setPinnies_charge(pinneyChrg);
            objEventDetails.setPinnies_position(pinneyPos);
            objEventDetails.setAward_trophy(awdTrophy);
            objEventDetails.setAward_prize(awdPrize);
            objEventDetails.setAward_others(awdOthers);
            objEventDetails.setDate_available(dateAvail);
            objEventDetails.setAvailable_from(availableFrom);
            objEventDetails.setAvailable_from_other(availFrmOther);
            objEventDetails.setAvailable_position(availPosition);
            objEventDetails.setStabling_limited(stabLimited);
            objEventDetails.setStall_pernight_price(stallPerNightPrice);
            objEventDetails.setPer_stall_price(perStallPrice);
            objEventDetails.setPer_stall_from_time(perStallFrmTime);
            objEventDetails.setPer_stall_from_date(perStallFrmDt);
            
            objEventDetails.setPer_stall_to_time(perStallToTime);
            objEventDetails.setPer_stall_to_date(perStallToDt);
            objEventDetails.setNo_of_stalls(noOfStalls);
            objEventDetails.setNo_of_temp_stalls(noOfTempStalls);
            objEventDetails.setNo_of_temp_permanent_stalls(noOfTempPermantStalls);
            
            objEventDetails.setMiles_from_event(milesFrmEvent);
            objEventDetails.setVeterinarian_name(vertName);
            objEventDetails.setVeterinarian_phone(vertPhne);
            objEventDetails.setVeterinarian_place(vertPlace);
            objEventDetails.setAccomodation_camping(accomCamp);
            
            objEventDetails.setDirections(direct);
            objEventDetails.setFooting_desc(footingDesc);
            objEventDetails.setComments(comments);
            objEventDetails.setOrgId(orgId);
            Debug.print("orgId in DAO"+objEventDetails.getOrgId()); 
            objEventDetails.setEventTitle(eveTitle);
            objEventDetails.setCompetition_zip(compZip);
            objEventDetails.setEvent_secretary_id(eveSecId);
            Debug.print("secId in DAO"+objEventDetails.getEvent_secretary_id());
            
                
           eventList.add(objEventDetails);
            }
            Debug.print("eventList in DAO"+eventList.size());
            
            Debug.print("eventList in DAO"+eventList.size());
            rs.close();
            prepStmt.close();
        } catch(SQLException sqe){
            //throw new EJBException(sqe);
            Debug.print("Exception in EventCalendarDAO.selectOldEventDetails()");
            sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return eventList;
    }  
   
 public ArrayList getProvEventDetails(String eventId) throws RemoteException{
        Debug.print("Inside DAO");
        Debug.print("eventId in getProvEventDetails()"+eventId);
        ArrayList eventDet = new ArrayList();
        try {
            makeConnection();
                  
            String selStmt1 ="select organizer_id, event_title, begin_date, end_date, oe_begin_date, oe_end_date, oe_extended_due_date, " +
                    "state_id, area_id from tblOEProvisionalCalendar where event_id= ?";
            
            Debug.print("Query: "+selStmt1);
            prepStmt = con.prepareStatement(selStmt1);
            prepStmt.setString(1,eventId);
            Debug.print("evetId in set"+eventId);
            rs = prepStmt.executeQuery();
            
            while(rs.next()) {
                String orgId = rs.getString(1);
                String eveTitle = rs.getString(2);
                Date eveBegDt = rs.getDate(3);
                Date eveEndDt = rs.getDate(4);
                Date oeBegDt = rs.getDate(5);
                Date oeEndDt = rs.getDate(6);
                Date oeExDueDt = rs.getDate(7);
                String state = rs.getString(8);
                //String zip=rs.getString(9);
                String area=rs.getString(9);
                               
                HLCCalendarVO calVO = new HLCCalendarVO();
                calVO.setOrganizerId(orgId);
                calVO.setEventTitle(eveTitle);
                calVO.setBeginDate(eveBegDt);
                calVO.setEndDate(eveEndDt);
                calVO.setEntryStrtDate(oeBegDt);
                calVO.setEntryEndDate(oeEndDt);
                calVO.setExtDueDate(oeExDueDt);
                calVO.setStateId(state);  
                calVO.setAreaId(area);
                eventDet.add(calVO);
            }
            
            Debug.print("eventDet in EventCalendarDAO"+eventDet.size());
            
            Debug.print("eventDet in EventCalendarDAO"+eventDet.size());
            rs.close();
            prepStmt.close();
        } catch(SQLException sqe){
            //throw new EJBException(sqe);
            Debug.print("Exception in EventCalendarDAO.getProvEventDetails()");
            sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return eventDet;
    } 
  
 public boolean updateNewEveDets(String orgId,String eveTitle, Date eveBegDt, Date eveEndDt,Date oeBegDt,Date oeEndDt,Date oeExDueDt,String state,String area,String eventId) {
        Debug.print("DITRegDAO.updateNewEveDets():");
        boolean result = false;
        PreparedStatement prepStmt = null;
        if(eventId!=null && eventId.trim().length()!=0){
            makeConnection();
            try {
                String updateStatement = "update tblMeeEventDetails set organizer_id = ?, " +
                        "event_title = ?, event_begin_date = ?, event_end_date = ?, oe_begin_date = ?, " +
                        "oe_end_date = ?, oe_extended_due_date = ?, competition_state = ?, championship_area = ? where event_id = ?";
                
                prepStmt = con.prepareStatement(updateStatement);
                
                if(orgId!=null && orgId.trim().length()!=0){
                prepStmt.setString(1, orgId);
                }else{
                  prepStmt.setString(1, null);  
                }
                if(eveTitle!=null && eveTitle.trim().length()!=0){
                prepStmt.setString(2, eveTitle);
                }else {
                    prepStmt.setString(2, null);
                }
                if(eveBegDt!=null){
                prepStmt.setDate(3, DBHelper.toSQLDate(eveBegDt));
                }else{
                    prepStmt.setDate(3, null);
                }
                if(eveEndDt!=null){
                prepStmt.setDate(4, DBHelper.toSQLDate(eveEndDt));
                }else{
                 prepStmt.setDate(4, null);   
                }
                if(oeBegDt!=null){
                prepStmt.setDate(5, DBHelper.toSQLDate(oeBegDt));
                }else{
                  prepStmt.setDate(5,null);  
                }
                if(oeEndDt!=null){
                prepStmt.setDate(6, DBHelper.toSQLDate(oeEndDt));
                }else{
                 prepStmt.setDate(6, null);   
                }
                if(oeExDueDt!=null){
                prepStmt.setDate(7, DBHelper.toSQLDate(oeExDueDt));
                }else{
                    prepStmt.setDate(7, null); 
                }
                if(state!=null && state.trim().length()!=0){
                prepStmt.setString(8, state);
                }else{
                 prepStmt.setString(8, null);   
                }
                if(area!=null && area.trim().length()!=0){
                prepStmt.setString(9, area);
                }else{
                 prepStmt.setString(9, null);   
                }
                if(eventId!=null && eventId.trim().length()!=0){
                prepStmt.setString(10, eventId);
                }else{
                 prepStmt.setString(10, null);   
                }
                
                int cnt = prepStmt.executeUpdate();
                Debug.print("Record Inserted succefully into updateNewEveDets "+cnt);
                
                if(cnt>=1){
                    result = true;
                }
                
                Debug.print("EventCalendarDAO updateNewEveDets() Status :" + result);
                prepStmt.close();
                releaseConnection();
            } catch(SQLException sql){
                releaseConnection();
                Debug.print("SQL Exception in EventCalendarDAO.updateNewEveDets():" + sql.getMessage());
            } catch(Exception e){
                releaseConnection();
                Debug.print("General Exception  in EventCalendarDAO.updateNewEveDets():" + e.getMessage());
            }
        }
        return result;
    }
    
 
 public boolean updateOmnibusDetsInAllTables(String eventId, String oldEventId) {
        Debug.print("DITRegDAO.updateOmnibusDetsInAllTables():");
        boolean result = false;
        
        if(oldEventId!=null && oldEventId.trim().length()!=0){
           
         try{
           makeConnection(); 
           PreparedStatement prepStmt = null;
           String selStmt1 ="select event_id, hotel_name, hotel_phone, miles_from_event " +
                    "from tblMeeAccommodationDetails where event_id= ?";
            
            Debug.print("Query: "+selStmt1);
            prepStmt = con.prepareStatement(selStmt1);
            prepStmt.setString(1,oldEventId);
            Debug.print("oldEventId in set"+oldEventId);
            rs = prepStmt.executeQuery();   
           while(rs.next()){
             String eveId=rs.getString(1); 
             String hotelName=rs.getString(2); 
             String hotelPh=rs.getString(3); 
             String milesFrmEve=rs.getString(4); 
           
            
          String insertStatement = "insert into tblMeeAccommodationDetails (event_id, hotel_name, hotel_phone, miles_from_event)" +
                    " values ( ? , ? , ? , ?) ";
            
            prepStmt = con.prepareStatement(insertStatement);
            if(eventId!=null){
                prepStmt.setString(1, eventId);
            }else {
                prepStmt.setString(1, null);
            }
            if(hotelName!=null){
            prepStmt.setString(2, hotelName);
            }else {
              prepStmt.setString(2, null);  
            }
            if(hotelPh!=null){
            prepStmt.setString(3, hotelPh);
            }else{
             prepStmt.setString(3, null);   
            }
            if(milesFrmEve!=null){
            prepStmt.setString(4, milesFrmEve);
            }else {
              prepStmt.setString(4, null);  
            }
            
            int cnt = prepStmt.executeUpdate();
            Debug.print("Record Inserted succefully into tblMeeAccommodationDetails "+cnt);
            
            if(cnt>=1){
                result = true;
            }  
            }
            rs.close();
            prepStmt.close();
           releaseConnection();  
         }catch(Exception e){
             releaseConnection();
           Debug.print("General Exception  in EventCalendarDAO.updateNewEveDets() in tblMeeAccommodationDetails:" + e.getMessage());  
         }
        
         try{
              makeConnection(); 
              PreparedStatement prepStmt2 = null;
              ResultSet rs2 = null;
         String selStmt2 ="select event_id, all_state, in_state, out_of_state, no_state, others " +
                    "from tblMeeCogginsDetails where event_id= ?";
            
            Debug.print("Query: "+selStmt2);
            prepStmt2 = con.prepareStatement(selStmt2);
            prepStmt2.setString(1,oldEventId);
            Debug.print("oldEventId in set"+oldEventId);
            rs2 = prepStmt2.executeQuery();   
           while(rs2.next()){
             String eveId=rs2.getString(1); 
             String allState=rs2.getString(2); 
             String inState=rs2.getString(3); 
             String outState=rs2.getString(4); 
             String noState=rs2.getString(5);
             String others=rs2.getString(6);
           
            
          String insertStatement = "insert into tblMeeCogginsDetails (event_id, all_state, in_state, out_of_state, no_state, others)" +
                    " values ( ? , ? , ? , ? , ?, ? ) ";
            
            prepStmt2 = con.prepareStatement(insertStatement);
            if(eventId!=null){
            prepStmt2.setString(1, eventId);
            }else{
              prepStmt2.setString(1, null); 
            }
            if(allState!=null){
            prepStmt2.setString(2, allState);
            }else{
              prepStmt2.setString(2, null);  
            }
            if(inState!=null){
            prepStmt2.setString(3, inState);
            }else{
             prepStmt2.setString(3, null);   
            }
            if(outState!=null){
            prepStmt2.setString(4, outState);
            }else{
             prepStmt2.setString(4, null);   
            }
            if(noState!=null){
            prepStmt2.setString(5, noState);
            }else{
               prepStmt2.setString(5, null); 
            }
            if(others!=null){
            prepStmt2.setString(6, others);
            }else{
              prepStmt2.setString(6, null);  
            }
            
            int cnt2 = prepStmt2.executeUpdate();
            Debug.print("Record Inserted succefully into tblMeeCogginsDetails "+cnt2);
            
            if(cnt2>=1){
                result = true;
            }  
            }
            rs2.close();
            prepStmt2.close();
           releaseConnection();      
             
         }catch(Exception e){
             releaseConnection();
           Debug.print("General Exception  in EventCalendarDAO.updateNewEveDets() in tblMeeCogginsDetails:" + e.getMessage());  
         }
         
         try{
              makeConnection(); 
              PreparedStatement prepStmt3 = null;
              ResultSet rs3= null;
         String selStmt3 ="select event_id, division, length, speed, course_description, add_information " +
                    "from tblMeeCrossCountryDetails where event_id= ?";
            
            Debug.print("Query: "+selStmt3);
            prepStmt3 = con.prepareStatement(selStmt3);
            prepStmt3.setString(1,oldEventId);
            Debug.print("oldEventId in set"+oldEventId);
            rs3 = prepStmt3.executeQuery();   
           while(rs3.next()){
             String eveId=rs3.getString(1); 
             String div=rs3.getString(2); 
             String length=rs3.getString(3); 
             String speed=rs3.getString(4); 
             String courseDesc=rs3.getString(5);
             String addInfo=rs3.getString(6);
           
            
          String insertStatement3 = "insert into tblMeeCrossCountryDetails (event_id, division, length, speed, course_description, add_information)" +
                    " values ( ? , ? , ? , ? , ?, ?) ";
            
            prepStmt3 = con.prepareStatement(insertStatement3);
            if(eventId!=null){
            prepStmt3.setString(1, eventId);
            }else{
              prepStmt3.setString(1, null);  
            }
            if(div!=null){
            prepStmt3.setString(2, div);
            }else{
             prepStmt3.setString(2, null);   
            }
            if(length!=null){
            prepStmt3.setString(3, length);
            }else{
              prepStmt3.setString(3,null);  
            }
            if(speed!=null){
            prepStmt3.setString(4, speed);
            }else{
              prepStmt3.setString(4, null);  
            }
            if(courseDesc!=null){
            prepStmt3.setString(5, courseDesc);
            }else{
             prepStmt3.setString(5, null);   
            }
            if(addInfo!=null){
            prepStmt3.setString(6, addInfo);
            }else{
             prepStmt3.setString(6, null);   
            }
            
            int cnt3 = prepStmt3.executeUpdate();
            Debug.print("Record Inserted succefully into tblMeeCrossCountryDetails "+cnt3);
            
            if(cnt3>=1){
                result = true;
            }  
            }
            rs3.close();
            prepStmt3.close();
           releaseConnection();      
             
         }catch(Exception e){
             releaseConnection();
           Debug.print("General Exception  in EventCalendarDAO.updateNewEveDets() in tblMeeCrossCountryDetails:" + e.getMessage());  
         }
         
       try{
            makeConnection();  
            PreparedStatement prepStmt4 = null;
              ResultSet rs4 = null;
         String selStmt4 ="select event_id, dressage_map_id, arena_size_id " +
                    "from tblMeeHorseDressageDetails where event_id= ?";
            
            Debug.print("Query: "+selStmt4);
            prepStmt4 = con.prepareStatement(selStmt4);
            prepStmt4.setString(1,oldEventId);
            Debug.print("oldEventId in set"+oldEventId);
            rs4 = prepStmt4.executeQuery();   
           while(rs4.next()){
             String eveId=rs4.getString(1); 
             String dressMapId=rs4.getString(2); 
             String arenaSize=rs4.getString(3); 
                             
          String insertStatement4 = "insert into tblMeeHorseDressageDetails (event_id, dressage_map_id, arena_size_id)" +
                    " values ( ? , ? , ?) ";
            
            prepStmt4 = con.prepareStatement(insertStatement4);
            if(eventId!=null){
            prepStmt4.setString(1, eventId);
            }else{
             prepStmt4.setString(1, null);   
            }
            if(dressMapId!=null){
            prepStmt4.setString(2, dressMapId);
            }else{
            prepStmt4.setString(2, null); 
            }
            if(arenaSize!=null){
            prepStmt4.setString(3, arenaSize); 
            }else{
             prepStmt4.setString(3, null);    
            }
            int cnt4 = prepStmt4.executeUpdate();
            Debug.print("Record Inserted succefully into tblMeeHorseDressageDetails "+cnt4);
            
            if(cnt4>=1){
                result = true;
            }  
            }
            rs4.close();
            prepStmt4.close();
           releaseConnection();      
             
         }catch(Exception e){
             releaseConnection();
           Debug.print("General Exception  in EventCalendarDAO.updateNewEveDets() in tblMeeHorseDressageDetails:" + e.getMessage());  
         }
         
         try{
              makeConnection(); 
               PreparedStatement prepStmt5 = null;
              ResultSet rs5 = null;
         String selStmt5 ="select event_id, judge_type_id, judge_names " +
                    "from tblMeeJudgeDetails where event_id= ?";
            
            Debug.print("Query: "+selStmt5);
            prepStmt5 = con.prepareStatement(selStmt5);
            prepStmt5.setString(1,oldEventId);
            Debug.print("oldEventId in set"+oldEventId);
            rs5 = prepStmt5.executeQuery();   
           while(rs5.next()){
             String eveId=rs5.getString(1); 
             String judgeTypeId=rs5.getString(2); 
             String judgeNames=rs5.getString(3); 
                             
          String insertStatement5 = "insert into tblMeeJudgeDetails (event_id, judge_type_id, judge_names)" +
                    " values ( ? , ? , ?) ";
            
            prepStmt5 = con.prepareStatement(insertStatement5);
            if(eventId!=null){
            prepStmt5.setString(1, eventId);
            }else{
            prepStmt5.setString(1, null);    
            }
            if(judgeTypeId!=null){
            prepStmt5.setString(2, judgeTypeId);
            }else{
              prepStmt5.setString(2, null);  
            }
            if(judgeTypeId!=null){
            prepStmt5.setString(3, judgeNames); 
            }else{
               prepStmt5.setString(3, null);  
            }
                
            int cnt5 = prepStmt5.executeUpdate();
            Debug.print("Record Inserted succefully into tblMeeJudgeDetails "+cnt5);
            
            if(cnt5>=1){
                result = true;
            }  
            }
            rs5.close();
            prepStmt5.close();
           releaseConnection();      
             
         }catch(Exception e){
             releaseConnection();
           Debug.print("General Exception  in EventCalendarDAO.updateNewEveDets() in tblMeeJudgeDetails:" + e.getMessage());  
         }
               
       try{
            makeConnection();
             PreparedStatement prepStmt6 = null;
              ResultSet rs6 = null;
         String selStmt6 ="select event_id, course_close_date, entry_limit, riders_horse_level_limit, riders_horse_entire_limit, " +
                 "division_entry_birth_date, lease_dogs, team_competition, per_time_price, other_team_info, party_name, add_info " +
                 "from tblMeeOtherDetails where event_id= ?";
            
            Debug.print("Query: "+selStmt6);
            prepStmt6 = con.prepareStatement(selStmt6);
            prepStmt6.setString(1,oldEventId);
            Debug.print("oldEventId in set"+oldEventId);
            rs6 = prepStmt6.executeQuery();   
           while(rs6.next()){
             String eveId=rs6.getString(1); 
             Date courseCloseDt=rs6.getDate(2); 
             String entryLmit=rs6.getString(3);
             String riderHrsLevelLimit=rs6.getString(4); 
             String riderHrsEntireLimit=rs6.getString(5); 
             Date divEntryBirthDate=rs6.getDate(6); 
             String leaseDogs=rs6.getString(7); 
             String teamComp=rs6.getString(8); 
             float perTimePri=rs6.getFloat(9);
             String otherTemInfo=rs6.getString(10); 
             String partyName=rs6.getString(11); 
             String addInfo=rs6.getString(12); 
                             
          String insertStatement6 = "insert into tblMeeOtherDetails (event_id, course_close_date, entry_limit, riders_horse_level_limit, riders_horse_entire_limit, division_entry_birth_date, lease_dogs, team_competition, per_time_price, other_team_info, party_name, add_info)" +
                    " values ( ? , ? , ?, ? , ? , ?, ? , ? , ?, ? , ? , ?) ";
            
            prepStmt6 = con.prepareStatement(insertStatement6);
            if(eventId!=null){
            prepStmt6.setString(1, eventId);
            }else{
             prepStmt6.setString(1, null);   
            }
            if(courseCloseDt!=null){
            prepStmt6.setDate(2, DBHelper.toSQLDate(courseCloseDt));
            }else{
              prepStmt6.setDate(2,null);  
            }
            if(entryLmit!=null){
            prepStmt6.setString(3, entryLmit); 
            }else{
              prepStmt6.setString(3, null);   
            }
            if(riderHrsLevelLimit!=null){
             prepStmt6.setString(4, riderHrsLevelLimit);
            }else{
             prepStmt6.setString(4, null);   
            }
            if(riderHrsEntireLimit!=null){
            prepStmt6.setString(5, riderHrsEntireLimit);
            }else{
             prepStmt6.setString(5, null);   
            }
            if(divEntryBirthDate!=null){
             prepStmt6.setDate(6, DBHelper.toSQLDate(divEntryBirthDate));
            }else{
              prepStmt6.setDate(6, null);  
            }
            if(leaseDogs!=null){
            prepStmt6.setString(7, leaseDogs);
            }else{
                prepStmt6.setString(7, null);
            }
            if(teamComp!=null){
             prepStmt6.setString(8, teamComp);
            }else{
              prepStmt6.setString(8, null);  
            }
            if(perTimePri!=0){
            prepStmt6.setFloat(9, perTimePri);
            }else{
               prepStmt6.setFloat(9, 0); 
            }
            if(otherTemInfo!=null){
             prepStmt6.setString(10, otherTemInfo);
            }else{
              prepStmt6.setString(10, null);  
            }
             if(partyName!=null){
            prepStmt6.setString(11, partyName);
             }else{
                prepStmt6.setString(11, null);
             }
             if(addInfo!=null){
             prepStmt6.setString(12, addInfo);
             }else{
               prepStmt6.setString(12, null); 
             }
            
            int cnt6 = prepStmt6.executeUpdate();
            Debug.print("Record Inserted succefully into tblMeeOtherDetails "+cnt6);
            
            if(cnt6>=1){
                result = true;
            }  
            }
            rs6.close();
            prepStmt6.close();
           releaseConnection();      
             
         }catch(Exception e){
             releaseConnection();
           Debug.print("General Exception  in EventCalendarDAO.updateNewEveDets() in tblMeeOtherDetails:" + e.getMessage());  
         }
          
      try{
           makeConnection(); 
            PreparedStatement prepStmt7 = null;
              ResultSet rs7 = null;
         String selStmt7 ="select event_id, refund_map_id, amount " +
                    "from tblMeeRefundRuleDetails where event_id= ?";
            
            Debug.print("Query: "+selStmt7);
            prepStmt7 = con.prepareStatement(selStmt7);
            prepStmt7.setString(1,oldEventId);
            Debug.print("oldEventId in set"+oldEventId);
            rs7 = prepStmt7.executeQuery();   
           while(rs7.next()){
             String eveId=rs7.getString(1); 
             String refundMapId=rs7.getString(2); 
             float amt=rs7.getFloat(3); 
                             
          String insertStatement7 = "insert into tblMeeRefundRuleDetails (event_id, refund_map_id, amount)" +
                    " values ( ? , ? , ?) ";
            
            prepStmt7 = con.prepareStatement(insertStatement7);
            if(eventId!=null){
            prepStmt7.setString(1, eventId);
            }else{
               prepStmt7.setString(1, null); 
            }
            if(refundMapId!=null){
            prepStmt7.setString(2, refundMapId);
            }else{
             prepStmt7.setString(2, null);
            }
            if(amt!=0){
            prepStmt7.setFloat(3, amt); 
            }else{
                prepStmt7.setFloat(3, 0); 
            }
            int cnt7 = prepStmt7.executeUpdate();
            Debug.print("Record Inserted succefully into tblMeeRefundRuleDetails "+cnt7);
            
            if(cnt7>=1){
                result = true;
            }  
            }
            rs7.close();
            prepStmt7.close();
           releaseConnection();      
             
         }catch(Exception e){
             releaseConnection();
           Debug.print("General Exception  in EventCalendarDAO.updateNewEveDets() in tblMeeRefundRuleDetails:" + e.getMessage());  
         }
         
         try{
              makeConnection(); 
              PreparedStatement prepStmt8 = null;
              ResultSet rs8 = null;
         String selStmt8 ="select event_id, day, phase, time " +
                    "from tblMeeTentativeTimeSchedule where event_id= ?";
            
            Debug.print("Query: "+selStmt8);
            prepStmt8 = con.prepareStatement(selStmt8);
            prepStmt8.setString(1,oldEventId);
            Debug.print("oldEventId in set"+oldEventId);
            rs8 = prepStmt8.executeQuery();   
           while(rs8.next()){
             String eveId=rs8.getString(1); 
             Date day=rs8.getDate(2); 
             String phase=rs8.getString(3); 
             String time=rs8.getString(4); 
                             
          String insertStatement8 = "insert into tblMeeTentativeTimeSchedule (event_id, day, phase, time)" +
                    " values ( ? , ? , ?, ?) ";
            
            prepStmt8 = con.prepareStatement(insertStatement8);
            if(eventId!=null){
               prepStmt8.setString(1, eventId); 
            }else{
               prepStmt8.setString(1, null); 
            }
            if(day!=null){
            prepStmt8.setDate(2, DBHelper.toSQLDate(day));
            }else{
              prepStmt8.setDate(2, null);  
            }
             if(phase!=null){
            prepStmt8.setString(3, phase);
             }else{
              prepStmt8.setString(3, null);  
             }
             if(time!=null){
            prepStmt8.setString(4, time);
             }else{
               prepStmt8.setString(4, null); 
             }
            int cnt8 = prepStmt8.executeUpdate();
            Debug.print("Record Inserted succefully into tblMeeTentativeTimeSchedule "+cnt8);
            
            if(cnt8>=1){
                result = true;
            }  
            }
            rs8.close();
            prepStmt8.close();
           releaseConnection();      
             
         }catch(Exception e){
             releaseConnection();
           Debug.print("General Exception  in EventCalendarDAO.updateNewEveDets() in tblMeeTentativeTimeSchedule:" + e.getMessage());  
         }
         
         
        try{
             makeConnection(); 
              PreparedStatement prepStmt9 = null;
              ResultSet rs9 = null;
         String selStmt9 ="select event_id, map_type_id " +
                    "from tblMeeEventTypeDetails where event_id= ?";
            
            Debug.print("Query: "+selStmt9);
            prepStmt9 = con.prepareStatement(selStmt9);
            prepStmt9.setString(1,oldEventId);
            Debug.print("oldEventId in set"+oldEventId);
            rs9 = prepStmt9.executeQuery();   
           while(rs9.next()){
             String eveId=rs9.getString(1); 
             String mapTypeId=rs9.getString(2); 
           
                             
          String insertStatement9 = "insert into tblMeeEventTypeDetails (event_id, map_type_id)" +
                    " values ( ? , ?) ";
            
            prepStmt9 = con.prepareStatement(insertStatement9);
            
            if(eventId!=null){
               prepStmt9.setString(1, eventId); 
            }else{
               prepStmt9.setString(1, null); 
            }
            if(mapTypeId!=null){
            prepStmt9.setString(2, mapTypeId); 
            }else{
               prepStmt9.setString(2, null);   
            }
            int cnt9 = prepStmt9.executeUpdate();
            Debug.print("Record Inserted succefully into tblMeeEventTypeDetails "+cnt9);
            
            if(cnt9>=1){
                result = true;
            }  
            }
            rs9.close();
            prepStmt9.close();
           releaseConnection();      
             
         }catch(Exception e){
             releaseConnection();
           Debug.print("General Exception  in EventCalendarDAO.updateNewEveDets() in tblMeeEventTypeDetails:" + e.getMessage());  
         }
             
        }
        return result;
    }
   
 
 
 public boolean insertProCalEveIntoEveDetails(HLCEventRequestVO objEventDetails) {
        Debug.print("EventCalendarDAO.insertProCalEveIntoEveDetails():");
        boolean result = false;
        PreparedStatement prepStmt = null;
        String eventId=objEventDetails.getEvent_id();
        String orgId=objEventDetails.getOrgId();
        Debug.print("EventCalendarDAO.insertProCalEveIntoEveDetails():" +eventId);
        Debug.print("EventCalendarDAO.insertProCalEveIntoEveDetails():" +orgId);
        
        if(eventId!=null && eventId.trim().length()!=0){
            makeConnection();
            try {
                String insertStatement = "insert into " + DBHelper.OE_EVENT_DETAILS + " "+ 
                        " (event_id, competition_location, competition_city, competition_country, issue_id, entry_fee, other_entry_fee, membership_applicable, double_entry_fee_status, office_fee, check_payable_to, pinnies_charge," +
                        "pinnies_position, award_trophy, award_prize, award_others,date_available, available_from, available_from_other, available_position, stabling_limited, stall_pernight_price, per_stall_price, per_stall_from_time," +
                        "per_stall_from_date, per_stall_to_time, per_stall_to_date, no_of_stalls, no_of_temp_stalls, no_of_temp_permanent_stalls, miles_from_event, veterinarian_name, veterinarian_phone," +
                        "veterinarian_place, accomodation_camping, directions, footing_desc, comments, organizer_id, event_title, competition_zip, event_secretary_id, status_id)" +
                        " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
                
                Debug.print("Query: "+insertStatement);
                prepStmt = con.prepareStatement(insertStatement);
                if(eventId!=null && eventId.trim().length()!=0){
                  prepStmt.setString(1, eventId);  
                }else {
                  prepStmt.setString(1, null);    
                }
                if(objEventDetails.getCompetition_location()!=null && objEventDetails.getCompetition_location().trim().length()!=0){
                  prepStmt.setString(2, objEventDetails.getCompetition_location()); 
                }else {
                  prepStmt.setString(2,null); 
                }
                if(objEventDetails.getCompetition_city()!=null && objEventDetails.getCompetition_city().trim().length()!=0){
                   prepStmt.setString(3, objEventDetails.getCompetition_city());
                }else {
                   prepStmt.setString(3, null);  
                }
                if(objEventDetails.getCompetition_country()!=null && objEventDetails.getCompetition_country().trim().length()!=0){
                  prepStmt.setString(4,  objEventDetails.getCompetition_country());
                }else {
                  prepStmt.setString(4,  null);    
                }
                if(objEventDetails.getIssueId()!=null && objEventDetails.getIssueId().trim().length()!=0){
                prepStmt.setString(5, objEventDetails.getIssueId());
                }else {
                  prepStmt.setString(5,null);  
                }
                if(objEventDetails.getEntry_fee()!=0){
                  prepStmt.setFloat(6,objEventDetails.getEntry_fee());
                }else {
                  prepStmt.setFloat(6, 0); 
                }
                if(objEventDetails.getOther_entry_fee()!=null && objEventDetails.getOther_entry_fee().trim().length()!=0){
                  prepStmt.setString(7, objEventDetails.getOther_entry_fee());
                }else {
                  prepStmt.setString(7, null);  
                }
                
                if(objEventDetails.getOther_entry_fee()!=null && objEventDetails.getOther_entry_fee().trim().length()!=0){
                  prepStmt.setString(7, objEventDetails.getOther_entry_fee());
                }else {
                  prepStmt.setString(7, null);  
                }
                
                 if(objEventDetails.getMembership_applicable()!=null){
                   prepStmt.setString(8, objEventDetails.getMembership_applicable());
                }else {
                   prepStmt.setString(8, null);
                }
                 if(objEventDetails.getDouble_entry_fee_status()!=null && objEventDetails.getDouble_entry_fee_status().trim().length()!=0){
                  prepStmt.setString(9,objEventDetails.getDouble_entry_fee_status());
                }else {
                  prepStmt.setString(9,null); 
                }
                 if(objEventDetails.getOffice_fee()!=0){
                  prepStmt.setFloat(10,objEventDetails.getOffice_fee());
                }else {
                  prepStmt.setFloat(10,0);
                }
                if(objEventDetails.getCheck_payable_to()!=null){
                  prepStmt.setString(11,objEventDetails.getCheck_payable_to());
                }else {
                  prepStmt.setString(11,null);
                }
                 if(objEventDetails.getPinnies_charge()!=0){
                   prepStmt.setFloat(12,objEventDetails.getPinnies_charge());
                }else {
                  prepStmt.setFloat(12,0);
                }
                 if(objEventDetails.getPinnies_position()!=0){
                  prepStmt.setInt(13,objEventDetails.getPinnies_position());
                }else {
                  prepStmt.setInt(13,0);
                }
                
                //
                
                if(objEventDetails.getAward_trophy()!=null && objEventDetails.getAward_trophy().trim().length()!=0){
                  prepStmt.setString(14,objEventDetails.getAward_trophy());
                }else {
                  prepStmt.setString(14,null);
                }
                 if(objEventDetails.getAward_prize()!=null && objEventDetails.getAward_prize().trim().length()!=0){
                  prepStmt.setString(15,objEventDetails.getAward_prize());
                }else {
                  prepStmt.setString(15,null);
                }
                
                 if(objEventDetails.getAward_others()!=null && objEventDetails.getAward_others().trim().length()!=0){
                  prepStmt.setString(16,objEventDetails.getAward_others());
                }else {
                  prepStmt.setString(16,null);
                }
                if(objEventDetails.getDate_available()!=null){
                  prepStmt.setDate(17,DBHelper.toSQLDate(objEventDetails.getDate_available()));
                }else {
                  prepStmt.setDate(17,null);
                }if(objEventDetails.getAvailable_from()!=null && objEventDetails.getAvailable_from().trim().length()!=0){
                  prepStmt.setString(18,objEventDetails.getAvailable_from());
                }else {
                  prepStmt.setString(18,null);
                }if(objEventDetails.getAvailable_from_other()!=null){
                  prepStmt.setString(19,objEventDetails.getAvailable_from_other());
                }else {
                  prepStmt.setString(19,null);
                }if(objEventDetails.getAvailable_position()!=0){
                   prepStmt.setInt(20,objEventDetails.getAvailable_position());
                }else {
                   prepStmt.setInt(20,0);
                }
                
                 prepStmt.setBoolean(21,objEventDetails.isStabling_limited());
                
                if(objEventDetails.getStall_pernight_price()!=0){
                  prepStmt.setFloat(22,objEventDetails.getStall_pernight_price());
                }else {
                  prepStmt.setFloat(22,0);
                }
                if(objEventDetails.getPer_stall_price()!=0){
                  prepStmt.setFloat(23,objEventDetails.getPer_stall_price());
                }else {
                 prepStmt.setFloat(23,0);
                }
                if(objEventDetails.getPer_stall_from_time()!=null && objEventDetails.getPer_stall_from_time().trim().length()!=0){
                  prepStmt.setString(24,objEventDetails.getPer_stall_from_time());
                }else {
                  prepStmt.setString(24,null);
                }
                if(objEventDetails.getPer_stall_from_date()!=null){
                  prepStmt.setDate(25,DBHelper.toSQLDate(objEventDetails.getPer_stall_from_date()));
                }else {
                  prepStmt.setDate(25,null);
                }
                if(objEventDetails.getPer_stall_to_time()!=null && objEventDetails.getPer_stall_to_time().trim().length()!=0){
                  prepStmt.setString(26,objEventDetails.getPer_stall_to_time());
                }else {
                  prepStmt.setString(26,null);
                }
                if(objEventDetails.getPer_stall_to_date()!=null){
                  prepStmt.setDate(27,DBHelper.toSQLDate(objEventDetails.getPer_stall_to_date()));
                }else {
                  prepStmt.setDate(27,null);
                }
                if(objEventDetails.getNo_of_stalls()!=0){
                   prepStmt.setInt(28,objEventDetails.getNo_of_stalls());
                }else {
                  prepStmt.setInt(28,0);
                }
                if(objEventDetails.getNo_of_temp_stalls()!=0){
                  prepStmt.setInt(29,objEventDetails.getNo_of_temp_stalls());
                }else {
                  prepStmt.setInt(29,0);
                }
                if(objEventDetails.getNo_of_temp_permanent_stalls()!=0){
                  prepStmt.setInt(30,objEventDetails.getNo_of_temp_permanent_stalls());
                }else {
                  prepStmt.setInt(30,0);
                }
                if(objEventDetails.getMiles_from_event()!=null && objEventDetails.getMiles_from_event().trim().length()!=0){
                  prepStmt.setString(31,objEventDetails.getMiles_from_event());
                }else {
                  prepStmt.setString(31,null);
                }
                if(objEventDetails.getVeterinarian_name()!=null && objEventDetails.getVeterinarian_name().trim().length()!=0){
                  prepStmt.setString(32,objEventDetails.getVeterinarian_name());
                }else {
                  prepStmt.setString(32,null);
                }
                if(objEventDetails.getVeterinarian_phone()!=null && objEventDetails.getVeterinarian_phone().trim().length()!=0){
                  prepStmt.setString(33,objEventDetails.getVeterinarian_phone());
                }else {
                 prepStmt.setString(33,null);
                }
                if(objEventDetails.getVeterinarian_place()!=null && objEventDetails.getVeterinarian_place().trim().length()!=0){
                  prepStmt.setString(34,objEventDetails.getVeterinarian_place());
                }else {
                  prepStmt.setString(34,null);
                }
                if(objEventDetails.getAccomodation_camping()!=null && objEventDetails.getAccomodation_camping().trim().length()!=0){
                  prepStmt.setString(35,objEventDetails.getAccomodation_camping());
                }else {
                  prepStmt.setString(35,null);
                }
                if(objEventDetails.getDirections()!=null && objEventDetails.getDirections().trim().length()!=0){
                 prepStmt.setString(36,objEventDetails.getDirections());
                }else {
                  prepStmt.setString(36,null);
                }
                if(objEventDetails.getFooting_desc()!=null && objEventDetails.getFooting_desc().trim().length()!=0){
                  prepStmt.setString(37,objEventDetails.getFooting_desc());
                }else {
                  prepStmt.setString(37,null);
                }
                if(objEventDetails.getComments()!=null && objEventDetails.getComments().trim().length()!=0){
                  prepStmt.setString(38,objEventDetails.getComments());
                }else {
                  prepStmt.setString(38,null);
                }
                 if(objEventDetails.getOrgId()!=null && objEventDetails.getOrgId().trim().length()!=0){
                  prepStmt.setString(39,objEventDetails.getOrgId());
                }else {
                  prepStmt.setString(39,null);
                }
                  if(objEventDetails.getEventTitle()!=null && objEventDetails.getEventTitle().trim().length()!=0){
                  prepStmt.setString(40,objEventDetails.getEventTitle());
                }else {
                  prepStmt.setString(40,null);
                }
       
              if(objEventDetails.getCompetition_zip()!=null && objEventDetails.getCompetition_zip().trim().length()!=0){
                  prepStmt.setString(41,objEventDetails.getCompetition_zip());
                }else {
                  prepStmt.setString(41,null);
                }  
                 
                if(objEventDetails.getEvent_secretary_id()!=null && objEventDetails.getEvent_secretary_id().trim().length()!=0){
                  prepStmt.setString(42, objEventDetails.getEvent_secretary_id());  
                }else {
                  prepStmt.setString(42, null);    
                } 
                 prepStmt.setString(43, "Approved"); 
                 
                int cnt = prepStmt.executeUpdate();
                Debug.print("Record Inserted succefully into insertProCalEveIntoEveDetails "+cnt);
                if(cnt >=1) result = true;
                Debug.print("EventCalendarDAO insertProCalEveIntoEveDetails() Status :" + result);
                prepStmt.close();
                releaseConnection();
            } catch(SQLException sql){
                releaseConnection();
                //Debug.print("SQL Exception in EventCalendarDAO.insertProCalEveIntoEveDetails():" + sql.getMessage());
                sql.printStackTrace();
            } catch(Exception e){
                releaseConnection();
                e.printStackTrace();
                //Debug.print("General Exception  in EventCalendarDAO.insertProCalEveIntoEveDetails():" + e.getMessage());
            }
            
        }
           
        return result;
    } 
 
 public boolean eveDetailsExistsInEveTable(String newEventId) {
        Debug.print("DITRegDAO.eveDetailsExistsInEveTable():");
        boolean result = false;
        PreparedStatement prepStmt = null;
        if(newEventId!=null && newEventId.trim().length()!=0){
           
            try {
                 makeConnection();
                String selectStatement = "select event_id from tblMeeEventDetails where event_id= ? ";
                
                prepStmt = con.prepareStatement(selectStatement);
                
                prepStmt.setString(1,newEventId);
            Debug.print("newEventId in set"+newEventId);
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                result = true;
            }
            rs.close();
            Debug.print("EventCalendarDAO eveDetailsExistsInEveTable() result: " + result);
            prepStmt.close();
            releaseConnection();
            } catch(SQLException sql){
                releaseConnection();
                Debug.print("SQL Exception in EventCalendarDAO.eveDetailsExistsInEveTable():" + sql.getMessage());
            } catch(Exception e){
                releaseConnection();
                Debug.print("General Exception  in EventCalendarDAO.eveDetailsExistsInEveTable():" + e.getMessage());
            }
        }
        return result;
    }
    
 public ArrayList selectOldEventHistoryDetails(String oldEventId) throws RemoteException{
        Debug.print("Inside DAO");
        Debug.print("oldEventId in first"+oldEventId);
        ArrayList eventList = new ArrayList();
        
        try {
            makeConnection();                    
            String selStmt="select A.event_title, A.organizer_id, " +
                    "A.competition_year from tblMeeEventHistoryDetails A "+
                    "where event_id= ?";
            
            Debug.print("Query: "+selStmt);
            prepStmt = con.prepareStatement(selStmt);
            prepStmt.setString(1,oldEventId);
            Debug.print("oldEventId in set"+oldEventId);                        
            rs = prepStmt.executeQuery();
            
            while(rs.next()) {              
                String eveTitle = rs.getString(1);
                String orgId = rs.getString(2);              
                int compYear = rs.getInt(3);
                
        
                
            HLCEventRequestVO objEventDetails = new HLCEventRequestVO(); 
            
           
            
            objEventDetails.setEventTitle(eveTitle);
            objEventDetails.setOrgId(orgId);
            Debug.print("orgId in DAO"+objEventDetails.getOrgId());
             objEventDetails.setCompYear(compYear);
            // objEventDetails.setApprovStatus(approveStat);
            // objEventDetails.setPaymentId(payId);
                                  
           eventList.add(objEventDetails);
            }
            Debug.print("eventList in DAO"+eventList.size());
            
            Debug.print("eventList in DAO"+eventList.size());
            rs.close();
            prepStmt.close();
        } catch(SQLException sqe){
            //throw new EJBException(sqe);
            Debug.print("Exception in EventCalendarDAO.selectOldEventHistoryDetails()");
            sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return eventList;
    }  
 
 
 
 public boolean insertProCalEveIntoEveHistoryDetails(HLCEventRequestVO objEventDetails) {
        Debug.print("EventCalendarDAO.insertProCalEveIntoEveHistoryDetails():");
        boolean result = false;
        PreparedStatement prepStmt = null;
        String eventId=objEventDetails.getEvent_id();
        String orgId=objEventDetails.getOrgId();
        Debug.print("EventCalendarDAO.insertProCalEveIntoEveHistoryDetails():" +eventId);
        Debug.print("EventCalendarDAO.insertProCalEveIntoEveHistoryDetails():" +orgId);
        
        if(eventId!=null && eventId.trim().length()!=0){
            makeConnection();
            try {
                String insertStatement = "insert into tblMeeEventHistoryDetails" + 
                        " (event_id, event_title, organizer_id, competition_year)" +
                        " values (?, ?, ?, ?) ";
                
                Debug.print("Query: "+insertStatement);
                prepStmt = con.prepareStatement(insertStatement);
                if(eventId!=null && eventId.trim().length()!=0){
                  prepStmt.setString(1, eventId);  
                }else {
                  prepStmt.setString(1, null);    
                }
                
                 if(objEventDetails.getEventTitle()!=null && objEventDetails.getEventTitle().trim().length()!=0){
                  prepStmt.setString(2,objEventDetails.getEventTitle());
                }else {
                  prepStmt.setString(2,null);
                }
       
               
                 if(objEventDetails.getOrgId()!=null && objEventDetails.getOrgId().trim().length()!=0){
                  prepStmt.setString(3,objEventDetails.getOrgId());
                }else {
                  prepStmt.setString(3,null);
                }
                
                 if(objEventDetails.getCompYear()!=0){
                  prepStmt.setInt(4, objEventDetails.getCompYear()); 
                }else {
                  prepStmt.setInt(4,0); 
                }
                          
                
                int cnt = prepStmt.executeUpdate();
                Debug.print("Record Inserted succefully into insertProCalEveIntoEveHistoryDetails "+cnt);
                if(cnt >=1) result = true;
                Debug.print("EventCalendarDAO insertProCalEveIntoEveHistoryDetails() Status :" + result);
                prepStmt.close();
                releaseConnection();
            } catch(SQLException sql){
                releaseConnection();
                //Debug.print("SQL Exception in EventCalendarDAO.insertProCalEveIntoEveDetails():" + sql.getMessage());
                sql.printStackTrace();
            } catch(Exception e){
                releaseConnection();
                e.printStackTrace();
                //Debug.print("General Exception  in EventCalendarDAO.insertProCalEveIntoEveDetails():" + e.getMessage());
            }
            
        }
           
        return result;
    }
 
 
 public String getZipCode(String memberId) {
     String zipCode="";
        Debug.print("getZipCode():");
        PreparedStatement prepStmt = null;
        if(memberId!=null && memberId.trim().length()!=0){
           
            try {
                 makeConnection();
                String selectStatement = "select zip from tblContactDetails A,tblMemberDetails B "+
                        "where A.user_id=B.user_id and B.member_id= ?  ";
                
                prepStmt = con.prepareStatement(selectStatement);
                
                prepStmt.setString(1,memberId);
            Debug.print("memberId in set"+memberId);
            rs = prepStmt.executeQuery();
            while(rs.next()) {              
                zipCode = rs.getString(1);
            }
            rs.close();
            Debug.print("EventCalendarDAO getZipCode() zipCode: " + zipCode);
            prepStmt.close();
            releaseConnection();
            } catch(SQLException sql){
                releaseConnection();
                Debug.print("SQL Exception in EventCalendarDAO.getZipCode():" + sql.getMessage());
            } catch(Exception e){
                releaseConnection();
                Debug.print("General Exception  in EventCalendarDAO.getZipCode():" + e.getMessage());
            }
        }
        return zipCode;
    }
 
 public String getArea(String zipCode) {
     String area="";
        Debug.print("getArea():");
        PreparedStatement prepStmt = null;
        if(zipCode!=null && zipCode.trim().length()!=0){
           
            try {
                 makeConnection();
                String selectStatement = "select A.area_id, B.area_name from tblMeeMapStateZip A, tblMeeAreaMaster B where A.area_id = B.area_id "+
                                          "and ? between A.zip_code_from and A.zip_code_to";
                
                prepStmt = con.prepareStatement(selectStatement);
                
                prepStmt.setString(1,zipCode);
            Debug.print("zipCode in set"+zipCode);
            rs = prepStmt.executeQuery();
            while(rs.next()) {              
                area = rs.getString(1);
            }
            rs.close();
            Debug.print("EventCalendarDAO getArea() area: " + area);
            prepStmt.close();
            releaseConnection();
            } catch(SQLException sql){
                releaseConnection();
                Debug.print("SQL Exception in EventCalendarDAO.getArea():" + sql.getMessage());
            } catch(Exception e){
                releaseConnection();
                Debug.print("General Exception  in EventCalendarDAO.getArea():" + e.getMessage());
            }
        }
        return area;
    }
 
        public ArrayList getEventDivisionDetail() throws RemoteException{
        Debug.print("getEventDivisionDetail");
        boolean flag= true;
        ArrayList divisionList = new ArrayList();
        
        try {
            makeConnection();                    
            String selStmt="select event_division_id,event_division_name from "+
                    "tblMeeEventDivisionMaster where oe_status=?";
            
            Debug.print("Query in getEventDivisionDetail: "+selStmt);
            prepStmt = con.prepareStatement(selStmt);
            prepStmt.setBoolean(1, flag);
            rs = prepStmt.executeQuery();
            
            while(rs.next()) {              
                String divisionId = rs.getString(1);
                String divisionName = rs.getString(2);
                Debug.print("divisionId in DAO"+divisionId);
                Debug.print("divisionName in DAO"+divisionName);
                
           String [] divisionDetail ={divisionId,divisionName};
           divisionList.add(divisionDetail);
            }
            Debug.print("divisionDetail in DAO"+divisionList.size());
            
            
            rs.close();
            prepStmt.close();
        } catch(SQLException sqe){
            //throw new EJBException(sqe);
            Debug.print("Exception in EventCalendarDAO.getEventDivisionDetail()");
            sqe.printStackTrace();
        } finally {
            releaseConnection();
        }

        return divisionList;
    } 
        
        
     public ArrayList getSelectedDivisionDetails(String provisionalId) throws RemoteException{
        Debug.print("getSelectedDivisionDetails");
        ArrayList divisionList = new ArrayList();
        
        try {
            makeConnection();                    
            String selStmt="select event_division_id, event_type_id, event_level_id from "+
                    "tblOEChampionshipDetails where pro_calendar_id=?";
            
            Debug.print("Query in getSelectedDivisionDetails: "+selStmt);
            prepStmt = con.prepareStatement(selStmt);
            prepStmt.setString(1, provisionalId);
            rs = prepStmt.executeQuery();
            
            while(rs.next()) {              
                String divisionId = rs.getString(1);
                Debug.print("divisionId in getSelectedDivisionDetails"+divisionId);
                String eventTypeId = rs.getString(2);
                Debug.print("eventTypeId in getSelectedDivisionDetails"+eventTypeId);
                String eventLevelId = rs.getString(3);
                Debug.print("eventLevelId in getSelectedDivisionDetails"+eventLevelId);
                
           String [] divisionDetail ={divisionId,eventTypeId,eventLevelId};
           divisionList.add(divisionDetail);
            }
            Debug.print("divisionDetail in getSelectedDivisionDetails"+divisionList.size());
            
            
            rs.close();
            prepStmt.close();
        } catch(SQLException sqe){
            //throw new EJBException(sqe);
            Debug.print("Exception in EventCalendarDAO.getSelectedDivisionDetails()");
            sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return divisionList;
    } 
     
     public String getEventName(String eventTypeId) throws RemoteException{
        Debug.print("getEventName: "+eventTypeId);
        String eventName = "";
        
        try {
            makeConnection();                    
            String selStmt="select event_type_name from "+
                    "tblMeeEventTypeMaster where event_type_id=?";
            
            Debug.print("Query in getSelectedDivisionDetails: "+selStmt);
            prepStmt = con.prepareStatement(selStmt);
            prepStmt.setString(1, eventTypeId);
            rs = prepStmt.executeQuery();
            
            while(rs.next()) {              
                eventName = rs.getString(1);
                Debug.print("eventName in getEventName"+eventName);
            }
            rs.close();
            prepStmt.close();
        } catch(SQLException sqe){
            //throw new EJBException(sqe);
            Debug.print("Exception in EventCalendarDAO.getEventName()");
            sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return eventName;
    } 
 
 public ArrayList selectApprovedEventDet(String provisionalId, String eventId){
        Debug.print("EventCalendarDAO.selectApprovedEventDet():");
        ArrayList proCalDet = new ArrayList();
               
        makeConnection();
        try {
            String selectStatement ="Select A.pro_calendar_id, A.organizer_id, A.begin_date, A.end_date, A.event_title," +
                    " E.user_id, E.first_name, E.last_name, B.area_code, B.area_name, C.state_name, A.area_chair_approval_status, A.org_approval_status, A.usea_approval_status, A.event_id, A.old_event_id, A.competition_year," +
                    " A.oe_begin_date, A.oe_end_date, A.oe_extended_due_date, A.area_id, A.state_id, A.event_type_id, A.event_levels, A.location, A.city, A.zip, A.issue_id from "+DBHelper.OE_PROVISIONAL_CALENDAR+" A," +
                    DBHelper.OE_AREA_MASTER+" B, "+DBHelper.OE_STATE_MASTER+" C, "+DBHelper.OE_MEMBER_DETAILS+" D, "+DBHelper.OE_USER_MASTER+" E " +
                    " where A.area_id=B.area_id and A.state_id=C.state_id and D.user_id=E.user_id and A.organizer_id=D.member_id "+
                    " and A.pro_calendar_id= ? and event_id= ?";
                   
            PreparedStatement prepSelect = con.prepareStatement(selectStatement);
            prepSelect.setString(1,provisionalId);
            prepSelect.setString(2,eventId);
            ResultSet rs = prepSelect.executeQuery();
            
            while(rs.next()){
                String proCalId = rs.getString(1);
                String organizerId = rs.getString(2);
                Date beginDate=rs.getDate(3);
                Date endDate=rs.getDate(4);
                String eventTitle=rs.getString(5);
                String userId=rs.getString(6);
                String firstName=rs.getString(7);
                String lastName=rs.getString(8);
                String areaCode=rs.getString(9);
                String areaName=rs.getString(10);
                String stateName=rs.getString(11);
                String areaChApprovStat=rs.getString(12);
                String orgApprovStat=rs.getString(13);
                String uStaffApprovStat=rs.getString(14);
                 eventId=rs.getString(15);
                 String oldEventId=rs.getString(16);
                 int compYear=rs.getInt(17);
                Date oeBeginDate=rs.getDate(18);
                Date oeEndDate=rs.getDate(19); 
                Date oeExtndDate=rs.getDate(20); 
                String areaId=rs.getString(21);
                String statId=rs.getString(22); 
                String eveTyId=rs.getString(23);
                String eveLevId=rs.getString(24);             
                String eveLocat=rs.getString(25); 
                String eveCity=rs.getString(26); 
                String eveZip=rs.getString(27); 
                String eveIssueId=rs.getString(28); 
                                                 
                HLCCalendarVO objEventDetails = new HLCCalendarVO();
                
                objEventDetails.setCalenderId(proCalId);
                objEventDetails.setOrganizerId(organizerId);
                objEventDetails.setBeginDate(beginDate);
                objEventDetails.setEndDate(endDate);
                objEventDetails.setEventTitle(eventTitle);
                objEventDetails.setUserId(userId);
                objEventDetails.setOrgFName(firstName);
                objEventDetails.setOrgLName(lastName);
                objEventDetails.setAreaCode(areaCode);
                objEventDetails.setAreaName(areaName);
                objEventDetails.setStateName(stateName);
                objEventDetails.setArearChairApproStatus(areaChApprovStat);
                objEventDetails.setOrgApprovalStatus(orgApprovStat);
                objEventDetails.setApprovalStatus(uStaffApprovStat);
                objEventDetails.setEventId(eventId);
                objEventDetails.setOldEventId(oldEventId);
                objEventDetails.setCompYear(compYear);
                
                objEventDetails.setEntryStrtDate(oeBeginDate);
                objEventDetails.setEntryEndDate(oeEndDate);
                objEventDetails.setExtDueDate(oeExtndDate);
                objEventDetails.setAreaId(areaId);
                objEventDetails.setStateId(statId);
                objEventDetails.setEventTypeId(eveTyId);
                objEventDetails.setEventLevelId(eveLevId);                
                objEventDetails.setEveLocation(eveLocat);
                objEventDetails.setEveCity(eveCity);
                objEventDetails.setEveZip(eveZip);
                objEventDetails.setEveIssueId(eveIssueId);
                                
                proCalDet.add(objEventDetails);
                
            }
            rs.close();
            prepSelect.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            sql.printStackTrace();
        } catch(Exception e){
            releaseConnection();
            e.printStackTrace();
        }
        return proCalDet;
    } 
   
 
 
 public ArrayList selectEveTyLevIdDet(String provisionalId, String eventId){
        Debug.print("EventCalendarDAO.selectEveTyLevIdDet():");
        ArrayList chmDet = new ArrayList();
               
        makeConnection();
        try {
            String selectStatement ="select event_type_id, event_level_id from tblOEChampionshipDetails where pro_calendar_id=? and event_id=?";
                   
            PreparedStatement prepSelect = con.prepareStatement(selectStatement);
            prepSelect.setString(1,provisionalId);
            prepSelect.setString(2,eventId);
            ResultSet rs = prepSelect.executeQuery();
            
            while(rs.next()){
               
                String eveTyId=rs.getString(1);
                String eveLevId=rs.getString(2);   
                                                 
                HLCCalendarVO objEventDetails = new HLCCalendarVO();                                
                objEventDetails.setEventTypeId(eveTyId);
                objEventDetails.setEventLevelId(eveLevId);                           
                chmDet.add(objEventDetails);
                
            }
            rs.close();
            prepSelect.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            sql.printStackTrace();
        } catch(Exception e){
            releaseConnection();
            e.printStackTrace();
        }
        return chmDet;
    } 
   
 
 
 public boolean insertEventDetailsFrmProCal(HLCEventRequestVO objEventDetails) {
        Debug.print("EventCalendarDAO.insertEventDetailsFrmProCal():");
        boolean result = false;
        PreparedStatement prepStmt = null;
        String eventId=objEventDetails.getEvent_id();
        String orgId=objEventDetails.getOrgId();
        Debug.print("EventCalendarDAO.insertEventDetailsFrmProCal():" +eventId);
        Debug.print("EventCalendarDAO.insertEventDetailsFrmProCal():" +orgId);
        
        if(eventId!=null && eventId.trim().length()!=0){
            makeConnection();
            try {
                String insertStatement = "insert into " + DBHelper.OE_EVENT_DETAILS + " "+ 
                        " (event_id, organizer_id, event_title, event_begin_date, event_end_date, oe_begin_date, oe_end_date, oe_extended_due_date, competition_state, championship_area, status_id, competition_location, competition_city, competition_zip, issue_id, event_secretary_id)" +
                        " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
                
                Debug.print("Query: "+insertStatement);
                prepStmt = con.prepareStatement(insertStatement);
                if(eventId!=null && eventId.trim().length()!=0){
                  prepStmt.setString(1, eventId);  
                }else {
                  prepStmt.setString(1, null);    
                }
                if(objEventDetails.getOrgId()!=null && objEventDetails.getOrgId().trim().length()!=0){
                  prepStmt.setString(2, objEventDetails.getOrgId()); 
                }else {
                  prepStmt.setString(2,null); 
                }
                if(objEventDetails.getEventTitle()!=null && objEventDetails.getEventTitle().trim().length()!=0){
                   prepStmt.setString(3, objEventDetails.getEventTitle());
                }else {
                   prepStmt.setString(3, null);  
                }
                if(objEventDetails.getEveBegDate()!=null){
                  prepStmt.setDate(4,DBHelper.toSQLDate(objEventDetails.getEveBegDate()));
                }else {
                  prepStmt.setDate(4,null);    
                }
                
                if(objEventDetails.getEveEndDate()!=null){
                  prepStmt.setDate(5,DBHelper.toSQLDate(objEventDetails.getEveEndDate()));
                }else {
                  prepStmt.setDate(5,null);    
                }
                
                
                 if(objEventDetails.getEveOeBegDate()!=null){
                  prepStmt.setDate(6,DBHelper.toSQLDate(objEventDetails.getEveOeBegDate()));
                }else {
                  prepStmt.setDate(6,null);    
                }
                
                
                 if(objEventDetails.getEveOeEndDate()!=null){
                  prepStmt.setDate(7,DBHelper.toSQLDate(objEventDetails.getEveOeEndDate()));
                }else {
                  prepStmt.setDate(7,null);    
                }
                
                
                 if(objEventDetails.getEveOeExtnDate()!=null){
                  prepStmt.setDate(8,DBHelper.toSQLDate(objEventDetails.getEveOeExtnDate()));
                }else {
                  prepStmt.setDate(8,null);    
                }
                
       
                
                if(objEventDetails.getCompetition_state()!=null && objEventDetails.getCompetition_state().trim().length()!=0){
                prepStmt.setString(9, objEventDetails.getCompetition_state());
                }else {
                  prepStmt.setString(9,null);  
                }
                
              
                if(objEventDetails.getChampionship_area()!=null && objEventDetails.getChampionship_area().trim().length()!=0){
                  prepStmt.setString(10, objEventDetails.getChampionship_area());
                }else{
                  prepStmt.setString(10, null);  
                }
                             
                prepStmt.setString(11,"Approved");
                
                if(objEventDetails.getCompetition_location()!=null && objEventDetails.getCompetition_location().trim().length()!=0){
                  prepStmt.setString(12, objEventDetails.getCompetition_location());
                }else{
                  prepStmt.setString(12, null);  
                }
                
                if(objEventDetails.getCompetition_city()!=null && objEventDetails.getCompetition_city().trim().length()!=0){
                  prepStmt.setString(13, objEventDetails.getCompetition_city());
                }else{
                  prepStmt.setString(13, null);  
                }
               
                if(objEventDetails.getCompetition_zip()!=null && objEventDetails.getCompetition_zip().trim().length()!=0){
                  prepStmt.setString(14, objEventDetails.getCompetition_zip());
                }else{
                  prepStmt.setString(14, null);  
                }
                 
                if(objEventDetails.getIssueId()!=null && objEventDetails.getIssueId().trim().length()!=0){
                  prepStmt.setString(15, objEventDetails.getIssueId());
                }else{
                  prepStmt.setString(15, null);  
                }
                
                if(objEventDetails.getEvent_secretary_id()!=null && objEventDetails.getEvent_secretary_id().trim().length()!=0){
                  prepStmt.setString(16, objEventDetails.getEvent_secretary_id());
                }else{
                  prepStmt.setString(16, null);  
                }
                                                
                int cnt = prepStmt.executeUpdate();
                Debug.print("Record Inserted succefully into insertEventDetailsFrmProCal "+cnt);
                if(cnt >=1) result = true;
                Debug.print("EventCalendarDAO insertEventDetailsFrmProCal() Status :" + result);
                prepStmt.close();
                releaseConnection();
            } catch(SQLException sql){
                releaseConnection();
                //Debug.print("SQL Exception in EventCalendarDAO.insertProCalEveIntoEveDetails():" + sql.getMessage());
                sql.printStackTrace();
            } catch(Exception e){
                releaseConnection();
                e.printStackTrace();
                //Debug.print("General Exception  in EventCalendarDAO.insertProCalEveIntoEveDetails():" + e.getMessage());
            }
            
        }
           
        return result;
    } 

 public String getEveTypLevelMapId(String eventTypeId, String eventLevelId){
        Debug.print("EventCalendarDAO.getEveTypLevelMapId():");
        String mapTypeId = null;
               
        makeConnection();
        try {
            String selectStatement ="select map_type_id from tblMeeMapEventLevel where event_type_id=? and event_level_id=?";
                   
            PreparedStatement prepSelect = con.prepareStatement(selectStatement);
            prepSelect.setString(1,eventTypeId);
            prepSelect.setString(2,eventLevelId);
            ResultSet rs = prepSelect.executeQuery();
            
            while(rs.next()){              
                 mapTypeId=rs.getString(1);
            }
            rs.close();
            prepSelect.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            sql.printStackTrace();
        } catch(Exception e){
            releaseConnection();
            e.printStackTrace();
        }
        return mapTypeId;
    } 
  

 public boolean insertMapDetails(String eventId, String mapTypId) {
        Debug.print("EventCalendarDAO.insertMapDetails():");
        boolean result = false;
        PreparedStatement prepStmt = null;
      
        Debug.print("EventCalendarDAO.insertMapDetails()  mapTypId:" +mapTypId);
         Debug.print("EventCalendarDAO.insertMapDetails()  eventId:" +eventId);
           
        if(mapTypId!=null && mapTypId.trim().length()!=0){
            makeConnection();
            try {
                String insertStatement="insert into tblMeeEventTypeDetails (event_id, map_type_id) values (?,?)";
                
                 prepStmt = con.prepareStatement(insertStatement);
                  prepStmt.setString(1, eventId);                             
                  prepStmt.setString(2, mapTypId);  
            
                int cnt = prepStmt.executeUpdate();
                Debug.print("Record Inserted succefully into insertMapDetails "+cnt);
                if(cnt >=1) result = true;
                Debug.print("EventCalendarDAO insertMapDetails() Status :" + result);
                prepStmt.close();
                releaseConnection();
            } catch(SQLException sql){
                releaseConnection();
                //Debug.print("SQL Exception in EventCalendarDAO.insertProCalEveIntoEveDetails():" + sql.getMessage());
                sql.printStackTrace();
            } catch(Exception e){
                releaseConnection();
                e.printStackTrace();
                //Debug.print("General Exception  in EventCalendarDAO.insertProCalEveIntoEveDetails():" + e.getMessage());
            }
            
        }
           
        return result;
    }
 
 public boolean selectMapDetails(String eventId, String mapTypId) {
        Debug.print("EventCalendarDAO.selectMapDetails():");
        boolean result = false;
        PreparedStatement prepStmt = null;
      
        Debug.print("EventCalendarDAO.selectMapDetails()  mapTypId:" +mapTypId);
         Debug.print("EventCalendarDAO.selectMapDetails()  eventId:" +eventId);
           
        if(mapTypId!=null && mapTypId.trim().length()!=0){
            makeConnection();
            try {
                String insertStatement="select event_id, map_type_id from tblMeeEventTypeDetails where event_id=? and map_type_id=?";
                
                 prepStmt = con.prepareStatement(insertStatement);
                  prepStmt.setString(1, eventId);                             
                  prepStmt.setString(2, mapTypId);  
            
               ResultSet rs = prepStmt.executeQuery();
            
            while(rs.next()){              
                String eveId=rs.getString(1);
                 String mapTypeId=rs.getString(1);
                 result=true;
            }
                prepStmt.close();
                releaseConnection();
            } catch(SQLException sql){
                releaseConnection();
                //Debug.print("SQL Exception in EventCalendarDAO.insertProCalEveIntoEveDetails():" + sql.getMessage());
                sql.printStackTrace();
            } catch(Exception e){
                releaseConnection();
                e.printStackTrace();
                //Debug.print("General Exception  in EventCalendarDAO.insertProCalEveIntoEveDetails():" + e.getMessage());
            }
            
        }
           
        return result;
    }
 
 public ArrayList selectChampionEveLevelDets(String eventId){
        Debug.print("EventsDAO.selectChampionEveLevelDets():");
        Debug.print("EventsDAO.selectChampionEveLevelDets(): eventId"+eventId);
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
                  Debug.print("EventsDAO.selectChampionEveLevelDets(): proCalId" +proCalId); 
                  Debug.print("EventsDAO.selectChampionEveLevelDets(): eveTypeId" +eveTypId); 
                  Debug.print("EventsDAO.selectChampionEveLevelDets(): eveLevelId" +eveLevelId);
                  Debug.print("EventsDAO.selectChampionEveLevelDets(): eveLevCode" +eveLevCode); 
                  
                String[] eveList = {proCalId,eveLevelId,eveLevCode,eveTypId,eveTypeName};
                eveLevList.add(eveList);
            }  
          Debug.print("EventsDAO.selectChampionEveLevelDets(): eveLevList1" +eveLevList.size());              
                                                        
            rs.close();
            prepStmt.close();
            releaseConnection();       
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in EventsDAO.selectChampionEveLevelDets():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in EventsDAO.selectChampionEveLevelDets():" + e.getMessage());
        }
        return eveLevList;
    }
  
 public boolean deleteEveTypeDets(String eventId) {
        Debug.print("EventCalendarDAO.selectMapDetails():");
        boolean result = false;
        PreparedStatement prepStmt = null;        
         Debug.print("EventCalendarDAO.selectMapDetails()  eventId:" +eventId);
           
        if(eventId!=null && eventId.trim().length()!=0){
            makeConnection();
            try {
                String insertStatement="delete from tblMeeEventTypeDetails where event_id=?";
                
                 prepStmt = con.prepareStatement(insertStatement);
                  prepStmt.setString(1, eventId);                                                      
               int cnt = prepStmt.executeUpdate();
            
            if(cnt>=1){
                
               result=true; 
            }
                prepStmt.close();
                releaseConnection();
            } catch(SQLException sql){
                releaseConnection();
                //Debug.print("SQL Exception in EventCalendarDAO.insertProCalEveIntoEveDetails():" + sql.getMessage());
                sql.printStackTrace();
            } catch(Exception e){
                releaseConnection();
                e.printStackTrace();
                //Debug.print("General Exception  in EventCalendarDAO.insertProCalEveIntoEveDetails():" + e.getMessage());
            }
            
        }
           
        return result;
    }
 
 public boolean updateEveDetsInMeeEventTable(HLCCalendarVO calVO) {
        Debug.print("EventCalendarDAO.updateEveDetsInMeeEventTable():");
        boolean result = false;
        PreparedStatement prepStmt = null;
       
        String eventId=calVO.getEventId();
      
        String insertStatement=null;
        Debug.print("EventId in EventCalendarDAO.updateEveDetsInMeeEventTable()"+eventId);
        if(eventId!=null && eventId.trim().length()!=0){
            makeConnection();
            try {
                 insertStatement = "update "+DBHelper.OE_EVENT_DETAILS+" set  event_title = ?, event_begin_date = ?,"+
                        " event_end_date =?, oe_begin_date = ?, oe_end_date = ?, oe_extended_due_date = ?, " +
                        "championship_area =?, competition_state =? where event_id = ?" ;
                                         
               prepStmt = con.prepareStatement(insertStatement);                
               calVO.setEventId(eventId);
                               
                prepStmt.setString(1, calVO.getEventTitle());
                if(calVO.getBeginDate()!=null){
                    prepStmt.setDate(2, DBHelper.toSQLDate(calVO.getBeginDate()));
                } else{
                    prepStmt.setDate(2, null);
                }
                 
                 
                if(calVO.getEndDate()!=null){
                    prepStmt.setDate(3, DBHelper.toSQLDate(calVO.getEndDate()));
                } else{
                    prepStmt.setDate(3, null);
                }
               
                
               if(calVO.getEntryStrtDate()!=null){
                    prepStmt.setDate(4, DBHelper.toSQLDate(calVO.getEntryStrtDate()));
                } else{
                    prepStmt.setDate(4, null);
                }
                
                if(calVO.getEntryEndDate()!=null){
                    prepStmt.setDate(5, DBHelper.toSQLDate(calVO.getEntryEndDate()));
                } else{
                    prepStmt.setDate(5, null);
                }
                
                if(calVO.getExtDueDate()!=null){
                    prepStmt.setDate(6, DBHelper.toSQLDate(calVO.getExtDueDate()));
                } else{
                    prepStmt.setDate(6, null);
                } 
                prepStmt.setString(7, calVO.getAreaId());
                prepStmt.setString(8, calVO.getStateId());             
                prepStmt.setString(9, eventId);  
                                                                                        
                int cnt = prepStmt.executeUpdate();
                Debug.print("Record Inserted succefully into provisional calendar table in updateEveDetsInMeeEventTable"+cnt);
                if(cnt>=1){
                    result = true;
                }
                Debug.print("EventCalendarDAO provisional calendar table Status in updateEveDetsInMeeEventTable:" + result);
                prepStmt.close();
                releaseConnection();
            } catch(SQLException sql){
                releaseConnection();
                Debug.print("SQL Exception in EventCalendarDAO.updateEveDetsInMeeEventTable():" + sql.getMessage());
            } catch(Exception e){
                releaseConnection();
                Debug.print("General Exception  in EventCalendarDAO.updateEveDetsInMeeEventTable():" + e.getMessage());
            }
        }
        /*if(result==true) {
            try{
                boolean deleteResult = deleteChampDetails(eventId);
                Debug.print("EventCalendarDAO.deleteChampDetails() result: " +  deleteResult);
            } catch(Exception e){
                Debug.print("General Exception in EventCalendarDAO.deleteChampDetails():" + e.getMessage());
            }
        }*/
        return result;
    }

public boolean updateEveDetsInMeeEventTableByAdmin(HLCCalendarVO calVO) {
        Debug.print("EventCalendarDAO.updateEveDetsInMeeEventTableByAdmin():");
        boolean result = false;
        PreparedStatement prepStmt = null;
       
        String eventId=calVO.getEventId();
      
        String insertStatement=null;
        Debug.print("calendarId in EventCalendarDAO.updateEveDetsInMeeEventTableByAdmin()"+eventId);
        if(eventId!=null && eventId.trim().length()!=0){
            makeConnection();
            try {
                 insertStatement = "update "+DBHelper.OE_EVENT_DETAILS+" set  event_title = ?, event_begin_date = ?,"+
                        " event_end_date =?, oe_begin_date = ?, oe_end_date = ?, oe_extended_due_date = ?, " +
                        "championship_area =?, competition_state =?, event_secretary_id =?,issue_id=?, organizer_id=? " +
                        "where event_id = ?" ;
                                         
               prepStmt = con.prepareStatement(insertStatement);                
               calVO.setEventId(eventId);
                               
                prepStmt.setString(1, calVO.getEventTitle());
                if(calVO.getBeginDate()!=null){
                    prepStmt.setDate(2, DBHelper.toSQLDate(calVO.getBeginDate()));
                } else{
                    prepStmt.setDate(2, null);
                }
                 
                 
                if(calVO.getEndDate()!=null){
                    prepStmt.setDate(3, DBHelper.toSQLDate(calVO.getEndDate()));
                } else{
                    prepStmt.setDate(3, null);
                }
               
                
               if(calVO.getEntryStrtDate()!=null){
                    prepStmt.setDate(4, DBHelper.toSQLDate(calVO.getEntryStrtDate()));
                } else{
                    prepStmt.setDate(4, null);
                }
                
                if(calVO.getEntryEndDate()!=null){
                    prepStmt.setDate(5, DBHelper.toSQLDate(calVO.getEntryEndDate()));
                } else{
                    prepStmt.setDate(5, null);
                }
                
                if(calVO.getExtDueDate()!=null){
                    prepStmt.setDate(6, DBHelper.toSQLDate(calVO.getExtDueDate()));
                } else{
                    prepStmt.setDate(6, null);
                } 
                prepStmt.setString(7, calVO.getAreaId());
                prepStmt.setString(8, calVO.getStateId());
                if(calVO.getEveSecreId()!=null){
                    prepStmt.setString(9, calVO.getEveSecreId());
                } else{
                    prepStmt.setString(9, null);
                } 
                prepStmt.setString(10,calVO.getEveIssueId());
                
                if(calVO.getOrganizerId()!=null){
                    prepStmt.setString(11, calVO.getOrganizerId());
                } else{
                    prepStmt.setString(11, null);
                } 
                prepStmt.setString(12, eventId); 
       
                                                                                        
                int cnt = prepStmt.executeUpdate();
                Debug.print("Record Inserted succefully into provisional calendar table in updateEveDetsInMeeEventTableByAdmin"+cnt);
                if(cnt>=1){
                    result = true;
                }
                Debug.print("EventCalendarDAO provisional calendar table Status in updateEveDetsInMeeEventTableByAdmin:" + result);
                prepStmt.close();
                releaseConnection();
            } catch(SQLException sql){
                releaseConnection();
                Debug.print("SQL Exception in EventCalendarDAO.updateEveDetsInMeeEventTableByAdmin():" + sql.getMessage());
            } catch(Exception e){
                releaseConnection();
                Debug.print("General Exception  in EventCalendarDAO.updateEveDetsInMeeEventTableByAdmin():" + e.getMessage());
            }
        }
        /*if(result==true) {
            try{
                boolean deleteResult = deleteChampDetails(eventId);
                Debug.print("EventCalendarDAO.deleteChampDetails() result: " +  deleteResult);
            } catch(Exception e){
                Debug.print("General Exception in EventCalendarDAO.deleteChampDetails():" + e.getMessage());
            }
        }*/
        return result;
    }

 
 public ArrayList selectAllIssues(){
        Debug.print("EventsDAO.selectAllIssues():");
        ArrayList issueList = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
        try {
            String selectStatement="SELECT issue_id, issue_name from tblAdvIssueMaster " +
                    "where omnibus_status='True'";
            
            prepStmt = con.prepareStatement(selectStatement);
            rs = prepStmt.executeQuery();
            issueList = new ArrayList();
            while(rs.next()){
                String issId = rs.getString(1);
                String issName = rs.getString(2);              
                String[] strIssueList = {issId, issName};
                issueList.add(strIssueList);
            }
            rs.close();
            prepStmt.close();
          releaseConnection();   
        } catch(SQLException sql){
          releaseConnection();   
            Debug.print("SQL Exception in EventsDAO.selectAllIssues():" + sql.getMessage());
        } catch(Exception e){
           releaseConnection();     
            Debug.print("General Exception  in EventsDAO.selectAllIssues():" + e.getMessage());
        }
        return issueList;
    }

 
 public String getEveSecretaryId(String eventId){
        Debug.print("EventCalendarDAO.getEveSecretaryId():");
        String eveSecreId = null;
               
        makeConnection();
        try {
            String selectStatement ="select event_secretary_id from tblMeeEventDetails where event_id=?";
                   
            PreparedStatement prepSelect = con.prepareStatement(selectStatement);
            prepSelect.setString(1,eventId);
            
            ResultSet rs = prepSelect.executeQuery();
            
            while(rs.next()){              
                 eveSecreId=rs.getString(1);
            }
            rs.close();
            prepSelect.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            sql.printStackTrace();
        } catch(Exception e){
            releaseConnection();
            e.printStackTrace();
        }
        return eveSecreId;
    } 
  
 
  public String getUserName(String eveSecreId){
        Debug.print("EventCalendarDAO.getUserName():");
        String usrName = "";
               
        makeConnection();
        try {
      String selectStatement ="select login_name from tblUserMaster where user_id=?";
                   
            PreparedStatement prepSelect = con.prepareStatement(selectStatement);
            prepSelect.setString(1,eveSecreId);
            
            ResultSet rs = prepSelect.executeQuery();
            
            while(rs.next()){              
                 usrName=rs.getString(1);
            }           
            rs.close();
            prepSelect.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            sql.printStackTrace();
        } catch(Exception e){
            releaseConnection();
            e.printStackTrace();
        }
        return usrName;
    } 
  
 public String getEveSecretaryIdBasedOnMemId(String membId){
        Debug.print("EventCalendarDAO.getEveSecretaryIdBasedOnMemId():");
        String eveSecreId = null;
               
        makeConnection();
        try {
            String selectStatement ="select user_id from tblMemberDetails where member_id=?";
                   
            PreparedStatement prepSelect = con.prepareStatement(selectStatement);
            prepSelect.setString(1,membId);
            
            ResultSet rs = prepSelect.executeQuery();
            
            while(rs.next()){              
                 eveSecreId=rs.getString(1);
            }
            rs.close();
            prepSelect.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            sql.printStackTrace();
        } catch(Exception e){
            releaseConnection();
            e.printStackTrace();
        }
        return eveSecreId;
    } 
   
  
  public String getEveSecretaryIdBasedOnUsr(String usrName){
        Debug.print("EventCalendarDAO.getEveSecretaryIdBasedOnUsr():");
        String eveSecreId = null;
               
        makeConnection();
        try {
            String selectStatement ="select user_id from tblUserMaster where login_name=?";
                   
            PreparedStatement prepSelect = con.prepareStatement(selectStatement);
            prepSelect.setString(1,usrName);
            
            ResultSet rs = prepSelect.executeQuery();
            
            while(rs.next()){              
                 eveSecreId=rs.getString(1);
            }
            rs.close();
            prepSelect.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            sql.printStackTrace();
        } catch(Exception e){
            releaseConnection();
            e.printStackTrace();
        }
        return eveSecreId;
    } 
   
 public boolean insertEveSecreRelationDets(String eveOrgId, String eveSecreId,String eventId) {
        Debug.print("EventCalendarDAO.insertEveSecreRelationDets(): For event Secratary Id"+eveSecreId +" Event Oragni. Id"+eveOrgId);
        boolean result = false;
        String secretary_relation_id=null;
        try
        {
            secretary_relation_id=getNextId();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        PreparedStatement prepStmt = null;        
         Debug.print("EventCalendarDAO.insertEveSecreRelationDets()  eventId:" +eventId);
           int cnt=0;
        if(eventId!=null && eventId.trim().length()!=0){
            makeConnection();
            try {
                
                String selectStatement="select count(*) from tblMeeSecretaryRelationDetails where event_id=?";
                
                 prepStmt = con.prepareStatement(selectStatement);
                  prepStmt.setString(1, eventId);     
                  
              ResultSet rs = prepStmt.executeQuery();
                if(rs.next()){
                    cnt = rs.getInt(1);
                }
               if(cnt==0){
                   
        String insertStatement = "insert into tblMeeSecretaryRelationDetails " +
                "(secretary_relation_id,event_organizer_id, event_secretary_id, event_id,add_date)" +
                    " values (?, ?, ?, ?,?) ";
            
            prepStmt = con.prepareStatement(insertStatement);
            //Debug.print("EventCalendarDAO insertEventDetails:" + provCalId);
            
            //if(provCalId!=null || provCalId.trim().length()!=0){
            prepStmt.setString(1,secretary_relation_id);
            prepStmt.setString(2, eveOrgId);
            prepStmt.setString(3, eveSecreId);
             prepStmt.setString(4, eventId);
             prepStmt.setDate(5,new java.sql.Date(new java.util.Date().getTime()));
             
             int cnt1 = prepStmt.executeUpdate();
                Debug.print("Record Inserted succefully into tblMeeSecretaryRelationDetails "+cnt1);
                
                if(cnt1>=1){
                    result = true;
                }               
               }   
               else{
                  
    String updateStatement = "update tblMeeSecretaryRelationDetails set " +
            "event_organizer_id = ?, event_secretary_id = ? where event_id = ?";
                
                prepStmt = con.prepareStatement(updateStatement);
              
                prepStmt.setString(1, eveOrgId);
                prepStmt.setString(2, eveSecreId);
                prepStmt.setString(3, eventId);
                
                int cnt2 = prepStmt.executeUpdate();
                Debug.print("Record Inserted succefully into tblMeeSecretaryRelationDetails "+cnt2);
                
                if(cnt2>=1){
                    result = true;
                }               
                  
               }
                           
                prepStmt.close();
                releaseConnection();
            } catch(SQLException sql){
                releaseConnection();
                //Debug.print("SQL Exception in EventCalendarDAO.insertProCalEveIntoEveDetails():" + sql.getMessage());
                sql.printStackTrace();
            } catch(Exception e){
                releaseConnection();
                e.printStackTrace();
                //Debug.print("General Exception  in EventCalendarDAO.insertProCalEveIntoEveDetails():" + e.getMessage());
            }
            
        }
           
        return result;
    }
  

 public ArrayList selectEveLevTypDets(String provisionalId) throws SQLException {
        ArrayList eveLevTypList = new ArrayList();
        Debug.print("Inside DAO selectEveLevTypDets");
        try {
            makeConnection();
            
            String selStatement = "Select B.event_level_code, D.event_type_name, E.event_division_name from tblOEChampionshipDetails A, " +
                    "tblMeeEventLevelMaster B, tblOEProvisionalCalendar C, tblMeeEventTypeMaster D, tblMeeEventDivisionMaster E " +
                    "where A.event_level_id=B.event_level_id and A.event_id=C.event_id and A.event_type_id=D.event_type_id " +
                    "and E.event_division_id=A.event_division_id and C.pro_calendar_id = ?";
                      
            PreparedStatement prepStmt = con.prepareStatement(selStatement);
            prepStmt.setString(1,provisionalId);
           
            
            ResultSet rs = prepStmt.executeQuery();
             
            while (rs.next()){
                String eveLeveCode=rs.getString(1);
                String eveType=rs.getString(2);
                String eveDiv=rs.getString(3);
                
                String[] temp={eveLeveCode,eveType,eveDiv};
                eveLevTypList.add(temp);
            }
            Debug.print("ArrayList Size: "+eveLevTypList.size());
            prepStmt.close();
            releaseConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return eveLevTypList;
    }
 //kalai starts
 public ArrayList getViews() throws RemoteException{
	 ArrayList ViewsList = new ArrayList();
     Debug.print("Inside DAO getViews()");
     try {
         makeConnection();
         
         String selStatement = "select view_id,view_name from tblViewMaster";
                   
         PreparedStatement prepStmt = con.prepareStatement(selStatement);
          
         
         ResultSet rs = prepStmt.executeQuery();
          
         while (rs.next()){
             String view_id=rs.getString(1);
             String view_name=rs.getString(2);
             
             
             String[] temp={view_id,view_name};
             ViewsList.add(temp);
         }
         Debug.print("ArrayList Size: "+ViewsList.size());
         prepStmt.close();
         releaseConnection();
     }catch (Exception e) {
         e.printStackTrace();
     }
     return ViewsList;

 }
 
 public ArrayList getGroupValues() throws RemoteException{
	 ArrayList GroupValuesList = new ArrayList();
     Debug.print("Inside DAO getViews()");
     try {
         makeConnection();
         
         String selStatement = "select master_id,master_name from tblframeworkmaster where show_master='1'";
                   
         PreparedStatement prepStmt = con.prepareStatement(selStatement);
          
         
         ResultSet rs = prepStmt.executeQuery();
          
         while (rs.next()){
             String master_id=rs.getString(1);
             String master_name=rs.getString(2);
             
             
             String[] temp={master_id,master_name};
             GroupValuesList.add(temp);
         }
         Debug.print("ArrayList Size: "+GroupValuesList.size());
         prepStmt.close();
         releaseConnection();
     }catch (Exception e) {
         e.printStackTrace();
     }
     return GroupValuesList;

 }
 
 
 public ArrayList getViewRelatedRoles() throws RemoteException{
	 
	 ArrayList viewRelatedRolesList = new ArrayList();
     Debug.print("Inside DAO getViewRelatedRoles()");
     try {
         makeConnection();
         
         String selStatement = "select role_id,role_name from tblRoleMaster where status='true'";
                   
         PreparedStatement prepStmt = con.prepareStatement(selStatement);
          
         
         ResultSet rs = prepStmt.executeQuery();
          
         while (rs.next()){
             String role_id=rs.getString(1);
             String role_name=rs.getString(2);            
             
             String[] temp={role_id,role_name};
             viewRelatedRolesList.add(temp);
         }
         Debug.print("ArrayList Size: "+viewRelatedRolesList.size());
         prepStmt.close();
         releaseConnection();
     }catch (Exception e) {
         e.printStackTrace();
     }
     return viewRelatedRolesList;
 }
 
 public ArrayList getViewRelatedRoleUsers(String viewRoleId) throws RemoteException{
	 ArrayList ViewRelatedRoleUsersList = new ArrayList();
     Debug.print("Inside DAO getViewRelatedRoleUsers()");
     try {
         makeConnection();
         
         String selStatement = "select a.user_id,a.login_name,a.first_name,a.last_name from tblUserMaster a, tblMapUserPrivilege b where a.user_id = b.user_id and b.role_id=?";
                   
         PreparedStatement prepStmt = con.prepareStatement(selStatement);
         prepStmt.setString(1, viewRoleId);
          
         
         ResultSet rs = prepStmt.executeQuery();
          
         while (rs.next()){
             String user_id=rs.getString(1);
             String login_name=rs.getString(2); 
             String first_name=rs.getString(3);
             String last_name=rs.getString(4); 
             
             String[] temp={user_id,login_name,first_name,last_name};
             ViewRelatedRoleUsersList.add(temp);
         }
         Debug.print("ArrayList Size: "+ViewRelatedRoleUsersList.size());
         prepStmt.close();
         releaseConnection();
     }catch (Exception e) {
         e.printStackTrace();
     }
     return ViewRelatedRoleUsersList;
 }
 
 public ArrayList  getviewRoleLOB(String viewRoleId) throws RemoteException{
	 ArrayList viewRoleLOBsList = new ArrayList();
     Debug.print("Inside DAO getviewRoleLOB()");
     try {
         makeConnection();
         
         String selStatement = "select a.layer_value_id,a.layer_value from tblFrameworkLayermap a,tblMapArtifact b where b.lob_value_id=a.layer_value_id and b.view_id=?";
                   
         PreparedStatement prepStmt = con.prepareStatement(selStatement);
         prepStmt.setString(1, viewRoleId);
          
         
         ResultSet rs = prepStmt.executeQuery();
          
         while (rs.next()){
             String layer_id=rs.getString(1);
             String layer_name=rs.getString(2); 
                          
             String[] temp={layer_id,layer_name};
             viewRoleLOBsList.add(temp);
         }
         Debug.print("ArrayList Size: "+viewRoleLOBsList.size());
         prepStmt.close();
         releaseConnection();
     }catch (Exception e) {
         e.printStackTrace();
     }
     return viewRoleLOBsList;
	 
 }
 
 
 public ArrayList  getUserMapLOBList() throws RemoteException{
	 ArrayList viewUserMapLOBList = new ArrayList();
     Debug.print("Inside DAO getUserMapLOBList()");
     try {
         makeConnection();
         
         String selStatement = "select a.role_name , b.login_name, c.layer_value from tblrolemaster a," +
         		" tblusermaster b, tblframeworklayermap c , tblmapuserview d where d.role_view_id = a.role_id and" +
         		" d.user_view_id = b.user_id and d.lob_view_id = c.layer_value_id";

           Debug.print("getUserMapLOBList selStatement======="+selStatement);
         PreparedStatement prepStmt = con.prepareStatement(selStatement);
       //  prepStmt.setString(1, viewRoleId);
          
         
         ResultSet rs = prepStmt.executeQuery();
          
         while (rs.next()){
             String role_name=rs.getString(1);
             String login_name=rs.getString(2); 
             String lob_value=rs.getString(3); 
                          
             String[] temp={role_name,login_name,lob_value};
             viewUserMapLOBList.add(temp);
         }
         Debug.print("ArrayList Size: "+viewUserMapLOBList.size());
         prepStmt.close();
         releaseConnection();
     }catch (Exception e) {
         e.printStackTrace();
     }
     return viewUserMapLOBList;
	 
 }
 
 
 
 public boolean insertLobViewpointMap(String viewRoleId,String viewUserId,String viewLobId,String[] viewPointId) throws RemoteException
 {
	 boolean result = false;
     PreparedStatement prepStmt = null;
     
     
     makeConnection();
     try {
    	
    	/* for(int i=0;i<=viewPointId.length;i++)
    	 {
    	 String insertStatement  = "insert into tblmapuserview (role_view_id,user_view_id,lob_view_id,point_view_id)" +
                 " values ( ?, ?,?,?) ";         
     
         prepStmt = con.prepareStatement(insertStatement);             
         prepStmt.setString(1, viewRoleId);
         prepStmt.setString(2, viewUserId);
         prepStmt.setString(3, viewLobId);
         prepStmt.setString(4, viewPointId[i]);
         int cnt = prepStmt.executeUpdate();
         Debug.print("Record Inserted successfully in insertGroupDetails(): " + cnt);
         result = true;
    	 }*/
    	 String insertStatement  = "insert into tblmapuserview (role_view_id,user_view_id,lob_view_id)" +
                 " values ( ?, ?,?) ";         
     
         prepStmt = con.prepareStatement(insertStatement);             
         prepStmt.setString(1, viewRoleId);
         prepStmt.setString(2, viewUserId);
         prepStmt.setString(3, viewLobId);
        // prepStmt.setString(4, viewPointId[i]);
         int cnt = prepStmt.executeUpdate();
         Debug.print("Record Inserted successfully in insertGroupDetails(): " + cnt);
         result = true;
         Debug.print("EventCalendarDAO.insertGroupDetails(): Status :" + result);
      
         prepStmt.close();
         releaseConnection();
     } catch(SQLException sql){
         releaseConnection();
         Debug.print("SQL Exception in EventCalendarDAO.insertGroupDetails():" + sql.getMessage());
     } catch(Exception e){
         releaseConnection();
         Debug.print("General Exception  in EventCalendarDAO.insertGroupDetails():" + e.getMessage());
     }
     return result;
 }
 
 public ArrayList  getviewRoleViewPoint() throws RemoteException{
	 ArrayList getviewRoleViewPoint = new ArrayList();
     Debug.print("Inside DAO getviewRoleViewPoint()");
     try {
         makeConnection();
         
         String selStatement = "select layer_value_id,layer_value from tblFrameworkLayermap where master_id='9C3914B5-435A-4ED9-9E22-EE92BD700178'";
                   
         PreparedStatement prepStmt = con.prepareStatement(selStatement);
      
          
         
         ResultSet rs = prepStmt.executeQuery();
          
         while (rs.next()){
             String layer_id=rs.getString(1);
             String layer_name=rs.getString(2); 
                          
             String[] temp={layer_id,layer_name};
             getviewRoleViewPoint.add(temp);
         }
         Debug.print("ArrayList Size: "+getviewRoleViewPoint.size());
         prepStmt.close();
         releaseConnection();
     }catch (Exception e) {
         e.printStackTrace();
     }
     return getviewRoleViewPoint;
	 
 }
 
 public int selectCount(String masterId) throws RemoteException
 {
	int count=0;
     Debug.print("Inside DAO selectCount()");
     try {
         makeConnection();
         
         String selStatement = "select count(1) from tblframeworkmaster where master_sequence ='0'and master_id=?";
                   
         PreparedStatement prepStmt = con.prepareStatement(selStatement);
         prepStmt.setString(1, masterId);
         
         ResultSet rs = prepStmt.executeQuery();
          
         while (rs.next()){
              count=rs.getInt(1);
             
         }
        
         prepStmt.close();
         releaseConnection();
     }catch (Exception e) {
         e.printStackTrace();
     }
     return count;

 }
 public boolean insertGroupDetails(String groupId,String groupDet) throws RemoteException
 {
	 boolean result = false;
     PreparedStatement prepStmt = null;
     
     
     makeConnection();
     try {
    	
    	 String insertStatement  = "insert into tblframeworklayermap (master_id, layer_value)" +
                 " values ( ?, ?) ";         
     
         prepStmt = con.prepareStatement(insertStatement);             
         prepStmt.setString(1, groupId);
         prepStmt.setString(2, groupDet);
  
         int cnt = prepStmt.executeUpdate();
         Debug.print("Record Inserted successfully in insertGroupDetails(): " + cnt);
         result = true;
         Debug.print("EventCalendarDAO.insertGroupDetails(): Status :" + result);
      
         prepStmt.close();
         releaseConnection();
     } catch(SQLException sql){
         releaseConnection();
         Debug.print("SQL Exception in EventCalendarDAO.insertGroupDetails():" + sql.getMessage());
     } catch(Exception e){
         releaseConnection();
         Debug.print("General Exception  in EventCalendarDAO.insertGroupDetails():" + e.getMessage());
     }
     return result;
     
 }
 public ArrayList getViewGroupValues(String group_id) throws RemoteException{
	 ArrayList getViewGroupValuesList = new ArrayList();
     Debug.print("Inside DAO getViewGroupValues()");
     try {
         makeConnection();
         
         String selStatement = "select a.layer_value_id,a.master_id,a.layer_value,c.master_name from tblframeworklayermap a,tblframeworkmaster c where  a.master_id=c.master_id  and a.master_id=?";
                   
         PreparedStatement prepStmt = con.prepareStatement(selStatement);
       
         prepStmt.setString(1, group_id);
         
         ResultSet rs = prepStmt.executeQuery();
          
         while (rs.next()){
             String layer_value_id=rs.getString(1);
           
             String master_id=rs.getString(2);
             String layer_value=rs.getString(3);
            
             String master_name=rs.getString(4);             
             
             String[] temp={layer_value_id,master_id,layer_value,master_name};
             getViewGroupValuesList.add(temp);
         }
         Debug.print("ArrayList Size: "+getViewGroupValuesList.size());
         prepStmt.close();
         releaseConnection();
     }catch (Exception e) {
         e.printStackTrace();
     }
     return getViewGroupValuesList;
 }
 
 public boolean updateGroupDetails(String layerId,String groupId,String groupDet) throws RemoteException{
	 boolean result = false;
     PreparedStatement prepStmt = null;
         
     makeConnection();
     try {
         String updateStatement = "update tblframeworklayermap set master_id=?,layer_value=? where layer_value_id=? ";
         
         prepStmt = con.prepareStatement(updateStatement);
             
             prepStmt.setString(1, groupId);
         prepStmt.setString(2, groupDet);
         prepStmt.setString(3, layerId);
         
         int cnt = prepStmt.executeUpdate();
         Debug.print("Record updated successfully in updateGroupDetails(): " + cnt);
         result = true;
         Debug.print("EventCalendarDAO.updateGroupDetails(): Status :" + result);
      
         prepStmt.close();
         releaseConnection();
     } catch(SQLException sql){
         releaseConnection();
         Debug.print("SQL Exception in EventCalendarDAO.updateGroupDetails():" + sql.getMessage());
     } catch(Exception e){
         releaseConnection();
         Debug.print("General Exception  in EventCalendarDAO.updateGroupDetails():" + e.getMessage());
     }
     return result;
 }
 
 public boolean alreadyExistCheck(String groupId,String groupDet) throws RemoteException{
	 boolean alreadyExistCheckStatus=false;
	 try {
         makeConnection();
         
         String selStatement = "select layer_value_id,master_id,layer_value from tblframeworklayermap  where master_id=? and layer_value=?";
                   
         PreparedStatement prepStmt = con.prepareStatement(selStatement);
         
         prepStmt.setString(1, groupId);
         prepStmt.setString(2, groupDet);
         ResultSet rs = prepStmt.executeQuery();
          
         while (rs.next()){
        	 alreadyExistCheckStatus=true;
         }
       
         prepStmt.close();
         releaseConnection();
     }catch (Exception e) {
         e.printStackTrace();
     }
     return alreadyExistCheckStatus;
 }
 
 public boolean deleteGroupValues(String[] chkIds) throws RemoteException{
	 boolean delStatus = false;
     PreparedStatement prepStmt = null;
     ResultSet rs = null;
     makeConnection();
     try {

         String selectStatement = "delete from tblframeworklayermap where layer_value_id=?";
         for (int i = 0; i < chkIds.length; i++) {

             
             prepStmt = con.prepareStatement(selectStatement);
             prepStmt.setString(1, chkIds[i]);
             int delResult = prepStmt.executeUpdate();

             if (delResult > 0) {
                 delStatus = true;
             }

         }
         rs.close();
         prepStmt.close();
         releaseConnection();

     } catch (SQLException sql) {
         releaseConnection();
        
     } catch (Exception e) {
         releaseConnection();
       
     }
     return delStatus;
 }
 
 public String getGroupEditList(String layerId) throws RemoteException{
	  String layer_value="";
	 try {
         makeConnection();
       
         String selStatement = "select layer_value from tblframeworklayermap  where layer_value_id=?";
                   
         PreparedStatement prepStmt = con.prepareStatement(selStatement);
         
         prepStmt.setString(1, layerId);
        
         ResultSet rs = prepStmt.executeQuery();
          
         while (rs.next()){
              layer_value=rs.getString(1);
         }
       
         prepStmt.close();
         releaseConnection();
     }catch (Exception e) {
         e.printStackTrace();
     }
     return layer_value;
 }
 public ArrayList getSubViewList(String lobId,String viewId)throws RemoteException
 {
	 ArrayList getSubViewList = new ArrayList();
     Debug.print("Inside DAO getSubViewList()");
     try {
         makeConnection();
         
         String selStatement = "select distinct a.layer_value,a.layer_value_id from tblframeworklayermap a,tblmapartifact b where a.view_point_id=b.view_point_value_id and b.view_point_value_id=? and b.lob_value_id=?";
                   
         PreparedStatement prepStmt = con.prepareStatement(selStatement);
       
         prepStmt.setString(1, viewId);
         prepStmt.setString(2, lobId);
         ResultSet rs = prepStmt.executeQuery();
          
         while (rs.next()){
             String layer_value=rs.getString(1);           
             String layer_value_id=rs.getString(2);               
             
             String[] temp={layer_value,layer_value_id};
             getSubViewList.add(temp);
         }
         Debug.print("ArrayList Size: "+getSubViewList.size());
         prepStmt.close();
         releaseConnection();
     }catch (Exception e) {
         e.printStackTrace();
     }
     return getSubViewList;
	 
 }
 public Map getSubViewMap(String lobId,String viewId)throws RemoteException
 {
	 HashMap hs=new HashMap();
	 try {
         makeConnection();
         
         String selStatement = "select a.layer_value,a.layer_value_id from tblframeworklayermap a,tblmapartifact b where a.layer_value_id=b.view_point_value_id and b.view_id=? and b.lob_value_id=?";
                   
         PreparedStatement prepStmt = con.prepareStatement(selStatement);
       
         prepStmt.setString(1, viewId);
         prepStmt.setString(2, lobId);
         ResultSet rs = prepStmt.executeQuery();
          int i=0;
         while (rs.next()){
             String layer_value=rs.getString(1);           
             String layer_value_id=rs.getString(2);               
             
             String[] temp={layer_value,layer_value_id};
             hs.put(i, temp);
             i++;
         }
       //  Debug.print("ArrayList Size: "+getSubViewList.size());
         prepStmt.close();
         releaseConnection();
     }catch (Exception e) {
         e.printStackTrace();
     }
	 return hs;
	 
 }
 public ArrayList getTreeArtifactDetails(String user_Id,String lobId)throws RemoteException{
	 ArrayList getTreeArtifactDetails = new ArrayList();
     Debug.print("Inside DAO getTreeArtifactDetails()");
     try {
         makeConnection();
         
         /*String selStatement = "select (select view_point_name from tblviewpointmaster where view_point_id = a.view_id) as View_Point,(select layer_value from tblframeworklayermap where layer_value_id = a.lob_value_id) as LOB,"+
        		 			"(select layer_value from tblframeworklayermap where layer_value_id = a.view_point_value_id) as Views,(select layer_value_id from tblframeworklayermap where layer_value_id = a.org_asset_value_id) as GroupId,"+
        		 			"(select layer_value from tblframeworklayermap where layer_value_id = a.org_asset_value_id) as Groups,(select layer_value_id from tblframeworklayermap where layer_value_id = a.process_value_id) as Process_Domain_Id,"        		 			
        		 			+"(select layer_value from tblframeworklayermap where layer_value_id = a.process_value_id) as Process_Domains from tblmapartifact a, tblmapuserview b where a.view_id = b.view_point_id and b.user_id =? and a.lob_value_id=?";
         */
         
         String selStatement = " select (select view_point_name from tblviewpointmaster where view_point_id = a.view_point_value_id ) as View_Point,(select layer_value from tblframeworklayermap where layer_value_id = a.lob_value_id) as LOB,"+
         "(select layer_value from tblframeworklayermap where layer_value_id =a.view_value_id) as Views,(select layer_value_id from tblframeworklayermap where layer_value_id = a.group_value_id) as GroupId,"+      		 			
          "(select ltrim(rtrim(layer_value)) from tblframeworklayermap where layer_value_id = a.group_value_id) as Groups ,(select layer_value_id from tblframeworklayermap where layer_value_id = a.process_domain_value_id)  as Process_Domain_Id,"+
         "(select layer_value from tblframeworklayermap where layer_value_id = a.process_domain_value_id)as Process_Domains from tblmapartifact a, tblmapuserview b where a.view_point_value_id = b.view_point_id "+ 
         "and b.user_id =? and a.lob_value_id=? order by (select layer_value from tblframeworklayermap where layer_value_id = a.group_value_id)";
         
         System.out.println("selStatement query-----"+selStatement);
         PreparedStatement prepStmt = con.prepareStatement(selStatement);
       
         prepStmt.setString(1, user_Id);
         prepStmt.setString(2, lobId);
         ResultSet rs = prepStmt.executeQuery();
          
         while (rs.next()){
             String viewPointName=rs.getString(1);           
             String lobName=rs.getString(2); 
             String viewName=rs.getString(3); 
             String groupId=rs.getString(4); 
             String goupName=rs.getString(5);
             String processId=rs.getString(6); 
             String processName=rs.getString(7); 
             
             
             String[] temp={viewPointName,lobName,viewName,groupId,goupName,processId,processName};
             getTreeArtifactDetails.add(temp);
         }
         Debug.print("ArrayList Size: "+getTreeArtifactDetails.size());
         prepStmt.close();
         releaseConnection();
     }catch (Exception e) {
         e.printStackTrace();
     }
     return getTreeArtifactDetails;
 }
 
 //kalai ends
 public ArrayList getMappedEventLavelBasedOnEventType(String eventId){
 ArrayList mappedLevel = new ArrayList();
        Debug.print("Inside DAO selectEveLevTypDets");
        try {
            makeConnection();
            
            String selStatement = "SELECT a.map_type_id , a.event_type_id ,a.event_level_id, b.event_type_name,"+
           "c.event_level_name, c.event_level_code from tblMeeMapEventLevel a, tblMeeEventTypeMaster b , tblMeeEventLevelMaster c "+
           "where a.event_level_id = c.event_level_id and a.event_type_id = b.event_type_id and a.event_type_id =?";
                      
            PreparedStatement prepStmt = con.prepareStatement(selStatement);
            prepStmt.setString(1,eventId);
           
            
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
               // ValidationVO valVO = new ValidationVO();
                String mapid=rs.getString(1);
                String eveType=rs.getString(2);
                String eveLeveCode=rs.getString(3);
                String eveTypeName=rs.getString(4);
                String eveLavelName=rs.getString(5);
                String eveLavelCode=rs.getString(6);
                String[] temp={mapid,eveType,eveLeveCode,eveTypeName,eveLavelName,eveLavelCode};
                mappedLevel.add(temp);
            }
            Debug.print("ArrayList Size: "+mappedLevel.size());
            prepStmt.close();
            releaseConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return mappedLevel;
 }
 

    /**********************************************************************************************************************************************/
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
