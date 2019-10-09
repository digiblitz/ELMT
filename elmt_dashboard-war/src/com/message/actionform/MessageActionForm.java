/*
 * MessageActionForm.java
 *
 * Created on July 25, 2007, 12:39 PM
 */

package com.message.actionform;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import java.io.Serializable;
import org.apache.struts.upload.FormFile;

/**
 *
 * @author Dhivya
 * @version
 */

public class MessageActionForm extends org.apache.struts.action.ActionForm implements Serializable{

        private String  userId;
        private String  subject;
        private String  mesgDesc;
        private transient FormFile attachFile;
        private String  imagePath;
      
    
     public MessageActionForm(){}

    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping,request);
        this.userId=null;
        this.subject=null;
        this.mesgDesc=null;
        this.attachFile=null;
        this.imagePath=null;
        
    }
   
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        return null;
    }

   
    public String toString(){
  
   StringBuffer buffer = new StringBuffer()
   .append(" userId= "+this.userId+"\n")
   .append(" subject= "+this.subject+"\n")
   .append(" mesgDesc= " +this.mesgDesc+"\n")
   .append(" attachFile= " +this.attachFile+"\n")
   .append(" imagePath= " +this.imagePath);
  
  return buffer.toString();
  }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setMesgDesc(String mesgDesc) {
        this.mesgDesc = mesgDesc;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setAttachFile(FormFile attachFile) {
        this.attachFile = attachFile;
    }

    public String getUserId() {
        return userId;
    }

    public String getSubject() {
        return subject;
    }

    public String getMesgDesc() {
        return mesgDesc;
    }

    public String getImagePath() {
        return imagePath;
    }

    public FormFile getAttachFile() {
        return attachFile;
    }
}

