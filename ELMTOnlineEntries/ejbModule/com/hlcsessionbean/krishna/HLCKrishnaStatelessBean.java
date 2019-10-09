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
package com.hlcsessionbean.krishna;

import com.hlcentitybean.ujanidam.HLCUjaniDamEntityLocal;
import com.hlcentitybean.ujanidam.HLCUjaniDamEntityLocalHome;
import com.hlcutil.HLCCalendarVO;
import com.hlcutil.DBHelper;
import com.hlcutil.Debug;
import com.hlcutil.HLCEventCalendarDAO;
import com.hlcutil.HLCEventRequestVO;
import com.hlcutil.HLCValidationVO;
import java.text.SimpleDateFormat;
import javax.ejb.*;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;

/**
 * This is the bean class for the KrishnaStatelessBean enterprise bean.
 * Created Oct 29, 2007 1:19:14 PM
 * @author Dhivya
 */
public class HLCKrishnaStatelessBean implements SessionBean, HLCKrishnaStatelessRemoteBusiness {

    private SessionContext context;
    private InitialContext ic = null;
    private Connection con;
    ResultSet rs = null;
    PreparedStatement prepStmt = null;
    HLCEventCalendarDAO objDAO = new HLCEventCalendarDAO();
    // <editor-fold defaultstate="collapsed" desc="EJB infrastructure methods. Click the + sign on the left to edit the code.">
    // TODO Add code to acquire and use other enterprise resources (DataSource, JMS, enterprise bean, Web services)
    // TODO Add business methods or web service operations
    /**
     * @see javax.ejb.SessionBean#setSessionContext(javax.ejb.SessionContext)
     */
    public void setSessionContext(SessionContext aContext) {
        context = aContext;
    }

    /**
     * @see javax.ejb.SessionBean#ejbActivate()
     */
    public void ejbActivate() {

    }

    /**
     * @see javax.ejb.SessionBean#ejbPassivate()
     */
    public void ejbPassivate() {

    }

    /**
     * @see javax.ejb.SessionBean#ejbRemove()
     */
    public void ejbRemove() {

    }
    // </editor-fold>
    private HLCUjaniDamEntityLocalHome objEventLoHome;
    private HLCUjaniDamEntityLocal objEventLocal;

    /**
     * See section 7.10.3 of the EJB 2.0 specification
     * See section 7.11.3 of the EJB 2.1 specification
     */
    public void ejbCreate() {
        // TODO implement ejbCreate if necessary, acquire resources
        // This method has access to the JNDI context so resource aquisition
        // spanning all methods can be performed here such as home interfaces
        // and data sources.
        try {
            InitialContext jndiContext = getInitialContext();
            Object objPayd = jndiContext.lookup("ejb/HLCUjaniDamEntityJNDI");
            objEventLoHome = (HLCUjaniDamEntityLocalHome) objPayd;
            Debug.print("Inside KrishnaStatelessBean ejbCreate");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Add business logic below. (Right-click in editor and choose
    // "EJB Methods > Add Business Method" or "Web Service > Add Operation")
    /************************************************PROVISIONAL CALENDAR*********************************************************/
    public boolean calenderDetailsExist(String calenderYear) throws RemoteException {
        Debug.print("KrishnaStatelesBean.calenderDetailsExist() :");
        boolean result = false;
        try {
            if (calenderYear != null && calenderYear.trim().length() != 0) {
                result = objDAO.calenderDetailsExist(calenderYear);

            }
        } catch (Exception e) {
            Debug.print("Exception in KrishnastalessBean.calenderDetailsExist()" + e.getMessage());
        }
        return result;
    }

    public ArrayList getEventDetails(int presentYear) throws RemoteException {
        Debug.print("KrishnaStatelesBean.getEventDetails() :");
        ArrayList vObj = new ArrayList();
        try {
            if (presentYear != 0) {
                vObj = objDAO.selectEventDetails(presentYear);

            }
        } catch (Exception e) {
            Debug.print("Exception in getEventDetails().getEventDetails()" + e.getMessage());
        }
        return vObj;
    }

    public ArrayList getEventDetailsBasedOnEventId(String eventId) throws RemoteException {
        Debug.print("KrishnaStatelesBean.getEventDetailsBasedOnEventId() :");
        ArrayList vObj = new ArrayList();
        try {
            if (eventId != null) {
                vObj = objDAO.getEventDetailsBasedOnEventId(eventId);

            }
        } catch (Exception e) {
            Debug.print("Exception in getEventDetails.getEventDetailsBasedOnEventId()" + e.getMessage());
        }
        return vObj;
    }

    public boolean insertCalenderDetails(String newEventId, String organizerId, String eventTitle, Date beginDat, Date endDat, String areaId, String stateId, int compYear, String eventTypeId, String eventLevelNames, String oldEventId) throws RemoteException {
        Debug.print("KrishnaStatelesBean.insertCalenderDetails() :");
        boolean result = false;

        try {
            if (newEventId != null && newEventId.trim().length() != 0 && organizerId != null && organizerId.trim().length() != 0 && eventTitle != null && eventTitle.trim().length() != 0 && beginDat != null && endDat != null && areaId != null && areaId.trim().length() != 0 && stateId != null && stateId.trim().length() != 0 && compYear != 0) {
                Debug.print("Inside != null:");
                objEventLocal = objEventLoHome.create(newEventId, organizerId, eventTitle, beginDat, endDat, areaId, stateId, compYear, eventTypeId, eventLevelNames, oldEventId);

            }
        } catch (Exception e) {
            Debug.print("Exception in KrishnastalessBean.insertCalenderDetails()" + e.getMessage());
        }
        return result;
    }

    public boolean insertChampionshipDetails(String proCalId, String eventId, String eventTypeId, String eventLevelId) throws RemoteException {
        Debug.print("KrishnaStatelesBean.insertChampionshipDetails() :");
        boolean eveDet1 = false;

        try {
            if (eventId != null && eventId.trim().length() != 0 && eventTypeId != null && eventTypeId.trim().length() != 0 && eventLevelId != null && eventLevelId.trim().length() != 0) {
                objEventLocal = objEventLoHome.create(proCalId, eventId, eventTypeId, eventLevelId);
            }
        } catch (Exception e) {
            Debug.print("Exception in KrishnastalessBean.insertChampionshipDetails()" + e.getMessage());
        }
        return eveDet1;
    }
    /*public String insertCalenderDetails(String eventId,String organizerId,String eventTitle,Date beginDat,Date endDat,String areaId,String stateId,int compYear,String eventTypeId,String eventLevelNames) throws RemoteException{
    Debug.print("KrishnaStatelesBean.insertCalenderDetails() :");
    String result = "";
    try{
    if(eventId!=null && eventId.trim().length()!=0 && organizerId!=null && organizerId.trim().length()!=0 && eventTitle!=null && eventTitle.trim().length()!=0 && beginDat!=null && endDat!=null && areaId!=null && areaId.trim().length()!=0 && stateId!=null && stateId.trim().length()!=0 && compYear!=0){
    Debug.print("Inside != null:");
    result= objDAO.insertCalenderDetails(eventId, organizerId, eventTitle, beginDat, endDat, areaId, stateId, compYear,eventTypeId, eventLevelNames);                  
    }
    }
    catch(Exception e){
    Debug.print("Exception in KrishnastalessBean.insertCalenderDetails()" + e.getMessage());
    }
    return result;
    }
    public String insertChampionshipDetails(String proCalId,String eventId,String eventTypeId,String eventLevelId) throws RemoteException{
    Debug.print("KrishnaStatelesBean.insertChampionshipDetails() :");
    String result = "";
    try{
    if(eventId!=null && eventId.trim().length()!=0 && eventTypeId!=null && eventTypeId.trim().length()!=0 && eventLevelId!=null && eventLevelId.trim().length()!=0){
    result= objDAO.insertChampionshipDetails(proCalId,eventId, eventTypeId, eventLevelId);
    }
    } catch(Exception e){
    Debug.print("Exception in KrishnastalessBean.insertChampionshipDetails()" + e.getMessage());
    }
    return result;
    }*/

    public String getProvCalId(String eventId, int year) throws RemoteException {
        Debug.print("KrishnaStatelesBean.getProvCalId() :");
        String eveId = "";

        try {
            if (eventId != null && eventId.trim().length() != 0) {
                eveId = objDAO.getProvCalId(eventId, year);
            }
        } catch (Exception e) {
            Debug.print("Exception in KrishnastalessBean.getProvCalId()" + e.getMessage());
        }
        return eveId;
    }

    public ArrayList getAllEventLevels() throws RemoteException {
        Debug.print("KrishnaStatelesBean.getAllEventLevels() Called");
        ArrayList eventList = new ArrayList();
        HLCEventCalendarDAO EventCalendarDAO = new HLCEventCalendarDAO();
        try {
            eventList = EventCalendarDAO.selectAllEventLevels();
        } catch (Exception e) {
            Debug.print("Exception in KrishnaStatelesBean.getAllEventLevels()" + e.getMessage());
        }
        Debug.print("KrishnaStatelesBean.getAllEventLevels():" + eventList.size());
        return eventList;
    }

    public ArrayList getSelectedEventLevels(String calendarId) throws RemoteException {
        Debug.print("KrishnaStatelesBean.getSelectedEventLevels() Called");
        ArrayList eventList = new ArrayList();
        try {
            if (calendarId != null && calendarId.trim().length() != 0) {
                eventList = objDAO.selectLevelsChecked(calendarId);
            }
        } catch (Exception e) {
            Debug.print("Exception in KrishnaStatelesBean.getSelectedEventLevels()" + e.getMessage());
        }
        Debug.print("KrishnaStatelesBean.getSelectedEventLevels():" + eventList.size());
        return eventList;
    }

    public boolean updateChampStatus(String eventLevelId, String eventTypeId, String provisionalId) throws RemoteException {
        Debug.print("KrishnaStatelesBean.updateChampStatus() :");
        boolean result = false;
        try {
            if (provisionalId != null && provisionalId.trim().length() != 0) {
                result = objDAO.updateChampStatus(eventLevelId, eventTypeId, provisionalId);

            }
        } catch (Exception e) {
            Debug.print("Exception in KrishnastalessBean.updateChampStatus()" + e.getMessage());
        }
        return result;
    }

    public ArrayList getOrganizerList(int year, String acStatus, String organizerId) throws RemoteException {
        ArrayList organizerList = new ArrayList();
        HLCEventCalendarDAO EventCalendarDAO = new HLCEventCalendarDAO();
        try {
            if (year != 0 && organizerId != null && organizerId.trim().length() != 0) {
                organizerList = EventCalendarDAO.getOrganizerList(year, acStatus, organizerId);
            }
            Debug.print("ArrayList Size in Session Bean: " + organizerList.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return organizerList;
    }

    public HLCCalendarVO getSingleEventDetails(String provisionalId) throws RemoteException {
        HLCCalendarVO calVO = new HLCCalendarVO();
        HLCEventCalendarDAO EventCalendarDAO = new HLCEventCalendarDAO();
        try {
            if (provisionalId != null && provisionalId.trim().length() != 0) {
                calVO = EventCalendarDAO.getSingleEventDetails(provisionalId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return calVO;
    }

    public HLCCalendarVO getSingleEventDetailsByEventId(String eventId) throws RemoteException {
        HLCCalendarVO calVO = new HLCCalendarVO();
        HLCEventCalendarDAO EventCalendarDAO = new HLCEventCalendarDAO();
        try {
            if (eventId != null && eventId.trim().length() != 0) {
                calVO = EventCalendarDAO.getSingleEventDetailsByEventId(eventId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return calVO;
    }

    public boolean approveSingleEventDetails(String provisionalId, String orgStatus, String orgComments) throws RemoteException {
        boolean result = false;
        HLCEventCalendarDAO EventCalendarDAO = new HLCEventCalendarDAO();
        try {
            if (provisionalId != null && provisionalId.trim().length() != 0) {
                result = EventCalendarDAO.approveSingleEventDetails(provisionalId, orgStatus, orgComments);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList getAreaChairList(int year, String acStatus, String acId) throws RemoteException {
        ArrayList areaChairList = new ArrayList();
        HLCEventCalendarDAO EventCalendarDAO = new HLCEventCalendarDAO();
        try {
            if (year != 0 && acId != null && acId.trim().length() != 0) {
                areaChairList = EventCalendarDAO.getAreaChairList(year, acStatus, acId);
            }
            Debug.print("ArrayList Size in Session Bean: " + areaChairList.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return areaChairList;
    }

    public boolean approveAreaChairDetails(String provisionalId, String acStatus, String acComments) throws RemoteException {
        boolean result = false;
        HLCEventCalendarDAO EventCalendarDAO = new HLCEventCalendarDAO();
        try {
            if (provisionalId != null && provisionalId.trim().length() != 0) {
                result = EventCalendarDAO.approveAreaChairDetails(provisionalId, acStatus, acComments);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean updateEventDetByOrg(HLCCalendarVO calVO) throws RemoteException {
        Debug.print("KrishnaStatelesBean.updateEventDetByOrg() Called");
        boolean result = false;
        try {
            if (calVO.getCalenderId() != null && calVO.getCalenderId().trim().length() != 0) {
                result = objDAO.updateEventDetByOrg(calVO);
            }
        } catch (Exception e) {
            Debug.print("Exception in KrishnaStatelesBean.updateEventDetByOrg()" + e.getMessage());
        }
        return result;
    }

    public boolean updateEventDetByAreaChair(HLCCalendarVO calVO) throws RemoteException {
        Debug.print("KrishnaStatelesBean.updateEventDetByAreaChair() Called");
        boolean result = false;
        try {
            if (calVO.getCalenderId() != null && calVO.getCalenderId().trim().length() != 0) {
                result = objDAO.updateEventDetByAreaChair(calVO);
            }
        } catch (Exception e) {
            Debug.print("Exception in KrishnaStatelesBean.updateEventDetByAreaChair()" + e.getMessage());
        }
        return result;
    }

    public boolean updateEventDetByStaff(HLCCalendarVO calVO) throws RemoteException {
        Debug.print("KrishnaStatelesBean.updateEventDetByStaff() Called");
        boolean result = false;
        try {
            if (calVO.getCalenderId() != null && calVO.getCalenderId().trim().length() != 0) {
                result = objDAO.updateEventDetByStaff(calVO);
            }
        } catch (Exception e) {
            Debug.print("Exception in KrishnaStatelesBean.updateEventDetByStaff()" + e.getMessage());
        }
        return result;
    }

    public ArrayList getAllAreaMasters() throws RemoteException {
        Debug.print("KrishnaStatelessBean getAllAreaMasters()");
        HLCEventCalendarDAO EventCalendarDAO = new HLCEventCalendarDAO();
        ArrayList results = EventCalendarDAO.selectAllAreaMaster();
        return results;
    }

    public ArrayList getAllStateMasters() throws RemoteException {
        ArrayList results = null;
        Debug.print("KrishnaStatelessBean getAllStateMasters()");
        try {
            results = objDAO.selectAllStateMaster();
        } catch (Exception e) {
            Debug.print("Exception in KrishnaStatelesBean.getAllStateMasters()" + e.getMessage());
        }
        return results;
    }

    public Vector getAllMapTyLe() throws RemoteException {

        Vector vecObj = new Vector();
        try {
            makeConnection();
            System.out.println("Inside the Loop getAllMapTyLe");
            String typeId = "select event_type_id from tblMeeEventTypeMaster";
            PreparedStatement prepStmt1 = con.prepareStatement(typeId);
            ResultSet rs1 = prepStmt1.executeQuery();
            System.out.println("Main TypeIds:" + typeId);
            System.out.println("Event Id Inside the getAllMapTyLe  ");
            while (rs1.next()) {
                String eventID = rs1.getString(1);

                System.out.println("Event Id Inside the getAllMapTyLe  " + typeId);
                String selectStr = "select A.map_type_id,A.event_type_id,A.event_level_id, B.event_type_name, C.event_level_code, C.event_level_name " +
                        "from tblMeeMapEventLevel A, tblMeeEventTypeMaster B, tblMeeEventLevelMaster C  " +
                        "where A.event_type_id = B.event_type_id and A.event_level_id = C.event_level_id and A.event_type_id = ? order by event_level_code";

                PreparedStatement prepStmt = con.prepareStatement(selectStr);
                System.out.println("eventID:" + eventID);
                prepStmt.setString(1, eventID);
                ResultSet rs = prepStmt.executeQuery();
                while (rs.next()) {
                    String mapid = rs.getString(1);
                    System.out.println("mapid:" + mapid);
                    String tyid = rs.getString(2);
                    System.out.println("tyid:" + tyid);
                    String leid = rs.getString(3);
                    System.out.println("leid:" + leid);
                    String tyname = rs.getString(4);
                    System.out.println("tyname:" + tyname);
                    String levelcode = rs.getString(5);
                    System.out.println("levelcode:" + levelcode);
                    String levelName = rs.getString(6);
                    System.out.println("levelName:" + levelName);
                    String[] eventName = {mapid, tyid, leid, tyname, levelcode, levelName};
                    vecObj.add(eventName);
                }
                prepStmt.close();
            }

            prepStmt1.close();
            releaseConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vecObj;
    }

    public int getMaxEventId() throws RemoteException {

        int maxEventId = 0;

        try {
            makeConnection();
            System.out.println("Inside the Loop getAllMapTyLe");
            String sqlMaxEventId = "SELECT max(cast(event_id as int)) from tblMeeEventDetails";
            PreparedStatement prepStmt1 = con.prepareStatement(sqlMaxEventId);
            ResultSet rs1 = prepStmt1.executeQuery();
            System.out.println("max eventId:" + sqlMaxEventId);
            while (rs1.next()) {
                maxEventId = rs1.getInt(1);
            }
            prepStmt1.close();
            releaseConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maxEventId;
    }

    public boolean updateChampionshipDetails(ArrayList champList) throws RemoteException {
        Debug.print("KrishnaStatelessBean.updateChampionshipDetails() Called");
        boolean result = false;
        try {
            if (champList != null && champList.size() != 0) {
                Iterator it = champList.iterator();
                while (it.hasNext()) {
                    String champDetList[] = (String[]) it.next();
                    String provisionalId = champDetList[0];
                    String eventId = champDetList[1];
                    String eventTypeId = champDetList[2];
                    String eventLevelId = champDetList[3];
                    result = objDAO.insertChampDetails(provisionalId, eventId, eventTypeId, eventLevelId);
                    Debug.print("KrishnaStatelessBean.updateChampionshipDetails() Returned Result:" + result);
                }
            }
        } catch (Exception e) {
            Debug.print("Exception in RegistrationSessionBean.updateChampionshipDetails()" + e.getMessage());
        }
        Debug.print("KrishnaStatelessBean.updateChampionshipDetails() Returned Result:" + result);
        return result;
    }

    public ArrayList getUseaStaffList(int year, String areaId) throws RemoteException {
        Debug.print("KrishnaStatelessBean.getUseaStaffList()");
        Debug.print("year inside sessionbean" + year);
        Debug.print("areaId inside sessionbean" + areaId);
        HLCEventCalendarDAO EventCalendarDAO = new HLCEventCalendarDAO();
        ArrayList proCalObj = new ArrayList();
        try {
            if (year != 0 && areaId != null && areaId.trim().length() != 0) {
                proCalObj = EventCalendarDAO.selectUseaStaffList(year, areaId);
                Debug.print("getUseaStaffList in VaigaiSessionBean" + proCalObj);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return proCalObj;
    }

    public ArrayList searchEventDetailsList(String eventId, String eventTitle, String areaId) throws RemoteException {
        Debug.print("KrishnaStatelessBean.searchEventDetailsList()");
        Debug.print("eventId inside sessionbean" + eventId);
        Debug.print("eventTitle inside sessionbean" + eventTitle);
        Debug.print("areaId inside sessionbean" + areaId);

        HLCEventCalendarDAO EventCalendarDAO = new HLCEventCalendarDAO();
        ArrayList proCalObj = new ArrayList();
        try {
            if (eventId != null && eventId.trim().length() != 0 || eventTitle != null && eventTitle.trim().length() != 0 ||
                    areaId != null && areaId.trim().length() != 0) {
                proCalObj = EventCalendarDAO.selectEventDetailsList(eventId, eventTitle, areaId);
                Debug.print("searchEventDetailsList in KrishnaStatelessBean" + proCalObj);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return proCalObj;
    }

    public ArrayList searchEventDetailsListAdmin(String eventId, String eventTitle, String areaId, int compYear) throws RemoteException {
        Debug.print("KrishnaStatelessBean.searchEventDetailsList()");
        Debug.print("eventId inside sessionbean" + eventId);
        Debug.print("eventTitle inside sessionbean" + eventTitle);
        Debug.print("areaId inside sessionbean" + areaId);
        Debug.print("compYear inside sessionbean" + compYear);
        HLCEventCalendarDAO EventCalendarDAO = new HLCEventCalendarDAO();
        ArrayList proCalObj = new ArrayList();
        try {
            if (eventId != null && eventId.trim().length() != 0 || eventTitle != null && eventTitle.trim().length() != 0 ||
                    areaId != null && areaId.trim().length() != 0 || compYear != 0) {
                proCalObj = EventCalendarDAO.searchEventDetailsListAdmin(eventId, eventTitle, areaId, compYear);
                Debug.print("searchEventDetailsList in KrishnaStatelessBean" + proCalObj);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return proCalObj;
    }

    public boolean approveEventDetailsByUStaff(String provisionalId, String uStaffStatus, String uStaffComments) throws RemoteException {
        boolean result = false;
        HLCEventCalendarDAO EventCalendarDAO = new HLCEventCalendarDAO();
        try {
            if (provisionalId != null && provisionalId.trim().length() != 0) {
                result = EventCalendarDAO.approveEventDetailsByUStaff(provisionalId, uStaffStatus, uStaffComments);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

   public ArrayList getFinalizedEventCalDetails(String areaId) throws RemoteException{
        Debug.print("KrishnaStatelessBean.getFinalizedEventCalDetails()");
        //Debug.print("year inside sessionbean"+year);
        Debug.print("areaId inside sessionbean"+areaId);
         HLCEventCalendarDAO EventCalendarDAO = new HLCEventCalendarDAO();
        ArrayList proCalObj = new ArrayList();
        try{
            //if(year!=0){
                proCalObj = EventCalendarDAO.selectApprovedEveCalendarDets( areaId);
                Debug.print("getFinalizedEventCalDetails in getFinalizedEventCalDetails" + proCalObj);
                
            //} 
        } catch(Exception e){
            e.printStackTrace();
        }
        return proCalObj;
    }

    public ArrayList getMappedEventLavelBasedOnEventType(String eventId) throws RemoteException {
        Debug.print("KrishnaStatelesBean.getEventDetailsBasedOnEventId() :");
        ArrayList mappedLevel = new ArrayList();
        try {
            if (eventId != null) {
                mappedLevel = objDAO.getMappedEventLavelBasedOnEventType(eventId);

            }
        } catch (Exception e) {
            Debug.print("Exception in getEventDetails.getEventDetailsBasedOnEventId()" + e.getMessage());
        }
        return mappedLevel;
    }

    /**********************************************For Event Entries************************************************************************************/
    public Vector getAllEventTypes() throws RemoteException {
        Debug.print("KrishnaStatelessBean.getAllEventTypes()");
        HLCEventCalendarDAO EventCalendarDAO = new HLCEventCalendarDAO();
        Vector vecValue = new Vector();
        try {
            vecValue = EventCalendarDAO.selectAllEventTypes();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return vecValue;
    }

    public ArrayList getUserTypes() throws RemoteException {
        Debug.print("KrishnaStatelessBean.getUserTypes()");
        HLCEventCalendarDAO EventCalendarDAO = new HLCEventCalendarDAO();
        ArrayList objUsrTyp = new ArrayList();
        try {
            objUsrTyp = EventCalendarDAO.selectUserTypes();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return objUsrTyp;
    }

    public ArrayList getDivisions() throws RemoteException {
        Debug.print("KrishnaStatelessBean.getDivisions()");
        HLCEventCalendarDAO EventCalendarDAO = new HLCEventCalendarDAO();
        ArrayList objDiv = new ArrayList();
        try {
            objDiv = EventCalendarDAO.selectDivisions();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return objDiv;
    }

    public ArrayList getMembershipTypes(String userTypeId) throws RemoteException {
        Debug.print("KrishnaStatelessBean.getMembershipTypes()");
        HLCEventCalendarDAO EventCalendarDAO = new HLCEventCalendarDAO();
        ArrayList objMembTyps = new ArrayList();
        try {
            if (userTypeId != null && userTypeId.trim().length() != 0) {
                Debug.print("KrishnaStatelessBean.userTypeId()" + userTypeId);
                objMembTyps = EventCalendarDAO.selectMembershipTypes(userTypeId);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return objMembTyps;
    }

    public ArrayList getEventLevelsForArea(String areaId) throws RemoteException {
        Debug.print("KrishnaStatelessBean.getDivisions()");
        Debug.print("areaId:" + areaId);
        //Debug.print("champStatus:"+champStatus);
        HLCEventCalendarDAO EventCalendarDAO = new HLCEventCalendarDAO();
        ArrayList eventDetails = new ArrayList();
        try {
            if (areaId != null && areaId.trim().length() != 0) {
                eventDetails = EventCalendarDAO.getEventLevelsForArea(areaId);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return eventDetails;
    }

    public ArrayList getEventLevelsBasedOnEventId(String eventId) throws RemoteException {
        Debug.print("KrishnaStatelessBean.getEventLevelsBasedOnEventId()");
        //Debug.print("areaId:"+areaId);
        HLCEventCalendarDAO EventCalendarDAO = new HLCEventCalendarDAO();
        ArrayList eventDetails = new ArrayList();
        try {
            eventDetails = EventCalendarDAO.getEventLevelsBasedOnEventId(eventId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return eventDetails;
    }

    public boolean insertValidationDetails(HLCValidationVO objValidation) throws RemoteException {
        Debug.print("KrishnaStatelesBean.insertValidationDetails() :");
        Debug.print("KrishnaStatelesBean insertValidationDetails():" + objValidation.getEventTypeId());
        Debug.print("KrishnaStatelesBean insertValidationDetails():" + objValidation.getUserTypeId());
        Debug.print("KrishnaStatelesBean insertValidationDetails():" + objValidation.getEventDivisionId());
        Debug.print("KrishnaStatelesBean insertValidationDetails():" + objValidation.getMembTypeName());
        boolean objVal = false;
        String eventTypeId = objValidation.getEventTypeId();
        String userTypeId = objValidation.getUserTypeId();
        String areaId = objValidation.getAreaId();
        //String areaId=objValidation.getAreaId();
        String eventDivId = objValidation.getEventDivisionId();
        String memName = objValidation.getMembTypeName();
        HLCEventCalendarDAO EventCalendarDAO = new HLCEventCalendarDAO();
        try {
            if (eventTypeId != null && eventTypeId.trim().length() != 0 && userTypeId != null && userTypeId.trim().length() != 0) {
                objVal = EventCalendarDAO.insertValidationDetails(objValidation);
            }
        } catch (Exception e) {
            Debug.print("Exception in KrishnastalessBean.insertValidationDetails()" + e.getMessage());
        }
        return objVal;
    }

    public ArrayList getValidationDetails(String eventTypId, String usrTypId, String divisionId, String areaId, String chmpStatus) throws RemoteException {
        ArrayList valVO = new ArrayList();
        HLCEventCalendarDAO EventCalendarDAO = new HLCEventCalendarDAO();

        try {
            if (eventTypId != null && eventTypId.trim().length() != 0 && usrTypId != null && usrTypId.trim().length() != 0) {
                valVO = EventCalendarDAO.getValidationDetails(eventTypId, usrTypId, divisionId, areaId, chmpStatus);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return valVO;
    }

    public boolean updateValidationDetails(HLCValidationVO validVO) throws RemoteException {
        Debug.print("KrishnaStatelesBean.updateValidationDetails() Called");
        boolean result = false;
        try {
            if (validVO.getQualificationId() != null && validVO.getQualificationId().trim().length() != 0) {
                Debug.print(" Qualification KrishnaStatelesBean.updateValidationDetails()" + validVO.getQualificationId());
                result = objDAO.updateValidationDetails(validVO);
            }
        } catch (Exception e) {
            Debug.print("Exception in KrishnaStatelesBean.updateValidationDetails()" + e.getMessage());
        }
        return result;
    }

    public ArrayList getValidationDetsForEdit(String eventTypId, String usrTypId, String divisionId, String areaId, String chmpStatus) throws RemoteException {
        Debug.print("KrishnaStatelesBean.getValidationDetsForEdit() Called");
        Debug.print("EventType.getValidationDetsForEdit() Called" + eventTypId);
        Debug.print("UserType.getValidationDetsForEdit() Called" + usrTypId);
        Debug.print("area.getValidationDetsForEdit() Called" + areaId);
        Debug.print("division.getValidationDetsForEdit() Called" + divisionId);
        Debug.print("Championship.getValidationDetsForEdit() Called" + chmpStatus);
        ArrayList valVO = new ArrayList();
        HLCEventCalendarDAO EventCalendarDAO = new HLCEventCalendarDAO();

        try {
            if (eventTypId != null && eventTypId.trim().length() != 0 && usrTypId != null && usrTypId.trim().length() != 0) {
                Debug.print("KrishnaStatelesBean.getValidationDetsForEdit()" + eventTypId);
                Debug.print("KrishnaStatelesBean.getValidationDetsForEdit()" + usrTypId);
                valVO = EventCalendarDAO.getValidationDetsForEdit(eventTypId, usrTypId, divisionId, areaId, chmpStatus);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return valVO;
    }

    public boolean mappingAreaWithEventLevel(String areaId, ArrayList eventLevelList) throws RemoteException {
        Debug.print("KrishnaStatelesBean mappingAreaWithEventLevel()" + areaId);
        Debug.print("KrishnaStatelesBean mappingAreaWithEventLevel()" + eventLevelList);
        boolean result = false;
        HLCEventCalendarDAO EventCalendarDAO = new HLCEventCalendarDAO();
        try {
            if (areaId != null && areaId.trim().length() != 0) {
                result = EventCalendarDAO.mappingAreaWithEventLevel(areaId, eventLevelList);
            }

        } catch (Exception e) {
            Debug.print("Exception in KrishnaStatelesBean.mappingAreaWithEventLevel()" + e.getMessage());
        }
        return result;
    }

    public boolean getMapDetailsExists(String areaId, String eventLevelId) throws RemoteException {
        Debug.print("KrishnaStatelesBean.getMapDetailsExists() :");
        Debug.print("KrishnaStatelesBean getMapDetailsExists():" + areaId);
        Debug.print("KrishnaStatelesBean getMapDetailsExists():" + eventLevelId);

        boolean result = false;
        try {
            if (areaId != null && areaId.trim().length() != 0 && eventLevelId != null && eventLevelId.trim().length() != 0) {
                result = objDAO.getMapDetailsExists(areaId, eventLevelId);

            }
        } catch (Exception e) {
            Debug.print("Exception in KrishnastalessBean.getMapDetailsExists()" + e.getMessage());
        }
        return result;
    }

    public boolean ValidationDetailsExist(String eventTypId, String usrTypId, String divisionId, String areaId, String chmpStatus) throws RemoteException {
        Debug.print("KrishnaStatelesBean.ValidationDetailsExist() :");
        Debug.print("KrishnaStatelesBean ValidationDetailsExist():" + eventTypId);
        Debug.print("KrishnaStatelesBean ValidationDetailsExist():" + usrTypId);
        Debug.print("KrishnaStatelesBean ValidationDetailsExist():" + divisionId);
        //Debug.print("KrishnaStatelesBean ValidationDetailsExist():" + membTypeId);
        boolean result = false;
        try {
            if (eventTypId != null && eventTypId.trim().length() != 0 && usrTypId != null && usrTypId.trim().length() != 0) {
                result = objDAO.ValidationDetailsExist(eventTypId, usrTypId, divisionId, areaId, chmpStatus);

            }
        } catch (Exception e) {
            Debug.print("Exception in KrishnastalessBean.ValidationDetailsExist()" + e.getMessage());
        }
        return result;
    }

    public ArrayList getEventLevelsBasedOnAreaId(String areaId) throws RemoteException {
        Debug.print("KrishnaStatelessBean.getEventLevelsBasedOnAreaId()");
        //Debug.print("areaId:"+areaId);
        HLCEventCalendarDAO EventCalendarDAO = new HLCEventCalendarDAO();
        ArrayList eventDetails = new ArrayList();
        try {
            eventDetails = EventCalendarDAO.getEventLevelsBasedOnAreaId(areaId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return eventDetails;
    }

    /*public boolean updateAreaEveLevelMapping(String areaId, ArrayList eventLevelList) throws RemoteException {
    Debug.print("KrishnaStatelesBean updateAreaEveLevelMapping()" + areaId);
    Debug.print("KrishnaStatelesBean updateAreaEveLevelMapping()" + eventLevelList);
    boolean result =false;
    HLCEventCalendarDAO EventCalendarDAO = new HLCEventCalendarDAO();        
    try{                       
    if(areaId!=null && areaId.trim().length()!=0 && eventLevelList!=null){
    result=EventCalendarDAO.updateAreaEveLevelMapping(areaId, eventLevelList);
    }           
    } catch(Exception e){
    Debug.print("Exception in KrishnaStatelesBean.updateAreaEveLevelMapping()" + e.getMessage());
    }
    return result;
    }*/
    public ArrayList getProvCalIdBasedOnYear(String year) throws RemoteException {
        Debug.print("KrishnaStatelessBean.getProvCalIdBasedOnYear()");
        ArrayList eventDetails = new ArrayList();
        try {
            if (year != null && year.trim().length() != 0) {
                eventDetails = objDAO.getProvCalIdBasedOnYear(year);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return eventDetails;
    }

    public boolean deleteCalendarDetails(String calId) throws RemoteException {
        Debug.print("KrishnaStatelesBean.deleteCalendarDetails() :");
        boolean result = false;
        try {
            if (calId != null && calId.trim().length() != 0) {
                result = objDAO.deleteCalendarDetails(calId);

            }
        } catch (Exception e) {
            Debug.print("Exception in KrishnastalessBean.deleteCalendarDetails()" + e.getMessage());
        }
        return result;
    }

    public boolean updateChampDetails(String provisionalId, String eventId, String eventType, String eventLevel, String champStatus, String divisionId) throws RemoteException {
        boolean result = false;
        HLCEventCalendarDAO EventCalendarDAO = new HLCEventCalendarDAO();
        try {
            if (provisionalId != null && provisionalId.trim().length() != 0) {
                result = EventCalendarDAO.insertChampionshipDet(provisionalId, eventId, eventType, eventLevel, champStatus, divisionId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean deleteChampDetails(String eventId) throws RemoteException {
        Debug.print("KrishnaStatelesBean.deleteChampDetails() :");
        boolean result = false;
        try {
            if (eventId != null && eventId.trim().length() != 0) {
                result = objDAO.deleteChampDetails(eventId);

            }
        } catch (Exception e) {
            Debug.print("Exception in KrishnastalessBean.deleteChampDetails()" + e.getMessage());
        }
        return result;
    }

    public ArrayList getEventDetsApprovedByAll(String provisionalId, String eventId) throws RemoteException {
        Debug.print("KrishnaStatelessBean.getEventDetsApprovedByAll()");
        Debug.print("provisionalId inside sessionbean" + provisionalId);
        Debug.print("eventId inside sessionbean" + eventId);
        HLCEventCalendarDAO EventCalendarDAO = new HLCEventCalendarDAO();
        ArrayList eveStatObj = new ArrayList();
        try {
            if (provisionalId != null) {
                eveStatObj = EventCalendarDAO.selectEventDetsApprovedByAll(provisionalId, eventId);
                Debug.print("getEventDetsApprovedByAll in VaigaiSessionBean" + eveStatObj);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return eveStatObj;
    }

    public ArrayList getOldEventDetails(String oldEventId) throws RemoteException {
        Debug.print("KrishnaStatelesBean.getOldEventDetails() :");
        ArrayList vObj = new ArrayList();
        try {
            if (oldEventId != null && oldEventId.trim().length() != 0) {
                vObj = objDAO.selectOldEventDetails(oldEventId);

            }
        } catch (Exception e) {
            Debug.print("Exception in getEventDetails().getOldEventDetails()" + e.getMessage());
        }
        return vObj;
    }

    public boolean insertProCalEveIntoEveDetails(HLCEventRequestVO objEventDetails) throws RemoteException {
        Debug.print("KrishnaStatelesBean.insertProCalEveIntoEveDetails() :");
        boolean result = false;
        String newEveId = objEventDetails.getEvent_id();

        try {
            if (newEveId != null && newEveId.trim().length() != 0) {
                result = objDAO.insertProCalEveIntoEveDetails(objEventDetails);

            }
        } catch (Exception e) {
            Debug.print("Exception in KrishnastalessBean.insertCalenderDetails()" + e.getMessage());
        }
        return result;
    }

    public ArrayList getProvEventDetails(String eventId) throws RemoteException {
        Debug.print("KrishnaStatelesBean.getProvEventDetails() :");
        ArrayList vObj = new ArrayList();
        try {
            if (eventId != null) {
                vObj = objDAO.getProvEventDetails(eventId);

            }
        } catch (Exception e) {
            Debug.print("Exception in getEventDetails.getProvEventDetails()" + e.getMessage());
        }
        return vObj;
    }

    public boolean updateNewEveDets(String orgId, String eveTitle, Date eveBegDt, Date eveEndDt, Date oeBegDt, Date oeEndDt, Date oeExDueDt, String state, String area, String eventId) throws RemoteException {
        Debug.print("KrishnaStatelesBean.updateNewEveDets() :");
        boolean result = false;
        HLCEventCalendarDAO EventCalendarDAO = new HLCEventCalendarDAO();
        try {
            if (eventId != null && eventId.trim().length() != 0) {
                result = EventCalendarDAO.updateNewEveDets(orgId, eveTitle, eveBegDt, eveEndDt, oeBegDt, oeEndDt, oeExDueDt, state, area, eventId);
                Debug.print("updateNewEveDets in VaigaiSessionBean" + result);
            }
        } catch (Exception e) {
            Debug.print("Exception in KrishnastalessBean.insertCalenderDetails()" + e.getMessage());
        }
        return result;
    }

    public boolean updateOmnibusDetsInAllTables(String eventId, String oldEventId) throws RemoteException {
        Debug.print("KrishnaStatelesBean.updateOmnibusDetsInAllTables() :");
        boolean result = false;
        HLCEventCalendarDAO EventCalendarDAO = new HLCEventCalendarDAO();
        try {
            if (eventId != null && eventId.trim().length() != 0) {
                result = EventCalendarDAO.updateOmnibusDetsInAllTables(eventId, oldEventId);
                Debug.print("updateOmnibusDetsInAllTables in VaigaiSessionBean" + result);
            }
        } catch (Exception e) {
            Debug.print("Exception in KrishnastalessBean.updateOmnibusDetsInAllTables()" + e.getMessage());
        }
        return result;
    }

    public boolean eveDetailsExistsInEveTable(String newEventId) throws RemoteException {
        Debug.print("KrishnaStatelesBean.eveDetailsExistsInEveTable() :");
        boolean result = false;
        HLCEventCalendarDAO EventCalendarDAO = new HLCEventCalendarDAO();
        try {
            if (newEventId != null && newEventId.trim().length() != 0) {
                result = EventCalendarDAO.eveDetailsExistsInEveTable(newEventId);
                Debug.print("eveDetailsExistsInEveTable in VaigaiSessionBean" + result);
            }
        } catch (Exception e) {
            Debug.print("Exception in KrishnastalessBean.eveDetailsExistsInEveTable()" + e.getMessage());
        }
        return result;
    }

    public ArrayList getOldEventHistoryDetails(String oldEventId) throws RemoteException {
        Debug.print("KrishnaStatelesBean.getOldEventHistoryDetails() :");
        ArrayList vObj = new ArrayList();
        try {
            if (oldEventId != null && oldEventId.trim().length() != 0) {
                vObj = objDAO.selectOldEventHistoryDetails(oldEventId);

            }
        } catch (Exception e) {
            Debug.print("Exception in getEventDetails().getOldEventHistoryDetails()" + e.getMessage());
        }
        return vObj;
    }

    public boolean insertProCalEveIntoEveHistoryDetails(HLCEventRequestVO objEventDetails) throws RemoteException {
        Debug.print("KrishnaStatelesBean.insertProCalEveIntoEveHistoryDetails() :");
        boolean result = false;
        String newEveId = objEventDetails.getEvent_id();

        try {
            if (newEveId != null && newEveId.trim().length() != 0) {
                result = objDAO.insertProCalEveIntoEveHistoryDetails(objEventDetails);

            }
        } catch (Exception e) {
            Debug.print("Exception in KrishnastalessBean.insertProCalEveIntoEveHistoryDetails()" + e.getMessage());
        }
        return result;
    }

    public String getZipCodeDetail(String memberId) throws RemoteException {
        String zipCode = "";
        Debug.print("KrishnaStatelessBean getZipCodeDetail()");
        try {
            zipCode = objDAO.getZipCode(memberId);
        } catch (Exception e) {
            Debug.print("Exception in KrishnaStatelesBean.getZipCodeDetail()" + e.getMessage());
        }
        return zipCode;
    }

    public String getAreaDetail(String zipCode) throws RemoteException {
        String area = "";
        Debug.print("KrishnaStatelessBean getAreaDetail()");
        try {
            area = objDAO.getArea(zipCode);
        } catch (Exception e) {
            Debug.print("Exception in KrishnaStatelesBean.getAreaDetail()" + e.getMessage());
        }
        return area;
    }

    public ArrayList getEventDivision() throws RemoteException {
        ArrayList division = new ArrayList();
        Debug.print("KrishnaStatelessBean getEventDivision()");
        try {
            division = objDAO.getEventDivisionDetail();
        } catch (Exception e) {
            Debug.print("Exception in KrishnaStatelesBean.getEventDivision()" + e.getMessage());
        }
        return division;
    }

    public ArrayList getselectDivisionDetails(String provisionalId) throws RemoteException {
        ArrayList division = new ArrayList();
        Debug.print("KrishnaStatelessBean getselectDivisionDetails()");
        try {
            division = objDAO.getSelectedDivisionDetails(provisionalId);
        } catch (Exception e) {
            Debug.print("Exception in KrishnaStatelesBean.getSelectedDivisionDetails()" + e.getMessage());
        }
        return division;
    }

    public String getEventTypeName(String eventTypeId) throws RemoteException {
        String eventName = "";
        Debug.print("KrishnaStatelessBean getEventTypaName()");
        try {
            eventName = objDAO.getEventName(eventTypeId);
        } catch (Exception e) {
            Debug.print("Exception in KrishnaStatelesBean.getEventTypaName()" + e.getMessage());
        }
        return eventName;
    }

    public ArrayList getApprovedEventDet(String provisionalId, String eventId) throws RemoteException {
        Debug.print("KrishnaStatelessBean.getApprovedEventDet()");
        Debug.print("provisionalId inside sessionbean" + provisionalId);
        Debug.print("eventId inside sessionbean" + eventId);
        HLCEventCalendarDAO EventCalendarDAO = new HLCEventCalendarDAO();
        ArrayList eveStatObj = new ArrayList();
        try {
            if (provisionalId != null) {
                eveStatObj = EventCalendarDAO.selectApprovedEventDet(provisionalId, eventId);
                Debug.print("getApprovedEventDet in VaigaiSessionBean" + eveStatObj);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return eveStatObj;
    }

    public ArrayList getEveTyLevIdDet(String provisionalId, String eventId) throws RemoteException {
        Debug.print("KrishnaStatelessBean.getEveTyLevIdDet()");
        Debug.print("provisionalId inside sessionbean" + provisionalId);
        Debug.print("eventId inside sessionbean" + eventId);
        HLCEventCalendarDAO EventCalendarDAO = new HLCEventCalendarDAO();
        ArrayList eveStatObj = new ArrayList();
        try {
            if (provisionalId != null) {
                eveStatObj = EventCalendarDAO.selectEveTyLevIdDet(provisionalId, eventId);
                Debug.print("getEveTyLevIdDet in VaigaiSessionBean" + eveStatObj);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return eveStatObj;
    }

    public boolean insertEveDetails(HLCEventRequestVO objEventDetails) throws RemoteException {
        Debug.print("KrishnaStatelesBean.insertEveDetails() :");
        boolean result = false;
        String newEveId = objEventDetails.getEvent_id();

        try {
            if (newEveId != null && newEveId.trim().length() != 0) {
                result = objDAO.insertEventDetailsFrmProCal(objEventDetails);

            }
        } catch (Exception e) {
            Debug.print("Exception in KrishnastalessBean.insertEveDetails()" + e.getMessage());
        }
        return result;
    }

    public String getEveTypLevelMapId(String eventTypeId, String eventLevelId) throws RemoteException {
        Debug.print("KrishnaStatelessBean.getEveTypLevelMapId()");
        //Debug.print("provisionalId inside sessionbean"+eventTypeId);
        // Debug.print("eventId inside sessionbean"+eventLevelId);
        HLCEventCalendarDAO EventCalendarDAO = new HLCEventCalendarDAO();
        String mapTypId = null;
        try {
            if (eventTypeId != null && eventLevelId != null) {
                mapTypId = EventCalendarDAO.getEveTypLevelMapId(eventTypeId, eventLevelId);
                Debug.print("getEveTypLevelMapId in VaigaiSessionBean" + mapTypId);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapTypId;
    }

    public boolean insertMapDets(String eventId, String mapTypId) throws RemoteException {
        Debug.print("KrishnaStatelesBean.insertMapDets() :");
        boolean result = false;

        try {
            if (mapTypId != null && mapTypId.trim().length() != 0 && eventId != null) {
                result = objDAO.insertMapDetails(eventId, mapTypId);

            }
        } catch (Exception e) {
            Debug.print("Exception in KrishnastalessBean.insertMapDets()" + e.getMessage());
        }
        return result;
    }

    public boolean getMapDets(String eventId, String mapTypId) throws RemoteException {
        Debug.print("KrishnaStatelesBean.getMapDets() :");
        boolean result = false;

        try {
            if (mapTypId != null && mapTypId.trim().length() != 0 && eventId != null) {
                result = objDAO.selectMapDetails(eventId, mapTypId);

            }
        } catch (Exception e) {
            Debug.print("Exception in KrishnastalessBean.getMapDets()" + e.getMessage());
        }
        return result;
    }

    public ArrayList getChampionEveLevelDets(String eventId) throws RemoteException {
        ArrayList chmpEveLevelList = new ArrayList();
        try {

            chmpEveLevelList = objDAO.selectChampionEveLevelDets(eventId);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return chmpEveLevelList;
    }

    public boolean deleteEveTypeDets(String eventId) throws RemoteException {
        Debug.print("KrishnaStatelesBean.deleteEveTypeDets() :");
        boolean result = false;

        try {
            if (eventId != null && eventId.trim().length() != 0) {
                result = objDAO.deleteEveTypeDets(eventId);

            }
        } catch (Exception e) {
            Debug.print("Exception in KrishnastalessBean.deleteEveTypeDets()" + e.getMessage());
        }
        return result;
    }

    public boolean updateEveDetsInMeeEventTable(HLCCalendarVO calVO) throws RemoteException {
        Debug.print("KrishnaStatelesBean.updateEveDetsInMeeEventTable() Called");
        boolean result = false;
        try {
            if (calVO.getCalenderId() != null && calVO.getCalenderId().trim().length() != 0) {
                result = objDAO.updateEveDetsInMeeEventTable(calVO);
            }
        } catch (Exception e) {
            Debug.print("Exception in KrishnaStatelesBean.updateEveDetsInMeeEventTable()" + e.getMessage());
        }
        return result;
    }

    public boolean updateEveDetsInMeeEventTableByAdmin(HLCCalendarVO calVO) throws RemoteException {
        Debug.print("KrishnaStatelesBean.updateEveDetsInMeeEventTableByAdmin() Called");
        boolean result = false;
        try {
            if (calVO.getCalenderId() != null && calVO.getCalenderId().trim().length() != 0) {
                result = objDAO.updateEveDetsInMeeEventTableByAdmin(calVO);
            }
        } catch (Exception e) {
            Debug.print("Exception in KrishnaStatelesBean.updateEveDetsInMeeEventTableByAdmin()" + e.getMessage());
        }
        return result;
    }

    public ArrayList getAllIssues() throws RemoteException {
        Debug.print("KrishnaStatelesBean getAllIssues");
        ArrayList objIssue = new ArrayList();
        try {

            objIssue = objDAO.selectAllIssues();
        } catch (Exception e) {
            Debug.print("Exception in KrishnaStatelesBean.getAllIssues()" + e.getMessage());
        }
        return objIssue;
    }

    public String getEveSecretaryId(String eventId) throws RemoteException {
        Debug.print("KrishnaStatelessBean.getEveSecretaryId()");

        HLCEventCalendarDAO EventCalendarDAO = new HLCEventCalendarDAO();
        String eveSecreId = null;
        try {
            if (eventId != null && eventId != null) {
                eveSecreId = EventCalendarDAO.getEveSecretaryId(eventId);
                Debug.print("getEveSecretaryId in KrishnaStatelessBean" + eveSecreId);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return eveSecreId;
    }

    public String getUserName(String eveSecreId) throws RemoteException {
        Debug.print("KrishnaStatelessBean.getMemberId()");

        HLCEventCalendarDAO EventCalendarDAO = new HLCEventCalendarDAO();
        String usrName = "";
        try {
            if (eveSecreId != null && eveSecreId != null) {
                usrName = EventCalendarDAO.getUserName(eveSecreId);
                Debug.print("getUserName in KrishnaStatelessBean" + usrName);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usrName;
    }

    public String getEveSecretaryIdBasedOnMemId(String membId) throws RemoteException {
        Debug.print("KrishnaStatelessBean.getEveSecretaryIdBasedOnMemId()");

        HLCEventCalendarDAO EventCalendarDAO = new HLCEventCalendarDAO();
        String eveSecreId = null;
        try {
            if (membId != null && membId != null) {
                eveSecreId = EventCalendarDAO.getEveSecretaryIdBasedOnMemId(membId);
                Debug.print("getEveSecretaryIdBasedOnMemId in KrishnaStatelessBean" + eveSecreId);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return eveSecreId;
    }

    public String getEveSecretaryIdBasedOnUsr(String usrName) throws RemoteException {
        Debug.print("KrishnaStatelessBean.getEveSecretaryIdBasedOnUsr()");

        HLCEventCalendarDAO EventCalendarDAO = new HLCEventCalendarDAO();
        String eveSecreId = null;
        try {
            if (usrName != null && usrName != null) {
                eveSecreId = EventCalendarDAO.getEveSecretaryIdBasedOnUsr(usrName);
                Debug.print("getEveSecretaryIdBasedOnMemId in KrishnaStatelessBean" + eveSecreId);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return eveSecreId;
    }

    public boolean insertEveSecreRelationDets(String eveOrgId, String eveSecreId, String eventId) throws RemoteException {
        Debug.print("KrishnaStatelesBean.insertEveSecreRelationDets() Called");
        boolean result = false;
        try {
            if (eventId != null && eventId.trim().length() != 0) {
                result = objDAO.insertEveSecreRelationDets(eveOrgId, eveSecreId, eventId);
            }
        } catch (Exception e) {
            Debug.print("Exception in KrishnaStatelesBean.insertEveSecreRelationDets()" + e.getMessage());
        }
        return result;
    }

    public ArrayList getEveLevTypDets(String provisionalId) throws RemoteException {
        Debug.print("KrishnaStatelessBean.getEveLevTypDets()");
        //Debug.print("year inside sessionbean"+year);
        Debug.print("provisionalId inside sessionbean getEveLevTypDets" + provisionalId);
        HLCEventCalendarDAO EventCalendarDAO = new HLCEventCalendarDAO();
        ArrayList eveLeTypObj = new ArrayList();
        try {
            //if(year!=0){
            eveLeTypObj = EventCalendarDAO.selectEveLevTypDets(provisionalId);
            Debug.print("sessionbean in getEveLevTypDets" + eveLeTypObj);

        //} 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return eveLeTypObj;
    }
    //kalai
    public ArrayList getViews() throws RemoteException{
    	
    	HLCEventCalendarDAO EventCalendarDAO = new HLCEventCalendarDAO();
        ArrayList getViewsList = new ArrayList();
        try {
            
        	getViewsList = EventCalendarDAO.getViews();
            

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getViewsList;
    }
    
    public ArrayList getGroupValues() throws RemoteException{
    	HLCEventCalendarDAO EventCalendarDAO = new HLCEventCalendarDAO();
        ArrayList getGroupValuesList = new ArrayList();
        try {
            
        	getGroupValuesList = EventCalendarDAO.getGroupValues();
    

        
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getGroupValuesList;
    }
    
    public boolean insertGroupDetails(String groupId,String groupDet) throws RemoteException
    {
     	HLCEventCalendarDAO EventCalendarDAO = new HLCEventCalendarDAO();
    	boolean insertStatus=EventCalendarDAO.insertGroupDetails(groupId,groupDet);
    	return insertStatus;
    }
    
    public ArrayList getViewGroupValues(String group_id) throws RemoteException{
    	HLCEventCalendarDAO EventCalendarDAO = new HLCEventCalendarDAO();
        ArrayList getViewGroupValuesList = new ArrayList();
        try {
            
        	getViewGroupValuesList = EventCalendarDAO.getViewGroupValues(group_id);
    

        
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getViewGroupValuesList;
    }
    public boolean updateGroupDetails(String layerId,String groupId,String groupDet) throws RemoteException{
    	HLCEventCalendarDAO EventCalendarDAO = new HLCEventCalendarDAO();
    	boolean updateGroupDetails=EventCalendarDAO.updateGroupDetails(layerId,groupId,groupDet);
    	return updateGroupDetails;
    }
    public boolean alreadyExistCheck(String groupId,String groupDet) throws RemoteException{
     	HLCEventCalendarDAO EventCalendarDAO = new HLCEventCalendarDAO();
    	boolean alreadyExistCheckStatus=EventCalendarDAO.alreadyExistCheck(groupId,groupDet);
    	return alreadyExistCheckStatus;
    }
    public boolean deleteGroupValues(String[] chkIds) throws RemoteException{
    	HLCEventCalendarDAO EventCalendarDAO = new HLCEventCalendarDAO();
    	boolean delStatus=EventCalendarDAO.deleteGroupValues(chkIds);
    	return delStatus;
    }
    public String getGroupEditList(String layerId) throws RemoteException{
    	HLCEventCalendarDAO EventCalendarDAO = new HLCEventCalendarDAO();
    	String layerValue=EventCalendarDAO.getGroupEditList(layerId);
    	return layerValue;
    }
    public ArrayList getViewRelatedRoles() throws RemoteException{
    	HLCEventCalendarDAO EventCalendarDAO = new HLCEventCalendarDAO();
        ArrayList getViewRelatedRolesList = new ArrayList();
        try {
            
        	getViewRelatedRolesList = EventCalendarDAO.getViewRelatedRoles();
    

        
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getViewRelatedRolesList;
    }
    public ArrayList getViewRelatedRoleUsers(String viewRoleId) throws RemoteException{
    	HLCEventCalendarDAO EventCalendarDAO = new HLCEventCalendarDAO();
        ArrayList ViewRelatedRolesList = new ArrayList();
        try {            
        	ViewRelatedRolesList = EventCalendarDAO.getViewRelatedRoleUsers(viewRoleId);
           
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ViewRelatedRolesList;
    }
   
     public ArrayList  getviewRoleLOB(String viewRoleId) throws RemoteException{
    	HLCEventCalendarDAO EventCalendarDAO = new HLCEventCalendarDAO();
        ArrayList viewRoleLOBsList = new ArrayList();
        try {            
        	viewRoleLOBsList = EventCalendarDAO.getviewRoleLOB(viewRoleId);
           
        } catch (Exception e) {
            e.printStackTrace();
        }
        return viewRoleLOBsList;
    }
     public ArrayList  getviewRoleViewPoint() throws RemoteException{
     	HLCEventCalendarDAO EventCalendarDAO = new HLCEventCalendarDAO();
         ArrayList viewRoleViewPointList = new ArrayList();
         try {            
        	 viewRoleViewPointList = EventCalendarDAO.getviewRoleViewPoint();
            
         } catch (Exception e) {
             e.printStackTrace();
         }
         return viewRoleViewPointList;
     }
     public ArrayList  getUserMapLOBList() throws RemoteException{
      	HLCEventCalendarDAO EventCalendarDAO = new HLCEventCalendarDAO();
          ArrayList viewRoleViewPointList = new ArrayList();
          try {            
         	 viewRoleViewPointList = EventCalendarDAO.getUserMapLOBList();
             
          } catch (Exception e) {
              e.printStackTrace();
          }
          return viewRoleViewPointList;
      }
     
     
     public boolean insertLobViewpointMap(String viewRoleId,String viewUserId,String viewLobId,String[] viewPointId) throws RemoteException
     {
    	 HLCEventCalendarDAO EventCalendarDAO = new HLCEventCalendarDAO();
    	 boolean status=EventCalendarDAO.insertLobViewpointMap(viewRoleId,viewUserId,viewLobId,viewPointId);
    	 return status;
    	 
     }
     public ArrayList getSubViewList(String lobId,String viewId)throws RemoteException
     {
    	 HLCEventCalendarDAO EventCalendarDAO = new HLCEventCalendarDAO();
    	 ArrayList getSubViewList=EventCalendarDAO.getSubViewList(lobId,viewId);
    	 return getSubViewList;
    	 
     }
     
     public Map getSubViewMap(String lobId,String viewId)throws RemoteException{
    	 HLCEventCalendarDAO EventCalendarDAO = new HLCEventCalendarDAO();
    	Map hs=new HashMap();
    	 hs=EventCalendarDAO.getSubViewMap(lobId,viewId);
    	 return hs;
     }
     public ArrayList getTreeArtifactDetails(String user_Id,String lobId)throws RemoteException{
    	 HLCEventCalendarDAO EventCalendarDAO = new HLCEventCalendarDAO();
    	 ArrayList getTreeArtifactDetails=EventCalendarDAO.getTreeArtifactDetails(user_Id,lobId);
    	 return getTreeArtifactDetails;
     }
    //kalai
    /***************************************************************************************************************************************************/
    /**===================Initialization for Initial Context===============================*/
    public InitialContext getInitialContext() throws javax.naming.NamingException {
        if (this.ic == null) {
            ic = new InitialContext();
        }
        System.out.println("This is from getInitialContext()");
        return ic;
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
