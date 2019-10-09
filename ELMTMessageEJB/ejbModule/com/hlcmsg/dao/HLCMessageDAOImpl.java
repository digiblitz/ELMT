/*
 * MessageDAOImpl.java
 *
 * Created on September 26, 2006, 11:07 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package com.hlcmsg.dao;

import com.hlccommon.util.Debug;
import com.hlccommon.util.HLCEJBAllJNDIs;
import com.hlccommon.util.HLCMessageVO;
import com.hlccommon.util.HLCProjectVO;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.naming.InitialContext;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.hlccommon.util.DBHelper;
import com.hlccommon.util.HLCTaskVO;
import java.util.List;
import java.util.ArrayList;
import java.util.*;
import java.util.Map;
import java.util.HashMap;
/**
 *
 * @author suresh
 */
public class HLCMessageDAOImpl implements HLCMessageDAO {

    private Connection conn;

//=============================================Create Mapping permission with privilege details=========================================      
    public String insertGroupDetails(String groupName, String userId, String groupType) throws SQLException {
        Debug.print("MessageDAOImpl.insertPermissionPrivilegeMapping():");
        PreparedStatement prepStmt = null;
        String groupId = getNextGroupId();
        makeConnection();
        try {
            String insertStatement = "insert into " + DBHelper.USEA_MSG_GROUP_MASTER + " (group_id , group_name, user_id, group_type) " +
                    " values( ? , ? , ? , ? )";
            prepStmt = conn.prepareStatement(insertStatement);
            prepStmt.setString(1, groupId);
            prepStmt.setString(2, groupName);
            prepStmt.setString(3, userId);
            prepStmt.setString(4, groupType);
            prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection(conn);
        } catch (SQLException sql) {
            releaseConnection(conn);
            Debug.print("SQL Exception in MessageDAOImpl.insertGroupDetails():" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(conn);
            Debug.print("General Exception  in MessageDAOImpl.insertGroupDetails():" + e.getMessage());
        }
        return groupId;
    }

    //=============================================Access Name Checking details=========================================      
    public boolean isGroupExist(String groupName) {
        Debug.print("MessageDAOImpl.isAccessNameExist():" + groupName);
        boolean result = true;
        makeConnection();
        try {
            String selectStatement = "select map_permission_id from " + DBHelper.USEA_MSG_GROUP_MASTER + " where access_name = ? ";
            PreparedStatement prepStmt = conn.prepareStatement(selectStatement);
            prepStmt.setString(1, groupName);
            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()) {
                result = false;
            }
            rs.close();
            prepStmt.close();
            releaseConnection(conn);
        } catch (SQLException e) {
            releaseConnection(conn);
            Debug.print("Could not find any from groupName" + e.getMessage());
        } catch (Exception e) {
            releaseConnection(conn);
            Debug.print("General Exception  in isAccessNameExist:" + e.getMessage());
        }
        Debug.print("isAccessNameExist():" + result);
        return result;
    }

    //============================================ Messages ===================================
    //============================================ show Inbox based on user Id ===================================    
    public ArrayList showInbox(String toUserId, int startIndex, int endIndex) {
        Debug.print("MessageDAOImpl.showInbox():" + toUserId);
        ArrayList messageList = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
        try {
            String selectStatement = " SELECT B.message_id, B.from_user_id, B.to_user_id, B.subject, B.message, B.post_date, B.view_status," +
                    "   A.first_name, A.last_name, A.email_id as from_email FROM " + DBHelper.USEA_MSG_USER_MASTER +
                    " A, " + DBHelper.USEA_MSG_MESSAGES + " B  WHERE A.user_id = B.from_user_id and B.to_user_id = ? " +
                    " order by B.post_date desc";
            prepStmt = conn.prepareStatement(selectStatement);
            prepStmt.setString(1, toUserId);
            rs = prepStmt.executeQuery();
            messageList = new ArrayList();
            while (rs.next()) {
                HLCMessageVO msgVO = new HLCMessageVO();
                msgVO.setMessageId(rs.getString(1));
                msgVO.setFromUserId(rs.getString(2));
                msgVO.setToUserId(rs.getString(3));
                msgVO.setSubject(rs.getString(4));
                msgVO.setMessage(rs.getString(5));
                msgVO.setPostDate(rs.getDate(6));
                msgVO.setViewStatus(rs.getBoolean(7));
                msgVO.setFirstName(rs.getString(8));
                msgVO.setLastName(rs.getString(9));
                msgVO.setFromEmailId(rs.getString(10));
                messageList.add(msgVO);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(conn);
            Debug.print("MessageDAOImpl.showInbox():" + messageList);
        } catch (SQLException sql) {
            releaseConnection(conn);
            Debug.print("SQL Exception in MessageDAOImpl.showInbox():" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(conn);
            Debug.print("General Exception  in MessageDAOImpl.showInbox():" + e.getMessage());
        }
        return messageList;
    }
    //============================================ show detailed Message based on message id===================================       
    public HLCMessageVO showMessage(String messageId) {
        Debug.print("MessageDAOImpl.showMessage():" + messageId);
        HLCMessageVO msgVO = new HLCMessageVO();
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        boolean statusResult = statusChange(messageId);
        makeConnection();
        try {
            String selectStatement = " SELECT B.message_id, B.from_user_id, B.to_user_id, B.subject, B.message, B.post_date, B.view_status," +
                    "   A.first_name, A.last_name, A.email_id, B.file_path as from_email FROM " + DBHelper.USEA_MSG_USER_MASTER +
                    " A, " + DBHelper.USEA_MSG_MESSAGES + " B  " +
                    " WHERE A.user_id = B.from_user_id and B.message_id = ? ";
            prepStmt = conn.prepareStatement(selectStatement);
            prepStmt.setString(1, messageId);
            rs = prepStmt.executeQuery();
            while (rs.next()) {
                msgVO.setMessageId(rs.getString(1));
                msgVO.setFromUserId(rs.getString(2));
                msgVO.setToUserId(rs.getString(3));
                msgVO.setSubject(rs.getString(4));
                msgVO.setMessage(rs.getString(5));
                msgVO.setPostDate(rs.getDate(6));
                msgVO.setViewStatus(rs.getBoolean(7));
                msgVO.setFirstName(rs.getString(8));
                msgVO.setLastName(rs.getString(9));
                msgVO.setFromEmailId(rs.getString(10));
                msgVO.setFilePath(rs.getString(11));
            }
            rs.close();
            prepStmt.close();
            releaseConnection(conn);
            Debug.print("MessageDAOImpl.showMessage():" + msgVO);
        } catch (SQLException sql) {
            releaseConnection(conn);
            Debug.print("SQL Exception in MessageDAOImpl.showMessage():" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(conn);
            Debug.print("General Exception  in MessageDAOImpl.showMessage():" + e.getMessage());
        }
        return msgVO;
    }
    //============================================ show detailed Message based on message id===================================       
    public int newMessagesCount(String toUserId) {
        Debug.print("MessageDAOImpl.newMessagesCount():" + toUserId);
        int count = 0;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        // boolean result = statusChange(toUserId);
        makeConnection();
        try {
            String selectStatement = " SELECT count(*) from " + DBHelper.USEA_MSG_MESSAGES + " where view_status = 0 and to_user_id= ? ";
            prepStmt = conn.prepareStatement(selectStatement);
            prepStmt.setString(1, toUserId);
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(conn);
            Debug.print("MessageDAOImpl.newMessagesCount():" + count);
        } catch (SQLException sql) {
            releaseConnection(conn);
            Debug.print("SQL Exception in MessageDAOImpl.newMessagesCount():" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(conn);
            Debug.print("General Exception  in MessageDAOImpl.newMessagesCount():" + e.getMessage());
        }
        return count;
    }

    public int totalMessageCount(String toUserId) {
        Debug.print("MessageDAOImpl.totalMessageCount():" + toUserId);
        int count = 0;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        // boolean result = statusChange(toUserId);
        makeConnection();
        try {
            String selectStatement = " SELECT count(*) from " + DBHelper.USEA_MSG_MESSAGES + " where to_user_id= ? ";
            prepStmt = conn.prepareStatement(selectStatement);
            prepStmt.setString(1, toUserId);
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(conn);
            Debug.print("MessageDAOImpl.totalMessageCount():" + count);
        } catch (SQLException sql) {
            releaseConnection(conn);
            Debug.print("SQL Exception in MessageDAOImpl.totalMessageCount():" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(conn);
            Debug.print("General Exception  in MessageDAOImpl.totalMessageCount():" + e.getMessage());
        }
        return count;
    }
    //============================================ status Change for particular message  ===================================       
    private boolean statusChange(String messageId) {
        Debug.print("MessageDAOImpl.statusChange():" + messageId);
        boolean result = false;
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String selectStatement = "  update " + DBHelper.USEA_MSG_MESSAGES + " set view_status = 1  where message_id = ? ";
            prepStmt = conn.prepareStatement(selectStatement);
            prepStmt.setString(1, messageId);
            int intResult = prepStmt.executeUpdate();
            if (intResult != 0) {
                result = true;
            }

            prepStmt.close();
            releaseConnection(conn);
            Debug.print("MessageDAOImpl.statusChange():" + result);
        } catch (SQLException sql) {
            releaseConnection(conn);
            Debug.print("SQL Exception in MessageDAOImpl.statusChange():" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(conn);
            Debug.print("General Exception  in MessageDAOImpl.statusChange():" + e.getMessage());
        }
        return result;
    }
    //============================================ Delete  Inbox message ===================================       
    public boolean deleteMessages(String messageIds) {
        Debug.print("MessageDAOImpl.deleteMessages():" + messageIds);
        boolean result = false;
        StringTokenizer strTkns = new StringTokenizer(messageIds, "#");
        while (strTkns.hasMoreTokens()) {
            try {
                String messageId = (String) strTkns.nextToken();
                if (messageId != null && messageId.trim().length() != 0) {
                    Debug.print("MessageDAOImpl.deleteMessage() deleting messageId from Stokenizer:" + messageId);
                    result = deleteMessage(messageId);
                }
                result = true;
            } catch (Exception e) {
                Debug.print("Exception in deleteMessages():" + e);
            }
        }
        return result;
    }
    //============================================ delete a particular message ===================================       
    public boolean deleteMessage(String messageId) {
        Debug.print("MessageDAOImpl.deleteMessage():" + messageId);
        boolean result = false;
        makeConnection();
        try {
            String deleteStatement = "delete from " + DBHelper.USEA_MSG_MESSAGES + "  where message_id = ? ";
            PreparedStatement prepStmt = conn.prepareStatement(deleteStatement);
            prepStmt.setString(1, messageId);
            Debug.print("MessageDAOImpl.deleteMessage() Deleted ID:" + messageId);
            prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection(conn);
            result = true;
        } catch (SQLException sql) {
            releaseConnection(conn);
            Debug.print("SQL Exception in deleteMessage:" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(conn);
            Debug.print("General Exception  in deleteMessage:" + e.getMessage());
        }
        return result;
    }
    //============================================ compose  new Message ===================================   
    public boolean composeMessage(String fromUserId, String emailId, String subject, String message, String attachFile) {
        Debug.print("MessageDAOImpl.composeMessage():" + emailId);
        PreparedStatement prepStmt = null;
        boolean result = false;
        String toUserId = selectUserId(emailId);
        Debug.print("MessageDAOImpl.composeMessage(): Email Id:" + emailId);
        Debug.print("MessageDAOImpl.composeMessage(): toUserId:" + toUserId);
        if (emailId != null && emailId.trim().length() != 0 || emailId.equalsIgnoreCase("null")) {
            Debug.print("MessageDAOImpl.composeMessage(): emailId:" + emailId);
            makeConnection();
            try {
                String insertStatement = "insert into " + DBHelper.USEA_MSG_MESSAGES + " (from_user_id, to_user_id, subject, message,file_path) " +
                        " values( ? , ? , ? , ? , ?)";
                Debug.print("Insert Statement: " + insertStatement);
                prepStmt = conn.prepareStatement(insertStatement);
                prepStmt.setString(1, fromUserId);
                prepStmt.setString(2, toUserId);
                prepStmt.setString(3, subject);
                prepStmt.setString(4, message);
                prepStmt.setString(5, attachFile);
                prepStmt.executeUpdate();
                prepStmt.close();
                releaseConnection(conn);

                result = true;

            } catch (SQLException sql) {

                releaseConnection(conn);
                Debug.print("SQL Exception in MessageDAOImpl.composeMessage():" + sql.getMessage());
            } catch (Exception e) {
                releaseConnection(conn);
                Debug.print("General Exception  in MessageDAOImpl.composeMessage():" + e.getMessage());
            }
        }
        return result;
    }

    //============================================ Assign new Task ===================================   
    public boolean newTask(String projectId, String taskTitle, String taskDesc, String taskFile, String assignedTo, Date dueDate, String taskStatus, String taskPriority, String assignedBy) {
        PreparedStatement prepStmt = null;
        boolean result = false;
        Debug.print("MessageDAOImpl.newTask(): Project Id:" + projectId);
        Debug.print("MessageDAOImpl.newTask(): Task Title:" + taskTitle);
        if (taskTitle != null && taskTitle.trim().length() != 0 || taskTitle.equalsIgnoreCase("null")) {
            Debug.print("MessageDAOImpl.newTask(): Task Title:" + taskTitle);
            makeConnection();
            try {
                String insertStatement = "insert into " + DBHelper.TASK_DETAILS + " (project_id, task_title, task_desc, task_file_path, assigned_to, due_date, task_status, task_priority, assigned_by) " +
                        " values( ? , ? , ? , ? , ?, ?, ?, ?, ?)";
                Debug.print("Insert Statement: " + insertStatement);
                prepStmt = conn.prepareStatement(insertStatement);
                prepStmt.setString(1, projectId);
                prepStmt.setString(2, taskTitle);
                prepStmt.setString(3, taskDesc);
                prepStmt.setString(4, taskFile);
                prepStmt.setString(5, assignedTo);
                prepStmt.setDate(6, DBHelper.toSQLDate(dueDate));
                prepStmt.setString(7, taskStatus);
                prepStmt.setString(8, taskPriority);
                prepStmt.setString(9, assignedBy);
                prepStmt.executeUpdate();
                prepStmt.close();
                releaseConnection(conn);
                result = true;
            } catch (SQLException sql) {
                releaseConnection(conn);
                Debug.print("SQL Exception in MessageDAOImpl.newTask():" + sql.getMessage());
            } catch (Exception e) {
                releaseConnection(conn);
                Debug.print("General Exception  in MessageDAOImpl.newTask():" + e.getMessage());
            }
        }
        return result;
    }

    public boolean isProjectExist(String projectTitle) {
        boolean result = true;
        makeConnection();
        try {
            String selectStatement = "select project_id from " + DBHelper.PROJECT_MASTER + " where project_title = ?";
            PreparedStatement prepStmt = conn.prepareStatement(selectStatement);
            prepStmt.setString(1, projectTitle);

            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()) {
                result = false;
            }
            rs.close();
            prepStmt.close();
            releaseConnection(conn);
        } catch (SQLException e) {
            releaseConnection(conn);
            Debug.print("Could not find any from MessageDAOImpl.isProjectExist" + e.getMessage());
        } catch (Exception e) {
            releaseConnection(conn);
            Debug.print("General Exception  in MessageDAOImpl.isProjectExist:" + e.getMessage());
        }
        Debug.print("MessageDAOImpl.isProjectExist():" + result);
        return result;
    }

    //============================================ Create New Project ===================================   
    public boolean newProject(String projectTitle, String projectDesc, Date projectStartDate, Date projectEndDate, String projectStatus, String createdBy) {
        PreparedStatement prepStmt = null;
        boolean result = false;
        Debug.print("MessageDAOImpl.newProject(): Project Title:" + projectTitle);
        if (projectTitle != null && projectTitle.trim().length() != 0 || projectTitle.equalsIgnoreCase("null")) {
            // Debug.print("MessageDAOImpl.newProject(): Project Title:" + projectTitle);
            makeConnection();
            try {
                String insertStatement = "insert into " + DBHelper.PROJECT_MASTER + " (project_title, project_desc, project_start_date, project_end_date, project_status, created_by) " +
                        " values( ? , ? , ? , ? , ?, ?)";
                Debug.print("Insert Statement: " + insertStatement);
                prepStmt = conn.prepareStatement(insertStatement);
                prepStmt.setString(1, projectTitle);
                prepStmt.setString(2, projectDesc);
                prepStmt.setDate(3, DBHelper.toSQLDate(projectStartDate));
                prepStmt.setDate(4, DBHelper.toSQLDate(projectEndDate));
                prepStmt.setString(5, projectStatus);
                prepStmt.setString(6, createdBy);
                prepStmt.executeUpdate();
                prepStmt.close();
                releaseConnection(conn);
                result = true;
            } catch (SQLException sql) {
                releaseConnection(conn);
                Debug.print("SQL Exception in MessageDAOImpl.newTask():" + sql.getMessage());
            } catch (Exception e) {
                releaseConnection(conn);
                Debug.print("General Exception  in MessageDAOImpl.newTask():" + e.getMessage());
            }
        }
        return result;
    }

    //============================================ get User id based on the Email Id ===================================       
    private String selectUserId(String emailId) {
        Debug.print("MessageDAOImpl.selectUserId():" + emailId);
        String userId = "";
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
        try {
            String selectStatement = " SELECT user_id from " + DBHelper.USEA_MSG_USER_MASTER + " where email_id= ?";
            prepStmt = conn.prepareStatement(selectStatement);
            prepStmt.setString(1, emailId);
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                userId = rs.getString(1);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(conn);
            Debug.print("MessageDAOImpl.selectUserId():" + userId);
        } catch (SQLException sql) {
            releaseConnection(conn);
            Debug.print("SQL Exception in MessageDAOImpl.selectUserId():" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(conn);
            Debug.print("General Exception  in MessageDAOImpl.selectUserId():" + e.getMessage());
        }
        return userId;
    }

    //============================================ get User id based on the Email Id ===================================       
    private String selectEmailId(String userId) {
        Debug.print("MessageDAOImpl.selectEmailId():" + userId);
        String emailId = "";
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
        try {
            String selectStatement = " SELECT email_id from " + DBHelper.USEA_MSG_USER_MASTER + " where user_id= ?";
            prepStmt = conn.prepareStatement(selectStatement);
            prepStmt.setString(1, userId);
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                emailId = rs.getString(1);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(conn);
            Debug.print("MessageDAOImpl.selectEmailId():" + emailId);
        } catch (SQLException sql) {
            releaseConnection(conn);
            Debug.print("SQL Exception in MessageDAOImpl.selectEmailId():" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(conn);
            Debug.print("General Exception  in MessageDAOImpl.selectEmailId():" + e.getMessage());
        }
        return emailId;
    }
    //============================================ Check Email id existence ===================================       
    public boolean findUserByEmailId(String emailId) {
        Debug.print("MessageDAOImpl.findUserByEmailId():" + emailId);
        HLCMessageVO msgVO = new HLCMessageVO();
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        boolean result = false;
        makeConnection();
        try {
            String selectStatement = " SELECT email_id  from " + DBHelper.USEA_MSG_USER_MASTER + " where email_id= ?";
            prepStmt = conn.prepareStatement(selectStatement);
            prepStmt.setString(1, emailId);
            rs = prepStmt.executeQuery();
            result = rs.next();
            rs.close();
            prepStmt.close();
            releaseConnection(conn);
            Debug.print("MessageDAOImpl.findUserByEmailId():" + result);
        } catch (SQLException sql) {
            releaseConnection(conn);
            Debug.print("SQL Exception in MessageDAOImpl.findUserByEmailId():" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(conn);
            Debug.print("General Exception  in MessageDAOImpl.findUserByEmailId():" + e.getMessage());
        }
        return result;
    }
    //=============================================Database Connection details=========================================    
    private void makeConnection() {
        Debug.print("MessageDAOImpl : makeConnection");
        try {
            InitialContext ic = new InitialContext();
            DataSource ds = (DataSource) ic.lookup(HLCEJBAllJNDIs.USEA_DB);
            conn = ds.getConnection();
        } catch (SQLException sqlExp) {
            Debug.print("Unable to connect to database. " + sqlExp.getMessage());
        } catch (Exception exp) {
            Debug.print("Exception while calling makeConnection. " + exp.getMessage());
        }
    }

    private void releaseConnection(Connection con) {
        Debug.print("MessageDAOImpl: releaseConnection");
        try {
            if (!con.isClosed()) {
                con.close();
            }
        } catch (SQLException sqlExp) {
            Debug.print("Unable to release Connection. " + sqlExp.getMessage());
        } catch (Exception ex) {
            Debug.print("Exception while releasing Connection: " + ex.getMessage());
        }
    }

    private String getNextGroupId() throws SQLException {
        Debug.print("MessageDAOImpl getNextGroupId");
        makeConnection();
        String nextId = null;
        try {
            String selectStatement = "select newid() as groupId";
            Debug.print("MessageDAOImpl getNextId:" + selectStatement);
            PreparedStatement prepSelect = conn.prepareStatement(selectStatement);
            ResultSet rs = prepSelect.executeQuery();
            rs.next();
            nextId = rs.getString(1);
            rs.close();
            prepSelect.close();
            releaseConnection(conn);
        } catch (SQLException sql) {
            releaseConnection(conn);
            Debug.print("SQL Exception in getNextGroupId:" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(conn);
            Debug.print("General Exception  in getNextGroupId:" + e.getMessage());
        }
        return nextId;
    }

    /**
     * Fetches all Projects and their associated details from database.
     * 
     * @param userId
     * @return
     */
    public List listProjects(String userId, String projectStatus) {
        Debug.print("HLCMessageDAOImpl : listProjects");
        List projectsList = new ArrayList();
        HLCProjectVO projectVO = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sqlQuery = null;
        makeConnection();
        try {

            // Fetch all projects of the user
            if (projectStatus != null && projectStatus.equalsIgnoreCase("all")) {
                sqlQuery = "Select project_id, project_title, project_desc, project_start_date, project_end_date, project_status from " + DBHelper.PROJECT_MASTER + " where created_by=? order by project_title";
                ps = conn.prepareStatement(sqlQuery);
                ps.setString(1, userId);

            // Fetches projects with the particular Project Status
            } else {
                sqlQuery = "Select project_id, project_title, project_desc, project_start_date, project_end_date, project_status from " + DBHelper.PROJECT_MASTER + " where created_by=? AND project_status=? order by project_title";
                ps = conn.prepareStatement(sqlQuery);
                ps.setString(1, userId);
                ps.setString(2, projectStatus);
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                projectVO = new HLCProjectVO();
                projectVO.setProjectId(rs.getString("project_id"));
                projectVO.setProjectTitle(rs.getString("project_title"));
                projectVO.setProjectDesc(rs.getString("project_desc"));
                projectVO.setProjectStartDate(rs.getDate("project_start_date"));
                projectVO.setProjectEndDate(rs.getDate("project_end_date"));
                projectVO.setProjectStatus(rs.getString("project_status"));
                projectsList.add(projectVO);
            }
            rs.close();
            ps.close();
            releaseConnection(conn);
        } catch (SQLException sqlException) {
            releaseConnection(conn);
            Debug.print("SQL exception in listProjects:", sqlException);
        } catch (Exception e) {
            releaseConnection(conn);
            Debug.print("General Exception in listProjects:", e.getMessage());
        }
          finally{
           try
           {
               conn.close();
               
           }
           catch(Exception e)
           {
               Debug.print("Exception closing connection");
               Debug.print(e.getMessage());
           }
       }
        return projectsList;
    }

    /**
     * Updates the database with Project values edited.
     * 
     * @param projectVOarg - An HLCProjectVO object
     * @return boolean
     */
    public boolean updateEditedProject(HLCProjectVO projectVOarg) {
        Debug.print("HLCMessageDAO : updateEditedProject");
        boolean result = false;
        PreparedStatement ps = null;
        HLCProjectVO projectVO = (HLCProjectVO) projectVOarg;

        makeConnection();
        try {
            ps = conn.prepareStatement("update " + DBHelper.PROJECT_MASTER + " set project_title=?, project_desc=?, project_start_date=?, project_end_date=?, project_status=? where project_id=?");
            ps.setString(1, projectVO.getProjectTitle());
            ps.setString(2, projectVO.getProjectDesc());
            ps.setDate(3, DBHelper.toSQLDate(projectVO.getProjectStartDate()));
            ps.setDate(4, DBHelper.toSQLDate(projectVO.getProjectEndDate()));
            ps.setString(5, projectVO.getProjectStatus());
            ps.setString(6, projectVO.getProjectId());
            ps.executeUpdate();
            result = true;
            ps.close();
            releaseConnection(conn);
        } catch (SQLException sqlException) {
            releaseConnection(conn);
            Debug.print("SQLException in updateEditedProject:" + sqlException.getMessage());
        } catch (Exception e) {
            releaseConnection(conn);
            Debug.print("General Exception in updateEditedProject:" + e.getMessage());
        }
        return result;
    }

    public List listTasksAssgnByMe(String assgnToUserId, String taskStatus, String userId) {
        Debug.print("HLCMessageDao listTasksAssgnByMe");
        List tasksList = new ArrayList();
        HLCTaskVO taskVO =null;
        PreparedStatement pt = null;
        String query = null;
        ResultSet rs = null;
        makeConnection();
        Debug.print("Inside HLCMessageDAOImpl: listTasksAssgnByMe");
       Debug.print("AssignToUserId "+ assgnToUserId);
       Debug.print("Task-status "+ taskStatus);
       Debug.print("UserId from Session "+ userId);
        try {
            if (taskStatus != null && assgnToUserId.equalsIgnoreCase("All")) {
                query = "select t.task_id,t.project_id,t.task_title,t.task_desc,t.task_file_path,t.assigned_to,t.due_date,t.task_status,t.task_priority,u.first_name,u.last_name,p.project_title from "+DBHelper.TASK_DETAILS+" as t, tblUserMaster as u, "+ DBHelper.PROJECT_MASTER+" as p where t.task_status=? AND p.project_id=t.project_id AND t.assigned_by=?  AND t.assigned_to=u.user_id order by p.project_title";
           
    
                pt = conn.prepareStatement(query);
                pt.setString(1, taskStatus);
                pt.setString(2, userId);
                rs = pt.executeQuery();
                while (rs.next()) {
                    Debug.print("Inside loop TaskId"+rs.getString(1)+"  ProjectId"+rs.getString(2));
                    taskVO=new HLCTaskVO();
                    taskVO.setTaskId(rs.getString(1));
                    taskVO.setProjectId(rs.getString(2));
                    taskVO.setTaskTitle(rs.getString(3));
                    taskVO.setTaskDescription(rs.getString(4));
                    taskVO.setTaskFilePath(rs.getString(5));
                    taskVO.setAssignedTo(rs.getString(6));
                    taskVO.setDueDate(rs.getDate(7));
                    taskVO.setTaskStatus(rs.getString(8));
                    taskVO.setTaskPriority(rs.getString(9));
                    taskVO.setFirstName(rs.getString(10));
                    taskVO.setLastName(rs.getString(11));
                    taskVO.setProjectTitle(rs.getString(12));
                    tasksList.add(taskVO);

                }
            } else if (assgnToUserId != null && !(assgnToUserId.equalsIgnoreCase("All")) ) {
                    query = "select t.task_id,t.project_id,t.task_title,t.task_desc,t.task_file_path,t.assigned_to,t.due_date,t.task_status,t.task_priority,u.first_name,u.last_name,p.project_title from " + DBHelper.TASK_DETAILS + " as t, " + DBHelper.USEA_MMS_USERMASTER + " as u, "+DBHelper.PROJECT_MASTER+" as p where t.task_status=? AND p.project_id=t.project_id AND t.assigned_by=? AND t.assigned_to=? AND t.assigned_to=u.user_id order by p.project_title";
                     pt = conn.prepareStatement(query);
                    pt.setString(1, taskStatus);
                    pt.setString(2, userId);
                    pt.setString(3, assgnToUserId);
                    rs=pt.executeQuery();
                    while (rs.next()) {
                        taskVO=new HLCTaskVO();
                        
                        taskVO.setTaskId(rs.getString(1));
                        taskVO.setProjectId(rs.getString(2));
                        taskVO.setTaskTitle(rs.getString(3));
                        taskVO.setTaskDescription(rs.getString(4));
                        taskVO.setTaskFilePath(rs.getString(5));
                        taskVO.setAssignedTo(rs.getString(6));
                        taskVO.setDueDate(rs.getDate(7));
                        taskVO.setTaskStatus(rs.getString(8));
                        taskVO.setTaskPriority(rs.getString(9));
                        taskVO.setFirstName(rs.getString(10));
                        taskVO.setLastName(rs.getString(11));
                        taskVO.setProjectTitle(rs.getString(12));
                        tasksList.add(taskVO);
                    }

    
            }
           
            releaseConnection(conn);
        } catch (SQLException e) {
            releaseConnection(conn);
            Debug.print("SQL Exception occured while fetching task details ");
            Debug.print(e.getMessage());
        } catch (Exception e) {
            releaseConnection(conn);
            Debug.print("SQL Exception occured while fetching task details ");
            e.printStackTrace();
        }
      

        return tasksList;

    }
    
    public List listTasksAssgnToMe(String taskStatus,String userId)
    {
        Debug.print("HLCMessageDAO listTasksAssgnToMe");
         List tasksList = new ArrayList();
        HLCTaskVO taskVO = null;
        String query=null;
        PreparedStatement pt=null;
        ResultSet rs=null;
        makeConnection();
        try
        {
        if(taskStatus!=null)
        {
           query="select p.project_title, t.task_id, t.task_title, t.task_desc, t.task_file_path, t.task_status, t.task_priority, t.due_date from " + DBHelper.TASK_DETAILS + " as t, " + DBHelper.PROJECT_MASTER + " as p  where t.project_id=p.project_id  AND t.task_status=? AND t.assigned_to=? order by t.task_title";
           pt=conn.prepareStatement(query);
           pt.setString(1,taskStatus);
           pt.setString(2,userId);
           rs=pt.executeQuery();
           while(rs.next())
           {
               taskVO=new HLCTaskVO();
               taskVO.setProjectTitle(rs.getString(1));               
               taskVO.setTaskId(rs.getString(2));
               taskVO.setTaskTitle(rs.getString(3));
               taskVO.setTaskDescription(rs.getString(4));
               taskVO.setTaskFilePath(rs.getString(5));
               taskVO.setTaskStatus(rs.getString(6));
               taskVO.setTaskPriority(rs.getString(7));
               taskVO.setDueDate(rs.getDate(8));
               
               tasksList.add(taskVO);
           }
        }
        releaseConnection(conn);
        }
        catch(Exception e)
        {
            releaseConnection(conn);
            Debug.print("Execption while fetching records :"+e.getMessage());
            
        }
         
       
        return tasksList;
        
    }
    public boolean updateTask(HLCTaskVO taskvo) 
    {
        boolean result=false;
        HLCTaskVO vo=(HLCTaskVO)taskvo;
        String query=null;
        PreparedStatement pt=null;
        makeConnection();
        try
        {
            query="update "+DBHelper.TASK_DETAILS+" set project_id=?,task_title=?,task_desc=?,task_file_path=?,assigned_to=?,due_date=?,task_status=?,task_priority=? where task_id=?";
            
            pt=conn.prepareStatement(query);
            pt.setString(1,vo.getProjectId());
            pt.setString(2,vo.getTaskTitle());
            pt.setString(3,vo.getTaskDescription());
            pt.setString(4,vo.getTaskFilePath());
            pt.setString(5,vo.getAssignedTo());
            pt.setDate(6,DBHelper.toSQLDate(vo.getDueDate()));
            pt.setString(7,vo.getTaskStatus());
            pt.setString(8,vo.getTaskPriority());
            pt.setString(9,vo.getTaskId());
            pt.executeUpdate();
            result=true;
            pt.close();
            releaseConnection(conn);
        }
        catch(SQLException e)
        {
           releaseConnection(conn);
           Debug.print("Exception during HLCMessageDAO : updateTask "+e.getMessage()); 
        }
        catch(Exception e)
        {
            releaseConnection(conn);
            Debug.print("Exception during HLCMessageDAO : updateTask "+e.getMessage());
        }
         
        return result;
    }
    
   public boolean updateTaskStatus(String taskStatus,String taskId)
   {
       boolean result=false;
       String query=null;
       PreparedStatement pt=null;
      
       makeConnection();
       try
       {
           query="update "+DBHelper.TASK_DETAILS+" set task_status=? where task_id=?";
           pt=conn.prepareStatement(query);
           pt.setString(1,taskStatus);
           pt.setString(2,taskId);
           pt.executeUpdate();
           result=true;
           pt.close();
           releaseConnection(conn);
       }
       catch(SQLException f)
       {
           releaseConnection(conn);
           Debug.print("SQLException occured while HLCMessageDAO : updateTaskStatus");
           Debug.print(f.getMessage());
           
       }
       catch(Exception f)
       {
           releaseConnection(conn);
           Debug.print("Exception occured while HLCMessageDAO : updateTaskStatus" + f.getMessage());
       }
       return result;
   }
   public Map  listUsersWithAssgnTasks(String userId) 
   {
       Map userList=new HashMap();
       String query=null;
       PreparedStatement pt=null;
       ResultSet rs=null;
       makeConnection();
       try
       {
           query="SELECT u.first_name,u.last_name,u.user_id from tblTaskDetails as t JOIN tblUserMaster as u on u.user_id=t.assigned_to AND t.assigned_by=?";
           pt=conn.prepareStatement(query);
           pt.setString(1,userId);
           rs=pt.executeQuery();
          while(rs.next())
          {
              userList.put(rs.getString(3),rs.getString(1)+" "+rs.getString(2));
              
          }
           
          
           releaseConnection(conn);
       }
       catch(SQLException f)
       {
           releaseConnection(conn);
           Debug.print("SQLException occured while HLCMessageDAO : updateTaskStatus"+f.getMessage());
           
       }
       return userList;
   }
    
      /**
     * Fetches all users with true active status from database.
     * 
     * @return - list of Map containing userId, firstName and lastName
     */
    public List getActiveUsers() {

        Debug.print("HLCMessageDAOImpl : getActiveUsers");
        List usersList = new ArrayList();
        HashMap userMap = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sqlQuery = null;
        makeConnection();
        try {

            // Fetch all users having active_status true
            sqlQuery = "Select user_id, first_name, last_name from " + DBHelper.USEA_MMS_USERMASTER + " where active_status='true' order by first_name";
            ps = conn.prepareStatement(sqlQuery);
            rs = ps.executeQuery();
            userMap = new HashMap();
            while (rs.next()) {
                userMap = new HashMap();
                userMap.put("userId", rs.getString("user_id"));
                userMap.put("firstName", rs.getString("first_name"));
                userMap.put("lastName", rs.getString("last_name"));
                usersList.add(userMap);
            }
            rs.close();
            ps.close();
            releaseConnection(conn);
        } catch (SQLException sqlException) {
            releaseConnection(conn);
            Debug.print("SQL exception in getActiveUsers:", sqlException);
        } catch (Exception e) {
            releaseConnection(conn);
            Debug.print("General Exception in getActiveUsers:", e.getMessage());
        }
        return usersList;
    }

    /**
     * Fetches all Projects from database.
     * 
     * @return - list of Map containing projectId and projectTitle
     */
    public List getAllProjects() {
        Debug.print("HLCMessageDAOImpl : getAllProjects");
        List projectsList = new ArrayList();
        HashMap projectMap = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sqlQuery = null;
        makeConnection();
        try {

            // Fetch all users having active_status true
            sqlQuery = "Select project_id, project_title from " + DBHelper.PROJECT_MASTER + " order by project_title";
            ps = conn.prepareStatement(sqlQuery);
            rs = ps.executeQuery();
            projectMap = new HashMap();
            while (rs.next()) {
                projectMap = new HashMap();
                projectMap.put("projectId", rs.getString("project_id"));
                projectMap.put("projectTitle", rs.getString("project_title"));
                projectsList.add(projectMap);
            }
            rs.close();
            ps.close();
            releaseConnection(conn);
        } catch (SQLException sqlException) {
            releaseConnection(conn);
            Debug.print("SQL exception in getAllProjects:", sqlException);
        } catch (Exception e) {
            releaseConnection(conn);
            Debug.print("General Exception in getAllProjects:", e.getMessage());
        }
        return projectsList;
    }
}
