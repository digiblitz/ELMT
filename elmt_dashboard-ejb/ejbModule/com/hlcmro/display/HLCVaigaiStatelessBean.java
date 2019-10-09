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
// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OrganizerFormDisplayBean.java
/*====================================================================================    
 *
 * Author : Suresh & Sharma
 *
 *======================================================================================*/

package com.hlcmro.display;


import java.rmi.*;
import java.sql.*;
import java.util.Vector;
import java.util.*;
import javax.ejb.*;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import com.hlcmro.util.Debug;

// Referenced classes of package com.mro.display:
//            OrganizerFormDisplayRemoteBusiness

public class HLCVaigaiStatelessBean
    implements SessionBean {

    public HLCVaigaiStatelessBean(){}
    private SessionContext context;
    private Connection con;
    int cross_country_id;
    Vector vecObj;
    String returnStausValue = null;
    Vector vecValue = null;
    Vector list = null;

    public void setSessionContext(SessionContext aContext){
        context = aContext;
        Debug.print("This is from  setSessionContext");
    }

    public void ejbActivate() {
        Debug.print("This is from  ejbActivate");
    }

    public void ejbPassivate(){
    Debug.print("This is from ejbPassivate");
    }

    public void ejbRemove() {
    Debug.print("This is from ejbRemove");
    }

    public void ejbCreate()  throws CreateException, RemoteException{
        Debug.print("This is from ejbCreate");
    }

    /*===========================TYPE AND LEVEL MAPPING==========================================================*/    
    public String createNewType(String strEvtTyName) throws RemoteException {
        try {
           returnStausValue =  insertNewType(strEvtTyName);
        } catch (Exception ex) {
             ex.printStackTrace();
        }
        return returnStausValue;
    }
    
    /**
 * Name         :insertNewType
 * Description  :This method is used to insert new record into tblMeeEventTypeMaster table
 * @ param      :event_type_name
 * @return      :String
 * @throws      :SQLException
 */
    public String insertNewType(String strEvtTyName) throws SQLException {
    String returnValue = null;
    Debug.print("This is from insertNewType Calling");
    try {
        boolean result = isTypeExist(strEvtTyName);
        Debug.print("THis is from createNewType: Calling isTypeExist: " + result);
        if(!result) {
            makeConnection();
            String insertStatement = "insert into tblMeeEventTypeMaster (event_type_name) values (?)";
            PreparedStatement prepStmt = con.prepareStatement(insertStatement);
            prepStmt.setString(1, strEvtTyName);
            int insertResult = prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection();
            if(insertResult != 0)
                returnValue = "success";
        }
        else{
         returnValue = "exist";
        }
    }
    catch(Exception ex) {
            ex.printStackTrace();
    }
    return returnValue;
    }

    public String createNewLevel(String strEvtLevName, String strEvtLevCode, String strEvtLevJump) throws RemoteException  {
    try {
       returnStausValue =  insertNewLevel(strEvtLevName,strEvtLevCode,strEvtLevJump);
    } catch (Exception ex) {
         ex.printStackTrace();
    }
    return returnStausValue;
    }

 /**
 * Name         :insertNewLevel
 * Description  :This method is used to insert new record into tblMeeEventLevelMaster table
 * @ param      :strEvtLevName,strEvtLevCode,strEvtLevJump
 * @return      :String
 * @throws      :SQLException
 */
    
    public String insertNewLevel(String strEvtLevName, String strEvtLevCode, String strEvtLevJump) throws SQLException  {
    String returnValue = null;
    Debug.print("THis is from createNewLevel Calling");
    try {
        // boolean result = isLevelExist(strEvtLevName);
        //Debug.print("THis is from createNewLevel: Calling isLevelExist: " + result);
        //  if(!result)
        // {
        makeConnection();
        String insertStatement = "insert into tblMeeEventLevelMaster (event_level_name,event_level_code,jumping_efforts) values (?,?,?)";
        PreparedStatement prepStmt = con.prepareStatement(insertStatement);
        prepStmt.setString(1, strEvtLevName);
        prepStmt.setString(2, strEvtLevCode);
        prepStmt.setString(3, strEvtLevJump);
        int insertResult = prepStmt.executeUpdate();
        prepStmt.close();
        releaseConnection();
        if(insertResult != 0)
            returnValue = "success";
        // }
        // else{
        //     returnValue="exist";
        //  }
    }
    catch(Exception ex){
        ex.printStackTrace();
        Debug.print("createNewLevel: " + ex.getMessage());
    }
    return returnValue;
    }

  /**
 * Name         :isTypeExist
 * Description  :This method is used to check whether this type is already exist or not in  tblMeeEventTypeMaster table
 * @ param      :strEvtTyName
 * @return      :bollean
 * @throws      :SQLException
 */
    public boolean isTypeExist(String strEvtTyName) throws SQLException {
    boolean result = false;
    Debug.print("This is from isTypeExist");
    try  {
        makeConnection();
        String selectStatement = "select event_type_id from tblMeeEventTypeMaster where event_type_name = ? ";
        PreparedStatement prepStmt = con.prepareStatement(selectStatement);
        prepStmt.setString(1, strEvtTyName);
        ResultSet rs = prepStmt.executeQuery();
        result = rs.next();
        prepStmt.close();
        releaseConnection();
    }
    catch(Exception ede) {
      ede.printStackTrace();
      Debug.print("Exception occur while checking existence of Type" + ede);
    }
    return result;
    }
    
  /**
 * Name         :isLevelExist
 * Description  :This method is used to check whether this level already exist or not tblMeeEventLevelMaster table
 * @ param      :strEvtLevName
 * @return      :bollean
 * @throws      :SQLException
 */
    
    public boolean isLevelExist(String strEvtLevName) throws SQLException {
    boolean result = false;
    Debug.print("THis is from isLevelExist");
    try {
        makeConnection();
        String selectStatement = "select event_level_id from tblMeeEventLevelMaster where event_level_name = ? ";
        PreparedStatement prepStmt = con.prepareStatement(selectStatement);
        prepStmt.setString(1, strEvtLevName);
        ResultSet rs = prepStmt.executeQuery();
        result = rs.next();
        prepStmt.close();
        releaseConnection();
    }
    catch(Exception ede){
        ede.printStackTrace();
        Debug.print("Exception occur while checking existence of Level" + ede);
    }
    return result;
    }
    
        
  /**
 * Name         :isTypeEditExist
 * Description  :This method is used to check whether this type already exist or not in tblMeeEventTypeMaster table
 * @ param      :uniEvtTyID,strEvtTyName
 * @return      :bollean
 * @throws      :SQLException
 */

    public boolean isTypeEditExist(String uniEvtTyID, String strEvtTyName) throws SQLException {
    boolean result = false;
    Debug.print("THis is from isTypeExist");
    try{
        makeConnection();
        String selectStatement = "select event_type_id from tblMeeEventTypeMaster where event_type_name = ? and event_type_id != ? ";
        PreparedStatement prepStmt = con.prepareStatement(selectStatement);
        prepStmt.setString(1, strEvtTyName);
        prepStmt.setString(2, uniEvtTyID);
        ResultSet rs = prepStmt.executeQuery();
        result = rs.next();
        prepStmt.close();
        releaseConnection();
    }
    catch(Exception ede) {
        ede.printStackTrace();
        Debug.print("Exception occur while checking existence of Type" + ede);
    }
    return result;
    }

      /**
 * Name         :isLevelEditExist
 * Description  :This method is used to check whether this type already exist or not in  tblMeeEventLevelMaster table
 * @ param      :uniEvtTyID,strEvtTyName
 * @return      :bollean
 * @throws      :SQLException
 */
    public boolean isLevelEditExist(String uniEvtLevID,String strEvtLevName) throws SQLException  {
        boolean result = false;
        Debug.print("THis is from isLevelExist");
        try {
            makeConnection();
            String selectStatement = "select event_level_id from tblMeeEventLevelMaster where event_level_name = ? and event_level_id= ? ";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, strEvtLevName);
            prepStmt.setString(2, uniEvtLevID);
            ResultSet rs = prepStmt.executeQuery();
            result = rs.next();
            prepStmt.close();
            releaseConnection();
        }
        catch(Exception ede){
            ede.printStackTrace();
            Debug.print("Exception occur while checking existence of Level" + ede);
        }
        return result;
    }
      
    public Vector getAllTypes() throws RemoteException {
        Debug.print("Getting All Types");
        try {
            vecValue = loadAllTypes();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return vecValue;
    }

/**
 * Name         :loadAllTypes
 * Description  :This method is used to get all the records from  tblMeeEventTypeMaster table
 * @ param      :
 * @return      :Vector
 * @throws      :SQLException
 */
    public Vector loadAllTypes() throws SQLException {
        Vector a = new Vector();
        Debug.print("THis is from getAllTypes");
        try {
            makeConnection();
            String selectStatement = "select event_type_id,event_type_name,active_status,add_date from tblMeeEventTypeMaster ";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            ResultSet rs = prepStmt.executeQuery();

            while(rs.next()){
                String id = rs.getString(1);
                String name = rs.getString(2);
                String status = rs.getString(3);
                String add_date = rs.getString(4);
                String[] types= {id,name,status,add_date};
                a.add(types);
            }
            prepStmt.close();
            releaseConnection();
        }
        catch(Exception ede){
             ede.printStackTrace();
            Debug.print("Exception occur while getting all Types" + ede);
        }
        return a;
    }

    public Vector getAllLevels() throws RemoteException {
        Debug.print("Getting All Levels");
        try {
            vecValue = loadAllLevels();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return vecValue;
    }
       
/**
 * Name         :loadAllLevels
 * Description  :This method is used to get all the records from  tblMeeEventLevelMaster table
 * @ param      :
 * @return      :Vector
 * @throws      :SQLException
 */
    public Vector loadAllLevels() throws  SQLException {
        Debug.print("This is from getAllLevels");
        Vector a = new Vector();
        try {
            makeConnection();
            String selectStatement = "select event_level_id,event_level_name,event_level_code,jumping_efforts from tblMeeEventLevelMaster";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            ResultSet rs = prepStmt.executeQuery();
            Debug.print("This is from getAllLevels After Connection Making");
            while(rs.next()){
                String id = rs.getString(1);
                String name = rs.getString(2);
                String code = rs.getString(3);
                String jumping_efforts = rs.getString(4);
                String[] levels= {id,name,code,jumping_efforts};
                Debug.print("This is from getAllLevels in side loop:" + name);
                a.add(levels);
            }
            prepStmt.close();
            releaseConnection();
        }
        catch(Exception ede){
            ede.getStackTrace();
            Debug.print("Exception occur while All getting Levels" + ede);
        }
        return a;
    }

    public Vector getType(String uniEvtTyID) throws RemoteException {
        Debug.print("Getting All Levels");
        try {
            list = (Vector)loadType(uniEvtTyID);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }
            
 /**
 * Name         :loadType
 * Description  :This method is used to get a particular type record from  tblMeeEventTypeMaster table
 * @ param      :uniEvtTyID
 * @return      :Vector
 * @throws      :SQLException
 */
public Vector loadType(String uniEvtTyID) throws SQLException {
Vector a = new Vector();
Debug.print("THis is from getType");
try {
    makeConnection();
    String selectStatement = "select event_type_id,event_type_name,active_status,add_date from tblMeeEventTypeMaster where event_type_id = ? ";
    PreparedStatement prepStmt = con.prepareStatement(selectStatement);
    prepStmt.setString(1, uniEvtTyID);
    ResultSet rs = prepStmt.executeQuery();
    while(rs.next()){
        a.add(rs.getString(1));
        a.add(rs.getString(2));
        a.add(rs.getString(3));
        a.add(rs.getString(4));
    }
    prepStmt.close();
    releaseConnection();
}
catch(Exception ede){
    ede.printStackTrace();
    Debug.print("Exception occur while getting Type" + ede);
}
return a;
}

public Vector getLevel(String uniEvtLevID) throws RemoteException {
        Debug.print("Getting All Levels");
        try {
            list = (Vector)loadLevel(uniEvtLevID);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

/**
 * Name         :loadLevel
 * Description  :This method is used to get a particular record from  tblMeeEventLevelMaster table
 * @ param      :uniEvtLevID
 * @return      :Vector
 * @throws      :SQLException
 */
     
    public Vector loadLevel(String uniEvtLevID) throws SQLException  {
        Vector a = new Vector();
        try {
        makeConnection();
        String selectStatement = "select event_level_id,event_level_name,event_level_code,jumping_efforts from tblMeeEventLevelMaster where event_level_id = ? ";
        PreparedStatement prepStmt = con.prepareStatement(selectStatement);
        prepStmt.setString(1, uniEvtLevID);
        ResultSet rs = prepStmt.executeQuery();
        while(rs.next()){
            a.add(rs.getString(1));
            a.add(rs.getString(2));
            a.add(rs.getString(3));
            a.add(rs.getString(4));
        }

        prepStmt.close();
        releaseConnection();
        }
        catch(Exception ede) {
            Debug.print("Exception occur while getting Level" + ede);
        }
        return a;
    }
    
    public String editType(String uniEvtTyID, String strEvtTyName) throws RemoteException {
        Debug.print("Edit Type for " + uniEvtTyID);
        try {
            returnStausValue = updateType(uniEvtTyID, strEvtTyName);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return returnStausValue;
    }
    
    
/**
 * Name         :updateType
 * Description  :This method is used to update a particular record from  tblMeeEventTypeMaster table
 * @ param      :uniEvtTyID,strEvtTyName
 * @return      :String
 * @throws      :SQLException
 */
     
    public String updateType(String uniEvtTyID, String strEvtTyName) throws SQLException  {
        String  returnVlaue = null;
        try {
            boolean result = isTypeEditExist(uniEvtTyID,strEvtTyName);
            Debug.print("THis is from editType: Calling isTypeEditExist: " + result);
            if(!result) {
                makeConnection();
                String updateStatement = "update tblMeeEventTypeMaster set event_type_name = ? where event_type_id = ? ";
                PreparedStatement prepStmt = con.prepareStatement(updateStatement);
                prepStmt.setString(1, strEvtTyName);
                prepStmt.setString(2, uniEvtTyID);
                int rowCount = prepStmt.executeUpdate();
                prepStmt.close();
                releaseConnection();
                if(rowCount != 0)
                    returnVlaue = "success";
            }
            else{
                returnVlaue = "exist";
            }
        }
        catch(Exception ede) {
            Debug.print("Exception occur while Editing Type" + ede);
        }
        return returnVlaue;
    }

     public String editLevel(String uniEvtLevID, String strEvtLevName, String strEvtLevCode, String strEvtLevJump) throws RemoteException {
        Debug.print("Edit Level for " + uniEvtLevID);
        try {
            returnStausValue = updateLevel(uniEvtLevID, strEvtLevName, strEvtLevCode, strEvtLevJump);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return returnStausValue;
    }
    
/**
 * Name         :updateLevel
 * Description  :This method is used to update a particular record from  tblMeeEventTypeLevel table
 * @ param      :uniEvtLevID,strEvtLevName,strEvtLevCode,strEvtLevJump
 * @return      :String
 * @throws      :SQLException
 */
     
    public String updateLevel(String uniEvtLevID, String strEvtLevName, String strEvtLevCode, String strEvtLevJump) throws SQLException {
        String  returnVlaue = null;
        try {
            //boolean result = isLevelEditExist(uniEvtLevID,strEvtLevName);
            // Debug.print("THis is from editType: Calling isTypeEditExist: " + result);
            // if(!result) {
                makeConnection();
                String updateStatement = "update tblMeeEventLevelMaster set event_level_name = ? ,event_level_code = ? , jumping_efforts = ? where event_level_id = ? ";
                PreparedStatement prepStmt = con.prepareStatement(updateStatement);
                prepStmt.setString(1, strEvtLevName);
                prepStmt.setString(2, strEvtLevCode);
                prepStmt.setString(3, strEvtLevJump);
                prepStmt.setString(4, uniEvtLevID);
                int rowCount = prepStmt.executeUpdate();
                prepStmt.close();
                releaseConnection();
                 if(rowCount != 0)
                    returnVlaue = "success";
            // }
            // else{
            //   returnVlaue = "exist";
            // }
        }

        catch(Exception ede)  {
            Debug.print("Exception occur while Editing Level" + ede);
        }
        return returnVlaue;
    }
/**
 * Name         :deleteType
 * Description  :This method is used to delete a particular record from tblMeeEventTypeMaster table
 * @ param      :uniEvtTyID
 * @return      :int
 * @throws      :SQLException
 */
    public int deleteType(String uniEvtTyID) throws RemoteException {
        int rowCount = 0;
        try {
            makeConnection();
            String deleteStatement = "delete from tblMeeEventTypeMaster where event_type_id = ? ";
            Debug.print("SQL Query:" + deleteStatement);
            PreparedStatement prepStmt = con.prepareStatement(deleteStatement);
            prepStmt.setString(1, uniEvtTyID);
            rowCount = prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection();
        }
        catch(Exception ede) {
            ede.printStackTrace();
            Debug.print("Exception occur while deleting Type" + ede);
        }
        return rowCount;
    }
/**
 * Name         :deleteLevel
 * Description  :This method is used to delete a particular record from tblMeeEventLevelMaster table
 * @ param      :uniEvtLevID
 * @return      :int
 * @throws      :SQLException
 */
    public int deleteLevel(String uniEvtLevID) throws RemoteException   {
        int rowCount = 0;
        try {
            makeConnection();
            String deleteStatement = "delete from tblMeeEventLevelMaster where event_level_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(deleteStatement);
            prepStmt.setString(1, uniEvtLevID);
            rowCount = prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection();
        }
        catch(Exception ede)
        {
        Debug.print("Exception occur while deleting Level" + ede);
        }
        return rowCount;
    }

    public void generateMapForTypeLEvel(String uniEvtTyID, Vector uniEvtLevIDs) throws RemoteException {
        try{
            makeConnection();
            String deleteStatement = "delete from tblMeeMapEventLevel where event_type_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(deleteStatement);
            prepStmt.setString(1, uniEvtTyID);
            int rowCount = prepStmt.executeUpdate();
            prepStmt.close();

            String insertStatement = "insert into tblMeeMapEventLevel (event_type_id,event_level_id) values (?,?)";
            prepStmt = con.prepareStatement(insertStatement);
            Enumeration e = uniEvtLevIDs.elements();
            while(e.hasMoreElements()){
                String uniEvtLevID = (String)e.nextElement();
                if(!uniEvtTyID.equals("") && uniEvtTyID!=null && !uniEvtLevIDs.equals("") && uniEvtLevIDs!=null  ){
                    prepStmt.setString(1, uniEvtTyID);
                    prepStmt.setString(2, uniEvtLevID);
                    prepStmt.executeUpdate();
                }
            }
            prepStmt.close();
            releaseConnection();
        }
        catch(Exception ede){
            Debug.print("Exception occur while Mapping Type and Level" + ede);
        }
    }

    public Vector getAllMappingLevels(String uniEvtTyID) throws RemoteException {
        Debug.print("Getting All Mapping Levels");
        try {
            vecValue = loadMappingLevels(uniEvtTyID);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return vecValue;
    }
    
    
     public Vector getAllMapTyLe() throws RemoteException{
        
        Vector vecObj = new Vector();
        try {
                makeConnection();
                System.out.println("Inside the Loop getAllMapTyLe");
                String typeId = "select event_type_id from tblMeeEventTypeMaster";
                PreparedStatement prepStmt1 = con.prepareStatement(typeId);
                ResultSet rs1 = prepStmt1.executeQuery();
                //System.out.println("Main TypeIds:" + typeId);
                //System.out.println("Event Id Inside the getAllMapTyLe  ");
                while (rs1.next()){
                    String eventID = rs1.getString(1);
                    
                     System.out.println("Event Id Inside the getAllMapTyLe  "+ typeId);
                    String selectStr = "select A.map_type_id,A.event_type_id,A.event_level_id, B.event_type_name, C.event_level_code, C.event_level_name "+
                    "from tblMeeMapEventLevel A, tblMeeEventTypeMaster B, tblMeeEventLevelMaster C  "+
                    "where A.event_type_id = B.event_type_id and A.event_level_id = C.event_level_id and A.event_type_id = ?" ;
                    
                    PreparedStatement prepStmt = con.prepareStatement(selectStr);
                     System.out.println("eventID:" + eventID);
                    prepStmt.setString(1,eventID);
                    ResultSet rs = prepStmt.executeQuery();
                    while (rs.next()){
                        String mapid = rs.getString(1);
                        //System.out.println("mapid:" + mapid);
                        String tyid = rs.getString(2);
                        //System.out.println("tyid:" + tyid);
                        String leid = rs.getString(3);
                        //System.out.println("leid:" + leid);
                        String tyname = rs.getString(4);
                        //System.out.println("tyname:" + tyname);
                        String levelcode = rs.getString(5);
                        //System.out.println("levelcode:" + levelcode);
                        String levelName = rs.getString(6);
                        //System.out.println("levelName:" + levelName);
                        String [] eventName = {mapid,tyid,leid,tyname,levelcode,levelName};
                        vecObj.add(eventName);
                    }
                    prepStmt.close();
                }
        
                prepStmt1.close();
                releaseConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return vecObj;
    }    
    
/**
 * Name         :loadMappingLevels
 * Description  :This method is used to get all mapping records from tblMeeMapEventLevel,tblMeeEventTypeMaster,tblMeeEventLevelMaster
 * @ param      :uniEvtTyID
 * @return      :Vector
 * @throws      :SQLException
 */
    public Vector loadMappingLevels(String uniEvtTyID) throws SQLException {
        Debug.print("This is from getAllMappingLevels");
        Vector a = new Vector();
        try {
            makeConnection();
            String selectStatement = "select A.map_type_id,A.event_type_id,A.event_level_id, B.event_type_name, C.event_level_code " + 
               " from tblMeeMapEventLevel A, tblMeeEventTypeMaster B, tblMeeEventLevelMaster C " + 
               " where A.event_type_id = B.event_type_id and A.event_level_id = C.event_level_id and A.event_type_id = ?" ;
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, uniEvtTyID);
            ResultSet rs = prepStmt.executeQuery();

            while(rs.next()){
                String mapid = rs.getString(1);
                 String type_id = rs.getString(2);
                String levelid = rs.getString(3);
                String evt_name = rs.getString(4);
                String level_code = rs.getString(5);
                String[] maplevels= {mapid,type_id,levelid,evt_name,level_code};
                a.add(maplevels);
            }
            prepStmt.close();
            releaseConnection();
        }
        catch(Exception ede){
            ede.printStackTrace();
            Debug.print("Exception occur while All getting Levels" + ede);
        }
        return a;
    }
    
/*===========================Rule/Sub Rule Manupulation==========================================================*/    
   
    
    public String createRuleType(String strRuleTyName) throws RemoteException {
        try {
           returnStausValue =  insertRuleType(strRuleTyName);
        } catch (Exception ex) {
             ex.printStackTrace();
        }
        return returnStausValue;
    }
    
/* Name         :insertRuleType
 * Description  :This method is used to insert new record into tblMeeRefundRuleMaster table
 * @ param      :strRuleTyName
 * @return      :String
 * @throws      :SQLException
 */
        
    public String insertRuleType(String strRuleTyName) throws SQLException {
        String returnValue = null;
        Debug.print("This is from createRuleType Calling");
        try{
            boolean result = isRuleTypeExist(strRuleTyName);
            Debug.print("This is from createRuleType: Calling isRuleTypeExist: " + result);
            if(!result){
                makeConnection();
                String insertStatement = "insert into tblMeeRefundRuleMaster (refund_rule_type_name) values (?)";
                PreparedStatement prepStmt = con.prepareStatement(insertStatement);
                prepStmt.setString(1, strRuleTyName);
                int insertResult = prepStmt.executeUpdate();
                prepStmt.close();
                releaseConnection();
                if(insertResult != 0)
                    returnValue = "success";
            }
            else{
                returnValue = "exist";
            }
        }
        catch(Exception ex){
            Debug.print("createNewType: " + ex.getMessage());
        }
        return returnValue;
    }

    public String createSubRuleType(String strSubRuleName, String strSubRuletxtStatus) throws RemoteException {
        try {
           returnStausValue =  insertSubRuleType(strSubRuleName,strSubRuletxtStatus);
        } catch (Exception ex) {
             ex.printStackTrace();
        }
        return returnStausValue;
    }
 /* Name        :insertSubRuleType
 * Description  :This method is used to insert new record into tblMeeRefundSubRuleMaster table
 * @ param      :strSubRuleName,strSubRuletxtStatus
 * @return      :String
 * @throws      :SQLException
 */           
    
    public String insertSubRuleType(String strSubRuleName, String strSubRuletxtStatus) throws SQLException {
        String returnValue = null;
        Debug.print("This is from insertSubRuleType Calling");
        try {
            boolean result = isSubRuleTypeExist(strSubRuleName);
            Debug.print("THis is from createSubRuleType: Calling isSubRuleLevelExist: " + result);
            if(!result) {
                makeConnection();
                String insertStatement = "insert into tblMeeRefundRuleSubTypeMaster (refund_rule_sub_type_name,text_box_status) values (?,?)";
                PreparedStatement prepStmt = con.prepareStatement(insertStatement);
                prepStmt.setString(1, strSubRuleName);
                prepStmt.setString(2, strSubRuletxtStatus);
                int insertResult = prepStmt.executeUpdate();
                prepStmt.close();
                releaseConnection();
                if(insertResult != 0)
                    returnValue = "success";
                 }
            else{
                returnValue="exist";
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
            Debug.print("createSubRuleType: " + ex.getMessage());
        }
        return returnValue;
    }

/* Name         :isRuleTypeExist
 * Description  :This method is used to check whether this type is already exist or not in tblMeeRefundRuleMaster table
 * @ param      :strRuleTyName
 * @return      :boolean
 * @throws      :SQLException
 */
    
    public boolean isRuleTypeExist(String strRuleTyName) throws SQLException {
        boolean result = false;
        Debug.print("This is from isRuleTypeExist");
        try {
            makeConnection();
            String selectStatement = "select refund_rule_type_id from tblMeeRefundRuleMaster where refund_rule_type_name = ? ";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, strRuleTyName);
            ResultSet rs = prepStmt.executeQuery();
            result = rs.next();
            prepStmt.close();
            releaseConnection();
        }
        catch(Exception ede){
            Debug.print("Exception occur while checking existence of RuleType" + ede);
        }
        return result;
    }

/* Name         :isSubRuleTypeExist
 * Description  :This method is used to check whether this sub rule type is already exist or not in tblMeeRefundSubRuleMaster table
 * @ param      :strSubRuleName
 * @return      :boolean
 * @throws      :SQLException
 */
    
    public boolean isSubRuleTypeExist(String strSubRuleName) throws SQLException {
        boolean result = false;
        Debug.print("THis is from isLevelExist");
        try{
            makeConnection();
            String selectStatement = "select refund_rule_sub_type_id from tblMeeRefundRuleSubTypeMaster where refund_rule_sub_type_name = ? ";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, strSubRuleName);
            ResultSet rs = prepStmt.executeQuery();
            result = rs.next();
            prepStmt.close();
            releaseConnection();
        }
        catch(Exception ede) {
            Debug.print("Exception occur while checking existence of Sub Rule" + ede);
        }
        return result;
    }

/* Name         :isRuleTypeEditExist
 * Description  :This method is used to check whether this sub rule type is already exist or not in tblMeeRefundRuleMaster table
 * @ param      :uniRuleTyID,strSubRuleName
 * @return      :boolean
 * @throws      :SQLException
 */
    
    public boolean isRuleTypeEditExist(String uniRuleTyID, String strSubRuleName) throws SQLException {
        boolean result = false;
        Debug.print("THis is from isRuleTypeEditExist");
        try {
            makeConnection();
            String selectStatement = "select refund_rule_type_id from tblMeeRefundRuleMaster where refund_rule_type_name = ? and refund_rule_type_id != ? ";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, strSubRuleName);
            prepStmt.setString(2, uniRuleTyID);
            ResultSet rs = prepStmt.executeQuery();
            result = rs.next();
            prepStmt.close();
            releaseConnection();
        }
        catch(Exception ede) {
            Debug.print("Exception occur while checking existence of Type" + ede);
        }
        return result;
    }


 /* Name        :isSubRuleLevelEditExist
 * Description  :This method is used to insert new record into tblMeeRefundSubRuleMaster table
 * @ param      :uniRuleTyID,strSubRuleName
 * @return      :boolean
 * @throws      :SQLException
 */
    public boolean isSubRuleLevelEditExist(String uniSubRuleID,String strSubRuleName) throws SQLException  {
        boolean result = false;
        Debug.print("This is from isLevelExist");
        try {
        makeConnection();
            String selectStatement = "select refund_rule_sub_type_id from tblMeeRefundRuleSubTypeMaster where refund_rule_sub_type_name = ? and refund_rule_sub_type_id= ? ";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, strSubRuleName);
            prepStmt.setString(2, uniSubRuleID);
            ResultSet rs = prepStmt.executeQuery();
            result = rs.next();
            prepStmt.close();
            releaseConnection();
        }
        catch(Exception ede){
            Debug.print("Exception occur while checking existence of Level" + ede);
        }
        return result;
    }

    public Vector getAllRuleTypes() throws RemoteException {
        Debug.print("Getting All Types");
        try {
            vecValue = loadAllRuleTypes();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return vecValue;
    }
   
 /**
 * Name         :loadAllRuleTypes
 * Description  :This method is used to get all rule type record from  tblMeeRefundRuleMaster table
 * @ param      :
 * @return      :Vector
 * @throws      :SQLException
 */
    
    public Vector loadAllRuleTypes() throws SQLException {
        Vector a = new Vector();
        Debug.print("This is from getAllRuleTypes");
        try {
            makeConnection();
            String selectStatement = "select refund_rule_type_id,refund_rule_type_name,active_status,add_date from tblMeeRefundRuleMaster ";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            ResultSet rs = prepStmt.executeQuery();

            while(rs.next()){
                String id = rs.getString(1);
                String name = rs.getString(2);
                String status = rs.getString(3);
                String add_date = rs.getString(4);
                String[] rules= {id,name,status,add_date};
                a.add(rules);
            }
            prepStmt.close();
            releaseConnection();
        }
        catch(Exception ede){
             ede.printStackTrace();
            Debug.print("Exception occur while getting all Rules" + ede);
        }
        return a;
    }
    
     public Vector getAllSubRuleTypes() throws RemoteException {
        Debug.print("Getting All SubTypes");
        try {
            vecValue = loadAllSubRuleTypes();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return vecValue;
    }
    
 /**
 * Name         :loadAllSubRuleTypes
 * Description  :This method is used to get all sub rule type record from  tblMeeRefundRuleSubTypeMaster table
 * @ param      :
 * @return      :Vector
 * @throws      :SQLException
 */
    public Vector loadAllSubRuleTypes() throws SQLException {
        Debug.print("This is from getAllSubRuleTypes");
        Vector a = new Vector();
        try {
            makeConnection();
            String selectStatement = "select refund_rule_sub_type_id,refund_rule_sub_type_name,text_box_status from tblMeeRefundRuleSubTypeMaster";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            ResultSet rs = prepStmt.executeQuery();

            while(rs.next()){
                String subRuleID = rs.getString(1);
                String subRuleName = rs.getString(2);
                String txtStatus = rs.getString(3);
                String[] subrules= {subRuleID,subRuleName,txtStatus};
                a.add(subrules);
            }
            prepStmt.close();
            releaseConnection();
        }
        catch(Exception ede){
            Debug.print("Exception occur while All getting All Sub Rules" + ede);
        }
        return a;
    }

     public Vector getRuleType(String uniRuleTyID) throws RemoteException {
        Debug.print("Getting All Levels");
        try {
            list = (Vector)loadRuleType(uniRuleTyID);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }
    
    /**
 * Name         :loadRuleType
 * Description  :This method is used to get a particular type record from  tblMeeRefundRuleMaster table
 * @ param      :uniEvtTyID
 * @return      :Vector
 * @throws      :SQLException
 */
    public Vector loadRuleType(String uniRuleTyID) throws SQLException {
        Vector a = new Vector();
        Debug.print("This is from getRuleType");
        try {
            makeConnection();
            String selectStatement = "select refund_rule_type_id,refund_rule_type_name,active_status,add_date from tblMeeRefundRuleMaster where refund_rule_type_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, uniRuleTyID);
            ResultSet rs = prepStmt.executeQuery();
            while(rs.next()){
                a.add(rs.getString(1));
                a.add(rs.getString(2));
                a.add(rs.getString(3));
                a.add(rs.getString(4));
            }
            prepStmt.close();
            releaseConnection();
        }
        catch(Exception ede) {
            Debug.print("Exception occur while getting Type" + ede);
        }
        return a;
    }

     public Vector getSubRuleType(String uniSubRuleTyID) throws RemoteException {
        Debug.print("Getting All Levels");
        try {
            list = (Vector)loadSubRuleType(uniSubRuleTyID);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }
    
/**
 * Name         :loadSubRuleType
 * Description  :This method is used to get a particular type record from  tblMeeRefundRuleSubTypeMaster table
 * @ param      :uniSubRuleTyID
 * @return      :Vector
 * @throws      :SQLException
 */
    public Vector loadSubRuleType(String uniSubRuleTyID) throws SQLException    {
        Vector a = new Vector();
        try {
            makeConnection();
            String selectStatement = "select refund_rule_sub_type_id,refund_rule_sub_type_name,text_box_status from tblMeeRefundRuleSubTypeMaster where refund_rule_sub_type_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, uniSubRuleTyID);
            ResultSet rs = prepStmt.executeQuery();
            while(rs.next()){
                a.add(rs.getString(1));
                a.add(rs.getString(2));
                a.add(rs.getString(3));
            }

            prepStmt.close();
            releaseConnection();
        }
        catch(Exception ede){
            Debug.print("Exception occur while getting SubRule" + ede);
        }
        return a;
    }

     public String editRuleType(String uniRuleTyID, String strRuleName) throws RemoteException {
        Debug.print("Edit Rule Type for " + uniRuleTyID);
        try {
            returnStausValue = updateRuleType(uniRuleTyID, strRuleName);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return returnStausValue;
    }
   
/**
 * Name         :updateRuleType
 * Description  :This method is used to update a particular record from  tblMeeRefundRuleMaster table
 * @ param      :uniRuleTyID,strRuleName
 * @return      :String
 * @throws      :SQLException
 */
     
    public String updateRuleType(String uniRuleTyID, String strRuleName) throws SQLException  {
        String  returnVlaue = null;
        try {
            boolean result = isRuleTypeEditExist(uniRuleTyID,strRuleName);
            Debug.print("THis is from editRuleType: Calling isRuleTypeEditExist: " + result);
            if(!result) {
                makeConnection();
                String updateStatement = "update tblMeeRefundRuleMaster set refund_rule_type_name = ? where refund_rule_type_id = ? ";
                PreparedStatement prepStmt = con.prepareStatement(updateStatement);
                prepStmt.setString(1, strRuleName);
                prepStmt.setString(2, uniRuleTyID);
                int rowCount = prepStmt.executeUpdate();
                prepStmt.close();
                releaseConnection();
                if(rowCount != 0)
                    returnVlaue = "success";
            }
            else{
                returnVlaue = "exist";
            }
        }
        catch(Exception ede) {
            ede.printStackTrace();
            Debug.print("Exception occur while Editing Type" + ede);
        }
        return returnVlaue;
        }

    
    public String editSubRuleType(String uniSubRuleID, String strSubRuleName, String strSubRuletxtStatus) throws RemoteException    {
        Debug.print("Edit Rule Sub Type for " + uniSubRuleID);
        try {
            returnStausValue = updateSubRuleType(uniSubRuleID, strSubRuleName,strSubRuletxtStatus);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return returnStausValue;
    }
    
/**
 * Name         :updateSubRuleType
 * Description  :This method is used to update a particular record from  tblMeeRefundRuleSubTypeMaster table
 * @ param      :uniSubRuleID,strSubRuleName,strSubRuletxtStatus
 * @return      :String
 * @throws      :SQLException
 */
    
    public String updateSubRuleType(String uniSubRuleID, String strSubRuleName, String strSubRuletxtStatus) throws SQLException {
        String  returnVlaue = null;
        try {
            boolean result = isSubRuleLevelEditExist(uniSubRuleID,strSubRuleName);
            Debug.print("This is from editSubRuleType: Calling isSubRuleLevelEditExist: " + result);
            if(!result) {
                makeConnection();
                String updateStatement = "update tblMeeRefundRuleSubTypeMaster set refund_rule_sub_type_name = ?, text_box_status = ? where refund_rule_sub_type_id = ? ";
                PreparedStatement prepStmt = con.prepareStatement(updateStatement);
                prepStmt.setString(1, strSubRuleName);
                prepStmt.setString(2, strSubRuletxtStatus);
                prepStmt.setString(3, uniSubRuleID);
                int rowCount = prepStmt.executeUpdate();
                prepStmt.close();
                releaseConnection();
                 if(rowCount != 0)
                    returnVlaue = "success";
            }
             else{
               returnVlaue = "exist";
            }
        }

        catch(Exception ede)  {
            Debug.print("Exception occur while Editing Level" + ede);
        }
        return returnVlaue;
    }

    /**
 * Name         :deleteRuleType
 * Description  :This method is used to delete a particular record from tblMeeRefundRuleTypeMaster table
 * @ param      :uniRuleTyID
 * @return      :int
 * @throws      :SQLException
 */
    public int deleteRuleType(String uniRuleTyID) throws RemoteException {
        int rowCount = 0;
        try {
            makeConnection();
            String deleteStatement = "delete from tblMeeRefundRuleMaster where refund_rule_type_id = ? ";
            Debug.print("SQL Query:" + deleteStatement);
            PreparedStatement prepStmt = con.prepareStatement(deleteStatement);
            prepStmt.setString(1, uniRuleTyID);
            rowCount = prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection();
        }
        catch(Exception ede){
            ede.printStackTrace();
            Debug.print("Exception occur while deleting Type" + ede);
        }
        return rowCount;
    }
/**
 * Name         :deleteSubRuleType
 * Description  :This method is used to delete a particular record from tblMeeRefundRuleSubTypeMaster table
 * @ param      :uniSubRuleID
 * @return      :int
 * @throws      :SQLException
 */
    public int deleteSubRuleType(String uniSubRuleID) throws RemoteException   {
        int rowCount = 0;
        try {
            makeConnection();
            String deleteStatement = "delete from tblMeeRefundRuleSubTypeMaster where refund_rule_sub_type_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(deleteStatement);
            prepStmt.setString(1, uniSubRuleID);
            rowCount = prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection();
        }
        catch(Exception ede){
            ede.printStackTrace();
            Debug.print("Exception occur while deleting Sub Rules" + ede);
        }
        return rowCount;
    }

    public void generateMapForRuleToSubRule(String uniRuleTyID, Vector uniSubRuleIDs) throws RemoteException {
        try {
            makeConnection();
            String deleteStatement = "delete from tblMeeMapRefundRule where refund_rule_type_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(deleteStatement);
            prepStmt.setString(1, uniRuleTyID);
            int rowCount = prepStmt.executeUpdate();
            prepStmt.close();

            String insertStatement = "insert into tblMeeMapRefundRule (refund_rule_type_id,refund_rule_sub_type_id) values (?,?)";
            prepStmt = con.prepareStatement(insertStatement);
            Enumeration e = uniSubRuleIDs.elements();
            while(e.hasMoreElements()){
                String uniSubRuleID = (String)e.nextElement();
                prepStmt.setString(1, uniRuleTyID);
                prepStmt.setString(2, uniSubRuleID);
                prepStmt.executeUpdate();
            }
            prepStmt.close();
            releaseConnection();
        }
        catch(Exception ede){
            Debug.print("Exception occur while Mapping Type and Level" + ede);
        }
    }
    
    
    
    

     public Vector getAllMappingRules(String uniRuleTyID) throws RemoteException {
        Debug.print("Getting All Mapping Levels");
        try {
            vecValue = loadAllMappingRules(uniRuleTyID);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return vecValue;
    }
    
 /**
 * Name         :loadAllMappingRules
 * Description  :This method is used to get a particular type record from  tblMeeMapRefundRule,tblMeeRefundRuleMaster,tblMeeRefundRuleSubTypeMaster
 * @ param      :uniRuleTyID
 * @return      :Vector
 * @throws      :SQLException
 */
     
       public Vector getAllMapRuleSubRule() throws RemoteException{
        
          Vector vecObj = new Vector();
        try {
                makeConnection();
                System.out.println("Inside the Loop getAllMapRuleSubRule");

                 String RuleId_id = "select refund_rule_type_id from tblMeeRefundRuleMaster";
               
                PreparedStatement prepStmt1 = con.prepareStatement(RuleId_id);
                ResultSet rs1 = prepStmt1.executeQuery();
                System.out.println("Event Id Inside the getAllMapRuleSubRule  ");
                while (rs1.next()){
                    String eventID = rs1.getString(1);
                     System.out.println("Event Id Inside the getAllMapRuleSubRule  "+ RuleId_id);
                   String selectStatement = "select A.refund_map_id, A.refund_rule_type_id,A.refund_rule_sub_type_id,B.refund_rule_type_name, C.refund_rule_sub_type_name, C.text_box_status " + 
                    " from tblMeeMapRefundRule A, tblMeeRefundRuleMaster B, tblMeeRefundRuleSubTypeMaster C " + 
                    " where A.refund_rule_type_id = B.refund_rule_type_id and A.refund_rule_sub_type_id = C.refund_rule_sub_type_id and A.refund_rule_type_id = ?" ;

                    
                    PreparedStatement prepStmt = con.prepareStatement(selectStatement);
                    prepStmt.setString(1,eventID);
                    ResultSet rs = prepStmt.executeQuery();
                    while (rs.next()){
                        String mapid = rs.getString(1);
                        String ruleid = rs.getString(2);
                        String subruleid = rs.getString(3);
                        String rulename = rs.getString(4);
                        String subrule = rs.getString(5);
                        String txt_Statuse = rs.getString(6);
                        String[] maprules= {mapid,ruleid,subruleid,rulename,subrule,txt_Statuse};
                        vecObj.add(maprules);
                    }
                    prepStmt.close();
                }
        
                prepStmt1.close();
                releaseConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return vecObj;
    }    
     
     
     
    public Vector loadAllMappingRules(String uniRuleTyID) throws SQLException{
        Debug.print("This is from getAllMappingRules");
        Vector a = new Vector();
        try {
            makeConnection();
            String selectStatement = "select A.refund_map_id, A.refund_rule_type_id,,A.refund_rule_sub_type_id,B.refund_rule_type_name, C.refund_rule_sub_type_name, C.text_box_status " + 
               " from tblMeeMapRefundRule A, tblMeeRefundRuleMaster B, tblMeeRefundRuleSubTypeMaster C " + 
               " where A.refund_rule_type_id = B.refund_rule_type_id and A.refund_rule_sub_type_id = C.refund_rule_sub_type_id and A.refund_rule_type_id = ?" ;
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, uniRuleTyID);
            ResultSet rs = prepStmt.executeQuery();

            while(rs.next()){
                String mapid = rs.getString(1);
                String ruleid = rs.getString(2);
                String subruleid = rs.getString(3);
                String rulename = rs.getString(4);
                String subrule = rs.getString(5);
                String txt_Statuse = rs.getString(6);
                String[] maprules= {mapid,ruleid,subruleid,rulename,subrule,txt_Statuse};
                a.add(maprules);
            }
            prepStmt.close();
            releaseConnection();
        }
        catch(Exception ede){
            Debug.print("Exception occur while All getting Levels" + ede);
        }
        return a;
    }
/*====================================================================================    
 *
 * Author : Sharma
 *
 *======================================================================================*/
    
     public int insertCrossCountry(int event_id, String organizer_id,
            String division, String length, String speed, String course_description, String add_information) throws RemoteException {
        int retStat = 0;
        try {
           retStat =  insertRowCC(event_id, organizer_id, division, length, speed, course_description, add_information);
        } catch (Exception ex) {
            throw new EJBException("ejbCreate: " + ex.getMessage());
        }

        return retStat;
    }
    
    public int deleteCrossCountry(String cross_country_id) throws RemoteException {
        int retStat = 0;
        try {
            retStat = deletetRowCC(cross_country_id);
        } catch (Exception ex) {
            throw new EJBException("ejbCreate: " + ex.getMessage());
        }

        return retStat;
    }
    
    public int updateCrossCountry(String cross_country_id, int event_id, String organizer_id, String division, String length, 
            String speed, String course_description, String add_information) throws RemoteException {
        int retStat = 0;
        try {
            retStat = updateRowCC(cross_country_id, event_id, organizer_id, division, length, 
            speed, course_description, add_information);
        } catch (Exception ex) {
            throw new EJBException("ejbCreate: " + ex.getMessage());
        }

        return retStat;
    }
   
    public Vector selectCrossCountry(String cross_country_id) throws RemoteException{
       
        try {
            vecObj = loadCC(cross_country_id);
        } catch (Exception ex) {
            throw new EJBException("loadCrossCountry: " + ex.getMessage());
        }
        return vecObj;
    }
      
    /*------------------------End of Cross Country ------------------------------------------*/
    
    /*------------------------Start for Distance & Speed ------------------------------------------*/
    
    public int insertDistanceSpeed (String event_level_name, 
              String event_level_code, String jumping_effort, boolean active_status, String add_date) throws RemoteException {
        int retStat = 0;
        try {
            retStat = insertRowDS(event_level_name, event_level_code, jumping_effort, active_status, add_date);
        } catch (Exception ex) {
            throw new EJBException("Create: " + ex.getMessage());
        }

        return retStat;
    }
    
    public Vector selectDistanceSpeed() throws RemoteException {
        System.out.println("Inside the Distance & speed");
        try {
            
            vecObj = loadDS();
        } catch (Exception ex) {
            ex.printStackTrace();
            //throw new EJBException("Distance & Speed: " + ex.getMessage());
            
        }
        return vecObj;
    }
        
    /*------------------------End of Distance & Speed ------------------------------------------*/
    
    /*------------------------Start of Other Information----------------------------------------*/
    
     public Vector selectOtherInformation(String Other_id) throws RemoteException {
        // System.out.println("Inside the Other Information");
         try {
            
            vecObj = loadOI(Other_id);
        } catch (Exception ex) {
            ex.printStackTrace();
            //throw new EJBException("Distance & Speed: " + ex.getMessage());
         }
        return vecObj;
     }
    
    /*------------------------End of Other Information------------------------------------------*/
    
    /*----------------------------Start of Dressage Test for Horse and Trials-----------------*/
     public int insertDressageHorseTrials( String event_sub_level_name, boolean active_status, String add_date) throws RemoteException {
        int retStat = 0;
        try {
            retStat = insertRowDHT(event_sub_level_name, active_status, add_date);
        } catch (Exception ex) {
            throw new EJBException("Error in Arena Size: " + ex.getMessage());
        }

        return retStat;
     }
     public Vector selectDressageHorseTrials() throws RemoteException {
         System.out.println("Dressage Horse Trials");
         try {
            vecObj = loadDHT();
        } catch (Exception ex) {
            ex.printStackTrace();
            //throw new EJBException("Distance & Speed: " + ex.getMessage());
         }
        return vecObj;
     }
     
    /*----------------------------END of Dressage Test for Horse and Trials-----------------*/
     /*---------------------------Start of Arena Size---------------------------------------*/
      public int insertArenaSize( String arena_size_name, boolean active_status, String add_date) throws RemoteException {
          
        int retStat = 0;
        try {
            retStat = insertRowAS(arena_size_name, active_status, add_date);
        } catch (Exception ex) {
            throw new EJBException("Error in Arena Size: " + ex.getMessage());
        }

        return retStat;
      }
     public Vector selectArenaSize() throws RemoteException {
         
         try {
            vecObj = loadAS();
        } catch (Exception ex) {
            ex.printStackTrace();
            //throw new EJBException("Distance & Speed: " + ex.getMessage());
         }
        return vecObj;
     }
     
     /*--------------------------End of Arena Size------------------------------------------*/
     
     
     
    /*************************************************** Database Routines *****************************************************/
    
    /*-------------------------------Start of Cross Country -----------------------------------*/
/**
 * Name         :insertRow
 * Description  :This method is used to dinsert record into tblMeeCrossCountryDetails table
 * @ param      :cross_country_id, event_id
 * @return      :integer
 * @throws      :SQLException
 */

    private int insertRowCC(int event_id, String organizer_id, String division, String length, 
            String speed, String course_description, String add_information) throws SQLException {
        System.out.print("CustomerBean insertRow");
        int retStat=0;
        makeConnection();

        String insertStatement =
            "insert into tblMeeCrossCountryDetails(event_id, organizer_id, division, length, speed, course_description, add_information) values (?,?,?,?,?,?,?)";
        PreparedStatement prepStmt = con.prepareStatement(insertStatement);

        try {
               // prepStmt.setInt(1, cross_country_id );
                prepStmt.setInt(1,event_id );
                prepStmt.setString(2,organizer_id);
                prepStmt.setString(3,division);
                prepStmt.setString(4,length);
                prepStmt.setString(5,speed);
                prepStmt.setString(6,course_description);
                prepStmt.setString(7,add_information);
                                        
                retStat = prepStmt.executeUpdate();
        }catch (SQLException e) { 
                e.printStackTrace();
        }finally {
            prepStmt.close();
            releaseConnection();
        }
        return retStat;
    }
    
/**
 * Name         :deletetRow
 * Description  :This method is used to delete cross_country_id from the tblMeeCrossCountryDetails table
 * @ param      :cross_country_id
 * @return      :integer
 * @throws      :SQLException
 */

    public int deletetRowCC(String cross_country_id) throws SQLException {

        //create the connection object by calling makeConnection() method 
            makeConnection();
            PreparedStatement psDelete = null;
            int intChkReturn = 0;
            try {
                    StringBuffer sbString = new StringBuffer();
                    sbString.append("DELETE tblMeeCrossCountryDetails");
                    sbString.append(" WHERE cross_country_id =?");
                    psDelete = con.prepareStatement(sbString.toString());
                    psDelete.setString(1, cross_country_id);
                    intChkReturn = psDelete.executeUpdate();

            }
            catch (Exception ex) {

                    System.out.println("Error:  "+ex.getMessage());
            }
            finally {
                    //closes connection
                    psDelete.close();
                    releaseConnection();
            }
            return intChkReturn;
    }
	
    
   /** Name		:updateRow
     * Description	:This method will be used to update the tblMeeCrossCountryDetails while updating a Cross Country
     * 				 
     * Exception	:Throws EJBException 
     * @param   	:
     * @return  	:int intRtrnStatus
     */
	public int updateRowCC(String cross_country_id, int event_id, String organizer_id, String division, String length, 
            String speed, String course_description, String add_information) throws SQLException {
            
            makeConnection();
            PreparedStatement psUpdate = null;
            int intRtrnStatus = 0;
            StringBuffer sbCrossCountryId = new StringBuffer();
            try {
                    
                    sbCrossCountryId.append(" UPDATE tblMeeCrossCountryDetails ");
                    sbCrossCountryId.append(" SET event_id = ? , organizer_id = ?, division = ?,");
                    sbCrossCountryId.append(" length = ? , speed = ?, course_description = ?, add_information = ? ");
                    sbCrossCountryId.append(" WHERE cross_country_id = ? ");
                    psUpdate = con.prepareStatement(sbCrossCountryId.toString());
                    psUpdate.setInt(1,event_id );
                    psUpdate.setString(2,organizer_id);
                    psUpdate.setString(3,division);
                    psUpdate.setString(4,length);
                    psUpdate.setString(5,speed);
                    psUpdate.setString(6,course_description);
                    psUpdate.setString(7,add_information);
                    psUpdate.setString(8,cross_country_id);
                    
                    intRtrnStatus = psUpdate.executeUpdate();
                    
            }
            catch (Exception e) {
                    
                    throw new EJBException(e.getMessage(), e);
            }
            finally {
                    psUpdate.close();
                    releaseConnection();
            }
            return intRtrnStatus;
	}
        

/** Name	:loadCrossCountry
  * Description	:This method will be used to display the content of tblMeeCrossCountryDetails using Cross Country ID
  * 				 
  * Exception	:Throws EJBException 
  * @param   	:cross_country_id
  * @return  	:int vecObj
  */
    private Vector loadCC(String cross_country_id) throws SQLException {
        System.out.print("CROSS-COUNTRY");
        Vector vecObj = new Vector();

        makeConnection();

        String selectStatement =
            "select cross_country_id, event_id, organizer_id, division, length, speed, "+
            "course_description, add_information from tblMeeCrossCountryDetails where cross_country_id = ?";
            
        PreparedStatement psSelect = con.prepareStatement(selectStatement);

        psSelect.setString(1, cross_country_id);

        ResultSet rs = psSelect.executeQuery();
        int x;
        if (rs.next()) {
            x = rs.getInt(2);
           String myString = Integer.toString(x);

            vecObj.add(rs.getString(1));
            vecObj.add(myString);
            vecObj.add(rs.getString(3));
            vecObj.add(rs.getString(4));
            vecObj.add(rs.getString(5));
            vecObj.add(rs.getString(6));
            vecObj.add(rs.getString(7));
            vecObj.add(rs.getString(8));
                        
            psSelect.close();
            releaseConnection();
        } else {
            psSelect.close();
            releaseConnection();
            throw new EJBException("Row for id " + cross_country_id +
                " not found in database.");
        }
        return vecObj;
    }
 /*------------------------------------End of Cross Country---------------------------------------*/
    
    
 /*------------------------------------start for Distance & Speed --------------------------------*/
    
/**
 * Name         :insertRow
 * Description  :This method is used to dinsert record into tblMeeCrossCountryDetails table
 * @ param      :cross_country_id, event_id
 * @return      :integer
 * @throws      :SQLException
 */

    private int insertRowDS(String event_level_name, String event_level_code, 
                    String jumping_effort, boolean active_status, String add_date) throws SQLException {
       
        int retStat = 0;
        makeConnection();

        String insertStatement =
            "insert into tblMeeEventLevelMaster(event_level_name, event_level_code, jumping_effort, active_status, add_date) values (?,?,?,?,?)";
        PreparedStatement prepStmt = con.prepareStatement(insertStatement);

        try {
               // prepStmt.setInt(1, cross_country_id );
                
                prepStmt.setString(1,event_level_name);
                prepStmt.setString(2,event_level_code);
                prepStmt.setString(3,jumping_effort);
                prepStmt.setBoolean(4,active_status);
                prepStmt.setString(5,add_date);
                
                                        
                retStat = prepStmt.executeUpdate();
        }catch (SQLException e) { 
                e.printStackTrace();
        }finally {
            prepStmt.close();
            releaseConnection();
        }
        return retStat;
    }    
    
/**
 * Name         :deletetRow
 * Description  :This method is used to delete cross_country_id from the tblMeeCrossCountryDetails table
 * @ param      :cross_country_id
 * @return      :integer
 * @throws      :SQLException
 */

    public int deletetRowDS(String event_level_id) throws SQLException {

        //create the connection object by calling makeConnection() method 
            makeConnection();
            PreparedStatement psDelete = null;
            int intChkReturn = 0;
            try {
                    StringBuffer sbString = new StringBuffer();
                    sbString.append("DELETE tblMeeEventLevelMaster");
                    sbString.append(" WHERE event_level_id =?");
                    psDelete = con.prepareStatement(sbString.toString());
                    psDelete.setString(1, event_level_id);
                    intChkReturn = psDelete.executeUpdate();

            }
            catch (Exception ex) {

                    System.out.println("Error:  "+ex.getMessage());
            }
            finally {
                    //closes connection
                    psDelete.close();
                    releaseConnection();
            }
            return intChkReturn;
    }
	
    
   /** Name		:updateRow
     * Description	:This method will be used to update the tblMeeCrossCountryDetails while updating a Cross Country
     * 				 
     * Exception	:Throws EJBException 
     * @param   	:
     * @return  	:int intRtrnStatus
     */
	public int updateRowDS(String event_level_name, String event_level_code, 
                    String jumping_effort, boolean active_status, String add_date) throws SQLException {
            
            makeConnection();
            PreparedStatement psUpdate = null;
            int intRtrnStatus = 0;
            StringBuffer sbCrossCountryId = new StringBuffer();
            try {
                    
                    sbCrossCountryId.append(" UPDATE tblMeeEventLevelMaster ");
                    sbCrossCountryId.append(" SET event_level_name = ? , event_level_code = ?, jumping_effort = ?,");
                    sbCrossCountryId.append(" active_status = ? , add_date = ? ");
                    sbCrossCountryId.append(" WHERE cross_country_id = ? ");
                    psUpdate = con.prepareStatement(sbCrossCountryId.toString());
                    psUpdate.setString(1,event_level_name);
                    psUpdate.setString(2,event_level_code);
                    psUpdate.setString(3,jumping_effort);
                    psUpdate.setBoolean(4,active_status);
                    psUpdate.setString(5,add_date);

                    intRtrnStatus = psUpdate.executeUpdate();
                    
            }
            catch (Exception e) {
                    
                    throw new EJBException(e.getMessage(), e);
            }
            finally {
                    psUpdate.close();
                    releaseConnection();
            }
            return intRtrnStatus;
	}
        

/** Name	:loadCrossCountry
  * Description	:This method will be used to display the content of tblMeeCrossCountryDetails using Cross Country ID
  * 				 
  * Exception	:Throws EJBException 
  * @param   	:cross_country_id
  * @return  	:int vecObj
  */
    public Vector loadDS() throws SQLException {
        
        Vector vecObj = new Vector();
        
        
        try {
        makeConnection();
        String selectStatement = "select event_level_name, jumping_efforts from tblMeeEventLevelMaster";
        PreparedStatement psSelect = con.prepareStatement(selectStatement);
                
        ResultSet rs = psSelect.executeQuery();
       
        while (rs.next()) {
           
           String eventName = rs.getString(1);
           String jumping = rs.getString(2);
           String [] str = {eventName, jumping};
           
           vecObj.add(str);
        } 
         psSelect.close();
        }catch (Exception e) {
           
            releaseConnection();
            e.printStackTrace();
        } finally {
            //psSelect.close();
            releaseConnection();
        }
        return vecObj;
    }    
    
  /*-----------------------------------End of Distance & Speed------------------------------------*/
    
  /*------------------------------------start for Other Information --------------------------------*/
    
/**
 * Name         :insertRow
 * Description  :This method is used to dinsert record into tblMeeCrossCountryDetails table
 * @ param      :cross_country_id, event_id
 * @return      :integer
 * @throws      :SQLException
 */

    private int insertRowOI( int event_id, String organizer_id,String course_close_date, String entry_limit,
        String riders_horse_level_limit, String riders_horse_entire_limit, String division_entry_birth_date,
        String lease_dogs, String team_competition, double per_time_price, String other_team_info, 
        String party_name, String add_info) throws SQLException {
       
        int retStat = 0;
        makeConnection();

        String insertStatement =
            "insert into tblMeeOtherDetails(event_id, organizer_id, course_close_date, entry_limit,"+
            " riders_horse_level_limit, riders_horse_entire_limit, division_entry_birth_date, lease_dogs"+
            "team_competition,per_time_price, other_team_info, party_name, add_info) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        
        PreparedStatement prepStmt = con.prepareStatement(insertStatement);

        try {
               // prepStmt.setInt(1, cross_country_id );
               
                
                prepStmt.setInt(1,event_id);
                prepStmt.setString(2,organizer_id);
                prepStmt.setString(3,course_close_date);
                prepStmt.setString(4,entry_limit);
                prepStmt.setString(5,riders_horse_level_limit);
                prepStmt.setString(6,riders_horse_entire_limit);
                prepStmt.setString(7,division_entry_birth_date);
                prepStmt.setString(8,lease_dogs);
                prepStmt.setString(9,team_competition);
                prepStmt.setDouble(10, per_time_price);
                 prepStmt.setString(11,other_team_info);
                prepStmt.setString(12,party_name);
                prepStmt.setString(13, add_info);
             
                
                                        
                retStat = prepStmt.executeUpdate();
        }catch (SQLException e) { 
                e.printStackTrace();
        }finally {
            prepStmt.close();
            releaseConnection();
        }
        return retStat;
    }    
    
/**
 * Name         :deletetRow
 * Description  :This method is used to delete cross_country_id from the tblMeeCrossCountryDetails table
 * @ param      :cross_country_id
 * @return      :integer
 * @throws      :SQLException
 */

    public int deletetRowOI(String Other_id) throws SQLException {

        //create the connection object by calling makeConnection() method 
            makeConnection();
            PreparedStatement psDelete = null;
            int intChkReturn = 0;
            try {
                    StringBuffer sbString = new StringBuffer();
                    sbString.append("DELETE tblMeeOtherDetails");
                    sbString.append(" WHERE Other_id =?");
                    psDelete = con.prepareStatement(sbString.toString());
                    psDelete.setString(1, Other_id);
                    intChkReturn = psDelete.executeUpdate();

            }
            catch (Exception ex) {

                    System.out.println("Error:  "+ex.getMessage());
            }
            finally {
                    //closes connection
                    psDelete.close();
                    releaseConnection();
            }
            return intChkReturn;
    }
	
    
   /** Name		:updateRow
     * Description	:This method will be used to update the tblMeeCrossCountryDetails while updating a Cross Country
     * 				 
     * Exception	:Throws EJBException 
     * @param   	:
     * @return  	:int intRtrnStatus
     */
	public int updateRowOI(String course_close_date, String entry_limit,
        String riders_horse_level_limit, String riders_horse_entire_limit, String division_entry_birth_date,
        String lease_dogs, String team_competition, double per_time_price, String other_team_info, 
        String party_name, String add_info) throws SQLException {
            
            makeConnection();
            PreparedStatement psUpdate = null;
            int intRtrnStatus = 0;
            StringBuffer sbCrossCountryId = new StringBuffer();
            try {
                    
                    sbCrossCountryId.append(" UPDATE tblMeeOtherDetails ");
                    sbCrossCountryId.append(" SET course_close_date = ? , entry_limit = ?, riders_horse_level_limit = ?,");
                    sbCrossCountryId.append(" riders_horse_entire_limit = ? , division_entry_birth_date = ?, lease_dogs=? ");
                    sbCrossCountryId.append(" team_competition = ? , per_time_price = ?, other_team_info=?, party_name=?,add_info=? ");
                    sbCrossCountryId.append(" WHERE cross_country_id = ? ");
                    psUpdate = con.prepareStatement(sbCrossCountryId.toString());
                
                    
                    psUpdate.setString(1,course_close_date);
                    psUpdate.setString(2,entry_limit);
                    psUpdate.setString(3,riders_horse_level_limit);
                    psUpdate.setString(4,riders_horse_entire_limit);
                    psUpdate.setString(5,division_entry_birth_date);
                    psUpdate.setString(6,lease_dogs);
                    psUpdate.setString(7,team_competition);
                    psUpdate.setDouble(8, per_time_price);
                    psUpdate.setString(9,other_team_info);
                    psUpdate.setString(10,party_name);
                    psUpdate.setString(11, add_info);

                    intRtrnStatus = psUpdate.executeUpdate();
                    
            }
            catch (Exception e) {
                    
                    throw new EJBException(e.getMessage(), e);
            }
            finally {
                    psUpdate.close();
                    releaseConnection();
            }
            return intRtrnStatus;
	}
        

/** Name	:loadCrossCountry
  * Description	:This method will be used to display the content of tblMeeCrossCountryDetails using Cross Country ID
  * 				 
  * Exception	:Throws EJBException 
  * @param   	:cross_country_id
  * @return  	:int vecObj
  */
    public Vector loadOI(String Other_id) throws SQLException {
        String id = Other_id;
        Vector vecObj = new Vector();
        try {
        makeConnection();

        String selectStatement =
            "select other_id, event_id, organizer_id, course_close_date, entry_limit, riders_horse_level_limit,"+
            "riders_horse_entire_limit, division_entry_birth_date,lease_dogs, team_competition, per_time_price,"+
            "other_team_info, party_name, add_info "+
            "from tblMeeOtherDetails where other_id = ?";
            
        PreparedStatement psSelect = con.prepareStatement(selectStatement);

        psSelect.setString(1, id);

        ResultSet rs = psSelect.executeQuery();
       System.out.println("Inside the OtherDetails");
        if (rs.next()) {
                          
            vecObj.add(rs.getString(1));
            vecObj.add(rs.getString(2));
            vecObj.add(rs.getString(3));
            vecObj.add(rs.getString(4));
            vecObj.add(rs.getString(5));
            vecObj.add(rs.getString(6));
            vecObj.add(rs.getString(7));
            vecObj.add(rs.getString(8));
            vecObj.add(rs.getString(9));
            vecObj.add(rs.getString(10));
            vecObj.add(rs.getString(11));
            vecObj.add(rs.getString(12));
            vecObj.add(rs.getString(13));
            vecObj.add(rs.getString(14));
            
                        
            psSelect.close();
            releaseConnection();
        } else {
            psSelect.close();
            releaseConnection();
            throw new EJBException("Row for id " + Other_id +
                " not found in database.");
        }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return vecObj;
    }    
    
  /*-----------------------------------End of Other Information------------------------------------*/
     
    
    /*------------------------------------start for Dressage test for horse trails --------------------------------*/
    
/**
 * Name         :insertRow
 * Description  :This method is used to dinsert record into tblMeeCrossCountryDetails table
 * @ param      :cross_country_id, event_id
 * @return      :integer
 * @throws      :SQLException
 */

    private int insertRowDHT( String event_sub_level_name, boolean active_status, String add_date) throws SQLException {
       
        int retStat = 0;
        makeConnection();

        String insertStatement =
            "insert into tblMeeEventSubLevelMaster(event_sub_level_name, active_status, add_date) values (?,?,?)";
        
        PreparedStatement prepStmt = con.prepareStatement(insertStatement);

        try {
                        
               
                prepStmt.setString(1,event_sub_level_name);
                prepStmt.setBoolean(2, active_status);
                prepStmt.setString(3,add_date);
                                                        
                retStat = prepStmt.executeUpdate();
        }catch (SQLException e) { 
                e.printStackTrace();
        }finally {
            prepStmt.close();
            releaseConnection();
        }
        return retStat;
    }    
    
/**
 * Name         :deletetRow
 * Description  :This method is used to delete cross_country_id from the tblMeeCrossCountryDetails table
 * @ param      :cross_country_id
 * @return      :integer
 * @throws      :SQLException
 */

    public int deletetRowDHT(String event_sub_level_id) throws SQLException {

        //create the connection object by calling makeConnection() method 
            makeConnection();
            PreparedStatement psDelete = null;
            int intChkReturn = 0;
            try {
                    StringBuffer sbString = new StringBuffer();
                    sbString.append("DELETE tblMeeEventSubLevelMaster");
                    sbString.append(" WHERE event_sub_level_id =?");
                    psDelete = con.prepareStatement(sbString.toString());
                    psDelete.setString(1, event_sub_level_id);
                    intChkReturn = psDelete.executeUpdate();

            }
            catch (Exception ex) {

                    System.out.println("Error:  "+ex.getMessage());
            }
            finally {
                    //closes connection
                    psDelete.close();
                    releaseConnection();
            }
            return intChkReturn;
    }
	
    
   /** Name		:updateRow
     * Description	:This method will be used to update the tblMeeCrossCountryDetails while updating a Cross Country
     * 				 
     * Exception	:Throws EJBException 
     * @param   	:
     * @return  	:int intRtrnStatus
     */
	public int updateRowDHT(String event_sub_level_name, boolean active_status, String add_date) throws SQLException {
            
            makeConnection();
            PreparedStatement psUpdate = null;
            int intRtrnStatus = 0;
            StringBuffer sbCrossCountryId = new StringBuffer();
            try {
                    
                    sbCrossCountryId.append(" UPDATE tblMeeOtherDetails ");
                    sbCrossCountryId.append(" SET event_sub_level_name = ? , active_status = ?, add_date = ?,");
                    sbCrossCountryId.append(" WHERE event_sub_level_id = ? ");
                    psUpdate = con.prepareStatement(sbCrossCountryId.toString());
                
                    
                    psUpdate.setString(1,event_sub_level_name);
                    psUpdate.setBoolean(2, active_status);
                    psUpdate.setString(3,add_date);

                    intRtrnStatus = psUpdate.executeUpdate();
                    
            }
            catch (Exception e) {
                    
                    throw new EJBException(e.getMessage(), e);
            }
            finally {
                    psUpdate.close();
                    releaseConnection();
            }
            return intRtrnStatus;
	}
        

/** Name	:loadCrossCountry
  * Description	:This method will be used to display the content of tblMeeCrossCountryDetails using Cross Country ID
  * 				 
  * Exception	:Throws EJBException 
  * @param   	:cross_country_id
  * @return  	:int vecObj
  */
    public Vector loadDHT() throws SQLException {
        
        Vector vecObj = new Vector();
        try {
                makeConnection();
                System.out.println("Inside the Loop loadDHT");

                String Event_id = "select event_level_id from tblMeeEventLevelMaster";
               
                PreparedStatement prepStmt1 = con.prepareStatement(Event_id);
                ResultSet rs1 = prepStmt1.executeQuery();
                System.out.println("Event Id Inside the loadDHT  ");
                while (rs1.next()){
                    String eventID = rs1.getString(1);
                     System.out.println("Event Id Inside the loadDHT  "+eventID);
                    String selectStr = "select A.dressage_map_id, B.event_level_name, C.event_sub_level_name,C.event_sub_level_id "+
                    "from tblMeeMapEventSubLevel A, tblMeeEventLevelMaster B, tblMeeEventSubLevelMaster C  "+
                    "where A.event_level_id = B.event_level_id and A.event_sub_level_id = C.event_sub_level_id and "+
                    "A.event_level_id = ?";
                    PreparedStatement prepStmt = con.prepareStatement(selectStr);
                    prepStmt.setString(1,eventID);
                    ResultSet rs = prepStmt.executeQuery();
                    while (rs.next()){
                        String mapId=rs.getString(1);
                        String eventLevelName = rs.getString(2);
                        String eventSubLevelName = rs.getString(3);
                        String eventSubLevelId = rs.getString(4);
                        String [] eventName = {eventLevelName, eventSubLevelName,eventSubLevelId,mapId};
                        vecObj.add(eventName);
                    }
                    prepStmt.close();
                }
        
                prepStmt1.close();
                releaseConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return vecObj;
    }    
    
  /*-----------------------------------End of Dressage Test for Horse Trails------------------------------------*/
 
     /*------------------------------------start for Arena Size --------------------------------*/
    
/**
 * Name         :insertRow
 * Description  :This method is used to dinsert record into tblMeeCrossCountryDetails table
 * @ param      :cross_country_id, event_id
 * @return      :integer
 * @throws      :SQLException
 */

    private int insertRowAS( String arena_size_name, boolean active_status, String add_date) throws SQLException {
       
        int retStat = 0;
        makeConnection();

        String insertStatement =
            "insert into tblMeeArenaSizeMaster (arena_size_name,  active_status, add_date) values (?,?,?)";
        
        PreparedStatement prepStmt = con.prepareStatement(insertStatement);

        try {
                        
               
                prepStmt.setString(1,arena_size_name);
                prepStmt.setBoolean(2, active_status);
                prepStmt.setString(3,add_date);
                                                        
                retStat = prepStmt.executeUpdate();
        }catch (SQLException e) { 
                e.printStackTrace();
        }finally {
            prepStmt.close();
            releaseConnection();
        }
        return retStat;
    }    
    
/**
 * Name         :deletetRow
 * Description  :This method is used to delete cross_country_id from the tblMeeCrossCountryDetails table
 * @ param      :cross_country_id
 * @return      :integer
 * @throws      :SQLException
 */

    public int deletetRowAS(String arena_size_id) throws SQLException {

        //create the connection object by calling makeConnection() method 
            makeConnection();
            PreparedStatement psDelete = null;
            int intChkReturn = 0;
            try {
                    StringBuffer sbString = new StringBuffer();
                    sbString.append("DELETE tblMeeArenaSizeMaster");
                    sbString.append(" WHERE arena_size_id =?");
                    psDelete = con.prepareStatement(sbString.toString());
                    psDelete.setString(1, arena_size_id);
                    intChkReturn = psDelete.executeUpdate();

            }
            catch (Exception ex) {

                    System.out.println("Error:  "+ex.getMessage());
            }
            finally {
                    //closes connection
                    psDelete.close();
                    releaseConnection();
            }
            return intChkReturn;
    }
	
    
   /** Name		:updateRow
     * Description	:This method will be used to update the tblMeeCrossCountryDetails while updating a Cross Country
     * 				 
     * Exception	:Throws EJBException 
     * @param   	:
     * @return  	:int intRtrnStatus
     */
	public int updateRowAS(String arena_size_name, boolean active_status, String add_date) throws SQLException {
            
            makeConnection();
            PreparedStatement psUpdate = null;
            int intRtrnStatus = 0;
            StringBuffer sbCrossCountryId = new StringBuffer();
            try {
                    
                    sbCrossCountryId.append(" UPDATE tblMeeArenaSizeMaster ");
                    sbCrossCountryId.append(" SET arena_size_name = ? , active_status = ?, add_date = ?,");
                    sbCrossCountryId.append(" WHERE event_sub_level_id = ? ");
                    psUpdate = con.prepareStatement(sbCrossCountryId.toString());
                
                    
                    psUpdate.setString(1,arena_size_name);
                    psUpdate.setBoolean(2, active_status);
                    psUpdate.setString(3,add_date);

                    intRtrnStatus = psUpdate.executeUpdate();
                    
            }
            catch (Exception e) {
                    
                    throw new EJBException(e.getMessage(), e);
            }
            finally {
                    psUpdate.close();
                    releaseConnection();
            }
            return intRtrnStatus;
	}
        
        

  public ArrayList getAreaPriceMaster() throws RemoteException{   
        Debug.print("ActivityOrganizerDAO getAreaPriceMaster() ");
         ArrayList areaSizeList = new ArrayList();
         PreparedStatement prepStmt = null;
         
         ResultSet rs = null;
   	try {
          String selectStatement = " SELECT arena_size_id, arena_size_name from tblMeeArenaSizeMaster ";
              makeConnection();
             prepStmt = con.prepareStatement(selectStatement);
             rs = prepStmt.executeQuery();
            ArrayList al = null;
            while(rs.next()){
                al = new ArrayList();
                String arena_size_id = rs.getString(1);
                String arena_size_name = rs.getString(2);
                String areaList [] = {arena_size_id, arena_size_name};
                areaSizeList.add(areaList);
            }            
            releaseConnection();
            Debug.print("ActivityOrganizerDAO in getAreaPriceMaster:" + areaSizeList);
        } 
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in getAreaPriceMaster:" + sql.getMessage());
        }
        catch(Exception e){
             releaseConnection();
            throw new EJBException("General Exception  in getAreaPriceMaster:" + e.getMessage());
        }
        return areaSizeList;
    }        
        
        

/** Name	:loadAS
  * Description	:This method will be used to display the content of tblMeeCrossCountryDetails using Cross Country ID
  * 				 
  * Exception	:Throws EJBException 
  * @param   	:cross_country_id
  * @return  	:int vecObj
  */
    public Vector loadAS() throws SQLException {
        
        Vector vecObj = new Vector();
        try {
              
                makeConnection();
                System.out.println("Inside the Loop loadAS");

                String selectStatement = "select dressage_map_id from tblMeeHorseDressageDetails";
               
                PreparedStatement prepStmt1 = con.prepareStatement(selectStatement);
                ResultSet rs1 = prepStmt1.executeQuery();
                System.out.println("Event Id Inside the loadDHT  ");
                while (rs1.next()){
                    String mapID = rs1.getString(1);
                     System.out.println("Event Id Inside the loadAS  " + mapID);
                    String selectStr = "select A.event_level_name, B.event_sub_level_name, C.arena_size_name, D.dressage_map_id,C.arena_size_id "+
                    "from tblMeeEventLevelMaster A, tblMeeEventSubLevelMaster B, tblMeeArenaSizeMaster C, tblMeeHorseDressageDetails D, "+
                    "tblMeeMapEventSubLevel E where A.event_level_id = E.event_level_id and B.event_sub_level_id = E.event_sub_level_id "+
                    "and D.dressage_map_id = E.dressage_map_id and D.arena_size_id = C.arena_size_id and D.dressage_map_id = ?";
                    PreparedStatement prepStmt = con.prepareStatement(selectStr);
                    prepStmt.setString(1,mapID);
                    ResultSet rs = prepStmt.executeQuery();
                    while (rs.next()){
                        String arenaName = rs.getString(3);
                        String arenaSizeId = rs.getString(4);
                        String [] eventName = {arenaName, arenaSizeId};
                        vecObj.add(eventName);
                    }
                    prepStmt.close();
                }
        
                prepStmt1.close();
                releaseConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return vecObj;
    }    
    
  /*-----------------------------------End of Arena Size------------------------------------*/
    // ================== Judges Details ========================
    
        public ArrayList selectJudgesDetails() throws RemoteException{
            Debug.print("VaigaiStatelessBean.selectJudgesDetails():");
            ArrayList judgesList = null;
            PreparedStatement prepStmt = null;
            ResultSet rs = null;
            makeConnection();
            try {
                String selectStatement=" SELECT judge_type_id, judge_type_name from tblMeeJudgeTypeMaster" ;
                prepStmt = con.prepareStatement(selectStatement);
                rs = prepStmt.executeQuery();
                judgesList = new ArrayList();
                while(rs.next()){
                    String judgeTypeId = rs.getString(1);
                    String judgeTypeName = rs.getString(2);
                    String[] judList = {judgeTypeId, judgeTypeName};
                    judgesList.add(judList);
                }
                rs.close();
                prepStmt.close();
                releaseConnection();
                Debug.print("VaigaiStatelessBean.selectJudgesDetails():" + judgesList);
            } 
            catch(SQLException sql){
                releaseConnection();
                Debug.print("SQL Exception in VaigaiStatelessBean.selectJudgesDetails():" + sql.getMessage());
            }
            catch(Exception e){
                releaseConnection();
                Debug.print("General Exception  in VaigaiStatelessBean.selectJudgesDetails():" + e.getMessage());
            }
            return judgesList;
        }
    
 public ArrayList selectOfficialDetails(String eventId) throws RemoteException{
            Debug.print("VaigaiStatelessBean.selectOfficialDetails():");
            ArrayList judgesList = null;
            PreparedStatement prepStmt = null;
            ResultSet rs = null;
            makeConnection();
            try {
                String selectStatement=" select A.judge_type_id, B.judge_type_name, A.judge_names from tblMeeJudgeDetails A, "+
                "tblMeeJudgeTypeMaster B where A.judge_type_id=B.judge_type_id and A.event_id=? ";
                prepStmt = con.prepareStatement(selectStatement);
                prepStmt.setString(1,eventId);
                rs = prepStmt.executeQuery();
                judgesList = new ArrayList();
                while(rs.next()){
                    String judgeTypeId = rs.getString(1);
                    String judgeTypeName = rs.getString(2);
                    String judgeNames= rs.getString(3);
                    String[] judList = {judgeTypeId, judgeTypeName, judgeNames};
                    judgesList.add(judList);
                }
                rs.close();
                prepStmt.close();
                releaseConnection();
                Debug.print("VaigaiStatelessBean.selectOfficialDetails():" + judgesList);
            } 
            catch(SQLException sql){
                releaseConnection();
                Debug.print("SQL Exception in VaigaiStatelessBean.selectOfficialDetails():" + sql.getMessage());
            }
            catch(Exception e){
                releaseConnection();
                Debug.print("General Exception  in VaigaiStatelessBean.selectOfficialDetails():" + e.getMessage());
            }
            return judgesList;
        }   
/*************************************************** Database Routines *****************************************************/
    private void makeConnection() {
       
        try {
            InitialContext ic = new InitialContext();
            DataSource ds = (DataSource) ic.lookup("java:/ELMTMSSQLDS");
            con = ds.getConnection();
        } catch (Exception ex) {
            throw new EJBException("Unable to connect to database. " +
                ex.getMessage());
        }
    }
     // makeConnection

    private void releaseConnection() {
       

        try {
            con.close();
        } catch (SQLException ex) {
            throw new EJBException("releaseConnection: " + ex.getMessage());
        }
    }
     // releaseConnection

   
}
