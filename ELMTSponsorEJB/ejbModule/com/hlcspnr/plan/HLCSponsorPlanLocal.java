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
package com.hlcspnr.plan;

import javax.ejb.EJBLocalObject;
import java.util.*;


/**
 * This is the local interface for SponsorPlan enterprise bean.
 */
public interface HLCSponsorPlanLocal extends EJBLocalObject, HLCSponsorPlanLocalBusiness {
        public Collection getPlan();
        public void setPlanId(String planId);
        public void setPlanName(String planName);
        public void setPlanDescription(String planDescription);
        public void setPlanAmount(String planAmount);
        public String getPlanId();
        public String getPlanName();
        public String getPlanDescription();
        public String getPlanAmount();        
}
