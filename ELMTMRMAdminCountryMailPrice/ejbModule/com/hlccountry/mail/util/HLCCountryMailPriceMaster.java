/*
 * CountryMailPriceMaster.java
 *
 * Created on August 29, 2006, 2:44 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlccountry.mail.util;

/**
 *
 * @author harmohan
 */
public class HLCCountryMailPriceMaster implements java.io.Serializable {
	private String countryMailTypeId;
	private String countryMailTypeName;
        private String membershipTypeId;
        private String memberShipName;
	private String countryMailPrice;
        private String year;
        

	public HLCCountryMailPriceMaster()  { }

	public HLCCountryMailPriceMaster(String countryMailTypeId,String countryMailTypeName, String membershipTypeId, String memberShipName, String countryMailPrice ,String year) {
		this.countryMailTypeId = countryMailTypeId;
		this.countryMailTypeName = countryMailTypeName;
                this.membershipTypeId = membershipTypeId;
                this.memberShipName = memberShipName;
		this.countryMailPrice = countryMailPrice;
                this.year=year;
	}

	//getter
	public String getCountryMailTypeId() {
		return 	countryMailTypeId;
	}
	public String getCountryMailTypeName() {
		return  countryMailTypeName;
	}
        
         public String getMembershipTypeId() {
		return membershipTypeId;
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
        
        public void setMembershipTypeId(String membershipTypeId) {
		this.membershipTypeId = membershipTypeId;
	}
        
	public void setCountryMailPrice(String countryMailPrice) {
		this.countryMailPrice = countryMailPrice ;
	}

        public void setMemberShipName(String memberShipName) {
            this.memberShipName = memberShipName;
        }

        public String getMemberShipName() {
            return memberShipName;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
        this.year = year;
        }
}