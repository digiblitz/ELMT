/*******************************************************************************
 * * * Copyright: 2019 digiBlitz Foundation
 *  * * 
 *  * * License: digiBlitz Public License 1.0 (DPL) 
 *  * * Administered by digiBlitz Foundation. www.digiblitz.org/dpl/
 *  * * 
 *  * * Inventor: Suresh Kannan (Maya Suresh Kannan Balabisegan ) (www.sureshkannan.org)
 *  * * 
 *  * * Authors: Suresh Kannan (Maya Suresh Kannan Balabisegan )& digiBlitz.
 *  * * 
 *  * * "Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software in accordance to the rules & restrictions of the digiBlitz Public License."
 ******************************************************************************/
/*
 * MeeAnnualRegistrationDetails.java
 *
 * Created on August 30, 2006, 10:33 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmeeting.util;

/**
 *
 * @author harmohan
 */
public class HLCMeeAnnualRegistrationDetails implements java.io.Serializable{
    private String ardId;
    private String meetingId;
    private String noOfTickets;
    private String priceId;
    private String accomodationDetails;
    private String daysApplied;
    /** Creates a new instance of MeeAnnualRegistrationDetails */
    public HLCMeeAnnualRegistrationDetails() {
    }
    
     public HLCMeeAnnualRegistrationDetails(String ardId,String meetingId,String noOfTickets,String priceId,String accomodationDetails,String daysApplied) {
         this.ardId = ardId;
         this.meetingId = meetingId;
         this.noOfTickets = noOfTickets;
         this.priceId = priceId;
         this.accomodationDetails = accomodationDetails;
         this.daysApplied = daysApplied;
     }
     
      // getters
        public String getArdId() {return ardId; }
        public String getMeetingId() { return meetingId; }
        public String getNoOfTickets() {return noOfTickets; }
        public String getPriceId() {return priceId; }
        public String getAccomodationDetails() { return accomodationDetails;}
        public String getDaysApplied() { return daysApplied;}
        
         //Setters methods
        public void setArdId(String ardId) {this.ardId = ardId; }
        public void setMeetingId(String meetingId) {this.meetingId = meetingId; }
        public void setNoOfTickets(String noOfTickets) {this.noOfTickets = noOfTickets;}
        public void setPriceId(String priceId) {this.priceId = priceId;}
        public void setAccomodationDetails(String accomodationDetails) {this.accomodationDetails = accomodationDetails;}
        public void setDaysApplied(String daysApplied) {this.daysApplied = daysApplied;}
}
