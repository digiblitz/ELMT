/*
 * MissingPrimaryKeyException.java
 *
 * Created on August 21, 2006, 10:44 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcreg.exception;

/**
 *
 * @author harmohan
 */
public class HLCMissingPrimaryKeyException extends Exception{
    
    /** Creates a new instance of MissingPrimaryKeyException */
    public HLCMissingPrimaryKeyException() {
    }
    
    public HLCMissingPrimaryKeyException(String msg) {
        super(msg);
    }
    
}
