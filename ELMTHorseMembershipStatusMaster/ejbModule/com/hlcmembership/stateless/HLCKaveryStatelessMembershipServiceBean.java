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
package com.hlcmembership.stateless;

import com.hlchorse.member.status.HLCArabianSeaHorseMembershipStatusLocal;
import com.hlchorse.member.status.HLCArabianSeaHorseMembershipStatusLocalHome;
import com.hlchorse.member.util.DBHelper;
import com.hlchorse.member.util.Debug;
import com.hlchorse.member.util.HLCMembershipStatusMaster;
import javax.ejb.*;
import java.rmi.RemoteException;
import javax.naming.*;
import java.sql.*;
import javax.sql.*;
import java.util.*;
import java.util.Date;


/**
 * This is the bean class for the KaveryStatelessMembershipServiceBean enterprise bean.
 * Created Aug 29, 2006 7:15:17 PM
 * @author harmohan
 */
public class HLCKaveryStatelessMembershipServiceBean implements SessionBean, HLCKaveryStatelessMembershipServiceRemoteBusiness {
    private SessionContext context;
    private InitialContext ic = null;
    private static final String dbName = "java:/ELMTMSSQLDS";
    private Connection con;
    private ResultSet rs= null;
    private PreparedStatement prepStmt = null;
    private String name = "ejb/HLCMemberServiceJNDI";
    
    private String  statusId;
    private String  statusName;
    private String  description;
    
    HLCArabianSeaHorseMembershipStatusLocalHome home;
    HLCArabianSeaHorseMembershipStatusLocal remote;
    
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
        home = (HLCArabianSeaHorseMembershipStatusLocalHome)obj;
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
   /**
  * Name         :addContactTypeMaster
  * Description  :This method will insert record into the Membership Refund Details table
  * @ param      :passing object of type of the class MembershipRefundDetails
  * @return      :void
  * @throws      :RemoteException, EJBException
  */      
    public boolean addMembershipStatusMaster(HLCMembershipStatusMaster objMemberStat) throws RemoteException {
       boolean result = false;
       result = getMembershipStatusId(objMemberStat.getStatusName());
       if (!result){
           return false;
       } 
        try{
             remote = home.create(objMemberStat);
        }
        catch(Exception exp){
             throw new EJBException("createMembershipStatusMaster : " + exp.getMessage());
        }
       return true;
    }
/**
  * Name         :editContactTypeMaster
  * Description  :This method will retrieve the member details for refund details table
  * @ param      :None
  * @return      :member Identification number
  * @throws      :RemoteException, FinderException
  */     
     public boolean editMembershipStatusMaster(HLCMembershipStatusMaster objMemberStat) throws RemoteException, FinderException{
       System.out.print("MembershipStatusMaster  ID : "+objMemberStat.getStatusId());
       boolean result = false;
       if ((objMemberStat.getStatusName().length()) > 79){
           Debug.print("Character Size is more then 80");
           return false;
       }
       result = getMembershipStatusId(objMemberStat.getStatusName());
       if (!result){
           Debug.print("Name Exist in database : Same Name cannot be allowed. ");
           return false;
       } 
        if (objMemberStat == null && (objMemberStat.getStatusId().equals("")) ) {
                throw new EJBException("MembershipStatus ID can't be empty");
            }
        if (statusExists(objMemberStat.getStatusId()) == false) {
            //throw new EJBException("Contact Type ID Not Exists : " + contactTypeId);
                 Debug.print("Kavery Session Bean statusExists inside edit "+ statusId);
               result = false;
        }
        else {//if (result == true){
            Debug.print("Kavery Session Bean statusExists inside edit true part: "+ objMemberStat.getStatusId());               
            remote.setStatusId(objMemberStat.getStatusId());
            remote.setStatusName(objMemberStat.getStatusName());
            remote.setDescription(objMemberStat.getDescription());
            result = true;
        }
           return result;
     }    
     
     public boolean deleteMembershipStatusMaster(String statusId) throws RemoteException,FinderException{
        Debug.print("Kavery Session Bean MembershipStatusMaster");
        Debug.print("Contact Type Id :" + statusId);
        boolean result = false;
        if (statusId == null) {
            throw new EJBException("statusId can't be empty");
        }
        if (statusExists(statusId) == false) {
            throw new EJBException("statusId Not Exists" + statusId);
        }
        try {
            remote.remove();
            result = true;
        } catch (Exception ex) {
              throw new EJBException("remove MembershipStatusMaster : " + ex.getMessage());
        }
        finally{
            return result;
        }   
    }

/**
  * Name         :getMembershipStatusId
  * Description  :This method will retrieve the member details for refund details table
  * @ param      :None
  * @return      :member Identification number
  * @throws      :RemoteException, FinderException
  */     
    public boolean getMembershipStatusId(String statusName)  {
        String statusId = null;
        try{
            ArrayList result =  (ArrayList)home.findByMembershipStatusName(statusName);
            if(result!=null){
                Iterator e = result.iterator();
                while(e.hasNext()){
                   HLCArabianSeaHorseMembershipStatusLocal localMSIRemote = (HLCArabianSeaHorseMembershipStatusLocal)e.next();
                   statusId = localMSIRemote.getStatusId();
                }
            }
        }
        catch(Exception e){
            Debug.print("Exception in getMembershipStatusId:" + e);
        }
         if (statusId != null){
            return false;
         }else {
            return true;
         }
    }    
    
    public Collection getMembershipStatusDetail() throws RemoteException, FinderException {
        Collection c = null;
        String statusName = null;
        Debug.print("Inside the getMembershipStatusDetail");
        ArrayList A = new ArrayList();
        Collection col = home.findByMembershipStatusDetail();
                 ArrayList array = new ArrayList(col);
                for (Iterator it=array.iterator(); it.hasNext( ); ) { 
                   // Object anObject = it.next( ); 
                    String str = String.valueOf(it.next( )); 
                    String[] result = str.split(":");
                     for (int x=1; x<result.length; x++){
                         System.out.println(result[x]);
                         statusName = result[x];
                         A.add(result[1]);
                     }
                   System.out.println( statusName); //anObject.toString() ); 
                }
        return A;
    }  
    
    public Vector getStatusDetail() throws RemoteException, FinderException {
        Vector vObj = new Vector();
        boolean bol;
        //String statusName = null;
        Debug.print("Inside the getStatusDetail");

         try {
            makeConnection();
            String selectStatement = "SELECT status_id,status_name,description FROM  " +DBHelper.USEA_MEMBER_STATUS_MASTER+" ORDER BY status_id ASC ";
            prepStmt = con.prepareStatement(selectStatement);
            Debug.print(" Inside the getStatusDetail");
            rs = prepStmt.executeQuery();
            while (bol = rs.next()) {
                this.statusId = rs.getString(1);
                this.statusName = rs.getString(2);
                this.description = rs.getString(3);
                System.out.println("  status Id : "+statusId);
                System.out.println("  status Id : "+statusName);
                System.out.println("  status Id : "+description);
                String statusDescription [] = {statusId, statusName, description };
                vObj.add(statusDescription);
                Debug.print(" Inside the getStatusDetail"+bol);
             } 
             prepStmt.close();
             Debug.print(" Inside the getStatusDetail"+bol);
        } catch (SQLException sqe) {
            //throw new EJBException(sqe);
            Debug.print("  "+sqe.getMessage());
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
         Debug.print("after final the getStatusDetail" + vObj);
        return vObj;
    } 








/**
  * Name         :memberExists
  * Description  :This method will check for the existing of memberId and call the findByPrimaryKey method
  * @ param      :None
  * @return      :boolean value
  * @throws      :none
  */         
   private boolean statusExists(String statusId) {
        boolean result =false;
//Debug.print("Kavery Session Bean contactTypeIdExists in side loop: "+contactTypeId);
        if ( statusId != this.statusId ) {
            try {
              //  Debug.print("Kavery Session Bean contactTypeIdExists in side loop: "+contactTypeId);
                remote = home.findByPrimaryKey(statusId);
                this.statusId = statusId;
                 Debug.print("Kavery Session Bean contactTypeIdExists in side home: "+ home);
                Debug.print("Kavery Session Bean contactTypeIdExists in side remote: "+ remote);
                 result =true;
            } catch (Exception ex) {
               result =true;
               System.out.println("Exception:" + ex);
            }
        }
        Debug.print("Kavery Session Bean statusExists  "+ result);
        return result;
    } 

   /**
  * Name         :getHorseMembersStatusWise
  * Description  :This method will fetch the total count of horse members against each status
  * @ param      :date
  * @return      :HashMap
  * @throws      :RemoteException
  */




   public HashMap getHorseMembersStatusWise(Date fromDate,Date toDate) throws RemoteException
    {
        HashMap horseMemberCountStatusWise=new HashMap();
        String statusName=null;
        ResultSet rs=null;ResultSet rs1=null;
        PreparedStatement prepStmt=null;PreparedStatement prepStmt1=null;
          makeConnection();
           java.sql.Date startDate=DBHelper.toSQLDate(fromDate);
           java.sql.Date endDate=DBHelper.toSQLDate(toDate);
          String query="select status_id,status_name from "+ DBHelper.USEA_MEMBER_STATUS_MASTER;
          String query_1="select count(*) as total   from tblHorseMemberDetails   where request_status=? AND (add_date>=? AND add_date<=?) ";
          try
          {
          prepStmt = con.prepareStatement(query);
            rs=prepStmt.executeQuery();
           while(rs.next())
           {
                  String total=null;
                  String statusId=rs.getString(1);
                  statusName=rs.getString(2);
                  
                  prepStmt1=con.prepareStatement(query_1);
                  prepStmt1.setString(1,statusName);
                  prepStmt1.setDate(2, startDate);
                  prepStmt1.setDate(3, endDate);



                   rs1=prepStmt1.executeQuery();
                              while(rs1.next()){
                                     total=rs1.getString(1);
                              }
                   System.out.println("Status Name"+statusName+" total"+total);
                   horseMemberCountStatusWise.put(statusName,total);
                    rs1.close();
                    prepStmt1.close();

                   //Debug.print("StatusName+++++++++"+statusName+"   Total "+total);
           }
            Debug.print("Map Size inside HLCkaverystatelessMembershipServiceBean"+horseMemberCountStatusWise.size());
            
                  rs.close();
                  prepStmt.close();
                  releaseConnection();
          }
          catch(SQLException e)
          {

                Debug.print("Eception occured inside HLCkaverystatelessMembershipServiceBean" + e.getMessage());
                 releaseConnection();
                e.printStackTrace();


            }
          return horseMemberCountStatusWise;
    }

public HashMap getHorseMembersMemberShipsByStatus(Date fromDate,Date toDate,String status) throws RemoteException
    {
       HashMap horseMemberCountMembershipWiseByStatus=new HashMap();
        String user_type_id=null,membership_type_name=null,membership_type_id=null;
         ResultSet rs=null;ResultSet rs1=null;
        PreparedStatement prepStmt=null;PreparedStatement prepStmt1=null;
          makeConnection();
           java.sql.Date startDate=DBHelper.toSQLDate(fromDate);
           java.sql.Date endDate=DBHelper.toSQLDate(toDate);
           String query="select user_type_id from tblUserTypeMaster where user_type_name='Horse'";
           String query_1="select membership_type_name,membership_type_id from tblMembershipTypeMaster where user_type_id=?";
           String query_2="select count(*) as total  from tblMemberDetails m,tblHorseMemberDetails h where h.request_status=? AND m.member_id=h.horse_member_id  AND  m.membership_type_id=? AND (m.add_date>=? AND m.add_date<=?)";

           try
           {
            prepStmt = con.prepareStatement(query);
            rs=prepStmt.executeQuery();
           while(rs.next())
           {
               user_type_id=rs.getString(1);
           }
            rs.close();prepStmt.close();
            prepStmt = con.prepareStatement(query_1);
            prepStmt.setString(1,user_type_id);
            rs=prepStmt.executeQuery();
           while(rs.next())
           {
                 String total=null;
               membership_type_name=rs.getString(1);
               membership_type_id=rs.getString(2);
               Debug.print("Type_Name"+membership_type_name+"  Type_id="+membership_type_id);
               prepStmt1=con.prepareStatement(query_2);
               prepStmt1.setString(1,status);
               prepStmt1.setString(2,membership_type_id);
               prepStmt1.setDate(3,startDate);
               prepStmt1.setDate(4,endDate);
               rs1=prepStmt1.executeQuery();
               while(rs1.next())
               {
                   total=rs1.getString(1);
               }
               horseMemberCountMembershipWiseByStatus.put(membership_type_name,total);
               Debug.print("Mebership Name"+membership_type_name+"  Total"+total);
           }
            Debug.print("Map Size inside HLCkaverystatelessBean"+horseMemberCountMembershipWiseByStatus.size());
            rs.close();
           prepStmt.close();
            rs1.close();
            prepStmt1.close();
            releaseConnection();
           }

           catch(SQLException e)
           {

                //releaseConnection();
               Debug.print("Error occured"+e.getMessage());
           }
           return horseMemberCountMembershipWiseByStatus;
    }
public String getHorseMemberShipsBySpecificTypeAndStatus(Date fromDate,Date toDate,String status,String membership_type_id) throws RemoteException
    {
        Debug.print("Inside HLCHaveryStatelessBean getHumanMembersSpecificMembershipByStatus");
        Debug.print("Status "+status+" membership Type "+membership_type_id);
        String total=null;
        String query="select count(*) as total  from tblMemberDetails m,tblHorseMemberDetails h where h.request_status=? AND m.member_id=h.horse_member_id  AND  m.membership_type_id=? AND (m.add_date>=? AND m.add_date<=?)";
        ResultSet rs=null;
        PreparedStatement prepStmt=null;
        java.sql.Date startDate=DBHelper.toSQLDate(fromDate);
        java.sql.Date endDate=DBHelper.toSQLDate(toDate);
        makeConnection();
        try
        {
             prepStmt=con.prepareStatement(query);
             prepStmt.setString(1,status);
             prepStmt.setString(2,membership_type_id);
             prepStmt.setDate(3,startDate);
             prepStmt.setDate(4,endDate);
             rs=prepStmt.executeQuery();
               while(rs.next())
               {
                   total=rs.getString(1);
               }
             Debug.print("Total for membership Id"+membership_type_id +" is "+total);
             rs.close();
             prepStmt.close();
             releaseConnection();

        }
        catch(SQLException r)
        {
             r.printStackTrace();
             Debug.print(r.getMessage());
        }
        return total;
    }


 public HashMap getNewlyRegisteredHorseMembersMemTypeWise(Date fromDate,Date toDate) throws RemoteException
    {
       HashMap newHorseMemberCount=new HashMap();
        String user_type_id=null,membership_type_name=null,membership_type_id=null;
        ResultSet rs=null;ResultSet rs1=null;
        PreparedStatement prepStmt=null;PreparedStatement prepStmt1=null;
          makeConnection();
           java.sql.Date startDate=DBHelper.toSQLDate(fromDate);
           java.sql.Date endDate=DBHelper.toSQLDate(toDate);
           String query="select user_type_id from tblUserTypeMaster where user_type_name='Horse'";
           String query_1="select membership_type_name,membership_type_id from tblMembershipTypeMaster where user_type_id=?";
           String query_2="select count(*) as total  from tblMemberDetails m,tblHorseMemberDetails h where m.member_id=h.horse_member_id  AND  m.membership_type_id=? AND (m.add_date>=? AND m.add_date<=?)";
           try
           {
            prepStmt = con.prepareStatement(query);
            rs=prepStmt.executeQuery();
           while(rs.next())
           {
               user_type_id=rs.getString(1);
           }
            rs.close();prepStmt.close();
            prepStmt = con.prepareStatement(query_1);
            prepStmt.setString(1,user_type_id);
            rs=prepStmt.executeQuery();
           while(rs.next())
           {
                 String total=null;
               membership_type_name=rs.getString(1);
               membership_type_id=rs.getString(2);
               Debug.print("Type_Name"+membership_type_name+"  Type_id="+membership_type_id);
               prepStmt1=con.prepareStatement(query_2);
               prepStmt1.setString(1,membership_type_id);
               prepStmt1.setDate(2,startDate);
               prepStmt1.setDate(3,endDate);
               rs1=prepStmt1.executeQuery();
               while(rs1.next())
               {
                   total=rs1.getString(1);
               }
               newHorseMemberCount.put(membership_type_name,total);
               Debug.print("Mebership Name"+membership_type_name+"  Total"+total);
           }
            Debug.print("Map Size inside HLCkaverystatelessBean"+newHorseMemberCount.size());
            rs.close();
           prepStmt.close();
            rs1.close();
            prepStmt1.close();
            releaseConnection();
           }
          catch(SQLException e)
          {

                Debug.print("Eception occured inside HLCkaverystatelessMembershipServiceBean" + e.getMessage());
                 releaseConnection();
                e.printStackTrace();


            }
          return newHorseMemberCount;
    }
 public HashMap getNumberofHorseEventWise(Date fromDate,Date toDate,String[] years) throws RemoteException
    {
        HashMap horseMemberCountEventWise=new HashMap();
        String statusName=null;
        ResultSet rs=null;
        PreparedStatement prepStmt=null;
           java.sql.Date startDate=DBHelper.toSQLDate(fromDate);
           java.sql.Date endDate=DBHelper.toSQLDate(toDate);
           String query="select count(horse_member_id) from tblMeeCompResultDetails where year(add_date)=?";

           int yrCount = years.length;
         for ( int i = 0 ; i < yrCount ; ++i ) {
              String year=years[i];
          try
          {
          makeConnection();
          prepStmt = con.prepareStatement(query);
           prepStmt.setString(1,year);
            rs=prepStmt.executeQuery();
           while(rs.next())
           {
                  String total=null;
                  total = rs.getString(1);
                  Debug.print("For Year"+ year+"---->"+"total is:"+total);
                   horseMemberCountEventWise.put(year,total);

           }

            Debug.print("Map Size inside HLCkaverystatelessMembershipServiceBean"+horseMemberCountEventWise.size());

                  rs.close();
                  prepStmt.close();
                  releaseConnection();
           }
          catch(SQLException e)
          {

                Debug.print("Eception occured inside HLCkaverystatelessMembershipServiceBean" + e.getMessage());
                e.printStackTrace();
                releaseConnection();

            }

          }
          return horseMemberCountEventWise;
    }

public HashMap getHorseMembersEventsMembershipWise(Date fromDate,Date toDate) throws RemoteException
    {
       HashMap horseMembersEventsMembershipWise=new HashMap();
        String user_type_id=null,membership_type_name=null,membership_type_id=null;
         ResultSet rs=null;ResultSet rs1=null;
        PreparedStatement prepStmt=null;PreparedStatement prepStmt1=null;
          makeConnection();
           java.sql.Date startDate=DBHelper.toSQLDate(fromDate);
           java.sql.Date endDate=DBHelper.toSQLDate(toDate);
           String query="select user_type_id from tblUserTypeMaster where user_type_name='Horse'";
           String query_1="select membership_type_name,membership_type_id from tblMembershipTypeMaster where user_type_id=?";
           String query_2="select count(*) as total  from tblMemberDetails m,tblMeeCompResultDetails h where  m.member_id=h.horse_member_id  AND  m.membership_type_id=? AND (m.add_date>=? AND m.add_date<=?)";

           try
           {
            prepStmt = con.prepareStatement(query);
            rs=prepStmt.executeQuery();
           while(rs.next())
           {
               user_type_id=rs.getString(1);
           }
            rs.close();prepStmt.close();
            prepStmt = con.prepareStatement(query_1);
            prepStmt.setString(1,user_type_id);
            rs=prepStmt.executeQuery();
           while(rs.next())
           {
                 String total=null;
               membership_type_name=rs.getString(1);
               membership_type_id=rs.getString(2);
               Debug.print("Type_Name"+membership_type_name+"  Type_id="+membership_type_id);
               prepStmt1=con.prepareStatement(query_2);
               prepStmt1.setString(1,membership_type_id);
               prepStmt1.setDate(2,startDate);
               prepStmt1.setDate(3,endDate);
               rs1=prepStmt1.executeQuery();
               while(rs1.next())
               {
                   total=rs1.getString(1);
               }
               horseMembersEventsMembershipWise.put(membership_type_name,total);
               Debug.print("Mebership Name"+membership_type_name+"  Total"+total);
           }
            Debug.print("Map Size inside HLCkaverystatelessBean"+horseMembersEventsMembershipWise.size());
            rs.close();
           prepStmt.close();
            rs1.close();
            prepStmt1.close();
            releaseConnection();
           }

           catch(SQLException e)
           {

                //releaseConnection();
               Debug.print("Error occured"+e.getMessage());
           }
           return horseMembersEventsMembershipWise;
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
    
}
