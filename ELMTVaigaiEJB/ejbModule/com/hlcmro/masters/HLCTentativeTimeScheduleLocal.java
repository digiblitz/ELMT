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
import java.util.Date;

/**
 * This is the local interface for TentativeTimeSchedule enterprise bean.
 */
public interface HLCTentativeTimeScheduleLocal extends EJBLocalObject, HLCTentativeTimeScheduleLocalBusiness {
    //getter
    public String getTimeScheduleId();
    public String getEventId();
    public Date getDay();
    public String getPhase();
    public String getTime();

    //setter
    public void setTimeScheduleId(String timeScheduleId);
    public void setEventId(String eventId);
    public void setDay(Date day);
    public void setPhase(String phase);
    public void setTime(String time);
}
