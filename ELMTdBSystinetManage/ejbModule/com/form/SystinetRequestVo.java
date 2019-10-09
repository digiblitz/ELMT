/*
 * UserMaster.java
 *
 * Created on August 24, 2006, 1:02 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.form;
import java.io.Serializable;
import java.util.*;

/**
 *
 * @author harmohan
 */
public class SystinetRequestVo implements Serializable {
  
    public String reqId;

    public String artUid;
    public String reqTitle;
    public String date1;


    private String date2;

    private String curVersion;
    private String modVersion;
    private String reqPri;
    private String reqDesc;
    

    private String stageName;
    private int stageNo;
    private String artifactId;
    
    
	public String getStageName() {
		return stageName;
	}
	public void setStageName(String stageName) {
		this.stageName = stageName;
	}
	public int getStageNo() {
		return stageNo;
	}
	public void setStageNo(int stageNo) {
		this.stageNo = stageNo;
	}
	public String getArtifactId() {
		return artifactId;
	}
	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}
	public String getReqId() {
		return reqId;
	}
	public void setReqId(String reqId) {
		this.reqId = reqId;
	}
	public String getReqTitle() {
		return reqTitle;
	}
	public void setReqTitle(String reqTitle) {
		this.reqTitle = reqTitle;
	}
	public String getDate1() {
		return date1;
	}
	public void setDate1(String date1) {
		this.date1 = date1;
	}
	public String getDate2() {
		return date2;
	}
	public void setDate2(String date2) {
		this.date2 = date2;
	}
	public String getCurVersion() {
		return curVersion;
	}
	public void setCurVersion(String curVersion) {
		this.curVersion = curVersion;
	}
	public String getModVersion() {
		return modVersion;
	}
	public void setModVersion(String modVersion) {
		this.modVersion = modVersion;
	}
	public String getReqPri() {
		return reqPri;
	}
	public void setReqPri(String reqPri) {
		this.reqPri = reqPri;
	}
	public String getReqDesc() {
		return reqDesc;
	}
	public void setReqDesc(String reqDesc) {
		this.reqDesc = reqDesc;
	}
	public String getArtUid() {
		return artUid;
	}
	public void setArtUid(String artUid) {
		this.artUid = artUid;
	}
   
    
}
