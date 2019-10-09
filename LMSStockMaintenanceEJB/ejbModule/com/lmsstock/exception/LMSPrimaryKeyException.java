/*
 * PrimaryKeyException.java
 *
 * Created on August 24, 2006, 12:38 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.lmsstock.exception;

/**
 *
 * @author harmohan
 */
public class LMSPrimaryKeyException extends Exception {
    
    /** Creates a new instance of PrimaryKeyException */
    public LMSPrimaryKeyException() {
    }
    
    public LMSPrimaryKeyException(String msg) {
        super(msg);
    }
    
}
