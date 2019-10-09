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
package com.hlccountry.mail.stateless;

import com.hlccountry.dao.HLCCountryMailPriceDAO;
import com.hlccountry.mail.price.HLCArabianSeaCountryMailPriceLocal;
import com.hlccountry.mail.price.HLCArabianSeaCountryMailPriceLocalHome;
import com.hlccountry.mail.util.HLCCountryMailPriceMaster;
import com.hlccountry.mail.util.DBHelper;
import com.hlccountry.mail.util.Debug;
import javax.ejb.*;
import java.rmi.RemoteException;
import javax.naming.*;
import java.sql.*;
import javax.sql.*;
import java.util.*;

/**
 * This is the bean class for the KaveryCountryMailPriceSeaaionBean enterprise bean.
 * Created Aug 29, 2006 3:20:33 PM
 * @author harmohan
 */
public class HLCKaveryCountryMailPriceSeaaionBean implements SessionBean, HLCKaveryCountryMailPriceSeaaionRemoteBusiness {
    private SessionContext context;
    private InitialContext ic = null;
    private static final String dbName = "java:/ELMTMSSQLDS";
    private Connection con;
    private ResultSet rs= null;
    private PreparedStatement prepStmt = null;
    private String name = "ejb/HLCMailPriceJNDI";
    private String countryMailTypeId;
    private String countryMailTypeName;
    private String countryMailPrice;
    
    HLCArabianSeaCountryMailPriceLocalHome home;
    HLCArabianSeaCountryMailPriceLocal remote;
    
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
            home = (HLCArabianSeaCountryMailPriceLocalHome)obj;
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
 /**
  * Name         :addCountryMailPriceMaster
  * Description  :This method will insert record into the Membership Refund Details table
  * @ param      :passing object of type of the class MembershipRefundDetails
  * @return      :void
  * @throws      :RemoteException, EJBException
  */      
/*    public boolean addCountryMailPriceMaster(CountryMailPriceMaster objCountryMail) throws RemoteException {
       boolean result = false;
       result = getCountryMailTypeID(objCountryMail.getCountryMailTypeName());
       if (!result){
           return false;
       } 
        try{
             remote = home.create(objCountryMail);
        }
        catch(Exception exp){
             throw new EJBException("createCountryMailPriceMaster : " + exp.getMessage());
        }
       return true;
    }
 *
 */
    
     public boolean addCountryMailPriceMaster(HLCCountryMailPriceMaster objCountryMail) throws RemoteException {
       boolean result = false;
       String countryMailTypeName = objCountryMail.getCountryMailTypeName();
       String membershipTypeId = objCountryMail.getMembershipTypeId();
       String countryMailPrice = objCountryMail.getCountryMailPrice();
       HLCCountryMailPriceDAO dao = new HLCCountryMailPriceDAO();
       
       if (countryMailTypeName==null || countryMailTypeName.trim().length()==0 || membershipTypeId==null || membershipTypeId.trim().length()==0){
           Debug.print("CountryMailTypeId Country Name and MemberDetails are Null or empty");
           return false;
       }
       else{
           Debug.print("Country Name and MemberDetails are not Null and empty");
           if(dao.isCountryMailTypeNameExist(countryMailTypeName,membershipTypeId)){
                result = dao.insertCountryPrice(countryMailTypeName, membershipTypeId, countryMailPrice);
           }
       }
      return result;
     }
    
    
    
/**
  * Name         :editCountryMailPriceMaster
  * Description  :This method will retrieve the member details for refund details table
  * @ param      :None
  * @return      :member Identification number
  * @throws      :RemoteException, FinderException
  */     
  /*   public boolean editCountryMailPriceMaster(CountryMailPriceMaster objCountryMail) throws RemoteException, FinderException{
       System.out.print("Country Mail Type Id : "+objCountryMail.getCountryMailTypeId());
       boolean result = false;
       if ((objCountryMail.getCountryMailTypeName().length()) > 79){
           Debug.print("Character Size is more then 80");
           return false;
       }
       result = getCountryMailTypeID(objCountryMail.getCountryMailTypeName());
      if (!result){
           Debug.print(" Contact type Name Exist Cannot be update database with two same name");
           return false;
       } 
          
        if (objCountryMail == null && (objCountryMail.getCountryMailTypeId().equals("")) ) {
                throw new EJBException("Country Mail Type ID can't be empty");
            }
        if (countryMailTypeIdExists(objCountryMail.getCountryMailTypeId()) == false) {
            //throw new EJBException("Contact Type ID Not Exists : " + contactTypeId);
                 Debug.print("Kavery Session Bean contactTypeIdExists inside edit "+ countryMailTypeId);
               result = false;
        }
        else {//if (result == true) {
            Debug.print("Kavery Session Bean contactTypeIdExists inside edit true part: "+ objCountryMail.getCountryMailTypeId());               
            remote.setCountryMailTypeId(objCountryMail.getCountryMailTypeId());
            remote.setCountryMailTypeName(objCountryMail.getCountryMailTypeName());
            remote.setCountryMailPrice(objCountryMail.getCountryMailPrice());
            result = true;
        }
           return result;
     }  
   *
   **/
     
     /**
  * Name         :editCountryMailPriceMaster
  * Description  :This method will retrieve the member details for refund details table
  * @ param      :None
  * @return      :member Identification number
  * @throws      :RemoteException, FinderException
  */     
     public boolean editCountryMailPriceMaster(HLCCountryMailPriceMaster objCountryMail) throws RemoteException, FinderException{
        boolean result = false;
        String countryMailTypeId = objCountryMail.getCountryMailTypeId();
       String countryMailTypeName = objCountryMail.getCountryMailTypeName();
       String membershipTypeId = objCountryMail.getMembershipTypeId();
       String countryMailPrice = objCountryMail.getCountryMailPrice();
       HLCCountryMailPriceDAO dao = new HLCCountryMailPriceDAO();
       
       if (countryMailTypeId==null || countryMailTypeId.trim().length()==0 || countryMailTypeName==null || countryMailTypeName.trim().length()==0 || membershipTypeId==null || membershipTypeId.trim().length()==0){
           Debug.print("CountryMailTypeId Country Name and MemberDetails are Null or empty");
           return false;
       }
       else{
           Debug.print("Country Name and MemberDetails are not Null and empty");
           if(dao.isCountryMailTypeNameEditExist(countryMailTypeId, countryMailTypeName, membershipTypeId)){
                result = dao.updateCountryPrice(countryMailTypeId, countryMailTypeName, membershipTypeId, countryMailPrice);
           }
       }
      return result;
     }    
     
     public boolean deleteCountryMailPriceMaster(String countryMailTypeId) throws RemoteException,FinderException{
        Debug.print("Kavery Session Bean deleteCountryMailPriceMaster");
        Debug.print("Country Mail Type Id :" + countryMailTypeId);
        boolean result = false;
        if (countryMailTypeId == null) {
            throw new EJBException("countryMailTypeId can't be empty");
        }
        if (countryMailTypeIdExists(countryMailTypeId) == false) {
            throw new EJBException("contactTypeId Not Exists" + countryMailTypeId);
        }
        try {
            remote.remove();
            result = true;
        } catch (Exception ex) {
              throw new EJBException("removeCountryMailTypeName: " + ex.getMessage());
        }
        finally{
            return result;
        }   
    }

/**
  * Name         :getCountryMailTypeID
  * Description  :This method will retrieve the member details for refund details table
  * @ param      :None
  * @return      :member Identification number
  * @throws      :RemoteException, FinderException
  */     
    public boolean getCountryMailTypeID(String countryMailTypeName) {
        String countryMailID = null;
        try{
            ArrayList result =  (ArrayList)home.findByCountryMailTypeName(countryMailTypeName);
            if(result!=null){
                Iterator e = result.iterator();
                while(e.hasNext()){
                   HLCArabianSeaCountryMailPriceLocal localCMPRemote = (HLCArabianSeaCountryMailPriceLocal)e.next();
                   countryMailID = localCMPRemote.getCountryMailTypeId();
                }
            }
        }
        catch(Exception e){
            Debug.print("Exception in getNonUSEAOrgId:" + e);
        }
         if (countryMailID != null){
            return false;
         }else {
            return true;
         }
 
    }    
    
    public Collection getCountryMailDetail() throws RemoteException, FinderException {

        ArrayList A = new ArrayList();
       Collection col = home.findByCountryMailPrice();
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
     
     /*   Debug.print("KaveryCountryMailPrice getCountryMailDetail  ");
        try {
            A = (ArrayList)new CountryMailPriceDAO().selectAllCountryPriceDetails();
        }catch(Exception e){
            Debug.print("Exception in getCountryMailDetail:" + e);
        }
      */
        return A;
    } 

    public Vector displayCountryMailDetail() throws RemoteException, FinderException {
        Vector vObj = new Vector();
        try {
            makeConnection();
            String selectStatement = "SELECT country_mail_type_id,country_mail_type_name,country_mail_price FROM  " +DBHelper.USEA_COUNTRY_MAIL_PRICEMASTER;
            prepStmt = con.prepareStatement(selectStatement);
           
            rs = prepStmt.executeQuery();
            while (rs.next()) {
                String countryMailTypeId =rs.getString(1);
                String countryMailTypeName = rs.getString(2);
                String countryMailPrice =rs.getString(3);
                Debug.print("Inside the Loop displayCountryMailDetail in MailPrice country");
                String mailPrice[] = {countryMailTypeId,countryMailTypeName,countryMailPrice };
                vObj.add(mailPrice);
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
    
    public String[] getCountryMailDetailById(String countryMailTypeId) throws RemoteException, FinderException {
        String [] strCountry = null;
        try {
            makeConnection();
            String selectStatement = "SELECT country_mail_type_id,country_mail_type_name,country_mail_price, membership_type_id FROM  " +DBHelper.USEA_COUNTRY_MAIL_PRICEMASTER + " where country_mail_type_id=?";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,countryMailTypeId);
            rs = prepStmt.executeQuery();
            while (rs.next()) {
                String countryMailTypeIdVal =rs.getString(1);
                String countryMailTypeName = rs.getString(2);
                String countryMailPrice =rs.getString(3);
                String membership_type_id = rs.getString(4);
                Debug.print("Inside the Loop displayCountryMailDetail in MailPrice country");
                String mailCountry [] = {countryMailTypeIdVal,countryMailTypeName,countryMailPrice,membership_type_id };
                strCountry = mailCountry;
            } 
             prepStmt.close();
        } catch (SQLException sqe) {
            throw new EJBException(sqe);
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return strCountry;
    }
    
     public ArrayList getCountryMailDetails(int membershipYear) throws RemoteException {
       Debug.print("KaveryCountryMailPrice displayCountryMailDetail  ");
       ArrayList  A = new ArrayList();
        try {
            A = (ArrayList)new HLCCountryMailPriceDAO().selectAllCountryPriceDetails(membershipYear);
        }catch(Exception e){
            Debug.print("Exception in displayCountryMailDetail:" + e);
        }
        return A;
    }
     
     
      public ArrayList getAllMembershipType() throws RemoteException{
       Debug.print("KaveryCountryMailPrice getAllMembershipType  ");
       ArrayList  A = new ArrayList();
        try {
            A = (ArrayList)new HLCCountryMailPriceDAO().selectAllMembershipType();
        }catch(Exception e){
            Debug.print("Exception in getAllMembershipType:" + e);
        }
        return A;
      }
     
    
     public HLCCountryMailPriceMaster getCountryMailDetailByCntMailId(String countryMailTypeId) throws RemoteException, FinderException {
       /* String [] strCountry = null;
        try {
            makeConnection();
            String selectStatement = "SELECT country_mail_type_id,country_mail_type_name,country_mail_price FROM  " +DBHelper.USEA_COUNTRY_MAIL_PRICEMASTER + " where country_mail_type_id=?";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,countryMailTypeId);
            rs = prepStmt.executeQuery();
            while (rs.next()) {
                String countryMailTypeIdVal =rs.getString(1);
                String countryMailTypeName = rs.getString(2);
                String countryMailPrice =rs.getString(3);
                Debug.print("Inside the Loop displayCountryMailDetail in MailPrice country");
                String mailCountry [] = {countryMailTypeIdVal,countryMailTypeName,countryMailPrice };
                strCountry = mailCountry;
            } 
             prepStmt.close();
        } catch (SQLException sqe) {
            throw new EJBException(sqe);
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return strCountry;
        **/
         Debug.print(" getCountryMailDetailByCntMailId():");
         HLCCountryMailPriceMaster A = new HLCCountryMailPriceMaster();
         try {
             if(countryMailTypeId!=null && countryMailTypeId.trim().length()!=0){
                A = (HLCCountryMailPriceMaster)new HLCCountryMailPriceDAO().selectCountryPriceDetailsByPriceId(countryMailTypeId);
             }
        }catch(Exception e){
            Debug.print("Exception in getCountryMailDetail:" + e);
        }
         return A;
    }
    
    
/**
  * Name         :memberExists
  * Description  :This method will check for the existing of memberId and call the findByPrimaryKey method
  * @ param      :None
  * @return      :boolean value
  * @throws      :none
  */         
   private boolean countryMailTypeIdExists(String countryMailTypeId) {
        boolean result =false;
        if ( countryMailTypeId != this.countryMailTypeId ) {
            try {
              //  Debug.print("Kavery Session Bean contactTypeIdExists in side loop: "+contactTypeId);
                remote = home.findByPrimaryKey(countryMailTypeId);
                this.countryMailTypeId = countryMailTypeId;
               //  Debug.print("Kavery Session Bean contactTypeIdExists in side home: "+ home);
               /// Debug.print("Kavery Session Bean contactTypeIdExists in side remote: "+ remote);
                 result =true;
            } catch (Exception ex) {
               result =true;
               System.out.println("Exception:" + ex);
            }
        }
        Debug.print("Kavery Session Bean countryMailTypeIdExists  "+ result);
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
