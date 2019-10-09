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
package com.hlcevent.activity;


import com.hlccommon.util.HLCActivityOrganizerVO;
import com.hlccommon.util.DBHelper;
import com.hlccommon.util.Debug;
import com.hlccommon.util.HLCEJBAllJNDIs;
import javax.ejb.*;
import java.sql.*;
import javax.sql.DataSource;
import java.util.*;
import javax.naming.*;
import java.util.Date;

/**
 * This is the bean class for the ActivityOrganizerBean enterprise bean.
 * Created Sep 5, 2006 11:02:59 AM
 * @author suresh
 */
public class HLCActivityOrganizerBean implements EntityBean, HLCActivityOrganizerLocalBusiness {
    private EntityContext context;
    private Connection con;
    private String  activityMeetingId;
    private String  activityName;
    private Date  activityDate;
    private String  noOfDays;
    private String  useaAreaId;
    private String  location;
    private String  state;
    private String  country;
    private String  city;
    private String  zip;
    private String  activityOrganizerId;
    private String  activityTypeId;
    private String  otherActivityType;
    private String  activityFees;
    private String  instructorsCoaches;
    private String  facilities;
    private String  otherFacilities;
    private String  landOwnerName;
    private String  landOwnerBusinessName;
    private String  landOwnerAddress;
    private String  landOwnerCity;
    private String  landOwnerState;
    private String  landOwnerCountry;
    private String  landOwnerZip;
    private String  landOwnerPhone;
    private boolean  additionalSites;
    private String  additionalSitesPath;
    private Date  addDate;
    private String  approvedBy;
    private Date  approvedDate;
    private String  postingType;
    private boolean  activeStatus;
    private String  requestStatus;
    private String comments;
    private String paymentId;
    private Vector publication;
    private String  landOwnerFax;
    private String  landOwnerEmail;

    public String getLandOwnerEmail() {
        return landOwnerEmail;
    }

    public void setLandOwnerEmail(String landOwnerEmail) {
        this.landOwnerEmail = landOwnerEmail;
    }

    public String getLandOwnerFax() {
        return landOwnerFax;
    }

    public void setLandOwnerFax(String landOwnerFax) {
        this.landOwnerFax = landOwnerFax;
    }
        
    private HLCActivityOrganizerVO objActivity;
    
    private HLCPublicationLocalHome objPubHome;
    private HLCPublicationLocal objPubRemote;
    private InitialContext ic;
    
    
    
    public HLCActivityOrganizerVO getActivityOrganizerDetails(){
        Debug.print("ActivityOrganizerBean getActivityOrganizerDetails");        
        return new HLCActivityOrganizerVO(activityMeetingId, activityName, activityDate,
	         noOfDays, useaAreaId, location, state, country, city, zip, activityOrganizerId,
	         activityTypeId,  otherActivityType,  activityFees,  instructorsCoaches,
	         facilities,  otherFacilities,  landOwnerName,  landOwnerBusinessName,
	         landOwnerAddress, landOwnerCity, landOwnerState, landOwnerCountry,
	         landOwnerZip, landOwnerPhone, additionalSites,additionalSitesPath, addDate, approvedBy, approvedDate,
	         postingType, activeStatus, requestStatus, comments, paymentId,landOwnerFax,landOwnerEmail);
    }
    
    
//getter

	public String getActivityMeetingId() {
		return activityMeetingId;
	}

	public String getActivityName() {
		return activityName;
	}

	public Date getActivityDate() {
		return activityDate;
	}

	public String getNoOfDays() {
		return noOfDays;
	}

	public String getUseaAreaId() {
		return useaAreaId;
	}

	public String getLocation() {
		return location;
	}

	public String getState() {
		return state;
	}
	public String getActivityOrganizerId() {
            return activityOrganizerId;
	}

	public String getActivityTypeId() {
            return activityTypeId;
	}

	public String getOtherActivityType() {
		return otherActivityType;
	}

	public String getActivityFees() {
		return activityFees;
	}
	public String getInstructorsCoaches() {
		return instructorsCoaches;
	}
	public String getFacilities() {
		return facilities;
	}
	public String getOtherFacilities() {
		return otherFacilities;
	}
	public String getLandOwnerName() {
		return landOwnerName;
	}
	public String getLandOwnerBusinessName() {
		return landOwnerBusinessName;
	}
	public String getLandOwnerAddress() {
		return landOwnerAddress;
	}
	public String getLandOwnerCity() {
		return landOwnerCity;
	}
	public String getLandOwnerState() {
		return landOwnerState;
	}
	public String getLandOwnerCountry() {
		return landOwnerCountry;
	}
	public String getLandOwnerZip() {
		return landOwnerZip;
	}
	public String getLandOwnerPhone() {
		return landOwnerPhone;
	}
        
        public boolean isAdditionalSites() {
            return additionalSites;
	}
        
	public String getAdditionalSitesPath() {
		return additionalSitesPath;
	}
        
	public Date getAddDate() {
		return addDate;
	}
	public String getApprovedBy() {
		return approvedBy;
	}
	public Date getApprovedDate() {
		return approvedDate;
	}
	public String getPostingType() {
		return postingType;
	}
	public boolean isActiveStatus() {
		return activeStatus;
	}
	public String getRequestStatus() {
		return requestStatus;
	}
        
        public Vector getPublicationDetails(){
            return publication;
        }
        public void setPublicationDetails(Vector publication){
            this.publication = publication;
        }
        
         public String getComments() {
            return comments;
	}
         

 //Setters methods

    public void setActivityMeetingId(String activityMeetingId) {
        this.activityMeetingId = activityMeetingId;
    }

    public void setActivityName(String activityName)  {
        this.activityName = activityName;
    }
    public void setActivityDate(Date activityDate)  {
        this.activityDate = activityDate;
    }

    public void setNoOfDays(String noOfDays) {
        this.noOfDays =  noOfDays;
    }
    public void setUseaAreaId(String useaAreaId)   {
        this.useaAreaId =  useaAreaId;
    }
    public void setLocation(String location) {
       this.location =  location;
    }
    public void setState(String state) {
       this.state = state;
    }
    public void setActivityOrganizerId(String activityOrganizerId) {
       this.activityOrganizerId = activityOrganizerId;
    }
    public void setActivityTypeId(String activityTypeId) {
    this.activityTypeId = activityTypeId;
    }


    public void setOtherActivityType(String otherActivityType)  {
 	this.otherActivityType = otherActivityType;
    }

    public void setActivityFees(String activityFees) {
 	this.activityFees =  activityFees;
    }
    public void setInstructorsCoaches(String instructorsCoaches)   {
        this.instructorsCoaches =  instructorsCoaches;
    }
    public void setFacilities(String facilities) {
	this.facilities =  facilities;
    }
    public void setOtherFacilities(String otherFacilities) {
	   this.otherFacilities = otherFacilities;
    }
    //public void set(String ) {
	//   this. = ;
   // }
    public void setLandOwnerName(String landOwnerName) {
        this.landOwnerName = landOwnerName;
    }

    public void setLandOwnerBusinessName(String landOwnerBusinessName)  {
            this.landOwnerBusinessName = landOwnerBusinessName;
    }

    public void setLandOwnerAddress(String landOwnerAddress) {
            this.landOwnerAddress =  landOwnerAddress;
    }
    public void setLandOwnerCity(String landOwnerCity)   {
	    this.landOwnerCity =  landOwnerCity;
    }
    public void setLandOwnerState(String landOwnerState) {
	   this.landOwnerState =  landOwnerState;
    }
    public void setLandOwnerCountry(String landOwnerCountry) {
	   this.landOwnerCountry = landOwnerCountry;
    }
    public void setLandOwnerZip(String landOwnerZip) {
	   this.landOwnerZip = landOwnerZip;
    }
    
    public void setAdditionalSites(boolean additionalSites) {
            this.additionalSites = additionalSites;
    }

    public void setAdditionalSitesPath(String additionalSitesPath) {
         this.additionalSitesPath = additionalSitesPath;
    }

    public void setLandOwnerPhone(String landOwnerPhone)  {
            this.landOwnerPhone = landOwnerPhone;
    }
    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }


    public void setApprovedBy(String ApprovedBy) {
            this.approvedBy =  approvedBy;
    }
    
    public void setApprovedDate(Date approvedDate)   {
	    this.approvedDate =  approvedDate;
    }
    public void setPostingType(String postingType) {
	   this.postingType =  postingType;
    }
    public void setActiveStatus(boolean activeStatus) {
	   this.activeStatus = activeStatus;
    }
    public void setRequestStatus(String requestStatus) {
	   this.requestStatus = requestStatus;
    }
    
    public void setComments(String comments) {
        this.comments = comments;
    }
    
    

    public String ejbCreate(HLCActivityOrganizerVO objActivityDet,Vector publication) throws CreateException{
        Debug.print("ActivityOrganizerBean ejbCreate"); 
        if(objActivityDet==null){
                throw new EJBException("ejbCreate: objActivityDet or objPayDet arg is null or empty");
        }
        //this.activityMeetingId = objActivityDet.getActivityMeetingId();
        this.activityName = objActivityDet.getActivityName();
        this.activityDate = objActivityDet.getActivityDate();
        this.noOfDays = objActivityDet.getNoOfDays();
        this.useaAreaId = objActivityDet.getUseaAreaId();
        this.location= objActivityDet.getLocation();
        this.state = objActivityDet.getState();
        this.country = objActivityDet.getCountry();
        this.city = objActivityDet.getCity();
        this.zip = objActivityDet.getZip();
        this.activityOrganizerId = objActivityDet.getActivityOrganizerId();
        this.activityTypeId = objActivityDet.getActivityTypeId();
        this.otherActivityType = objActivityDet.getOtherActivityType();
        this.activityFees = objActivityDet.getActivityFees();
        this.instructorsCoaches = objActivityDet.getInstructorsCoaches();
        this.facilities = objActivityDet.getFacilities();
        this.otherFacilities = objActivityDet.getOtherFacilities();
        this.landOwnerName = objActivityDet.getLandOwnerName();
        this.landOwnerBusinessName = objActivityDet.getLandOwnerBusinessName();
        this.landOwnerAddress = objActivityDet.getLandOwnerAddress();
        this.landOwnerCity = objActivityDet.getLandOwnerCity();
        this.landOwnerState = objActivityDet.getLandOwnerState();
        this.landOwnerCountry = objActivityDet.getLandOwnerCountry();
        this.landOwnerZip = objActivityDet.getLandOwnerZip();
        this.landOwnerPhone = objActivityDet.getLandOwnerPhone();
        this.landOwnerFax=objActivityDet.getLandOwnerFax();
        this.landOwnerEmail=objActivityDet.getLandOwnerEmail();
        this.additionalSites = objActivityDet.isAdditionalSites();
        this.additionalSitesPath =  objActivityDet.getAdditionalSitesPath();
        this.addDate = objActivityDet.getAddDate();
        this.approvedBy = objActivityDet.getApprovedBy();
        this.approvedDate = objActivityDet.getApprovedDate();
        this.postingType = objActivityDet.getPostingType();
        this.activeStatus = objActivityDet.isActiveStatus();
        this.requestStatus = objActivityDet.getRequestStatus();
        this.comments = objActivityDet.getComments();
        this.paymentId = objActivityDet.getPaymentId();
        this.publication = publication;
        
        try {
            insertRowActivityOrganizer();
        } catch (Exception ex) {
            throw new EJBException("ejbCreate: " + ex.getMessage());
        }
        return activityMeetingId;
    }
    
     public void ejbPostCreate(HLCActivityOrganizerVO objActivityDet,Vector publication) throws CreateException{
        Debug.print("ActivityOrganizerBean ejbPostCreate"); 
    }
    
    public String ejbFindByPrimaryKey(String primarykey) throws FinderException {
        Debug.print("ActivityOrganizerBean ejbFindByPrimaryKey");
        boolean result;
        try {
            result = selectByPrimaryKey(primarykey);
        } catch (Exception ex) {
            throw new EJBException("ejbFindByPrimaryKey: " + ex.getMessage());
        }
        if (result) {
            Debug.print("ActivityOrganizerBean ejbFindByPrimaryKey ID result::" + primarykey);
            return primarykey;
        } else {
            throw new ObjectNotFoundException("Row for id " + primarykey + " not found.");
        }
    }
     
    
    public void setEntityContext(EntityContext aContext) {
        Debug.print("ActivityOrganizerBean setEntityContext");
        context = aContext;
    }
    
    public void ejbActivate() {
      //  Debug.print("ActivityOrganizerBean ejbActivate:" + context.getPrimaryKey());
        //Long tempActMeeId = (Long)context.getPrimaryKey();
        //Debug.print("ActivityOrganizerBean Long Value:" + tempActMeeId);
       // activityMeetingId = tempActMeeId.longValue();
        //activityMeetingId = Long.parseLong((String)context.getPrimaryKey());
       // Debug.print("ActivityOrganizerBean ejbActivate:" + activityMeetingId);
    }
    
    public void ejbPassivate() {
        Debug.print("ActivityOrganizerBean ejbPassivate");
        //activityMeetingId = 0;
    }
        
   public void ejbRemove() {
        Debug.print("ActivityOrganizerBean ejbRemove");

        try {
            deleteRow(activityMeetingId);
        } catch (Exception ex) {
            throw new EJBException("ejbRemove: " + ex.getMessage());
        }
    }
    
    public void unsetEntityContext() {
        context = null;
    }
    
     public void ejbLoad() {
        Debug.print("ActivityOrganizerBean ejbLoad");

        try {
            loadActivityOrganizer();
        } catch (Exception ex) {
            throw new EJBException("ejbLoad: " + ex.getMessage());
        }
    }
    
    public void ejbStore() {
        Debug.print("ActivityOrganizerBean ejbStore");

        try {
            storeActivityOrganizer();
        } catch (Exception ex) {
            throw new EJBException("ejbStore: " + ex.getMessage());
        }
    }

        
    /*********************** Database Routines *************************/

    private void insertRowActivityOrganizer() throws SQLException {
        Debug.print("ActivityOrganizerBean insertRowActivityOrganizer");
        this.activityMeetingId = getNextId();
        Debug.print("ActivityOrganizerBean insertRowActivityOrganizer primary Key Get generated:" + activityMeetingId);
        makeConnection();
            try{
                String insertStatement = "insert into " + DBHelper.USEA_EVT_EDU_ACTIVITY_ORGANIZER + " (activity_meeting_id,activity_name,activity_date,no_of_days, " +
                        " usea_area_id, location, state, country, city, zip, activity_organizer_id, activity_type_id, other_activity_type,activity_fees , instructors_coaches, facilities, " +
                        " other_facilities, land_owner_name, land_owner_business_name, land_owner_address, land_owner_city, land_owner_state, " +
                        "land_owner_country, land_owner_zip, land_owner_phone, additional_sites,additional_sites_path,add_date, approved_by, approved_date, posting_type, active_status, request_status, comments, Payment_id,land_owner_fax,land_owner_email) " +
                        " values ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? ,? , ?, ?,  ? , ? , ? , ? , ? , ? , ? , ? , ?, ? , ? , ? , ? , ?, ? , ?, ?, ?, ?,?,?) ";

                PreparedStatement prepStmt = con.prepareStatement(insertStatement);
                prepStmt.setLong(1, Long.parseLong(activityMeetingId));
                prepStmt.setString(2, activityName);
                if(activityDate == null){
                     prepStmt.setDate(3, null);
                } else{
                    prepStmt.setDate(3, DBHelper.toSQLDate(activityDate));
                }
                if(noOfDays==null || noOfDays.equals("")){
                     prepStmt.setInt(4, 0);
                } else{
                    prepStmt.setInt(4, Integer.parseInt(noOfDays));
                }
                prepStmt.setString(5, useaAreaId);
                prepStmt.setString(6, location);
                prepStmt.setString(7, state);
                
                prepStmt.setString(8, country);
                prepStmt.setString(9, city);
                prepStmt.setString(10, zip);
                
                
                prepStmt.setString(11, activityOrganizerId);
                // DBHelper.toSQLDate(competitionDate)
                prepStmt.setString(12, activityTypeId);
                prepStmt.setString(13, otherActivityType);
                
                if(activityFees==null || activityFees.equals("")){
                    prepStmt.setFloat(14, 0);
                } else{
                    prepStmt.setFloat(14, Float.parseFloat(activityFees));
                }
                prepStmt.setString(15, instructorsCoaches);
                prepStmt.setString(16, facilities);
                prepStmt.setString(17, otherFacilities);
                prepStmt.setString(18, landOwnerName);
                prepStmt.setString(19, landOwnerBusinessName);
                prepStmt.setString(20, landOwnerAddress);
                prepStmt.setString(21, landOwnerCity);
                prepStmt.setString(22, landOwnerState);
                prepStmt.setString(23, landOwnerCountry);
                prepStmt.setString(24, landOwnerZip);
                prepStmt.setString(25, landOwnerPhone);
                prepStmt.setBoolean(26, additionalSites);
                prepStmt.setString(27, additionalSitesPath);
                
                if(addDate==null || addDate.equals("")){
                     prepStmt.setDate(28, null);
                } else{
                    prepStmt.setDate(28, DBHelper.toSQLDate(addDate));
                }
              
                prepStmt.setString(29, approvedBy);
                
                if(approvedDate == null){
                     prepStmt.setDate(30, null);
                } else{
                    prepStmt.setDate(30, DBHelper.toSQLDate(approvedDate));
                }
                prepStmt.setString(31, postingType);
                prepStmt.setBoolean(32, true);
                prepStmt.setString(33, "Pending");
                prepStmt.setString(34,comments);
                prepStmt.setString(35,paymentId);
                prepStmt.setString(36,landOwnerFax);
                prepStmt.setString(37,landOwnerEmail);

                prepStmt.executeUpdate();
                prepStmt.close();
                releaseConnection();
                getInitialContext();
                insertRowPublication();
            }
            catch(SQLException sql){
                releaseConnection();
                throw new EJBException("SQL Exception in insertRowActivityOrganizer:" + sql.getMessage());
            }
            catch(Exception e){
                releaseConnection();
                throw new EJBException("General Exception  in insertRowActivityOrganizer:" + e.getMessage());
            }
        }
        
    private void insertRowPublication() throws SQLException {
        Debug.print("ActivityOrganizerBean insertRowPublication");
        try{
            Enumeration pubEnum = publication.elements();
            if(pubEnum.hasMoreElements()){
                //String activityMeetingId = (String)pubEnum.nextElement();
                String publicationEmail = (String)pubEnum.nextElement();
                String mailingFormat = (String)pubEnum.nextElement();
                String mailingBy = (String)pubEnum.nextElement();
                String mailingSortBy = (String)pubEnum.nextElement();
                String noOfCopies = (String)pubEnum.nextElement();
                String mailListValues = (String)pubEnum.nextElement();
                objPubRemote = objPubHome.create(activityMeetingId , publicationEmail,  mailingFormat, 
                                mailingBy,  mailingSortBy,  noOfCopies, mailListValues);
            }
        }
        catch(Exception e){
            throw new EJBException("General Exception  in insertRowPublication:" + e.getMessage());
        }       
    }
        
    private boolean selectByPrimaryKey(String primarykey) throws SQLException {
        Debug.print("ActivityOrganizerBean selectByPrimaryKey");

        makeConnection();

        String selectStatement = "SELECT activity_meeting_id from " + DBHelper.USEA_EVT_EDU_ACTIVITY_ORGANIZER + " WHERE activity_meeting_id = ?";
        PreparedStatement prepStmt = con.prepareStatement(selectStatement);
        prepStmt.setLong(1, Long.parseLong(primarykey));

        ResultSet rs = prepStmt.executeQuery();
        boolean result = rs.next();
        prepStmt.close();
        releaseConnection();
        return result;
    }

   
    public Collection ejbFindByAll() throws FinderException {
        Debug.print("ActivityOrganizerBean ejbFindAll");
        Vector actOrgList = new Vector();
        makeConnection();
   	try {
            String selectStatement = "select activity_meeting_id from " + DBHelper.USEA_EVT_EDU_ACTIVITY_ORGANIZER ;
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                actOrgList.addElement(rs.getString(1));
                Debug.print("ActivityOrganizerBean In find all:" + rs.getString(1));
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
        } 
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in ejbFindByAll:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in ejbFindByAll:" + e.getMessage());
        }
        return actOrgList;
}

    public Collection ejbFindByActivityOrganizerRequestStatus(String requestStatus) throws FinderException {
    Debug.print("ActivityOrganizerBean ejbFindByActivityOrganizerRequestStatus:" + requestStatus);
    Vector actOrgList = new Vector();
    makeConnection();
    try {
        String selectStatement = "select activity_meeting_id from " + DBHelper.USEA_EVT_EDU_ACTIVITY_ORGANIZER + " where request_status = ? "  ;
        PreparedStatement prepStmt = con.prepareStatement(selectStatement);
        Debug.print("Query:" + selectStatement);
        prepStmt.setString(1,requestStatus);
        ResultSet rs = prepStmt.executeQuery();
        while (rs.next()){
            actOrgList.addElement(rs.getString(1));
            Debug.print("ActivityOrganizerBean In ejbFindByActivityOrganizerRequestStatus:" + rs.getString(1));
        }
        rs.close();
        prepStmt.close();
        releaseConnection();
    }
    
    catch(SQLException sql){
        releaseConnection();
        throw new EJBException("SQL Exception in ejbFindByActivityOrganizerRequestStatus:" + sql.getMessage());
    }
    
    catch(Exception e){
        releaseConnection();
        throw new EJBException("General Exception  in ejbFindByActivityOrganizerRequestStatus:" + e.getMessage());
    }
    return actOrgList;
    }
    
    public Collection ejbFindByActivityOrganizerRequestStatusByOrganizer(String organizerId, String requestStatus) throws FinderException {
    Debug.print("ActivityOrganizerBean ejbFindByActivityOrganizerRequestStatusByOrganizer:" + requestStatus);
    Vector actOrgList = new Vector();
    makeConnection();
    try {
        String selectStatement = "select activity_meeting_id from " + DBHelper.USEA_EVT_EDU_ACTIVITY_ORGANIZER + " where request_status = ? and activity_organizer_id = ? "  ;
        PreparedStatement prepStmt = con.prepareStatement(selectStatement);
        Debug.print("Query:" + selectStatement);
        prepStmt.setString(1,requestStatus);
        prepStmt.setString(2,organizerId);
        ResultSet rs = prepStmt.executeQuery();
        while (rs.next()){
            actOrgList.addElement(rs.getString(1));
            Debug.print("ActivityOrganizerBean In ejbFindByActivityOrganizerRequestStatusByOrganizer:" + rs.getString(1));
        }
        rs.close();
        prepStmt.close();
        releaseConnection();
    }
    
    catch(SQLException sql){
        releaseConnection();
        throw new EJBException("SQL Exception in ejbFindByActivityOrganizerRequestStatusByOrganizer:" + sql.getMessage());
    }
    
    catch(Exception e){
        releaseConnection();
        throw new EJBException("General Exception  in ejbFindByActivityOrganizerRequestStatusByOrganizer:" + e.getMessage());
    }
    return actOrgList;
    }

    public Collection ejbFindByActivityName(String activityName) throws FinderException {
    Debug.print("ActivityOrganizerBean ejbFindByActivityName");
    Vector actOrgList = new Vector();
    makeConnection();
    try {
        String selectStatement = "select activity_meeting_id from " + DBHelper.USEA_EVT_EDU_ACTIVITY_ORGANIZER + " where activity_name = ? "  ;
        PreparedStatement prepStmt = con.prepareStatement(selectStatement);
        prepStmt.setString(1,activityName);
        ResultSet rs = prepStmt.executeQuery();
        while (rs.next()){
            actOrgList.addElement(rs.getString(1));
            Debug.print("ActivityOrganizerBean In ejbFindByActivityName:" + rs.getString(1));
        }
        rs.close();
        prepStmt.close();
        releaseConnection();
    } 
    catch(SQLException sql){
        releaseConnection();
        throw new EJBException("SQL Exception in ejbFindByActivityName:" + sql.getMessage());
    }
    catch(Exception e){
        releaseConnection();
        throw new EJBException("General Exception  in ejbFindByActivityName:" + e.getMessage());
    }
    return actOrgList;
    }

    public Collection ejbFindByActivityDate(Date activityDate) throws FinderException {
    Debug.print("ActivityOrganizerBean ejbFindByActivityName");
    Vector actOrgList = new Vector();
    makeConnection();
    try {
        String selectStatement = "select activity_meeting_id from " + DBHelper.USEA_EVT_EDU_ACTIVITY_ORGANIZER + " where activity_date = ? "  ;
        PreparedStatement prepStmt = con.prepareStatement(selectStatement);

        if(activityDate==null){
            prepStmt.setDate(1,null);
        }
        else{
            prepStmt.setDate(1,DBHelper.toSQLDate(activityDate));
        }

        ResultSet rs = prepStmt.executeQuery();
        while (rs.next()){
            actOrgList.addElement(rs.getString(1));
            Debug.print("ActivityOrganizerBean In ejbFindByActivityName:" + rs.getString(1));
        }
        rs.close();
        prepStmt.close();
        releaseConnection();
    } 
    catch(SQLException sql){
        releaseConnection();
        throw new EJBException("SQL Exception in ejbFindByActivityName:" + sql.getMessage());
    }
    catch(Exception e){
        releaseConnection();
        throw new EJBException("General Exception  in ejbFindByActivityName:" + e.getMessage());
    }
    return actOrgList;
    }

    public Collection ejbFindByActivityOrganizerId(String activityOrganizerId) throws FinderException {
    Debug.print("ActivityOrganizerBean ejbFindByActivityOrganizerId");
    Vector actOrgList = new Vector();
    makeConnection();
    try {
        String selectStatement = "select activity_meeting_id from " + DBHelper.USEA_EVT_EDU_ACTIVITY_ORGANIZER + " where activity_organizer_id = ? "  ;
        PreparedStatement prepStmt = con.prepareStatement(selectStatement);
        prepStmt.setString(1,activityOrganizerId);

        ResultSet rs = prepStmt.executeQuery();
        while (rs.next()){
            actOrgList.addElement(rs.getString(1));
            Debug.print("ActivityOrganizerBean In ejbFindByActivityOrganizerId:" + rs.getString(1));
        }
        rs.close();
        prepStmt.close();
        releaseConnection();
    } 
    catch(SQLException sql){
        releaseConnection();
        throw new EJBException("SQL Exception in ejbFindByActivityOrganizerId:" + sql.getMessage());
    }
    catch(Exception e){
        releaseConnection();
        throw new EJBException("General Exception  in ejbFindByActivityOrganizerId:" + e.getMessage());
    }
    return actOrgList;
    }

     private String getNextId() throws SQLException  {
        Debug.print("ActivityOrganizerBean getNextId");
        makeConnection();
       
        String selectStatement = "SELECT max(activity_meeting_id) from " + DBHelper.USEA_EVT_EDU_ACTIVITY_ORGANIZER;

        PreparedStatement prepSelect = con.prepareStatement(selectStatement);

        ResultSet rs = prepSelect.executeQuery();
        rs.next();
        long nextId = rs.getLong(1);
        
        if(nextId==0){
            nextId = 10000;
        }
        else{
            nextId = nextId+1;
        }
        rs.close();
        prepSelect.close();
        releaseConnection();
        return String.valueOf(nextId);
    }

    private void loadActivityOrganizer() throws SQLException {
       Debug.print("ActivityOrganizerBean loadActivityOrganizer");
      // Long tempActivityMeetingId = (Long)context.getPrimaryKey();
       activityMeetingId = (String)context.getPrimaryKey();
        //activityMeetingId = Long.parseLong((String)context.getPrimaryKey());
        Debug.print("ActivityOrganizerBean loadActivityOrganizer:" + activityMeetingId);

        makeConnection();
        try{    
            String selectStatement =
                    "select activity_name,activity_date,no_of_days, " +
                    " usea_area_id, location, state, activity_organizer_id, activity_type_id, other_activity_type,activity_fees , instructors_coaches, facilities, " +
                    " other_facilities, land_owner_name, land_owner_business_name, land_owner_address, land_owner_city, land_owner_state, " +
                    "land_owner_country, land_owner_zip, land_owner_phone, additional_sites,additional_sites_path, add_date, approved_by, " +
                    " approved_date, posting_type, active_status, request_status , comments, payment_id, country, city, zip " +
                    " from " + DBHelper.USEA_EVT_EDU_ACTIVITY_ORGANIZER + " where activity_meeting_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);

            prepStmt.setLong(1, Long.parseLong(activityMeetingId));
            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()) {
                this.activityName = rs.getString(1);
                this.activityDate = rs.getDate(2);
                this.noOfDays = rs.getString(3);
                this.useaAreaId = rs.getString(4);
                this.location= rs.getString(5);
                this.state = rs.getString(6);
                this.activityOrganizerId = rs.getString(7);
                this.activityTypeId = rs.getString(8);
                this.otherActivityType = rs.getString(9);
                this.activityFees = rs.getString(10);
                this.instructorsCoaches = rs.getString(11);
                this.facilities = rs.getString(12);
                this.otherFacilities = rs.getString(13);
                this.landOwnerName = rs.getString(14);
                this.landOwnerBusinessName = rs.getString(15);
                this.landOwnerAddress = rs.getString(16);
                this.landOwnerCity = rs.getString(17);
                this.landOwnerState = rs.getString(18);
                this.landOwnerCountry = rs.getString(19);
                this.landOwnerZip = rs.getString(20);
                this.landOwnerPhone= rs.getString(21);
                this.additionalSites = rs.getBoolean(22);
                this.additionalSitesPath =  rs.getString(23);
                this.addDate = rs.getDate(24);
                this.approvedBy = rs.getString(25);
                this.approvedDate = rs.getDate(26);
                this.postingType = rs.getString(27);
                this.activeStatus =  rs.getBoolean(28);
                this.requestStatus = rs.getString(29);
                this.comments = rs.getString(30);
                this.paymentId = rs.getString(31);
                this.country = rs.getString(32);
                this.city = rs.getString(33);
                this.zip = rs.getString(34);
            }
            prepStmt.close();
            releaseConnection();
            getInitialContext();
            loadPublicationDetails();
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in loadActivityOrganizer:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in loadActivityOrganizer:" + e.getMessage());
        }            
    }

    public void loadPublicationDetails(){
    Debug.print("ActivityOrganizerBean loadPublicationDetails");
    Vector v = new Vector();
    try{
        getInitialContext();
        Collection result = objPubHome.findByActivityMeetingId(String.valueOf(activityMeetingId));
        if(result!=null && result.size()!=0){
            Iterator e = result.iterator();
            if(e.hasNext()){
                HLCPublicationLocal localPubRemote = (HLCPublicationLocal)e.next();
                Debug.print("ActivityOrganizerBean loadPublicationDetails localPubRemote" + localPubRemote);
                 Debug.print("ActivityOrganizerBean publication1 Email" + localPubRemote.getPublicationEmail());
                 if(publication!=null){
                    Debug.print("ActivityOrganizerBean publication" + publication);
                    Enumeration enumPub = publication.elements();
                    if(enumPub.hasMoreElements()){
                        String publicationEmail = (String) enumPub.nextElement();
                        String mailingFormat = (String) enumPub.nextElement();
                        String mailingBy = (String) enumPub.nextElement();
                        String mailingSortBy = (String) enumPub.nextElement();
                        String noOfCopies = (String) enumPub.nextElement();
                        String mailListValues = (String) enumPub.nextElement();
                        localPubRemote.setPublicationEmail(publicationEmail);
                        localPubRemote.setMailingFormat(mailingFormat);
                        localPubRemote.setMailingBy(mailingBy);
                        localPubRemote.setMailingSortBy(mailingSortBy);
                        localPubRemote.setNoOfCopies(noOfCopies);
                        localPubRemote.setMailListValues(mailListValues);
                        
                    }
               }
               else{
                    Debug.print("ActivityOrganizerBean publication null value :" + publication);
                    Debug.print("ActivityOrganizerBean publication1 Email" + localPubRemote.getPublicationEmail());
                    Debug.print("ActivityOrganizerBean publication1 getMailingFormat" + localPubRemote.getMailingFormat());
                    Debug.print("ActivityOrganizerBean publication1 getMailingBy" + localPubRemote.getMailingBy());
                    Debug.print("ActivityOrganizerBean publication1 getMailingSortBy" + localPubRemote.getMailingSortBy());
                    Debug.print("ActivityOrganizerBean publication1 getNoOfCopies" + localPubRemote.getNoOfCopies());
                    Debug.print("ActivityOrganizerBean publication1 getMailListValues" + localPubRemote.getMailListValues());
                    Vector vs = new Vector();
                    vs.add(localPubRemote.getPublicationEmail());
                    vs.add(localPubRemote.getMailingFormat());
                    vs.add(localPubRemote.getMailingBy());
                    vs.add(localPubRemote.getMailingSortBy());
                    vs.add(localPubRemote.getNoOfCopies());
                    vs.add(localPubRemote.getMailListValues());
                    this.publication = vs;
                    Debug.print("ActivityOrganizerBean publication null value :" + publication);
               }
            }
        }
    }
    catch(Exception e){
        Debug.print("Exception while invoking loadPublicationDetails()" + e.getMessage());
    }
    }     

    public void storeActivityOrganizer() throws SQLException {
        Debug.print("ActivityOrganizerBean storeActivityOrganizer");
        makeConnection();
        try{
            Debug.print("ActivityOrganizerBean inside storeActivityOrganizer:" + activityMeetingId);

            String updateStatement =
                    "update " + DBHelper.USEA_EVT_EDU_ACTIVITY_ORGANIZER + " set activity_name = ?, activity_date = ?, no_of_days = ?, " +
                            " usea_area_id = ?, location = ?, state = ?, activity_organizer_id = ?, activity_type_id = ?, " +
                            " other_activity_type = ? , activity_fees = ? , instructors_coaches = ?, facilities = ?, " +
                            " other_facilities = ? , land_owner_name = ? , land_owner_business_name = ?, land_owner_address= ?, " +
                            " land_owner_city = ?, land_owner_state = ?, land_owner_country = ? , land_owner_zip = ?, land_owner_phone = ?, " +
                            " additional_sites = ?,additional_sites_path = ?,add_date = ?, approved_by = ?, approved_date = ?, posting_type = ?, " +
                            "active_status = ?, request_status = ?, comments = ?, payment_id = ?, country=?, city = ?, zip = ? " +
                        "  where activity_meeting_id = ? ";

            PreparedStatement prepStmt = con.prepareStatement(updateStatement);


                prepStmt.setString(1, activityName);

                if(activityDate == null){
                     prepStmt.setDate(2, null);
                } else{
                    prepStmt.setDate(2, DBHelper.toSQLDate(activityDate));
                }

                if(noOfDays==null || noOfDays.equals("")){
                     prepStmt.setInt(3, 0);
                } else{
                    prepStmt.setInt(3, Integer.parseInt(noOfDays));
                }

                prepStmt.setString(4, useaAreaId);
                prepStmt.setString(5, location);
                prepStmt.setString(6, state);
                prepStmt.setString(7, activityOrganizerId);
                // DBHelper.toSQLDate(competitionDate)
                prepStmt.setString(8, activityTypeId);
                prepStmt.setString(9, otherActivityType);

                if(activityFees==null || activityFees.equals("")){
                    prepStmt.setFloat(10, 0);
                } else{
                    prepStmt.setFloat(10, Float.parseFloat(activityFees));
                }

                prepStmt.setString(11, instructorsCoaches);
                prepStmt.setString(12, facilities);
                prepStmt.setString(13, otherFacilities);
                prepStmt.setString(14, landOwnerName);
                prepStmt.setString(15, landOwnerBusinessName);
                prepStmt.setString(16, landOwnerAddress);
                prepStmt.setString(17, landOwnerCity);
                prepStmt.setString(18, landOwnerState);
                prepStmt.setString(19, landOwnerCountry);
                prepStmt.setString(20, landOwnerZip);
                prepStmt.setString(21, landOwnerPhone);
                prepStmt.setBoolean(22, additionalSites);
                prepStmt.setString(23, additionalSitesPath);
                prepStmt.setDate(24, DBHelper.toSQLDate(new Date()));
                prepStmt.setString(25, approvedBy);

                if(approvedDate==null){
                     prepStmt.setDate(26, null);
                } else{
                    prepStmt.setDate(26, DBHelper.toSQLDate(approvedDate));
                }

                prepStmt.setString(27, postingType);
                prepStmt.setBoolean(28, activeStatus);
                prepStmt.setString(29, requestStatus);
                prepStmt.setString(30, comments);
                prepStmt.setString(31, paymentId);
                
                prepStmt.setString(32, country);
                prepStmt.setString(33, city);
                prepStmt.setString(34, zip);
                
                prepStmt.setLong(35, Long.parseLong(activityMeetingId));


                int rowCount = prepStmt.executeUpdate();
                prepStmt.close();
                releaseConnection();

            if (rowCount == 0) {
                throw new EJBException("Storing row for id " + activityMeetingId +  " failed.");
            }
            //storePublicationDetails();
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in storeActivityOrganizer:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in storeActivityOrganizer:" + e.getMessage());
        }            

    }
    
    public void storePublicationDetails(){
        Debug.print("ActivityOrganizerBean storePublicationDetails");
        try{
            getInitialContext();
           // String activityMeetingId = "";
            String publicationEmail = "";
            String mailingFormat = "";
            String mailingBy = "";
            String mailingSortBy = "";
            String noOfCopies = "";
            String mailListValues = "";
            if(publication!=null && publication.size()!=0){
               Enumeration pubEnum = publication.elements();
               if(pubEnum.hasMoreElements()){
                    publicationEmail = (String)pubEnum.nextElement();
                    mailingFormat = (String)pubEnum.nextElement();
                    mailingBy = (String)pubEnum.nextElement();
                    mailingSortBy = (String)pubEnum.nextElement();
                    noOfCopies = (String)pubEnum.nextElement();
                    mailListValues = (String)pubEnum.nextElement();
               }
               ArrayList  iName = (ArrayList) objPubHome.findByActivityMeetingId(String.valueOf(activityMeetingId));
               Iterator e = iName.iterator();
               if(e.hasNext()){
                   HLCPublicationLocal localPubRemote = (HLCPublicationLocal)e.next();
                    localPubRemote.setPublicationEmail(publicationEmail);
                    localPubRemote.setMailingFormat(mailingFormat);
                    localPubRemote.setMailingBy(mailingBy);
                    localPubRemote.setMailingSortBy(mailingSortBy);
                    localPubRemote.setNoOfCopies(noOfCopies);
                    localPubRemote.setMailListValues(mailListValues);
                    Debug.print("ActivityOrganizerBean storePublicationDetails updating publication" + noOfCopies);
               }
             }
        }
        catch(Exception exp){
            Debug.print("Exception in ActivityOrganizerBean storePublicationDetails" + exp.getMessage());
            exp.printStackTrace();
        }
    }
    
    
    private void deleteRow(String activityMeetingId) throws SQLException {
        Debug.print("ActivityOrganizerBean deleteRow");
        boolean result =  deleteRowPublication(activityMeetingId);
        Debug.print("Publication  deleteRow:" + result);
        if(result==true){
            makeConnection();
            try{
                String deleteStatement = "delete from " + DBHelper.USEA_EVT_EDU_ACTIVITY_ORGANIZER  + "  where activity_meeting_id = ? ";
                PreparedStatement prepStmt = con.prepareStatement(deleteStatement);

                prepStmt.setLong(1, Long.parseLong(activityMeetingId));
                prepStmt.executeUpdate();
                prepStmt.close();
                releaseConnection();
            }
            catch (SQLException e) {
                releaseConnection();
                throw new EJBException("Could not find any from activityMeetingId");
            }
            catch(Exception e){
                releaseConnection();
                throw new EJBException("General Exception  in deleteRow:" + e.getMessage());
            }
        }
    }
    
      public boolean deleteRowPublication(String activityMeetingId){
        Debug.print("ActivityOrganizerBean deleteRowPublication");
        boolean result = false;
        try{
            getInitialContext();
           ArrayList  iName = (ArrayList) objPubHome.findByActivityMeetingId(activityMeetingId);
           Iterator e = iName.iterator();
           if(e.hasNext()){
               HLCPublicationLocal localPubRemote = (HLCPublicationLocal)e.next();
                localPubRemote.remove();
                result = true;
           }
        }
        catch(Exception exp){
             result = true;
            exp.printStackTrace();
        }
        return result;
    }
      
  
    private void makeConnection() {
        Debug.print("ActivityOrganizerBean : makeConnection");
        try {
            InitialContext ic = new InitialContext();
            DataSource ds = (DataSource) ic.lookup(HLCEJBAllJNDIs.USEA_DB);
            con = ds.getConnection();
        } catch (Exception ex) {
            throw new EJBException("Unable to connect to database. " + ex.getMessage());
        }
    }
     // makeConnection
    private void releaseConnection() {
        Debug.print("ActivityOrganizerBean releaseConnection");
        try {
            if(!con.isClosed()){
                con.close();
            }
        } catch (SQLException ex) {
            throw new EJBException("releaseConnection: " + ex.getMessage());
        }
    }
     // releaseConnection


    public InitialContext getInitialContext() throws javax.naming.NamingException {
        if( this.ic == null ) {
            ic = new InitialContext();
            Object objPub = ic.lookup("HLCPublicationLocalHome");
            objPubHome = (HLCPublicationLocalHome)objPub;  
        }
        System.out.println("ActivityOrganizerBean Bean getInitialContext()");
        return ic;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getZip() {
        return zip;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

}
