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
/*
 * PriceDetailDAO.java
 *
 * Created on September 20, 2006, 11:16 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmeeting.util;
import javax.ejb.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import java.util.*;
import java.util.Date;
/**
 *
 * @author harmohan
 */
public class HLCPriceDetailDAO {
    private static final String dbName = "java:/ELMTMSSQLDS";
    private Connection con = null;
    private PreparedStatement prepStmt = null;
    private ResultSet rs = null;
    private PreparedStatement prepStmt1 = null;
    private ResultSet rs1 = null;
    
    
    private String priceId;
    private String specificationId;
    private String userTypeId;
    private String eventRegistrationTypeId;
    private double dueDatePrice;
    private double aftDueDatePrice;
    private String specId;
    
    /** Creates a new instance of PriceDetailDAO */
    public HLCPriceDetailDAO() {
    }
    
/**
  * Name         :listRowPriceDetail
  * Description  :This method will check for existence of specification id and user type id in the database
  * @ param      :specificationId,userTypeId,eventRegistrationTypeId,dueDatePrice,aftDueDatePrice
  * @return      :boolean
  * @throws      :SQLException
  */   
    public boolean listRowPriceDetail(String specId,String userTypId,String eventRegistrationTypeId) throws SQLException {
        Debug.print("PriceDetailDAO listRowPriceDetail");
         Debug.print(" After Select specificationId : "+specId);
             Debug.print(" After select userTypeId : "+userTypId);
        boolean bol = false;
        try {
            makeConnection();
            String insertStatement = "SELECT  price_id  FROM " + DBHelper.USEA__ANNUAL_PRICE +
                    " WHERE specification_id = ? and mem_type_id = ? and event_registration_type_id = ? ";
            prepStmt = con.prepareStatement(insertStatement);
            prepStmt.setString(1, specId);
            prepStmt.setString(2, userTypId);
            prepStmt.setString(3, eventRegistrationTypeId);
            rs = prepStmt.executeQuery();
            System.out.println("Inside the listRowPriceDetail  ");
            if (bol = rs.next()) {
                this.priceId = rs.getString(1);
            }
            
            Debug.print("  Select priceId : "+priceId);
          
            prepStmt.close();
            releaseConnection();
            Debug.print("Existing of specificationId AND  userTypeId  for priceId : " + bol);
        }catch(Exception e){
            releaseConnection();
            Debug.print("Error while checking specificationId AND  userTypeId in Price Details : "+e.getMessage());
        }finally {
            releaseConnection();
        }
        return bol;
     }
    
/**
  * Name         :checkEditPriceDetail
  * Description  :This method will check for existence of specification id and user type id in the database
  * @ param      :specificationId,userTypeId,eventRegistrationTypeId,dueDatePrice,aftDueDatePrice
  * @return      :boolean
  * @throws      :SQLException
  */   
    public boolean checkEditPriceDetail(String priceId, String specId,String userTypId,String eventRegistrationTypeId) throws SQLException {
        Debug.print("PriceDetailDAO checkEditPriceDetail");
        boolean bol = false;
        try {
            makeConnection();
            String insertStatement = "SELECT  price_id from " + DBHelper.USEA__ANNUAL_PRICE +
                    " WHERE specification_id = ? AND mem_type_id = ? and event_registration_type_id = ? and price_id! = ? ";
            prepStmt = con.prepareStatement(insertStatement);
            prepStmt.setString(1, specId);
            prepStmt.setString(2, userTypId);
            prepStmt.setString(3, eventRegistrationTypeId);
            prepStmt.setString(4, priceId);
            rs = prepStmt.executeQuery();
            if (bol = rs.next()) {
                this.priceId = rs.getString(1);
            }
            Debug.print("  Edit Checking priceId : " + priceId);
            prepStmt.close();
            releaseConnection();
            Debug.print("Existing of specificationId AND  userTypeId  for priceId : " + bol);
        }catch(Exception e){
            releaseConnection();
            Debug.print("Error while checking specificationId AND  userTypeId in Price Details : "+e.getMessage());
        }finally {
            releaseConnection();
        }
        return bol;
     }         
/**
  * Name         :insertRowPriceDetail
  * Description  :This method will insert record into the Price Details table
  * @ param      :specificationId,userTypeId,eventRegistrationTypeId,dueDatePrice,aftDueDatePrice
  * @return      :boolean
  * @throws      :SQLException
  */   
    public boolean insertRowPriceDetail(String specificationId,String userTypeId,String eventRegistrationTypeId,
            double dueDatePrice,double aftDueDatePrice) throws SQLException {
        Debug.print("PriceDetailDAO insertRowPriceDetail");
        try {
            makeConnection();
            String insertStatement = "insert into " + DBHelper.USEA__ANNUAL_PRICE + " (specification_id,mem_type_id,event_registration_type_id," +
                    " due_date_price, aft_due_date_price) values ( ? , ? , ? , ? , ? )";
            prepStmt = con.prepareStatement(insertStatement);

            prepStmt.setString(1, specificationId);
            prepStmt.setString(2, userTypeId);
            prepStmt.setString(3, eventRegistrationTypeId);
            prepStmt.setDouble(4, dueDatePrice);
            prepStmt.setDouble(5, aftDueDatePrice);

            int cnt = prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection();
            Debug.print("Successfully Inserted into PriceDetails......:" + cnt);
        }catch(Exception e){
            releaseConnection();
            Debug.print("Error while inserting Price Details : "+e.getMessage());
        }finally {
            releaseConnection();
        }
        return true;
     } 
     public boolean insertAnnualSpecificationDetails(String activityTypeId,String SpecName,String SpecDescription) throws SQLException {
        Debug.print("PriceDetailDAO insertAnnualSpecificationDetails");
        try {
            makeConnection();
            String insertStatement = "insert into " + DBHelper.USEA_SPECIFICATION_MASTER + " (activity_type_id,specification_name,specification_desc" +
                    " ) values ( ? , ? , ? )";
            prepStmt = con.prepareStatement(insertStatement);

            prepStmt.setString(1, activityTypeId);
            prepStmt.setString(2, SpecName);
            prepStmt.setString(3, SpecDescription);
            
            int cnt = prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection();
            Debug.print("Successfully Inserted into Specification Details Table .....:" + cnt);
        }catch(Exception e){
            releaseConnection();
            Debug.print("Error while inserting Specification Details : "+e.getMessage());
        }finally {
            releaseConnection();
        }
        return true;
     } 
     
     
/**
  * Name         :updateRowPriceDetail
  * Description  :This method will Update the due_date_price and  aft_due_date_price in the Price Details table
  * @ param      :specificationId,userTypeId,eventRegistrationTypeId,dueDatePrice,aftDueDatePrice
  * @return      :boolean
  * @throws      :SQLException
  */   
    public boolean updateRowPriceDetail(String priceId,
            double dueDatePrice,double aftDueDatePrice) throws SQLException {
        Debug.print("PriceDetailDAO insertRowPriceDetail");
        try {
            makeConnection();
            String insertStatement = "UPDATE " + DBHelper.USEA__ANNUAL_PRICE + " SET  due_date_price = ?, aft_due_date_price = ? "+
                    " WHERE  price_id = ? ";
            prepStmt = con.prepareStatement(insertStatement);
            prepStmt.setDouble(1, dueDatePrice);
            prepStmt.setDouble(2, aftDueDatePrice);
            prepStmt.setString(3, priceId);
            //prepStmt.setString(4, userTypeId);
           // prepStmt.setString(5, eventRegistrationTypeId);

            int cnt = prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection();
            Debug.print("Successfully Updated into PriceDetails......:" + cnt);
        }catch(Exception e){
            releaseConnection();
            Debug.print("Error while Updating Price Details : "+e.getMessage());
        }finally {
            releaseConnection();
        }
        return true;
     }     
    
/**
  * Name         :editRowPriceDetail
  * Description  :This method will Update the due_date_price and  aft_due_date_price in the Price Details table
  * @ param      :specificationId,userTypeId,eventRegistrationTypeId,dueDatePrice,aftDueDatePrice
  * @return      :boolean
  * @throws      :SQLException
  */   
    public boolean editRowPriceDetail(String priceId,
            String specificationId,String userTypeId,String eventRegistrationTypeId,
            double dueDatePrice,double aftDueDatePrice) throws SQLException {
        Debug.print("PriceDetailDAO editRowPriceDetail");
        try {
            makeConnection();
            String insertStatement = "UPDATE " + DBHelper.USEA__ANNUAL_PRICE + " SET  specification_id = ? ," +
                    " mem_type_id = ? , event_registration_type_id = ? , due_date_price = ?, aft_due_date_price = ? "+
                    " WHERE  price_id = ? ";
            prepStmt = con.prepareStatement(insertStatement);
            prepStmt.setString(1, specificationId);
            prepStmt.setString(2, userTypeId);
            prepStmt.setString(3, eventRegistrationTypeId);
            prepStmt.setDouble(4, dueDatePrice);
            prepStmt.setDouble(5, aftDueDatePrice);
            prepStmt.setString(6, priceId);

            int cnt = prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection();
            Debug.print("Successfully Updated into PriceDetails......: editRowPriceDetail()" + cnt);
            if(cnt<1){
                return false;
            }
        }catch(Exception e){
            releaseConnection();
            Debug.print("Error while Updating Price Details :editRowPriceDetail() "+e.getMessage());
        }finally {
            releaseConnection();
        }
        return true;
     }
    public ArrayList loadAllSpecDetails(boolean status) throws SQLException {
        ArrayList arrObj = new ArrayList();
        try {
                makeConnection();
                System.out.println("Inside the Loop loadAllSpecDetails");
                String specId = "select specification_id from "+DBHelper.USEA_SPECIFICATION_MASTER+" WHERE active_status = ?";
                PreparedStatement prepStmt1 = con.prepareStatement(specId);
                prepStmt1.setBoolean(1,status);
                ResultSet rs1 = prepStmt1.executeQuery();
                System.out.println("Spec Id Inside the loadAllSpecDetails  ");
                while (rs1.next()){
                    String specificationID = rs1.getString(1);
                    Debug.print("Spec Id Inside the loadAllSpecDetails  "+specificationID);
                    String selectStr = "select A.specification_id,B.activity_type_id,B.activity_type_name,A.specification_name,A.specification_desc,A.active_status,A.add_date from "+
                    DBHelper.USEA_SPECIFICATION_MASTER + " A, " +  DBHelper.USEA_ACTIVITY_CATEGORY + " B "+" where A.activity_type_id=B.activity_type_id and A.specification_id = ?";
                                   
                    PreparedStatement prepStmt = con.prepareStatement(selectStr);
                    prepStmt.setString(1,specificationID);
                    ResultSet rs = prepStmt.executeQuery();
                    while (rs.next()){
                        String specID =rs.getString(1);
                        String actId = rs.getString(2);
                        String actName =rs.getString(3);
                        String specName = rs.getString(4);
                        String specDesc = rs.getString(5);
                        boolean activeStatus = rs.getBoolean(6);
                        String AddDate = rs.getString(7);
                        String [] arenaName = {specID, actId,actName,specName,specDesc,Boolean.toString(activeStatus),AddDate};
                        arrObj.add(arenaName);
                    }
                    prepStmt.close();
                }
                prepStmt1.close();
                releaseConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return arrObj;
    }  
    
public ArrayList loadSpecDetails(String specId) throws SQLException {
        ArrayList arrObj = new ArrayList();
        try {
                makeConnection();
                String selectStr = "select A.specification_id,B.activity_type_id,B.activity_type_name,A.specification_name,A.specification_desc,A.active_status,A.add_date from "+
                    DBHelper.USEA_SPECIFICATION_MASTER + " A, " +  DBHelper.USEA_ACTIVITY_CATEGORY + " B "+" where A.activity_type_id=B.activity_type_id and A.specification_id = ?";
                PreparedStatement prepStmt = con.prepareStatement(selectStr);
                prepStmt.setString(1,specId);
               // prepStmt.setBoolean(2,true);
                ResultSet rs = prepStmt.executeQuery();
                while (rs.next()){
                        String specID =rs.getString(1);
                        String actId = rs.getString(2);
                        String actName =rs.getString(3);
                        String specName = rs.getString(4);
                        String specDesc = rs.getString(5);
                        boolean activeStatus = rs.getBoolean(6);
                        String AddDate = rs.getString(7);
                        String [] arenaName = {specID, actId,actName,specName,specDesc,Boolean.toString(activeStatus),AddDate};
                        arrObj.add(arenaName);
                    }
                prepStmt.close();
                releaseConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return arrObj;
    }        
  public boolean editAnnualspecDetail(String specificationId,String activityId,String specName,String specDesc) throws SQLException {
        Debug.print("PriceDetailDAO editAnnualspecDetail");
        try {
            makeConnection();
            String insertStatement = "UPDATE " + DBHelper.USEA_SPECIFICATION_MASTER + " SET  activity_type_id = ? ," +
                    " specification_name = ? , specification_desc = ? "+
                    " WHERE  specification_id = ? ";
            prepStmt = con.prepareStatement(insertStatement);
            prepStmt.setString(1, activityId);
            prepStmt.setString(2, specName);
            prepStmt.setString(3, specDesc);
            prepStmt.setString(4, specificationId);
           
            int cnt = prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection();
            Debug.print("Successfully Updated into Spec Details......: editAnnualspecDetail()" + cnt);
            if(cnt<1){
                return false;
            }
        }catch(Exception e){
            releaseConnection();
            Debug.print("Error while Updating Spec Details :editAnnualspecDetail() "+e.getMessage());
        }finally {
            releaseConnection();
        }
        return true;
     }  
public boolean deactivateSpecDetail(String specId) throws SQLException {
        boolean bol = false;
        makeConnection();
        PreparedStatement psDelete = null;
        int intChkReturn = 0;
        try {
            StringBuffer sbString = new StringBuffer();
            sbString.append("UPDATE "+DBHelper.USEA_SPECIFICATION_MASTER);
            sbString.append("  SET active_status = ? WHERE specification_id =?");
            psDelete = con.prepareStatement(sbString.toString());
            psDelete.setBoolean(1, false);
            psDelete.setString(2, specId);
            intChkReturn = psDelete.executeUpdate();
            Debug.print(" Succeffully Deleted Spec Detail : "+intChkReturn);
            bol = true;

        }
        catch (Exception ex) {
             releaseConnection();
             Debug.print("Error:  "+ex.getMessage());
        }
        finally {
             psDelete.close();
             releaseConnection();
        }
        return bol;
    }    
 public boolean activateSpecDetail(String specId) throws SQLException {
        boolean bol = false;
        makeConnection();
        PreparedStatement psDelete = null;
        int intChkReturn = 0;
        try {
            StringBuffer sbString = new StringBuffer();
            sbString.append("UPDATE "+DBHelper.USEA_SPECIFICATION_MASTER);
            sbString.append("  SET active_status = ? WHERE specification_id =?");
            psDelete = con.prepareStatement(sbString.toString());
            psDelete.setBoolean(1, true);
            psDelete.setString(2, specId);
            intChkReturn = psDelete.executeUpdate();
            Debug.print(" Succeffully activated Spec Detail master : "+intChkReturn);
            bol = true;

        }
        catch (Exception ex) {
             releaseConnection();
             Debug.print("Error:  "+ex.getMessage());
        }
        finally {
             psDelete.close();
             releaseConnection();
        }
        return bol;
    }  
 
 /* public boolean isSpecNameExist(String specName) {
        Debug.print("PriceDetailDAO isSpecNameExist");
        Debug.print(" specName:" + specName);
        
        makeConnection();
        boolean result = false;
        try{
            String selectStatement = "select specification_id from "+DBHelper.USEA_SPECIFICATION_MASTER+" WHERE specification_name = ?";
            PreparedStatement prepSelect = con.prepareStatement(selectStatement);
            prepSelect.setString(1, specName);
            
            ResultSet rs = prepSelect.executeQuery();
            result = rs.next();
            Debug.print(" Result:" + result);
            rs.close();
            prepSelect.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in PriceDetailDAO.isSpecNameExist():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in PriceDetailDAO.isSpecNameExist():" + e.getMessage());
        }
        return result;
    }*/
  /* public boolean isEditSpecNameExist(String specId,String specName) {
        Debug.print("PriceDetailDAO isEditSpecNameExist");
        Debug.print(" specId:" + specId);
        Debug.print(" specName:" + specName);
        makeConnection();
        boolean result = false;
        try{
            String selectStatement = "select specification_id from "+DBHelper.USEA_SPECIFICATION_MASTER+" WHERE specification_name = ? and specification_id!=?";
            PreparedStatement prepSelect = con.prepareStatement(selectStatement);
            prepSelect.setString(1, specName);
            prepSelect.setString(2, specId);
            ResultSet rs = prepSelect.executeQuery();
            result = rs.next();
            Debug.print(" Result:" + result);
            rs.close();
            prepSelect.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in PriceDetailDAO.isEditSpecNameExist():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in PriceDetailDAO.isEditSpecNameExist():" + e.getMessage());
        }
        return result;
    }*/

public boolean isSpecNameExist(String activityTypeId,String specName) throws SQLException {
        Debug.print("PriceDetailDAO isSpecNameExist");
        Debug.print(" After Select activityTypeId : "+activityTypeId);
        Debug.print(" After select specName : "+specName);
        boolean bol = false;
        try {
            makeConnection();
            String insertStatement = "SELECT  specification_id  FROM " + DBHelper.USEA_SPECIFICATION_MASTER +
                    " WHERE activity_type_id = ? and specification_name = ? ";
            prepStmt = con.prepareStatement(insertStatement);
            prepStmt.setString(1, activityTypeId);
            prepStmt.setString(2, specName);
            rs = prepStmt.executeQuery();
            System.out.println("Inside the isSpecNameExist  ");
            if (bol = rs.next()) {
                this.specId = rs.getString(1);
            }
            
            Debug.print("  Select specId : "+specId);
          
            prepStmt.close();
            releaseConnection();
            Debug.print("Existing of specificationName AND  activityTypeId  : " + bol);
        }catch(Exception e){
            releaseConnection();
            Debug.print("Error while checking specificationName AND  activityTypeId : "+e.getMessage());
        }finally {
            releaseConnection();
        }
        return bol;
     }   
public boolean isEditSpecNameExist(String specId,String activityTypeId,String specName) throws SQLException {
        Debug.print("PriceDetailDAO isEditSpecNameExist");
        boolean bol = false;
        try {
            makeConnection();
            String insertStatement = "SELECT  specification_id from " + DBHelper.USEA_SPECIFICATION_MASTER +
                    " WHERE activity_type_id = ? AND specification_name = ? and specification_id! = ? ";
            prepStmt = con.prepareStatement(insertStatement);
            prepStmt.setString(1, activityTypeId);
            prepStmt.setString(2, specName);
            prepStmt.setString(3, specId);
           
            rs = prepStmt.executeQuery();
            if (bol = rs.next()) {
                this.specId = rs.getString(1);
            }
            Debug.print("  Edit Checking specId : " + specId);
            prepStmt.close();
            releaseConnection();
            Debug.print("Existing of specification Name AND  activityTypeId  for specId : " + bol);
        }catch(Exception e){
            releaseConnection();
            Debug.print("Error while checking specification Name AND  activityTypeId in Price Details : "+e.getMessage());
        }finally {
            releaseConnection();
        }
        return bol;
     } 

/******************************************************EventRegistrationPriceCRUD*************************************************/
 public ArrayList selectAllPriceTypes() throws SQLException {
        Debug.print("PriceDetailsDAO selectAllPriceTypes()");
        ArrayList priceList = new ArrayList();
        makeConnection();
        try{
            String selectStatement = "select event_price_type_id, event_price_type_name from " + DBHelper.USEA_EVENT_REG_PRICE_MASTER+ " " ;
            PreparedStatement prepSelect = con.prepareStatement(selectStatement);
            
            ResultSet rs = prepSelect.executeQuery();
            while (rs.next()){
                String priceId = rs.getString(1);
                String priceName = rs.getString(2);
                String tempList[] = {priceId, priceName};
                priceList.add(tempList);
            }
            rs.close();
            prepSelect.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in PriceDetailsDAO.selectAllPriceTypes():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in PriceDetailsDAO.selectAllPriceTypes():" + e.getMessage());
        }
        return priceList;
    }
 /*==============================================================================================================================*/
 public ArrayList selectAllSeasons() throws SQLException {
        Debug.print("PriceDetailsDAO selectAllSeasons()");
        ArrayList seasonList = new ArrayList();
        makeConnection();
        try{
            String selectStatement = "select issue_id, issue_name from "+DBHelper.USEA_ISSUE_MASTER+" where omnibus_status = ? ";
            PreparedStatement prepSelect = con.prepareStatement(selectStatement);
            prepSelect.setBoolean(1,true);
            ResultSet rs = prepSelect.executeQuery();
            while (rs.next()){
                String seasonId = rs.getString(1);
                String seasonName = rs.getString(2);
                String tempList[] = {seasonId, seasonName};
                seasonList.add(tempList);
            }
            rs.close();
            prepSelect.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in PriceDetailsDAO.selectAllSeasons():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in PriceDetailsDAO.selectAllSeasons():" + e.getMessage());
        }
        return seasonList;
    }
 /*=============================================================================================================================*/
  public boolean isEventRegPriceExist(String priceTypeId, String seasonId) throws SQLException {
        Debug.print("PriceDetailsDAO.isEventRegPriceExist():");
        boolean result = false;
        try {
            makeConnection();
            
            String selectStatement = "SELECT event_reg_price_id FROM "+DBHelper.USEA_EVENT_REG_PRICE_DETAILS+ 
                    " WHERE event_price_type_id=? and issue_id= ? ";
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,priceTypeId);
            prepStmt.setString(2,seasonId);
       
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                result = true;
            }
            rs.close();
            Debug.print("PriceDetailsDAO isEventRegPriceExist() result: " + result);
            prepStmt.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in PriceDetailsDAO.isEventRegPriceExist():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in PriceDetailsDAO.isEventRegPriceExist():" + e.getMessage());
        }
        return result;
    }
  /*========================================================================================================================*/
  public boolean insertEventRegPriceDetails(String priceTypeId,String seasonId, float duePrice, float afterPrice) throws SQLException {
        Debug.print("PriceDetailsDAO.insertEventRegPriceDetails():");
        boolean result = false;
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String insertStatement = "insert into "+DBHelper.USEA_EVENT_REG_PRICE_DETAILS+" (event_price_type_id, issue_id, due_date_price, aft_due_date_price)" +
                    " values ( ?, ?, ?, ? ) ";
            
            prepStmt = con.prepareStatement(insertStatement);
            prepStmt.setString(1, priceTypeId);
            prepStmt.setString(2, seasonId);
            prepStmt.setFloat(3,duePrice);
            prepStmt.setFloat(4,afterPrice);
            int cnt = prepStmt.executeUpdate();
            Debug.print("Record Inserted succefully into PriceDetailsDAO insertEventRegPriceDetails "+cnt);
            if(cnt>=1){
                result = true;
            }
            Debug.print("PriceDetailsDAO insertEventRegPriceDetails() Status :" + result);
            prepStmt.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in PriceDetailsDAO.insertEventRegPriceDetails():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in PriceDetailsDAO.insertEventRegPriceDetails():" + e.getMessage());
        }
        return result;
    }
  /*===========================================================================================================================*/
   public ArrayList selectAllPriceDetails(String priceTypeId) throws SQLException{
        Debug.print("Inside DAO");
        ArrayList priceList = new ArrayList();
        try {
            makeConnection();
            String selStmt1 ="select A.event_reg_price_id, A.event_price_type_id, B.issue_name, A.due_date_price, A.aft_due_date_price from "+DBHelper.USEA_EVENT_REG_PRICE_DETAILS+ 
                    " A, "+DBHelper.USEA_ISSUE_MASTER+ " B where A.issue_id=B.issue_id and A.event_price_type_id=? ";
            Debug.print("Query: "+selStmt1);
            prepStmt = con.prepareStatement(selStmt1);
            prepStmt.setString(1,priceTypeId);
            Debug.print("result in selectAllPriceItems"+priceTypeId);
            rs = prepStmt.executeQuery();
            while(rs.next()) {
                String regPriceId=rs.getString(1);
                String priceTypeId1 = rs.getString(2);
                String seasonName = rs.getString(3);
                float duePrice = rs.getFloat(4);
                float afterPrice = rs.getFloat(5);
                String tempList[] = {regPriceId, priceTypeId1, seasonName, String.valueOf(duePrice), String.valueOf(afterPrice)};
                priceList.add(tempList);
            }
            Debug.print("PriceDetailsDAO.selectAllPriceDetails"+priceList.size());
            rs.close();
            prepStmt.close();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in PriceDetailsDAO.selectAllPriceDetails():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in PriceDetailsDAO.selectAllPriceDetails():" + e.getMessage());
        } finally {
            releaseConnection();
        }
        return priceList;
    }
   
   /*===========================================================================================================================*/
   public ArrayList selectSinglePriceDetails(String priceId) throws SQLException{
        Debug.print("Inside PriceDetailsDAO");
        ArrayList priceList = new ArrayList();
        try {
            makeConnection();
            String selStmt1 ="select A.event_reg_price_id, A.event_price_type_id, C.event_price_type_name, A.issue_id, B.issue_name, A.due_date_price, A.aft_due_date_price from "+DBHelper.USEA_EVENT_REG_PRICE_DETAILS+ 
                    " A, "+DBHelper.USEA_ISSUE_MASTER+ " B, "+DBHelper.USEA_EVENT_REG_PRICE_MASTER+ " C where A.issue_id=B.issue_id and A.event_price_type_id=C.event_price_type_id and A.event_reg_price_id=? ";
            Debug.print("Query: "+selStmt1);
            prepStmt = con.prepareStatement(selStmt1);
            prepStmt.setString(1,priceId);
            rs = prepStmt.executeQuery();
            while(rs.next()) {
                String regPriceId=rs.getString(1);
                String priceTypeId = rs.getString(2);
                String priceTypeName = rs.getString(3);
                String seasonId = rs.getString(4);
                String seasonName = rs.getString(5);
                float duePrice = rs.getFloat(6);
                float afterPrice = rs.getFloat(7);
                String tempList[] = {regPriceId, priceTypeId, priceTypeName, seasonId, seasonName, String.valueOf(duePrice), String.valueOf(afterPrice)};
                priceList.add(tempList);
            }
            Debug.print("PriceDetailsDAO.selectSinglePriceDetails"+priceList.size());
            rs.close();
            prepStmt.close();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in PriceDetailsDAO.selectSinglePriceDetails():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in PriceDetailsDAO.selectSinglePriceDetails():" + e.getMessage());
        } finally {
            releaseConnection();
        }
        return priceList;
    }
   
  /*==============================================================================================================================*/
   public boolean updateEventRegPriceDetails(String priceId,String priceTypeId,String seasonId, float duePrice, float afterPrice) {
        Debug.print("PriceDetailsDAO.updateEventRegPriceDetails():");
        boolean result = false;
        PreparedStatement prepStmt = null;
        
            makeConnection();
            try {
                String updateStatement = "update "+DBHelper.USEA_EVENT_REG_PRICE_DETAILS+" set event_price_type_id = ? , issue_id = ? , " +
                        " due_date_price=?, aft_due_date_price=? where event_reg_price_id = ?";
                
                prepStmt = con.prepareStatement(updateStatement);
                prepStmt.setString(1,priceTypeId);
                prepStmt.setString(2,seasonId);
                prepStmt.setFloat(3,duePrice);
                prepStmt.setFloat(4,afterPrice);
                prepStmt.setString(5,priceId);
                int cnt = prepStmt.executeUpdate();
                Debug.print("Record Inserted succefully into updateEventRegPriceDetails "+cnt);
                
                if(cnt>=1){
                    result = true;
                }
                
                Debug.print("PriceDetailsDAO updateEventRegPriceDetails() Status :" + result);
                prepStmt.close();
                releaseConnection();
            } catch(SQLException sql){
                releaseConnection();
                Debug.print("SQL Exception in PriceDetailsDAO.updateEventRegPriceDetails():" + sql.getMessage());
            } catch(Exception e){
                releaseConnection();
                Debug.print("General Exception  in PriceDetailsDAO.updateEventRegPriceDetails():" + e.getMessage());
            }
       
        return result;
    }
   
    /*===========================================================================================================================*/
   public ArrayList selectEventRegDateDetails(String seasonId,int year) throws SQLException{
        Debug.print("Inside PriceDetailsDAO");
        ArrayList priceList = new ArrayList();
        try {
            makeConnection();
            String selStmt1 ="select A.event_reg_date_id, A.issue_id,  B.issue_name, A.event_due_date, A.event_grace_date, A.competition_year from "+DBHelper.USEA_EVENT_REG_DATES+ 
                    " A, "+DBHelper.USEA_ISSUE_MASTER+ " B where A.issue_id=B.issue_id  and A.issue_id=? and A.competition_year=? ";
            Debug.print("Query: "+selStmt1);
            prepStmt = con.prepareStatement(selStmt1);
            prepStmt.setString(1,seasonId);
            prepStmt.setInt(2,year);
            rs = prepStmt.executeQuery();
            while(rs.next()) {
                String regDateId=rs.getString(1);
                String seaId = rs.getString(2);
                String seasonName = rs.getString(3);
                Date dueDate = rs.getDate(4);
                Date graceDate = rs.getDate(5);
                int compYear=rs.getInt(6);
                String tempList[] = {regDateId, seaId, seasonName, String.valueOf(dueDate), String.valueOf(graceDate), String.valueOf(compYear)};
                priceList.add(tempList);
            }
            Debug.print("PriceDetailsDAO.selectEventRegDateDetails"+priceList.size());
            rs.close();
            prepStmt.close();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in PriceDetailsDAO.selectEventRegDateDetails():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in PriceDetailsDAO.selectEventRegDateDetails():" + e.getMessage());
        } finally {
            releaseConnection();
        }
        return priceList;
    }
  /*========================================================================================================================*/
  public boolean insertEventRegDateDetails(String regDateId,String seasonId, Date dueDate, Date graceDate, int year) throws SQLException {
        Debug.print("PriceDetailsDAO.insertEventRegDateDetails():");
        boolean result = false;
        PreparedStatement prepStmt = null;
        String insertStatement="";
        makeConnection();
        try {
            if(regDateId!=null && regDateId.trim().length()!=0){
                
            insertStatement = "update "+DBHelper.USEA_EVENT_REG_DATES+" set issue_id = ? , " +
                        " event_due_date=?, event_grace_date=?, competition_year=?  where event_reg_date_id = ?";  
            prepStmt = con.prepareStatement(insertStatement);
            
            prepStmt.setString(1, seasonId);
            prepStmt.setDate(2, DBHelper.toSQLDate(dueDate));
            prepStmt.setDate(3, DBHelper.toSQLDate(graceDate));
            prepStmt.setInt(4,year);
            prepStmt.setString(5, regDateId);
            }else{
            insertStatement = "insert into "+DBHelper.USEA_EVENT_REG_DATES+" (issue_id, event_due_date, event_grace_date, competition_year)" +
                    " values ( ?, ?, ?, ? ) ";
            prepStmt = con.prepareStatement(insertStatement);
            prepStmt.setString(1, seasonId);
            prepStmt.setDate(2, DBHelper.toSQLDate(dueDate));
            prepStmt.setDate(3, DBHelper.toSQLDate(graceDate));
            prepStmt.setInt(4,year);
            }
          
            int cnt = prepStmt.executeUpdate();
            Debug.print("Record Inserted succefully into PriceDetailsDAO insertEventRegDateDetails "+cnt);
            if(cnt>=1){
                result = true;
            }
            Debug.print("PriceDetailsDAO insertEventRegDateDetails() Status :" + result);
            prepStmt.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in PriceDetailsDAO.insertEventRegDateDetails():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in PriceDetailsDAO.insertEventRegDateDetails():" + e.getMessage());
        }
        return result;
    } 
 
 /*********************************************************************************************************************************/
/**
  * Name         :makeConnection
  * Description  :This method will create the databacse connection
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