/*
 * PrimaryKeyException.java
 *
 * Created on August 13, 2006, 4:08 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmro.exception;

/**
 *
 * @author suresh
 */
public class HLCMissingPrimaryKeyException extends Exception {
    
    /** Creates a new instance of MissingPrimaryKeyException */
    public HLCMissingPrimaryKeyException() {
    }
    
     public HLCMissingPrimaryKeyException(String msg) {
        super(msg);
    }
    
}
