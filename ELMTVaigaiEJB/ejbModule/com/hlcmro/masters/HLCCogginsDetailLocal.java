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
 * This is the local interface for CogginsDetail enterprise bean.
 */
public interface HLCCogginsDetailLocal extends EJBLocalObject, HLCCogginsDetailLocalBusiness {
    public String getCogginsId();
    public String getEventId();
    public String getAllState();
    public String getInState();
    public String getOutOfState();
    public String getNoState();
    public String getOthers();
    //Setters methods
    public void setCogginsId(String cogginsId);
    public void setEventId(String eventId);
    public void setAllState(String allState);
    public void setInState(String inState);
    public void setOutOfState(String outOfState) ;
    public void setNoState(String noState);
    public void setOther(String others);
}
