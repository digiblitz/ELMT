/*
 * PublicationVO.java
 *
 * Created on November 21, 2006, 3:09 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmrm.util;

/**
 *
 * @author punitha
 */
public class HLCPublicationVO implements java.io.Serializable{
    
    /** Creates a new instance of PublicationVO */
    public HLCPublicationVO() {
    }
 
    private String publicationId;
    private String publicationName;
 
    public void setPublicationId(String publicationId) {
        this.publicationId = publicationId;
    }

    public void setPublicationName(String publicationName) {
        this.publicationName = publicationName;
    }

    public String getPublicationId() {
        return publicationId;
    }

    public String getPublicationName() {
        return publicationName;
    }
    
    
 }
