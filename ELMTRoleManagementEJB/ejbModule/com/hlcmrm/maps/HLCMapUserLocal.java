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
package com.hlcmrm.maps;

import javax.ejb.EJBLocalObject;


/**
 * This is the local interface for MapUser enterprise bean.
 */
public interface HLCMapUserLocal extends EJBLocalObject, HLCMapUserLocalBusiness {
        public void setUserMapId(String userMapId);
        public void setUserId(String userId);
        public void setMapPrivilegeId(String mapPrivilegeId);
        public void setUserPrivilegePath(String userPrivilegePath);
        public void setActiveStatus(String activeStatus);
        public String getUserMapId();
        public String getUserId();
        public String getMapPrivilegeId();
        public String getUserPrivilegePath();
        public String isActiveStatus();
}
