/*
 * Debug.java
 *
 * Created on August 25, 2006, 11:57 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlchorse.form.util;

/**
 *
 * @author harmohan
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
