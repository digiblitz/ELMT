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
/*  Program Name    : DBHelper.java
 *  Created Date    : Sep 5, 2006 10:57:08 AM
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

import javax.sql.*;
import java.sql.*;

/**
 *
 * @author suresh
 */
public final class DBHelper {
    
    public static final String USEA_ORG_RENEWAL = "tblMeeEventOrganizerRenewalDetails"; 
    public static final String USEA_PAYMENT = "tblUserPaymentDetails";
    public static final String USEA_MMS_USERMASTER = "tblUserMaster"; 
    public static final String USEA_MMS_TYPEMASTER = "tblUserTypeMaster"; 
    public static final String USEA_MMS_MEMBERDETAIL = "tblMemberDetails";
    public static final String USEA_SPNR_BILL = "tblBillingScheduleDetails"; 
    public static final String USEA_SPNR_SPONSOR = "tblSponsors";
    public static final String USEA_SPNR_SPONSORPLAN = "tblSponsorshipPlanMaster";
    public static final String USEA_SPNR_SCHE_MASTER = "tblBillingScheduleMaster";
    public static final String USEA_SPNR_SCHE_DETAILS = "tblBillingScheduleDetails"; 
    
    public static final String USEA_ADV_MEDIA = "tblAdvMediaMaster";
    public static final String USEA_ADV_DIMENSION_MASTER = "tblAdvDimensionTypeMaster"; 
    public static final String USEA_ADV_ISSUE_MASTER = "tblAdvIssueMaster";
    
    public static final String USEA_ADV_DISPLAY_TYPE = "tblAdvDisplayTypeMaster";
    public static final String USEA_ADV_DISPLAY_SUB_TYPE = "tblAdvDisplaySubTypes"; 
    public static final String USEA_ADV_DIMENSION_DETAILS = "tblAdvDimensionDetails";
    public static final String USEA_ADV_MAP_PRICE = "tblAdvMapPrice";
    
    public static final String USEA_ADV_ADS_DETAILS = "tblAdvertisementDetails";
    public static final String USEA_ADV_ADVERTISER = "tblAdvertisers";
    
    public static final String USEA_ROLE_PERMISSION_MASTER = "tblPermissionMaster";
    public static final String USEA_ROLE_PRIVILEGE_MASTER = "tblPrivilegeMaster";
    public static final String USEA_ROLE_ENTITY_MASTER = "tblEntityMaster";

    
    public static final String USEA_ROLE_MAP_PERMISSION = "tblMapPermission";
    public static final String USEA_ROLE_MAP_PRIVILEGE = "tblMapPrivilege";

    public static final String USEA_ROLE_ROLE_MASTER = "tblRoleMaster";
    public static final String USEA_ROLE_MAP_ENTITY = "tblMapEntity";
    
    public static final String USEA_ROLE_MAP_ROLE = "tblMapRole";
    public static final String USEA_USER_MAP_PRIVILEGE = "tblMapUserPrivilege";
    
    
public static final String USEA_USER_MAP_SUB_PRIVILEGE = "tblSubMenuList";
public static final String USEA_ARTIFACT_MASTER = "tblArtifactMaster";
    

    
    
    
    

 /*   public static final long getNextEventId(Connection con) throws SQLException ,MissingPrimaryKeyException{
        Debug.print("DBHelper getNextAccountId");
        return getNextId(con,"tblMeeEventDetails");
    }

    private static final long getNextId(Connection con,String table)
    throws SQLException ,MissingPrimaryKeyException {
    Debug.print("DBHelper getNextId");
    String selectStatement = "SELECT max(event_id) from " + table + " WHERE event_id = ? ";

    // String updateStatement = "update " + table + " " + "set id = id + 1 ";

    PreparedStatement prepSelect = con.prepareStatement(selectStatement);

    ResultSet rs = prepSelect.executeQuery();
    rs.next();

    long nextId = rs.getLong(1);
    if(nextId==0){
    nextId = 10000;
    }
    else{
    nextId = nextId+1;
    }

    rs.close();
    prepSelect.close();

    // if (nextId <= "") {
    //throw new MissingPrimaryKeyException("Table " + table +
       // " is empty.");
    //  }

    return nextId;
    }

    public static final java.sql.Date toSQLDate(java.util.Date inDate) {
        return new java.sql.Date(inDate.getTime());
    }
    */
}
