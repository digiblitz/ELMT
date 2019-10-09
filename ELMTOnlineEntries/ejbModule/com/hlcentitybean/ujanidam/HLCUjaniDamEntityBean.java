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
package com.hlcentitybean.ujanidam;

import com.hlcutil.DBHelper;
import com.hlcutil.Debug;
import java.text.ParseException;
import javax.ejb.*;
import java.sql.*;
import javax.sql.*;
import java.util.*;
import java.util.Date;
import javax.naming.*;

/**
 * This is the bean class for the UjaniDamEntityBean enterprise bean.
 * Created Oct 29, 2007 1:30:00 PM
 * @author Dhivya
 */
public class HLCUjaniDamEntityBean implements EntityBean, HLCUjaniDamEntityLocalBusiness {  
    
    private EntityContext context;
    private Connection con = null;
    private PreparedStatement prepStmt = null;
    private ResultSet rs = null;
    
    private String   calenderId;
    private String   eventId;
    private String   newEventId;
    private String   oldEventId;
    private String   organizerId;
    private String   eventTitle;
    private Date     beginDate;
    private int  noDays;
    private Date     endDate;
    private String  areaId;
    private String  stateId;
    private String  eventDesc;
    private String  eventTypeId;
    private String eventLevelId;
    private String  eventLevel;
    private int  compYear;
    private Date  addDate;
    private Boolean  renewStat;
    private Boolean  approvalStatus;
    private Boolean  orgApprovalStatus;
    private String   comments;
    private Boolean  arearChairApproStatus;
    private String  areaChairCommt;
    private Date    modifyDate;
    private String  modifyBy;
    private String  ipAddress;
    private Boolean  activeStatus;
    private String  staffComments;
    
    // <editor-fold defaultstate="collapsed" desc="EJB infrastructure methods. Click the + sign on the left to edit the code.">
    // TODO Add code to acquire and use other enterprise resources (DataSource, JMS, enterprise beans, Web services)
    // TODO Add business methods
    // TODO Add create methods
    /**
     * @see javax.ejb.EntityBean#setEntityContext(javax.ejb.EntityContext)
     */
    public void setEntityContext(EntityContext aContext) {
        context = aContext;
    }
    
    /**
     * @see javax.ejb.EntityBean#ejbActivate()
     */
    public void ejbActivate() {
        
    }
    
    /**
     * @see javax.ejb.EntityBean#ejbPassivate()
     */
    public void ejbPassivate() {
        
    }
    
    /**
     * @see javax.ejb.EntityBean#ejbRemove()
     */
    public void ejbRemove() {
        
    }
    
    /**
     * @see javax.ejb.EntityBean#unsetEntityContext()
     */
    public void unsetEntityContext() {
        context = null;
    }
    
    /**
     * @see javax.ejb.EntityBean#ejbLoad()
     */
    public void ejbLoad() {
        // TODO add code to retrieve data
    }
    
    /**
     * @see javax.ejb.EntityBean#ejbStore()
     */
    public void ejbStore() {
        // TODO add code to persist data
    }
    
    // </editor-fold>
    
    /**
     * See EJB 2.0 and EJB 2.1 section 12.2.5
     */
    public String ejbFindByPrimaryKey(String aKey) throws FinderException {
        // TODO add code to locate aKey from persistent storage
        // throw javax.ejb.ObjectNotFoundException if aKey is not in
        // persistent storage.
     return null;   
    }
    
     public String ejbCreate(String newEventId,String organizerId,String eventTitle,Date beginDat,Date endDat,String areaId,
            String stateId,int compYear,String eventTypeId,String eventLevelNames,String oldEventId) throws CreateException{
            Debug.print("UjaniDamEntityBean ejbCreate"); 
            try{
            this.calenderId = getNextId();
            } catch(Exception e){
            Debug.print("Exception while calling getNextId():" + e.getMessage());
            }
            this.newEventId = newEventId;
            this.organizerId = organizerId;
            this.eventTitle = eventTitle;
            this.beginDate = beginDat;
            this.endDate = endDat;
            this.areaId = areaId;
            this.stateId = stateId;
            this.compYear = compYear;
            this.eventTypeId =eventTypeId;
            this.eventLevel = eventLevelNames;
            this.newEventId =  newEventId;
            this.oldEventId = oldEventId;
        
        try {
             insertCalenderDetails();
        } catch (Exception ex) {
            throw new EJBException("ejbCreate: " + ex.getMessage());
        }
        return calenderId;
    }
    
     public void ejbPostCreate(String newEventId,String organizerId,String eventTitle,Date beginDat,Date endDat,String areaId,
            String stateId,int compYear,String eventTypeId,String eventLevelNames,String oldEventId){
        Debug.print("UjaniDamEntityBean ejbPostCreate"); 
    }
    public String ejbCreate(String proCalId,String eventId,String eventTypeId,String eventLevelId) throws CreateException{
            Debug.print("UjaniDamEntityBean ejbCreate"); 
                     
            this.calenderId = proCalId;
            this.eventId = eventId;
            this.eventTypeId = eventTypeId;
            this.eventLevelId = eventLevelId;
                    
        try {
             insertChampionshipDetails();
        } catch (Exception ex) {
            throw new EJBException("ejbCreate: " + ex.getMessage());
        }
        return null;
    } 
    public void ejbPostCreate(String proCalId,String eventId,String eventTypeId,String eventLevelId){
        Debug.print("UjaniDamEntityBean ejbPostCreate"); 
    }
    public boolean insertCalenderDetails() {
        Debug.print("UjaniDamEntityBean.insertCalenderDetails():");
        boolean result = false;
        PreparedStatement prepStmt = null;
        Debug.print("UjaniDamEntityBean.insertCalenderDetails()CalendarId:" +calenderId);
        if(newEventId!=null && newEventId.trim().length()!=0){
            makeConnection();
            try {
                String insertStatement = "insert into " + DBHelper.OE_PROVISIONAL_CALENDAR + " "+ 
                        " (pro_calendar_id,event_id, organizer_id, event_title, begin_date, end_date, area_id, state_id, competition_year, event_type_id, event_levels, old_event_id)" +
                        " values (? , ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
                
                Debug.print("Query: "+insertStatement);
                prepStmt = con.prepareStatement(insertStatement);
                prepStmt.setString(1, calenderId);
                prepStmt.setString(2, newEventId);
                prepStmt.setString(3, organizerId);
                prepStmt.setString(4, eventTitle);
                prepStmt.setDate(5, DBHelper.toSQLDate(beginDate));
                prepStmt.setDate(6, DBHelper.toSQLDate(endDate));
                prepStmt.setString(7, areaId);
                prepStmt.setString(8, stateId);
                prepStmt.setInt(9,compYear);
                prepStmt.setString(10,eventTypeId);
                prepStmt.setString(11,eventLevel);
                prepStmt.setString(12,oldEventId);
                
                int cnt = prepStmt.executeUpdate();
                Debug.print("Record Inserted succefully into insertCalenderDetails "+cnt);
                if(cnt >=1) result = true;
                Debug.print("UjaniDamEntityBean insertCalenderDetails() Status :" + result);
                prepStmt.close();
                releaseConnection();
            } catch(SQLException sql){
                releaseConnection();
                Debug.print("SQL Exception in UjaniDamEntityBean.insertCalenderDetails():" + sql.getMessage());
            } catch(Exception e){
                releaseConnection();
                Debug.print("General Exception  in UjaniDamEntityBean.insertCalenderDetails():" + e.getMessage());
            }
        }
        return result;
    } 
 
    public boolean insertChampionshipDetails() {
            Debug.print("UjaniDamEntityBean.insertChampionshipDetails():");
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
                    prepStmt.setString(1, calenderId);
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
            }
        catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in UjaniDamEntityBean.insertChampionshipDetails():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in UjaniDamEntityBean.insertChampionshipDetails():" + e.getMessage());
        }
    return result;   
    }
 
 /**
  * @Method Name    :getNextId.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :None.
  * @return         :String  value.
  * @throws         :RemoteException.
  */      
     
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
             if(!con.isClosed())
                   con.close();
        } catch (SQLException ex) {
            throw new EJBException("releaseConnection: " + ex.getMessage());
        }
    }    
    
    

}
