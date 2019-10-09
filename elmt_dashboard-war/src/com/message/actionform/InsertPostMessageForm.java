/*
 * InsertPostMessageForm.java
 *
 * Created on oct 03, 2006, 20:30 
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.message.actionform;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;


/**
 *
 * @author rathna
 */
public class InsertPostMessageForm extends ActionForm{
    
    private String[] to;
    private String from;
    private String subject;
    private String msg;
    private FormFile file;
    private String parentMsgId;
    private String groupName;
            
    public String[] getTo() {
        return to;
    }
   public void setTo(String[] to) {
        this.to = to;
    }
    
    public String getFrom() {
        return from;
    }
    public void setFrom(String from) {
        this.from = from;
          
    }
     
    public String getSubject() {
        return subject;
    }
    public void setSubject(String  subject) {
        this.subject = subject;
    }         
   
   public String getMsg() {
        return msg;
    }
    public void setMsg(String  msg) {
        this.msg = msg;
    }



	public void reset(ActionMapping mapping, HttpServletRequest request) {
        
		to=null;
		from=null;
		subject=null;
		msg=null;
		file=null;
	  }
    
    public String toString(){
        
        StringBuffer buffer = new StringBuffer()
        .append(" to :"+ to+"\t\t")
        .append(" from :"+ from+"\t\t")
        .append(" subject :"+ subject +"\t\t")
        .append(" msg :"+ msg +"\t\t")
        .append(" msg :"+ msg +"\t\t")
        .append(" groupName :"+ groupName +"\t\t");
                        
        return buffer.toString();
    }
    public void setfile(FormFile file) {
        this.file = file;
    }

    public FormFile getfile() {
        return file;
    }

    public void setParentMsgId(String parentMsgId) {
        this.parentMsgId = parentMsgId;
    }

    public String getParentMsgId() {
        return parentMsgId;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }
}

