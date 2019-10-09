/*
 * PrimaryKeyException.java
 *
 * Created on August 30, 2006, 8:18 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmeeting.exception;

/**
 *
 * @author harmohan
 */
public class HLCPrimaryKeyException extends Exception {
    
    /** Creates a new instance of PrimaryKeyException */
    public HLCPrimaryKeyException() {
    }
    
    public HLCPrimaryKeyException(String msg) {
        super(msg);
    }
    
}
