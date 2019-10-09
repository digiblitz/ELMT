/*
 * FileDeletion.java
 *
 * Created on May 31, 2007, 2:30 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcaccounts.util;
import java.io.*;

/**
 *
 * @author suresh
 */
public final class HLCFileDeletion {
    
    /** Creates a new instance of FileDeletion */
    public HLCFileDeletion() {
    }
    
    public static final boolean deleteFile(String s) {
        boolean result = false;
        try{
            File file = new File(s);
            Debug.print("File Path in FileDeletion.deleteFile:"+ s);
            if(file.exists()) {
                result = file.delete();
                Debug.print("File Delete Status in FileDeletion.deleteFile:"+ result);
            }
        }catch(Exception e){
            Debug.print("Exception in FileDeletion.deleteFile:" + e.getMessage());
        }
        return result;
    }
}
