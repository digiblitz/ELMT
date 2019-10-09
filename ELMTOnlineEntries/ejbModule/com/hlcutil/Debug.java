/*
 * Debug.java
 *
 * Created on October 29, 2007, 1:20 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcutil;

/**
 *
 * @author Dhivya
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
