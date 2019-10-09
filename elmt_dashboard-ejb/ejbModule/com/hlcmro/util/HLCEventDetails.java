/*
 * EventDetails.java
 *
 * Created on August 13, 2006, 4:21 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmro.util;
/**
 *
 * @author suresh
 */
 
   public class HLCEventDetails implements java.io.Serializable {
    private String eventId;
    private String organizeId;
    private String eventSecretaryId;
    
    /*==============Entries Details=================*/
    
    private String entryFee;
    private String otherEntryFee;
    private String doubleEntryFee;
    private String checkPayableTo;
    
    /*==============Pinnies Details=================*/
    private String pinniesCharge;
    private String pinniesPosition;
    
    /*==============Award Details=================*/
    private String awardTrophy;
    private String awardPrize;
    private String awardOthers;
    
    /*==============Starting Time Details=================*/
    private String dateAvailable;
    private String availableFrom;
    private String availableFromOther;
    private String availablePosition;
    
    /*==============Stabling Details=================*/
    private String stablingLimited;
    private String stallPernightPrice;
    private String perStallPrice;
    private String perStallFromTime;
    private String perStallFromDate;
    private String perStallToTime;
    private String perStallToDate;
    private String noOfStalls;
    private String noOfTempStalls;
    private String noOfTempPermanentStalls;
    private String milesFromEvent;
    
    /*==============VETERINARIAN Details=================*/
    private String veterinarianName;
    private String veterinarianPhone;
    private String veterinarianPlace;
    
    /*==============Accomodation Details=================*/
    private String accomodationCamping;

    /*==============Direction Details=================*/
    private String directions;


    

  /*  public EventDetails(String customerId, String lastName,
        String firstName, String middleInitial, String street, String city,
        String state, String zip, String phone, String email) {
        this.customerId = customerId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleInitial = middleInitial;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phone = phone;
        this.email = email;
    }
   **/

    
    // getters
public String getEventId() {
    return eventId;
}

public String getOrganizeId() {
    return organizeId;
}

public String getEventSecretaryId() {
    return eventSecretaryId;
}

public String getEntryFee() {
    return entryFee;
}

public String getOtherEntryFee() {
    return otherEntryFee;
}

public String getDoubleEntryFee() {
    return doubleEntryFee;
}

public String getCheckPayableTo() {
return checkPayableTo;
}

public String getPinniesCharge() {
return pinniesCharge;
}

public String getPinniesPosition() {
return pinniesPosition;
}

public String getAwardTrophy() {
    return awardTrophy;
}

public String getAwardPrize() {
    return awardPrize;
}

public String getAwardOthers() {
    return awardOthers;
}


public String getDateAvailable() {
    return dateAvailable;
}

public String getAvailableFrom() {
    return availableFrom;
}

public String getAvailableFromOther() {
    return availableFromOther;
}

public String getAvailablePosition() {
    return availablePosition;
}

public String getStablingLimited() {
    return stablingLimited;
}

public String getStallPernightPrice() {
    return stallPernightPrice;
}

public String getPerStallPrice() {
    return perStallPrice;
}

public String getPerStallFromTime() {
    return perStallFromTime;
}


 public String getPerStallFromDate() {
    return perStallFromDate;
}

public String getPerStallToTime() {
    return perStallToTime;
}

public String getPerStallToDate() {
    return perStallToDate;
}

public String getNoOfStalls() {
    return noOfStalls;
}

public String getNoOfTempStalls() {
    return noOfTempStalls;
}

public String getNoOfTempPermanentStalls() {
    return noOfTempPermanentStalls;
}

public String getMilesFromEvent() {
    return milesFromEvent;
}

 public String getVeterinarianName() {
    return veterinarianName;
}

public String getVeterinarianPhone() {
    return veterinarianPhone;
}

public String getVeterinarianPlace() {
    return veterinarianPlace;
}

 public String getAccomodationCamping() {
    return accomodationCamping;
}

public String getDirections() {
    return directions;
}

//Setters methods
public void setEventId(String eventId) {
        this.eventId = eventId;
    }

public void setOrganizeId(String organizeId) {
        this.organizeId = organizeId;
    }

public void setEventSecretaryId(String eventSecretaryId) {
        this.eventSecretaryId = eventSecretaryId;
    }

public void setEntryFee(String entryFee) {
        this.entryFee = entryFee;
    }

public void setOtherEntryFee(String otherEntryFee) {
        this.otherEntryFee = otherEntryFee;
    }
    
public void setDoubleEntryFee(String doubleEntryFee) {
        this.doubleEntryFee = doubleEntryFee;
    }

public void setCheckPayableTo(String checkPayableTo) {
        this.checkPayableTo = checkPayableTo;
    }
   
public void setPinniesCharge(String pinniesCharge) {
        this.pinniesCharge = pinniesCharge;
    }

public void setPinniesPosition(String pinniesPosition) {
        this.pinniesPosition = pinniesPosition;
    }

public void setAwardTrophy(String awardTrophy) {
        this.awardTrophy = awardTrophy;
    }
   
public void setAwardPrize(String awardPrize) {
        this.awardPrize = awardPrize;
    }
    
public void setAwardOthers(String awardOthers) {
        this.awardOthers = awardOthers;
    }
    
public void setDateAvailable(String dateAvailable) {
        this.dateAvailable = dateAvailable;
    }
   
public void setAvailableFrom(String availableFrom) {
        this.availableFrom = availableFrom;
    }
    
public void setAvailableFromOther(String availableFromOther) {
        this.availableFromOther = availableFromOther;
    }
    
public void setAvailablePosition(String availablePosition) {
        this.availablePosition = availablePosition;
    }
    
    
/*==============Stabling Details=================*/
    public void setStablingLimited(String stablingLimited) {
        this.stablingLimited = stablingLimited;
    }
    
    public void setStallPernightPrice(String stallPernightPrice) {
        this.stallPernightPrice = stallPernightPrice;
    }
    
    public void setPerStallPrice(String perStallPrice) {
        this.perStallPrice = perStallPrice;
    }
    
    public void setPerStallFromTime(String perStallFromTime) {
        this.perStallFromTime = perStallFromTime;
    }
    
    public void setPerStallFromDate(String perStallFromDate) {
        this.perStallFromDate = perStallFromDate;
    }
    
    public void setPerStallToTime(String perStallToTime) {
        this.perStallToTime = perStallToTime;
    }
    
    public void setPerStallToDate(String perStallToDate) {
        this.perStallToDate = perStallToDate;
    }
    
    public void setNoOfStalls(String noOfStalls) {
        this.noOfStalls = noOfStalls;
    }
    
    public void setNoOfTempStalls(String noOfTempStalls) {
        this.noOfTempStalls = noOfTempStalls;
    }
    
    public void setNoOfTempPermanentStalls(String noOfTempPermanentStalls) {
        this.noOfTempPermanentStalls = noOfTempPermanentStalls;
    }
    
    public void setMilesFromEvent(String milesFromEvent) {
        this.milesFromEvent = milesFromEvent;
    }
 /*==============VETERINARIAN Details=================*/
    public void setVeterinarianName(String veterinarianName) {
        this.veterinarianName = veterinarianName;
    }
     
     public void setveterinarianPhone(String veterinarianPhone) {
        this.veterinarianPhone = veterinarianPhone;
    }
      public void setVeterinarianPlace(String veterinarianPlace) {
        this.veterinarianPlace = veterinarianPlace;
    }
 
 /*==============Accomodation Details=================*/
       public void setAccomodationCamping(String accomodationCamping) {
        this.accomodationCamping = accomodationCamping;
    }
 /*==============Direction Details=================*/
        public void setDirections(String directions) {
        this.directions = directions;
    }
 
}
