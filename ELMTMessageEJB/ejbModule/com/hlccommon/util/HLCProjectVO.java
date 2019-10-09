/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlccommon.util;

import java.util.Date;

/**
 *
 * @author Rupinder
 */
public class HLCProjectVO {

    
    String projectId = null;
    String projectTitle = null;
    String projectDesc = null;
    Date projectStartDate = null;
    Date projectEndDate = null;
    String projectStatus = null;

    public HLCProjectVO() {
    }

    public HLCProjectVO(String projectId, String projectTitle, String projectDesc, Date projectStartDate, Date projectEndDate, String projectStatus) {
    }
    
    public String getProjectDesc() {
        return projectDesc;
    }

    public void setProjectDesc(String projectDesc) {
        this.projectDesc = projectDesc;
    }

    public Date getProjectEndDate() {
        return projectEndDate;
    }

    public void setProjectEndDate(Date projectEndDate) {
        this.projectEndDate = projectEndDate;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public Date getProjectStartDate() {
        return projectStartDate;
    }

    public void setProjectStartDate(Date projectStartDate) {
        this.projectStartDate = projectStartDate;
    }

    public String getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }


}