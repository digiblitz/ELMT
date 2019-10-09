/*
 * Debug.java
 *
 * Created on August 29, 2006, 11:24 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmember.type.util;

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
}