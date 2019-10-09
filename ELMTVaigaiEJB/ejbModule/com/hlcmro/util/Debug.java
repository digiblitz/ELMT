/*
 * Debug.java
 *
 * Created on August 12, 2006, 5:00 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmro.util;

/**
 *
 * @author suresh
 */

    public final class Debug {
    public static final boolean debuggingOn = true;

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

