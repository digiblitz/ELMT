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
package com.hlcmrm.refund;

import com.hlcmrm.util.DBHandler;
import com.hlcmrm.util.Debug;
import com.hlcmrm.util.HLCMembershipRefundDetails;
import com.hlcmrm.util.HLCMembershipRefundTypeDetails;
import com.hlcmrm.util.HLCMembershipRefundTypeMaster;
import javax.ejb.*;
import java.sql.*;
import javax.sql.*;
import java.util.*;
import java.util.Date;
import javax.naming.*;

/**
 * This is the bean class for the RefundDetailsBean enterprise bean.
 * Created Aug 25, 2006 11:44:07 PM
 * @author harmohan
 */
public class HLCRefundDetailsBean implements EntityBean, HLCRefundDetailsLocalBusiness {
    private static final String dbName = "java:/ELMTMSSQLDS";
    private Connection con = null;
    private PreparedStatement prepStmt = null;
    private ResultSet rs = null;
    private EntityContext context;
    
    /**===================Membership Refund Details Variable====================*/
   
    private double claimAmount;
    private Date claimDate;
    private double refundAmount;
    private Date refundDate;
    private String refundedBy;
    private String bankName;
    private int checkNumber;
    private Date checkDate;
    private double balanceAmount;
    private String mailId;
    private String refundStatus;
    private String loginName;
    
    // added by Ravikumar.G
    private String comments;
    
    /**==================Membership Refund Type Details Variable===============*/
    private String refundId;
    private String refundTypeId;
   // private double refundAmount;
    
    /**==================Membership Refund Type Master Variable=================*/
    private String refundTypeName;
    private String refundDescription;
    
    private String memberId;
    private String userId;
    
    /**===============Object Creation==========================================*/
    
    HLCMembershipRefundTypeMaster objRefTypeMaster = new HLCMembershipRefundTypeMaster();
    HLCMembershipRefundDetails objRefDetails = new HLCMembershipRefundDetails();
    HLCMembershipRefundTypeDetails objRefTypeDetails = new HLCMembershipRefundTypeDetails();
    
    public HLCMembershipRefundDetails getMembershipRefundDetails(){
        Debug.print("MembershipRefundDetails getMembershipRefundDetails");  
        return new HLCMembershipRefundDetails(refundId, claimAmount, claimDate, refundAmount, refundStatus,
            refundDate, refundedBy, bankName, checkNumber, checkDate, balanceAmount, mailId, userId);
        
    }
    public HLCMembershipRefundTypeMaster getMembershipRefundTypeMaster(){
        Debug.print("MembershipRefundTypeMaster getMembershipRefundTypeMaster");  
        return objRefTypeMaster;
    }
    public HLCMembershipRefundTypeDetails getMembershipRefundTypeDetails() {
        Debug.print("MembershipRefundTypeDetails getMembershipRefundTypeDetails");
        return objRefTypeDetails;
    }
    
    /*===================Setter Method =========================================*/
    public void setRefundStatus(String refundStatus){this.refundStatus = refundStatus; }
    public void setMemberId(String memberId){this.memberId = memberId; }
    public void setClaimAmount(double claimAmount) { this.claimAmount = claimAmount; }
    public void setClaimDate(Date claimDate) {this.claimDate = claimDate; }
    public void setRefundAmount(double refundAmount) { this.refundAmount = refundAmount; }
    public void setRefundDate(Date refundDate) {this.refundDate = refundDate; }
    public void setRefundedBy(String refundedBy) {this.refundedBy = refundedBy; }
    public void setBankName(String bankName) {this.bankName = bankName; }
    public void setCheckNumber(int checkNumber) {this.checkNumber = checkNumber; }
    public void setCheckDate(Date checkDate) {this.checkDate = checkDate; }
    public void setBalanceAmount(double balanceAmount) {this.balanceAmount = balanceAmount;}
    public void setMailId(String mailId){this.mailId = mailId; }
    public void setLoginName(String loginName) {this.loginName = loginName;}

    public void setRefundId(String refundId) {this.refundId = refundId; }
    public void setRefundTypeId(String refundTypeId) {this.refundTypeId = refundTypeId;}
   // public void setRefundAmount(double refundAmount) {this.refundAmount = refundAmount;}
    
    public void setRefundDescription(String refundDescription){this.refundDescription = refundDescription;}
    public void setRefundTypeName(String refundTypeName){this.refundTypeName = refundTypeName;}
    
    // added by Ravikumar.G
    public void setComments(String comments){this.comments = comments;}
    
    public void setUserId(String userId){this.userId = userId;}
    
   /* public String getRefundId(){return refundId; };
    public String getMemberId(){return memberId;};
    public double getClaimAmount() { return claimAmount; }
    public Date getClaimDate(){ return claimDate; }
    public double getRefundAmount() {return refundAmount;}
    public Date getRefundDate(){return refundDate; }
    public String getRefundedBy() {return refundedBy; }
    public String getBankName(){return bankName;}
    public int getCheckNumber() {return checkNumber; }
    public Date getCheckDate(){return checkDate;}
    public double getBalanceAmount(){return balanceAmount;}
    public String getMailId(){return mailId; }*/
    
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
        try {
            loadRefundDetails();
            System.out.println("ejbLoad for loadRefundDetails");
        } catch (Exception ex) {
           throw new EJBException("ejbLoad: " + ex.getMessage());
           // ex.printStackTrace();
        }
    }
    
    /**
     * @see javax.ejb.EntityBean#ejbStore()
     */
    public void ejbStore() {
        try {
            System.out.println("ejbStore for ejbStore");
            storeRefundDetails();
        } catch (Exception ex) {
           throw new EJBException("ejbStore: " + ex.getMessage());
           // ex.printStackTrace();
        }
    }
    
    // </editor-fold>
    
    public String ejbCreate(HLCMembershipRefundDetails objRefDetails) throws CreateException {
         
        Debug.print("MembershipRefundDetails ejbCreate"); 
        if(objRefDetails==null){
                throw new EJBException("ejbCreate: objRefDetails argument is null or empty");
        }
        
        this.claimAmount = objRefDetails.getClaimAmount();
        this.claimDate = objRefDetails.getClaimDate();
        this.refundAmount = objRefDetails.getRefundAmount();
        this.refundDate = objRefDetails.getRefundDate();
        this.refundedBy = objRefDetails.getRefundedBy();
        this.bankName = objRefDetails.getBankName();
        this.checkNumber = objRefDetails.getCheckNumber();
        this.checkDate = objRefDetails.getCheckDate();
        this.balanceAmount = objRefDetails.getBalanceAmount();
        this.mailId = objRefDetails.getMailId();
        this.comments = objRefDetails.getComments();
        this.loginName = objRefDetails.getLoginName();
        this.userId = objRefDetails.getUserId();
        
       // this.objRefTypeMaster = objRefTypeMaster;
       // this.objRefTypeDetails = objRefTypeDetails;
        
        // added by Ravikumar.G
        this.comments = objRefDetails.getComments();
        
        try {
              insertRowMembershipRefundDetails();
             // insertRowMembershipRefundTypeMaster();
             // insertRowMembershipRefundTypeDetails();
        } catch (Exception ex) {
            throw new EJBException("ejbCreate: MembershipRefundDetails  --- " + ex.getMessage());
        }
        
        return null;
    }
    
     public void ejbPostCreate(HLCMembershipRefundDetails objRefDetails) {
        Debug.print("MembershipRefundDetails ejbPostCreate"); 
    }
     
    public String ejbCreate(HLCMembershipRefundTypeDetails objRefTypeDetails) throws CreateException {
         
        Debug.print("MembershipRefundTypeDetails ejbCreate"); 
        if(objRefTypeDetails==null){
                throw new EJBException("ejbCreate: objRefTypeDetails argument is null or empty");
        }
        this.refundId = objRefTypeDetails.getRefundId();
        this.refundTypeId = objRefTypeDetails.getRefundTypeId();
        this.refundAmount = objRefTypeDetails.getRefundAmount();
        this.mailId = objRefTypeDetails.getMailId();
        
       // this.objRefTypeMaster = objRefTypeMaster;
       // this.objRefTypeDetails = objRefTypeDetails;
        
        try {
             insertRowMembershipRefundTypeMaster();
        } catch (Exception ex) {
            throw new EJBException("ejbCreate: RowMembershipRefundTypeMaster  --- " + ex.getMessage());
        }
        
        return null;
    }
    
     public void ejbPostCreate(HLCMembershipRefundTypeDetails objRefTypeDetails) {
        Debug.print("MembershipRefundTypeDetails ejbPostCreate"); 
    }
    /**
     * See EJB 2.0 and EJB 2.1 section 12.2.5
     */
    public String ejbFindByPrimaryKey(String refundId) throws FinderException {
        String result;
        try {
            result = selectByPrimaryKey(refundId);
        } catch (Exception ex) {
            throw new EJBException("ejbFindByPrimaryKey: " + ex.getMessage());
        }
        
         return result;
    }
    
    public Collection ejbFindByRefundList() throws ObjectNotFoundException {
        this.memberId =memberId;
        ArrayList array = new ArrayList();
        HLCMembershipRefundDetails objRefDetails = new HLCMembershipRefundDetails();
        Vector V = new Vector();
        try {
            
           makeConnection();
            
            String selectStatement = "SELECT refund_id, member_id, claim_amount, claim_date, refund_amount, refund_date, refunded_by," +
                    "bank_name,check_number,check_date,balance_amount FROM  " +DBHandler.USEA_MEMBER_REFUNDDETAILS;
            prepStmt = con.prepareStatement(selectStatement);
           // prepStmt.setString(1, memberId);
             
            rs = prepStmt.executeQuery();
            while (rs.next()) {
                array.add(rs.getString(1));
                array.add(rs.getString(2));     objRefDetails.setMemberId(rs.getString(2));
                array.add(rs.getString(3));     objRefDetails.setClaimAmount(rs.getDouble(3));
                array.add(rs.getString(4));     objRefDetails.setClaimDate(rs.getDate(4));
                array.add(rs.getString(5));     objRefDetails.setRefundAmount(rs.getDouble(5));
                array.add(rs.getString(6));     objRefDetails.setRefundDate(rs.getDate(6));
                array.add(rs.getString(7));     objRefDetails.setRefundedBy(rs.getString(7));
                array.add(rs.getString(8));     objRefDetails.setBankName(rs.getString(8));
                array.add(rs.getString(9));     objRefDetails.setCheckNumber(rs.getInt(9));
                array.add(rs.getString(10));    objRefDetails.setCheckDate(rs.getDate(10));
                array.add(rs.getString(11));    objRefDetails.setBalanceAmount(rs.getDouble(11));
                V.add(objRefDetails);
             } 
             prepStmt.close();
        } catch (SQLException sqe) {
            throw new EJBException(sqe);
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        
        return V;
    
    }
    
    public Vector ejbFindByMemberId(String memberId) throws ObjectNotFoundException {
        this.memberId =memberId;
        Vector array = new Vector();
        try {
            
           makeConnection();
            
            String selectStatement = "SELECT refund_id, member_id, claim_amount, claim_date, refund_amount, refund_date, refunded_by," +
                    "bank_name,check_number,check_date,balance_amount,refund_status FROM  " +DBHandler.USEA_MEMBER_REFUNDDETAILS +
                    "  WHERE member_id = ?";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, memberId);
             
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                array.add(rs.getString(1)); System.out.println("refund_id  "+rs.getString(1));
                array.add(rs.getString(2));System.out.println("member_id : "+rs.getString(2));
                array.add(rs.getString(3));System.out.println("claim_amount: "+rs.getString(3));
                array.add(rs.getString(4)); System.out.println("claim_date : "+rs.getString(4));
                array.add(rs.getString(5));System.out.println("refund_amount : "+rs.getString(5));
                array.add(rs.getString(6));System.out.println("refund_date"+rs.getString(6));
                array.add(rs.getString(7));System.out.println(rs.getString(7));
                array.add(rs.getString(8));System.out.println(rs.getString(8));
                array.add(rs.getString(9)); System.out.println(rs.getString(9));
                array.add(rs.getString(10));System.out.println(rs.getString(10));
                array.add(rs.getString(11)); System.out.println(rs.getString(11));
                array.add(rs.getString(12));System.out.println(rs.getString(12));
                    
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
    
    private String selectByPrimaryKey(String refundId) throws SQLException {
    Debug.print("RefundDetailsBean selectByPrimaryKey");
    boolean result = false;
         try {
            makeConnection();
            String selectStatement = "SELECT refund_id from " + DBHandler.USEA_MEMBER_REFUNDDETAILS + " WHERE refund_id = ?";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, refundId);
            ResultSet rs = prepStmt.executeQuery();
            if( rs.next()) {
                this.refundId = rs.getString(1);
            }
            Debug.print("Refund Id in selectByPrimaryKey: "+refundId);
            prepStmt.close();
            releaseConnection();
         }catch (Exception e){
             e.printStackTrace();
         }finally {
             releaseConnection();
         }
            return refundId;
    }
    
/**
  * Name         :loadHorseMemberDetails
  * Description  :This method will Display the content from the databse 
  * @ param      :none
  * @return      :void
  * @throws      :SQLException
  */  
    private void loadRefundDetails() throws SQLException {
        Debug.print("RefundDetailsBean loadRefundDetails");
        
        this.refundId = (String) context.getPrimaryKey();
        Debug.print("Refund Id in loadRefundDetails: "+refundId);
        try {
        makeConnection();
        String selectStatement = "SELECT * FROM " +DBHandler.USEA_MEMBER_REFUNDDETAILS+" WHERE refund_id = ?";
        PreparedStatement prepStmt = con.prepareStatement(selectStatement);
        prepStmt.setString(1, refundId);
        ResultSet rs = prepStmt.executeQuery();
        
         if (rs.next()) {
                this.refundId = rs.getString(1);
                this.memberId = rs.getString(2);
                this.userId = rs.getString(3);
                this.claimAmount = rs.getDouble(4);
                this.claimDate = rs.getDate(5); 
                this.comments = rs.getString(6);
                this.refundAmount = rs.getDouble(7);
                
                this.refundDate = rs.getDate(8);
                this.refundedBy = rs.getString(9);
                this.bankName = rs.getString(10);
                this.checkNumber = rs.getInt(11); 
                this.checkDate = rs.getDate(12);
                this.balanceAmount = rs.getDouble(13); 
                this.refundStatus = rs.getString(14);
                    
             } 
             prepStmt.close();
             releaseConnection();
        } catch (SQLException sqe) {
            throw new EJBException(sqe);
        } finally {
            releaseConnection();
        }
    }    
/**
  * Name         :storeHorseMemberDetails
  * Description  :This method will UPDATE a ROW into the Database 
  * @ param      :horseMemberId
  * @return      :void
  * @throws      :SQLException
  */          
    
    private void storeRefundDetails() throws SQLException {
        Debug.print("RefundDetailsBean storeRefundDetails");
        java.sql.Date jsqlD =  new java.sql.Date( new Date().getTime() );
       // horseId = (String) context.getPrimhorseIdaryKey();
      
        makeConnection();
        String updateStatement =
                "update " + DBHandler.USEA_MEMBER_REFUNDDETAILS  + " set refund_amount = ? , refund_date = ? , refunded_by = ? , bank_name = ? , " +
                "check_number = ? , check_date = ? ,balance_amount = ?, refund_status = ?  where refund_id = ?";
            //refund_amount,refund_date,refunded_by,bank_name,check_number,check_date,balance_amount
        PreparedStatement prepStmt = con.prepareStatement(updateStatement);
        prepStmt.setDouble(1, refundAmount);
         Debug.print("RefundDetailsBean refundAmount : "+refundAmount);
         
         if (refundDate == null)
            prepStmt.setDate(2, null);
         else         
            prepStmt.setDate(2, DBHandler.toSQLDate(refundDate));
         
        Debug.print("RefundDetailsBean refundDate : "+refundDate);
        
        prepStmt.setString(3, refundedBy);
        Debug.print("RefundDetailsBean refundedBy : "+refundedBy);
        prepStmt.setString(4, bankName);
        Debug.print("RefundDetailsBean bankName : "+bankName);
        prepStmt.setInt(5, checkNumber);
         Debug.print("RefundDetailsBean checkNumber : "+checkNumber);
        if (checkDate == null)
            prepStmt.setDate(6, null);
        else
            prepStmt.setDate(6, DBHandler.toSQLDate(checkDate));
        Debug.print("RefundDetailsBean checkDate : "+checkDate);
        prepStmt.setDouble(7, balanceAmount);
        Debug.print("RefundDetailsBean balanceAmount : "+balanceAmount);
        prepStmt.setString(8,refundStatus);
      
        Debug.print("RefundDetailsBean refundStatus : "+refundStatus);
        prepStmt.setString(9, refundId);
        Debug.print("RefundDetailsBean refundId : "+refundId);
        
        int rowCount = prepStmt.executeUpdate();
        Debug.print("Sucessfully Updated." + rowCount);
        prepStmt.close();
        releaseConnection();
    }    
/**
  * Name         :insertRowMembershipRefundDetails
  * Description  :This method will insert record into the Membership Refund Details table
  * @ param      :
  * @return      :void
  * @throws      :SQLException
  */  
    private void insertRowMembershipRefundDetails() throws SQLException {
        Debug.print("insertRowMembershipRefundDetails");
        java.sql.Date dt = java.sql.Date.valueOf("2007-11-30");
       
        makeConnection();
     try {
            this.memberId = DBHandler.getMemberId( con, loginName);
        }catch (Exception e){
            e.printStackTrace();
        }
        //System.out.println("Member ID :  "+memberId);
        
        Debug.print("insertRowMembershipRefundDetails userId :"+userId);
        
        String insertStatement = "insert into " + DBHandler.USEA_MEMBER_REFUNDDETAILS + " (user_id, " +
                "claim_amount,claim_date,comments) values (?, ? , ? , ? )"; //, ? , ? , ? , ? , ? , ? , ?) ";
        //,refund_amount,refund_date,refunded_by,bank_name,check_number,check_date,balance_amount

        prepStmt = con.prepareStatement(insertStatement);
        prepStmt.setString(1, userId);
        prepStmt.setDouble(2, claimAmount);
        prepStmt.setDate(3, DBHandler.toSQLDate(claimDate));
        prepStmt.setString(4, comments);
       // prepStmt.setDate(5, DBHandler.toSQLDate(refundDate));
       // prepStmt.setString(6, refundedBy);
       // prepStmt.setString(7, bankName);
       // prepStmt.setInt(8, checkNumber);
       // prepStmt.setDate(9, DBHandler.toSQLDate(checkDate));
       // prepStmt.setDouble(10, balanceAmount);
       // prepStmt.setDate(14, DBHelper.toSQLDate("2007-11-30"));
        
        prepStmt.executeUpdate();

        prepStmt.close();
        releaseConnection();
       
       
        }

/**
  * Name         :insertRowMembershipRefundDetails
  * Description  :This method will insert record into the Membership Refund Details table
  * @ param      :
  * @return      :void
  * @throws      :SQLException
  */  
    private void insertRowMembershipRefundTypeMaster() throws SQLException {
        Debug.print("insertRowMembershipRefundTypeMaster");
        java.sql.Date dt = java.sql.Date.valueOf("2007-11-30");
       
        makeConnection();
       /* try {
            this.refundId = DBHandler.getRefundId(con, mailId);
        }catch (Exception e){
            e.printStackTrace();
        }*/
        System.out.println("Refund ID :  "+refundId);

        String insertStatement = "insert into " + DBHandler.USEA_MEMBER_REFUNDTYPEDETAILS + 
                " (refund_id, refund_type_id, refund_amount) values ( ? , ? , ?) ";

        prepStmt = con.prepareStatement(insertStatement);
        prepStmt.setString(1, refundId);
        prepStmt.setString(2, refundTypeId);
        prepStmt.setDouble(3, refundAmount);
        
       System.out.println("Succesfully Inserted into membershipRefundTypeDetails");
        
        prepStmt.executeUpdate();

        prepStmt.close();
        releaseConnection();
       
       
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
