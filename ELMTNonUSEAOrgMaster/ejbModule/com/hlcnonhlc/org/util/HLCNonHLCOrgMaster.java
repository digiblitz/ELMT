/*
 * NonUSEAOrgMaster.java
 *
 * Created on August 28, 2006, 6:33 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcnonhlc.org.util;

/**
 *
 * @author harmohan
 */
public class HLCNonHLCOrgMaster implements java.io.Serializable {
	private String  nonuseaOrgId;
	private String nonuseaOrgName;

	public HLCNonHLCOrgMaster() {  }

	public HLCNonHLCOrgMaster(String  nonuseaOrgId,String nonuseaOrgName) {
		this.nonuseaOrgId = nonuseaOrgId;
		this.nonuseaOrgName = nonuseaOrgName;
	}

	//getter

	public  String  getNonuseaOrgId() {
		return nonuseaOrgId;
	}
	public  String  getNonuseaOrgName() {
		return nonuseaOrgName;
	}
	//setter
	public void setNonuseaOrgId(String nonuseaOrgId) {
		this.nonuseaOrgId = nonuseaOrgId;
    }

    public void setNonuseaOrgName(String nonuseaOrgName) {
		this.nonuseaOrgName = nonuseaOrgName;
    }

}

