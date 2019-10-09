/*
 * Debug.java
 *
 * Created on April 12, 2007, 6:28 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlccommon.util;

/**
 *
 * @author Suresh
 */
public class Debug {
    
    public static final boolean debuggingOn = true;
    /** Creates a new instance of Debug */
    public Debug() {
    }
 
    public static final void print(String msg) {
        if (debuggingOn) {
            System.out.println("Debug: " + msg);
        }
    }

    public static final void print(String msg, Object object) {
        if (debuggingOn) {
            System.err.println("Debug: " + msg);
            System.err.println("       " + object.getClass().getName());
        }
    }
    
}