/*
 * HorseSearch2VO.java
 *
 * Created on February 28, 2007, 2:24 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlccommon.util;

import java.io.Serializable;
/**
 *
 * @author Suresh
 */
public class HLCHorseSearch2VO implements Serializable{

    /** Creates a new instance of HorseSearch2VO */
    public HLCHorseSearch2VO() {
    }
    
    private String riderFirstName;
    private String riderLastName;
    private String ownerFirstName;
    private String ownerlastName;
    private String horseMemberId;
    private String horseName;
    private String membershipTypeId;
    private String membershipTypeName;
    private String statusName;
    private String colorDesc;
    private String gender;
    private String height;
    private String yearFoaled;
    private String secondBreed;
    private String breedDesc;
    private String ownerId;
    private String riderMemberId;
    
    
    public void setRiderFirstName(String riderFirstName) {
        this.riderFirstName = riderFirstName;
    }

    public void setRiderLastName(String riderLastName) {
        this.riderLastName = riderLastName;
    }

    public void setOwnerFirstName(String ownerFirstName) {
        this.ownerFirstName = ownerFirstName;
    }

    public void setOwnerlastName(String ownerlastName) {
        this.ownerlastName = ownerlastName;
    }

    public void setHorseMemberId(String horseMemberId) {
        this.horseMemberId = horseMemberId;
    }

    public void setHorseName(String horseName) {
        this.horseName = horseName;
    }

    public void setMembershipTypeId(String membershipTypeId) {
        this.membershipTypeId = membershipTypeId;
    }

    public void setMembershipTypeName(String membershipTypeName) {
        this.membershipTypeName = membershipTypeName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
    
    public String getRiderFirstName() {
        return riderFirstName;
    }

    public String getRiderLastName() {
        return riderLastName;
    }

    public String getOwnerFirstName() {
        return ownerFirstName;
    }

    public String getOwnerlastName() {
        return ownerlastName;
    }

    public String getHorseMemberId() {
        return horseMemberId;
    }

    public String getHorseName() {
        return horseName;
    }

    public String getMembershipTypeId() {
        return membershipTypeId;
    }

    public String getMembershipTypeName() {
        return membershipTypeName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setColorDesc(String colorDesc) {
        this.colorDesc = colorDesc;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setYearFoaled(String yearFoaled) {
        this.yearFoaled = yearFoaled;
    }

    public void setBreedDesc(String breedDesc) {
        this.breedDesc = breedDesc;
    }
     public void setSecondBreed(String secondBreed) {
        this.secondBreed = secondBreed;
    }

    public String getColorDesc() {
        return colorDesc;
    }

    public String getGender() {
        return gender;
    }

    public String getHeight() {
        return height;
    }

    public String getYearFoaled() {
        return yearFoaled;
    }

    public String getBreedDesc() {
        return breedDesc;
    }
    
    public String getSecondBreed() {
        return secondBreed;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public void setRiderMemberId(String riderMemberId) {
        this.riderMemberId = riderMemberId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getRiderMemberId() {
        return riderMemberId;
    }
}
