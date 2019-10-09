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
package com.hlcICP.user.reg;

import com.hlcmeeting.util.DBHelper;
import com.hlcmeeting.util.Debug;
import com.hlcmeeting.util.HLCMeeICPUserDetails;
import javax.ejb.*;
import java.util.Date;
import java.util.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;

/**
 * This is the bean class for the MullaiyarDamICPUserBean enterprise bean.
 * Created Sep 19, 2006 12:22:27 AM
 * @author harmohan
 */
public class HLCMullaiyarDamICPUserBean implements EntityBean, HLCMullaiyarDamICPUserLocalBusiness {
    private EntityContext context;
    private static final String dbName = "java:/ELMTMSSQLDS";
    private Connection con = null;
    private PreparedStatement prepStmt = null;
    private ResultSet rs = null;
    private ResultSet rs1 = null;
    
    /*================ICPUSERDETAILS Variable Decleration========================*/
    private String icpMeetingId;
    private String icpMeetingName;
    private String userId;
    private String membershipStatus;
    private String memberId;
    private String requestStatus;
    private String emailId;
    private String releaseId;
    private String comments;
    
    /*=============================Setter Method===========================*/
    public void setIcpMeetingId(String icpMeetingId) {this.icpMeetingId = icpMeetingId; }
    public void setIcpMeetingName(String icpMeetingName) {this.icpMeetingName = icpMeetingName; }
    public void setUserId(String userId) {this.userId = userId; }
    public void setMembershipStatus(String membershipStatus) {this.membershipStatus = membershipStatus; }
    public void setMemberId(String memberId) {this.memberId = memberId; }
    public void setRequestStatus(String requestStatus) {this.requestStatus = requestStatus; }
    public void setEmailId(String emailId) {this.emailId = emailId; }
    public void setReleaseId(String releaseId) {this.releaseId = releaseId; }
    public void setComments(String comments){this.comments = comments;}
    
    /*=========================Object Creation=================================*/
    public HLCMeeICPUserDetails getMeeICPUserDetails() {
        Debug.print("MeeICPUserDetails getMeeICPUserDetails");
        return new HLCMeeICPUserDetails(comments, releaseId,emailId,icpMeetingId,icpMeetingName,userId,membershipStatus,memberId,requestStatus);
    }
    
    HLCMeeICPUserDetails objMICPUser = new HLCMeeICPUserDetails();
    
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
          Debug.print("MullaiyarDamICPUserBean ejbLoad");
        try {
            loadICPUserDetails();
            Debug.print("Going to loadICPUserDetails");
        } catch (Exception ex) {
           throw new EJBException("loadICPUserDetails ejbLoad: " + ex.getMessage());
           // ex.printStackTrace();
        }
    }
    
    /**
     * @see javax.ejb.EntityBean#ejbStore()
     */
    public void ejbStore() {
         Debug.print("MullaiyarDamICPUserBean ejbStore");

        try {
            storeICPUserRequestStatus();
            Debug.print("Going to storeICPRequestStatus");
        } catch (Exception ex) {
            throw new EJBException("ejbStore: " + ex.getMessage());
        }
    }
    
    // </editor-fold>
    
    public String ejbCreate(HLCMeeICPUserDetails objMICPUser) throws CreateException {
         
        Debug.print("MullaiyarDamICPUserEntityBean ejbCreate"); 
        if(objMICPUser==null){
                throw new EJBException("ejbCreate: objMICPUser argument is null or empty");
        }
         this.icpMeetingId = objMICPUser.getIcpMeetingId();
         this.icpMeetingName = objMICPUser.getIcpMeetingName();
         this.userId = objMICPUser.getUserId();
         this.membershipStatus = objMICPUser.getMembershipStatus();
         this.memberId = objMICPUser.getMemberId();
         this.requestStatus = objMICPUser.getRequestStatus();
         this.emailId = objMICPUser.getEmailId();
        
        try {
              insertRowICPUserDetails();
              System.out.println("after insertRowICPUserDetails()");
        } catch (Exception ex) {
            throw new EJBException("ejbCreate: " + ex.getMessage());
        }
        return null;
    }
    
    public void ejbPostCreate(HLCMeeICPUserDetails objMICPUser) {
        Debug.print("MullaiyarDamICPUserEntityBean ejbPostCreate"); 
    }
    
    public String ejbFindByPrimaryKey(String releaseId) throws FinderException {
        Debug.print("MullaiyarDamICPUserEntityBean ejbFindByPrimaryKey");
        
        try {
            makeConnection();
            String selectStatement = "SELECT release_id FROM " + DBHelper.USEA_ICP_USER_DETAIL + " WHERE release_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, releaseId);
            ResultSet rs = prepStmt.executeQuery();
            if( rs.next()) {
                this.releaseId = rs.getString(1);
            }
            prepStmt.close();
         }catch (Exception e){
             e.printStackTrace();
         }finally {
             releaseConnection();
         }
         return releaseId;
        
       /* boolean result;
        try {
            result = selectByPrimaryKey(releaseId);
        } catch (Exception ex) {
            throw new EJBException("ejbFindByPrimaryKey: " + ex.getMessage());
        }
        if (result) {
             Debug.print("MullaiyarDamICPUserEntityBean ejbFindByPrimaryKey ID:" + releaseId);
            return releaseId;
        } else {
            throw new ObjectNotFoundException("Row for releaseId : " + releaseId + " not found.");
        }*/
    }
    
    /* private boolean selectByPrimaryKey(String releaseId) throws SQLException {
            Debug.print("MullaiyarDamICPUserEntityBean selectByPrimaryKey releaseId:  "+releaseId);
            boolean result = false;
            try {
                makeConnection();
                String selectStatement = "SELECT release_id FROM " + DBHelper.USEA_ICP_USER_DETAIL + " WHERE release_id = ? ";
                PreparedStatement prepStmt = con.prepareStatement(selectStatement);
                prepStmt.setString(1, releaseId);
                ResultSet rs = prepStmt.executeQuery();
                System.out.println("selectByPrimaryKey : Working releaseId : "+releaseId);
                result = rs.next();
                prepStmt.close();
                releaseConnection();
            }catch(Exception e){
                releaseConnection();
                e.printStackTrace();
                Debug.print("Error in selecting primary key : "+e.getMessage());
            }finally {
                releaseConnection();
            }
            
            return result;
      }*/
    
     private void insertRowICPUserDetails() throws SQLException {
            Debug.print("MullaiyarDamICPUserEntityBean insertRowICPUserDetails");
            //java.sql.Date dt = java.sql.Date.valueOf(dob);
           try {
                makeConnection();
                String insertStatement = 
                "insert into " + DBHelper.USEA_ICP_USER_DETAIL + " (icp_meeting_id, user_id, membership_status,member_id) "+
                " values ( ? , ? , ? , ?) ";
                prepStmt = con.prepareStatement(insertStatement);
                Debug.print("Inside the Instructor Details ....\n\n ");
                prepStmt.setString(1, icpMeetingId);
                Debug.print("icpMeetingId :  "+icpMeetingId);
                prepStmt.setString(2, userId);
                Debug.print("userId :  "+userId);
                if ("YES".equalsIgnoreCase(membershipStatus)){
                    prepStmt.setBoolean(3, true); 
                }else {
                    prepStmt.setBoolean(3, false); 
                }
                Debug.print("membershipStatus :  "+membershipStatus);
                prepStmt.setString(4, memberId);
                Debug.print("memberId :  "+memberId);

                int cnt = prepStmt.executeUpdate();
                prepStmt.close();
                releaseConnection();
                System.out.println("Succefully inserted :  "+cnt);
           }catch (Exception e){
               Debug.print("Error While inserting insertRowICPUserDetails : "+e.getMessage());
           }finally {
                releaseConnection();
           }
       
     }
/**
  * Name         :loadAnnualDetails
  * Description  :This method will Display the content from the databse 
  * @ param      :none
  * @return      :void
  * @throws      :SQLException
  */  
    private void loadICPUserDetails() throws SQLException {
        Debug.print("MullaiyarDamICPUserBean loadICPUserDetails");
        
        this.releaseId = (String) context.getPrimaryKey();
        Debug.print(" ReleaseId : "+releaseId);
        try {
                makeConnection();
                String selectStatement = 
                "SELECT icp_meeting_id,user_id,membership_status,member_id, request_status,comments FROM " + DBHelper.USEA_ICP_USER_DETAIL+" WHERE release_id = ?";
                PreparedStatement prepStmt = con.prepareStatement(selectStatement);
                prepStmt.setString(1, releaseId);
                ResultSet rs = prepStmt.executeQuery();
                if (rs.next()) {
                    this.icpMeetingId = rs.getString(1);
                    Debug.print(" In loadICPUserDetails icpMeetingId: "+icpMeetingId);
                    this.userId = rs.getString(2);
                    Debug.print(" In loadICPUserDetails userId: "+userId);
                    this.membershipStatus = rs.getString(3);
                    Debug.print(" In loadICPUserDetails membershipStatus: "+membershipStatus);
                    this.memberId = rs.getString(4);
                    Debug.print(" In loadICPUserDetails memberId: "+memberId);
                    this.requestStatus = rs.getString(5);
                    Debug.print(" In loadICPUserDetails requestStatus: "+requestStatus);
                    this.comments = rs.getString(6);
                    Debug.print(" In loadICPUserDetails comments: "+comments);

                prepStmt.close();
                releaseConnection();
            } else {
                prepStmt.close();
                releaseConnection();
                throw new NoSuchEntityException("Row for id " + icpMeetingId + " not found in database.");
            }
        }catch (Exception e){ 
            releaseConnection();
            Debug.print("Error In loadICPUserDetails : "+e.getMessage());
        }finally {
            releaseConnection();
        }
        
    }      
    
/**
  * Name         :storeICPUserRequestStatus
  * Description  :This method will UPDATE a ROW into the Database 
  * @ param      :horseMemberId
  * @return      :void
  * @throws      :SQLException
  */          
    
    private void storeICPUserRequestStatus() throws SQLException {
        Debug.print("MullaiyarDamICPUserBean storeICPUserRequestStatus");
        //java.sql.Date jsqlD =  new java.sql.Date( new Date().getTime() );
       // horseId = (String) context.getPrimhorseIdaryKey();
      //area_id,badge_info,total_amount
        try {
        makeConnection();
        String updateStatement = "update " + DBHelper.USEA_ICP_USER_DETAIL  + " set  request_status = ?,membership_status = ?, "+
                " comments = ? where release_id = ?";
        PreparedStatement prepStmt = con.prepareStatement(updateStatement);
        prepStmt.setString(1, requestStatus);
        if ("Yes".equalsIgnoreCase(membershipStatus)){
            prepStmt.setBoolean(2, true);
        }else {
             prepStmt.setBoolean(2, false);
        }
        prepStmt.setString(3,comments);
        prepStmt.setString(4, releaseId);
        int rowCount = prepStmt.executeUpdate();
        Debug.print("Sucessfully Updated." + rowCount);
        prepStmt.close();
        releaseConnection();
        }catch (Exception e){
            releaseConnection();
            Debug.print("Error While Request Update : "+e.getMessage());
        }finally {
            releaseConnection();
        }
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
            con.close();
        } catch (SQLException ex) {
            throw new EJBException("releaseConnection: " + ex.getMessage());
        }
    }        
}
