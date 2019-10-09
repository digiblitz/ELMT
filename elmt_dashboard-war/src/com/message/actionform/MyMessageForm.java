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

import com.hlcmsg.util.HLCGroupMessage;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class MyMessageForm extends ActionForm
{

    public MyMessageForm()
    {
    }

    public String getSubject()
    {
        return subject;
    }

    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    public String getGroupName()
    {
        return groupName;
    }

    public void setGroupName(String groupName)
    {
        this.groupName = groupName;
    }

    public void reset(ActionMapping mapping, HttpServletRequest request)
    {
        groupId = null;
        groupName = null;
        subject = null;
        results = null;
        grpname = null;
        grpResults = null;
        grpDetails = null;
        messageId = null;
        parentMessageId = null;
        postedMsgs = null;
    }

    public String toString()
    {
        StringBuffer buffer = (new StringBuffer()).append(" subject :" + subject + "\t\t").append(" groupName :" + groupName + "\t\t").append(" groupId :" + groupId + "\t\t").append(" results :" + results + "\t\t").append(" grpname :" + grpname + "\t\t").append(" messageId :" + messageId + "\t\t").append(" grpDetails :" + grpDetails + "\t\t").append(" grpResults :" + grpResults + "\t\t").append(" grpResults :" + parentMessageId + "\t\t");
        return buffer.toString();
    }

    public String getGroupId()
    {
        return groupId;
    }

    public void setGroupId(String groupId)
    {
        this.groupId = groupId;
    }

    public List getResult()
    {
        return results;
    }

    public void setResult(List results)
    {
        this.results = results;
    }

    public List getGrpname()
    {
        return grpname;
    }

    public void setGrpname(List grpname)
    {
        this.grpname = grpname;
    }

    public List getGrpResults()
    {
        return grpResults;
    }

    public void setGrpResults(List grpResults)
    {
        this.grpResults = grpResults;
    }

    public List getGrpDetails()
    {
        return grpDetails;
    }

    public void setGrpDetails(List grpDetails)
    {
        this.grpDetails = grpDetails;
    }

    public String getMessageId()
    {
        return messageId;
    }

    public void setMessageId(String messageId)
    {
        this.messageId = messageId;
    }

    public void setGroupMessage(HLCGroupMessage groupMessage)
    {
        this.groupMessage = groupMessage;
    }

    public HLCGroupMessage getGroupMessage()
    {
        return groupMessage;
    }

    public String getParentMessageId()
    {
        return parentMessageId;
    }

    public void setParentMessageId(String parentMessageId)
    {
        this.parentMessageId = parentMessageId;
    }

    private String subject;
    private String groupName;
    private String groupId;
    private List results;
    private List grpname;
    private List grpResults;
    private List grpDetails;
    private HLCGroupMessage groupMessage;
    private String messageId;
    private String parentMessageId;
    private String emailId;
    private List postedMsgs;
    
    

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setPostedMsgs(List postedMsgs) {
        this.postedMsgs = postedMsgs;
    }

    public List getPostedMsgs() {
        return postedMsgs;
    }
}
