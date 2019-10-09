/*
 * UploadRegDataActionForm.java
 *
 * Created on April 11, 2007, 1:21 PM
 */

package com.mrm.actionform;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import java.io.Serializable;
import org.apache.struts.upload.FormFile;
/**
 *
 * @author Punitha
 * @version
 */

public class UploadRegDataActionForm extends org.apache.struts.action.ActionForm implements Serializable{
    
    private transient FormFile fileUpload;
    private String eventId;
    public UploadRegDataActionForm() {
    }
    public FormFile getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(FormFile fileUpload) {
        this.fileUpload = fileUpload;
    }
   public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventId() {
        return eventId;
    }
     public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping,request);
        this.fileUpload=null;
        this.eventId=null;
    }  
 
   public String toString(){
  
   StringBuffer buffer = new StringBuffer()
   .append(" eventId :"+ eventId+"\n")
   .append("  ");
 
  return buffer.toString();
  }

  
}

