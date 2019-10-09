/*
 * HorseColorVO.java
 *
 * Created on November 20, 2006, 11:49 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmrm.util;

/**
 *
 * @author suresh
 */
public class HLCHorseColorVO implements java.io.Serializable{
    
    /** Creates a new instance of HorseColorVO */
    public HLCHorseColorVO() {
    }
    
    private String colorId ;
    private String colorCode ;
    private String colorDesc;
	/*<!--Start:[DemographicDetails] For Demographic Type Addition, Editing and Deletion changes dated 11-Apr-2011 by Deepa-->*/

    private String demoId ;
    private String demoDescription ;
    private String demoType;
    private String demographic;
    private String status ;
	/*<!--End:[DemographicDetails] For Demographic Type Addition, Editing and Deletion changes dated 11-Apr-2011 by Deepa-->*/

	/*<!--Start:[DemographicDetails] For Demographic Type Addition, Editing and Deletion changes dated 11-Apr-2011 by Deepa-->*/


    public String getDemoDescription() {
        return demoDescription;
    }

    public void setDemoDescription(String demoDescription) {
        this.demoDescription = demoDescription;
    }

    public String getDemoId() {
        return demoId;
    }

    public void setDemoId(String demoId) {
        this.demoId = demoId;
    }

    public String getDemoType() {
        return demoType;
    }

    public void setDemoType(String demoType) {
        this.demoType = demoType;
    }

    public String getDemographic() {
        return demographic;
    }

    public void setDemographic(String demographic) {
        this.demographic = demographic;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
/*<!--End:[DemographicDetails] For Demographic Type Addition, Editing and Deletion changes dated 11-Apr-2011 by Deepa-->*/

    
    
    public void setColorId(String colorId) {
        this.colorId = colorId;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public void setColorDesc(String colorDesc) {
        this.colorDesc = colorDesc;
    }

    public String getColorId() {
        return colorId;
    }

    public String getColorCode() {
        return colorCode;
    }

    public String getColorDesc() {
        return colorDesc;
    }
    
    
    
}
