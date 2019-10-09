/*
 * MessageGroupDAO.java
 *
 * Created on October 2, 2006, 1:28 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmsg.DAO;
import com.hlcmsg.util.DBHelper;
import com.hlcmsg.util.Debug;
import com.hlcmsg.util.HLCGroup;
import com.hlcmsg.util.HLCMsgGroupMaster;
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
public class HLCMessageGroupDAO {
    private static final String dbName = "java:/ELMTMSSQLDS";
    private Connection con = null;
    private PreparedStatement prepStmt = null;
    private ResultSet rs = null;
    private PreparedStatement prepStmt1 = null;
    private ResultSet rs1 = null;
    
    private String group_id = null;
    private String category_id = null;
    private String group_name = null;
    private String user_id = null;
    private String group_type = null;
    private String group_desc = null;
    private String moderator_id = null;
    private String active_status = null;
    private String add_date = null;
    private String category_Name = null;
    private String listName = null;
    
    /** Creates a new instance of MessageGroupDAO */
    public HLCMessageGroupDAO() {
    }
/**
  * Name         :insertRowMsgGroupMaster
  * Description  :This method will insert a record into the Message Group Master 
  * @ param      :Message Group Master VO
  * @return      :boolean
  * @throws      :SQLException
  */        
    public boolean insertRowMsgGroupMaster(HLCGroup objMsgGrp) throws SQLException {
        Debug.print("MessageGroupDAO insertRowMsgGroupMaster");
        //java.sql.Date dt = java.sql.Date.valueOf(dob);
       try {
            makeConnection();

            String insertStatement = 
              "insert into " + DBHelper.USEA_MSG_GROUPMASTER + " (category_id,group_name,user_id,group_type,group_desc, " +
                    "moderator_id) values ( ? , ?, ?, ?, ?, ? ) ";
            prepStmt = con.prepareStatement(insertStatement);
            System.out.println("Inside the MessageGroupDAO ....\n\n ");
            prepStmt.setString(1, objMsgGrp.getCategoryId());
            prepStmt.setString(2, objMsgGrp.getGroupName());
            prepStmt.setString(3, objMsgGrp.getUserId());
            prepStmt.setString(4, objMsgGrp.getGroupType());
            prepStmt.setString(5, objMsgGrp.getGroupDesc());
            prepStmt.setString(6, objMsgGrp.getModeratorId());
            Debug.print("Inside the DAO \n"+objMsgGrp);

            int cnt = prepStmt.executeUpdate();
            System.out.println("Succefully inserted :  "+cnt);
            prepStmt.close();
       }catch(Exception e){
           releaseConnection();
           Debug.print("Error while inserting Msessage Group Master  "+e.getMessage());
       }finally {
            releaseConnection();
       }
        return true;
   }
    
    
    //Editing the group : boolean updateGroup(Group group);
    
/**
  * Name         :updateMsgGroupMaster
  * Description  :This method will update a record into the Message Group Master 
  * @ param      :Message Group Master VO
  * @return      :boolean
  * @throws      :SQLException
  */        
    public boolean updateMsgGroupMaster(HLCGroup objMsgGrp) throws SQLException {
        Debug.print("MessageGroupDAO updateMsgGroupMaster");
        //java.sql.Date dt = java.sql.Date.valueOf(dob);
         int cnt = 0;
       try {
            makeConnection();

            String insertStatement = 
              "UPDATE " + DBHelper.USEA_MSG_GROUPMASTER + " SET group_name = ?, group_desc = ? " +
                    " WHERE group_id = ? ";
            prepStmt = con.prepareStatement(insertStatement);
            System.out.println("Inside the MessageGroupDAO ....\n\n ");
            prepStmt.setString(1, objMsgGrp.getGroupName());
            prepStmt.setString(2, objMsgGrp.getGroupDesc());
            prepStmt.setString(3, objMsgGrp.getGroupId());
            Debug.print("Inside the DAO \n"+objMsgGrp);

            cnt = prepStmt.executeUpdate();
            System.out.println("Succefully Updated :  "+cnt);
            prepStmt.close();
       }catch(Exception e){
           releaseConnection();
           Debug.print("Error while Updating Msessage Group Master  "+e.getMessage());
       }finally {
            releaseConnection();
       }
       if (cnt > 0)
        return true;
       else 
           return false;
   }
    //select user_id from tblUserMaster where email_id = ?
    //delete from tblMsgGroupUsers whete user_id = ? and group_id = ?
/**
  * Name         :deleteGroupUser
  * Description  :This method will delete a record based on group id and eamil id fro groupUser table 
  * @ param      :groupId, emailId
  * @return      :boolean
  * @throws      :SQLException
  */        
    public boolean deleteGroupUser(String groupId, String email) throws SQLException {
        Debug.print("MessageGroupDAO updateMsgGroupMaster");
        //java.sql.Date dt = java.sql.Date.valueOf(dob);
       try {
            makeConnection();
            
            String stmtQuery = "select user_id from "+DBHelper.USEA_MMS_USERMASTER+" WHERE email_id = ?";
            prepStmt = con.prepareStatement(stmtQuery);
            prepStmt.setString(1, email);
            rs = prepStmt.executeQuery();
            if(rs.next()) {
                this.user_id =rs.getString("user_id");
            }
            
            stmtQuery = "DELETE FROM "+DBHelper.USEA_MSG_GROUPUSERS+" WHERE group_id = ? AND user_id = ?";
            prepStmt = con.prepareStatement(stmtQuery);
            prepStmt.setString(1, groupId);
            prepStmt.setString(2, user_id);
            int cnt = prepStmt.executeUpdate();
            System.out.println("Succefully Deleteed record form user Group users:  "+cnt);

            
            prepStmt.close();
       }catch(Exception e){
           releaseConnection();
           Debug.print("Error while deactiveGroupStatus Group Master  "+e.getMessage());
       }finally {
            releaseConnection();
       }
        return true;
   }      
    
/**
  * Name         :selectAvailableGroups
  * Description  :This method will receive all the group information based on the current user participation 
  * @ param      :userID
  * @return      :ArrayList
  * @throws      :SQLException
  */        
    public ArrayList selectAvailableGroups(String userID) throws SQLException {
        Debug.print("MessageGroupDAO selectAvailableGroups  "+userID);
         ArrayList Avail_Group = new ArrayList();
       try {
            makeConnection();
            
            String stmtQuery = "select group_name,group_id,category_id,group_type,group_desc from "+ DBHelper.USEA_MSG_GROUPMASTER+" where user_id=?";
            prepStmt = con.prepareStatement(stmtQuery);
            prepStmt.setString(1, userID);
            rs = prepStmt.executeQuery();
            while(rs.next()) {
                String group_name =rs.getString(1);
                String group_id = rs.getString(2);
                String category_id = rs.getString(3);
                String group_type = rs.getString(4);
                String group_desc = rs.getString(5);
                String [] groupInfo = {group_name,group_id,category_id,group_type,group_desc};
                //Debug.print("Group Values "+groupInfo.toString());
                Avail_Group.add(groupInfo);
            }           
            prepStmt.close();
       }catch(Exception e){
           releaseConnection();
           Debug.print("Error while selectAvailableGroups "+e.getMessage());
       }finally {
            releaseConnection();
       }
       return Avail_Group;
   }
    
/**
  * Name         :updateMsgGroupMaster
  * Description  :This method will update a record into the Message Group Master 
  * @ param      :Message Group Master VO
  * @return      :boolean
  * @throws      :SQLException
  */        
    public boolean deactiveGroupStatus(String groupId) throws SQLException {
        Debug.print("MessageGroupDAO updateMsgGroupMaster");
        //java.sql.Date dt = java.sql.Date.valueOf(dob);
       try {
            makeConnection();

            String insertStatement = 
              "UPDATE  "+ DBHelper.USEA_MSG_GROUPMASTER +" SET active_status = ? " +
                    " WHERE group_id = ? ";
            prepStmt = con.prepareStatement(insertStatement);
            System.out.println("Inside the MessageGroupDAO ....\n\n ");
            prepStmt.setBoolean(1, false);
            prepStmt.setString(2, groupId);
           
            int cnt = prepStmt.executeUpdate();
            System.out.println("Succefully deactivated Status:  "+cnt);
            prepStmt.close();
       }catch(Exception e){
           releaseConnection();
           Debug.print("Error while deactiveGroupStatus Group Master  "+e.getMessage());
       }finally {
            releaseConnection();
       }
        return true;
   }    
/**
  * Name         :selectMemberAdded
  * Description  :This method will list the distribution list and no of member based on user_id
  * @ param      : list name
  * @return      :Vector
  * @throws      :SQLException
  */          
    public List selectCategory() throws SQLException {
        Debug.print("Inside the selectCategory  : ");
        HLCMsgGroupMaster objMGM = new HLCMsgGroupMaster();
        List catDet = new ArrayList();
        try {
            makeConnection();
            String selectStatement = "select category_id, category_name FROM "+DBHelper.USEA_MSG_CATEGORY+" WHERE active_status = 'true'";
            
            prepStmt = con.prepareStatement(selectStatement);
            rs = prepStmt.executeQuery();
            while (rs.next()) {
                this.category_id =rs.getString("category_id");
                this.category_Name = rs.getString("category_name");
                Debug.print("category_id : "+category_id);
                Debug.print("category_name : "+category_Name);
                String [] catagoryDet = {category_id,category_Name};
                catDet.add(catagoryDet);
              } 
             prepStmt.close();
        } catch (SQLException sqe) {
            releaseConnection();
            throw new EJBException(sqe);
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return catDet;
    }     
    
    //List<GroupVO> searchByGroup(String category,String groupName);
    /*String countQuery = "SELECT count(1) as total_records FROM (SELECT ROW_NUMBER() OVER (ORDER BY first_name ASC)AS Row "+
            " from  tblMsgContactListMaster ";
            if(sortableName!=null && sortableName.trim().length()>0){
        countQuery += "  where first_name like '"+sortableName+"%' or "+
		" middle_name like '"+sortableName+"%' or last_name like '"+sortableName+"%')" ;
        
        };       
        DBHelper.USEA_MSG_GROUPMASTER + " (category_id,group_name,user_id,group_type,group_desc,*/
 /**
  * Name         :searchByGroupAndCategoryId
  * Description  :This method will list the distribution list and no of member based on user_id
  * @ param      : list name
  * @return      :Vector
  * @throws      :SQLException
  */          
    public List searchByGroupAndCategoryId(String categoryId,String groupName) throws SQLException {
        Debug.print("Inside the selectCategory  : ");
        HLCGroup objGRP =null;
        List searchData = new ArrayList();
        try {
            makeConnection();
            String selectStatement = "select A.category_name,B.group_id,B.group_name,B.group_type,B.group_desc FROM "+
            DBHelper.USEA_MSG_CATEGORY+" A, "+DBHelper.USEA_MSG_GROUPMASTER+" B WHERE A.category_id = B.category_id and B.active_status='true'";   
            
            if (categoryId != null && categoryId.trim().length() > 0) {
                selectStatement +=" AND A.category_id = '"+categoryId+"' "; 
               // checkFlag = false;
            }
            if (groupName != null && groupName.trim().length() > 0)
                selectStatement +=" AND B.group_name LIKE '%"+groupName+"%' ";
            
           /* boolean checkFlag = true;
            if (categoryId != null) {
                selectStatement +=((checkFlag)?" WHERE ":" AND ")+" A.category_id = B.category_id AND A.category_id = '"+categoryId+"' "; 
                checkFlag = false;
            }
            if (categoryId == null && groupName != null)
                selectStatement +=((checkFlag)?" WHERE ":" AND ")+" B.group_name = '"+groupName+"' ";*/
            
            Debug.print("Query is : "+selectStatement);
            
            prepStmt = con.prepareStatement(selectStatement);
            rs = prepStmt.executeQuery();
            while (rs.next()) {
                objGRP = new HLCGroup();
                objGRP.setGroupId(rs.getString("group_id"));
                objGRP.setCategoryName(rs.getString("category_name"));
                objGRP.setGroupName(rs.getString("group_name"));
                objGRP.setGroupType(rs.getString("group_type"));
                objGRP.setGroupDesc(rs.getString("group_desc"));
                Debug.print(" Content in DAO : "+objGRP);
                searchData.add(objGRP);
              } 
             prepStmt.close();
        } catch (SQLException sqe) {
            releaseConnection();
            throw new EJBException(sqe);
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return searchData;
    } 
    
    
 /**
  * Name         :searchByGroupAndCategoryId
  * Description  :This method will list the distribution list and no of member based on user_id
  * @ param      : list name
  * @return      :Vector
  * @throws      :SQLException
  */          
    public HLCGroup selectGroupDetailsBasedOnGroupId(String groupId) throws SQLException {
        Debug.print("Inside the selectGroupDetailsBasedOnGroupId  : ");
        HLCGroup objGRP = new HLCGroup();
       
        try {
            makeConnection();
            String selectStatement = "select A.category_id,A.category_name,B.group_name,B.group_type,B.group_desc,B.group_id FROM "+
            DBHelper.USEA_MSG_CATEGORY+" A, "+DBHelper.USEA_MSG_GROUPMASTER+" B "+
            " WHERE B.category_id = A.category_id ";
            
            if (groupId != null && groupId.trim().length() > 0 )
                selectStatement += " AND B.group_id = '"+groupId+"' ";
            
            Debug.print("Query is : "+selectStatement);
            
            prepStmt = con.prepareStatement(selectStatement);
            rs = prepStmt.executeQuery();
            if(rs.next()) {
                objGRP.setCategoryId(rs.getString("category_id"));
                objGRP.setCategoryName(rs.getString("category_name"));
                objGRP.setGroupName(rs.getString("group_name"));
                objGRP.setGroupType(rs.getString("group_type"));
                objGRP.setGroupDesc(rs.getString("group_desc"));
                objGRP.setGroupId(rs.getString("group_id"));
                Debug.print(" Content in DAO : "+objGRP);
                
              } 
             prepStmt.close();
        } catch (SQLException sqe) {
            releaseConnection();
            sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return objGRP;
    }     
   /* select A.group_name, count(B.user_id) as no_of_users 
from tblMsgGroupMaster A, tblMsgGroupUsers B 
where A.group_id = B.group_id and B.owner_id = '74dc37e0-97a7-4f0f-b705-d52a875492f0' 
group by B.group_id, A.group_name*/

/**
  * Name         :selectGroupMyGroupBasedOnOwnerId
  * Description  :This method will list the group name and no of days based on owner id
  * @ param      :owner id
  * @return      :List
  * @throws      :SQLException
  */          

 public List selectGroupMyGroupBasedOnOwnerId(String ownerId) throws SQLException {
        Debug.print("Inside the selectGroupDetailsBasedOnGroupId  : ");
        List list =  new ArrayList();
       
        try {
            makeConnection();
            String selectStatement = "SELECT A.group_id,A.group_name, count(B.user_id) as no_of_users FROM "+
            DBHelper.USEA_MSG_GROUPMASTER+" A, "+DBHelper.USEA_MSG_GROUPUSERS+" B "+
            " where A.group_id = B.group_id or B.owner_id = '"+ownerId+"' and  A.active_status='true'" +
                    " group by A.group_name,A.group_id";
                       
            Debug.print("Query is : "+selectStatement);
            
            prepStmt = con.prepareStatement(selectStatement);
            rs = prepStmt.executeQuery();
            while(rs.next()) {
                String groupId = rs.getString("group_id");
                String groupName = rs.getString("group_name");
                String noOfUser = rs.getString("no_of_users");
                String [] myGroup = {groupId,groupName,noOfUser};
                list.add(myGroup);
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
  * Name         :isValidGroupName
  * Description  :This method will list the distribution list and no of member based on user_id
  * @ param      :groupName
  * @return      :boolean
  * @throws      :SQLException
  */          
    public boolean isValidGroupName(String userId, String groupName) throws SQLException {
        Debug.print("Inside the isValidGroupName  : ");
        boolean bol = false;
        try {
            makeConnection();
            String countQuery = "select count(1) as total_records FROM "+DBHelper.USEA_MSG_GROUPMASTER+
            " WHERE group_name ='"+groupName+"' and user_id='"+userId+"'";
            Debug.print("countQuery is "+countQuery);
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
    //Group  getGroupDetails(String groupName);
/**
  * Name         :getGroupDetails
  * Description  :This method will list the distribution list and no of member based on user_id
  * @ param      :groupName
  * @return      :Group
  * @throws      :SQLException
  */          
    public HLCGroup selectGroupDetails(String groupName) throws SQLException {
        HLCGroup objGrp = new HLCGroup();
        Debug.print("Inside the isValidGroupName  : ");
        boolean bol = false;
        try {
            makeConnection();
            String countQuery = "select A.group_id,B.category_name,A.group_name,A.user_id,A.group_type,A.group_desc,A.add_date "+
            " FROM "+DBHelper.USEA_MSG_GROUPMASTER+" A, "+DBHelper.USEA_MSG_CATEGORY+" B "+
            " WHERE A.category_id = B.category_id AND group_name = '"+groupName+"' AND A.active_status = 'true'";

            Statement stmt1 = con.createStatement();       
            rs = stmt1.executeQuery(countQuery);
            String totalRecords = "0";
            if (rs.next()){
                objGrp.setGroupId(rs.getString("group_id"));
                objGrp.setUserId(rs.getString("user_id"));
                objGrp.setCategoryName(rs.getString("category_name"));
                objGrp.setGroupName(rs.getString("group_name"));
                objGrp.setGroupType(rs.getString("group_type"));
                objGrp.setGroupDesc(rs.getString("group_desc"));
                objGrp.setAddDate(rs.getString("add_date"));
                Debug.print(""+objGrp);
            }
            stmt1.close();
        } catch (SQLException sqe) {
            releaseConnection();
            throw new EJBException(sqe);
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return objGrp;
    }          

    public ArrayList selectDistribitionListNameAndmailId(String userId) throws SQLException {
        Debug.print("Inside the selectDistribitionListName  contactlistId : "+listName);
        this.listName =listName;
        ArrayList vObj = new ArrayList();
        try {
            makeConnection();
            String selectStatement = "select A.list_name, B.email_id, count(A.contactlist_id) as no_of_users  from " +
                    " tblMsgDistributionListMaster A, tblMsgContactListMaster B" +
                    " where A.contactlist_id = B.contactlist_id and  B.user_id= ?" +
                    " group by A.list_name, B.email_id ";
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, userId);
            rs = prepStmt.executeQuery();
            while (rs.next()) {
               
                this.listName = rs.getString(1);
                String emailId = rs.getString(2);
                String noOfUser = rs.getString(3);
                String list[] = {listName,emailId,noOfUser};
                
                
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
    
    public String getEmailId(String userId) throws SQLException {
        Debug.print("Inside the selectDistribitionListName  contactlistId : "+listName);
        String emailId = "";
        
        try {
            makeConnection();
            
            String selectStatement = "SELECT email_id FROM "+DBHelper.USEA_USER_MASTER+"  WHERE user_id = '"+userId+"'";
            Debug.print(" Connection : "+con);
            Statement stmt1 = con.createStatement();       
            ResultSet rs1 = stmt1.executeQuery(selectStatement);
            Debug.print("\n "+selectStatement+" rs="+rs1);
            while(rs1.next()) {
                 emailId = rs1.getString("email_id");
                 System.out.println("emailId ******:"+emailId);
            }
            Debug.print(" emailId : "+emailId);
            stmt1.close();
        } catch (SQLException sqe) {
            releaseConnection();
            sqe.printStackTrace();
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return emailId;
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
