/*
 * MsgDataBaseDAO.java
 *
 * Created on October 10, 2006, 5:42 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmsg.DAO;
import com.hlcmsg.util.HLCDBDetailVO;
import com.hlcmsg.util.DBHelper;
import com.hlcmsg.util.Debug;
import com.hlcmsg.util.HLCUserMaster;
import com.hlcmsg.util.HLClMsgDBRequestDetailsVO;
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
public class HLCMsgDataBaseDAO {
    private static final String dbName = "java:/ELMTMSSQLDS";
    private Connection con = null;
    private PreparedStatement prepStmt = null;
    private ResultSet rs = null;
    private PreparedStatement prepStmt1 = null;
    private ResultSet rs1 = null;
    private ResultSet rs2 = null;
    
    private String membershipTypeId = null;
    private String membershipTypeName = null;
    private String userTypeId = null;
    private String membershipAmount = null;
    private String statusId = null;
    private String statusName = null;
    private String userId = null;
    private String searchPattern = null;
    private String filePath = null;
    
    /** Creates a new instance of MsgDataBaseDAO */
    public HLCMsgDataBaseDAO() {
    }
    
/**
  * Name         :displayMembershipTypeDetails 
  * Description  :This method will list the member type id and member type name
  * @ param      :none
  * @return      :List
  * @throws      :SQLException
  */       
    public boolean addMsgDBRequestDetails(HLClMsgDBRequestDetailsVO objDBRD) throws SQLException {

        try {
            makeConnection();
             String insertStatement = "insert into " + DBHelper.USEA_MSG_DBREQUEST_DETAIL+ 
             " (user_id,no_of_records,total_amount,file_path,comments,search_pattern) " +
             " values ( ? , ?, ?, ?, ? ) ";
            prepStmt = con.prepareStatement(insertStatement);
            System.out.println("Inside the MsgDBRequestDetails ....\n\n ");
            prepStmt.setString(1, objDBRD.getUserId());
            prepStmt.setInt(2, Integer.parseInt(objDBRD.getNoOfRecords()));
            prepStmt.setDouble(3, Double.parseDouble(objDBRD.getTotalAmount()));
            prepStmt.setString(4, objDBRD.getFilePath());
            prepStmt.setString(5, objDBRD.getComments());
            prepStmt.setString(5, objDBRD.getSearchPattern());
            Debug.print(" Before Insertion ...\n"+objDBRD);
            int cnt = prepStmt.executeUpdate();
            System.out.println("Succefully inserted :  "+cnt);
            prepStmt.close();
        } catch (SQLException sqe) {
            releaseConnection();
            throw new EJBException(sqe);
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
       return true;                  
    } //updateRequestStatus(dbReqId)
    
/**
  * Name         :updateDBRequest 
  * Description  :This method will update dbRequest Table based on deReqId
  * @ param      :dbReqId, status, comments
  * @return      :boolean
  * @throws      :SQLException
  */       
    public boolean updateDBRequest(String  dbReqId, String status, String comments) throws SQLException {

        try {
            makeConnection();
             String insertStatement = "UPDATE " + DBHelper.USEA_MSG_DBREQUEST_DETAIL+ 
             " SET request_status = ?, comments = ? WHERE db_request_id = ? ";
            
            prepStmt = con.prepareStatement(insertStatement);
            
            prepStmt.setString(1, status);
            prepStmt.setString(2, comments);
            prepStmt.setString(3, dbReqId);

            int cnt = prepStmt.executeUpdate();
            System.out.println("Successfully Updated DBRequest :  "+cnt);
            prepStmt.close();
        } catch (SQLException sqe) {
            releaseConnection();
            throw new EJBException(sqe);
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
       return true;                  
    }    
/**
  * Name         :displayAdminMSGDataBase 
  * Description  :This method will list all the Databse request for admin
  * @ param      :none
  * @return      :List containing object of lMsgDBRequestDetailsVO
  * @throws      :SQLException
  */       
    public List displayAdminMSGDataBase() throws SQLException {
        HLClMsgDBRequestDetailsVO objDBRD = null;
        List vObj = new ArrayList();
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
        
        try {
            makeConnection();
            String selectStatement =  
            "SELECT B.first_name,B.middle_name,B.last_name,A.db_request_id,A.user_id,A.no_of_records, A.total_amount,A.add_date FROM  " +
               DBHelper.USEA_MSG_DBREQUEST_DETAIL+" A, "+DBHelper.USEA_MMS_USERMASTER+" B  WHERE A.user_id = B.user_id AND A.active_status = 'True'";
            prepStmt = con.prepareStatement(selectStatement);
            rs = prepStmt.executeQuery();
            while (rs.next()) {
                objDBRD = new HLClMsgDBRequestDetailsVO();
                objDBRD.setFirstName(rs.getString("first_name"));
                objDBRD.setMiddleName(rs.getString("middle_name"));
                objDBRD.setLastName(rs.getString("last_name"));
                objDBRD.setDbRequestId(rs.getString("db_request_id"));
                objDBRD.setUserId(rs.getString("user_id"));
                objDBRD.setNoOfRecords(rs.getString("no_of_records"));
                objDBRD.setTotalAmount(rs.getString("total_amount"));
                Date dateReq = rs.getDate("add_date");
                String dateOfReq = formatter.format(dateReq);
                objDBRD.setAddDate(dateOfReq);
                
                vObj.add(objDBRD);
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
  * Name         :displayMSGDataBase 
  * Description  :This method will list the Databse request based on user Id
  * @ param      :user id
  * @return      :List containing object of lMsgDBRequestDetailsVO
  * @throws      :SQLException
  */       
    public List displayMSGDataBase(String userId) throws SQLException {
        HLClMsgDBRequestDetailsVO objDBRD = null;
        List vObj = new ArrayList();
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
        
        try {
            makeConnection();
            String selectStatement =  "SELECT A.db_request_id,A.user_id,A.no_of_records, A.total_amount,A.add_date,B.first_name,B.middle_name,B.last_name FROM  " +
               DBHelper.USEA_MSG_DBREQUEST_DETAIL+" A, "+DBHelper.USEA_MMS_USERMASTER+" B "+
               " WHERE A.user_id = B.user_id  AND A.user_id = '"+userId+"' AND A.active_status = 'True' ";
            prepStmt = con.prepareStatement(selectStatement);
            rs = prepStmt.executeQuery();
            while (rs.next()) {
                objDBRD = new HLClMsgDBRequestDetailsVO();
                objDBRD.setDbRequestId(rs.getString("db_request_id"));
                objDBRD.setUserId(rs.getString("user_id"));
                objDBRD.setNoOfRecords(rs.getString("no_of_records"));
                objDBRD.setTotalAmount(rs.getString("total_amount"));
                Date dateReq = rs.getDate("add_date");
                objDBRD.setFirstName(rs.getString("first_name"));
                objDBRD.setMiddleName(rs.getString("middle_name"));
                objDBRD.setLastName(rs.getString("last_name"));
                
                String dateOfReq = formatter.format(dateReq);
                objDBRD.setAddDate(dateOfReq);
                
                vObj.add(objDBRD);
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
  * Name         :displayMSGDataBaseDetail 
  * Description  :This method will list the Databse request based on user Id
  * @ param      :db_request_id
  * @return      :lMsgDBRequestDetailsVO
  * @throws      :SQLException
  */       
    public HLClMsgDBRequestDetailsVO displayMSGDataBaseDetail(String dbReqId) throws SQLException {
        HLClMsgDBRequestDetailsVO objDBRD = null;
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
        try {
            makeConnection();
            String selectStatement =  "SELECT A.db_request_id,A.file_path,A.user_id,A.no_of_records, A.total_amount,A.add_date,A.request_status,A.comments, B.first_name,B.middle_name,B.last_name FROM  " +
               DBHelper.USEA_MSG_DBREQUEST_DETAIL+" A, "+DBHelper.USEA_MMS_USERMASTER+" B "+
                " WHERE A.db_request_id = '"+dbReqId+"' AND A.active_status = 'True' ";
            prepStmt = con.prepareStatement(selectStatement);
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                objDBRD = new HLClMsgDBRequestDetailsVO();
                objDBRD.setFirstName(rs.getString("first_name"));
                objDBRD.setMiddleName(rs.getString("middle_name"));
                objDBRD.setLastName(rs.getString("last_name"));
                objDBRD.setDbRequestId(rs.getString("db_request_id"));
                objDBRD.setFilePath(rs.getString("file_path"));
                objDBRD.setRequestStatus(rs.getString("request_status"));
                objDBRD.setComments(rs.getString("comments"));
                objDBRD.setUserId(rs.getString("user_id"));
                objDBRD.setNoOfRecords(rs.getString("no_of_records"));
                objDBRD.setTotalAmount(rs.getString("total_amount"));
                Date dateReq = rs.getDate("add_date");
                String dateOfReq = formatter.format(dateReq);
                objDBRD.setAddDate(dateOfReq);
             } 
             prepStmt.close();
        } catch (SQLException sqe) {
            throw new EJBException(sqe);
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
       return objDBRD;                  
    }     
/**
  * Name         :displayMembershipTypeDetails 
  * Description  :This method will list the member type id and member type name
  * @ param      :none
  * @return      :List
  * @throws      :SQLException
  */       
    public List displayMembershipTypeDetails() throws SQLException {

        List vObj = new ArrayList();
        try {
            makeConnection();
            String selectStatement = "SELECT membership_type_id, membership_type_name FROM  " +DBHelper.USEA_MEMBER_TYPE_MASTER;
            prepStmt = con.prepareStatement(selectStatement);
            rs = prepStmt.executeQuery();
            while (rs.next()) {
                membershipTypeId = rs.getString("membership_type_id");
                membershipTypeName = rs.getString("membership_type_name");
                String memberTypeDet[] = {membershipTypeId,membershipTypeName};
                vObj.add(memberTypeDet);
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
  * Name         :displayMembershipTypeDetails 
  * Description  :This method will list the member type id and member type name
  * @ param      :none
  * @return      :List
  * @throws      :SQLException
  */       
    public List displayMembershipStatus() throws SQLException {

        List vObj = new ArrayList();
        try {
            makeConnection();
            String selectStatement = "SELECT status_id, status_name FROM  " +DBHelper.USEA_MEMBERSHIP_STATUS;
            prepStmt = con.prepareStatement(selectStatement);
            rs = prepStmt.executeQuery();
            while (rs.next()) {
                statusId = rs.getString("status_id");
                statusName = rs.getString("status_name");
                String memberTypeDet[] = {statusId,statusName};
                vObj.add(memberTypeDet);
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
  * Name         :displayMembershipTypeDetails 
  * Description  :This method will list the member type id and member type name
  * @ param      :none
  * @return      :List
  * @throws      :SQLException
  */       
    public List displayArea() throws SQLException {

        List vObj = new ArrayList();
        try {
            makeConnection();
            String selectStatement = "SELECT status_id, status_name FROM  " +DBHelper.USEA_MEMBERSHIP_STATUS;
            prepStmt = con.prepareStatement(selectStatement);
            rs = prepStmt.executeQuery();
            while (rs.next()) {
                statusId = rs.getString("status_id");
                statusName = rs.getString("status_name");
                String memberTypeDet[] = {statusId,statusName};
                vObj.add(memberTypeDet);
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
  * Name         :getSearchAndListDBAdd 
  * Description  :This method will list the first name, last name, address and city, user id based on the condition
  * @ param      :fromZipCode,toZipCode, memberTypeId, statusId
  * @return      :List
  * @throws      :SQLException
  */          
    public List getSearchAndListDBAddress(String fromZipCode, String toZipCode, String memberTypeId, String statusId) throws SQLException {
        Debug.print("Inside the Search & List Database Addresses ... ");
        HLCDBDetailVO objVO = null;
        List lst = new ArrayList();
        try {
            makeConnection();

            String stmtQuery = "select  A.user_id, A.first_name, A.last_name, B.address1, B.city, B.zip "+
            " FROM tblUserMaster A, tblContactDetails B, tblMemberDetails C ";
           // " tblMembershipTypeMaster D, tblMembershipStatusMaster E "+
          //  " where A.user_id = C.user_id  and B.user_id = A.user_id and "+
          //  " A.contact_type_id = B.contact_type_id and " +
          //  "CAST(B.zip as int) >= '"+fromZipCode+"' and CAST(B.zip as int) <= '"+toZipCode+"'" ;
            if ((memberTypeId.equals("") && memberTypeId.length() == 0) && (statusId.equals("") && statusId.length() == 0) ){
                stmtQuery += " where A.user_id = C.user_id  and B.user_id = A.user_id and "+
                                " A.contact_type_id = B.contact_type_id and " +
                                "CAST(B.zip as int) >= '"+fromZipCode+"' and CAST(B.zip as int) <= '"+toZipCode+"' AND A.active_status = 'True'" ;
            }
            if ((memberTypeId != null && memberTypeId.length() > 0) && (statusId != null && statusId.length() > 0) ){
                stmtQuery += " ,tblMembershipTypeMaster D, tblMembershipStatusMaster E ";
                stmtQuery += " where A.user_id = C.user_id  and B.user_id = A.user_id and "+
                            " A.contact_type_id = B.contact_type_id and " +
                            "CAST(B.zip as int) >= '"+fromZipCode+"' and CAST(B.zip as int) <= '"+toZipCode+"'" ;
                stmtQuery += "  AND C.membership_type_id = D.membership_type_id AND C.membership_type_id = '"+memberTypeId+"'";
                stmtQuery +="  AND C.status_id = E.status_id and C.status_id = '"+statusId+"' AND A.active_status = 'True' ";
            }
            else {
                if (memberTypeId != null && memberTypeId.length() > 0){
                    stmtQuery += " ,tblMembershipTypeMaster D ";
                    stmtQuery += " where A.user_id = C.user_id  and B.user_id = A.user_id and "+
                                " A.contact_type_id = B.contact_type_id and " +
                                "CAST(B.zip as int) >= '"+fromZipCode+"' and CAST(B.zip as int) <= '"+toZipCode+"'" ;
                    stmtQuery += "  AND C.membership_type_id = D.membership_type_id AND C.membership_type_id = '"+memberTypeId+"' AND A.active_status = 'True' ";
                }
                if (statusId != null && statusId.length() > 0) {
                    stmtQuery += " , tblMembershipStatusMaster E ";
                    stmtQuery += " where A.user_id = C.user_id  and B.user_id = A.user_id and "+
                                " A.contact_type_id = B.contact_type_id and " +
                                "CAST(B.zip as int) >= '"+fromZipCode+"' and CAST(B.zip as int) <= '"+toZipCode+"'" ;
                    stmtQuery +="  AND C.status_id = E.status_id and C.status_id = '"+statusId+"' AND A.active_status = 'True' ";
                }
            }
            
            Debug.print(" Query is : \n"+stmtQuery);

            Statement stmt1 = con.createStatement();       
            rs = stmt1.executeQuery(stmtQuery);
            int totalRecords = 0;
            while(rs.next()){
                totalRecords++;
                objVO = new HLCDBDetailVO();
                objVO.setUserId(rs.getString("user_id"));
                objVO.setFirstName(rs.getString("first_name"));
                objVO.setLastName(rs.getString("last_name"));
                objVO.setAddress(rs.getString("address1"));
                objVO.setCity(rs.getString("city"));
                objVO.setZip(rs.getString("Zip"));
               
                objVO.setTotRec(String.valueOf(totalRecords));
                lst.add(objVO);
            }
            stmt1.close();
            releaseConnection();
        } catch (SQLException sqe) {
            releaseConnection();
            throw new EJBException(sqe);
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return lst;
    }     
    
    public List getUserDetails(String[] dbReqId) throws SQLException { //String[] dbReqId
        boolean flag = true;
        HLCUserMaster objUser = new HLCUserMaster();
        List lst = new ArrayList();
        Map map = null;    
        
        try {
            makeConnection();
            for(int i=0;i<dbReqId.length; i++ ) {
            /*=============================get the pattern from the mensioned query===============================*/
            
           /* String str = "SELECT file_path, user_id, search_pattern FROM  " +
              DBHelper.USEA_MSG_DBREQUEST_DETAIL+" WHERE db_request_id in ("+getAsString(dbReqId)+") ";*/
            String str = "SELECT file_path, user_id, search_pattern FROM  " +
              DBHelper.USEA_MSG_DBREQUEST_DETAIL+" WHERE db_request_id = '"+dbReqId[i]+"' ";
            prepStmt = con.prepareStatement(str);
           // prepStmt.setString(1, dbReqId);
           Debug.print(" The Pattern Query is : \n"+str);
            rs = prepStmt.executeQuery();
            if (rs.next()){
                this.userId = rs.getString("user_id");
                this.searchPattern = rs.getString("search_pattern");
                this.filePath = rs.getString("file_path");
            }
            Debug.print(" Search pattern  : "+searchPattern);
            /*==========================Generate the user id based on the search pattern====================*/
            StringBuffer buff = new StringBuffer();
            String zipCode = null;
            boolean bol = false;
            String searchStr[] = searchPattern.split(",");
            //zipcode=222-777,memberType=3333XXX,memberStatus=yHHHHHH333
            //zipcode=222-777,memberType=3333XXX
            //zipcode=222-777,memberStatus=yHHHHHH333
            String fromZipCode = null;
            String toZipCode = null;
            
           /* for (int i = 0; i < searchStr.length; i++) {
                if ("zipCode".equalsIgnoreCase(searchStr[i])){
                String ZipCode[] = searchStr[0].split("-");
                 fromZipCode = ZipCode[0];
                 toZipCode = ZipCode[1];
                zipCode = "WHERE A.user_id = C.user_id  and B.user_id = A.user_id and "+
                                    " A.contact_type_id = B.contact_type_id and " +
                                    "CAST(B.zip as int) >= '"+fromZipCode+"' and CAST(B.zip as int) <= '"
                        +toZipCode+"' AND A.active_status = 'True'" ;  
                
                }else if ("memberType".equalsIgnoreCase(searchStr[i])){
                    //only member type id
                    
                    bol = true;
                }else if ("memberStatus".equalsIgnoreCase(searchStr[i])){
                 if(bol){
                    // memberType id and status id present
                 } else {
                   //status id only present
                 }
                }
            }*/
          
            String ZipCode[] = searchStr[0].split("-");
                 fromZipCode = ZipCode[0];
                 toZipCode = ZipCode[1];
            String memberTypeId = "";
            String statusId = "";
            if (searchStr.length == 2){
                 memberTypeId = searchStr[1];
            }else if (searchStr.length == 3){
                memberTypeId = searchStr[1];
                statusId = searchStr[2];
            }
            
          //  Debug.print("  "+fromZipCode+"  "+toZipCode+"   "+memberTypeId+"  "+statusId);
                        
            String stmtQuery = "select  A.user_id, A.first_name, A.last_name, B.address1, B.city, B.zip "+
            " FROM tblUserMaster A, tblContactDetails B, tblMemberDetails C ";

            if ((memberTypeId.equals("") && memberTypeId.length() == 0) && (statusId.equals("") && statusId.length() == 0) ){
                stmtQuery = "WHERE A.user_id = C.user_id  and B.user_id = A.user_id and "+
                                    " A.contact_type_id = B.contact_type_id and " +
                                    "CAST(B.zip as int) >= '"+Integer.parseInt(fromZipCode)+"' and CAST(B.zip as int) <= '"
                        +Integer.parseInt(toZipCode)+"' AND A.active_status = 'True'" ;  
            }
            if ((memberTypeId != null && memberTypeId.length() > 0) && (statusId != null && statusId.length() > 0) ){
                stmtQuery += " ,tblMembershipTypeMaster D, tblMembershipStatusMaster E ";
                stmtQuery += " where A.user_id = C.user_id  and B.user_id = A.user_id and "+
                            " A.contact_type_id = B.contact_type_id and " +
                            "CAST(B.zip as int) >= '"+Integer.parseInt(fromZipCode)+"' and CAST(B.zip as int) <= '"+Integer.parseInt(toZipCode)+"'" ;
                stmtQuery += "  AND C.membership_type_id = D.membership_type_id AND C.membership_type_id = '"+memberTypeId+"'";
                stmtQuery +="  AND C.status_id = E.status_id and C.status_id = '"+statusId+"' AND A.active_status = 'True' ";
            }
            else {
                if (memberTypeId != null && memberTypeId.length() > 0){
                    stmtQuery += " ,tblMembershipTypeMaster D ";
                    stmtQuery += " where A.user_id = C.user_id  and B.user_id = A.user_id and "+
                                " A.contact_type_id = B.contact_type_id and " +
                                "CAST(B.zip as int) >= '"+Integer.parseInt(fromZipCode)+"' and CAST(B.zip as int) <= '"+Integer.parseInt(toZipCode)+"'" ;
                    stmtQuery += "  AND C.membership_type_id = D.membership_type_id AND C.membership_type_id = '"+memberTypeId+"' AND A.active_status = 'True' ";
                }
                if (statusId != null && statusId.length() > 0) {
                    stmtQuery += " , tblMembershipStatusMaster E ";
                    stmtQuery += " where A.user_id = C.user_id  and B.user_id = A.user_id and "+
                                " A.contact_type_id = B.contact_type_id and " +
                                "CAST(B.zip as int) >= '"+Integer.parseInt(fromZipCode)+"' and CAST(B.zip as int) <= '"+Integer.parseInt(toZipCode)+"'" ;
                    stmtQuery +="  AND C.status_id = E.status_id and C.status_id = '"+statusId+"' AND A.active_status = 'True' ";
                }
            }
            
            Debug.print(" Query is : \n"+stmtQuery);

            Statement stmt1 = con.createStatement();       
            rs2 = stmt1.executeQuery(stmtQuery);
            Debug.print(" Result set value  is : "+rs2);

            
            while(rs2.next()){
                Debug.print(" Result set value  is : "+rs2);
              this.userId = rs2.getString("user_id");
                        Debug.print(" user id : "+userId);

                String selectStatement = 
                "SELECT A.first_name,A.middle_name,A.last_name,A.email_id ,B.address1,B.address2,B.city,B.state,B.country,B.zip,B.phone_no,B.mobile_no,B.fax_no FROM  "+
                DBHelper.USEA_MMS_USERMASTER+" A, "+DBHelper.USEA_CONTACT_DETAILS+" B WHERE A.user_id = B.user_id AND A.user_id = '"+userId+"'";
                prepStmt1 = con.prepareStatement(selectStatement);
               // prepStmt1.setString(1, userId);

                 Debug.print(" Select Query : \n"+selectStatement);
                rs1 = prepStmt1.executeQuery();
                if (rs1.next()){
                    map = new HashMap();
                    Debug.print("After hashMap:  "+userId);
                  
                    map.put("First Name", rs1.getString(1));
                    map.put("Middle Name", rs1.getString(2));
                    map.put("Last Name", rs1.getString(3));
                    map.put("Email Id", rs1.getString(4));
                    map.put("Address1", rs1.getString(5));
                    map.put("Address2", rs1.getString(6));
                    map.put("City", rs1.getString(7));
                    map.put("State", rs1.getString(8));
                    map.put("Country", rs1.getString(9));
                    map.put("Zip", rs1.getString(10));
                    map.put("Phone Number", rs1.getString(11));
                    map.put("Mobile Number", rs1.getString(12));
                    map.put("Fax Number", rs1.getString(13));
                    lst.add(map);
                    Debug.print(" At ened of map user id : "+userId);
                }
                
            }
            prepStmt.close();
            prepStmt1.close();
            }
        }catch (Exception e){
             releaseConnection();
             e.printStackTrace();
            Debug.print("Error in getUserDetails : "+e.getMessage());
        }finally {
            releaseConnection();
        }
        return lst;
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
 private String getAsString(String[] arr)  {
           if(arr==null)
               return null;       
         StringBuffer buffer = new StringBuffer();
         int len =  (arr.length-1);
         for (int i = 0; i < len; i++) {
             buffer.append("'"+arr[i]+"',");
         }
         if(arr.length>0)
            buffer.append("'"+arr[arr.length-1]+"'"); 
         //buffer.append();
      return buffer.toString();
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
