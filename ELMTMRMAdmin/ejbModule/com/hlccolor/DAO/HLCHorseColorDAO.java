/*
 * HorseColorDAO.java
 *
 * Created on November 20, 2006, 11:41 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlccolor.DAO;
import com.hlcmrm.util.DBHelper;
import com.hlcmrm.util.Debug;
import com.hlcmrm.util.HLCHorseColorVO;
import javax.ejb.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import java.util.*;
/**
 *
 * @author suresh
 */
public class HLCHorseColorDAO {
    private Connection con;
    private static final String DATASOURCE = "java:/ELMTMSSQLDS";
    
    /** Creates a new instance of HorseColorDAO */
    public HLCHorseColorDAO() {
    }
    
    
     //=============================================Insert Donation Details =========================================      
    public boolean insertHorseColor(String colorDesc) {
            Debug.print("HorseColorDAO.insertHorseColor():");
            boolean result = false;
            PreparedStatement prepStmt = null;
            makeConnection();
            try {
                 String insertStatement = "insert into " + DBHelper.USEA_HORSE_COLOR  + 
                        " (color_desc)" +
                        " values (?) ";

                prepStmt = con.prepareStatement(insertStatement);
                
                if(colorDesc!=null && colorDesc.trim().length()!=0){
                  //  prepStmt.setString(1, colorCode);
                    prepStmt.setString(1, colorDesc);
                    int cnt = prepStmt.executeUpdate();
                    
                    Debug.print("Record Inserted succefully in insertHorseColor Details: " + cnt);
                    if(cnt>=1){
                        result = true;
                    }
                    
                    Debug.print("HorseColorDAO insertHorseColor Status :" + result);
                }
                prepStmt.close();
                releaseConnection(con);
            }
        catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseColorDAO.insertHorseColor():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseColorDAO.insertHorseColor():" + e.getMessage());
        }
        return result;
    }
      /* <!--Start:[DemographicDetails] For Demographic Type Addition, Editing and Deletion changes dated 11-Apr-2011 by Deepa-->*/
    
     public boolean insertDemo(String Demo,String DemoDesc,String Type,boolean status) {
            Debug.print("HorseColorDAO.insertDemo():");
            boolean result = false;
            PreparedStatement prepStmt = null;
            makeConnection();
            try {
                 String insertStatement = "insert into " + DBHelper.USEA_Demo_MASTER  +
                        " (demographic,demo_description,type_id,active_status)"+
                        " values (?,?,?,?) ";
                prepStmt = con.prepareStatement(insertStatement);
                System.out.println("Query is ==>"+insertStatement);
                if(Demo!=null && Demo.trim().length()!=0 && DemoDesc!=null && DemoDesc.trim().length()!=0 ){
                  //  prepStmt.setString(1, colorCode);
                    prepStmt.setString(1, Demo);
                    prepStmt.setString(2, DemoDesc);
                    prepStmt.setString(3, Type);
                    prepStmt.setBoolean(4, status);
                    System.out.println("Demo is ==>"+Demo);
                    System.out.println("DemoDesc is ==>"+DemoDesc);
                    System.out.println("Type is ==>"+Type);
                    System.out.println("status is ==>"+status);

                    int cnt = prepStmt.executeUpdate();

                    Debug.print("Record Inserted succefully in HorseColorDAO.insertDemo(): Details: " + cnt);
                    if(cnt>=1){
                        result = true;
                    }

                    Debug.print("HorseColorDAO insertHorseColor Status :" + result);
                }
                prepStmt.close();
                releaseConnection(con);
            }
        catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseColorDAO.insertDemo()::" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseColorDAO.insertDemo():" + e.getMessage());
        }
        return result;
    }

      /* <!--End:[DemographicDetails] For Demographic Type Addition, Editing and Deletion changes dated 11-Apr-2011 by Deepa-->*/
    
    //=============================================Update Request Status for Annual Details =========================================      
    public boolean updateHorseColor(String colorId, String colorDesc) {
            Debug.print("HorseColorDAO.updateHorseColor():");
            boolean result = false;
            PreparedStatement prepStmt = null;
            makeConnection();
            try {
                 String updateStatement = "update " + DBHelper.USEA_HORSE_COLOR  + " set color_desc = ? " +
                         " where color_id = ?";

                prepStmt = con.prepareStatement(updateStatement);

               // prepStmt.setString(1, colorCode);
                prepStmt.setString(1, colorDesc);
                prepStmt.setString(2, colorId);
                                
                int cnt = prepStmt.executeUpdate();
                Debug.print("Record Updated succefully in updateHorseColor : " + cnt);
                if(cnt>=1){
                    result = true;
                }
                Debug.print("HorseColorDAO updateHorseColor :" + result);
                prepStmt.close();
                releaseConnection(con);
            }
        catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseColorDAO.updateHorseColor():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseColorDAO.updateHorseColor():" + e.getMessage());
        }
        return result;
    }    
      /* <!--Start:[DemographicDetails] For Demographic Type Addition, Editing and Deletion changes dated 11-Apr-2011 by Deepa-->*/
    public boolean updateDemo(String demoId, String Demographic,String desc,String demoType,boolean demostatus) {
            Debug.print("HorseColorDAO.updateDemo():");
            boolean result = false;
            PreparedStatement prepStmt = null;
            makeConnection();
            try {
                 String updateStatement = "update " + DBHelper.USEA_Demo_MASTER  + " set demographic = ? ,demo_description= ? ,type_id = ? ,active_status= ?" +
                         " where demographic_Id = ?";
    
                prepStmt = con.prepareStatement(updateStatement);
                System.out.println("select------------------>"+prepStmt);
    
               // prepStmt.setString(1, colorCode);
                prepStmt.setString(1, Demographic);
                prepStmt.setString(2, desc);
                prepStmt.setString(3, demoType);
                prepStmt.setBoolean(4, demostatus);
                prepStmt.setString(5, demoId);
                 System.out.println("Demographic------------------>"+Demographic);
                  System.out.println("desc------------------>"+desc);
                 System.out.println("demoType------------------>"+demoType);
                   System.out.println("demostatus------------------>"+demostatus);
                int cnt = prepStmt.executeUpdate();
                Debug.print("Record Updated succefully in updateDemo : " + cnt);
                if(cnt>=1){
                    result = true;
                }
                Debug.print("HorseColorDAO updateDemo :" + result);
                prepStmt.close();
                releaseConnection(con);
            }
        catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseColorDAO.updateDemo():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseColorDAO.updateDemo():" + e.getMessage());
        }
        return result;
    }
      /* <!--End:[DemographicDetails] For Demographic Type Addition, Editing and Deletion changes dated 11-Apr-2011 by Deepa-->*/
    //=============================================select a All Country price Details =========================================      
    public ArrayList selectAllHorseColorDetails(){
            Debug.print("HorseColorDAO.selectAllDonationPriceDetails():");
            ArrayList colorList = new ArrayList();
            PreparedStatement prepStmt = null;
            makeConnection();
            try {
                 String selectStatement = "select color_id, " +
                         " color_desc from " + DBHelper.USEA_HORSE_COLOR + " order by color_desc" ;
                        
                prepStmt = con.prepareStatement(selectStatement);
                ResultSet rs = prepStmt.executeQuery();
                while(rs.next()){
                    HLCHorseColorVO objColorVO = new HLCHorseColorVO();
                    String colorId = rs.getString(1);
                    //String colorCode = rs.getString(2);
                    String colorDesc = rs.getString(2);
                    
                    objColorVO.setColorId(colorId);
                    //objColorVO.setColorCode(colorCode);
                    objColorVO.setColorDesc(colorDesc);
                    colorList.add(objColorVO);
                                
                }
                rs.close();
                prepStmt.close();
                releaseConnection(con);
            }
        catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseColorDAO.selectAllDonationPriceDetails():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseColorDAO.selectAllDonationPriceDetails():" + e.getMessage());
        }
        return colorList;
    }
      /* <!--Start:[DemographicDetails] For Demographic Type Addition, Editing and Deletion changes dated 11-Apr-2011 by Deepa-->*/
      public ArrayList selectAllDemoDetails(){
            Debug.print("HorseColorDAO.selectAllDemoDetails():");
            ArrayList colorList = new ArrayList();
            PreparedStatement prepStmt = null;
            makeConnection();
            try {
                 Debug.print("-----------iniside try-----------------");
                 String selectStatement = "SELECT a.[demographic_Id] ,a.[demographic] ,a.[demo_description] ,b.[type_name] ,a.[active_status]  FROM  "+DBHelper.USEA_Demo_MASTER +  " a, " + DBHelper.USEA_DemoType_MASTER +" b where a.type_id=b.type_id order by demo_description";
                         
                         
                       
                  Debug.print("selectStatement------------->"+selectStatement);
                prepStmt = con.prepareStatement(selectStatement);
                ResultSet rs = prepStmt.executeQuery();
                while(rs.next()){
                    HLCHorseColorVO objColorVO = new HLCHorseColorVO();
                    String DemographicId = rs.getString(1);
                    String Demogrphic = rs.getString(2);
                    String Description = rs.getString(3);
                    String TypeId = rs.getString(4);
                    String Status = rs.getString(5);

                    objColorVO.setDemoId(DemographicId);
                    objColorVO.setDemographic(Demogrphic);
                    objColorVO.setDemoDescription(Description);
                    objColorVO.setDemoType(TypeId);
                    objColorVO.setStatus(Status);
                    colorList.add(objColorVO);
 Debug.print("HorseColorDAO.selectAllDemoDetails()DemographicId:"+DemographicId);
  Debug.print("HorseColorDAO.selectAllDemoDetails():Demogrphic"+Demogrphic);
   Debug.print("HorseColorDAO.selectAllDemoDetails(): Description" +Description);
    Debug.print("HorseColorDAO.selectAllDemoDetails():TypeId" +TypeId);
     Debug.print("HorseColorDAO.selectAllDemoDetails():Status" +Status);
     
                }
                rs.close();
                prepStmt.close();
                releaseConnection(con);
            }
        catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseColorDAO.selectAllDemoDetails():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseColorDAO.selectAllDemoDetails():" + e.getMessage());
        }
        return colorList;
    }
  /* <!--End:[DemographicDetails] For Demographic Type Addition, Editing and Deletion changes dated 11-Apr-2011 by Deepa-->*/

      /* <!--Start:[DemographicDetails] For Demographic Type Addition, Editing and Deletion changes dated 11-Apr-2011 by Deepa-->*/
 public boolean deletedemo(String chkdemoIdArr[]) {
        Debug.print("RoleManagementDAOImpl.deleteRole():");
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String deleteStatement = "delete from " + DBHelper.USEA_Demo_MASTER  + " where demographic_Id = ? ";
         System.out.println("deleteStat--------------"+deleteStatement);
            for(int i=0;i<chkdemoIdArr.length;i++)
            {
                prepStmt = con.prepareStatement(deleteStatement);
                //prepStmt.setString(1, roleId);
                prepStmt.setString(1, chkdemoIdArr[i]);

                Debug.print("RoleManagementDAOImpl.deleteRole():"+deleteStatement+"=="+chkdemoIdArr[i]);
                prepStmt.executeUpdate();
                prepStmt.close();
            }

            releaseConnection(con);
        }

        catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in RoleManagementDAOImpl.deleteRole:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in RoleManagementDAOImpl.deleteRole:" + e.getMessage());
        }
        return true;
    }
   /* <!--End:[DemographicDetails] For Demographic Type Addition, Editing and Deletion changes dated 11-Apr-2011 by Deepa-->*/
    
    //=============================================select a All Country price Details =========================================      
    public HLCHorseColorVO selectHorseColorById(String colorId){
            Debug.print("HorseColorDAO.selectHorseColorById() colorId:" + colorId);
             HLCHorseColorVO objColorVO = new HLCHorseColorVO();
            PreparedStatement prepStmt = null;
            makeConnection();
            try {
                 String selectStatement = "select color_id, " +
                         " color_desc from " + DBHelper.USEA_HORSE_COLOR +
                         " where color_id= ?";
                        
                prepStmt = con.prepareStatement(selectStatement);
                prepStmt.setString(1,colorId);
                ResultSet rs = prepStmt.executeQuery();
                if(rs.next()){
                    String tempColorId = rs.getString(1);
                    //String colorCode = rs.getString(2);
                    String colorDesc = rs.getString(2);
                    
                    objColorVO.setColorId(tempColorId);
                    //objColorVO.setColorCode(colorCode);
                    objColorVO.setColorDesc(colorDesc);
                }
                rs.close();
                prepStmt.close();
                releaseConnection(con);
            }
        catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseColorDAO.selectHorseColorById():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseColorDAO.selectHorseColorById():" + e.getMessage());
        }
        return objColorVO;
    }
      /* <!--Start:[DemographicDetails] For Demographic Type Addition, Editing and Deletion changes dated 11-Apr-2011 by Deepa-->*/
        public HLCHorseColorVO selectDemoById(String demoId){
            Debug.print("HorseColorDAO.selectDemoById() demoId:" + demoId);
             HLCHorseColorVO objColorVO = new HLCHorseColorVO();
            PreparedStatement prepStmt = null;
            makeConnection();
            try {
                Debug.print("Insider try");
                 String selectStatement = "SELECT a.[demographic_Id] ,a.[demographic] ,a.[demo_description] ,b.[type_name] ,a.[active_status]  FROM  " +DBHelper.USEA_Demo_MASTER +  " a, " + DBHelper.USEA_DemoType_MASTER +" b  where a.type_id=b.type_id and demographic_Id=?";
                  Debug.print("select statment=========="+selectStatement);

                prepStmt = con.prepareStatement(selectStatement);
                prepStmt.setString(1,demoId);
               
                ResultSet rs = prepStmt.executeQuery();
                if(rs.next()){
                     String tempColorId = rs.getString(1);
                    String Demogrphic = rs.getString(2);
                    String Description = rs.getString(3);
                    String TypeId = rs.getString(4);
                    String Status = rs.getString(5);
                    Debug.print("tempColorId=========="+tempColorId);
                     Debug.print("Demogrphic=========="+Demogrphic);
                       Debug.print("Description=========="+Description);
                        Debug.print("TypeId=========="+TypeId);
                         Debug.print("Status=========="+Status);
                    objColorVO.setDemoId(tempColorId);
                    objColorVO.setDemographic(Demogrphic);
                    objColorVO.setDemoDescription(Description);
                    objColorVO.setDemoType(TypeId);
                    objColorVO.setStatus(Status);
                    
                }
                rs.close();
                prepStmt.close();
                releaseConnection(con);
            }
        catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseColorDAO.selectHorseColorById():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseColorDAO.selectHorseColorById():" + e.getMessage());
        }
        return objColorVO;
    }
          /* <!--End:[DemographicDetails] For Demographic Type Addition, Editing and Deletion changes dated 11-Apr-2011 by Deepa-->*/
    //=============================================Country Price Name details=========================================      
    public boolean isHorseColorExist(String colorDesc) {
        Debug.print("HorseColorDAO.isHorseColorExist():" + colorDesc);
        boolean result = true;
        makeConnection();
   	try {
            String selectStatement = "select color_id from " + DBHelper.USEA_HORSE_COLOR + " where color_desc = ?" ;
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,colorDesc);
            //prepStmt.setString(2,colorCode);
            
            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()){
                result = false;
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } 
        catch (SQLException e) {
            releaseConnection(con);
            Debug.print("Could not find any from HorseColorDAO.isHorseColorExist" + e.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseColorDAO.isHorseColorExist:" + e.getMessage());
        }
        Debug.print("HorseColorDAO.isHorseColorExist():" + result);
        return result;
    }
      /* <!--Start:[DemographicDetails] For Demographic Type Addition, Editing and Deletion changes dated 11-Apr-2011 by Deepa-->*/
    public boolean isdemoAddExist(String Demo,String DemoDesc,String Type,boolean status) {
        Debug.print("HorseColorDAO.isdemoAddExist():" + Demo);
         Debug.print("HorseColorDAO.isdemoAddExist():" + DemoDesc);
          Debug.print("HorseColorDAO.isdemoAddExist():" + Type);
          Debug.print("HorseColorDAO.isdemoAddExist():" + status);
        boolean result = true;
        makeConnection();
   	try {
            String selectStatement = "select * from " + DBHelper.USEA_Demo_MASTER + " where demographic=? " ;
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            Debug.print("selectStatement isdemoAddExist"+ selectStatement);
            prepStmt.setString(1,Demo);
           // prepStmt.setString(2,DemoDesc);
           // prepStmt.setString(3,Type);
           // prepStmt.setBoolean(4,status);
            //prepStmt.setString(2,colorCode);

            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()){
                result = false;
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        }
        catch (SQLException e) {
            releaseConnection(con);
            Debug.print("Could not find any from HorseColorDAO.isdemoAddExist" + e.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseColorDAO.isdemoAddExist:" + e.getMessage());
        }
        Debug.print("HorseColorDAO.isdemoAddExist():" + result);
        return result;
    }

  /* <!--End:[DemographicDetails] For Demographic Type Addition, Editing and Deletion changes dated 11-Apr-2011 by Deepa-->*/
     //=============================================Privilege Name Checking details=========================================      
    public boolean isHorseColorEditExist(String colorId,  String colorDesc) {
        Debug.print("HorseColorDAO.isHorseColorEditExist():" + colorId);
        boolean result = true;
        makeConnection();
   	try {
            String selectStatement = "select color_id from " + DBHelper.USEA_HORSE_COLOR + " where color_desc =? and  color_id!=?" ;
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,colorDesc);
           // prepStmt.setString(2,colorCode);
            prepStmt.setString(2,colorId);
            
            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()){
                result = false;
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } 
        catch (SQLException e) {
            releaseConnection(con);
            Debug.print("Could not find any from HorseColorDAO.isHorseColorEditExist" + e.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseColorDAO.isHorseColorEditExist:" + e.getMessage());
        }
        Debug.print("HorseColorDAO.isHorseColorEditExist():" + result);
        return result;
    }
      /* <!--Start:[DemographicDetails] For Demographic Type Addition, Editing and Deletion changes dated 11-Apr-2011 by Deepa-->*/
    //Debugs Starts by Lakshmi
      public boolean isdemoEditExist(String demoId,String Demographic) {
          //Debugs Ends by Lakshmi
        Debug.print("HorseColorDAO.isdemoEditExist():" + demoId);
        boolean result = true;
        makeConnection();
   	try {
            //Debugs Starts by Lakshmi
            String selectStatement = "select * from " + DBHelper.USEA_Demo_MASTER + " where demographic_Id!=? and demographic =? " ;
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);

            prepStmt.setString(1,demoId);
           prepStmt.setString(2,Demographic);
           //Debugs Ends by Lakshmi
            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()){
                result = false;
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        }
        catch (SQLException e) {
            releaseConnection(con);
            Debug.print("Could not find any from HorseColorDAO.isdemoEditExist" + e.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseColorDAO.isdemoEditExist:" + e.getMessage());
        }
        Debug.print("HorseColorDAO.isdemoEditExist():" + result);
        return result;
    }
      /* <!--End:[DemographicDetails] For Demographic Type Addition, Editing and Deletion changes dated 11-Apr-2011 by Deepa-->*/

     //=============Dhivya Here: Demographic Category (Non Human)============================

   public ArrayList selectAllTypeDetails(){
            Debug.print("HorseColorDAO.selectAllTypeDetails():");
            ArrayList typeList = new ArrayList();
            PreparedStatement prepStmt = null;
            makeConnection();
            try {
                 String selectStatement = "select type_id, " +
                         " type_name from " + DBHelper.HLC_TYPE_MASTER + " order by type_name" ;

                prepStmt = con.prepareStatement(selectStatement);
                ResultSet rs = prepStmt.executeQuery();
                while(rs.next()){

                    String typeId = rs.getString(1);
                    String typeName = rs.getString(2);

                   String temp[]={typeId,typeName};
                    typeList.add(temp);

                }
                rs.close();
                prepStmt.close();
                releaseConnection(con);
            }
        catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseColorDAO.selectAllTypeDetails():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseColorDAO.selectAllTypeDetails():" + e.getMessage());
        }
        return typeList;
    }


  public ArrayList selectAllSpeciesDetails(){
            Debug.print("HorseColorDAO.selectAllSpeciesDetails():");
            ArrayList speciesList = new ArrayList();
            PreparedStatement prepStmt = null;
            makeConnection();
            try {
                 String selectStatement = "select user_type_id, user_type_name from "
                         + "" + DBHelper.HLC_USER_TYPE_MASTER + " where "
                         + "active_status='true' and user_type_name!='Human' " ;

                prepStmt = con.prepareStatement(selectStatement);
                ResultSet rs = prepStmt.executeQuery();
                while(rs.next()){

                    String userTypeId = rs.getString(1);
                    String userTypName = rs.getString(2);

                   String temp[]={userTypeId,userTypName};
                    speciesList.add(temp);

                }
                rs.close();
                prepStmt.close();
                releaseConnection(con);
            }
        catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseColorDAO.selectAllSpeciesDetails():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseColorDAO.selectAllSpeciesDetails():" + e.getMessage());
        }
        return speciesList;
    }

public ArrayList selectDemographicListForNonHuman(String speciesId){
            Debug.print("HorseColorDAO.selectDemographicListForNonHuman():");
            ArrayList demoList = new ArrayList();
            PreparedStatement prepStmt = null;
            makeConnection();
            try {
                 String selectStatement = "select A.non_human_demographic_id, A.demographic_name, "
                         + "A.demographic_description, B.type_name, A.active_status from "
                         + "" + DBHelper.HLC_DEMOGRAPHIC_NON_HUMAN + " A, " + DBHelper.HLC_TYPE_MASTER + " B where A.type_id=B.type_id and A.user_type_id=?" ;

                prepStmt = con.prepareStatement(selectStatement);
                 prepStmt.setString(1,speciesId);
                ResultSet rs = prepStmt.executeQuery();
                while(rs.next()){

                    String nonHumanDemoId = rs.getString(1);
                    String demographicName = rs.getString(2);
                     String demographicDesc = rs.getString(3);
                    String typeName = rs.getString(4);
                     boolean activeStatus = rs.getBoolean(5);


                   String temp[]={nonHumanDemoId,demographicName,demographicDesc,typeName,String.valueOf(activeStatus)};
                    demoList.add(temp);

                }
                rs.close();
                prepStmt.close();
                releaseConnection(con);
            }
        catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseColorDAO.selectDemographicListForNonHuman():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseColorDAO.selectDemographicListForNonHuman():" + e.getMessage());
        }
        return demoList;
    }


 public boolean isDemographicNameExist(String speciesId, String demo) {

        boolean result = false;
        int cnt=0;
        makeConnection();
   	try {
            String selectStatement = "select count(*) from " + DBHelper.HLC_DEMOGRAPHIC_NON_HUMAN + " where "
                    + "user_type_id=? and demographic_name=?" ;
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,speciesId);
            prepStmt.setString(2,demo);
            ResultSet rs = prepStmt.executeQuery();
            if(rs.next()){
                    cnt = rs.getInt(1);
                }
            if(cnt==0){
               result=false;
            }else{
                result=true;
            }

            rs.close();
            prepStmt.close();
            releaseConnection(con);
        }
        catch (SQLException e) {
            releaseConnection(con);
            Debug.print("Could not find any from isDemographicNameExist" + e.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in isDemographicNameExist:" + e.getMessage());
        }
        Debug.print("isDemographicNameExist():" + result);
        return result;
    }
//Debugs Starts by Lakshmi
 public boolean isNonDemographicNameExist(String speciesId,String nonHumanDemoId, String demo) {

        boolean result = true;
       //String nonHumandemoId=null;
        makeConnection();
   	try {
            String selectStatement = "select * from " + DBHelper.HLC_DEMOGRAPHIC_NON_HUMAN + " where "
                    + "user_type_id=? and non_human_demographic_id!=? and demographic_name=?" ;
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,speciesId);
            prepStmt.setString(2,nonHumanDemoId);
            prepStmt.setString(3,demo);
            ResultSet rs = prepStmt.executeQuery();
            if(rs.next()){
                    // nonHumandemoId = rs.getString(1);
                     result=false;
                }
           /* if(nonHumandemoId==null){
               result=true;
            }else{
                result=false;
            }*/

            rs.close();
            prepStmt.close();
            releaseConnection(con);
        }
        catch (SQLException e) {
            releaseConnection(con);
            Debug.print("Could not find any from isDemographicNameExist" + e.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in isDemographicNameExist:" + e.getMessage());
        }
        Debug.print("isNonDemographicNameExist():" + result);
        return result;
    }
//Debugs Ends by Lakshmi

    public boolean insertDemographicCategoryDetails(String speciesId, String demo, String demoDesc, String TypId, String demoStatus) {
            Debug.print("HorseColorDAO.insertDemographicCategoryDetails():");

            Debug.print("HorseColorDAO.insertDemographicCategoryDetails() demoStatus:" +demoStatus);
            boolean result = false;
            PreparedStatement prepStmt = null;
            makeConnection();
            try {
                 String insertStatement = "insert into " + DBHelper.HLC_DEMOGRAPHIC_NON_HUMAN + " "
                         + "(demographic_name, demographic_description,type_id,active_status,user_type_id)" +
                        " values ( ? , ?, ?, ? , ?) ";

                Debug.print("Query Log :"+insertStatement);

                prepStmt = con.prepareStatement(insertStatement);

                if(speciesId!=null || speciesId.trim().length()!=0 && demo!=null && demo.trim().length()!=0){
                    prepStmt.setString(1, demo);
                    prepStmt.setString(2, demoDesc);
                    prepStmt.setString(3,TypId);
                    prepStmt.setBoolean(4,Boolean.parseBoolean(demoStatus));
                    prepStmt.setString(5,speciesId);
                    int cnt = prepStmt.executeUpdate();

                    Debug.print("Record Inserted succefully in insertDemographicCategoryDetails Details: " + cnt);
                    if(cnt>=1){
                        result = true;
                    }

                    Debug.print("DonationDAO insertDemographicCategoryDetails Status :" + result);
                }
                prepStmt.close();
                releaseConnection(con);
            }
        catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in DonationDAO.insertDemographicCategoryDetails():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in DonationDAO.insertDemographicCategoryDetails():" + e.getMessage());
        }
        return result;
    }


 public ArrayList selectDemographicDetailsForNonHuman(String nonHumanDemoId){
            Debug.print("HorseColorDAO.selectDemographicDetailsForNonHuman():");
            ArrayList demoList = new ArrayList();
            PreparedStatement prepStmt = null;
            makeConnection();
            try {
                 String selectStatement = "select B.user_type_name, A.demographic_name, "
                         + "A.demographic_description, A.type_id, A.active_status from "
                         + "" + DBHelper.HLC_DEMOGRAPHIC_NON_HUMAN + " A, " + DBHelper.HLC_USER_TYPE_MASTER + " B where A.user_type_id=B.user_type_id and non_human_demographic_id=?" ;

                prepStmt = con.prepareStatement(selectStatement);
                 prepStmt.setString(1,nonHumanDemoId);
                ResultSet rs = prepStmt.executeQuery();
                while(rs.next()){

                    String speciesName = rs.getString(1);
                    String demographicName = rs.getString(2);
                     String demographicDesc = rs.getString(3);
                    String typeId = rs.getString(4);
                     boolean activeStatus = rs.getBoolean(5);


                   String temp[]={speciesName,demographicName,demographicDesc,typeId,String.valueOf(activeStatus)};
                    demoList.add(temp);

                }
                rs.close();
                prepStmt.close();
                releaseConnection(con);
            }
        catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseColorDAO.selectDemographicDetailsForNonHuman():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseColorDAO.selectDemographicDetailsForNonHuman():" + e.getMessage());
        }
        return demoList;
    }

//Debugs Starts by Lakshmi
  //public boolean updateDemographicCategoryDetails(String speciesId, String nonHumanDemoId, String demoDesc, String TypId, String demoStatus) {
   public boolean updateDemographicCategoryDetails(String speciesId, String nonHumanDemoId,String demo, String demoDesc, String TypId, String demoStatus) {
       //Debugs Ends by Lakshmi
            Debug.print("HorseColorDAO.updateDemographicCategoryDetails():");
            boolean result = false;
            PreparedStatement prepStmt = null;
            makeConnection();
            try {
                //Debugs Starts by Lakshmi
                 String updateStatement = "update " + DBHelper.HLC_DEMOGRAPHIC_NON_HUMAN  + " set demographic_name = ?, demographic_description = ?, "
                         + "type_id = ?, user_type_id = ?, active_status = ? " +
                         " where non_human_demographic_id = ?";

                prepStmt = con.prepareStatement(updateStatement);

                prepStmt.setString(1, demo);
                prepStmt.setString(2, demoDesc);
                prepStmt.setString(3, TypId);
                prepStmt.setString(4, speciesId);
                prepStmt.setString(5, demoStatus);
                prepStmt.setString(6, nonHumanDemoId);
                //Debugs Ends by Lakshmi

                int cnt = prepStmt.executeUpdate();
                Debug.print("Record Updated succefully in updateDemographicCategoryDetails : " + cnt);
                if(cnt>=1){
                    result = true;
                }
                Debug.print("HorseColorDAO updateDemographicCategoryDetails :" + result);
                prepStmt.close();
                releaseConnection(con);
            }
        catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseColorDAO.updateDemographicCategoryDetails():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseColorDAO.updateDemographicCategoryDetails():" + e.getMessage());
        }
        return result;
    }


  public boolean deleteDemographicCategoryDetails(String chkNonHumanDemoIdArr[]) {
     
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String deleteStatement = "delete from " + DBHelper.HLC_DEMOGRAPHIC_NON_HUMAN  + " where "
                         + "non_human_demographic_id=? ";
            for(int i=0;i<chkNonHumanDemoIdArr.length;i++)
            {
                prepStmt = con.prepareStatement(deleteStatement);
               
                prepStmt.setString(1, chkNonHumanDemoIdArr[i]);
             
                prepStmt.executeUpdate();
                prepStmt.close();
            }
            releaseConnection(con);
        }
        catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in deleteDemographicCategoryDetails.deleteRole:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in deleteDemographicCategoryDetails.deleteRole:" + e.getMessage());
        }
        return true;
    }

  //==============Dhivya Here: Pagination=======================================================

/*  public int demographicCategoryRowCount(String speciesId){
        Debug.print("HorseColorDAO.demographicCategoryRowCount()  speciesId:" + speciesId);

        int rowCnt = 0;
          PreparedStatement prepStmt = null;
        makeConnection();
        try {
          
             String selectStatement = "select count(*) from " + DBHelper.HLC_DEMOGRAPHIC_NON_HUMAN + " where "
                    + "user_type_id=? ";
             
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, speciesId);
            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()) {
              rowCnt = rs.getInt(1);
             }
             rs.close();
             prepStmt.close();
        } catch (SQLException sqe) {
            throw new EJBException(sqe);
            //sqe.printStackTrace();
        } finally {
            releaseConnection(con);
        }
        Debug.print("HorseColorDAO.demographicCategoryRowCount():" + rowCnt);
       return rowCnt;
    }


     public ArrayList selectDemographicCategoryForPagination(String speciesId, int pNo) throws SQLException{
            Debug.print("HorseColorDAO.selectDemographicCategoryForPagination() speciesId:" + speciesId);
            Debug.print("pNo :" + pNo);

          ArrayList demoList = new ArrayList();
            PreparedStatement prepStmt = null;
            makeConnection();

            int rowsPerPage=10;
            int startRow=((pNo- 1) * rowsPerPage)+1;
            Debug.print("startRow :" + startRow);
            int endRow=startRow + rowsPerPage -1;
            
 Debug.print("endRow :" + endRow);
            try {
                 String selectStatement = "SELECT * FROM (SELECT A.non_human_demographic_id, A.demographic_name, "
                         + "A.demographic_description, B.type_name, A.active_status, "
                         + "row_number() OVER (ORDER BY A.add_date desc) as resultNum "
                         + "FROM " + DBHelper.HLC_DEMOGRAPHIC_NON_HUMAN + " A , " + DBHelper.HLC_TYPE_MASTER + " B WHERE A.type_id=B.type_id and A.user_type_id =?) as numberResults WHERE resultNum BETWEEN "+startRow+" AND "+endRow+"" ;

                prepStmt = con.prepareStatement(selectStatement);         
                prepStmt.setString(1,speciesId);
                //prepStmt.setInt(2,pNo);
                ResultSet rs = prepStmt.executeQuery();
                while(rs.next()){

                    String nonHumanDemoId = rs.getString(1);
                    String demographicName = rs.getString(2);
                     String demographicDesc = rs.getString(3);
                    String typeName = rs.getString(4);
                     boolean activeStatus = rs.getBoolean(5);


                   String temp[]={nonHumanDemoId,demographicName,demographicDesc,typeName,String.valueOf(activeStatus)};
                    demoList.add(temp);

                }
                 Debug.print("demoList in HorseColorDAO :" + demoList.size());
                rs.close();
                prepStmt.close();
                releaseConnection(con);
            }
        catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HorseColorDAO.selectDemographicCategoryForPagination():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HorseColorDAO.selectDemographicCategoryForPagination():" + e.getMessage());
        }
        return demoList;
    }

*/


//=====================Ends Here(Dhivya:Demographic Category)=============================================================





      //============================================= Database Connectivity=========================================
       private Connection makeConnection() {
            Debug.print("HorseColorDAO : makeConnection");
            try {
                InitialContext ic = new InitialContext();
                DataSource ds = (DataSource) ic.lookup(DATASOURCE);
                con = ds.getConnection();
            } catch (Exception ex) {
                throw new EJBException("Unable to connect to database in HorseColorDAO. " + ex.getMessage());
            }
            return con; 
        }

    //=============================================Connection Release=========================================  
         private void releaseConnection(Connection con) {
            Debug.print("HorseColorDAO releaseConnection");
            try {
               if(!con.isClosed())
                   con.close();
            } catch (Exception e) {

            }
         }
    
}
