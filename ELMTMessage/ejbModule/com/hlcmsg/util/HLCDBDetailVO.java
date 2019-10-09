/*
 * DBDetailVO.java
 *
 * Created on October 10, 2006, 5:54 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmsg.util;

import java.io.Serializable;

/**
 *
 * @author harmohan
 */
public class HLCDBDetailVO implements Serializable {
    private String userId;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String usertype;
    private String membershipType;
    private String riderLevel;
    private String memberStatus;
    private String totRec;
    private String zip;
    
    /** Creates a new instance of DBDetailVO */
    public HLCDBDetailVO() {
    }
    
   

    public String getAddress() { return address; }
    public String getCity() {return city; }
    public String getFirstName() { return firstName;}
    public String getLastName() { return lastName; }
    public String getMemberStatus() { return memberStatus; }
    public String getMembershipType() {return membershipType; }
    public String getRiderLevel() {return riderLevel; }
    public String getUserId() { return userId;}
    public String getUsertype() {return usertype;}
    public String getTotRec() { return totRec; }


    public void setAddress(String address) { this.address = address;}
    public void setCity(String city) { this.city = city; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setMemberStatus(String memberStatus) { this.memberStatus = memberStatus; }
    public void setMembershipType(String membershipType) { this.membershipType = membershipType; }
    public void setRiderLevel(String riderLevel) { this.riderLevel = riderLevel; }
    public void setUserId(String userId) { this.userId = userId; }
    public void setUsertype(String usertype) { this.usertype = usertype;}
    public void setTotRec(String totRec) { this.totRec = totRec; }
    
    
      public String toString(){
     
    StringBuffer buffer = new StringBuffer()
   	.append(" Total No Of Records :"+ totRec+"\n")
        .append(" firstName :"+ firstName+"\n")
	.append(" lastName :"+ lastName+"\n")
	.append(" address :"+ address+"\n")
	.append(" city :"+city+"\n")
	.append(" memberStatus :"+memberStatus+"\n")
	.append(" userId :"+userId+"\t\t")
        .append(" membershipType :"+membershipType+"\n")
	.append(" riderLevel :"+riderLevel+"\n")
        .append(" userId :"+userId+"\n")
        .append(" zip :"+zip+"\n")
        .append(" usertype :"+usertype+"\n");
	
    return buffer.toString();
  }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

   
    
    
}
