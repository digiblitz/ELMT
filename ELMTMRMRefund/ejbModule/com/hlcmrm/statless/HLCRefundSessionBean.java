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
package com.hlcmrm.statless;

import com.hlcmrm.refund.HLCRefundDetailsLocal;
import com.hlcmrm.refund.HLCRefundDetailsLocalHome;
import com.hlcmrm.util.HLCContactDetails;
import com.hlcmrm.util.DBHandler;
import com.hlcmrm.util.Debug;
import com.hlcmrm.util.HLCMembershipRefundDetails;
import com.hlcmrm.util.HLCMembershipRefundTypeDetails;
import com.hlcmrm.util.HLCUserMaster;
import javax.ejb.*;
import java.rmi.RemoteException;
import java.text.*;
import java.util.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;

/**
 * This is the bean class for the RefundSessionBean enterprise bean.
 * Created Aug 26, 2006 11:55:46 AM
 * @author harmohan
 */
public class HLCRefundSessionBean implements SessionBean, HLCRefundSessionRemoteBusiness {
    private SessionContext context;
    private InitialContext ic = null;
    private static final String dbName = "java:/ELMTMSSQLDS";
    private Connection con;
    private ResultSet rs= null;
    private PreparedStatement prepStmt = null;
    private ResultSet rs1= null;
    private PreparedStatement prepStmt1 = null;
    HLCRefundDetailsLocalHome home;
    HLCRefundDetailsLocal remote;
    
    private String refundTypeId;
    private String refundTypeName;
    private String refundDescription;
    private String memberId;
    
    private String userId;
    private String prefix;
    private String firstName;
    private String middleName;
    private String lastName;
    private String suite;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zip;
    private String phoneNo;
    private String mobileNo;
    private String emailId;
    private String loginName;
    
    private String refundId;
   // private String memberId;
    private String claimAmount;
    private String claimDate;
    private String refundAmount;
    private String refundDate;
    private String refundedBy;
    private String bankName;
    private String checkNumber; 
    private String checkDate;
    private String balanceAmount;
    private String comments;
    private String refundStatus;
    private String approval_status;
            
    String name = "ejb/HLCRefundEntityJNDI";
    
    public  HLCRefundSessionBean(){ }
    
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
        try{
            InitialContext jndiContext = getInitialContext();
            Object obj = jndiContext.lookup(name);
            home = (HLCRefundDetailsLocalHome)obj;
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
/**
  * Name         :addMembershipRefundDetail
  * Description  :This method will insert record into the Membership Refund Details table
  * @ param      :passing object of type of the class MembershipRefundDetails
  * @return      :void
  * @throws      :RemoteException, EJBException
  */      
    public void addMembershipRefundDetail(HLCMembershipRefundDetails objRefDetails) throws RemoteException {
        try{
             remote = home.create(objRefDetails);
        }
        catch(Exception exp){
             throw new EJBException("createMembershipRefundDetails : " + exp.getMessage());
        }
    }
/**
  * Name         :addMembershipRefundTypeDetail
  * Description  :This method will insert record into the Membership Refund Type Details table
  * @ param      :passing object of type of the class MembershipRefundTypeDetails
  * @return      :void
  * @throws      :RemoteException, EJBException
  */  
    public void addMembershipRefundTypeDetail(HLCMembershipRefundTypeDetails objRefTypeDetails) throws RemoteException {
        try{
             remote = home.create(objRefTypeDetails);
        }
        catch(Exception exp){
             throw new EJBException("createMembershipRefundDetails : " + exp.getMessage());
        }
    }
/**
  * Name         :displayRefundTypes
  * Description  :This method will Initialize the naming context for the container
  * @ param      :None
  * @return      :InitialContext
  * @throws      :NamingException
  */  
    public Vector displayRefundTypes() throws RemoteException, SQLException {
        Debug.print("Inside the Refund type details");
        
        Vector V = new Vector();
        Debug.print("DBHandler getRefundTypes");
        makeConnection();
        try {
        String selectStatement = " SELECT refund_type_id, refund_type_name, refund_description FROM "+ DBHandler.USEA_MEMBER_REFUNDTYPEMASTER;
        prepStmt = con.prepareStatement(selectStatement);
        rs = prepStmt.executeQuery();
            while (rs.next()){
                  this.refundTypeId = rs.getString(1);
                  this.refundTypeName = rs.getString(2);
                  this.refundDescription = rs.getString(3);
                  String refundType [] ={refundTypeId,refundTypeName,refundDescription };
                  V.add(refundType);
             }
        }catch (Exception e){
            releaseConnection();
            System.out.println("Exception In displayRefundTypes :  "+e);
        }
        releaseConnection();
        return V;
    }
/**
  * Name         :editRefundDetails
  * Description  :This method will retrieve the member details for refund details table
  * @ param      :None
  * @return      :member Identification number
  * @throws      :RemoteException, FinderException
  */     
     public void editRefundDetails(HLCMembershipRefundDetails objRefDetails) throws RemoteException, FinderException{
          // System.out.print("Member ID : "+objRefDetails.getMemberId());
       
         Debug.print("editRefundDetails() userId :"+objRefDetails.getUserId());
         
        if (memberExists(objRefDetails.getRefundId()) == false) {
            throw new EJBException("Horse Member ID Not Exists : " + refundId);
        }
        //refund_amount,refund_date,refunded_by,bank_name,check_number,check_date,balance_amount
           Debug.print("objRefDetails.getRefundId()"+objRefDetails.getRefundId());
           
      //  remote.setRefundId(objRefDetails.getRefundId());
        if (objRefDetails.getMemberId() != null){
             remote.setMemberId(objRefDetails.getMemberId());
        }
        if (objRefDetails.getRefundAmount() != 0){
             remote.setRefundAmount(objRefDetails.getRefundAmount());
        }
        if (objRefDetails.getRefundDate() != null){
             remote.setRefundDate(objRefDetails.getRefundDate());
        }
        if (objRefDetails.getRefundedBy() != null){
             remote.setRefundedBy(objRefDetails.getRefundedBy());
        }
        if (objRefDetails.getBankName() != null){
             remote.setBankName(objRefDetails.getBankName());
        }
        if (objRefDetails.getCheckNumber() != 0){
             remote.setCheckNumber(objRefDetails.getCheckNumber());
        }
        if (objRefDetails.getCheckDate() != null){
            remote.setCheckDate(objRefDetails.getCheckDate());
        }
         if (objRefDetails.getRefundStatus() != null){
             remote.setRefundStatus(objRefDetails.getRefundStatus());
        }
           
           if (objRefDetails.getUserId() != null){
             remote.setUserId(objRefDetails.getUserId());
        }
           
       // if (objRefDetails.getBalanceAmount() != 0){
             remote.setBalanceAmount(objRefDetails.getBalanceAmount());
       // }
       // remote.setCheckNumber(objRefDetails.getCheckNumber());
       // remote.setCheckDate(objRefDetails.getCheckDate());
       // remote.setBalanceAmount(objRefDetails.getBalanceAmount());
        //remote.setRefundStatus(objRefDetails.getRefundStatus());
     }
/**
  * Name         :memberExists
  * Description  :This method will check for the existing of memberId and call the findByPrimaryKey method
  * @ param      :None
  * @return      :boolean value
  * @throws      :none
  */         
   private boolean memberExists(String refundId) {
        Debug.print("Kavery Session Bean memberExists");

        if ( refundId!=this.refundId ) {
            try {
                 Debug.print("Kavery Session Bean inside cond ");
                remote = home.findByPrimaryKey(refundId);
                Debug.print("remote"+remote);
                
                this.refundId = refundId;
            } catch (Exception ex) {
                return false;
            }
        }
         Debug.print("outside cond ");
        return true;
    }
   
   public Vector getRefundDetailList() throws RemoteException, FinderException {
       Vector V = new Vector();
      /* Collection col = home.findByRefundList();
       ArrayList array = new ArrayList();
        String [] arr = new String[11];
      Vector vObj = new Vector(col);
                 
        try {
             Enumeration it = vObj.elements();
      
                while(it.hasMoreElements()){
                 array = (ArrayList)it.nextElement();
                 int k=0;
               //  String s[] = new String(array.size());
                     for (Iterator it1=array.iterator(); it1.hasNext( ); ) { 
                       // Object anObject = it.next( ); 
                        String str = String.valueOf(it1.next( )); 
                        arr[k++] = str;
                     } 
                    V.add(arr);
               
                 }*/
        HLCMembershipRefundDetails objRefDetails = new HLCMembershipRefundDetails();
       try {
           makeConnection();
             
           String selectStatement = "SELECT refund_id, member_id, claim_amount, claim_date, refund_amount, refund_date, refunded_by," +
                    "bank_name,check_number,check_date,balance_amount,comments,refund_status,user_id FROM  " +DBHandler.USEA_MEMBER_REFUNDDETAILS+" ORDER BY member_id ASC";
            prepStmt = con.prepareStatement(selectStatement);
           // prepStmt.setString(1, memberId);
             
            rs = prepStmt.executeQuery();
            while (rs.next()) {
                String s1 = rs.getString(1);System.out.print(" "+s1);
                String s2 = rs.getString(2);System.out.print(" "+s2);
                String s3 = rs.getString(3);System.out.print(" "+s3);
                String s4 = rs.getString(4); System.out.print(" "+s4);
                String s5 = rs.getString(5);System.out.print(" "+s5);
                String s6 = rs.getString(6);System.out.print(" "+s6);
                String s7 = rs.getString(7);System.out.print(" "+s7);
                String s8 = rs.getString(8);System.out.print(" "+s8);
                String s9 = rs.getString(9); System.out.print(" "+s9);
                String s10 = rs.getString(10);System.out.print(" "+s10);
                String s11= rs.getString(11); System.out.print(" "+s11);
                String s12= rs.getString(12); System.out.print(" "+s12);
                String s13= rs.getString(13); System.out.print(" "+s13);
                String s14= rs.getString(14); System.out.print("user id : "+s14);
                
                objRefDetails.setMemberId(rs.getString(2));
                objRefDetails.setClaimAmount(rs.getDouble(3));
                objRefDetails.setClaimDate(rs.getDate(4));
                objRefDetails.setRefundAmount(rs.getDouble(5));
                objRefDetails.setRefundDate(rs.getDate(6));
                objRefDetails.setRefundedBy(rs.getString(7));
                objRefDetails.setBankName(rs.getString(8));
                objRefDetails.setCheckNumber(rs.getInt(9));
                objRefDetails.setCheckDate(rs.getDate(10));
                objRefDetails.setBalanceAmount(rs.getDouble(11));
                objRefDetails.setComments(rs.getString(12));
                objRefDetails.setRefundStatus(rs.getString(13));
                
                String str [] = {s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14};
                V.add(str);
             } 
             releaseConnection();
         }catch (Exception e){
                     System.out.println(" "+e.getMessage());
         }
       return V;
   }
   
   public HLCMembershipRefundDetails  getRefundDetail(String refId) throws RemoteException, FinderException {
       HLCMembershipRefundDetails objRefDetails = new HLCMembershipRefundDetails();
       try {
           makeConnection();
           String selectStatement = "SELECT refund_id, member_id, claim_amount, claim_date, refund_amount, refund_date, refunded_by," +
                    "bank_name,check_number,check_date,balance_amount,comments,refund_status FROM  " +DBHandler.USEA_MEMBER_REFUNDDETAILS+
                    " WHERE refund_id = ? ";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, refId);
            rs = prepStmt.executeQuery();
            if(rs.next()) {
                objRefDetails.setMemberId(rs.getString(2));
                objRefDetails.setClaimAmount(rs.getDouble(3));
                objRefDetails.setClaimDate(rs.getDate(4));
                objRefDetails.setRefundAmount(rs.getDouble(5));
                objRefDetails.setRefundDate(rs.getDate(6));
                objRefDetails.setRefundedBy(rs.getString(7));
                objRefDetails.setBankName(rs.getString(8));
                objRefDetails.setCheckNumber(rs.getInt(9));
                objRefDetails.setCheckDate(rs.getDate(10));
                objRefDetails.setBalanceAmount(rs.getDouble(11));
                objRefDetails.setComments(rs.getString(12));
                objRefDetails.setRefundStatus(rs.getString(13));
             } 
             releaseConnection();
         }catch (Exception e){
                     System.out.println(" "+e.getMessage());
         }
       return objRefDetails;
   }
   
   
   public boolean getApproveBalance(String refundId) throws RemoteException {
       boolean bol = false;
       double spentAmt = 0;
       double balAmt = 0;
       
       try {
           makeConnection();
          // String selectString = "SELECT sum(A.refund_amount) as applied_amount, B.refund_amount FROM "+DBHandler.USEA_MEMBER_REFUNDTYPEDETAILS+" A, "+
          // DBHandler.USEA_MEMBER_REFUNDDETAILS+"  B  where A.refund_id = B.refund_id and A.refund_id = ? "; //'74260bfc-248f-4d19-a36d-1eecef5eda3f'";
           String selectString = "SELECT sum(refund_amount) as applied_amount FROM "+
                 DBHandler.USEA_MEMBER_REFUNDTYPEDETAILS+" WHERE refund_id = ?";
          
           prepStmt = con.prepareStatement(selectString);
           prepStmt.setString(1, refundId);
           rs = prepStmt.executeQuery();
           if (rs.next()) {
                spentAmt = rs.getDouble(1);
               // balAmt = rs.getDouble(2);
           }
           
           selectString = "SELECT refund_amount FROM "+DBHandler.USEA_MEMBER_REFUNDDETAILS+" WHERE refund_id = ?";
          
           prepStmt = con.prepareStatement(selectString);
           prepStmt.setString(1, refundId);
           rs = prepStmt.executeQuery();
           if (rs.next()) {
                //spentAmt = rs.getDouble(1);
               balAmt = rs.getDouble(1);
           }
           
           System.out.println(" spentAmt : "+spentAmt);
           System.out.println(" balAmt : "+balAmt);
           releaseConnection();
           if (spentAmt <= balAmt ){
               bol = true;
           }else {
               bol = false;
           }
           
       }catch(Exception e){
           Debug.print("Error in getApproveBalance : "+e.getMessage());
       }finally{
            releaseConnection();
       }
       return bol;
   }
   
   public String[] getBalanaceAmount(String emailId) throws RemoteException {
       String balAmount = null;
       try {
           makeConnection();
               try {
                    this.memberId = DBHandler.getMemberId( con, emailId);
                }catch (Exception e){
                    e.printStackTrace();
                }
           
           String selectStatement = "SELECT refund_id, member_id, claim_amount, claim_date, refund_amount, refund_date, refunded_by," +
                    "bank_name,check_number,check_date,balance_amount,comments,refund_status FROM  " +DBHandler.USEA_MEMBER_REFUNDDETAILS+" WHERE member_id = ?";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, memberId);
             
            rs = prepStmt.executeQuery();
            while (rs.next()) {
                refundId = rs.getString(1);
                memberId = rs.getString(2);
                claimAmount = rs.getString(3);
                claimDate = rs.getString(4); 
                refundAmount = rs.getString(5);
                refundDate = rs.getString(6);
                refundedBy = rs.getString(7);
                bankName = rs.getString(8);
                checkNumber = rs.getString(9); 
                checkDate = rs.getString(10);
                balanceAmount= rs.getString(11); 
                comments= rs.getString(12); 
                refundStatus = rs.getString(13);
            }
            
            
           releaseConnection();
       }catch (Exception e){
           e.printStackTrace();
       }
       
       String str [] = {refundId,memberId,claimAmount,claimDate,refundAmount,refundDate,refundedBy,bankName,checkNumber,checkDate,balanceAmount,comments,refundStatus };
       return str;
   }
   
   public Vector getRefundBasedOnStatus(String statusName) throws RemoteException {
       Vector vObj = new Vector();
       try {
           makeConnection();
               
           String selectStatement = "SELECT A.refund_id, A.member_id, A.claim_amount, A.claim_date, A.refund_amount, A.refund_date, A.refunded_by," +
                    "A.bank_name,A.check_number,A.check_date,A.balance_amount,A.comments,A.refund_status,B.first_name,B.last_name,B.email_id,B.user_id  FROM  " +DBHandler.USEA_MEMBER_REFUNDDETAILS+" A,"+
                   DBHandler.USEA_MMS_USERMASTER+" B "+
                      "  WHERE B.user_id = A.user_id and A.refund_status = ?";
                
                //   "  WHERE B.user_id = C.user_id and C.member_id = A.member_id and A.refund_status = ?";
                
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, statusName);
             
            rs = prepStmt.executeQuery();
            while (rs.next()) {
                refundId = rs.getString(1);
                memberId = rs.getString(2);
                claimAmount = rs.getString(3);
                claimDate = rs.getString(4); 
                refundAmount = rs.getString(5);
                refundDate = rs.getString(6);
                refundedBy = rs.getString(7);
                bankName = rs.getString(8);
                checkNumber = rs.getString(9); 
                checkDate = rs.getString(10);
                balanceAmount= rs.getString(11); 
                comments= rs.getString(12); 
                refundStatus = rs.getString(13);
                String firstName  =rs.getString(14);
                String lastName = rs.getString(15);
                String email = rs.getString(16);
                String userId= rs.getString(17);
                
                String str [] = {firstName,lastName,refundId,memberId,claimAmount,claimDate,refundAmount,refundDate,refundedBy,bankName,checkNumber,checkDate,balanceAmount,comments,refundStatus,email,userId };
                vObj.add(str);
            }
            
            
           releaseConnection();
       }catch (Exception e){
           e.printStackTrace();
       }
       
       
       return vObj;
   }
   
   public Vector getRefundDetailByUserId(String userId) throws RemoteException {
       Vector V = new Vector();
       String balAmount = null;
       try {
           makeConnection();
               
           String selectStatement = "SELECT refund_id, member_id, claim_amount, claim_date, refund_amount, refund_date, refunded_by," +
                    "bank_name,check_number,check_date,balance_amount,comments,refund_status, approval_status FROM  " +DBHandler.USEA_MEMBER_REFUNDDETAILS+" WHERE user_id = ?";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, userId);
            Debug.print("getRefundDetailByUserId :"+userId);
            
            rs = prepStmt.executeQuery();
            while (rs.next()) {
                refundId = rs.getString(1);
                memberId = rs.getString(2);
                claimAmount = rs.getString(3);
                claimDate = rs.getString(4); 
                refundAmount = rs.getString(5);
                refundDate = rs.getString(6);
                refundedBy = rs.getString(7);
                bankName = rs.getString(8);
                checkNumber = rs.getString(9); 
                checkDate = rs.getString(10);
                balanceAmount= rs.getString(11); 
                comments= rs.getString(12); 
                refundStatus = rs.getString(13);
                approval_status = rs.getString(14);
                
            String refStr [] = {refundId,memberId,claimAmount,claimDate,refundAmount,refundDate,refundedBy,bankName,checkNumber,checkDate,balanceAmount,comments,refundStatus,approval_status};
            V.add(refStr);
            }
           releaseConnection();
       }catch (Exception e){
           e.printStackTrace();
       }
       return V;
   }
   
   public Vector displayRefundTypeDetail(String refundId) throws RemoteException {
       Vector V = new Vector();
       String balAmount = null;
       String refTypName = null;
       try {
           makeConnection();
               
          // String selectStatement = "SELECT refund_detail_id,refund_id, refund_type_id, refund_amount FROM  " +DBHandler.USEA_MEMBER_REFUNDTYPEDETAILS+" WHERE refund_id = ?";
           String selectStatement = "SELECT A.refund_detail_id,A.refund_id, A.refund_type_id, A.refund_amount, B.refund_type_name FROM "+
           DBHandler.USEA_MEMBER_REFUNDTYPEDETAILS+" A, "+DBHandler.USEA_MEMBER_REFUNDTYPEMASTER+"  B  "+ 
           " WHERE  A.refund_type_id = B.refund_type_id AND A.refund_id = ? ";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, refundId);
             
            rs = prepStmt.executeQuery();
            while (rs.next()) {
                String refundDetailId = rs.getString(1);
                refundId = rs.getString(2);
                refundTypeId = rs.getString(3);
                refundAmount = rs.getString(4); 
                refTypName = rs.getString(5);
                System.out.println("refundDetailId : "+refundDetailId);
                System.out.println("refundId : "+refundId);
                System.out.println("refundTypeId : "+refundTypeId);
                System.out.println("refundAmount : "+refundAmount);
                System.out.println("refTypName : "+refTypName);

                String refTypDet [] = {refundDetailId, refundId, refTypName, refundAmount};
                V.add(refTypDet);
            }
           releaseConnection();
       }catch (Exception e){
           e.printStackTrace();
       }
       return V;
   }
   
/**
  * Name         :getRefundDetails
  * Description  :This method will retrieve the member details for refund details table
  * @ param      :None
  * @return      :member Identification number
  * @throws      :RemoteException, FinderException
  */     
    public Vector getRefundDetails(String memberId) throws RemoteException, FinderException {
        
        Vector A = new Vector();
        Vector col = home.findByMemberId(memberId);
       /*ArrayList array = new ArrayList(col);
        for (Iterator it=array.iterator(); it.hasNext( ); ) { 
           // Object anObject = it.next( ); 
            String str = String.valueOf(it.next( )); 
            String[] result = str.split(":");
             for (int x=1; x<result.length; x++){
                 System.out.println(result[x]);
                 A.add(result[1]);
             }

           //System.out.println( nonuseaOrgId); //anObject.toString() ); 
        }*/
        
        Enumeration it = col.elements();
        while(it.hasMoreElements()){
            String s = (String)it.nextElement();
            //for(int i=0;i<s.length; i++)
                System.out.println("   "+s);
                A.add(s);
        }
        
        
        //Collection c = home.findByMemberId(memberId);
        return A;
    }
    
    /**
  * Name         :memberDetails
  * Description  :This method will extract the member Details line Name, Address etc
  * @ param      :userId
  * @return      :Array of String
  * @throws      :SQLException
  */
    
    public Collection memberDetails(String memberId) throws SQLException, RemoteException {
        HLCContactDetails objContact = new HLCContactDetails();
        HLCRefundSessionBean objRef = new HLCRefundSessionBean();
        Vector V = new Vector();
        ArrayList arr = new ArrayList();
        boolean bol = false;
        this.memberId = memberId;
        try {
            makeConnection();
            String selectStatement = "SELECT user_id FROM "+DBHandler.USEA_MMS_MEMBERDETAIL +" WHERE member_id = ? ";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, memberId);
            rs = prepStmt.executeQuery();
            if (rs.next()){
                this.userId = rs.getString(1);
            }
            System.out.println("User ID : "+userId+"    Member ID : "+memberId);
            HLCUserMaster objUser = (HLCUserMaster)contactDetails (userId);
            arr.add(objUser);
            System.out.print("objUser : "+objUser.getFirstName());
            
            /*String contactName [] = contactDetails (userId);
            this.userId = contactName[0];
            this.prefix = contactName[1];
            this.firstName = contactName[2];
            this.middleName = contactName[3];
            this.lastName = contactName[4];*/

             selectStatement = "SELECT suite,address1,address2,city,state,zip,phone_no, mobile_no FROM " +
                    DBHandler.USEA_CONTACT_DETAILS+" WHERE user_id = ?";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, userId);
            rs = prepStmt.executeQuery();
            System.out.println("Inside the loadContactDetails User Id : "+userId);
            if (rs.next()) {
            this.suite = rs.getString(1);     objContact.setSuite(rs.getString(1));
            this.address1 = rs.getString(2);  objContact.setAddress1(rs.getString(2));
            this.address2 = rs.getString(3);  objContact.setAddress2(rs.getString(3));
            this.city = rs.getString(4);      objContact.setCity(rs.getString(4));
            this.state = rs.getString(5);     objContact.setState(rs.getString(5));
            this.zip = rs.getString(6);       objContact.setZip(rs.getString(6));
            this.phoneNo = rs.getString(7);   objContact.setPhoneNo(rs.getString(7));
            this.mobileNo = rs.getString(8);  objContact.setMobileNo(rs.getString(8));
            }
            arr.add(objContact);
            System.out.print("objContact : "+objContact.getAddress1());
            releaseConnection();
        }catch (Exception e){
            //e.printStackTrace();
            System.out.println("Error While getting owner and rider details  : "+e.getMessage());
        }
         //String memberDetail [] =  {prefix, firstName, middleName, lastName, suite, address1, address2, city, state, zip, phoneNo, mobileNo, emailId };
         //arr.add(memberDetail);
         return arr;
    }
    
    public Collection memberDetailsOnUserId(String userId) throws SQLException, RemoteException {
        HLCContactDetails objContact = new HLCContactDetails();
        HLCRefundSessionBean objRef = new HLCRefundSessionBean();
        Vector V = new Vector();
        ArrayList arr = new ArrayList();
        boolean bol = false;
        this.memberId = memberId;
        try {
            makeConnection();
            
            this.userId = userId;
                    
            System.out.println("User ID : "+userId );
            HLCUserMaster objUser = (HLCUserMaster)contactDetails (userId);
            arr.add(objUser);
            System.out.print("objUser : "+objUser.getFirstName());
           
             String selectStatement = "SELECT suite,address1,address2,city,state,zip,phone_no, mobile_no FROM " +
                    DBHandler.USEA_CONTACT_DETAILS+" WHERE user_id = ?";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, userId);
            rs = prepStmt.executeQuery();
            System.out.println("Inside the loadContactDetails User Id : "+userId);
            if (rs.next()) {
            this.suite = rs.getString(1);     objContact.setSuite(rs.getString(1));
            this.address1 = rs.getString(2);  objContact.setAddress1(rs.getString(2));
            this.address2 = rs.getString(3);  objContact.setAddress2(rs.getString(3));
            this.city = rs.getString(4);      objContact.setCity(rs.getString(4));
            this.state = rs.getString(5);     objContact.setState(rs.getString(5));
            this.zip = rs.getString(6);       objContact.setZip(rs.getString(6));
            this.phoneNo = rs.getString(7);   objContact.setPhoneNo(rs.getString(7));
            this.mobileNo = rs.getString(8);  objContact.setMobileNo(rs.getString(8));
            }
            arr.add(objContact);
            System.out.print("objContact : "+objContact.getAddress1());
            releaseConnection();
        }catch (Exception e){
            //e.printStackTrace();
            System.out.println("Error While getting owner and rider details  : "+e.getMessage());
        }
         //String memberDetail [] =  {prefix, firstName, middleName, lastName, suite, address1, address2, city, state, zip, phoneNo, mobileNo, emailId };
         //arr.add(memberDetail);
         return arr;
    }
    
    public HLCUserMaster contactDetails (String userId)  {
        HLCUserMaster objUser = new HLCUserMaster();
        try {
        String selectStatement = "SELECT prefix,first_name,middle_name,last_name,email_id FROM " +
                DBHandler.USEA_MMS_USERMASTER+" WHERE user_id = ?";
        prepStmt = con.prepareStatement(selectStatement);
        prepStmt.setString(1, userId);
        rs = prepStmt.executeQuery();
        System.out.println("Inside the loadUserDetails User Id : "+userId);
        if (rs.next()) {
        this.prefix = rs.getString(1);     objUser.setPrefix(rs.getString(1));
        this.firstName = rs.getString(2);  objUser.setFirstName(rs.getString(2));
        this.middleName = rs.getString(3); objUser.setMiddleName(rs.getString(3));
        this.lastName = rs.getString(4);   objUser.setLastName(rs.getString(4));
        this.emailId = rs.getString(5);    objUser.setEmailId(rs.getString(5));
        }
       // String contactName [] = {userId,prefix,firstName,middleName,lastName };
       // return contactName;
        }catch (Exception e){
            System.out.println("Error while getting  Contact Details : "+e);
        }
       // String contactName [] = {userId,prefix,firstName,middleName,lastName };
        return objUser;
    }
    
    /**
  * Name         :listExcessPayment
  * Description  :This method will list Excess Payment details
  * @ param      :String
  * @return      :ArrayList
  * @throws      :RemoteException, SQLException
  */  
    
    public ArrayList listExcessPayment(String status) throws RemoteException,SQLException {
       ArrayList excessPayDetails = new ArrayList();
       
       Debug.print("status in RefundSessionBean.listExcessPayment() :"+status);
       
       try {
           makeConnection();
               
           String selectStatement = "select A.refund_id, A.balance_amount, B.first_name, B.last_name, C.member_id, " +
                   "A.claim_date from "+DBHandler.USEA_MEMBER_REFUNDDETAILS+" A, "+DBHandler.USEA_MMS_USERMASTER+" B,"+
                   DBHandler.USEA_MMS_MEMBERDETAIL+" C where A.claim_amount is NULL and A.approval_status = ? and " +
                   "A.refund_id not in (select refund_id from tblMembershipRefundTypeDetails) and  A.user_id = B.user_id " +
                   "and A.user_id = C.user_id order by A.claim_date desc";
            
           Debug.print("select Statement :"+selectStatement);
           
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,status);
            
            rs = prepStmt.executeQuery();
            while (rs.next()) {
                HLCMembershipRefundDetails refDetail = new HLCMembershipRefundDetails();
                
                refDetail.setRefundId(rs.getString(1));
                refDetail.setBalanceAmount(rs.getDouble(2));
                refDetail.setFirstName(rs.getString(3));
                refDetail.setLastName(rs.getString(4)); 
                refDetail.setMemberId(rs.getString(5));
                refDetail.setClaimDate(rs.getDate(6));
                
                excessPayDetails.add(refDetail);
            }
           
       }catch (SQLException ex){
           Debug.print("Sql Exception in listExcessPayment() :");
           ex.printStackTrace();
           
       }
       catch (Exception e){
           Debug.print("General Exception in listExcessPayment() :");
           e.printStackTrace();
       }
       finally
       {
            releaseConnection();
       }
       
       return excessPayDetails;
   }
    
    /**
  * Name         :getExcessPaymentDetails
  * Description  :This method will get Excess Payment Details
  * @ param      :String
  * @return      :MembershipRefundDetails
  * @throws      :RemoteException, SQLException
  */  
    
    public HLCMembershipRefundDetails  getExcessPaymentDetails(String refId) throws RemoteException, SQLException {
       HLCMembershipRefundDetails objRefDetails = new HLCMembershipRefundDetails();
       Debug.print("refund id in getExcessPaymentDetails() :"+refId);
       
       try {
           makeConnection();
           String selectStatement = "SELECT A.refund_id, C.member_id, A.claim_amount, A.claim_date, A.refund_amount, A.refund_date, A.refunded_by," +
                    "A.bank_name, A.check_number, A.check_date, A.balance_amount, A.comments, A.refund_status, B.first_name, B.last_name, B.email_id FROM " +DBHandler.USEA_MEMBER_REFUNDDETAILS+
                    " A, "+DBHandler.USEA_MMS_USERMASTER+" B, "+DBHandler.USEA_MMS_MEMBERDETAIL+" C WHERE refund_id = ? and A.user_id = B.user_id and A.user_id = C.user_id";
           
           Debug.print("selectStatement :"+selectStatement);
           
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, refId);
            rs = prepStmt.executeQuery();
            if(rs.next()) {
                objRefDetails.setRefundId(rs.getString(1));
                objRefDetails.setMemberId(rs.getString(2));
                objRefDetails.setClaimAmount(rs.getDouble(3));
                objRefDetails.setClaimDate(rs.getDate(4));
                objRefDetails.setRefundAmount(rs.getDouble(5));
                objRefDetails.setRefundDate(rs.getDate(6));
                objRefDetails.setRefundedBy(rs.getString(7));
                objRefDetails.setBankName(rs.getString(8));
                objRefDetails.setCheckNumber(rs.getInt(9));
                objRefDetails.setCheckDate(rs.getDate(10));
                objRefDetails.setBalanceAmount(rs.getDouble(11));
                objRefDetails.setComments(rs.getString(12));
                objRefDetails.setRefundStatus(rs.getString(13));                
                objRefDetails.setFirstName(rs.getString(14));
                objRefDetails.setLastName(rs.getString(15)); 
                objRefDetails.setMailId(rs.getString(16));
             } 
             
         }catch (SQLException e){
             Debug.print("SQLException in getExcessPaymentDetails() :");
             Debug.print(e.getMessage());
         }
            catch (Exception ex){
             Debug.print("General Exception in getExcessPaymentDetails() :");
             Debug.print(ex.getMessage());
         }
           finally
           {
                releaseConnection();
           }
           
       return objRefDetails;
   }
    
    /**
  * Name         :updateRefundDetails
  * Description  :This method will UPDATE a ROW into the Membership Refund Details table
  * @ param      :MembershipRefundDetails
  * @return      :boolean
  * @throws      :RemoteException,SQLException
  */     
    
    public boolean updateRefundDetails(HLCMembershipRefundDetails memRefundDet) throws RemoteException,SQLException {
        Debug.print("Inside the updateRefundDetails :");
        Debug.print("memRefundDet.toString() :"+memRefundDet.toString());
        
        boolean bol = false;
        
      try {
            makeConnection();
            String updateStatement ="update " + DBHandler.USEA_MEMBER_REFUNDDETAILS + " set comments = ?, refund_date = ?, "
                                    +"refunded_by = ?, bank_name = ?, check_number = ?, check_date = ?, approval_status = 'Approved' where refund_id = ?";
            Debug.print("update Statement :"+updateStatement);
            
            PreparedStatement prepStmt = con.prepareStatement(updateStatement);
            
            prepStmt.setString(1, memRefundDet.getComments());
            if(memRefundDet.getRefundDate()!=null)
            {
                prepStmt.setDate(2, DBHandler.toSQLDate(memRefundDet.getRefundDate()));
            }
            else
            {
                prepStmt.setDate(2, null);
            }
            prepStmt.setString(3, memRefundDet.getRefundedBy());
            prepStmt.setString(4, memRefundDet.getBankName());
            prepStmt.setInt(5, memRefundDet.getCheckNumber());
            if(memRefundDet.getCheckDate()!=null)
            {
                prepStmt.setDate(6, DBHandler.toSQLDate(memRefundDet.getCheckDate()));
            }
            else
            {
                prepStmt.setDate(6, null);
            }
            prepStmt.setString(7, memRefundDet.getRefundId());
            
            int rowCount = prepStmt.executeUpdate();
            Debug.print("update Refund Details Sucessfully Updated." + rowCount);
            if(rowCount>0)
                bol = true;
                        
            prepStmt.close();
            
      }
      
      catch (SQLException e){
          Debug.print("SQLException in updateRefundDetails() :");
          e.printStackTrace();
      }
        catch (Exception e){
          Debug.print("Exception in updateRefundDetails() :");
          e.printStackTrace();
      }
        finally
        {
            releaseConnection();
        }
        return bol;
    }
    
/**
  * Name         :getInitialContext
  * Description  :This method will Initialize the naming context for the container
  * @ param      :None
  * @return      :InitialContext
  * @throws      :NamingException
  */  
     public InitialContext getInitialContext() throws javax.naming.NamingException {
        if( this.ic == null ) {
            ic = new InitialContext();
        }
        System.out.println("This is from getInitialContext()");
        return ic;
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
