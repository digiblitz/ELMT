/*
 * RenewalPK.java
 *
 * Created on August 19, 2006, 11:14 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmro.renewal;

/**
 *
 * @author suresh
 */


import java.io.Serializable;
import java.util.Date;

public class HLCRenewalPK implements Serializable{
	public String renewalId;
	public int eventId;

	public HLCRenewalPK(){
	}
	public void setRenewalId(String renewalId){
		this.renewalId=renewalId;
	}
	public String getRenewalId(){
		return renewalId;
	}
	public void setEventId(int eventId){
		this.eventId=eventId;
	}
	public int getEventId(){
		return eventId;
	}

        public int hashCode(){
		return (renewalId + eventId).hashCode();
	}
        
	public boolean equals(Object renewal){
			HLCRenewalPK pk=(HLCRenewalPK)renewal;
			return ((pk.getRenewalId()).equals(renewalId) &&
						pk.getEventId()==eventId);
	}
	public String toString(){
		return renewalId+":"+eventId;
	}

}