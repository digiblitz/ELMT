/*
 * AdvertiserVO.java
 *
 * Created on August 29, 2006, 2:33 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlccommon.util;
import java.io.*;
import java.util.*;

/**
 *
 * @author suresh
 */
public class HLCAdvertiserVO implements java.io.Serializable{
    
    /** Creates a new instance of AdvertiserVO */
    public HLCAdvertiserVO() {
    }
        private String  advertisementId;
	private String  userId;
	private String  mediaId;
	private String  advertiser;
	private String  adAgency;
	private String  agencyFirstName;
	private String  agencyMiddleName;
	private String  agencyLastName;
	private String  agencyPhone;
	private String  agencyFax;
	private String  agencyEmail;
	private String  agencyAddress;
	private String  agencySuite;
	private String  agencyCity;
	private String  agencyState;
	private String  agencyCountry;
	private String  agencyZip;
	private String  invoiceTo;
	private String  comments;
	private boolean  advPosted;
	private Date    advPostedDate;
	private String   materialComingFrom;
	private Date     materialComingDate;
	private String   advSuppliedOn;
	private String   salesPersonId;
	private String   requestStatus;
	private boolean  activeStatus;
        private Date  addDate;
        
        
        public HLCAdvertiserVO (String advertisementId, String userId, String mediaId, String advertiser,
            String adAgency, String agencyFirstName, String agencyMiddleName, String agencyLastName, String agencyPhone,
            String agencyFax, String agencyEmail, String agencyAddress, String agencySuite, String agencyCity,
            String agencyState, String agencyCountry, String agencyZip, String invoiceTo, String comments,
            boolean advPosted ,Date advPostedDate, String materialComingFrom, Date materialComingDate,
            String advSuppliedOn, String salesPersonId, String requestStatus, boolean activeStatus,Date  addDate) {

           this.advertisementId = advertisementId;
           this.userId      =  userId;
           this.mediaId    =  mediaId;
           this.advertiser  = advertiser;
           this.adAgency  = adAgency;
           this.agencyFirstName  =  agencyFirstName;
           this.agencyMiddleName = agencyMiddleName;
           this.agencyLastName = agencyLastName;
           this.agencyPhone = agencyPhone;
           this.agencyFax  = agencyFax;
           this.agencyEmail  = agencyEmail;
           this.agencyAddress = agencyAddress;
           this.agencySuite = agencySuite;
           this.agencyCity = agencyCity;
           this.agencyState = agencyState;
           this.agencyCountry = agencyCountry;
           this.agencyZip  =  agencyZip;
           this.invoiceTo = invoiceTo;
           this.comments  = comments;
           this.advPosted = advPosted;
           this.advPostedDate = advPostedDate;
           this.materialComingFrom = materialComingFrom;
           this.materialComingDate = materialComingDate;
           this.advSuppliedOn = advSuppliedOn;
           this.salesPersonId = salesPersonId;
           this.requestStatus = requestStatus;
           this.activeStatus = activeStatus;
           this.addDate = addDate;
	}
        
        
        
        //getter
        public String getAdvertisementId() {
            return advertisementId;
        }
        
        public String getUserId() {
            return  userId;
        }
        
        public String getMediaId(){
            return mediaId;
        }
        
        public String getAdvertiser()	{
            return  advertiser;
        }
        
        public String getAdAgency() {
            return   adAgency;
        }
        
        public String getAgencyFirstName() {
            return  agencyFirstName;
        }
        public String getAgencyMiddleName() {
            return  agencyMiddleName;
        }
        
        public String getAgencyLastName(){
            return agencyLastName;
        }
        
        public String getAgencyPhone() {
            return  agencyPhone;
        }
        
        public String getAgencyFax() {
            return  agencyFax;
        }
        
        public String getAgencyEmail() {
            return agencyEmail;
        }
        
        public String getAgencyAddress() {
            return agencyAddress;
        }
        
        public String getAgencySuite()  {
            return agencySuite;
        }
        
        public String getAgencyCity() {
            return agencyCity;
        }
        
        public String getAgencyState() {
            return agencyState;
        }
        
        public String getAgencyCountry() {
            return agencyCountry;
        }
        
        public String getAgencyZip() {
            return agencyZip;
        }
        
        public String getInvoiceTo() {
            return invoiceTo;
        }
        
        public String getComments() {
            return comments;
        }
        
        public boolean isAdvPosted() {
            return advPosted;
        }
        
        public Date getAdvPostedDate(){
            return advPostedDate;
        }
        
        public String getMaterialComingFrom() {
            return materialComingFrom;
        }
        
        public Date getMaterialComingDate(){
            return materialComingDate;
        }
        
        public String getAdvSuppliedOn(){
            return advSuppliedOn;
        }
        
        public String getSalesPersonId(){
            return salesPersonId;
        }
        
        public String getRequestStatus() {
            return requestStatus;
        }
        public boolean isActiveStatus() {
            return activeStatus;
        }
        
          public Date getAddDate() {
            return addDate;
        }
        
        
        //setter
        public void setAdvertisementId(String advertisementId) {
            this.advertisementId = advertisementId;
        }
        
        public void setUserId(String userId) {
            this.userId = userId;
        }
        
        public void setMediaId(String mediaId) {
            this.mediaId =  mediaId;
        }
        
        public void setAdvertiser(String advertiser) {
            this.advertiser = advertiser;
        }
        
        public void setAdAgency(String adAgency) {
            this.adAgency  =  adAgency;
        }
        
        public void setAgencyFirstName(String agencyFirstName) {
            this.agencyFirstName  = agencyFirstName;
        }
        
        public void setAgencyMiddleName(String agencyMiddleName) {
            this.agencyMiddleName = agencyMiddleName;
        }
        
        public void setAgencyLastName(String agencyLastName) {
        this.agencyLastName = agencyLastName;
        }
        public void setAgencyPhone(String agencyPhone ) {
            this.agencyPhone = agencyPhone;
        }
        
        public void setAgencyFax(String agencyFax) {
            this.agencyFax = agencyFax;
        }
        public void setAgencyEmail(String agencyEmail ) {
        this.agencyEmail = agencyEmail;
        }
        
        public void setAgencyAddress(String agencyAddress) {
            this.agencyAddress = agencyAddress;
        }
       
        public void setAgencySuite(String agencySuite) {
            this.agencySuite = agencySuite;
        }
        
        public void setAgencyCity(String agencyCity)  {
            this.agencyCity = agencyCity;
        }
        
        public void setAgencyState(String agencyState) {
            this.agencyState = agencyState;
        }
        
        public void setAgencyCountry(String agencyCountry) {
            this.agencyCountry = agencyCountry;
        }
        
        public void setAgencyZip(String agencyZip) {
            this.agencyZip = agencyZip;
        }
        
        public void setInvoiceTo(String invoiceTo) {
            this.invoiceTo = invoiceTo;
        }
        
        public void setComments(String comments) {
            this.comments = comments;
        }
        
        public void setAdvPosted(boolean advPosted)	{
            this.advPosted = advPosted;
        }
        
        public void setAdvPostedDate(Date advPostedDate)	{
            this.advPostedDate = advPostedDate;
        }
        
        public void setMaterialComingFrom(String materialComingFrom)	{
            this.materialComingFrom = materialComingFrom;
        }
        
        public void setMaterialComingDate(Date materialComingDate) 	{
            this.materialComingDate = materialComingDate;
        }
        
        public void setAdvSuppliedOn(String advSuppliedOn)	{
            this.advSuppliedOn = advSuppliedOn;
        }
        
        public void setSalesPersonId(String salesPersonId)	{
            this.salesPersonId  = salesPersonId;
        }
        
        public void setRequestStatus(String requestStatus)	{
            this.requestStatus = requestStatus;
        }
        
        public void setActiveStatus(boolean activeStatus)	{
            this.activeStatus = activeStatus;
        }
        
        public void setAddDate(Date addDate) {
            this.addDate =  addDate;
        }
        
}
