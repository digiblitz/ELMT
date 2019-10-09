/*
 * CountryMailPriceMaster.java
 *
 * Created on August 28, 2006, 6:32 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmrm.util;

/**
 *
 * @author harmohan
 */
public class HLCCountryMailPriceMaster implements java.io.Serializable {
	private String countryMailTypeId;
	private String countryMailTypeName;
	private String countryMailPrice;

	public HLCCountryMailPriceMaster()  { }

	public HLCCountryMailPriceMaster(String countryMailTypeId,String countryMailTypeName,String countryMailPrice) {
		this.countryMailTypeId = countryMailTypeId;
		this.countryMailTypeName = countryMailTypeName;
		this.countryMailPrice = countryMailPrice;
	}

	//getter
	public String getCountryMailTypeId() {
		return 	countryMailTypeId;
	}
	public String getCountryMailTypeName() {
		return  countryMailTypeName;
	}
	public String getCountryMailPrice() {
		return countryMailPrice;
	}

	public void setCountryMailTypeId(String countryMailTypeId) {
		this.countryMailTypeId = countryMailTypeId;
	}
	public void setCountryMailTypeName(String countryMailTypeName) {
		this.countryMailTypeName = countryMailTypeName;
	}
	public void setCountryMailPrice(String countryMailPrice) {
		this.countryMailPrice = countryMailPrice ;
	}
}