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
package com.hlcmsg.groups;

import com.hlccommon.util.HLCMessageVO;
import com.hlccommon.util.HLCProjectVO;
import com.hlccommon.util.HLCTaskVO;
import javax.ejb.EJBObject;
import java.rmi.*;
import java.util.*;

/**
 * This is the remote interface for MessageSession enterprise bean.
 */
public interface HLCMessageSessionRemote extends EJBObject, HLCMessageSessionRemoteBusiness {
    //=====================================Messaging =======
    public ArrayList showInbox(String toUserId, int startIndex, int endIndex) throws RemoteException;    
    public HLCMessageVO showMessage(String messageId) throws RemoteException;
    public boolean deleteMessages(String messageIds) throws RemoteException;
    public boolean deleteMessage(String messageId) throws RemoteException;
    public boolean composeMessage(String fromUserId, String toUserId, String subject, String message, String attachFile) throws RemoteException;
    public boolean newTask(String projectId, String taskTitle, String taskDesc, String taskFile, String assignedTo, Date dueDate, String taskStatus, String taskPriority, String assignedBy) throws RemoteException;
    public boolean createProject(String projectTitle, String projectDesc, Date projectStartDate, Date projectEndDate, String projectStatus, String createdBy) throws RemoteException;
    public List listProjects(String userId, String projectStatus) throws RemoteException;
    public boolean updateEditedProject(HLCProjectVO projectVO) throws RemoteException;
    //public boolean composeMessage(String fromUserId, String toUserId, String subject, String message) throws RemoteException;
    public boolean findUserByEmailId(String emailId) throws RemoteException;
    public int newMessagesCount(String toUserId) throws RemoteException;
    public int totalMessageCount(String toUserId) throws RemoteException;
    public List getActiveUsers() throws RemoteException;
    public List getAllProjects() throws RemoteException;
    public List listTasksAssgnByMe(String assgnToUserId,String taskStatus,String userId) throws RemoteException;
    public List listTasksAssgnToMe(String taskStatus,String userId) throws RemoteException;
    public boolean updateTask(HLCTaskVO vo) throws RemoteException;
    public boolean updateTaskStatus(String taskStatus,String taskId) throws RemoteException;
    public Map listUsersWithAssgnTasks(String userId) throws RemoteException; 
}
