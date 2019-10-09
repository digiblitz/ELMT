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
package com.hlcmember.type.stateless;

import com.hlcmember.type.master.HLCArabianSeaMembershipTypeMasterLocal;
import com.hlcmember.type.master.HLCArabianSeaMembershipTypeMasterLocalHome;
import com.hlcmember.type.util.DBHelper;
import com.hlcmember.type.util.Debug;
import com.hlcmember.type.util.HLCHorseListVO;
import com.hlcmember.type.util.HLCMemberStatusUpdate;
import com.hlcmember.type.util.HLCMembershipTypeMaster;
import java.text.SimpleDateFormat;
import javax.ejb.*;
import java.rmi.RemoteException;
import javax.naming.*;
import java.sql.*;
import javax.sql.*;
import java.util.*;
import java.util.Date;
import java.text.NumberFormat;

/**
 * This is the bean class for the KaveryMembershipTypeSessionBean enterprise bean.
 * Created Aug 30, 2006 8:42:12 AM
 * @author harmohan
 */
public class HLCKaveryMembershipTypeSessionBean implements SessionBean, HLCKaveryMembershipTypeSessionRemoteBusiness {

    private SessionContext context;
    private InitialContext ic = null;
    private static final String dbName = "java:/ELMTMSSQLDS";
    private Connection con;
    private ResultSet rs = null;
    private PreparedStatement prepStmt = null;
    private String name = "ejb/HLCMembershipTypeJNDI";
    private String membershipTypeId;
    private String membershipTypeName;
    private String userTypeId;
    private String membershipAmount;
    private String duration;
    private String active_status;
    private String userTypeName;
    private Date modifyDate;
    HLCArabianSeaMembershipTypeMasterLocalHome home;
    HLCArabianSeaMembershipTypeMasterLocal remote;

    /*=======================MembnerDetails Variable ===============================*/
    String memberId = null;
    String userId = null;
    String nonuseaOrgName = null;
    String nonuseaOrgMemberId = null;
    String countryMailTypeName = null;
    String familyaddOns = null;
    String parentMemberId = null;
    String endowmentTrustAmount = null;
    String activeStatus = null;
    String appliedDate = null;
    String statusName = null;
    String expiryDate = null;
    String publicationName = null;

    /*============================User Master Variable ==================================*/
    String contactTypeId = null;
    String contactTypeName = null;
    String prefix = null;
    String firstName = null;
    String middleName = null;
    String lastName = null;
    String sufix = null;
    String dob = null;
    String gender = null;
    String emailId = null;
    String password = null;
    String secretQuestion = null;
    String secretAnswer = null;
    /*===========================Contact Details=========================================*/
    String suite = null;
    String address1 = null;
    String address2 = null;
    String city = null;
    String state = null;
    String country = null;
    String zip = null;
    String phoneNo = null;
    String mobileNo = null;
    String faxNo = null;
    String areaName = null;

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

    /**
     * See section 7.10.3 of the EJB 2.0 specification
     * See section 7.11.3 of the EJB 2.1 specification
     */
    public void ejbCreate() {
        try {
            InitialContext jndiContext = getInitialContext();
            Object obj = jndiContext.lookup(name);
            home = (HLCArabianSeaMembershipTypeMasterLocalHome) obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Name         :addMembershipTypeMaster
     * Description  :This method will insert record into the MembershipTypeMaster  table
     * @ param      :passing object of type of the class MembershipTypeMaster
     * @return      :void
     * @throws      :RemoteException, EJBException
     */
    public boolean addMembershipTypeMaster(HLCMembershipTypeMaster objMemberType) throws RemoteException {
        Debug.print("objMemberType:" + objMemberType.getUserTypeId());
        boolean result = false;
        //result = getMembershipTypeId(objMemberType.getMembershipTypeName());
        //Debugs Starts by Lakshmi
       // result = isMembershipTypeExist(objMemberType.getMembershipTypeName(), objMemberType.getMembership_year());
        result = isMembershipTypeExist(objMemberType.getUserTypeId(),objMemberType.getMembershipTypeId(),objMemberType.getMembershipTypeName(), objMemberType.getMembership_year());
        //Debugs Ends by Lakshmi
        if (result) {
            return false;
        } else {
            try {
                remote = home.create(objMemberType);
            } catch (Exception exp) {
                throw new EJBException("createMembershipTypeMaster : " + exp.getMessage());
            }
        }
        return true;
    }
    //for bug starts

    /**
     * Name         :editMembershipTypeMaster
     * Description  :This method will retrieve the member details for refund details table
     * @ param      :None
     * @return      :member Identification number
     * @throws      :RemoteException, FinderException
     */
    public boolean editMembershipTypeMaster(HLCMembershipTypeMaster objMemberType) throws RemoteException, FinderException {
        Debug.print("MembershipType ID : " + objMemberType.getMembershipTypeId());
        Debug.print("MembershipTypeName-->" + objMemberType.getMembershipTypeName());
        boolean result = false;
        /* if ((objMemberType.getMembershipTypeName().length()) > 79){
        Debug.print("Character Size is more then 80");
        return false;
        } */
        if (objMemberType == null || objMemberType.getMembershipTypeId() == null || objMemberType.getMembershipTypeId().trim().length() == 0) {

            result = false;
            throw new EJBException("MembershipType ID can't be empty");
        }
        //Debugs Starts by Lakshmi
           /* if(isMembershipTypeExist(objMemberType.getUserTypeId(),objMemberType.getMembershipTypeId(),objMemberType.getMembershipTypeName(), objMemberType.getMembership_year())){
            result=false;
           }*/
           //Debugs Ends by Lakshmi
            else {
            if (memberTypeIdExists(objMemberType.getMembershipTypeId()) == false) {
                //throw new EJBException("Contact Type ID Not Exists : " + contactTypeId);
                Debug.print("objMemberType.getMembershipTypeId():-->" + objMemberType.getMembershipTypeId());
                Debug.print("Kavery Session Bean contactTypeIdExists inside edit " + objMemberType.getMembershipTypeId());
                result = false;
            } else {//if (result == true){
                remote.setMembershipTypeId(objMemberType.getMembershipTypeId());
                Debug.print("Kavery Session BeanobjMemberType.getMembershipTypeId() inside edit true part: " + objMemberType.getMembershipTypeId());
                /*if (!remote.getMembershipTypeName().equals(objMemberType.getMembershipTypeName()) || !remote.getMembershipAmount().equals(objMemberType.getMembershipAmount()) || !remote.getDuration().equals(objMemberType.getDuration()) || !remote.getUserTypeId().equals(objMemberType.getUserTypeId()) || !remote.getPeriodValue().equals(objMemberType.getPeriodValue()) || !remote.getTransaction_type_id().equals(objMemberType.getTransaction_type_id())) {
                    Debug.print("getMembershipTypeName" + remote.getMembershipTypeName());
                    Debug.print("getMembershipAmount" + remote.getMembershipAmount());
                    Debug.print("getDuration()" + remote.getDuration());
                    Debug.print("getActive_status" + remote.getActive_Status());
                    Debug.print("getUserTypeId()" + remote.getUserTypeId());
                    Debug.print("getPeriodValue()" + remote.getPeriodValue());
                    Debug.print("getTransaction_type_id()" + remote.getTransaction_type_id());*/
                    remote.setMembershipTypeName(objMemberType.getMembershipTypeName());
                    Debug.print("Kavery Session Bean objMemberType.getMembershipTypeName() inside edit true part: " + objMemberType.getMembershipTypeName());
                    remote.setMembershipAmount(objMemberType.getMembershipAmount());
                    remote.setDuration(objMemberType.getDuration());
                    Debug.print("Kavery Session Bean objMemberType.getDuration edit true part: " + objMemberType.getDuration());
                    remote.setActive_Status(objMemberType.getActive_Status());
                    remote.setUserTypeId(objMemberType.getUserTypeId());
                    Debug.print("Kavery Session Bean getUserTypeId inside edit true part: " + objMemberType.getUserTypeId());
                    remote.setPeriodValue(objMemberType.getPeriodValue());
                    Debug.print("Kavery Session BeanobjMemberType.getPeriodValue() inside edit true part: " + objMemberType.getPeriodValue());
                    remote.setTransaction_type_id(objMemberType.getTransaction_type_id());
                    Debug.print("Kavery Session Bean objMemberType.getTransaction_type_id() inside edit true part: " + objMemberType.getTransaction_type_id());
                    remote.setMembership_year(objMemberType.getMembership_year());
                    Debug.print("Kavery Session Bean objMemberType.getMembership_year()inside edit true part: " + objMemberType.getMembership_year());
                    result = true;
                /*} else {
                    Debug.print("(((((");
                    return false;
                }*/
            }
        }
        return result;
    }
    //for bug ends

    public boolean editHumanMembershipTypeMaster(HLCMembershipTypeMaster objMemberType) throws RemoteException, FinderException {
        System.out.print("MembershipType ID : " + objMemberType.getMembershipTypeId());
        boolean result = false;

        if (objMemberType == null || objMemberType.getMembershipTypeId() == null || objMemberType.getMembershipTypeId().trim().length() == 0) {
            result = false;
            throw new EJBException("MembershipType ID can't be empty");
        }
        //Debugs Starts by Lakshmi
        /*if(isMembershipTypeExist(objMemberType.getUserTypeId(),objMemberType.getMembershipTypeId(),objMemberType.getMembershipTypeName(), objMemberType.getMembership_year())){
        result=false;
        }*/
        //Debugs Ends by Lakshmi
        else {
            if (memberTypeIdExists(objMemberType.getMembershipTypeId()) == false) {
                //throw new EJBException("Contact Type ID Not Exists : " + contactTypeId);
                Debug.print("Kavery Session Bean contactTypeIdExists inside edit " + membershipTypeId);
                result = false;
            } else {//if (result == true){
                Debug.print("Kavery Session Bean contactTypeIdExists inside edit true part: " + objMemberType.getMembershipTypeId());
                remote.setMembershipTypeId(objMemberType.getMembershipTypeId());
                remote.setMembershipTypeName(objMemberType.getMembershipTypeName());
                remote.setMembershipAmount(objMemberType.getMembershipAmount());
                remote.setActive_Status(objMemberType.getActive_Status());
                remote.setDuration(objMemberType.getDuration());
                remote.setUserTypeId(objMemberType.getUserTypeId());
                remote.setPeriodValue(objMemberType.getPeriodValue());
                remote.setTransaction_type_id(objMemberType.getTransaction_type_id());
                remote.setMembership_year(objMemberType.getMembership_year());
                result = true;
            }
        }
        return result;
    }

    public boolean editMemberStatus(String activeStat, String memberId) throws RemoteException {
        boolean bol = false;
        try {
            HLCMemberStatusUpdate objDAO = new HLCMemberStatusUpdate();
            bol = objDAO.updateMemberStatus(activeStat, memberId);
        } catch (Exception e) {
            Debug.print("Error while updating status : " + e.getMessage());
        }
        return true;
    }

    public boolean editHorseMemberStatus(String activeStat, String horseMemberId) throws RemoteException {
        boolean bol = true;
        try {
            HLCMemberStatusUpdate objDAO = new HLCMemberStatusUpdate();
            objDAO.updateHorseStatus(activeStat, horseMemberId);
        } catch (Exception e) {
            Debug.print("Error while updating editHorseMemberStatus : " + e.getMessage());
        }
        return true;
    }

    public boolean deleteMembershipTypeMaster(String membershipTypeId) throws RemoteException, FinderException {
        Debug.print("Kavery Session Bean deleteSponsorPlan");
        Debug.print("membershipTypeId :" + membershipTypeId);
        boolean result = false;
        if (membershipTypeId == null) {
            throw new EJBException("membershipTypeId can't be empty");
        }
        if (memberTypeIdExists(membershipTypeId) == false) {
            throw new EJBException("contactTypeId Not Exists" + membershipTypeId);
        }
        try {
            remote.remove();
            result = true;
        } catch (Exception ex) {
            throw new EJBException("removeMembershipTypeName: " + ex.getMessage());
        } finally {
            return result;
        }
    }

    public String getUserTypeIdOnName(String uname) throws RemoteException, FinderException {
        Debug.print("getUserTypeIdOnName on UserName:" + uname);
        String UserTypeID = null;
        try {
            makeConnection();
            String selectStatement = "SELECT user_type_id FROM " + DBHelper.USEA_USERTYPE_MASTER
                    + " where user_type_name = ?";
            Debug.print("selectStatement :" + selectStatement);
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, uname);
            rs = prepStmt.executeQuery();
            while (rs.next()) {
                UserTypeID = rs.getString(1);
                Debug.print("UserTypeID is :" + UserTypeID);
            }
            rs.close();
            prepStmt.close();
            return (UserTypeID);
        } catch (SQLException ex) {
            Debug.print("Exception in getMembershipTypeId:" + ex);
            return (UserTypeID);
        } finally {
            releaseConnection();
        }
    }
//for bug starts

    /**
     * Name         :getMembershipTypeId
     * Description  :This method will retrieve the member details for refund details table
     * @ param      :None
     * @return      :member Identification number
     * @throws      :RemoteException, FinderException
     */
    public boolean getMembershipTypeId(String getMembershipTypeName) {
        Debug.print("getMembershipTypeName:" + getMembershipTypeName);
        String memberTypeID = null;
        String MembershipTypeName = null;
        try {
            ArrayList result = (ArrayList) home.findByMembershipTypeName(getMembershipTypeName);
            Debug.print("getMembershipTypeID result size:" + result.size());
            Debug.print("result" + result);
            Debug.print("membershipTypeName::" + getMembershipTypeName);
            if (result != null) {
                Debug.print("iterat result");
                Iterator e = result.iterator();
                while (e.hasNext()) {
                    Debug.print("inside while loop");
                    HLCArabianSeaMembershipTypeMasterLocal localMTMRemote = (HLCArabianSeaMembershipTypeMasterLocal) e.next();
                    memberTypeID = localMTMRemote.getMembershipTypeId();
                    Debug.print("memberTypeID" + memberTypeID);
                    Debug.print("membership type ID" + localMTMRemote.getMembershipTypeId());
                    membershipTypeName = localMTMRemote.getMembershipTypeName();
                    Debug.print("memberTypeName" + membershipTypeName);
                    Debug.print("membership type Name" + localMTMRemote.getMembershipTypeName() + "exist membership name" + getMembershipTypeName);
                }
            }
        } catch (Exception e) {
            Debug.print("Exception in getMembershipTypeId:" + e);
        }
        if (memberTypeID != null) {
            Debug.print("memberTypeID != null");
            return false;
        } else {
            Debug.print("else part");
            return true;
        }

    }
//for bug ends

    /**
     * Name         :isMembershipTypeExist
     * Description  :This method will check Membership Type Existance
     * @ param      :None
     * @return      :member Identification number
     * @throws      :RemoteException, FinderException
     */
    //Debugs Starts by Lakshmi
  // public boolean isMembershipTypeExist(String membershipTypeName, int membership_year) {
    public boolean isMembershipTypeExist(String userTypeId,String membershipTypeId,String membershipTypeName, int membership_year) {
         Debug.print("isMembershipTypeExist userTypeId :" +userTypeId );
         Debug.print("isMembershipTypeExist membershipTypeId :" + membershipTypeId);
        //Debugs Ends by Lakshmi
        Debug.print("isMembershipTypeExist membershipTypeName :" + membershipTypeName);
        Debug.print("isMembershipTypeExist membership_year :" + membership_year);
        boolean status = false;
        try {
            makeConnection();
            //Debugs Starts by Lakshmi
          //if(userTypeId==null){
           // if(membershipTypeId==null){
                Debug.print("if");
            String selectStatement = "SELECT a.membership_type_id FROM " + DBHelper.USEA_MEMBER_TYPE_MASTER
                    + " a where a.user_type_id= ? and a.membership_type_name = ? and membership_year = datepart(year,getdate())";
            Debug.print("selectStatement :" + selectStatement);
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, userTypeId);
            prepStmt.setString(2, membershipTypeName);
          /*  }
      else{
                Debug.print("else");
             String selectStatement = "SELECT membership_type_id FROM " + DBHelper.USEA_MEMBER_TYPE_MASTER
                    + " where membership_type_id!=? and membership_type_name = ? and membership_year=? ";
            Debug.print("selectStatement :" + selectStatement);
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, membershipTypeId);
             prepStmt.setString(2, membershipTypeName);
             prepStmt.setInt(3, membership_year);
           }*/
       //  }
      /* else{
              if(membershipTypeId==null){
                  Debug.print("else if");
                String selectStatement = "SELECT membership_type_id FROM " + DBHelper.USEA_MEMBER_TYPE_MASTER
                    + " where user_type_id=? and membership_type_name = ?";
            Debug.print("selectStatement :" + selectStatement);
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, userTypeId);
            prepStmt.setString(2, membershipTypeName);
            // prepStmt.setInt(2, membership_year);
            }
      else{
                  Debug.print("else else");
             String selectStatement = "SELECT membership_type_id FROM " + DBHelper.USEA_MEMBER_TYPE_MASTER
                    + " where user_type_id=? and membership_type_id!=? and membership_type_name = ? and membership_year=? ";
            Debug.print("selectStatement :" + selectStatement);
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, userTypeId);
            prepStmt.setString(2, membershipTypeId);
             prepStmt.setString(3, membershipTypeName);
             prepStmt.setInt(4, membership_year);
           }
         }*/
            //Debugs Ends by Lakshmi
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                status = true;
            }
            Debug.print("record existance status :" + status);
            rs.close();
            prepStmt.close();
        } catch (SQLException sqe) {
            Debug.print("SQLException in isMembershipTypeExist :");
            sqe.printStackTrace();
        } catch (Exception e) {
            Debug.print("Exception in isMembershipTypeExist :" + e);
        } finally {
            releaseConnection();
        }
        return status;
    }

    /**
     * Name         :getMembershipTypeYearOnId
     * Description  :This method will check Membership Type Existance
     * @ param      :membershipTypeId
     * @return      :int
     * @throws      :RemoteException
     */
    public int getMembershipTypeYearOnId(String membershipTypeId) {
        Debug.print("getMembershipTypeYearOnId membershipTypeId :" + membershipTypeId);
        int year = 0;
        try {
            makeConnection();
            String selectStatement = "SELECT membership_year FROM " + DBHelper.USEA_MEMBER_TYPE_MASTER
                    + " where membership_type_id = ? ";
            Debug.print("selectStatement :" + selectStatement);
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, membershipTypeId);
            rs = prepStmt.executeQuery();
            while (rs.next()) {
                year = rs.getInt(1);
                Debug.print("year is :" + year);
            }
            rs.close();
            prepStmt.close();
        } catch (SQLException sqe) {
            Debug.print("SQLException in getMembershipTypeYearOnId :");
            sqe.printStackTrace();
        } catch (Exception e) {
            Debug.print("Exception in getMembershipTypeYearOnId :" + e);
        } finally {
            releaseConnection();
        }
        return year;
    }

    public Collection getMembershipTypeDetails() throws RemoteException, FinderException {
        ArrayList A = new ArrayList();
        Collection col = home.findByMembershipTypeDetails();
        ArrayList array = new ArrayList(col);
        for (Iterator it = array.iterator(); it.hasNext();) {
            // Object anObject = it.next( );
            String str = String.valueOf(it.next());
            String[] result = str.split(":");
            for (int x = 1; x < result.length; x++) {
                System.out.println(result[x]);
                A.add(result[1]);
            }
            //System.out.println( nonuseaOrgId); //anObject.toString() );
        }
        return A;
    }

    public Vector displayMembershipTypeDetails() throws RemoteException, FinderException {
        String membershipTypeId = null;
        String membershipTypeName = null;
        String userTypeId = null;
        String membershipAmount = null;
        String duration = null;
        String periodValue = null;
        String mDate = null;
        Vector vObj = new Vector();
        try {
            makeConnection();
            String selectStatement = "SELECT membership_type_id, membership_type_name, user_type_id, membership_amount, duration, modify_date, priority_value FROM  " + DBHelper.USEA_MEMBER_TYPE_MASTER;
            prepStmt = con.prepareStatement(selectStatement);
            //prepStmt.setString(1, userTypeName);
            rs = prepStmt.executeQuery();
            while (rs.next()) {
                membershipTypeId = rs.getString(1);
                membershipTypeName = rs.getString(2);
                userTypeId = rs.getString(3);
                membershipAmount = rs.getString(4);
                duration = rs.getString(5);
                mDate = rs.getString(6);
                periodValue = rs.getString(7);
                //  transacTypeId = rs.getString(8);
                String memberTypeDet[] = {membershipTypeId, membershipTypeName, userTypeId, membershipAmount, duration, mDate, periodValue};
                vObj.add(memberTypeDet);
            }
            rs.close();
            prepStmt.close();
        } catch (SQLException sqe) {
            throw new EJBException(sqe);
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return vObj;
    }

    public ArrayList getMembershipTypeForRegisteration() throws RemoteException {
        Debug.print("getMembershipTypeForRegisteration :");

        ArrayList vObj = new ArrayList();
        try {
            makeConnection();

            String selectStatement = "select DISTINCT A.membership_type_name, A.priority_value from "
                    + DBHelper.USEA_MEMBER_TYPE_MASTER + " A," + DBHelper.USEA_USERTYPE_MASTER + " B where A.user_type_id = B.user_type_id AND "
                    + "B.user_type_name = ? AND A.active_status = ? order by A.priority_value";
            Debug.print("selectStatement :" + selectStatement);
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, "Human");
            prepStmt.setBoolean(2, true);
            rs = prepStmt.executeQuery();
            while (rs.next()) {
                String membershipTypeName = rs.getString(1);
                vObj.add(membershipTypeName);
            }
            rs.close();
            prepStmt.close();
        } catch (SQLException sqe) {
            throw new EJBException(sqe);
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return vObj;
    }

    public ArrayList getMembershipTypeIdAndAmt(String MembershipTypeName, int year) throws RemoteException {
        Debug.print("getMembershipTypeIdAndAmt :");
        Debug.print("MembershipTypeName :" + MembershipTypeName);
        Debug.print("year :" + year);
        String membershipTypeId = "";
        float membershipAmt = 0;
        ArrayList vObj = new ArrayList();
        try {
            makeConnection();
            String selectStatement = " SELECT membership_type_id, membership_amount FROM " + DBHelper.USEA_MEMBER_TYPE_MASTER
                    + " WHERE membership_type_name = ? and membership_year = ? ";
            Debug.print("selectStatement :" + selectStatement);
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, MembershipTypeName);
            prepStmt.setInt(2, year);
            rs = prepStmt.executeQuery();
            while (rs.next()) {
                membershipTypeId = rs.getString(1);
                membershipAmt = rs.getFloat(2);
            }
            Debug.print("membershipTypeId :" + membershipTypeId);
            Debug.print("membershipAmt :" + membershipAmt);
            vObj.add(membershipTypeId);
            vObj.add(String.valueOf(membershipAmt));
            rs.close();
            prepStmt.close();
        } catch (SQLException sqe) {
            throw new EJBException(sqe);
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return vObj;
    }

    public Vector displayMembershipTypeDetails1(int membership_year) throws RemoteException, FinderException {

        Debug.print("membership_year :" + membership_year);
        String membershipTypeId = null;
        String membershipTypeName = null;
        String userTypeId = null;
        String membershipAmount = null;
        Vector vObj = new Vector();
        try {
            makeConnection();
            String selectStatement = "SELECT A.membership_type_id, A.membership_type_name, A.user_type_id, A.membership_amount, A.modify_date, A.priority_value FROM  "
                    + DBHelper.USEA_MEMBER_TYPE_MASTER + " A," + DBHelper.USEA_USERTYPE_MASTER + " B where A.user_type_id = B.user_type_id and B.user_type_name = ? and membership_year = ? ";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, "human");
            prepStmt.setInt(2, membership_year);
            rs = prepStmt.executeQuery();
            while (rs.next()) {
                membershipTypeId = rs.getString(1);
                membershipTypeName = rs.getString(2);
                userTypeId = rs.getString(3);
                membershipAmount = rs.getString(4);
                String mDate = rs.getString(5);
                String periodValue = rs.getString(6);
                String memberTypeDet[] = {membershipTypeId, membershipTypeName, userTypeId, membershipAmount, mDate, periodValue};
                vObj.add(memberTypeDet);
            }
            rs.close();
            prepStmt.close();
        } catch (SQLException sqe) {
            throw new EJBException(sqe);
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return vObj;
    }

    public Vector displayMembershipType(String userTypeId) throws RemoteException, FinderException {

        String membershipTypeId = null;
        String membershipTypeName = null;
        //String userTypeId = null;
        String membershipAmount = null;
        Vector vObj = new Vector();
        try {
            makeConnection();
            String selectStatement = "SELECT membership_type_id, membership_type_name, membership_amount,modify_date, priority_value FROM  "
                    + DBHelper.USEA_MEMBER_TYPE_MASTER + " WHERE user_type_id = ? ";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, userTypeId);
            rs = prepStmt.executeQuery();
            while (rs.next()) {
                membershipTypeId = rs.getString(1);
                membershipTypeName = rs.getString(2);
                membershipAmount = rs.getString(3);
                String mDate = rs.getString(4);
                String periodValue = rs.getString(5);
                String memberTypeDet[] = {membershipTypeId, membershipTypeName, membershipAmount, mDate, periodValue};
                vObj.add(memberTypeDet);
            }
            rs.close();
            prepStmt.close();
        } catch (SQLException sqe) {
            throw new EJBException(sqe);
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return vObj;
    }

    public Vector displayMembershipTypeOnYear(String userTypeId, int year) throws RemoteException {

        String membershipTypeId = null;
        String membershipTypeName = null;
        // String userTypeId = null;
        String membershipAmount = null;
        Vector vObj = new Vector();
        Debug.print("userTypeId :" + userTypeId);
        Debug.print("year :" + year);

        try {
            makeConnection();
            String selectStatement = "SELECT membership_type_id, membership_type_name, membership_amount, substring(duration,1,charindex('-',duration)-1)+'-'+substring(duration,charindex('-',duration)+1,len(duration)) duration, active_status, modify_date, priority_value, membership_year FROM  "
                    + DBHelper.USEA_MEMBER_TYPE_MASTER + " WHERE user_type_id = ? and membership_year = ? ";

            Debug.print("selectStatement :" + selectStatement);
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, userTypeId);
            prepStmt.setInt(2, year);
            rs = prepStmt.executeQuery();

            while (rs.next()) {
                membershipTypeId = rs.getString(1);
                membershipTypeName = rs.getString(2);
                membershipAmount = rs.getString(3);
                String duration = rs.getString(4);
                // Boolean active_status = rs.getBoolean(5);
                String mDate = rs.getString(6);
                String periodValue = rs.getString(7);
                String membership_year = String.valueOf(rs.getInt(8));

                String memberTypeDet[] = {membershipTypeId, membershipTypeName, membershipAmount, duration, mDate, periodValue, membership_year};
                vObj.add(memberTypeDet);
            }
            rs.close();
            prepStmt.close();
        } catch (SQLException sqe) {
            throw new EJBException(sqe);
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return vObj;
    }
    /* This function is used to display all the membership type of HumanMembers
     */

    public Vector displayHumanMembershipTypeDetail() throws RemoteException {
        String candelete = null;
        String membershipTypeId = null;
        String membershipTypeName = null;
        //For Debug Starts
        String membershipTypeYear = null;
        //For Debug Ends
        String DurationNO = null;
        String DurationType = null;
        String active_status = null;
        //String userTypeId = null;
        String membershipAmount = null;
        Vector vObj = new Vector();
        Debug.print("userTypeName :Human");

        try {
            makeConnection();
            String selectStatement = "select Case "
                    + "When m.membership_type_id in(select membership_type_id from " + DBHelper.USEA_MMS_CNTMAIL_MASTER + "  union all select membership_type_id from " + DBHelper.USEA_MMS_USERMASTER + "  union all select membership_type_id from " + DBHelper.USEA_MMS_MEMBERDETAIL + " ) Then 'No' "
                    + "Else 'Yes' "
                    //For Debug Starts
                    //+ "End CanDelete, m.membership_type_id, m.membership_type_name+'-'+convert(varchar(50),m.membership_year) membership_type_name,m.membership_amount,substring(m.duration,1,charindex('-',m.duration)-1) durationNo,substring(m.duration,charindex('-',m.duration)+1,len(m.duration)) durationType,m.active_status from "
                    + "End CanDelete, m.membership_type_id, m.membership_type_name,m.membership_year ,m.membership_amount,substring(m.duration,1,charindex('-',m.duration)-1) durationNo,substring(m.duration,charindex('-',m.duration)+1,len(m.duration)) durationType,m.active_status from "
                    //For Debug Ends
                    + DBHelper.USEA_MEMBER_TYPE_MASTER + " m , " + DBHelper.USEA_USERTYPE_MASTER + " u where m.user_type_id=u.user_type_id and user_type_name=? ";

            Debug.print("selectStatement :" + selectStatement);

            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, "Human");
            rs = prepStmt.executeQuery();

            while (rs.next()) {
                candelete = rs.getString(1);
                membershipTypeId = rs.getString(2);
                //For Debug Starts
                /*membershipTypeName = rs.getString(3);
                Float comSysNumber = rs.getFloat(4);
                NumberFormat f = NumberFormat.getInstance();
                f.setGroupingUsed(false);
                membershipAmount = f.format(comSysNumber);
                DurationNO = rs.getString(5);
                DurationType = rs.getString(6);
                active_status = String.valueOf(rs.getInt(7));
                 */
                membershipTypeName = rs.getString(3);
                membershipTypeYear = rs.getString(4);
                System.out.println("membershipTypeYear "+membershipTypeYear.length()+"=="+membershipTypeYear);
                if(membershipTypeYear!=null && !membershipTypeYear.equals("") && !membershipTypeYear.equals("0") && membershipTypeYear.length()==4)
                {
                    membershipTypeName = membershipTypeName+"-"+membershipTypeYear;
                }

                float comSysNumber = rs.getFloat(5);
                NumberFormat f = NumberFormat.getInstance();
                f.setGroupingUsed(false);
                membershipAmount = f.format(comSysNumber);
                DurationNO = rs.getString(6);
                DurationType = rs.getString(7);
                active_status = String.valueOf(rs.getInt(8));
                //For Debug Ends
                String memberTypeDet[] = {candelete, membershipTypeId, membershipTypeName, membershipAmount, DurationNO, DurationType, active_status};
                vObj.add(memberTypeDet);

            }
            rs.close();
            prepStmt.close();
        } catch (SQLException sqe) {
            throw new EJBException(sqe);
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return vObj;

    }
//for bug starts
/* This function is used to display all the membership type of NonHumanMembers
     */

    public Vector displayNonHumanMembershipTypeDetail(String uTypeId) throws RemoteException {

        String membershipTypeId = null;
        String membershipTypeName = null;
        //For Debug Starts
        String membershipTypeYear = null;
        //For Debug Ends
        String DurationNO = null;
        String DurationType = null;
        String active_status = null;
        String userTypeId = null;
        String membershipAmount = null;
        Vector vObj = new Vector();
        Debug.print("userTypeName :Horse");
        try {
            makeConnection();
            String selectStatement = "select Case "
                    + "When m.membership_type_id in(select membership_type_id from " + DBHelper.USEA_MMS_CNTMAIL_MASTER + "  union all select membership_type_id from " + DBHelper.USEA_MMS_DASHBOARD_HORSES + " union all select membership_type_id from " + DBHelper.USEA_MMS_MEMBERDETAIL + " ) Then 'No' "
                    + "Else 'Yes' "
                    //For Debugs starts
                   // + "End CanDelete,m.membership_type_id, m.membership_type_name+'-'+convert(varchar(50),m.membership_year) membership_type_name,m.membership_amount,substring(m.duration,1,charindex('-',m.duration)-1) durationNo,substring(m.duration,charindex('-',m.duration)+1,len(m.duration)) durationType,m.active_status from "
                    + "End CanDelete,m.membership_type_id, m.membership_type_name, m.membership_year,m.membership_amount,substring(m.duration,1,charindex('-',m.duration)-1) durationNo,substring(m.duration,charindex('-',m.duration)+1,len(m.duration)) durationType,m.active_status from "
                    //For Debugs Ends
                    + DBHelper.USEA_MEMBER_TYPE_MASTER + " m  where m.user_type_id=?";
            Debug.print("selectStatement :" + selectStatement);
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, uTypeId);
            rs = prepStmt.executeQuery();
            while (rs.next()) {
                String candelete = rs.getString(1);
                membershipTypeId = rs.getString(2);
                membershipTypeName = rs.getString(3);
                //For Debugs Starts
                /*Float comSysNumber = rs.getFloat(4);
                NumberFormat f = NumberFormat.getInstance();
                f.setGroupingUsed(false);
                membershipAmount = f.format(comSysNumber);
                DurationNO = rs.getString(5);
                DurationType = rs.getString(6);
                active_status = String.valueOf(rs.getInt(7));                 */
                membershipTypeYear = rs.getString(4);
                System.out.println("membershipTypeYear "+membershipTypeYear.length()+"=="+membershipTypeYear);
                if(membershipTypeYear!=null && !membershipTypeYear.equals("") && !membershipTypeYear.equals("0") && membershipTypeYear.length()==4)
                {
                    membershipTypeName = membershipTypeName+"-"+membershipTypeYear;
                }
                float comSysNumber = rs.getFloat(5);
                NumberFormat f = NumberFormat.getInstance();
                f.setGroupingUsed(false);
                membershipAmount = f.format(comSysNumber);
                DurationNO = rs.getString(6);
                DurationType = rs.getString(7);
                active_status = String.valueOf(rs.getInt(8));
                //For Debugs Ends
                String memberTypeDet[] = {candelete, membershipTypeId, membershipTypeName, membershipAmount, DurationNO, DurationType, active_status};
                vObj.add(memberTypeDet);
            }
            rs.close();
            prepStmt.close();
        } catch (SQLException sqe) {
            throw new EJBException(sqe);
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return vObj;
    }
    //for bug ends

    public Vector displayMembershipTypeOnYearAndTypeName(String userTypeName, int year) throws RemoteException {

        String membershipTypeId = null;
        String membershipTypeName = null;
        //String userTypeId = null;
        String membershipAmount = null;
        Vector vObj = new Vector();
        Debug.print("userTypeName :" + userTypeName);
        Debug.print("year :" + year);

        try {
            makeConnection();
            String selectStatement = "SELECT membership_type_id, membership_type_name, membership_amount,modify_date, priority_value, membership_year FROM "
                    + DBHelper.USEA_MEMBER_TYPE_MASTER + " A ," + DBHelper.USEA_USERTYPE_MASTER + " B WHERE A.user_type_id = B.user_type_id AND B.user_type_name = ? and membership_year = ? ";

            Debug.print("selectStatement :" + selectStatement);
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, userTypeName);
            prepStmt.setInt(2, year);
            rs = prepStmt.executeQuery();
            while (rs.next()) {
                membershipTypeId = rs.getString(1);
                membershipTypeName = rs.getString(2);
                membershipAmount = rs.getString(3);
                String mDate = rs.getString(4);
                String periodValue = rs.getString(5);
                String membership_year = String.valueOf(rs.getInt(6));
                String memberTypeDet[] = {membershipTypeId, membershipTypeName, membershipAmount, mDate, periodValue, membership_year};
                vObj.add(memberTypeDet);
            }
            rs.close();
            prepStmt.close();
        } catch (SQLException sqe) {
            throw new EJBException(sqe);
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return vObj;
    }

    public String[] getMembershipTypeDetail(String memberTypeId) throws RemoteException, FinderException {
        String memberTypeDet[] = null;
        try {
            makeConnection();
            String selectStatement = "SELECT membership_type_id, membership_type_name,user_type_id, membership_amount,modify_date, priority_value, transaction_type_id, substring(duration,1,charindex('-',duration)-1) durationNo,substring(duration,charindex('-',duration)+1,len(duration)) durationType,active_status,membership_year FROM  "
                    + DBHelper.USEA_MEMBER_TYPE_MASTER + " WHERE  membership_type_id= ? ";
            prepStmt = con.prepareStatement(selectStatement);
            Debug.print(selectStatement);
            prepStmt.setString(1, memberTypeId);
            Debug.print(memberTypeId);
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                String membershipTypeId = rs.getString(1);
                String membershipTypeName = rs.getString(2);
                Debug.print(membershipTypeName);
                String UserTypeID = rs.getString(3);
                Debug.print(UserTypeID);
                float comSysNumber = rs.getFloat(4);
                NumberFormat f = NumberFormat.getInstance();
                f.setGroupingUsed(false);
                String membershipAmount = f.format(comSysNumber);
                String mDate = rs.getString(5);
                String priorityValue = rs.getString(6);
                String transaction_type_id = rs.getString(7);
                String durationNo = rs.getString(8);
                Debug.print(durationNo);
                String durationType = rs.getString(9);
                Debug.print(durationType);
                String active_status = rs.getString(10);
                Debug.print(active_status);
                //Debugs Starts by Lakshmi
                int membershipyear = rs.getInt(11);
                NumberFormat i = NumberFormat.getInstance();
                i.setGroupingUsed(false);
                String membership_year = i.format(membershipyear);
                Debug.print("year" +membership_year);
                //Debugs Ends by lakshmi
                // Hari Changed
                //Debugs Starts by Lakshmi
                //String memberTypeDetlocal[] = {membershipTypeId, membershipTypeName, UserTypeID, membershipAmount, mDate, priorityValue, transaction_type_id, durationNo, durationType, active_status};
                String memberTypeDetlocal[] = {membershipTypeId, membershipTypeName, UserTypeID, membershipAmount, mDate, priorityValue, transaction_type_id, durationNo, durationType, active_status,membership_year};
                //Debugs Ends by Lakshmi
                memberTypeDet = memberTypeDetlocal;
            }
            rs.close();
            prepStmt.close();
        } catch (SQLException sqe) {
            throw new EJBException(sqe);
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return memberTypeDet;
    }

    public Vector displayMembershipDetailByMemberIDe(String membershipTypeId, String statusId, int rCnt) throws RemoteException, FinderException {
        Debug.print("KaveryMembershipTypeSessionBean.displayMembershipDetailByMemberIDe()  MemberShip Type Id:" + membershipTypeId);
        Debug.print("KaveryMembershipTypeSessionBean.displayMembershipDetailByMemberIDe()  MemberShip Status Id:" + statusId);
        Vector vObj = new Vector();
        String command = "{call sp_get_registeredmembers(?,?,?)}";
        try {
            makeConnection();
            /*
            String selectStr = "SELECT A.add_date, A.member_id, B.first_name, B.last_name,C.status_name,B.user_id FROM  "+
            DBHelper.USEA_MMS_MEMBERDETAIL+" A, "+DBHelper.USEA_MMS_USERMASTER+" B, "+DBHelper.USEA_STATUS_MASTER+" C "+
            " WHERE A.user_id = B.user_id and A.status_id = C.status_id and A.membership_type_id = ? and A.status_id = ?";
            prepStmt = con.prepareStatement(selectStr);
            prepStmt.setString(1, membershipTypeId);
            prepStmt.setString(2, statusId);
             */
            CallableStatement cstmt = con.prepareCall(command);
            cstmt.setInt(1, rCnt);
            cstmt.setString(2, membershipTypeId);
            cstmt.setString(3, statusId);
            rs = cstmt.executeQuery();
            while (rs.next()) {
                String addDate = rs.getString(1);
                String memberId = rs.getString(2);
                String firstName = rs.getString(3);
                String lastName = rs.getString(4);
                String statusName = rs.getString(5);
                String userId = rs.getString(6);
                String paymentId = rs.getString(7);
                String memberTypeDet[] = {addDate, memberId, firstName, lastName, statusName, userId, paymentId};
                vObj.add(memberTypeDet);
            }
            rs.close();
            cstmt.close();
        } catch (SQLException sqe) {
            throw new EJBException(sqe);
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        Debug.print("KaveryMembershipTypeSessionBean.displayMembershipDetailByMemberIDe() - Record Size of Member:" + vObj.size());
        return vObj;
    }

    public int memberRowCount(String membershipTypeId, String statusId) throws RemoteException {
        Debug.print("KaveryMembershipTypeSessionBean.memberRowCount()  MemberShip Type Id:" + membershipTypeId);
        Debug.print("KaveryMembershipTypeSessionBean.memberRowCount()  MemberShip Status Id:" + statusId);
        int rowCnt = 0;
        try {
            makeConnection();

            String selectStr = "SELECT count(*) FROM  "
                    + DBHelper.USEA_MMS_MEMBERDETAIL + " A, " + DBHelper.USEA_MMS_USERMASTER + " B, " + DBHelper.USEA_STATUS_MASTER + " C "
                    + " WHERE A.user_id = B.user_id and A.status_id = C.status_id and A.membership_type_id = ? and A.status_id = ?";
            prepStmt = con.prepareStatement(selectStr);
            prepStmt.setString(1, membershipTypeId);
            prepStmt.setString(2, statusId);
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                rowCnt = rs.getInt(1);
            }
            rs.close();
            prepStmt.close();
        } catch (SQLException sqe) {
            throw new EJBException(sqe);
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        Debug.print("KaveryMembershipTypeSessionBean.memberRowCount() - Record Size of Member:" + rowCnt);
        return rowCnt;
    }

    public ArrayList displayMembershipDetailForHorse(String userTypeId, String membershipTypeId, String statusId) throws RemoteException {

        ArrayList horseList = new ArrayList();
        try {
            makeConnection();
            /*            String selectStr = "SELECT A.add_date, A.member_id, B.first_name, B.last_name,C.status_name,B.user_id FROM  "+
            DBHelper.USEA_MMS_MEMBERDETAIL+" A, "+DBHelper.USEA_MMS_USERMASTER+" B, "+DBHelper.USEA_STATUS_MASTER+" C "+
            " WHERE A.user_id = B.user_id and A.status_id = C.status_id and A.membership_type_id = ? and A.status_id = ?";
             */
            String selectStr = "select A.horse_id, A.horse_member_id, A.owner_id, A.competition_name, "
                    + " A.rider_member_id, A.add_date, B.status_name  from tblHorseMemberDetails A, "
                    + " tblMembershipStatusMaster B, tblMemberDetails C, tblMembershipTypeMaster D,  "
                    + " tblUserMaster E, tblUserTypeMaster F  "
                    + " where A.horse_member_id = C.member_id and C.membership_type_id = D.membership_type_id and "
                    + " C.user_id = E.user_id and E.user_type_id = F.user_type_id and A.status_id = B.status_id and "
                    + " A.status_id = ? and  C.membership_type_id = ?  and  E.user_type_id = ?";

            prepStmt = con.prepareStatement(selectStr);
            Debug.print("displayMembershipDetailForHorse()" + selectStr);
            prepStmt.setString(1, statusId);
            prepStmt.setString(2, membershipTypeId);
            prepStmt.setString(3, userTypeId);
            rs = prepStmt.executeQuery();
            while (rs.next()) {
                String horseId = rs.getString(1);
                String horseMemberId = rs.getString(2);
                String ownerId = rs.getString(3);
                String competitionName = rs.getString(4);
                String riderMemberId = rs.getString(5);
                Date addDate = rs.getDate(6);
                String statusName = rs.getString(7);
                HLCHorseListVO objHorseListVO = new HLCHorseListVO();
                objHorseListVO.setHorseId(horseId);
                objHorseListVO.setHorseMemberId(horseMemberId);
                objHorseListVO.setOwnerId(ownerId);
                objHorseListVO.setCompetitionName(competitionName);
                objHorseListVO.setRiderMemberId(riderMemberId);
                objHorseListVO.setAddDate(addDate);
                objHorseListVO.setStatusName(statusName);
                horseList.add(objHorseListVO);
            }
            rs.close();
            prepStmt.close();
        } catch (SQLException sqe) {
            throw new EJBException(sqe);
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return horseList;
    }

    /**
     * Name         :displaynonhumanUserTypeDetails
     * Description  :This method will retrieve the user Type details for Admin page
     * @ param      :None
     * @return      :member Identification number
     * @throws      :RemoteException, FinderException
     */
    //bug starts
//here select the usertype from user type master
    public Vector displayUserTypeDetails() throws RemoteException, FinderException {
        Vector V = new Vector();
        try {
            makeConnection();
            String selectStatement = "SELECT user_type_id, user_type_name FROM  " + DBHelper.USEA_USERTYPE_MASTER + " where active_status = ? and user_type_name!=?";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setBoolean(1, true);
            prepStmt.setString(2, "Human");
            rs = prepStmt.executeQuery();
            while (rs.next()) {
                String ID = rs.getString(1);
                String name = rs.getString(2);
                String[] userType = {ID, name};
                V.addElement(userType);
            }
            rs.close();
            prepStmt.close();
        } catch (Exception e) {
            Debug.print("Error while displaying user type  " + e.getMessage());
        } finally {
            releaseConnection();
        }
        return V;
    }
//For Services Type Human Add,Edit ,delete

    /**
     * Name         :displayHumanUserTypeDetails
     * Description  :This method will retrieve the user Type details for Admin page
     * @ param      :None
     * @return      :member Identification number
     * @throws      :RemoteException, FinderException
     */
    public Vector displayHumanUserTypeDetails() throws RemoteException, FinderException {
        Vector V = new Vector();
        try {
            makeConnection();
            String selectStatement = "SELECT user_type_id, user_type_name FROM  " + DBHelper.USEA_USERTYPE_MASTER + " where active_status = ? and user_type_name=?";
            prepStmt = con.prepareStatement(selectStatement);

            prepStmt.setBoolean(1, true);
            prepStmt.setString(2, "Human");

            rs = prepStmt.executeQuery();
            while (rs.next()) {
                String ID = rs.getString(1);
                String name = rs.getString(2);
                String[] userType = {ID, name};
                V.addElement(userType);
            }
            rs.close();
            prepStmt.close();
        } catch (Exception e) {
            Debug.print("Error while displaying user type  " + e.getMessage());
        } finally {
            releaseConnection();
        }
        return V;
    }
//For Services Type Human Add,Edit ,delete

    /**
     * Name         :displayUserTypeDetails
     * Description  :This method will retrieve the user Type details for Admin page
     * @ param      :None
     * @return      :member Identification number
     * @throws      :RemoteException, FinderException
     */
    public Vector displayAnnualMemberDetails() throws RemoteException, FinderException {
        Vector V = new Vector();
        try {
            makeConnection();
            String selectStatement = "SELECT mem_type_id, mem_type_name FROM " + DBHelper.USEA_ANNUAL_MEMBERTYPEMASTER;
            prepStmt = con.prepareStatement(selectStatement);

            rs = prepStmt.executeQuery();
            while (rs.next()) {
                String ID = rs.getString(1);
                String name = rs.getString(2);
                String[] userType = {ID, name};
                V.addElement(userType);
            }
            rs.close();
            prepStmt.close();
        } catch (Exception e) {
            Debug.print("Error while displayAnnualMemberDetails " + e.getMessage());
        } finally {
            releaseConnection();
        }
        return V;
    }

    /**
     * Name         :memberExists
     * Description  :This method will check for the existing of memberId and call the findByPrimaryKey method
     * @ param      :None
     * @return      :boolean value
     * @throws      :none
     */
    private boolean memberTypeIdExists(String membershipTypeId) {
        boolean result = false;
//Debug.print("Kavery Session Bean contactTypeIdExists in side loop: "+contactTypeId);
        if (membershipTypeId != this.membershipTypeId) {
            try {
                //  Debug.print("Kavery Session Bean contactTypeIdExists in side loop: "+contactTypeId);
                remote = home.findByPrimaryKey(membershipTypeId);
                this.membershipTypeId = membershipTypeId;
                Debug.print("Kavery Session Bean memberTypeIdExists in side home: " + home);
                Debug.print("Kavery Session Bean memberTypeIdExists in side remote: " + remote);
                result = true;
            } catch (Exception ex) {
                result = true;
                System.out.println("Exception:" + ex);
            }
        }
        Debug.print("Kavery Session Bean memberTypeIdExists  " + result);
        return result;
    }

    /**
     * Name         :getInitialContext
     * Description  :This method will Initialize the naming context for the container
     * @ param      :None
     * @return      :InitialContext
     * @throws      :NamingException
     */
    public InitialContext getInitialContext() throws javax.naming.NamingException {
        if (this.ic == null) {
            ic = new InitialContext();
        }
        System.out.println("This is from getInitialContext()");
        return ic;
    }

    /**
     * Name         :selectDetailsForMember
     * Description  :This method will Display the member Details, User Details and contact Details
     * @ param      :memberShipTypeId,statusId
     * @return      :Vector
     * @throws      :RemoteException, FinderException
     */
    public Vector selectDetailsForMember(String memberId) throws RemoteException, FinderException {
        Vector vObj = new Vector();
        //String memberShipTypeId, String statusId
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Date dt1 = new Date();
        Date dt2 = new Date();

        makeConnection();
        try {

            String selectStatement = "SELECT A.member_id, A.user_id, A.family_add_ons, A.parent_member_id, "
                    + "A.endowment_trust_amount, A.active_status, A.add_date, A.expiry_date,A.nonusea_org_member_id, "
                    + "B.other_org_name, C.country_mail_type_name, D.status_name, E.membership_type_name  FROM "
                    + DBHelper.USEA_MMS_MEMBERDETAIL + " A " + " LEFT JOIN tblOtherOrgMaster AS B ON "
                    + "A.nonusea_org_id = B.other_org_id LEFT JOIN " + DBHelper.USEA_MMS_CNTMAIL_MASTER + " AS C ON "
                    + "A.country_mail_type_id = C.country_mail_type_id INNER JOIN " + DBHelper.USEA_STATUS_MASTER + " AS D ON "
                    + "A.status_id = D.status_id INNER JOIN " + DBHelper.USEA_MEMBER_TYPE_MASTER + " AS E ON "
                    + "A.membership_type_id = E.membership_type_id AND A.member_id = ? ";//AND A.status_id = ? ";

            Debug.print(selectStatement);
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, memberId);
            // prepStmt.setString(2, statusId);
            rs = prepStmt.executeQuery();
            // System.out.println("Inside the selectDetailsForMember memberShipTypeId : "+memberShipTypeId);
            if (rs.next()) {
                this.memberId = rs.getString(1);
                this.userId = rs.getString(2);
                this.familyaddOns = rs.getString(3);
                this.parentMemberId = rs.getString(4);
                this.endowmentTrustAmount = rs.getString(5);
                this.activeStatus = rs.getString(6);
                //dt1 = rs.getDate(7);
                this.appliedDate = rs.getString(7);
                // dt2 = rs.getDate(8);
                this.expiryDate = rs.getString(8);
                this.nonuseaOrgMemberId = rs.getString(9);
                this.nonuseaOrgName = rs.getString(10);
                this.countryMailTypeName = rs.getString(11);
                this.statusName = rs.getString(12);
                this.membershipTypeName = rs.getString(13);
                Debug.print("membershipTypeName==>" + membershipTypeName);

            }
            rs.close();
            //this.appliedDate = formatter.format(dt1);
            //this.expiryDate = formatter.format(dt2);


            String memDet[] = {memberId, userId, familyaddOns, parentMemberId, endowmentTrustAmount, activeStatus, appliedDate,
                expiryDate, nonuseaOrgMemberId, nonuseaOrgName, countryMailTypeName, statusName, membershipTypeName};
            vObj.add(memDet);

            dt1 = dt2 = null;
            selectStatement = "SELECT user_id, contact_type_id, prefix,first_name,middle_name,last_name,sufix,dob,gender,email_id, password, secret_question, "
                    + "secret_answer FROM " + DBHelper.USEA_MMS_USERMASTER + " WHERE user_id = ?";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, userId);
            rs = prepStmt.executeQuery();
            System.out.println("Inside the selectDetailsForMember userId : " + userId);
            if (rs.next()) {
                this.userId = rs.getString(1);
                this.contactTypeId = rs.getString(2);
                this.prefix = rs.getString(3);
                this.firstName = rs.getString(4);
                this.middleName = rs.getString(5);
                this.lastName = rs.getString(6);
                this.sufix = rs.getString(7);
                dt1 = rs.getDate(8);
                this.dob = rs.getString(8);
                this.gender = rs.getString(9);
                this.emailId = rs.getString(10);
                this.password = rs.getString(11);
                this.secretQuestion = rs.getString(12);
                this.secretAnswer = rs.getString(13);
            }
            try {
                String str = "SELECT contact_type_name FROM " + DBHelper.USEA_MMS_CONTACT_TYPE_MASTER + " WHERE contact_type_id = ? ";
                prepStmt = con.prepareStatement(str);
                prepStmt.setString(1, contactTypeId);
                rs = prepStmt.executeQuery();
                if (rs.next()) {
                    this.contactTypeName = rs.getString(1);
                    System.out.println(" contactTypeName   :  " + contactTypeName);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            //prepStmt.close();
            //rs.close();
            //releaseConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //this.dob = formatter.format(dt1);
            /*====================Adding User Details fro User Master===== Vector One=============================================*/
        String userDetail[] = {contactTypeName, prefix, firstName, middleName, lastName, sufix, dob, gender, emailId, password, secretQuestion, secretAnswer};
        vObj.add(userDetail);
        //DBHelper.USEA_CONTACT_DETAILS + " (contact_type_id,user_id,suite,address1,"+
        //        "address2,city,state,country,zip,phone_no, mobile_no, fax_no
        try {

            String selectStatement = "SELECT suite,address1,address2,city,state,country,zip,phone_no, mobile_no,fax_no,contact_type_id FROM "
                    + DBHelper.USEA_MMS_CONTACT_DETAIL + " WHERE user_id = ?";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, userId);
            rs = prepStmt.executeQuery();
            Debug.print("The Query String is : " + selectStatement);
            System.out.println("Inside the loadContactDetails User Id : " + userId);
            while (rs.next()) {
                this.suite = rs.getString(1);
                this.address1 = rs.getString(2);
                this.address2 = rs.getString(3);
                this.city = rs.getString(4);
                this.state = rs.getString(5);
                this.country = rs.getString(6);
                this.zip = rs.getString(7);
                this.phoneNo = rs.getString(8);
                this.mobileNo = rs.getString(9);
                this.faxNo = rs.getString(10);
                this.contactTypeId = rs.getString(11);
                try {
                    String str = "SELECT contact_type_name FROM " + DBHelper.USEA_MMS_CONTACT_TYPE_MASTER + " WHERE contact_type_id = ? ";
                    prepStmt = con.prepareStatement(str);
                    prepStmt.setString(1, contactTypeId);
                    rs = prepStmt.executeQuery();
                    Debug.print("The Query String is : " + str);
                    if (rs.next()) {
                        this.contactTypeName = rs.getString(1);
                        System.out.println(" contactTypeName   :  " + contactTypeName);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    String str1 = " select A.area_name from tblmeeAreaMaster A, tblMemberDetails B, "
                            + " tblContactDetails C, tblMeeMapStateZip D, tblUserMaster E, tblMeeStateMaster F "
                            + " where A.area_id = D.area_id and B.user_id = C.user_id and "
                            + " C.zip >= D.zip_code_from and C.zip <= D.zip_code_to and "
                            + " C.contact_type_id = E.contact_type_id and D.state_id = F.state_id and "
                            + " C.user_id = E.user_id and B.member_id =? ";

                    prepStmt = con.prepareStatement(str1);
                    prepStmt.setString(1, memberId);
                    Debug.print("The Query String is : " + str1);
                    rs = prepStmt.executeQuery();
                    Debug.print("The Query String is : " + str1);
                    if (rs.next()) {
                        this.areaName = rs.getString(1);
                        System.out.println(" areaName   :  " + areaName);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                /**==========================Contact Details including Primary and Secondary name ==================================*/
                String contactDetail[] = {contactTypeName, suite, address1, address2, city, state, country, zip, phoneNo, mobileNo, faxNo, areaName};
                vObj.add(contactDetail);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        //prepStmt.close();
        //rs.close();
        //rs1.close();
        releaseConnection();

        return vObj;
    }

    public ArrayList getMailPreference(String memId) throws RemoteException {
        ArrayList mailPreference = new ArrayList();
        makeConnection();
        try {
            String selectstatement = "select A.publication_name from tblMemberSubscription A, tblPublicationDetails B "
                    + " where A.publication_id =B.publication_id AND B.member_id=?";

            prepStmt = con.prepareStatement(selectstatement);
            prepStmt.setString(1, memId);
            rs = prepStmt.executeQuery();
            while (rs.next()) {
                mailPreference.add(rs.getString(1));
                this.publicationName = rs.getString(1);
                Debug.print("Mailing Preference : " + publicationName);
            }
            rs.close();
            releaseConnection();
        } catch (Exception e) {
            releaseConnection();
            e.printStackTrace();
        }
        return mailPreference;
    }

    public ArrayList getFamilyAddOnId(String parentMemberId) throws RemoteException {
        ArrayList familyAddOn = new ArrayList();
        makeConnection();
        try {
            String selectStatement = "SELECT member_id,user_id  FROM "
                    + DBHelper.USEA_MMS_MEMBERDETAIL + " WHERE parent_member_id = ? ";

            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, parentMemberId);
            // prepStmt.setString(2, statusId);
            rs = prepStmt.executeQuery();
            while (rs.next()) {
                String[] addon = {rs.getString(1), rs.getString(2)};
                familyAddOn.add(addon);
            }

            releaseConnection();
        } catch (Exception e) {
            releaseConnection();
            e.printStackTrace();
        }
        return familyAddOn;
    }

    public boolean updateUserMemberFlag(String uId, String splNotes, boolean value) throws RemoteException {
        boolean bol = false;

        Debug.print("updateUserMemberFlag(String uId, String splNotes, boolean value) :");
        Debug.print("uId :" + uId);
        Debug.print("splNotes :" + splNotes);
        Debug.print("address status :" + value);

        if (uId != null && uId.trim().length() != 0) {
            try {
                makeConnection();
                String updateStatement = "UPDATE " + DBHelper.USEA_MMS_USERMASTER + " set address_status = ?, spl_notes = ? "
                        + " WHERE user_id = ? ";

                prepStmt = con.prepareStatement(updateStatement);
                prepStmt.setBoolean(1, value);
                prepStmt.setString(2, splNotes);
                prepStmt.setString(3, uId);
                int rowCount = prepStmt.executeUpdate();
                Debug.print("UserMaster in KaveryMembershipTypeSessionBean Sucessfully Updated." + rowCount);
                prepStmt.close();
                if (rowCount > 0) {
                    bol = true;
                }

                releaseConnection();
            } catch (Exception e) {
                releaseConnection();
                e.printStackTrace();
            } finally {
                releaseConnection();
            }
        }
        return bol;
    }

    public ArrayList getAccTxnTypDetails() throws RemoteException {

        ArrayList accTxnDet = new ArrayList();
        Debug.print("getAccTxnTypDetails in session bean :");

        try {
            HLCMemberStatusUpdate objDAO = new HLCMemberStatusUpdate();
            accTxnDet = objDAO.getAccTxnTypDetails();
        } catch (Exception e) {
            Debug.print("Error at getAccTxnTypDetails in session bean : " + e.getMessage());
        }

        return accTxnDet;
    }

    public String[] selectNextYrPrice(String membershipTypName, int membershipYear) throws RemoteException {
        float amt = 0;
        String memTypId = "";

        Debug.print("selectNextYrPrice() in session bean :");
        Debug.print("membershipTypName :" + membershipTypName);
        Debug.print("membershipYear :" + membershipYear);

        makeConnection();
        try {
            String selectstatement = "select membership_amount, membership_type_id from " + DBHelper.USEA_MEMBER_TYPE_MASTER
                    + " where membership_type_name = ? AND membership_year = ?";

            Debug.print("selectstatement :" + selectstatement);
            prepStmt = con.prepareStatement(selectstatement);
            prepStmt.setString(1, membershipTypName);
            prepStmt.setInt(2, membershipYear);

            rs = prepStmt.executeQuery();

            while (rs.next()) {
                amt = rs.getFloat(1);
                memTypId = rs.getString(2);

                Debug.print("memTypId Retrieved is :" + memTypId);
                Debug.print("Amount Retrieved is :" + amt);

            }

            rs.close();

        } catch (SQLException e) {
            Debug.print("SQLException in selectNextYrPrice() in session bean :");
            e.printStackTrace();
        } catch (Exception e) {
            Debug.print("Exception in selectNextYrPrice() in session bean :");
            e.printStackTrace();
        } finally {
            releaseConnection();
        }
        String[] memDet = {memTypId, String.valueOf(amt)};
        return memDet;
    }

    /**
     * Name         :makeConnection
     * Description  :This method will Create a databacse connection
     * @ param      :
     * @return      :void
     * @throws      :EJBException
     */
    public int selectPriorityValue(String membershipTypId) throws RemoteException {
        int priority_value = 0;
        try {
            makeConnection();
            String sql = "select priority_value from " + DBHelper.USEA_MEMBER_TYPE_MASTER + " where membership_type_id = ?";

            prepStmt = con.prepareStatement(sql);
            prepStmt.setString(1, membershipTypId);

            rs = prepStmt.executeQuery();

            while (rs.next()) {
                priority_value = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            releaseConnection();
        }
        return priority_value;
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
