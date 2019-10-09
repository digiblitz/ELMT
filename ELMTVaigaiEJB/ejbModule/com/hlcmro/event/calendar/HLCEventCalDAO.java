/*
 * EventCalDAO.java
 *
 * Created on January 4, 2007, 2:26 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmro.event.calendar;

import com.hlcmro.util.HLCCalendarVO;
import com.hlcmro.util.DBHelper;
import com.hlcmro.util.Debug;
import com.hlcmro.util.HLCEJBAllJNDIs;
import com.hlcmro.util.HLCEventDetailsVO;
import com.hlcmro.util.HLCOtherDetailsVO;
import java.sql.*;
import java.util.*;
import javax.ejb.EJBException;
import javax.naming.InitialContext;
import javax.sql.*;
/**
 *
 * @author harmohan
 */
public class HLCEventCalDAO {
    
    private Connection con;
    /** Creates a new instance of EventCalDAO */
    public HLCEventCalDAO() {
    }
    
/**
  * @Method Name    :createCalendar.
  * @Description    :This method will create a calendar. 
  * @param          :CalendarVO.
  * @return         :boolean value.
  * @throws         :SQLException.
  */       
   public boolean createCalendar(HLCCalendarVO calVO) {
       Debug.print("Inside createCalendar in EventCalDAO");
       PreparedStatement pmst = null;
       makeConnection();
       try {
           String insertStatement = 
            "insert into " + DBHelper.USEA_MMS_EVENTDETAILS + " (event_name, organizer_name, organizer_id, event_area, "+
            "event_type,event_level) values( ? , ? , ? , ?, ?, ? )";
            pmst = con.prepareStatement(insertStatement);
            pmst.setString(1, calVO.getEventName());
            pmst.setString(2, calVO.getOrganizerName());
            pmst.setString(3, calVO.getOrganizerId());
            pmst.setString(4, calVO.getEventArea());
            pmst.setString(5, calVO.getEventType());
            pmst.setString(6, calVO.getEventLevel());
            Debug.print(" Calendar VO Value Contain in EventCalDAO.createCalendar()... : \n"+calVO);
            pmst.executeUpdate();
            pmst.close();
            releaseConnection(con);
       }catch(SQLException sql){
            releaseConnection(con);
            sql.printStackTrace();
            Debug.print("SQL Exception in EventCalDAO.createCalendar():" + sql.getMessage());
       }catch(Exception e){
            releaseConnection(con);
            e.printStackTrace();
            Debug.print("General Exception  in EventCalDAO.createCalendar():" + e.getMessage());
       }      
       return true;
   }
/**
  * @Method Name    :editCalendar.
  * @Description    :This method will update the calendar. 
  * @param          :CalendarVO.
  * @return         :boolean value.
  * @throws         :SQLException.
  */       
   public boolean editCalendar(HLCCalendarVO calVO) {
       Debug.print("Inside editCalendar in EventCalDAO");
       PreparedStatement pmst = null;
       makeConnection();
       try {
           String insertStatement = 
            "UPDATE " + DBHelper.USEA_MMS_EVENTDETAILS + " set event_name = ?, organizer_name=?, organizer_id=?, event_area=?, "+
            "event_type=?,event_level=?  WHERE cal_event_id = ?";
            pmst = con.prepareStatement(insertStatement);
            pmst.setString(1, calVO.getEventName());
            pmst.setString(2, calVO.getOrganizerName());
            pmst.setString(3, calVO.getOrganizerId());
            pmst.setString(4, calVO.getEventArea());
            pmst.setString(5, calVO.getEventType());
            pmst.setString(6, calVO.getEventLevel());
            pmst.setString(7, calVO.getCalEventId());
            Debug.print(" Calendar VO Value Contain in EventCalDAO.editCalendar()... : \n"+calVO);
            pmst.executeUpdate();
            pmst.close();
            releaseConnection(con);
       }catch(SQLException sql){
            releaseConnection(con);
            sql.printStackTrace();
            Debug.print("SQL Exception in EventCalDAO.editCalendar():" + sql.getMessage());
       }catch(Exception e){
            releaseConnection(con);
            e.printStackTrace();
            Debug.print("General Exception  in EventCalDAO.editCalendar():" + e.getMessage());
       }      
       return true;
   }   
/**
  * @Method Name    :editCalendar.
  * @Description    :This method will update the calendar. 
  * @param          :CalendarVO.
  * @return         :CalendarVO value.
  * @throws         :SQLException.
  */       
   public HLCCalendarVO getCalendarDetails(String calId) {
       Debug.print("Inside getCalendarDetails in EventCalDAO");
       PreparedStatement pmst = null;
       ResultSet rs = null;
       HLCCalendarVO calVO = new HLCCalendarVO();
       makeConnection();
       try {
           String insertStatement = 
            "SELECT cal_event_id, event_name, organizer_name, organizer_id, event_area, event_type,event_level  FROM  " + DBHelper.USEA_MMS_EVENTDETAILS + 
            "  WHERE cal_event_id = ?";
            pmst = con.prepareStatement(insertStatement);
            pmst.setString(1, calId);
            rs = pmst.executeQuery();
            if(rs.next()){
                calVO.setCalEventId("cal_event_id");
                calVO.setEventName("event_name");
                calVO.setOrganizerName("organizer_name");
                calVO.setOrganizerId("organizer_id");
                calVO.setEventArea("event_area");
                calVO.setEventType("event_type");
                calVO.setEventLevel("event_level");
            }
            Debug.print(" Calendar VO Value Contain in EventCalDAO.getCalendarDetails()... : \n"+calVO);
            pmst.close();
            releaseConnection(con);
       }catch(SQLException sql){
            releaseConnection(con);
            sql.printStackTrace();
            Debug.print("SQL Exception in EventCalDAO.getCalendarDetails():" + sql.getMessage());
       }catch(Exception e){
            releaseConnection(con);
            e.printStackTrace();
            Debug.print("General Exception  in EventCalDAO.getCalendarDetails():" + e.getMessage());
       }      
       return calVO;
   }   
/**
  * @Method Name    :getEventId.
  * @Description    :This method will get the provisional calendar details based on the year. 
  * @param          :String value i.e. year.
  * @return         :List value.
  * @throws         :SQLException.
  */ 
   
   public List getEventId(String year) {
       Debug.print("Inside getEventCalendar in EventCalDAO");
       PreparedStatement pmst = null;
       ResultSet rs = null;
       List list = new ArrayList();
       HLCEventDetailsVO eventCalVO = null;
       /* SELECT event_id,  date_available,available_from, status_id  FROM  
           tblMeeEventDetails  WHERE status_id = 'Pending' AND date_available 
           >= '2006-01-01' AND date_available <= '2006-12-31'
           
           SELECT event_id,  date_available,available_from, status_id FROM tblMeeEventDetails
           WHERE status_id = 'Approved' AND YEAR(date_available) = '2007'*/
       makeConnection();
       try {
           String selectStatement = "SELECT event_id  FROM  " + DBHelper.USEA_MMS_EVENTDETAILS + 
                   "  WHERE status_id = ? AND YEAR(date_available) = ?";

           pmst = con.prepareStatement(selectStatement);
           pmst.setString(1, "Approved");
           pmst.setString(2,year);
           rs = pmst.executeQuery();
           while(rs.next()){
               list.add(rs.getString("event_id"));                        
            }
            Debug.print(" Calendar VO Value Contain in EventCalDAO.getEventCalendar()... : \n"+list);
            pmst.close();
            releaseConnection(con);
       }catch(SQLException sql){
            releaseConnection(con);
            sql.printStackTrace();
            Debug.print("SQL Exception in EventCalDAO.getEventCalendar():" + sql.getMessage());
       }catch(Exception e){
            releaseConnection(con);
            e.printStackTrace();
            Debug.print("General Exception  in EventCalDAO.getEventCalendar():" + e.getMessage());
       }      
       return list;
   }  
/**
  * @Method Name    :getEventCalendar.
  * @Description    :This method will get the provisional calendar details based on the year. 
  * @param          :String value i.e. year.
  * @return         :EventDetailsVO value.
  * @throws         :SQLException.
  */ 
   
   public boolean  isEventNameExist(String eventTitle, String year) {
       Debug.print("Inside isEventNameExist in EventCalDAO");
       PreparedStatement pmst = null;
       ResultSet rs = null;
       boolean bol = false;
       String eventId = null;
       
       /* SELECT event_id,  date_available,available_from, status_id  FROM  
           tblMeeEventDetails  WHERE status_id = 'Pending' AND date_available 
           >= '2006-01-01' AND date_available <= '2006-12-31'
           
           SELECT event_id,  date_available,available_from, status_id FROM tblMeeEventDetails
           WHERE status_id = 'Approved' AND YEAR(date_available) = '2007'*/
       makeConnection();
       try {
           String selectStatement = "SELECT event_id  FROM  " + DBHelper.USEA_MMS_EVENTDETAILS + 
                   "  WHERE event_title = '"+eventTitle+"' AND YEAR(date_available) = '"+year+"'";
            

           pmst = con.prepareStatement(selectStatement);
          // pmst.setString(1, eventTitle);
          // pmst.setString(2, year);
           Debug.print("The Query Statement is : \n"+selectStatement);
           rs = pmst.executeQuery();
           if (rs.next()){
                eventId =rs.getString("event_id");
                Debug.print(" Event id In isEventNameExist is : "+eventId);
           }
           releaseConnection(con);
       }catch(Exception e){
           releaseConnection(con);      
           e.printStackTrace();
       }
       if (eventId != null && eventId.trim().length()>0){
           bol = true;
       }
       Debug.print("Status in isEventNameExist : "+bol);
       return bol;
   }
/**
  * @Method Name    :getEventCalendar.
  * @Description    :This method will get the provisional calendar details based on the year. 
  * @param          :String value i.e. year.
  * @return         :EventDetailsVO value.
  * @throws         :SQLException.
  */ 
   
   public HLCEventDetailsVO getEventCalendar(String eventId) {
       Debug.print("Inside getEventCalendar in EventCalDAO");
       PreparedStatement pmst = null;
       ResultSet rs = null;
       List list = new ArrayList();
       HLCEventDetailsVO eventCalVO = null;
       /* SELECT event_id,  date_available,available_from, status_id  FROM  
           tblMeeEventDetails  WHERE status_id = 'Pending' AND date_available 
           >= '2006-01-01' AND date_available <= '2006-12-31'
           
           SELECT event_id,  date_available,available_from, status_id FROM tblMeeEventDetails
           WHERE status_id = 'Approved' AND YEAR(date_available) = '2007'*/
       makeConnection();
       try {
           String selectStatement = 
            "SELECT event_id,  organizer_id, event_title, event_secretary_id, entry_fee, other_entry_fee, " +
            " membership_applicable, double_entry_fee_status, office_fee, check_payable_to, pinnies_charge, " +
            "pinnies_position,award_trophy, award_prize, award_others ,date_available,available_from, available_from_other," +
            "available_position, stabling_limited, stall_pernight_price, per_stall_price, per_stall_from_time, " +
            "per_stall_from_date, per_stall_to_time, per_stall_to_date,no_of_stalls,no_of_temp_stalls,no_of_temp_permanent_stalls," +
            "miles_from_event,veterinarian_name,veterinarian_phone,veterinarian_place,accomodation_camping,directions,footing_desc," +
            "status_id  FROM  " + DBHelper.USEA_MMS_EVENTDETAILS + "  WHERE event_id = ? ";
            

           pmst = con.prepareStatement(selectStatement);
           pmst.setString(1, eventId);
           rs = pmst.executeQuery();
           if (rs.next()){
                eventCalVO = new HLCEventDetailsVO();
                eventCalVO.setEventId(rs.getString("event_id"));
                eventCalVO.setOrganizeId(rs.getString("organizer_id"));
                eventCalVO.setEventTitle(rs.getString("event_title"));
                eventCalVO.setEventSecretaryId(rs.getString("event_secretary_id"));
                eventCalVO.setEntryFee(rs.getString("entry_fee"));
                eventCalVO.setMembershipApplicable(rs.getString("membership_applicable"));
                eventCalVO.setDoubleEntryFee(rs.getString("double_entry_fee_status"));
                eventCalVO.setOfficeFee(rs.getString("office_fee"));
                eventCalVO.setCheckPayableTo(rs.getString("check_payable_to"));
                eventCalVO.setPinniesCharge(rs.getString("pinnies_charge"));
                eventCalVO.setPinniesPosition(rs.getString("pinnies_position"));
                eventCalVO.setAwardTrophy(rs.getString("award_trophy"));
                eventCalVO.setAwardPrize(rs.getString("award_prize"));
                eventCalVO.setAwardOthers(rs.getString("award_others"));
                eventCalVO.setDateAvailable(rs.getString("date_available"));
                eventCalVO.setAvailableFrom(rs.getString("available_from"));
                eventCalVO.setAvailableFromOther(rs.getString("available_from_other"));
                eventCalVO.setAvailablePosition(rs.getString("available_position"));
                eventCalVO.setStablingLimited(rs.getString("stabling_limited"));
                eventCalVO.setStallPernightPrice(rs.getString("stall_pernight_price"));
                eventCalVO.setPerStallPrice(rs.getString("per_stall_price"));
                eventCalVO.setPerStallFromTime(rs.getString("per_stall_from_time"));
                eventCalVO.setPerStallFromDate(rs.getString("per_stall_from_date"));
                eventCalVO.setPerStallToTime(rs.getString("per_stall_to_time"));
                eventCalVO.setPerStallToDate(rs.getString("per_stall_to_date"));
                eventCalVO.setNoOfStalls(rs.getString("no_of_stalls"));
                eventCalVO.setNoOfTempStalls(rs.getString("no_of_temp_stalls"));
                eventCalVO.setNoOfTempPermanentStalls(rs.getString("no_of_temp_permanent_stalls"));
                eventCalVO.setMilesFromEvent(rs.getString("miles_from_event"));
                eventCalVO.setVeterinarianName(rs.getString("veterinarian_name"));
                eventCalVO.setVeterinarianPhone(rs.getString("veterinarian_phone"));
                eventCalVO.setVeterinarianPlace(rs.getString("veterinarian_place"));
                eventCalVO.setAccomodationCamping(rs.getString("accomodation_camping"));
                eventCalVO.setDirections(rs.getString("directions"));
                eventCalVO.setFootingDesc(rs.getString("footing_desc"));
                        
            }
            Debug.print(" Calendar VO Value Contain in EventCalDAO.getEventCalendar()... : \n"+eventCalVO);
            pmst.close();
            releaseConnection(con);
       }catch(SQLException sql){
            releaseConnection(con);
            sql.printStackTrace();
            Debug.print("SQL Exception in EventCalDAO.getEventCalendar():" + sql.getMessage());
       }catch(Exception e){
            releaseConnection(con);
            e.printStackTrace();
            Debug.print("General Exception  in EventCalDAO.getEventCalendar():" + e.getMessage());
       }      
       return eventCalVO;
   }  
   
/**
  * @Method Name    :getAccomodationForCalendar.
  * @Description    :This method will get accomodation details based on the event id. 
  * @param          :String value i.e. eventId.
  * @return         :Vector.
  * @throws         :SQLException.
  */ 
   
   public Vector getAccomodationForCalendar(String eventId) {
       Debug.print("Inside getAccomodationForCalendar in EventCalDAO");
       PreparedStatement pmst = null;
       ResultSet rs = null;
       Vector accommodation = new Vector();      
       makeConnection();
       try {
           String selectStatement = 
            "SELECT hotel_name,  hotel_phone, miles_from_event  FROM  " + 
             DBHelper.USEA_MRO_ACCOMMODATION_DETAILS + "  WHERE event_id = ? ";
            
           pmst = con.prepareStatement(selectStatement);
           pmst.setString(1, eventId);
           rs = pmst.executeQuery();
           while(rs.next()) {
            String hotelName = rs.getString(1);
            String hotelPhone = rs.getString(2);
            String milesFromEvent = rs.getString(3);
            String acc1[] = {hotelName,hotelPhone,milesFromEvent};
            accommodation.addElement(acc1);                       
            }
            Debug.print(" Calendar Accomodation Value Contain in EventCalDAO.getAccomodationForCalendar()... : \n"+accommodation);
            pmst.close();
            releaseConnection(con);
       }catch(SQLException sql){
            releaseConnection(con);
            sql.printStackTrace();
            Debug.print("SQL Exception in EventCalDAO.getAccomodationForCalendar():" + sql.getMessage());
       }catch(Exception e){
            releaseConnection(con);
            e.printStackTrace();
            Debug.print("General Exception  in EventCalDAO.getAccomodationForCalendar():" + e.getMessage());
       }      
       return accommodation;
   } 
/**
  * @Method Name    :getCrossCountryForCalendar.
  * @Description    :This method will get Cross Country details based on the event id. 
  * @param          :String value i.e. eventId.
  * @return         :Vector.
  * @throws         :SQLException.
  */ 
   
   public Vector getCrossCountryForCalendar(String eventId) {
       Debug.print("Inside getCrossCountryForCalendar in EventCalDAO");
       ResultSet rs = null;
       Vector crossCountry = new Vector();      
       makeConnection();
       try {
           String selectStatement ="select division, length, speed, course_description, add_information from " + 
                   DBHelper.USEA_MRO_CROSS_COUNTRY_DETAILS  + " where event_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, eventId);
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                String division = rs.getString(1);
                String length = rs.getString(2);
                String speed = rs.getString(3);
                String courseDescription = rs.getString(4);
                String addInformation = rs.getString(5);
                String cross1[] = {division,length,speed,courseDescription,addInformation};
                crossCountry.addElement(cross1);                       
            }
            Debug.print(" Calendar Cross Country Value Contain in EventCalDAO.getCrossCountryForCalendar()... : \n"+crossCountry);
            prepStmt.close();
            releaseConnection(con);
       }catch(SQLException sql){
            releaseConnection(con);
            sql.printStackTrace();
            Debug.print("SQL Exception in EventCalDAO.getCrossCountryForCalendar():" + sql.getMessage());
       }catch(Exception e){
            releaseConnection(con);
            e.printStackTrace();
            Debug.print("General Exception  in EventCalDAO.getCrossCountryForCalendar():" + e.getMessage());
       }      
       return crossCountry;
   }   
/**
  * @Method Name    :getCogginsDetailsForCalendar.
  * @Description    :This method will get Coggins details based on the event id. 
  * @param          :String value i.e. eventId.
  * @return         :Vector.
  * @throws         :SQLException.
  */ 
   
   public Vector getCogginsDetailsForCalendar(String eventId) {
       Debug.print("Inside getCogginsDetailsForCalendar in EventCalDAO");
       ResultSet rs = null;
       Vector cogginsDetails = new Vector();     
       makeConnection();
       try {
           String selectStatement = "select all_state, in_state, out_of_state, no_state, others from " + 
                   DBHelper.USEA_MRO_COGGINS_DETAILS  + " where event_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, eventId);
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                cogginsDetails.add(rs.getString("all_state"));
                cogginsDetails.add(rs.getString("in_state"));
                cogginsDetails.add(rs.getString("out_of_state"));
                cogginsDetails.add(rs.getString("no_state"));
                cogginsDetails.add(rs.getString("others"));                       
            }
            Debug.print(" Calendar Coggins Value Contain in EventCalDAO.getCogginsDetailsForCalendar()... : \n"+cogginsDetails);
            prepStmt.close();
            releaseConnection(con);
       }catch(SQLException sql){
            releaseConnection(con);
            sql.printStackTrace();
            Debug.print("SQL Exception in EventCalDAO.getCogginsDetailsForCalendar():" + sql.getMessage());
       }catch(Exception e){
            releaseConnection(con);
            e.printStackTrace();
            Debug.print("General Exception  in EventCalDAO.getCogginsDetailsForCalendar():" + e.getMessage());
       }      
       return cogginsDetails;
   }   
/**
  * @Method Name    :getDivisionLevelsForCalendar.
  * @Description    :This method will get Coggins details based on the event id. 
  * @param          :String value i.e. eventId.
  * @return         :Vector.
  * @throws         :SQLException.
  */ 
   
   public Vector getDivisionLevelsForCalendar(String eventId) {
       Debug.print("Inside getDivisionLevelsForCalendar in EventCalDAO");
       ResultSet rs = null;
       Vector divisionLevels = new Vector();     
       makeConnection();
       try {
           String selectStatement =
                "select map_type_id from "+ DBHelper.USEA_MRO_EVENT_TYPE_DETAILS+ " where event_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, eventId);
            rs = prepStmt.executeQuery();
            while(rs.next()) {
                divisionLevels.add(rs.getString(1));                      
            }
            Debug.print(" Calendar Division Level Value Contain in EventCalDAO.getDivisionLevelsForCalendar()... : \n"+divisionLevels);
            prepStmt.close();
            releaseConnection(con);
       }catch(SQLException sql){
            releaseConnection(con);
            sql.printStackTrace();
            Debug.print("SQL Exception in EventCalDAO.getDivisionLevelsForCalendar():" + sql.getMessage());
       }catch(Exception e){
            releaseConnection(con);
            e.printStackTrace();
            Debug.print("General Exception  in EventCalDAO.getDivisionLevelsForCalendar():" + e.getMessage());
       }      
       return divisionLevels;
   }   
/**
  * @Method Name    :getHorseDressageForCalendar.
  * @Description    :This method will get horse dressage details based on the event id. 
  * @param          :String value i.e. eventId.
  * @return         :Vector.
  * @throws         :SQLException.
  */ 
   
   public Vector getHorseDressageForCalendar(String eventId) {
       Debug.print("Inside getHorseDressageForCalendar in EventCalDAO");
       ResultSet rs = null;
       Vector hDressage = new Vector();     
       makeConnection();
       try {
           String selectStatement ="select dressage_map_id, arena_size_id from " + DBHelper.USEA_MRO_HORSE_DRESSAGE_DETAILS  + 
                   " where event_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, eventId);
            rs = prepStmt.executeQuery();
            while (rs.next()) {
                String dressageMapId = rs.getString(1);
                String arenaSizeId = rs.getString(2);
                String hDress1[] = {dressageMapId,arenaSizeId};
                hDressage.addElement(hDress1);
            }
            Debug.print(" Calendar Horse Dressage Value Contain in EventCalDAO.getHorseDressageForCalendar()... : \n"+hDressage);
            prepStmt.close();
            releaseConnection(con);
       }catch(SQLException sql){
            releaseConnection(con);
            sql.printStackTrace();
            Debug.print("SQL Exception in EventCalDAO.getHorseDressageForCalendar():" + sql.getMessage());
       }catch(Exception e){
            releaseConnection(con);
            e.printStackTrace();
            Debug.print("General Exception  in EventCalDAO.getHorseDressageForCalendar():" + e.getMessage());
       }      
       return hDressage;
   }  
/**
  * @Method Name    :getHorseDressageForCalendar.
  * @Description    :This method will get horse dressage details based on the event id. 
  * @param          :String value i.e. eventId.
  * @return         :Vector.
  * @throws         :SQLException.
  */ 
   
   public Vector getJudgesDetailsForCalendar(String eventId) {
       Debug.print("Inside getJudgesDetailsForCalendar in EventCalDAO");
       ResultSet rs = null;
       Vector judgesDetails = new Vector();  
       makeConnection();
       try {
           String selectStatement =
                "select judge_type_id, judge_names from " + DBHelper.USEA_MRO_JUDGE_DETAILS  + " where event_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, eventId);
            rs = prepStmt.executeQuery();
            while(rs.next()) {
                String judgeTypeId = rs.getString(1);
                String judgeNames = rs.getString(2);
                String [] jud = {judgeTypeId,judgeNames};
                judgesDetails.addElement(jud);
            }
            Debug.print(" Calendar Judges Details Value Contain in EventCalDAO.getJudgesDetailsForCalendar()... : \n"+judgesDetails);
            prepStmt.close();
            releaseConnection(con);
       }catch(SQLException sql){
            releaseConnection(con);
            sql.printStackTrace();
            Debug.print("SQL Exception in EventCalDAO.getJudgesDetailsForCalendar():" + sql.getMessage());
       }catch(Exception e){
            releaseConnection(con);
            e.printStackTrace();
            Debug.print("General Exception  in EventCalDAO.getJudgesDetailsForCalendar():" + e.getMessage());
       }      
       return judgesDetails;
   }   
/**
  * @Method Name    :getOtherDetailsForCalendar.
  * @Description    :This method will get Other details based on the event id. 
  * @param          :String value i.e. eventId.
  * @return         :OtherDetailsVO.
  * @throws         :SQLException.
  */ 
   
   public HLCOtherDetailsVO getOtherDetailsForCalendar(String eventId) {
       Debug.print("Inside getOtherDetailsForCalendar in EventCalDAO");
       ResultSet rs = null;
       HLCOtherDetailsVO othersDet = new HLCOtherDetailsVO(); 
       makeConnection();
       try {
           String selectStatement =
                "select course_close_date, entry_limit, riders_horse_level_limit, " +
                " riders_horse_entire_limit, division_entry_birth_date, lease_dogs, team_competition, " +
                " per_time_price, other_team_info, party_name, add_info from " + DBHelper.USEA_MRO_OTHER_DETAILS  + 
                   " where event_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, eventId);
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                othersDet.setCourseCloseDate(rs.getDate("course_close_date"));
                othersDet.setEntryLimit(rs.getString("entry_limit"));
                othersDet.setRidersHorseLevelLimit(rs.getString("riders_horse_level_limit"));
                othersDet.setRidersHorseEntireLimit(rs.getString("riders_horse_entire_limit"));
                othersDet.setDivisionEntryBirthDate(rs.getDate("division_entry_birth_date"));
                othersDet.setLeaseDogs(rs.getBoolean("lease_dogs"));
                othersDet.setTeamCompetition(rs.getString("team_competition"));
                othersDet.setPerTimePrice(rs.getString("per_time_price"));
                othersDet.setOtherTeamInfo(rs.getString("other_team_info"));
                othersDet.setPartyName(rs.getString("party_name"));
                othersDet.setAddInfo(rs.getString("add_info"));
            }
            Debug.print(" Calendar Other Details Value Contain in EventCalDAO.getOtherDetailsForCalendar()... : \n"+othersDet);
            prepStmt.close();
            releaseConnection(con);
       }catch(SQLException sql){
            releaseConnection(con);
            sql.printStackTrace();
            Debug.print("SQL Exception in EventCalDAO.getOtherDetailsForCalendar():" + sql.getMessage());
       }catch(Exception e){
            releaseConnection(con);
            e.printStackTrace();
            Debug.print("General Exception  in EventCalDAO.getOtherDetailsForCalendar():" + e.getMessage());
       }      
       return othersDet;
   }   
/**
  * @Method Name    :getRefundRuleDetailsForCalendar.
  * @Description    :This method will get refund rule details based on the event id. 
  * @param          :String value i.e. eventId.
  * @return         :Vector.
  * @throws         :SQLException.
  */ 
   
   public Vector getRefundRuleDetailsForCalendar(String eventId) {
       Debug.print("Inside getRefundRuleDetailsForCalendar in EventCalDAO");
       ResultSet rs = null;
       Vector refundRuleDetails = new Vector(); 
       makeConnection();
       try {
           String selectStatement =
                "select refund_map_id, amount from " + DBHelper.USEA_MRO_REFUND_RULE_DETAILS  + " where event_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, eventId);
            rs = prepStmt.executeQuery();
            while (rs.next()) {
                String refundMapId = rs.getString(1);
                String amount = rs.getString(2);
                String [] rr1 = {refundMapId,amount};
                refundRuleDetails.addElement(rr1);
            }
            Debug.print(" Calendar Refund Rule Details Value Contain in EventCalDAO.getRefundRuleDetailsForCalendar()... : \n"+refundRuleDetails);
            prepStmt.close();
            releaseConnection(con);
       }catch(SQLException sql){
            releaseConnection(con);
            sql.printStackTrace();
            Debug.print("SQL Exception in EventCalDAO.getRefundRuleDetailsForCalendar():" + sql.getMessage());
       }catch(Exception e){
            releaseConnection(con);
            e.printStackTrace();
            Debug.print("General Exception  in EventCalDAO.getRefundRuleDetailsForCalendar():" + e.getMessage());
       }      
       return refundRuleDetails;
   }    
/**
  * @Method Name    :getTentativeTimeForCalendar.
  * @Description    :This method will get Tentatative Time scheduled rule details based on the event id. 
  * @param          :String value i.e. eventId.
  * @return         :Vector.
  * @throws         :SQLException.
  */ 
   
   public Vector getTentativeTimeForCalendar(String eventId) {
       Debug.print("Inside getRefundRuleDetailsForCalendar in EventCalDAO");
       ResultSet rs = null;
       Vector tentativeTime= new Vector(); 
       makeConnection();
       try {
           String selectStatement ="select  day, phase, time from " + DBHelper.USEA_MRO_TENTATIVE_TIME_SCHEDULE  + 
                   " where event_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, eventId);
            rs = prepStmt.executeQuery();
            while(rs.next()) {
		java.util.Date day = rs.getDate(1);
		String phase = rs.getString(2);
		String time = rs.getString(3);
                ArrayList al = new ArrayList();
                   al.add(day);
                   al.add(phase);
                   al.add(time);
                tentativeTime.add(al);
            }
            Debug.print(" Calendar Tentative Time Scheduled Value Contain in EventCalDAO.getTentativeTimeForCalendar()... : \n"+tentativeTime);
            prepStmt.close();
            releaseConnection(con);
       }catch(SQLException sql){
            releaseConnection(con);
            sql.printStackTrace();
            Debug.print("SQL Exception in EventCalDAO.getTentativeTimeForCalendar():" + sql.getMessage());
       }catch(Exception e){
            releaseConnection(con);
            e.printStackTrace();
            Debug.print("General Exception  in EventCalDAO.getTentativeTimeForCalendar():" + e.getMessage());
       }      
       return tentativeTime;
   }    
   
/**
  * @Method Name    :getCalendarInfo.
  * @Description    :This method will get minimal information for the calendar based on the event id. 
  * @param          :String value i.e. eventId.
  * @return         :CalendarVO.
  * @throws         :SQLException.
  */ 
   
   public HLCCalendarVO getCalendarInfo(String eventId,String areaName) {
       Debug.print("Inside getCalendarInfo in EventCalDAO");
       ResultSet rs = null;
       HLCCalendarVO calVO = null;
       makeConnection();
       try {
           String selectStatement ="SELECT A.first_name,A.middle_name,A.last_name,C.organizer_id,C.event_title,D.area_name, "+
           "G.event_type_name, H.event_level_code, J.event_id FROM "+ DBHelper.USEA_MMS_USERMASTER+" A, "+
           DBHelper.USEA_MMS_MEMBERDETAIL +" B, "+DBHelper.USEA_MRO_EVENTDETAILS+ " C, " +DBHelper.USEA_MRO_AREA_MASTER+" D, "+ 
           DBHelper.USEA_ORG_RENEWAL+" E, "+DBHelper.USEA_MRO_MAP_STATE_ZIP+" F, "+DBHelper.USEA_MRO_TYPE_MASTER+" G, "+ 
           DBHelper.USEA_MRO_LEVEL_MASTER+" H, "+DBHelper.USEA_MRO_MAP_EVENT_LEVEL+" I, "+ DBHelper.USEA_MRO_EVENT_TYPE_DETAILS+
           " J where A.user_id = B.user_id AND B.member_id = C.organizer_id AND C.event_id = E.event_id AND "+
           " E.competition_zip <= F.zip_code_to and D.area_id = F.area_id and G.event_type_id = I.event_type_id AND "+
           " H.event_level_id = I.event_level_id AND I.map_type_id = J.map_type_id AND J.event_id = E.event_id AND " +
           " E.event_id = '"+eventId+"'"; //'10006'";
           if (areaName != null && areaName.trim().length() > 0 ){
               selectStatement += "   AND D.area_name = '"+areaName+"'";
           }
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
           // prepStmt.setString(1, eventId);
            rs = prepStmt.executeQuery();
            Debug.print(" The Query String is ... \n"+selectStatement);
            Debug.print(" The Event Id is ... "+eventId);
            if(rs.next()) {
                calVO = new HLCCalendarVO();
                calVO.setOrganizerName(rs.getString("last_name")+"  "+rs.getString("first_name")+"  "+rs.getString("middle_name"));
                calVO.setOrganizerId(rs.getString("organizer_id"));
                calVO.setEventName(rs.getString("event_title"));
                calVO.setEventArea(rs.getString("area_name"));
                calVO.setEventType(rs.getString("event_type_name"));
                calVO.setEventLevel(rs.getString("event_level_code"));
                calVO.setCalEventId(rs.getString("event_id"));		
            }
            Debug.print(" Calendar Value Contain in EventCalDAO.getCalendarInfo()....: \n"+calVO);
            prepStmt.close();
            releaseConnection(con);
       }catch(SQLException sql){
            releaseConnection(con);
            sql.printStackTrace();
            Debug.print("SQL Exception in EventCalDAO.getCalendarInfo():" + sql.getMessage());
       }catch(Exception e){
            releaseConnection(con);
            e.printStackTrace();
            Debug.print("General Exception  in EventCalDAO.getCalendarInfo():" + e.getMessage());
       }      
       return calVO;
   }    
   
    
    //==============Data Base Coneectivity ============================   
/**
  * @Method Name    :makeConnection.
  * @Description    :This method will create a Database connection. 
  * @param          :Null.
  * @return         :Connection value.
  * @throws         :EJBException.
  */    
    private Connection makeConnection() {
        Debug.print("EventCalDAO : makeConnection");
        try {
            InitialContext ic = new InitialContext();
            DataSource ds = (DataSource) ic.lookup(HLCEJBAllJNDIs.USEA_DB);
            con = ds.getConnection();
        } catch (Exception ex) {
            throw new EJBException("Unable to connect to database. " + ex.getMessage());
        }
       return con; 
    }
/**
  * @Method Name    :releaseConnection.
  * @Description    :This method will create a Database connection. 
  * @param          :Null.
  * @return         :Connection value.
  * @throws         :EJBException.
  */       
       
    private void releaseConnection(Connection con) {
            Debug.print("EventCalDAO releaseConnection");
            try {
               if(!con.isClosed())
                   con.close();
            } catch (Exception e) {
                
            }
    }
    
}
