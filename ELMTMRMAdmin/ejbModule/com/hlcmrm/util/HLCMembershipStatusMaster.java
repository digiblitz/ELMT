/*
 * MembershipStatusMaster.java
 *
 * Created on August 28, 2006, 6:32 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmrm.util;
import java.util.*;

/**
 *
 * @author harmohan
 */
public class HLCMembershipStatusMaster implements java.io.Serializable {
	private String  statusId;
	private String  statusName;
	private String  description;
	public  HLCMembershipStatusMaster() { }
	public  HLCMembershipStatusMaster(String  statusId,String  statusName,String  description) {
		this.statusId = statusId;
		this.statusName = statusName;
		this.description = description;
    }
    //getter
    public String getStatusId() {
    	return statusId;
    }

    public String getStatusName() {
    	return statusName;
    }
    public String getDescription() {
	    return description;
    }
    //setter
    public void setStatusId(String statusId) {
		this.statusId = statusId ;
    }

    public void setStatusName(String statusName) {
			this.statusName = statusName ;
    }

	public void setDescription(String Description) {
				this.description = description ;
    }
}