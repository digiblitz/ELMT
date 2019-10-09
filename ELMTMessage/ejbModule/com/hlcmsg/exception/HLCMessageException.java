/*
 * MessageException.java
 *
 * Created on September 2, 2006, 1:08 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmsg.exception;

/**
 *
 * @author harmohan
 */
public class HLCMessageException extends Exception {
    
    /** Creates a new instance of MessageException */
    public HLCMessageException() {
    }
    
    public HLCMessageException(String msg){
        super(msg);
    }
    
}
