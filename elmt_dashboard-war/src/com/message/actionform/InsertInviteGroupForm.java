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
package com.message.actionform;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class InsertInviteGroupForm extends ActionForm
{

    public InsertInviteGroupForm()
    {
    }

    public String getselect()
    {
        return select;
    }

    public void setselect(String select)
    {
        this.select = select;
    }

    public String[] getEmailIds()
    {
        return emailIds;
    }

    public void setEmailIds(String emailIds[])
    {
        this.emailIds = emailIds;
    }

    public String getinvSubject()
    {
        return invSubject;
    }

    public void setinvSubject(String invSubject)
    {
        this.invSubject = invSubject;
    }

    public String getmsg()
    {
        return msg;
    }

    public void setmsg(String msg)
    {
        this.msg = msg;
    }
    
    public String getemailId()
    {
        return emailId;
    }

    public void setemailId(String emailId)
    {
        this.emailId = emailId;
    }

    public String getsubject()
    {
        return subject;
    }

    public void setsubject(String subject)
    {
        this.subject = subject;
    }

    public String getbody()
    {
        return body;
    }

    public void setbody(String body)
    {
        this.body = body;
    }

    public List getResult()
    {
        return result;
    }

    public void setResult(List result)
    {
        this.result = result;
    }

    public List getInvResult()
    {
        return invResult;
    }

    public void setInvResult(List invResult)
    {
        this.invResult = invResult;
    }

    public void reset(ActionMapping mapping, HttpServletRequest request)
    {
        select = null;
        emailIds = null;
        msg = null;
        groupName = null;
        emailId = null;
        subject = null;
        body = null;
        result = null;
        invResult = null;
        groupId = null;
        noEmail = null;
    }

    public String toString()
    {
        StringBuffer buffer = (new StringBuffer()).append(" select :" + select + "\t\t").
                append(" Email[] :" + emailIds + "\t\t").append(" msg :" + msg + "\t\t")
                .append(" groupId :" + groupId + "\t\t")
                .append(" groupName :" + groupName + "\t\t").append(" emailId :" + emailId + "\t\t").append("noEmail ="+noEmail).
                append(" subject :" + subject + "\t\t").append(" body :" + body + "\t\t");
        return buffer.toString();
    }

    private String select;
    private String emailIds[];
    private String invSubject;
    private String msg;
    private List invResult;
    private String groupNames[];
    private String groupName;
    private String groupId;
    private String emailId;
    private String subject;
    private String body;
    private List result;

    private String noEmail;
   

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setNoEmail(String noEmail) {
        this.noEmail = noEmail;
    }

    public String getNoEmail() {
        return noEmail;
    }
}
