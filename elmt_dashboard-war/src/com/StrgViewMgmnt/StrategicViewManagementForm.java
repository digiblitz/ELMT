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
package com.StrgViewMgmnt;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class StrategicViewManagementForm extends ActionForm
{

    public StrategicViewManagementForm()
    {
    }
    private FormFile fileUpload;
    public FormFile getFileUpload()
    {
        return fileUpload;
    }

    public void setFileUpload(FormFile fileUpload)
    {
        this.fileUpload = fileUpload;
    }

    
}
