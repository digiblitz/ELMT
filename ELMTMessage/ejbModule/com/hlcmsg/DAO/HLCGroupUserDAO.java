/*
 * GroupUserDAO.java
 *
 * Created on October 3, 2006, 12:21 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmsg.DAO;
import com.hlcmsg.exception.HLCMessageException;
import com.hlcmsg.util.DBHelper;
import com.hlcmsg.util.Debug;
import com.hlcmsg.util.HLCGroupUser;
import com.hlcmsg.util.HLCUserMaster;
import java.text.DateFormat;
import java.text.ParseException;
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
public class HLCGroupUserDAO {
    private static final String dbName = "java:/ELMTMSSQLDS";
    private Connection con = null;
    private PreparedStatement prepStmt = null;
    private ResultSet rs = null;
    private PreparedStatement prepStmt1 = null;
    private ResultSet rs1 = null;
    
    private String invitationId = null;
    private String ownerId = null;
    private String groupId = null;
    private String groupName = null;
    private String userId = null;
    private String joinDate = null;
    private String activeStatus = null;
    private String contactTypeId = null;
            
    /** Creates a new instance of GroupUserDAO */
    public HLCGroupUserDAO() {
    }
    
/**
  * Name         :insertRowMsgGroupUser
  * Description  :This method will insert a record into the Message Group Users 
  * @ param      : Message Group User VO
  * @return      :void
  * @throws      :SQLException
  */        
    public boolean insertRowMsgGroupUser(HLCGroupUser objGrpUsr) throws SQLException {
        Debug.print("MessageGroupDAO insertRowMsgGroupMaster");
        //java.sql.Date dt = java.sql.Date.valueOf(dob);
       try {
            makeConnection();

            String insertStatement = 
              "insert into " + DBHelper.USEA_MSG_GROUPUSERS+ " (owner_id,group_id,user_id) " +
                    " values ( ? , ?, ? ) ";
            prepStmt = con.prepareStatement(insertStatement);
            System.out.println("Inside the MessageGroupDAO ....\n\n ");
            prepStmt.setString(1, objGrpUsr.getOwnerId());
            prepStmt.setString(2, objGrpUsr.getGroupId());
            prepStmt.setString(3, objGrpUsr.getUserId());
            //prepStmt.setString(4, objGrpUsr.getJoinDate());
            
            Debug.print("Inside the DAO \n"+objGrpUsr);

            int cnt = prepStmt.executeUpdate();
            System.out.println("Succefully inserted :  "+cnt);
            prepStmt.close();
       }catch(Exception e){
           releaseConnection();
           Debug.print("Error while inserting Msessage Group Users  "+e.getMessage());
       }finally {
            releaseConnection();
       }
        return true;
   }    

/**
  * Name         :getUserIdemailId
  * Description  :This method will list the distribution list and no of member based on user_id
  * @ param      :groupName
  * @return      :boolean
  * @throws      :SQLException
  */          
    public String getUserIdemailId(String emailId) throws SQLException {
        Debug.print("Inside the getUserIdemailId  : "+emailId);
        String userId = null;
        try {
            makeConnection();
            
            String selectStatement = "SELECT user_id FROM "+DBHelper.USEA_USER_MASTER+"  WHERE email_id = '"+emailId+"'";
            Debug.print(" Connection : "+con);
            Statement stmt1 = con.createStatement();       
            ResultSet rs1 = stmt1.executeQuery(selectStatement);
            Debug.print("\n "+selectStatement+" rs="+rs1);
            while(rs1.next()) {
                
                 userId = rs1.getString("user_id");
                 System.out.println("UserId ******:"+userId);
            }
            Debug.print(" USER ID : "+userId);
            stmt1.close();
        } catch (SQLException sqe) {
            releaseConnection();
            sqe.printStackTrace();
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return userId;
    }        
/**
  * Name         :isValidEmailInDAO
  * Description  :This method will list the distribution list and no of member based on user_id
  * @ param      :groupName
  * @return      :boolean
  * @throws      :SQLException
  */          
    public boolean isValidEmailInDAO(String emailId) throws SQLException {
        Debug.print("Inside the isValidEmail  : ");
        boolean bol = false;
        try {
            makeConnection();
            String countQuery = "select count(1) as total_records FROM "+DBHelper.USEA_MMS_USERMASTER+
            " WHERE email_id = '"+emailId.trim()+"' AND active_status = 'true'";
            Statement stmt1 = con.createStatement();       
            rs = stmt1.executeQuery(countQuery);
            int totalRecords = 0;
            if (rs.next()){
              totalRecords = rs.getInt("total_records") ;
            }
            if (totalRecords > 0)
                bol = true;
                        
            stmt1.close();
            
        } catch (SQLException sqe) {
            releaseConnection();
            throw new EJBException(sqe);
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return bol;
    }    

    
    
/**
  * Name         :isValidGroupName
  * Description  :This method will list the distribution list and no of member based on user_id
  * @ param      :groupName
  * @return      :boolean
  * @throws      :SQLException
  */          
     public boolean isValidGroupName(String groupName) throws SQLException {
        Debug.print("Inside the isValidGroupName  : ");
        boolean bol = false;
        try {
            makeConnection();
            String countQuery = "select count(1) as total_records FROM "+DBHelper.USEA_MSG_GROUPMASTER+
            " WHERE group_name ='"+groupName+"'";
            Statement stmt1 = con.createStatement();       
            rs = stmt1.executeQuery(countQuery);
            int count = 0;
            if (rs.next()){              
              count = rs.getInt("total_records") ;
            }            
            bol=(count>0)?true:false;                         
            stmt1.close();
        } catch (SQLException sqe) {
            releaseConnection();
            throw new EJBException(sqe);
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return bol;
    }        
/**
  * Name         :selectGroupName
  * Description  :This method will list the group id and group name based on user_id
  * @ param      :userId
  * @return      :List
  * @throws      :SQLException
  */          
    public List selectGroupName(String userId ) throws SQLException {
        Debug.print("Inside the selectGroupName  : ");
       List list = new ArrayList();
        try {
            makeConnection();
            String countQuery = "select group_id, group_name FROM "+DBHelper.USEA_MSG_GROUPMASTER+
            " WHERE user_id = '"+userId+"' AND active_status = 'true'";
            Statement stmt1 = con.createStatement();       
            rs = stmt1.executeQuery(countQuery);
            String totalRecords = "0";
            while (rs.next()){
              this.groupId = rs.getString("group_id");
              this.groupName = rs.getString("group_name");
              String groupDet[] = {groupId,groupName};
              list.add(groupDet);
              
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
        return list;
    }      
    
/**
  * Name         :selectUserDetail
  * Description  :This method will list the group id and group name based on user_id
  * @ param      :userId
  * @return      :List
  * @throws      :SQLException
  */          
    public HLCUserMaster selectUserDetail(String email ) throws SQLException {
        HLCUserMaster objUser = new HLCUserMaster();
       List list = new ArrayList();
        try {
            makeConnection();
            String countQuery = 
             "select user_id,first_name,middle_name,last_name,email_id, password FROM "+DBHelper.USEA_USER_MASTER+
            " WHERE email_id = '"+email+"'";
            Statement stmt1 = con.createStatement();       
            rs = stmt1.executeQuery(countQuery);
            String totalRecords = "0";
            if (rs.next()){
             objUser.setUserId(rs.getString("user_id"));
             objUser.setUserId(rs.getString("first_name"));
             objUser.setUserId(rs.getString("middle_name"));
             objUser.setUserId(rs.getString("last_name"));
             objUser.setUserId(rs.getString("email_id"));
             objUser.setUserId(rs.getString("password"));
             
              
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
        return objUser;
    }     
    
     public boolean insertUserDetails(HLCUserMaster objUser) throws SQLException {
        Debug.print("GroupUserDAO insertUserDetails");
        java.sql.Date jsqlD =  new java.sql.Date( new Date().getTime() );
       // horseId = (String) context.getPrimhorseIdaryKey();
        String userCode = null;
        String userTypeId = null;
      try {
        makeConnection();
        if (objUser.getUserTypeName().startsWith("USEA Staff")) {
                try {
                    userCode = DBHelper.getNextUserCode(con);
                   
                }catch (Exception e){
                    //e.printStackTrace();
                    Debug.print("Error In user code ID : "+e.getMessage());
                }
        }
                try {

                   contactTypeId = DBHelper.getContacttypeId(con,objUser.getContactType() );
                    userTypeId =DBHelper.getUserTypeId(con, objUser.getUserTypeName());
                }catch (Exception e){
                    //e.printStackTrace();
                    Debug.print("Error In user code ID : "+e.getMessage());
                }
        Debug.print("GroupUserDAO insertUserDetails "+userCode);
        objUser.setUserCode(userCode);
        objUser.setContactTypeId(contactTypeId);
        objUser.setUserTypeId(userTypeId);
        //Date dob = (Date)objUser.getDob();
        
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
            try {
                 date = (Date)formatter.parse(objUser.getDob());
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
       // java.sql.Date dt = java.sql.Date(date.getTime());
        
        String s1 = objUser.getSecretQuestion();
        String s2 = "'";
        String s3 = "";
        String str1 = null;
        Debug.print(" Replace String : "+s1);
        if (s1 != null && s1.trim().length() > 0) {
            str1 = DBHelper.replace(s1,s2,s3);
            Debug.print(" Replace String : "+str1);
            int index = s1.indexOf("'");    
            if (index >= 0){
                s1 = str1;
            }
        }
        Debug.print(" Final String : "+s1);  
        objUser.setSecretQuestion(s1);

        
        String insertStatement = "insert into " + DBHelper.USEA_MMS_USERMASTER + " (contact_type_id,user_type_id,prefix,first_name,middle_name,"+
            "last_name,sufix,dob,gender,email_id, password, secret_question, secret_answer, user_code)" +
                " values ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?, ?,?) ";

        prepStmt = con.prepareStatement(insertStatement);
        System.out.println("Inside the User Master ....\n\n ");

        prepStmt.setString(1, objUser.getContactTypeId());
        prepStmt.setString(2, objUser.getUserId());
        prepStmt.setString(3, objUser.getPrefix());
        prepStmt.setString(4, objUser.getFirstName());
        prepStmt.setString(5, objUser.getMiddleName());
        prepStmt.setString(6, objUser.getLastName());
        prepStmt.setString(7, objUser.getSufix());
        prepStmt.setDate(8, DBHelper.toSQLDate(date));
        prepStmt.setString(9, objUser.getGender());
        prepStmt.setString(10, objUser.getEmailId());
        prepStmt.setString(11, objUser.getPassword());
        prepStmt.setString(12, objUser.getSecretQuestion());
        prepStmt.setString(13, objUser.getSecretAnswer());
        prepStmt.setString(14, objUser.getUserCode());
        Debug.print(" content of User Details \n"+objUser);
        
        int cnt = prepStmt.executeUpdate();
       
        System.out.println("Succefully inserted :  "+cnt);
        prepStmt.close();
      }catch (SQLException e){
           releaseConnection();
          e.printStackTrace();
      }finally {
          releaseConnection();
      }
      return true;
    }
     
     public boolean insertRowContactDetails(HLCUserMaster objUser) throws SQLException {
            Debug.print("ArabianSeaEntityBean insertRowContactDetails");
             try {
                makeConnection();
                if (objUser.getEmailId() != null){
                    try {
                        userId = DBHelper.getUserId(con, objUser.getEmailId());
                        contactTypeId = DBHelper.getContacttypeId(con,objUser.getContactType() );
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                objUser.setUserId(userId);
                objUser.setContactTypeId(contactTypeId);
                
                System.out.print("User Id    : "+userId);
                Debug.print("Contact Type Id : "+contactTypeId);
                
                String insertStatement = 
                   "insert into " + DBHelper.USEA_CONTACT_DETAILS + " (contact_type_id,user_id,suite,address1,"+
                    "address2,city,state,country,zip,phone_no, mobile_no, fax_no)" +
                        " values ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?, ?) ";
                prepStmt = con.prepareStatement(insertStatement);
                System.out.println("Inside the Contact Details ....\n\n ");
                prepStmt.setString(1, objUser.getContactTypeId());
                prepStmt.setString(2, objUser.getUserId());
                prepStmt.setString(3, objUser.getSuite());
                prepStmt.setString(4, objUser.getAddress1());
                prepStmt.setString(5, objUser.getAddress2());
                prepStmt.setString(6, objUser.getCity());
                prepStmt.setString(7, objUser.getState());
                prepStmt.setString(8, objUser.getCountry());
                prepStmt.setString(9, objUser.getZip());
                prepStmt.setString(10, objUser.getPhoneNo());
                prepStmt.setString(11, objUser.getMobileNo());
                prepStmt.setString(12, objUser.getFaxNo());
                Debug.print(" content of Contect Details \n"+objUser);
                
                int cnt = prepStmt.executeUpdate();
                System.out.print("Record Inserted succefully  "+cnt);
                Debug.print(" Insert Contact Detail Data : \n"+objUser);
               
                prepStmt.close();
               
          }catch (SQLException e){
            releaseConnection();
            e.printStackTrace();
          }finally {
              releaseConnection();
          }
          return true;
        }
/**
  * Name         :insertRowMsgGroupUser
  * Description  :This method will insert a record into the Message Group Users 
  * @ param      : Message Group User VO
  * @return      :void
  * @throws      :SQLException
  */        
    public boolean insertGroupUser(HLCUserMaster objGrpUsr) throws SQLException {
        Debug.print("MessageGroupDAO insertRowMsgGroupMaster");
        //java.sql.Date dt = java.sql.Date.valueOf(dob);
       try {
            makeConnection();

            String insertStatement = 
              "insert into " + DBHelper.USEA_MSG_GROUPUSERS+ " (owner_id,group_id,user_id) " +
                    " values ( ? , ?, ? ) ";
            prepStmt = con.prepareStatement(insertStatement);
            System.out.println("Inside the MessageGroupDAO ....\n\n ");
            prepStmt.setString(1, objGrpUsr.getOwnerId());
            prepStmt.setString(2, objGrpUsr.getGroupId());
            prepStmt.setString(3, objGrpUsr.getUserId());
           // prepStmt.setString(4, objGrpUsr.getJoinDate());
            Debug.print(" content of User Details \n"+objGrpUsr);
            
            Debug.print("Inside the DAO \n"+objGrpUsr);

            int cnt = prepStmt.executeUpdate();
            System.out.println("Succefully inserted :  "+cnt);
            prepStmt.close();
       }catch(Exception e){
           releaseConnection();
           Debug.print("Error while inserting Msessage Group Users  "+e.getMessage());
       }finally {
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
