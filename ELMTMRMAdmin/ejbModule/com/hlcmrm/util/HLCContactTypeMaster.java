/*
 * ContactTypeMaster.java
 *
 * Created on August 28, 2006, 6:31 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmrm.util;

/**
 *
 * @author harmohan
 */
public class HLCContactTypeMaster implements java.io.Serializable {

	private String contactTypeId;
	private String contactTypeName;
        //Start:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
        private String contactTypeDescription;
	private String contactType;
        private String status;
        //End:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
	public HLCContactTypeMaster() {  }
        //Start:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
        //public HLCContactTypeMaster(String contactTypeId,String contactTypeName) {
	public HLCContactTypeMaster(String contactTypeId,String contactTypeName,String contactTypeDescription,String contactType,String status) {
	  //End:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
           this.contactTypeId = contactTypeId;
	   this.contactTypeName = contactTypeName;
           //Start:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
	   this.contactTypeDescription=contactTypeDescription;
	   this.contactType = contactType;
           this.status=status;
           //End:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
	}

	//getter
	public String getContactTypeId() {
		return contactTypeId;
	}
	public String getContactTypeName() {
		return contactTypeName;
	}
        //Start:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
        public String getContactTypeDescription() {
                return contactTypeDescription;
    }
	public String getContactType() {
		return contactType;
	}
        public String getStatus() {
		return status;
	}
        //End:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
	//setter
	public void setContactTypeId(String contactTypeId ) {
		 this.contactTypeId = contactTypeId;
	}
	public void setContactTypeName(String contactTypeName) {

		this.contactTypeName = contactTypeName;
	}
        //Start:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
         public void setContactTypeDescription(String contactTypeDescription) {

                this.contactTypeDescription = contactTypeDescription;
        }
	public void setContactType(String contactType) {

		 this.contactType = contactType;
	}
        public void setStatus(String status) {

		 this.status = status;
	}
        //End:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
    }
