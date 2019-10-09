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
package com.hlcmember.type.master;

import com.hlcmember.type.util.DBHelper;
import com.hlcmember.type.util.Debug;
import com.hlcmember.type.util.HLCMembershipTypeMaster;
import javax.ejb.*;
import java.util.Date;
import java.util.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;

/**
 * This is the bean class for the ArabianSeaMembershipTypeMasterBean enterprise bean.
 * Created Aug 29, 2006 11:23:35 PM
 * @author harmohan
 */
public class HLCArabianSeaMembershipTypeMasterBean implements EntityBean, HLCArabianSeaMembershipTypeMasterLocalBusiness {
    private EntityContext context;
    private static final String dbName = "java:/ELMTMSSQLDS";
    private Connection con = null;
    private PreparedStatement prepStmt = null;
    private ResultSet rs = null;

     /*==============MembershipTypeMaster Variable ==========================*/
    private String membershipTypeId;
    private String membershipTypeName;
    private String userTypeId;
    private String membershipAmount;
    private String userTypeName;
    private Date modifyDate;
    private String periodValue;
    private String transaction_type_id;
    private int membership_year;
    private int active_status;
    private String duration;

    /**=========================Object Creation=========================*/

    HLCMembershipTypeMaster objMemTypeMaster = new HLCMembershipTypeMaster();

    public void setMembership_year(int membershipYear) {
        this.membership_year = membershipYear;
    }

    public int getMembership_year() {
        return membership_year;
    }

    public int getActive_Status() {
        return active_status;
    }



    public String getDuration() {
        return duration;
    }


    /**====================Setter Method================================*/
    public  void setMembershipTypeId(String membershipTypeId) {
	this.membershipTypeId = membershipTypeId;
    }
    public  void setMembershipTypeName(String membershipTypeName) {
        this.membershipTypeName = membershipTypeName;
    }
    public  void setUserTypeId(String userTypeId) {
        this.userTypeId = userTypeId;
    }
    public  void setMembershipAmount(String membershipAmount) {
        this.membershipAmount = membershipAmount;
    }
    public  void setModifyDate(Date modifyDate) {
	this.modifyDate = modifyDate;
    }
    public void setUserTypeName(String userTypeName){
        this.userTypeName = userTypeName;
    }

    public void setPeriodValue(String periodValue){
        this.periodValue = periodValue;
    }
    public void setTransaction_type_id(String transaction_type_id){
        this.transaction_type_id = transaction_type_id;
    }

    public void setActive_Status(int active_status) {
        this.active_status = active_status;
    }


    public void setDuration(String duration) {
        this.duration = duration;
    }


     public  String getMembershipTypeId() {
	return membershipTypeId;
    }
    public  String getMembershipTypeName() {
	return membershipTypeName;
    }
    public  String getUserTypeId() {
	return userTypeId;
    }
     public String getUserTypeName(){
        return userTypeName;
    }
    public  String getMembershipAmount() {
	return  membershipAmount;
    }
    public  Date getModifyDate() {
	return modifyDate;
    }

    public  String getPeriodValue() {
	return periodValue;
    }


    public String getTransaction_type_id() {
	return transaction_type_id;
    }


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
        Debug.print("ArabianSeaMembershipTypeBean ejbRemove");

        try {
            makeConnection();
            String deleteStatement = "delete from " + DBHelper.USEA_MEMBER_TYPE_MASTER   + "  where membership_type_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(deleteStatement);
            prepStmt.setString(1, membershipTypeId);
            int cnt = prepStmt.executeUpdate();
            System.out.println("Successfully Delete the MEMBER_TYPE_MASTER record : "+cnt);
            prepStmt.close();
            releaseConnection();
        } catch (Exception ex) {
            throw new EJBException("ArabianSeaMembershipTypeBean ejbRemove: " + ex.getMessage());
        }
    }

    /**
     * @see javax.ejb.EntityBean#unsetEntityContext()
     */
    public void unsetEntityContext() {
        context = null;
    }
    //for bug starts
    /**
     * @see javax.ejb.EntityBean#ejbLoad()
     */
    public void ejbLoad() {
        Debug.print("ArabianSeaMembershipTypeBean ejbLoad");
        this.membershipTypeId = (String) context.getPrimaryKey();
        try {
        makeConnection();
        Debug.print("ejb load select statement");
        String selectStatement = "SELECT membership_type_id,membership_type_name, membership_amount, substring(duration,1,charindex('-',duration)-1)+'-'+substring(duration,charindex('-',duration)+1,len(duration)) duration,active_status, user_type_id, priority_value   FROM " +
                DBHelper.USEA_MEMBER_TYPE_MASTER+" WHERE membership_type_id = ?";
        Debug.print("Query Log :"+selectStatement);
        PreparedStatement prepStmt = con.prepareStatement(selectStatement);
        prepStmt.setString(1, membershipTypeId);
        ResultSet rs = prepStmt.executeQuery();
        if (rs.next()) {
                this.membershipTypeId = rs.getString(1);
                Debug.print("membershipTypeId"+membershipTypeId);
                this.membershipTypeName = rs.getString(2);
                Debug.print("membershipTypeName"+membershipTypeName);
                this.membershipAmount = rs.getString(3);
                Debug.print("membershipAmount"+membershipAmount);
                this.duration = rs.getString(4);
                Debug.print("get duration"+duration);
                //For Debug Starts
                /*Debug.print("after duration");
                this.active_status = rs.getShort(5);
                Debug.print("utype ID");
                this.userTypeId = rs.getString(6);
                this.periodValue = rs.getString(7);*/
               // this.durationType = rs.getString(5);
                this.active_status = rs.getInt(5);

                 Debug.print("get the active_status "+active_status);
                //Debug.print("get the usertypeID");
                this.userTypeId = rs.getString(6);
                Debug.print("get the usertypeID "+userTypeId);
                this.periodValue = rs.getString(7);
                 Debug.print("getperiodValue--> "+periodValue);

                //For Debug Ends

        }
        prepStmt.close();
        } catch (SQLException sqe) {
            throw new EJBException(sqe);
        } finally {
            releaseConnection();
        }
       System.out.println("ejbLoad for ArabianSeaMembershipTypeBean");
    }

    /**
     * @see javax.ejb.EntityBean#ejbStore()
     */
    public void ejbStore() {
        try {
            System.out.println("ejbStore for ArabianSeaMembershipTypeBean");
            makeConnection();
            String updateStatement = "update " + DBHelper.USEA_MEMBER_TYPE_MASTER  +
             " set membership_type_name = ?, membership_amount = ?, duration = ?, active_status = ? , priority_value = ? , transaction_type_id = ? , membership_year = ?  where membership_type_id = ?";
            Debug.print("Query Log :"+updateStatement);

            PreparedStatement prepStmt = con.prepareStatement(updateStatement);
            double amt = Double.parseDouble(membershipAmount);
            prepStmt.setString(1, membershipTypeName);
            Debug.print(membershipTypeName);
            prepStmt.setDouble(2, amt);
            Debug.print(membershipAmount);
            prepStmt.setString(3, duration);
            Debug.print("duration"+duration);
            prepStmt.setInt(4, active_status);
            System.out.print(active_status);

         //   prepStmt.setString(5, userTypeId);
         //   Debug.print("set usertypeID "+userTypeId);
            prepStmt.setString(5, periodValue);
            Debug.print(periodValue);
            prepStmt.setString(6, transaction_type_id);
              Debug.print(transaction_type_id);
            prepStmt.setInt(7, membership_year);
            prepStmt.setString(8, membershipTypeId);
            Debug.print("set membership type id"+membershipTypeId);
            int rowCount = prepStmt.executeUpdate();
            Debug.print("ejbStore Sucessfully Updated." + rowCount);
            prepStmt.close();
        } catch (Exception ex) {
           throw new EJBException("ejbStore: " + ex.getMessage());
           // ex.printStackTrace();
        }finally {
            releaseConnection();
        }
    }

    //for bug ends
    // </editor-fold>

    public String ejbCreate(HLCMembershipTypeMaster objMemTypeMaster) throws CreateException {

        Debug.print("MembershipTypeMaster ejbCreate");
        if(objMemTypeMaster==null){
                throw new EJBException("ejbCreate: objMemTypeMaster argument is null or empty");
        }
        this.membershipTypeName = objMemTypeMaster.getMembershipTypeName();
        this.userTypeId = objMemTypeMaster.getUserTypeId();
        this.membershipAmount = objMemTypeMaster.getMembershipAmount();
        this.userTypeName = objMemTypeMaster.getUserTypeName();
        double amt = Double.parseDouble(membershipAmount);
        this.periodValue =objMemTypeMaster.getPeriodValue();
        //for bug starts
        this.duration=objMemTypeMaster.getDuration();
        this.active_status=objMemTypeMaster.getActive_Status();
        //for bug ends
         makeConnection();
        try {
            //for bug starts
            /* String insertStatement = "insert into " + DBHelper.USEA_MEMBER_TYPE_MASTER +
                " ( membership_type_name, membership_year, membership_amount, user_type_id, priority_value, transaction_type_id,active_status, membership_year,duration ) values ( ? , datepart(year,getdate()), ? , ? , ? , ? , ?,? ,?)";
             Debug.print("Query Log :"+insertStatement);

             prepStmt = con.prepareStatement(insertStatement);
             prepStmt.setString(1, membershipTypeName);
             prepStmt.setDouble(2, amt);
             prepStmt.setString(3, userTypeId);
             prepStmt.setString(4, periodValue);
             prepStmt.setString(5, objMemTypeMaster.getTransaction_type_id());
             prepStmt.setInt(6,active_status);
             prepStmt.setInt(7, objMemTypeMaster.getMembership_year());
             prepStmt.setString(8, duration);*/
             //for bug ends
            String insertStatement = "insert into " + DBHelper.USEA_MEMBER_TYPE_MASTER +
                " ( membership_type_name, membership_amount, user_type_id, priority_value, transaction_type_id,active_status, membership_year,duration ) values ( ? , ? , ? , ? , ? , ?, datepart(year,getdate()),?)";
             Debug.print("Query Log :"+insertStatement);

             prepStmt = con.prepareStatement(insertStatement);
             prepStmt.setString(1, membershipTypeName);
             prepStmt.setDouble(2, amt);
             prepStmt.setString(3, userTypeId);
             prepStmt.setString(4, periodValue);
             prepStmt.setString(5, objMemTypeMaster.getTransaction_type_id());
             prepStmt.setInt(6,active_status);
            // prepStmt.setInt(7, objMemTypeMaster.getMembership_year());
             prepStmt.setString(7, duration);
             int cnt = prepStmt.executeUpdate();
             System.out.println("successfully inserted into MembershipTypeMaster  : "+cnt);
             prepStmt.close();
             releaseConnection();
        } catch (Exception ex) {
            throw new EJBException("ejbCreate: MembershipTypeMaster  --- " + ex.getMessage());
        }
        return null;
    }

    public void ejbPostCreate(HLCMembershipTypeMaster objMemTypeMaster) {
        Debug.print("MembershipTypeMaster ejbPostCreate");
    }

    public String ejbFindByPrimaryKey(String membershipTypeId) throws FinderException {
         boolean result = false;
         try {
            makeConnection();
            String selectStatement = "SELECT membership_type_id FROM " + DBHelper.USEA_MEMBER_TYPE_MASTER + " " +
                    "WHERE membership_type_id = ? order by priority_value";

            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, membershipTypeId.trim());
            ResultSet rs = prepStmt.executeQuery();
            if( rs.next()) {
                this.membershipTypeId = rs.getString(1);
            }
            prepStmt.close();
         }catch (Exception e){
             e.printStackTrace();
         }finally {
             releaseConnection();
         }
         return membershipTypeId;
    }

    public Collection ejbFindByMembershipTypeName(String getmembershipTypeName) throws ObjectNotFoundException {
        this.membershipTypeName =getmembershipTypeName;
        Debug.print("membershipTypeName:="+membershipTypeName);
        ArrayList array = new ArrayList();
        try {
            makeConnection();
            String selectStatement = "SELECT membership_type_id FROM  " +DBHelper.USEA_MEMBER_TYPE_MASTER +
                    "  WHERE membership_type_name = ? order by priority_value";
            Debug.print("selectStatement"+selectStatement);
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, getmembershipTypeName);
            Debug.print("membershipTypeName::="+getmembershipTypeName);
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                array.add(rs.getString(1));
             }
             prepStmt.close();
        } catch (SQLException sqe) {
            throw new EJBException(sqe);
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return array;
    }

    public Collection ejbFindByMembershipTypeDetails() throws ObjectNotFoundException {
        this.userTypeName =userTypeName;
        ArrayList array = new ArrayList();
        try {
            makeConnection();
            String selectStatement = "SELECT membership_type_id,user_type_id, membership_type_name, " +
                    "membership_amount, priority_value FROM  " +DBHelper.USEA_MEMBER_TYPE_MASTER + " order by priority_value";
            prepStmt = con.prepareStatement(selectStatement);
            //prepStmt.setString(1, userTypeName);
            rs = prepStmt.executeQuery();
            while (rs.next()) {
                array.add(rs.getString(1));
                array.add(rs.getString(2));
                array.add(rs.getString(3));
                array.add(rs.getString(4));
                array.add(rs.getString(5));
                //array.add(rs.getString(6));

             }
             prepStmt.close();
        } catch (SQLException sqe) {
            throw new EJBException(sqe);
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return array;
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

