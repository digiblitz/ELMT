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
package com.hlccountry.mail.price;

import com.hlccountry.mail.util.HLCCountryMailPriceMaster;
import com.hlccountry.mail.util.DBHelper;
import com.hlccountry.mail.util.Debug;
import javax.ejb.*;
import java.util.Date;
import java.util.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;

/**
 * This is the bean class for the ArabianSeaCountryMailPriceBean enterprise bean.
 * Created Aug 29, 2006 2:43:14 PM
 * @author harmohan
 */
public class HLCArabianSeaCountryMailPriceBean implements EntityBean, HLCArabianSeaCountryMailPriceLocalBusiness {
    private EntityContext context;
    private static final String dbName = "java:/ELMTMSSQLDS";
    private Connection con = null;
    private PreparedStatement prepStmt = null;
    private ResultSet rs = null;
    
   /*==============CountryMailPriceMaster Variable ==========================*/
    private String countryMailTypeId;
    private String countryMailTypeName;
    private String countryMailPrice;
    private String membershipTypeId;
    
    /**=======================Object Creation================================*/
    HLCCountryMailPriceMaster objCountryMail = new HLCCountryMailPriceMaster();
    
    /**==========================Setter Method===============================*/
     public void setCountryMailTypeId(String countryMailTypeId) {
	this.countryMailTypeId = countryMailTypeId;
	}
    public void setCountryMailTypeName(String countryMailTypeName) {
            this.countryMailTypeName = countryMailTypeName;
    }
    public void setCountryMailPrice(String countryMailPrice) {
            this.countryMailPrice = countryMailPrice ;
    }
    
    public void setMembershipTypeId(String membershipTypeId) {
         this.membershipTypeId = membershipTypeId ;
    }
    
    public String getCountryMailTypeId() {
            return 	countryMailTypeId;
    }
    public String getCountryMailTypeName() {
            return  countryMailTypeName;
    }
    public String getCountryMailPrice() {
            return countryMailPrice;
    }
    
     public String getMembershipTypeId() {
            return membershipTypeId;
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
        Debug.print("ArabianSeaCountryMailPriceBean ejbRemove");

        try {
            makeConnection();
            String deleteStatement = "delete from " + DBHelper.USEA_COUNTRY_MAIL_PRICEMASTER   + "  where country_mail_type_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(deleteStatement);
            prepStmt.setString(1, countryMailTypeId);
            int cnt = prepStmt.executeUpdate();
            System.out.println("Successfully Delete the CONTACT_TYPEMASTER record : "+cnt);
            prepStmt.close();
            releaseConnection();
        } catch (Exception ex) {
            throw new EJBException("ArabianSeaCountryMailPriceBean ejbRemove: " + ex.getMessage());
        }
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
        Debug.print("ArabianSeaCountryMailPriceBean ejbLoad");
        this.countryMailTypeId = (String) context.getPrimaryKey();
        try {
        makeConnection();
        String selectStatement = "SELECT country_mail_type_id,country_mail_type_name,country_mail_price, membership_type_id FROM " +DBHelper.USEA_COUNTRY_MAIL_PRICEMASTER+" WHERE country_mail_type_id = ?";
        PreparedStatement prepStmt = con.prepareStatement(selectStatement);
        prepStmt.setString(1, countryMailTypeId);
        ResultSet rs = prepStmt.executeQuery();
        if (rs.next()) {
                this.countryMailTypeId = rs.getString(1);
                this.countryMailTypeName = rs.getString(2);
                this.countryMailPrice = rs.getString(3);
                this.membershipTypeId = rs.getString(4);
        } 
        prepStmt.close();
        } catch (SQLException sqe) {
            throw new EJBException(sqe);
        } finally {
            releaseConnection();
        }
       System.out.println("ejbLoad for ArabianSeaCountryMailPriceBean");
    }
    
    /**
     * @see javax.ejb.EntityBean#ejbStore()
     */
    public void ejbStore() {
        try {
            
            System.out.println("ejbStore for ArabianSeaCountryMailPriceBean");
            makeConnection();
            String updateStatement = "update "+DBHelper.USEA_COUNTRY_MAIL_PRICEMASTER+
                    " set country_mail_type_name = '"+countryMailTypeName+"', country_mail_price = '"+Double.valueOf(countryMailPrice).doubleValue()+
                    "'   WHERE country_mail_type_id = '"+countryMailTypeId+"'";
            Debug.print(""+updateStatement);
            PreparedStatement prepStmt = con.prepareStatement(updateStatement);
            //prepStmt.setString(1, countryMailTypeName);
           // prepStmt.setDouble(2, Double.valueOf(countryMailPrice).doubleValue());
            //prepStmt.setString(3, countryMailTypeId.trim());
            Debug.print(" countryMailTypeName "+countryMailTypeName);
            Debug.print(" countryMailPrice "+countryMailPrice);
            Debug.print(" countryMailTypeId "+countryMailTypeId);
            int rowCount = prepStmt.executeUpdate();
            Debug.print("ejbStore Sucessfully Updated." + rowCount);
            prepStmt.close();
        } catch (Exception ex) {
             releaseConnection();
           throw new EJBException("ejbStore: " + ex.getMessage());
           // ex.printStackTrace();
        }finally {
            releaseConnection();
        }
    }
    
    // </editor-fold>
    public String ejbCreate(HLCCountryMailPriceMaster objCountryMail) throws CreateException {
         
        Debug.print("ContactTypeMaster ejbCreate"); 
        if(objCountryMail==null){
                throw new EJBException("ejbCreate: objCountryMail argument is null or empty");
        }
       // this.countryMailTypeId = objCountryMail.getCountryMailTypeId();
        this.countryMailTypeName = objCountryMail.getCountryMailTypeName();
        this.countryMailPrice = objCountryMail.getCountryMailPrice();
        try {
             makeConnection();
             String insertStatement = "insert into " + DBHelper.USEA_COUNTRY_MAIL_PRICEMASTER + " ( " +
                "country_mail_type_name, country_mail_price) values ( ?, ? )"; 
             prepStmt = con.prepareStatement(insertStatement);
             prepStmt.setString(1, countryMailTypeName);
             prepStmt.setDouble(2, Double.valueOf(countryMailPrice).doubleValue());
             int cnt = prepStmt.executeUpdate();
             System.out.println("successfully inserted into CountryMailPriceMaster  : "+cnt);
             prepStmt.close();
            
        } catch (Exception ex) {
             releaseConnection();
            throw new EJBException("ejbCreate: CountryMailPriceMaster  --- " + ex.getMessage());
        }finally {
             releaseConnection();
        }
        return null;
    }
    
    public void ejbPostCreate(HLCCountryMailPriceMaster objCountryMail) {
        Debug.print("CountryMailPriceMaster ejbPostCreate"); 
    }
    /**
     * See EJB 2.0 and EJB 2.1 section 12.2.5
     */
    public String ejbFindByPrimaryKey(String countryMailTypeId) throws FinderException {
         boolean result = false;
         try {
            makeConnection();
            String selectStatement = "SELECT country_mail_type_id FROM " + DBHelper.USEA_COUNTRY_MAIL_PRICEMASTER + " WHERE country_mail_type_id = ?";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, countryMailTypeId);
            ResultSet rs = prepStmt.executeQuery();
            if( rs.next()) {
                this.countryMailTypeId = rs.getString(1);
            }
            prepStmt.close();
         }catch (Exception e){
             e.printStackTrace();
         }finally {
             releaseConnection();
         }
         return countryMailTypeId;
    }
    
    public Collection ejbFindByCountryMailTypeName(String countryMailTypeName) throws FinderException {
        Debug.print("Inside the ejbFindByCountryMailTypeName  countryMailTypeName : "+countryMailTypeName);
        this.countryMailTypeName =countryMailTypeName;
        ArrayList array = new ArrayList();
        try {
            makeConnection();
            String selectStatement = "SELECT country_mail_type_id FROM  " +DBHelper.USEA_COUNTRY_MAIL_PRICEMASTER +
                    "  WHERE country_mail_type_name = ?";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, countryMailTypeName);
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
    
    public Collection ejbFindByCountryMailPrice() throws FinderException {
        Debug.print("Inside the ejbFindByCountryMailPrice   : ");
       
        ArrayList array = new ArrayList();
        try {
            makeConnection();
            String selectStatement = "SELECT country_mail_type_id,country_mail_type_name,country_mail_price, membership_type_id  FROM  " +DBHelper.USEA_COUNTRY_MAIL_PRICEMASTER;
            prepStmt = con.prepareStatement(selectStatement);
           
            rs = prepStmt.executeQuery();
            while (rs.next()) {
                array.add(rs.getString(1));
                array.add(rs.getString(2));
                array.add(rs.getString(3));
                array.add(rs.getString(4));
                Debug.print("Inside the Loop in MailPrice country");
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
