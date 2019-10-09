/*
 * MsgDistributionListMaster.java
 *
 * Created on September 2, 2006, 2:50 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmsg.util;

/**
 *
 * @author harmohan
 */
import java.io.*;
import java.util.*;

public class HLCMsgDistributionListMaster implements Serializable {

private String listId;
private String listName;
private String contactlistId;
private String activeStatus;
private String addDate;
private String emailId;

public HLCMsgDistributionListMaster(){  }

public HLCMsgDistributionListMaster(String listId,String listName,String contactlistId,String activeStatus,String addDate, String emailId)
{
    this.listId = listId ;
    this.listName = listName;
    this.contactlistId = contactlistId;
    this.activeStatus =activeStatus ;
    this.addDate = addDate;
    this.emailId = emailId;
}

    public String getListId(){ return listId; }
    public String getListName(){ return listName; }
    public String getContactlistId(){ return contactlistId;}
    public String getActiveStatus(){ return activeStatus; }
    public String getAddDate(){ return addDate; }
    public String getEmailId(){ return emailId; }

    public void setListId(String listId){this.listId = listId ; }
    public void setListName(String listName){ this.listName = listName; }
    public void setContactlistId(String contactlistId){ this.contactlistId = contactlistId; }
    public void setActiveStatus(String activeStatus){ this.activeStatus =activeStatus ; }
    public void setAddDate(String addDate){ this.addDate = addDate; }
    public void setEmailId(String emailId){ this.emailId = emailId; }

}

