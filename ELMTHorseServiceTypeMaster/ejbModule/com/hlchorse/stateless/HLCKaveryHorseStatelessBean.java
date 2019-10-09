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
package com.hlchorse.stateless;

import com.hlchorse.service.HLCArabianSeaHorseServiceTypeLocal;
import com.hlchorse.service.HLCArabianSeaHorseServiceTypeLocalHome;
import com.hlchorse.service.util.DBHelper;
import com.hlchorse.service.util.Debug;
import com.hlchorse.service.util.HLCHorseServiceTypeMaster;
import javax.ejb.*;
import java.rmi.RemoteException;
import javax.naming.*;
import java.sql.*;
import javax.sql.*;
import java.util.*;

/**
 * This is the bean class for the KaveryHorseStatelessBean enterprise bean.
 * Created Aug 29, 2006 5:33:55 PM
 * @author harmohan
 */
public class HLCKaveryHorseStatelessBean implements SessionBean, HLCKaveryHorseStatelessRemoteBusiness {
    private SessionContext context;
     private InitialContext ic = null;
    private static final String dbName = "java:/ELMTMSSQLDS";
    private Connection con;
    private ResultSet rs= null;
    private PreparedStatement prepStmt = null;
    private String name = "ejb/HLCHorseServiceJNDI";
    private String horseServiceTypeId;
    private String horseServiceTypeName;
    private String horseServiceTypeAmount;

    HLCArabianSeaHorseServiceTypeLocalHome home;
    HLCArabianSeaHorseServiceTypeLocal remote;
    
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
        home = (HLCArabianSeaHorseServiceTypeLocalHome)obj;
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
       /**
  * Name         :addHorseServicesTypeMaster
  * Description  :This method will insert record into the Membership Refund Details table
  * @ param      :passing object of type of the class MembershipRefundDetails
  * @return      :void
  * @throws      :RemoteException, EJBException
  */      
    public boolean addHorseServiceTypeMaster(HLCHorseServiceTypeMaster objHorse) throws RemoteException {
       boolean result = false;
       //Debugs starts by Lakshmi
       System.out.println("horse type id" +objHorse.getHorseServiceTypeId());
      // result = getHorseServiceTypeId(objHorse.getHorseServiceTypeName(),objHorse.getUserTypeId());
       result = getHorseServiceTypeId(objHorse.getHorseServiceTypeId(),objHorse.getHorseServiceTypeName(),objHorse.getUserTypeId());
        if (!result){
      // if (result==true){
       //Debugs Ends by Lakshmi
           return false;
       } 
        try{
             remote = home.create(objHorse);
        }
        catch(Exception exp){
             throw new EJBException("createHorseServiceTypeMaster : " + exp.getMessage());
        }
       return true;
    }
/**
  * Name         :editHorseServiceTypeMaster
  * Description  :This method will retrieve the member details for refund details table
  * @ param      :None
  * @return      :member Identification number
  * @throws      :RemoteException, FinderException
  */     
     public boolean editHorseServiceTypeMaster(HLCHorseServiceTypeMaster objHorse) throws RemoteException, FinderException{
       System.out.print("HorseServiceType ID : "+objHorse.getHorseServiceTypeId());
       boolean result = false;
       //Debugs Starts by Lakshmi
       /*if ((objHorse.getHorseServiceTypeName().length()) > 80){
           Debug.print("Character Size is more then 80");
           return false;
       }*/
       
       result = getHorseServiceTypeId(objHorse.getHorseServiceTypeId(),objHorse.getHorseServiceTypeName(),objHorse.getUserTypeId());
       if (!result){
            Debug.print("Name Exist in database : Same name can not be allowed");
           return false;
       }
       //Debugs Ends by Lakshmi
      /* result = getHorseServiceTypeId(objHorse.getHorseServiceTypeName());
       Debug.print(" Resutl for HorseService Type Id : "+result);
//hari changed !result to result==false       
       if (result==false){
            Debug.print("Name Exist in database : Same name can not be allowed");
           return false;
       }
     */
        if (objHorse == null && objHorse.getHorseServiceTypeId() != null && objHorse.getHorseServiceTypeId().trim().length()!=0 ) {
                throw new EJBException("HorseServiceType ID can't be empty");
        }
        else{
           /* if (isHorseEditExist(objHorse.getHorseServiceTypeId(), objHorse.getHorseServiceTypeName()) == true) {
                //throw new EJBException("Contact Type ID Not Exists : " + contactTypeId);
                     Debug.print("Kavery Session Bean contactTypeIdExists inside edit FALSE PART "+ horseServiceTypeId);
                   result = false;
            }
            else {*/
                Debug.print("KaverySessionBean contactTypeIdExists inside edit TRUE part: "+ objHorse.getHorseServiceTypeId());
                Debug.print("UserTypeId is "+objHorse.getUserTypeId());
                Debug.print("HorseServiceTypeId is "+objHorse.getHorseServiceTypeId());
                Debug.print("HorseServiceTypeName is "+objHorse.getHorseServiceTypeName());
                Debug.print("HorseServiceTypeAmount is "+objHorse.getHorseServiceTypeAmount());
                Debug.print("HorseActiveStatus is"+objHorse.getActiveStatus());
                result = updateService(objHorse.getUserTypeId(),objHorse.getHorseServiceTypeId(), objHorse.getHorseServiceTypeName(),objHorse.getHorseServiceTypeAmount(),objHorse.getTransaction_type_id(),objHorse.getActiveStatus());
                Debug.print("result:" + result);
            //}
        }
           return result;
     } 
     
      private boolean updateService(String userTypeId,String hrServiceId, String hrName, String amount, String transaction_type_id,int active_status) {
          boolean result = false;
        try {
            System.out.println("KaverySessionBean for updateService");
            Debug.print("transaction_type_id in updateService :"+transaction_type_id);
            
            makeConnection();
            String updateStatement = "update " + DBHelper.USEA_SERVICETYPE_MASTER +
            " set service_type_name = ?, service_type_amount = ?, transaction_type_id = ?,user_type_id=?,active_status=? where service_type_id = ?";
            PreparedStatement prepStmt = con.prepareStatement(updateStatement);
            prepStmt.setString(1, hrName);
            prepStmt.setDouble(2, Double.parseDouble(amount));
            prepStmt.setString(3, transaction_type_id);
            prepStmt.setString(4, userTypeId);
            prepStmt.setInt(5, active_status);
            prepStmt.setString(6, hrServiceId);
            
            int rowCount = prepStmt.executeUpdate();
            if(rowCount>=1){
                result = true;
            }
            Debug.print("updateService Sucessfully Updated." + rowCount);
            prepStmt.close();
        } catch (Exception ex) {
           throw new EJBException("updateService: " + ex.getMessage());
           // ex.printStackTrace();
        }finally {
            releaseConnection();
        }
        return result;
    }
     
     public boolean deleteHorseServiceTypeMaster(String horseServiceTypeId) throws RemoteException,FinderException{
        Debug.print("Kavery Session Bean deleteSponsorPlan");
        Debug.print("horseServiceTypeId :" + horseServiceTypeId);
        boolean result = false;
        if (horseServiceTypeId == null) {
            throw new EJBException("horseServiceTypeId can't be empty");
        }
        if (horseServiceTypeIdExists(horseServiceTypeId) == false) {
            throw new EJBException("horseServiceTypeId Not Exists" + horseServiceTypeId);
        }
        try {
            remote.remove();
            result = true;
        } catch (Exception ex) {
              throw new EJBException("removehorseServiceTypeName: " + ex.getMessage());
        }
        finally{
            return result;
        }   
    }
    
/**
  * Name         :getRefundDetails
  * Description  :This method will retrieve the member details for refund details table
  * @ param      :None
  * @return      :member Identification number
  * @throws      :RemoteException, FinderException
  */     
    //Debugs Starts by Lakshmi
   //public boolean getHorseServiceTypeId(String horseServiceTypeName,String userTypeId) {
   public boolean getHorseServiceTypeId(String horseServiceTypeId,String horseServiceTypeName,String userTypeId) {
       //Debugs Ends by Lakshmi
        String horseServiceTypeID = null;
        try{
            //Debugs Starts by Lakshmi
          //  ArrayList result =  (ArrayList)home.findByHorseTypeName(horseServiceTypeName,userTypeId);
            ArrayList result =  (ArrayList)home.findByHorseTypeName(horseServiceTypeId,horseServiceTypeName,userTypeId);
            Debug.print("result" +result);
            //Debugs Ends by Lakshmi
            if(result!=null){
                Iterator e = result.iterator();
                while(e.hasNext()){
                   HLCArabianSeaHorseServiceTypeLocal localHSTRemote = (HLCArabianSeaHorseServiceTypeLocal)e.next();
                   horseServiceTypeID = localHSTRemote.getHorseServiceTypeId();
                   Debug.print("HorseServiceTypeID value is "+horseServiceTypeID);
                }
            }
        }
        catch(Exception e){
            Debug.print("Exception in getHorseServiceTypeId:" + e);
        }
         if (horseServiceTypeID != null){
            return false;
         }else {
            return true;
         }
    }    

    public Collection getHorseServiceDetails() throws RemoteException, FinderException {
        ArrayList A = new ArrayList();
        Collection col = home.findByHorseServiceTypeDatails();
                 ArrayList array = new ArrayList(col);
                for (Iterator it=array.iterator(); it.hasNext( ); ) { 
                   // Object anObject = it.next( ); 
                    String str = String.valueOf(it.next( )); 
                    String[] result = str.split(":");
                     for (int x=1; x<result.length; x++){
                         System.out.println(result[x]);
                         A.add(result[1]);
                     }
                }
        return A;
    }  
    
    public Vector displayHorseServiceDetails() throws RemoteException, FinderException {
      
        Vector vObj = new Vector();
        try {
            makeConnection();
            String selectStatement = "SELECT horse_service_type_id,horse_service_type_name,horse_service_type_amount FROM  " +DBHelper.USEA_HORSE_SERVICETYPE_MASTER;
            prepStmt = con.prepareStatement(selectStatement);
            rs = prepStmt.executeQuery();
            while (rs.next()) {
                String horseServiceTypeId = rs.getString(1);
                String horseServiceTypeName = rs.getString(2);
                String horseServiceTypeAmount  = rs.getString(3);
                String horseService [] = {horseServiceTypeId,horseServiceTypeName, horseServiceTypeAmount};
                vObj.add(horseService);
             } 
             prepStmt.close();
        } catch (SQLException sqe) {
            throw new EJBException(sqe);
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return vObj;
   }
/*Method Edited by Nisha 11/04/2011*/
    public Vector displayNonHumanServiceDetails(String userid)throws RemoteException, FinderException {
        System.out.print("HLCKaveryHorseStatelessBean.java:displayNonHumanServicesDetails ");
         Vector vObj = new Vector();
        try {
            makeConnection();
            String selectStatement = "SELECT service_type_id,service_type_name,service_type_amount,active_status FROM  " +DBHelper.USEA_SERVICETYPE_MASTER+
                    " where user_type_id=?";
            System.out.print(selectStatement);
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, userid);
            rs = prepStmt.executeQuery();
            while (rs.next()) {
                String nonHumanServiceTypeId = rs.getString(1);
                String nonHumanServiceTypeName = rs.getString(2);
                String nonHumanServiceTypeAmount  = rs.getString(3);
                String active_status=rs.getString(4);
                String horseService [] = {nonHumanServiceTypeId,nonHumanServiceTypeName, nonHumanServiceTypeAmount,active_status};
                vObj.add(horseService);
             }
             prepStmt.close();
        } catch (SQLException sqe) {
            throw new EJBException(sqe);
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return vObj;
    }

    /*Method Edited by Nisha 11/04/2011*/
    
      public String[] displayHorseServiceDetailsById(String horseServiceTypeId) throws RemoteException, FinderException {
          String horseService [] = null;
        try {
            makeConnection();
            String selectStatement = "SELECT a.service_type_id,a.service_type_name,a.service_type_amount,a.transaction_type_id,a.user_type_id,a.active_status,b.user_type_name FROM  " +DBHelper.USEA_SERVICETYPE_MASTER + " a," + DBHelper.USEA_USERTYPE_MASTER + " b where a.service_type_id=? and a.user_type_id=b.user_type_id";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,horseServiceTypeId);
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                String horseServiceTypeIdVal = rs.getString(1);
                String horseServiceTypeName = rs.getString(2);
                String horseServiceTypeAmount  = rs.getString(3);
                String transaction_type_id  = rs.getString(4);
                String user_type_id=rs.getString(5);
                String active_status=rs.getString(6);
                String userTypeName=rs.getString(7);
                Debug.print("Active Status"+active_status);
                
                String horseServiceLocal [] = {horseServiceTypeIdVal,horseServiceTypeName, horseServiceTypeAmount, transaction_type_id,user_type_id,active_status,userTypeName};
                horseService = horseServiceLocal;
             } 
             prepStmt.close();
        } catch (SQLException sqe) {
            throw new EJBException(sqe);
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return horseService;
   }  
      
       private boolean isHorseEditExist(String horseServiceTypeId, String horseServiceName) throws RemoteException, FinderException {
          boolean result = false;
          makeConnection();
        try {
            String selectStatement = "SELECT service_type_id FROM  " +DBHelper.USEA_SERVICETYPE_MASTER + " where service_type_name = ? and service_type_id ! = ? ";
            System.out.append("SQL is"+selectStatement);
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,horseServiceName);
            prepStmt.setString(2,horseServiceTypeId);
            
            rs = prepStmt.executeQuery();
            if (rs.next()) {
              result = true;
            }
            rs.close();
            prepStmt.close();
            Debug.print("result:" + result);
        } catch (SQLException sqe) {
          
            //sqe.printStackTrace();
            Debug.print("Exception:" + sqe.getMessage());
            throw new EJBException(sqe);
        } finally {
            releaseConnection();
        }
        return result;
   }  
    
    
/**
  * Name         :memberExists
  * Description  :This method will check for the existing of memberId and call the findByPrimaryKey method
  * @ param      :None
  * @return      :boolean value
  * @throws      :none
  */         
   private boolean horseServiceTypeIdExists(String horseServiceTypeId) {
        boolean result =false;
        Debug.print("Kavery Horse Stateless contactTypeIdExists in side loop: "+horseServiceTypeId);
        if ( this.horseServiceTypeId != horseServiceTypeId ) {
            try {
              //  Debug.print("Kavery Session Bean contactTypeIdExists in side loop: "+contactTypeId);
                remote = home.findByPrimaryKey(horseServiceTypeId);
                this.horseServiceTypeId = horseServiceTypeId;
                 Debug.print("Kavery Session Bean contactTypeIdExists in side home: "+ home);
                Debug.print("Kavery Session Bean contactTypeIdExists in side remote: "+ remote);
                 result =true;
            } catch (Exception ex) {
               result =true;
               System.out.println("Exception:" + ex);
            }
        }
        Debug.print("Kavery Session Bean horseServiceTypeIdExists  "+ result);
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
