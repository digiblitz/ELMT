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
/*  Program Name    : MenuListVO.java
 *  Created Date    : October 10, 2006, 1:32 PM
 *  Author          : Suresh.K
 *  Version         : 1.20
 *  CopyrightInformation:
    (c)2006,digiBlitz Technologies Inc/digiBlitz Technologies (P) Ltd. All rights reserved.
    916 W. Broad Street Suite 205, FallsChurch, VA 22046.

    This document is protected by copyright. No part of this document may be reproduced in any form by any means without
    prior written authorization of Sun and its licensors. if any.

    The information described in this document may be protected by one or more U.S.patents.foreign patents,or
    pending applications.
 */

package com.hlccommon.util;


import java.io.*;

public class HLCMenuListVO implements Serializable {

    /** Creates a new instance of MenuListVO */
    public HLCMenuListVO() {
    }

    private String roleId;
    private String entityId;
    private String privilegeId;
    private String permissionId;
    private String subPermissionId;
    private String roleName;
    private String entityName;
    private String privilegeName;
    private String permissionName;
    private String subPermissionName;
    private String accessName;
    private String accessUrl;
    private String mapPermissionId;
    
    //
    
    private String masterId;
    private String lobLayerId;
    private String viewPtId;
    private String orgAssetId;
    private String processId;
    private String processDomainId;
    private String domainId;
    
    private int sequence;
    private String artifactId;
    private String artifactDesc;
    private String groupsId;
    private String viewId;

    
    
    public String getViewId() {
		return viewId;
	}

	public void setViewId(String viewId) {
		this.viewId = viewId;
	}

	public String getGroupsId() {
		return groupsId;
	}

	public void setGroupsId(String groupsId) {
		this.groupsId = groupsId;
	}

	

	public String getProcessDomainId() {
		return processDomainId;
	}

	public void setProcessDomainId(String processDomainId) {
		this.processDomainId = processDomainId;
	}

	public void setMasterId(String masterId) {
        this.masterId = masterId;
    }
    
    public void setLobLayerId(String lobLayerId) {
        this.lobLayerId = lobLayerId;
    }
    public void setViewPtId(String viewPtId) {
        this.viewPtId = viewPtId;
    }
    public void setOrgAssetId(String orgAssetId) {
        this.orgAssetId = orgAssetId;
    }
    public void setProcessId(String processId) {
        this.processId = processId;
    }
    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }
    
    public void setSequence(int sequence) {
        this.sequence = sequence;
    }
    
    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }
    public void setArtifactDesc(String artifactDesc) {
        this.artifactDesc = artifactDesc;
    }
    
    
    
    public String getMasterId() {
        return masterId;
    }
   
   public String getLobLayerId() {
       return lobLayerId;
   }
   
   public String getViewPtId() {
       return viewPtId;
   }
   public String getOrgAssetId() {
       return orgAssetId;
   }
   
   public String getProcessId() {
       return processId;
   }
   public String getDomainId() {
       return domainId;
   }
   
    public String getArtifactId() {
        return artifactId;
    }

    public String getArtifactDesc() {
        return artifactDesc;
    }
    
    public int getSequence() {
        return sequence;
    }
    
    
   
   public String getRoleId() {
        return roleId;
    }
   
   public String getMapPermissionId() {
       return mapPermissionId;
   }

    public String getEntityId() {
        return entityId;
    }

    public String getPrivilegeId() {
        return privilegeId;
    }

    public String getPermissionId() {
        return permissionId;
    }

     public String getSubPermissionId() {
        return subPermissionId;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getEntityName() {
        return entityName;
    }

    public String getPrivilegeName() {
        return privilegeName;
    }

    public String getPermissionName() {
        return permissionName;
    }

     public String getSubPermissionName() {
        return subPermissionName;
    }

    public String getAccessName() {
        return accessName;
    }

    public String getAccessUrl() {
        return accessUrl;
    }


   

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
    
    public void setMapPermissionId(String mapPermissionId) {
        this.mapPermissionId = mapPermissionId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public void setPrivilegeId(String privilegeId) {
        this.privilegeId = privilegeId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

     public void setSubPermissionId(String subPermissionId) {
        this.subPermissionId = subPermissionId;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public void setPrivilegeName(String privilegeName) {
        this.privilegeName = privilegeName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public void setSubPermissionName(String subPermissionName) {
        this.subPermissionName = subPermissionName;
    }

    public void setAccessName(String accessName) {
        this.accessName = accessName;
    }

    public void setAccessUrl(String accessUrl) {
        this.accessUrl = accessUrl;
    }


    public String toString(){
        StringBuffer sb = new StringBuffer()
        .append("roleId: " + roleId + "\n")
        .append("entityId: " + entityId + "\n")
        .append("privilegeId: " + privilegeId + "\n")
        .append("permissionId: " + permissionId + "\n")
        .append("subPermissionId: " + subPermissionId + "\n")
        .append("roleName: " + roleName + "\n")
        .append("entityName: " + entityName + "\n")
        .append("privilegeName: " + privilegeName + "\n")
        .append("permissionName: " + permissionName + "\n")
        .append("subPermissionName: " + subPermissionName + "\n")
        .append("accessName: " + accessName + "\n")
        .append("accessUrl: " + accessUrl + "\n");
        return sb.toString();
    }






}

