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
package com.hlcmro.masters;

import javax.ejb.EJBLocalObject;


/**
 * This is the local interface for JudgeDetail enterprise bean.
 */
public interface HLCJudgeDetailLocal extends EJBLocalObject, HLCJudgeDetailLocalBusiness {
    // getters
    public String getJudgeId();
    public String getEventId();
    public String getJudgeTypeId();
    public String getJudgeNames();

    //setter
    public void setJudgeId(String judgeId);
    public void setEventId(String eventId);
    public void setJudgeTypeId(String judgeTypeId);
    public void setJudgeNames(String judgeNames);
}
