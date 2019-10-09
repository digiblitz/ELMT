/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.message.actionform;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author vidhya
 */
public class ProjectActionForm extends ActionForm {
    
   private String projectTitle=null;

   private String projectDescription=null;
   
   private String startDate=null;
   
   private String endDate=null;
   
   private String projectStatus=null;

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
  
       
  public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
       ActionErrors errors = new ActionErrors();
       if (getProjectTitle()== null || getProjectTitle().length() < 1) 
           errors.add("projectNameRequired", new ActionMessage("error.projectname.required"));
           // TODO: add 'error.name.required' key to your resources
         if(getProjectDescription()==null || getProjectDescription().length()<1)
              errors.add("projectDescriptionRequired",new  ActionMessage("error.projectdescription.required"));
       if(getStartDate()==null)
            errors.add("startdaterequired",new ActionMessage("error.startdate.required"));
       
        if(getEndDate()==null)
            errors.add("enddaterequired",new ActionMessage("error.enddate.required"));
       
        if(getProjectStatus() ==null || getProjectStatus().length() <1)
            errors.add("projectstatusrequired",new ActionMessage("error.projectstatus.required"));
       return errors;
   }
}
