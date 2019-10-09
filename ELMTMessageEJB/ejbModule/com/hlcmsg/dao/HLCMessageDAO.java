/*
 * MessageDAO.java
 *
 * Created on September 26, 2006, 11:06 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmsg.dao;
import com.hlccommon.util.HLCMessageVO;
import com.hlccommon.util.HLCProjectVO;
import com.hlccommon.util.HLCTaskVO;
import java.util.*;
import java.util.List;

/**
 *
 * @author suresh
 */
public interface HLCMessageDAO {
    //=====================================Messaging =======
    public ArrayList showInbox(String toUserId, int startIndex, int endIndex);    
    public HLCMessageVO showMessage(String messageId);
    public boolean deleteMessages(String messageIds);
    public boolean deleteMessage(String messageId);
    public boolean composeMessage(String fromUserId, String toUserId, String subject, String message, String attachFile);
    //public boolean composeMessage(String fromUserId, String toUserId, String subject, String message);
    public boolean findUserByEmailId(String emailId);
    public int newMessagesCount(String userId);
    public int totalMessageCount(String userId);
    //public String insertGroupDetails(String groupName, String userId, String groupType);
    public boolean newTask(String projectId, String taskTitle, String taskDesc, String taskFile, String assignedTo, Date dueDate, String taskStatus, String taskPriority, String assignedBy);
    public boolean isProjectExist(String projectTitle);
    public boolean newProject(String projectTitle, String projectDesc, Date projectStartDate, Date projectEndDate, String projectStatus, String createdBy);
    public List listProjects(String userId, String projectStatus);
    public boolean updateEditedProject(HLCProjectVO projectVO);
    public List getActiveUsers();
    public List getAllProjects();
    public List listTasksAssgnByMe(String assgnToUserId,String taskStatus,String userId);
    public List listTasksAssgnToMe(String taskStatus,String userId);
    public boolean updateTask(HLCTaskVO vo);
    public boolean updateTaskStatus(String taskStatus,String taskId);
    public Map listUsersWithAssgnTasks(String userId);
}
