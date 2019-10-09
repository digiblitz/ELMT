/*
 * MessageDAO.java
 *
 * Created on September 27, 2006, 7:40 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmsg.util;
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
public class HLCMessageDAO {
    private static final String dbName = "java:/ELMTMSSQLDS";
    private Connection con = null;
    private PreparedStatement prepStmt = null;
    private ResultSet rs = null;
    private PreparedStatement prepStmt1 = null;
    private ResultSet rs1 = null;
    
    private String userId = null;
    private String contactlistId = null;
    private String firstName = null;
    private String lastName = null;
    private String emailId = null;
    private String listName = null;
    private String listId = null;
    
    private String middleName;
    private String nickName;
    private String alterNameMailId;
    private String phoneNo;
    private String mobileNo;
    private String faxNo;
    private String street;
    private String country;
    private String state;
    private String city;
    private String zip;
    
    private String groupId;
    private String groupName;
    private String groupType;
    private String groupDescription;
    private String categoryId;

    private HLCMsgPaginationVO pageVO;
    
    /** Creates a new instance of MessageDAO */
    public HLCMessageDAO() {
    }
    
/**
  * Name         :selectContactListId
  * Description  :This method will display the updateEventRegType
  * @ param      :specificationId,userTypeId,eventRegistrationTypeId,dueDatePrice,aftDueDatePrice
  * @return      :boolean
  * @throws      :SQLException
  */   
    public Vector selectContactListId(String ContactListId) throws SQLException {
        Debug.print("Inside the selectContactListId  userId : "+userId);
        this.userId =userId;
        Vector vObj = new Vector();
        try {
            makeConnection();
            String selectStatement = "SELECT contactlist_id,first_name,middle_name, last_name,nick_name, email_id,alternate_email_id," +
                    " phone_no,mobile_no,fax_no,street,country,state,city,zip FROM  " +DBHelper.USEA_MSG_CONTACT_LIST_MASTER+
                    " WHERE contactlist_id = ? ";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, ContactListId);
            rs = prepStmt.executeQuery();
            while (rs.next()) {
                this.contactlistId = rs.getString(1);
                Debug.print(" contactlistId : "+contactlistId);
                this.firstName = rs.getString(2);
                Debug.print(" firstName : "+firstName);
                this.middleName = rs.getString(3);
                this.lastName = rs.getString(4);
                Debug.print(" lastName : "+lastName);
                this.nickName = rs.getString(5);
                this.emailId = rs.getString(6);
                Debug.print(" emailId : "+emailId);
                this.alterNameMailId = rs.getString(7);
                Debug.print(" alterNameMailId : "+alterNameMailId);
                this.phoneNo = rs.getString(8);
                this.mobileNo = rs.getString(9);
                this.faxNo = rs.getString(10);
                this.street = rs.getString(11);
                this.country = rs.getString(12);
                this.state = rs.getString(13);
                this.city = rs.getString(14);
                this.zip = rs.getString(15);
                String [] contact = {contactlistId,firstName,middleName,lastName,nickName, emailId,alterNameMailId,phoneNo,mobileNo,faxNo,street,
                country,state,city,zip};
               
                vObj.add(contact);
             } 
             prepStmt.close();
        } catch (SQLException sqe) {
            throw new EJBException(sqe);
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return vObj;
     }      
    
/**
  * Name         :selectContactListId
  * Description  :This method will display the updateEventRegType
  * @ param      :specificationId,userTypeId,eventRegistrationTypeId,dueDatePrice,aftDueDatePrice
  * @return      :boolean
  * @throws      :SQLException
  */   
    public HLCMsgContactListMaster selectContactListIdVO(String ContactListId) throws SQLException {
        Debug.print("Inside the selectContactListId  ContactListId : "+ContactListId);
        this.userId =userId;
        HLCMsgContactListMaster objVO = null;
      
        try {
            makeConnection();
            String selectStatement = "SELECT contactlist_id,first_name,middle_name, last_name,nick_name, email_id,alternate_email_id," +
                    " phone_no,mobile_no,fax_no,street,country,state,city,zip FROM  " +DBHelper.USEA_MSG_CONTACT_LIST_MASTER+
                    " WHERE contactlist_id = ? ";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, ContactListId);
            rs = prepStmt.executeQuery();
            while (rs.next()) {
                objVO = new HLCMsgContactListMaster();
                objVO.setContactlistId(rs.getString(1));
                objVO.setFirstName(rs.getString(2));
                objVO.setMiddleName(rs.getString(3));
                objVO.setLastName(rs.getString(4));
                objVO.setNickName(rs.getString(5));
                objVO.setEmailId(rs.getString(6));
                objVO.setAlternateEmailId(rs.getString(7));
                objVO.setPhoneNo(rs.getString(8));
                objVO.setMobileNo(rs.getString(9));
                objVO.setFaxNo(rs.getString(10));
                objVO.setStreet(rs.getString(11));
                objVO.setCountry(rs.getString(12));
                objVO.setState(rs.getString(13));
                objVO.setCity(rs.getString(14));
                objVO.setZip(rs.getString(15));
             } 
             prepStmt.close();
        } catch (SQLException sqe) {
            throw new EJBException(sqe);
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return objVO;
     }          
    
/**
  * Name         :selectContactListBasedOnUserId
  * Description  :This method will display the updateEventRegType
  * @ param      :specificationId,userTypeId,eventRegistrationTypeId,dueDatePrice,aftDueDatePrice
  * @return      :boolean
  * @throws      :SQLException
  */   
    public Vector selectContactListBasedOnUserId(String userId) throws SQLException {
        Debug.print("Inside the selectContactListId  userId : "+userId);
        this.userId =userId;
        Vector vObj = new Vector();
        try {
            makeConnection();
            String selectStatement = "SELECT contactlist_id,first_name,middle_name, last_name,nick_name, email_id" +
                    "  FROM  " +DBHelper.USEA_MSG_CONTACT_LIST_MASTER+
                    " WHERE user_id = ? ";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, userId);
            rs = prepStmt.executeQuery();
            while (rs.next()) {
                this.contactlistId = rs.getString(1);
                Debug.print(" contactlistId : "+contactlistId);
                this.firstName = rs.getString(2);
                Debug.print(" firstName : "+firstName);
                this.middleName = rs.getString(3);
                this.lastName = rs.getString(4);
                Debug.print(" lastName : "+lastName);
                this.nickName = rs.getString(5);
                this.emailId = rs.getString(6);
                Debug.print(" emailId : "+emailId);
                
                String [] contact = {contactlistId,firstName,middleName,lastName,nickName, emailId};
               
                vObj.add(contact);
             } 
             prepStmt.close();
        } catch (SQLException sqe) {
            throw new EJBException(sqe);
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return vObj;
     }     
    
/**
  * Name         :selectContactListBasedOnUserId
  * Description  :This method will display the updateEventRegType
  * @ param      :specificationId,userTypeId,eventRegistrationTypeId,dueDatePrice,aftDueDatePrice
  * @return      :boolean
  * @throws      :SQLException
  */   
    public List selectContactListVO(String userId) throws SQLException {
        Debug.print("Inside the selectContactListId  userId : "+userId);
        this.userId =userId;
        HLCMsgContactListMaster objVO = null;
        List list = new ArrayList();
        try {
            makeConnection();
            String selectStatement = "SELECT contactlist_id,first_name,middle_name, last_name,nick_name, email_id" +
                    "  FROM  " +DBHelper.USEA_MSG_CONTACT_LIST_MASTER+
                    " WHERE user_id = ? ";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, userId);
            rs = prepStmt.executeQuery();
            while (rs.next()) {
                objVO = new HLCMsgContactListMaster();
                objVO.setContactlistId(rs.getString(1));
                Debug.print(" contactlistId : "+contactlistId);
                objVO.setFirstName(rs.getString(2));
                Debug.print(" firstName : "+firstName);
                objVO.setMiddleName(rs.getString(3));
                objVO.setLastName(rs.getString(4));
                Debug.print(" lastName : "+lastName);
                objVO.setNickName(rs.getString(5));
                objVO.setEmailId(rs.getString(6));
                Debug.print(" emailId : "+emailId);
 
                list.add(objVO);
             } 
             prepStmt.close();
        } catch (SQLException sqe) {
            throw new EJBException(sqe);
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return list;
     }   
    
    public List  selectStateByAreaId(String areaId){
        Debug.print("EventsDAO.selectStateByAreaId():");
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        List lst = new ArrayList();
        makeConnection();
   	try {
            String selectStatement=" select A.state_id, A.state_name, A.state_code, B.zip_code_from, B.zip_code_to  " +
                    " from " + DBHelper.USEA_MRO_STATE_MASTER + " A , " + DBHelper.USEA_MRO_MAP_STATE_ZIP +
                    " B , " + DBHelper.USEA_MRO_AREA_MASTER + " C " +
                    " where B.area_id = C.area_id and B.state_id = A.state_id and B.area_id = '"+areaId+"'";
    
            prepStmt = con.prepareStatement(selectStatement);
           // prepStmt.setString(1,areaId);
            Debug.print(" Query : \n"+selectStatement);
            rs = prepStmt.executeQuery();
            while(rs.next()){
                String stateId = rs.getString("state_id");
                String stateName = rs.getString("state_name");
                String stateCode = rs.getString("state_code");
                String zipCodeFrom = rs.getString("zip_code_from");
                String zipCodeTo = rs.getString("zip_code_to");
                String tempStateDetail[] = {stateId, stateName, stateCode, zipCodeFrom, zipCodeTo };
                lst.add(tempStateDetail);
            }
  
            rs.close();
            prepStmt.close();
            releaseConnection();
            Debug.print("EventsDAO.selectStateByAreaId():" + lst);
        } 
        catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in EventsDAO.selectStateByAreaId():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in EventsDAO.selectStateByAreaId():" + e.getMessage());
        }
        return lst;
    }  
    
/**
  * Name         :selectContactListBasedOnChar
  * Description  :This method will display the updateEventRegType
  * @ param      :specificationId,userTypeId,eventRegistrationTypeId,dueDatePrice,aftDueDatePrice
  * @return      :boolean
  * @throws      :SQLException
  */   
    public Vector selectContactListBasedOnChar(String ch) throws SQLException {
        Debug.print("Inside the selectContactListId  userId : "+userId);
        this.userId =userId;
        ch = ch.trim()+"%";
        Vector vObj = new Vector();
        try {
            makeConnection();
            String selectStatement = "SELECT contactlist_id,first_name,middle_name, last_name,nick_name, email_id" +
                    "  FROM  " +DBHelper.USEA_MSG_CONTACT_LIST_MASTER+
                    " WHERE first_name LIKE ?";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, ch.trim());
            rs = prepStmt.executeQuery();
            while (rs.next()) {
                this.contactlistId = rs.getString(1);
                Debug.print(" contactlistId : "+contactlistId);
                this.firstName = rs.getString(2);
                Debug.print(" firstName : "+firstName);
                this.middleName = rs.getString(3);
                this.lastName = rs.getString(4);
                Debug.print(" lastName : "+lastName);
                this.nickName = rs.getString(5);
                this.emailId = rs.getString(6);
                Debug.print(" emailId : "+emailId);
                
                String [] contact = {contactlistId,firstName,middleName,lastName,nickName, emailId};
               
                vObj.add(contact);
             } 
             prepStmt.close();
        } catch (SQLException sqe) {
            throw new EJBException(sqe);
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return vObj;
     }     
    
    public Vector selectDistribitionListName(String listName) throws SQLException {
        Debug.print("Inside the selectDistribitionListName  contactlistId : "+listName);
        this.listName =listName;
        Vector vObj = new Vector();
        try {
            makeConnection();
            String selectStatement = "SELECT A.list_id,A.list_name,A.contactlist_id,B.email_id,B.first_name, B.last_name FROM  " +
            DBHelper.USEA_MSS_DISTRIBUTION_LIST+" A, "+DBHelper.USEA_MSG_CONTACT_LIST_MASTER+" B "+
            " where A.contactlist_id = B.contactlist_id AND list_name = ?";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, listName);
            rs = prepStmt.executeQuery();
            while (rs.next()) {
                this.listId = rs.getString(1);
                this.listName = rs.getString(2);
                this.contactlistId = rs.getString(3);
                this.emailId = rs.getString(4);
                this.firstName = rs.getString(5);
                this.lastName = rs.getString(6);
                String list[] = {listId,listName,contactlistId,emailId,firstName,lastName};
                vObj.add(list);
             } 
             prepStmt.close();
        } catch (SQLException sqe) {
            releaseConnection();
            throw new EJBException(sqe);
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return vObj;
    }
    
/**
  * This method is used get the conact details with given offset
  *@param - offset - start index of the record 
  *@param rowSize - how many rows do you want to show per page
  *@paramsortableName - Default Sortable name or order name
  * return the list of conact details.
*/

    public HLCMsgPaginationVO selectContactDetails(String sortableName,int offset, int rowSize, String userId) {

        List contactListDetails =  new ArrayList(); 

        HLCMsgContactListMaster objConactDetails = null;
        HLCMsgPaginationVO pageVO = null;
        Debug.print("userId :"+userId);
        
        try {
            makeConnection();
     
            
            
            
            String countQuery = "SELECT count(1) as total_records FROM  tblMsgContactListMaster where user_id = '" +userId+"'";
            if(sortableName!=null && sortableName.trim().length()>0){
        countQuery += "  and (first_name like '"+sortableName+"%' or "+
		" middle_name like '"+sortableName+"%' or last_name like '"+sortableName+"%')" ;
        
        };                  
     
      
           Debug.print("Contact Details Count Query :\n"+countQuery);
           Statement stmt1 = con.createStatement();       
           ResultSet rs1 = stmt1.executeQuery(countQuery);
           String totalRecords = "0";
           if (rs1.next()){
              totalRecords = rs1.getString("total_records") ;
           }
           rs1.close();
           stmt1.close();
        String query = 	" SELECT Row,contactlist_id,first_name,last_name,middle_name,nick_name ,email_id FROM (SELECT ROW_NUMBER()  "+
        " OVER (ORDER BY first_name ASC)AS Row,contactlist_id,first_name,middle_name,last_name,nick_name ,email_id from "+ 
	    " tblMsgContactListMaster where user_id = '"+userId+"' ) as RowNumbersObject  "+
        " WHERE Row between "+offset+" AND "+(offset+rowSize);
        if(sortableName!=null && sortableName.trim().length()>0){
        query += " and (first_name like '"+sortableName+"%' or "+
		" middle_name like '"+sortableName+"%' or last_name like '"+sortableName+"%')" ;
        
        };

        pageVO = new HLCMsgPaginationVO();
        pageVO.setTotalRecords(totalRecords);
        // log the query with parameters 
        Debug.print("Contact Details Query :\n"+query);

        //call the prepared statement 
        Statement stmt = con.createStatement();       
        rs = stmt.executeQuery(query);
        // iterate for the records and create the object and store into the list as ValueObject 
  
        while(rs.next()) {

            objConactDetails = new HLCMsgContactListMaster ();
            objConactDetails.setContactlistId(rs.getString("contactlist_id"));
            objConactDetails.setFirstName(rs.getString("first_name"));
            objConactDetails.setLastName(rs.getString("last_name"));
            objConactDetails.setMiddleName(rs.getString("middle_name"));
            objConactDetails.setNickName(rs.getString("nick_name"));
            objConactDetails.setEmailId(rs.getString("email_id"));


            contactListDetails.add(objConactDetails );

            }// while loop end
        pageVO.setResults(contactListDetails);
        
        rs.close();
        stmt.close();
        Debug.print("Result "+pageVO);
        }catch(Exception e){
            releaseConnection();
            Debug.print("Error while retrieving Records : "+e.getMessage());
        }finally {
            releaseConnection();
        }

  return pageVO;

}


    
/**
  * Name         :selectDistribitionListNameAndNoOfMembers
  * Description  :This method will list the distribution list and no of member based on user_id
  * @ param      : list name
  * @return      :Vector
  * @throws      :SQLException
  */          
    public Vector selectDistribitionListNameAndNoOfMembers(String userId) throws SQLException {
        Debug.print("Inside the selectDistribitionListName  contactlistId : "+listName);
        this.listName =listName;
        Vector vObj = new Vector();
        try {
            makeConnection();
            String selectStatement = "select A.list_name, count(A.contactlist_id) as no_of_users from "+
            DBHelper.USEA_MSS_DISTRIBUTION_LIST+" A, "+DBHelper.USEA_MSG_CONTACT_LIST_MASTER+" B "+
            "WHERE A.contactlist_id = B.contactlist_id AND "+
            " B.user_id = ? group by A.list_name";
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, userId);
            rs = prepStmt.executeQuery();
            while (rs.next()) {
                this.listName = rs.getString(1);
                String noOfUser = rs.getString(2);
                
                String list[] = {listName,noOfUser};
                vObj.add(list);
             } 
             prepStmt.close();
        } catch (SQLException sqe) {
            releaseConnection();
            throw new EJBException(sqe);
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return vObj;
    }
    
/**
  * Name         :selectAddressBook
  * Description  :This method will list the distribution list and no of member based on user_id
  * @ param      : list name
  * @return      :Vector
  * @throws      :SQLException
  */          
    public Vector selectAddressBook(String userId) throws SQLException {
        Debug.print("Inside the selectDistribitionListName  contactlistId : "+listName);
        this.listName =listName;
        Vector vObj = new Vector();
        try {
            makeConnection();
            String selectStatement = "SELECT contactlist_id,first_name,middle_name,last_name FROM  " +DBHelper.USEA_MSG_CONTACT_LIST_MASTER+
                    " where user_id = ?";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, userId);
            rs = prepStmt.executeQuery();
            while (rs.next()) {
                this.contactlistId = rs.getString(1);
                this.firstName = rs.getString(2);
                this.middleName = rs.getString(3);
                this.lastName = rs.getString(4);
                
                String list[] = {contactlistId,firstName,middleName,lastName};
                vObj.add(list);
             } 
             prepStmt.close();
        } catch (SQLException sqe) {
            releaseConnection();
            throw new EJBException(sqe);
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return vObj;
    }    
    
/**
  * Name         :selectMemberAdded
  * Description  :This method will list the distribution list and no of member based on user_id
  * @ param      : list name
  * @return      :Vector
  * @throws      :SQLException
  */          
    public Vector selectMemberAdded(String listName) throws SQLException {
        Debug.print("Inside the selectDistribitionListName  contactlistId : "+listName);
        this.listName =listName;
        Vector vObj = new Vector();
        try {
            makeConnection();
            String selectStatement = "select A.contactlist_id, B.first_name, B.middle_name, B.last_name,B.email_id,A.list_id FROM "+
            DBHelper.USEA_MSS_DISTRIBUTION_LIST+" A, "+DBHelper.USEA_MSG_CONTACT_LIST_MASTER+" B "+
            "where A.list_name = ? and A.contactlist_id = B.contactlist_id ";
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, listName);
            rs = prepStmt.executeQuery();
            while (rs.next()) {
                this.contactlistId = rs.getString(1);
                this.firstName = rs.getString(2);
                this.middleName = rs.getString(3);
                this.lastName = rs.getString(4);
                this.emailId = rs.getString(5);
                this.listId = rs.getString(6);
                
                String list[] = {contactlistId,firstName,middleName,lastName,emailId,listId};
                vObj.add(list);
             } 
             prepStmt.close();
        } catch (SQLException sqe) {
            releaseConnection();
            throw new EJBException(sqe);
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return vObj;
    }     
    
    public boolean deleteDistMasterBasedOnDistName(String listName) throws SQLException {
        try {
            makeConnection();
            //USEA_MSS_DISTRIBUTION_LIST
            System.out.println("Inside ejbRemove Contact List Name :  "+listName);
            String deleteStatement = "DELETE FROM "+DBHelper.USEA_MSS_DISTRIBUTION_LIST +" WHERE list_name = ? ";
            PreparedStatement prepStmt = con.prepareStatement(deleteStatement);
            prepStmt.setString(1, listName.trim());
            int cnt = prepStmt.executeUpdate();
            Debug.print("Successfully Delete the USEA_MSS_DISTRIBUTION_LIST record : "+cnt);

            prepStmt.close();
        } catch (Exception ex) {
            releaseConnection();
            throw new EJBException("MessageEntityBean ejbRemove: " + ex.getMessage());
        }finally{
            releaseConnection();
        } 
        return true;
    }
    
    public boolean deleteDistMasterBasedOnContactId(String contactId) throws SQLException {
        try {
            makeConnection();
            //USEA_MSS_DISTRIBUTION_LIST
            System.out.println("Inside deleteDistMasterBasedOnContactId :  "+contactId);
            String deleteStatement = "DELETE FROM "+DBHelper.USEA_MSS_DISTRIBUTION_LIST +" WHERE contactlist_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(deleteStatement);
            prepStmt.setString(1, contactId);
            int cnt = prepStmt.executeUpdate();
            Debug.print("Successfully Delete the USEA_MSS_DISTRIBUTION_LIST record : "+cnt);

            prepStmt.close();
        } catch (Exception ex) {
            releaseConnection();
            throw new EJBException("MessageEntityBean delete: " + ex.getMessage());
        }finally{
            releaseConnection();
        } 
        return true;
    }
    /*
     *Method Name  : updatecontactListOnContactId
     *Description  : Added this method for updating the contact details of the address book 
     *Param Names  : object of MsgContactListMaster 
     *Return Value : boolean
     */ 
    
       public boolean updatecontactListOnContactId(HLCMsgContactListMaster objContactList) throws SQLException {
        try {
            makeConnection();
            //USEA_MSS_DISTRIBUTION_LIST
            System.out.println("Inside updatecontactListOnContactId :  "+objContactList.getContactlistId());
            String deleteStatement = "update "+DBHelper.USEA_MSG_CONTACT_LIST_MASTER +" set first_name = ?, middle_name = ?, last_name = ?, nick_name = ?, email_id = ?," +
                                     "alternate_email_id = ?, phone_no = ?, mobile_no = ?, fax_no = ?, street = ?, city = ?, state = ?, country = ?, zip = ? WHERE contactlist_id = ? ";
            
            PreparedStatement prepStmt = con.prepareStatement(deleteStatement);

            prepStmt.setString(1, objContactList.getFirstName());
            prepStmt.setString(2, objContactList.getMiddleName());
            prepStmt.setString(3, objContactList.getLastName());
            prepStmt.setString(4, objContactList.getNickName());
            prepStmt.setString(5, objContactList.getEmailId());
            prepStmt.setString(6, objContactList.getAlternateEmailId());
            prepStmt.setString(7, objContactList.getPhoneNo());
            prepStmt.setString(8, objContactList.getMobileNo());            
            prepStmt.setString(9, objContactList.getFaxNo());
            prepStmt.setString(10, objContactList.getStreet());
            prepStmt.setString(11, objContactList.getCity());
            prepStmt.setString(12, objContactList.getState());            
            prepStmt.setString(13, objContactList.getCountry());
            prepStmt.setString(14, objContactList.getZip());            
            prepStmt.setString(15, objContactList.getContactlistId());                        
            
            int cnt = prepStmt.executeUpdate();
            
            Debug.print("Successfully Delete the USEA_MSS_DISTRIBUTION_LIST record : "+cnt);

            prepStmt.close();
        } catch (Exception ex) {
            releaseConnection();
            throw new EJBException("MessageEntityBean delete: " + ex.getMessage());
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
