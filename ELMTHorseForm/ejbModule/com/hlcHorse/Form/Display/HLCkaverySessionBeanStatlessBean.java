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
package com.hlcHorse.Form.Display;

import com.hlccommon.util.Debug;
import com.hlccommon.util.HLCDonationDetailVO;
import com.hlccommon.util.HLCHorseSearch2VO;
import com.hlccommon.util.HLCHorseSearchVO;
import com.hlccommon.util.HLCNonUseaOrgVO;
import javax.ejb.*;
import javax.sql.*;
import javax.naming.*;
import java.rmi.RemoteException;
import java.sql.*;
import java.util.*;


/**
 * This is the bean class for the kaverySessionBeanStatlessBean enterprise bean.
 * Created Aug 14, 2006 6:04:51 PM
 * @author harmohan
 */
public class HLCkaverySessionBeanStatlessBean implements SessionBean, HLCkaverySessionBeanStatlessRemoteBusiness {
    private SessionContext context;
    private Connection con = null;
    private PreparedStatement prepStmt=null;
    private ResultSet rs= null;
    Vector vObj;
    
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
    
    
/**    Display the dynamic content from the table for the Horse and Human Member Registration Form  */
    
    
    public Vector areUAMember() throws RemoteException {
       
        try {
              vObj = getUSEA();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vObj;
    }
    
    public Vector membershipTo() throws RemoteException {
        try {
              vObj = getMailing();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vObj;
    }
    
    public Vector displayMembershipType(String h_name) throws RemoteException {
        try {
              String memberName = getMemberName(h_name);
              vObj = loadMT(memberName, h_name);
        } catch (Exception ex) {
              ex.printStackTrace();
        }
        return vObj;
    }
    /*
     * getting all members(Human & Horse) types from DB 
     *
     */
    public ArrayList displayAllMembershipType() throws RemoteException{
       ArrayList dispMembers = null; 
        try{
            dispMembers = loadMembers();
        }
        catch(Exception allMemType){
            System.out.print("Exception In displayAllMembershipType():" + allMemType.getMessage());
            
        }
        return dispMembers;
    }
    /*
     * getting all CompettionResult types from tblMeeCompResultFieldMaster table
     *
     */
    public ArrayList displayAllCompResultDetails() throws RemoteException{
       ArrayList dispCompResults = null; 
        try{
            dispCompResults = loadCompResults();
        }
        catch(Exception allMemType){
            System.out.print("Exception In displayAllCompResultDetails():" + allMemType.getMessage());
            
        }
        return dispCompResults;
    }
    /*
     *
     *
     */
    
     public ArrayList getMapDetForMemberAndEventLevel(String membershipTypeId)  throws RemoteException{
        Debug.print("kaverySessionBeanStatelessBean getMapDetForMemberAndEventLevel:" + membershipTypeId);
        ArrayList results = null;
        try{
            if(membershipTypeId!=null && membershipTypeId.trim().length()!=0){
                results = selectAllMappingDetailsForMembership(membershipTypeId);
            }
              
        }
        catch(Exception ex){
             System.out.print("Exception In getMapDetForMemberAndEventLevel():" + ex.getMessage());
        }
        
        return results;
    }
     
/**-----------------------DataBase Connection---------------------------------------*/   
/**
  * Name         : selectAllMappingDetailsForMembership
  * Author       : punitha.r
  * Description  : This method will return mapping entity level and membership type name based on membership type id
  * @ param      : membership type id
  * @return      : ArrayList
  * @throws      : SQLException
  */  
     public ArrayList selectAllMappingDetailsForMembership(String membershipTypeId) throws SQLException{
        ArrayList objMemLevelDetails = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
         try{
            String selectStatement=" SELECT a.map_level_id, a.membership_type_id, a.event_type_id, a.event_level_id ,b.membership_type_name,c.event_level_name from " +
                    " tblMeeMapEventLevelMembership  a ,  tblMembershipTypeMaster  b , tblMeeEventLevelMaster c , tblMeeEventTypeMaster d" +
                    " where a.event_level_id = c.event_level_id and a.event_type_id = d.event_type_id and a.membership_type_id = b.membership_type_id and a.membership_type_id = ? order by c.event_level_name";
         
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,membershipTypeId);
            Debug.print(selectStatement);
            rs = prepStmt.executeQuery();
            objMemLevelDetails = new ArrayList();
            while(rs.next()){
                String mapLevelId = rs.getString(1);
                String memTypeId = rs.getString(2);
                String eventTypeId = rs.getString(3);
                String eventLevelId = rs.getString(4);
                String memTypeName = rs.getString(5);
                String eventLevelName = rs.getString(6);
                String[] evntMapMemList = {mapLevelId, memTypeId, eventTypeId, eventLevelId, memTypeName, eventLevelName};
                objMemLevelDetails.add(evntMapMemList);
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
            Debug.print("kaverySessionBeanStatelessBean.selectAllMappingDetailsForMembership():" + objMemLevelDetails); 
         }
         
         catch(Exception gen){
            releaseConnection();
            Debug.print("General Exception of selectAllMappingDetailsForMembership in kaverySessionBeanStatelessBean:" + gen);
         }
        
         return objMemLevelDetails;
     }
  /**
  * Name         : generateMappingMemWithEventLevel
  * Author       : punitha.r
  * Description  : This method will return boolean value once generates the membershipTypeId and Event Level
  * @ param      : membershipTypeId , EventLevelList
  * @return      : boolean
  * @throws      : SQLException
  */  
      public boolean generateMappingMemWithEventLevel(String membershipTypeId, String eventTypeId, ArrayList eventLevelList) throws RemoteException {
        Debug.print("kaverySessionBeanStatelessBean generateMappingMemWithEventLevel()" + membershipTypeId);
        boolean result =false;
        Iterator itEventLevelList = eventLevelList.iterator();
        boolean flag = deleteRow("membership_type_id", membershipTypeId, "tblMeeMapEventLevelMembership");
       
        if(flag){
             Debug.print("inside the while loop of generateMappingMemWithEventLevel in eventLevelId 1:");
            if(eventLevelList!=null && eventLevelList.size()!=0){
                   Debug.print("inside the while loop of generateMappingMemWithEventLevel in eventLevelId size: " + eventLevelList.size());
                while(itEventLevelList.hasNext()){
                    String eventLevelId = (String)itEventLevelList.next();
                    
                    if(eventLevelId!=null && eventLevelId.trim().length()!=0){
                       insertMemEventLevelMapping(membershipTypeId, eventTypeId, eventLevelId);
                        Debug.print("successfully inserted");
                    }
                }
            }
            result = true;
        }
         return result;
     }
 /**
  * Name         : insertMemEventLevelMapping
  * Author       : punitha.r
  * Description  : This method will return boolean value once inserts the membershipTypeId and Event Level id into
  *                tblMeeMapEventLevelMembership
  * @param       : membershipTypeId , eventLevelId
  * @return      : boolean
  * @throws      : SQLException
  */  
   //=============================================  Mapping Entites with privilege details=========================================      
    public boolean insertMemEventLevelMapping(String membershipTypeId,String eventTypeId, String eventLevelId) {
        Debug.print("kaverySessionBeanStatelessBean.insertRoleEntityMapping():");
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String insertStatement = "insert into tblMeeMapEventLevelMembership(membership_type_id, event_type_id,event_level_id) " +
                    " values( ? , ? , ?)";
            prepStmt = con.prepareStatement(insertStatement);
            prepStmt.setString(1, membershipTypeId);
            prepStmt.setString(2, eventTypeId);
            prepStmt.setString(3, eventLevelId);
            prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection();
        } 
        catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in kaverySessionBeanStatelessBean.insertRoleEntityMapping():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in kaverySessionBeanStatelessBean.insertRoleEntityMapping():" + e.getMessage());
        }
        return true;
    } 
  //=============================================Delete Mapping Records with fieldValue details=========================================     
  /**
  * Name         : deleteRow
  * Author       : punitha.r
  * Description  : This method will return boolean value once it deletes the record from tblMeeMapEventLevelMembership
  * @ param      : fieldName,fieldValue,tableName
  * @return      : boolean
  * @throws      : SQLException
  */     
    //=============================================Delete Mapping Records with fieldValue details========================================= 
     public boolean deleteRow(String fieldName, String fieldValue, String tableName) {
        Debug.print("kaverySessionBeanStatelessBean.deleteRow() \n:" +"fieldName: \n" + fieldName + "fieldValue: \n " + fieldValue +"tableName:<br/> " + tableName);
        boolean result = false;
        makeConnection();
        try{
            String deleteStatement = "delete from " + tableName + "  where " + fieldName  + " = ? ";
            Debug.print("kaverySessionBeanStatelessBean.deleteRow():" + "\n" + deleteStatement + ":" +  fieldValue);
            PreparedStatement prepStmt = con.prepareStatement(deleteStatement);
            prepStmt.setString(1, fieldValue);
            prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection();
            result = true;
        }
        catch(SQLException sql){
           releaseConnection();
           Debug.print("SQL Exception in deleteRow:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in deleteRow:" + e.getMessage());
        }        
        return result;
    } 
   //=============================================-====================================================================================================     
     
     public Vector displayHorseServiceType(String h_name) throws RemoteException {
        try {
              String memberName = getMemberName(h_name);
              vObj = loadHT();
        } catch (Exception ex) {
              ex.printStackTrace();
        }
        return vObj;
    }
     
     public Vector displayHorsePhoneServiceType(String h_name) throws RemoteException {
        try {
              String memberName = getMemberName(h_name);
              vObj = loadHTHorse();
        } catch (Exception ex) {
              ex.printStackTrace();
        }
        return vObj;
    }
    
     public String[] getFamilyAddOnPrice(String memberTypeName, int currentYear)throws RemoteException {
         String [] familyMem = new String[3];
         try {
             familyMem = listFamiltAddOnPrice(memberTypeName, currentYear);
         }catch(Exception e){
             System.out.println(" Error While getting family add on price : "+e.getMessage());
         }
         return familyMem;
     }
     
      public String[] getFamilyAddOn(String memberTypeName)throws RemoteException {
         String [] familyMem = new String[3];
         try {
             familyMem = listFamiltAddOn(memberTypeName);
         }catch(Exception e){
             System.out.println(" Error While getting family add on price : "+e.getMessage());
         }
         return familyMem;
     }
     
       public ArrayList getMemberDonationDetails(String userId)throws RemoteException {
            System.out.println(" KaverySessionBean getMemberDonationDetails userId: "+ userId);
         ArrayList donationList = new ArrayList();
         try {
             if(userId!=null && userId.trim().length()!=0){
                donationList = selectDonationDisplayDetails(userId);
             }
         }catch(Exception e){
             System.out.println(" Error While getting KaverySessionBean getMemberDonationDetails : "+e.getMessage());
         }
         return donationList;
     }
       
         public ArrayList getMemberNonUseaDetails(String memberId)throws RemoteException {
             System.out.println(" KaverySessionBean getMemberNonUseaDetails memberId: "+ memberId);
             ArrayList nonUseaList = new ArrayList();
             try {
                 if(memberId!=null && memberId.trim().length()!=0){
                    nonUseaList = selectNonUseaDisplayDetails(memberId);
                 }
             }catch(Exception e){
                 System.out.println("  Error While getting  KaverySessionBean getMemberNonUseaDetails : "+e.getMessage());
             }
             return nonUseaList;
        }
         
          public Hashtable getSearchHorseDetails(String horseName, String regName, String baRegName, String baPastName , 
                                String prevRiderMemberId, String paramYearFoaled, String paramBreed, String paramSire, String paramDam ) throws RemoteException {
            System.out.println(" KaverySessionBean getSearchHorseDetails horseName: "+ horseName);
             Hashtable searchList = new Hashtable();
             try {
                 if(horseName!=null && horseName.trim().length()!=0){
                    searchList = searchHorseDetails(horseName, regName, baRegName, baPastName, prevRiderMemberId, paramYearFoaled, paramBreed, paramSire, paramDam);
                 }
             }catch(Exception e){
                 System.out.println("  Error While getting  KaverySessionBean getSearchHorseDetails : "+e.getMessage());
             }
             return searchList;
          }
   
   
    
/**-----------------------DataBase Connection---------------------------------------*/
    
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
    
/**-----------------------DataBase Connection---------------------------------------*/   
/**
  * Name         :listFamiltAddOnPrice
  * Description  :This method will return membership price based on member ship name
  * @ param      :member ship type name
  * @return      :String
  * @throws      :SQLException
  */      
    public String[] listFamiltAddOnPrice(String typeName, int year) throws SQLException {
        String memPrice = null;
        String memTypId = null;
        try {
            makeConnection();
            String selectStatement = "SELECT membership_type_id,membership_amount FROM  tblMembershipTypeMaster WHERE membership_type_name = ? and membership_year = ?";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, typeName);
            prepStmt.setInt(2, year);
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                memTypId = rs.getString(1);
                memPrice = rs.getString(2);
            } 
            System.out.println("   "+typeName+"   Member ship Price:  "+memPrice);
            prepStmt.close();
        } catch (SQLException sqe) {
             releaseConnection();
            System.out.println("Error while getting Family ad on details : "+sqe.getMessage());
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        String [] memDet = {memTypId,memPrice};
        return memDet;
    } 
    
   public String[] listFamiltAddOn(String typeName) throws SQLException {
        String memPrice = null;
        String memTypId = null;
        try {
            makeConnection();
            String selectStatement = "SELECT membership_type_id,membership_amount FROM  tblMembershipTypeMaster WHERE membership_type_name = ?";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, typeName);
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                memTypId = rs.getString(1);
                memPrice = rs.getString(2);
            } 
            System.out.println("   "+typeName+"   Member ship Price:  "+memPrice);
            prepStmt.close();
        } catch (SQLException sqe) {
             releaseConnection();
            System.out.println("Error while getting Family ad on details : "+sqe.getMessage());
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        String [] memDet = {memTypId,memPrice};
        return memDet;
    } 
   
    public String getMemberName(String h_name) throws SQLException {
        PreparedStatement prepStmt1=null;
        ResultSet rs= null;
        String name_id = "";
        try {
            makeConnection();
            String str = "select user_type_id from tblUserTypeMaster where user_type_name = ?";
             prepStmt1 = con.prepareStatement(str);
             prepStmt1.setString(1, h_name.trim());
             rs = prepStmt1.executeQuery();
             if (rs.next()) {
                 name_id = rs.getString(1);
             } 
             releaseConnection();
        }catch (Exception e) {
                 e.printStackTrace();
        }
        
        return name_id;
    }
    
   
      
    public Vector loadMT(String name_id, String h_name) throws SQLException {
        Vector V = new Vector();
        PreparedStatement prepStmt1=null;
        ResultSet rs= null;
        
        makeConnection();
        String selectStatement = "select membership_type_id, membership_type_name, membership_amount, priority_value  " +
                " from tblMembershipTypeMaster where user_type_id = ? and active_status = ? ";
        prepStmt1 = con.prepareStatement(selectStatement);
        prepStmt1.setString(1, name_id);
        prepStmt1.setBoolean(2, true);
        rs = prepStmt1.executeQuery();

        while (rs.next()){
            String memberTypeId = rs.getString(1);
            String memberType = rs.getString(2);
            String memberAmount = rs.getString(3);
            String priorityValue = rs.getString(4);
            String[] member = {memberTypeId, memberType, memberAmount, priorityValue};
            V.add(member);
         }

        rs.close();
        prepStmt1.close();
        releaseConnection();

        return V;
    }
    
   /*
     *  getting membership type name,id etc.. from tblUserTypeMaster(Human & Horse)
     *
     */ 
    public ArrayList loadMembers() throws SQLException {
        ArrayList objloadMembers = new ArrayList();
        PreparedStatement prepStmt1=null;
        ResultSet rs= null;
        
        makeConnection();
        try{
        String selectStatement = "select membership_type_id, membership_type_name, membership_amount, priority_value  " +
                " from tblMembershipTypeMaster where active_status = ? ";
        prepStmt1 = con.prepareStatement(selectStatement);
        
        prepStmt1.setBoolean(1, true);
        
        rs = prepStmt1.executeQuery();

        while(rs.next()){
            String memberTypeId = rs.getString(1);
            String memberType = rs.getString(2);
            String memberAmount = rs.getString(3);
            String priorityValue = rs.getString(4);
            String[] member = {memberTypeId, memberType, memberAmount, priorityValue};
            objloadMembers.add(member);
         }

        rs.close();
        prepStmt1.close();
        releaseConnection();
        
        } catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in kaverySessionBeanStatelessBean :" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in kaverySessionBeanStatelessBean :" + e.getMessage());
        }
        return objloadMembers;
    }
    
   /*
     *  getting membership type name,id etc.. from tblUserTypeMaster(Human & Horse)
     *
     */ 
    public ArrayList loadCompResults() throws SQLException {
        ArrayList objloadCompResults = new ArrayList();
        PreparedStatement prepStmt1=null;
        ResultSet rs= null;
        
        makeConnection();
        try{
        String selectStatement = "select comp_result_field_id, comp_result_field_name, comp_result_field_desc, add_date  " +
                " from tblMeeCompResultFieldMaster  where active_status = ? ";
        prepStmt1 = con.prepareStatement(selectStatement);
        prepStmt1.setBoolean(1, true);
        rs = prepStmt1.executeQuery();
        Debug.print("selectStatement:" + selectStatement);
        while(rs.next()){
            String compResultTypeId = rs.getString(1);
            String compResultName = rs.getString(2);
            String compResultDesc = rs.getString(3);
            String addDate = rs.getString(4);
            String[] member = {compResultTypeId, compResultName, compResultDesc, addDate};
            objloadCompResults.add(member);
         }

        rs.close();
        prepStmt1.close();
        releaseConnection();
        
        } catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in loadCompResults kaverySessionBeanStatelessBean :" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in loadCompResults kaverySessionBeanStatelessBean :" + e.getMessage());
        }
        return objloadCompResults;
    } 
    
    public Vector loadHT() throws SQLException {
        Vector V = new Vector();
        PreparedStatement prepStmt1=null;
        ResultSet rs= null;
        makeConnection();

        String selectStatement1 = "select horse_service_type_id, horse_service_type_name, horse_service_type_amount from tblHorseServiceTypeMaster";
        prepStmt1 = con.prepareStatement(selectStatement1);
        rs = prepStmt1.executeQuery();

        while (rs.next()){
            String serviceTypeId = rs.getString(1);
            String memberType = rs.getString(2);
            String memberAmount = rs.getString(3);
            String[] member = {serviceTypeId, memberType, memberAmount};
            V.add(member);
        }
        rs.close();
        prepStmt1.close();
        releaseConnection();

        return V;
    }
    
    public Vector loadHTHorse() throws SQLException {
        Vector V = new Vector();
        PreparedStatement prepStmt1=null;
        ResultSet rs= null;
        makeConnection();

        String selectStatement1 = "select horse_service_type_id, horse_service_type_name, horse_service_type_amount " +
                " from tblHorseServiceTypeMaster where horse_service_type_name='Phone Service Charge'";
        prepStmt1 = con.prepareStatement(selectStatement1);
        rs = prepStmt1.executeQuery();

        while (rs.next()){
            String serviceTypeId = rs.getString(1);
            String memberType = rs.getString(2);
            String memberAmount = rs.getString(3);
            String[] member = {serviceTypeId, memberType, memberAmount};
            V.add(member);
        }
        rs.close();
        prepStmt1.close();
        releaseConnection();

        return V;
    }
    
    public Vector getUSEA() throws SQLException {
        
        Vector V = new Vector();
        PreparedStatement prepStmt1=null;
        ResultSet rs= null;
         //try {
                makeConnection();
                String selectStatement = "select other_org_id, other_org_name from tblOtherOrgMaster";
                prepStmt1 = con.prepareStatement(selectStatement);
            
                rs = prepStmt1.executeQuery();
               
                while (rs.next()){
                    String nonuseaId = rs.getString(1);
                    String nonuseaName = rs.getString(2);
                   
                    String[] member = {nonuseaId, nonuseaName};
                    V.add(member);
                 }
        
        //}catch (Exception e) {
         ///   e.printStackTrace();
        //} finally {
            rs.close();
            prepStmt1.close();
            releaseConnection();
       // }
        return V;
    }
    
    public Vector getMailing() throws SQLException {
        
        Vector V = new Vector();
        PreparedStatement prepStmt1=null;
        ResultSet rs= null;
        // try {
                makeConnection();
                String selectStatement = "select country_mail_type_id, country_mail_type_name, country_mail_price from tblCountryMailPriceMaster";
                prepStmt1 = con.prepareStatement(selectStatement);
            
                rs = prepStmt1.executeQuery();
               
                while (rs.next()){
                    String mailId = rs.getString(1);
                    String mailName = rs.getString(2);
                    String mailPrice = rs.getString(3);
                    String[] member = {mailId, mailName, mailPrice};
                    V.add(member);
                 }
        
       // }catch (Exception e) {
       //     e.printStackTrace();
       // } finally {
            rs.close();
            prepStmt1.close();
            releaseConnection();
       // }
        return V;
    }
    
      //=============================================Select All Annual Register USer=========================================      
    public ArrayList selectDonationDisplayDetails(String userId) throws SQLException{
        System.out.print("kaverySessionBeanStatlessBean selectDonationDisplayDetails");
        ArrayList donationList = new ArrayList();
        makeConnection();
        try{
            String selectStatement = "select A.donation_map_id, A.user_id, A.donation_id, A.donation_price, A.donated_by, B.donation_name,  " +
                    " B.precheck_status, A.add_date from tblMemberDonationDetails A, tblDonationDetails B " + 
                    "  where A.donation_id = B.donation_id and A.user_id = ? order by A.add_date desc";
            PreparedStatement prepSelect = con.prepareStatement(selectStatement);
            prepSelect.setString(1,userId);
            ResultSet rs = prepSelect.executeQuery();
            while(rs.next()){
                HLCDonationDetailVO onjDon = new HLCDonationDetailVO();
                String donation_map_id = rs.getString(1);
                String user_id = rs.getString(2);
                String donation_id = rs.getString(3);
                String donation_price = rs.getString(4);
                String donated_by = rs.getString(5);
                String donation_name = rs.getString(6);
                String donationDate = rs.getString(8);
                boolean precheckStatus = rs.getBoolean(7);
                

                onjDon.setDonationMapId(donation_map_id);
                onjDon.setUserId(user_id);
                onjDon.setDonationId(donation_id);
                onjDon.setDonationPrice(donation_price);
                onjDon.setDonationName(donation_name);
                onjDon.setDonatedBy(donated_by);
                onjDon.setMemberDonationDate(donationDate);
                onjDon.setPrecheckStatus(precheckStatus);
                donationList.add(onjDon);
            }
            rs.close();
            prepSelect.close();
            releaseConnection();
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in selectDonationDisplayDetails:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in selectDonationDisplayDetails:" + e.getMessage());
        }
        return donationList;
    }
    
  //=============================================Select All Annual Register USer=========================================      
    public ArrayList selectNonUseaDisplayDetails(String memberId) throws SQLException{
        //tblMemberNonUseaOrgDetails, tblNonUSEAOrgMaster
        System.out.print("kaverySessionBeanStatlessBean selectNonUseaDisplayDetails");
        ArrayList nonUseaList = new ArrayList();
        makeConnection();
        try{
           /* String selectStatement = "select A.nonusea_org_map_id, A.member_id, A.nonusea_org_id, A.nonusea_price, B.nonusea_org_name  " +
                    " from tblMemberNonUseaOrgDetails A, tblNonUSEAOrgMaster B " + 
                    " where A.nonusea_org_id = B.nonusea_org_id and A.member_id = ?";*/
            
            String selectStatement = "select A.nonusea_org_map_id, A.member_id, A.other_org_id, A.nonusea_member_id, B.other_org_name  " +
                    " from tblMemberNonUseaOrgDetails A, tblOtherOrgMaster B " + 
                    " where A.other_org_id = B.other_org_id and A.member_id = ?";
            
            PreparedStatement prepSelect = con.prepareStatement(selectStatement);
            prepSelect.setString(1,memberId);
            ResultSet rs = prepSelect.executeQuery();
            while(rs.next()){
                HLCNonUseaOrgVO onjNonUsea = new HLCNonUseaOrgVO();
                String nonusea_org_map_id = rs.getString(1);
                String member_id = rs.getString(2);
                String nonusea_org_id = rs.getString(3);
                //String nonusea_price = rs.getString(4);
                String nonusea_MemberId = rs.getString(4);
                String nonusea_org_name = rs.getString(5);

                onjNonUsea.setNonuseaOrgMapId(nonusea_org_map_id);
                onjNonUsea.setMemberId(member_id);
                onjNonUsea.setNonuseaOrgId(nonusea_org_id);
                //onjNonUsea.setNonuseaPrice(nonusea_price);
                onjNonUsea.setNonuseaMemberId(nonusea_MemberId);
                onjNonUsea.setNonuseaOrgName(nonusea_org_name);
                nonUseaList.add(onjNonUsea);
            }
            rs.close();
            prepSelect.close();
            releaseConnection();
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in selectNonUseaDisplayDetails:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in selectNonUseaDisplayDetails:" + e.getMessage());
        }
        return nonUseaList;
    }

    //=============================================Insert  details=========================================      
    public Hashtable searchHorseDetails(String horseName, String regName, String baRegName, String baPastName, 
                            String prevRiderMemberId, String paramYearFoaled, String paramBreed, String paramSire, String paramDam) {
            System.out.print("kaverySessionBeanStatlessBean.searchHorseDetails():");
            PreparedStatement prepStmt = null;
            Hashtable searchList = new Hashtable();
            makeConnection();
            
            horseName = horseName.replaceAll("'","''");
            if(regName!=null)  regName = regName.replaceAll("'","''");
            if(baRegName!=null)  baRegName = baRegName.replaceAll("'","''");
            if(baPastName!=null)  baPastName = baPastName.replaceAll("'","''");
            
            try {
                String selectStatement = "select A.competition_name , A.registered_name, A.rider_member_id, " +
                        " D.first_name , D.last_name, B.gender, B.year_foaled, B.sire, " +
                        " B.dam, A.horse_member_id from tblHorseMemberDetails A, tblHorseDescription B , " +
                        " tblMemberDetails C , tblUserMaster D, tblUserPaymentDetails E " +
                        " where A.horse_member_id = B.horse_member_id and " +
                        " A.rider_member_id = C.member_id and C.user_id = D.user_id and " +
                        " A.competition_name like '%" + horseName + "%'";
                
                System.out.println("Search Query: " + selectStatement);

                prepStmt = con.prepareStatement(selectStatement);

                rs = prepStmt.executeQuery();
                while (rs.next()) {
                    HLCHorseSearchVO objSearchHorse = new HLCHorseSearchVO();
                    String competitionName = rs.getString(1); 
                    String registeredName = rs.getString(2); 
                    String riderMemberId = rs.getString(3);
                    String firstName = rs.getString(4);
                    String lastName = rs.getString(5); 
                    String gender = rs.getString(6); 
                   // String breed = rs.getString(7);
                    String yearFoaled = rs.getString(7); 
                    String sire = rs.getString(8); 
                    String dam =rs.getString(9);
                    String horseMemberId = rs.getString(10);
                    
                    objSearchHorse.setCompetitionName(competitionName);
                    objSearchHorse.setRegisteredName(registeredName);
                   // objSearchHorse.setRiderMemberId(registeredName);
                    objSearchHorse.setRiderMemberId(riderMemberId);
                    objSearchHorse.setFirstName(firstName);
                    objSearchHorse.setLastName(lastName);
                    objSearchHorse.setGender(gender);
                    //objSearchHorse.setBreed(breed);
                    objSearchHorse.setYearFoaled(yearFoaled);
                    objSearchHorse.setSire(sire);
                    objSearchHorse.setDam(dam);
                    objSearchHorse.setHorseMemberId(horseMemberId);
                    if(!searchList.containsKey(horseMemberId)){
                       searchList.put(horseMemberId , objSearchHorse);
                    }
                }
                rs.close();
                prepStmt.close();
                releaseConnection();
            } 
            catch(SQLException sql){
                releaseConnection();
                System.out.print("SQL Exception in kaverySessionBeanStatlessBean.searchHorseDetails():" + sql.getMessage());
            }
            catch(Exception e){
                releaseConnection();
                System.out.print("General Exception  in kaverySessionBeanStatlessBean.searchHorseDetails():" + e.getMessage());
            }
   
            makeConnection();
            try {
                String selectStatement = "select A.competition_name , A.registered_name, A.rider_member_id, " +
                        " D.first_name , D.last_name, B.gender, B.year_foaled, B.sire, " +
                        " B.dam , A.horse_member_id from tblHorseMemberDetails A, tblHorseDescription B , " +
                        " tblMemberDetails C , tblUserMaster D, tblUserPaymentDetails E " +
                        " where A.horse_member_id = B.horse_member_id and " +
                        " A.rider_member_id = C.member_id and C.user_id = D.user_id and " + 
                        " A.registered_name like '%" + horseName + "%'";

                
                System.out.println("Search Query: " + selectStatement);

                prepStmt = con.prepareStatement(selectStatement);

                rs = prepStmt.executeQuery();
                while (rs.next()) {
                    HLCHorseSearchVO objSearchHorse = new HLCHorseSearchVO();
                    String competitionName = rs.getString(1); 
                    String registeredName = rs.getString(2); 
                    String riderMemberId = rs.getString(3);
                    String firstName = rs.getString(4);
                    String lastName = rs.getString(5); 
                    String gender = rs.getString(6); 
                    //String breed = rs.getString(7);
                    String yearFoaled = rs.getString(7); 
                    String sire = rs.getString(8); 
                    String dam =rs.getString(9);
                    String horseMemberId = rs.getString(10);
                    
                    objSearchHorse.setCompetitionName(competitionName);
                    objSearchHorse.setRegisteredName(registeredName);
                   // objSearchHorse.setRiderMemberId(registeredName);
                    objSearchHorse.setRiderMemberId(riderMemberId);
                    objSearchHorse.setFirstName(firstName);
                    objSearchHorse.setLastName(lastName);
                    objSearchHorse.setGender(gender);
                    //objSearchHorse.setBreed(breed);
                    objSearchHorse.setYearFoaled(yearFoaled);
                    objSearchHorse.setSire(sire);
                    objSearchHorse.setDam(dam);
                    objSearchHorse.setHorseMemberId(horseMemberId);
                    
                     if(!searchList.containsKey(horseMemberId)){
                       searchList.put(horseMemberId ,objSearchHorse);
                    }
                }
                rs.close();
                prepStmt.close();
                releaseConnection();
            } 
            catch(SQLException sql){
                releaseConnection();
                System.out.print("SQL Exception in kaverySessionBeanStatlessBean.searchHorseDetails():" + sql.getMessage());
            }
            catch(Exception e){
                releaseConnection();
                System.out.print("General Exception  in kaverySessionBeanStatlessBean.searchHorseDetails():" + e.getMessage());
            }
 
             makeConnection();
             try {
                String selectStatement = "select A.competition_name , A.registered_name, A.rider_member_id, " +
                        " D.first_name , D.last_name, B.gender, B.year_foaled, B.sire, " +
                        " B.dam, A.horse_member_id from tblHorseMemberDetails A, tblHorseDescription B , " +
                        " tblMemberDetails C , tblUserMaster D , tblUserPaymentDetails E " +
                        " where A.horse_member_id = B.horse_member_id and " +
                        " A.rider_member_id = C.member_id and C.user_id = D.user_id and " + 
                        " A.ba_registered_name like '%" + horseName + "%'";

                System.out.println("Search Query: " + selectStatement);
                prepStmt = con.prepareStatement(selectStatement);

                rs = prepStmt.executeQuery();
                while (rs.next()) {
                    HLCHorseSearchVO objSearchHorse = new HLCHorseSearchVO();
                    String competitionName = rs.getString(1); 
                    String registeredName = rs.getString(2); 
                    String riderMemberId = rs.getString(3);
                    String firstName = rs.getString(4);
                    String lastName = rs.getString(5); 
                    String gender = rs.getString(6); 
                    //String breed = rs.getString(7);
                    String yearFoaled = rs.getString(7); 
                    String sire = rs.getString(8); 
                    String dam =rs.getString(9);
                    String horseMemberId = rs.getString(10);
                    
                    objSearchHorse.setCompetitionName(competitionName);
                    objSearchHorse.setRegisteredName(registeredName);
                   // objSearchHorse.setRiderMemberId(registeredName);
                    objSearchHorse.setRiderMemberId(riderMemberId);
                    objSearchHorse.setFirstName(firstName);
                    objSearchHorse.setLastName(lastName);
                    objSearchHorse.setGender(gender);
                    //objSearchHorse.setBreed(breed);
                    objSearchHorse.setYearFoaled(yearFoaled);
                    objSearchHorse.setSire(sire);
                    objSearchHorse.setDam(dam);
                    objSearchHorse.setHorseMemberId(horseMemberId);
                    
                     if(!searchList.containsKey(horseMemberId)){
                       searchList.put(horseMemberId , objSearchHorse);
                    }
                }
                rs.close();
                prepStmt.close();
                releaseConnection();
            } 
            catch(SQLException sql){
                releaseConnection();
                System.out.print("SQL Exception in kaverySessionBeanStatlessBean.searchHorseDetails():" + sql.getMessage());
            }
            catch(Exception e){
                releaseConnection();
                System.out.print("General Exception  in kaverySessionBeanStatlessBean.searchHorseDetails():" + e.getMessage());
            }
  
            makeConnection();
            try {
                String selectStatement = " select A.competition_name , A.registered_name, A.rider_member_id, " +
                        " D.first_name , D.last_name, B.gender, B.year_foaled, B.sire, " +
                        " B.dam, A.horse_member_id from tblHorseMemberDetails A, tblHorseDescription B , " +
                        " tblMemberDetails C , tblUserMaster D , tblUserPaymentDetails E " +
                        " where A.horse_member_id = B.horse_member_id and " +
                        " A.rider_member_id = C.member_id and C.user_id = D.user_id and " + 
                        " A.ba_past_name like '%" + horseName + "%'";
                
                System.out.println("Search Query: " + selectStatement);

                prepStmt = con.prepareStatement(selectStatement);

                rs = prepStmt.executeQuery();
                while (rs.next()) {
                    HLCHorseSearchVO objSearchHorse = new HLCHorseSearchVO();
                    String competitionName = rs.getString(1); 
                    String registeredName = rs.getString(2); 
                    String riderMemberId = rs.getString(3);
                    String firstName = rs.getString(4);
                    String lastName = rs.getString(5); 
                    String gender = rs.getString(6); 
                    //String breed = rs.getString(7);
                    String yearFoaled = rs.getString(7); 
                    String sire = rs.getString(8); 
                    String dam =rs.getString(9);
                    String horseMemberId = rs.getString(10);                    
                    
                    objSearchHorse.setCompetitionName(competitionName);
                    objSearchHorse.setRegisteredName(registeredName);
                   // objSearchHorse.setRiderMemberId(registeredName);
                    objSearchHorse.setRiderMemberId(riderMemberId);
                    objSearchHorse.setFirstName(firstName);
                    objSearchHorse.setLastName(lastName);
                    objSearchHorse.setGender(gender);
                    //objSearchHorse.setBreed(breed);
                    objSearchHorse.setYearFoaled(yearFoaled);
                    objSearchHorse.setSire(sire);
                    objSearchHorse.setDam(dam);
                    objSearchHorse.setHorseMemberId(horseMemberId);
                    
                    if(!searchList.containsKey(horseMemberId)){
                       searchList.put(horseMemberId , objSearchHorse);
                    }
                }
                rs.close();
                prepStmt.close();
                releaseConnection();
            } 
            catch(SQLException sql){
                releaseConnection();
                System.out.print("SQL Exception in kaverySessionBeanStatlessBean.searchHorseDetails():" + sql.getMessage());
            }
            catch(Exception e){
                releaseConnection();
                System.out.print("General Exception  in kaverySessionBeanStatlessBean.searchHorseDetails():" + e.getMessage());
            }
             if((regName!=null && regName.trim().length()!=0) || (baRegName!=null && baRegName.trim().length()!=0) 
             || (baPastName!=null && baPastName.trim().length()!=0) || (prevRiderMemberId!=null && prevRiderMemberId.trim().length()!=0)
                        || (paramYearFoaled!=null && paramYearFoaled.trim().length()!=0) ||
                    (paramSire!=null && paramSire.trim().length()!=0) || (paramDam!=null && paramDam.trim().length()!=0)){
                        
            makeConnection();
            try {
                String selectStatement="";
                  selectStatement = "select A.competition_name , A.registered_name, A.rider_member_id, " +
                        " D.first_name , D.last_name, B.gender, B.year_foaled, B.sire, " +
                        " B.dam , A.horse_member_id from tblHorseMemberDetails A, tblHorseDescription B , " +
                        " tblMemberDetails C , tblUserMaster D , tblUserPaymentDetails E " +
                        " where A.horse_member_id = B.horse_member_id and " +
                        " A.rider_member_id = C.member_id and C.user_id = D.user_id ";
               
                if(regName!=null && regName.trim().length()!=0){
                    selectStatement = selectStatement +  " and A.registered_name like '%" + regName + "%'";
                }
                
                if(baRegName!=null && baRegName.trim().length()!=0){
                    selectStatement = selectStatement +  " and A.ba_registered_name like '%" + baRegName + "%'";
                }
                
                if(baPastName!=null && baPastName.trim().length()!=0){
                    selectStatement = selectStatement +  " and A.ba_past_name like '%" + baPastName + "%'";
                }
                
                if(prevRiderMemberId!=null && prevRiderMemberId.trim().length()!=0){
                    selectStatement = selectStatement +  " and A.prev_rider_member_id =" + prevRiderMemberId ;
                }
                
                if(paramYearFoaled!=null && paramYearFoaled.trim().length()!=0){
                    selectStatement = selectStatement +  " and B.year_foaled =" + paramYearFoaled ;
                }
                
               /* if(paramBreed!=null && paramBreed.trim().length()!=0){
                    selectStatement = selectStatement +  " and B.breed =  " + paramBreed ;
                }
                */
                
                if(paramSire!=null && paramSire.trim().length()!=0){
                    selectStatement = selectStatement +  " and B.sire =" + paramSire ;
                }
                
                if(paramDam!=null && paramDam.trim().length()!=0){
                    selectStatement = selectStatement +  " and B.dam =" + paramDam ;
                }
                 
                System.out.println("Search Query: " + selectStatement);

                prepStmt = con.prepareStatement(selectStatement);

                rs = prepStmt.executeQuery();
                while (rs.next()) {
                    HLCHorseSearchVO objSearchHorse = new HLCHorseSearchVO();
                    String competitionName = rs.getString(1); 
                    String registeredName = rs.getString(2); 
                    String riderMemberId = rs.getString(3);
                    String firstName = rs.getString(4);
                    String lastName = rs.getString(5); 
                    String gender = rs.getString(6); 
                    //String breed = rs.getString(7);
                    String yearFoaled = rs.getString(7); 
                    String sire = rs.getString(8); 
                    String dam =rs.getString(9);
                    String horseMemberId = rs.getString(10);                    
                    
                    objSearchHorse.setCompetitionName(competitionName);
                    objSearchHorse.setRegisteredName(registeredName);
                   // objSearchHorse.setRiderMemberId(registeredName);
                    objSearchHorse.setRiderMemberId(riderMemberId);
                    objSearchHorse.setFirstName(firstName);
                    objSearchHorse.setLastName(lastName);
                    objSearchHorse.setGender(gender);
                    //objSearchHorse.setBreed(breed);
                    objSearchHorse.setYearFoaled(yearFoaled);
                    objSearchHorse.setSire(sire);
                    objSearchHorse.setDam(dam);
                    objSearchHorse.setHorseMemberId(horseMemberId);
                    
                     if(!searchList.containsKey(horseMemberId)){
                       searchList.put(horseMemberId , objSearchHorse);
                    }
                }
                rs.close();
                prepStmt.close();
                releaseConnection();
           
        }
            catch(SQLException sql){
                releaseConnection();
                System.out.print("SQL Exception in kaverySessionBeanStatlessBean.searchHorseDetails():" + sql.getMessage());
            }
            catch(Exception e){
                releaseConnection();
                System.out.print("General Exception  in kaverySessionBeanStatlessBean.searchHorseDetails():" + e.getMessage());
            }
         }    
               System.out.print("searchList .Size():" + searchList.size());
            return searchList;
        }
    
    /*public ArrayList searchHorse(String hrMemberId, String hrName, String ownName, String riderName) throws RemoteException{
        Debug.print("kaverySessionBeanStatlessBean.searchHorse()");
        ArrayList searchList = new ArrayList();
        boolean resultFlagHrName = false;
        boolean resultFlagHrMemberId = false;
        boolean resultFlagOwner = false;
        makeConnection();
        try {
           //String selectStatement = " select A1.first_name as rider_fname, A1.last_name as rider_lname, " +
                 //   " A2.first_name as owner_fname, A2.last_name as owner_lname, B.horse_member_id, " +
                 //   " B.competition_name, C.membership_type_id, D1.membership_type_name, E.status_name " +
                 //   " from tblUserMaster A2 inner join tblHorseMemberDetails B ON " +
                 //   " B.owner_id = A2.user_id inner join tblMemberDetails C ON " +
                 //   " C.member_id = B.rider_member_id inner join tblUserMaster A1 ON " +
                //    " A1.user_id = C.user_id inner join tblMembershipTypeMaster D ON " +
                //    " D.membership_type_id = C.membership_type_id inner join tblMemberDetails C1 ON " +
                //    " C1.member_id = B.horse_member_id inner join tblMembershipTypeMaster D1 ON " +
                //    " D1.membership_type_id = C1.membership_type_id inner join tblMemberDetails C2 ON " +
                //    " C2.member_id = B.horse_member_id inner join tblMembershipStatusMaster E ON " +
                //    " E.status_id = C2.status_id ";
            
             String selectStatement="";
             if((hrName!=null && hrName.trim().length()!=0) || (hrMemberId!=null && hrMemberId.trim().length()!=0) ||
                    (ownName!=null && ownName.trim().length()!=0) ||  (riderName!=null && riderName.trim().length()!=0 ) ){
              selectStatement = "select A1.first_name as rider_fname, A1.last_name as rider_lname, " +
                     " A2.first_name as owner_fname, A2.last_name as owner_lname, B.horse_member_id, " +
                     " B.competition_name, C.membership_type_id, D1.membership_type_name, E.status_name, " +
                     " F.color_desc, G.gender, G.height, G.year_foaled,  H.breed_desc , B.owner_id, C.member_id " +
                     " from tblUserMaster A2 inner join tblHorseMemberDetails B ON " +
                     " B.owner_id = A2.user_id inner join tblMemberDetails C ON " +
                     " C.member_id = B.rider_member_id inner join tblUserMaster A1 ON " +
                     " A1.user_id = C.user_id inner join tblMembershipTypeMaster D ON " +
                     " D.membership_type_id = C.membership_type_id inner join tblMemberDetails C1 ON " +
                     " C1.member_id = B.horse_member_id inner join tblMembershipTypeMaster D1 ON " +
                     " D1.membership_type_id = C1.membership_type_id inner join tblMemberDetails C2 ON " +
                     " C2.member_id = B.horse_member_id inner join tblMembershipStatusMaster E ON " +
                     " E.status_id = C2.status_id left join tblHorseDescription G ON " +
                     " G.horse_member_id = B.horse_member_id left join tblColorMaster F ON " +
                     " F.color_id = G.color left join tblBreedMaster H ON " +
                     " H.breed_id = G.breed";
             }
            if((hrName!=null && hrName.trim().length()!=0) || (hrMemberId!=null && hrMemberId.trim().length()!=0) ||
                    (ownName!=null && ownName.trim().length()!=0) ||  (riderName!=null && riderName.trim().length()!=0 ) ){
                selectStatement += " where ";
            }
            
            
            if(hrName!=null && hrName.trim().length()!=0){
                selectStatement += " B.competition_name like '" + hrName.replaceAll("'","''") + "%'";
                resultFlagHrName = true;
            }

            if(hrMemberId!=null && hrMemberId.trim().length()!=0){
                if(resultFlagHrName==true){
                    selectStatement += " and  ";
                }
                selectStatement += " B.horse_member_id = '" + hrMemberId + "'";
                resultFlagHrMemberId = true;
            }
            
            if(ownName!=null && ownName.trim().length()!=0){
                if(resultFlagHrName == true || resultFlagHrMemberId==true){
                    selectStatement += " and  ";
                }
                 selectStatement += " A2.first_name like '" + ownName.replaceAll("'","''") + "%' or A2.last_name like '" + ownName.replaceAll("'","''") + "%'";
                 resultFlagOwner = true;
            }
            
           
            if(riderName!=null && riderName.trim().length()!=0){
                if(resultFlagOwner==true || resultFlagHrName == true || resultFlagHrMemberId==true){
                    selectStatement += " and  ";
                }
                selectStatement += " A1.first_name like '" + riderName.replaceAll("'","''") + "%' or A1.last_name like '" + riderName.replaceAll("'","''") + "%'";
            }
            
            Debug.print("Search Query: " + selectStatement);

            prepStmt = con.prepareStatement(selectStatement);

            rs = prepStmt.executeQuery();
            while (rs.next()) {
                HorseSearch2VO objSearchHorse = new HorseSearch2VO();
                String riderFirstName = rs.getString(1); 
                String riderLastName = rs.getString(2); 
                String ownerFirstName = rs.getString(3);
                String ownerlastName = rs.getString(4);
                String horseMemberId = rs.getString(5); 
                String horseName = rs.getString(6); 
                String membershipTypeId = rs.getString(7); 
                String membershipTypeName = rs.getString(8); 
                String statusName = rs.getString(9);
                String colorDesc = rs.getString(10) ;
                String gender = rs.getString(11);
                String height = rs.getString(12);
                String yearFoaled = rs.getString(13);
                String breedDesc = rs.getString(14);
                String ownerId = rs.getString(15);
                String riderMemberId = rs.getString(16);

                objSearchHorse.setRiderFirstName(riderFirstName);
                objSearchHorse.setRiderLastName(riderLastName);
                objSearchHorse.setOwnerFirstName(ownerFirstName);
                objSearchHorse.setOwnerlastName(ownerlastName);
                objSearchHorse.setHorseMemberId(horseMemberId);
                objSearchHorse.setHorseName(horseName);
                objSearchHorse.setMembershipTypeId(membershipTypeId);
                objSearchHorse.setMembershipTypeName(membershipTypeName);
                objSearchHorse.setStatusName(statusName);
                objSearchHorse.setColorDesc(colorDesc);
                objSearchHorse.setGender(gender);
                objSearchHorse.setHeight(height);
                objSearchHorse.setYearFoaled(yearFoaled);
                objSearchHorse.setBreedDesc(breedDesc);
                objSearchHorse.setOwnerId(ownerId);
                objSearchHorse.setRiderMemberId(riderMemberId);
                searchList.add(objSearchHorse);
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
        } 
        catch(SQLException sql){
            releaseConnection();
            System.out.print("SQL Exception in kaverySessionBeanStatlessBean.searchHorseDetails():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            System.out.print("General Exception  in kaverySessionBeanStatlessBean.searchHorseDetails():" + e.getMessage());
        }
        Debug.print("Horse Search Result Size:" + searchList.size());
        return searchList;
    }
    
    */
     public ArrayList searchHorse(String hrMemberId, String hrName, String ownName, String riderName,String ownersLastName, String ridersLastName) throws RemoteException{
        Debug.print("kaverySessionBeanStatlessBean.searchHorse()");
        ArrayList searchList = new ArrayList();
        boolean resultFlagHrName = false;
        boolean resultFlagHrMemberId = false;
        boolean resultFlagOwner = false;
        boolean resultFlagOwnerLastName = false;
        boolean resultFlagRiderFirstName = false;
        makeConnection();
        try {
           /*String selectStatement = " select A1.first_name as rider_fname, A1.last_name as rider_lname, " +
                    " A2.first_name as owner_fname, A2.last_name as owner_lname, B.horse_member_id, " +
                    " B.competition_name, C.membership_type_id, D1.membership_type_name, E.status_name " +
                    " from tblUserMaster A2 inner join tblHorseMemberDetails B ON " +
                    " B.owner_id = A2.user_id inner join tblMemberDetails C ON " +
                    " C.member_id = B.rider_member_id inner join tblUserMaster A1 ON " +
                    " A1.user_id = C.user_id inner join tblMembershipTypeMaster D ON " +
                    " D.membership_type_id = C.membership_type_id inner join tblMemberDetails C1 ON " +
                    " C1.member_id = B.horse_member_id inner join tblMembershipTypeMaster D1 ON " +
                    " D1.membership_type_id = C1.membership_type_id inner join tblMemberDetails C2 ON " +
                    " C2.member_id = B.horse_member_id inner join tblMembershipStatusMaster E ON " +
                    " E.status_id = C2.status_id ";
            */
             String selectStatement="";
             if((hrName!=null && hrName.trim().length()!=0) || (hrMemberId!=null && hrMemberId.trim().length()!=0) ||
                    (ownName!=null && ownName.trim().length()!=0) ||  (riderName!=null && riderName.trim().length()!=0) || 
                    (ownersLastName!=null && ownersLastName.trim().length()!=0 )||  (ridersLastName!=null && ridersLastName.trim().length()!=0 )){
              selectStatement = "select A1.first_name as rider_fname, A1.last_name as rider_lname, " +
                     " A2.first_name as owner_fname, A2.last_name as owner_lname, B.horse_member_id, " +
                     " B.competition_name, C.membership_type_id, D1.membership_type_name, E.status_name, " +
                     " F.color_desc, G.gender, G.height, G.year_foaled, H.breed_desc , H2.breed_desc as breed2, B.owner_id, C.member_id " +
                     " from tblUserMaster A2 inner join tblHorseMemberDetails B ON " +
                     " B.owner_id = A2.user_id inner join tblMemberDetails C ON " +
                     " C.member_id = B.rider_member_id inner join tblUserMaster A1 ON " +
                     " A1.user_id = C.user_id inner join tblMembershipTypeMaster D ON " +
                     " D.membership_type_id = C.membership_type_id inner join tblMemberDetails C1 ON " +
                     " C1.member_id = B.horse_member_id inner join tblMembershipTypeMaster D1 ON " +
                     " D1.membership_type_id = C1.membership_type_id inner join tblMemberDetails C2 ON " +
                     " C2.member_id = B.horse_member_id inner join tblMembershipStatusMaster E ON " +
                     " E.status_id = C2.status_id left join tblHorseDescription G ON " +
                     " G.horse_member_id = B.horse_member_id left join tblColorMaster F ON " +
                     " F.color_id = G.color left join tblBreedMaster H ON " +
                     " H.breed_id = G.breed left join tblBreedMaster H2 ON H2.breed_id = G.breed2 ";
             }
            if((hrName!=null && hrName.trim().length()!=0) || (hrMemberId!=null && hrMemberId.trim().length()!=0) ||
                    (ownName!=null && ownName.trim().length()!=0) ||  (riderName!=null && riderName.trim().length()!=0 ) || 
                    (ownersLastName!=null && ownersLastName.trim().length()!=0 )||  (ridersLastName!=null && ridersLastName.trim().length()!=0 )){
                selectStatement += " where ";
            }
            
            if(hrName!=null && hrName.trim().length()!=0){
                selectStatement += " B.competition_name like '" + hrName.replaceAll("'","''") + "%'";
                resultFlagHrName = true;
            }

            if(hrMemberId!=null && hrMemberId.trim().length()!=0){
                if(resultFlagHrName==true){
                    selectStatement += " or  ";
                }
                selectStatement += " B.horse_member_id = '" + hrMemberId + "'";
                resultFlagHrMemberId = true;
            }
            
            if(ownName!=null && ownName.trim().length()!=0){
                if(resultFlagHrName == true || resultFlagHrMemberId==true){
                    selectStatement += " or  ";
                }
                 selectStatement += " A2.first_name like '" + ownName.replaceAll("'","''")  + "%'";
                 resultFlagOwner = true;
            }
             if(ownersLastName!=null && ownersLastName.trim().length()!=0){
                if(resultFlagOwner == true){
                    selectStatement += " and  ";
                }
                else if(resultFlagHrName == true || resultFlagHrMemberId==true){
                    selectStatement += " or  ";
                }
                if(resultFlagOwner==false || resultFlagHrName == false || resultFlagHrMemberId==false){
                    selectStatement +="";
                }
                 selectStatement += " A2.last_name like '" + ownersLastName.replaceAll("'","''") +  "%'";
                 resultFlagOwnerLastName = true;
            }
            
           
            if(riderName!=null && riderName.trim().length()!=0){
                if(resultFlagOwner==true || resultFlagHrName == true || resultFlagHrMemberId==true || resultFlagOwnerLastName == true){
                    selectStatement += " or  ";
                }
                selectStatement += " A1.first_name like '" + riderName.replaceAll("'","''") + "%'";
                resultFlagRiderFirstName = true;
            }
             
            if(ridersLastName!=null && ridersLastName.trim().length()!=0){
                if(resultFlagRiderFirstName == true){
                    selectStatement += " and  ";
                }
                else if(resultFlagOwner==true || resultFlagHrName == true || resultFlagHrMemberId==true || resultFlagOwnerLastName == true){
                     selectStatement += " or  ";
                }
                if(resultFlagRiderFirstName==false || resultFlagOwner==false || resultFlagOwnerLastName==false || resultFlagHrName == false || resultFlagHrMemberId==false){
                    selectStatement +="";
                }
                selectStatement += " A1.last_name like '" + ridersLastName.replaceAll("'","''")  + "%'";
            }
            
            
            Debug.print("Search Query: " + selectStatement);

            prepStmt = con.prepareStatement(selectStatement);

            rs = prepStmt.executeQuery();
            while (rs.next()) {
                HLCHorseSearch2VO objSearchHorse = new HLCHorseSearch2VO();
                String riderFirstName = rs.getString(1); 
                String riderLastName = rs.getString(2); 
                String ownerFirstName = rs.getString(3);
                String ownerlastName = rs.getString(4);
                String horseMemberId = rs.getString(5); 
                String horseName = rs.getString(6); 
                String membershipTypeId = rs.getString(7); 
                String membershipTypeName = rs.getString(8); 
                String statusName = rs.getString(9);
                String colorDesc = rs.getString(10) ;
                String gender = rs.getString(11);
                String height = rs.getString(12);
                String yearFoaled = rs.getString(13);
                String breedDesc = rs.getString(14);
                String breed2 = rs.getString(15);
                String ownerId = rs.getString(16);
                String riderMemberId = rs.getString(17);

                objSearchHorse.setRiderFirstName(riderFirstName);
                objSearchHorse.setRiderLastName(riderLastName);
                objSearchHorse.setOwnerFirstName(ownerFirstName);
                objSearchHorse.setOwnerlastName(ownerlastName);
                objSearchHorse.setHorseMemberId(horseMemberId);
                objSearchHorse.setHorseName(horseName);
                objSearchHorse.setMembershipTypeId(membershipTypeId);
                objSearchHorse.setMembershipTypeName(membershipTypeName);
                objSearchHorse.setStatusName(statusName);
                objSearchHorse.setColorDesc(colorDesc);
                objSearchHorse.setGender(gender);
                objSearchHorse.setHeight(height);
                objSearchHorse.setYearFoaled(yearFoaled);
                objSearchHorse.setSecondBreed(breed2);
                objSearchHorse.setBreedDesc(breedDesc);
                objSearchHorse.setOwnerId(ownerId);
                objSearchHorse.setRiderMemberId(riderMemberId);
                searchList.add(objSearchHorse);
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
        } 
        catch(SQLException sql){
            releaseConnection();
            System.out.print("SQL Exception in kaverySessionBeanStatlessBean.searchHorseDetails():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            System.out.print("General Exception  in kaverySessionBeanStatlessBean.searchHorseDetails():" + e.getMessage());
        }
        Debug.print("Horse Search Result Size:" + searchList.size());
        return searchList;
    }
    
     public String getCountryPrice(String memTypeId, String countryId) throws RemoteException{
         System.out.println(" KaverySessionBean getCountryPrice memTypeId: "+ memTypeId);
         System.out.println(" KaverySessionBean getCountryPrice countryId: "+ countryId);
         String countryPrice = "";
         try {
             if(memTypeId!=null && memTypeId.trim().length()!=0 && countryId!=null && countryId.trim().length()!=0){
                countryPrice = selectCountryPrice(memTypeId, countryId);
             }
         }catch(Exception e){
             System.out.println(" Error While getting getCountryPrice : "+e.getMessage());
         }
         return countryPrice;
    }

     
 public String selectCountryPrice(String memTypeId, String countryName) throws SQLException{
        System.out.print("kaverySessionBeanStatlessBean selectCountryPrice");
        String countryMailPrice = "";
        makeConnection();
        try{
            String selectStatement = "select country_mail_price from tblCountryMailPriceMaster " +
                    " where country_mail_type_name=? and membership_type_id = ?";
            
            PreparedStatement prepSelect = con.prepareStatement(selectStatement);
            prepSelect.setString(1,countryName.trim());
            prepSelect.setString(2,memTypeId.trim());
            ResultSet rs = prepSelect.executeQuery();
            if(rs.next()){
                countryMailPrice = rs.getString(1);
            }
            rs.close();
            prepSelect.close();
            releaseConnection();
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in selectDonationDisplayDetails:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in selectDonationDisplayDetails:" + e.getMessage());
        }
        return countryMailPrice;
    }


 public String selectCountryDetails(String userId) throws SQLException{
        System.out.print("kaverySessionBeanStatlessBean selectCountryDetails userId :"+userId);
        String countryName = "";
        makeConnection();
        try{
            String selectStatement = "select B.country from tblUserMaster A, tblContactDetails B " +
                    " where A.contact_type_id = B.contact_type_id and  A.user_id =B.user_id" +
                    " and A.user_id = ?";
            PreparedStatement prepSelect = con.prepareStatement(selectStatement);
            prepSelect.setString(1,userId);
            ResultSet rs = prepSelect.executeQuery();
            if(rs.next()){
                countryName = rs.getString(1);
            }
            rs.close();
            prepSelect.close();
            releaseConnection();
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in selectCountryDetails:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in selectCountryDetails:" + e.getMessage());
        }
        return countryName;
    }

   public String getCountryName(String userId)throws RemoteException {
            System.out.println(" KaverySessionBean getCountryName userId: "+ userId);
         String countryName = "";
         try {
             if(userId!=null && userId.trim().length()!=0){
                countryName = selectCountryDetails(userId);
             }
         }catch(Exception e){
             System.out.println(" Error While getting getCountryName : "+e.getMessage());
         }
         return countryName;
     }
//==================================================================================================
   //Suresh Here
//==================================================================================================
     public boolean generateMappingEventTypeWithResultFields(String eventTypeId, ArrayList compResultFieldList) throws RemoteException {
        Debug.print("BrahmaputraSessionBean generateMappingEventTypeWithResultFields()" + eventTypeId);
        boolean result =false;
        Iterator itEntityList = compResultFieldList.iterator();
        boolean flag = deleteTableRow("event_type_id", eventTypeId, "tblMeeMapCompResultFields");
        Debug.print("BrahmaputraSessionBean generateMappingEventTypeWithResultFields(): Deleted Competition Result:" + flag);
        //if(flag){
            if(compResultFieldList!=null && compResultFieldList.size()!=0){
                while(itEntityList.hasNext()){
                    String resultIds[] = (String[])itEntityList.next();
                    String resultId = resultIds[0];
                    int priorityId = 0;
                    if(resultIds[1] !=null && resultIds[1].trim().length()!=0){
                        priorityId = Integer.parseInt(resultIds[1]);    
                    }
                    
                    if(resultId!=null && resultId.trim().length()!=0){
                        insertEventTypeResultMapping(eventTypeId, resultId, priorityId);
                    }
                }
            }
            result = true;
      //  }
         return result;
     }
  
     public ArrayList getMappingDetailsForEventTypeAndCompResult(String  eventTypeId)  throws RemoteException {
        Debug.print("BrahmaputraSessionBean getMappingDetailsForEventTypeAndCompResult");
        ArrayList results = null;
        if(eventTypeId!=null && eventTypeId.trim().length()!=0){
            results = selectAllMappingDetailsForEventType(eventTypeId);
        }
        return results;
    }
  
    public boolean generateMappingEventTypeWithEventLevels(String eventTypeId, ArrayList eventLevelList) throws RemoteException {
        Debug.print("kaverySessionBeanStatlessBean generateMappingEventTypeWithEventLevels()" + eventTypeId);
        boolean result =false;      
            try{
            if(eventTypeId!=null && eventTypeId.trim().length()!=0){
                Iterator itEvtLevelList = eventLevelList.iterator();
                     while(itEvtLevelList.hasNext()){
                    String evtLevelId = (String)itEvtLevelList.next();
                    if(evtLevelId!=null && evtLevelId.trim().length()!=0){
                        Debug.print("evtLevelId in kaverySessionBeanStatlessBean generateMappingEventTypeWithEventLevels()" + evtLevelId);
                        result= insertEventTypeLevelMapping(eventTypeId, evtLevelId);
                    }
                }
            } 
        }catch(Exception e){
                Debug.print("General Exception in kaverySessionBeanStatlessBean.generateMappingEventTypeWithEventLevels():" + e.getMessage());
            }              
       
         return result;
     }
  
     public ArrayList getMappingDetailsForEventTypeAndEventLevels(String  eventTypeId)  throws RemoteException {
        Debug.print("BrahmaputraSessionBean getMappingDetailsForEventTypeAndEventLevels");
        ArrayList results = null;
        if(eventTypeId!=null && eventTypeId.trim().length()!=0){
            results = selectAllMappingDetailsForEventTypeLevel(eventTypeId);
        }
        return results;
    }     
    
  //=============================================  Mapping Entites with privilege details=========================================      
    public boolean insertEventTypeResultMapping(String eventTypeId, String resultId, int priorityId) {
        Debug.print("kaverySessionBeanStatlessBean.insertEventTypeResultMapping():");
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String insertStatement = "insert into tblMeeMapCompResultFields (event_type_id , comp_result_field_id, priority) " +
                    " values( ? , ?, ? )";
            prepStmt = con.prepareStatement(insertStatement);
            prepStmt.setString(1, eventTypeId);
            prepStmt.setString(2, resultId);
            prepStmt.setInt(3, priorityId);
            prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection();
        } 
        catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in kaverySessionBeanStatlessBean.insertEventTypeResultMapping():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in kaverySessionBeanStatlessBean.insertEventTypeResultMapping():" + e.getMessage());
        }
        return true;
    }
    
    
     //=============================================  Mapping Entites with privilege details=========================================      
    public boolean insertEventTypeLevelMapping(String eventTypeId, String evtLevelId) {
        Debug.print("kaverySessionBeanStatlessBean.insertEventTypeLevelMapping():");
         Debug.print("kaverySessionBeanStatlessBean.insertEventTypeLevelMapping():"+eventTypeId);
          Debug.print("kaverySessionBeanStatlessBean.insertEventTypeLevelMapping():"+evtLevelId);
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String insertStatement = "insert into tblMeeMapEventLevel (event_type_id , event_level_id) " +
                    " values( ? , ? )";
            
            Debug.print("insertStatement in insertEventTypeLevelMapping():"+insertStatement);
            prepStmt = con.prepareStatement(insertStatement);
            prepStmt.setString(1, eventTypeId);
            prepStmt.setString(2, evtLevelId);
            prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection();
        } 
        catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in kaverySessionBeanStatlessBean.insertEventTypeLevelMapping():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in kaverySessionBeanStatlessBean.insertEventTypeLevelMapping():" + e.getMessage());
        }
        return true;
    }
    
    //=============================================Delete Mapping Records with fieldValue details========================================= 
     public boolean deleteTableRow(String fieldName, String fieldValue, String tableName) {
        Debug.print("kaverySessionBeanStatlessBean.deleteTableRow()");
        boolean result = false;
        makeConnection();
        try{
            String deleteStatement = "delete from " + tableName + "  where " + fieldName  + " = ? ";
            Debug.print("kaverySessionBeanStatlessBean.deleteTableRow():" + "\n" + deleteStatement + ":" +  fieldValue);
            PreparedStatement prepStmt = con.prepareStatement(deleteStatement);
            prepStmt.setString(1, fieldValue);
            int i = prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection();
            if (i>=1) {
                result = true;
            }
            else{
                result =false;
            }
        }
        catch(SQLException sql){
           releaseConnection();
           Debug.print("SQL Exception in kaverySessionBeanStatlessBean.deleteTableRow:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in kaverySessionBeanStatlessBean.deleteTableRow:" + e.getMessage());
        }        
        return result;
    }
//=============================================Delete Mapping Records with fieldValue details========================================= 
     public boolean deleteRow(String fieldName1, String fieldValue1, String fieldName2, String fieldValue2, String tableName) {
        Debug.print("BrahmaputraSessionBean.deleteRow()");
        boolean result = false;
        makeConnection();
        try{
            String deleteStatement = "delete from " + tableName + "  where " + fieldName1  + " = ? and " + fieldName2 + " = ?" ;
            PreparedStatement prepStmt = con.prepareStatement(deleteStatement);
            prepStmt.setString(1, fieldValue1);
            prepStmt.setString(2, fieldValue2);
            prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection();
            result = true;
        }
        catch(SQLException sql){
           releaseConnection();
           Debug.print("SQL Exception in BrahmaputraSessionBean.deleteRow:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in BrahmaputraSessionBean.deleteRow:" + e.getMessage());
        }        
        return result;
    }

   //=============================================Getting Mapping Role with Entities details=========================================      
   public ArrayList selectAllMappingDetailsForEventType(String eventTypeId){
        Debug.print("BrahmaputraSessionBean.selectAllMappingDetailsForEventType():");
        ArrayList eventTypeMapList = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
   	try {
            String selectStatement=" SELECT a.map_comp_result_id , a.event_type_id ,a.comp_result_field_id, b.event_type_name," +
                    " c.comp_result_field_name , a.priority from tblMeeMapCompResultFields "
                    + " a , tblMeeEventTypeMaster b , tblMeeCompResultFieldMaster c " +
                    " where a.comp_result_field_id = c.comp_result_field_id and a.event_type_id = b.event_type_id and a.event_type_id = ? order by c.comp_result_field_name";
         
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,eventTypeId);
            //Debug.print(selectStatement);
            rs = prepStmt.executeQuery();
            eventTypeMapList = new ArrayList();
            while(rs.next()){
                String mapEventTypeId = rs.getString(1);
                String tepmEventTypeId = rs.getString(2);
                String compResultId = rs.getString(3);
                String eventTypeName = rs.getString(4);
                String resultName = rs.getString(5);
                int priority = rs.getInt(6);
                String[] evtMapList = {mapEventTypeId, tepmEventTypeId, compResultId, eventTypeName, resultName,String.valueOf(priority)};
                eventTypeMapList.add(evtMapList);
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
            //Debug.print("BrahmaputraSessionBean.selectAllMappingDetailsForEventType():" + roleEntityMapList);
        } 
        catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in BrahmaputraSessionBean.selectAllMappingDetailsForEventType():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in BrahmaputraSessionBean.selectAllMappingDetailsForEventType():" + e.getMessage());
        }
        return eventTypeMapList;
    }
   
   
//=============================================Getting Mapping Role with Entities details=========================================      
   public ArrayList selectAllMappingDetailsForEventTypeLevel(String eventTypeId){
        Debug.print("BrahmaputraSessionBean.selectAllMappingDetailsForEventTypeLevel():");
        ArrayList eventTypeMapList = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
   	try {
            String selectStatement=" SELECT a.map_type_id , a.event_type_id ,a.event_level_id, b.event_type_name," +
                    " c.event_level_name, c.event_level_code from tblMeeMapEventLevel a, "
                    + " tblMeeEventTypeMaster b , tblMeeEventLevelMaster c " +
                    " where a.event_level_id = c.event_level_id and " +
                    "a.event_type_id = b.event_type_id and a.event_type_id = ? order by c.event_level_name";
         
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,eventTypeId);
            //Debug.print(selectStatement);
            rs = prepStmt.executeQuery();
            eventTypeMapList = new ArrayList();
            while(rs.next()){
                String mapTypeId = rs.getString(1);
                String tepmEventTypeId = rs.getString(2);
                String eventLevelId = rs.getString(3);
                String eventTypeName = rs.getString(4);
                String eventLevelName = rs.getString(5);
                String eventLevelCode = rs.getString(6);
                
                String[] evtMapList = {mapTypeId, tepmEventTypeId, eventLevelId, eventTypeName, eventLevelName, eventLevelCode};
                eventTypeMapList.add(evtMapList);
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
            //Debug.print("BrahmaputraSessionBean.selectAllMappingDetailsForEventTypeLevel():" + roleEntityMapList);
        } 
        catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in BrahmaputraSessionBean.selectAllMappingDetailsForEventTypeLevel():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in BrahmaputraSessionBean.selectAllMappingDetailsForEventTypeLevel():" + e.getMessage());
        }
        return eventTypeMapList;
    }   
     public ArrayList getWebSiteDonationDetails(String donationPaymentId)throws RemoteException {
            System.out.println(" KaverySessionBean getWebSiteDonationDetails userId: "+ donationPaymentId);
         ArrayList donationList = new ArrayList();
         try {
             if(donationPaymentId!=null && donationPaymentId.trim().length()!=0){
                donationList = selectWebSiteDisplayDetails(donationPaymentId);
             }
         }catch(Exception e){
             System.out.println(" Error While getting KaverySessionBean getWebSiteDonationDetails : "+e.getMessage());
         }
         return donationList;
     }
    public ArrayList selectWebSiteDisplayDetails(String donationPaymentId) throws SQLException{
        System.out.print("kaverySessionBeanStatlessBean selectDonationDisplayDetails");
        ArrayList donationList = new ArrayList();
        makeConnection();
        try{
            String selectStatement = "select A.donation_payment_id, A.user_id, A.donation_id, B.donation_name, "+
                                     "A.donation_price, A.donated_by, A.add_date,C.cc_number, C.first_name, C.last_name, "+
                                     "C.amount from tblMemberDonationDetails A, tblDonationDetails B, tblDonationPaymentDetails C "+
                                     "where A.donation_payment_id=C.donation_payment_id and A.donation_id=B.donation_id and A.donation_payment_id= ? ";
            
            PreparedStatement prepSelect = con.prepareStatement(selectStatement);
            prepSelect.setString(1,donationPaymentId);
            ResultSet rs = prepSelect.executeQuery();
            while(rs.next()){
                HLCDonationDetailVO onjDon = new HLCDonationDetailVO();
                String donPaymentId = rs.getString(1);
                String user_id = rs.getString(2);
                String donation_id = rs.getString(3);
                String donation_name = rs.getString(4);
                String donation_price = rs.getString(5);
                String donated_by = rs.getString(6);
                String donationDate = rs.getString(7);
                String ccNumber = rs.getString(8);
                String firstName = rs.getString(9);
                String lastName = rs.getString(10);
                float totAmount = rs.getFloat(11);

                onjDon.setDonationPaymentId(donPaymentId);
                onjDon.setUserId(user_id);
                onjDon.setDonationId(donation_id);
                onjDon.setDonationPrice(donation_price);
                onjDon.setDonationName(donation_name);
                onjDon.setDonatedBy(donated_by);
                onjDon.setMemberDonationDate(donationDate);
                onjDon.setCcNumber(ccNumber);
                onjDon.setFirstName(firstName);
                onjDon.setLastName(lastName);
                onjDon.setTotDonAmt(totAmount);
                donationList.add(onjDon);
            }
            rs.close();
            prepSelect.close();
            releaseConnection();
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in selectDonationDisplayDetails:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in selectDonationDisplayDetails:" + e.getMessage());
        }
        return donationList;
    }   
   public Vector getMembershipTypesOnCurYr(String userTypeName) throws RemoteException {
       
       Vector V = new Vector();
        
       try
       {
        PreparedStatement prepStmt1=null;
        ResultSet rs= null;
        Debug.print("user Type Name :"+userTypeName);
        String userTypeId = getMemberName(userTypeName);
        Debug.print("user Type Id :"+userTypeId);
        
        /*java.util.Date today = new java.util.Date();
        date.setYear(today.getYear()+1);*/
            
       Calendar tCal = Calendar.getInstance();
       int curYr = tCal.get(Calendar.YEAR);
       
       Debug.print("current year :"+curYr);
       
        makeConnection();
        String selectStatement = "select membership_type_id, membership_type_name, membership_amount, priority_value  " +
                " from tblMembershipTypeMaster where user_type_id = ? and active_status = ? and membership_year = ? ";
        prepStmt1 = con.prepareStatement(selectStatement);
        prepStmt1.setString(1, userTypeId);
        prepStmt1.setBoolean(2, true);
        prepStmt1.setInt(3, curYr);
        
        rs = prepStmt1.executeQuery();

        while (rs.next()){
            String memberTypeId = rs.getString(1);
            String memberType = rs.getString(2);
            String memberAmount = rs.getString(3);
            String priorityValue = rs.getString(4);
            String[] member = {memberTypeId, memberType, memberAmount, priorityValue};
            V.add(member);
         }

        rs.close();
        prepStmt1.close();
        releaseConnection();
       }
       catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in BrahmaputraSessionBean.selectAllMappingDetailsForEventTypeLevel():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in BrahmaputraSessionBean.selectAllMappingDetailsForEventTypeLevel():" + e.getMessage());
        }
       
        return V;
    }
   
   /**
  * Name         : getMemberStatus
  * Author       : Khushwinder
  * Description  : This method is used for checking the status of the user based on his memeberId.
                   It will return true if the user is an active member and false if the user is non-active member.
                   
  * @ param      : memeberId
  * @return      : boolean
  * @throws      : SQLException
  */     
   public String getMemberStatus(String memberId)throws RemoteException {
            System.out.println(" KaverySessionBean getMemberStatus userId: "+ memberId);
         String memberStatus = "";
         try {
             if(memberId!=null && memberId.trim().length()!=0){
                memberStatus = selectMemberStatus(memberId);
             }
         }catch(Exception e){
             System.out.println(" Error While getting getMemberStatus : "+e.getMessage());
         }
         return memberStatus;
     } 
   public String selectMemberStatus(String memberId) throws SQLException{
        System.out.print("kaverySessionBeanStatlessBean selectMemberStatus userId :"+memberId);
        String statusId="";
        String statusName = "";
        makeConnection();
        try{
            String selectStatement = "select A.status_id, B.status_name from tblMemberDetails A, tblMembershipStatusMaster B where " +
                    " A.status_id=B.status_id and A.member_id=? ";
            PreparedStatement prepSelect = con.prepareStatement(selectStatement);
            prepSelect.setString(1,memberId.trim());
            ResultSet rs = prepSelect.executeQuery();
            if(rs.next()){
                statusId=rs.getString(1);
                statusName = rs.getString(2);
            }
            rs.close();
            prepSelect.close();
            releaseConnection();
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in selectMemberStatus:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in selectMemberStatus:" + e.getMessage());
        }
        return statusName;
    }
}
