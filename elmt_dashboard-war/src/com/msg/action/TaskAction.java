/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msg.action;

import com.hlccommon.util.Debug;
import com.hlccommon.util.HLCTaskVO;
import com.hlcmsg.groups.HLCMessageSessionRemote;
import com.hlcmsg.groups.HLCMessageSessionRemoteHome;
import com.message.actionform.TaskActionForm;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.upload.FormFile;
import org.apache.struts.util.MessageResources;

/**
 *
 * @author Rupinder
 */
public class TaskAction extends Action {

    private String forwardName = null;

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // If the action is not called by the input form, display the input form to get values.   
        if (request.getParameter("task") == null) {
            Debug.print("TaskAction : execute -- Displaying input form");

            forwardName = "input";

        // If the action is called as a result of submission of the input form, go ahead to process the values.
        } else if (request.getParameter("task") != null) {

            MessageResources mr = getResources(request);

            String namingfactory = mr.getMessage("ejbclient.namingfactory");
            String contextfactory = mr.getMessage("ejbclient.contextfactory");
            String urlprovider = mr.getMessage("ejbclient.urlprovider");
            String lookupip = mr.getMessage("ejbclient.ip");
            String jndiname = mr.getMessage("jndi.messages");
            Debug.print("TaskAction.jndiname:" + jndiname);
            System.setProperty(namingfactory, contextfactory);
            System.setProperty(urlprovider, lookupip);
            Context jndiContext = new InitialContext();
            Object objref = jndiContext.lookup(jndiname);

            HLCMessageSessionRemoteHome sessionRemoteHome = (HLCMessageSessionRemoteHome) PortableRemoteObject.narrow(objref, HLCMessageSessionRemoteHome.class);
            HLCMessageSessionRemote sessionRemote = sessionRemoteHome.create();

            TaskActionForm taskActionForm = (TaskActionForm) form;

            FormFile attachedFile =(FormFile) taskActionForm.getAttachment();
            String filePath = mr.getMessage("task.attachment.path.windows");
            String fileName = null;

            String taskTitle = null;
            String projectId = null;
            String taskDesc = null;
            String assignTo = null;
            Date dueDate = null;
            String taskPriority = null;
            String taskStatus;
            SimpleDateFormat dueDateFormat = new SimpleDateFormat("MM/dd/yyyy");
            taskTitle = taskActionForm.getTaskTitle();
            projectId = taskActionForm.getProjectId();
            taskDesc = taskActionForm.getTaskDesc();
            assignTo = taskActionForm.getAssignTo();
            dueDate = dueDateFormat.parse(taskActionForm.getDueDate());
            taskPriority = taskActionForm.getTaskPriority();
            taskStatus = taskActionForm.getTaskStatus();

            HttpSession session = request.getSession(true);
            String userId = (String) session.getAttribute("userId");
             Debug.print(" Attaced File Name---------------"+attachedFile.getFileName());
            if (attachedFile != null) {
                fileName = attachedFile.getFileName();
                String imageFilePath = filePath + "\\" + fileName;
                Debug.print("File location= " + filePath);
                BufferedOutputStream outFile = null;

                try {
                    //creating the dir if not exists
                    File fDir = new File(filePath);
                    if (!fDir.exists()) {
                        if (!fDir.mkdir()) {
                            throw new IOException("Cannot create dir for attachment file");
                        }
                    }
                    File newFile = new File(imageFilePath);
                    Debug.print("NewFilePath =" + newFile.getAbsolutePath());
                    outFile = new BufferedOutputStream(
                            new FileOutputStream(newFile));
                    outFile.write(attachedFile.getFileData());

                } catch (IOException ie) {
                    Debug.print("Error while writing into the file" + ie.getMessage());
                } finally {
                    if (outFile != null) {
                        outFile.flush();
                        outFile.close();
                    }
                }
            }
            // if request is for creating a new task
            if (request.getParameter("task").equalsIgnoreCase("createTask")) {
                sessionRemote.newTask(projectId, taskTitle, taskDesc, fileName, assignTo, dueDate, taskStatus, taskPriority, userId);

                forwardName = "taskCreatedSuccess";
            // if request is for editing of existing task
            } else if (request.getParameter("task").equalsIgnoreCase("editTask")) {

                String taskId = request.getParameter("taskId").toString();
                HLCTaskVO taskVO = new HLCTaskVO();
                taskVO.setTaskId(taskId);
                taskVO.setAssignedBy(userId);
                taskVO.setAssignedTo(assignTo);
                taskVO.setDueDate(dueDate);
                taskVO.setProjectId(projectId);
                taskVO.setTaskDescription(taskDesc);
                taskVO.setTaskFilePath(fileName);
                taskVO.setTaskPriority(taskPriority);
                taskVO.setTaskStatus(taskStatus);
                taskVO.setTaskTitle(taskTitle);

                boolean result=sessionRemote.updateTask(taskVO);
                Debug.print("Task updated sucessfully "+result);
                forwardName = "taskUpdatedSuccess";
            }
        }
        return mapping.findForward(forwardName);
    }
}
