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

import com.hlccommon.util.Debug;
import com.hlccommon.util.HLCMessageVO;
import com.hlccommon.util.HLCProjectVO;
import com.hlccommon.util.HLCTaskVO;
import com.hlcmsg.dao.HLCMessageDAO;
import com.hlcmsg.dao.HLCMessageDAOImpl;
import javax.ejb.*;
import java.rmi.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * This is the bean class for the MessageSessionBean enterprise bean.
 * Created Sep 26, 2006 11:10:39 AM
 * @author suresh
 */
public class HLCMessageSessionBean implements SessionBean, HLCMessageSessionRemoteBusiness {

    private SessionContext context;
    private static HLCMessageDAO dao = null;

    // <editor-fold defaultstate="collapsed" desc="EJB infrastructure methods. Click the + sign on the left to edit the code.">
    // TODO Add code to acquire and use other enterprise resources (DataSource, JMS, enterprise bean, Web services)
    // TODO Add business methods or web service operations
    /**
     * @see javax.ejb.SessionBean#setSessionContext(javax.ejb.SessionContext)
     */
    public void setSessionContext(SessionContext aContext) {
        Debug.print("MessageSessionBean.setSessionContext()");
        context = aContext;
    }

    /**
     * @see javax.ejb.SessionBean#ejbActivate()
     */
    public void ejbActivate() {
        Debug.print("MessageSessionBean.ejbActivate()");

    }

    /**
     * @see javax.ejb.SessionBean#ejbPassivate()
     */
    public void ejbPassivate() {
        Debug.print("MessageSessionBean.ejbPassivate()");
    }

    /**
     * @see javax.ejb.SessionBean#ejbRemove()
     */
    public void ejbRemove() {
        Debug.print("MessageSessionBean.ejbRemove()");
    }
    // </editor-fold>
    /**
     * See section 7.10.3 of the EJB 2.0 specification
     * See section 7.11.3 of the EJB 2.1 specification
     */
    public void ejbCreate() {
        dao = (HLCMessageDAO) new HLCMessageDAOImpl();
        Debug.print("ejbCreate() Called:");
    }

    //==============================Mapping  Permission  with Privilege ==========================================================
    public String createGroup(String groupName, String userId, String groupType) throws RemoteException {
        Debug.print("MessageSessionBean createGroup");
        String groupStatus = "";
        //  if(dao.isAccessNameExist(accessName)){
        //    Stringdao.insertPermissionPrivilegeMapping(groupName, userId, groupType);
        //  }
        return null;
    }

    //============================================ show Inbox based on user Id ===================================    
    public ArrayList showInbox(String toUserId, int startIndex, int endIndex) throws RemoteException {
        Debug.print("MessageSessionBean showInbox");
        ArrayList results = new ArrayList();
        results = dao.showInbox(toUserId, startIndex, endIndex);
        return results;
    }

    public HLCMessageVO showMessage(String messageId) throws RemoteException {
        Debug.print("MessageSessionBean showMessage");
        HLCMessageVO msgVo = new HLCMessageVO();
        if (messageId != null) {
            msgVo = dao.showMessage(messageId);
        }
        return msgVo;
    }

    public boolean deleteMessages(String messageIds) throws RemoteException {
        Debug.print("MessageSessionBean deleteMessages");
        boolean result = dao.deleteMessages(messageIds);
        return result;
    }

    public boolean deleteMessage(String messageId) throws RemoteException {
        Debug.print("MessageSessionBean deleteMessage");
        boolean result = dao.deleteMessage(messageId);
        return result;
    }

    public boolean findUserByEmailId(String emailId) throws RemoteException {
        Debug.print("MessageSessionBean findUserByEmailId");
        boolean result = dao.deleteMessages(emailId);
        return result;
    }

    public int newMessagesCount(String toUserId) throws RemoteException {
        Debug.print("MessageSessionBean newMessagesCount");
        int count = 0;
        if (toUserId != null) {
            count = dao.newMessagesCount(toUserId);
        }
        return count;
    }

    public int totalMessageCount(String toUserId) throws RemoteException {
        Debug.print("MessageSessionBean totalMessagesCount");
        int count = 0;
        if (toUserId != null) {
            count = dao.newMessagesCount(toUserId);
        }
        return count;
    }

    public boolean composeMessage(String fromUserId, String toUserId, String subject, String message, String attachFile) throws RemoteException {
        Debug.print("MessageSessionBean.editMappingPermissionToPrivilege()");
        boolean result = dao.composeMessage(fromUserId, toUserId, subject, message, attachFile);
        return result;
    }

    public boolean newTask(String projectId, String taskTitle, String taskDesc, String taskFile, String assignedTo, Date dueDate, String taskStatus, String taskPriority, String assignedBy) throws RemoteException {
        Debug.print("MessageSessionBean.newTask()");
        boolean result = dao.newTask(projectId, taskTitle, taskDesc, taskFile, assignedTo, dueDate, taskStatus, taskPriority, assignedBy);
        return result;
    }

    public boolean createProject(String projectTitle, String projectDesc, Date projectStartDate, Date projectEndDate, String projectStatus, String createdBy) throws RemoteException {
        Debug.print("MessageSessionBean.createProject()");
        boolean result = false;
        if (projectTitle != null && projectTitle.trim().length() != 0) {
            if (dao.isProjectExist(projectTitle)) {
                result = dao.newProject(projectTitle, projectDesc, projectStartDate, projectEndDate, projectStatus, createdBy);
            }
        }
        // boolean result = dao.createProject(projectTitle, projectDesc, projectStartDate, projectEndDate, projectStatus, createdBy);
        return result;
    }

    /**
     * Interacts with DAO to fetch all Projects and their details associated to the specified User ID.
     *
     * @param userId
     * @return projectList - List of HLCProjectVO objects.
     * @throws java.rmi.RemoteException
     */
    public List listProjects(String userId, String projectStatus) throws RemoteException {
        Debug.print("MessageSessionBean : listProjects");
        List projectsList = null;

        projectsList = dao.listProjects(userId, projectStatus);
        return projectsList;
    }

    /**
     * Interacts with DAO to update the Project with values edited.
     *  
     * @param projectVO
     * @return result - boolean
     * @throws java.rmi.RemoteException
     */
    public boolean updateEditedProject(HLCProjectVO projectVO) throws RemoteException {
        Debug.print("MessageSessionBean : updateEditedProject");
        boolean result = false;
        result = dao.updateEditedProject(projectVO);
        return result;
    }


    /**
     * Interacts with DAO to get the list of all users with Active Status as true
     * 
     * @return users - List of HashMap containing users
     * @throws java.rmi.RemoteException
     */
    public List getActiveUsers() throws RemoteException {
        Debug.print("MessageSessionBean : getActiveUsers");
        List users = dao.getActiveUsers();
        return users;
    }

    /**
     * Interacts with DAO to get the list of all Projects
     * 
     * @return users - List of HashMap containing projects.
     * @throws java.rmi.RemoteException
     */
    public List getAllProjects() throws RemoteException {
        Debug.print("MessageSessionBean : getAllProjects");
        List projects = dao.getAllProjects();
        return projects;
    }
    
    public List listTasksAssgnByMe(String assgnToUserId,String taskStatus,String userId) throws RemoteException
    {
        Debug.print("MessageSessionBean :listTasksAssgnByMe");
          List tasksList=null;
          tasksList=dao.listTasksAssgnByMe(assgnToUserId,taskStatus,userId);
          return tasksList;
    }
    public List listTasksAssgnToMe(String taskStatus,String userId) throws RemoteException
    {
        Debug.print("MessageSessionBean :listTaskaAssgnToMe");
        List tasksList=null;
        tasksList=dao.listTasksAssgnToMe(taskStatus,userId);
        return tasksList;
    }
      public boolean updateTask(HLCTaskVO vo) throws RemoteException
      {
          Debug.print("MessageSessionBean : updateTask");
          boolean result=false;
          result=dao.updateTask(vo);
          return result;
      }
      public boolean updateTaskStatus(String taskStatus,String taskId) throws RemoteException
      {
          Debug.print("MessageSessionBean : updateTaskStatus");
          boolean result=false;
          result=dao.updateTaskStatus(taskStatus,taskId);
          return result;
      }
      public Map listUsersWithAssgnTasks(String userId) throws RemoteException
      {
          Debug.print("MessageSessionBean: findAssgnIdByName");
          Map  assgnToIdName=dao.listUsersWithAssgnTasks(userId);
          return assgnToIdName;
      }
}
