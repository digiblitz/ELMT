/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.message.actionform;

import org.apache.struts.upload.FormFile;
import org.apache.struts.action.ActionForm;

/**
 *
 * @author Rupinder
 */
public class TaskActionForm extends ActionForm {

    private String taskTitle = null;
    private String projectId = null;
    private String taskDesc = null;
    private String assignTo = null;
    private String dueDate = null;
    private String taskPriority = null;
    private String taskStatus = null;
    private FormFile attachment = null;

    public String getAssignTo() {
        return assignTo;
    }

    public void setAssignTo(String assignTo) {
        this.assignTo = assignTo;
    }

    public FormFile getAttachment() {
        return attachment;
    }

    public void setAttachment(FormFile attachment) {
        this.attachment = attachment;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public String getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(String taskPriority) {
        this.taskPriority = taskPriority;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    /**
     *
     */
    public TaskActionForm() {
        super();
    // TODO Auto-generated constructor stub
    }
}
