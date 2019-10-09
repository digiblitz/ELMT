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
package com.hlcmsg.distribution;

import com.hlcmsg.util.DBHelper;
import com.hlcmsg.util.Debug;
import com.hlcmsg.util.HLCMsgDistributionListMaster;
import javax.ejb.*;
import java.util.Date;
import java.util.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;

/**
 * This is the bean class for the MessagedistributionEntityBean enterprise bean.
 * Created Sep 4, 2006 7:52:41 PM
 * @author harmohan
 */
public class HLCMessagedistributionEntityBean implements EntityBean, HLCMessagedistributionEntityLocalBusiness {
    private EntityContext context;
    private static final String dbName = "java:/ELMTMSSQLDS";
    private Connection con = null;
    private PreparedStatement prepStmt = null;
    private ResultSet rs = null;
    private ResultSet rs1 = null;
    
    /*===================Msg Distribution List Master=======================*/
    private String listId;
    private String listName;
    private String contactlistId;
    private String emailId;
    private String addDate;
    private String activeStatus;
    
    public void setListId(String listId){this.listId = listId ; }
    public void setListName(String listName){ this.listName = listName; }
    public void setContactlistId(String contactlistId){ this.contactlistId = contactlistId; }
    public void setEmailId(String emailId){ this.emailId = emailId; }
    //public void setRegistrationEmailId(String registrationEmailId){ this.registrationEmailId = registrationEmailId; }
   // public void setActiveStatus(String activeStatus){ this.activeStatus =activeStatus ; }
    public void setAddDate(String addDate){ this.addDate = addDate; }
    
    /*============================Object Creation========================*/
   
    HLCMsgDistributionListMaster objMsgDist = new HLCMsgDistributionListMaster();
    
    public HLCMsgDistributionListMaster getMsgDistributionListMaster() {
        Debug.print("MsgDistributionListMaster getMsgDistributionListMaster"); 
        return new HLCMsgDistributionListMaster(listId,listName, contactlistId,activeStatus,addDate,emailId);
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
        Debug.print("MessageDistributionEntityBean ejbRemove");

        try {
            makeConnection();
            //USEA_MSS_DISTRIBUTION_LIST
            System.out.println("Inside ejbRemove Contact List ID :  "+listId);
            String deleteStatement = "DELETE FROM "+DBHelper.USEA_MSS_DISTRIBUTION_LIST +" WHERE list_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(deleteStatement);
            prepStmt.setString(1, listId.trim());
            int cnt = prepStmt.executeUpdate();
            System.out.println("Successfully Delete the USEA_MSS_DISTRIBUTION_LIST record : "+cnt);

            prepStmt.close();
        } catch (Exception ex) {
            releaseConnection();
            throw new EJBException("MessageEntityBean ejbRemove: " + ex.getMessage());
        }finally{
            releaseConnection();
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
        Debug.print("MessagingEntityBean ejbLoad");
        this.listId = (String) context.getPrimaryKey();
        try {
        makeConnection();
        String selectStatement = "SELECT list_id,list_name,contactlist_id FROM " +DBHelper.USEA_MSS_DISTRIBUTION_LIST+" WHERE list_id = ?";
        PreparedStatement prepStmt = con.prepareStatement(selectStatement);
        prepStmt.setString(1, listId);
        ResultSet rs = prepStmt.executeQuery();
        if (rs.next()) {
            this.listId = rs.getString(1);
            this.listName = rs.getString(2);
            this.contactlistId = rs.getString(3);
            
        } 
        prepStmt.close();
        } catch (SQLException sqe) {
            throw new EJBException(sqe);
        } finally {
            releaseConnection();
        }
       System.out.println("ejbLoad for MessagingEntityBean");
    }
    
    /**
     * @see javax.ejb.EntityBean#ejbStore()
     */
    public void ejbStore() {
        try {
            System.out.println("ejbStore for MessagingDistributionEntityBean");
            makeConnection();
            String updateStatement = "update " + DBHelper.USEA_MSS_DISTRIBUTION_LIST  + 
           " set list_name = ?,contactlist_id = ?   where list_id = ?";
            PreparedStatement prepStmt = con.prepareStatement(updateStatement);
            prepStmt.setString(1, listName);
            prepStmt.setString(2, contactlistId);
            prepStmt.setString(3, listId);
            
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
    
    // </editor-fold>
    
    
    public String ejbCreate(HLCMsgDistributionListMaster objMsgDist) throws CreateException {
        Debug.print("MessagingDistributionEntityBean ejbCreate"); 
        if(objMsgDist==null ){
                throw new EJBException("ejbCreate: ObjMsgList argument is null or empty");
        }
         if (isNotNull(objMsgDist.getListName())){
                this.listName = objMsgDist.getListName();
         }
        if (isNotNull(objMsgDist.getContactlistId())){
                this.contactlistId = objMsgDist.getContactlistId();
         }
       
        this.contactlistId = objMsgDist.getContactlistId();
        
        try {
              insertRowMsgDistributionList();
              System.out.println("after insertRowMsgDistributionList()");
          } catch (Exception ex) {
            throw new EJBException("ejbCreate: " + ex.getMessage());
        }
        return null;
    }
    
    public void ejbPostCreate(HLCMsgDistributionListMaster objMsgDist) {
        Debug.print("MessagingDistributionEntityBean  ejbPostCreate"); 
    }
    
    private void insertRowMsgDistributionList() throws SQLException {
        Debug.print("MessagingDistributionEntityBean insertRowMsgDistributionList");
        //java.sql.Date dt = java.sql.Date.valueOf(dob);
        long icp_ID = 0;
        makeConnection();
        
        String insertStatement = 
          "insert into " + DBHelper.USEA_MSS_DISTRIBUTION_LIST + " (list_name,contactlist_id) values ( ? , ? ) ";
        prepStmt = con.prepareStatement(insertStatement);
        System.out.println("Inside the Instructor Details ....\n\n ");
        prepStmt.setString(1, listName);
        System.out.println("listName :  "+listName);
        prepStmt.setString(2, contactlistId);
        System.out.println("contactlistId :  "+contactlistId);
        int cnt = prepStmt.executeUpdate();
        prepStmt.close();
        releaseConnection();
        System.out.println("Succefully inserted :  "+cnt);
        }
    
    public String ejbFindByPrimaryKey(String listId) throws FinderException {
        Debug.print("MessageDistributionEntityBean ejbFindByPrimaryKey");

        boolean result;
        try {
            result = selectByPrimaryKey(listId);
        } catch (Exception ex) {
            throw new EJBException("ejbFindByPrimaryKey: " + ex.getMessage());
        }
        if (result) {
             Debug.print("MessageDistributionEntityBean ejbFindByPrimaryKey ID:" + listId);
            return listId;
        } else {
            throw new ObjectNotFoundException("Row for id " + listId + " not found.");
        }
    }
    
     private boolean selectByPrimaryKey(String listId) throws SQLException {
            Debug.print("MessageDistributionEntityBean selectByPrimaryKey");

            makeConnection();
            String selectStatement = "SELECT list_id from " + DBHelper.USEA_MSS_DISTRIBUTION_LIST + " WHERE list_id = ?";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, listId);
            ResultSet rs = prepStmt.executeQuery();
            System.out.println("selectByPrimaryKey : Working ");
            boolean result = rs.next();
            prepStmt.close();
            releaseConnection();
            return result;
        }
    
    public Collection ejbFindByDistribitionListName(String listName) throws ObjectNotFoundException {
        Debug.print("Inside the ejbFindByDistribitionListName  contactlistId : "+listName);
        this.listName =listName;
        ArrayList array = new ArrayList();
        try {
            makeConnection();
            String selectStatement = "SELECT list_id,list_name,contactlist_id FROM  " +DBHelper.USEA_MSS_DISTRIBUTION_LIST+
                    " where list_name = ?";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, listName);
            rs = prepStmt.executeQuery();
            while (rs.next()) {
                for (int i=1; i<=3;i++)
                array.add(rs.getString(i));
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
    
    private boolean isNotNull(String data) {      
          return (data!=null && data.trim().length()>0) ? true :false;      
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
