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
package com.hlcinv.session;

import com.hlcinv.util.Debug;
import com.hlcinv.util.HLCInventoryMasterDAO;
import com.hlcinv.util.HLCInventoryMasterVO;
import javax.ejb.*;
import javax.transaction.UserTransaction ;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;

/**
 * This is the bean class for the SaraswathySessionBean enterprise bean.
 * Created Sep 29, 2006 4:27:14 PM
 * @author harmohan
 */
public class HLCSaraswathySessionBean implements SessionBean, HLCSaraswathySessionRemoteBusiness {
    private SessionContext context;
    HLCInventoryMasterVO objINVBean = new HLCInventoryMasterVO();
    
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
        
    }
    
/**
  * Name         :insertINVMST
  * Description  :This method will insert a record into inventoryMaster table
  * @ param      :objINV
  * @return      :boolean
  * @throws      :RemoteException
  */       
  public boolean insertINVMST(HLCInventoryMasterVO objINV) throws RemoteException {
       HLCInventoryMasterDAO objINVDAO = new HLCInventoryMasterDAO();
       
       if (isNotNull(objINV.getCategoryId())){
                objINVBean.setCategoryId(objINV.getCategoryId());
       }
       if (isNotNull(objINV.getSubcategoryId())){
                objINVBean.setSubcategoryId(objINV.getSubcategoryId());
       }
       if (isNotNull(objINV.getProductName())){
                objINVBean.setProductName(objINV.getProductName());
       }
       if (isNotNull(objINV.getProductDescription())){
                objINVBean.setProductDescription(objINV.getProductDescription());
       }
       if (isNotNull(objINV.getProductCode())){
                objINVBean.setProductCode(objINV.getProductCode());
       }
       if (isNotNull(objINV.getProductGlAccountId())){
                objINVBean.setProductGlAccountId(objINV.getProductGlAccountId());
       }
       if (isNotNull(objINV.getProductCogAccountId())){
                objINVBean.setProductCogAccountId(objINV.getProductCogAccountId());
       }
       if (isNotNull(objINV.getTotalProductQuantity())){
                objINVBean.setTotalProductQuantity(objINV.getTotalProductQuantity());
       }
       if (isNotNull(objINV.getBuyProductPrice())){
                objINVBean.setBuyProductPrice(objINV.getBuyProductPrice());
       }
       if (isNotNull(objINV.getProfitMargin())){
                objINVBean.setProfitMargin(objINV.getProfitMargin());
       }
       if (isNotNull(objINV.getDiscountMargin())){
                objINVBean.setDiscountMargin(objINV.getDiscountMargin());
       }
       if (isNotNull(objINV.getSalesPrice())){
                objINVBean.setSalesPrice(objINV.getSalesPrice());
       }
       if (isNotNull(objINV.getPerProductPrice())){
                objINVBean.setPerProductPrice(objINV.getPerProductPrice());
       }
       if (isNotNull(objINV.getProductImagepath())){
                objINVBean.setProductImagepath(objINV.getProductImagepath());
       }
        boolean bol = false;
        try {
            bol = objINVDAO.insertRowInventoryMaster(objINVBean);
        }catch(Exception e){
            Debug.print(" Error In insertINVMST : "+e.getMessage());
        }
     return bol;
    }      
  
/**
  * Name         :updateINVMST
  * Description  :This method will insert a record into inventoryMaster table based on product id
  * @ param      :objINV
  * @return      :boolean
  * @throws      :RemoteException
  */       
  public boolean updateINVMST(HLCInventoryMasterVO objINV) throws RemoteException {
       HLCInventoryMasterDAO objINVDAO = new HLCInventoryMasterDAO();
       
       if (isNotNull(objINV.getProductId())){
                objINVBean.setProductId(objINV.getProductId());
       }
       if (isNotNull(objINV.getCategoryId())){
                objINVBean.setCategoryId(objINV.getCategoryId());
       }
       if (isNotNull(objINV.getSubcategoryId())){
                objINVBean.setSubcategoryId(objINV.getSubcategoryId());
       }
       if (isNotNull(objINV.getProductName())){
                objINVBean.setProductName(objINV.getProductName());
       }
       if (isNotNull(objINV.getProductDescription())){
                objINVBean.setProductDescription(objINV.getProductDescription());
       }
       if (isNotNull(objINV.getProductCode())){
                objINVBean.setProductCode(objINV.getProductCode());
       }
       if (isNotNull(objINV.getProductGlAccountId())){
                objINVBean.setProductGlAccountId(objINV.getProductGlAccountId());
       }
       if (isNotNull(objINV.getProductCogAccountId())){
                objINVBean.setProductCogAccountId(objINV.getProductCogAccountId());
       }
       if (isNotNull(objINV.getTotalProductQuantity())){
                objINVBean.setTotalProductQuantity(objINV.getTotalProductQuantity());
       }
       if (isNotNull(objINV.getBuyProductPrice())){
                objINVBean.setBuyProductPrice(objINV.getBuyProductPrice());
       }
       if (isNotNull(objINV.getProfitMargin())){
                objINVBean.setProfitMargin(objINV.getProfitMargin());
       }
       if (isNotNull(objINV.getDiscountMargin())){
                objINVBean.setDiscountMargin(objINV.getDiscountMargin());
       }
       if (isNotNull(objINV.getSalesPrice())){
                objINVBean.setSalesPrice(objINV.getSalesPrice());
       }
       if (isNotNull(objINV.getPerProductPrice())){
                objINVBean.setPerProductPrice(objINV.getPerProductPrice());
       }
       if (isNotNull(objINV.getProductImagepath())){
                objINVBean.setProductImagepath(objINV.getProductImagepath());
       }
        boolean bol = false;
        try {
            bol = objINVDAO.updateInventory(objINVBean);
        }catch(Exception e){
            Debug.print(" Error In updateINVMST : "+e.getMessage());
        }
     return bol;
    }    
    
/**
  * Name         :deleteINVMST
  * Description  :This method will delete a record based on based on product id in inventoryMaster table
  * @ param      :product id
  * @return      :boolean
  * @throws      :RemoteException
  */       
  public boolean deleteINVMST(String productId) throws RemoteException {
       HLCInventoryMasterDAO objINVDAO = new HLCInventoryMasterDAO();
        boolean bol = false;
        try {
            bol = objINVDAO.deleteInventoryMaster(productId);
        }catch(Exception e){
            Debug.print(" Error In deleteINVMST : "+e.getMessage());
        }
     return bol;
    }    
    
/**
  * Name         :displayINVBasedOnProdId
  * Description  :This method will list the inventory details based on product id
  * @ param      :product id
  * @return      :InventoryMasterVO
  * @throws      :RemoteException, FinderException
  */          
  public HLCInventoryMasterVO displayINVBasedOnProdId(String prodId) throws RemoteException, FinderException { 
        HLCInventoryMasterDAO objINVDAO = new HLCInventoryMasterDAO();
        HLCInventoryMasterVO invVO = null;
       
        try {
            invVO = objINVDAO.selectInvMstrProdId(prodId);
        }catch(Exception e){
            Debug.print(" Error In displayINVBasedOnProdId : "+e.getMessage());
        }
       return invVO;
    }   

  
/**
  * Name         :displayINVBasedOnCategoryId
  * Description  :This method will list the inventory details based on category id
  * @ param      :category id
  * @return      :InventoryMasterVO
  * @throws      :RemoteException, FinderException
  */          
  public List displayINVBasedOnCategoryId(String categoryId) throws RemoteException, FinderException { 
        HLCInventoryMasterDAO objINVDAO = new HLCInventoryMasterDAO();
        HLCInventoryMasterVO invVO = null;
        List listObj = null;
       
        try {
            listObj = objINVDAO.selectInvMstrCatagoryId(categoryId);
        }catch(Exception e){
            Debug.print(" Error In displayINVBasedOnProdId : "+e.getMessage());
        }
       return listObj;
    }       

/**
  * Name         :displayINVBasedOnCategoryId
  * Description  :This method will list the inventory details based on sub category id
  * @ param      :sub category id
  * @return      :InventoryMasterVO
  * @throws      :RemoteException, FinderException
  */          
    public List displayINVBasedOnSubCategoryId(String subCategoryId) throws RemoteException, FinderException { 
        HLCInventoryMasterDAO objINVDAO = new HLCInventoryMasterDAO();
        HLCInventoryMasterVO invVO = null;
        List listObj = null;
        try {
            listObj = objINVDAO.selectInvMstrSubCatagoryId(subCategoryId);
        }catch(Exception e){
            Debug.print(" Error In displayINVBasedOnProdId : "+e.getMessage());
        }
       return listObj;
    }           
    
   private boolean isNotNull(String data) {      
          return (data!=null && data.trim().length()>0) ? true :false;      
   } 
    
}
