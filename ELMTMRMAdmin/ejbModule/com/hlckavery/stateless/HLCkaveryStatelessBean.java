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
package com.hlckavery.stateless;

import com.hlcbreed.DAO.HLCHorseBreedDAO;
import com.hlccolor.DAO.HLCHorseColorDAO;
import com.hlcdonation.dao.HLCDonationDAO;
import com.hlcmrm.admin.HLCArabianSeaAdminLocal;
import com.hlcmrm.admin.HLCArabianSeaAdminLocalHome;
import com.hlcmrm.util.HLCBreedVO;
import com.hlcmrm.util.HLCContactTypeMaster;
import com.hlcmrm.util.DBHelper;
import com.hlcmrm.util.Debug;
import com.hlcmrm.util.HLCDonationVO;
import com.hlcmrm.util.HLCHorseColorVO;
import com.hlcmrm.util.HLCPublicationVO;
import com.hlcpublication.DAO.HLCPublicationDAO;
import javax.ejb.*;
import java.rmi.RemoteException;
import javax.naming.*;
import java.sql.*;
import javax.sql.*;
import java.util.*;

/**
 * This is the bean class for the kaveryStatelessBean enterprise bean.
 * Created Aug 28, 2006 8:55:17 PM
 * @author harmohan
 */
public class HLCkaveryStatelessBean implements SessionBean, HLCkaveryStatelessRemoteBusiness {
    private SessionContext context;
    private InitialContext ic = null;
    private static final String dbName = "java:/ELMTMSSQLDS";
    private Connection con;
    private ResultSet rs= null;
    private PreparedStatement prepStmt = null;
    private String name = "ejb/HLCAdminJNDI";
    private String contactTypeId;
    
    HLCArabianSeaAdminLocalHome home;
    HLCArabianSeaAdminLocal remote;
    HLCDonationDAO donationDAO;
    HLCHorseBreedDAO breedDAO;
    HLCHorseColorDAO colorDAO;
    HLCPublicationDAO publicationDAO;
    
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
    public void ejbCreate() {
    try{
        InitialContext jndiContext = getInitialContext();
        Object obj = jndiContext.lookup(name);
        home = (HLCArabianSeaAdminLocalHome)obj;
        donationDAO = new HLCDonationDAO();
        breedDAO = new HLCHorseBreedDAO();
        colorDAO = new HLCHorseColorDAO();
        publicationDAO = new HLCPublicationDAO();
    }
    catch(Exception e){
        e.printStackTrace();
    }
    }
    
   /**
  * Name         :addContactTypeMaster
  * Description  :This method will insert record into the Membership Refund Details table
  * @ param      :passing object of type of the class MembershipRefundDetails
  * @return      :void
  * @throws      :RemoteException, EJBException
  */      
    public boolean addContactTypeMaster(HLCContactTypeMaster objContact) throws RemoteException {
       boolean result = false;
       System.out.print("Contact Type ID : "+objContact.getContactTypeId());
       result = getContactTypeID(objContact.getContactTypeId(),objContact.getContactTypeName());
       if (!result){
           return false;
       } 
        try{
             remote = home.create(objContact);
        }
        catch(Exception exp){
             throw new EJBException("createContactTypeMaster : " + exp.getMessage());
        }
       return true;
    }
      //Start:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
	 public ArrayList getAllContactType() throws RemoteException{
        Debug.print("KaveryBean.getAllContactType() Calling.");
        ArrayList conTypeList= new ArrayList();

        try {
            System.out.println("Before calling selectContactType()");
            conTypeList=selectContactType();
             System.out.println("After calling selectContactType()");

        } catch(Exception e){
            Debug.print("Error while getAllContactTypeMaster in Kaverystatelessbean : "+e.getMessage());
        }
        return conTypeList;
    }
  //End:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011

    
	/* <!--Start:[DemographicDetails] For Demographic Type Addition, Editing and Deletion changes dated 11-Apr-2011 by Deepa-->*/
	public ArrayList getAllDemographicType() throws RemoteException{
        Debug.print("KaveryBean.getAllDemographicType() Calling.");
        ArrayList DemoTypeList= new ArrayList();

        try {
            System.out.println("Before calling selectDemoType()");
            DemoTypeList=selectDemoType();
             System.out.println("After calling selectDemoType()");

        } catch(Exception e){
            Debug.print("Error while getAllContactTypeMaster in Kaverystatelessbean : "+e.getMessage());
        }
        return DemoTypeList;
    }

/* <!--End:[DemographicDetails] For Demographic Type Addition, Editing and Deletion changes dated 11-Apr-2011 by Deepa-->*/

  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  //Start:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
	 /**
     * Name         :selectAccountTypeMaster
     * @ param      :Nil
     * @return      :ArrayList
     * @throws      :SQLException
     */
public ArrayList selectContactType() throws RemoteException,SQLException {
        Debug.print("tblcontactTypelist() calling");
        ArrayList conTypeList = new ArrayList();
        makeConnection();

        try {
            String selectStatement = "select contact_id, contact_type from tblContactTypeList";

            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            ResultSet rs = prepStmt.executeQuery();

            while (rs.next()) {
                String contactId = rs.getString(1);
                String contactType = rs.getString(2);
                String conTypeArray[] = {contactId,contactType};
                conTypeList.add(conTypeArray);
            }

            Debug.print("selectContactType() size :" + conTypeList.size());
            rs.close();
            prepStmt.close();

        } catch (SQLException sql) {
            releaseConnection();
            Debug.print("SQL Exception in selectContactType() :" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection();
            Debug.print("General Exception  in selectContactType() :" + e.getMessage());
        } finally {
            releaseConnection();
        }

        return conTypeList;
    }
//End:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
/* <!--Start:[DemographicDetails] For Demographic Type Addition, Editing and Deletion changes dated 11-Apr-2011 by Deepa-->*/


public ArrayList selectDemoType() throws RemoteException,SQLException {
        Debug.print("tblcontactTypelist() calling");
        ArrayList DemoTypeList = new ArrayList();
        makeConnection();

        try {
            String selectStatement = "select type_id,type_name from tblTypeMaster";

            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            ResultSet rs = prepStmt.executeQuery();

            while (rs.next()) {
                String TypeId = rs.getString(1);
                String TypeName = rs.getString(2);
                String DemoTypeArray[] = {TypeId,TypeName};
                DemoTypeList.add(DemoTypeArray);
            }

            Debug.print("selectContactType() size :" + DemoTypeList.size());
            rs.close();
            prepStmt.close();

        } catch (SQLException sql) {
            releaseConnection();
            Debug.print("SQL Exception in selectContactType() :" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection();
            Debug.print("General Exception  in selectContactType() :" + e.getMessage());
        } finally {
            releaseConnection();
        }

        return DemoTypeList;
    }
/* <!--End:[DemographicDetails] For Demographic Type Addition, Editing and Deletion changes dated 11-Apr-2011 by Deepa-->*/

/**
  * Name         :editContactTypeMaster
  * Description  :This method will retrieve the member details for refund details table
  * @ param      :None
  * @return      :member Identification number
  * @throws      :RemoteException, FinderException
  */     
     public boolean editContactTypeMaster(HLCContactTypeMaster objContact) throws RemoteException, FinderException{
       System.out.print("Contact Type ID : "+objContact.getContactTypeId());
       boolean result = false;
      /* if ((objContact.getContactTypeName().length()) > 79){
           Debug.print("Character Size is more then 80");
           return false;
       }*/
      //Start:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
       result = getContactTypeID(objContact.getContactTypeId(),objContact.getContactTypeName());
       if (!result){
           Debug.print(" Contact type Name Exist Cannot be update database with two same name");
           return false;
       }
      //Start:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
       /*result = getContactTypeID(objContact.getContactTypeName());
       if (!result){
           Debug.print(" Contact type Name Exist Cannot be update database with two same name");
           return false;
       }
        if (objContact == null && (objContact.getContactTypeId().equals("")) ) {
                throw new EJBException("Contact Type ID can't be empty");
            }*/
      
        if (contactTypeIdExists(objContact.getContactTypeId()) == false) {
            //throw new EJBException("Contact Type ID Not Exists : " + contactTypeId);
                 Debug.print("Kavery Session Bean contactTypeIdExists inside edit "+ contactTypeId);
               result = false;
        }
        else {//if (result == true){
       //End:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
            Debug.print("Kavery Session Bean contactTypeIdExists inside edit true part: "+ objContact.getContactTypeId());               
            remote.setContactTypeId(objContact.getContactTypeId());
            remote.setContactTypeName(objContact.getContactTypeName());
       //Start:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
            remote.setContactTypeDescription(objContact.getContactTypeDescription());
            remote.setContactType(objContact.getContactType());
            remote.setStatus(objContact.getStatus());
       //End:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
            result = true;
        }
           return result;
     }

 
   //Start:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
//public boolean deleteContactTypeMaster(String contactTypeId) throws RemoteException,FinderException{
 public boolean deleteContactTypeMaster(String chkConIdArr[]) throws RemoteException,FinderException{
        Debug.print("Kavery Session Bean deleteSponsorPlan");
       // Debug.print("Contact Type Id :" + contactTypeId);
		 Debug.print("Contact Type Id :" + chkConIdArr[0]);
        boolean result = false;
        if (chkConIdArr!=null) {
       // if(contactTypeId==null){
           // throw new EJBException("contactTypeId can't be empty");
        //}
      // if (contactTypeIdExists(contactTypeId) == false) {
		   boolean chkResult=contactTypeIdExists(chkConIdArr[0]);
		   //     throw new EJBException("contactTypeId Not Exists" + chkConIdArr[0]);
			  //	  }
       // try {
		   if(chkResult==true){
                  result=remove(chkConIdArr);
			  	 // remote.remove();
				 // result = true;
				 //   } catch (Exception ex) {
             // throw new EJBException("removeContactTypeName: " + ex.getMessage());
       }
		}
			return result;
 }
 //End:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011


//Start:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
//=================for delete contact type=========
  public boolean remove(String chkConIdArr[]) {
        Debug.print("KaveryBean.remove calling():");
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String deleteStatement = "delete from " + DBHelper.USEA_CONTACT_TYPEMASTER   + "  where contact_type_id = ? ";

            for(int i=0;i<chkConIdArr.length;i++)
            {
                prepStmt = con.prepareStatement(deleteStatement);
                //prepStmt.setString(1, contactTypeId);
                prepStmt.setString(1, chkConIdArr[i]);

                Debug.print("deleteContact():"+deleteStatement+"=="+chkConIdArr[i]);
                prepStmt.executeUpdate();
                prepStmt.close();
            }
		   releaseConnection();
        }

        catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in RoleManagementDAOImpl.deleteRole:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in RoleManagementDAOImpl.deleteRole:" + e.getMessage());
        }
        return true;
    }
  //End:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
/**
  * Name         :getCountryMailTypeID
  * Description  :This method will check for existing contact type name and return refund id
  * @ param      :None
  * @return      :boolean
  * @throws      :
  */     
    public boolean getContactTypeID(String contactTypeId,String contactTypeName) {
        String contactID = null;
        try{
            ArrayList result =  (ArrayList)home.findByContactTypeID(contactTypeId,contactTypeName);
            if(result!=null){
                Iterator e = result.iterator();
                while(e.hasNext()){
                   HLCArabianSeaAdminLocal localCTMRemote = (HLCArabianSeaAdminLocal)e.next();
                   contactID = localCTMRemote.getContactTypeId();
                }
            }
        }
        catch(Exception e){
            Debug.print("Exception in getContactTypeID:" + e);
        }
         if (contactID != null){
            return false;
         }else {
            return true;
         }
       
    }         

    
/**
  * Name         :getRefundDetails
  * Description  :This method will retrieve the member details for refund details table
  * @ param      :None
  * @return      :member Identification number
  * @throws      :RemoteException, FinderException
  */     
    public Collection getContactTypeDetails() throws RemoteException, FinderException {
        
        ArrayList A = new ArrayList();
        Collection col = home.findByContactTypeName();
                 ArrayList array = new ArrayList(col);
                for (Iterator it=array.iterator(); it.hasNext( ); ) { 
                   // Object anObject = it.next( ); 
                    String str = String.valueOf(it.next( )); 
                    String[] result = str.split(":");
                     for (int x=1; x<result.length; x++){
                         System.out.println(result[x]);
                         A.add(result[1]);
                     }

                   //System.out.println( nonuseaOrgId); //anObject.toString() ); 
                }
       // Collection c = home.findByNonUSEAOrgDetails();
        return A;
        //Collection c = home.findByContactTypeName(contactTypeName);
       // return c;
    }    
/**
  * Name         :displayContactTypedetails
  * Description  :This method will check for existing contact type name and return refund id
  * @ param      :None
  * @return      :boolean
  * @throws      :
  */     
    public Vector displayContactTypedetails() throws RemoteException, FinderException{
        String contactID = null;
        String contactName = null;
        //Start:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
        String contactDescription = null;
        String contactType = null;
        String status = null;
        String flag=null;
        //End:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
        Vector vObj = new Vector();
        try {
            makeConnection();

            //Start:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
            //String selectStatement = "SELECT contact_type_id, contact_type_name,contact_type_desc,contact_type,status1 FROM  " +DBHelper.USEA_CONTACT_TYPEMASTER;
            String selectStatement="select case when contact_type_id in(select contact_type_id from tblContactDetails union all select contact_type_id from tblUserMaster union all select contact_type_id from tblShoppingOrders) then '0' else '1' "+
                                    "end flag, contact_type_id, contact_type_name,contact_type_desc,a.contact_type ,b.active_status from tblContactTypeList a,tblContactTypeMaster b "+
                                    "where a.contact_id = b.contact_type ";
            System.out.println("DBHelper.USEA_CONTACT_TYPEMASTER "+DBHelper.USEA_CONTACT_TYPEMASTER);
            //End:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011

            prepStmt = con.prepareStatement(selectStatement);
            rs = prepStmt.executeQuery();
            while (rs.next()) {
               //Start:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
                //contactID = rs.getString(1);
                //contactName = rs.getString(2);
                flag=rs.getString(1);
                contactID = rs.getString(2);
                contactName = rs.getString(3);
                contactDescription = rs.getString(4);
                contactType = rs.getString(5);
                status = rs.getString(6);
                //String contact[] = {contactID, contactName,contactDescription,contactType,status1};
                String contact[] = {contactID, contactName,contactDescription,contactType,status,flag};
                //End:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
                vObj.add(contact);
             } 
           
             prepStmt.close();
              return vObj;
        } catch (SQLException sqe) {
            throw new EJBException(sqe);
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        
    
       
        
        
       /* try{
            ArrayList result =  (ArrayList)home.findByContactTypeName();
            if(result!=null){
                Iterator e = result.iterator();
                while(e.hasNext()){
                   ArabianSeaAdminLocal localCTMRemote = (ArabianSeaAdminLocal)e.next();
                   contactID = localCTMRemote.getContactTypeId();
                   contactName = localCTMRemote.getContactTypeName();
                   String contact[] = {contactID, contactName};
                   vObj.add(contact);
                }
            }
        }
        catch(Exception e){
            Debug.print("Exception in getContactTypeID:" + e);
        }*/
      // return vObj;  
    }
    
        
       public String [] getContactTypedetailsById(String contactTypeId) throws RemoteException, FinderException {
        String contactID = null;
        String contactName = null;
        //Start:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
        String contactDescription = null;
        String contactType = null;
        String status = null;
        String flag = null;
        //End:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
        String [] strContDet  = null;
        try {
            makeConnection();
            //Start:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
           // String selectStatement = "SELECT contact_type_id, contact_type_name FROM  " +DBHelper.USEA_CONTACT_TYPEMASTER + " WHERE contact_type_id=? ";
           String selectStatement= "select case when contact_type_id in(select contact_type_id from tblContactDetails union all select contact_type_id from tblUserMaster  union all select contact_type_id from tblShoppingOrders) then '0' else '1' end flag, contact_type_id, contact_type_name,contact_type_desc,contact_type,active_status from " +DBHelper.USEA_CONTACT_TYPEMASTER + " WHERE contact_type_id=? " ;
            System.out.println("DBHelper.USEA_CONTACT_TYPEMASTER "+DBHelper.USEA_CONTACT_TYPEMASTER);
            //End:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,contactTypeId);
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                //Start:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
                //contactID = rs.getString(1);
                //contactName = rs.getString(2);
                flag=rs.getString(1);
                contactID = rs.getString(2);
                contactName = rs.getString(3);
                contactDescription = rs.getString(4);
                contactType = rs.getString(5);
                status = rs.getString(6);
                // String contact[] = {contactID, contactName};
                String contact[] = {contactID, contactName,contactDescription,contactType,status,flag};
                //End:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
               strContDet = contact;
            } 
            rs.close();
            prepStmt.close();
        } catch (SQLException sqe) {
            throw new EJBException(sqe);
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
         return strContDet;
       }
    
    
    
/**
  * Name         :memberExists
  * Description  :This method will check for the existing of memberId and call the findByPrimaryKey method
  * @ param      :None
  * @return      :boolean value
  * @throws      :none
  */         
   private boolean contactTypeIdExists(String contactTypeId) {
        boolean result =false;
//Debug.print("Kavery Session Bean contactTypeIdExists in side loop: "+contactTypeId);
        if ( contactTypeId !=this.contactTypeId ) {
            try {
              //  Debug.print("Kavery Session Bean contactTypeIdExists in side loop: "+contactTypeId);
                remote = home.findByPrimaryKey(contactTypeId);
                this.contactTypeId = contactTypeId;
                 Debug.print("Kavery Session Bean contactTypeIdExists in side home: "+ home);
                Debug.print("Kavery Session Bean contactTypeIdExists in side remote: "+ remote);
                 result =true;
            } catch (Exception ex) {
               result =true;
               System.out.println("Exception:" + ex);
            }
        }
        Debug.print("Kavery Session Bean contactTypeIdExists  "+ result);
        return result;
    }
   
   
     public boolean createDonation(String donationName, String donationPrice, String transaction_type_id, boolean precheckStatus, int priority) throws RemoteException {
        Debug.print("KaverySessionBean createDonation() donationName:" + donationName);
        boolean result = false;
        if(donationName!=null && donationName.trim().length()!=0 &&  donationPrice!=null && donationPrice.trim().length()!=0){
            if(donationDAO.isDonationTypeNameExist(donationName)){
                result = donationDAO.insertDonationPrice(donationName,donationPrice,transaction_type_id,precheckStatus,priority);
                Debug.print("KaverySessionBean Result:" +  result);
            }
        }
        return result;
    }
     
    public boolean editDonation(String donationId, String donationName, String donationPrice, String transaction_type_id, boolean precheckStatus,int priority) throws RemoteException {
        Debug.print("KaverySessionBean createDonation() donationId:" + donationId);
        boolean result = false;
        if(donationId!=null && donationId.trim().length()!=0 && donationName!=null && donationName.trim().length()!=0 &&  donationPrice!=null && donationPrice.trim().length()!=0){
            if(donationDAO.isDonationTypeNameEditExist(donationId,donationName)){
                result = donationDAO.updateDonationPrice(donationId,donationName,donationPrice,transaction_type_id,precheckStatus,priority);
                Debug.print("KaverySessionBean Result:" +  result);
            }
        }
        return result;
    }
    public boolean activateDonation(String donationId) throws RemoteException {
        boolean bol = false;
        Debug.print("MRMAdmin KaverySessionBean activateDonation");
        bol = donationDAO.activeDonation(donationId);
        
        return bol;
    }
    
     public boolean deactivateDonation(String donationId) throws RemoteException {
        boolean bol = false;
        Debug.print("MRMAdmin KaverySessionBean deactivateDonation");
        bol = donationDAO.deactiveDonation(donationId);
        
        return bol;
    }
    
     public ArrayList getAllDonationDetails() throws RemoteException{
        Debug.print("KaverySessionBean getAllDonationDetails");
        ArrayList results = donationDAO.selectAllDonationPriceDetails();
        return results; 
    }
    
    public ArrayList getAllDonationDetailsBasedOnStatus(boolean status) throws RemoteException{
        Debug.print("KaverySessionBean getAllDonationDetails");
        ArrayList results = donationDAO.selectAllDonationPriceDetailsBasedOnStatus(status);
        return results; 
    }

    public HLCDonationVO getDonationDetailsById(String donationId) throws RemoteException{
        Debug.print("KaverySessionBean getDonationDetailsById donationId : " + donationId);
        HLCDonationVO results = null;
        if(donationId!=null && donationId.trim().length()!=0){
            results = donationDAO.selectDonationPriceDetailsById(donationId);
        }
        return results; 
    }
    
    //  Horse Color CRUD Operation
     public boolean createHorseColor(String colorDesc) throws RemoteException {
        Debug.print("KaverySessionBean createHorseColor() colorDesc:" + colorDesc);
        boolean result = false;
        if(colorDesc!=null && colorDesc.trim().length()!=0){
            if(colorDAO.isHorseColorExist(colorDesc)){
                result = colorDAO.insertHorseColor(colorDesc);
                Debug.print("KaverySessionBean createHorseColor Result:" +  result);
            }
        }
        return result;
    }
    /* <!--Start:[DemographicDetails] For Demographic Type Addition, Editing and Deletion changes dated 11-Apr-2011 by Deepa-->*/

     public boolean createDemographic(String Demo,String DemoDesc,String Type,boolean status) throws RemoteException {
        Debug.print("KaverySessionBean createDemographic() Demo:" + Demo);
        Debug.print("KaverySessionBean createDemographic() colorDesc:" + DemoDesc);
        boolean result = false;
        if(Demo!=null && Demo.trim().length()!=0 && DemoDesc!=null && DemoDesc.trim().length()!=0 &&  Type!=null && Type.trim().length()!=0){
            if(colorDAO.isdemoAddExist(Demo,DemoDesc,Type,status)){
                result = colorDAO.insertDemo(Demo,DemoDesc,Type,status);
                Debug.print("KaverySessionBean createDemographic() Result:" +  result);
            }
        }
        return result;
    }
  /*<!--End:[DemographicDetails] For Demographic Type Addition, Editing and Deletion changes dated 11-Apr-2011 by Deepa-->*/
    public boolean editHorseColor(String colorId, String colorDesc) throws RemoteException {
        Debug.print("KaverySessionBean editHorseColor() colorId:" + colorId);
        boolean result = false;
        if(colorId!=null && colorId.trim().length()!=0 && colorDesc!=null && colorDesc.trim().length()!=0 ){
            if(colorDAO.isHorseColorEditExist(colorId, colorDesc)){
                result = colorDAO.updateHorseColor(colorId, colorDesc);
                Debug.print("KaverySessionBean editHorseColor Result:" +  result);
            }
        }
        return result;
    }
      /* <!--Start:[DemographicDetails] For Demographic Type Addition, Editing and Deletion changes dated 11-Apr-2011 by Deepa-->*/
     public boolean editDemo(String demoId, String Demographic,String desc,String demoType,boolean status) throws RemoteException {
        Debug.print("KaverySessionBean editDemo() :" + demoId);
        boolean result = false;
        
       // if(demoId!=null && demoId.trim().length()!=0 && Demographic!=null && Demographic.trim().length()!=0 && desc!=null && desc.trim().length()!=0 && demoType.trim().length()!=0 && demoType!=null ){
            if(colorDAO.isdemoEditExist(demoId,Demographic)){
                result = colorDAO.updateDemo(demoId, Demographic,desc,demoType,status);
                Debug.print("KaverySessionBean editHorseColor Result:" +  result);
            //}
        }
        return result;
    }
       /* <!--End:[DemographicDetails] For Demographic Type Addition, Editing and Deletion changes dated 11-Apr-2011 by Deepa-->*/

       /* <!--Start:[DemographicDetails] For Demographic Type Addition, Editing and Deletion changes dated 11-Apr-2011 by Deepa-->*/
  public boolean deletedemo(String chkdemoIdArr[]) throws RemoteException{
        Debug.print("KaverySessionBean  deleteRole");
        boolean result = false;

         if(chkdemoIdArr!=null){
           Debug.print("inside  deleteRole");
                result = colorDAO.deletedemo(chkdemoIdArr);
                  Debug.print("after  deleteRole");
        }
        Debug.print("KaverySessionBean deleteDemo() Result:" + result);
        return result;
    }
    /* <!--End:[DemographicDetails] For Demographic Type Addition, Editing and Deletion changes dated 11-Apr-2011 by Deepa-->*/
     public ArrayList getAllHorseColorDetails() throws RemoteException{
        Debug.print("KaverySessionBean getAllHorseColorDetails");
        ArrayList results = colorDAO.selectAllHorseColorDetails();
        return results; 
    }
       /* <!--Start:[DemographicDetails] For Demographic Type Addition, Editing and Deletion changes dated 11-Apr-2011 by Deepa-->*/
       public ArrayList getAllDemoDetails() throws RemoteException{
        Debug.print("KaverySessionBean getAllDemoDetails");
        ArrayList results = colorDAO.selectAllDemoDetails();
        return results;
    }
       /* <!--End:[DemographicDetails] For Demographic Type Addition, Editing and Deletion changes dated 11-Apr-2011 by Deepa-->*/

    public HLCHorseColorVO getHorseColorDetailsById(String colorId) throws RemoteException{
        Debug.print("KaverySessionBean getHorseColorDetailsById colorId : " + colorId);
        HLCHorseColorVO results = null;
        if(colorId!=null && colorId.trim().length()!=0){
            results = colorDAO.selectHorseColorById(colorId);
        }
        return results; 
    }
      /* <!--Start:[DemographicDetails] For Demographic Type Addition, Editing and Deletion changes dated 11-Apr-2011 by Deepa-->*/
     public HLCHorseColorVO getDemoById(String demoId) throws RemoteException{
        Debug.print("KaverySessionBean getDemoById colorId : " + demoId);
        HLCHorseColorVO results = null;
        if(demoId!=null && demoId.trim().length()!=0){
            results = colorDAO.selectDemoById(demoId);
        }
        return results;
    }
  /* <!--End:[DemographicDetails] For Demographic Type Addition, Editing and Deletion changes dated 11-Apr-2011 by Deepa-->*/
  //----------------------------------------------------------------------------------------------------  
     //  Publication CRUD Operation
     public boolean createPublication(String publicationName) throws RemoteException {
        Debug.print("KaverySessionBean createHorseColor() publicationName:" + publicationName);
        boolean result = false;
        if(publicationName!=null && publicationName.trim().length()!=0){
            if(publicationDAO.isPublicationNameExist(publicationName)){
                result = publicationDAO.insertPublicationName(publicationName);
                Debug.print("KaverySessionBean createPublication Result:" +  result);
            }
        }
        return result;
    }
     
    public boolean editPublication(String publicationId, String publicationName) throws RemoteException {
        Debug.print("KaverySessionBean editPublication() publicationId:" + publicationId);
        boolean result = false;
        if(publicationId!=null && publicationId.trim().length()!=0 && publicationName!=null && publicationName.trim().length()!=0 ){
            if(publicationDAO.isPublicationNameEditExist(publicationId,publicationName)){
                result = publicationDAO.updatePublication(publicationId,publicationName);
                Debug.print("KaverySessionBean editPublication Result:" +  result);
            }
        }
        return result;
    }
    
     public ArrayList getAllPublicationDetails() throws RemoteException{
        Debug.print("KaverySessionBean selectAllPublicationDetails()");
        ArrayList results = publicationDAO.selectAllPublicationDetails();
        return results; 
    }

    public HLCPublicationVO getPublicationDetailsById(String publicationId) throws RemoteException{
        Debug.print("KaverySessionBean getPublicationDetailsById publicationId : " + publicationId);
        HLCPublicationVO results = null;
        if(publicationId!=null && publicationId.trim().length()!=0){
            results = publicationDAO.selectPublicationDetailsById(publicationId);
        }
        return results; 
    }
//--------------------------------------------------------------------------------------------------------------------    
    
     //  Horse Breed CRUD Operation
	 //Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 10-Mar-2011 by Satheesh
    // public boolean createHorseBreed(String breedDesc) throws RemoteException {
     public boolean createHorseBreed(String breedDesc,String breedStatus,String breedSpecieId) throws RemoteException {
        Debug.print("KaverySessionBean createHorseBreed() breedDesc:" + breedDesc);
        boolean result = false;
        if(breedDesc!=null && breedDesc.trim().length()!=0 ){
            if(breedDAO.isHorseBreedExist(breedDesc)){
                //Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 12-Mar-2011
                result = breedDAO.insertHorseBreed(breedDesc,breedStatus,breedSpecieId);
                Debug.print("KaverySessionBean createHorseBreed Result:" +  result);
            }

        }
        return result;
    }
		 //End:[BreedMgt] For breed Addition, Editing and Deletion changes dated 10-Mar-2011 by Satheesh
   //Starts:[SpeciesMgt] For Species Addition, Editing and Deletion changes dated 10-Mar-2011
      public boolean createHorseSpecies(String speciesname,String speciesStatus) throws RemoteException {
        Debug.print("KaverySessionBean createHorseBreed() breedDesc:" + speciesname);
        boolean result = false;
        if(speciesname!=null && speciesname.trim().length()!=0 && breedDAO.isHorseSpeciesExist(speciesname)){
           
                //Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 12-Mar-2011
                result = breedDAO.insertHorseSpecies(speciesname,speciesStatus);
                Debug.print("KaverySessionBean createHorseBreed Result:" +  result);
          

        }
        return result;
    }

     
//Ends:[SpeciesMgt] For Species Addition, Editing and Deletion changes dated 10-Mar-2011

	 //Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 10-Mar-2011 by Satheesh
     public boolean editHorseBreed(String breedDesc,String breedStatus, String breedId) throws RemoteException {
        Debug.print("KaverySessionBean editHorseBreed() breedDesc:" + breedDesc);
        boolean result = false;
        if(breedId!=null && breedId.trim().length()!=0 && breedDesc!=null && breedDesc.trim().length()!=0 ){
            if(breedDAO.isHorseBreedEditExist(breedDesc,breedId)){
                //Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 10-Mar-2011
                 result = breedDAO.updateHorseBreed(breedDesc,breedStatus,breedId);
                Debug.print("KaverySessionBean editHorseBreed Result:" +  result);
            }
        }
        return result;
    }
	//End:[BreedMgt] For breed Addition, Editing and Deletion changes dated 10-Mar-2011 by Satheesh
//Starts:[SpeciesMgt] For Species Addition, Editing and Deletion changes dated 10-Mar-2011
      public boolean editHorseSpecies(String SpeciesName,String speciesStatus,String speciesId) throws RemoteException {
        Debug.print("KaverySessionBean editHorseBreed() breedDesc:" + SpeciesName);
        boolean result = false;
        
        if( SpeciesName!=null && SpeciesName.trim().length()!=0 )

        {
            if( breedDAO.isHorseSpeciesEditExist(SpeciesName,speciesId))
            {
            
                //Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 10-Mar-2011
                 result = breedDAO.updateHorseSpecies(SpeciesName,speciesStatus,speciesId);
                Debug.print("KaverySessionBean editHorseBreed Result:" +  result);
           
        }
          }
        return result;
    }
     //Ends:[SpeciesMgt] For Species Addition, Editing and Deletion changes dated 10-Mar-2011

    	 //Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 10-Mar-2011 by Satheesh
      public boolean deleteBreed(String  chkRoleIdArr[]) throws RemoteException {

        boolean result = false;
        if(chkRoleIdArr!=null   ){

                result = breedDAO.deleteBreed(chkRoleIdArr);
                Debug.print("KaverySessionBean editHorseBreed Result:" +  result);
        }
        return result;
    }
		 //End:[BreedMgt] For breed Addition, Editing and Deletion changes dated 10-Mar-2011 by Satheesh
     //Starts:[SpeciesMgt] For Species Addition, Editing and Deletion changes dated 10-Mar-2011
      public boolean  deleteSpecies(String chkRoleIdArr[]) throws RemoteException {

        boolean result = false;
        if(chkRoleIdArr!=null   ){

                result = breedDAO.deleteSpecies(chkRoleIdArr);
                Debug.print("KaverySessionBean editHorseBreed Result:" +  result);
        }
        return result;
    }
     //Ends:[SpeciesMgt] For Species Addition, Editing and Deletion changes dated 10-Mar-2011
       public ArrayList getAllHorseBreedDetails() throws RemoteException{
        Debug.print("KaverySessionBean getAllHorseBreedDetails");
           System.out.print("cal the remote class ");
        ArrayList results = breedDAO.selectAllHorseBreedDetails();
        return results;
    }

    public HLCBreedVO getHorseBreedDetailsById(String breedId) throws RemoteException{
        Debug.print("KaverySessionBean getHorseColorDetailsById breedId : " + breedId);
        HLCBreedVO results = null;
        if(breedId!=null && breedId.trim().length()!=0){
            results = breedDAO.selectHorseBreedById(breedId);
        }
        return results; 
    }
    //Starts:[SpeciesMgt] For Species Addition, Editing and Deletion changes dated 10-Mar-2011
    public HLCBreedVO getHorseSpeciesDetailsById(String speciesId) throws RemoteException{
        Debug.print("KaverySessionBean getHorseColorDetailsById breedId : " + speciesId);
        HLCBreedVO results = null;
        if(speciesId!=null && speciesId.trim().length()!=0){
            results = breedDAO.selectHorseSpeciesById(speciesId);
        }
        return results;
    }
    //Ends:[SpeciesMgt] For Species Addition, Editing and Deletion changes dated 10-Mar-2011
	 //Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 10-Mar-2011 by Satheesh
//  this used for select in species and select species id from user tybe master table non human
     public Vector displayUserTypeDetails() throws RemoteException, FinderException{
        Debug.print("KaverySessionBean getAllHorseBreedDetails**********************");

        Vector results = breedDAO.displayUserTypeDetails();
        Debug.print("After calling KaverySessionBean getAllHorseBreedDetails **********************");
        return results;
    }
		 //End:[BreedMgt] For breed Addition, Editing and Deletion changes dated 10-Mar-2011 by Satheesh
	 //Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 10-Mar-2011 by Satheesh
// uTybe Id is species Id
   public ArrayList getAllHorseBreedDetailsByspecieId(String uTypeId) throws RemoteException{
        Debug.print("KaverySessionBean getAllHorseBreedDetails");
           System.out.print("cal the remote class ");
         ArrayList results = breedDAO. selectAllHorseBreedDetailsByspecies(uTypeId);
        return results;
    }
		 //End:[BreedMgt] For breed Addition, Editing and Deletion changes dated 10-Mar-2011 by Satheesh
   public ArrayList getAllHorseSpeciesDetails(String uTypeId) throws RemoteException{
        Debug.print("KaverySessionBean getAllHorseBreedDetails");
           System.out.print("cal the remote class ");
         ArrayList results = breedDAO. selectAllHorseSpeciesDetails(uTypeId);
        return results;
    }


     //=============Dhivya Here: Demographic Category{Non Human}============================

      public ArrayList getAllTypeDetails() throws RemoteException{
        Debug.print("KaverySessionBean getAllTypeDetails");
        ArrayList objRes = colorDAO.selectAllTypeDetails();
        return objRes;
    }

  public ArrayList getAllSpeciesDetails() throws RemoteException{
        Debug.print("KaverySessionBean getAllSpeciesDetails");
        ArrayList objRes = colorDAO.selectAllSpeciesDetails();
        return objRes;
    }

    public ArrayList getDemographicListForNonHuman(String speciesId) throws RemoteException{
        Debug.print("KaverySessionBean getDemographicListForNonHuman");

        ArrayList objRes=new ArrayList();
        if(speciesId!=null && speciesId.length()!=0){
         objRes = colorDAO.selectDemographicListForNonHuman(speciesId);
        }
        return objRes;
    }

  public boolean createDemographicCategoryForNonHuman(String speciesId, String demo, String demoDesc, String TypId, String demoStatus) throws RemoteException {
        Debug.print("KaverySessionBean createDemographicCategoryForNonHuman");
        boolean result = false;
       if(demo!=null && demo.trim().length()!=0 &&  speciesId!=null && speciesId.trim().length()!=0){

           boolean res=colorDAO.isDemographicNameExist(speciesId,demo);
            if(res==false){
                result = colorDAO.insertDemographicCategoryDetails(speciesId,demo,demoDesc,TypId,demoStatus);
                Debug.print("KaverySessionBean Result:" +  result);
            }
        }
        return result;
    }

  public ArrayList getDemographicDetailsForNonHuman(String nonHumanDemoId) throws RemoteException{
        Debug.print("KaverySessionBean getDemographicDetailsForNonHuman");

        ArrayList objRes=new ArrayList();
        if(nonHumanDemoId!=null && nonHumanDemoId.length()!=0){
         objRes = colorDAO.selectDemographicDetailsForNonHuman(nonHumanDemoId);
        }
        return objRes;
    }


  public boolean updateDemographicCategoryForNonHuman(String speciesId, String nonHumanDemoId, String demo, String demoDesc, String TypId, String demoStatus) throws RemoteException {
        Debug.print("KaverySessionBean updateDemographicCategoryForNonHuman");
        boolean result = false;
       // if(nonHumanDemoId!=null && nonHumanDemoId.trim().length()!=0 &&  speciesId!=null && speciesId.trim().length()!=0){
               if(colorDAO.isNonDemographicNameExist(speciesId,nonHumanDemoId,demo)){
                result = colorDAO.updateDemographicCategoryDetails(speciesId,nonHumanDemoId,demo,demoDesc,TypId,demoStatus);
    Debug.print("KaverySessionBean Result:" +  result);

        }
        return result;
    }



public boolean deleteDemographicCategoryForNonHuman(String chkNonHumanDemoIdArr[]) throws RemoteException {
 Debug.print("KaverySessionBean deleteDemographicCategoryForNonHuman");
        boolean result = false;
        if(chkNonHumanDemoIdArr!=null   ){

                result = colorDAO.deleteDemographicCategoryDetails(chkNonHumanDemoIdArr);
                Debug.print("KaverySessionBean deleteDemographicCategoryForNonHuman Result:" +  result);
        }
        return result;
    }

  //====Ends Here===============================================

//=================Dhivya Here: Demographic Category (Non Human)Pagination function [15th April'11]===========


/*public int demographicCategoryRowCount(String speciesId) throws RemoteException {
 Debug.print("KaverySessionBean demographicCategoryRowCount");
        int rowCnt = 0;
        if(speciesId!=null   ){

                rowCnt = colorDAO.demographicCategoryRowCount(speciesId);
                Debug.print("KaverySessionBean demographicCategoryRowCount rowCnt:" +  rowCnt);
        }
        return rowCnt;
    }

public ArrayList getDemographicCategoryForPagination(String speciesId, int pNo) throws RemoteException {
        Debug.print("KaverySessionBean demographicCategoryRowCount speciesId:" + speciesId);
        ArrayList demoDetails = new ArrayList();
        try {
            demoDetails = colorDAO.selectDemographicCategoryForPagination(speciesId,pNo);
        }catch(Exception e){
            Debug.print("Exception in getDemographicCategoryForPagination:" + e.getMessage());
        }
        Debug.print("getDemographicCategoryForPagination.size()" + demoDetails.size());
        return demoDetails;
    }
*/

//===========================Ends Here====================================================


//============Dhivya Here: Species Breed Management:===================================

public String getSpeciesName(String speciesId) throws RemoteException{
        Debug.print("KaverySessionBean getSpeciesName");

         String speciesName = breedDAO. selectSpeciesName(speciesId);
        return speciesName;
    }


  //================End Here==================================


public ArrayList getAllHorseSpeciesDetails() throws RemoteException{
        Debug.print("KaverySessionBean getAllHorseBreedDetails");
           System.out.print("cal the remote class ");
         ArrayList results = breedDAO. selectAllHorseSpeciesDetailsfor();
        return results;
    }

//Starts:[SpeciesMgt] For Species Addition, Editing and Deletion changes dated 10-Mar-2011
   public ArrayList getAllHorseSpeciesDetailsByspecieId(String speciesId) throws RemoteException{
        Debug.print("KaverySessionBean getAllHorseBreedDetails");
           System.out.print("cal the remote class ");
         ArrayList results = breedDAO. selectAllHorseBreedDetailsByspecies(speciesId);
        return results;
    }



 // Starts:[MemMgnt] For Breed Characteristic Detail Management Dated:21-4-2011
   
     public Vector getBreedCharacterCategory(String uTypeId) throws RemoteException, FinderException{
        Debug.print("KaverySessionBean getBreedCharacterCategory##########");
        Vector results = breedDAO.selectAllCharacterForBreed(uTypeId);
        return results;
    }

    public Vector getAllBreedCharacterDetails(String  uTypeId, String charId)  throws RemoteException{
        Debug.print("KaverySessionBean getAllBreedCharDetails");
        Vector result = breedDAO.selectAllCharDetailsForBreed(uTypeId,charId);
        return result;
    }
    public Vector getBreedCharacterEditDetails(String characterdetId)  throws RemoteException{
        Debug.print("KaverySessionBean selectCharEditDetailsForBreed");
        Vector result = breedDAO.selectCharEditDetailsForBreed(characterdetId);
        return result;
    }
     public boolean createBreedCharDetails(String charDet, String charId, String status) throws RemoteException {
        Debug.print("KaverySessionBean createBreedCharDetails() charDet:" + charDet);
        boolean result = false;
        if(charDet!=null && charDet.trim().length()!=0 ){
           if(breedDAO.isBreedCharDetExist(charDet,charId)){
                result = breedDAO.insertBreedCharDetails(charDet, charId, status);
                Debug.print("KaverySessionBean createHorseBreed Result:" +  result);
           }
        }
        return result;
    }
      public boolean updateBreedCharDetail(String characterdetid,String charDet,String status,String charId) throws RemoteException {
        Debug.print("KaverySessionBean updateBreedCharDetail() charDet:" + charDet);
        boolean result = false;
        if(charDet!=null && charDet.trim().length()!=0 && charDet!=null && charDet.trim().length()!=0 ){
         if(breedDAO.isBreedCharDetEditExist(charDet,characterdetid,charId)){
                result = breedDAO.updateBreedCharDet(characterdetid,charDet,status);
                Debug.print("KaverySessionBean editHorseBreed Result:" +  result);
           }
        }
        return result;
    }
       public boolean deleteBrdCharDet(String[] chkCharIdArr)throws RemoteException{

        boolean result = false;
        if(chkCharIdArr!=null   ){

                result = breedDAO.deleteBrdChar(chkCharIdArr);
                Debug.print("KaverySessionBean deleteBrdCharDet Result:" +  result);
        }
        return result;
       }
        // Ends:[MemMgnt] For Breed Characteristic Detail Management Dated:21-4-2011






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
  * Description  :This method will Create a database connection
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
	//Satheesh-Start- MMMA_0008:For each species, allow for customer definition of breed characteristic categories
 public Vector displayUserTypeDetail() throws RemoteException, FinderException{
        Debug.print("KaverySessionBean getAllHorseBreedDetails**********************");

        Vector results = breedDAO.displayUserTypeDetails();
        Debug.print("After calling KaverySessionBean getAllHorseBreedDetails **********************");
        return results;
    }
  public ArrayList  getAllSpeciesCharDetailsByspecieId(String uTypeId) throws RemoteException{
        Debug.print("KaverySessionBean getAllHorseBreedDetails");
           System.out.print("cal the remote class ");
         ArrayList results = breedDAO. selectAllSpeciesCharDetailsByspecies(uTypeId);
        return results;
    }
  public boolean createSpeciesChar(String charDesc,String charStatus,String charSpecieId) throws RemoteException {
        Debug.print("KaverySessionBean createHorseBreed() charDesc:" + charDesc);
        boolean result = false;
        if(charDesc!=null && charDesc.trim().length()!=0 ){
            if(breedDAO. isSpeciesCharExist(charDesc,charSpecieId)){
              
                result = breedDAO.insertSpeciesChar(charDesc,charStatus,charSpecieId);
                Debug.print("KaverySessionBean createHorseBreed Result:" +  result);
            }

        }
        return result;
    }

  public HLCBreedVO getSpeciesCharDetailsById(String charId) throws RemoteException{
        Debug.print("KaverySessionBean getHorseColorDetailsById breedId : " + charId);
        HLCBreedVO results = null;
        if(charId!=null && charId.trim().length()!=0){
            results = breedDAO.selectSpeciesCharById(charId);
        }
        return results;
    }

   public boolean editSpeciesChar(String charDesc,String charStatus, String charId,String uTypeId) throws RemoteException {
        Debug.print("KaverySessionBean editHorseBreed() breedDesc:" + charDesc);
        boolean result = false;
        if(charId!=null && charId.trim().length()!=0 && charDesc!=null && charDesc.trim().length()!=0 ){
            System.out.println("sss");
            if(breedDAO.isSpeciesCharEditExist(charDesc,charId,uTypeId)){
              System.out.println("sss");
                 result = breedDAO.updateSpeciesChar(charDesc,charStatus,charId);
                Debug.print("KaverySessionBean editHorseBreed Result:" +  result);
            }
        }
        return result;
    }

public boolean deleteChar(String  chkRoleIdArr[]) throws RemoteException {

        boolean result = false;
        if(chkRoleIdArr!=null   ){

                result = breedDAO.deleteChar(chkRoleIdArr);
                Debug.print("KaverySessionBean editHorseBreed Result:" +  result);

        }
        return result;
    }


//Satheesh-End- MMMA_0008:For each species, allow for customer definition of breed characteristic categories


//==========Dhivya Here: Breed Characteristics [Category and Details Management])===============

public String getCharacteristicName(String charId) throws RemoteException{
        Debug.print("KaverySessionBean getCharacteristicName");

         String characteristicName = breedDAO. selectCharacteristicName(charId);
        return characteristicName;
    }


  //================End Here==================================



}
