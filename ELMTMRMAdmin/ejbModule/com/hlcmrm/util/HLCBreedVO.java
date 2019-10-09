/*
 * BreedVO.java
 *
 * Created on November 20, 2006, 11:52 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
//Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 12-Mar-2011
package com.hlcmrm.util;

/**
 *
 * @author suresh
 */
public class HLCBreedVO implements java.io.Serializable{
    
    /** Creates a new instance of BreedVO */
    public HLCBreedVO() {
    }
    private String breedId;
    private String breedCode;
    private String breedDesc;
    private String breedStatus;
    private String breedspiceid;
     //Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 10-Mar-2011
    private String UsertypeName;
    private String userTypeId;
    private String userTypeStatus;
    private String flag;
    private String charId;
    private String charStatus;
    private String charDesc;
 //Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 10-Mar-2011
    public String getBreedStatus() {
        return breedStatus;
    }

    public String getCharDesc() {
        return charDesc;
    }

    public void setCharDesc(String charDesc) {
        this.charDesc = charDesc;
    }

    public String getCharStatus() {
        return charStatus;
    }

    public void setCharStatus(String charStatus) {
        this.charStatus = charStatus;
    }

    public String getCharId() {
        return charId;
    }

    public void setCharId(String charId) {
        this.charId = charId;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getUserTypeStatus() {
        return userTypeStatus;
    }

    public void setUserTypeStatus(String userTypeStatus) {
        this.userTypeStatus = userTypeStatus;
    }

    public String getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(String userTypeId) {
        this.userTypeId = userTypeId;
    }
 //Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 10-Mar-2011
    public void setBreedStatus(String breedStatus) {
        this.breedStatus = breedStatus;
    }

    public void setBreedId(String breedId) {
        this.breedId = breedId;
    }
 //Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 10-Mar-2011
    public void setBreedCode(String breedCode) {
        this.breedCode = breedCode;
    }
 //Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 10-Mar-2011
    public void setBreedDesc(String breedDesc) {
        this.breedDesc = breedDesc;
    }

    public String getBreedId() {
        return breedId;
    }

    public String getBreedCode() {
        return breedCode;
    }

    public String getBreedDesc() {
        return breedDesc;
    }
    public String getBreedSpecieId() {
        return breedspiceid;
    }
 //Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 10-Mar-2011
    public void setBreedSpecieId(String breedspiceid) {
        this.breedspiceid = breedspiceid;
 //Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 10-Mar-2011
    }
    public String getUserTybeName() {
        return UsertypeName;
    }
 //Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 10-Mar-2011
    public void setUserTybeName(String UsertypeName) {
        this.UsertypeName = UsertypeName;
    }
}
//End:[BreedMgt] For breed Addition, Editing and Deletion changes dated 10-Mar-2011
