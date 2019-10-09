/*
 * HorseBreedDAO.java
 *
 * Created on November 20, 2006, 12:01 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcbreed.DAO;

import com.hlcmrm.util.HLCBreedVO;
import com.hlcmrm.util.DBHelper;
import com.hlcmrm.util.Debug;
import java.rmi.RemoteException;
import javax.ejb.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import java.util.*;

/**
 *
 * @author suresh
 */
public class HLCHorseBreedDAO {
    private Connection con;
    private static final String DATASOURCE = "java:/ELMTMSSQLDS";
    private PreparedStatement prepStmt;
    private ResultSet rs;
    private  ResultSet rs1;
    private PreparedStatement prepStmt1;
    /** Creates a new instance of HorseBreedDAO */
    public HLCHorseBreedDAO() {
    }


    //=============================================Breed Insert Donation Details =========================================
    //Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 10-Mar-2011 by Satheesh
    public boolean insertHorseBreed(String breedDesc, String breedStatus, String breedSpecieId) {
        Debug.print("HorseBreedDAO.insertHorseBreed():");
        boolean result = false;
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            //Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 12-Mar-2011
            String insertStatement = "insert into " + DBHelper.USEA_HORSE_BREED + " (breed_desc,user_type_id,active_status) "
                                     + " values( ?,?,?  )";
            prepStmt = con.prepareStatement(insertStatement);
            if (breedDesc != null && breedDesc.trim().length() != 0) {
                // prepStmt.setString(1, breedCode);
                prepStmt.setString(1, breedDesc);
                prepStmt.setString(2, breedSpecieId);
                prepStmt.setString(3, breedStatus);
                int cnt = prepStmt.executeUpdate();
                Debug.print("Record Inserted succefully in insertHorseBreed Details: " + cnt);
                if (cnt >= 1) {
                    result = true;
                }

                Debug.print("HorseBreedDAO insertHorseBreed Status :" + result);
            }
            prepStmt.close();
            releaseConnection(con);
        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in HorseBreedDAO.insertHorseColor():" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in HorseBreedDAO.insertHorseBreed():" + e.getMessage());
        }
        return result;
    }
//End:[BreedMgt] For breed Addition, Editing and Deletion changes dated 10-Mar-2011 by Satheesh
    //==========================================breedId,===Update Request Status for Annual Details =========================================
//Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 10-Mar-2011 by Satheesh
    public boolean updateHorseBreed(String breedDesc, String breedStatus, String breedId) {
        Debug.print("HorseBreedDAO.updateHorseBreed():");
        boolean result = false;
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            //Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 10-Mar-2011
            String updateStatement = "update " + DBHelper.USEA_HORSE_BREED + " set breed_desc = ? ,active_status =?"+
                    " where breed_id = ?";
            System.out.println("update statement is ===>"+updateStatement);
            prepStmt = con.prepareStatement(updateStatement);
            // prepStmt.setString(1, breedCode);
            prepStmt.setString(1, breedDesc);
            prepStmt.setString(2, breedStatus);
            System.out.println("activestatus"+breedStatus);
            prepStmt.setString(3, breedId);
            int cnt = prepStmt.executeUpdate();
            Debug.print("Record Updated succefully in updateHorseBreed : " + cnt);
            if (cnt >= 1) {
                result = true;
            }
            Debug.print("HorseBreedDAO updateHorseBreed :" + result);
            prepStmt.close();
            releaseConnection(con);
        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in HorseBreedDAO.updateHorseBreed():" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in HorseBreedDAO.updateHorseBreed():" + e.getMessage());
        }
        return result;
    }
//End:[BreedMgt] For breed Addition, Editing and Deletion changes dated 10-Mar-2011 by Satheesh
    //Starts:[SpeciesMgt] For Species Addition, Editing and Deletion changes dated 10-Mar-2011

    public boolean insertHorseSpecies(String speciesname,String speciesStatus) {
        Debug.print("HorseBreedDAO.insertHorseBreed():");
        boolean result = false;
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            //Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 12-Mar-2011
            String insertStatement = "insert into " + DBHelper.USEA_USERTYPE_MASTER + " (user_type_name,active_status) "
                                     + " values( ?,?  )";
            prepStmt = con.prepareStatement(insertStatement);
            if (speciesname != null && speciesname.trim().length() != 0) {
                // prepStmt.setString(1, breedCode);
                prepStmt.setString(1, speciesname);

                prepStmt.setString(2, speciesStatus);
                int cnt = prepStmt.executeUpdate();
                Debug.print("Record Inserted succefully in insertHorseBreed Details: " + cnt);
                if (cnt >= 1) {
                    result = true;
                }

                Debug.print("HorseBreedDAO insertHorseBreed Status :" + result);
            }
            prepStmt.close();
            releaseConnection(con);
        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in HorseBreedDAO.insertHorseColor():" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in HorseBreedDAO.insertHorseBreed():" + e.getMessage());
        }
        return result;
    }

    //Ends:[SpeciesMgt] For Species Addition, Editing and Deletion changes dated 10-Mar-2011
    //Starts:[SpeciesMgt] For Species Addition, Editing and Deletion changes dated 10-Mar-2011

    public boolean updateHorseSpecies(String speciesName,String speciesStatus, String uTypeId) {
        Debug.print("HorseBreedDAO.updateHorseBreed():");
        boolean result = false;
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            //Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 12-Mar-2011
            String updateStatement = "update " + DBHelper.USEA_USERTYPE_MASTER + " set user_type_name = ? ,active_status =? "+
                    " where user_type_id = ?";
            System.out.println("update statement is ===>"+updateStatement);
            prepStmt = con.prepareStatement(updateStatement);
            // prepStmt.setString(1, breedCode);
            prepStmt.setString(1, speciesName);
            prepStmt.setString(2, speciesStatus);
            System.out.println("activestatus"+speciesStatus);
            prepStmt.setString(3, uTypeId);
            int cnt = prepStmt.executeUpdate();
            Debug.print("Record Updated succefully in updateHorseBreed : " + cnt);
            if (cnt >= 1) {
                result = true;
            }
            Debug.print("HorseBreedDAO updateHorseBreed :" + result);
            prepStmt.close();
            releaseConnection(con);
        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in HorseBreedDAO.updateHorseBreed():" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in HorseBreedDAO.updateHorseBreed():" + e.getMessage());
        }
        return result;
    }

   //Ends:[SpeciesMgt] For Species Addition, Editing and Deletion changes dated 10-Mar-2011

//==========================================Breed Delete Request Status for Annual Details =========================================
    //Start:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011
//Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 10-Mar-2011 by Satheesh
    //public boolean deleteRole(String BreedId) {
    public boolean deleteBreed(String chkRoleIdArr[]) {
       
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String deleteStatement = "delete from " +DBHelper.USEA_HORSE_BREED + " where breed_id = ?";
            for(int i=0;i<chkRoleIdArr.length;i++)
            {
                prepStmt = con.prepareStatement(deleteStatement);
                //prepStmt.setString(1, roleId);
                prepStmt.setString(1, chkRoleIdArr[i]);
               //Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 10-Mar-2011
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
	//End:[BreedMgt] For breed Addition, Editing and Deletion changes dated 10-Mar-2011 by Satheesh

//Starts:[SpeciesMgt] For Species Addition, Editing and Deletion changes dated 10-Mar-2011

    public boolean deleteSpecies(String chkRoleIdArr[]) {
       //Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 13-Mar-2011
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String deleteStatement = "delete from " +DBHelper.USEA_USERTYPE_MASTER + " where user_type_id = ?";
            System.out.println(deleteStatement);
            for(int i=0;i<chkRoleIdArr.length;i++)
            {
                prepStmt = con.prepareStatement(deleteStatement);
                //prepStmt.setString(1, roleId);
                prepStmt.setString(1, chkRoleIdArr[i]);
               //Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 12-Mar-2011
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

   //Ends:[SpeciesMgt] For Species Addition, Editing and Deletion changes dated 10-Mar-2011
    //=============================================select a All Breed  Details =========================================
//Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 10-Mar-2011 by Satheesh
    // Utybe ID is Species ID
    public ArrayList selectAllHorseBreedDetails() {
        Debug.print("HorseBreedDAO.selectAllHorseBreedDetails():");
        ArrayList colorList = new ArrayList();
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            //Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 13-Mar-2011
            String selectStatement = "select breed_id, " + " active_status,"
                                     + " breed_desc from " + DBHelper.USEA_HORSE_BREED;// + "  where user_type_id=?";
            prepStmt = con.prepareStatement(selectStatement);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()) {
                HLCBreedVO objBreedVO = new HLCBreedVO();
                String breedId = rs.getString(1);
                //String breedCode = rs.getString(2);
//Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 1-Mar-2011
                String breedStatus = String.valueOf(rs.getInt(2));
                // System.out.println(""+breedStatus);
                //Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 10-Mar-2011
                String breedDesc = rs.getString("breed_desc");
                System.out.println("breedstatus" + breedDesc);
                objBreedVO.setBreedId(breedId);
                //Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 10-Mar-2011
                //objBreedVO.setBreedCode(breedCode);
                objBreedVO.setBreedDesc(breedDesc);
                objBreedVO.setBreedStatus(breedStatus);
                colorList.add(objBreedVO);

            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in HorseBreedDAO.selectAllHorseBreedDetails():" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in HorseBreedDAO.selectAllHorseBreedDetails():" + e.getMessage());
        }
        return colorList;
    }
//End:[BreedMgt] For breed Addition, Editing and Deletion changes dated 10-Mar-2011 by Satheesh
    //=============================================select a All Breed Details by breed  ID =========================================
//Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 10-Mar-2011 by Satheesh
    // uTybeId is species Id
    public HLCBreedVO selectHorseBreedById(String breedId) {
        Debug.print("HorseBreedDAO.selectHorseBreedById() breedId:" + breedId);
        HLCBreedVO objBreedVO = new HLCBreedVO();
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            //Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 10-Mar-2011
            //String selectStatement = " select breed_id, breed_desc,active_status, specie_id from " + DBHelper.USEA_HORSE_BREED + " where breed_id= ?";

           String selectStatement = "select b.user_type_name, a.breed_id, a.breed_desc, a.active_status, a.user_type_id from "+ DBHelper.USEA_HORSE_BREED+
          " a," + DBHelper.USEA_USERTYPE_MASTER + " b where a.breed_id= ? and a.user_type_id = b.user_type_id ";

            prepStmt = con.prepareStatement(selectStatement);
            System.out.println("query"+selectStatement);
            prepStmt.setString(1, breedId);
            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()) {
                String UserTybeName = rs.getString(1);
                System.out.println("utybename"+UserTybeName);
                //String breedCode = rs.getString(2);
                String BreedId = rs.getString(2);
                String breedDesc = rs.getString(3);
                String Status = String.valueOf(rs.getInt(4));
                String SpecieId = rs.getString(5);
                System.out.print("breed status" + Status);
                 objBreedVO.setUserTybeName(UserTybeName);
                 objBreedVO.setBreedId(BreedId);
                //objBreedVO.setBreedCode(breedCode);
                 objBreedVO.setBreedDesc(breedDesc);
                 objBreedVO.setBreedStatus(Status);
                 objBreedVO.setBreedSpecieId(SpecieId);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in HorseBreedDAO.selectHorseBreedById():" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in HorseBreedDAO.selectHorseBreedById():" + e.getMessage());
        }
        return objBreedVO;
    }
//End:[BreedMgt] For breed Addition, Editing and Deletion changes dated 10-Mar-2011 by Satheesh
   //Starts:[SpeciesMgt] For Species Addition, Editing and Deletion changes dated 10-Mar-2011
     public HLCBreedVO selectHorseSpeciesById(String speciesId) {
        Debug.print("HorseBreedDAO.selectHorseBreedById() breedId:" + speciesId);
        HLCBreedVO objBreedVO = new HLCBreedVO();
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            //Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 14-Mar-2011
            //String selectStatement = " select breed_id, breed_desc,active_status, specie_id from " + DBHelper.USEA_HORSE_BREED + " where breed_id= ?";

           String selectStatement = "select user_type_id,user_type_name,active_status from "+ DBHelper.USEA_USERTYPE_MASTER + " where user_type_id = ? ";

            prepStmt = con.prepareStatement(selectStatement);
            System.out.println("query"+selectStatement);
            prepStmt.setString(1, speciesId);
            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()) {
                 String UserTypeId = rs.getString(1);
                String UserTybeName = rs.getString(2);
                System.out.println("utybename"+UserTybeName);
                //String breedCode = rs.getString(2);

                String Status = String.valueOf(rs.getInt(3));

                System.out.print("breed status" + Status);
                 objBreedVO.setUserTypeId(UserTypeId);
                  objBreedVO.setUserTybeName(UserTybeName);
                 objBreedVO.setUserTypeStatus(Status);

            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in HorseBreedDAO.selectHorseBreedById():" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in HorseBreedDAO.selectHorseBreedById():" + e.getMessage());
        }
        return objBreedVO;
    }

   //Ends:[SpeciesMgt] For Species Addition, Editing and Deletion changes dated 10-Mar-2011

    //=============================================Breed details=========================================
//Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 10-Mar-2011 by Satheesh
public boolean isHorseBreedExist(String breedDesc) {
        Debug.print("HorseBreedDAO.isHorseBreedExist():" + breedDesc);
        boolean result = true;
        makeConnection();
        try {
            String selectStatement = "select breed_id from " + DBHelper.USEA_HORSE_BREED + " where breed_desc = ?";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, breedDesc);
            // prepStmt.setString(2,breedCode);
            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()) {
                result = false;
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch (SQLException e) {
            releaseConnection(con);
            Debug.print("Could not find any from HorseBreedDAO.isHorseBreedExist" + e.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in HorseBreedDAO.isHorseBreedExist:" + e.getMessage());
        }
        Debug.print("HorseBreedDAO.isHorseBreedExist():" + result);
        return result;
    }

	//End:[BreedMgt] For breed Addition, Editing and Deletion changes dated 10-Mar-2011 by Satheesh

   //Starts:[SpeciesMgt] For Species Addition, Editing and Deletion changes dated 10-Mar-2011
    public boolean isHorseSpeciesExist(String speciesname) {
        Debug.print("HorseBreedDAO.isHorseBreedExist():" + speciesname);
        boolean result = true;
        makeConnection();
        try {
            String selectStatement = "select user_type_id from " +DBHelper.USEA_USERTYPE_MASTER  + " where user_type_name = ? ";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, speciesname);
            // prepStmt.setString(2,speciesStatus);
            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()) {
                result = false;
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch (SQLException e) {
            releaseConnection(con);
            Debug.print("Could not find any from HorseBreedDAO.isHorseBreedExist" + e.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in HorseBreedDAO.isHorseBreedExist:" + e.getMessage());
        }
        Debug.print("HorseBreedDAO.isHorseBreedExist():" + result);
        return result;
    }
    public boolean isHorseSpeciesEditExist(String speciesname,String speciesId) {
        Debug.print("HorseBreedDAO.isHorseBreedExist():" + speciesname);
        boolean result = true;
        makeConnection();
        try {
            String selectStatement = "select user_type_id from " +DBHelper.USEA_USERTYPE_MASTER  + " where user_type_name = ? and user_type_id!= ? ";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, speciesname);
             prepStmt.setString(2,speciesId);
            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()) {
                result = false;
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch (SQLException e) {
            releaseConnection(con);
            Debug.print("Could not find any from HorseBreedDAO.isHorseBreedExist" + e.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in HorseBreedDAO.isHorseBreedExist:" + e.getMessage());
        }
        Debug.print("HorseBreedDAO.isHorseBreedExist():" + result);
        return result;
    }
   //Ends:[SpeciesMgt] For Species Addition, Editing and Deletion changes dated 10-Mar-2011
      //=============================================Select the Species details=========================================
//Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 10-Mar-2011 by Satheesh
// THIS IS USED FOR SELECT SPECIES AN SPECIES ID FROM USER T YBE MASTER TABLE
    public Vector displayUserTypeDetails() throws RemoteException, FinderException {
        System.out.println(" inside HLCHorseBreadDAO:: displayUserTypeDetails() ");
        Vector V = new Vector();
        try {
            makeConnection();
            String selectStatement = "SELECT user_type_id, user_type_name FROM  " + DBHelper.USEA_USERTYPE_MASTER +
                    " where active_status = ? and user_type_name!=?";
            prepStmt = con.prepareStatement(selectStatement);
            System.out.println(" Query is ====> " + selectStatement);
            prepStmt.setBoolean(1, true);
            prepStmt.setString(2, "Human");
            rs = prepStmt.executeQuery();
            while (rs.next()) {
                String ID = rs.getString(1);
                String name = rs.getString(2);
                String[] userType = {ID, name};
                System.out.println("username" + name);
                V.addElement(userType);
            }
            System.out.println(" After ====> " + selectStatement);
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch (Exception e) {
            releaseConnection(con);

        }
        return V;
    }
//End:[BreedMgt] For breed Addition, Editing and Deletion changes dated 10-Mar-2011 by Satheesh
    //============================================= select all breed deatials by spiece id=========================================
    //Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 10-Mar-2011
    // uTybeId is species id
    public ArrayList selectAllHorseBreedDetailsByspecies(String uTypeId) {
        Debug.print("HorseBreedDAO.getAllHorseBreedDetailsByspecieId():");
        ArrayList colorList = new ArrayList();
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            //Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 10-Mar-2011
            String selectStatement = "select breed_id, " + " active_status,"
                        + " breed_desc from " + DBHelper.USEA_HORSE_BREED + "  where user_type_id= ?";


            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, uTypeId);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()) {
                HLCBreedVO objBreedVO = new HLCBreedVO();
                String breedId = rs.getString(1);
                //String breedCode = rs.getString(2);

                String breedStatus = String.valueOf(rs.getInt(2));
                //System.out.println(""+breedStatus);
                String breedDesc = rs.getString("breed_desc");
                System.out.println("breedstatus" + breedDesc);
                objBreedVO.setBreedId(breedId);
                //objBreedVO.setBreedCode(breedCode);
                objBreedVO.setBreedDesc(breedDesc);
                objBreedVO.setBreedStatus(breedStatus);
                colorList.add(objBreedVO);

            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in HorseBreedDAO.getAllHorseBreedDetailsByspecieId():" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in HorseBreedDAO.getAllHorseBreedDetailsByspecieId():" + e.getMessage());
        }
        return colorList;
    }
//Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 10-Mar-2011 by Satheesh
public ArrayList selectAllHorseSpeciesDetails(String uTypeId) {
        Debug.print("HorseBreedDAO.selectAllHorseBreedDetails():");
        ArrayList colorList = new ArrayList();
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            //Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 15-Mar-2011
            String selectStatement = "select breed_id, " + " active_status,"
                        + " breed_desc from " + DBHelper.USEA_HORSE_BREED + "  where user_type_id= ?";


            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, uTypeId);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()) {
                HLCBreedVO objBreedVO = new HLCBreedVO();
                String breedId = rs.getString(1);
                //String breedCode = rs.getString(2);

                String breedStatus = String.valueOf(rs.getInt(2));
                //System.out.println(""+breedStatus);
                String breedDesc = rs.getString("breed_desc");
                System.out.println("breedstatus" + breedDesc);
                objBreedVO.setBreedId(breedId);
                //objBreedVO.setBreedCode(breedCode);
                objBreedVO.setBreedDesc(breedDesc);
                objBreedVO.setBreedStatus(breedStatus);
                colorList.add(objBreedVO);

            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in HorseBreedDAO.selectAllHorseBreedDetails():" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in HorseBreedDAO.selectAllHorseBreedDetails():" + e.getMessage());
        }
        return colorList;
    }
	//End:[BreedMgt] For breed Addition, Editing and Deletion changes dated 10-Mar-2011 by Satheesh
//Starts:[SpeciesMgt] For Species Addition, Editing and Deletion changes dated 10-Mar-2011
    /*public ArrayList selectAllHorseSpeciesDetails(String speciesId) {
        Debug.print("HorseBreedDAO.selectAllHorseBreedDetails():");
        ArrayList colorList = new ArrayList();
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            //Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 15-Mar-2011
            String selectStatement = "select user_type_name,active_status from " + DBHelper.USEA_USERTYPE_MASTER+"  where user_type_id= ?" ;

System.out.println(selectStatement);
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, speciesId);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()) {
                HLCBreedVO objBreedVO = new HLCBreedVO();


                String speciesName = rs.getString(1);

                //String breedCode = rs.getString(2);

                String speciesStatus = String.valueOf(rs.getInt(2));
                //System.out.println(""+breedStatus);


                //objBreedVO.setBreedCode(breedCode);
                objBreedVO.setUserTybeName(speciesName);
                objBreedVO.setUserTypeStatus(speciesStatus);
                colorList.add(objBreedVO);



            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in HorseBreedDAO.selectAllHorseBreedDetails():" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in HorseBreedDAO.selectAllHorseBreedDetails():" + e.getMessage());
        }
        return colorList;
    }

*/

	 
	 public ArrayList selectAllHorseSpeciesDetailsfor() {
        Debug.print("HorseBreedDAO.selectAllHorseBreedDetails():");
        ArrayList colorList = new ArrayList();
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            //Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 15-Mar-2011
            //String selectStatement = "select user_type_id,user_type_name,active_status from " + DBHelper.USEA_USERTYPE_MASTER ;
           // String selectStatement="select case when user_type_id in(select user_type_id from  " + DBHelper.USEA_USER_MASTER + ") then '0' else '1' end flag, user_type_id, user_type_name,active_status from "+ DBHelper.USEA_USERTYPE_MASTER  ;
            String selectStatement="select case when user_type_id in(select user_type_id from " +DBHelper.USEA_HORSE_BREED + " union all select species_id from  tblHorseRelationshipTypeMaster "
+ "union all select species_id from " +DBHelper.USEA_USEA_CHARACTERISTIC_MASTER + " union all select user_type_id from  tblServiceTypeMaster union all select user_type_id from  "+ DBHelper.USEA_USER_MASTER +" ) "
+ "then '0' else '1' end flag, user_type_id, user_type_name,active_status from " + DBHelper.USEA_USERTYPE_MASTER ;

System.out.println(selectStatement);
            prepStmt = con.prepareStatement(selectStatement);

            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()) {
                HLCBreedVO objBreedVO = new HLCBreedVO();
                    String flag = rs.getString(1);
                    String speciesId = rs.getString(2);
                    String speciesName = rs.getString(3);

                    //String breedCode = rs.getString(2);

                    String speciesStatus = String.valueOf(rs.getInt(4));
                //System.out.println(""+breedStatus);


                //objBreedVO.setBreedCode(breedCode);
                    objBreedVO.setFlag(flag);
                objBreedVO.setUserTypeId(speciesId);
                objBreedVO.setUserTybeName(speciesName);
                objBreedVO.setUserTypeStatus(speciesStatus);
                colorList.add(objBreedVO);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in HorseBreedDAO.selectAllHorseBreedDetails():" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in HorseBreedDAO.selectAllHorseBreedDetails():" + e.getMessage());
        }
        return colorList;
    }

     public ArrayList selectAllHorseSpeciesDetailsBySpeciesID(String uTypeId) {
        Debug.print("HorseBreedDAO.selectAllHorseSpeciesDetailsBySpeciesID():");
        ArrayList colorList = new ArrayList();
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            //Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 15-Mar-2011
            String selectStatement = "select user_type_id,user_type_name,active_status from " + DBHelper.USEA_USERTYPE_MASTER +" where user_type_id= ? ";


            prepStmt = con.prepareStatement(selectStatement);
         prepStmt.setString(1, uTypeId);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()) {
                HLCBreedVO objBreedVO = new HLCBreedVO();

                String utypeId = rs.getString(1);
                String speciesName = rs.getString(2);

                //String breedCode = rs.getString(2);

                String speciesStatus = String.valueOf(rs.getInt(3));
                //System.out.println(""+breedStatus);

                objBreedVO.setUserTypeId(utypeId);
                //objBreedVO.setBreedCode(breedCode);
                objBreedVO.setUserTybeName(speciesName);
                objBreedVO.setUserTypeStatus(speciesStatus);
                colorList.add(objBreedVO);

            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in HorseBreedDAO.selectAllHorseBreedDetails():" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in HorseBreedDAO.selectAllHorseBreedDetails():" + e.getMessage());
        }
        return colorList;
    }
  //Ends:[SpeciesMgt] For Species Addition, Editing and Deletion changes dated 10-Mar-2011
    //=============================================Breed Name Checking details=========================================
	//Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 10-Mar-2011 by Satheesh
    public boolean isHorseBreedEditExist(String breedDesc,String breedId) {
        Debug.print("HorseBreedDAO.isHorseBreedEditExist():");
        boolean result = true;
        makeConnection();
        try {
             //Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 10-Mar-2011
            String selectStatement = "select breed_desc from " + DBHelper.USEA_HORSE_BREED + " where breed_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            //Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 10-Mar-2011
            String selectStatementExist = "select breed_desc from " + DBHelper.USEA_HORSE_BREED + " where breed_desc = ? ";
            //Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 10-Mar-2011
            PreparedStatement prepStmt1 =con.prepareStatement(selectStatementExist);
            //Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 10-Mar-2011
            prepStmt.setString(1,breedId);
            // prepStmt.setString(2,breedCode);

            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()) {
                //result = false;
                //Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 10-Mar-2011
                String BreedDescNew = rs.getString(1);
                //Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 10-Mar-2011
                if(BreedDescNew.equals(breedDesc))
                {
                    System.out.println("caltheif block");
               result = true;

                }
                else{
                //Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 10-Mar-2011

                prepStmt1.setString(1,breedDesc);
                //Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 10-Mar-2011
                ResultSet rs1 = prepStmt1.executeQuery();
                //Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 10-Mar-2011
                System.out.println("calthe else block");
                    if (rs1.next()){
                         System.out.println("calthe else2 block");
                        result=false;
                    }


            }
            }

            //Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 10-Mar-2011
            if(rs != null){
                rs.close();
                prepStmt.close();
            }
            //Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 10-Mar-2011
            if(rs1 != null){
                rs1.close();
                prepStmt1.close();
            }
            releaseConnection(con);
        } catch (SQLException e) {
            releaseConnection(con);
            Debug.print("Could not find any from HorseBreedDAO.isHorseBreedEditExist" + e.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in HorseBreedDAO.isHorseBreedEditExist:" + e.getMessage());
        }
        Debug.print("HorseBreedDAO.isHorseBreedEditExist():" + result);
        return result;
    }
//End:[BreedMgt] For breed Addition, Editing and Deletion changes dated 10-Mar-2011 by Satheesh
   //:[BreedMgt] For breed Addition, Editing and Deletion changes dated 12-Mar-2011
    /*public ArrayList selectAllHorseBreedDetails() {
        throw new UnsupportedOperationException("Not yet implemented");
    }*/


  //===============Dhivya Here: Species Breed Management=============================


 public String selectSpeciesName(String speciesId) {
        Debug.print("HorseBreedDAO.selectSpeciesName():");
        String speciesName ="";
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            //Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 15-Mar-2011
            String selectStatement = "select user_type_name from " + DBHelper.USEA_USERTYPE_MASTER +" where user_type_id= ? ";


            prepStmt = con.prepareStatement(selectStatement);
         prepStmt.setString(1, speciesId);
            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()) {
                 speciesName = rs.getString(1);

            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in HorseBreedDAO.selectSpeciesName():" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in HorseBreedDAO.selectSpeciesName():" + e.getMessage());
        }
        return speciesName;
    }


     //=============================================Select the Species details=========================================
 // Starts:[MemMgnt] For Breed Characteristic Detail Management Dated:21-4-2011
   
    public Vector selectAllCharacterForBreed(String uTypeId){

        Vector BreedCharList = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        
   	try {
            makeConnection();
            String selectStatement="select character_id, characteristic,species_id from "+DBHelper.USEA_USEA_CHARACTERISTIC_MASTER +" where species_id=?";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,uTypeId);
            rs = prepStmt.executeQuery();
            BreedCharList = new Vector();
            while(rs.next()){
                String characterId = rs.getString(1);
                String characterstic = rs.getString(2);
                System.out.println("character"+characterstic);
                String speciesId = rs.getString(3);
                String[] brdCharList = {characterId, characterstic,speciesId};
                BreedCharList.add(brdCharList);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        }
        catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HLCHorseBreedDAO.selectAllCharacterForBreed():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HLCHorseBreedDAO.selectAllCharacterForBreed():" + e.getMessage());
        }
        return BreedCharList;
    }

    public Vector selectAllCharDetailsForBreed(String uTypeId,String charId){

        Vector BreedCharDetailList = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
   	try {
            String selectStatement="select b.characterdet_id, b.Details, b.active_status from "+DBHelper.USEA_USEA_CHARACTERISTIC_MASTER +" a," +DBHelper.USEA_USEA_CHARACTERISTIC_DETAILS_MASTER +" b where a.character_id = b.character_id and a.species_id =? and a.character_id =?";

            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,uTypeId);
            prepStmt.setString(2,charId);
            rs = prepStmt.executeQuery();
            BreedCharDetailList = new Vector();
            while(rs.next()){
                String characterDetId = rs.getString(1);
                String charDetail = rs.getString(2);
                String activestatus = rs.getString(3);
                String[] brdCharList = {characterDetId, charDetail, activestatus};
                BreedCharDetailList.add(brdCharList);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        }
        catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HLCHorseBreedDAO.selectAllCharDetailsForBreed():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HLCHorseBreedDAO.selectAllCharDetailsForBreed():" + e.getMessage());
        }
        return BreedCharDetailList;
    }
     public boolean isBreedCharDetExist(String charDet,String charId) {
        Debug.print("HLCHorseBreedDAO.isBreedCharDetExist():" + charDet);
        boolean result = true;
        makeConnection();
        try {
            String selectStatement = "select characterdet_id from " +DBHelper.USEA_USEA_CHARACTERISTIC_DETAILS_MASTER +" where Details=? and character_id= ? ";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, charDet);
            prepStmt.setString(2,charId);
            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()) {
                result = false;
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch (SQLException e) {
            releaseConnection(con);
            Debug.print("Could not find any from HLCHorseBreedDAO.isBreedCharDetExist" + e.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in HLCHorseBreedDAO.isBreedCharDetExist:" + e.getMessage());
        }
        Debug.print("HLCHorseBreedDAO.isBreedCharDetExist():" + result);
        return result;
    }
     public boolean isBreedCharDetEditExist(String charDet,String characterdetid,String charId) {
        Debug.print("HLCHorseBreedDAO.isBreedCharDetEditExist():" + charDet);
        boolean result = true;
        makeConnection();
        try {
            String selectStatement = "select characterdet_id from " +DBHelper.USEA_USEA_CHARACTERISTIC_DETAILS_MASTER +" where Details=? and characterdet_id!=? and character_id=?";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, charDet);
            prepStmt.setString(2,characterdetid);
			prepStmt.setString(3,charId);
            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()) {
                result = false;
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch (SQLException e) {
            releaseConnection(con);
            Debug.print("Could not find any from HLCHorseBreedDAO.isBreedCharDetEditExist" + e.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in HLCHorseBreedDAO.isBreedCharDetEditExist:" + e.getMessage());
        }
        Debug.print("HLCHorseBreedDAO.isBreedCharDetEditExist():" + result);
        return result;
    }
    
     public Vector selectCharEditDetailsForBreed(String characterdetId){

        Vector BreedCharDetailList = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
   	try {
            String selectStatement="select c.characterdet_id,a.user_type_name,b.characteristic,c.details,c.active_status,a.user_type_id,b.character_id from "+ DBHelper.USEA_USERTYPE_MASTER +" a,"+DBHelper.USEA_USEA_CHARACTERISTIC_MASTER +" b," +DBHelper.USEA_USEA_CHARACTERISTIC_DETAILS_MASTER +" c where a.user_type_id=b.species_id and b.character_id=c.character_id and c.characterdet_id= ? ";
   System.out.println(selectStatement);
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,characterdetId);
           
            rs = prepStmt.executeQuery();
            BreedCharDetailList = new Vector();
            while(rs.next()){
                String characterDetId = rs.getString(1);
                String userTypeName = rs.getString(2);
                String Characteristic = rs.getString(3);
                  String Details = rs.getString(4);
                    String status = rs.getString(5);
                    String uTypeId = rs.getString(6);
                    String charId = rs.getString(7);
                
                String[] brdCharList = {characterDetId, userTypeName, Characteristic,Details,status,uTypeId,charId};
                BreedCharDetailList.add(brdCharList);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        }
        catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HLCHorseBreedDAO.selectAllCharDetailsForBreed():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in HLCHorseBreedDAO.selectAllCharDetailsForBreed():" + e.getMessage());
        }
        return BreedCharDetailList;
    }
    
    public boolean insertBreedCharDetails(String charDet,String charId, String status) {
        Debug.print("HLCHorseBreedDAO.insertBreedCharDetails():");
        boolean result = false;
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            
            String insertStatement = "insert into " +DBHelper.USEA_USEA_CHARACTERISTIC_DETAILS_MASTER +" (Details,character_id,active_status) "
                                     + " values( ?,?,?  )";
            prepStmt = con.prepareStatement(insertStatement);
            if (charDet != null && charDet.trim().length() != 0) {
                prepStmt.setString(1, charDet);
                prepStmt.setString(2, charId);
                prepStmt.setString(3, status);
                int cnt = prepStmt.executeUpdate();
                Debug.print("Record Inserted succefully in insertBreedCharDetails: " + cnt);
                if (cnt >= 1) {
                    result = true;
                }

                Debug.print("HLCHorseBreedDAO insertBreedCharDetails Status :" + result);
            }
            prepStmt.close();
            releaseConnection(con);
        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in HLCHorseBreedDAO.insertBreedCharDetails():" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in HLCHorseBreedDAO.insertBreedCharDetails():" + e.getMessage());
        }
        return result;
    }

   public boolean updateBreedCharDet(String characterdetid, String charDet, String status) {
        Debug.print("HLCHorseBreedDAO.updateBreedCharDet():");
        boolean result = false;
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            
            String updateStatement = "update " +DBHelper.USEA_USEA_CHARACTERISTIC_DETAILS_MASTER +" set details = ? ,active_status =?"+
                    " where characterdet_id = ?";
            System.out.println("update statement is ===>"+updateStatement);
            prepStmt = con.prepareStatement(updateStatement);
            prepStmt.setString(1, charDet);
            prepStmt.setString(2, status);
            prepStmt.setString(3, characterdetid);
            int cnt = prepStmt.executeUpdate();
            Debug.print("Record Updated succefully in updateBreedCharDet : " + cnt);
            if (cnt >= 1) {
                result = true;
            }
            Debug.print("HLCHorseBreedDAO updateBreedCharDet :" + result);
            prepStmt.close();
            releaseConnection(con);
        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in HLCHorseBreedDAO.updateBreedCharDet():" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in HLCHorseBreedDAO.updateBreedCharDet():" + e.getMessage());
        }
        return result;
    }
public boolean deleteBrdChar(String[] chkCharIdArr) {
         PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String deleteStatement = "delete from " +DBHelper.USEA_USEA_CHARACTERISTIC_DETAILS_MASTER + " where characterdet_id = ?";
            for(int i=0;i<chkCharIdArr.length;i++)
            {
                    prepStmt = con.prepareStatement(deleteStatement);
                    prepStmt.setString(1, chkCharIdArr[i]);
                    prepStmt.executeUpdate();
                    prepStmt.close();
            }
            releaseConnection(con);
        }
        catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in HLCHorseBreedDAO deleteBrdChar:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception in HLCHorseBreedDAO deleteBrdChar:" + e.getMessage());
        }
        return true;
    }
 // Ends:[MemMgnt] For Breed Characteristic Detail Management Dated:21-4-2011
    
//Satheesh-Start- MMMA_0008:For each species, allow for customer definition of breed characteristic categories


    public Vector displayUserTypeDetail() throws RemoteException, FinderException {
        System.out.println(" inside HLCHorseBreadDAO:: displayUserTypeDetails() ");
        Vector V = new Vector();
        try {
            makeConnection();
            String selectStatement = "SELECT user_type_id, user_type_name FROM  " + DBHelper.USEA_USERTYPE_MASTER +
                    " where active_status = ? and user_type_name!=?";
            prepStmt = con.prepareStatement(selectStatement);
            System.out.println(" Query is ====> " + selectStatement);
            prepStmt.setBoolean(1, true);
            prepStmt.setString(2, "Human");
            rs = prepStmt.executeQuery();
            while (rs.next()) {
                String ID = rs.getString(1);
                String name = rs.getString(2);
                String[] userType = {ID, name};
                System.out.println("username" + name);
                V.addElement(userType);
            }
            System.out.println(" After ====> " + selectStatement);
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch (Exception e) {
            releaseConnection(con);

        }
        return V;
    }
 public ArrayList selectAllSpeciesCharDetailsByspecies(String uTypeId) {
        Debug.print("HorseBreedDAO.selectAllHorseBreedDetails():");
        ArrayList colorList = new ArrayList();
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            //Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 15-Mar-2011
           // String selectStatement = "select character_id, " + " active_status,"
                        //+ " characteristic from " + DBHelper.USEA_USEA_CHARACTERISTIC_MASTER+ "  where species_id= ?";

                   String selectStatement="select case when character_id in(select character_id from  " + DBHelper.USEA_USEA_CHARACTERISTIC_DETAILS_MASTER + ") then '0' else '1' end flag, character_id, active_status,characteristic from "+ DBHelper.USEA_USEA_CHARACTERISTIC_MASTER+ "  where species_id= ?" ;
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, uTypeId);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()) {
                HLCBreedVO objBreedVO = new HLCBreedVO();
                String Flag =rs.getString(1);
                String CharId = rs.getString(2);
                //String breedCode = rs.getString(2);

                String charStatus = String.valueOf(rs.getInt(3));
                //System.out.println(""+breedStatus);
                String charDesc = rs.getString("characteristic");
                System.out.println("breedstatus" + charDesc);
                objBreedVO.setCharId(CharId);
                objBreedVO.setCharDesc(charDesc);
                objBreedVO.setFlag(Flag);
                objBreedVO.setCharStatus(charStatus);
                colorList.add(objBreedVO);

            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in HorseBreedDAO.selectAllHorseBreedDetails():" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in HorseBreedDAO.selectAllHorseBreedDetails():" + e.getMessage());
        }
        return colorList;
    }



  public boolean isSpeciesCharExist(String charDesc,String charSpecieId) {
        Debug.print("HorseBreedDAO.isSpeciesCharExist():" + charSpecieId);
        boolean result = false;
        makeConnection();
        try {
            String selectStatement = "select count(0) from " + DBHelper.USEA_USEA_CHARACTERISTIC_MASTER + " where species_id = ? and characteristic = ?";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            System.out.println("select statement"+selectStatement);
            System.out.println("sss"+selectStatement);
            prepStmt.setString(1,  charSpecieId);
            System.out.println("breedSpecieId==>"+charSpecieId);
            prepStmt.setString(2,  charDesc);
             System.out.println("breedDesc==>"+charDesc);
            // prepStmt.setString(2,breedCode);
            ResultSet rs = prepStmt.executeQuery();
             System.out.println("After executing query ==>SpeciesCharExist");
           // int icountRecord=0;
            if (rs.next()) {
                String strCount=rs.getString(1);
                 System.out.println("strCount ==>"+strCount);
                //icountRecord=Integer.getInteger(strCount);
                 // System.out.println("icountRecord ==>"+icountRecord);
                if(Integer.parseInt(strCount) == 0)
                    result = true;
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch (SQLException e) {
            releaseConnection(con);
            Debug.print("Could not find any from HorseBreedDAO.isSpeciesCharExist" + e.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in HorseBreedDAO.isSpeciesCharExist:" + e.getMessage());
        }
        Debug.print("HorseBreedDAO.isHorseBreedExist():" + result);
        return result;
    }


  public boolean insertSpeciesChar(String charDesc, String charStatus, String charSpecieId) {
        Debug.print("HorseBreedDAO.insertHorseBreed():");
        boolean result = false;
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            //Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 12-Mar-2011
            String insertStatement = "insert into " + DBHelper.USEA_USEA_CHARACTERISTIC_MASTER  + " (characteristic,species_id,active_status) "
                                     + " values( ?,?,?  )";
            prepStmt = con.prepareStatement(insertStatement);
            if (charDesc != null && charDesc.trim().length() != 0) {
                // prepStmt.setString(1, breedCode);
                prepStmt.setString(1, charDesc);
                prepStmt.setString(2, charSpecieId);
                prepStmt.setString(3, charStatus);
                int cnt = prepStmt.executeUpdate();
                Debug.print("Record Inserted succefully in insertHorseBreed Details: " + cnt);
                if (cnt >= 1) {
                    result = true;
                }

                Debug.print("HorseBreedDAO insertHorseBreed Status :" + result);
            }
            prepStmt.close();
            releaseConnection(con);
        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in HorseBreedDAO.insertHorseColor():" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in HorseBreedDAO.insertHorseBreed():" + e.getMessage());
        }
        return result;
    }

  public HLCBreedVO selectSpeciesCharById(String charId) {
        Debug.print("HorseBreedDAO.selectHorseBreedById() breedId:" + charId);
        HLCBreedVO objBreedVO = new HLCBreedVO();
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            //Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 14-Mar-2011
            //String selectStatement = " select breed_id, breed_desc,active_status, specie_id from " + DBHelper.USEA_HORSE_BREED + " where breed_id= ?";

           String selectStatement = "select b.user_type_name, a.character_id, a.characteristic, a.active_status, a.species_id from "+ DBHelper.USEA_USEA_CHARACTERISTIC_MASTER+
          " a," + DBHelper.USEA_USERTYPE_MASTER + " b where a.character_id= ? and a.species_id = b.user_type_id ";

            prepStmt = con.prepareStatement(selectStatement);
            System.out.println("query"+selectStatement);
            prepStmt.setString(1, charId);
            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()) {
                String UserTybeName = rs.getString(1);
                System.out.println("utybename"+UserTybeName);
                //String breedCode = rs.getString(2);
                String charid = rs.getString(2);
                String charDesc = rs.getString(3);
                String Status = String.valueOf(rs.getInt(4));
                String SpecieId = rs.getString(5);
                System.out.print("breed status" + Status);
                 objBreedVO.setUserTybeName(UserTybeName);
                 objBreedVO.setBreedId(charid);
                //objBreedVO.setBreedCode(breedCode);
                 objBreedVO.setBreedDesc(charDesc);
                 objBreedVO.setBreedStatus(Status);
                 objBreedVO.setBreedSpecieId(SpecieId);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in HorseBreedDAO.selectHorseBreedById():" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in HorseBreedDAO.selectHorseBreedById():" + e.getMessage());
        }
        return objBreedVO;
    }

  public boolean isSpeciesCharEditExist( String charDesc,String charId,String uTypeId) {
        Debug.print("HorseBreedDAO.isHorseBreedEditExist():");
        boolean result = false;
        makeConnection();
        try {
             //Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 12-Mar-2011
            String selectStatement = "select characteristic from " + DBHelper.USEA_USEA_CHARACTERISTIC_MASTER+ " where character_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            //Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 12-Mar-2011
            String selectStatementExist = "select count(0) from " + DBHelper.USEA_USEA_CHARACTERISTIC_MASTER+ " where species_id = ? and characteristic = ?";
            //Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 12-Mar-2011
            PreparedStatement prepStmt1 =con.prepareStatement(selectStatementExist);
            System.out.println(""+selectStatementExist);
            //Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 12-Mar-2011
            prepStmt.setString(1, charId);
            // prepStmt.setString(2,breedCode);

            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()) {
                //result = false;
                //Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 12-Mar-2011
                String BreedDescNew = rs.getString(1);
                //Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 12-Mar-2011
                if(BreedDescNew.equals(charDesc))
                {
                    System.out.println("caltheif block");
               result = true;

                }
                else{


                 prepStmt1.setString(1,uTypeId);
                 System.out.println("uTypeId"+uTypeId);
                 prepStmt1.setString(2, charDesc);
                  

                ResultSet rs1 = prepStmt1.executeQuery();
                 System.out.println("After executing query ==>");

                System.out.println("calthe else block");
                    if (rs1.next()){
                   String strCount=rs1.getString(1);
                   System.out.println("strCount ==>"+strCount);
                   if(Integer.parseInt(strCount) == 0)
                   {
                        result=true;
                        }
                    }


            }
            }


            if(rs != null){
                rs.close();
                prepStmt.close();
            }

            if(rs1 != null){

                prepStmt1.close();
            }
            releaseConnection(con);
        } catch (SQLException e) {
            releaseConnection(con);
            Debug.print("Could not find any from HorseBreedDAO.isHorseBreedEditExist" + e.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in HorseBreedDAO.isHorseBreedEditExist:" + e.getMessage());
        }
        Debug.print("HorseBreedDAO.isHorseBreedEditExist():" + result);
        return result;
    }


  public boolean updateSpeciesChar(String charDesc, String charStatus, String charId) {
        Debug.print("HorseBreedDAO.updateHorseBreed():");
        boolean result = false;
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            //Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 12-Mar-2011
            String updateStatement = "update " + DBHelper.USEA_USEA_CHARACTERISTIC_MASTER + " set characteristic = ? ,active_status =?"+
                    " where character_id = ?";
            System.out.println("update statement is ===>"+updateStatement);
            prepStmt = con.prepareStatement(updateStatement);
            // prepStmt.setString(1, breedCode);
            prepStmt.setString(1, charDesc);
            prepStmt.setString(2, charStatus);
            System.out.println("activestatus"+charStatus);
            prepStmt.setString(3, charId);
            int cnt = prepStmt.executeUpdate();
            Debug.print("Record Updated succefully in updateHorseBreed : " + cnt);
            if (cnt >= 1) {
                result = true;
            }
            Debug.print("HorseBreedDAO updateHorseBreed :" + result);
            prepStmt.close();
            releaseConnection(con);
        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in HorseBreedDAO.updateHorseBreed():" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in HorseBreedDAO.updateHorseBreed():" + e.getMessage());
        }
        return result;
    }

  public boolean deleteChar(String chkRoleIdArr[]) {
       //Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 13-Mar-2011
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String deleteStatement = "delete from " +DBHelper.USEA_USEA_CHARACTERISTIC_MASTER + " where character_id = ?";
            for(int i=0;i<chkRoleIdArr.length;i++)
            {
                prepStmt = con.prepareStatement(deleteStatement);
                //prepStmt.setString(1, roleId);
                prepStmt.setString(1, chkRoleIdArr[i]);
               //Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 12-Mar-2011
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

//Satheesh-End- MMMA_0008:For each species, allow for customer definition of breed characteristic categories


  //===============Dhivya Here: Breed Characteristics [Category and Details Management])=====================================


 public String selectCharacteristicName(String charId) {
        Debug.print("HorseBreedDAO.selectCharacteristicName():");
        Debug.print("HorseBreedDAO.selectCharacteristicName():" +charId);
        String characName ="";
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
           
            String selectStatement = "select characteristic from " + DBHelper.HLC_SPECIES_CHARACTERISTICS_MASTER +" where character_id= ? ";


            prepStmt = con.prepareStatement(selectStatement);
         prepStmt.setString(1, charId);
            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()) {
                 characName = rs.getString(1);
Debug.print("HorseBreedDAO.selectCharacteristicName(): characName" +characName);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in HorseBreedDAO.selectCharacteristicName():" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in HorseBreedDAO.selectCharacteristicName():" + e.getMessage());
        }
        return characName;
    }
 
    //============================================= Database Connectivity=========================================  
    private Connection makeConnection() {
        Debug.print("HorseBreedDAO : makeConnection");
        try {
            InitialContext ic = new InitialContext();
            DataSource ds = (DataSource) ic.lookup(DATASOURCE);
            con = ds.getConnection();
        } catch (Exception ex) {
            throw new EJBException("Unable to connect to database in HorseBreedDAO. " + ex.getMessage());
        }
        return con;
    }

    //=============================================Connection Release=========================================  
    private void releaseConnection(Connection con) {
        Debug.print("HorseBreedDAO releaseConnection");
        try {
            if (!con.isClosed()) {
                con.close();
            }
        } catch (Exception e) {
        }
    }
 
}
