/*
 * ActivityOrganizerVO.java
 *
 * Created on September 5, 2006, 11:10 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlccommon.util;
import java.util.*;

/**
 *
 * @author suresh
 */
    
  
public class HLCActivityOrganizerVO implements java.io.Serializable {

	private String  activityMeetingId;
	private String  activityName;
	private Date  activityDate;
	private String  noOfDays;
	private String  useaAreaId;
	private String  location;
	private String  state;
        private String country;
        private String city;
        private String zip;
	private String  activityOrganizerId;
	private String  activityTypeId;
	private String  otherActivityType;
	private String  activityFees;
	private String  instructorsCoaches;
	private String  facilities;
	private String  otherFacilities;
	private String  landOwnerName;
	private String  landOwnerBusinessName;
	private String  landOwnerAddress;
	private String  landOwnerCity;
	private String  landOwnerState;
	private String  landOwnerCountry;
	private String  landOwnerZip;
	private String  landOwnerPhone;
        private boolean  additionalSites;
        private String  additionalSitesPath;
	private Date  addDate;
	private String  approvedBy;
	private Date  approvedDate;
	private String  postingType;
	private boolean  activeStatus;
	private String  requestStatus;
        private String comments;
        private String paymentId;
         private String landOwnerFax;
        private String landOwnerEmail;

    public String getLandOwnerEmail() {
        return landOwnerEmail;
    }

    public void setLandOwnerEmail(String landOwnerEmail) {
        this.landOwnerEmail = landOwnerEmail;
    }

    public String getLandOwnerFax() {
        return landOwnerFax;
    }

    public void setLandOwnerFax(String landOwnerFax) {
        this.landOwnerFax = landOwnerFax;
    }

	public  HLCActivityOrganizerVO() { }

	public  HLCActivityOrganizerVO(String  activityMeetingId, String activityName,Date activityDate,
	        String noOfDays,String useaAreaId,String location,String  state, String country, String city,
                String zip, String activityOrganizerId, String  activityTypeId,
                String  otherActivityType,String  activityFees,String  instructorsCoaches,
	        String  facilities,String  otherFacilities,String  landOwnerName,String  landOwnerBusinessName,
	        String  landOwnerAddress,String  landOwnerCity,String  landOwnerState,String  landOwnerCountry,
	        String  landOwnerZip,String  landOwnerPhone,boolean additionalSites,String additionalSitesPath,
                Date  addDate,String  approvedBy,Date  approvedDate,
	        String  postingType,boolean  activeStatus,String  requestStatus, String comments, String paymentId,String landOwnerFax,String landOwnerEmail) 	{
		 this.activityMeetingId = activityMeetingId;
		 this.activityName = activityName;
		 this.activityDate = activityDate;
		 this.noOfDays = noOfDays;
		 this.useaAreaId = useaAreaId;
		 this. location= location;
		 this.state = state;
                 this.country = country;
                 this.city = city;
                 this.zip = zip;
		 this.activityOrganizerId = activityOrganizerId;
		 this.activityTypeId = activityTypeId;
		 this.otherActivityType = otherActivityType;
		 this.activityFees = activityFees;
		 this.instructorsCoaches = instructorsCoaches;
		 this.facilities = facilities;
		 this.otherFacilities = otherFacilities;
		 this.landOwnerName = landOwnerName;
		 this.landOwnerBusinessName = landOwnerBusinessName;
		 this.landOwnerAddress = landOwnerAddress;
		 this.landOwnerCity = landOwnerCity;
		 this.landOwnerState = landOwnerState;
		 this.landOwnerCountry = landOwnerCountry;
		 this.landOwnerZip = landOwnerZip;
		 this.landOwnerPhone= landOwnerPhone;
                 this.additionalSites = additionalSites;
                 this.additionalSitesPath = additionalSitesPath;
		 this.addDate = addDate;
		 this.approvedBy = approvedBy;
		 this.approvedDate = approvedDate;
		 this.postingType = postingType;
		 this.activeStatus = activeStatus;
		 this.requestStatus = requestStatus;
                 this.comments = comments;
                 this.paymentId = paymentId;
                 this.landOwnerFax=landOwnerFax;
                 this.landOwnerEmail=landOwnerEmail;
    }


//getter

	public String getActivityMeetingId() {
		return activityMeetingId;
	}

	public String getActivityName() {
		return activityName;
	}

	public Date getActivityDate() {
		return activityDate;
	}

	public String getNoOfDays() {
		return noOfDays;
	}

	public String getUseaAreaId() {
		return useaAreaId;
	}

	public String getLocation() {
		return location;
	}

	public String getState() {
		return state;
	}
	public String getActivityOrganizerId() {
			return activityOrganizerId;
	}

	public String getActivityTypeId() {
			return activityTypeId;
	}

	public String getOtherActivityType() {
		return otherActivityType;
	}

	public String getActivityFees() {
		return activityFees;
	}
	public String getInstructorsCoaches() {
		return instructorsCoaches;
	}
	public String getFacilities() {
		return facilities;
	}
	public String getOtherFacilities() {
		return otherFacilities;
	}
	public String getLandOwnerName() {
		return landOwnerName;
	}
	public String getLandOwnerBusinessName() {
		return landOwnerBusinessName;
	}
	public String getLandOwnerAddress() {
		return landOwnerAddress;
	}
	public String getLandOwnerCity() {
		return landOwnerCity;
	}
	public String getLandOwnerState() {
		return landOwnerState;
	}
	public String getLandOwnerCountry() {
		return landOwnerCountry;
	}
	public String getLandOwnerZip() {
		return landOwnerZip;
	}
	public String getLandOwnerPhone() {
		return landOwnerPhone;
	}
        
	public boolean isAdditionalSites() {
		return additionalSites;
	}
        
	public String getAdditionalSitesPath() {
		return additionalSitesPath;
	}
        
        
       
	public Date getAddDate() {
		return addDate;
	}
	public String getApprovedBy() {
		return approvedBy;
	}
	public Date getApprovedDate() {
		return approvedDate;
	}
	public String getPostingType() {
		return postingType;
	}
	public boolean isActiveStatus() {
		return activeStatus;
	}
	public String getRequestStatus() {
		return requestStatus;
	}
        
        public String getComments() {
            return comments;
	}

        

 //Setters methods

        public void setActivityMeetingId(String activityMeetingId) {
            this.activityMeetingId = activityMeetingId;
        }


        public void setActivityName(String activityName)  {
        this.activityName = activityName;
        }
        public void setActivityDate(Date activityDate)  {
        this.activityDate = activityDate;
        }

        public void setNoOfDays(String noOfDays) {
        this.noOfDays =  noOfDays;
        }
        public void setUseaAreaId(String useaAreaId)   {
        this.useaAreaId =  useaAreaId;
        }
        public void setLocation(String location) {
        this.location =  location;
        }
        public void setState(String state) {
        this.state = state;
        }
        public void setActivityOrganizerId(String activityOrganizerId) {
        this.activityOrganizerId = activityOrganizerId;
        }
        public void setActivityTypeId(String activityTypeId) {
        this.activityTypeId = activityTypeId;
        }


        public void setOtherActivityType(String otherActivityType)  {
        this.otherActivityType = otherActivityType;
        }

        public void setActivityFees(String activityFees) {
        this.activityFees =  activityFees;
        }
        public void setInstructorsCoaches(String instructorsCoaches)   {
        this.instructorsCoaches =  instructorsCoaches;
        }
        public void setFacilities(String facilities) {
        this.facilities =  facilities;
        }
        public void setOtherFacilities(String otherFacilities) {
        this.otherFacilities = otherFacilities;
        }
        //public void set(String ) {
        //   this. = ;
        // }
        public void setLandOwnerName(String landOwnerName) {
        this.landOwnerName = landOwnerName;
        }


        public void setLandOwnerBusinessName(String landOwnerBusinessName)  {
        this.landOwnerBusinessName = landOwnerBusinessName;
        }

        public void setLandOwnerAddress(String landOwnerAddress) {
        this.landOwnerAddress =  landOwnerAddress;
        }
        public void setLandOwnerCity(String landOwnerCity)   {
        this.landOwnerCity =  landOwnerCity;
        }
        public void setLandOwnerState(String landOwnerState) {
        this.landOwnerState =  landOwnerState;
        }
        public void setLandOwnerCountry(String landOwnerCountry) {
        this.landOwnerCountry = landOwnerCountry;
        }
        public void setLandOwnerZip(String landOwnerZip) {
        this.landOwnerZip = landOwnerZip;
        }

        public void setLandOwnerPhone(String landOwnerPhone)  {
        this.landOwnerPhone = landOwnerPhone;
        }
        
         public void setAdditionalSites(boolean additionalSites) {
            this.additionalSites = additionalSites;
	}
        
	public void setAdditionalSitesPath(String additionalSitesPath) {
             this.additionalSitesPath = additionalSitesPath;
	}

        public void setAddDate(Date addDate) {
        this.addDate = addDate;
        }


        public void setApprovedBy(String ApprovedBy) {
            this.approvedBy =  approvedBy;
        }
        
        public void setApprovedDate(Date approvedDate)   {
            this.approvedDate =  approvedDate;
        }
        
        public void setPostingType(String postingType) {
            this.postingType =  postingType;
        }
        
        public void setActiveStatus(boolean activeStatus) {
            this.activeStatus = activeStatus;
        }
        
        public void setRequestStatus(String requestStatus) {
            this.requestStatus = requestStatus;
        }
        
        public void setComments(String comments) {
            this.comments = comments;
	}

        public void setPaymentId(String paymentId) {
            this.paymentId = paymentId;
        }

        public String getPaymentId() {
            return paymentId;
        }
        
    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getZip() {
        return zip;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }        
}
