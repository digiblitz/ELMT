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
import java.util.Collection;
import com.hlcmro.util.HLCOtherDetailsVO;
import java.util.Date;


/**
 * This is the local interface for OtherDetail enterprise bean.
 */
public interface HLCOtherDetailLocal extends EJBLocalObject, HLCOtherDetailLocalBusiness {
     public HLCOtherDetailsVO getOtherDetailsVO();
    
     //Setters methods

    public void setOtherId(String otherId);
    public void setEventId(String eventId);
    public void setCourseCloseDate(Date courseCloseDate);
    public void setEntryLimit(String entryLimit);
    public void setRidersHorseLevelLimit(String ridersHorseLevelLimit);
    public void setRidersHorseEntireLimit(String ridersHorseEntireLimit);
    public void setDivisionEntryBirthDate(Date divisionEntryBirthDate);
    public void setLeaseDogs(boolean leaseDogs);
    public void setTeamCompetition(String teamCompetition);
    public void setPerTimePrice(String perTimePrice);
    public void setOtherTeamInfo(String otherTeamInfo);
    public void setPartyName(String  partyName);
    public void setAddInfo(String addInfo);
    
 //getter
    public String getOtherId();
    public String getEventId();
    public Date getCourseCloseDate();
    public String getEntryLimit();
    public String getRidersHorseLevelLimit();
    public String getRidersHorseEntireLimit();
    public Date getDivisionEntryBirthDate();
    public boolean isLeaseDogs();
    public String getTeamCompetition();
    public String getPerTimePrice();
    public String getOtherTeamInfo();
    public String getPartyName();
    public String getAddInfo();
    
}
