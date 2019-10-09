/*
 * InstructorPriceMaster.java
 *
 * Created on September 1, 2006, 7:18 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmeeting.util;

/**
 *
 * @author harmohan
 */
public class HLCInstructorPriceMaster implements java.io.Serializable {

	private String  priceId;
	private String  eventRegistrationTypeId;
	private String  noOfDays;
	private String  price;
        private String eventRegistrationTypeName;

	public  HLCInstructorPriceMaster() { }

	public  HLCInstructorPriceMaster(String  priceId, String eventRegistrationTypeId, String noOfDays, String  price, String eventRegistrationTypeName) 	{
             this.priceId = priceId;
             this.eventRegistrationTypeId = eventRegistrationTypeId;
             this.noOfDays = noOfDays;
             this.price = price;
             this.eventRegistrationTypeName = eventRegistrationTypeName;
    }


//getter

	public String getPriceId() {
		return priceId;
	}

	public String getEventRegistrationTypeId() {
		return eventRegistrationTypeId;
	}

	public String getNoOfDays() {
		return noOfDays;
	}

	public String getPrice() {
		return price;
	}
        public String getEventRegistrationTypeName() {
		return eventRegistrationTypeName;
	}
     
  

 //Setters methods

    public void setPriceId(String PriceId) {
        this.priceId = priceId;
    }


 	public void setEventRegistrationTypeId(String EventRegistrationTypeId)  {
	 	this.eventRegistrationTypeId = eventRegistrationTypeId;
  	}

	public void setNoOfDays(String noOfDays) {
	 	this.noOfDays =  noOfDays;
 	}
 	public void setPrice(String price)   {
	    this.price =  price;
    }
   public void setEventRegistrationTypeName(String eventRegistrationTypeName) 
   {this.eventRegistrationTypeName= eventRegistrationTypeName; }

}
