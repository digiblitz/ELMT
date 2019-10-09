/*
 * EventDetails.java
 *
 * Created on August 13, 2006, 4:21 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmro.util;
import java.util.*;
/**
 *
 * @author suresh
 */
 
    public class HLCEventDetailsVO implements java.io.Serializable {
   private String eventId;
    private String organizeId;
    private String eventTitle;
    private String eventSecretaryId;
    private String eventSecName;
    private String eventSecAddress;
    private String eventSecAddress2;
    private String eventSecCity;
    private String eventSecPhone;
    private String eventSecPhoneEvent;
    private String eventSecFax;
    private String eventSecEmail;
    private String secretaryComments;
    private String officialComments;
    private boolean payExist;
    private String provisId;
    private boolean entryFeeExist;
  
    /*==============Entries Details=================*/
    
    private String entryFee;
    private String otherEntryFee;
    private String membershipApplicable;
    private String doubleEntryFee;
    private String officeFee;
    private String checkPayableTo;
    private String entriesComments;
    private String eveTitleComments;
    private String eveFeeComments;
    private String eveMgtComments;
    private String eveWebSite;
    
    /*==============Pinnies Details=================*/
    private String pinniesCharge;
    private String pinniesPosition;
    
    /*==============Award Details=================*/
    private String awardTrophy;
    private String awardPrize;
    private String awardOthers;
    private String awardCommts;
    
    /*==============Starting Time Details=================*/
    private String dateAvailable;
    private String availableFrom;
    private String availableFromOther;
    private String availablePosition;
    private String dressageComment;
    private String timeComment;
    private String startTimeCommts;
    
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
    private String stabVetCommts;
    private String crsCountCommts;
    private String otherCommts;
    
    /*==============VETERINARIAN Details=================*/
    private String veterinarianName;
    private String veterinarianPhone;
    private String veterinarianPlace;
    
    /*==============Accomodation Details=================*/
    private String accomodationCamping;


    private String accomodation;
    
    /*==============Direction Details=================*/
    private String directions;


    private String additionalInfo;
    private String footingDesc;
    private Date  addDate;
    private String statusId;
    private String comments;
    
    /*for org event list*/
    private String areaName;
    private String requestStatus;
    
    private String paymentId;
    private Date  eveBegDt;
     public HLCEventDetailsVO(){

     }

    public HLCEventDetailsVO(String eventId, String organizeId, String eventTitle, String eventSecretaryId,
        String entryFee, String otherEntryFee, String membershipApplicable, String doubleEntryFee,String officeFee, String checkPayableTo,
        String pinniesCharge, String pinniesPosition, String awardTrophy, String awardPrize,
        String awardOthers, String dateAvailable, String availableFrom, String availableFromOther,
        String availablePosition, String stablingLimited, String stallPernightPrice, String perStallPrice,
        String perStallFromTime, String perStallFromDate, String perStallToTime, String perStallToDate,
        String noOfStalls, String noOfTempStalls, String noOfTempPermanentStalls, String milesFromEvent,
        String veterinarianName, String veterinarianPhone, String veterinarianPlace, String accomodationCamping,
        String directions,String footingDesc, Date addDate, String statusId, String comments){
        
        this.eventId = eventId;
        this.organizeId = organizeId;
        this.eventTitle = eventTitle;
        this.eventSecretaryId = eventSecretaryId;
        this.entryFee = entryFee;
        this.otherEntryFee = otherEntryFee;
        this.membershipApplicable = membershipApplicable;
        this.doubleEntryFee = doubleEntryFee;
        this.officeFee = officeFee;
        this.checkPayableTo = checkPayableTo;
        this.pinniesCharge = pinniesCharge;
        this.pinniesPosition = pinniesPosition;
        this.awardTrophy = awardTrophy;
        this.awardPrize = awardPrize;
        this.awardOthers = awardOthers;
        this.dateAvailable = dateAvailable;
        this.availableFrom = availableFrom;
        this.availableFromOther = availableFromOther;
        this.availablePosition = availablePosition;
        this.stablingLimited = stablingLimited;
        this.stallPernightPrice = stallPernightPrice;
        this.perStallPrice = perStallPrice;
        this.perStallFromTime = perStallFromTime;
        this.perStallFromDate = perStallFromDate;
        this.perStallToTime = perStallToTime;
        this.perStallToDate = perStallToDate;
        this.noOfStalls = noOfStalls;
        this.noOfTempStalls = noOfTempStalls;
        this.noOfTempPermanentStalls = noOfTempPermanentStalls;
        this.milesFromEvent = milesFromEvent;
        this.veterinarianName = veterinarianName;
        this.veterinarianPhone = veterinarianPhone;
        this.veterinarianPlace = veterinarianPlace;
        this.accomodationCamping = accomodationCamping;
        this.directions = directions;
        this.footingDesc = footingDesc;
        this.addDate = addDate;
        this.statusId = statusId;
        this.comments = comments;
        
    }
 
  
 
    // getters
    
   
public String getEveWebSite() {
        return eveWebSite;
    }
public String getEventId() {
    return eventId;
}

public String getOrganizeId() {
    return organizeId;
}

public String getEventTitle() {
    return eventTitle;
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

public String getMembershipApplicable() {
    return membershipApplicable;
}

public String getDoubleEntryFee() {
    return doubleEntryFee;
}

public String getOfficeFee() {
    return officeFee;
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

 public String getFootingDesc() {
       return  footingDesc;
}
 
 public Date getAddDate() {
        return addDate;
}
 
 public String  getStatusId() {
        return statusId;
}
 
  public String getPaymentId() {
        return paymentId;
    }
//Setters methods
public void setEventId(String eventId) {
    this.eventId = eventId;
}

public void setOrganizeId(String organizeId) {
        this.organizeId = organizeId;
    }

public void setEventTitle(String eventTitle) {
    this.eventTitle = eventTitle;
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

public void setMembershipApplicable(String membershipApplicable) {
    this.membershipApplicable = membershipApplicable;
}
    
public void setDoubleEntryFee(String doubleEntryFee) {
        this.doubleEntryFee = doubleEntryFee;
    }

public void  setOfficeFee(String officeFee) {
    this.officeFee = officeFee;
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
     
     public void setVeterinarianPhone(String veterinarianPhone) {
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
    
    public void setFootingDesc(String footingDesc) {
        this.footingDesc = footingDesc;
    }
    
  public void setAddDate(Date addDate) {
    this.addDate = addDate;
  }
  
   public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getComments() {
        return comments;
    }

    public String getAreaName() {
        return areaName;
    }
 
    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
    
    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getSecretaryComments() {
        return secretaryComments;
    }

    public void setSecretaryComments(String secretaryComments) {
        this.secretaryComments = secretaryComments;
    }

    public String getEventSecName() {
        return eventSecName;
    }

    public void setEventSecName(String eventSecName) {
        this.eventSecName = eventSecName;
    }

    public String getEventSecAddress() {
        return eventSecAddress;
    }

    public void setEventSecAddress(String eventSecAddress) {
        this.eventSecAddress = eventSecAddress;
    }

    public String getEventSecAddress2() {
        return eventSecAddress2;
    }

    public void setEventSecAddress2(String eventSecAddress2) {
        this.eventSecAddress2 = eventSecAddress2;
    }

    public String getEventSecCity() {
        return eventSecCity;
    }

    public void setEventSecCity(String eventSecCity) {
        this.eventSecCity = eventSecCity;
    }

    public String getEventSecPhone() {
        return eventSecPhone;
    }

    public void setEventSecPhone(String eventSecPhone) {
        this.eventSecPhone = eventSecPhone;
    }

    public String getEventSecPhoneEvent() {
        return eventSecPhoneEvent;
    }

    public void setEventSecPhoneEvent(String eventSecPhoneEvent) {
        this.eventSecPhoneEvent = eventSecPhoneEvent;
    }

    public String getEventSecFax() {
        return eventSecFax;
    }

    public void setEventSecFax(String eventSecFax) {
        this.eventSecFax = eventSecFax;
    }

    public String getEventSecEmail() {
        return eventSecEmail;
    }

    public void setEventSecEmail(String eventSecEmail) {
        this.eventSecEmail = eventSecEmail;
    }
    
        public String getAccomodation() {
        return accomodation;
    }

    public void setAccomodation(String accomodation) {
        this.accomodation = accomodation;
    }
    
        public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String getDressageComment() {
        return dressageComment;
    }

    public void setDressageComment(String dressageComment) {
        this.dressageComment = dressageComment;
    }

    public String getTimeComment() {
        return timeComment;
    }

    public void setTimeComment(String timeComment) {
        this.timeComment = timeComment;
    }

    public void setAwardCommts(String awardCommts) {
        this.awardCommts = awardCommts;
    }

    public void setCrsCountCommts(String crsCountCommts) {
        this.crsCountCommts = crsCountCommts;
    }

    public void setEntriesComments(String entriesComments) {
        this.entriesComments = entriesComments;
    }

    public void setOfficialComments(String officialComments) {
        this.officialComments = officialComments;
    }

    public void setOtherCommts(String otherCommts) {
        this.otherCommts = otherCommts;
    }

    public void setStabVetCommts(String stabVetCommts) {
        this.stabVetCommts = stabVetCommts;
    }

    public void setStartTimeCommts(String startTimeCommts) {
        this.startTimeCommts = startTimeCommts;
    }

    public String getAwardCommts() {
        return awardCommts;
    }

    public String getCrsCountCommts() {
        return crsCountCommts;
    }

    public String getEntriesComments() {
        return entriesComments;
    }

    public String getOfficialComments() {
        return officialComments;
    }

    public String getOtherCommts() {
        return otherCommts;
    }

    public String getStabVetCommts() {
        return stabVetCommts;
    }

    public String getStartTimeCommts() {
        return startTimeCommts;
    }

    public void setEveBegDt(Date eveBegDt) {
        this.eveBegDt = eveBegDt;
    }

    public Date getEveBegDt() {
        return eveBegDt;
    }

    public void setPayExist(boolean payExist) {
        this.payExist = payExist;
    }

    public boolean isPayExist() {
        return payExist;
    }

    public String getProvisId() {
        return provisId;
    }

    public void setProvisId(String provisId) {
        this.provisId = provisId;
    }

    public void setEntryFeeExist(boolean entryFeeExist) {
        this.entryFeeExist = entryFeeExist;
    }

    public boolean isEntryFeeExist() {
        return entryFeeExist;
    }

    public void setEveFeeComments(String eveFeeComments) {
        this.eveFeeComments = eveFeeComments;
    }

    public String getEveFeeComments() {
        return eveFeeComments;
    }

    public void setEveTitleComments(String eveTitleComments) {
        this.eveTitleComments = eveTitleComments;
    }

    public String getEveTitleComments() {
        return eveTitleComments;
    }

    public void setEveMgtComments(String eveMgtComments) {
        this.eveMgtComments = eveMgtComments;
    }

    public String getEveMgtComments() {
        return eveMgtComments;
    }

    public void setEveWebSite(String eveWebSite) {
        this.eveWebSite = eveWebSite;
    }
   
}
