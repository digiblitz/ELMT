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
package com.hlcmrm.rolemanagement.masters;

import javax.ejb.EJBLocalObject;


/**
 * This is the local interface for Privilege enterprise bean.
 */
public interface HLCPrivilegeLocal extends EJBLocalObject, HLCPrivilegeLocalBusiness {
    public void setPrivilegeId(String privilegeId);
    public void setPrivilegeName(String privilegeName);
    public void setPrivilegePath(String privilegePath);
    public void setActiveStatus(boolean activeStatus);
    public String getPrivilegeId();
    public String getPrivilegeName();
    public String getPrivilegePath();
    public boolean isActiveStatus();
}
