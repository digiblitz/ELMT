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
package com.view.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.upload.FormFile;

public class viewActionForm extends org.apache.struts.action.ActionForm{
	/*private List formFiles = new ArrayList();

	public List getUploads() { 
		System.out.println("----------in getter----------");
		System.out.println("----------in getter----------"+formFiles.size());
		return this.formFiles; 
		}

	public void setUploads(FormFile uploads){
	System.out.println("----------in setter----------");
	   this.formFiles.add(uploads);
	}*/
	
	private List formFiles = new ArrayList();

    public List getUploads() {
    	System.out.println("----------in getter----------");
		System.out.println("----------in getter----------"+formFiles.size());
      return this.formFiles;
    }

    public void setUploads(int iIndex, FormFile formFile) {
    	System.out.println("----------in setter----------");
      if ( !formFile.getFileName().equals("") ) {
        if ( this.formFiles.size() <= iIndex ) {
          // Fill the list to the specified size
          for ( int i=this.formFiles.size(); i < iIndex; i++ ){
            this.formFiles.add(null);
          }
          this.formFiles.add(formFile);
        } else {
          this.formFiles.set(iIndex, formFile);
        }
      }
    } 
	    

}
