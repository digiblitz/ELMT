/*
 * PrimaryKeyException.java
 *
 * Created on August 25, 2006, 11:53 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmrm.Exception;

/**
 *
 * @author harmohan
 */
public class HLCPrimaryKeyException extends Exception {
    
    /** Creates a new instance of PrimaryKeyException */
    public HLCPrimaryKeyException() {
    }
    
    public HLCPrimaryKeyException(String msg){
        super(msg);
    }
    
}
