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
package com.mrm.actionform;
/*
 * InsertOrgHLCEventRegActionForm.java
 *
 * Created on August 17, 2006, 4:57 PM
 */

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author karthikeyan
 * @version
 */

public class InsertOrgHLCEventRegActionForm extends org.apache.struts.action.ActionForm {
    
    private String esecid;
       
    /**
     * @return
     */
   public void setEventsecid(String str) {
        esecid = str;
    }
   
    public String getEventsecid() {
        return esecid;
    }
   
 public void set(String str) {
        esecid = str;
    }
   
    public String get() {
        return esecid;
    }   

        
    }

