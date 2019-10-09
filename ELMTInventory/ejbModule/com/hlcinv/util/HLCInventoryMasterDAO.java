/*
 * InventoryMasterDAO.java
 *
 * Created on September 29, 2006, 4:32 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcinv.util;
import javax.ejb.*;
import java.sql.*;
import javax.sql.*;
import java.util.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.naming.*;
/**
 *
 * @author harmohan
 */
public class HLCInventoryMasterDAO {
    private static final String dbName = "java:/ELMTMSSQLDS";
    private Connection con = null;
    private PreparedStatement prepStmt = null;
    private ResultSet rs = null;
    private PreparedStatement prepStmt1 = null;
    private ResultSet rs1 = null;
    HLCInventoryMasterVO objINV = null;
    
    /** Creates a new instance of InventoryMasterDAO */
    public HLCInventoryMasterDAO() {
        objINV = new HLCInventoryMasterVO();
    }
/**
  * Name         :insertRowInventoryMaster
  * Description  :This method will insert the detail into the the InventoryMaster 
  * @ param      :categoryId, subCategoryId,productName etc...
  * @return      :boolean
  * @throws      :SQLException
  */       
    public boolean insertRowInventoryMaster(HLCInventoryMasterVO voObj) throws SQLException {
        Debug.print("SaraswathySessionBean insertRowInventoryMaster");
        //java.sql.Date dt = java.sql.Date.valueOf(dob);

        try{
            makeConnection();
            String insertStatement = "insert into " + DBHelper.USEA_INV_INVENTORY_MASTER + " (category_id,subcategory_id," +
                    "product_name, product_description,product_code,product_gl_account_id,product_cog_account_id," +
                    "total_product_quantity,buy_product_price,profit_margin,discount_margin,sales_price,per_product_price," +
                    "product_imagepath) values ( ? , ? , ? , ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
            prepStmt = con.prepareStatement(insertStatement);
            System.out.println("Inside the Inventory Master inside method....\n\n ");
            prepStmt.setString(1, voObj.getCategoryId());
            prepStmt.setString(2, voObj.getSubcategoryId());
            prepStmt.setString(3, voObj.getProductName()); 
            prepStmt.setString(4, voObj.getProductDescription());
            prepStmt.setString(5, voObj.getProductCode());
            prepStmt.setString(6, voObj.getProductGlAccountId());
            prepStmt.setString(7, voObj.getProductCogAccountId());
            prepStmt.setInt(8, DBHelper.toInt(voObj.getTotalProductQuantity()));
            prepStmt.setDouble(9, DBHelper.toDouble(voObj.getBuyProductPrice()));
            prepStmt.setDouble(10, DBHelper.toDouble(voObj.getProfitMargin()));
            prepStmt.setDouble(11, DBHelper.toDouble(voObj.getDiscountMargin()));
            prepStmt.setDouble(12, DBHelper.toDouble(voObj.getSalesPrice()));
            prepStmt.setDouble(13, DBHelper.toDouble(voObj.getPerProductPrice()));
            prepStmt.setString(14, voObj.getProductImagepath());
            Debug.print(""+voObj);

            int cnt = prepStmt.executeUpdate();
            prepStmt.close();
            System.out.println("Succefully inserted into Inventory Master :  "+cnt);
        }catch(Exception e){
            releaseConnection();
            Debug.print(" Error while Inserting : "+e.getMessage());
        }finally {
            releaseConnection();
        }
        return true;
     }
    
/**
  * Name         :selectInvMstrProdId
  * Description  :This method will display the InventoryMaster details
  * @ param      :specificationId,userTypeId,eventRegistrationTypeId,dueDatePrice,aftDueDatePrice
  * @return      :boolean
  * @throws      :SQLException
  */   
    public HLCInventoryMasterVO selectInvMstrProdId(String productId) throws SQLException {
        Debug.print("Inside the selectInventoryMaster  productId : "+productId);
        HLCInventoryMasterVO objINV = new HLCInventoryMasterVO();
        Vector vObj = new Vector();
        try {
            makeConnection();
            String selectStatement = "SELECT category_id,subcategory_id,product_name, product_description,product_code, " +
                    "product_gl_account_id,product_cog_account_id,total_product_quantity,buy_product_price,profit_margin," +
                    " discount_margin,sales_price,per_product_price,product_imagepath,add_date,active_status FROM  " 
                    +DBHelper.USEA_INV_INVENTORY_MASTER+" WHERE product_id = ? ";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, productId);
            rs = prepStmt.executeQuery();
            while (rs.next()) {
                objINV.setCategoryId(rs.getString(1));
                objINV.setSubcategoryId(rs.getString(2));
                objINV.setProductName(rs.getString(3));
                objINV.setProductDescription(rs.getString(4));
                objINV.setProductCode(rs.getString(5));
                objINV.setProductGlAccountId(rs.getString(6));
                objINV.setProductCogAccountId(rs.getString(7));
                objINV.setTotalProductQuantity(rs.getString(8));
                objINV.setBuyProductPrice(rs.getString(9));
                objINV.setProfitMargin(rs.getString(10));
                objINV.setDiscountMargin(rs.getString(11));
                objINV.setSalesPrice(rs.getString(12));
                objINV.setPerProductPrice(rs.getString(13));
                objINV.setProductImagepath(rs.getString(14));
                objINV.setAddDate(rs.getString(15));
                objINV.setActiveStatus(rs.getString(16));
                Debug.print(""+objINV);
             } 
             prepStmt.close();
        } catch (SQLException sqe) {
            throw new EJBException(sqe);
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return objINV;
     }     

/**
  * Name         :selectInvMstrCatagoryId
  * Description  :This method will display the InventoryMaster details
  * @ param      :specificationId,userTypeId,eventRegistrationTypeId,dueDatePrice,aftDueDatePrice
  * @return      :boolean
  * @throws      :SQLException
  */   
    public List selectInvMstrCatagoryId(String categoryId) throws SQLException {
        Debug.print("Inside the selectInvMstrCatagoryId  categoryId : "+categoryId);
        HLCInventoryMasterVO objINV = new HLCInventoryMasterVO();
        List listObj = new ArrayList();
        try {
            makeConnection();
            String selectStatement = "SELECT product_id,subcategory_id,product_name, product_description,product_code, " +
                    "product_gl_account_id,product_cog_account_id,total_product_quantity,buy_product_price,profit_margin," +
                    " discount_margin,sales_price,per_product_price,product_imagepath,add_date,active_status FROM  " 
                    +DBHelper.USEA_INV_INVENTORY_MASTER+" WHERE category_id = ? ";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, categoryId);
            rs = prepStmt.executeQuery();
            while (rs.next()) {
                objINV.setProductId(rs.getString(1));
                objINV.setSubcategoryId(rs.getString(2));
                objINV.setProductName(rs.getString(3));
                objINV.setProductDescription(rs.getString(4));
                objINV.setProductCode(rs.getString(5));
                objINV.setProductGlAccountId(rs.getString(6));
                objINV.setProductCogAccountId(rs.getString(7));
                objINV.setTotalProductQuantity(rs.getString(8));
                objINV.setBuyProductPrice(rs.getString(9));
                objINV.setProfitMargin(rs.getString(10));
                objINV.setDiscountMargin(rs.getString(11));
                objINV.setSalesPrice(rs.getString(12));
                objINV.setPerProductPrice(rs.getString(13));
                objINV.setProductImagepath(rs.getString(14));
                objINV.setAddDate(rs.getString(15));
                objINV.setActiveStatus(rs.getString(16));
                listObj.add(objINV);
                Debug.print(""+objINV);
             } 
             prepStmt.close();
        } catch (SQLException sqe) {
            throw new EJBException(sqe);
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return listObj;
     }       
   
/**
  * Name         :selectInvMstrSubCatagoryId
  * Description  :This method will display the InventoryMaster details
  * @ param      :specificationId,userTypeId,eventRegistrationTypeId,dueDatePrice,aftDueDatePrice
  * @return      :boolean
  * @throws      :SQLException
  */   
    public List selectInvMstrSubCatagoryId(String subcategoryId) throws SQLException {
        Debug.print("Inside the selectInvMstrCatagoryId  subcategoryId : "+subcategoryId);
        HLCInventoryMasterVO objINV = new HLCInventoryMasterVO();
        List listObj = new ArrayList();
        try {
            makeConnection();
            String selectStatement = "SELECT product_id,category_id,product_name, product_description,product_code, " +
                    "product_gl_account_id,product_cog_account_id,total_product_quantity,buy_product_price,profit_margin," +
                    " discount_margin,sales_price,per_product_price,product_imagepath,add_date,active_status FROM  " 
                    +DBHelper.USEA_INV_INVENTORY_MASTER+" WHERE subcategory_id = ? ";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, subcategoryId);
            rs = prepStmt.executeQuery();
            while (rs.next()) {
                objINV.setProductId(rs.getString(1));
                objINV.setCategoryId(rs.getString(2));
                objINV.setProductName(rs.getString(3));
                objINV.setProductDescription(rs.getString(4));
                objINV.setProductCode(rs.getString(5));
                objINV.setProductGlAccountId(rs.getString(6));
                objINV.setProductCogAccountId(rs.getString(7));
                objINV.setTotalProductQuantity(rs.getString(8));
                objINV.setBuyProductPrice(rs.getString(9));
                objINV.setProfitMargin(rs.getString(10));
                objINV.setDiscountMargin(rs.getString(11));
                objINV.setSalesPrice(rs.getString(12));
                objINV.setPerProductPrice(rs.getString(13));
                objINV.setProductImagepath(rs.getString(14));
                objINV.setAddDate(rs.getString(15));
                objINV.setActiveStatus(rs.getString(16));
                listObj.add(objINV);
                Debug.print(""+objINV);
             } 
             prepStmt.close();
        } catch (SQLException sqe) {
            throw new EJBException(sqe);
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return listObj;
     }        
/**
  * Name         :updateInventory
  * Description  :This method will Update the updateEventRegType
  * @ param      :specificationId,userTypeId,eventRegistrationTypeId,dueDatePrice,aftDueDatePrice
  * @return      :boolean
  * @throws      :SQLException
  */   
    public boolean updateInventory(HLCInventoryMasterVO objINV) throws SQLException {
        Debug.print("InventoryMasterDAO updateInventory");
       
        try {
            makeConnection();
            String updateStatement = "UPDATE " + DBHelper.USEA_INV_INVENTORY_MASTER + " SET  " +
            "product_name = ?, product_description = ?,product_code =?,product_gl_account_id = ?,product_cog_account_id = ?," +
            "total_product_quantity= ?,buy_product_price= ?,profit_margin = ?,discount_margin= ?,sales_price = ?,per_product_price = ?," +
            "product_imagepath = ? WHERE  product_id = ? ";
            prepStmt = con.prepareStatement(updateStatement);

            prepStmt.setString(1, objINV.getProductName());
            prepStmt.setString(2, objINV.getProductDescription());
            prepStmt.setString(3, objINV.getProductCode());
            prepStmt.setString(4, objINV.getProductGlAccountId());
            prepStmt.setString(5, objINV.getProductCogAccountId());
            prepStmt.setInt(6, DBHelper.toInt(objINV.getTotalProductQuantity()));
            prepStmt.setDouble(7, DBHelper.toDouble(objINV.getBuyProductPrice()));
            prepStmt.setDouble(8, DBHelper.toDouble(objINV.getProfitMargin()));
            prepStmt.setDouble(9, DBHelper.toDouble(objINV.getDiscountMargin()));
            prepStmt.setDouble(10, DBHelper.toDouble(objINV.getSalesPrice()));
            prepStmt.setDouble(11, DBHelper.toDouble(objINV.getPerProductPrice()));
            prepStmt.setString(12, objINV.getProductImagepath());
            prepStmt.setString(13, objINV.getProductId());
            Debug.print(" Product ID : "+objINV.getProductId());
            Debug.print(""+objINV);

            int cnt = prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection();
            Debug.print("Successfully Updated into Inventory Master......:" + cnt);
        }catch(Exception e){
            releaseConnection();
            Debug.print("Error while Updating Inventory Master : "+e.getMessage());
        }finally {
            releaseConnection();
        }
        return true;
     }     
/**
  * Name         :deleteInventoryMaster
  * Description  :This method will create the databacse connection
  * @ param      :
  * @return      :void
  * @throws      :EJBException
  */  
    public boolean deleteInventoryMaster(String productId) throws SQLException {
        try {
            makeConnection();
            //USEA_MSS_DISTRIBUTION_LIST
            System.out.println("Inside deleteInventoryMaster productId :  "+productId);
            String deleteStatement = "DELETE FROM "+DBHelper.USEA_INV_INVENTORY_MASTER +" WHERE product_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(deleteStatement);
            prepStmt.setString(1, productId);
            int cnt = prepStmt.executeUpdate();
            Debug.print("Successfully Delete the INV_INVENTORY_MASTER No Of Record : "+cnt);

            prepStmt.close();
        } catch (Exception ex) {
            releaseConnection();
            Debug.print("deleteInventoryMaster : " + ex.getMessage());
        }finally{
            releaseConnection();
        } 
        return true;
    }
    
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
