/*
 * MsgGroupMessageDAO.java
 *
 * Created on October 3, 2006, 10:52 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmsg.DAO;
import com.hlcmsg.util.DBHelper;
import com.hlcmsg.util.Debug;
import com.hlcmsg.util.HLCGroupMessage;
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
public class HLCMsgGroupMessageDAO {
    private static final String dbName = "java:/ELMTMSSQLDS";
    private Connection con = null;
    private PreparedStatement prepStmt = null;
    private ResultSet rs = null;
    private PreparedStatement prepStmt1 = null;
    private ResultSet rs1 = null;
    
    private String ownerId = null;
    
    /** Creates a new instance of MsgGroupMessageDAO */
    public HLCMsgGroupMessageDAO() {
    }
    
    
    
/**
  * Name         :isValidEmailInDAO
  * Description  :This method will list the distribution list and no of member based on user_id
  * @ param      :groupName
  * @return      :boolean
  * @throws      :SQLException
  */          
    public boolean isValidOwnerBasedOnUserId(String userId) throws SQLException {
        Debug.print("Inside the isValidOwner  : ");
        boolean bol = false;
        try {
            makeConnection();
            String countQuery = "select owner_id FROM "+DBHelper.USEA_MSG_GROUPUSERS+
            " WHERE user_id = '"+userId+"'";
            Statement stmt1 = con.createStatement();       
            rs = stmt1.executeQuery(countQuery);
            if (bol = rs.next()){
              this.ownerId = rs.getString("owner_id");
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
        return bol;
    }      
    
/**
  * Name         :insertGroupMessage
  * Description  :This method will insert the details into the group message table
  * @ param      :GroupMessage VO
  * @return      :boolean
  * @throws      :SQLException
  */          
    public boolean insertGroupMessage(HLCGroupMessage objGM) throws SQLException {
        Debug.print("Inside the insertGroupMessage  : ");
        boolean bol = false;
        try {
            makeConnection();

            String insertStatement = 
              "insert into " + DBHelper.USEA_MSG_GROUPMESSAGE+ " (group_id,from_user_id,to_user_id,subject,message_desc," +
                    "parent_message_id,poster_ip,attach_file_path) values ( ?, ?, ?, ?, ?, ?, ?, ? ) ";
            prepStmt = con.prepareStatement(insertStatement);
            System.out.println("Inside the MessageGroupDAO ....\n\n ");
            prepStmt.setString(1, objGM.getGroupId());
            prepStmt.setString(2, objGM.getFromUserId());
            prepStmt.setString(3, objGM.getToUserId());
            prepStmt.setString(4, objGM.getSubject());
            prepStmt.setString(5, objGM.getMessageDesc());
            prepStmt.setString(6, objGM.getParentMessageId());
            prepStmt.setString(7, objGM.getPosterIp());
            prepStmt.setString(8, objGM.getAttachFilePath());
            
            Debug.print("Inside the DAO \n"+objGM);

            int cnt = prepStmt.executeUpdate();
            Debug.print("Succefully inserted :  "+cnt);
            if (cnt > 0) {bol = true;}
                
            prepStmt.close();
        } catch (SQLException sqe) {
            releaseConnection();
            throw new EJBException(sqe);
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return bol;
    }      
    
    //getmessages
/**
  * Name         :selectGroupMyMember
  * Description  :This method will list the distribution list and no of member based on user_id
  * @ param      :fromUserId, subject, groupId
  * @return      :List
  * @throws      :SQLException
  */          
    public List selectGroupMyMember(String ownerId,String subject,String groupId) throws SQLException {
        Debug.print("Inside the selectGroupMyMessages  : ");

        HLCGroupMessage objGRPMSG = null;
        List list = new ArrayList();
        try {
            makeConnection();
            String query = " select A.group_id,B.first_name, B.last_name FROM "+DBHelper.USEA_MSG_GROUPUSERS+" A, "+
            DBHelper.USEA_MMS_USERMASTER+" B where A.user_id = B.user_id and A.owner_id = '"+ownerId+"' ";
           
            if (subject != null && subject.trim().length() > 0)
             query += " And (B.first_name like '%"+subject+"%' OR B.last_name like '%"+subject+"%')";
            
            if (groupId != null && groupId.trim().length() > 0)
                query += " And A.group_id = '"+groupId+"'";

            Debug.print("Query is : "+query);
            
            prepStmt = con.prepareStatement(query);
            rs = prepStmt.executeQuery();

            while(rs.next()) {
                String groupId1 = rs.getString("group_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String[] myMem = {groupId1,firstName+"  "+lastName };
                list.add(myMem);              
              } 
             prepStmt.close();
        } catch (SQLException sqe) {
            releaseConnection();
            sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return list;
    }      //selectEmailByGroupName(groupName)
    
/**
  * Name         :selectEmailByGroupName
  * Description  :This method will list the distribution list and no of member based on user_id
  * @ param      :fromUserId, subject, groupId
  * @return      :List
  * @throws      :SQLException
  */          
    public List selectEmailByGroupName(String groupName) throws SQLException {
        Debug.print("Inside the selectGroupMyMessages  : ");
        HLCGroupMessage objGRPMSG = null;
        List list = new ArrayList();
        try {
            makeConnection();
            String query = " select A.user_id, A.email_id, B.group_id  FROM "+DBHelper.USEA_MMS_USERMASTER+" A, "+DBHelper.USEA_MSG_GROUPMASTER+" B "+
            " where A.user_id = B.user_id AND B.group_name = '"+groupName+"' ";

            Debug.print("Query is : "+query);
            
            prepStmt = con.prepareStatement(query);
            rs = prepStmt.executeQuery();
            int i=0;
            while(rs.next()) {
                String userId = rs.getString("user_id");
                String email = rs.getString("email_id");
                String groupId = rs.getString("group_id");
                String [] str = {userId,email,groupId};
                list.add(str);
              } 
             prepStmt.close();
        } catch (SQLException sqe) {
            releaseConnection();
            sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return list;
    } 
    
 /**
  * Name         :selectGroupMyMessages
  * Description  :This method will list the distribution list and no of member based on user_id
  * @ param      :fromUserId, subject, groupId
  * @return      :List
  * @throws      :SQLException
  */          
     public List selectGroupMyMessages(String fromUserId,String subject,String groupId) throws SQLException {
        Debug.print("Inside the selectGroupMyMessages  : ");
        HLCGroupMessage objGRPMSG = null;
        List list = new ArrayList();
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
        try {
            makeConnection();
            String query = " select A.group_id,A.message_id, A.subject, A.message_desc, A.attach_file_path, A.add_date, "+
            "B.first_name as author FROM "+DBHelper.USEA_MSG_GROUPMESSAGE+" A, "+DBHelper.USEA_MMS_USERMASTER+" B "+
            " where A.from_user_id = B.user_id and from_user_id = '"+fromUserId+"' and parent_message_id is NULL ";

            if (subject != null && subject.trim().length()>0) {
                query +=" AND subject like '%"+subject+"%'";                
            }            
             if (groupId != null && groupId.trim().length()>0) {
                query +=" AND  group_id = '"+groupId+"'";      
            }           
       
            Debug.print("Query is : "+query);
            
            prepStmt = con.prepareStatement(query);
            rs = prepStmt.executeQuery();

            while(rs.next()) {
                objGRPMSG = new HLCGroupMessage();
                objGRPMSG.setGroupId("group_id");
                objGRPMSG.setMessageId(rs.getString("message_id"));
                objGRPMSG.setSubject(rs.getString("subject"));
                objGRPMSG.setMessageDesc(rs.getString("message_desc"));
                objGRPMSG.setAttachFilePath(rs.getString("attach_file_path"));
                Date dateReq = rs.getDate("add_date");
                String dateOfReq = formatter.format(dateReq);
                objGRPMSG.setAddDate(dateOfReq);
                objGRPMSG.setAuthor(rs.getString("author"));                
                list.add(objGRPMSG);              
              } 
             prepStmt.close();
        } catch (SQLException sqe) {
            releaseConnection();
            sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return list;
    }         
            
/**
  * Name         :selectPostedMessages
  * Description  :This method will list the message_id, subject and mesage description based on message Id
  * @ param      :messageId
  * @return      :GroupMessage
  * @throws      :SQLException
  */          
    public HLCGroupMessage selectPostedMessages(String messageId) throws SQLException {
        Debug.print("Inside the selectGroupMyMessages  : ");
        HLCGroupMessage objGRPMSG = new HLCGroupMessage();
        try {
            makeConnection();
            
           String query =
            " select A.message_id, A.subject,A.message_desc, A.attach_file_path,A.parent_message_id," +
            " B.first_name as posted_by, A.add_date, C.group_id,C.group_name,C.group_type,C.group_desc " +
            " FROM tblMsgGrpMessages A, tblUserMaster B ,tblMsgGroupMaster C "+
            " where A.from_user_id = B.user_id and A.group_id = C.group_Id and A.message_id = '"+messageId+"'";

            Debug.print("Query is : "+query);
            
            prepStmt = con.prepareStatement(query);
            rs = prepStmt.executeQuery();

            if(rs.next()) {
                
                objGRPMSG.setMessageId(rs.getString("message_id"));
                objGRPMSG.setSubject(rs.getString("subject"));
                objGRPMSG.setMessageDesc(rs.getString("message_desc"));
                objGRPMSG.setParentMessageId(rs.getString("parent_message_id"));
                 objGRPMSG.setPostedBy(rs.getString("posted_by"));
                objGRPMSG.setGroupId(rs.getString("group_id"));
                objGRPMSG.setGroupName(rs.getString("group_name"));
                objGRPMSG.setGroupType(rs.getString("group_type"));
                objGRPMSG.setGroupDesc(rs.getString("group_desc"));
              } 
             prepStmt.close();
        } catch (SQLException sqe) {
            releaseConnection();
            sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return objGRPMSG;
    }      
    
/**
  * Name         :selectMessagesAndReplies
  * Description  :This method will list the message_id, subject and mesage description based on groupId and parentMessageId
  * @ param      :groupId, parentMessageId
  * @return      :List
  * @throws      :SQLException
  */          
    public List selectMessagesAndReplies(String groupId, String parentMessageId ) throws SQLException {
        Debug.print("Inside the selectGroupMyMessages  : ");
        HLCGroupMessage objGRPMSG = null;
        List list = new ArrayList();
        try {
            makeConnection();
            String query = " select A.message_id, A.subject, A.message_desc, A.attach_file_path, B.first_name as posted_by, " +
            " A.add_date FROM "+DBHelper.USEA_MSG_GROUPMESSAGE+" A, "+DBHelper.USEA_MMS_USERMASTER+" B "+
            " where A.from_user_id = B.user_id and A.group_id = '"+groupId+"' ";
            
            if (parentMessageId != null && parentMessageId.trim().length() > 0)
                query += " AND A.parent_message_id = '"+parentMessageId+"' ";
           
            Debug.print("Query is : "+query);
            
            prepStmt = con.prepareStatement(query);
            rs = prepStmt.executeQuery();

            while(rs.next()) {
                objGRPMSG = new HLCGroupMessage();
                objGRPMSG.setMessageId(rs.getString("message_id"));
                objGRPMSG.setSubject(rs.getString("subject"));
                objGRPMSG.setMessageDesc(rs.getString("message_desc"));
                objGRPMSG.setAttachFilePath(rs.getString("attach_file_path"));
                objGRPMSG.setPostedBy(rs.getString("posted_by"));
                objGRPMSG.setAddDate(rs.getString("add_date"));
                list.add(objGRPMSG);
              } 
             prepStmt.close();
        } catch (SQLException sqe) {
            releaseConnection();
            sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return list;
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
